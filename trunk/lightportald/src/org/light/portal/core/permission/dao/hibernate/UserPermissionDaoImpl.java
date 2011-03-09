package org.light.portal.core.permission.dao.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.core.permission.dao.UserPermissionDao;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.Permission;
import org.light.portal.model.RolePermission;
import org.light.portal.model.UserObjectPermission;
import org.light.portal.model.UserObjectRole;

public class UserPermissionDaoImpl extends BaseDaoImpl implements UserPermissionDao {

	public long getAllPermission(long orgId, long userId, long objectId, long objectTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select rp.permissionId from light_user_obj_role uor, light_role_permission rp where uor.userId = ");
		hql.append(userId);
		hql.append(" and uor.orgId = ");
		hql.append(orgId);
		hql.append(" and uor.objectId = ");
		hql.append(objectId);
		hql.append(" and uor.objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and uor.roleId = rp.roleId");
		hql.append(" union select up.permissionId from light_user_obj_permission up where up.userId = ");
		hql.append(userId);
		hql.append(" and up.orgId = ");
		hql.append(orgId);
		hql.append(" and up.objectId = ");
		hql.append(objectId);
		hql.append(" and up.objectTypeId = ");
		hql.append(objectTypeId);
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString());
		List<BigInteger> perms = query.list();
		session.close();
		long permission = 0L;
		for(BigInteger p : perms){
			long value = p.longValue();
			if(value > 0){
				permission = (permission > 0) ? permission | value : value;
			}
		}
		return permission;
	}
	public List<Permission> getAllPermissions(long orgId, long userId, long objectId, long objectTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select {p.*} from light_permission {p}, light_user_obj_role uor, light_role_permission rp where uor.userId = ");
		hql.append(userId);
		hql.append(" and uor.orgId = ");
		hql.append(orgId);
		hql.append(" and uor.objectId = ");
		hql.append(objectId);
		hql.append(" and uor.objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and uor.roleId = rp.roleId and p.id = rp.permissionId");
		hql.append(" union select p.* from light_permission p, light_user_obj_permission up where up.userId = ");
		hql.append(userId);
		hql.append(" and up.orgId = ");
		hql.append(orgId);
		hql.append(" and up.objectId = ");
		hql.append(objectId);
		hql.append(" and up.objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and up.permissionId = p.id");
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString()).addEntity("p",Permission.class);
		List<Permission> perms = query.list();
		session.close();
		return perms;
	}

	public List<Permission> getRolePermissions(long orgId, long roleId, long userId, long objectId, long objectTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select {p.*} from light_permission {p}, light_user_obj_role uor, light_role_permission rp where uor.userId = ");
		hql.append(userId);
		hql.append(" and uor.orgId = ");
		hql.append(orgId);
		hql.append(" and uor.objectId = ");
		hql.append(objectId);
		hql.append(" and uor.objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and roleId = ");
		hql.append(roleId);
		hql.append(" and uor.roleId = rp.roleId and p.id = rp.permissionId");
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString()).addEntity("p",Permission.class);
		List<Permission> perms = query.list();
		session.close();
		return perms;
	}
	
	public void addUserObjectPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId) {
		//TODO: check user has permission to add permission
		
		UserObjectPermission up = new UserObjectPermission();
		//up.setObjectTypeId(Constants._OBJECT_TYPE_ORG);
		up.setObjectTypeId(objectTypeId);
		up.setOrgId(orgId);
		up.setObjectId(objectId);
		up.setUserId(userId);
		up.setPermissionId(permissionId);
		
		this.save(up);
	}
	
	public void removeUserObjectPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId) {
		StringBuffer hql = new StringBuffer("select * from light_user_obj_permission where objectId = ");
		hql.append(objectId);
		hql.append(" and objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and permissionId = ");
		hql.append(permissionId);
		hql.append(" and orgId = ");
		hql.append(orgId);
		hql.append(" and userId = ");
		hql.append(userId);
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString());
		UserObjectPermission up = (UserObjectPermission)query.uniqueResult();
		session.close();
		
		this.delete(up);
	}

	public boolean hasPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId) {
		StringBuffer hql = new StringBuffer("select count(*) from light_user_obj_permission where objectId = ");
		hql.append(objectId);
		hql.append(" and objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and permissionId = ");
		hql.append(permissionId);
		hql.append(" and orgId = ");
		hql.append(orgId);
		hql.append(" and userId = ");
		hql.append(userId);
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		if (count > 0) {
			return true;
		}
		
		hql = null;
		hql = new StringBuffer("select count(*) from light_user_obj_role uor, light_role_permission rp where uor.userId = ");
		hql.append(userId);
		hql.append(" and uor.orgId = ");
		hql.append(orgId);
		hql.append(" and uor.objectId = ");
		hql.append(objectId);
		hql.append(" and uor.objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and uor.roleId = rp.roleId and rp.permissionId = ");
		hql.append(permissionId);
		
		
		query =session.createSQLQuery(hql.toString());
		count = (Long)query.uniqueResult();
		session.close();
		
		if (count > 0) {
			return true;
		}

		return false;
	}
	
	public Permission getPermissionById(long id){
		return (Permission)this.getHibernateTemplate().get(Permission.class,id);
	}
	
	public List<RolePermission> getRolePermissionById(long roleId,long permissionId){
		Object[] params = new Object[2];
		params[0] = roleId;
		params[1] = permissionId;		
		List<RolePermission> list =this.getHibernateTemplate().find("from RolePermission  rp where rp.roleId=? and rp.permissionId=?", params);		
		
		return list;
	}
	public void addUserObjectRole(long orgId, long userId, long objectId, long objectTypeId, long roleId) {
		UserObjectRole uor = new UserObjectRole();
		uor.setObjectId(objectId);
		uor.setObjectTypeId(objectTypeId);
		uor.setRoleId(roleId);
		uor.setOrgId(orgId);
		uor.setUserId(userId);
		
		this.save(uor);
	}
	
	public void removeUserObjectRole(long orgId, long userId, long objectId, long objectTypeId, long roleId) {
		StringBuffer hql = new StringBuffer("select * from light_user_obj_role where userId = ");
		hql.append(userId);
		hql.append(" and orgId = ");
		hql.append(orgId);
		hql.append(" and objectId = ");
		hql.append(objectId);
		hql.append(" and objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and roleId = ");
		hql.append(roleId);
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery(hql.toString());
		UserObjectRole uor = (UserObjectRole)query.uniqueResult();
		session.close();
		
		this.delete(uor);
	}

	public List<UserObjectRole> getRolesByUserId(long orgId, long userId, long objectId, long objectTypeId) {
		StringBuffer hql = new StringBuffer("from UserObjectRole  where userId = ");
		hql.append(userId);
		hql.append(" and orgId = ");
		hql.append(orgId);
		hql.append(" and objectId = ");
		hql.append(objectId);
		hql.append(" and objectTypeId = ");
		hql.append(objectTypeId);
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql.toString());
		List<UserObjectRole> perms = query.list();
		session.close();
		
		return perms;
	}

	public boolean hasRole(long orgId, long userId, long roleId, long objectId, long objectTypeId) {
		StringBuffer hql = new StringBuffer("select count(*) from UserObjectRole where userId = ");
		hql.append(userId);
		hql.append(" and orgId = ");
		hql.append(orgId);
		hql.append(" and objectId = ");
		hql.append(objectId);
		hql.append(" and objectTypeId = ");
		hql.append(objectTypeId);
		hql.append(" and roleId = ");
		hql.append(roleId);
		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		if (count > 0) {
			return true;
		}
		
		return false;
	}
	
	public ObjectRole getRoleByName(String name, long orgId) {
		List<ObjectRole> list = this.getHibernateTemplate().find("from ObjectRole  where name = ? and orgId = ?", new Object[]{name,orgId});			
		ObjectRole role = null;
		if(list != null && list.size() > 0)
			role = list.get(0);
		return role;
	}


}
