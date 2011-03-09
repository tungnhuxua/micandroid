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

package org.light.portlets.microblog;

import java.util.Set;

import org.light.portal.model.Entity;
import org.light.portal.model.UserComment;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Microblog extends Entity {

	private long userId;
	private long postById;
	private long orgId;	
	private String content;
	private int status;
	private int score;
	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	private Set<UserComment> comments;
	
	public Microblog(){
		super();
	}
	
	public Microblog(long userId, long orgId, String content){
		this();
		this.userId = userId;	
		this.postById = userId;		
		this.orgId  = orgId;		
		this.content = content;			
	}
	
	public Microblog(long userId, long postById,long orgId, String content){
		this(userId,orgId,content);
		this.postById = postById;			
	}
		
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
		
	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}
	

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}
	

	public int getStatus() {
		return status;
	}
	

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Set<UserComment> getComments() {
		return comments;
	}

	public void setComments(Set<UserComment> comments) {
		this.comments = comments;
	}

	public long getPostById() {
		return postById;
	}

	public void setPostById(long postById) {
		this.postById = postById;
	}

}
