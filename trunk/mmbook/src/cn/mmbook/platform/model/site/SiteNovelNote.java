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
 * <p> SiteNovelNote 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteNovelNote extends BaseEntity {
	
	//date formats
	
	//columns START
	/**小说ID*/
	private java.lang.String id;
	/**内容ID*/
	private java.lang.String contentId;
	/**章节数*/
	private java.lang.Integer chapterCount;
	/**连载状态*/
	private java.lang.Integer serialstoryStat;
	/**小说作者信息*/
	private java.lang.String authorNote;
	/**总字数*/
	private java.lang.Integer wordCount;
	//columns END
	/**内容基本类*/
	private SiteContent siteContent;

	public SiteNovelNote(){
	}

	public SiteNovelNote(
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
	public void setChapterCount(java.lang.Integer value) {
		this.chapterCount = value;
	}
	
	public java.lang.Integer getChapterCount() {
		return this.chapterCount;
	}
	public void setSerialstoryStat(java.lang.Integer value) {
		this.serialstoryStat = value;
	}
	
	public java.lang.Integer getSerialstoryStat() {
		return this.serialstoryStat;
	}
	public void setAuthorNote(java.lang.String value) {
		this.authorNote = value;
	}
	
	public java.lang.String getAuthorNote() {
		return this.authorNote;
	}
	public void setWordCount(java.lang.Integer value) {
		this.wordCount = value;
	}
	
	public java.lang.Integer getWordCount() {
		return this.wordCount;
	}
	
	public SiteContent getSiteContent() {
		return siteContent;
	}

	public void setSiteContent(SiteContent siteContent) {
		this.siteContent = siteContent;
	}
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ContentId",getContentId())
			.append("ChapterCount",getChapterCount())
			.append("SerialstoryStat",getSerialstoryStat())
			.append("AuthorNote",getAuthorNote())
			.append("WordCount",getWordCount())
			.append("SiteContent",getSiteContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getContentId())
			.append(getChapterCount())
			.append(getSerialstoryStat())
			.append(getAuthorNote())
			.append(getWordCount())
			.append(getSiteContent())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteNovelNote == false) return false;
		if(this == obj) return true;
		SiteNovelNote other = (SiteNovelNote)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getContentId(),other.getContentId())
			.append(getChapterCount(),other.getChapterCount())
			.append(getSerialstoryStat(),other.getSerialstoryStat())
			.append(getAuthorNote(),other.getAuthorNote())
			.append(getWordCount(),other.getWordCount())
			.append(getSiteContent(),other.getSiteContent())
			.isEquals();
	}
}

