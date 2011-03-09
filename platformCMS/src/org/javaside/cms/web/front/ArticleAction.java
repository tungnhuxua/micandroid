package org.javaside.cms.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Ads;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Category;
import org.javaside.cms.entity.Comment;
import org.javaside.cms.entity.DownloadResource;
import org.javaside.cms.entity.DownloadType;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberFootmark;
import org.javaside.cms.service.AdsLocationManager;
import org.javaside.cms.service.ArticleManager;
import org.javaside.cms.service.CategoryManager;
import org.javaside.cms.service.CommentManager;
import org.javaside.cms.service.DownloadResourceManager;
import org.javaside.cms.service.DownloadTypeManager;
import org.javaside.cms.service.MemberCircleManager;
import org.javaside.cms.service.MemberDynamicsManager;
import org.javaside.cms.service.MemberFootmarkManager;
import org.javaside.cms.service.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 获取栏目对应的信息
 * 
 * @author
 */
public class ArticleAction extends ActionSupport implements Preparable {

	// Spring 自动实例化这个类
	@Autowired
	CategoryManager categoryManager;
	@Autowired
	ArticleManager articleManager;
	@Autowired
	AdsLocationManager locationManager;
	@Autowired
	DownloadResourceManager resourceManager;
	@Autowired
	DownloadTypeManager typeManager;
	@Autowired
	CommentManager commentManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberFootmarkManager memberFootmarkManager;
	@Autowired
	private MemberCircleManager memberCircleManager;
	@Autowired
	private MemberDynamicsManager memberDynamicsManager;

	private Category category; // 栏目
	private Category[] subCategory; // 栏目对应的子栏目
	private Category showCategory; // 文章列表的栏目
	private String action; // 栏目 uri
	private List<List<Article>> articles;
	private List<List<Article>> blogArticles; //用户发布的日志，对应资讯栏目
	private Article article; // 文章
	private DownloadResource downloadresource;
	private Article preArticle; // 上一篇文章
	private Article nextArticle; // 下一篇文章

	private List<Article> weekVotes; // 本周投票排行
	private List<Article> monthVotes; // 本月投票排行
	private List<Article> weekComments; // 本周最热评论
	private List<Article> monthComments; // 本月最热评论
	private List<Article> weekHots; // 本周最热文章
	private List<Article> monthHots; // 本月最热文章

	private Page<Article> page = new Page<Article>(20); // 栏目文章列表
	private Ads[] adsArray; // 广告位
	private List<Article> categoryHomeArticle; // 栏目首页图片文章
	private List<Article> siteHomeArticle; // 文章首页滚动图片文章
	private List<Article> recommendArticle; // 推荐文章
	private List<Article> recommendArticleAll; // 所有栏目推荐
	private List<Article> hotArticle; // 首页热门文章
	private List<Article> showArticle; // 首页秀场
	private List<Article> circleRecommend; // 圈网精选
	private List<Article> NewPersonArticle; // 首页最新人物
	private List<Article> TagCorrelationArticle; // 文章页面相关文章
	private List<Article> randomFourArticle = new ArrayList<Article>();//随机取四条新闻
	private List<Article> blogTopArticle; //NEW BLOG_精彩博文
	private List circleTopList;//热门圈子
	private List memberDynamics; //用户动态信息

	private Page<Member> newMemberPage = new Page<Member>(12); //最新加入
	private Page<Member> heroPage = new Page<Member>(18); //大侠
	private Page<Member> columnsPage = new Page<Member>(12); //专栏

	private List<DownloadResource> downloadResourceTop;
	private String newsContent; // 搜索新闻内容
	private String isNewsVote; // 判断字符串
	private DownloadType type;
	private Comment comment; // 文章评论
	private Page<Comment> commentPage = new Page<Comment>(10); //文章评论列表

	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	
	private Member member; //用户信息
	private String authority = "A_VIEW_ALL_WZ";

