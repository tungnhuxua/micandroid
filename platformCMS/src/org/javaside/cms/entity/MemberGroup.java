package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员好友分类表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_GROUP")
public class MemberGroup {

	private Long id;
	private Long uid; //会员id
	private Long groupType; //会员好友分类类型 1同学2同事 3挚友4网络
	private String groupName; //会员好友分类名称

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
