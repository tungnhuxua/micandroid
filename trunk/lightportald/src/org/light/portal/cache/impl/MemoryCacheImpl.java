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

package org.light.portal.cache.impl;

import static org.light.portal.util.Constants._CACHE_CAPABILITY;
import static org.light.portal.util.Constants._CACHE_ENABLED;
import static org.light.portal.util.Constants._CACHE_CLEAN_MAX_INTERVAL;
import static org.light.portal.util.Constants._CACHE_CLEAN_MIN_INTERVAL;
import static org.light.portal.util.Constants._CACHE_CLEAN_CAPABILITY_THRESHOLD;

import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.light.portal.cache.CacheObject;
import org.light.portal.cache.Cache;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class MemoryCacheImpl implements Cache {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	private ConcurrentHashMap<Object,CacheObject> cacheMap;
	
	public MemoryCacheImpl(){
		if(_CACHE_ENABLED){
			cacheMap = new ConcurrentHashMap<Object,CacheObject>(_CACHE_CAPABILITY);
			new Cleaner(_CACHE_CLEAN_MAX_INTERVAL,_CACHE_CLEAN_MIN_INTERVAL);
		}
	}
	public CacheObject get(Serializable key) {
		if(cacheMap == null) return null;
		return cacheMap.get(key);
	}

	public void put(Serializable key, CacheObject object) {
		if(cacheMap == null) return;
		cacheMap.put(key,object);			
	}

	public void remove(Serializable key) {
		if(cacheMap == null) return;
		cacheMap.remove(key);				
	}
	
	class Cleaner implements Runnable{
		
		long interval;	
		long minInterval;
		
		public Cleaner(long interval, long minInterval){
			this.interval = interval;
			this.minInterval = minInterval;
			new Thread(this).start();
		}
		
		public void run(){
			try{
				while(true){
					Thread.sleep(interval);
					clean();
				}
			}catch(Throwable e){
					
			}
		}
		
		private void clean(){
			int size = cacheMap.size();
			logger.info(String.format("Start to clean Cache [interval:%d ms, total: %d]",interval,size));
			Iterator itr = cacheMap.keySet().iterator();
            while (itr.hasNext()) {
            	Object key = itr.next();
            	CacheObject cobj = cacheMap.get(key);
            	if(cobj == null || cobj.hasExpired()) {
            		if(cobj != null){
	            		logger.info(String.format("Removing %s: Idle time=%d; Stale time: %d",key.toString(),cobj.getIdleTime(),cobj.getStaleTime()));	            						                  
            		}
            		itr.remove();
            		Thread.yield();
            	}
            }
            size = cacheMap.size();
            logger.info(String.format("End to clean Cache [interval:%d ms, total: %d]",interval,size));
            while(size > _CACHE_CAPABILITY - _CACHE_CLEAN_CAPABILITY_THRESHOLD){
            	fastClean();
            	logger.info(String.format("Start to fast clean Cache [total: %d]",size));
    			itr = cacheMap.keySet().iterator();
                while (itr.hasNext()) {
                	Object key = itr.next();
                	CacheObject cobj = cacheMap.get(key);
                	if(cobj == null || cobj.fastExpired()) {
                		if(cobj != null){
	                		logger.info(String.format("Removing %s: Idle time=%d; Stale time: %d",key.toString(),cobj.getIdleTime(),cobj.getStaleTime()));	                						                  
                		}
                		itr.remove();
                		Thread.yield();
                	}
                }
                size = cacheMap.size();
                logger.info(String.format("End to fast clean Cache [total: %d]",size));
            }
		}
		private void fastClean(){
			interval = interval / 2;
			if(interval < minInterval) interval = minInterval;
		}
	}
}
