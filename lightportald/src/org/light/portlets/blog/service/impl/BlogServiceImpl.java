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

package org.light.portlets.blog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.user.service.UserService;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.BlogCategory;
import org.light.portlets.blog.dao.BlogDao;
import org.light.portlets.blog.service.BlogService;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlogServiceImpl extends BaseServiceImpl implements BlogService{
	
	private UserService userService;
	
	public int getBlogsTotalByUser(long userId, long orgId){
		String key = orgId+CacheService.SEPARATOR+userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Blog.class,key);
		if(count == null){
			count = blogDao.getBlogsTotalByUser(userId,userService.getOrgById(orgId));
			getCacheService().setObject(Blog.class,key,count);
		}
		return count;
	}
	
	public List<Blog> getBlogsByUser(long userId, long orgId){
		List<Blog> list = getBlogsFromCache(userId,orgId,"",0,0);
		if(list == null){
			list = blogDao.getBlogsByUser(userId,userService.getOrgById(orgId));
			setBlogsToCache(userId,orgId,"",0,0,list);
		}
		return list;
	}
	
	public List<Blog> getBlogsByUser(long userId, long orgId,String sort,int start,int end){
		List<Blog> list = getBlogsFromCache(userId,orgId,sort,start,end);
		if(list == null){
			list = blogDao.getBlogsByUser(userId,userService.getOrgById(orgId),sort,start,end);
			setBlogsToCache(userId,orgId,sort,start,end,list);
		}
		return list;
	}
	
	public int getBlogsTotalByVisitor(long userId, long orgId){
		String key = orgId+CacheService.SEPARATOR+userId+CacheService.TYPE1+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Blog.class,key);
		if(count == null){
			count = blogDao.getBlogsTotalByVisitor(userId,orgId);
			getCacheService().setObject(Blog.class,key,count);
		}
		return count;
	}
	
	public List<Blog> getBlogsByVisitor(long userId, long orgId){
		List<Blog> list = getBlogsFromCache(userId,orgId,CacheService.TYPE1,0,0);
		if(list == null){
			list = blogDao.getBlogsByVisitor(userId,orgId);
			setBlogsToCache(userId,orgId,CacheService.TYPE1,0,0,list);
		}
		return list;
	}
	
	public List<Blog> getBlogsByVisitor(long userId, long orgId,String sort,int start,int end){
		List<Blog> list = getBlogsFromCache(userId,orgId,CacheService.TYPE1+sort,start,end);
		if(list == null){
			list = blogDao.getBlogsByVisitor(userId,orgId,sort,start,end);
			setBlogsToCache(userId,orgId,CacheService.TYPE1+sort,start,end,list);
		}
		return list;
	}
	
	public Blog getBlogById(long id){
		Blog blog = (Blog)getCacheService().getObject(Blog.class,id);
		if(blog == null){
			blog = blogDao.getBlogById(id);	
			getCacheService().setObject(Blog.class,id,blog);
		}
	    return blog;
	}
	
	public int getBlogsTotal(long orgId){
		String key = orgId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Blog.class,key);
		if(count == null){
			count = blogDao.getBlogsTotal(orgId);
			getCacheService().setObject(Blog.class,key,count);
		}
		return count;
	}
	
	public List<Blog> getBlogs(long orgId){
		List<Blog> list = getBlogsFromCache(0L,orgId,"",0,0);
		if(list == null){
			list = blogDao.getBlogs(orgId,null);
			setBlogsToCache(0L,orgId,"",0,0,list);
		}
		return list;
	}
	
	public List<Blog> getBlogs(long orgId,String sort){
		List<Blog> list = getBlogsFromCache(0L,orgId,sort,0,0);
		if(list == null){
			list = blogDao.getBlogs(orgId,sort);
			setBlogsToCache(0L,orgId,sort,0,0,list);
		}
		return list; 
	}
	
	public List<Blog> getBlogs(long orgId, int start, int end){
		List<Blog> list = (List<Blog>)getBlogsFromCache(0L,orgId,"",start,end);
		if(list == null){
			list = blogDao.getBlogs(orgId,null,start,end);
			setBlogsToCache(0L,orgId,"",start,end,list);
		}
		return list; 
	}
	
	public List<Blog> getBlogs(long orgId,String sort, int start, int end){
		List<Blog> list = getBlogsFromCache(0L,orgId,sort,start,end);
		if(list == null){
			list = blogDao.getBlogs(orgId,sort,start,end);
			setBlogsToCache(0L,orgId,sort,start,end,list);
		}
		return list; 
	}

	public List<Blog> getBlogsByCategory(long categoryId, String sort, int start, int end){
		String key = categoryId+CacheService.SEPARATOR+sort+CacheService.SEPARATOR+start+CacheService.SEPARATOR+end;
		List<Blog> list = (List<Blog>)getCacheService().getList(Blog.class,key);
		if(list == null){
			list = blogDao.getBlogsByCategory(categoryId,sort,start,end);
			getCacheService().setList(Blog.class,key,list);
		}
		return list; 
	}
	
	public List<BlogCategory> getBlogCategoriesByUser(long userId){
		List<BlogCategory> list = (List<BlogCategory>)getCacheService().getList(BlogCategory.class,userId);
		if(list == null){
			list = blogDao.getBlogCategoriesByUser(userId);
			getCacheService().setList(BlogCategory.class,userId,list);
		}
		return list;  
	}
	
	public int getBlogsTotalByCategory(long categoryId){
		String key = categoryId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Blog.class,key);
		if(count == null){
			count = blogDao.getBlogsTotalByCategory(categoryId);
			getCacheService().setObject(Blog.class,key,count);
		}
		return count;
	}
	
	protected List<Blog> getBlogsFromCache(long userId, long orgId,String sort,int start,int end){
		Map<String,List<Blog>> map = (Map<String,List<Blog>>)getCacheService().getObject(Blog.class,orgId+CacheService.SEPARATOR+userId);
		if(map == null) return null;
		return map.get(sort+CacheService.SEPARATOR+start+CacheService.SEPARATOR+end);
	}
	
	protected void setBlogsToCache(long userId, long orgId,String sort,int start,int end,List<Blog> list){
		ConcurrentHashMap<String,List<Blog>> map = (ConcurrentHashMap<String,List<Blog>>)getCacheService().getObject(Blog.class,orgId+CacheService.SEPARATOR+userId);
		if(map == null) map = new ConcurrentHashMap<String,List<Blog>>();
		map.put(sort+CacheService.SEPARATOR+start+CacheService.SEPARATOR+end,list);
		getCacheService().setObject(Blog.class,orgId+CacheService.SEPARATOR+userId,map);
	}
	
	public BlogDao getBlogDao() {
		return blogDao;
	}	
	
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	private BlogDao blogDao;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
}
