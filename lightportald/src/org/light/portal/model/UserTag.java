package org.light.portal.model;

public class UserTag extends Entity{
	private long userId;
	private long objectId;
	private int  objectType;
	private String tag;
	private long orgId;
	
	//read only
	private int score; //numbers of object tagged as this tag
	private long size;  //compare socre to Max socre
	
	public UserTag(){
		super();
	}
	public UserTag(String tag,long objectId,int objectType,long userId,long orgId){
		this();
		this.tag = tag;
		this.objectId = objectId;
		this.objectType = objectType;
		this.userId = userId;
		this.orgId = orgId;
	}
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
