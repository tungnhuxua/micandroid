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
 * <p> SitePicNote 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SitePicNote extends BaseEntity {
	
	//date formats
	
	//columns START
	/**图片ID*/
	private java.lang.String id;
	/**内容ID*/
	private java.lang.String contentId;
	/**图片小标题*/
	private java.lang.String picTitle;
	/**图片格式*/
	private java.lang.String picFormat;
	/**图片类型*/
	private java.lang.Integer picTypes;
	//columns END

	/**关联网站内容*/
	private SiteContent siteContent;
	
	public SiteContent getSiteContent() {
		return siteContent;
	}

	public void setSiteContent(SiteContent siteContent) {
		this.siteContent = siteContent;
	}

	public SitePicNote(){
	}

	public SitePicNote(
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
	public void setContentId(java.lang.String value) {
		this.contentId = value;
	}
	
	public java.lang.String getContentId() {
		return this.contentId;
	}
	public void setPicTitle(java.lang.String value) {
		this.picTitle = value;
	}
	
	public java.lang.String getPicTitle() {
		return this.picTitle;
	}
	public void setPicFormat(java.lang.String value) {
		this.picFormat = value;
	}
	
	public java.lang.String getPicFormat() {
		return this.picFormat;
	}
	public void setPicTypes(java.lang.Integer value) {
		this.picTypes = value;
	}
	
	public java.lang.Integer getPicTypes() {
		return this.picTypes;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ContentId",getContentId())
			.append("PicTitle",getPicTitle())
			.append("PicFormat",getPicFormat())
			.append("PicTypes",getPicTypes())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getContentId())
			.append(getPicTitle())
			.append(getPicFormat())
			.append(getPicTypes())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SitePicNote == false) return false;
		if(this == obj) return true;
		SitePicNote other = (SitePicNote)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getContentId(),other.getContentId())
			.append(getPicTitle(),other.getPicTitle())
			.append(getPicFormat(),other.getPicFormat())
			.append(getPicTypes(),other.getPicTypes())
			.isEquals();
	}
}

