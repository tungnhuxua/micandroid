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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 下载资源
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "DOWNLOADRESOURCE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DownloadResource {
	private Long id;
	private String name; //资源名
	private String description; //描述
	private String uri; //资源路径
	private String copyright; //版权
	private String format; //文件格式
	private Float size; //文件大小
	private Float points; //积分
	private Integer downcount = 0; //下载次数
	private Integer recommend; //大于零的为推荐
	private String letter; //首字母
	private DownloadType type; //类型
	private Date createDate = new Timestamp(System.currentTimeMillis()); //上传时间
	private String tag; //标签
	private Member member; //发布人

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public Integer getDowncount() {
		return downcount;
	}

	public void setDowncount(Integer downcount) {
		this.downcount = downcount;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.REFRESH })
	@JoinColumn(name = "type")
	public DownloadType getType() {
		return type;
	}

	public void setType(DownloadType type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	@ManyToOne
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
