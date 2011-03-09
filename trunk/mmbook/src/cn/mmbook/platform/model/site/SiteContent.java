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
 * <p> SiteContent 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteContent extends BaseEntity {
	
	//date formats
	public static final String FORMAT_SUBMIT_TIME_ = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	/**内容ID*/
	private java.lang.String id;
	/**容内分类ID*/
	private java.lang.Integer sortId;
	/**一级分类ID*/
	private java.lang.Integer oneSortId;
	/**标题*/
	private java.lang.String title;
	/**全标题*/
	private java.lang.String titleFull;
	/**内容简介*/
	private java.lang.String synopsis;
	/**来源*/
	private java.lang.String sources;
	/**作者*/
	private java.lang.String author;
	/**内容提交人*/
	private java.lang.String submitNam;
	/**提交时间*/
	private java.sql.Date submitTime;
	/**修改人*/
	private java.lang.String updateNam;
	/**最近修改时间*/
	private java.sql.Date updateTime;
	/**缩略图URL*/
	private java.lang.String previewsImgUrl;
	/**状态*/
	private java.lang.Integer stateNo;
	/**模型ID*/
	private java.lang.String modelId;
	/**排序值*/
	private java.lang.Integer sortValue;
	/**评论状态*/
	private java.lang.Integer commentStat;
	/**查看级别*/
	private java.lang.Integer viewClass;
	/**显示时间段*/
	private java.lang.Integer showTime;
	/**内容URL*/
	private java.lang.String conentUrl;
	//columns END
	
	/**关键值*/
	private java.lang.String keywordsValue;
	/**修改内容分类时用*/
	private java.lang.Integer parentId;


	public SiteContent(){
	}

	public SiteContent(
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
	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}
	
	public java.lang.Integer getSortId() {
		return this.sortId;
	}
	public void setOneSortId(java.lang.Integer value) {
		this.oneSortId = value;
	}
	
	public java.lang.Integer getOneSortId() {
		return this.oneSortId;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setTitleFull(java.lang.String value) {
		this.titleFull = value;
	}
	
	public java.lang.String getTitleFull() {
		return this.titleFull;
	}
	public void setSynopsis(java.lang.String value) {
		this.synopsis = value;
	}
	
	public java.lang.String getSynopsis() {
		return this.synopsis;
	}
	public void setSources(java.lang.String value) {
		this.sources = value;
	}
	
	public java.lang.String getSources() {
		return this.sources;
	}
	public void setAuthor(java.lang.String value) {
		this.author = value;
	}
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	public void setSubmitNam(java.lang.String value) {
		this.submitNam = value;
	}
	
	public java.lang.String getSubmitNam() {
		return this.submitNam;
	}
	public String getSubmitTimeString() {
		return date2String(getSubmitTime(), FORMAT_SUBMIT_TIME_);
	}
	public void setSubmitTimeString(String value) {
		setSubmitTime(string2Date(value, FORMAT_SUBMIT_TIME_,java.sql.Date.class));
	}
	
	public void setSubmitTime(java.sql.Date value) {
		this.submitTime = value;
	}
	
	public java.sql.Date getSubmitTime() {
		return this.submitTime;
	}
	public void setUpdateNam(java.lang.String value) {
		this.updateNam = value;
	}
	
	public java.lang.String getUpdateNam() {
		return this.updateNam;
	}
	public String getUpdateTimeString() {
		return date2String(getUpdateTime(), FORMAT_UPDATE_TIME_);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(string2Date(value, FORMAT_UPDATE_TIME_,java.sql.Date.class));
	}
	
	public void setUpdateTime(java.sql.Date value) {
		this.updateTime = value;
	}
	
	public java.sql.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setPreviewsImgUrl(java.lang.String value) {
		this.previewsImgUrl = value;
	}
	
	public java.lang.String getPreviewsImgUrl() {
		return this.previewsImgUrl;
	}
	public void setStateNo(java.lang.Integer value) {
		this.stateNo = value;
	}
	
	public java.lang.Integer getStateNo() {
		return this.stateNo;
	}
	public void setModelId(java.lang.String value) {
		this.modelId = value;
	}
	
	public java.lang.String getModelId() {
		return this.modelId;
	}
	public void setSortValue(java.lang.Integer value) {
		this.sortValue = value;
	}
	
	public java.lang.Integer getSortValue() {
		return this.sortValue;
	}
	public void setCommentStat(java.lang.Integer value) {
		this.commentStat = value;
	}
	
	public java.lang.Integer getCommentStat() {
		return this.commentStat;
	}
	public void setViewClass(java.lang.Integer value) {
		this.viewClass = value;
	}
	
	public java.lang.Integer getViewClass() {
		return this.viewClass;
	}
	public void setShowTime(java.lang.Integer value) {
		this.showTime = value;
	}
	
	public java.lang.Integer getShowTime() {
		return this.showTime;
	}
	public void setConentUrl(java.lang.String value) {
		this.conentUrl = value;
	}
	
	public java.lang.String getConentUrl() {
		return this.conentUrl;
	}
	public java.lang.String getKeywordsValue() {
		return keywordsValue;
	}

	public void setKeywordsValue(java.lang.String keywordsValue) {
		this.keywordsValue = keywordsValue;
	}

	public java.lang.Integer getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.Integer parentId) {
		this.parentId = parentId;
	}

	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("SortId",getSortId())
			.append("OneSortId",getOneSortId())
			.append("Title",getTitle())
			.append("TitleFull",getTitleFull())
			.append("Synopsis",getSynopsis())
			.append("Sources",getSources())
			.append("Author",getAuthor())
			.append("SubmitNam",getSubmitNam())
			.append("SubmitTime",getSubmitTime())
			.append("UpdateNam",getUpdateNam())
			.append("UpdateTime",getUpdateTime())
			.append("PreviewsImgUrl",getPreviewsImgUrl())
			.append("StateNo",getStateNo())
			.append("ModelId",getModelId())
			.append("SortValue",getSortValue())
			.append("CommentStat",getCommentStat())
			.append("ViewClass",getViewClass())
			.append("ShowTime",getShowTime())
			.append("ConentUrl",getConentUrl())
			.append("KeywordsValue",getKeywordsValue())
			.append("ParentId",getParentId())
			
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getSortId())
			.append(getOneSortId())
			.append(getTitle())
			.append(getTitleFull())
			.append(getSynopsis())
			.append(getSources())
			.append(getAuthor())
			.append(getSubmitNam())
			.append(getSubmitTime())
			.append(getUpdateNam())
			.append(getUpdateTime())
			.append(getPreviewsImgUrl())
			.append(getStateNo())
			.append(getModelId())
			.append(getSortValue())
			.append(getCommentStat())
			.append(getViewClass())
			.append(getShowTime())
			.append(getConentUrl())
			.append(getKeywordsValue())
			.append(getParentId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteContent == false) return false;
		if(this == obj) return true;
		SiteContent other = (SiteContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getSortId(),other.getSortId())
			.append(getOneSortId(),other.getOneSortId())
			.append(getTitle(),other.getTitle())
			.append(getTitleFull(),other.getTitleFull())
			.append(getSynopsis(),other.getSynopsis())
			.append(getSources(),other.getSources())
			.append(getAuthor(),other.getAuthor())
			.append(getSubmitNam(),other.getSubmitNam())
			.append(getSubmitTime(),other.getSubmitTime())
			.append(getUpdateNam(),other.getUpdateNam())
			.append(getUpdateTime(),other.getUpdateTime())
			.append(getPreviewsImgUrl(),other.getPreviewsImgUrl())
			.append(getStateNo(),other.getStateNo())
			.append(getModelId(),other.getModelId())
			.append(getSortValue(),other.getSortValue())
			.append(getCommentStat(),other.getCommentStat())
			.append(getViewClass(),other.getViewClass())
			.append(getShowTime(),other.getShowTime())
			.append(getConentUrl(),other.getConentUrl())
			.append(getKeywordsValue(),other.getKeywordsValue())
			.append(getParentId(),other.getParentId())
			.isEquals();
	}


}

