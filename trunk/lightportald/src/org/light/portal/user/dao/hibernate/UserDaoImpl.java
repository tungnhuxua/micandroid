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

package org.light.portal.user.dao.hibernate;

import static org.light.portal.util.Constants._DEFAULT_ORG_ADMIN_PREFIX;
import static org.light.portal.util.Constants._DEFAULT_ORG_USER_PREFIX;
import static org.light.portal.util.Constants._DEFAULT_ROLE;
import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._MESSAGE_EVENT_CONNECTION;
import static org.light.portal.util.Constants._OBJECT_TYPE_ORG;
import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._PERMISSIONS_SUFFIX;
import static org.light.portal.util.Constants._PORTAL_ROLES;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import static org.light.portal.util.Constants._ROLE_ADMIN;
import static org.light.portal.util.Constants._ROLE_MEMBER;
import static org.light.portal.util.Constants._ROLE_USER;
import static org.light.portal.util.Constants._STATUS_APPROVED;
import static org.light.portal.util.Constants._STATUS_DELETE;
import static org.light.portal.util.Constants._TITLE_SUFFIX;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.model.Keyword;
import org.light.portal.model.NotKeyword;
import org.light.portal.model.NotWord;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.PicturePositionTag;
import org.light.portal.model.SocialActivity;
import org.light.portal.model.Subscriber;
import org.light.portal.model.TagFilter;
import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserExtRole;
import org.light.portal.model.UserFavourite;
import org.light.portal.model.UserFile;
import org.light.portal.model.UserInvite;
import org.light.portal.model.UserKeyword;
import org.light.portal.model.UserMusic;
import org.light.portal.model.UserObjectRole;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
import org.light.portal.user.dao.UserDao;
import org.light.portal.util.HashUtil;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PermissionUtil;
import org.light.portal.util.PropUtil;
import org.light.portlets.addressbook.AddressBook;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.bulletin.Bulletin;
import org.light.portlets.connection.Connection;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.group.GroupCategory;
import org.light.portlets.group.UserGroup;
import org.light.portlets.horoscope.Horoscope;
import org.light.portlets.message.Message;
import org.light.portlets.note.Note;
import org.light.portlets.todolist.ToDoBean;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
		
	public List<User> viewUsersByOrg(Organization org){								
		return this.getHibernateTemplate().find("select user from User user where user.orgId=? and user.id <> ? order by createDate desc",new Object[]{org.getId(),org.getUserId()});
	}
	
	public List<User> getUsersByOrgId(long orgId){
		List<User> users = this.getHibernateTemplate().find("select user from User user where user.orgId=?",orgId);
		return users;
	}
	
	public Organization getOrgByVirtualHost(String host){
		List<Organization> orgs = this.getHibernateTemplate().find("select org from Organization org where org.webId=? or org.virtualHost=?", new String[]{host,host});
		Organization org = null;
		if(orgs != null && orgs.size() > 0) org = orgs.get(0);
		return org;
	}
	public Organization getOrgById(long id){
		return (Organization)this.getHibernateTemplate().get(Organization.class,id);
	}
	public List<Organization> getOrganizations(){
		List<Organization> orgs = this.getHibernateTemplate().find("select org from Organization org where org.parentId = 0 order by org.id");
		return orgs;
	}
	public OrgProfile getOrgProfileByOrgId(long orgId, String language){
		Object[] params = new Object[2];
		params[0] = orgId;
		params[1] = language;
		List<OrgProfile> orgs = this.getHibernateTemplate().find("select org from OrgProfile org where org.orgId=? and org.language=?", params);
		OrgProfile org = null;
		if(orgs != null && orgs.size() > 0) org = orgs.get(0);
		return org;
	}	
	public List<OrgProfile> getOrgProfiles(long orgId){
		List<OrgProfile> orgs = this.getHibernateTemplate().find("select org from OrgProfile org where org.orgId = ?",orgId);
		return orgs;
	}
	public List<User> getAllUsers(){
		List<User> users = this.getHibernateTemplate().find("select user from User user");
		return users;
	}
	public long getAdminUserId(long orgId){
		long userId = 0;
		List<User> admins = this.getHibernateTemplate().find("select user from User user, UserObjectRole role, ObjectRole ref where ref.orgId="+orgId+" and ref.name='"+_ROLE_ADMIN+"' and ref.id = role.roleId and role.objectTypeId="+_OBJECT_TYPE_ORG+" and role.objectId = "+orgId+" and role.userId = user.id");
		if(admins != null && admins.size() > 0) userId = admins.get(0).getId();
		return userId;
	}
	public boolean isOrgDefaultUser(long userId){
		String hql="select count(*) from Organization org where org.userId="+userId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return (count.intValue() > 0) ? true : false;
	}
	public void createOrganization(Organization org,OrgProfile profile){
		createOrganization(org,profile,true);
	}
	public void createOrganization(Organization org,OrgProfile profile,boolean flag){
		this.save(org,flag);
		profile.setOrgId(org.getId());
		this.save(profile,flag);
		
		//add roles
		String[] roles = PropUtil.getStringArray(_PORTAL_ROLES,org.getWebId());
		if(roles != null){	
			for(String srole : roles){
				String title = PropUtil.getString(srole+_TITLE_SUFFIX);			
				ObjectRole orole = this.getUserPermissionDao().getRoleByName(srole,org.getId());
				String[] rolePermissions = PropUtil.getStringArray(srole+_PERMISSIONS_SUFFIX,org.getWebId());
				long permission = PermissionUtil.getPermission(rolePermissions);
				if(orole == null){	
					orole = new ObjectRole(srole,title,org.getId(),permission);
					this.save(orole,flag);
				}else if(orole.getPermission() != permission){
					orole.setPermission(permission);
					this.save(orole,flag);
				}
//				this.getPermissionDao().deleteRolePermissions(orole.getId());
//				RolePermission rolePermission = new RolePermission(orole.getId(),permission);
//				this.save(rolePermission,flag);
//				for(String permission : rolePermissions){
//					for(Permission p : permissions){
//						if(p.getName().equals(permission)){
//							RolePermission rolePermission = new RolePermission(orole.getId(),p.getId());
//							this.save(rolePermission,flag);
//						}
//					}
//				}			
			}
		}
		
		String ePassword = HashUtil.MD5Hashing(_DEFAULT_ORG_USER_PREFIX);	
		//default user
		User user = new User(_DEFAULT_ORG_USER_PREFIX+"@"+org.getWebId(),ePassword,_DEFAULT_ORG_USER_PREFIX,_DEFAULT_ORG_USER_PREFIX+"@"+org.getWebId(),org.getId());
		user.setDisabled(1);
		this.save(user,flag);
		org.setUserId(user.getId());
		org.setUser(user);
		this.save(org,flag);
		UserProfile userProfile = new UserProfile(user.getId());
		this.save(userProfile,flag);		
		ObjectRole role = this.getUserPermissionDao().getRoleByName(org.getRole(),org.getId());
		UserObjectRole userRole = new UserObjectRole(user.getId(),user.getOrgId(),user.getOrgId(),_OBJECT_TYPE_ORG,role.getId());				 
		this.save(userRole,flag);
	 	
	 	//admin user
	 	ePassword = HashUtil.MD5Hashing(_DEFAULT_ORG_ADMIN_PREFIX);
	 	User admin = new User(_DEFAULT_ORG_ADMIN_PREFIX+"@"+org.getWebId(),ePassword,_DEFAULT_ORG_ADMIN_PREFIX,_DEFAULT_ORG_ADMIN_PREFIX+"@"+org.getWebId(),org.getId());
		this.save(admin,flag);
		org.setAdminId(admin.getId());
		this.save(org,flag);
		UserProfile adminProfile = new UserProfile(admin.getId());
		this.save(adminProfile,flag);
		ObjectRole role1 = this.getUserPermissionDao().getRoleByName(_ROLE_ADMIN,org.getId());
		UserObjectRole adminRole1 = new UserObjectRole(admin.getId(),admin.getOrgId(),admin.getOrgId(),_OBJECT_TYPE_ORG,role1.getId());				 
		this.save(adminRole1,flag);
		ObjectRole role2 = this.getUserPermissionDao().getRoleByName(_ROLE_USER,org.getId());
		UserObjectRole adminRole2 = new UserObjectRole(admin.getId(),admin.getOrgId(),admin.getOrgId(),_OBJECT_TYPE_ORG,role2.getId());				 
		this.save(adminRole2,flag);
		ObjectRole role3 = this.getUserPermissionDao().getRoleByName(_ROLE_MEMBER,org.getId());
		UserObjectRole adminRole3 = new UserObjectRole(admin.getId(),admin.getOrgId(),admin.getOrgId(),_OBJECT_TYPE_ORG,role3.getId());				 
		this.save(adminRole3,flag);
	 		 	
	 	//create organization references
	 	createOrgRef(org,flag);
	}
	
	public void createSubOrganization(Organization org,OrgProfile profile){		
		this.save(org);
		profile.setOrgId(org.getId());
		this.save(profile); 
		String ePassword = HashUtil.MD5Hashing(_DEFAULT_ORG_USER_PREFIX);	
		//default user
		String defaultUser = _DEFAULT_ORG_USER_PREFIX+"@"+org.getWebId();
		if(org.getType() == Organization._TYPE_GROUP) defaultUser = _GROUP_PREFIX+org.getId();
		User user = new User(defaultUser,ePassword,_DEFAULT_ORG_USER_PREFIX,defaultUser,org.getId());
		user.setDisabled(1);
		this.save(user);
		org.setUserId(user.getId());
		this.save(org);
		UserProfile userProfile = new UserProfile(user.getId());
		this.save(userProfile);
		ObjectRole role = this.getUserPermissionDao().getRoleByName(_DEFAULT_ROLE,OrganizationThreadLocal.getOrganizationId());
		UserObjectRole userRole = new UserObjectRole(user.getId(),user.getOrgId(),user.getOrgId(),_OBJECT_TYPE_ORG,role.getId());				 
		this.save(userRole);
	 	
	 	ForumCategory ref = new ForumCategory(org.getWebId(),org.getWebId(),"en",org.getId(),0,1);
		ref.addForum(new Forum("general",false,org.getId()));
		this.save(ref);
	}
	
	protected void createOrgRef(Organization org,boolean flag){
		if(org.getType() != Organization._TYPE_TOP_ORGANIZATION){
			ForumCategory ref = new ForumCategory(org.getWebId(),org.getWebId(),"en",org.getId(),0,1);
			ref.addForum(new Forum("general",false,org.getId()));
			this.save(ref,flag);
		}else{
			ForumCategory ref0 = new ForumCategory("automotive","en",org.getId());
			ref0.addForum(new Forum("chinese",false,org.getId()));
			ref0.addForum(new Forum("european",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("japanese",false,org.getId()));
			ref0.addForum(new Forum("northAmerican",false,org.getId()));
			ref0.addForum(new Forum("racing",false,org.getId()));
			ref0.addForum(new Forum("southKorean",false,org.getId()));
			ref0.addForum(new Forum("skill",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("business","en",org.getId());
			ref0.addForum(new Forum("business",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("money",false,org.getId()));
			ref0.addForum(new Forum("start",false,org.getId()));
			ref0.addForum(new Forum("stocks",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("campus","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("gradSchool",false,org.getId()));
			ref0.addForum(new Forum("highSchool",false,org.getId()));
			ref0.addForum(new Forum("undergrad",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("career","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("comedy","en",org.getId());
			ref0.addForum(new Forum("comedian",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("computer","en",org.getId());
			ref0.addForum(new Forum("computers",false,org.getId()));
			ref0.addForum(new Forum("electronics",false,org.getId()));
			ref0.addForum(new Forum("gadgets",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("culture","en",org.getId());
			ref0.addForum(new Forum("arts",false,org.getId()));
			ref0.addForum(new Forum("cultue",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("literature",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("filmmaker","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("food","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("recipes",false,org.getId()));
			ref0.addForum(new Forum("restaurants",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("games","en",org.getId());
			ref0.addForum(new Forum("boardGames",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("other",false,org.getId()));
			ref0.addForum(new Forum("roleGames",false,org.getId()));
			ref0.addForum(new Forum("videoGames",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("general","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("health","en",org.getId());
			ref0.addForum(new Forum("exercise",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("nutrition",false,org.getId()));
			this.save(ref0,flag);
	
			ref0 = new ForumCategory("system","en",org.getId());
			ref0.addForum(new Forum("custom",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("movies","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("music","en",org.getId());
			ref0.addForum(new Forum("acoustic",false,org.getId()));
			ref0.addForum(new Forum("alternative",false,org.getId()));
			ref0.addForum(new Forum("dance",false,org.getId()));
			ref0.addForum(new Forum("emo",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("hardcore",false,org.getId()));
			ref0.addForum(new Forum("hip",false,org.getId()));
			ref0.addForum(new Forum("metal",false,org.getId()));
			ref0.addForum(new Forum("punk",false,org.getId()));
			ref0.addForum(new Forum("rock",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("news","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("religion","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("philosophy",false,org.getId()));	
			ref0.addForum(new Forum("religion",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("science","en",org.getId());
			ref0.addForum(new Forum("biology",false,org.getId()));	
			ref0.addForum(new Forum("engineer",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("physics",false,org.getId()));	
			ref0.addForum(new Forum("spaces",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("sports","en",org.getId());
			ref0.addForum(new Forum("extremeSports",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("profSports",false,org.getId()));	
			ref0.addForum(new Forum("teamSports",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("tv","en",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("series",false,org.getId()));	
			ref0.addForum(new Forum("shows",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("travel","en",org.getId());
			ref0.addForum(new Forum("china",false,org.getId()));	
			ref0.addForum(new Forum("asia",false,org.getId()));
			ref0.addForum(new Forum("canada",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("us",false,org.getId()));	
			this.save(ref0,flag);
			
			//-------------------
			ref0 = new ForumCategory("automotive","zh_CN",org.getId());
			ref0.addForum(new Forum("chinese",false,org.getId()));
			ref0.addForum(new Forum("european",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("japanese",false,org.getId()));
			ref0.addForum(new Forum("northAmerican",false,org.getId()));
			ref0.addForum(new Forum("racing",false,org.getId()));
			ref0.addForum(new Forum("southKorean",false,org.getId()));
			ref0.addForum(new Forum("skill",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("business","zh_CN",org.getId());
			ref0.addForum(new Forum("business",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("money",false,org.getId()));
			ref0.addForum(new Forum("start",false,org.getId()));
			ref0.addForum(new Forum("stocks",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("campus","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("highSchool",false,org.getId()));
			ref0.addForum(new Forum("undergrad",false,org.getId()));
			ref0.addForum(new Forum("gradSchool",false,org.getId()));						
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("career","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			
			ref0 = new ForumCategory("emotion","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("city","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("pop","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("computer","zh_CN",org.getId());
			ref0.addForum(new Forum("computers",false,org.getId()));
			ref0.addForum(new Forum("electronics",false,org.getId()));
			ref0.addForum(new Forum("gadgets",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("culture","zh_CN",org.getId());
			ref0.addForum(new Forum("arts",false,org.getId()));
			ref0.addForum(new Forum("cultue",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("literature",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("filmmaker","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("food","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("recipes",false,org.getId()));
			ref0.addForum(new Forum("restaurants",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("games","zh_CN",org.getId());
			ref0.addForum(new Forum("boardGames",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("other",false,org.getId()));
			ref0.addForum(new Forum("roleGames",false,org.getId()));
			ref0.addForum(new Forum("videoGames",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("general","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("health","zh_CN",org.getId());
			ref0.addForum(new Forum("exercise",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("nutrition",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("system","zh_CN",org.getId());
			ref0.addForum(new Forum("custom",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("movies","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("music","zh_CN",org.getId());
			ref0.addForum(new Forum("acoustic",false,org.getId()));
			ref0.addForum(new Forum("alternative",false,org.getId()));
			ref0.addForum(new Forum("dance",false,org.getId()));
			ref0.addForum(new Forum("emo",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));
			ref0.addForum(new Forum("hardcore",false,org.getId()));
			ref0.addForum(new Forum("hip",false,org.getId()));
			ref0.addForum(new Forum("metal",false,org.getId()));
			ref0.addForum(new Forum("punk",false,org.getId()));
			ref0.addForum(new Forum("rock",false,org.getId()));
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("news","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("religion","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("philosophy",false,org.getId()));	
			ref0.addForum(new Forum("religion",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("science","zh_CN",org.getId());
			ref0.addForum(new Forum("biology",false,org.getId()));	
			ref0.addForum(new Forum("engineer",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("physics",false,org.getId()));	
			ref0.addForum(new Forum("spaces",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("sports","zh_CN",org.getId());
			ref0.addForum(new Forum("extremeSports",false,org.getId()));	
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("profSports",false,org.getId()));	
			ref0.addForum(new Forum("teamSports",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("tv","zh_CN",org.getId());
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("series",false,org.getId()));	
			ref0.addForum(new Forum("shows",false,org.getId()));	
			this.save(ref0,flag);
			
			ref0 = new ForumCategory("travel","zh_CN",org.getId());
			ref0.addForum(new Forum("china",false,org.getId()));	
			ref0.addForum(new Forum("asia",false,org.getId()));
			ref0.addForum(new Forum("canada",false,org.getId()));
			ref0.addForum(new Forum("general",false,org.getId()));	
			ref0.addForum(new Forum("us",false,org.getId()));	
			this.save(ref0,flag);		
		}

		GroupCategory ref1 = new GroupCategory("group.category.other",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.activities",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.automotive",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.business",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.cities",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.companies",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.computers",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.countries",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.cultures",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.entertainment",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.family",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.fun",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.fashion",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.film",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.food",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.games",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.gay",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.gov",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.health",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.hobbies",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.literature",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.money",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.music",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.nightlife",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.nonprofit",org.getId());
		this.save(ref1,flag);		
		ref1 = new GroupCategory("group.category.pets",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.places",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.prof",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.recreation",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.religion",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.schools",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.science",org.getId());
		this.save(ref1,flag);
		ref1 = new GroupCategory("group.category.sorority",org.getId());
		this.save(ref1,flag);
	}
		
	public User getUserById(long id){
		User user = (User)this.getHibernateTemplate().get(User.class,id);
		loadUserDependents(user);
		return user;
	}
	
	public User getUserByUserId(String userId, long orgId){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = orgId;
		List<User> users =this.getHibernateTemplate().find("from User where userId=? and orgId=?",params);
		User user = null;
		if(users != null && users.size() > 0){
			user = users.get(0);
			loadUserDependents(user);
		}
		return user;
	}
	private void loadUserDependents(User user){
		if(user != null){
			user.setRoles(this.getUserRoles(user.getId(),user.getOrgId()));
			//user.setPermission(this.getUserPermissionDao().getAllPermission(user.getOrgId(), user.getId(), user.getOrgId(), Constants._OBJECT_TYPE_ORG));
			//user.setPermissions(this.getUserPermissionDao().getAllPermissions(user.getOrgId(), user.getId(), user.getOrgId(), Constants._OBJECT_TYPE_ORG));
		}
		
	}
	public User getUserByUri(String uri,long orgId){
		List<User> users = this.getHibernateTemplate().find("select user from User user where user.uri =? and orgId="+orgId, uri);
		User user = null;
		if(users != null && users.size() > 0) {
			user = users.get(0);
			loadUserDependents(user);
		}
		return user;
	}
	
	public User getUserByEmail(String email){		
		return getUserByEmail(email,OrganizationThreadLocal.getOrganizationId());
	}
	public User getUserByEmail(String email, long orgId){
		List<User> users = this.getHibernateTemplate().find("select user from User user where user.email =?  and orgId="+orgId, email);
		User user = null;
		if(users != null && users.size() > 0) {
			user = users.get(0);
			loadUserDependents(user);
		}
		return user;
	}
	public UserProfile getUserProfileById(long userId){
		List<UserProfile> users = this.getHibernateTemplate().find("select user from UserProfile user where user.userId =? ", userId);
		UserProfile user = null;
		if(users != null && users.size() > 0) user = users.get(0);
		return user;
	}
	public List<User> getCoolNewPeople(int start, int max){				
		String hql="select user from User user where user.photoUrl is not null order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(max);
		List<User> list = query.list();
		session.close();
		return list;
	}
	public int getCoolNewPeopleTotal(){
		String hql="select count(*) from User user where user.photoUrl is not null";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<User> getOnlinePeople(int start, int max){		
		String hql="select user from User user where user.userCurrentStatusId = 1 order by lastLoginDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(max);
		List<User> list = query.list();
		session.close();
		return list;
	}
	public int getOnlinePeopleTotal(){
		String hql="select count(*) from User user where user.userCurrentStatusId = 1";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<UserInvite> getUserInviteByEmail(String email){
		List<UserInvite> invites = this.getHibernateTemplate().find("select invite from UserInvite invite where invite.inviteEmail=? and invite.status = 0 order by createDate asc", email);	
		return invites;
	}
	public List<UserComment> getCommentsByType(long objectId, int objectType){
		List<UserComment> userComments = this.getHibernateTemplate().find("select comments from UserComment comments where comments.objectId=? and comments.objectType = ? and comments.status = ? order by parentId asc, createDate asc", new Object[]{objectId,objectType,_STATUS_APPROVED});
		List<UserComment> result = new LinkedList<UserComment>();
		for(UserComment comment : userComments){
			if(comment.getParentId() == 0){
				result.add(comment);
			}else{
				for(UserComment parent : result){
					parent.resetChecker();
				}
				for(UserComment parent : result){
					parent.setChecked(true);
					if(comment.getParentId() == parent.getId()){
						parent.addChild(comment);
						break;
					}
//					else{
//						boolean notFound = true;
//						while(!parent.isAllChecked() && notFound){
//							UserComment compare = parent;
//							while(compare.getChildren() != null && notFound){
//								for(UserComment c : compare.getChildren()){
//									if(c.isChecked()) continue;
//									c.setChecked(true);
//									if(comment.getParentId() == c.getId()){
//										c.addChild(comment);
//										notFound = false;
//										break;
//									}else{
//										if(c.getChildren() != null){
//											compare = c;
//											break;
//										}									
//									}
//								}
//							}
//						}
//					}
				}
			}			
		}
		return result;
	}
	
	public List<UserComment> getUserComments(long userId){
		List<UserComment> userComments = this.getHibernateTemplate().find("select comments from UserComment comments where comments.objectId=? and comments.objectType = ? and comments.status = ? order by createDate desc", new Object[]{userId,_OBJECT_TYPE_USER,_STATUS_APPROVED});
		return userComments;
	}
	public List<UserComment> getUserComments(long userId, int showNumber){
	   String hql="select comments from UserComment comments where comments.objectId="+userId+" and comments.objectType = "+_OBJECT_TYPE_USER+" and comments.status = "+_STATUS_APPROVED+" order by createDate desc"; 
	   Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(0)
					.setMaxResults(showNumber);
	   List<UserComment> list = query.list();
	   session.close();
       return list;   
	}
	public int getUserCommentsCount(long userId){			
		String hql="select count(*) from UserComment where status = "+_STATUS_APPROVED+" and objectId="+userId+" and objectType = "+_OBJECT_TYPE_USER;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public UserComment getUserCommentsById(long id){
		return (UserComment)this.getHibernateTemplate().get(UserComment.class,id);
	}
	public void deleteUserCommentsById(long id){
		UserComment userComment = getUserCommentsById(id);
		if(userComment != null){
			userComment.setStatus(_STATUS_DELETE);
			this.save(userComment);
		}
	}
	public List<TagFilter> getTagFilters(long orgId){
		List<TagFilter> tagFilters = this.getHibernateTemplate().find("select tag from TagFilter tag where tag.orgId = ? order by createDate desc", orgId);
		return tagFilters;
	}
	public List<UserTag> getUserTags(long objectId, int objectType){
		List<UserTag> userTags = this.getHibernateTemplate().find("select tag from UserTag tag where tag.objectId=? and tag.objectType = ? order by createDate asc", new Object[]{objectId,objectType});
		return userTags;
	}
	public UserTag getUserTagById(long id){
		return (UserTag)this.getHibernateTemplate().get(UserTag.class,id);
	}
	public boolean hasUserTag(long objectId, int objectType, String tag){
		String hql="select count(*) from UserTag where objectId="+objectId+" and objectType="+objectType+" and tag = '"+tag +"'";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue() > 0;
	}
	public long getMaxUserTagScore(long orgId, int objectType){
		String hql="select count(*) as score  from UserTag where orgId="+orgId+" and objectType = "+objectType +" group by tag order by score desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql).setFirstResult(0).setMaxResults(1);
		List<Long> score = query.list();
		session.close();
		long max = 0;
		if(score != null && score.size() > 0) max = score.get(0);
		return max;
	}
	public int getUserPictureCount(long userId,long orgId){			
		String hql="select count(*) from UserPicture where userId="+userId+" and orgId="+orgId+" and status >= 0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<UserPicture> getUserPictures(long userId,long orgId){
		List<UserPicture> userPictures = this.getHibernateTemplate().find("select picture from UserPicture picture where picture.userId=? and picture.orgId=? and picture.status >= 0 order by createDate desc", new Object[]{userId,orgId});
		return userPictures;
	}
	
	public List<UserPicture> getUserPicturesByStatus(long userId, int status,long orgId){		
		Object[] params = new Object[3];
		params[0] = userId;
		params[1] = orgId;
		params[2] = status;
		List<UserPicture> userPictures = this.getHibernateTemplate().find("select picture from UserPicture picture where picture.userId=? and picture.orgId=? and picture.status >=? order by createDate desc", params);
		return userPictures;
	}
	
	public List<UserPicture> getUserRankPictures(long userId){		
		List<UserPicture> userPictures = this.getHibernateTemplate().find("select picture from UserPicture picture where picture.userId=? and picture.rankable=1 order by createDate desc", userId);
		return userPictures;
	}
	public void updatePictureStatus(long userId,int status){
		String hql="update UserPicture set status="+status+" where userId="+userId+" and status >= 0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
			   .executeUpdate();
		session.close();
	}
	public void updateMusicStatus(long userId,int status){
		String hql="update UserMusic set status="+status+" where userId="+userId+" and status >= 0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
			   .executeUpdate();
		session.close();
	}
	public void updateFileStatus(long userId,int status){
		String hql="update UserFile set status="+status+" where userId="+userId+" and status >= 0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
			   .executeUpdate();
		session.close();
	}
	public UserPicture getUserPictureById(long id){
		return (UserPicture)this.getHibernateTemplate().get(UserPicture.class,id);
	}
	
	public List<UserPicture> getPublicPictures(long orgId, int start,int max){
		String hql="select picture from UserPicture picture where picture.topOrgId="+orgId+" and picture.status = "+_PRIVACY_PUBLIC+" order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
						.setFirstResult(start)
						.setMaxResults(max);
		   List<UserPicture> list = query.list();
		   session.close();
	       return list;   
	}
	
	public List<UserPicture> getAllPictures(long orgId){
		List<UserPicture> userPictures = this.getHibernateTemplate().find("select picture from UserPicture picture where picture.topOrgId=? and picture.status >= 0 order by score desc, createDate desc", orgId);
		return userPictures;
	}
	
	public List<UserPicture> getOrgPictures(long orgId){
		List<UserPicture> userPictures = this.getHibernateTemplate().find("select picture from UserPicture picture where picture.orgId=? and picture.status >= 0 order by score desc,createDate desc",orgId);
		return userPictures;
	}
	
	public int getOrgPictureCount(long orgId){
		String hql="select count(*) from UserPicture where orgId="+orgId+" and status >= 0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<PicturePositionTag> getPicturePositionTags(long pictureId){
		List<PicturePositionTag> tags =  this.getHibernateTemplate().find("from PicturePositionTag tag where tag.pictureId=?", pictureId);
		return tags;
	}
	
	public List<PicturePositionTag> getPicturePositionTags(String pictureUrl){
		List<PicturePositionTag> tags =  this.getHibernateTemplate().find("from PicturePositionTag tag where tag.pictureUrl?", pictureUrl);
		return tags;
	}
	
	public PicturePositionTag getPicturePositionTagById(long id){
		return (PicturePositionTag)this.getHibernateTemplate().get(PicturePositionTag.class, id);
	}
	
	public int getUserMusicCount(long userId){			
		String hql="select count(*) from UserMusic where userId="+userId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<UserMusic> getUserMusics(long userId){
		List<UserMusic> userMusics = this.getHibernateTemplate().find("select userMusics from UserMusic userMusics where userMusics.userId=? order by createDate desc", userId);
		return userMusics;
	}
	
	public List<UserMusic> getUserMusicsByStatus(long userId, int status){		
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = status;
		List<UserMusic> userMusics = this.getHibernateTemplate().find("select userMusics from UserMusic userMusics where userMusics.userId=? and userMusics.status >=? order by createDate desc", params);
		return userMusics;
	}
	public List<UserMusic> getUserRankMusics(long userId){		
		List<UserMusic> userMusics = this.getHibernateTemplate().find("select userMusics from UserMusic userMusics where userMusics.userId=? and userMusics.rankable=1 order by createDate desc", userId);
		return userMusics;
	}
	
	public UserMusic getUserMusicById(long id){
		return (UserMusic)this.getHibernateTemplate().get(UserMusic.class,id);
	}
	public int getUserFileCount(long userId){			
		String hql="select count(*) from UserFile where userId="+userId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<UserFile> getUserFiles(long userId){
		List<UserFile> userFiles = this.getHibernateTemplate().find("select userFiles from UserFile userFiles where userFiles.userId=? order by createDate desc", userId);
		return userFiles;
	}
	public List<UserFile> getUserFilesByStatus(long userId, int status){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = status;
		List<UserFile> userFiles = this.getHibernateTemplate().find("select userFiles from UserFile userFiles where userFiles.userId=? and userFiles.status >=? order by createDate desc", params);
		return userFiles;
	}
	public UserFile getUserFileById(long id){
		return (UserFile)this.getHibernateTemplate().get(UserFile.class,id);
	}
	public List<UserFavourite> getUserFavourites(long userId){
		List<UserFavourite> userFavourites = this.getHibernateTemplate().find("select favourite from UserFavourite favourite where favourite.userId=? order by createDate asc", userId);
		return userFavourites;
	}
	
	public List<UserBlock> getUserBlocks(long userId){
		List<UserBlock> userBlocks = this.getHibernateTemplate().find("select userBlocks from UserBlock userBlocks where userBlocks.userId=? order by createDate asc", userId);
		return userBlocks;
	}
	
	public List<UserInvite> getUserInvites(long userId){
		List<UserInvite> userInvites = this.getHibernateTemplate().find("select userInvites from UserInvite userInvites where userInvites.userId=? order by createDate asc", userId);
		return userInvites;
	}
	public int getUserBulletinCount(long userId){			
		String hql="select count(*) from Bulletin where userId="+userId+" and status=0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<Bulletin> getBulletinsByUser(long userId){
		List<Bulletin> bulletins = this.getHibernateTemplate().find("select bulletin from Bulletin bulletin where bulletin.userId=? and bulletin.type=1 order by createDate desc", userId);
		return bulletins;
	}
	public List<Bulletin> getBulletinsByOrg(long orgId){
		List<Bulletin> bulletins = this.getHibernateTemplate().find("select bulletin from Bulletin bulletin where bulletin.orgId=? and bulletin.type=2 order by createDate desc", orgId);
		return bulletins;
	}
	public Bulletin getBulletinById(long id){
		Bulletin bulletin =(Bulletin)this.getHibernateTemplate().get(Bulletin.class,id);
		if(bulletin != null){
			bulletin.setStatus(1);
			this.save(bulletin);
		}
		return bulletin;
	}
	
	public UserBlock getUserBlockById(long id){
		return (UserBlock)this.getHibernateTemplate().get(UserBlock.class,id);
	}	
	
	public UserBlock getUserBlockByUser(long userId, long blockId){
		long[] params = new long[2];
		params[0] = userId;
		params[1] = blockId;
		List<UserBlock> userBlocks = this.getHibernateTemplate().find("select userBlock from UserBlock userBlock where userBlock.userId="+userId+" and userBlock.blockId="+blockId+" order by createDate desc");
		//List<UserBlock> userBlocks = this.getHibernateTemplate().find("select userBlock from UserBlock userBlock where userBlock.userId=? and userBlock.blockId=? order by createDate desc", params);
		UserBlock userBlock = null;
		if(userBlocks != null && userBlocks.size() > 0)
			userBlock = userBlocks.get(0);
		return userBlock;
	}	
	
	public Message getMessageById(long id){
		Message message = (Message)this.getHibernateTemplate().get(Message.class,id);
		if(message.getStatus() == 0){
			message.setStatus(1);
			this.save(message);
		}
		return message;
	}
	
	public List<Message> getMessagesBySender(long userId){
		List<Message> messages = this.getHibernateTemplate().find("select message from Message message where message.direction = 1 and message.postById=? order by createDate desc", userId);
		return messages;
	}
	
	public List<Message> getMessagesByUser(long userId){
		List<Message> messages = this.getHibernateTemplate().find("select message from Message message where message.direction = 0 and message.userId=? order by createDate desc", userId);
		return messages;
	}
	
	public List<Message> getConnectionRequestMessagesByUser(long userId){
		List<Message> messages = this.getHibernateTemplate().find("select message from Message message where message.event="+_MESSAGE_EVENT_CONNECTION+" and message.userId=? order by createDate desc", userId);
		return messages;
	}
	
	public int getNewMessageCountByUser(long userId){
		String hql="select count(*) from Message where userId="+userId+" and status=0 and direction = 0 ";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public int getNewConnectionRequestCountByUser(long userId){
		String hql="select count(*) from Message where userId="+userId+" and status=0 and event="+_MESSAGE_EVENT_CONNECTION;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<UserExtRole> getUserExtRole(long userId){
		List<UserExtRole> listRole = null;
		listRole= this.getHibernateTemplate().find("select userRole from UserExtRoleEntity userRole where userRole.userId= ?", userId);
		return listRole;
	}
		
	public AddressBook getAddressBookById(long id){
		return (AddressBook)this.getHibernateTemplate().get(AddressBook.class,id);
	}
	public List<AddressBook> getAddressBooksByUser(long userId){
		List<AddressBook> addressBooks = this.getHibernateTemplate().find("select addressBook from AddressBook addressBook where addressBook.userId= ? order by createDate asc", userId);
		return addressBooks;
	}
	public List<AddressBook> getAddressBooksByUser(long userId, String group){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = group;
		List<AddressBook> addressBooks = this.getHibernateTemplate().find("select addressBook from AddressBook addressBook where addressBook.userId= ? and addressBook.addressGroup= ? order by createDate asc", params);
		return addressBooks;
	}
	public List<String> getAddressBookGroupByUser(long userId){
		List<String> groups  = this.getHibernateTemplate().find("select distinct addressBook.addressGroup from AddressBook addressBook where addressBook.userId= ?", userId);
		return groups;
	}
	public Note getNoteByUser(long userId){
		Note note = (Note)this.getHibernateTemplate().get(Note.class,userId);
		if(note == null){
			User user =  this.getUserById(userId);
			Locale locale = Locale.ENGLISH;
			if(user != null){
				String[] localeParams = user.getLanguage().split("_");
				if(localeParams.length > 1)
					locale = new Locale(localeParams[0],localeParams[1]);
				else
					locale = new Locale(localeParams[0]);
			}
			String content = MessageUtil.getMessage("noteTemplate",locale);
			int width = 45;
			int height = 7;
			try{
				width =Integer.parseInt(MessageUtil.getMessage("noteWidth",locale));
				height =Integer.parseInt(MessageUtil.getMessage("noteHeight",locale));
			}catch(Exception e){}
			note = new Note(userId,content,width,height);
			this.save(note);
		}
		return note;
	}
	
	public List<Feedback> getFeedback(long orgId){
		List<Feedback> list= this.getHibernateTemplate().find("select feedback from Feedback feedback where feedback.orgId=? order by createDate desc",orgId);		
		return list;
	}
	
	public Feedback getFeedbackById(long id){
		return (Feedback)this.getHibernateTemplate().get(Feedback.class, id);
	}
	
	public Bookmark getBookmarkById(long id){
		return (Bookmark)this.getHibernateTemplate().get(Bookmark.class, id);
	}
	
	public List<Bookmark> getBookmarksByUser(long userId){
		List<Bookmark> list = this.getHibernateTemplate().find("select bookmark from Bookmark bookmark where bookmark.userId=? order by createDate asc",userId);  
		return list;
	}
	
	public List<Bookmark> getBookmarksByTag(long userId, String tag){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = tag;
		List<Bookmark> list = this.getHibernateTemplate().find("select bookmark from Bookmark bookmark where bookmark.userId=? and bookmark.tagId=? order by createDate asc",params);  
		return list;
	}
	public int getUserToDoCount(long userId){			
		String hql="select count(*) from ToDoBean where userId="+userId+" and status=0";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<ToDoBean> getToDosByUser(long userId){
		List<ToDoBean> list = this.getHibernateTemplate().find("select todo from ToDoBean todo where todo.userId=? order by status asc, priority asc, createDate desc",userId);  
		return list;
	}
	
	public ToDoBean getToDoById(long id){
	  return (ToDoBean)this.getHibernateTemplate().get(ToDoBean.class, id);
	}
	
	public Horoscope getUserHoroscope(int month, int day){
		Integer[] params = new Integer[4];
		params[0] = month;
		params[1] = day;
		params[2] = month;
		params[3] = day;
		List<Horoscope> list = this.getHibernateTemplate().find("select horoscope from Horoscope horoscope where (horoscope.endMonth = ? and horoscope.endDay >= ?) or (horoscope.startMonth = ? and horoscope.startDay <= ?) order by createDate asc",params);  		
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}
	
	public String getUserHoroscopeWeeklyInfo(long horoscopeId, String language){
		String lan = "en";//language
		Calendar current = Calendar.getInstance();
		current.setTimeInMillis(System.currentTimeMillis());
		current.setFirstDayOfWeek(Calendar.MONDAY);
		current.setMinimalDaysInFirstWeek(4);
		int weekNumber =current.get(Calendar.WEEK_OF_YEAR);
		if(weekNumber <1 || weekNumber > 52) weekNumber = 1;
		String hql="select description from HoroscopeWeekly where horoscopeId="+horoscopeId+" and language='"+lan+"' and weekNumber="+weekNumber;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		String desc = (String)query.uniqueResult();
		session.close();
		return desc;
	}
	
	public List<Horoscope> getHoroscopes(){
		List<Horoscope> list = this.getHibernateTemplate().find("select horoscope from Horoscope horoscope order by createDate asc");  
		return list;
	}

	public int getMyActivitiesCount(long userId){
		String hql="select count(*) from SocialActivity activity where activity.userId ="+userId+" order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Long count = (Long)session.createQuery(hql).uniqueResult();
		session.close();
		return count.intValue();
	}
	public int getMyConnectionsActivitiesCount(long userId){
		List<Connection> connections =this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by createDate desc", userId);	
		String hql = null;
		if(connections != null && connections.size() > 0){
			String userIds="";
			for(Connection c : connections){
				userIds+=c.getBuddyUserId()+",";
			}
			hql="select count(*) from SocialActivity activity where activity.userId in ("+userIds.substring(0,userIds.length() - 1)+") order by createDate desc"; 
		}
		if(hql != null){
			Session session= this.getHibernateTemplate().getSessionFactory().openSession();
			Long count = (Long)session.createQuery(hql).uniqueResult();
			session.close();
			return count.intValue();
		}else
			return 0;
	}
	public int getMyGroupsActivitiesCount(long userId){
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? order by createDate desc", userId);	
		String hql = null;
		if(userGroups != null && userGroups.size() > 0){
			String orgIds="";
			for(UserGroup g : userGroups){
				orgIds+=g.getId()+",";
			}
			hql="select count(*) from SocialActivity activity where activity.orgId in ("+orgIds.substring(0,orgIds.length() - 1)+") order by createDate desc"; 
		}
		if(hql != null){
			Session session= this.getHibernateTemplate().getSessionFactory().openSession();
			Long count = (Long)session.createQuery(hql).uniqueResult();
			session.close();
			return count.intValue();
		}else
			return 0;
	}
	public int getSocialActivitiesCount(long userId){
		List<Connection> connections =this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by createDate desc", userId);	
		String hql;
		String userIds=String.valueOf(userId);
		if(connections != null && connections.size() > 0){			
			for(Connection c : connections){
				userIds+=","+c.getBuddyUserId();
			}
		}
		String orgIds="";
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? order by createDate desc", userId);			
		if(userGroups != null && userGroups.size() > 0){			
			for(UserGroup g : userGroups){
				orgIds+=g.getId()+",";
			}
			hql="select count(*) from SocialActivity activity where activity.userId in ("+userIds+") or activity.orgId in ("+orgIds.substring(0,orgIds.length() - 1)+") order by createDate desc"; 
		}else		
			hql="select count(*) from SocialActivity activity where activity.userId in ("+userIds+") order by createDate desc"; 
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Long count = (Long)session.createQuery(hql).uniqueResult();
		session.close();
		return count.intValue();
	}
	public int getSocialActivitiesCountByOrg(long orgId){
		String hql="select count(*) from SocialActivity activity where activity.orgId ="+orgId+" order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Long count = (Long)session.createQuery(hql).uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<SocialActivity> getMyActivities(long userId, int start, int total){
		String hql="select activity from SocialActivity activity where activity.userId ="+userId+" order by createDate desc";
	    Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(total);
	   List<SocialActivity> list = query.list();
	   session.close();
       return list;
	}
	
	public List<SocialActivity> getMyConnectionsActivities(long userId, int start, int total){
		List<Connection> connections =this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by createDate desc", userId);	
		String hql = null;
		if(connections != null && connections.size() > 0){
			String userIds="";
			for(Connection c : connections){
				userIds+=c.getBuddyUserId()+",";
			}
			hql="select activity from SocialActivity activity where activity.userId in ("+userIds.substring(0,userIds.length() - 1)+") order by createDate desc"; 
		}
		List<SocialActivity> list = null;
		if(hql != null){
		    Session session= this.getHibernateTemplate().getSessionFactory().openSession();
			Query query =session.createQuery(hql)
						.setFirstResult(start)
						.setMaxResults(total);
		   list = query.list();
		   session.close();
		}
       return list;
	}
	public List<SocialActivity> getMyGroupsActivities(long userId, int start, int total){
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? order by createDate desc", userId);	
		String hql = null;
		if(userGroups != null && userGroups.size() > 0){
			String orgIds="";
			for(UserGroup g : userGroups){
				orgIds+=g.getId()+",";
			}
			hql="select activity from SocialActivity activity where activity.orgId in ("+orgIds.substring(0,orgIds.length() - 1)+") order by createDate desc"; 
		}
		List<SocialActivity> list = null;
		if(hql != null){
		    Session session= this.getHibernateTemplate().getSessionFactory().openSession();
			Query query =session.createQuery(hql)
						.setFirstResult(start)
						.setMaxResults(total);
		   list = query.list();
		   session.close();
		}
       return list;
	}
	public List<SocialActivity> getSocialActivities(long userId, int start, int total){
		List<Connection> connections =this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by createDate desc", userId);	
		String hql;
		String userIds=String.valueOf(userId);
		if(connections != null && connections.size() > 0){			
			for(Connection c : connections){
				userIds+=","+c.getBuddyUserId();
			}
		}
		String orgIds="";
		List<UserGroup> userGroups = this.getHibernateTemplate().find("select userGroup from UserGroup userGroup where userGroup.userId =? order by createDate desc", userId);			
		if(userGroups != null && userGroups.size() > 0){			
			for(UserGroup g : userGroups){
				orgIds+=g.getId()+",";
			}
			hql="select activity from SocialActivity activity where activity.userId in ("+userIds+") or activity.orgId in ("+orgIds.substring(0,orgIds.length() - 1)+") order by createDate desc"; 
		}else		
			hql="select activity from SocialActivity activity where activity.userId in ("+userIds+") order by createDate desc"; 
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(total);
	   List<SocialActivity> list = query.list();
	   session.close();
       return list;
	}
	
	public List<SocialActivity> getSocialActivitiesByOrg(long orgId, int start, int total){		
		String hql="select activity from SocialActivity activity where activity.orgId ="+orgId+" order by createDate desc"; 
	    Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(total);
	   List<SocialActivity> list = query.list();
	   session.close();
       return list;
	}

	public void deleteUser(User user){
		long userId = user.getId();
		 List<UserBlock> blocks = this.getUserBlocks(userId);
		 if(blocks != null){
			 for(UserBlock block : blocks){
				 this.delete(block);
			 }
		 }
		 List<UserComment> comments = this.getUserComments(userId);
		 if(comments != null){
			 for(UserComment comment : comments){
				 this.delete(comment);
			 }
		 }
		 List<UserMusic> musics = this.getUserMusics(userId);
		 if(musics != null){
			 for(UserMusic music : musics){
				 this.delete(music);
			 }
		 }
		 List<UserPicture> pictures = this.getUserPictures(userId,user.getOrgId());
		 if(pictures != null){
			 for(UserPicture picture : pictures){
				 this.delete(picture);
			 }
		 }
		 List<UserFile> files = this.getUserFiles(userId);
		 if(files != null){
			 for(UserFile file : files){
				 this.delete(file);
			 }
		 }
		 List<UserFavourite> favourites = this.getUserFavourites(userId);
		 if(favourites != null){
			 for(UserFavourite favourite : favourites){
				 this.delete(favourite);
			 }
		 }
		 List<AddressBook> addressBooks = getAddressBooksByUser(userId);
		 if(addressBooks != null){
			 for(AddressBook addressBook : addressBooks){
				 this.delete(addressBook);
			 }
		 }
		 List<Bookmark> bookmarks = getBookmarksByUser(userId);
		 if(bookmarks != null){
			 for(Bookmark bookmark : bookmarks){
				 this.delete(bookmark);
			 }
		 }
		 List<ToDoBean> todos = getToDosByUser(userId);
		 if(todos != null){
			 for(ToDoBean todo : todos){
				 this.delete(todo);
			 }
		 }
		 Note note = getNoteByUser(userId);
		 if(note != null)
			 this.delete(note);
		 UserProfile profile = this.getUserProfileById(userId);
		 if(profile != null)
			 this.delete(profile);
		 this.delete(user); 
	}
	public List<ObjectRole> getAllRoles(long orgId){
		return this.getHibernateTemplate().find("from ObjectRole where name like 'role_%' and orgId=?", orgId);		
	}
	
	public List<UserObjectRole> getUserRoles(long userId,long orgId){
		return this.getUserPermissionDao().getRolesByUserId(orgId,userId,orgId,_OBJECT_TYPE_ORG);		
	}
		
	public List<Keyword> getKeywords(int type, long orgId){
		String hql="select k from Keyword k where k.orgId= :orgId and k.type= :type order by weight desc, modifiedDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
						.setLong("orgId",orgId)
						.setInteger("type",type);
		List<Keyword> list = query.list();
		session.close();
		return list;		
	}
	
	public List<String> getKeywordsString(int type, long orgId){
		String hql="select k.keyword from Keyword k where k.orgId= :orgId and k.type= :type order by weight desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
						.setLong("orgId",orgId)
						.setInteger("type",type);
		List<String> list = query.list();
		session.close();
		return list;		
	}
	public List<String> getTopKeywords(int type, long orgId, int max){
		String hql="select k.keyword from Keyword k where k.orgId= :orgId and k.type= :type order by weight desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
						.setLong("orgId",orgId)
						.setInteger("type",type)
						.setFirstResult(0)
						.setMaxResults(max);
		List<String> list = query.list();
		session.close();
		return list;		
	}
	
	public Keyword getKeyword(String word, int type, long orgId){
		List<Keyword> keywords = this.getHibernateTemplate().find("select k from Keyword k where k.orgId= ? and k.type=? and k.keyword=?", new Object[] {orgId,type,word.trim()});		
		Keyword keyword = null;
		if(keywords != null && keywords.size() > 0) keyword = keywords.get(0);
		return keyword;
	}
	public List<String> getKeywords(int type, String input,long orgId){
		String hql="select k.keyword from Keyword k where k.orgId= :orgId and k.type=:type and k.keyword like :input order by k.weight desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setLong("orgId",orgId)
					.setInteger("type",type)
					.setString("input",input+"%")
					.setFirstResult(0)
					.setMaxResults(10);
		List<String> list = query.list();
		session.close();
		Collections.sort(list);	
		return list;
	}
	public List<UserKeyword> getUserKeywords(long personId){
		List<UserKeyword> list= this.getHibernateTemplate().find("select userKeyword from UserKeyword userKeyword where userKeyword.personId= ? and userKeyword.weight >= 100 order by userKeyword.weight asc", personId);		
		return list;
	}
	public List<String> getUserInputKeywords(long personId,int type){
		String hql = "select distinct uk.keyword from UserKeyword uk where uk.personId= "+personId+" and uk.type="+type+" and uk.weight >= 100 and uk.status > 0 order by createDate desc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(0)
					.setMaxResults(10);
		return query.list();
	}
	public void clearUserInputKeywords(long personId,int type){
		String hql="update UserKeyword set status= -1 where personId="+personId + " and type="+type;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
			   .executeUpdate();
		session.close();
	}
	public UserKeyword getUserKeyword(long keywordId,long userId){
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = keywordId;
		List<UserKeyword> list= this.getHibernateTemplate().find("select userKeyword from UserKeyword userKeyword where userKeyword.personId= ? and userKeyword.keywordId = ?", params);		
		UserKeyword userKeyword = null;
		if(list != null && list.size() > 0) userKeyword = list.get(0);
		return userKeyword;
	}
	public UserKeyword getUserKeywordById(long id){
		UserKeyword entity = (UserKeyword)this.getHibernateTemplate().get(UserKeyword.class,id);		
		return  entity;
	}
	
	public List<Long> getPersonIdsByKeyword(long orgId){
		List<Long> personIds = this.getHibernateTemplate().find("select distinct k.personId from UserKeyword k where k.orgId = ?", orgId);
		return personIds;
	}

	public List<NotKeyword> getNotKeywords(){
		List<NotKeyword> list= this.getHibernateTemplate().find("select notWord from NotKeyword notWord order by notWord.word");
		return list;
	}
	
	public NotKeyword getNotKeyword(String word){
		NotKeyword notKeyword = null;
		List<NotKeyword> list= this.getHibernateTemplate().find("select notWord from NotKeyword notWord where notWord.word=?",word);
		if(list != null && list.size() > 0) notKeyword = list.get(0);
		return notKeyword;
	}
	
	public List<NotWord> getNotWords(){
		List<NotWord> list= this.getHibernateTemplate().find("select notWord from NotWord notWord order by notWord.word");
		return list;
	}
	
	public NotWord getNotWord(String word){
		List<NotWord> list= this.getHibernateTemplate().find("select notWord from NotWord notWord where notWord.word=?",word);
		NotWord notKeyword = null;
		if(list != null && list.size() > 0) notKeyword = list.get(0);
		return notKeyword;
	}
	
	public List<Timestamp> getUserKeywordDates(long keywordId,int type){		
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		date.add(Calendar.DATE,-100);
		List<Timestamp> list = this.getHibernateTemplate().find("select modifiedDate from UserKeyword where status >=0 and keywordId=? and type=? and modifiedDate > ? order by modifiedDate desc",new Object[]{keywordId,type,date.getTime()});  
		return list;
	}
	
	public Subscriber getSubscriber(String email, int type,long orgId){
		List<Subscriber> list= this.getHibernateTemplate().find("from Subscriber where email=? and type=? and orgId=?",new Object[]{email,type,orgId});
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public List<Subscriber> getSubscribers(int type,long orgId){
		List<Subscriber> list= this.getHibernateTemplate().find("from Subscriber where type=? and orgId=?",new Object[]{type,orgId});
		return list;
	}
}
