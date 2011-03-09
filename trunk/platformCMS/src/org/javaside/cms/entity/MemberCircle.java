package org.javaside.cms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 圈子
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_CIRCLE")
public class MemberCircle {

	private Long id;
	private String circleName;//圈子名称
	private Long circleType;//圈子类型
	private Long uid; //创建人id	
	private Date createDate; //创建时间
	private Date updateDate; //更新圈子信息时间
	private String des; //圈子描述
	private String province; //省份
	private String city; //城市
	private String county; //县城
	private String circleImage;
	private Long verifyState;//审核状态 0表示等待管理员审核 1表示管理审核成功
	private Long commend; //特别推荐

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Long getCircleType() {
		return circleType;
	}

	public void setCircleType(Long circleType) {
		this.circleType = circleType;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Long getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Long verifyState) {
		this.verifyState = verifyState;
	}

	public String getCircleImage() {
		return circleImage;
	}

	public void setCircleImage(String circleImage) {
		this.circleImage = circleImage;
	}

	public Long getCommend() {
		return commend;
	}

	public void setCommend(Long commend) {
		this.commend = commend;
	}

}
