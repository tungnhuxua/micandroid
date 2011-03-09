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
 * <p> SitePart 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SitePart extends BaseEntity {
	
	//date formats
	public static final String FORMAT_INSERT_TIME_ = DATE_TIME_FORMAT;
	
	//columns START
	private Integer id;
	private java.lang.String partName;
	private Integer lowerNode;
	private Integer partParentId;
	private Integer showType;
	private Integer sortValue;
	private Integer effective;
	private java.lang.String partNotes;
	private java.sql.Date insertTime;
	//columns END
	
	/** arye 添加的属性 */
	private java.sql.Date insertTime1;
	private java.sql.Date insertTime2;
	private String parentPartName;//父节点名称，便于页面上显示
	
	/**关联网站分类字符串*/
	private String relateId;

	/**标签里读取的名称*/
	private java.lang.String name;
	
	
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public SitePart(){
	}

	public SitePart(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setPartName(java.lang.String value) {
		this.partName = value;
	}
	
	public java.lang.String getPartName() {
		return this.partName;
	}
	public void setLowerNode(Integer value) {
		this.lowerNode = value;
	}
	
	public Integer getLowerNode() {
		return this.lowerNode;
	}
	public void setPartParentId(Integer value) {
		this.partParentId = value;
	}
	
	public Integer getPartParentId() {
		return this.partParentId;
	}
	public void setShowType(Integer value) {
		this.showType = value;
	}
	
	public Integer getShowType() {
		return this.showType;
	}
	public void setSortValue(Integer value) {
		this.sortValue = value;
	}
	
	public Integer getSortValue() {
		return this.sortValue;
	}
	public void setEffective(Integer value) {
		this.effective = value;
	}
	
	public Integer getEffective() {
		return this.effective;
	}
	public void setPartNotes(java.lang.String value) {
		this.partNotes = value;
	}
	
	public java.lang.String getPartNotes() {
		return this.partNotes;
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
	
/*	public String getParentPartName() {
		return this.parentPartName;
		System.out.prIntegerln("getParentPartName>>"+new SitePartDao().getSitePartById(this.getPartParentId()).getPartName());
		//根据父节点id获得栏目名并返回
		return new SitePartDao().getSitePartById(this.getPartParentId()).getPartName();
	}

	public void setParentPartName(Integer parentPartId) {
		System.out.prIntegerln("++++++++++++++++++parentPartId+>>"+parentPartId);
		System.out.prIntegerln("setParentPartName>>"+new SitePartDao().getSitePartById(parentPartId).getPartName());
		//根据父节点id获得栏目名并赋值给parentPartName属性
		this.parentPartName = new SitePartDao().getSitePartById(parentPartId).getPartName();
	}*/
	 
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("PartName",getPartName())
			.append("LowerNode",getLowerNode())
			.append("PartParentId",getPartParentId())
			.append("ShowType",getShowType())
			.append("SortValue",getSortValue())
			.append("Effective",getEffective())
			.append("PartNotes",getPartNotes())
			.append("InsertTime",getInsertTime())
			.append("InsertTime1",getInsertTime1())
			.append("InsertTime2",getInsertTime2())
			.append("parentPartName",getParentPartName())
			.append("RelateId",getRelateId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getPartName())
			.append(getLowerNode())
			.append(getPartParentId())
			.append(getShowType())
			.append(getSortValue())
			.append(getEffective())
			.append(getPartNotes())
			.append(getInsertTime())
			.append(getInsertTime1())
			.append(getInsertTime2())
			.append(getParentPartName())
			.append(getRelateId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SitePart == false) return false;
		if(this == obj) return true;
		SitePart other = (SitePart)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getPartName(),other.getPartName())
			.append(getLowerNode(),other.getLowerNode())
			.append(getPartParentId(),other.getPartParentId())
			.append(getShowType(),other.getShowType())
			.append(getSortValue(),other.getSortValue())
			.append(getEffective(),other.getEffective())
			.append(getPartNotes(),other.getPartNotes())
			.append(getInsertTime(),other.getInsertTime())
			.append(getInsertTime1(),other.getInsertTime1())
			.append(getInsertTime2(),other.getInsertTime2())
			.append(getParentPartName(),other.getParentPartName())
			.append(getRelateId(),other.getRelateId())
			 
			.isEquals();
	}
	
	/** arye 添加的属性对应的getXXX(),和setXXX() */
	public String getInsertTime1String() {
		return date2String(getInsertTime1(), FORMAT_INSERT_TIME_);
	}
	public void setInsertTime1String(String value) {
		setInsertTime1(string2Date(value, FORMAT_INSERT_TIME_,java.sql.Date.class));
	}
	public java.sql.Date getInsertTime1() {
		return insertTime1;
	}

	public void setInsertTime1(java.sql.Date insertTime1) {
		this.insertTime1 = insertTime1;
	}
	public String getInsertTime2String() {
		return date2String(getInsertTime2(), FORMAT_INSERT_TIME_);
	}
	public void setInsertTime2String(String value) {
		setInsertTime2(string2Date(value, FORMAT_INSERT_TIME_,java.sql.Date.class));
	}
	public java.sql.Date getInsertTime2() {
		return insertTime2;
	}

	public void setInsertTime2(java.sql.Date insertTime2) {
		this.insertTime2 = insertTime2;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}

	public String getParentPartName() {
		return parentPartName;
	}

	public void setParentPartName(String parentPartName) {
		this.parentPartName = parentPartName;
	}
}

