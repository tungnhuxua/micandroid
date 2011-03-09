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

package org.light.portal.cache.impl;

import static org.light.portal.util.Constants._CACHE_ENABLED;
import static org.light.portal.util.Constants._CACHE_REPLICATION_ON_CACHE_WRITE;
import static org.light.portal.util.Constants._CACHE_REPLICATION_ON_CACHE_WRITE_LIST;
import static org.light.portal.util.Constants._CACHE_TIME_MAX_LIVE;
import static org.light.portal.util.Constants._CACHE_TIME_MIN_LIVE;
import static org.light.portal.util.Constants._OBJECT_TYPE_BLOG;
import static org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.light.portal.cache.Cache;
import org.light.portal.cache.CacheObject;
import org.light.portal.cache.CacheService;
import org.light.portal.distribute.Event;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Entity;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectPreferences;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.SocialActivity;
import org.light.portal.model.URLable;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserFile;
import org.light.portal.model.UserMusic;
import org.light.portal.model.UserObjectRole;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserTag;
import org.light.portal.user.service.UserService;
import org.light.portal.util.DateUtil;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.BlogCategory;
import org.light.portlets.calendar.CalendarBean;
import org.light.portlets.calendar.CalendarEvent;
import org.light.portlets.chat.ChattingUser;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.group.Group;
import org.light.portlets.group.UserGroup;
import org.light.portlets.todolist.ToDoBean;

/**
 * 
 * @author Jianmin Liu
 **/
public class CacheServiceImpl implements CacheService {
		
	public void init(){		
	}
		
	public Object getObject(Serializable key){
		return getObject(null,key);
	}
	
	public Object getObject(Class klass, Serializable key){
		CacheObject cobj = get(((klass != null) ? klass.getName() : "")+key);
		return (cobj != null && cobj.get() != null)
				? cobj.get() : null;
	}
	
	public void setObject(Serializable key, Serializable object){
		setObject(null,key,object);
	}
	public void setObject(Serializable key, Serializable object,boolean fixedTime){
		setObject(null,key,object,fixedTime);
	}
	public void setObject(Class klass, Serializable key, Serializable object){
		setObject(klass,key,object,false);		
	}
	public void setObject(Class klass, Serializable key, Serializable object,boolean fixedTime){
		if(object == null) return;
		key = ((klass != null) ? klass.getName() : "")+key;
		CacheObject<Serializable> cobj = (fixedTime)
											? new CacheObject<Serializable>(key,object,_CACHE_TIME_MAX_LIVE,fixedTime)
											: new CacheObject<Serializable>(key,object,_CACHE_TIME_MAX_LIVE,_CACHE_TIME_MIN_LIVE);
		put(key,cobj);
	}
	public void removeObject(Serializable key){
		removeObject(null,key);	
	}	
	
	public void removeObject(Class klass, Serializable key){
		key = ((klass != null) ? klass.getName() : "")+key;
		remove(key);		
	}
	
	public List<? extends Serializable> getList(Serializable key){
		return getList(null,key);
	}
	
	public List<? extends Serializable> getList(Class klass, Serializable key){
		CacheObject cobj = get(((klass != null) ? klass.getName() : "")+LIST_PREFIX+key);
		return (cobj != null && cobj.get() != null && cobj.get() instanceof List)
				? (List<? extends Serializable>)cobj.get() : null;
	}
	
	public void setList(Serializable key, List<? extends Serializable> list){
		setList(null,key,list);
	}
	public void setList(Serializable key, List<? extends Serializable> list,boolean fixedTime){
		setList(null,key,list,fixedTime);
	}
	public void setList(Class klass, Serializable key, List<? extends Serializable> list){
		setList(klass,key,list,false);				
	}
	public void setList(Class klass, Serializable key, List<? extends Serializable> list,boolean fixedTime){
		if(list == null) return;
		key = ((klass != null) ? klass.getName() : "")+LIST_PREFIX+key;
		CacheObject<List<? extends Serializable>> cobj = (fixedTime)
															? new CacheObject<List<? extends Serializable>>(key,list,_CACHE_TIME_MAX_LIVE,fixedTime)
															: new CacheObject<List<? extends Serializable>>(key,list,_CACHE_TIME_MAX_LIVE,_CACHE_TIME_MIN_LIVE);
		put(key,cobj);
	}
	
