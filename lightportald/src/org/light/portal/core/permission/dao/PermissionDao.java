package org.light.portal.core.permission.dao;

import java.util.List;

import org.light.portal.model.ObjectRole;
import org.light.portal.model.Permission;
import org.light.portal.model.RolePermission;

public interface PermissionDao {
	
	public List<Permission> getPermissions(long orgId);
	
	public List<RolePermission> getRolePermissions(long id);
	
	public void deleteRolePermissions(long id);
	
	public void addPermission(long orgId, long userId, Permission permission);
	
	public void updatePermission(long orgId, long userId, Permission permission);

	public void deletePermission(long orgId, long userId, Permission permission);

	public void addRole(long orgId, long userId, ObjectRole role,
			List<Permission> permissions);

	public void updateRole(long orgId, long userId, ObjectRole role,
			List<Permission> permissions);

	public void deleteRole(long orgId, long userId, ObjectRole role);
}
