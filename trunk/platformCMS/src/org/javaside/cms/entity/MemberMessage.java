package org.javaside.cms.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * blog留言
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_MESSAGE")
public class MemberMessage {

	private Long id; 
	private Long uid;  //用户id
	private Member member;  //对方用户id
	private String comment; //留言内容
	private Long type; //1:留言  2：主人回复 
	private Integer examine = 1; //1:主人没有查看当前留言  0：主人已经查看过该留言
	private Date createDate = new Timestamp(System.currentTimeMillis()); //足迹创建时间
	private Long connectionId = 0l; //主人回复关联留言id
	private Integer quietly = 0; //悄悄话 1：是   0：否
	private Member memberTemp; //获取发表回复留言临时用户信息

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "tid")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(Long connectionId) {
		this.connectionId = connectionId;
	}
	public Integer getQuietly() {
		return quietly;
	}
	public void setQuietly(Integer quietly) {
		this.quietly = quietly;
	}
	//非持久化属性.
	@Transient
	public Member getMemberTemp() {
		return memberTemp;
	}
	public void setMemberTemp(Member memberTemp) {
		this.memberTemp = memberTemp;
	}
	
}
