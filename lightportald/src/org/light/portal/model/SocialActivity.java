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

import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SocialActivity extends Entity{

	private long userId;
	private long orgId;
	private String content;
	private int type; //0 is user;1 is group 
	private int status;  //0 is public;
	
	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	
	public SocialActivity(){
		super();
	}
	public SocialActivity(long userId,long orgId,String content){
		this();
		this.userId = userId;
		this.orgId = orgId;
		this.content = content;
	}
		
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
		
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy HH:mm aaa");
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
