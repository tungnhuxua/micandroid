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

import static org.light.portal.util.Constants._SEARCH_LIST;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearchFactory {
	
	public static SearchFactory getInstance(){
		return _instance;
	}
	
	public Collection<Indexer> getIndexers(){
		return indexers.values();
	}
	
	public Indexer getIndexer(Class klass){
		return indexers.get(klass);
	}
	
	public Collection<org.light.portal.search.Searcher> getSearchers(){
		return searchers.values();
	}
	
	public org.light.portal.search.Searcher getSearcher(Class klass){
		return searchers.get(klass);
	}
	
	public org.light.portal.search.Searcher getSearcher(String entity){
		for(Class klass : indexers.keySet()){
			if(entity.equals(indexers.get(klass).getType()))
				return getSearcher(klass);
		}
		return null;
	}
	
	public IndexReader getLuceneIndexReader(long orgId) throws Exception{
		if(luceneReaders.get(orgId) == null){
			synchronized(luceneReaders){
				if(luceneReaders.get(orgId) == null){
					logger.info("open IndexReader for org "+orgId);
					IndexReader reader = IndexWorker.getInstance().getWriter(orgId).getReader();
					luceneReaders.put(orgId,reader);
					return reader;
				}
			}				
		}else if(!luceneReaders.get(orgId).isCurrent()){
			synchronized(luceneReaders){
				if(!luceneReaders.get(orgId).isCurrent()){
					logger.info("reopen IndexReader for org"+orgId);
					IndexReader oldReader = luceneReaders.get(orgId);
					IndexReader newReader = oldReader.reopen();
					oldReader.close();
					luceneReaders.put(orgId,newReader);
					luceneSearchers.remove(orgId);
					return newReader;					
				}
			}
		}
		return luceneReaders.get(orgId);
	}
	
	public void removeLuceneIndexReader(long orgId) throws Exception{
		if(luceneReaders.get(orgId) != null){
			synchronized(luceneReaders){
				if(luceneReaders.get(orgId) != null){
					logger.info("close IndexReader for org"+orgId);
					IndexReader oldReader = luceneReaders.get(orgId);
					oldReader.close();
					luceneReaders.remove(orgId);
					luceneSearchers.remove(orgId);
				}
			}				
		}
	}
	
	public org.apache.lucene.search.Searcher getLuceneSearcher(long orgId) throws Exception{
		if(luceneReaders.get(orgId) == null){
			synchronized(luceneReaders){
				if(luceneReaders.get(orgId) == null){
					logger.info("open IndexReader for org "+orgId);
					IndexReader reader = IndexWorker.getInstance().getWriter(orgId).getReader();
					luceneReaders.put(orgId,reader);
					IndexSearcher searcher = new IndexSearcher(reader);
					luceneSearchers.put(orgId,searcher);
					return searcher;
				}
			}				
		}else if(!luceneReaders.get(orgId).isCurrent()){
			synchronized(luceneReaders){
				if(!luceneReaders.get(orgId).isCurrent()){
					logger.info("reopen IndexReader for org"+orgId);
					IndexReader oldReader = luceneReaders.get(orgId);
					IndexReader newReader = oldReader.reopen();
					oldReader.close();
					luceneReaders.put(orgId,newReader);
					IndexSearcher searcher = new IndexSearcher(newReader);
					luceneSearchers.put(orgId,searcher);
					return searcher;					
				}
			}
		}

		if(luceneSearchers.get(orgId) == null){
			synchronized(luceneSearchers){
				if(luceneSearchers.get(orgId) == null){
					logger.info("open IndexSearcher for org "+orgId);
					IndexSearcher searcher = new IndexSearcher(luceneReaders.get(orgId));
					luceneSearchers.put(orgId,searcher);
					return searcher;
				}
			}
		}
		return luceneSearchers.get(orgId);
	}
	
	private SearchFactory(){
		String value=PropUtil.getString(_SEARCH_LIST);
		String[] lists = value.split(";");
		for(String list : lists){
			String[] unit = list.split(",");
			try{
				Class klass = Class.forName(unit[0]);
				Indexer indexer = (Indexer)Class.forName(unit[1]).newInstance();
				indexers.put(klass,indexer);
				if(unit.length >= 3){
					org.light.portal.search.Searcher searcher = (org.light.portal.search.Searcher)Class.forName(unit[2]).newInstance();
					searchers.put(klass,searcher);
				}
			}catch(Exception e){
				continue;
			}
		}
	}
	
	private static SearchFactory _instance = new SearchFactory();
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private Map<Class,Indexer> indexers = new HashMap<Class,Indexer>();
	private Map<Class,org.light.portal.search.Searcher> searchers = new HashMap<Class,org.light.portal.search.Searcher>();
	private Map<Long,IndexReader> luceneReaders = new HashMap<Long,IndexReader>();
	private Map<Long,org.apache.lucene.search.Searcher> luceneSearchers = new HashMap<Long,org.apache.lucene.search.Searcher>();
}
