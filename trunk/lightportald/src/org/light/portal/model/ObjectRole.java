package org.light.portal.model;

public class ObjectRole extends Entity {

	private String name;
	private String description;
	private long orgId;
	private long permission;
	
	public ObjectRole(){
		super();
	}
	public ObjectRole(String name, String description, long orgId,long permission){
		this();
		this.name = name;
		this.description = description;
		this.orgId = orgId;
		this.permission = permission;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getPermission() {
		return permission;
	}
	public void setPermission(long permission) {
		this.permission = permission;
	}

}
