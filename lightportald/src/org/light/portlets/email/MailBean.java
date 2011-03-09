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

package org.light.portlets.email;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.mail.Message;

import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class MailBean implements Serializable{
	private String from;
	private String fromAddress;
	private String sender;
	private String subject;
	private String content;
	private Date sentDate;
	private int flag;  //0 seen,1 new
	private int total;
	private int unreadCount;
	private Message msg;
	
	public MailBean(String from,String fromAddress,String sender,String subject,String content, Date sentDate,int flag,int total,int unreadCount,Message msg){
		this.from = from;
		this.fromAddress = fromAddress;
		this.sender = sender;
		this.subject = subject;
		this.content = content;
		this.sentDate = sentDate;
		this.flag = flag;
		this.total = total;
		this.unreadCount = unreadCount;
		this.msg= msg;
	}
	public String getFromName(){
		String name = this.from;
		if(name == null || name.length()<=0)
			name = this.fromAddress;
		return name;
	}
	public String getFromInfo(){
		String info = this.fromAddress;
		if(this.from != null && this.from.length()>0){
			String name = this.from;
			if(this.from.indexOf(",") > 0)
				name ="\""+this.from+"\"";
			if(this.fromAddress != null)
				info = name+"<"+this.fromAddress+">";
			else
				info = name;
		}
		return info;
	}
	public String getDate(){
		String date= null;
		if(this.sentDate != null){
			Calendar today = Calendar.getInstance();
			today.setTimeInMillis(System.currentTimeMillis());
		    today.set(Calendar.HOUR,0);
		    today.set(Calendar.MINUTE,0);
		    today.set(Calendar.SECOND,1);
		    
			Calendar sentTime = Calendar.getInstance();
			sentTime.setTimeInMillis(this.sentDate.getTime());			
			if(sentTime.after(today)){
				date= DateUtil.format(this.sentDate,"HH:mm aaa");
			}else{
				today.add(Calendar.DATE, -7);
				if(sentTime.after(today)){
					date= DateUtil.format(this.sentDate,"EEE HH:mm aaa");
				}else{
					date= DateUtil.format(this.sentDate,"MM/dd/yy");
				}
			}		
		}
		return date;
	}
	public String getFullDate(){
		String date= null;
		if(this.sentDate != null)
			date= DateUtil.format(this.sentDate,"EEEE, MMMM dd, yyyy HH:mm aaa");
		return date;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
	
}
