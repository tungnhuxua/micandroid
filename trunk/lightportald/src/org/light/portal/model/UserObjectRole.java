package org.light.portal.model;

public class UserObjectRole extends Entity {
	private long id;
	private long userId;
	private long objectId;
	private long objectTypeId;
	private long roleId;
	
	//read only
	private String name;
	private long permission;
	
	public UserObjectRole(){
		super();
	}
	public UserObjectRole(long userId,long orgId,long objectId,long objectTypeId,long roleId){
		this();
		this.userId = userId;
		this.setOrgId(orgId);
		this.objectId = objectId;
		this.objectTypeId = objectTypeId;
		this.roleId = roleId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	public long getObjectTypeId() {
		return objectTypeId;
	}
	public void setObjectTypeId(long objectTypeId) {
		this.objectTypeId = objectTypeId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPermission() {
		return permission;
	}
	public void setPermission(long permission) {
		this.permission = permission;
	}
}
