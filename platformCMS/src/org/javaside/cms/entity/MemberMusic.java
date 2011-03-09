package org.javaside.cms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员音乐表
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_MUSIC")
public class MemberMusic {

	private Long id;
	private String musicName; //音乐名称
	private String musicUri; //音乐连接
	private Long musicOrder; //音乐排序
	private Date musicCreateDate; //音乐添加时间
	private Long uid; //添加音乐用户id

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicUri() {
		return musicUri;
	}

	public void setMusicUri(String musicUri) {
		this.musicUri = musicUri;
	}

	public Long getMusicOrder() {
		return musicOrder;
	}

	public void setMusicOrder(Long musicOrder) {
		this.musicOrder = musicOrder;
	}

	public Date getMusicCreateDate() {
		return musicCreateDate;
	}

	public void setMusicCreateDate(Date musicCreateDate) {
		this.musicCreateDate = musicCreateDate;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
