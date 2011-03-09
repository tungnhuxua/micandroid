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

package org.light.portal.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Jianmin Liu
 **/
public class ContentCategory {
	private String name;
	private String title;
	private boolean showed;
	private List<ContentSubCategory> subCategories;
	private List<PortletObjectRef> feedLists = new ArrayList<PortletObjectRef>();
	
	public ContentCategory(String name, String title){
		this.name = name;
		this.title = title;
	}
	
	public void addSub(ContentSubCategory sub){
		subCategories.add(sub);
	}
	
	public void addFeed(PortletObjectRef ref){
		feedLists.add(ref);
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
        return getName().toString();
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
    public boolean equals(Object other) {
        if ( !(other instanceof ContentCategory) ) return false;
		ContentCategory castOther = (ContentCategory) other;
        if(this.hashCode()!=castOther.hashCode()) return false;
        return true;
    }

	/**
	 * @see java.lang.Object#hashCode()
	 */
    public int hashCode() {    	
        return getName().hashCode();
    }
	public boolean isShowed() {
		return showed;
	}
	

	public void setShowed(boolean showed) {
		this.showed = showed;
	}
	

	public List<PortletObjectRef> getFeedLists() {
		return feedLists;
	}
	

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public List<ContentSubCategory> getSubCategories() {
		return subCategories;
	}
	

	public void setSubCategories(List<ContentSubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	
	
}
