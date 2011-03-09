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


/**
 * 
 * @author Jianmin Liu
 **/
public class UserMusic extends Entity implements Fileable{

	private long userId;
	private String musicUrl;	
	private String caption;
	private int status; //see Constants started with _PRIVACY_
	private int rankable;
	
	private String contentType;
	private String dir;
	private byte[] file;
	
	public UserMusic(){
		super();
	}
	
	public UserMusic(long userId,String musicUrl,String caption,int status){
		this.userId = userId;
		this.musicUrl = musicUrl;
		this.caption = caption;		
		this.status = status;
	}
	
	public String getFileUrl() {
		return this.musicUrl;
	}
	
	public String getMusicName(){
		String name = this.musicUrl;
		int index = name.lastIndexOf("/");
		name = name.substring(index+1,name.length());
		return name;
	}
	public boolean isHttpUrl(){
		   return this.musicUrl.toLowerCase().startsWith("http");
	   }
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public int getRankable() {
		return rankable;
	}

	public void setRankable(int rankable) {
		this.rankable = rankable;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
