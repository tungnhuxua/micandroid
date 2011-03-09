package org.light.portal.model;

public class RolePermission extends Entity {
	private long id;
	private long roleId;
	private long permissionId;
	
	public RolePermission(){
		super();
	}
	public RolePermission(long roleId,long permissionId){
		this();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
