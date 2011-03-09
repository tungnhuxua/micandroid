package org.javaside.cms.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 广告位置
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "ADSLOCATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdsLocation {
	private Long id;
	private String location; //位置
	private Integer sort = 0;
	private AdsLocation parent; //父位置
	private Set<Ads> ads = new LinkedHashSet<Ads>(); //该位置对应的广告
	private Set<AdsLocation> child = new LinkedHashSet<AdsLocation>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne
	@JoinColumn(name = "parent")
	public AdsLocation getParent() {
		return parent;
	}

	public void setParent(AdsLocation parent) {
		this.parent = parent;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ADS_LOCATION", joinColumns = { @JoinColumn(name = "ADSLOCATION_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADS_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Ads> getAds() {
		return ads;
	}

	public void setAds(Set<Ads> ads) {
		this.ads = ads;
	}

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.TRUE)
	@OrderBy("sort")
	//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<AdsLocation> getChild() {
		return child;
	}

	public void setChild(Set<AdsLocation> child) {
		this.child = child;
	}
}
