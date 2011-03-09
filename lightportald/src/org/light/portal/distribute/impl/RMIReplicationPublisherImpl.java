 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.distribute.impl;

import static org.light.portal.util.Constants._REPLICATION_ENABLED;
import static org.light.portal.util.Constants._REPLICATION_HOSTS;
import static org.light.portal.util.Constants._REPLICATION_PUBLISHER_MAINTAIN_INTERVAL;
import static org.light.portal.util.Constants._REPLICATION_SERVER_NAME;
import static org.light.portal.util.Constants._REPLICATION_SERVER_PORT;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.light.portal.distribute.Event;
import org.light.portal.distribute.Message;
import org.light.portal.distribute.ReplicationListener;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Entity;


/**
 * 
 * @author Jianmin Liu
 **/
public class RMIReplicationPublisherImpl extends ReplicationAbstractImpl implements ReplicationPublisher {
		
	public RMIReplicationPublisherImpl(){		
	}
	
	public void init(){
		if(_REPLICATION_ENABLED){
			queue = new LinkedBlockingQueue<Message>();
			publisher = new Worker();
		}
	}
	
	public void connect(String server){
		new Connector(server);		
	}
	
	public void syncDone(String server){
		syncDoneCounter.addAndGet(1);
	}
	
	public boolean isReady(){
		return (publisher == null) || (syncDoneCounter.get() >= publisher.registries.size());
	}
	
