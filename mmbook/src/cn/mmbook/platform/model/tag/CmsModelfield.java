package cn.mmbook.platform.model.tag;

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


/**
 * 
 * @author Administrator
 *
 */

public class CmsModelfield extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.String id;
	private java.lang.String modelId;
	private java.lang.String fieldName;
	private java.lang.String fieldRemark;
	private java.lang.String isselect;
	private java.lang.String tableName;
	//columns END

	public CmsModelfield(){
	}

	public CmsModelfield(
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
	public void setModelId(java.lang.String value) {
		this.modelId = value;
	}
	
	public java.lang.String getModelId() {
		return this.modelId;
	}
	public void setFieldName(java.lang.String value) {
		this.fieldName = value;
	}
	
	public java.lang.String getFieldName() {
		return this.fieldName;
	}
	public void setFieldRemark(java.lang.String value) {
		this.fieldRemark = value;
	}
	
	public java.lang.String getFieldRemark() {
		return this.fieldRemark;
	}
	public void setIsselect(java.lang.String value) {
		this.isselect = value;
	}
	
	public java.lang.String getIsselect() {
		return this.isselect;
	}
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}
	
	public java.lang.String getTableName() {
		return this.tableName;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ModelId",getModelId())
			.append("FieldName",getFieldName())
			.append("FieldRemark",getFieldRemark())
			.append("Isselect",getIsselect())
			.append("TableName",getTableName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getModelId())
			.append(getFieldName())
			.append(getFieldRemark())
			.append(getIsselect())
			.append(getTableName())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsModelfield == false) return false;
		if(this == obj) return true;
		CmsModelfield other = (CmsModelfield)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelId(),other.getModelId())
			.append(getFieldName(),other.getFieldName())
			.append(getFieldRemark(),other.getFieldRemark())
			.append(getIsselect(),other.getIsselect())
			.append(getTableName(),other.getTableName())
			.isEquals();
	}
}

