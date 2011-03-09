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
 * <p> ExtjsTest 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class ExtjsTest extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	/**功能ID*/
	private java.lang.Integer id;
	/**功能名称*/
	private java.lang.String functionName;
	/**父节点ID*/
	private java.lang.Integer parentId;
	/**功能类型*/
	private java.lang.String types;
	/**新增时间*/
	private java.sql.Date insertTime;
	/**电话号码*/
	private java.lang.String telephone;
	/**手机号码*/
	private java.lang.String mobileTelephone;
	/**次数*/
	private java.lang.Integer frequency;
	/**上传文件URL*/
	private java.lang.String upfileUrl;
	/**功能等级*/
	private java.lang.Integer rating;
	/**默认功能*/
	private java.lang.String whetherDefault;
	/**功能说明*/
	private java.lang.String functionExplain;
	/**功能集合体*/
	private java.lang.String aggregate;
	//columns END

	public ExtjsTest(){
	}

	public ExtjsTest(
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
	public void setFunctionName(java.lang.String value) {
		this.functionName = value;
	}
	
	public java.lang.String getFunctionName() {
		return this.functionName;
	}
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setTypes(java.lang.String value) {
		this.types = value;
	}
	
	public java.lang.String getTypes() {
		return this.types;
	}
	public String getInsertTimeString() {
		return date2String(getInsertTime(), FORMAT_INSERT_TIME_);
	}
	public void setInsertTimeString(String value) {
		setInsertTime(string2Date(value, FORMAT_INSERT_TIME_,java.sql.Date.class));
	}
	
	public void setInsertTime(java.sql.Date value) {
		this.insertTime = value;
	}
	
	public java.sql.Date getInsertTime() {
		return this.insertTime;
	}
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	public void setMobileTelephone(java.lang.String value) {
		this.mobileTelephone = value;
	}
	
	public java.lang.String getMobileTelephone() {
		return this.mobileTelephone;
	}
	public void setFrequency(java.lang.Integer value) {
		this.frequency = value;
	}
	
	public java.lang.Integer getFrequency() {
		return this.frequency;
	}
	public void setUpfileUrl(java.lang.String value) {
		this.upfileUrl = value;
	}
	
	public java.lang.String getUpfileUrl() {
		return this.upfileUrl;
	}
	public void setRating(java.lang.Integer value) {
		this.rating = value;
	}
	
	public java.lang.Integer getRating() {
		return this.rating;
	}
	public void setWhetherDefault(java.lang.String value) {
		this.whetherDefault = value;
	}
	
	public java.lang.String getWhetherDefault() {
		return this.whetherDefault;
	}
	public void setFunctionExplain(java.lang.String value) {
		this.functionExplain = value;
	}
	
	public java.lang.String getFunctionExplain() {
		return this.functionExplain;
	}
	
	private Set extjsTests = new HashSet(0);
	public void setExtjsTests(Set<ExtjsTest> extjsTest){
		this.extjsTests = extjsTest;
	}
	
	public Set<ExtjsTest> getExtjsTests() {
		return extjsTests;
	}
	
	private ExtjsTest extjsTest;
	
	public void setExtjsTest(ExtjsTest extjsTest){
		this.extjsTest = extjsTest;
	}
	
	public ExtjsTest getExtjsTest() {
		return extjsTest;
	}
	public java.lang.String getAggregate() {
		return aggregate;
	}

	public void setAggregate(java.lang.String aggregate) {
		this.aggregate = aggregate;
	}
	 
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("FunctionName",getFunctionName())
			.append("ParentId",getParentId())
			.append("Types",getTypes())
			.append("InsertTime",getInsertTime())
			.append("Telephone",getTelephone())
			.append("MobileTelephone",getMobileTelephone())
			.append("Frequency",getFrequency())
			.append("UpfileUrl",getUpfileUrl())
			.append("Rating",getRating())
			.append("WhetherDefault",getWhetherDefault())
			.append("FunctionExplain",getFunctionExplain())
			.append("Aggregate",getAggregate())
			
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getFunctionName())
			.append(getParentId())
			.append(getTypes())
			.append(getInsertTime())
			.append(getTelephone())
			.append(getMobileTelephone())
			.append(getFrequency())
			.append(getUpfileUrl())
			.append(getRating())
			.append(getWhetherDefault())
			.append(getFunctionExplain())
			.append(getAggregate())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExtjsTest == false) return false;
		if(this == obj) return true;
		ExtjsTest other = (ExtjsTest)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getFunctionName(),other.getFunctionName())
			.append(getParentId(),other.getParentId())
			.append(getTypes(),other.getTypes())
			.append(getInsertTime(),other.getInsertTime())
			.append(getTelephone(),other.getTelephone())
			.append(getMobileTelephone(),other.getMobileTelephone())
			.append(getFrequency(),other.getFrequency())
			.append(getUpfileUrl(),other.getUpfileUrl())
			.append(getRating(),other.getRating())
			.append(getWhetherDefault(),other.getWhetherDefault())
			.append(getFunctionExplain(),other.getFunctionExplain())
			.append(getFunctionExplain(),other.getFunctionExplain())
			.append(getAggregate(),other.getAggregate())
			.isEquals();
	}


}

