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

package org.light.portlets.forum;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumPost extends Entity {

	private String topic;
	private String content;
	private long topId;
	private long forumId;
	private long categoryId;
	private long postById;
	private long orgId;
	private long lastPostId;
	private long lastPostById;	
	
	//read only
	private int posts;
	private String photoUrl;
	private String uri;
	private String displayName;
	private int currentStatusId;
	private String birth;
	private String gender;
	
	private String lastPhotoUrl;
	private String lastUri;
	private String lastDisplayName;
	private int lastCurrentStatusId;
	
	public ForumPost(){
		super();
	}
	
	public ForumPost(String topic,String content, long topId, long forumId, long categoryId,long postById, long orgId){
		this.topic = topic;
		this.content = content;
		this.topId = topId;
		this.forumId = forumId;
		this.categoryId = categoryId;
		this.postById = postById;
		this.lastPostById = postById;
		this.orgId = orgId;
	}
	
	public long getTopicId(){
		long topicId = this.topId;
		if(topicId == 0)
			topicId = this.getId();
		return topicId;
	}
	
	public String getAge(){
		String age = null;
		if(this.birth != null && this.birth.length() == 10){
			String  year = birth.substring(0,4);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			int currentYear = calendar.get( Calendar.YEAR );
			age = String.valueOf(currentYear - Integer.parseInt(year));
		}			
		return age;
	}

	public String getPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}

	public String getLastPhotoUrl() {		
		return ImageUtil.getPhotoUrl(this.lastPhotoUrl);
	}
	
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy HH:mm aaa");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getLastDate(){
		 return DateUtil.format(this.getModifiedDate(),"EEE, MMM dd, yyyy HH:mm aaa");
	}
	
	public String getLastFullDate(){
		 return DateUtil.format(this.getModifiedDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getLastUri() {
		return lastUri;
	}
	public void setLastUri(String uri) {
		this.lastUri = uri;
	}
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCurrentStatusId() {
		return currentStatusId;
	}

	public void setCurrentStatusId(int currentStatusId) {
		this.currentStatusId = currentStatusId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getLastCurrentStatusId() {
		return lastCurrentStatusId;
	}

	public void setLastCurrentStatusId(int lastCurrentStatusId) {
		this.lastCurrentStatusId = lastCurrentStatusId;
	}

	public String getLastDisplayName() {
		return lastDisplayName;
	}

	public void setLastDisplayName(String lastDisplayName) {
		this.lastDisplayName = lastDisplayName;
	}

	public void setLastPhotoUrl(String lastPhotoUrl) {
		this.lastPhotoUrl = lastPhotoUrl;
	}


	public long getLastPostById() {
		return lastPostById;
	}

	public void setLastPostById(long lastPostById) {
		this.lastPostById = lastPostById;
	}

	public long getLastPostId() {
		return lastPostId;
	}

	public void setLastPostId(long lastPostId) {
		this.lastPostId = lastPostId;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public long getPostById() {
		return postById;
	}

	public void setPostById(long postById) {
		this.postById = postById;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public long getTopId() {
		return topId;
	}

	public void setTopId(long topId) {
		this.topId = topId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public long getForumId() {
		return forumId;
	}

	public void setForumId(long forumId) {
		this.forumId = forumId;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}	
}