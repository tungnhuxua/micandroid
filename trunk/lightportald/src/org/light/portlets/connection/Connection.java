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

package org.light.portlets.connection;

import java.sql.Timestamp;

import org.light.portal.model.Entity;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Connection extends Entity {

	private long userId;
	private long buddyUserId;
	private int type;// 0 friend; 1 family; 2 close friend; 3 classmate; 4 colleague; 
	
	//	read only
	//private String buddyName;
	private int buddyCurrentStatusId;
	private String buddyCurrentStatus;
	
	private String photoUrl;
	private String uri;
	private String displayName;
	private int showTitleToFriends;
	private String title;
	private String birth;
	private Timestamp lastLoginDate;
	private String city;
	private String province;
	
	public Connection(){
		super();
	}
	
	public Connection(long userId, long buddyUserId){
		this();
		this.userId = userId;
		this.buddyUserId = buddyUserId;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
	
	public String getBirthM(){
		return StringUtil.isEmpty(birth) ? null : birth.substring(5,7);
	}
	
	public String getBirthD(){
		return StringUtil.isEmpty(birth) ? null : birth.substring(8,10);
	}
	
	public long getBuddyUserId() {
		return buddyUserId;
	}
	

	public void setBuddyUserId(long buddyUserId) {
		this.buddyUserId = buddyUserId;
	}
		

	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getBuddyCurrentStatus() {
		return buddyCurrentStatus;
	}
	

	public void setBuddyCurrentStatus(String buddyCurrentStatus) {
		this.buddyCurrentStatus = buddyCurrentStatus;
	}

	public int getBuddyCurrentStatusId() {
		return buddyCurrentStatusId;
	}
	

	public void setBuddyCurrentStatusId(int buddyCurrentStatusId) {
		this.buddyCurrentStatusId = buddyCurrentStatusId;
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
	
	public int getShowTitleToFriends() {
		return showTitleToFriends;
	}

	public void setShowTitleToFriends(int showTitleToFriends) {
		this.showTitleToFriends = showTitleToFriends;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
