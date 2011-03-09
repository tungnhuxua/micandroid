package cn.mmbook.platform.model.site;

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

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;

/**
 * <p> SiteNovelContent 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteNovelContent extends BaseEntity {
	
	//date formats
	public static final String FORMAT_CREATE_TIME_ = DATE_TIME_FORMAT;
	public static final String FORMAT_MODIFY_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	/**章节ID*/
	private java.lang.Integer id;
	/**小说ID*/
	private java.lang.String noveId;
	/**内容ID*/
	private java.lang.String contentId;
	/**上级章节ID*/
	private java.lang.Integer chapterParentId;
	/**章节名称*/
	private java.lang.String name;
	/**章节号*/
	private java.lang.String chapterNo;
	/**章节状态*/
	private java.lang.Integer chapterModeNo;
	/**新增时间*/
	private java.sql.Date createTime;
	/**新增人*/
	private java.lang.String creator;
	/**修改时间*/
	private java.sql.Date modifyTime;
	/**修改人*/
	private java.lang.String modifior;
	/**章节点击量*/
	private java.lang.Integer chapterCount;
	//columns END

	public SiteNovelContent(){
	}

	public SiteNovelContent(
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
	public void setNoveId(java.lang.String value) {
		this.noveId = value;
	}
	
	public java.lang.String getNoveId() {
		return this.noveId;
	}
	public void setContentId(java.lang.String value) {
		this.contentId = value;
	}
	
	public java.lang.String getContentId() {
		return this.contentId;
	}
	public void setChapterParentId(java.lang.Integer value) {
		this.chapterParentId = value;
	}
	
	public java.lang.Integer getChapterParentId() {
		return this.chapterParentId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setChapterNo(java.lang.String value) {
		this.chapterNo = value;
	}
	
	public java.lang.String getChapterNo() {
		return this.chapterNo;
	}
	public void setChapterModeNo(java.lang.Integer value) {
		this.chapterModeNo = value;
	}
	
	public java.lang.Integer getChapterModeNo() {
		return this.chapterModeNo;
	}
	public String getCreateTimeString() {
		return date2String(getCreateTime(), FORMAT_CREATE_TIME_);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(string2Date(value, FORMAT_CREATE_TIME_,java.sql.Date.class));
	}
	
	public void setCreateTime(java.sql.Date value) {
		this.createTime = value;
	}
	
	public java.sql.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	public String getModifyTimeString() {
		return date2String(getModifyTime(), FORMAT_MODIFY_TIME_);
	}
	public void setModifyTimeString(String value) {
		setModifyTime(string2Date(value, FORMAT_MODIFY_TIME_,java.sql.Date.class));
	}
	
	public void setModifyTime(java.sql.Date value) {
		this.modifyTime = value;
	}
	
	public java.sql.Date getModifyTime() {
		return this.modifyTime;
	}
	public void setModifior(java.lang.String value) {
		this.modifior = value;
	}
	
	public java.lang.String getModifior() {
		return this.modifior;
	}
	public void setChapterCount(java.lang.Integer value) {
		this.chapterCount = value;
	}
	
	public java.lang.Integer getChapterCount() {
		return this.chapterCount;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("NoveId",getNoveId())
			.append("ContentId",getContentId())
			.append("ChapterParentId",getChapterParentId())
			.append("Name",getName())
			.append("ChapterNo",getChapterNo())
			.append("ChapterModeNo",getChapterModeNo())
			.append("CreateTime",getCreateTime())
			.append("Creator",getCreator())
			.append("ModifyTime",getModifyTime())
			.append("Modifior",getModifior())
			.append("ChapterCount",getChapterCount())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getNoveId())
			.append(getContentId())
			.append(getChapterParentId())
			.append(getName())
			.append(getChapterNo())
			.append(getChapterModeNo())
			.append(getCreateTime())
			.append(getCreator())
			.append(getModifyTime())
			.append(getModifior())
			.append(getChapterCount())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteNovelContent == false) return false;
		if(this == obj) return true;
		SiteNovelContent other = (SiteNovelContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getNoveId(),other.getNoveId())
			.append(getContentId(),other.getContentId())
			.append(getChapterParentId(),other.getChapterParentId())
			.append(getName(),other.getName())
			.append(getChapterNo(),other.getChapterNo())
			.append(getChapterModeNo(),other.getChapterModeNo())
			.append(getCreateTime(),other.getCreateTime())
			.append(getCreator(),other.getCreator())
			.append(getModifyTime(),other.getModifyTime())
			.append(getModifior(),other.getModifior())
			.append(getChapterCount(),other.getChapterCount())
			.isEquals();
	}
}

