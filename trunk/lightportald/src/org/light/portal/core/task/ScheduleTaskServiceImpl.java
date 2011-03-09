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

package org.light.portal.core.task;

import static org.light.portal.util.Constants._FRONT_HOST;
import static org.light.portal.util.Constants._BACKEND_HOST;
import static org.light.portal.util.Constants._PORTAL_FRONT_DAILY_TASK;
import static org.light.portal.util.Constants._PORTAL_BACKEND_DAILY_TASK;

import java.util.List;

import org.light.portal.core.PortalTask;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Organization;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.group.service.GroupService;

/**
 * 
 * @author Jianmin Liu
 **/
public class ScheduleTaskServiceImpl implements  ScheduleTaskService {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private NotificationService notificationService;	
	private UserService userService;
	private GroupService groupService;
	private ForumService forumService;
	private BlogService blogService;
	
	
	
	public void runDailyTask(){
		if(_FRONT_HOST){
			logger.info("start running front host Daily Task");
			List<Organization> orgs = this.getUserService().getOrganizations();
			if(orgs != null && orgs.size() > 0){
				for(Organization org :orgs){
					String name = PropUtil.getString(_PORTAL_FRONT_DAILY_TASK,org.getWebId());
					if(!StringUtil.isEmpty(name)){
						if(name != null){
							try{
								Object task = Class.forName(name).newInstance();
								if(task instanceof PortalTask){
									((PortalTask)task).execute(org,notificationService,userService,groupService,forumService,blogService);
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
			logger.info("end running front host Daily Task");	
		}
		if(_BACKEND_HOST){
			logger.info("start running backend host Daily Task");
			List<Organization> orgs = this.getUserService().getOrganizations();
			if(orgs != null && orgs.size() > 0){
				for(Organization org :orgs){
					String name = PropUtil.getString(_PORTAL_BACKEND_DAILY_TASK,org.getWebId());
					if(!StringUtil.isEmpty(name)){
						if(name != null){
							try{
								Object task = Class.forName(name).newInstance();
								if(task instanceof PortalTask){
									((PortalTask)task).execute(org,notificationService,userService,groupService,forumService,blogService);
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
			logger.info("end running backend host Daily Task");	
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
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
}
