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

/**
 * 
 * @author Jianmin Liu
 **/
public class Keyword extends Entity implements Comparable{

	private String keyword;
	private int weight;
	private int type;
    private long orgId;
    
	public Keyword(){
       super();
	}
	
	public Keyword(String keyword, int weight, int type, long orgId){
		this();
		this.keyword = keyword;
		this.weight = weight;
		this.type = type;
		this.orgId = orgId;
	}
	
	/**
     * Compares to another Keyword; used for sorting.
     */
    public int compareTo(Object object)
    {
    	Keyword that = (Keyword) object;
    	return this.keyword.toLowerCase().compareTo(that.keyword.toLowerCase());
    }

	public void weightIt(){
	       this.weight++;
    }
    
	public void weightIt(int howMany){
       this.weight+=howMany;
    }

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

}