	public void removeList(Serializable key){
		removeList(null,key);
	}
	
	public void removeList(Class klass, Serializable key){
		key = ((klass != null) ? klass.getName() : "")+LIST_PREFIX+key;
		remove(key);		
	}
		
	public Organization getOrg(String key){
		return (Organization)getObject(Organization.class,key);
	}
	
	public void setOrg(Organization org){
		setObject(Organization.class,org.getId(),org);	
		if(org.getParentId() == 0){
			if(org.getWebId() != null) setObject(Organization.class,org.getWebId(),org);	
			if(org.getVirtualHost() != null) setObject(Organization.class,org.getVirtualHost(),org);
		}
	}	
	
	public List<Organization> getOrgs(){
		return (List<Organization>)getList(Organization.class, ALL);
	}
	
	public void setOrgs(List<Organization> orgs){
		setList(Organization.class, ALL, orgs);		
	}
	
	public List<Group> getGroups(long orgId){
		return (List<Group>)getList(Group.class, ALL);		
	}
	
	public void setGroups(long orgId, List<Group> groups){
		setList(Group.class, orgId, groups);	
		addURLables(orgId,groups);
	}
	
	public void setGroup(Group group){
		if(group == null) return;
		setObject(Group.class,group.getId(),group);
		setObject(Group.class,group.getUri(),group);
	}
			
	public User getUser(long id){
		return (User)getObject(User.class,id);
	}
	
	public User getUser(String key, long orgId){
		return (User)getObject(User.class,orgId+key);		
	}
	
	public void setUser(User user){
		if(user == null) return;
		String key = User.class.getName()+user.getId();
		removeObject(key);
		setObject(null,key,user);
		String key2 = User.class.getName()+user.getOrgId()+user.getUserId();
		setObject(null,key2,user);;
		String key3 = User.class.getName()+user.getOrgId()+user.getUri();
		setObject(null,key3,user);
	}
	
	protected void removeUser(User user){
		removeObject(User.class,user.getId());
		removeObject(User.class,user.getOrgId()+user.getUserId());
		removeObject(User.class,user.getOrgId()+user.getUri());		
	}
		
	public List<User> getUsers(long orgId){
		return (List<User>)getList(User.class, orgId);
	}
	
	public void setUsers(long orgId, List<User> users){
		setList(User.class, orgId, users);	
		addURLables(orgId,users);
	}
	
	public List<URLable> getURLables(long orgId){
		return (List<URLable>)getList(URLable.class, orgId);
	}
	
	public void addURLables(long orgId, List<? extends URLable> urlables){
		if(urlables == null) return;			
		lock.lock();
		List<URLable> allUrlables = getURLables(orgId);
		if(allUrlables == null)
			allUrlables = new LinkedList<URLable>();
		allUrlables.addAll(urlables);		
		setList(URLable.class,orgId,allUrlables);
		lock.unlock();
	}
	
	public ObjectRole getRole(String name, long orgId){
		Map<String,ObjectRole> roleMap = getRoles(orgId);
		return (roleMap != null) ? roleMap.get(name) : null;
	}
	
	public void setRole(ObjectRole role, long orgId){
		if(role == null) return;
		HashMap<String,ObjectRole> roleMap = getRoles(orgId);
		if(roleMap == null)
			roleMap = new HashMap<String,ObjectRole>();
		roleMap.put(role.getName(),role);
		setObject(ObjectRole.class,orgId,roleMap);		
	}
	
	protected HashMap<String,ObjectRole> getRoles(long orgId){
		return (HashMap<String,ObjectRole>)getObject(ObjectRole.class,orgId);	
	}
	
	public void setRoles(List<ObjectRole> roles, long orgId){
		if(roles == null) return;
		HashMap<String,ObjectRole> roleMap = new HashMap<String,ObjectRole>();
		for(ObjectRole role : roles){
			roleMap.put(role.getName(),role);
		}
		setObject(ObjectRole.class,orgId,roleMap);		
	}

