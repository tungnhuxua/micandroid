package org.light.portal.model;

import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

public class PicturePositionTagItem extends Entity{
	
	private long tagId;
	private long pictureId;
	private long userId;
	private long orgId;	
	private long topOrgId;
	private String name;
	private String description;
	private String owner;
	
	//	read only
	private String photoUrl;
	private String uri;
	private String displayName;
	
	public PicturePositionTagItem(){
		super();
	}
	
	public PicturePositionTagItem(long tagId, long pictureId, String name, String description, String owner, long userId,long orgId, long topOrgId){
		this();
		this.tagId = tagId;
		this.pictureId = pictureId;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.userId = userId;
		this.orgId = orgId;		
	}
		
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm");
	}
	
	public String getUri() {		
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
		
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTopOrgId() {
		return topOrgId;
	}

	public void setTopOrgId(long topOrgId) {
		this.topOrgId = topOrgId;
	}

	public long getPictureId() {
		return pictureId;
	}

	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
