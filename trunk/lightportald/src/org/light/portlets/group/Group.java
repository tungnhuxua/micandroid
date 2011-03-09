 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portlets.group;

import static org.light.portal.util.Constants._OBJECT_TYPE_GROUP;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.Entity;
import org.light.portal.model.URLable;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Group extends Entity implements URLable {
	//id is same as org id
	private String displayName;
	private long categoryId; //1 Other
	private Integer openJoin; // 0 no 1 yes
	private Integer hiddenGroup; // 0 no 1 yes
	private Integer memberInvite; //  member can invite 0 no 1 yes
	private Integer publicForum; // 0 no 1 yes
	private Integer memberBulletin; // member can post Bulletin 0 no 1 yes
	private Integer memberImage; // member can post Image 0 no 1 yes
	private Integer matureContent; // 0 no 1 yes
	private int noPicForward;
	private String country;
	private String province;
	private String city;
	private String postalCode;
	private String shortDesc;
	private String desc;
	private String uri;
	private long ownerId;
	private long leaderId;
	private long parentId;	
	private String caption;
	private String songUrl;
	private int viewCount;
	
	private String photoUrl;
	private int photoWidth;
	private int photoHeight;
	
	
	//read only
	private String category;
	private int members;
	
	public Group(){
		super();
	}
	
	public Group(String displayName, String uri, String photoUrl, int photoWidth, int photoHeight){
		this.displayName = displayName;
		this.uri = uri;
		this.photoUrl = photoUrl;
		this.photoWidth = photoWidth;
		this.photoHeight = photoHeight;
	}
	
	public Group(String displayName,long categoryId,int openJoin,int hiddenGroup,int memberInvite,int publicForum,int memberBulletin,int memberImage,int noPicForward,int matureContent,String country,String province,String city,String postalCode,String shortDesc,String desc,String uri,long ownerId,long topOrgId){
		this.displayName = displayName;
		this.categoryId = categoryId;
		this.openJoin = openJoin;
		this.hiddenGroup =hiddenGroup;
		this.memberInvite = memberInvite;
		this.publicForum = publicForum;		
		this.memberBulletin = memberBulletin;
		this.memberImage = memberImage;
		this.noPicForward = noPicForward;
		this.matureContent= matureContent;
		this.country = country;
		this.province = province;
		this.city = city;
		this.postalCode = postalCode;
		this.shortDesc = shortDesc;
		this.desc = desc;
		this.uri = uri;
		this.ownerId =ownerId;
		this.leaderId = ownerId;	
		this.setOrgId(topOrgId);
	}
	
	public Group(String displayName,long categoryId,int openJoin,int hiddenGroup,int memberInvite,int publicForum,int memberBulletin,int memberImage,int noPicForward,int matureContent,String country,String province,String city,String postalCode,String shortDesc,String desc,String uri,long ownerId,long orgId, long topOrgId){
		this(displayName,categoryId,openJoin,hiddenGroup,memberInvite,publicForum,memberBulletin,memberImage,noPicForward,matureContent,country,province,city,postalCode,shortDesc,desc,uri,ownerId,topOrgId);
		this.setId(orgId);
	}

	public String toString() {
		return super.toString()+getDisplayName();
	}

	public String getCategoryName(){
		String category = PortalContextFactory.getPortalContext().getMessageByKey(this.category);
		return category;
	}
	
	public int getType(){
		return _OBJECT_TYPE_GROUP;
    }
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getPhotoThumbUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
		
	public int getPhotoSmallWidth(){
		return ImageUtil.getPhotoSmallWidth(this.photoWidth,this.photoHeight);
	}
	public int getPhotoSmallHeight(){
		return ImageUtil.getPhotoSmallHeight(this.photoWidth,this.photoHeight);
	}
	
	public long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
		
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public Integer getHiddenGroup() {
		return hiddenGroup;
	}

	public void setHiddenGroup(Integer hiddenGroup) {
		this.hiddenGroup = hiddenGroup;
	}
	
	public Integer getMatureContent() {
		return matureContent;
	}

	public void setMatureContent(Integer matureContent) {
		this.matureContent = matureContent;
	}

	public Integer getMemberBulletin() {
		return memberBulletin;
	}
	
	public void setMemberBulletin(Integer memberBulletin) {
		this.memberBulletin = memberBulletin;
	}
	
	public Integer getMemberImage() {
		return memberImage;
	}
	
	public void setMemberImage(Integer memberImage) {
		this.memberImage = memberImage;
	}
	
	public Integer getMemberInvite() {
		return memberInvite;
	}
	
	public void setMemberInvite(Integer memberInvite) {
		this.memberInvite = memberInvite;
	}
	
	public Integer getOpenJoin() {
		return openJoin;
	}
	
	public void setOpenJoin(Integer openJoin) {
		this.openJoin = openJoin;
	}

	public long getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getPublicForum() {
		return publicForum;
	}

	public void setPublicForum(Integer publicForum) {
		this.publicForum = publicForum;
	}
	
	public String getShortDesc() {
		return shortDesc;
	}
	
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getViewCount() {
		return viewCount;
	}
	
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public int getPhotoHeight() {
		return photoHeight;
	}
	
	public void setPhotoHeight(int photoHeight) {
		this.photoHeight = photoHeight;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getPhotoWidth() {
		return photoWidth;
	}
	
	public void setPhotoWidth(int photoWidth) {
		this.photoWidth = photoWidth;
	}

	public String getSongUrl() {
		return songUrl;
	}

	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public int getMembers() {
		return members;
	}
	
	public void setMembers(int members) {
		this.members = members;
	}

	public long getLeaderId() {
		return leaderId;
	}
	
	public void setLeaderId(long leaderId) {
		this.leaderId = leaderId;
	}

	public int getNoPicForward() {
		return noPicForward;
	}

	public void setNoPicForward(int noPicForward) {
		this.noPicForward = noPicForward;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
}
