package org.javaside.cms.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

/**
 * 下载分类
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "DOWNLOADTYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DownloadType {
	private Long id;
	private String name;
	private Integer sort = 0;
	private DownloadType parent;
	private Set<DownloadType> child = new LinkedHashSet<DownloadType>();
	private Set<DownloadResource> resource = new LinkedHashSet<DownloadResource>();

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne
	@JoinColumn(name = "parent")
	public DownloadType getParent() {
		return parent;
	}

	public void setParent(DownloadType parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.TRUE)
	@OrderBy(clause = "sort asc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<DownloadType> getChild() {
		return child;
	}

	public void setChild(Set<DownloadType> child) {
		this.child = child;
	}

	@OneToMany(mappedBy = "type")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<DownloadResource> getResource() {
		return resource;
	}

	public void setResource(Set<DownloadResource> resource) {
		this.resource = resource;
	}
}