	/**
	 * 网站首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/home")
	public String siteHome() throws Exception {
		// 首页滚动图片文章
		siteHomeArticle = articleManager.getSiteHomeArticle();
		recommendArticle = articleManager.getRecommendAll(10);
		hotArticle = articleManager.getHotArticle();
		// 广告
		adsArray = locationManager.getAdsLocation(2L);
		showArticle(); // 首页秀场
		circleRecommend(); // 圈网精选
		downloadTop(2); // 首页下载
		NewPersionArticle(); // 人物最新
		blogArticle(); //NEW BLOG_精彩博文
		circleTop();//热门圈子
		memberDynamics = memberDynamicsManager.getMemberDynamicsList(commentPage); //用户动态信息获取
		newMemberPage = memberManager.getNewMember(newMemberPage);
		heroPage = memberManager.getHeroMember(heroPage);
		columnsPage = memberManager.getColumnsMember(columnsPage);
		return SUCCESS;
	}

	@Override
	@Actions( { @Action("/news"), @Action("/person"), @Action("/special"), @Action("/circle")

	})
	public String execute() throws Exception {
		// 导航栏的栏目及子栏目
		topCategory();
		// 广告
		Long locationId = locationManager.getCategoryHomeLocationId(category);
		adsArray = locationManager.getAdsLocation(locationId);
		// 排行
		weekVotes = articleManager.getTopArticleByVoteOrHot(category, "week", "voteCount", 10);
		monthVotes = articleManager.getTopArticleByVoteOrHot(category, "month", "voteCount", 10);

		weekHots = articleManager.getTopArticleByVoteOrHot(category, "week", "readCount", 10);
		monthHots = articleManager.getTopArticleByVoteOrHot(category, "month", "readCount", 10);
		weekComments = commentManager.getHotCommentArticleByTime(category, "week");
		monthComments = commentManager.getHotCommentArticleByTime(category, "month");

		// 栏目首页文章
		categoryHomeArticle = articleManager.getCategoryHomeArticle(category);

		if (category.getChild() != null) {
			subCategory = new Category[category.getChild().size()];
			subCategory = category.getChild().toArray(subCategory);
			articles = new ArrayList<List<Article>>(category.getChild().size());
			blogArticles = new ArrayList<List<Article>>(category.getChild().size());
			for (Category element : subCategory) {
				Page<Article> apage = new Page<Article>(5);
				articles.add(articleManager.getArticebyCategory(element.getId(), apage).getResult());
				if (category.getId() == 54l) { //读取用户发表的通过审核的日志
					blogArticles.add(articleManager.getBlogArticeByCategory(element.getId(), apage).getResult());
				}
			}

		}
		isIndexData(); // 页面中有此共用数据
		return SUCCESS;
	}

	/**
	 * 栏目文章列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/newslist"), @Action("/newspiclist"), @Action("/personlist"), @Action("/personpiclist"),
			@Action("/show"), @Action("/showlist"), @Action("/speciallist"), @Action("/specialpiclist"),
			@Action("/circlelist"), @Action("/circlepiclist") })
	public String list() throws Exception {
		// 导航栏的栏目及子栏目
		topCategory();
		// 文章列表栏目
		showCategory = categoryManager.get(showCategory.getId());
		// 广告
		Long locationId = locationManager.getCategoryListLocationId(category);
		adsArray = locationManager.getAdsLocation(locationId);

		// 排行
		weekVotes = articleManager.getTopArticleByVoteOrHot(showCategory, "week", "voteCount", 10);
		monthVotes = articleManager.getTopArticleByVoteOrHot(showCategory, "month", "voteCount", 10);

		weekHots = articleManager.getTopArticleByVoteOrHot(showCategory, "week", "readCount", 10);
		monthHots = articleManager.getTopArticleByVoteOrHot(showCategory, "month", "readCount", 10);

		if (isNewsVote == null) {
			isNewsVote = "";
		} else {
			isNewsVote = isNewsVote;
		}
		if (!isNewsVote.equals("news") && !isNewsVote.equals("vote") && !isNewsVote.equals("recommend")
				&& !isNewsVote.equals("all")) {
			//同时获取CMS及用户发布的日志(CMS对应资讯)，秀场数据
			if (showCategory.getId() == 54l || showCategory.getId() == 56l) {
				page = articleManager.getBlogOrCmsArticleByCategory(showCategory.getId(), page);
			} else {
				page = articleManager.getArticebyCategory(showCategory.getId(), page);
			}
		} else {
			if (isNewsVote.equals("news")) {
				page = articleManager.getNewsVote(showCategory, "createDate", page); // 新闻 最新发布
			}
			if (isNewsVote.equals("vote")) {
				page = articleManager.getNewsVote(showCategory, "voteCount", page); // 新闻 投票排行
			}
			if (isNewsVote.equals("recommend")) {
				page = articleManager.getRecommend(showCategory, page); // 新闻
			}
			// 特别推荐
			if (isNewsVote.equals("all")) {
				page = articleManager.getArticleOfCategory(showCategory, page); // 新闻
				// 栏目新闻
			}
		}
		weekComments = commentManager.getHotCommentArticleByTime(category, "week");
		monthComments = commentManager.getHotCommentArticleByTime(category, "month");
		isIndexData(); // 页面中有此共用数据
		return SUCCESS;
	}

	@Actions( { @Action("/newscontent"), @Action("/personcontent"), @Action("/showcontent"),
			@Action("/specialcontent"), @Action("/circlecontent"), @Action("/searcharticlecontent") })
	public String article() throws Exception {
		// 导航栏的栏目及子栏目
		topCategory();
		// 文章详细页面的广告
		Long locationId = locationManager.getArticleLocationId(category);
		adsArray = locationManager.getAdsLocation(locationId);
		if (article != null && article.getId() != null) {
			article = articleManager.get(article.getId());
			// 浏览次数加一
			if (article != null) {
				Integer count = article.getReadCount();
				if (count == null) {
					count = 0;
				}
				article.setReadCount(count + 1);
				articleManager.save(article);
			}
			// 获取上一篇文章
			preArticle = articleManager.getPreArticle(article.getId());
			// 获取下一篇文章
			nextArticle = articleManager.getNextArticle(article.getId());
		}
		downloadTop(6); // 文章内容下载
		randomFourArtilce(); //随机取新闻
		recommendArticle = articleManager.getRecommendCategory(category, 6); // 栏目推荐
		recommendArticleAll = articleManager.getRecommendAll(10); // 频道精选
		TagCorrelationArticle = articleManager.getTagCorrelationTen(article.getTitle()); // 相关文章
		commentPage = commentManager.getCommentsByArticle(article, commentPage);

		/********** 足迹 ****/
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getServletPath() + "?" + request.getQueryString();

