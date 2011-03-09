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
 * <p> SiteSubject 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteSubject extends BaseEntity {
	
	//date formats
	
	//columns START
	/**主题ID*/
	private java.lang.Integer id;
	/**模型ID*/
	private java.lang.String modelId;
	/**主题名称*/
	private java.lang.String subjectName;
	/**主题完整名称*/
	private java.lang.String fullName;
	/**主题说明*/
	private java.lang.String subjectNotes;
	//columns END

	public SiteSubject(){
	}

	public SiteSubject(
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
	public void setModelId(java.lang.String value) {
		this.modelId = value;
	}
	
	public java.lang.String getModelId() {
		return this.modelId;
	}
	public void setSubjectName(java.lang.String value) {
		this.subjectName = value;
	}
	
	public java.lang.String getSubjectName() {
		return this.subjectName;
	}
	public void setFullName(java.lang.String value) {
		this.fullName = value;
	}
	
	public java.lang.String getFullName() {
		return this.fullName;
	}
	public void setSubjectNotes(java.lang.String value) {
		this.subjectNotes = value;
	}
	
	public java.lang.String getSubjectNotes() {
		return this.subjectNotes;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ModelId",getModelId())
			.append("SubjectName",getSubjectName())
			.append("FullName",getFullName())
			.append("SubjectNotes",getSubjectNotes())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getModelId())
			.append(getSubjectName())
			.append(getFullName())
			.append(getSubjectNotes())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteSubject == false) return false;
		if(this == obj) return true;
		SiteSubject other = (SiteSubject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelId(),other.getModelId())
			.append(getSubjectName(),other.getSubjectName())
			.append(getFullName(),other.getFullName())
			.append(getSubjectNotes(),other.getSubjectNotes())
			.isEquals();
	}
}

