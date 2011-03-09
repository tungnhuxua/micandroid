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
 * <p> BasicParameters 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class BasicParameters extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.Integer id;
	private java.lang.String parametersName;
	private java.lang.String parametersValue;
	private java.lang.String parametersTag;
	private java.lang.Integer parametersType;
	private java.lang.String parametersNote;
	//columns END
	/**类型参数:1,系统参数;2,平台参数*/
	private String typeValue;
	
	
	public BasicParameters(){
	}

	public BasicParameters(
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
	public void setParametersName(java.lang.String value) {
		this.parametersName = value;
	}
	
	public java.lang.String getParametersName() {
		return this.parametersName;
	}
	public void setParametersValue(java.lang.String value) {
		this.parametersValue = value;
	}
	
	public java.lang.String getParametersValue() {
		return this.parametersValue;
	}
	public void setParametersTag(java.lang.String value) {
		this.parametersTag = value;
	}
	
	public java.lang.String getParametersTag() {
		return this.parametersTag;
	}
	public void setParametersType(java.lang.Integer value) {
		if(null==value) value = 0;
		this.parametersType = value;
	}
	
	public java.lang.Integer getParametersType() {
		if(null==parametersType)
			return 0;
		else 
		return this.parametersType;
	}
	public void setParametersNote(java.lang.String value) {
		this.parametersNote = value;
	}
	
	public java.lang.String getParametersNote() {
		return this.parametersNote;
	}
	
	 
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ParametersName",getParametersName())
			.append("ParametersValue",getParametersValue())
			.append("ParametersTag",getParametersTag())
			.append("ParametersType",getParametersType())
			.append("ParametersNote",getParametersNote())
			.append("TypeValue",getTypeValue())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getParametersName())
			.append(getParametersValue())
			.append(getParametersTag())
			.append(getParametersType())
			.append(getParametersNote())
			.append(getTypeValue())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasicParameters == false) return false;
		if(this == obj) return true;
		BasicParameters other = (BasicParameters)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getParametersName(),other.getParametersName())
			.append(getParametersValue(),other.getParametersValue())
			.append(getParametersTag(),other.getParametersTag())
			.append(getParametersType(),other.getParametersType())
			.append(getParametersNote(),other.getParametersNote())
			.append(getTypeValue(),other.getTypeValue())
			.isEquals();
	}
	
	public String getTypeValue() {
		//类型参数:1,系统参数;2,平台参数
		if(parametersType==1){
			this.typeValue="系统参数";
		}else if(parametersType==2){
			this.typeValue="平台参数";
		}else{
			this.typeValue="其它参数";
		}
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
}

