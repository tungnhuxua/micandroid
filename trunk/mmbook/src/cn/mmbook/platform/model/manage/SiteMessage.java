package cn.mmbook.platform.model.manage;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.service.manage.impl.*;
import cn.mmbook.platform.service.manage.*;

/**
 * <p> SiteMessage 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteMessage extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String messageTitle;
	private java.lang.String messageContent;
	private java.lang.Integer replyId;
	private java.lang.Integer messageUser;
	private java.lang.Integer messageType;
	private java.lang.Integer whetherReader;
	private java.lang.Integer whetherReply;
	private java.sql.Date insertTime;
	//columns END

	public SiteMessage(){
	}

	public SiteMessage(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMessageTitle(java.lang.String value) {
		this.messageTitle = value;
	}
	
	public java.lang.String getMessageTitle() {
		return this.messageTitle;
	}
	public void setMessageContent(java.lang.String value) {
		this.messageContent = value;
	}
	
	public java.lang.String getMessageContent() {
		return this.messageContent;
	}
	public void setReplyId(java.lang.Integer value) {
		this.replyId = value;
	}
	
	public java.lang.Integer getReplyId() {
		return this.replyId;
	}
	public void setMessageUser(java.lang.Integer value) {
		this.messageUser = value;
	}
	
	public java.lang.Integer getMessageUser() {
		return this.messageUser;
	}
	public void setMessageType(java.lang.Integer value) {
		this.messageType = value;
	}
	
	public java.lang.Integer getMessageType() {
		return this.messageType;
	}
	public void setWhetherReader(java.lang.Integer value) {
		this.whetherReader = value;
	}
	
	public java.lang.Integer getWhetherReader() {
		return this.whetherReader;
	}
	public void setWhetherReply(java.lang.Integer value) {
		this.whetherReply = value;
	}
	
	public java.lang.Integer getWhetherReply() {
		return this.whetherReply;
	}
	public String getInsertTimeString() {
		return date2String(getInsertTime(), FORMAT_INSERT_TIME_);
	}
	public void setInsertTimeString(String value) {
		setInsertTime(string2Date(value, FORMAT_INSERT_TIME_,java.sql.Date.class));
	}
	
	public void setInsertTime(java.sql.Date value) {
		this.insertTime = value;
	}
	
	public java.sql.Date getInsertTime() {
		return this.insertTime;
	}
	
	 
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("MessageTitle",getMessageTitle())
			.append("MessageContent",getMessageContent())
			.append("ReplyId",getReplyId())
			.append("MessageUser",getMessageUser())
			.append("MessageType",getMessageType())
			.append("WhetherReader",getWhetherReader())
			.append("WhetherReply",getWhetherReply())
			.append("InsertTime",getInsertTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getMessageTitle())
			.append(getMessageContent())
			.append(getReplyId())
			.append(getMessageUser())
			.append(getMessageType())
			.append(getWhetherReader())
			.append(getWhetherReply())
			.append(getInsertTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteMessage == false) return false;
		if(this == obj) return true;
		SiteMessage other = (SiteMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getMessageTitle(),other.getMessageTitle())
			.append(getMessageContent(),other.getMessageContent())
			.append(getReplyId(),other.getReplyId())
			.append(getMessageUser(),other.getMessageUser())
			.append(getMessageType(),other.getMessageType())
			.append(getWhetherReader(),other.getWhetherReader())
			.append(getWhetherReply(),other.getWhetherReply())
			.append(getInsertTime(),other.getInsertTime())
			.isEquals();
	}
}

