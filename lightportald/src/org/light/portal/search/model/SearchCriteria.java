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

package org.light.portal.search.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.light.portal.util.DateUtil;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearchCriteria {
	
	private long orgId;
	private String keyword;
	private String facet1;
	private String facet2;
	private String tag;
	private int page = 1;
	private int rowPerPage = 5;
	private String sort;
	private boolean includeExpired=true;
	private boolean analyzeKeyword=true;
	private boolean oneDayOnly = false;
	private String date;
	
	public SearchCriteria(){		
	}
	
	public SearchCriteria(long orgId){
		this();
		try{
			this.orgId = orgId;
			this.page = PropUtil.getInt("search.criteria.page.begin");
			this.rowPerPage = PropUtil.getInt("search.criteria.page.rows.default");
			this.sort = PropUtil.getString("search.criteria.sort.default");
		}catch(Exception e){}
	}
	
	public String toString(){
		return ((tag != null) ? tag : "")+"_"+((keyword != null) ? keyword : "")+"_"+((facet1 != null) ? facet1 : "")+"_"+((facet2 != null) ? facet2 : "")+"_"+((includeExpired) ? 1 : 0)+"_"+page+"_"+rowPerPage+"_"+((sort != null) ? sort : "")+"_"+(analyzeKeyword ? 1 : 0)+"_"+(oneDayOnly ? 1 : 0)+"_"+((this.getDate() != null) ? this.getDate() : "");
	}
	
	public Sort getSearchSort(boolean keywordFlag){
		Sort sort = null;
		boolean relevance = false;
		if(!StringUtil.isEmpty(this.getSort())){				
			String[] fields = this.getSort().split(",");
			List<SortField> sortFields = new LinkedList<SortField>();
			for(int i = 0;i<fields.length;i++){
				String[] parts = fields[i].split(" ");
				if(parts.length > 1){
					sortFields.add(new SortField(parts[0],SortField.STRING,Boolean.parseBoolean(parts[1].trim())));
				}
				else{
					if(fields[i].trim().length() > 0)
						sortFields.add(new SortField(fields[i],SortField.STRING));
					else
						relevance = true;
				}
			}
			if(!relevance || !keywordFlag){
				sort = new Sort(sortFields.toArray(new SortField[]{}));
			}
		}
		return sort;
	}
	
	public boolean hasCriteria(){
		return !StringUtil.isEmpty(this.keyword) || !this.includeExpired || this.oneDayOnly;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public boolean isIncludeExpired() {
		return includeExpired;
	}
	public void setIncludeExpired(boolean includeExpired) {
		this.includeExpired = includeExpired;
	}
	
	public boolean isAnalyzeKeyword() {
		return analyzeKeyword;
	}
	public void setAnalyzeKeyword(boolean analyzeKeyword) {
		this.analyzeKeyword = analyzeKeyword;
	}
	
	public String getFacet1() {
		return facet1;
	}

	public void setFacet1(String facet1) {
		this.facet1 = facet1;
	}

	public String getFacet2() {
		return facet2;
	}

	public void setFacet2(String facet2) {
		this.facet2 = facet2;
	}

	public String getDate() {
		return !StringUtil.isEmpty(date) ? date : isOneDayOnly() ? DateUtil.format(new Date(System.currentTimeMillis()),DateUtil._FORMAT_yyyy_MM_dd) : null;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isOneDayOnly() {
		return oneDayOnly;
	}

	public void setOneDayOnly(boolean oneDayOnly) {
		this.oneDayOnly = oneDayOnly;
	}	
}
