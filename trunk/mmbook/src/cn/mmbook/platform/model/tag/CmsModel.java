package cn.mmbook.platform.model.tag;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import java.util.*;
import javacommon.base.*;
import javacommon.util.*;


/**
 * 
 * @author Administrator
 *
 */


public class CmsModel extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.String id;
	private java.lang.String modelName;
	private java.lang.String depict;
	private java.lang.String dealClass;
	private java.lang.String dealObject;
	//columns END

	public CmsModel(){
	}

	public CmsModel(
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
	public void setModelName(java.lang.String value) {
		this.modelName = value;
	}
	
	public java.lang.String getModelName() {
		return this.modelName;
	}
	public void setDepict(java.lang.String value) {
		this.depict = value;
	}
	
	public java.lang.String getDepict() {
		return this.depict;
	}
	public void setDealClass(java.lang.String value) {
		this.dealClass = value;
	}
	
	public java.lang.String getDealClass() {
		return this.dealClass;
	}
	public void setDealObject(java.lang.String value) {
		this.dealObject = value;
	}
	
	public java.lang.String getDealObject() {
		return this.dealObject;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ModelName",getModelName())
			.append("Depict",getDepict())
			.append("DealClass",getDealClass())
			.append("DealObject",getDealObject())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getModelName())
			.append(getDepict())
			.append(getDealClass())
			.append(getDealObject())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsModel == false) return false;
		if(this == obj) return true;
		CmsModel other = (CmsModel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelName(),other.getModelName())
			.append(getDepict(),other.getDepict())
			.append(getDealClass(),other.getDealClass())
			.append(getDealObject(),other.getDealObject())
			.isEquals();
	}
}

