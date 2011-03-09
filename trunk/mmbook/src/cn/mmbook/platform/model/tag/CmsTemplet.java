package cn.mmbook.platform.model.tag;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javacommon.base.*;
import javacommon.util.*;
import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.impl.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 标签管理系统： 模板数据类
 * @author 自强  zqiangzhang@gmail.com
 *
 */


public class CmsTemplet extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	/**模板ID*/
	private java.lang.String id;
	/**模板名称*/
	private java.lang.String templetName;
	/**模板类型(已废弃）*/
	private java.lang.String templetType;
	/**入库时间*/
	private java.sql.Timestamp insertTime;
	/**更新时间*/
	private java.sql.Timestamp updateTime;
	
	private java.lang.String insertTimeString;
	 
	private java.lang.String updateTimeString;
	/**模板内容 二进制流 */
	private byte[] templetContent;
	/**模板内容 String*/
	private java.lang.String templetContentString;
	/**模板类型*/
	/**网页模板类型:
		模板类型为网页模板时需填写此字段
		10：首页
		11: 栏目首页
		12：列表页
		13：详细页
		14：下载页
		15：预览页
		16:章节列表页

		20：栏目标签
		21：内容标签
		22:推荐位标签
	*/	
	private java.lang.String webTempletType;
	/**文件名*/
	private java.lang.String fileName;
	/**模板版本ID*/
	private java.lang.String versionId;
	/**模板版本与皮肤组成的模板URL如: web/default (版本URL+皮肤URL)*/
	private java.lang.String templetPath;
	//columns END

	
	
	
	public CmsTemplet(){
	}

	public CmsTemplet(
		java.lang.String id
	){
		this.id = id;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setTempletName(java.lang.String value) {
		this.templetName = value;
	}
	
	public java.lang.String getTempletName() {
		return this.templetName;
	}
	public void setTempletType(java.lang.String value) {
		this.templetType = value;
	}
	
	public java.lang.String getTempletType() {
		return this.templetType;
	}
	
	public void setInsertTime(java.sql.Timestamp value) {
		this.insertTime = value;
		setInsertTimeString(date2String(value, FORMAT_INSERT_TIME_));
	}
	
	public java.sql.Timestamp getInsertTime() {
		return this.insertTime;
	}
	
	public void setUpdateTime(java.sql.Timestamp value) {
		this.updateTime = value;
		setUpdateTimeString(date2String(value, FORMAT_UPDATE_TIME_));
	}
	
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setWebTempletType(java.lang.String value) {
		this.webTempletType = value;
	}
	
	public java.lang.String getWebTempletType() {
		return this.webTempletType;
	}
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	public void setVersionId(java.lang.String value) {
		this.versionId = value;
	}
	
	public java.lang.String getVersionId() {
		return this.versionId;
	}

	
	public byte[] getTempletContent() {
		return templetContent;
	}

	public void setTempletContent(byte[] templetContent) throws UnsupportedEncodingException {
		this.templetContent = templetContent;
		setTempletContentString(new String(templetContent,"UTF-8"));
	}

	public java.lang.String getTempletContentString() {
		return templetContentString;
	}

	public void setTempletContentString(java.lang.String templetContentString) {
		this.templetContentString = templetContentString;
	}

	public java.lang.String getInsertTimeString() {
		return insertTimeString;
	}

	public void setInsertTimeString(java.lang.String insertTimeString) {
		this.insertTimeString = insertTimeString;
	}

	public java.lang.String getUpdateTimeString() {
		return updateTimeString;
	}

	public void setUpdateTimeString(java.lang.String updateTimeString) {
		this.updateTimeString = updateTimeString;
	}
	

	public java.lang.String getTempletPath() {
		return templetPath;
	}

	public void setTempletPath(java.lang.String templetPath) {
		this.templetPath = templetPath;
	}


	
	
	
	/******************************************/
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("TempletName",getTempletName())
			.append("TempletType",getTempletType())
			.append("InsertTime",getInsertTime())
			.append("UpdateTime",getUpdateTime())
			.append("TempletContent",getTempletContent())
			.append("WebTempletType",getWebTempletType())
			.append("FileName",getFileName())
			.append("VersionId",getVersionId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getTempletName())
			.append(getTempletType())
			.append(getInsertTime())
			.append(getUpdateTime())
			.append(getTempletContent())
			.append(getWebTempletType())
			.append(getFileName())
			.append(getVersionId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTemplet == false) return false;
		if(this == obj) return true;
		CmsTemplet other = (CmsTemplet)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getTempletName(),other.getTempletName())
			.append(getTempletType(),other.getTempletType())
			.append(getInsertTime(),other.getInsertTime())
			.append(getUpdateTime(),other.getUpdateTime())
			.append(getTempletContent(),other.getTempletContent())
			.append(getWebTempletType(),other.getWebTempletType())
			.append(getFileName(),other.getFileName())
			.append(getVersionId(),other.getVersionId())
			.isEquals();
	}


}

