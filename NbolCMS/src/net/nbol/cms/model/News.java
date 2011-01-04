package net.nbol.cms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
*
* 功能描述：新闻实体类
* 创建时间：2011-1-3 上午04:35:20
* 版权信息：宁波在线
* @author zoopnin
* @version 1.0
*
*/
@Entity(name = "news")
@Table(name = "a_news")
public class News implements Serializable{
	private static final long serialVersionUID = 6224944273947718411L;
	
	/*** 正常状态. */
    public static final int STATUS_NORMAL = 0;

    /*** 待审. */
    public static final int STATUS_WAIT = 1;

    /*** 不分页. */
    public static final int NO_PAGE = 0;

    /*** 自动分页. */
    public static final int AUTO_PAGE = 1;

    /*** 手工分页. */
    public static final int PAGE_BY_HAND = 2;

    private Long id;
    
    /*** 新闻所属的栏目名称. */
    private NewsCategory newsCategory;

    /*** 主标题. */
    private String contentTitle;

    /*** 子标题. */
    private String shortTitle;

    
    private String link;

    /*** 生成的静态页面地址. */
    private String html;

    /*** 分页方式. */
    private int pageType;

    /*** 自动分页字数. */
    private int pageSize;

    /*** 是否图片新闻. */
    private String isPicture;
    
    /*** 是否头条新闻. */
    private Integer isHeadline ;

    /*** 新闻点击数 */
    private Integer hit;

    /*** 新闻摘要 */
    private String summary;

    /*** 新闻内容 */
    private String content;

    /*** 信息来源. */
    private String infoFrom;

    /*** 作者. */
    private String author;

    /*** 新闻最后更新日期. */
    private Date updateDate = null;

    /*** status. */
    private Integer status;
    
    public News(){}

    @GenericGenerator(name = "generator" ,strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id" ,unique = true ,nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public NewsCategory getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}

	@Column(length = 200)
	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	@Column(length = 200)
	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@Column(length = 100)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(length = 100)
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Column(name = "page_type")
	public int getPageType() {
		return pageType;
	}

	public void setPageType(int pageType) {
		this.pageType = pageType;
	}

	@Column(name = "page_size")
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Column
	public String getIsPicture() {
		return isPicture;
	}

	public void setIsPicture(String isPicture) {
		this.isPicture = isPicture;
	}
	
	@Column
	public Integer getIsHeadline() {
		return isHeadline;
	}

	public void setIsHeadline(Integer isHeadline) {
		this.isHeadline = isHeadline;
	}

	@Column(name = "hit")
	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	 @Column(name = "summary", length = 255)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "content", length = 60000)
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "infoFrom", length = 50)
	public String getInfoFrom() {
		return infoFrom;
	}

	public void setInfoFrom(String infoFrom) {
		this.infoFrom = infoFrom;
	}
	@Column(name = "author", length = 50)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		if(updateDate != null){
			return (Date)updateDate.clone() ;
		}
		return null;
	}

	public void setUpdateDate(Date updateDate) {
		if(updateDate != null){
			this.updateDate =  (Date)updateDate.clone() ;
		}
		this.updateDate = null ;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
