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

package org.light.portlets.message;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;
import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Message extends Entity {
	
	private long userId;
	private long postById;
	private String subject;
	private String content;
	private int format; //0 html, 1 plain text
	private int direction; //0 incoming 1 sent
	private int status; //0 new message, 1 read message		
	private int type; // 0 regular; 1 invite; 2 request	
	private int event; // 0 regular, 1 group, 2 calendar, 3 comments, 4 forum category 5 forum sub category, 6 connection
	private long eventId;
	
	//read only
	private String fromPhotoUrl;
	private String fromUri;
	private String fromDisplayName;
	private int fromCurrentStatusId;
	
	private String toPhotoUrl;
	private String toUri;
	private String toDisplayName;
	private int toCurrentStatusId;
	public Message(){
		super();
	}
    
	public Message(String subject,String content,long userId,long postById,long orgId){
		this();
		this.subject = subject;
		this.content = content;
		this.userId = userId;
		this.postById = postById;
		this.setOrgId(orgId);
	}
	
	public Message(String subject,String content,long userId,long postById,long orgId, int format){
		this(subject,content,userId,postById,orgId);
		this.format = format;
	}
	public Message(String subject,String content,long userId,long postById,long orgId,int format,int direction){
		this(subject,content,userId,postById,orgId,format);
		this.direction = direction;
	}
	public Message(String subject,String content,long userId,long postById,long orgId,int type,int event,long eventId){
		this(subject,content,userId,postById,orgId);
		this.type = type;
		this.event =event;		
		this.eventId = eventId;
	}
	public Message(String subject,String content,long userId,long postById,long orgId, int format,int type,int event,long eventId){
		this(subject,content,userId,postById,orgId,type,event,eventId);
		this.format = format;
	}
	
	public String getDate(){
		 return DateUtil.format(this.getCreateDate(),"EEE, MMM dd, yyyy");
	}
	
	public String getFullDate(){
		 return DateUtil.format(this.getCreateDate(),"EEEE, MMMM dd, yyyy HH:mm aaa");
	}

	public String getFromPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.fromPhotoUrl);		
	}
	
	public String getToPhotoUrl() {
		return ImageUtil.getPhotoUrl(this.toPhotoUrl);		
	}
	public String getFromUri() {
		return fromUri;
	}
	
	public String getToUri() {
		return toUri;
	}
		public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}
	

	public long getPostById() {
		return postById;
	}
	

	public void setPostById(long postById) {
		this.postById = postById;
	}
	

	public String getSubject() {
		return subject;
	}
	

	public void setSubject(String subject) {
		this.subject = subject;
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

	public void setFromUri(String fromUri) {
		this.fromUri = fromUri;
	}
	

	public String getFromDisplayName() {
		return fromDisplayName;
	}
	

	public void setFromDisplayName(String fromDisplayName) {
		this.fromDisplayName = fromDisplayName;
	}
	
	public void setToUri(String toUri) {
		this.toUri = toUri;
	}
		

	public String getToDisplayName() {
		return toDisplayName;
	}
	

	public void setToDisplayName(String toDisplayName) {
		this.toDisplayName = toDisplayName;
	}


	public void setFromPhotoUrl(String fromPhotoUrl) {
		this.fromPhotoUrl = fromPhotoUrl;
	}
	


	public void setToPhotoUrl(String toPhotoUrl) {
		this.toPhotoUrl = toPhotoUrl;
	}
	

	public int getEvent() {
		return event;
	}
	

	public void setEvent(int event) {
		this.event = event;
	}
	

	public long getEventId() {
		return eventId;
	}
	

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	

	public int getType() {
		return type;
	}
	

	public void setType(int type) {
		this.type = type;
	}

	public int getFromCurrentStatusId() {
		return fromCurrentStatusId;
	}

	public void setFromCurrentStatusId(int fromCurrentStatusId) {
		this.fromCurrentStatusId = fromCurrentStatusId;
	}

	public int getToCurrentStatusId() {
		return toCurrentStatusId;
	}

	public void setToCurrentStatusId(int toCurrentStatusId) {
		this.toCurrentStatusId = toCurrentStatusId;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
