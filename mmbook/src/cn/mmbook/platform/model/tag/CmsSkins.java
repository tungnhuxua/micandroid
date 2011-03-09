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
 * 皮肤(风格\主题) 数据类 
 * @author 自强  zqiangzhang@gmail.com
 *
 */
public class CmsSkins extends BaseEntity {
	
	//date formats
	
	//columns START
	private java.lang.String id;
	private java.lang.String versionId;
	private java.lang.String skinName;
	private java.lang.String skinDir;
	/** 设为默认 1:是,2:否 */
	private java.lang.String isdefault;
	private java.lang.String isdfValue;
	
	/**模板版本与皮肤组成的模板URL如: web/default (版本URL+皮肤URL)*/
	private java.lang.String templetPath;
	
	private CmsVersion cmsVersion;
	//columns END
	
	public CmsSkins(){
	}

	public CmsSkins(
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
	public void setVersionId(java.lang.String value) {
		this.versionId = value;
	}
	
	public java.lang.String getVersionId() {
		return this.versionId;
	}
	public void setSkinName(java.lang.String value) {
		this.skinName = value;
	}
	
	public java.lang.String getSkinName() {
		return this.skinName;
	}
	public void setSkinDir(java.lang.String value) {
		this.skinDir = value;
	}
	
	public java.lang.String getSkinDir() {
		return this.skinDir;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("VersionId",getVersionId())
			.append("SkinName",getSkinName())
			.append("SkinDir",getSkinDir())
			.append("isdefault",getIsdefault())
			.append("CmsVersion",getCmsVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getVersionId())
			.append(getSkinName())
			.append(getSkinDir())
			.append(getIsdefault())
			.append(getCmsVersion())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CmsSkins == false) return false;
		if(this == obj) return true;
		CmsSkins other = (CmsSkins)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getVersionId(),other.getVersionId())
			.append(getSkinName(),other.getSkinName())
			.append(getSkinDir(),other.getSkinDir())
			.append(getIsdefault(),other.getIsdefault())
			.append("CmsVersion",other.getCmsVersion())
			.isEquals();
	}

	public CmsVersion getCmsVersion() {
		return cmsVersion;
	}

	public void setCmsVersion(CmsVersion cmsVersion) {
		this.cmsVersion = cmsVersion;
	}

	public java.lang.String getIsdefault() {
		return (null==this.isdefault) ? "0" : this.isdefault ;
	}

	public void setIsdefault(java.lang.String isdefault) {
		this.isdefault = (null==isdefault)? "0" : isdefault;
	}

	public java.lang.String getIsdfValue() {
		setIsdfValue(this.isdefault);
		return this.isdfValue;
	}

	public void setIsdfValue(java.lang.String isdfValue) {
		if("0".equals(isdefault)){
			this.isdfValue="否";
		}else{
			this.isdfValue="是";
		}
	}

	public java.lang.String getTempletPath() {
		return templetPath;
	}

	public void setTempletPath(java.lang.String templetPath) {
		this.templetPath = templetPath;
	}
}

