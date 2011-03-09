package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员好友表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_GROUP_USER")
public class MemberGroupUser {

	private Long id;
	private Long uid; //会员id
	private Long groupType; //会员好友分类类型 1同学2同事 3挚友4网络
	private Long tid;//会员好友id
	private int friendState;//暂时字段,现在未用
	private int blogIndex; //放置blog个人首页朋友显示  0 ：不显示  1：显示

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

	public Long getGroupType() {
		return groupType;
	}

	public void setGroupType(Long groupType) {
		this.groupType = groupType;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public int getFriendState() {
		return friendState;
	}

	public void setFriendState(int friendState) {
		this.friendState = friendState;
	}

	public int getBlogIndex() {
		return blogIndex;
	}

	public void setBlogIndex(int blogIndex) {
		this.blogIndex = blogIndex;
	}

}