	public Portal getPortal(long id){
		return (Portal)getObject(Portal.class,id);	
	}
	
	public Portal getPortal(String key){
		return (Portal)getObject(Portal.class,key);	
	}
	public void setPortal(Portal portal){
		setObject(Portal.class,portal.getId(),portal);
		setObject(Portal.class,portal.getOrgId()+portal.getOwnerId(),portal);
	}
		
	public List<PortalTab> getPortalTabs(Serializable key){
		return (List<PortalTab>)getList(PortalTab.class, key);
	}
	
	public void setPortalTabs(Serializable key, List<PortalTab> tabs){
		setList(PortalTab.class, key, tabs, true);	
	}
	
	protected void removePortalTabs(Serializable key){
		removeList(PortalTab.class,key);		
	}

	public PortalTab getPortalTab(Long id){
		return (PortalTab)getObject(PortalTab.class,id);
	}
	
	public PortalTab getPortalTab(String key){
		return (PortalTab)getObject(PortalTab.class,key);
	}
	
	public void setPortalTab(PortalTab tab){
		if(tab == null) return;
		setObject(PortalTab.class,tab.getId(),tab);		
		if(tab.getUrl() != null){
			setObject(PortalTab.class,tab.getUrl(),tab);		
		}
	}
	
	protected void removePortalTab(PortalTab tab){
		removeObject(PortalTab.class,tab.getId());
		if(tab.getUrl() != null)
			removeObject(PortalTab.class,tab.getUrl());		
	}
	
	public List<PortletObject> getPortlets(Long tabId){
		return (List<PortletObject>)getList(PortletObject.class, tabId);
	}
	
	public void setPortlets(long tabId, List<PortletObject> portlets){
		setList(PortletObject.class, tabId, portlets);
	}
	
	protected void removePortlets(long tabId){
		removeList(PortletObject.class, tabId);
	}
	
	public List<PortletObjectPreferences> getPortletPreferences(Long portletId){
		return (List<PortletObjectPreferences>)getList(PortletObjectPreferences.class, portletId);		
	}
	
	public void setPortletPreferences(Long portletId, List<PortletObjectPreferences> preferences){
		setList(PortletObjectPreferences.class,portletId,preferences);
	}
	
	public List<PortletObjectRef> getPortletRefs(String key){
		return (List<PortletObjectRef>)getList(PortletObjectRef.class, key);
	}
	
	public void setPortletRefs(String key, List<PortletObjectRef> refs){
		setList(PortletObjectRef.class,key,refs);
	}
	
	public PortletObjectRef getPortletRef(String key){
		return (PortletObjectRef)getObject(PortletObjectRef.class, key);
	}
	
	public void setPortletRef(String key, PortletObjectRef ref){
		setObject(PortletObjectRef.class,key,ref);
	}
						
	public void update(Entity entity) {
		if(entity instanceof Organization) delete((Organization)entity);
		else if(entity instanceof Group) update((Group)entity);
		else if(entity instanceof User) update((User)entity);
		else if(entity instanceof UserObjectRole) delete((UserObjectRole)entity);
		else if(entity instanceof URLable) update((URLable)entity);
		else if(entity instanceof Portal) update((Portal)entity);
		else if(entity instanceof PortalTab) update((PortalTab)entity);
		else if(entity instanceof PortletObject) update((PortletObject)entity);
		else if(entity instanceof PortletObjectPreferences) update((PortletObjectPreferences)entity);
		else if(entity instanceof PortletObjectRef) delete((PortletObjectRef)entity);
		else if(entity instanceof UserComment) delete((UserComment)entity);
		else if(entity instanceof ForumPost) delete((ForumPost)entity);
		else if(entity instanceof Blog) delete((Blog)entity);
		else if(entity instanceof BlogCategory) delete((BlogCategory)entity);
		else if(entity instanceof org.light.portlets.message.Message) delete((org.light.portlets.message.Message)entity);
		else if(entity instanceof Connection) delete((Connection)entity);
		else if(entity instanceof ToDoBean) delete((ToDoBean)entity);
		else if(entity instanceof CalendarBean) delete((CalendarBean)entity);
		else if(entity instanceof CalendarEvent) delete((CalendarEvent)entity);
		else if(entity instanceof ChattingUser) update((ChattingUser)entity);
		else if(entity instanceof UserPicture) delete((UserPicture)entity);
		else if(entity instanceof UserMusic) delete((UserMusic)entity);
		else if(entity instanceof UserFile) delete((UserFile)entity);
		else if(entity instanceof UserTag) delete((UserTag)entity);
		else if(entity instanceof UserGroup) delete((UserGroup)entity);
		else if(entity instanceof SocialActivity) delete((SocialActivity)entity);
		else{
			removeObject(entity.getClass(),entity.getId());
			removeList(entity.getClass(),entity.getOrgId());
		}
	}
	
