package org.light.portlets.blog;

import org.light.portal.model.Entity;

public class BlogCategory extends Entity {

	private String name;
	private String desc;
	private long parentId;
	private long userId;
	private long orgId;
	private int status;
	
	public BlogCategory(){
		super();
	}
	public BlogCategory(String name,String desc, long userId, long orgId){
		this();
		this.name = name;
		this.desc = desc;
		this.userId = userId;
		this.orgId = orgId;
	}
	public BlogCategory(String name,String desc, long parentId, long userId, long orgId, int status){
		this(name,desc,userId,orgId);		
		this.parentId = parentId;
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
