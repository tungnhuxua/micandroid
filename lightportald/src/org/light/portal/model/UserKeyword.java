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
public class UserKeyword extends Entity{

	   private long keywordId;
	   private int weight;
	   private long personId;
	   private int type;
	   private int status; //0 system analyze; 1 user input
	   private long orgId;
	   
	   private String keyword;
	   
	   public UserKeyword(){
	       super();
	   }
	   
	   public UserKeyword(long keywordId,long personId, long orgId, int weight, int type){
		   this();
		   this.keywordId = keywordId;
		   this.personId = personId;
		   this.orgId = orgId;
		   this.weight = weight;
		   this.type = type;
	   }
	   
	   public UserKeyword(long keywordId,long personId, long orgId, int type){
		   this(keywordId,personId,orgId,1,type);
	   }
	   
	   public UserKeyword(long keywordId,long personId, long orgId, int weight,int type,int status){
		   this(keywordId,personId,orgId,weight,type);
		   this.status = status;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(long keywordId) {
		this.keywordId = keywordId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	   
}
