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

package org.light.portlets.group.dao.hibernate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portlets.group.Group;
import org.light.portlets.group.GroupCategory;
import org.light.portlets.group.UserGroup;
import org.light.portlets.group.dao.GroupDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao{	
	
	public List<Group> viewGroupsByOrgId(long orgId){
		return this.getHibernateTemplate().find("select group from Group group where group.orgId=? order by createDate desc", orgId);
	}
	
	public List<Group> getGroupsByOrgId(long orgId){
		List<Group> groups = this.getHibernateTemplate().find("select group from Group group where group.orgId=? order by createDate desc", orgId);
		return groups;
	}
	
	public List<Group> getGroupsByOrgId(long orgId, int pageId, int max){
		//List<Group> groups = this.getHibernateTemplate().find("select group from Group group where group.orgId=? order by createDate desc", orgId); 
		//String hql="select g.displayName as {g.displayName},g.uri as {g.uri},g.photoUrl as {g.photoUrl},g.photoWidth as {g.photoWidth},g.photoHeight as {g.photoHeight},g.orgId as {g.orgId},g.id as {g.id} from light_group {g} where g.orgId="+orgId+" order by createDate desc";		
		//String hql="select new Group(g.displayName,g.uri,g.photoUrl,g.photoWidth,g.photoHeight) from Group g where g.orgId="+orgId+" order by createDate desc";
		String hql="select g.displayName,g.uri,g.photoUrl,g.photoWidth,g.photoHeight from Group g where g.orgId="+orgId+" order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		int first = pageId * max;
		Query query =session.createQuery(hql)
					.setFirstResult(first)
					.setMaxResults(max);		
		Iterator iter = query.iterate();
		List<Group> groups = new LinkedList<Group>();
		while(iter.hasNext()) {
			//groups.add((Group)iter.next());
			Object[] grp = (Object[]) iter.next();
			groups.add(new Group((String)grp[0],(String)grp[1],(String)grp[2],(Integer)grp[3],(Integer)grp[4]));
		}
		session.close();
		return groups;
	}
	public List<Group> getGroupsByCategory(long categoryId,long orgId){
		List<Group> groups = this.getHibernateTemplate().find("select group from Group group where group.categoryId =? and group.orgId=?", new Object[]{categoryId,orgId}); 
		return groups;
	}
	public int getUserGroupCount(long userId){			
		String hql="select count(*) from UserGroup where userId='"+userId+"'";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public int getGroupsCount(long orgId){
		String hql="select count(*) from Group where orgId='"+orgId+"'";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<UserGroup> getGroupsByUser(long userId){
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? order by createDate desc", userId);		
		return userGroups;
	}		
	public List<UserGroup> getUsersByGroup(long groupId){
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.groupId =? order by createDate desc", groupId);		
		return userGroups;
	}
	public UserGroup getUserGroup(long userId, long groupId){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = groupId;
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? and userGroup.groupId=? order by createDate desc", params);		
		return (userGroups != null && userGroups.size() > 0) ? userGroups.get(0) : null;
	}

	public List<GroupCategory> getGroupCategories(long orgId){
		List<GroupCategory> groupCategories = this.getHibernateTemplate().find("select ref from GroupCategory ref where ref.orgId=? order by ref.name asc",orgId);
		return groupCategories;
	}
	public GroupCategory getGroupCategoryById(long id){
		return (GroupCategory)this.getHibernateTemplate().get(GroupCategory.class,id);
	}
	public Group getGroupById(long id){
		return (Group)this.getHibernateTemplate().get(Group.class,id);
	}
	
	public Group getGroupByUri(String uri,long orgId){		
		List<Group> groups = this.getHibernateTemplate().find("select group from Group group where group.uri =? and group.orgId=?", new Object[]{uri,orgId});
		Group group = null;
		if(groups != null && groups.size() > 0) group = groups.get(0);
		return group;
	}
	
	public List<Group> searchGroups(Group group){
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		List<Group> groups =session.createCriteria(Group.class)
		           .add(Example.create(group).ignoreCase().enableLike(MatchMode.ANYWHERE))
		           .list();		
		session.close();
		return groups;
	}
}
