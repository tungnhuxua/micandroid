package org.light.portlets.group.search;

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
import org.light.portlets.group.Group;

public class GroupSearcher extends GenericSearcher implements org.light.portal.search.Searcher{
	
	public SearchResult search(Class entityType, SearchCriteria criteria) throws Exception{
		if(!Group.class.equals(entityType)) return null;
		return search(criteria);
	}
	
	public SearchResult search(SearchCriteria criteria) throws Exception { 
		SearchResult result = null;
		try {
			Searcher searcher = SearchFactory.getInstance().getLuceneSearcher(criteria.getOrgId());
			Analyzer analyzer = SearcherUtil.getAnalyzer();
			BooleanQuery query = new BooleanQuery();
			Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(GroupIndexer._TYPE);			
			query.add(queryType,BooleanClause.Occur.MUST);
			BooleanQuery queryKeyword = new BooleanQuery();
			if(criteria.getKeyword() != null){
				String keyword = SearcherUtil.getKeyword(criteria.getKeyword());
				Query query1 = new QueryParser(Version.LUCENE_30,"userId",analyzer).parse(keyword);
				queryKeyword.add(query1,BooleanClause.Occur.SHOULD);
				Query query2 = new QueryParser(Version.LUCENE_30,"name",analyzer).parse(keyword);
				queryKeyword.add(query2,BooleanClause.Occur.SHOULD);
				Query query3 = new QueryParser(Version.LUCENE_30,"uri",analyzer).parse(keyword);
				queryKeyword.add(query3,BooleanClause.Occur.SHOULD);
				Query query4 = new QueryParser(Version.LUCENE_30,"content",analyzer).parse(keyword);
				queryKeyword.add(query4,BooleanClause.Occur.SHOULD);
				Query query5 = new QueryParser(Version.LUCENE_30,"category",analyzer).parse(keyword);
				queryKeyword.add(query5,BooleanClause.Occur.SHOULD);
				Query query6 = new QueryParser(Version.LUCENE_30,"description",analyzer).parse(keyword);
				queryKeyword.add(query6,BooleanClause.Occur.SHOULD);				
				query.add(queryKeyword,BooleanClause.Occur.MUST);
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
				String content = doc.get("content");
				String contentFragment = (content != null) ?  highlighter.getBestFragment(analyzer,"content",content) : null;
				items.add(new SearchResultItem(
						i,
						doc.get("id"),
						doc.get("name"),
						doc.get("uri"),
						doc.get("photoUrl"),
						doc.get("date"),
						(contentFragment != null) ? contentFragment : content,
						doc.get("link"),
						GroupIndexer._TYPE_ENTITY
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
		Query queryType = new QueryParser(Version.LUCENE_30,Indexer._TYPE_ID,analyzer).parse(GroupIndexer._TYPE);			
		query.add(queryType,BooleanClause.Occur.MUST);
		BooleanQuery queryKeyword = new BooleanQuery();
		if(criteria.getKeyword() != null){
			String keyword = SearcherUtil.getKeyword(criteria.getKeyword());
			Query query1 = new QueryParser(Version.LUCENE_30,"userId",analyzer).parse(keyword);
			queryKeyword.add(query1,BooleanClause.Occur.SHOULD);
			Query query2 = new QueryParser(Version.LUCENE_30,"name",analyzer).parse(keyword);
			queryKeyword.add(query2,BooleanClause.Occur.SHOULD);
			Query query3 = new QueryParser(Version.LUCENE_30,"uri",analyzer).parse(keyword);
			queryKeyword.add(query3,BooleanClause.Occur.SHOULD);
			Query query4 = new QueryParser(Version.LUCENE_30,"content",analyzer).parse(keyword);
			queryKeyword.add(query4,BooleanClause.Occur.SHOULD);
			Query query5 = new QueryParser(Version.LUCENE_30,"category",analyzer).parse(keyword);
			queryKeyword.add(query5,BooleanClause.Occur.SHOULD);
			Query query6 = new QueryParser(Version.LUCENE_30,"description",analyzer).parse(keyword);
			queryKeyword.add(query6,BooleanClause.Occur.SHOULD);			
			query.add(queryKeyword,BooleanClause.Occur.MUST);
		}		
		return query;
	}
	
	public SearchResultItem getResultItem(Query query,Document doc) throws Exception{
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(SearcherUtil.getHighlighterPrefix(), SearcherUtil.getHighlighterSuffix()),new QueryScorer(query)); 
		String content = doc.get("content");
		Analyzer analyzer = SearcherUtil.getAnalyzer();
		String contentFragment = (content != null) ?  highlighter.getBestFragment(analyzer,"content",content) : null;
		return new SearchResultItem(
					0,
					doc.get("id"),
					doc.get("name"),
					doc.get("uri"),
					doc.get("photoUrl"),
					doc.get("date"),
					(contentFragment != null) ? contentFragment : content,
					doc.get("link"),
					GroupIndexer._TYPE_ENTITY
					);
	}
}
