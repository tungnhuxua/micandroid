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

import java.sql.Timestamp;
import java.util.Date;

import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class RecommendedItem extends Entity{
	//TODO:combine with ViewItem to item, and create UserItem 0 recommended 1 viewed -1 deleted
	   private long personId;
	   private String link;
	   private String title;
	   private String desc;
	   private String tag;
	   private String locale;
	   private Timestamp createDate;
	   private int weight;
	   private int read; //0 not read yet, 1 read
	   
	   public RecommendedItem(){
	       super();
	   }

	   public RecommendedItem(String link,String title,String desc, String tag, String locale,long personId){
	       this();
	       this.link = link;
	       this.title = title;
	       this.desc = desc;
	       this.tag = tag;
	       this.locale = locale;
	       this.personId = personId;
	       this.createDate = new Timestamp(System.currentTimeMillis());
	   }
	   
	   public void weighIt(){
		   this.weight++;
	   }
	   
	   public String getTime(){
		   return DateUtil.calculateDifference(new Date(System.currentTimeMillis()),new Date(this.createDate.getTime()));	
	   }
	   
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

}
