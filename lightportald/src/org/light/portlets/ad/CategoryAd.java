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

package org.light.portlets.ad;

import java.sql.Date;
import java.sql.Timestamp;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class CategoryAd extends Entity {

	private long userId;
	private String title;
	private String content;
	private Timestamp  createDate = new Timestamp(System.currentTimeMillis());
	private Date showDate;
	private Date effDate;
	private Date endEffDate;
	private int status; // 0 draft 1 posted
	private int category;
	private String country;
	private String province;
	private String city;
	private String adUrl;
	private int score;
	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	private int commentCount;
    
	public CategoryAd(){
		super();
	}
	
	public CategoryAd(String title, String content,int category,String country,String province,String city,String adUrl,int status,long userId,Date showDate,Date effDate,Date endEffDate){
		this();		
		this.title = title;
		this.content = content;
		this.category = category;
		this.country = country;
		this.province = province;
		this.city = city;
		this.adUrl = adUrl;
		this.status = status;
		this.userId = userId;
		this.showDate = showDate;
		this.effDate = effDate;
		this.endEffDate = endEffDate;
	}
	
	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
	
	public String getLocation(){
		String location = "";
		if(this.city != null)
			location = this.city;
		if(this.province != null)
			if(location.length() > 0)
				location+=","+this.province;
			else
				location = this.province;
		if(this.country != null)
			if(location.length() > 0)
				location+=" "+this.country;
			else
				location = this.country;
		return location;
	}
	public String getDate(){
		 return DateUtil.format(this.createDate,"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.createDate,"EEEE, MMMM dd, yyyy HH:mm aaa");
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getEndEffDate() {
		return endEffDate;
	}

	public void setEndEffDate(Date endEffDate) {
		this.endEffDate = endEffDate;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}
