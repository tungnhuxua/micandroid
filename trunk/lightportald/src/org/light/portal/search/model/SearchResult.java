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

import java.util.List;

import org.light.portal.util.LabelBean;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearchResult {
	
	private int total;
	private List<SearchResultItem> items;
	private List<LabelBean> facets;
	
	public SearchResult(){
		
	}
	public SearchResult(int total,List<SearchResultItem> items){
		this();
		this.total = total;
		this.items = items;
	}
	public SearchResult(int total,List<SearchResultItem> items,List<LabelBean> facets){
		this(total,items);
		this.facets = facets;
	}

	public List<SearchResultItem> getItems() {	
		return items;
	}

	public int getTotal() {
		return total;
	}
	
	public List<LabelBean> getFacets() {
		return facets;
	}
	
}
