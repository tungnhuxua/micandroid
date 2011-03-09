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
 * <p> SiteInfomation 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteInfomation extends BaseEntity {
	
	//date formats
	
	//columns START
	/**id*/
	private java.lang.Integer id;
	/**网站名称*/
	private java.lang.String siteName;
	/**配置ID*/
	private java.lang.Integer configId;
	/**上传ftp*/
	private java.lang.Integer ftpUploadId;
	/**域名*/
	private java.lang.String domain;
	/**路径*/
	private java.lang.String path;
	/**网站简称*/
	private java.lang.String shortName;
	/**当前系统*/
	private java.lang.String currentSystem;
	/**静态页面存在URL*/
	private java.lang.String staticDir;
	/**后台本地化*/
	private java.lang.String localeAdmin;
	/**前台本地化*/
	private java.lang.String localeFront;
	/**是否使用相对路径*/
	private java.lang.Integer isRelativePath;
	/**模板版本*/
	private java.lang.String tplSolution;
	/**动态页后缀*/
	private java.lang.String dynamicSuffix;
	/**静态页后缀*/
	private java.lang.String staticSuffix;
	/**协议*/
	private java.lang.String protocol;
	/**是否开启静态化*/
	private java.lang.Integer isStaticOn;
	/**域名别名*/
	private java.lang.String domainAlias;
	/**域名重定向*/
	private java.lang.String domainRedirect;
	/**终审级别*/
	private java.lang.Integer finalStep;
	/**审核后*/
	private java.lang.Integer afterCheck;
	/**网站LOGO*/
	private java.lang.String logoUrl;
	//columns END

	public SiteInfomation(){
	}

	public SiteInfomation(
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
	public void setSiteName(java.lang.String value) {
		this.siteName = value;
	}
	
	public java.lang.String getSiteName() {
		return this.siteName;
	}
	public void setConfigId(java.lang.Integer value) {
		this.configId = value;
	}
	
	public java.lang.Integer getConfigId() {
		return this.configId;
	}
	public void setFtpUploadId(java.lang.Integer value) {
		this.ftpUploadId = value;
	}
	
	public java.lang.Integer getFtpUploadId() {
		return this.ftpUploadId;
	}
	public void setDomain(java.lang.String value) {
		this.domain = value;
	}
	
	public java.lang.String getDomain() {
		return this.domain;
	}
	public void setPath(java.lang.String value) {
		this.path = value;
	}
	
	public java.lang.String getPath() {
		return this.path;
	}
	public void setShortName(java.lang.String value) {
		this.shortName = value;
	}
	
	public java.lang.String getShortName() {
		return this.shortName;
	}
	public void setCurrentSystem(java.lang.String value) {
		this.currentSystem = value;
	}
	
	public java.lang.String getCurrentSystem() {
		return this.currentSystem;
	}
	public void setStaticDir(java.lang.String value) {
		this.staticDir = value;
	}
	
	public java.lang.String getStaticDir() {
		return this.staticDir;
	}
	public void setLocaleAdmin(java.lang.String value) {
		this.localeAdmin = value;
	}
	
	public java.lang.String getLocaleAdmin() {
		return this.localeAdmin;
	}
	public void setLocaleFront(java.lang.String value) {
		this.localeFront = value;
	}
	
	public java.lang.String getLocaleFront() {
		return this.localeFront;
	}
	public void setIsRelativePath(java.lang.Integer value) {
		this.isRelativePath = value;
	}
	
	public java.lang.Integer getIsRelativePath() {
		return this.isRelativePath;
	}
	public void setTplSolution(java.lang.String value) {
		this.tplSolution = value;
	}
	
	public java.lang.String getTplSolution() {
		return this.tplSolution;
	}
	public void setDynamicSuffix(java.lang.String value) {
		this.dynamicSuffix = value;
	}
	
	public java.lang.String getDynamicSuffix() {
		return this.dynamicSuffix;
	}
	public void setStaticSuffix(java.lang.String value) {
		this.staticSuffix = value;
	}
	
	public java.lang.String getStaticSuffix() {
		return this.staticSuffix;
	}
	public void setProtocol(java.lang.String value) {
		this.protocol = value;
	}
	
	public java.lang.String getProtocol() {
		return this.protocol;
	}
	public void setIsStaticOn(java.lang.Integer value) {
		this.isStaticOn = value;
	}
	
	public java.lang.Integer getIsStaticOn() {
		return this.isStaticOn;
	}
	public void setDomainAlias(java.lang.String value) {
		this.domainAlias = value;
	}
	
	public java.lang.String getDomainAlias() {
		return this.domainAlias;
	}
	public void setDomainRedirect(java.lang.String value) {
		this.domainRedirect = value;
	}
	
	public java.lang.String getDomainRedirect() {
		return this.domainRedirect;
	}
	public void setFinalStep(java.lang.Integer value) {
		this.finalStep = value;
	}
	
	public java.lang.Integer getFinalStep() {
		return this.finalStep;
	}
	public void setAfterCheck(java.lang.Integer value) {
		this.afterCheck = value;
	}
	
	public java.lang.Integer getAfterCheck() {
		return this.afterCheck;
	}
	public void setLogoUrl(java.lang.String value) {
		this.logoUrl = value;
	}
	
	public java.lang.String getLogoUrl() {
		return this.logoUrl;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("SiteName",getSiteName())
			.append("ConfigId",getConfigId())
			.append("FtpUploadId",getFtpUploadId())
			.append("Domain",getDomain())
			.append("Path",getPath())
			.append("ShortName",getShortName())
			.append("CurrentSystem",getCurrentSystem())
			.append("StaticDir",getStaticDir())
			.append("LocaleAdmin",getLocaleAdmin())
			.append("LocaleFront",getLocaleFront())
			.append("IsRelativePath",getIsRelativePath())
			.append("TplSolution",getTplSolution())
			.append("DynamicSuffix",getDynamicSuffix())
			.append("StaticSuffix",getStaticSuffix())
			.append("Protocol",getProtocol())
			.append("IsStaticOn",getIsStaticOn())
			.append("DomainAlias",getDomainAlias())
			.append("DomainRedirect",getDomainRedirect())
			.append("FinalStep",getFinalStep())
			.append("AfterCheck",getAfterCheck())
			.append("LogoUrl",getLogoUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getSiteName())
			.append(getConfigId())
			.append(getFtpUploadId())
			.append(getDomain())
			.append(getPath())
			.append(getShortName())
			.append(getCurrentSystem())
			.append(getStaticDir())
			.append(getLocaleAdmin())
			.append(getLocaleFront())
			.append(getIsRelativePath())
			.append(getTplSolution())
			.append(getDynamicSuffix())
			.append(getStaticSuffix())
			.append(getProtocol())
			.append(getIsStaticOn())
			.append(getDomainAlias())
			.append(getDomainRedirect())
			.append(getFinalStep())
			.append(getAfterCheck())
			.append(getLogoUrl())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteInfomation == false) return false;
		if(this == obj) return true;
		SiteInfomation other = (SiteInfomation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getSiteName(),other.getSiteName())
			.append(getConfigId(),other.getConfigId())
			.append(getFtpUploadId(),other.getFtpUploadId())
			.append(getDomain(),other.getDomain())
			.append(getPath(),other.getPath())
			.append(getShortName(),other.getShortName())
			.append(getCurrentSystem(),other.getCurrentSystem())
			.append(getStaticDir(),other.getStaticDir())
			.append(getLocaleAdmin(),other.getLocaleAdmin())
			.append(getLocaleFront(),other.getLocaleFront())
			.append(getIsRelativePath(),other.getIsRelativePath())
			.append(getTplSolution(),other.getTplSolution())
			.append(getDynamicSuffix(),other.getDynamicSuffix())
			.append(getStaticSuffix(),other.getStaticSuffix())
			.append(getProtocol(),other.getProtocol())
			.append(getIsStaticOn(),other.getIsStaticOn())
			.append(getDomainAlias(),other.getDomainAlias())
			.append(getDomainRedirect(),other.getDomainRedirect())
			.append(getFinalStep(),other.getFinalStep())
			.append(getAfterCheck(),other.getAfterCheck())
			.append(getLogoUrl(),other.getLogoUrl())
			.isEquals();
	}
}

