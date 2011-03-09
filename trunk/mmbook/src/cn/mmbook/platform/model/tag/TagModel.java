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

import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.impl.*;
import cn.mmbook.platform.service.tag.*;

/**
 * <p> TagModel 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class TagModel extends BaseEntity {
	
	//date formats
	
	//columns START
	/**模型ID*/
	private java.lang.String id;
	/**模型名称*/
	private java.lang.String modelName;
	/**模型说明*/
	private java.lang.String modelNotes;
	/**模型访问URL*/
	private java.lang.String aurl;
	/**模型模板URL*/
	private java.lang.String modelUrl;
	/**是否默认模型*/
	private java.lang.Integer isDef;
	/**是否有效*/
	private java.lang.Integer effective;
	//columns END

 
	
	public TagModel(){
	}

	public TagModel( String id ){
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
	public void setModelNotes(java.lang.String value) {
		this.modelNotes = value;
	}
	
	public java.lang.String getModelNotes() {
		return this.modelNotes;
	}
	public void setAurl(java.lang.String value) {
		this.aurl = value;
	}
	
	public java.lang.String getAurl() {
		return this.aurl;
	}
	public void setModelUrl(java.lang.String value) {
		this.modelUrl = value;
	}
	
	public java.lang.String getModelUrl() {
		return this.modelUrl;
	}
	public void setIsDef(java.lang.Integer value) {
		this.isDef = value;
	}
	
	public java.lang.Integer getIsDef() {
		return this.isDef;
	}
	public void setEffective(java.lang.Integer value) {
		this.effective = value;
	}
	
	public java.lang.Integer getEffective() {
		return this.effective;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ModelName",getModelName())
			.append("ModelNotes",getModelNotes())
			.append("Aurl",getAurl())
			.append("ModelUrl",getModelUrl())
			.append("IsDef",getIsDef())
			.append("Effective",getEffective())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getModelName())
			.append(getModelNotes())
			.append(getAurl())
			.append(getModelUrl())
			.append(getIsDef())
			.append(getEffective())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TagModel == false) return false;
		if(this == obj) return true;
		TagModel other = (TagModel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getModelName(),other.getModelName())
			.append(getModelNotes(),other.getModelNotes())
			.append(getAurl(),other.getAurl())
			.append(getModelUrl(),other.getModelUrl())
			.append(getIsDef(),other.getIsDef())
			.append(getEffective(),other.getEffective())
			.isEquals();
	}
}