	public void delete(Entity entity) {		
		if(entity instanceof Organization) delete((Organization)entity);
		else if(entity instanceof Group) delete((Group)entity);
		else if(entity instanceof User) delete((User)entity);
		else if(entity instanceof UserObjectRole) delete((UserObjectRole)entity);
		else if(entity instanceof URLable) delete((URLable)entity);
		else if(entity instanceof PortalTab) delete((PortalTab)entity);
		else if(entity instanceof PortletObject) delete((PortletObject)entity);
		else if(entity instanceof PortletObjectPreferences) delete((PortletObjectPreferences)entity);
		else if(entity instanceof PortletObjectRef) delete((PortletObjectRef)entity);
		else if(entity instanceof UserComment) delete((UserComment)entity);
		else if(entity instanceof ForumPost) delete((ForumPost)entity);
		else if(entity instanceof Blog) delete((Blog)entity);
		else if(entity instanceof BlogCategory) delete((BlogCategory)entity);
		else if(entity instanceof org.light.portlets.message.Message) delete((org.light.portlets.message.Message)entity);
		else if(entity instanceof Connection) delete((Connection)entity);
		else if(entity instanceof ToDoBean) delete((ToDoBean)entity);
		else if(entity instanceof CalendarBean) delete((CalendarBean)entity);
		else if(entity instanceof CalendarEvent) delete((CalendarEvent)entity);
		else if(entity instanceof ChattingUser) delete((ChattingUser)entity);
		else if(entity instanceof UserPicture) delete((UserPicture)entity);
		else if(entity instanceof UserMusic) delete((UserMusic)entity);
		else if(entity instanceof UserFile) delete((UserFile)entity);
		else if(entity instanceof UserTag) delete((UserTag)entity);
		else if(entity instanceof UserGroup) delete((UserGroup)entity);
		else if(entity instanceof SocialActivity) delete((SocialActivity)entity);
		else{
			removeObject(entity.getClass(),entity.getId());
			removeList(entity.getClass(),entity.getOrgId());
		}		
	}
	
	protected void delete(Organization org){
		removeObject(Organization.class,org.getId());
		if(org.getWebId() != null) removeObject(Organization.class,org.getWebId());
		if(org.getVirtualHost() != null) removeObject(Organization.class,org.getVirtualHost());
		if(org.getParentId() == 0){
			removeList(Organization.class,ALL);
		}
	}
	
	protected void update(Group group){
		setGroup(group);
		List<Group> groups = getGroups(group.getOrgId());
		if(groups != null){
			if(groups.contains(group))
				groups.remove(group);
			groups.add(0,group);
		}
		update((URLable)group);
	}
	protected void delete(Group group){	
		removeObject(Group.class,group.getId());
		removeObject(Group.class,group.getUri());
		removeObject(Group.class,group.getOrgId()+CacheService.COUNT);
		List<Group> groups = getGroups(group.getOrgId());
		if(groups != null){
			groups.remove(group);
		}
		remove((URLable)group);
	}
	
