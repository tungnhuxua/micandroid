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
 * <p> SitePartContentReal 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SitePartContentReal extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.Integer id;
	private java.lang.Integer partId;
	private java.lang.Integer sortId;
	//columns END

	public SitePartContentReal(){
	}

	public SitePartContentReal(
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
	public void setPartId(java.lang.Integer value) {
		this.partId = value;
	}
	
	public java.lang.Integer getPartId() {
		return this.partId;
	}
	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}
	
	public java.lang.Integer getSortId() {
		return this.sortId;
	}
	
	 
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("PartId",getPartId())
			.append("SortId",getSortId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getPartId())
			.append(getSortId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SitePartContentReal == false) return false;
		if(this == obj) return true;
		SitePartContentReal other = (SitePartContentReal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getPartId(),other.getPartId())
			.append(getSortId(),other.getSortId())
			.isEquals();
	}
}