		Member member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member != null) {
			if (memberFootmarkManager.isFootmark(article.getId(), member.getId())) { //是否重复足迹记录
				//如果已有足迹记录，返回true则不操作
			} else {
				MemberFootmark memberFootmark = new MemberFootmark();
				memberFootmark.setArticle(article);
				memberFootmark.setCreateDate(new Date());
				memberFootmark.setOrderid(0l);
				memberFootmark.setUri(url);
				memberFootmark.setUid(member.getId());
				memberFootmarkManager.save(memberFootmark);
				memberFootmarkManager.getMemberFootmarkSize(member.getId()); //只保留20条足迹记录
			}
		}
		return SUCCESS;
	}

	/**
	 * 文章评论翻页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/comment-page"), @Action("/blog/comment-page") })
	public String commentPage() throws Exception {
		commentPage = commentManager.getCommentsByArticle(article, commentPage);
		return SUCCESS;
	}

	/**
	 * 投票
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/ding")
	public String ding() throws Exception {
		if (article != null && article.getId() != null) {
			article = articleManager.get(article.getId());
			if (article != null) {
				Integer count = article.getVoteCount();
				if (count == null) {
					count = 0;
				}
				article.setVoteCount(count + 1);
				articleManager.save(article);
			}
		}

		return null;
	}

	/**
	 * 发表文章评论
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/comment"), @Action("/comment") })
	public String comment() throws Exception {
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}
		Member member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (article != null && article.getId() != null && comment != null && comment.getContent() != null
				&& !"".equals(comment.getContent())) {
			comment.setArticle(article);
			comment.setCreateDate(new Date());
			comment.setMember(member);
			commentManager.save(comment);
		}
		return SUCCESS;
	}

	/**
	 * 搜索所有新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/searcharticle") })
	public String getAllSearchArticle() throws Exception {
		adsArray = locationManager.getAdsLocation(5L);
		page = articleManager.getAllSearchArticle(newsContent, page);
		return SUCCESS;
	}

	/**
	 * 按栏目搜索文章
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/newssearcharticle"), @Action("/personsearcharticle"), @Action("/showsearcharticle"),
			@Action("/specialsearcharticle"), @Action("/circlesearcharticle") })
	public String getSearchArticle() throws Exception {
		topCategory();
		// 广告
		Long locationId = locationManager.getCategoryListLocationId(category);
		adsArray = locationManager.getAdsLocation(locationId);
		page = articleManager.getSearchArticle(newsContent, category, page);

		return SUCCESS;
	}

	@Action("/del_comment")
	public String delComment() throws Exception {
		commentManager.delete(comment.getId());
		Struts2Utils.render("text/plain", "success");
		return null;
	}

	private void topCategory() {
		// 导航栏的栏目及子栏目
		action = Struts2Utils.getRequest().getServletPath();
		// 去掉 .action 后缀
		if (action.indexOf(".") > 0) {
			action = action.substring(0, action.indexOf(".")).replace(".", "");
		}
		category = categoryManager.get(categoryManager.getCategoryIdByAction(action));
	}

	/**
	 * 首页秀场
	 */
	public void showArticle() {
		Category categoryid = categoryManager.get(56L);
		showArticle = articleManager.getNewArticle(categoryid, 12);
	}

	/**
	 * 圈网精选
	 */
	public void circleRecommend() {
		Category categoryid = categoryManager.get(58L);
		circleRecommend = articleManager.getCircleRecommendArticle(categoryid);
	}

	/**
	 * 首页最新下载
	 */
	public void downloadTop(int rows) {
		type = typeManager.get(3L); // 获取拦目
		downloadResourceTop = resourceManager.getTopResource(type, "createDate", rows);
	}

	/**
	 * 人物最新
	 */
	public void NewPersionArticle() {
		Category categoryid = categoryManager.get(55L);
		NewPersonArticle = articleManager.getNewArticle(categoryid, 16);
	}

	/**
	 * 最新blog文章
	 */
	public void blogArticle() {
		Category categoryid = categoryManager.get(55L);
		blogTopArticle = articleManager.getNewBlogArticle(10);
	}

	public void circleTop() {

		Page circleTopPage = new Page(12);
		circleTopList = memberCircleManager.getTopCircle(circleTopPage);
	}

	/**
	 * 页面中共用方法，根据URI判断获取，防止其它页面不需要取其数据，减少服务器压力。
	 */
	public void isIndexData() {
		if ("/person".equals(action)) {
			NewPersionArticle(); // 人物最新
		}
		if ("/special".equals(action) || "/person".equals(action) || "/circle".equals(action)
				|| "/newslist".equals(action) || "/personlist".equals(action) || "/showlist".equals(action)
				|| "/speciallist".equals(action) || "/circlelist".equals(action)) {
			circleRecommend(); // 圈网精选
		}
		if ("/circle".equals(action) || "/newslist".equals(action) || "/personlist".equals(action)
				|| "/showlist".equals(action) || "/speciallist".equals(action) || "/circlelist".equals(action)) {
			downloadTop(2);
		}
	}

	/**
	 * 随机取新闻
	 */
	public void randomFourArtilce() {
		Category fresh = categoryManager.get(59l); //抢鲜看
		Category visual = categoryManager.get(61l); //平面视觉
		Category photo = categoryManager.get(102l); //摄影
		randomFourArticle.add(articleManager.getRandomFourArticle(fresh));
		randomFourArticle.add(articleManager.getRandomFourArticle(photo));
		randomFourArticle.add(articleManager.getRandomFourArticle(visual));
		randomFourArticle.add(articleManager.getRandomFourArticle(photo));
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Category[] getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Category[] subCategory) {
		this.subCategory = subCategory;
	}

	public List<List<Article>> getArticles() {
		return articles;
	}

	public void setArticles(List<List<Article>> articles) {
		this.articles = articles;
	}

	public Page<Article> getPage() {
		return page;
	}

	public void setPage(Page<Article> page) {
		this.page = page;
	}

	public Category getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(Category showCategory) {
		this.showCategory = showCategory;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Article getPreArticle() {
		return preArticle;
	}

	public void setPreArticle(Article preArticle) {
		this.preArticle = preArticle;
	}

	public Article getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(Article nextArticle) {
		this.nextArticle = nextArticle;
	}

	public Ads[] getAdsArray() {
		return adsArray;
	}

	public void setAdsArray(Ads[] adsArray) {
		this.adsArray = adsArray;
	}

	public List<Article> getWeekVotes() {
		return weekVotes;
	}

	public void setWeekVotes(List<Article> weekVotes) {
		this.weekVotes = weekVotes;
	}

	public List<Article> getMonthVotes() {
		return monthVotes;
	}

	public void setMonthVotes(List<Article> monthVotes) {
		this.monthVotes = monthVotes;
	}

	public List<Article> getWeekComments() {
		return weekComments;
	}

	public void setWeekComments(List<Article> weekComments) {
		this.weekComments = weekComments;
	}

	public List<Article> getMonthComments() {
		return monthComments;
	}

	public void setMonthComments(List<Article> monthComments) {
		this.monthComments = monthComments;
	}

	public List<Article> getWeekHots() {
		return weekHots;
	}

	public void setWeekHots(List<Article> weekHots) {
		this.weekHots = weekHots;
	}

	public List<Article> getMonthHots() {
		return monthHots;
	}

	public void setMonthHots(List<Article> monthHots) {
		this.monthHots = monthHots;
	}

	public List<Article> getCategoryHomeArticle() {
		return categoryHomeArticle;
	}

	public void setCategoryHomeArticle(List<Article> categoryHomeArticle) {
		this.categoryHomeArticle = categoryHomeArticle;
	}

	public List<Article> getSiteHomeArticle() {
		return siteHomeArticle;
	}

	public void setSiteHomeArticle(List<Article> siteHomeArticle) {
		this.siteHomeArticle = siteHomeArticle;
	}

	public List<Article> getRecommendArticle() {
		return recommendArticle;
	}

	public void setRecommendArticle(List<Article> recommendArticle) {
		this.recommendArticle = recommendArticle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getIsNewsVote() {
		return isNewsVote;
	}

	public void setIsNewsVote(String isNewsVote) {
		this.isNewsVote = isNewsVote;
	}

	public List<Article> getHotArticle() {
		return hotArticle;
	}

	public void setHotArticle(List<Article> hotArticle) {
		this.hotArticle = hotArticle;
	}

	public List<Article> getShowArticle() {
		return showArticle;
	}

	public void setShowArticle(List<Article> showArticle) {
		this.showArticle = showArticle;
	}

	public List<Article> getCircleRecommend() {
		return circleRecommend;
	}

	public void setCircleRecommend(List<Article> circleRecommend) {
		this.circleRecommend = circleRecommend;
	}

	public DownloadType getType() {
		return type;
	}

	public void setType(DownloadType type) {
		this.type = type;
	}

	public List<DownloadResource> getDownloadResourceTop() {
		return downloadResourceTop;
	}

	public void setDownloadResourceTop(List<DownloadResource> downloadResourceTop) {
		this.downloadResourceTop = downloadResourceTop;
	}

	public List<Article> getRecommendArticleAll() {
		return recommendArticleAll;
	}

	public void setRecommendArticleAll(List<Article> recommendArticleAll) {
		this.recommendArticleAll = recommendArticleAll;
	}

	public List<Article> getNewPersonArticle() {
		return NewPersonArticle;
	}

	public void setNewPersonArticle(List<Article> newPersonArticle) {
		NewPersonArticle = newPersonArticle;
	}

	public List<Article> getTagCorrelationArticle() {
		return TagCorrelationArticle;
	}

	public void setTagCorrelationArticle(List<Article> tagCorrelationArticle) {
		TagCorrelationArticle = tagCorrelationArticle;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Page<Comment> getCommentPage() {
		return commentPage;
	}

	public void setCommentPage(Page<Comment> commentPage) {
		this.commentPage = commentPage;
	}

	//判断登录
	public String getUserName() {
		return userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<Article> getRandomFourArticle() {
		return randomFourArticle;
	}

	public void setRandomFourArticle(List<Article> randomFourArticle) {
		this.randomFourArticle = randomFourArticle;
	}

	public List<List<Article>> getBlogArticles() {
		return blogArticles;
	}

	public void setBlogArticles(List<List<Article>> blogArticles) {
		this.blogArticles = blogArticles;
	}

	public List<Article> getBlogTopArticle() {
		return blogTopArticle;
	}

	public void setBlogTopArticle(List<Article> blogTopArticle) {
		this.blogTopArticle = blogTopArticle;
	}

	public List getCircleTopList() {
		return circleTopList;
	}

	public void setCircleTopList(List circleTopList) {
		this.circleTopList = circleTopList;
	}

	public List getMemberDynamics() {
		return memberDynamics;
	}

	public void setMemberDynamics(List memberDynamics) {
		this.memberDynamics = memberDynamics;
	}

	public Page<Member> getNewMemberPage() {
		return newMemberPage;
	}

	public void setNewMemberPage(Page<Member> newMemberPage) {
		this.newMemberPage = newMemberPage;
	}

	public Page<Member> getHeroPage() {
		return heroPage;
	}

	public void setHeroPage(Page<Member> heroPage) {
		this.heroPage = heroPage;
	}

	public Page<Member> getColumnsPage() {
		return columnsPage;
	}

	public void setColumnsPage(Page<Member> columnsPage) {
		this.columnsPage = columnsPage;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void prepare() throws Exception {
		if(!userName.equals("roleAnonymous") && userName !=null){
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()); //获取已登录用户个人信息
		}
			
	}

}
