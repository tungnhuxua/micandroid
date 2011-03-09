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

package org.light.portlets.group.service;

import java.util.List;

import org.light.portal.core.service.BaseService;
import org.light.portal.model.User;
import org.light.portlets.group.Group;
import org.light.portlets.group.GroupCategory;
import org.light.portlets.group.UserGroup;

/**
 * 
 * @author Jianmin Liu
 **/
public interface GroupService extends BaseService{
	public boolean createGroup(Group group, User user);
	public List<Group> viewGroupsByOrgId(long orgId);
	public List<Group> getGroupsByOrgId(long orgId);
	public List<Group> getGroupsByOrgId(long orgId, int pageId, int max);
	public List<Group> getGroupsByCategory(long categoryId,long orgId);
	public List<UserGroup> getGroupsByUser(long userId); 
	public List<UserGroup> getUsersByGroup(long groupId);
	public UserGroup getUserGroup(long userId, long groupId);
	public List<GroupCategory> getGroupCategories(long orgId);
	public GroupCategory getGroupCategoryById(long id);
	public Group getGroupById(long id);
	public Group getGroupByUri(String uri,long orgId);
	public List<Group> searchGroups(Group group);
	public int getUserGroupCount(long userId);
	public int getGroupsCount(long orgId);
}
