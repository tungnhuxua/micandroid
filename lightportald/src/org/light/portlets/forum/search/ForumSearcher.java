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

package org.light.portlets.forum.search;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.light.portal.search.GenericSearcher;
import org.light.portal.search.Indexer;
import org.light.portal.search.SearchFactory;
import org.light.portal.search.SearcherUtil;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.search.model.SearchResultItem;
import org.light.portal.util.StringUtil;
import org.light.portlets.forum.Forum;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumSearcher extends GenericSearcher implements org.light.portal.search.Searcher{
	
	public SearchResult search(Class entityType, SearchCriteria criteria) throws Exception{
		if(!Forum.class.equals(entityType)) return null;
		return search(criteria);
	}
	
	public SearchResult search(SearchCriteria criteria) throws Exception { 
		SearchResult result = null;
		try {
			Searcher searcher = SearchFactory.getInstance().getLuceneSearcher(criteria.getOrgId());
			Analyzer analyzer = SearcherUtil.getAnalyzer();
			BooleanQuery query = new BooleanQuery();
			Query query1 = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(ForumIndexer._TYPE);			
			query.add(query1,BooleanClause.Occur.MUST);
			if(criteria.getKeyword() != null){
				Query query2 = new QueryParser(Version.LUCENE_30,"keyword",analyzer).parse(SearcherUtil.getKeyword(criteria.getKeyword()));
				query.add(query2,BooleanClause.Occur.MUST);
			}
			Sort sort = criteria.getSearchSort(StringUtil.isEmpty(criteria.getKeyword()));
			TopDocs docs = searcher.search(query,null,SearcherUtil.top(),sort);
			Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(SearcherUtil.getHighlighterPrefix(), SearcherUtil.getHighlighterSuffix()),new QueryScorer(query)); 
			ScoreDoc[] scoreDocs = docs.scoreDocs;			
			int total = docs.totalHits;
			int start = (criteria.getPage() - 1) * criteria.getRowPerPage();
			int end = start + criteria.getRowPerPage();
			if(end > total) end = total;
			List<SearchResultItem> items = null;
			items = new LinkedList<SearchResultItem>();
			for(int i=start;i<end;i++){
				Document doc = searcher.doc(scoreDocs[i].doc);
				String title = doc.get("subject");
				String summary = doc.get("summary");
				String content = doc.get("content");
				String titleFragment = (title != null) ? highlighter.getBestFragment(analyzer,"title",title) : null;
				String summaryFragment = (summary != null) ? highlighter.getBestFragment(analyzer,"summary",summary) : null;
				String contentFragment = (content != null) ?  highlighter.getBestFragment(analyzer,"content",content) : null;
				items.add(new SearchResultItem(
						i,
						doc.get("id"),
						doc.get("name"),
						doc.get("uri"),
						doc.get("photoUrl"),
						doc.get("date"),
						(titleFragment != null) ? titleFragment : title,
						(summaryFragment != null) ? summaryFragment : (contentFragment == null) ? summary : "",
						(contentFragment != null) ? contentFragment : content,
						doc.get("link"),
						ForumIndexer._TYPE_ENTITY
						));				
			}				
			result = new SearchResult(total,items);	
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
        return result;
	}

	public Query getQurey(SearchCriteria criteria) throws Exception{
		Analyzer analyzer = SearcherUtil.getAnalyzer();
		BooleanQuery query = new BooleanQuery();
		Query query1 = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(ForumIndexer._TYPE);			
		query.add(query1,BooleanClause.Occur.MUST);
		if(criteria.getKeyword() != null){
			Query query2 = new QueryParser(Version.LUCENE_30,"keyword",analyzer).parse(SearcherUtil.getKeyword(criteria.getKeyword()));
			query.add(query2,BooleanClause.Occur.MUST);
		}		
		return query;
	}
	
	public SearchResultItem getResultItem(Query query,Document doc) throws Exception{
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(SearcherUtil.getHighlighterPrefix(), SearcherUtil.getHighlighterSuffix()),new QueryScorer(query)); 
		String title = doc.get("subject");
		String summary = doc.get("summary");
		String content = doc.get("content");
		Analyzer analyzer = SearcherUtil.getAnalyzer();
		String titleFragment = (title != null) ? highlighter.getBestFragment(analyzer,"title",title) : null;
		String summaryFragment = (summary != null) ? highlighter.getBestFragment(analyzer,"summary",summary) : null;
		String contentFragment = (content != null) ?  highlighter.getBestFragment(analyzer,"content",content) : null;
		return new SearchResultItem(
				0,
				doc.get("id"),
				doc.get("name"),
				doc.get("uri"),
				doc.get("photoUrl"),
				doc.get("date"),
				(titleFragment != null) ? titleFragment : title,
				(summaryFragment != null) ? summaryFragment : (contentFragment == null) ? summary : "",
				(contentFragment != null) ? contentFragment : content,
				doc.get("link"),
				ForumIndexer._TYPE_ENTITY
				);
		
	}
}