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
import static org.light.portal.util.Constants._REPLICATION_SERVER_MAINTAIN_INTERVAL;
import static org.light.portal.util.Constants._REPLICATION_SERVER_NAME;
import static org.light.portal.util.Constants._REPLICATION_SERVER_PORT;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.light.portal.distribute.Message;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.distribute.ReplicationReceiver;
import org.light.portal.distribute.ReplicationServer;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class RMIReplicationServerImpl extends ReplicationAbstractImpl implements ReplicationServer{
	
	public RMIReplicationServerImpl(){		
    }
	
	public void init(){
		if(_REPLICATION_ENABLED){
			initServer();
			initReceiver();
			initPublisher();
			initMaintainer();
		}
	}
	
	private void initServer(){
		try{
			server = (InetAddress.getLocalHost()).getHostAddress()+":"+_REPLICATION_SERVER_PORT;
	        Registry registry = LocateRegistry.createRegistry(_REPLICATION_SERVER_PORT);
	        registry.rebind(_REPLICATION_SERVER_NAME, new RMIReplicationListenerImpl(this));
	        logger.info(String.format("replication Server startup at [%s]",server));			                				
        }
        catch(Exception e){
        	logger.error(e.getMessage());
        }  
	}
	private void initReceiver(){
		replicationReceiver.init();		
	}
	private void initPublisher(){
		replicationPublisher.init();		
	}
	private void initMaintainer(){		
		new Maintainer();
	}
	
	public boolean isReady(){
		return replicationPublisher.isReady() && replicationReceiver.isReady();
	}
	
	public void publish(Message message){
		replicationPublisher.process(message);
	}
	
	public void receive(Message message){
		replicationReceiver.process(message);
	}
	
	public void process(Message message){
		receive(message);
	}
	
	class Maintainer implements Runnable{
		
		public Maintainer(){				
			new Thread(this).start();
		}
		
		public void run(){
			while(true){
				try{					
					this.maintain();
					Thread.sleep(_REPLICATION_SERVER_MAINTAIN_INTERVAL);
				}catch(Throwable e){
					logger.error(e.getMessage());
				}
			}
		}
		
		private void maintain(){
			logger.info(String.format("maintaining replicaiton server..."));	
			processMessages(server);
		}						
	}
	
	public ReplicationReceiver getReplicationReceiver() {
		return replicationReceiver;
	}

	public void setReplicationReceiver(ReplicationReceiver replicationReceiver) {
		this.replicationReceiver = replicationReceiver;
	}

	public ReplicationPublisher getReplicationPublisher() {
		return replicationPublisher;
	}

	public void setReplicationPublisher(ReplicationPublisher replicationPublisher) {
		this.replicationPublisher = replicationPublisher;
	}

	private String server;	
	private ReplicationReceiver replicationReceiver;
	private ReplicationPublisher replicationPublisher;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
