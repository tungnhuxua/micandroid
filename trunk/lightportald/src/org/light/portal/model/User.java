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

package org.light.portal.model;

import static org.light.portal.util.Constants._DEFAULT_LANGUAGE;
import static org.light.portal.util.Constants._DEFAULT_LOCALE;
import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._ROLE_ADMIN;
import static org.light.portal.util.Constants._STATUS_ONLINE;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class User extends Entity implements URLable{

	private long orgId;
	private String userId;
	private String password;
	private String displayName;	
	private String email;
    private String birth;
    private String gender;
	private String language= PropUtil.getString(_DEFAULT_LANGUAGE);
	private String region= PropUtil.getString(_DEFAULT_LOCALE);
	private String timeZone;
	private String uri;
	private int uriType; // 0 system assigned 1 user chosed
	private String caption;
	private String photoUrl;
	private int photoWidth;
	private int photoHeight;	
	private String musicUrl;
	private String ringToneUrl;
	private String videoUrl;
	
	//TODO: move to UserAddress
	private String country;
	private String province;
	private String city;
	private String postalCode;
	
	//TODO: move to UserPrivacy
	private int showFriendPicture = 1;
	private int showGroupPicture= 1;
	private int notification = 1;
	private int newsLetter = 1;
	private int fqNel; //friend request need email or last name
	private int commentNeedApprove;
	private int showBirthToFriend;
	private int blogCommentFriendOnly;
	private int profileFriendViewOnly;
	private int imprivacy; //0 public can istant meessage me 1 friend only 2 no one can im me
	private int noPicForward;
	private int myMusicAutoPlay = 1;
	private int otherMusucAutoPlay = 1;
	private int defaultMusicStatus = 0;//0 me; 1 friends; 2 public
	private int defaultPictureStatus = 0;//0 me; 1 friends; 2 public
	private int defaultFileStatus = 0;//0 me; 1 friends; 2 public
	private int growKeyword = 1; // 0 when user read the news, system don't collect the keywords
	private int showTitleToFriends;
	
	private int visitCount;	
	private Timestamp lastLoginDate;
	private int disabled; //0 enable; 1 disabled;
	private int locked; //0 unlocked; 1 locked;
	private int currentStatus; //login status
	private int defaultStatus=_STATUS_ONLINE; //default login status
	private long personId;
	private long permission;
	
	//authorized roles
	private List<UserObjectRole> roles;
	//private List<Permission> permissions;
	
	public User(){
		super();
	}
    
	public User(String userId, String password, String displayName, String email, long orgId){
		this(userId,password,displayName,email,String.valueOf(System.currentTimeMillis()),orgId);
		this.uriType=0;
	}
	
	public User(String userId, String password, String displayName, String email, String uri, long orgId){
		this();
		this.userId = userId;
		this.password = password;
		this.displayName = displayName;		
		this.email = email;			
		this.orgId = orgId;
		this.uri = uri;
		this.uriType = 1;
		this.lastLoginDate = new Timestamp(System.currentTimeMillis());		
	}
	
	public User(String userId, String password, String displayName,
			String email,String birth, String gender, String language,String region, String  timeZone, String uri, String country, String province, String city, String postalCode, long orgId){
		this(userId,password,displayName,email,uri,orgId);
		this.birth = birth;
		this.gender = gender;
		this.language = language;
		this.region = region;
		this.timeZone = timeZone;
		this.country = country;
		this.province= province;
		this.city = city;
		this.postalCode = postalCode;			
	}
	
	public boolean hasRole(long roleId, long objectId, long objectTypeId){
		if(roles != null){
			for(UserObjectRole role : roles){
				if(role.getRoleId() == roleId 
				  && role.getObjectId() == objectId
				  && role.getObjectTypeId() == objectTypeId)
					return true;
			}
		}
		return false;
	}
	public boolean hasRole(String name){
		if(roles != null){
			for(UserObjectRole role : roles){
				if(role.getName().equalsIgnoreCase(name))
					return true;
			}
		}
		return false;
	}
	public boolean isAdmin(){
		return hasRole(_ROLE_ADMIN);
	}
	public long getAllPermission(){
		long rolePermission = 0L;
		for(UserObjectRole r : this.getRoles()){
			if(r.getPermission() > 0)
				rolePermission = (rolePermission > 0) ? rolePermission | r.getPermission() : r.getPermission();
		}
		return (this.permission > 0) ? (this.permission | rolePermission) : rolePermission;
		
	}
	public String toString() {
		return super.toString()+getName();
	}

	public int getType(){
	   return _OBJECT_TYPE_USER;
	}
   
	public String getName(){		
		return displayName;
	}
	public String getBirthday(){
		String birthday = "";	
		Calendar calendar = GregorianCalendar.getInstance();
		if(this.birth != null && this.birth.length() == 10){
			int  year = Integer.parseInt(getBirthY());
			int  month = Integer.parseInt(getBirthM());
			int  day = Integer.parseInt(getBirthD());
			calendar.set(year,month - 1,day);
			birthday = DateUtil.format(calendar.getTime(),"MMMM,dd");
		}
		return birthday;
	}
	public String getBirthY(){
		return (this.birth != null && this.birth.length() == 10) ? birth.substring(0,4) : null;
	}
	public String getBirthM(){
		return (this.birth != null && this.birth.length() == 10) ? birth.substring(5,7) : null;
	}
	public String getBirthD(){
		return (this.birth != null && this.birth.length() == 10) ? birth.substring(8,10) : null;
	}
	public String getAge(){
		String age = "";
		if(this.birth != null && this.birth.length() == 10){
			String  year = getBirthY();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			int currentYear = calendar.get( Calendar.YEAR );
			age = String.valueOf(currentYear - Integer.parseInt(year));
		}			
		return age;
	}
	
	public String getUri() {		
		return uri;
	}
	public boolean isHttpPhotoUrl(){
		return photoUrl.toLowerCase().startsWith("http");
	}
	public String getPhotoThumbUrl(){
		return ImageUtil.getPhotoThumbUrl(this.photoUrl);
	}
	
	public int getPhotoSmallWidth(){
		return ImageUtil.getPhotoSmallWidth(this.photoWidth,this.photoHeight);
	}
	public int getPhotoSmallHeight(){
		return ImageUtil.getPhotoSmallHeight(this.photoWidth,this.photoHeight);		
	}
	
	public String getGenderName(){
		if(this.gender != null){
			if("M".equals(this.gender) || "male".equals(this.gender))
				return PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.gender.male");
			else if("F".equals(this.gender) || "female".equals(this.gender))
				return PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.gender.female");
			else 
				return null;
		}else 
			return null;
	}
	
	public String getLastDate(){
		return DateUtil.format(this.lastLoginDate);
	}
	
	public boolean userDisabled(){
		return (this.disabled == 0) ? false : true;
	}
	
	public boolean userLocked(){
		return (this.locked == 0) ? false : true;
	}
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhotoHeight() {
		return photoHeight;
	}
	

	public void setPhotoHeight(int photoHeight) {
		this.photoHeight = photoHeight;
	}
	

	public int getPhotoWidth() {
		return photoWidth;
	}
	

	public void setPhotoWidth(int photoWidth) {
		this.photoWidth = photoWidth;
	}

	public String getDisplayName() {
		return displayName;
	}
	

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMusicUrl() {
		return musicUrl;
	}
	

	public void setMusicUrl(String songUrl) {
		this.musicUrl = songUrl;
	}

	public String getCaption() {
		return caption;
	}
	

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getRingToneUrl() {
		return ringToneUrl;
	}

	public void setRingToneUrl(String ringToneUrl) {
		this.ringToneUrl = ringToneUrl;
	}

	public int getShowFriendPicture() {
		return showFriendPicture;
	}

	public void setShowFriendPicture(int showFriendPicture) {
		this.showFriendPicture = showFriendPicture;
	}

	public int getShowGroupPicture() {
		return showGroupPicture;
	}

	public void setShowGroupPicture(int showGroupPicture) {
		this.showGroupPicture = showGroupPicture;
	}

	public int getBlogCommentFriendOnly() {
		return blogCommentFriendOnly;
	}

	public void setBlogCommentFriendOnly(int blogCommentFriendOnly) {
		this.blogCommentFriendOnly = blogCommentFriendOnly;
	}

	public int getCommentNeedApprove() {
		return commentNeedApprove;
	}

	public void setCommentNeedApprove(int commentNeedApprove) {
		this.commentNeedApprove = commentNeedApprove;
	}

	public int getFqNel() {
		return fqNel;
	}

	public void setFqNel(int fqNel) {
		this.fqNel = fqNel;
	}

	public int getImprivacy() {
		return imprivacy;
	}

	public void setImprivacy(int imprivacy) {
		this.imprivacy = imprivacy;
	}

	public int getMyMusicAutoPlay() {
		return myMusicAutoPlay;
	}

	public void setMyMusicAutoPlay(int myMusicAutoPlay) {
		this.myMusicAutoPlay = myMusicAutoPlay;
	}

	public int getNewsLetter() {
		return newsLetter;
	}

	public void setNewsLetter(int newsLetter) {
		this.newsLetter = newsLetter;
	}

	public int getNoPicForward() {
		return noPicForward;
	}

	public void setNoPicForward(int noPicForward) {
		this.noPicForward = noPicForward;
	}

	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	public int getOtherMusucAutoPlay() {
		return otherMusucAutoPlay;
	}

	public void setOtherMusucAutoPlay(int otherMusucAutoPlay) {
		this.otherMusucAutoPlay = otherMusucAutoPlay;
	}

	public int getProfileFriendViewOnly() {
		return profileFriendViewOnly;
	}

	public void setProfileFriendViewOnly(int profileFriendViewOnly) {
		this.profileFriendViewOnly = profileFriendViewOnly;
	}

	public int getShowBirthToFriend() {
		return showBirthToFriend;
	}

	public void setShowBirthToFriend(int showBirthToFriend) {
		this.showBirthToFriend = showBirthToFriend;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getRegion() {
		return region;
	}
	

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public int getDefaultFileStatus() {
		return defaultFileStatus;
	}

	public void setDefaultFileStatus(int defaultFileStatus) {
		this.defaultFileStatus = defaultFileStatus;
	}

	public int getDefaultMusicStatus() {
		return defaultMusicStatus;
	}

	public void setDefaultMusicStatus(int defaultMusicStatus) {
		this.defaultMusicStatus = defaultMusicStatus;
	}

	public int getDefaultPictureStatus() {
		return defaultPictureStatus;
	}

	public void setDefaultPictureStatus(int defaultPictureStatus) {
		this.defaultPictureStatus = defaultPictureStatus;
	}

	public int getGrowKeyword() {
		return growKeyword;
	}

	public void setGrowKeyword(int growKeyword) {
		this.growKeyword = growKeyword;
	}

	public int getShowTitleToFriends() {
		return showTitleToFriends;
	}

	public void setShowTitleToFriends(int showTitleToFriends) {
		this.showTitleToFriends = showTitleToFriends;
	}
	
	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public int getUriType() {
		return uriType;
	}

	public void setUriType(int uriType) {
		this.uriType = uriType;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(int defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public List<UserObjectRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserObjectRole> roles) {
		this.roles = roles;
	}

	public long getPermission() {
		return permission;
	}

	public void setPermission(long permission) {
		this.permission = permission;
	}

}