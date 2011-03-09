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

import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserFavourite extends Entity{

	private long userId;
	private long favouriteId;

	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	
	public UserFavourite(){
		super();
	}
	
	public UserFavourite(long userId, long favouriteId){
		this();
		this.userId = userId;
		this.favouriteId = favouriteId;	
	}
	
	public String getUri() {
		return uri;
	}

	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}

	public String getDisplayName() {
		return displayName;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

	public long getFavouriteId() {
		return favouriteId;
	}
	

	public void setFavouriteId(long favouriteId) {
		this.favouriteId = favouriteId;
	}
		
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
