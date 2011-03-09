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
 * 文章栏目,文章栏目即为文章分类。
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "CATEGORY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category {
	private Long id;
	private String name;
	private String nameEn;
	private Integer sort;
	private Category parent;
	private Set<Category> child = new LinkedHashSet<Category>();
	private Set<Article> articles = new LinkedHashSet<Article>(); //栏目的所有文章

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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne
	@JoinColumn(name = "parent")
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.TRUE)
	@OrderBy(clause = "sort asc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Category> getChild() {
		return child;
	}

	public void setChild(Set<Category> child) {
		this.child = child;
	}

	@OneToMany(mappedBy = "category")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
}
