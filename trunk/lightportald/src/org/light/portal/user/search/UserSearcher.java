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

package org.light.portal.user.search;

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
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.User;
import org.light.portal.search.GenericSearcher;
import org.light.portal.search.Indexer;
import org.light.portal.search.SearchFactory;
import org.light.portal.search.SearcherUtil;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.search.model.SearchResultItem;
import org.light.portal.util.StringUtil;


/**
 * 
 * @author Jianmin Liu
 **/
public class UserSearcher extends GenericSearcher implements org.light.portal.search.Searcher{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	public SearchResult search(Class entityType, SearchCriteria criteria) throws Exception{
		if(!User.class.equals(entityType)) return null;
		return search(criteria);
	}
	
	public SearchResult search(SearchCriteria criteria) throws Exception { 
		SearchResult result = null;
		try {
			Searcher searcher = SearchFactory.getInstance().getLuceneSearcher(criteria.getOrgId());
			Analyzer analyzer = SearcherUtil.getAnalyzer();
			BooleanQuery query = new BooleanQuery();
			Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(UserIndexer._TYPE);			
			query.add(queryType,BooleanClause.Occur.MUST);
			BooleanQuery queryKeyword = new BooleanQuery();
			if(!StringUtil.isEmpty(criteria.getKeyword())){
				String keyword = SearcherUtil.getKeyword(criteria.getKeyword());
				Query query1 = new QueryParser(Version.LUCENE_30,"userId",analyzer).parse(keyword);
				queryKeyword.add(query1,BooleanClause.Occur.SHOULD);
				Query query2 = new QueryParser(Version.LUCENE_30,"email",analyzer).parse(keyword);
				queryKeyword.add(query2,BooleanClause.Occur.SHOULD);
				Query query3 = new QueryParser(Version.LUCENE_30,"uri",analyzer).parse(keyword);
				queryKeyword.add(query3,BooleanClause.Occur.SHOULD);
				Query query4 = new QueryParser(Version.LUCENE_30,"displayName",analyzer).parse(keyword);
				queryKeyword.add(query4,BooleanClause.Occur.SHOULD);
				Query query5 = new QueryParser(Version.LUCENE_30,"name",analyzer).parse(keyword);
				queryKeyword.add(query5,BooleanClause.Occur.SHOULD);
				Query query6 = new QueryParser(Version.LUCENE_30,"headline",analyzer).parse(keyword);
				queryKeyword.add(query6,BooleanClause.Occur.SHOULD);				
				Query query7 = new QueryParser(Version.LUCENE_30,"aboutMe",analyzer).parse(keyword);
				queryKeyword.add(query7,BooleanClause.Occur.SHOULD);
				Query query8 = new QueryParser(Version.LUCENE_30,"hometown",analyzer).parse(keyword);
				queryKeyword.add(query8,BooleanClause.Occur.SHOULD);
				Query query9 = new QueryParser(Version.LUCENE_30,"interests",analyzer).parse(keyword);
				queryKeyword.add(query9,BooleanClause.Occur.SHOULD);
				Query query10 = new QueryParser(Version.LUCENE_30,"likeToMeet",analyzer).parse(keyword);
				queryKeyword.add(query10,BooleanClause.Occur.SHOULD);
				Query query11 = new QueryParser(Version.LUCENE_30,"occupation",analyzer).parse(keyword);
				queryKeyword.add(query11,BooleanClause.Occur.SHOULD);
				Query query12 = new QueryParser(Version.LUCENE_30,"religion",analyzer).parse(keyword);
				queryKeyword.add(query12,BooleanClause.Occur.SHOULD);
				Query query13 = new QueryParser(Version.LUCENE_30,"movie",analyzer).parse(keyword);
				queryKeyword.add(query13,BooleanClause.Occur.SHOULD);
				Query query14 = new QueryParser(Version.LUCENE_30,"music",analyzer).parse(keyword);
				queryKeyword.add(query14,BooleanClause.Occur.SHOULD);
				Query query15 = new QueryParser(Version.LUCENE_30,"television",analyzer).parse(keyword);
				queryKeyword.add(query15,BooleanClause.Occur.SHOULD);
				Query query16 = new QueryParser(Version.LUCENE_30,"book",analyzer).parse(keyword);
				queryKeyword.add(query16,BooleanClause.Occur.SHOULD);
				Query query17 = new QueryParser(Version.LUCENE_30,"hero",analyzer).parse(keyword);
				queryKeyword.add(query17,BooleanClause.Occur.SHOULD);
				Query query18 = new QueryParser(Version.LUCENE_30,"tag",analyzer).parse(keyword);
				queryKeyword.add(query18,BooleanClause.Occur.SHOULD);
				
				query.add(queryKeyword,BooleanClause.Occur.MUST);
			}
			//if(logger.isDebugEnabled())
				logger.info(query.toString());
			Sort sort = criteria.getSearchSort(StringUtil.isEmpty(criteria.getKeyword()));
			TopDocs docs;
			if(sort != null)
				docs = searcher.search(query,null,SearcherUtil.top(),sort);
			else
				docs = searcher.search(query,null,SearcherUtil.top());
			Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(SearcherUtil.getHighlighterPrefix(), SearcherUtil.getHighlighterSuffix()),new QueryScorer(query)); 			
			ScoreDoc[] scoreDocs = docs.scoreDocs;			
			int total = docs.totalHits;			
			int start = (criteria.getPage() - 1) * criteria.getRowPerPage();
			int end = start + criteria.getRowPerPage();
			if(end > total) end = total;List<SearchResultItem> items = null;
			items = new LinkedList<SearchResultItem>();
			for(int i=start;i<end;i++){
				Document doc = searcher.doc(scoreDocs[i].doc);
				String name = doc.get("name");
				String nameFragment = (name != null) ?  highlighter.getBestFragment(analyzer,"name",name) : null;
				items.add(new SearchResultItem(
						i,
						doc.get("id"),
						(nameFragment != null) ? nameFragment : name,
						doc.get("uri"),
						doc.get("photoUrl"),
						doc.get("date")
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
		Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(UserIndexer._TYPE);			
		query.add(queryType,BooleanClause.Occur.MUST);
		BooleanQuery queryKeyword = new BooleanQuery();
		if(!StringUtil.isEmpty(criteria.getKeyword())){
			String keyword = SearcherUtil.getKeyword(criteria.getKeyword());
			Query query1 = new QueryParser(Version.LUCENE_30,"userId",analyzer).parse(keyword);
			queryKeyword.add(query1,BooleanClause.Occur.SHOULD);
			Query query2 = new QueryParser(Version.LUCENE_30,"email",analyzer).parse(keyword);
			queryKeyword.add(query2,BooleanClause.Occur.SHOULD);
			Query query3 = new QueryParser(Version.LUCENE_30,"uri",analyzer).parse(keyword);
			queryKeyword.add(query3,BooleanClause.Occur.SHOULD);
			Query query4 = new QueryParser(Version.LUCENE_30,"displayName",analyzer).parse(keyword);
			queryKeyword.add(query4,BooleanClause.Occur.SHOULD);
			Query query5 = new QueryParser(Version.LUCENE_30,"name",analyzer).parse(keyword);
			queryKeyword.add(query5,BooleanClause.Occur.SHOULD);
			Query query6 = new QueryParser(Version.LUCENE_30,"headline",analyzer).parse(keyword);
			queryKeyword.add(query6,BooleanClause.Occur.SHOULD);				
			Query query7 = new QueryParser(Version.LUCENE_30,"aboutMe",analyzer).parse(keyword);
			queryKeyword.add(query7,BooleanClause.Occur.SHOULD);
			Query query8 = new QueryParser(Version.LUCENE_30,"hometown",analyzer).parse(keyword);
			queryKeyword.add(query8,BooleanClause.Occur.SHOULD);
			Query query9 = new QueryParser(Version.LUCENE_30,"interests",analyzer).parse(keyword);
			queryKeyword.add(query9,BooleanClause.Occur.SHOULD);
			Query query10 = new QueryParser(Version.LUCENE_30,"likeToMeet",analyzer).parse(keyword);
			queryKeyword.add(query10,BooleanClause.Occur.SHOULD);
			Query query11 = new QueryParser(Version.LUCENE_30,"occupation",analyzer).parse(keyword);
			queryKeyword.add(query11,BooleanClause.Occur.SHOULD);
			Query query12 = new QueryParser(Version.LUCENE_30,"religion",analyzer).parse(keyword);
			queryKeyword.add(query12,BooleanClause.Occur.SHOULD);
			Query query13 = new QueryParser(Version.LUCENE_30,"movie",analyzer).parse(keyword);
			queryKeyword.add(query13,BooleanClause.Occur.SHOULD);
			Query query14 = new QueryParser(Version.LUCENE_30,"music",analyzer).parse(keyword);
			queryKeyword.add(query14,BooleanClause.Occur.SHOULD);
			Query query15 = new QueryParser(Version.LUCENE_30,"television",analyzer).parse(keyword);
			queryKeyword.add(query15,BooleanClause.Occur.SHOULD);
			Query query16 = new QueryParser(Version.LUCENE_30,"book",analyzer).parse(keyword);
			queryKeyword.add(query16,BooleanClause.Occur.SHOULD);
			Query query17 = new QueryParser(Version.LUCENE_30,"hero",analyzer).parse(keyword);
			queryKeyword.add(query17,BooleanClause.Occur.SHOULD);
			Query query18 = new QueryParser(Version.LUCENE_30,"tag",analyzer).parse(keyword);
			queryKeyword.add(query18,BooleanClause.Occur.SHOULD);
			
			query.add(queryKeyword,BooleanClause.Occur.MUST);
		}		
		return query;
	}
	
	public SearchResultItem getResultItem(Query query,Document doc) throws Exception{
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(SearcherUtil.getHighlighterPrefix(), SearcherUtil.getHighlighterSuffix()),new QueryScorer(query)); 
		String name = doc.get("name");
		Analyzer analyzer = SearcherUtil.getAnalyzer();
		String nameFragment = (name != null) ?  highlighter.getBestFragment(analyzer,"name",name) : null;
		return new SearchResultItem(
				0,
				doc.get("id"),
				(nameFragment != null) ? nameFragment : name,
				doc.get("uri"),
				doc.get("photoUrl"),
				doc.get("date")
				);
	}

}
