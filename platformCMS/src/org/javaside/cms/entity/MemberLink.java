package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 会员链接
 * @author dkun.liu@gmail.com
 */
@Entity
@Table(name = "MEMBER_LINK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberLink {
	
	private Long id;
	private String linkName;   //链接名称
	private String linkUri;    //链接地址
	private Long  linkOrder;   //链接排序
	private String linkInfo;     //链接介绍
	private Long uid;          //链接用户id
	private Long clickNumer;   //链接点击次数
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUri() {
		return linkUri;
	}
	public void setLinkUri(String linkUri) {
		this.linkUri = linkUri;
	}
	public Long getLinkOrder() {
		return linkOrder;
	}
	public void setLinkOrder(Long linkOrder) {
		this.linkOrder = linkOrder;
	}

	public String getLinkInfo() {
		return linkInfo;
	}
	public void setLinkInfo(String linkInfo) {
		this.linkInfo = linkInfo;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getClickNumer() {
		return clickNumer;
	}
	public void setClickNumer(Long clickNumer) {
		this.clickNumer = clickNumer;
	}
	
	

}
