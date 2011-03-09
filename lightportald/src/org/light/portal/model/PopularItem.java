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
public class PopularItem  extends Entity{

	private String link;
	private String title;
	private String desc;
	private String tag;
	private String locale;
	private long postById;
	private long orgId;
	private int popCount;

	//read only
	private String photoUrl;
	private int photoWidth;
	private int photoHeight;
	private String uri;
	private String displayName;
	private int currentStatusId;
    private int commentCount;
	    
	public PopularItem(){
		super();
	}

	public PopularItem(String link,String title,String desc, String tag, String locale, long postById, long orgId){
		this();
		this.link = link;
		this.title = title;
		this.desc = desc;
		this.tag = tag;
		this.locale = locale;
		this.postById = postById;
		this.orgId = orgId;
		this.popCount = 1;
	}

	public void popIt(){
		this.popCount++;
	}
	   
	public int getPhotoSmallWidth(){
		return ImageUtil.getPhotoSmallWidth(this.photoWidth,this.photoHeight);
	}
	public int getPhotoSmallHeight(){
		return ImageUtil.getPhotoSmallHeight(this.photoWidth,this.photoHeight);		
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getCurrentStatusId() {
		return currentStatusId;
	}

	public void setCurrentStatusId(int currentStatusId) {
		this.currentStatusId = currentStatusId;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
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

	public int getPopCount() {
		return popCount;
	}

	public void setPopCount(int popCount) {
		this.popCount = popCount;
	}

	public long getPostById() {
		return postById;
	}

	public void setPostById(long postById) {
		this.postById = postById;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}