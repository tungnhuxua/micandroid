package net.nbol.cms.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
*
* 功能描述： 新闻栏目的实体类
* 创建时间：2011-1-4 上午06:02:51
* 版权信息：宁波在线
* @author zoopnin
* @version 1.0
*
*/
@Entity(name = "newsCategory")
@Table(name = "a_news_category")
public class NewsCategory {
	
	
	private Long id  ;
	/***栏目的名称.*/
	private String name;
	/***排序值.*/
	private Integer sort;
	/***栏目的状态.*/
	private Integer status;
	/***父栏目对象*/
    private NewsCategory parent;
    /***该栏目的所有子栏目.*/
    private Set<NewsCategory> children = new HashSet<NewsCategory>(0);
    /***该栏目下的所有新闻.*/
    private Set<News> newses = new HashSet<News>(0);
    
    public NewsCategory(){}

    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 @Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public NewsCategory getParent() {
		return parent;
	}

	public void setParent(NewsCategory parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "parent")
	public Set<NewsCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<NewsCategory> children) {
		this.children = children;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsCategory")
	public Set<News> getNewses() {
		return newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}
}
