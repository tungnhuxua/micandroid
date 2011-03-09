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

package org.light.portal.search.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.light.portal.search.Indexer;
import org.light.portal.search.SearchFactory;
import org.light.portal.search.Searcher;
import org.light.portal.search.SearcherUtil;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.search.model.SearchResultItem;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearcherImpl implements Searcher{
			
	public SearchResult search(SearchCriteria criteria) throws Exception{
		SearchResult result = null;
		try {
			
			org.apache.lucene.search.Searcher searcher = SearchFactory.getInstance().getLuceneSearcher(criteria.getOrgId());			
			Query query = getQurey(criteria);
			Sort sort = criteria.getSearchSort(StringUtil.isEmpty(criteria.getKeyword()));
			TopDocs docs = searcher.search(query,null,SearcherUtil.top(),sort);
			ScoreDoc[] scoreDocs = docs.scoreDocs;			
			int total = docs.totalHits;		
			int start = (criteria.getPage() - 1) * criteria.getRowPerPage();
			int end = start + criteria.getRowPerPage();
			if(end > total) end = total;
			List<SearchResultItem> items = null;
			items = new LinkedList<SearchResultItem>();
			int number= 0;
			for(int i=start;i<end;i++){
				Document doc = searcher.doc(scoreDocs[i].doc);
				String entity = doc.get(Indexer._TYPE_ID);				
				Searcher search = SearchFactory.getInstance().getSearcher(entity);
				if(search != null){
					SearchResultItem item = search.getResultItem(query,doc);
					item.setNumber(number++);
					items.add(item);
				}
			}			
			result = new SearchResult(total,items);			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
        return result;
	}
	
	public SearchResult search(Class klass, SearchCriteria criteria) throws Exception{
		Searcher searcher = SearchFactory.getInstance().getSearcher(klass);
		if(searcher != null){
			return searcher.search(criteria);
		}
		return null;
	}
	
	public Query getQurey(SearchCriteria criteria) throws Exception{
		BooleanQuery query = new BooleanQuery();
		for(Searcher searcher : SearchFactory.getInstance().getSearchers()){
			query.add(searcher.getQurey(criteria),BooleanClause.Occur.SHOULD);
		}
		return query;
	}
	
	public SearchResultItem getResultItem(Query query,Document doc) throws Exception{
		return new SearchResultItem(
					0,
					doc.get("id"),
					doc.get("name"),
					doc.get("uri"),
					doc.get("photoUrl"),
					doc.get("date")
					);
	}
}
