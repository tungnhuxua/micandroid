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
 * 内容列表标签(tc_tag_list_info)
 * @author 自强  zqiangzhang@gmail.com
 *
 */


public class CmsTagList extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME_ = DATE_TIME_FORMAT;
	public static final String FORMAT_MAKE_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	/**标签ID*/
	private java.lang.String id;
	/**模板ID*/ 
	private java.lang.String templetId;
	/**分类ID*/
	private java.lang.String sortId;
	/**标签名称*/
	private java.lang.String tagName;
	/***/
	private java.lang.String tagNameString;
	/***/
	private java.lang.String tagNameValue;
	/**是否分页*/
	private java.lang.String ifPage;
	/**行数*/
	private java.lang.Integer rowNums;
	/**内容入库时间*/
	private java.sql.Timestamp insertTime;
	/***/
	private java.lang.String insertTimeString;
	/**模型ID*/
	private java.lang.String modelId;
	/**内容更新时间*/
	private java.sql.Timestamp updateTime;
	/***/
	private java.lang.String updateTimeString;
	/**标签说明*/
	private java.lang.String tagExp;
	/**排序类型*/
	private java.lang.String orderType;
	/**模板方案ID*/
	private java.lang.String versionId;
	/**发布单位*/
	private java.lang.String companyId;
	/**标题样式*/
	private java.lang.String listDestroy;
	/**制作人*/
	private java.lang.String makePeople;
	/**制作时间*/
	private java.sql.Timestamp makeTime;
	/***/
	private java.lang.String makeTimeString;
 
	//columns END
	/***/
	private java.lang.String cmsModelStore;
	/***/
	private java.lang.String posStore;
	/**小说连载状态*/
	private java.lang.String continueModeNo;

	
	/*****其它属性**/
	/**模板数据类(标签对应模板)*/
	private  CmsTemplet cmsTemplet;
	/**标签中要用的LIST数据*/
	private List tagListInfo;
	
	
	
	public List getTagListInfo() {
		return tagListInfo;
	}

	public void setTagListInfo(List tagListInfo) {
		this.tagListInfo = tagListInfo;
	}

	public CmsTagList(){
	}

	public CmsTagList(
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
	public void setSortId(java.lang.String value) {
		this.sortId = value;
	}
	
	public java.lang.String getSortId() {
		return this.sortId;
	}
	public void setTagName(java.lang.String value) {
		this.tagName = value;
	}
	
	public java.lang.String getTagName() {
		return this.tagName;
	}
	public void setIfPage(java.lang.String value) {
		this.ifPage = value;
	}
	
	public java.lang.String getIfPage() {
		return this.ifPage;
	}
	public void setRowNums(java.lang.Integer value) {
		this.rowNums = value;
	}
	
	public java.lang.Integer getRowNums() {
		return this.rowNums;
	}

	public void setInsertTime(java.sql.Timestamp value) {
		this.insertTime = value;
		setInsertTimeString(date2String(value, FORMAT_INSERT_TIME_));
	}
	
	public java.sql.Timestamp getInsertTime() {
		return this.insertTime;
	}
	
	 
	public void setModelId(java.lang.String value) {
		this.modelId = value;
	}
	
	public java.lang.String getModelId() {
		return this.modelId;
	}
	
	public void setUpdateTime(java.sql.Timestamp value) {
		this.updateTime = value;
		setUpdateTimeString(date2String(value,FORMAT_UPDATE_TIME_));
	}
	
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setTagExp(java.lang.String value) {
		this.tagExp = value;
	}
	
	public java.lang.String getTagExp() {
		return this.tagExp;
	}
	public void setOrderType(java.lang.String value) {
		this.orderType = value;
	}
	
	public java.lang.String getOrderType() {
		return this.orderType;
	}
	public void setVersionId(java.lang.String value) {
		this.versionId = value;
	}
	
	public java.lang.String getVersionId() {
		return this.versionId;
	}
	public void setCompanyId(java.lang.String value) {
		this.companyId = value;
	}
	
	public java.lang.String getCompanyId() {
		return this.companyId;
	}
 
	
	public void setMakePeople(java.lang.String value) {
		this.makePeople = value;
	}
	
	public java.lang.String getMakePeople() {
		return this.makePeople;
	}
	
	public void setMakeTime(java.sql.Timestamp value) {
		this.makeTime = value;
		setMakeTimeString(date2String(value, FORMAT_MAKE_TIME_));
	}
	
	public java.sql.Timestamp getMakeTime() {
		return this.makeTime;
	}
 

	public java.lang.String getCmsModelStore() {
		return cmsModelStore;
	}

	public void setCmsModelStore(java.lang.String cmsModelStore) {
		this.cmsModelStore = cmsModelStore;
	}

	public java.lang.String getPosStore() {
		return posStore;
	}

	public void setPosStore(java.lang.String posStore) {
		this.posStore = posStore;
	}

	public java.lang.String getTagNameString() {
		return tagName;
	}

	public java.lang.String getListDestroy() {
		return listDestroy;
	}

	public void setListDestroy(java.lang.String listDestroy) {
		this.listDestroy = listDestroy;
	}

	public void setTagNameString(java.lang.String tagNameString) {
		this.tagNameString = tagNameString;
		this.tagName = tagNameString;
	}
	
	public java.lang.String getInsertTimeString() {
		return insertTimeString;
	}

	public void setInsertTimeString(java.lang.String insertTimeString) {
		this.insertTimeString = insertTimeString;
	}

	public java.lang.String getUpdateTimeString() {
		return updateTimeString;
	}

	public void setUpdateTimeString(java.lang.String updateTimeString) {
		this.updateTimeString = updateTimeString;
	}

	public java.lang.String getMakeTimeString() {
		return makeTimeString;
	}

	public void setMakeTimeString(java.lang.String makeTimeString) {
		this.makeTimeString = makeTimeString;
	}
	
	public java.lang.String getTagNameValue() {
		this.setTagNameValue("");
		return this.tagNameValue;
	}

	public void setTagNameValue(java.lang.String tagNameValue) {
		this.tagNameValue = "{listtag_"+ this.tagName+"}";
		//this.tagNameValue = "{"+com.mylink.kingtercms.system.ResourceBundleImp.getValue("contentTagPre")+ this.tagName+"}";
	}

	public java.lang.String getContinueModeNo() {
		return continueModeNo;
	}

	public void setContinueModeNo(java.lang.String continueModeNo) {
		this.continueModeNo = continueModeNo;
	}

	/******************************/
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("TempletId",getTempletId())
			.append("CategoryId",getSortId())
			.append("TagName",getTagName())
			.append("IfPage",getIfPage())
			.append("RowNum",getRowNums())
			.append("InsertTime",getInsertTime())
			.append("ListDestroy",getListDestroy())
			.append("ModelId",getModelId())
			.append("UpdateTime",getUpdateTime())
			.append("TagExp",getTagExp())
			.append("OrderType",getOrderType())
			.append("VersionId",getVersionId())
			.append("CompanyId",getCompanyId())
			.append("MakePeople",getMakePeople())
			.append("MakeTime",getMakeTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getTempletId())
			.append(getSortId())
			.append(getTagName())
			.append(getIfPage())
			.append(getRowNums())
			.append(getInsertTime())
			.append(getListDestroy())
			.append(getModelId())
			.append(getUpdateTime())
			.append(getTagExp())
			.append(getOrderType())
			.append(getVersionId())
			.append(getCompanyId())
			.append(getMakePeople())
			.append(getMakeTime())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsTagContent == false) return false;
		if(this == obj) return true;
		CmsTagContent other = (CmsTagContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getTempletId(),other.getTempletId())
			.append(getSortId(),other.getCategoryId())
			.append(getTagName(),other.getTagName())
			.append(getIfPage(),other.getIfPage())
			.append(getRowNums(),other.getRowNums())
			.append(getInsertTime(),other.getInsertTime())
			.append(getModelId(),other.getModelId())
			.append(getUpdateTime(),other.getUpdateTime())
			.append(getTagExp(),other.getTagExp())
			.append(getOrderType(),other.getOrderType())
			.append(getVersionId(),other.getVersionId())
			.append(getCompanyId(),other.getCompanyId())
			.append(getMakePeople(),other.getMakePeople())
			.append(getMakeTime(),other.getMakeTime())
			.isEquals();
	}

	public CmsTemplet getCmsTemplet() {
		return cmsTemplet;
	}

	public void setCmsTemplet(CmsTemplet cmsTemplet) {
		this.cmsTemplet = cmsTemplet;
	}



}