	protected void update(User user){		
		List<Connection> connections = this.getConnectionService().getBuddysByUser(user.getId());
		if(connections != null){
			for(Connection conneciton : connections){
				List<Connection> buddyConnections = (List<Connection>)getList(Connection.class,conneciton.getBuddyUserId());
				if(buddyConnections != null){
					for(Connection bc : buddyConnections){
						if(bc.getBuddyUserId() == user.getId()){
							bc.setBuddyCurrentStatusId(user.getCurrentStatus());
							removeList(Connection.class,bc.getUserId()+CacheService.ONLINE);
							removeList(Connection.class,bc.getUserId()+CacheService.UPDATED);
							//hard code for connection type
							for(int i=0;i<5;i++){
								removeList(Connection.class,bc.getUserId()+CacheService.TYPE+i);
							}
						}
					}
				}
			}
		}
		removeUser(user);
		Organization org = this.getOrg(String.valueOf(user.getOrgId()));
		if(org != null && user.getId() == org.getUserId()){
			org.setUser(user);
			return;
		}
		List<User> users = getUsers(user.getOrgId());
		if(users != null){
			if(users.contains(user))
				users.remove(user);
			users.add(0,user);
		}
		update((URLable)user);
	}
	protected void delete(User user){	
		removeUser(user);
		List<User> users = getUsers(user.getOrgId());
		if(users != null){
			users.remove(user);
		}
		remove((URLable)user);
	}
	protected void delete(UserObjectRole userRole){
		removeList(UserObjectRole.class,userRole.getUserId());
		removePortalTabs(userRole.getOrgId()+CacheService.SEPARATOR+userRole.getUserId());
		User user = getUser(userRole.getUserId());
		if(user != null){
			removeUser(user);			
		}
	}
	
	protected void update(URLable url){
		lock.lock();
		List<URLable> urls = getURLables(url.getOrgId());
		if(urls != null){
			if(urls.contains(url))
				urls.remove(url);
			urls.add(0,url);
		}
		lock.unlock();
	}
	protected void delete(URLable url){		
		lock.lock();
		List<URLable> urls = getURLables(url.getOrgId());
		if(urls != null){
			urls.remove(url);
		}
		lock.unlock();
	}
	
	protected void update(Portal portal){		
		setPortal(portal);
		Organization org = this.getOrg(String.valueOf(portal.getOrgId()));
		if(org != null && org.getPortal().getId() == portal.getId())
			org.setPortal(portal);
	}
	
	protected void update(PortalTab tab){
		setPortalTab(tab);
		delete(tab);
	}
	protected void delete(PortalTab tab){		
		if(tab.getParentId() > 0){
			removePortalTabs(tab.getParentId());
		}else{
			User user = userService.getUserByUserId(tab.getOwnerId(),tab.getOrgId());
			if(user != null) removePortalTabs(tab.getOrgId()+CacheService.SEPARATOR+user.getId());
			removePortalTabs(tab.getOrgId()+tab.getOwnerId());
			removePortalTabs("Personal"+tab.getOrgId()+tab.getPortalId());
			removePortalTabs("Profile"+tab.getOrgId()+tab.getPortalId());
		}
		removePortalTab(tab);
		removePortlets(tab.getId());		
	}
	
	protected void update(PortletObject portlet){
		if(getObject(PortletObject.class,portlet.getId()) != null) 
			setObject(PortletObject.class,portlet.getId(),portlet);
		removePortlets(portlet.getTabId());
	}
	protected void delete(PortletObject portlet){		
		removeObject(PortletObject.class,portlet.getId());
		removePortlets(portlet.getTabId());
	}
	
	protected void delete(PortletObjectRef obj){		
		removeList(PortletObjectRef.class,obj.getUserId());
		removeObject(PortletObjectRef.class,obj.getId());
		removeObject(PortletObjectRef.class,obj.getName());
	}
	
