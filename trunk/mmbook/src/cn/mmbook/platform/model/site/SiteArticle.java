package cn.mmbook.platform.model.site;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import cn.mmbook.platform.model.base.BaseAccessories;

/**
 * <p> SiteArticle 文章\资讯\短文 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-08-18
 *
 */

public class SiteArticle extends BaseEntity {
	
	//date formats
	
	//columns START
	/**资讯文章内容id*/
	private java.lang.String id;
	/**内容ID*/
	private java.lang.String contentId;
	//columns END
	/**文章\资讯\短文 内容*/
	private java.lang.String fileContent;
	/**内容基本类*/
	private SiteContent siteContent;
	/**附件信息*/
	private BaseAccessories baseAccessories;
	
	/**查询 起止时间条件*/
	private java.sql.Date beginTime;
	private java.sql.Date endTime;
	
	public SiteContent getSiteContent() {
		return this.siteContent;
	}

	public void setSiteContent(SiteContent siteContent) {
		this.siteContent = siteContent;
	}

	public SiteArticle(){
	}

	public SiteArticle(
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
	
	public java.lang.String getFileContent() {
		return fileContent;
	}

	public void setFileContent(java.lang.String fileContent) {
		this.fileContent = fileContent;
	}
	public java.sql.Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(java.sql.Date beginTime) {
		this.beginTime = beginTime;
	}

	public java.sql.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}	
	public BaseAccessories getBaseAccessories() {
		return baseAccessories;
	}

	public void setBaseAccessories(BaseAccessories baseAccessories) {
		this.baseAccessories = baseAccessories;
	}


	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("ContentId",getContentId())
			.append("SiteContent",getSiteContent())
			.append("FileContent",getFileContent())
			.append("EndTime",getEndTime())
			.append("BeginTime",getBeginTime())
			.append("BaseAccessories",getBaseAccessories())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getContentId())
			.append(getSiteContent())
			.append(getFileContent())
			.append(getEndTime())
			.append(getBeginTime())
			.append(getBaseAccessories())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteArticle == false) return false;
		if(this == obj) return true;
		SiteArticle other = (SiteArticle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getContentId(),other.getContentId())
			.append(getSiteContent(),other.getSiteContent())
			.append(getFileContent(),other.getFileContent())
			.append(getEndTime(),other.getEndTime())
			.append(getBeginTime(),other.getBeginTime())
			.append(getBaseAccessories(),other.getBaseAccessories())
			.isEquals();
	}



}

