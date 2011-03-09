package cn.mmbook.platform.model.base;

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

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;

/**
 * <p> BaseKeywords 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class BaseKeywords extends BaseEntity {
	
	//date formats
	
	//columns START
	/**关键字ID*/
	private java.lang.Integer id;
	/**上级关键字ID*/
	private java.lang.Integer parentId;
	/**关键值*/
	private java.lang.String keywordsValue;
	/**关键字说明*/
	private java.lang.String keywordsNotes;
	/**是否有下级节点*/
	private java.lang.Integer lowerNode;
	//columns END

	public BaseKeywords(){
	}

	public BaseKeywords(
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
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setKeywordsValue(java.lang.String value) {
		this.keywordsValue = value;
	}
	
	public java.lang.String getKeywordsValue() {
		return this.keywordsValue;
	}
	public void setKeywordsNotes(java.lang.String value) {
		this.keywordsNotes = value;
	}
	
	public java.lang.String getKeywordsNotes() {
		return this.keywordsNotes;
	}
	public void setLowerNode(java.lang.Integer value) {
		this.lowerNode = value;
	}
	
	public java.lang.Integer getLowerNode() {
		return this.lowerNode;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ParentId",getParentId())
			.append("KeywordsValue",getKeywordsValue())
			.append("KeywordsNotes",getKeywordsNotes())
			.append("LowerNode",getLowerNode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getParentId())
			.append(getKeywordsValue())
			.append(getKeywordsNotes())
			.append(getLowerNode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseKeywords == false) return false;
		if(this == obj) return true;
		BaseKeywords other = (BaseKeywords)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getParentId(),other.getParentId())
			.append(getKeywordsValue(),other.getKeywordsValue())
			.append(getKeywordsNotes(),other.getKeywordsNotes())
			.append(getLowerNode(),other.getLowerNode())
			.isEquals();
	}
}

