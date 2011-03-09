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
 * <p> SiteMusic 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteMusic extends BaseEntity {
	
	//date formats
	
	//columns START
	/**音乐ID*/
	private java.lang.String id;
	/**内容ID*/
	private java.lang.String conentId;
	/**歌手名*/
	private java.lang.String singer;
	/**歌手地域*/
	private java.lang.Integer musicSection;
	/**音乐格式*/
	private java.lang.String musicFormat;
	/**音乐风格*/
	private java.lang.Integer musicStyle;
	//columns END

	/**内容基本类*/
	private SiteContent siteContent;
	
	public SiteContent getSiteContent() {
		return siteContent;
	}

	public void setSiteContent(SiteContent siteContent) {
		this.siteContent = siteContent;
	}

	public SiteMusic(){
	}

	public SiteMusic(
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
	public void setConentId(java.lang.String value) {
		this.conentId = value;
	}
	
	public java.lang.String getConentId() {
		return this.conentId;
	}
	public void setSinger(java.lang.String value) {
		this.singer = value;
	}
	
	public java.lang.String getSinger() {
		return this.singer;
	}
	public void setMusicSection(java.lang.Integer value) {
		this.musicSection = value;
	}
	
	public java.lang.Integer getMusicSection() {
		return this.musicSection;
	}
	public void setMusicFormat(java.lang.String value) {
		this.musicFormat = value;
	}
	
	public java.lang.String getMusicFormat() {
		return this.musicFormat;
	}
	public void setMusicStyle(java.lang.Integer value) {
		this.musicStyle = value;
	}
	
	public java.lang.Integer getMusicStyle() {
		return this.musicStyle;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ConentId",getConentId())
			.append("Singer",getSinger())
			.append("MusicSection",getMusicSection())
			.append("MusicFormat",getMusicFormat())
			.append("MusicStyle",getMusicStyle())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getConentId())
			.append(getSinger())
			.append(getMusicSection())
			.append(getMusicFormat())
			.append(getMusicStyle())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteMusic == false) return false;
		if(this == obj) return true;
		SiteMusic other = (SiteMusic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getConentId(),other.getConentId())
			.append(getSinger(),other.getSinger())
			.append(getMusicSection(),other.getMusicSection())
			.append(getMusicFormat(),other.getMusicFormat())
			.append(getMusicStyle(),other.getMusicStyle())
			.isEquals();
	}
}

