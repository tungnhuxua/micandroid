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

import java.util.LinkedList;
import java.util.List;

import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserComment extends Entity implements Comparable{
	
	private long userId;
	private long objectId;
	private int  objectType; //see Constants started with _OBJECT_TYPE_
	private String comment;
	private int status; // see Constants started with _STATUS_ 
	private long parentId;
	
	//read only
	private String photoUrl;
	private String uri;
	private String displayName;
	private List<UserComment> children;
	private boolean checked;
	
	public UserComment(){
		super();
	}
	
	public UserComment(long userId, long objectId, int objectType, String comment){
		this();
		this.userId = userId;
		this.objectId = objectId;
		this.objectType = objectType;		
		this.comment = comment;		
	}
	
	public void addChild(UserComment child){
		if(children == null) children = new LinkedList<UserComment>();
		children.add(child);
	}
	public boolean isAllChecked(){
		if(!this.isChecked()) return false;
		boolean allChecked = true;
		if(this.children != null){
			for(UserComment c : this.children){
				allChecked = allChecked && c.isAllChecked();
			}
		}
		return allChecked;
	}
	public void resetChecker(){
		this.setChecked(false);
		if(this.children != null){
			for(UserComment c : this.children){
				c.resetChecker();
			}
		}
	}
	
	/**
     * Compares to another Keyword; used for sorting.
     */
    public int compareTo(Object object)
    {
    	UserComment that = (UserComment) object;
    	return this.getCreateDate().compareTo(that.getCreateDate());
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
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<UserComment> getChildren() {
		return children;
	}

	public void setChildren(List<UserComment> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	

}
