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

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class IndexWorker {
	
	public static IndexWorker getInstance(){
		return _instance;
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private static IndexWorker _instance = new IndexWorker();
	
	private Map<Long,LinkedList<Object>> queues;
	private Map<Long,Worker> workers;	
	private IndexWorker(){
		queues = new HashMap<Long,LinkedList<Object>>();
		workers = new HashMap<Long,Worker>();
	}
	private void initWorker(long orgId){
		synchronized(queues){
			if(queues.get(orgId) == null)
				queues.put(orgId,new LinkedList<Object>());			
		}
		synchronized(workers){
			if(workers.get(orgId) == null)
				workers.put(orgId,new Worker(orgId));						
		}
	}

	public void addJob(Object object, long orgId){
		if(object == null) return;
		initWorker(orgId);
		synchronized(queues.get(orgId)){
			if(object instanceof List)
				queues.get(orgId).addAll((List)object);
			else
				queues.get(orgId).add(object);
		}		
		try{
			synchronized(queues.get(orgId)){
				logger.info(String.format("Org %d index worker notifying...",orgId));
				queues.get(orgId).notify();		
			}
		} catch(IllegalMonitorStateException e) {
			logger.error("IllegalMonitorStateException caught: "+e.getMessage());
		}
	}

	public void shutdown(){
		for(Worker worker : workers.values()){
			worker.shutdown();
		}
	}
	
	public IndexWriter getWriter(long companyId){
		initWorker(companyId);
		return workers.get(companyId).getWriter();
	} 
	
	class Worker implements Runnable{
		private long orgId;
		IndexWriter writer;
		public Worker(long orgId){
			this.orgId = orgId;
			init();
			new Thread(this).start();
		}
		public IndexWriter getWriter(){
			init();
			return this.writer;
		}
		public void run(){
			while(true){
				try{
					while(queues.get(orgId) != null && queues.get(orgId).size() > 0){
						work(queues.get(orgId));
					}				
					try {
						synchronized(queues.get(orgId)){
							logger.info(String.format("Org %d index worker waitting...",orgId));
							queues.get(orgId).wait();						
						}
					}catch(InterruptedException e) {
						logger.error("InterruptedException caught: "+e.getMessage());
					}
					try{
						logger.info(String.format("Org %d index worker delay for 1000 ms...",orgId));
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						logger.error("InterruptedException caught: "+e.getMessage());
					}
				}catch(Throwable e){
					
				}
			}
		}
		private void init(){
			if(writer == null){
				synchronized(this){
					if(writer == null){
						try {
							String path = SearcherUtil.getPath(this.orgId);
							File file = new File(path);
							boolean isNew = !file.exists();
							if(!isNew){
								File lock = new File(path+System.getProperty("file.separator")+"write.lock");
								if(lock.exists()){
									lock.delete();
								}
							}
							this.writer = new IndexWriter(FSDirectory.open(new File(SearcherUtil.getPath(orgId))), SearcherUtil.getAnalyzer(), isNew, IndexWriter.MaxFieldLength.LIMITED);
							this.writer.optimize();
						}catch(Throwable e){
							e.printStackTrace();			
						}
					}
				}
			}
		}
		private void delete(Query query){
			init();
			synchronized(this){
				try{
					writer.deleteDocuments(query);
					writer.commit();
					SearchFactory.getInstance().removeLuceneIndexReader(this.orgId);					
				}catch(Throwable e){
					e.printStackTrace();			
				}
			}
		}
		private void work(LinkedList<Object> jobs){
			init();
			synchronized(this){
				try{					
					Object job = null;
					synchronized(jobs){
						job = jobs.removeFirst();
					}
					while(job != null){		
						if(job instanceof Document){
							Document doc = (Document)job;
							writer.addDocument(doc);
							logger.info("add index: " +doc.getField(Indexer._TYPE_ID)+ " "+ doc.getField(Indexer._ENTRY_ID));
						}
						if(job instanceof Query){
							Query query = (Query)job;
							writer.deleteDocuments(query);
							logger.info("delete index: " +query.toString());
						}
						synchronized(jobs){
							if(jobs.size() > 0)
								job = jobs.removeFirst();
							else
								job = null;
						}			
					}  	
					writer.commit();
				}catch(Throwable e){
					e.printStackTrace();			
				}
			}
		}
	
		public void shutdown(){
			try{
				if(writer != null){
					writer.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
