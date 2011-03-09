package org.light.portal.core.permission.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.core.permission.dao.PermissionDao;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.Permission;
import org.light.portal.model.RolePermission;

public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {

	public List<Permission> getPermissions(long orgId) {
		List<Permission> permissions = this.getHibernateTemplate().find("select p from Permission p where orgId=? or orgId=0",orgId);		
		return permissions;
	}

	public List<RolePermission> getRolePermissions(long id){
		List<RolePermission> rolePermissions = this.getHibernateTemplate().find("from RolePermission where roleId=?",id);
		return rolePermissions;
	}
	
	public void deleteRolePermissions(long id){
		List<RolePermission> rolePermissions = this.getRolePermissions(id);
		for(RolePermission rp : rolePermissions){
			this.delete(rp);
		}
	}
	
	public void addPermission(long orgId, long userId, Permission permission) {
		// check user has priviage to add permission or not
		this.save(permission);
	}

	public void updatePermission(long orgId, long userId, Permission permission) {
		String hql = "select perm from light_permission perm where id = " + permission.getId();
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Permission perm = (Permission)query.uniqueResult();
		session.close();
		
		perm.setDescription(permission.getDescription());
		perm.setName(permission.getName());
		
		this.save(perm);
		
	}
	
	public void deletePermission(long orgId, long userId, Permission permission) {
		this.delete(permission);
	}

	public void addRole(long orgId, long userId, ObjectRole role, List<Permission> permissions) {
		this.save(role);
		List<RolePermission> rolePermissions = this.getRolePermissions(role.getId());		
		for(Permission p : permissions){
			boolean found = false;
			for(RolePermission rp : rolePermissions){
				if(rp.getPermissionId() == p.getId()){
					found = true;
					break;
				}
			}
			if(!found){
				RolePermission rp = new RolePermission(role.getId(),p.getId());
				this.save(rp);
			}
		}
	}

	public void updateRole(long orgId, long userId, ObjectRole role,
			List<Permission> permissions) {
		// TODO Auto-generated method stub

	}

	public void deleteRole(long orgId, long userId, ObjectRole role) {
		this.delete(role);
		this.deleteRolePermissions(role.getId());
	}
}
