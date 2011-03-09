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

package org.light.portlets.forum.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.forum.dao.ForumDao;
import org.light.portlets.forum.service.ForumService;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumServiceImpl extends BaseServiceImpl implements ForumService{
	private ForumDao forumDao;
	
	public List<ForumPost> getPosts(long orgId){
		return forumDao.getPosts(orgId);
	}
	public List<ForumCategory> getForumCategories(String language, long orgId){
		String key = orgId+language;
		List<ForumCategory> categories = (List<ForumCategory>)getCacheService().getList(ForumCategory.class,key);
		if(categories == null){
			categories = forumDao.getForumCategories(language,orgId);
			getCacheService().setList(ForumCategory.class,key,categories);
		}
		return categories;
	}
	
	public ForumCategory getForumCategoryById(long id){
		ForumCategory category = (ForumCategory)getCacheService().getObject(ForumCategory.class,id);
		if(category == null){
			category = forumDao.getForumCategoryById(id);
			getCacheService().setObject(ForumCategory.class,id,category);
		}
		return category;
	}
	
	public List<Forum> getForumByCategory(long categoryId){
		List<Forum> forums = (List<Forum>)getCacheService().getList(Forum.class,categoryId);
		if(forums == null){
			forums = forumDao.getForumByCategory(categoryId); 
			getCacheService().setList(Forum.class,categoryId,forums);
		}
		return forums;
	}
	
	public Forum getForumById(long id){
		Forum forum = (Forum)getCacheService().getObject(Forum.class,id);
		if(forum == null){
			forum = forumDao.getForumById(id);
			getCacheService().setObject(Forum.class,id,forum);
		}
		return forum;
	}
	
	public int getTopicsCountByForum(long forumId){
		Integer count = getTopicsCountByForumFromCache(forumId);
		if(count == null){
			count = forumDao.getTopicsCountByForum(forumId);
			setTopicsCountByForumToCache(forumId,count);
		}
		return count;
	}
	
	public int getPostsCountByTopic(long topicId){
		Integer count = getPostsCountByTopicFromCache(topicId);
		if(count == null){
			count = forumDao.getPostsCountByTopic(topicId);
			setPostsCountByTopicToCache(topicId,count);
		}
		return count;
	}
	
	public List<ForumPost> getTopicsByForum(long forumId,int pageId,int maxRow){		
		List<ForumPost> posts = getTopicsByForumFromCache(forumId,pageId,maxRow);
		if(posts == null){
			posts = forumDao.getTopicsByForum(forumId,pageId,maxRow);
			setTopicsByForumToCache(forumId,pageId,maxRow,posts);
		}
		return posts;
	}
	
	public List<ForumPost> getPostsByTopic(long topicId,int pageId,int maxRow){
		List<ForumPost> posts = getPostsByTopicFromCache(topicId,pageId,maxRow);
		if(posts == null){
			posts = forumDao.getPostsByTopic(topicId,pageId,maxRow);
			setPostsByTopicToCache(topicId,pageId,maxRow,posts);
		}
		return posts;
	}
	
	public ForumPost getPostById(long id){
		ForumPost post = (ForumPost)getCacheService().getObject(ForumPost.class,id);
		if(post == null){
			post = forumDao.getPostById(id);
			getCacheService().setObject(ForumPost.class,id,post);
		}
		return post;
	}
			
	public void deleteTopic(long topicId){
		List<ForumPost> posts = forumDao.getPostsByTopic(topicId);
		if(posts != null && posts.size() > 0){
			long forumId = posts.get(0).getForumId();
			for(ForumPost post : posts){
				this.delete(post);
			}
			removeTopicsByForumToCache(forumId);
		}
	}
	
	/**
     * called by daily task to generate sitemap
     * 
     * return all topics per organizaiton
     */
	public List<ForumPost> getTopics(long orgId){
		return forumDao.getTopics(orgId);		
	}
		
	protected List<ForumPost> getTopicsByForumFromCache(long forumId,int pageId,int maxRow){
		Map<String,List<ForumPost>> map = (Map<String,List<ForumPost>>)getCacheService().getObject(CacheService.TOPICS_BY_FORUM+forumId);
		if(map == null) return null;
		return map.get(pageId+CacheService.SEPARATOR+maxRow);
	}
	
	protected void setTopicsByForumToCache(long forumId,int pageId,int maxRow,List<ForumPost> list){
		ConcurrentHashMap<String,List<ForumPost>> map = (ConcurrentHashMap<String,List<ForumPost>>)getCacheService().getObject(CacheService.TOPICS_BY_FORUM+forumId);
		if(map == null) map = new ConcurrentHashMap<String,List<ForumPost>>();
		map.put(pageId+CacheService.SEPARATOR+maxRow,list);
		getCacheService().setObject(CacheService.TOPICS_BY_FORUM+forumId,map);
	}
	
	protected void removeTopicsByForumToCache(long forumId){
		ConcurrentHashMap<String,List<ForumPost>> map = (ConcurrentHashMap<String,List<ForumPost>>)getCacheService().getObject(CacheService.TOPICS_BY_FORUM+forumId);
		if(map == null) map = new ConcurrentHashMap<String,List<ForumPost>>();
		getCacheService().removeObject(CacheService.TOPICS_BY_FORUM+forumId);
	}
	
	protected List<ForumPost> getPostsByTopicFromCache(long topicId,int pageId,int maxRow){
		Map<String,List<ForumPost>> map = (Map<String,List<ForumPost>>)getCacheService().getObject(CacheService.POSTS_BY_TOPIC+topicId);
		if(map == null) return null;
		return map.get(pageId+CacheService.SEPARATOR+maxRow);
	}
	
	protected void setPostsByTopicToCache(long topicId,int pageId,int maxRow,List<ForumPost> list){
		ConcurrentHashMap<String,List<ForumPost>> map = (ConcurrentHashMap<String,List<ForumPost>>)getCacheService().getObject(CacheService.POSTS_BY_TOPIC+topicId);
		if(map == null) map = new ConcurrentHashMap<String,List<ForumPost>>();
		map.put(pageId+CacheService.SEPARATOR+maxRow,list);
		getCacheService().setObject(CacheService.POSTS_BY_TOPIC+topicId,map);
	}
	
	protected Integer getTopicsCountByForumFromCache(long forumId){
		return (Integer)getCacheService().getObject(CacheService.TOPICS_COUNT+forumId);
	}
	
	protected void setTopicsCountByForumToCache(long forumId,int count){
		getCacheService().setObject(CacheService.TOPICS_COUNT+forumId,count);
	}
	
	protected Integer getPostsCountByTopicFromCache(long topicId){
		return (Integer)getCacheService().getObject(CacheService.POSTS_COUNT+topicId);
	}
	
	protected void setPostsCountByTopicToCache(long topicId,int count){
		getCacheService().setObject(CacheService.POSTS_COUNT+topicId,count);
	}
	
	public ForumDao getForumDao() {
		return forumDao;
	}
	
	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}
}
