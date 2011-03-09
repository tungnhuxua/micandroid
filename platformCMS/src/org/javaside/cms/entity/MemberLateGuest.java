package org.javaside.cms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 会员访客表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_LATEGUEST")
public class MemberLateGuest {

	private Long id;
	private Member tomember; //访客
	private Long uid;      //会员自己
	private Date createDate = new Timestamp(System.currentTimeMillis());  //访问时间
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@ManyToOne
	public Member getTomember() {
		return tomember;
	}
	public void setTomember(Member tomember) {
		this.tomember = tomember;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	

	
	
	
	
}
