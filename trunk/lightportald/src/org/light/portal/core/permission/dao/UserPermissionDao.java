package org.light.portal.core.permission.dao;

import java.util.List;

import org.light.portal.model.ObjectRole;
import org.light.portal.model.Permission;
import org.light.portal.model.RolePermission;
import org.light.portal.model.UserObjectRole;

public interface UserPermissionDao {
	
	public long getAllPermission(long orgId, long userId, long objectId, long objectTypeId);
	
	//public List<Permission> getAllPermissions(long orgId, long userId, long objectId, long objectTypeId);
	
	//public List<Permission> getRolePermissions(long orgId, long roleId, long userId, long objectId, long objectTypeId);
	
	//public void addUserObjectPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId);
	
	//public void removeUserObjectPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId);
	
	//public boolean hasPermission(long orgId, long userId, long objectId, long objectTypeId, int permissionId);	
	
	public void addUserObjectRole(long orgId, long userId, long objectId, long objectTypeId, long roleId);
	
	public void removeUserObjectRole(long orgId, long userId, long objectId, long objectTypeId, long roleId);
	
	public List<UserObjectRole> getRolesByUserId(long orgId, long userId, long objectId, long objectTypeId);
	
	public boolean hasRole(long orgId, long userId, long roleId, long objectId, long objectTypeId);
	
	public ObjectRole getRoleByName(String name, long orgId);
	
	public Permission getPermissionById(long id);
	
	public List<RolePermission> getRolePermissionById(long roleId,long permissionId);
}
