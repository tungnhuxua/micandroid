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

package org.light.portlets.bulletin;

import java.sql.Timestamp;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Bulletin extends Entity {

	public static int _TYPE_USER  = 1;
	public static int _TYPE_ORG = 2;
	
	private long userId;
	private long postById;
	private long orgId;
	private String subject;
	private String content;
	private int status;
	private int type;
	private Timestamp  createDate = new Timestamp(System.currentTimeMillis());
	
	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	private int currentStatusId;
	
	public Bulletin(){
		super();
	}
    
	public Bulletin(String subject,String content,long userId){
		this();
		this.subject = subject;
		this.content = content;
		this.userId = userId;
		this.postById = userId;
		this.type = _TYPE_USER;
		this.status = 1;
	}
	
	public Bulletin(String subject,String content,long userId,long postById){
		this();
		this.subject = subject;
		this.content = content;
		this.userId = userId;
		this.postById = postById;
		this.type = _TYPE_USER;
	}
	
	public Bulletin(long orgId, String subject,String content,long userId){
		this();
		this.subject = subject;
		this.content = content;
		this.userId = userId;
		this.postById = userId;
		this.type = _TYPE_ORG;
		this.orgId = orgId;
		this.status = 1;
	}
	
	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
		
	public String getDate(){
		 return DateUtil.format(this.createDate,"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.createDate,"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	

	public String getDisplayName() {
		return displayName;
	}
	

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

	public long getPostById() {
		return postById;
	}
	

	public void setPostById(long postById) {
		this.postById = postById;
	}
	

	public String getSubject() {
		return subject;
	}
	

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}
	

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	

	public Timestamp getCreateDate() {
		return createDate;
	}
	

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCurrentStatusId() {
		return currentStatusId;
	}

	public void setCurrentStatusId(int currentStatusId) {
		this.currentStatusId = currentStatusId;
	}
}