	protected void update(PortletObjectPreferences preference){		
		List<PortletObjectPreferences> preferences = getPortletPreferences(preference.getPortletId());
		if(preferences != null){
			removeList(PortletObjectPreferences.class, preference.getPortletId());
//			boolean found = false;
//			for(PortletObjectPreferences p : preferences){
//				if(p.getName().equals(preference.getName())){
//					found = true;
//					if(!p.getValue().equals(preference.getValue())){
//						p.setValue(preference.getValue());
//						return;
//					}
//				}
//			}
//			if(!found)
//				preferences.add(preference);
		}
	}
	protected void delete(PortletObjectPreferences preference){
		List<PortletObjectPreferences> preferences = getPortletPreferences(preference.getPortletId());
		if(preferences != null){
			removeList(PortletObjectPreferences.class, preference.getPortletId());
//			for(PortletObjectPreferences p : preferences){
//				if(p.getName().equals(preference.getName())){
//					preferences.remove(p);
//					return;
//				}
//			}
		}
	}
	
	protected void delete(UserComment comment){		
		removeList(UserComment.class,comment.getUserId());			
		removeList(UserComment.class,comment.getObjectType()+SEPARATOR+comment.getObjectId());
		if(comment.getObjectType() == _OBJECT_TYPE_BLOG){
			Entity entity = this.getUserService().getEntityById(Blog.class,comment.getObjectId());
			this.delete(entity);
		}else if(comment.getObjectType() == _OBJECT_TYPE_FEEDBACK){
			Entity entity = this.getUserService().getEntityById(Feedback.class,comment.getObjectId());
			this.delete(entity);
		}
	}
	protected void delete(ForumPost post){
		removeObject(ForumPost.class,post.getId());
		removeObject(CacheService.TOPICS_BY_FORUM+post.getForumId());
		if(post.getTopicId() > 0){
			removeObject(POSTS_BY_TOPIC+post.getTopicId());
			removeObject(POSTS_COUNT+post.getTopicId());
		}else{
			removeObject(TOPICS_BY_FORUM+post.getForumId());
			removeObject(TOPICS_COUNT+post.getTopicId());
		}
	}
	
