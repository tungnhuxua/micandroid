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

package org.light.portlets.bookmark;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class Bookmark extends Entity{

   private String name;
   private String url;
   private String desc;
   private String tagId;
   private String tagName;
   private long userId;

   public Bookmark(){
       super();
   }
   
   public Bookmark(String name, String url, String tagId, String tagName, String desc, long userId){
       this();
       this.name = name;
       this.url = url;
       this.tagId = tagId;
       this.tagName = tagName;
       this.desc = desc;
       this.userId = userId;
   }
   
   public Bookmark(long id,String name, String url, String tagId, String tagName, String desc, long userId){
       this();
	   this.setId(id);
       this.name = name;
       this.url = url;
       this.tagId = tagId;
       this.tagName = tagName;
       this.desc = desc;
       this.userId = userId;
   }
 
   public String getTagNameValue(){
		String tagName = PortalContextFactory.getPortalContext().getMessageByKey(this.tagName);
		return tagName;
	}
   
   public String getName() {
       return name;
   }


   public void setName(String name) {
       this.name = name;
   }

   public String getUrl() {
       return url;
   }


   public void setUrl(String url) {
       this.url = url;
   }


   public long getUserId() {
       return userId;
   }

   public void setUserId(long userId) {
       this.userId = userId;
   }

	public String getTagId() {
		return tagId;
	}
	
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getTagName() {
		return tagName;
	}

}
