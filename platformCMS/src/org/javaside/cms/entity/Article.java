package org.javaside.cms.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 文章实体
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "ARTICLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article {
	private Long id;
	private String title; // 文章标题
	private String content; // 文章内容
	private String source; // 文章来源
	private Integer siteHome = 0; // 大于零为网站首页文章
	private Integer categoryHome = 0; // 大于零位栏目首页文章
	private Integer recommend = 0; // 大于零则表示推荐文章
	private Date createDate = new Timestamp(System.currentTimeMillis()); // 创建时间
	private Integer readCount = 0; // 浏览次数
	private Integer voteCount = 0; // 投票次数
	private Category category; // 对应栏目
	private LogCategory logCategory; //日志自定义分类
	private ShowCategory showCategory; //秀场自定分类
	private String imageUri; // 图片缩略图
	private String imageLink;//图片大图
	private String random = ""; // 随机数
	private Member member; // 发布人
	private Set<Comment> comments = new LinkedHashSet<Comment>(); // 文章评论集合
	private Set<MemberFootmark> footmark = new LinkedHashSet<MemberFootmark>(); // 足迹关联
	private Set<MemberCollection> collection = new LinkedHashSet<MemberCollection>(); // 收藏关联
	private Set<Tag> tags = new LinkedHashSet<Tag>();
	private Date amendDate = new Timestamp(System.currentTimeMillis()); // 修改时间
	private String uri; //链接地址
	private Character iscms; //1，表示门户新闻
	private Character isblog; //1，表示用户日志,2，表秀场	如果iscms,isblog同时为1则表明该文章是用户日志，并被管理员推荐到CMS
	private Character hidden = 0; //是否隐藏  0 显示，1隐藏
	private Character upspecial = 0; //首页特别推荐，1
	private Character up = 0; //1 顶置

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// 指定字段类型
	@Column(columnDefinition = "longtext")
	public String getContent() {
		// FCK回车问题解决方法
		content = content != null ? content.replaceAll("((\r?\n)+|\t*)", "") : "";
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.REFRESH })
	@JoinColumn(name = "category")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSiteHome() {
		return siteHome;
	}

	public void setSiteHome(Integer siteHome) {
		this.siteHome = siteHome;
	}

	public Integer getCategoryHome() {
		return categoryHome;
	}

	public void setCategoryHome(Integer categoryHome) {
		this.categoryHome = categoryHome;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	@ManyToOne
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
	public Set<Comment> getComments() {
		return comments;
	}

	@OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
	public Set<MemberFootmark> getFootmark() {
		return footmark;
	}

	public void setFootmark(Set<MemberFootmark> footmark) {
		this.footmark = footmark;
	}

	@OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
	public Set<MemberCollection> getCollection() {
		return collection;
	}

	public void setCollection(Set<MemberCollection> collection) {
		this.collection = collection;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Date getAmendDate() {
		return amendDate;
	}

	public void setAmendDate(Date amendDate) {
		this.amendDate = amendDate;
	}

	@ManyToMany()
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinTable(joinColumns = { @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void addTags(Tag tag) {
		getTags().add(tag);
	}

	@Transient
	public String getTag() {
		String tag = "";
		for (Tag t : tags) {
			tag += t.getName() + ",";
		}
		int index = tag.lastIndexOf(",");
		if (index > 0) {
			tag = tag.substring(0, index);
		}
		return tag.toString();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Character getIscms() {
		return iscms;
	}

	public void setIscms(Character iscms) {
		this.iscms = iscms;
	}

	public Character getIsblog() {
		return isblog;
	}

	public void setIsblog(Character isblog) {
		this.isblog = isblog;
	}

	@ManyToOne(cascade = CascadeType.REMOVE)
	public LogCategory getLogCategory() {
		return logCategory;
	}

	public void setLogCategory(LogCategory logCategory) {
		this.logCategory = logCategory;
	}

	@ManyToOne(cascade = CascadeType.REMOVE)
	public ShowCategory getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(ShowCategory showCategory) {
		this.showCategory = showCategory;
	}

	public Character getHidden() {
		return hidden;
	}

	public void setHidden(Character hidden) {
		this.hidden = hidden;
	}

	public Character getUpspecial() {
		return upspecial;
	}

	public void setUpspecial(Character upspecial) {
		this.upspecial = upspecial;
	}

	public Character getUp() {
		return up;
	}

	public void setUp(Character up) {
		this.up = up;
	}
}