	protected void delete(Blog blog){
		removeObject(Blog.class,blog.getId());
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getPostedById()+CacheService.COUNT);
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getUserId()+CacheService.COUNT);
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getPostedById()+CacheService.TYPE1+CacheService.COUNT);
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getUserId()+CacheService.TYPE1+CacheService.COUNT);
		removeObject(Blog.class,blog.getOrgId()+CacheService.COUNT);
		removeObject(Blog.class,blog.getCategoryId()+CacheService.COUNT);
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getPostedById());
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+blog.getUserId());
		removeObject(Blog.class,blog.getOrgId()+CacheService.SEPARATOR+0L);
	}
	
	protected void delete(BlogCategory entity){
		removeObject(BlogCategory.class,entity.getId());
		removeList(BlogCategory.class,entity.getUserId());		
	}
	protected void delete(org.light.portlets.message.Message message){
		removeObject(org.light.portlets.message.Message.class,message.getId());
		removeObject(org.light.portlets.message.Message.class,message.getUserId()+CacheService.SEPARATOR+CacheService.TYPE1+CacheService.COUNT);
		removeObject(org.light.portlets.message.Message.class,message.getUserId()+CacheService.SEPARATOR+CacheService.TYPE2+CacheService.COUNT);
	}
	
	protected void delete(Connection connection){
		removeObject(Connection.class,connection.getId());
		removeObject(Connection.class,connection.getUserId()+CacheService.SEPARATOR+connection.getBuddyUserId());
		removeObject(Connection.class,connection.getBuddyUserId()+CacheService.SEPARATOR+connection.getUserId());
		removeObject(Connection.class,connection.getUserId()+CacheService.COUNT);
		removeList(Connection.class,connection.getUserId());
		removeObject(Connection.class,connection.getBuddyUserId()+CacheService.COUNT);
		removeList(Connection.class,connection.getBuddyUserId());
	}

	protected void delete(ToDoBean entity){
		removeObject(ToDoBean.class,entity.getId());
		removeObject(ToDoBean.class,entity.getUserId()+CacheService.COUNT);
		removeList(ToDoBean.class,entity.getUserId());		
	}

	protected void delete(CalendarBean entity){
		removeObject(CalendarBean.class,entity.getId());
		removeObject(CalendarBean.class,entity.getUserId());		
	}
	
	protected void delete(CalendarEvent entity){
		removeObject(CalendarEvent.class,entity.getId());
		removeList(CalendarEvent.class,entity.getUserId()+CacheService.SEPARATOR+DateUtil.format(entity.getStartDate()));		
		removeList(CalendarEvent.class,DateUtil.format(entity.getStartDate())+CacheService.SEPARATOR+entity.getOrgId());
	}
	
	protected void delete(UserPicture entity){
		removeObject(UserPicture.class,entity.getId());
		removeObject(UserPicture.class,entity.getUserId()+CacheService.COUNT);
		removeList(UserPicture.class,entity.getUserId());		
	}
	
	protected void delete(UserMusic entity){
		removeObject(UserMusic.class,entity.getId());
		removeObject(UserMusic.class,entity.getUserId()+CacheService.COUNT);
		removeList(UserMusic.class,entity.getUserId());		
	}
	
	protected void delete(UserFile entity){
		removeObject(UserFile.class,entity.getId());
		removeObject(UserFile.class,entity.getUserId()+CacheService.COUNT);
		removeList(UserFile.class,entity.getUserId());		
	}
	
	protected void delete(UserTag entity){
		removeList(UserTag.class,entity.getObjectId()+CacheService.SEPARATOR+entity.getObjectType());			
	}
	
	protected void delete(UserGroup entity){
		removeList(UserGroup.class,TYPE1+CacheService.SEPARATOR+entity.getUserId());		
		removeList(UserGroup.class,TYPE2+CacheService.SEPARATOR+entity.getGroupId());	
		removeObject(UserGroup.class,entity.getUserId()+CacheService.SEPARATOR+entity.getGroupId());
		removeObject(UserGroup.class,entity.getUserId()+CacheService.COUNT);
	}
	
	protected void delete(SocialActivity entity){
		removeList(SocialActivity.class,entity.getOrgId());			
	}
	
	protected void update(ChattingUser chattingUser){
		List<ChattingUser> list = (List<ChattingUser>)getList(ChattingUser.class,chattingUser.getUserId());
		if(list == null){
			list = new LinkedList<ChattingUser>();
			setList(ChattingUser.class,chattingUser.getUserId(),list);
		}
		if(chattingUser.getStatus() == 0){
			list.add(chattingUser);
		}else{
			list.remove(chattingUser);
		}
	}
	
	protected void delete(ChattingUser chattingUser){
		List<ChattingUser> list = (List<ChattingUser>)getList(ChattingUser.class,chattingUser.getUserId());
		if(list != null){
			list.remove(chattingUser);
		}		
	}

	public CacheObject get(Serializable key){
		return cache.get(key);
	}
	
	public void put(Serializable key, CacheObject object){		
		put(key,object,true);
	}
	
	public void put(Serializable key, CacheObject object, boolean replicationFlag){		
		cache.put(key,object);
		if(replicationFlag && _CACHE_ENABLED && _CACHE_REPLICATION_ON_CACHE_WRITE){
			if(_CACHE_REPLICATION_ON_CACHE_WRITE_LIST != null){
				for(String name : _CACHE_REPLICATION_ON_CACHE_WRITE_LIST){
					if(key.toString().startsWith(name)){
						replicationPublisher.process(new org.light.portal.distribute.Message(Event.CACHE_PUT, 0L, object));
						return;
					}
				}				
			}			
		}
	}
	
	public void remove(Serializable key){
		remove(key,true);
	}
	
	public void remove(Serializable key, boolean replicationFlag){
		cache.remove(key);		
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public void setConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}	
	
	public ReplicationPublisher getReplicationPublisher() {
		return replicationPublisher;
	}

	public void setReplicationPublisher(ReplicationPublisher replicationPublisher) {
		this.replicationPublisher = replicationPublisher;
	}
	
	private Lock lock = new ReentrantLock();
	private Cache cache;
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	private UserService userService;
	private ConnectionService connectionService;
	private ReplicationPublisher replicationPublisher;
}
