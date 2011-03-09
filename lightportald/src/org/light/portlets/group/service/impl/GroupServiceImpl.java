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

package org.light.portlets.group.service.impl;

import java.util.List;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.Subdomain;
import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.group.Group;
import org.light.portlets.group.GroupCategory;
import org.light.portlets.group.UserGroup;
import org.light.portlets.group.dao.GroupDao;
import org.light.portlets.group.service.GroupService;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupServiceImpl extends BaseServiceImpl implements GroupService{
	private GroupDao groupDao;
	private UserService userService;
	
	public boolean createGroup(Group group, User user){
	   Subdomain sd = new Subdomain(group.getUri(),OrganizationThreadLocal.getOrganizationId());
	   this.save(sd);
	   Organization org = new Organization(group.getUri(),"","","","","","","");
	   org.setParentId(OrganizationThreadLocal.getOrganizationId());
	   org.setUserId(OrganizationThreadLocal.getOrg().getUserId());	
	   org.setType(Organization._TYPE_GROUP);
	   OrgProfile profile = new OrgProfile(user.getLanguage(),"","","");
	   this.getUserService().createSubOrganization(org,profile);
	   group.setId(org.getId());	
	   group.setCategory(this.getGroupCategoryById(group.getCategoryId()).getName());
	   this.save(group);
	   UserGroup userGroup = new UserGroup(group.getLeaderId(),group.getId());
	   this.save(userGroup);
	   return true;
	}
	public List<Group> viewGroupsByOrgId(long orgId){
		List<Group> groups = getCacheService().getGroups(orgId);
		if(groups == null){
			groups = groupDao.viewGroupsByOrgId(orgId);
			getCacheService().setGroups(orgId,groups);
		}
		return groups;
	}
	public List<Group> getGroupsByOrgId(long orgId){
		return groupDao.getGroupsByOrgId(orgId);
	}
	public List<Group> getGroupsByOrgId(long orgId, int pageId, int max){
		return groupDao.getGroupsByOrgId(orgId, pageId, max);
	}
	public List<Group> getGroupsByCategory(long categoryId,long orgId){
		String key = orgId+"_"+categoryId;
		List<Group> groups = (List<Group>)getCacheService().getList(Group.class,key);
		if(groups == null){
			groups = groupDao.getGroupsByCategory(categoryId,orgId);
			getCacheService().setList(Group.class,key,groups);
		}
		return groups;
	}
	public int getUserGroupCount(long userId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(UserGroup.class,key);
		if(count == null){
			count = groupDao.getUserGroupCount(userId);
			getCacheService().setObject(UserGroup.class,key,count);
		}
		return count;	
	}
	public int getGroupsCount(long orgId){
		String key = orgId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Group.class,key);
		if(count == null){
			count = groupDao.getGroupsCount(orgId);
			getCacheService().setObject(Group.class,key,count);
		}
		return count;
	}
	public List<UserGroup> getGroupsByUser(long userId){
		String key = CacheService.TYPE1+CacheService.SEPARATOR+userId;
		List<UserGroup> groups = (List<UserGroup>)getCacheService().getList(UserGroup.class,key);
		if(groups == null){
			groups = groupDao.getGroupsByUser(userId);
			getCacheService().setList(UserGroup.class,key,groups);
		}
		return groups;		
	}
	public UserGroup getUserGroup(long userId, long groupId){
		String key = userId+CacheService.SEPARATOR+groupId;
		UserGroup ugroup = (UserGroup)getCacheService().getObject(UserGroup.class,key);
		if(ugroup == null){
			ugroup = groupDao.getUserGroup(userId,groupId);
			getCacheService().setObject(UserGroup.class,key,ugroup);
		}
		return ugroup;
	}
	public List<UserGroup> getUsersByGroup(long groupId){
		String key = CacheService.TYPE2+CacheService.SEPARATOR+groupId;
		List<UserGroup> groups = (List<UserGroup>)getCacheService().getList(UserGroup.class,key);
		if(groups == null){
			groups = groupDao.getUsersByGroup(groupId);
			getCacheService().setList(UserGroup.class,key,groups);
		}
		return groups;	
	}
	public List<GroupCategory> getGroupCategories(long orgId){
		List<GroupCategory> categories = (List<GroupCategory>)getCacheService().getList(GroupCategory.class,orgId);
		if(categories == null){
			categories = groupDao.getGroupCategories(orgId);
			getCacheService().setList(GroupCategory.class,orgId,categories);
		}
		return categories;
	}
	public GroupCategory getGroupCategoryById(long id){
		GroupCategory category = (GroupCategory)getCacheService().getObject(GroupCategory.class,id);
		if(category == null){
			category = groupDao.getGroupCategoryById(id);
			getCacheService().setObject(GroupCategory.class,id,category);
		}
		return category;
	}
	public Group getGroupById(long id){
		Group group = (Group)getCacheService().getObject(Group.class,id);
		if(group == null){
			group = groupDao.getGroupById(id);
			getCacheService().setGroup(group);
		}
		return group;
	}
	public Group getGroupByUri(String uri,long orgId){
		Group group = (Group)getCacheService().getObject(Group.class,uri);
		if(group == null){
			group = groupDao.getGroupByUri(uri,orgId);
			getCacheService().setGroup(group);
		}
		return group;		
	}
	public List<Group> searchGroups(Group group){
		return groupDao.searchGroups(group);
	}
	public GroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
