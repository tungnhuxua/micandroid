package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员对会员之间欣赏表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_ENJOY")
public class MemberEnjoy {

	private Long id;
	private Long uid; //被欣赏的会员id
	private Long tid; //邀请欣赏的会员id
	private int enjoyState; //欣赏会员状态  1表示欣赏

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public int getEnjoyState() {
		return enjoyState;
	}

	public void setEnjoyState(int enjoyState) {
		this.enjoyState = enjoyState;
	}

}
