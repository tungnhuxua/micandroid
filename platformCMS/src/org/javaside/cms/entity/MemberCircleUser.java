package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 圈子成员表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_CIRCLE_User")
public class MemberCircleUser {

	private Long id;
	private Long cid; //关联圈子id
	private Long uid; //成员id
	private Long state; //申请状态 0表示成员申请加入圈子中  1表示圈子的正式会员
	private Long status; //圈子会员身份   0表示为普通会员  1表示为圈子的管理员
	private int circleIndex; //圈子放置个人首页  0 ：不显示  1：显示

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public int getCircleIndex() {
		return circleIndex;
	}

	public void setCircleIndex(int circleIndex) {
		this.circleIndex = circleIndex;
	}



}
