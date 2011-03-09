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

import org.light.portal.model.Entity;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserGroup extends Entity {

	private long userId;
	private long groupId;
	private int acceptLeaderBulletin =1;
	private int acceptMembersBulletin;
	
	//read only
	private String displayName;
	private String uri;
	private Integer openJoin;
	private Integer memberInvite; 
	private String photoUrl;
	
	private String userPhotoUrl;
	private String userUri;
	private String userDisplayName;
	private int userCurrentStatusId;
	
	public UserGroup(){
		super();
	}
	
	public UserGroup(long userId,long groupId){
		this();
		this.userId = userId;
		this.groupId= groupId;
	}
	
	public String getPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
	
	public String getUserPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.userPhotoUrl);
	}
			
	public String getUserUri() {
		return userUri;
	}
		
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserUri(String userUri) {
		this.userUri = userUri;
	}
	

	public String getUserDisplayName() {
		return userDisplayName;
	}
	

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	
	

	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}
	
	public int getUserCurrentStatusId() {
		return userCurrentStatusId;
	}
	

	public void setUserCurrentStatusId(int userCurrentStatusId) {
		this.userCurrentStatusId = userCurrentStatusId;
	}

	public int getAcceptLeaderBulletin() {
		return acceptLeaderBulletin;
	}
	

	public void setAcceptLeaderBulletin(int acceptLeaderBulletin) {
		this.acceptLeaderBulletin = acceptLeaderBulletin;
	}
	

	public int getAcceptMembersBulletin() {
		return acceptMembersBulletin;
	}
	

	public void setAcceptMembersBulletin(int acceptMembersBulletin) {
		this.acceptMembersBulletin = acceptMembersBulletin;
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


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


}
