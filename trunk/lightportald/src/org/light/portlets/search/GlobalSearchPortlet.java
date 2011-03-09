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

package org.light.portlets.search;

import static org.light.portal.util.Constants._PORTAL_URL_FORMAT;
import static org.light.portal.util.Constants._SEARCH_LIST;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.search.Searcher;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.search.model.SearchResultItem;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class GlobalSearchPortlet extends LightGenericPortlet {
	
	protected void doView (RenderRequest request, RenderResponse response)
   		throws PortletException, java.io.IOException
	{	
		String input = request.getParameter("input");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		String sort = request.getParameter("sort");
		String show = request.getParameter("show");
		if("0".equals(input)) keyword=null;
		SearchCriteria criteria = (SearchCriteria)request.getPortletSession().getAttribute("criteria",PortletSession.APPLICATION_SCOPE);
		if(criteria == null)
			criteria = new SearchCriteria(OrganizationThreadLocal.getOrganizationId());	   
		if(!StringUtil.isEmpty(show)) try{criteria.setRowPerPage(Integer.parseInt(show));}catch(Exception e){}
		int page =1;
		int number = 0;
		boolean pageChange = false;
		if(request.getParameter("page") != null){
			try{
				page = Integer.parseInt(request.getParameter("page"));				   
				number = page * criteria.getRowPerPage();
				pageChange = true;
			}catch(Exception e){}
		}else if(request.getParameter("number") != null){
			try{
				number = Integer.parseInt(request.getParameter("number"));	
				page = number / criteria.getRowPerPage();
				pageChange = true;
			}catch(Exception e){}
		}	   
		criteria.setPage(page);
		if(!pageChange){
			criteria.setKeyword(keyword);
			if(sort != null) criteria.setSort(sort);
		}
		SearchResult result= null;
		if(type == null)
			type = getDefaultType();
		try{
			if(type != null && !type.equals("all"))
				result = this.getSearcher(request).search(Class.forName(type),criteria);
			else
				result = this.getSearcher(request).search(criteria);
			request.getPortletSession().setAttribute("criteria",criteria,PortletSession.APPLICATION_SCOPE);			
		}catch(Exception e){
		   e.printStackTrace();
		}
		int total = result.getTotal();
		int rowPerPage = criteria.getRowPerPage();
		int pages = (total % rowPerPage == 0) ? total / rowPerPage : total / rowPerPage + 1;	 
		int start = (page-1) * rowPerPage;
		int end = (page * rowPerPage < total) ? page * rowPerPage : total;

		JSONObject json = new JSONObject();
		try{
			json.put("view", "globalSearch.view");
			json.put("type",type);
			json.put("sort", criteria.getSort());
			json.put("show", criteria.getRowPerPage());
			json.put("keyword", StringUtil.isEmpty(criteria.getKeyword()) ? "" : criteria.getKeyword());
			json.put("items", getSearchResultsJSON(result.getItems()));
			json.put("total", total);
			json.put("pages",pages);
			json.put("page",page);
			json.put("start",start);
			json.put("end",end);
			int begin = page;
			if(begin - 5 > 0) 
				begin -= 5;
			else
				begin = 1;
			int finish = pages;
			if(finish >= begin+10) finish = begin+9;
			JSONArray jArray = new JSONArray();
			for(int i=begin;i<=finish;i++)
				jArray.put(i);
			json.put("pageNumbers",jArray);
		}catch(Exception e){}				
		response.getWriter().write(json.toString());	  
	}	
			
	protected JSONArray getSearchResultsJSON(List<SearchResultItem> items){
		JSONArray jArray = new JSONArray();
		try{						
			for(SearchResultItem item : items){
				JSONObject jItem = getSearchItemJSON(item);
				jArray.put(jItem);
			}		
		}catch(Exception e){}
		return jArray;
	}
	
	protected JSONObject getSearchItemJSON(SearchResultItem item){
		JSONObject jItem = new JSONObject();
		try{			
			jItem.put("number",item.getNumber());
			jItem.put("id",item.getId());
			jItem.put("name",item.getName());			
			jItem.put("photoUrl",item.getPhotoUrl());
			jItem.put("photoWidth",item.getPhotoWidth());
			jItem.put("photoHeight",item.getPhotoHeight());
			jItem.put("date",item.getDate());
			jItem.put("type",item.getType());
			if(item.isUserData()) jItem.put("userData",1);
			if(item.isShowDetail()) jItem.put("showDetail",1);
			jItem.put("link",item.getLink());
			jItem.put("detail",item.getDetail());
			String url;
		    if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
		    	url = "http://"+item.getUri()+"."+OrganizationThreadLocal.getOrg().getHost();
		    else
		    	url = "http://"+OrganizationThreadLocal.getOrg().getUserSpacePrefix()+item.getUri();
		    jItem.put("url",url);
		    jItem.put("signature",item.getSignature());
		}catch(Exception e){}
		return jItem;
	}
  	
	private String getDefaultType(){
		String value=PropUtil.getString(_SEARCH_LIST);
		String[] lists = value.split(";");
		for(String list : lists){
			String[] unit = list.split(",");							
			if(unit.length >= 4)
				return unit[0];
		}
		return null;
	}
	
	protected Searcher getSearcher(PortletRequest request) {			
		return (Searcher)Context.getInstance().getService(this.getServletRequest(request), "searcher");
	}
}
