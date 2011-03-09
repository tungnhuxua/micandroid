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

package org.light.portlets.widget;

import java.sql.Timestamp;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Widget extends Entity {

	private long userId;
	private long portletId;
	private long orgId;
	private String title;
	private String summary;
	private String content;
	private String width;
	private String height;
	private String maxHeight;
	private Timestamp  createDate = new Timestamp(System.currentTimeMillis());
	private int status; // 0 private 1 public
	private int score;
	//read only
	private String uri;
	private String displayName;
	
	public Widget(){
		super();
	}
	
	public Widget(long userId, long portletId, long orgId, String title, String summary, String content, String width,String height, String maxHeight, int status){
		this();
		this.userId = userId;
		this.portletId = portletId;
		this.orgId  = orgId;
		this.title = title;
		this.summary = summary;
		this.content = content;	
		this.width = width;
		this.height = height;
		this.maxHeight = maxHeight;		
		this.status = status;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}
	

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
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

	public int getScore() {
		return score;
	}
	

	public void setScore(int score) {
		this.score = score;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getPortletId() {
		return portletId;
	}

	public void setPortletId(long portletId) {
		this.portletId = portletId;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}