	public void process(Message message){
		if(!_REPLICATION_ENABLED || queue == null) return;
		try{
			queue.put(message);
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	
	class Worker implements Runnable{
		private Set<Registry> registries = new HashSet<Registry>();
		private Map<Registry,String> registryMap = new HashMap<Registry,String>();
		private Map<String,Long> serverMap = new HashMap<String,Long>();
		private Set<String> offlineServers;
		private String[] servers;
		private int totalHosts;	
		private String local;
		
		public Worker(){					    	    
			new Thread(this).start();			
		}	
		
		public void run(){	
			init();
			while(true){
				try{					
					Message message = queue.take();
					this.execute(message);					
				}catch(Throwable e){
					logger.error(e.getMessage());
				}
			}
		}
		
		private void init(){
			servers = _REPLICATION_HOSTS.split(";");
			totalHosts = servers.length;				
			try{
		    	local = (InetAddress.getLocalHost()).getHostAddress();
		    }catch(Exception e){
		    	logger.error(e.getMessage());
    		}	
		    connect();
			new Maintainer();
		}
		
		private void connect(){			
			if(registries == null || registries.size() < totalHosts){
				lock.lock();
				if(registries == null || registries.size() < totalHosts){										    					    
				    if(servers != null){					  				    	
				    	for(String server : servers){
				    		connect(server);				    		
				       }
				    }				    
				}
				lock.unlock();
			}
		}

		private void connect(String server){
			if(!serverMap.containsKey(server)){
				lock.lock();
				String[] pair = server.split(":");
				String host = pair[0];
				int port = _REPLICATION_SERVER_PORT;
				if(pair.length > 1)
					port = Integer.parseInt(pair[1]);	
				else
					server = host+":"+port;
				if(!serverMap.containsKey(server)){
		    		if(!host.equals(local) || port != _REPLICATION_SERVER_PORT){
			    		try{								    	
					    	Registry registry=LocateRegistry.getRegistry(
											               host,
											               port
											       		);
					        // look up the replication listener
					    	ReplicationListener listener= (ReplicationListener)(registry.lookup(_REPLICATION_SERVER_NAME));
					        // connection the replication listener		            
					    	String thisServer = local+":"+_REPLICATION_SERVER_PORT;
						    String text=String.format("replication server from %s",thisServer);						    
						    listener.process(new Message(Event.CONNECTION,thisServer,text));		
						    listener = null;
						    if(registries == null) registries = new HashSet<Registry>();
					        registries.add(registry);
					        registryMap.put(registry,server);
					        serverMap.put(server,0L);
					        if(offlineServers != null) offlineServers.remove(server);
					        logger.info(String.format("connected with replication server %s",server));
			    		}catch(Exception e){
					    	logger.error(String.format("connect server %s failed: ",server,e.getMessage()));
					    	if(offlineServers == null) offlineServers = new HashSet<String>();
					    	offlineServers.add(server);
			    		}
		    		}								
				}
				lock.unlock();			
			}
		}

		private void execute(Message message){
			if(message.getServer() != null){
				Set<Entry<Registry,String>> map = registryMap.entrySet();
				for(Entry<Registry,String> entry : map){
					if(message.getServer().equals(entry.getValue())){
						publish(message,entry.getKey());
						break;
					}
				}
				
			}else{
				lock.lock();
				if(registries != null && registries.size() > 0){				
					for(Registry registry : registries){
						publish(message,registry);
					}
				}
				if(offlineServers != null && offlineServers.size() > 0){				
					for(String server : offlineServers){					
						saveMessage(server,message);
					}
				}
				lock.unlock();
			}
		}
		
		private void publish(Message message,Registry registry){
			try{
				// look up the remote object
				ReplicationListener listener = (ReplicationListener)(registry.lookup(_REPLICATION_SERVER_NAME));
		        // call the remote method
		       	logger.info(String.format("pulish replication message to server %s: %s",registryMap.get(registry),message.toString()));
		       	listener.process(message);
		       	listener= null;
		       	String server = registryMap.get(registry);
		       	serverMap.put(server,0L);
			}catch(Exception e){
				String server = registryMap.get(registry);
				long failedCount = serverMap.get(server) + 1;
				if(failedCount <= 5){							
					serverMap.put(server,failedCount);
					saveMessage(server,message);
				}else{
					registries.remove(registry);
					serverMap.remove(server);
					offlineServers.add(server);
				}
			}
		}
		private void saveMessage(String server,Message message){
			if(message.getEvent() == Event.ENTITY_UPDATE || message.getEvent() == Event.ENTITY_DELETE){
				try{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    ObjectOutputStream oout = new ObjectOutputStream(baos);
				    oout.writeObject(message);
				    oout.close();
				    saveMessage(server,message,baos.toByteArray());
					logger.info(String.format("save replication message for server %s: %s",server,message.toString()));		       				       				
				}catch(Exception e){
					e.printStackTrace();
					logger.error(String.format("save replication message for server %s failed: %s[Exception: %s]",server,message.toString(),e.getMessage()));		       		
				}
			}
		}
		
		private void saveMessage(String server,Message message, byte[] bytes) throws Exception{
			ConnectionProvider cp = null;
			Connection conn = null;
			PreparedStatement ps = null;
			try{
	        	SessionFactoryImplementor impl = (SessionFactoryImplementor)getPortalDao().getSessionFactory();
	        	cp = impl.getConnectionProvider();
	        	conn = cp.getConnection();
	        	conn.setAutoCommit(false);
	        	
	        	long orgId = 0;
	        	String className = "";
	        	long classId = 0;
	        	if(message.getBody() instanceof Entity){
	        		Entity entity = (Entity)message.getBody();
	        		orgId = entity.getOrgId();
	        		className = entity.getClass().getName();
	        		classId = entity.getId();
	        	}

	        	ps = conn.prepareStatement("insert into light_replication_message (orgId,server,event,className,classId,message,createDate) values(?,?,?,?,?,?,?);");	        	
	        	ps.setLong(1,orgId);
	        	ps.setString(2,server);
	        	ps.setString(3,message.getEvent().toString());
	        	ps.setString(4,className);
	        	ps.setLong(5,classId);
	        	ps.setBytes(6,bytes);
	        	ps.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
		        ps.executeUpdate();
	        	conn.commit();
	        	ps.close();
	        	conn.close();
	        }catch(Exception e){
	        	conn.rollback();
	        	ps.close();
	        	conn.close();
	        	e.printStackTrace();
	        	throw new Exception(e);
	        }
		}
		
		class Maintainer implements Runnable{
			
			public Maintainer(){
				new Thread(this).start();
			}
			
			public void run(){
				while(true){
					try{											
						Thread.sleep(_REPLICATION_PUBLISHER_MAINTAIN_INTERVAL);
						connect();
						maintain();
					}catch(Throwable e){
						logger.error(e.getMessage());
					}
				}
			}
			
			private void maintain(){		
				logger.info(String.format("maintaining replicaiton publisher..."));
				logger.info(String.format("retrieve saved messages from server %s", local+":"+_REPLICATION_SERVER_PORT));
				if(registries != null){	
					for(Registry registry : registries){
						String server = registryMap.get(registry);
						processMessages(server);
					}
				}
			}
		}
	}
	
	class Connector implements Runnable{
		private String server;
		public Connector(String server){
			this.server = server;
			new Thread(this).start();
		}
		
		public void run(){
			while(!isReady()){
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					
				}
			}
			publisher.connect(server);
			processMessages(server);
			process(new Message(Event.SYNC_DONE,server,null));
		}
	}

	private BlockingQueue<Message> queue;	
	private Worker publisher;
	private AtomicInteger syncDoneCounter = new AtomicInteger(0);
	private Lock lock = new ReentrantLock();		
	private Logger logger = LoggerFactory.getLogger(this.getClass());

}
