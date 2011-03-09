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

package org.light.portal.search;

import static org.light.portal.util.Constants._INDEX_THREAD_LIVE_TIME;
import static org.light.portal.util.Constants._INDEX_THREAD_POOL_MAX;
import static org.light.portal.util.Constants._INDEX_THREAD_POOL_MIN;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_INDEX;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.light.portal.cache.CacheService;
import org.light.portal.core.service.ServiceContext;
import org.light.portal.distribute.Event;
import org.light.portal.distribute.Message;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Entity;
import org.light.portal.user.service.UserService;

/**
 * 
 * @author Jianmin Liu
 **/
public abstract class GenericIndexer implements Indexer{
	
	public void reIndex(long orgId){
		this.reIndex(orgId,true);
	}
	public void reIndex(Class klass, long orgId){
		this.reIndex(klass,orgId,true);
	}
	
	public void reIndex(long orgId, boolean replicationFlag){
		doReIndex(orgId);
		if(replicationFlag && _REPLICATION_ENABLED_INDEX && replicationPublisher != null) replicationPublisher.process(new Message(Event.INDEX_ALL,orgId));
	}
	public void reIndex(Class klass, long orgId, boolean replicationFlag){
		doReIndex(klass, orgId);
		if(replicationFlag && _REPLICATION_ENABLED_INDEX && replicationPublisher != null) replicationPublisher.process(new Message(Event.INDEX_ALL,orgId,klass.getName()));
	}

	protected void doReIndex(final long orgId){
		deleteAllIndex(getType(), orgId);
		List<Long> ids = getIds(orgId);
		for(long id : ids){
			tpe.execute(new IndexThread(orgId, id));
		}
		tpe.execute(new Thread(){
							public void run() {
								clearCache(orgId);
							}
						});
	}
	
	protected void doReIndex(Class klass,long orgId){
		doReIndex(orgId);
	}
	
	protected void clearCache(long orgId){
		
	}
	
	protected abstract List<Long> getIds(long orgId);
	protected abstract void update(long orgId, long id);
		
	class IndexThread implements Runnable{
		 private long orgId;
		 private long id;
		 public IndexThread(long orgId, long id){
		 	this.orgId = orgId;
		 	this.id = id;
		 }
		 public void run(){
			 try
			 {
				 update(orgId, id);	
			 }catch(Exception e){				 
				  
			 }
		 }
	}
	
	protected void deleteAllIndex(String type, long orgId){
		try {			
			Analyzer analyzer = SearcherUtil.getAnalyzer();
			BooleanQuery query = new BooleanQuery();
			Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(type);			
			query.add(queryType,BooleanClause.Occur.MUST);
			IndexWorker.getInstance().addJob(query,orgId);			
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	protected void deleteIndex(Entity entity, String type, long orgId){
		try {		
			Analyzer analyzer = SearcherUtil.getAnalyzer();
			BooleanQuery query = new BooleanQuery();
			Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(type);			
			query.add(queryType,BooleanClause.Occur.MUST);
			Query queryId = new QueryParser(Version.LUCENE_30,Indexer._ENTRY_ID,analyzer).parse(type+String.valueOf(entity.getId()));			
			query.add(queryId,BooleanClause.Occur.MUST);
			IndexWorker.getInstance().addJob(query,orgId);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	protected void addIndex(List<Document> docs, long orgId){
		IndexWorker.getInstance().addJob(docs,orgId);
		logger.info(String.format("add %d index documents to the queue",docs.size()));
	}
	
	protected CacheService getCacheService(){
		return (CacheService)ServiceContext.getInstance().getContext().getBean("cacheService");
	}
	
	protected UserService getUserService(){
		return (UserService)ServiceContext.getInstance().getContext().getBean("userService");
	}
	
	public ReplicationPublisher getReplicationPublisher() {
		return replicationPublisher;
	}

	public void setReplicationPublisher(ReplicationPublisher replicationPublisher) {
		this.replicationPublisher = replicationPublisher;
	}
	
	private ThreadPoolExecutor tpe = new ThreadPoolExecutor(_INDEX_THREAD_POOL_MIN,_INDEX_THREAD_POOL_MAX,_INDEX_THREAD_LIVE_TIME,TimeUnit.MILLISECONDS,new LinkedBlockingQueue());
	private ReplicationPublisher replicationPublisher;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}