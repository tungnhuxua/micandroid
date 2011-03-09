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
 * <p> ContentContentReal 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class ContentContentReal extends BaseEntity {
	
	//date formats
	
	//columns START
	/**内容与内容关联ID*/
	private java.lang.Integer id;
	/**主内容ID*/
	private java.lang.String mainContentId;
	/**关联内容ID*/
	private java.lang.String relatingContentId;
	//columns END

	public ContentContentReal(){
	}

	public ContentContentReal(
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
	public void setMainContentId(java.lang.String value) {
		this.mainContentId = value;
	}
	
	public java.lang.String getMainContentId() {
		return this.mainContentId;
	}
	public void setRelatingContentId(java.lang.String value) {
		this.relatingContentId = value;
	}
	
	public java.lang.String getRelatingContentId() {
		return this.relatingContentId;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("MainContentId",getMainContentId())
			.append("RelatingContentId",getRelatingContentId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getMainContentId())
			.append(getRelatingContentId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ContentContentReal == false) return false;
		if(this == obj) return true;
		ContentContentReal other = (ContentContentReal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getMainContentId(),other.getMainContentId())
			.append(getRelatingContentId(),other.getRelatingContentId())
			.isEquals();
	}
}

