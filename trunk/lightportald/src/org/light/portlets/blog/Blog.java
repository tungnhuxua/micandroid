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

package org.light.portlets.blog;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Blog extends Entity {

	private long userId;
	private long postedById;
	private long orgId;
	private long categoryId;
	private String title;
	private String summary;
	private String content;
	private int status; //see Constants _STATUS_DRAFT _STATUS_PUBLISHED
	private int score;
	//read only
	private String uri;
	private String displayName;
	private int commentCount;
	
	public Blog(){
		super();
	}
	
	public Blog(long userId, long postedById, long orgId, String title, String summary, String content,int status){
		this();
		this.userId = userId;
		this.postedById = postedById;
		this.orgId  = orgId;
		this.title = title;
		this.summary = summary;
		this.content = content;		
		this.status = status;
	}
	
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
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
	

	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCommentCount() {
		return commentCount;
	}
	

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getPostedById() {
		return postedById;
	}

	public void setPostedById(long postedById) {
		this.postedById = postedById;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
}
