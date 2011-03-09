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

import static org.light.portal.util.Constants._FORUM_CATEGORY_DESC_PROFIX;
import static org.light.portal.util.Constants._FORUM_CATEGORY_PROFIX;

import java.util.HashSet;
import java.util.Set;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumCategory extends Entity {

	private String name;
	private String desc;
	private String language;
	private long orgId;
	private long ownerId;
	private int status;
	
	//read only
	private int topics;
	private int posts;
	private String lastForumId;
	private ForumPost lastForum;
	private Set<Forum> forums = new HashSet<Forum>();
	
	public ForumCategory(){
		super();
	}
	
	public ForumCategory(String name,String language,long orgId){
		this();
		this.name = _FORUM_CATEGORY_PROFIX+name;
		this.desc = _FORUM_CATEGORY_DESC_PROFIX+name;
		this.language = language;
		this.orgId = orgId;
		this.ownerId = 0;
		this.status = 1;//available to the public
	}
	
	public ForumCategory(String name,String desc,String language,long orgId,long ownerId){
		this();
		this.name = name;
		this.desc = desc;
		this.language = language;
		this.orgId = orgId;
		this.ownerId = ownerId;
		this.status = 0;//waiting for approve
	}
	
	public ForumCategory(String name,String desc,String language,long orgId,long ownerId,int status){
		this();
		this.name = name;
		this.desc = desc;
		this.language = language;
		this.orgId = orgId;
		this.ownerId = ownerId;
		this.status = status;//waiting for approve
	}
	
	public void addForum(Forum forum){
		this.forums.add(forum);
		forum.setCategory(this);
		
	}
	
	public String getDisplayName(){
		String displayName = PortalContextFactory.getPortalContext().getMessageByKey(this.name);
		return displayName;
	}
	public String getDisplayDesc(){
		String displayDesc = "";
		if(this.desc != null)
			displayDesc = PortalContextFactory.getPortalContext().getMessageByKey(this.desc);
		return displayDesc;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastForumId() {
		return lastForumId;
	}
	public void setLastForumId(String lastForumId) {
		this.lastForumId = lastForumId;
	}
	public int getPosts() {
		return posts;
	}
	public void setPosts(int posts) {
		this.posts = posts;
	}
	public int getTopics() {
		return topics;
	}
	public void setTopics(int topics) {
		this.topics = topics;
	}
	public ForumPost getLastForum() {
		return lastForum;
	}
	public void setLastForum(ForumPost lastForum) {
		this.lastForum = lastForum;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Forum> getForums() {
		return forums;
	}

	public void setForums(Set<Forum> forums) {
		this.forums = forums;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
}
