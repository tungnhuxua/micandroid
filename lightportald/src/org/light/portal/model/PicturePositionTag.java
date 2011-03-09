package org.light.portal.model;

import java.util.Set;

public class PicturePositionTag extends Entity{

	public final static int TAGGING_MEMBER 		= 100;
	public final static int TAGGING_GROUP 		= 110;
	public final static int TAGGING_BLOG		= 200;
	public final static int TAGGING_PRODUCT 	= 300;
	public final static int TAGGING_PRODUCTS 	= 400;
	
	private long pictureId;
	private String pictureUrl;
	private long parentId;
	private long userId;
	private long orgId;	
	private long topOrgId;
	private int positionX;
	private int positionY;
	private String tag;
	private int type; // 100 member tagging; 200 blog tagging; 300 product tagging; 400 products tagging;
	private long objectId; //if type is 200, object id will be  blog id;
	
	private Set<PicturePositionTagItem> items;
	
	public PicturePositionTag(){
		super();
	}
	
	public PicturePositionTag(String pictureUrl, int positionX, int positionY, String tag, int type, long userId,long orgId, long topOrgId){
		this();
		this.pictureUrl = pictureUrl;
		this.positionX = positionX;
		this.positionY = positionY;
		this.tag = tag;
		this.type = type;
		this.userId = userId;
		this.orgId = orgId;	
		this.topOrgId = topOrgId;
	}
	
	public PicturePositionTag(long pictureId, String pictureUrl,int positionX, int positionY, String tag, int type, long userId,long orgId, long topOrgId,long objectId){
		this(pictureUrl,positionX,positionY,tag,type,userId,orgId,topOrgId);		
		this.pictureId = pictureId;	
		this.objectId = objectId;
	}
	
	public String getDesc(){
		if(this.getItems() != null){
			for(PicturePositionTagItem item : this.getItems()){
				return item.getDescription();
			}
		}
		return null;
	}
	
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getPictureId() {
		return pictureId;
	}
	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public Set<PicturePositionTagItem> getItems() {
		return items;
	}

	public void setItems(Set<PicturePositionTagItem> items) {
		this.items = items;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public long getTopOrgId() {
		return topOrgId;
	}

	public void setTopOrgId(long topOrgId) {
		this.topOrgId = topOrgId;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

}
