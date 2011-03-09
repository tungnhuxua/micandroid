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

package org.light.portal.cache;

import java.io.Serializable;
import java.util.List;

import org.light.portal.model.Entity;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectPreferences;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.URLable;
import org.light.portal.model.User;
import org.light.portlets.group.Group;

/**
 * 
 * @author Jianmin Liu
 **/
public interface CacheService{
	
	public void init();

	public void update(Entity entity);
	public void delete(Entity entity); 
	
	public CacheObject get(Serializable key);
	public void put(Serializable key, CacheObject object);
	public void put(Serializable key, CacheObject object,boolean replicationFlag);
	public void remove(Serializable key);
	public void remove(Serializable key,boolean replicationFlag);
	
	public Object getObject(Serializable key);
	public Object getObject(Class klass, Serializable key);
	public void setObject(Serializable key, Serializable object);
	public void setObject(Serializable key, Serializable object,boolean fixedTime);
	public void setObject(Class klass, Serializable key, Serializable object);
	public void setObject(Class klass, Serializable key, Serializable object,boolean fixedTime);
	public void removeObject(Serializable key);
	public void removeObject(Class klass, Serializable key);
	
	public List<? extends Serializable> getList(Serializable key);
	public List<? extends Serializable> getList(Class klass, Serializable key);
	public void setList(Serializable key, List<? extends Serializable> list);
	public void setList(Serializable key, List<? extends Serializable> list,boolean fixedTime);
	public void setList(Class klass, Serializable key, List<? extends Serializable> list);
	public void setList(Class klass, Serializable key, List<? extends Serializable> list,boolean fixedTime);
	public void removeList(Serializable key);
	public void removeList(Class klass, Serializable key);
	
	public Organization getOrg(String key);	
	public void setOrg(Organization org);
	
	public List<Organization> getOrgs();	
	public void setOrgs(List<Organization> list);
	
	public List<Group> getGroups(long orgId);	
	public void setGroups(long orgId, List<Group> groups);
	public void setGroup(Group group);
	
	public User getUser(long userId);
	public User getUser(String key, long orgId);
	public void setUser(User user);
	
	public List<User> getUsers(long orgId);	
	public void setUsers(long orgId, List<User> users);
	
	public List<URLable> getURLables(long orgId);		
	public void addURLables(long orgId, List<? extends URLable> nurls);
	
	public ObjectRole getRole(String name, long orgId);
	public void setRole(ObjectRole role, long orgId);
	public void setRoles(List<ObjectRole> roles, long orgId);

	public Portal getPortal(long id);
	public Portal getPortal(String key);
	public void setPortal(Portal portal);
	
	public PortalTab getPortalTab(Long id);
	public PortalTab getPortalTab(String key);
	public void setPortalTab(PortalTab tab);
	
	public List<PortalTab> getPortalTabs(Serializable key);
	public void setPortalTabs(Serializable key, List<PortalTab> tabs);
	
	public List<PortletObject> getPortlets(Long tabId);
	public void setPortlets(long tabId, List<PortletObject> portlets);
	
	public List<PortletObjectPreferences> getPortletPreferences(Long portletId);
	public void setPortletPreferences(Long portletId, List<PortletObjectPreferences> preferences);

	public PortletObjectRef getPortletRef(String key);
	public void setPortletRef(String key, PortletObjectRef ref);
		
	public List<PortletObjectRef> getPortletRefs(String key);
	public void setPortletRefs(String key, List<PortletObjectRef> refs);
	
	public final static String LIST_PREFIX = "_list_";
	public final static String ALL = "all";
	public final static String COUNT = "count";
	public final static String LAST_COUNT = "lastCount";
	public final static String TODAY_COUNT = "todayCount";
	public final static String SEPARATOR = "_";
	public final static String TYPE = "type";
	public final static String TYPE1 = "t1";
	public final static String TYPE2 = "t2";
	public final static String NULL = "null";
	public final static String TOPICS_BY_FORUM = "TopicsByForum";
	public final static String POSTS_BY_TOPIC = "PostsByTopic";
	public final static String TOPICS_COUNT = "TopicsCount";
	public final static String POSTS_COUNT = "PostsCount";
	public final static String ONLINE = "online";
	public final static String LAST_ONLINE = "lastOnline";
	public final static String UPDATED = "updated";
	public final static String TODAY_LIST = "_today_list";
}
