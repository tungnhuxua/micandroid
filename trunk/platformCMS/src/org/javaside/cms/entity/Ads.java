package org.javaside.cms.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 广告实体
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "ADS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ads {

	private Long id;
	private String name; //广告名称
	private String description; //广告描述
	private String image; //广告图片
	private AdsType type; //广告类型,自定义图片广告,google广告
	private String script; //广告代码
	private Date createTime; //创建时间
	private String url; //广告链接
	private Set<AdsLocation> locations = new LinkedHashSet<AdsLocation>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToOne
	public AdsType getType() {
		return type;
	}

	public void setType(AdsType type) {
		this.type = type;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ADS_LOCATION", joinColumns = { @JoinColumn(name = "ADS_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADSLOCATION_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("sort")
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<AdsLocation> getLocations() {
		return locations;
	}

	public void setLocations(Set<AdsLocation> locations) {
		this.locations = locations;
	}
}
