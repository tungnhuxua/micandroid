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

import static org.light.portal.util.Constants._DEFAULT_USER_PICTURE_MAX_WIDTH;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import org.light.portal.core.service.ServiceContext;
import org.light.portal.user.service.UserService;
import org.light.portal.util.DateUtil;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserPicture extends Entity implements Fileable{
	
	private long userId;
	private long orgId;
	private long topOrgId;
	private String pictureUrl;
	private int pictureWidth;
	private int pictureHeight;
	private String caption;
	private String tag;
	private int status; //see Constants started with _PRIVACY_
	private int rankable;//1 for rank
	private int score;
		
	private String uri;
	private String displayName;
	
	private int smallWidth;
	private int smallHeight;
	private int largeWidth;
	private int largeHeight;
	
	private int _standardSmallWidth = PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_WIDTH) / 2;
	private int _standardLargeWidth = PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_WIDTH);
	private int _showColumns = 1;
		
	private Set<PicturePositionTag> taggings;
	private String contentType;
	private String dir;
	private byte[] file;
	
	public UserPicture(){
		super();
	}
	
	public UserPicture(long userId,long orgId, long topOrgId, String pictureUrl,int status,int pictureWidth,int pictureHeight){
		this();
		this.userId = userId;
		this.orgId = orgId;
		this.topOrgId = topOrgId;
		this.pictureUrl = pictureUrl;
		this.status = status;
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;		
	}
	public UserPicture(long userId,long orgId,long topOrgId,String pictureUrl,int status,int pictureWidth,int pictureHeight, Date takenDate){
		this(userId,orgId,topOrgId,pictureUrl,status,pictureWidth,pictureHeight);
		if(takenDate != null){
			this.setCreateDate(new Timestamp(takenDate.getTime()));
		}
	}
	public UserPicture(long userId,long orgId,long topOrgId,String pictureUrl,int status,int pictureWidth,int pictureHeight,String caption){
		this(userId,orgId,topOrgId,pictureUrl,status,pictureWidth,pictureHeight);
		this.caption = caption;
	}
	/* (non-Javadoc)
	    * @see java.lang.Object#hashCode()
	    */
   @Override
   public int hashCode() {       
	   final int PRIME = 31;
       int result = 1;
       result = PRIME * result + new Long(getId()).intValue();
       return result;
   }
  
   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       final UserPicture other = (UserPicture) obj;
       if (getId() != other.getId())
           return false;
       return true;
   }
   
   public String getFileUrl() {
		return this.pictureUrl;
   }
	
   public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
   }
   
   public boolean isHttpUrl(){
	   return this.pictureUrl.toLowerCase().startsWith("http");
   }
   
   public String getUri() {
	   return uri;
   }
   public void setUri(String uri) {
		this.uri = uri;
	}
   public Set<PicturePositionTag> getTaggings() {
		return taggings;
	}
   
	public void setStandardSmallWidth(int clientWidth){
		if(clientWidth > 0){
			int width = clientWidth - 10;
			if(_standardSmallWidth != width || _showColumns != 1){
				_standardSmallWidth = width;
				_showColumns = 1;
				smallWidth = 0;
				smallHeight = 0;
			}
			if(_standardSmallWidth > _standardLargeWidth)
				_standardSmallWidth = _standardLargeWidth;
		}
	}
	public void setStandardSmallWidth(int clientWidth, int columns){
		int width = _standardSmallWidth;
		if(clientWidth > 0) width = clientWidth - 10;
		if(_standardSmallWidth != width || _showColumns != columns){
			_standardSmallWidth = width / columns;
			_showColumns = columns;
			smallWidth = 0;
			smallHeight = 0;
		}
		if(_standardSmallWidth > _standardLargeWidth)
			_standardSmallWidth = _standardLargeWidth;
	}
	
	public int getSmallWidth(){
		if(smallWidth == 0){
			int newWidth = this.pictureWidth;
			int newHeight = this.pictureHeight;
			int rate = 99;
			while(newWidth > _standardSmallWidth){
				newWidth = this.pictureWidth * rate / 100;
				newHeight= this.pictureHeight * rate / 100;
				rate--;
			}
			smallWidth= newWidth;
			smallHeight = newHeight;
		}
		return smallWidth;
	}
	public int getSmallHeight(){
		if(smallHeight == 0){
			int newWidth = this.pictureWidth;
			int newHeight = this.pictureHeight;
			int rate = 99;
			while(newWidth > _standardSmallWidth){
				newWidth = this.pictureWidth * rate / 100;
				newHeight= this.pictureHeight * rate / 100;
				rate--;
			}
			smallWidth= newWidth;
			smallHeight = newHeight;
		}
		return smallHeight;
	}
	
	public int getLargeWidth(){
		if(largeWidth == 0){
			int newWidth = this.pictureWidth;
			int newHeight = this.pictureHeight;
			int rate = 99;
			while(newWidth > _standardLargeWidth){
				newWidth = this.pictureWidth * rate / 100;
				newHeight= this.pictureHeight * rate / 100;
				rate--;
			}
			largeWidth= newWidth;
			largeHeight = newHeight;
		}
		return largeWidth;
	}
	public int getLargeHeight(){
		if(largeHeight == 0){
			int newWidth = this.pictureWidth;
			int newHeight = this.pictureHeight;
			int rate = 99;
			while(newWidth > _standardLargeWidth){
				newWidth = this.pictureWidth * rate / 100;
				newHeight= this.pictureHeight * rate / 100;
				rate--;
			}
			largeWidth= newWidth;
			largeHeight = newHeight;
		}
		return largeHeight;
	}
	
	public int getPictureHeight() {
		return pictureHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		this.pictureHeight = pictureHeight;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getPictureWidth() {
		return pictureWidth;
	}

	public void setPictureWidth(int pictureWidth) {
		this.pictureWidth = pictureWidth;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRankable() {
		return rankable;
	}

	public void setRankable(int rankable) {
		this.rankable = rankable;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	public String getTaggingsString() {
		StringBuilder buffer = new StringBuilder();		
		if(this.getTaggings() != null && this.getTaggings().size() > 0){
			int i=0;
			for(PicturePositionTag tagging : this.getTaggings()){
				i++;
				buffer.append(tagging.getId())
					  .append("+")
					  .append(tagging.getPositionX())
					  .append("+")
					  .append(tagging.getPositionY())
					  .append("+")
					  .append(tagging.getType())
					  ;
				if(i < this.getTaggings().size())
					buffer.append("-");					
			}
		}
		return buffer.toString();
	}
	
	public void setTaggings(Set<PicturePositionTag> taggings) {
		this.taggings = taggings;
	}
	
	protected UserService getUserService() {
		return (UserService)ServiceContext.getInstance().getContext().getBean("userService");
	}

	public long getTopOrgId() {
		return topOrgId;
	}

	public void setTopOrgId(long topOrgId) {
		this.topOrgId = topOrgId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
