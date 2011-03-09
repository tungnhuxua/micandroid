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

package org.light.portal.controller;

import static org.light.portal.util.Constants._MAX_ROW_PER_FORUM_PAGE;
import static org.light.portal.util.Constants._RSS_URL_PREFIX;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.RssFactory;
import org.light.portal.model.Organization;
import org.light.portal.model.User;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.StringUtil;
import org.light.portlets.blog.Blog;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumPost;

/**
 * 
 * @author Jianmin Liu
 **/
public class RssController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		int index = uri.lastIndexOf("/");		
		String path = uri.substring(index+1);
		
		if(uri.indexOf(_RSS_URL_PREFIX) >= 0){
			doRss(request, response, path);
		}
		
		chain.execute(request,response);
	}
	
	private void doRss(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "no-store");
        
        if(uri.startsWith("blog")){
			String id = uri.substring(4,uri.indexOf("."));
			if(!StringUtil.isEmpty(id)){
				long userId = Long.parseLong(id);
				User user = this.getUserService(request).getUserById(userId);
				if(user != null){
					List<Blog> blogs = this.getBlogService(request).getBlogsByUser(userId,OrganizationThreadLocal.getOrganizationId());
					String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/blog/";
					(new File(dir)).mkdirs();
					String xml = dir+uri;
					String title = user.getName()+"'s blog";
					if(user.getId() == OrganizationThreadLocal.getOrg().getUserId()) title = OrganizationThreadLocal.getOrg().getWebId()+" blog";
					RssFactory.getInstance().getBlogRss(title,"",xml,blogs,getURL(request));
					request.getSession().getServletContext().getRequestDispatcher("/feed/blog/"+uri).forward(request,response);				
				}else{
					response.getWriter().print("this blog is not avaliable");
				}
			}else{
				response.getWriter().print("this blog is not avaliable");
			}
		}
        else if(uri.startsWith("communityBlog")){
			String id = uri.substring(13,uri.indexOf("."));
			if(!StringUtil.isEmpty(id)){
				long orgId = Long.parseLong(id);
				Organization org = this.getUserService(request).getOrgById(orgId);
				if(org != null){
					List<Blog> blogs = this.getBlogService(request).getBlogs(orgId);
					String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/blog/";
					(new File(dir)).mkdirs();
					String xml = dir+uri;
					String title = org.getWebId()+" blog";					
					RssFactory.getInstance().getBlogRss(title,"",xml,blogs,getURL(request));
					request.getSession().getServletContext().getRequestDispatcher("/feed/blog/"+uri).forward(request,response);				
				}else{
					response.getWriter().print("this blog is not avaliable");
				}
			}else{
				response.getWriter().print("this blog is not avaliable");
			}
		}
        else if(uri.startsWith("forum")){
			String id = uri.substring(5,uri.indexOf("."));
			if(id.indexOf("p") > 0){
				String[] ids= id.split("p");
				long forumId = Long.parseLong(ids[0]);
				int pageId = Integer.parseInt(ids[1]);
				List<ForumPost> topics = this.getForumService(request).getTopicsByForum(forumId,pageId - 1,_MAX_ROW_PER_FORUM_PAGE);
				Forum forum = this.getForumService(request).getForumById(forumId);
				if(forum != null){
					String category = MessageUtil.getMessage(forum.getCategory().getName(),getLocale(request))+":"+MessageUtil.getMessage(forum.getName(),getLocale(request));
					String categoryDesc ="";
					if(forum.getCategory().getDesc() != null)
						categoryDesc = MessageUtil.getMessage(forum.getCategory().getDesc(),getLocale(request));
					if(forum.getDesc() != null)
						categoryDesc += " "+MessageUtil.getMessage(forum.getDesc(),getLocale(request));
					String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/forum/";
					(new File(dir)).mkdirs();
					String xml = dir+uri;
					RssFactory.getInstance().getForumRss(category,categoryDesc,id,xml,topics,getURL(request));
					request.getSession().getServletContext().getRequestDispatcher("/feed/forum/"+uri).forward(request,response);				
				}else{
					response.getWriter().print("this fourm is not avaliable");
				}
			}else{
				response.getWriter().print("this fourm is not avaliable");
			}
		}
		else if(uri.startsWith("topic")){
			String id = uri.substring(5,uri.indexOf("."));
			if(id.indexOf("p") > 0){
				String[] ids= id.split("p");
				long topicId = Long.parseLong(ids[0]);
				int pageId = Integer.parseInt(ids[1]);
				List<ForumPost> posts = this.getForumService(request).getPostsByTopic(topicId,pageId - 1,_MAX_ROW_PER_FORUM_PAGE);
				if(posts != null && posts.size() > 0){
					Forum forum = this.getForumService(request).getForumById(posts.get(0).getForumId());
					String category = MessageUtil.getMessage(forum.getCategory().getName(),getLocale(request))+":"+MessageUtil.getMessage(forum.getName(),getLocale(request));
					String categoryDesc ="";
					if(forum.getCategory().getDesc() != null)
						categoryDesc = MessageUtil.getMessage(forum.getCategory().getDesc(),getLocale(request));
					if(forum.getDesc() != null)
						categoryDesc += " "+MessageUtil.getMessage(forum.getDesc(),getLocale(request));
					String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/forum/";
					(new File(dir)).mkdirs();
					String xml = dir+uri;
					RssFactory.getInstance().getForumTopicRss(category,categoryDesc,xml,posts,getURL(request));
					request.getSession().getServletContext().getRequestDispatcher("/feed/forum/"+uri).forward(request,response);				
				}else{
					response.getWriter().print("this fourm is not avaliable");
				}
				
			}else{
				response.getWriter().print("this fourm is not avaliable");
			}
		}
	}
	
	private String getURL(HttpServletRequest request){
		String host = "http://"+request.getHeader("Host");
		String uri = request.getRequestURI();	
		int index = uri.indexOf(_RSS_URL_PREFIX);
		if(index > 0) host = host+uri.substring(0,index);
		return host;
	}
}
