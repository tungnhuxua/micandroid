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

import static org.light.portal.util.Constants._CACHE_INSTANCE;
import static org.light.portal.util.Constants._DATABASE_INSTANCE;
import static org.light.portal.util.Constants._FILE_INSTANCE;
import static org.light.portal.util.Constants._INDEX_INSTANCE;
import static org.light.portal.util.Constants._REPLICATION_ENABLED;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_CACHE;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_DATABASE;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_FILE;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_INDEX;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.light.portal.cache.CacheObject;
import org.light.portal.cache.CacheService;
import org.light.portal.distribute.Event;
import org.light.portal.distribute.Message;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.distribute.ReplicationReceiver;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Entity;
import org.light.portal.model.Fileable;
import org.light.portal.search.Indexer;
import org.light.portal.util.FileUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class RMIReplicationReceiverImpl extends ReplicationAbstractImpl implements ReplicationReceiver{
	
	public RMIReplicationReceiverImpl(){		
		if(_REPLICATION_ENABLED){
			queue = new LinkedBlockingQueue<Message>();		        					              	      
		}
    }
	
	public void init(){
		if(_REPLICATION_ENABLED){
			new Worker();
		}
	}
	
	public boolean isReady(){
		return true;
	}
	
	public void process(Message message){
		logger.info(String.format("receive message: %s",message.toString()));
		try{
			queue.put(message);
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	
	class Worker implements Runnable{
		
		public Worker(){
			new Thread(this).start();
		}
		public void run(){
			while(true){
				try{					
					Message message = queue.take();
					this.execute(message);					
				}catch(Throwable e){
					logger.error(e.getMessage());
				}
			}
		}
		
		private void execute(Message message){
			logger.info(String.format("process message: %s",message.toString()));			
			if(message.getEvent() == Event.CONNECTION){
				doConnect(message);
			}else if(message.getEvent() == Event.SYNC_DONE){
				doSyncDone(message);
			}else if(message.getEvent() == Event.INDEX_ALL){
				doIndex(message);
			}else if(message.getEvent() == Event.ENTITY_UPDATE){
				doUpdate(message);
			}else if(message.getEvent() == Event.ENTITY_DELETE){
				doDelete(message);
			}else if(message.getEvent() == Event.CACHE_PUT){
				doCachePut(message);
			}else if(message.getEvent() == Event.CACHE_REMOVE){
				doCacheRemove(message);
			}
			
		}
		
		private void doConnect(Message message){
			replicationPublisher.connect(message.getServer());
		}
		
		private void doSyncDone(Message message){
			replicationPublisher.syncDone(message.getServer());
		}
		
		private void doIndex(Message message){
			if(_REPLICATION_ENABLED_INDEX && message.getIndexInstance() != _INDEX_INSTANCE){
				if(message.getBody() != null){
					try{
						Class klass = Class.forName((String)message.getBody());
						indexer.reIndex(klass,message.getOrgId(),false);
					}catch(Exception e){
						indexer.reIndex(message.getOrgId(),false);
					}
				}else
					indexer.reIndex(message.getOrgId(),false);
			}
		}
		
		private void doUpdate(Message message){			
			try{
				Entity entity = (Entity)message.getBody();
				if(_REPLICATION_ENABLED_DATABASE && message.getDatabaseInstance() != _DATABASE_INSTANCE) save(entity);
				if(_REPLICATION_ENABLED_INDEX && message.getIndexInstance() != _INDEX_INSTANCE) indexer.updateIndex(entity);					
				if(_REPLICATION_ENABLED_CACHE && message.getCacheInstance() != _CACHE_INSTANCE) cacheService.update(entity);
				if(_REPLICATION_ENABLED_FILE && message.getFileInstance() != _FILE_INSTANCE && message.getBody() instanceof Fileable){
					Fileable fileable = (Fileable)message.getBody();
					if(fileable.getFile() != null){
						String dir = fileable.getDir();
						FileUtil.saveFile(fileable.getFile(),fileable.getContentType(),dir);
					}
				}
				syncMessages(message);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		private void doDelete(Message message){
			try{
				Entity entity = (Entity)message.getBody();
				if(_REPLICATION_ENABLED_DATABASE && message.getDatabaseInstance() != _DATABASE_INSTANCE) getPortalDao().delete(entity,false);
				if(_REPLICATION_ENABLED_INDEX && message.getIndexInstance() != _INDEX_INSTANCE) indexer.deleteIndex(entity);
				if(_REPLICATION_ENABLED_CACHE && message.getCacheInstance() != _CACHE_INSTANCE) cacheService.delete(entity);
				if(_REPLICATION_ENABLED_FILE && message.getFileInstance() != _FILE_INSTANCE && message.getBody() instanceof Fileable){
					Fileable fileable = (Fileable)message.getBody();
					FileUtil.deleteFile(fileable.getFileUrl(),entity.getOrgId());	
				}
				syncMessages(message);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			
		private void doCachePut(Message message){
			try{
				CacheObject cacheObject = (CacheObject)message.getBody();
				if(_REPLICATION_ENABLED_CACHE && message.getCacheInstance() != _CACHE_INSTANCE) cacheService.put(cacheObject.getKey(),cacheObject,false);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		private void doCacheRemove(Message message){
			try{				
				Serializable key = message.getBody();
				if(_REPLICATION_ENABLED_CACHE && message.getCacheInstance() != _CACHE_INSTANCE) cacheService.remove(key,false);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		private void save(Entity entity){
			Entity originalEntity = null;
			if(entity.getVersion() > 0){
				originalEntity = getPortalDao().getEntityById(entity.getClass(),entity.getId());
			}
			if(originalEntity != null){
				entity.setVersion(originalEntity.getVersion());
			}else{
				entity.setVersion(-1);
			}
			getPortalDao().save(entity,false);
		}
		
	}
		
	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public Indexer getIndexer() {
		return indexer;
	}

	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	} 
	
	public ReplicationPublisher getReplicationPublisher() {
		return replicationPublisher;
	}

	public void setReplicationPublisher(ReplicationPublisher replicationPublisher) {
		this.replicationPublisher = replicationPublisher;
	}
	
	private BlockingQueue<Message> queue;	
	private Indexer indexer;
	private CacheService cacheService;
	private ReplicationPublisher replicationPublisher;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
