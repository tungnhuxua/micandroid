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
 * <p> SiteContentSort 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteContentSort extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	private java.lang.Integer id;
	/**网站内容分类名称*/
	private java.lang.String classifyName;
	/**是否有下级节点*/
	private java.lang.Integer lowerNode;
	/**上级节点ID*/
	private java.lang.Integer parentId;
	/**等级*/
	private java.lang.Integer classifyGrade;
	/**排序值*/
	private java.lang.Integer sortValue;
	/**是否有效:0 无效．1 有效*/
	private java.lang.Integer effective;
	/**分类性质*/
	private java.lang.Integer classifyNature;
	/**备注*/
	private java.lang.String classifyNotes;
	/**新增时间*/
	private java.sql.Date insertTime;
	/**新增属性*/
	/**模型ID*/
	private java.lang.String modelId;
	/**分类别名*/
	private java.lang.String sortByname;
	/**LogoUrl*/
	private java.lang.String logoUrl;
	//columns END
	
	/*********************/
	
	/**网站内容分类与栏目关联ID集合*/
	private java.lang.String sitePartIds;
	private SitePart sitePart;

	/**标签里读取的名称*/
	private java.lang.String name;
	
	
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public SitePart getSitePart() {
		return sitePart;
	}

	public void setSitePart(SitePart sitePart) {
		this.sitePart = sitePart;
	}

	public SiteContentSort(){
	}

	public SiteContentSort(
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
	public void setClassifyName(java.lang.String value) {
		this.classifyName = value;
	}
	
	public java.lang.String getClassifyName() {
		return this.classifyName;
	}
	public void setLowerNode(java.lang.Integer value) {
		this.lowerNode = value;
	}
	
	public java.lang.Integer getLowerNode() {
		return this.lowerNode;
	}
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setClassifyGrade(java.lang.Integer value) {
		this.classifyGrade = value;
	}
	
	public java.lang.Integer getClassifyGrade() {
		return this.classifyGrade;
	}
	public void setSortValue(java.lang.Integer value) {
		this.sortValue = value;
	}
	
	public java.lang.Integer getSortValue() {
		return this.sortValue;
	}
	public void setEffective(java.lang.Integer value) {
		this.effective = value;
	}
	
	public java.lang.Integer getEffective() {
		return this.effective;
	}
	public void setClassifyNature(java.lang.Integer value) {
		this.classifyNature = value;
	}
	
	public java.lang.Integer getClassifyNature() {
		return this.classifyNature;
	}
	public void setClassifyNotes(java.lang.String value) {
		this.classifyNotes = value;
	}
	
	public java.lang.String getClassifyNotes() {
		return this.classifyNotes;
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


	public java.lang.String getSitePartIds() {
		return sitePartIds;
	}

	public void setSitePartIds(java.lang.String sitePartIds) {
		this.sitePartIds = sitePartIds;
	}

	public java.lang.String getModelId() {
		return modelId;
	}

	public void setModelId(java.lang.String modelId) {
		this.modelId = modelId;
	}

	public java.lang.String getSortByname() {
		return sortByname;
	}

	public void setSortByname(java.lang.String sortByname) {
		this.sortByname = sortByname;
	}

	public java.lang.String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(java.lang.String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	public String toString() {
		System.out.println("toString");
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ClassifyName",getClassifyName())
			.append("LowerNode",getLowerNode())
			.append("ParentId",getParentId())
			.append("ClassifyGrade",getClassifyGrade())
			.append("SortValue",getSortValue())
			.append("Effective",getEffective())
			.append("ClassifyNature",getClassifyNature())
			.append("ClassifyNotes",getClassifyNotes())
			.append("InsertTime",getInsertTime())
			.append("SitePartIds",getSitePartIds())
			.append("ModelId",getModelId())
			.append("SortByname",getSortByname())
			.append("LogoUrl",getLogoUrl())
			.toString();
	}
	
	public int hashCode() {
		System.out.println("hashCode");
		System.out.println("getParentId()="+getParentId());
		return new HashCodeBuilder()
			.append(getId())
			.append(getClassifyName())
			.append(getLowerNode())
			.append(getParentId())
			.append(getClassifyGrade())
			.append(getSortValue())
			.append(getEffective())
			.append(getClassifyNature())
			.append(getClassifyNotes())
			.append(getInsertTime())
			.append(getSitePartIds())
			.append(getModelId())
			.append(getSortByname())
			.append(getLogoUrl())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		System.out.println("equals");
		if(obj instanceof SiteContentSort == false) return false;
		if(this == obj) return true;
		SiteContentSort other = (SiteContentSort)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getClassifyName(),other.getClassifyName())
			.append(getLowerNode(),other.getLowerNode())
			.append(getParentId(),other.getParentId())
			.append(getClassifyGrade(),other.getClassifyGrade())
			.append(getSortValue(),other.getSortValue())
			.append(getEffective(),other.getEffective())
			.append(getClassifyNature(),other.getClassifyNature())
			.append(getClassifyNotes(),other.getClassifyNotes())
			.append(getInsertTime(),other.getInsertTime())
			.append(getSitePartIds(),other.getSitePartIds())
			.append(getModelId(),other.getModelId())
			.append(getSortByname(),other.getSortByname())
			.append(getLogoUrl(),other.getLogoUrl())
			.isEquals();
	}



}

