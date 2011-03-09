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

package org.light.portal.task;

import static org.light.portal.util.Constants._BLOG_URL_PREFIX;
import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._FORUM_URL_PREFIX;
import static org.light.portal.util.Constants._GROUP_URL_PREFIX;
import static org.light.portal.util.Constants._SITE_MAP_FILE;
import static org.light.portal.util.Constants._SITE_MAP_PATH;
import static org.light.portal.util.Constants._SPACE_URL_PREFIX;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.light.portal.core.PortalTask;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Organization;
import org.light.portal.model.User;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;

/**
 * 
 * @author Jianmin Liu
 **/
public class SitemapDailyTask implements PortalTask{
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private NotificationService notificationService;
	private UserService userService;
	private GroupService groupService;
	private ForumService forumService;
	private BlogService blogService;
	
	public void execute(Organization org, Object... args) throws Exception{
		notificationService = (NotificationService)args[0];
		userService = (UserService)args[1];
		groupService = (GroupService)args[2];
		forumService = (ForumService)args[3];
		blogService = (BlogService)args[4];
		generateSitemap(org);
	}
	
	protected void generateSitemap(Organization org){	
		logger.info("start generating Sitemap");
		FileOutputStream fileOut = null;
		try{			
			fileOut = new FileOutputStream(new File(getFileName(org.getId())));
			generate(org,fileOut);
			fileOut.flush();
			fileOut.close();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fileOut != null) fileOut.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.info("end generating Sitemap");
	}
	
	protected void generate(Organization org, FileOutputStream fileOut) throws Exception{						
		List<User> users = userService.getUsersByOrgId(org.getId());
		for(User user : users){
			String url = "http://"+org.getWebId()+_SPACE_URL_PREFIX+user.getUri();
			fileOut.write(url.getBytes("UTF-8"));
			fileOut.write("\r\n".getBytes("UTF-8"));
		}
		List<Group> groups = groupService.getGroupsByOrgId(org.getOrgId());
		for(Group group : groups){
			String url = "http://"+org.getWebId()+_GROUP_URL_PREFIX+group.getUri();
			fileOut.write(url.getBytes("UTF-8"));
			fileOut.write("\r\n".getBytes("UTF-8"));
		}
		List<ForumPost> topics = forumService.getTopics(org.getId());
		for(ForumPost topic : topics){
			String url = "http://"+org.getWebId()+_FORUM_URL_PREFIX+topic.getForumId()+"-"+topic.getTopicId();
			fileOut.write(url.getBytes("UTF-8"));
			fileOut.write("\r\n".getBytes("UTF-8"));
		}
		List<Blog> blogs = blogService.getBlogs(org.getOrgId());
		for(Blog blog : blogs){
			String url = "http://"+org.getWebId()+_BLOG_URL_PREFIX+blog.getId();
			fileOut.write(url.getBytes("UTF-8"));
			fileOut.write("\r\n".getBytes("UTF-8"));
		}			
	}
	protected String getFileName(long orgId){
		String separator = System.getProperty("file.separator");
		return _FILE_PATH+orgId+separator+_SITE_MAP_PATH+separator+_SITE_MAP_FILE;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}