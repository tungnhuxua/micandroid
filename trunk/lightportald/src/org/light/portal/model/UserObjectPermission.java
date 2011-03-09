package org.light.portal.model;

public class UserObjectPermission extends Entity {
	private long id;
	private long userId;
	private long orgId;
	private long objectId;
	private long objectTypeId;
	private long permissionId;
	
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
	public long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
}
