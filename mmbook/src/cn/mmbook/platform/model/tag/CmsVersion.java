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
 * 
 * @author 自强  zqiangzhang@gmail.com
 *
 */
public class CmsVersion extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.String id;
	private java.lang.String versionName;
	private java.lang.String versionDir;
	//columns END

	public CmsVersion(){
	}

	public CmsVersion(
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
	public void setVersionName(java.lang.String value) {
		this.versionName = value;
	}
	
	public java.lang.String getVersionName() {
		return this.versionName;
	}
	public void setVersionDir(java.lang.String value) {
		this.versionDir = value;
	}
	
	public java.lang.String getVersionDir() {
		return this.versionDir;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("VersionName",getVersionName())
			.append("VersionDir",getVersionDir())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getVersionName())
			.append(getVersionDir())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsVersion == false) return false;
		if(this == obj) return true;
		CmsVersion other = (CmsVersion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getVersionName(),other.getVersionName())
			.append(getVersionDir(),other.getVersionDir())
			.isEquals();
	}
}

