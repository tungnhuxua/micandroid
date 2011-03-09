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

package org.light.portlets.rss;

import java.io.Serializable;
import java.util.Date;

import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class RssBean implements Serializable{
	private int index;
	private String link;
	private String title;
	private String desc;
	private String author;	
	private Date publishDate;
	private boolean addLink;
	
	public RssBean(){
		
	}
	
	public RssBean(int index, String link, String title, String desc, String author, Date date, boolean addLink){
		this.index = index;
		this.link = link;
		this.title = title;
		this.desc = desc;
		this.author = author;
		this.publishDate = date;
		this.addLink = addLink;
	}
	
	public String getDate(){
		 return DateUtil.format(this.publishDate,"EEE, MMM dd, yyyy HH:mm");
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
	
	public boolean isAddLink() {
		return addLink;
	}

	public int getIndex() {
		return index;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
	
}
