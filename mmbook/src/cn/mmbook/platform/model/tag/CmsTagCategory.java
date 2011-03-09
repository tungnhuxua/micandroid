package cn.mmbook.platform.model.tag;

import javacommon.base.BaseEntity;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import cn.mmbook.platform.model.manage.SitePart;

/**
 * 栏目标签  数据类
 * @author qiongguo
 *
 */
public class CmsTagCategory extends BaseEntity {
	
	//date formats
	
	//columns START
	/**标签ID*/
	private java.lang.String id;
	/**模板ID*/
	private java.lang.String templetId;
	/**栏目id*/
	private java.lang.String categoryId;
	/**标签名称*/
	private java.lang.String tagName;
	/**行数*/
	private java.math.BigDecimal rowNum;
	/**标签说明*/
	private java.lang.String tagExp;
	/**模板版本ID*/
	private java.lang.String versionId;
	
	/** qiongguo add*/
	/***/
	private java.lang.String tagNameValue;
	/***/
	private java.lang.String versionName;
	/*** tagName 参数名无法提交数据，所以 修改名称。 qiongguo */
	private java.lang.String tagNameNew;
	/***/
	private java.lang.String templetName;
	
	/**标签类别   channels频道,category栏目,sort分类*/
	private java.lang.String tagSort;
	
	//private CmsCategory cmsCategory;
	private CmsTemplet cmsTemplet;
	
	/**栏目ID结果集*/
	private String cids;
	/**标签中要用的LIST数据*/
	private List tagListInfo;
	
	
	//columns END
	
	
	
	
	
	public CmsTagCategory(){
	}

	public CmsTagCategory(
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
	public void setTempletId(java.lang.String value) {
		this.templetId = value;
	}
	
	public java.lang.String getTempletId() {
		return this.templetId;
	}
	public void setCategoryId(java.lang.String value) {
		this.categoryId = value;
	}
	
	public java.lang.String getCategoryId() {
		return this.categoryId;
	}
	public void setTagName(java.lang.String value) {
		this.tagName = value;
	}
	
	public java.lang.String getTagName() {
		return this.tagName;
	}
	public void setRowNum(java.math.BigDecimal value) {
		this.rowNum = value;
	}
	
	public java.math.BigDecimal getRowNum() {
		return this.rowNum;
	}
	public void setTagExp(java.lang.String value) {
		this.tagExp = value;
	}
	
	public java.lang.String getTagExp() {
		return this.tagExp;
	}
	public void setVersionId(java.lang.String value) {
		this.versionId = value;
	}
	
	public java.lang.String getVersionId() {
		return this.versionId;
	}

	public CmsTemplet getCmsTemplet() {
		return cmsTemplet;
	}

	public void setCmsTemplet(CmsTemplet cmsTemplet) {
		this.cmsTemplet = cmsTemplet;
	}

	public java.lang.String getVersionName() {
		this.setVersionName("");
		return this.versionName;
	}

	public void setVersionName(java.lang.String versionName) {
		if("1".equals(versionId))
		  this.versionName = "wap1.x";
		if("2".equals(versionId))
			  this.versionName = "wap2.0";
	}

	public java.lang.String getTagNameValue() {
		this.setTagNameValue("");
		return this.tagNameValue;
	}

	public void setTagNameValue(java.lang.String tagNameValue) {
		this.tagNameValue = "{catTag_"+this.tagName+"}";
		//this.tagNameValue = "{"+com.mylink.kingtercms.system.ResourceBundleImp.getValue("categoryTagPre")+ this.tagName+"}";
	}

	public java.lang.String getTagNameNew() {
		return tagNameNew;
	}

	public void setTagNameNew(java.lang.String tagNameNew) {
		this.tagNameNew = tagNameNew;
	}

	public java.lang.String getTempletName() {
		return templetName;
	}

	public void setTempletName(java.lang.String templetName) {
		this.templetName = templetName;
	}

	public String getCids() {
		return cids;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public java.lang.String getTagSort() {
		return tagSort;
	}

	public void setTagSort(java.lang.String tagSort) {
		this.tagSort = tagSort;
	}
	
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("TempletId",getTempletId())
			.append("CategoryId",getCategoryId())
			.append("TagName",getTagName())
			.append("RowNum",getRowNum())
			.append("TagExp",getTagExp())
			.append("VersionId",getVersionId())
			//.append("CmsCategory",getCmsCategory())
			.append("CmsTemplet",getCmsTemplet())
			.append("VersionName",getVersionName())
			.append("TagNameValue",getTagNameValue())
			.append("TagNameNew",getTagNameNew())
			.append("TempletName",getTempletName())
			.append("Cids",getCids())
			.append("TagSort",getTagSort())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getTempletId())
			.append(getCategoryId())
			.append(getTagName())
			.append(getRowNum())
			.append(getTagExp())
			.append(getVersionId())
			//.append(getCmsCategory())
			.append(getCmsTemplet())
			.append(getVersionName())
			.append(getTagNameValue())
			.append(getTagNameNew())
			.append(getTempletName())
			.append(getCids())
			.append(getTagSort())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTagCategory == false) return false;
		if(this == obj) return true;
		CmsTagCategory other = (CmsTagCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getTempletId(),other.getTempletId())
			.append(getCategoryId(),other.getCategoryId())
			.append(getTagName(),other.getTagName())
			.append(getRowNum(),other.getRowNum())
			.append(getTagExp(),other.getTagExp())
			.append(getVersionId(),other.getVersionId())
			//.append(getCmsCategory(),other.getCmsCategory())
			.append(getCmsTemplet(),other.getCmsTemplet())
			.append(getVersionName(),other.getVersionName())
			.append(getTagNameValue(),other.getTagNameValue())
			.append(getTagNameNew(),other.getTagNameNew())
			.append(getTempletName(),other.getTempletName())
			.append(getCids(),other.getCids())
			.append(getTagSort(),other.getTagSort())
			.isEquals();
	}

	public List getTagListInfo() {
		return tagListInfo;
	}

	public void setTagListInfo(List tagListInfo) {
		this.tagListInfo = tagListInfo;
	}

//	public CmsCategory getCmsCategory() {
//		return cmsCategory;
//	}
//
//	public void setCmsCategory(CmsCategory cmsCategory) {
//		this.cmsCategory = cmsCategory;
//	}


}

