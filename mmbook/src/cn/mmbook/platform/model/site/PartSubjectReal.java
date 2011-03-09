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
 * <p> PartSubjectReal 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class PartSubjectReal extends BaseEntity {
	
	//date formats
	
	//columns START
	/**栏目与主题关联id*/
	private java.lang.Integer id;
	/**主题ID*/
	private java.lang.Integer subjectId;
	/**栏目ID*/
	private java.lang.Integer partId;
	//columns END

	public PartSubjectReal(){
	}

	public PartSubjectReal(
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
	public void setSubjectId(java.lang.Integer value) {
		this.subjectId = value;
	}
	
	public java.lang.Integer getSubjectId() {
		return this.subjectId;
	}
	public void setPartId(java.lang.Integer value) {
		this.partId = value;
	}
	
	public java.lang.Integer getPartId() {
		return this.partId;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("SubjectId",getSubjectId())
			.append("PartId",getPartId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getSubjectId())
			.append(getPartId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PartSubjectReal == false) return false;
		if(this == obj) return true;
		PartSubjectReal other = (PartSubjectReal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getSubjectId(),other.getSubjectId())
			.append(getPartId(),other.getPartId())
			.isEquals();
	}
}

