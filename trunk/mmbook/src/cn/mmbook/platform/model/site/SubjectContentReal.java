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
 * <p> SubjectContentReal 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SubjectContentReal extends BaseEntity {
	
	//date formats
	
	//columns START
	/**主题与内容关联id*/
	private java.lang.Integer id;
	/**网站内容ID*/
	private java.lang.String contentId;
	/**主题ID*/
	private java.lang.Integer subjectId;
	//columns END

	public SubjectContentReal(){
	}

	public SubjectContentReal(
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
	public void setContentId(java.lang.String value) {
		this.contentId = value;
	}
	
	public java.lang.String getContentId() {
		return this.contentId;
	}
	public void setSubjectId(java.lang.Integer value) {
		this.subjectId = value;
	}
	
	public java.lang.Integer getSubjectId() {
		return this.subjectId;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ContentId",getContentId())
			.append("SubjectId",getSubjectId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getContentId())
			.append(getSubjectId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SubjectContentReal == false) return false;
		if(this == obj) return true;
		SubjectContentReal other = (SubjectContentReal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getContentId(),other.getContentId())
			.append(getSubjectId(),other.getSubjectId())
			.isEquals();
	}
}

