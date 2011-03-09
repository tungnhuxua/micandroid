package org.javaside.cms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Category;
import org.javaside.cms.entity.LogCategory;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.ShowCategory;
import org.javaside.cms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ArticleManager extends DefaultEntityManager<Article, Long> {

	@Autowired
	private CategoryManager categoryManager;

	/**
	 * 分页获取会员发表的日子或秀场
	 * 
	 * @param member
	 * @param page
	 * @param isblog
	 *            1表示日志，2表示秀场
	 * @param defCategory
	 *            日志，秀场的自定义分类ID。0表示所有分类。
	 * @return
	 */
	public Page getArticleOfMember(Member member, Page page, Character isblog, Long defCategory) {
		page.setOrder(page.DESC + "," + page.DESC);
		page.setOrderBy("up,createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", isblog));
		if (defCategory > 0) {
			if (isblog.equals('1')) { //日志
				criteria.add(Restrictions.eq("logCategory.id", defCategory));
			} else { //秀场
				criteria.add(Restrictions.eq("showCategory.id", defCategory));
			}
		}
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 分页获取会员发表的日子或秀场,推荐到首页排序
	 * 
	 * @param member
	 * @param page
	 * @param isblog
	 *            1表示日志，2表示秀场
	 * @return
	 */
	public Page getArticleOfMember(Member member, Page page, String isblog) {
		page.setOrder(page.DESC + "," + page.DESC);
		page.setOrderBy("up,createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", isblog));
		return entityDao.findByCriteria(page, criteria);
	}
	
	/**
	 * 获取会员发布的日志和秀场总数
	 * 
	 * @param member
	 * @param isblog
	 *            1表示日志，2表示秀场
	 * @return
	 */
	public Integer getArticleCountMember(Member member, Page page, String isblog) {
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", isblog));
		page = entityDao.findByCriteria(page, criteria);
		int count = page.getTotalCount();
		return count;
	}


	/**
	 * blog首页特别推荐
	 * 
	 * @param member
	 * @param page
	 * @param isblog
	 *            1表示日志，2表示秀场
	 * @return
	 */
	public Page getBlogRecommendArticleOfMember(Member member, Page page) {
		page.setOrder(page.DESC + "," + page.DESC);
		page.setOrderBy("up,createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.or(Restrictions.eq("isblog", 1), Restrictions.eq("isblog", 2)));
		criteria.add(Restrictions.eq("upspecial", 1));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按自定义日志分类分页获取会员发表的日子,推荐到首页排序
	 * 
	 * @param logCateId
	 *            自定义分类ID
	 * @param member
	 *            会员
	 * @param page
	 *            分页对象
	 * @return
	 */
	public Page getLogCategoryArticleOfMember(LogCategory logCate, Member member, Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("upspecial");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", "1"));
		criteria.add(Restrictions.eq("logCategory", logCate));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按自定义秀场分类分页获取会员发表的日子,安推荐到首页排序
	 * 
	 * @param showCateId
	 *            自定义分类ID
	 * @param member
	 *            会员
	 * @param page
	 *            分页对象
	 * @return
	 */
	public Page getShowCategoryArticleOfMember(ShowCategory showCate, Member member, Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("upspecial");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", "2"));
		criteria.add(Restrictions.eq("showCategory", showCate));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按时间段获取用户发布的日子和秀场
	 * 
	 * @param showCateId
	 *            自定义分类ID
	 * @param member
	 *            会员
	 * @param page
	 *            分页对象
	 * @return
	 */
	public Page getArticleOfDate(Member member, Page page, Date before, Date end) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.ge("createDate", before));
		criteria.add(Restrictions.lt("createDate", end));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按时间段获取用户发布的秀场
	 * 
	 * @param showCateId
	 *            自定义分类ID
	 * @param member
	 *            会员
	 * @param page
	 *            分页对象
	 * @return
	 */
	public Page getShowArticleOfDate(Member member, Page page, Date before, Date end) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", "2"));
		criteria.add(Restrictions.ge("createDate", before));
		criteria.add(Restrictions.lt("createDate", end));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按时间段获取用户发布的日子
	 * 
	 * @param showCateId
	 *            自定义分类ID
	 * @param member
	 *            会员
	 * @param page
	 *            分页对象
	 * @return
	 */
	public Page getLogArticleOfDate(Member member, Page page, Date before, Date end) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("isblog", "1"));
		criteria.add(Restrictions.ge("createDate", before));
		criteria.add(Restrictions.lt("createDate", end));
		return entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 按栏目获取文章,前台显示
	 * 
	 * @param category
	 * @param page
	 * @return
	 */
	public Page getArticleOfCategory(Category category, Page page) {
		if (category == null || category.getId() == null)
			return getAll(page);
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		category = categoryManager.get(category.getId());
		Set set = getCategoryAllChild(category);
		return entityDao.findByCriteria(page, Restrictions.in("category", set), Restrictions.eq("iscms", "1"));
	}

	/**
	 * 按栏目获取文章,后台显示
	 * 
	 * @param category
	 * @param page
	 * @return
	 */
	public Page getAdminArticleOfCategory(Category category, Page page) {
		if (category == null || category.getId() == null)
			return getAll(page);
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		category = categoryManager.get(category.getId());
		Set set = getCategoryAllChild(category);
		return entityDao.findByCriteria(page, Restrictions.in("category", set), Restrictions.eq("iscms", "1"),
				Restrictions.eq("isblog", "0"));
	}

	/**
	 * 按栏目获取用户发布的文章,后台显示
	 * 
	 * @param category
	 * @param page
	 * @param iscms
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page getAdminBlogArticleOfCategory(Category category, Page page, String iscms, String isblog) {
		if (category == null || category.getId() == null)
			return getAll(page);
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		category = categoryManager.get(category.getId());
		Set set = getCategoryAllChild(category);
		return entityDao.findByCriteria(page, Restrictions.in("category", set), Restrictions.eq("iscms", iscms),
				Restrictions.eq("isblog", isblog));
	}

	/**
	 * 获取栏目的所有子栏目,包括自身
	 * 
	 * @param category
	 * @return
	 */
	private Set<Category> getCategoryAllChild(Category category) {
		Set<Category> rs = new HashSet<Category>();
		rs.add(category);
		for (Category tmp : category.getChild()) {
			rs.addAll(getCategoryAllChild(tmp));
		}
		return rs;
	}

	/**
	 * 根据文章ID集合批量删除文章
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			entityDao.delete(id);
		}
	}

	/**
	 * 根据文章ID集合批量审核文章
	 * 
	 * @param ids
	 */
	public void verifyBatch(Long[] ids, Character iscms) {
		for (Long id : ids) {
			Article art = entityDao.get(id);
			art.setIscms(iscms);
			entityDao.save(art);
		}
	}

	/**
	 * 分页查找栏目文章,针对管理员发布
	 * 
	 * @param id
	 * @param page
	 * @iscms 1表示为CMS数据，0表示为用户的BLOG数据
	 * @return
	 */
	public Page<Article> getArticebyCategory(Long id, Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		return entityDao.findByCriteria(page, Restrictions.eq("category.id", id), Restrictions.eq("iscms", '1'),
				Restrictions.eq("isblog", '0'));
	}

	/**
	 * 分页查找一通过管理员审核的用户发布的日，对应资讯栏目
	 * 
	 * @param id
	 * @param page
	 * @iscms 1表示为CMS数据，0表示为用户的BLOG数据
	 * @return
	 */
	public Page<Article> getBlogArticeByCategory(Long id, Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		return entityDao.findByCriteria(page, Restrictions.eq("category.id", id), Restrictions.eq("iscms", "1"),
				Restrictions.eq("isblog", '1'));
	}

	/**
	 * 获取CMS及用户发布的并通过审核的日志或秀场
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	public Page<Article> getBlogOrCmsArticleByCategory(Long id, Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		return entityDao.findByCriteria(page, Restrictions.eq("category.id", id), Restrictions.eq("iscms", "1"));
	}

	/**
	 * 根据文章的分类获取下一篇文章
	 * 
	 * @param id
	 * @return
	 */
	public Article getNextArticle(Long id) {
		Article article = get(id);
		if (article == null)
			return null;
		Page<Article> page = new Page<Article>(1);
		page = entityDao.findByCriteria(page, Restrictions.gt("id", id), Restrictions.eq("category", article
				.getCategory()));
		if (page.getResult() != null && page.getResult().size() > 0)
			return page.getResult().get(0);
		return null;
	}

	/**
	 * 根据文章的分类获取上一篇文章
	 * 
	 * @param id
	 * @return
	 */
	public Article getPreArticle(Long id) {
		Article article = get(id);
		if (article == null)
			return null;
		Page<Article> page = new Page<Article>(1);
		page.setOrderBy("id");
		page.setOrder(Page.DESC);
		page = entityDao.findByCriteria(page, Restrictions.lt("id", id), Restrictions.eq("category", article
				.getCategory()));
		if (page.getResult() != null && page.getResult().size() > 0)
			return page.getResult().get(0);
		return null;
	}

	/**
	 * 根据会员获取下一篇文章
	 * 
	 * @param id
	 *            文章ID
	 * @return
	 */
	public Article getNextArticleByMember(Long id) {
		Article article = get(id);
		if (article == null)
			return null;
		Page<Article> page = new Page<Article>(1);
		page = entityDao
				.findByCriteria(page, Restrictions.gt("id", id), Restrictions.eq("member", article.getMember()));
		if (page.getResult() != null && page.getResult().size() > 0)
			return page.getResult().get(0);
		return null;
	}

	/**
	 * 根据会员获取上一篇文章
	 * 
	 * @param id
	 *            文章ID
	 * @return
	 */
	public Article getPreArticleByMember(Long id) {
		Article article = get(id);
		if (article == null)
			return null;
		Page<Article> page = new Page<Article>(1);
		page.setOrderBy("id");
		page.setOrder(Page.DESC);
		page = entityDao
				.findByCriteria(page, Restrictions.lt("id", id), Restrictions.eq("member", article.getMember()));
		if (page.getResult() != null && page.getResult().size() > 0)
			return page.getResult().get(0);
		return null;
	}

	/**
	 * 最新blog文章
	 * 
	 * @param rows
	 *            数据
	 * @return
	 */
	public List<Article> getNewBlogArticle(int rows) {
		Page<Article> page = new Page<Article>(rows);

		page.setOrderBy("createDate");
		page.setOrder(page.DESC);
		page = this.entityDao.findByCriteria(page, Restrictions.eq("isblog", "1"));

		return page.getResult();
	}

	/**
	 * 根据栏目获取月，周投票排行榜
	 * 
	 * @param category
	 * @param time
	 *            周，月
	 * @param type
	 *            voteCount:投票，readCount:最热
	 * @param rows
	 *            数据
	 * @return
	 */
	public List<Article> getTopArticleByVoteOrHot(Category category, String time, String type, int rows) {
		Page<Article> page = new Page<Article>(rows);
		Date startTime = null;
		Calendar cl = Calendar.getInstance();
		if ("week".equals(time)) {
			startTime = DateUtil.getFirstDayOfWeek(cl.getTime());
		} else if ("month".equals(time)) {
			startTime = DateUtil.getFirtsDayOfMonth(cl.getTime());
		}
		page.setOrderBy(type);
		page.setOrder(page.DESC);
		if (category.getChild() != null && category.getChild().size() > 0) {
			page = this.entityDao.findByCriteria(page, Restrictions.in("category", category.getChild()), Restrictions
					.ge("createDate", startTime));
		} else {
			page = this.entityDao.findByCriteria(page, Restrictions.eq("category", category), Restrictions.ge(
					"createDate", startTime));
		}
		return page.getResult();
	}

	/**
	 * 首页获取月，周投票的BLOG排行榜
	 * 
	 * @param category
	 * @param time
	 *            周，月
	 * @param type
	 *            voteCount:投票，readCount:最热
	 * @param rows
	 *            数据
	 * @return
	 */
	public List<Article> getTopBlogArticleByVoteOrHot(String time, String type, int rows) {
		Page<Article> page = new Page<Article>(rows);
		Date startTime = null;
		Calendar cl = Calendar.getInstance();
		if ("week".equals(time)) {
			startTime = DateUtil.getFirstDayOfWeek(cl.getTime());
		} else if ("month".equals(time)) {
			startTime = DateUtil.getFirtsDayOfMonth(cl.getTime());
		}
		page.setOrderBy(type);
		page.setOrder(page.DESC);
		page = this.entityDao.findByCriteria(page, Restrictions.ge("createDate", startTime), Restrictions.eq("isblog",
				"1"));

		return page.getResult();
	}

	/**
	 * 新闻 最新发布 投票排行
	 * 
	 * @param category
	 * @param type
	 *            createDate 创建时间 voteCount投票
	 * @param rows
	 *            分页条数
	 * @return
	 */
	public Page<Article> getNewsVote(Category category, String type, Page page) {
		page.setOrderBy(type);
		page.setOrder(page.DESC);
		if (category.getChild() != null && category.getChild().size() > 0) {
			page = this.entityDao.findByCriteria(page, Restrictions.in("category", category.getChild()));
		} else {
			page = this.entityDao.findByCriteria(page, Restrictions.eq("category", category));
		}
		return page;

	}

	/**
	 * 新闻 特别推荐
	 * 
	 * @param category
	 * @param page
	 * @return
	 */
	public Page<Article> getRecommend(Category category, Page page) {
		if (category.getChild() != null && category.getChild().size() > 0) {
			page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0), Restrictions.in("category", category
					.getChild()));
		} else {
			page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0), Restrictions
					.eq("category", category));
		}

		return page;
	}

	/**
	 * 位置：新闻内容页-栏目推荐
	 * 
	 * @param category
	 * @param rows
	 * @return
	 */
	public List<Article> getRecommendCategory(Category category, int rows) {
		Page<Article> page = new Page<Article>(rows);
		if (category.getChild() != null && category.getChild().size() > 0) {
			page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0), Restrictions.in("category", category
					.getChild()));
		} else {
			page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0), Restrictions
					.eq("category", category));
		}
		return page.getResult();
	}

	/**
	 * 位置：新闻内容页-频道精选
	 * 
	 * @param rows
	 * @return
	 */
	public List<Article> getRecommendAll(int rows) {
		Page<Article> page = new Page<Article>(rows);
		page.setOrderBy("amendDate");
		page.setOrder(page.DESC);
		page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0));
		return page.getResult();
	}

	/**
	 * 位置：二级栏目首页-图片新闻 栏目首页图片文章
	 * 
	 * @param category
	 * @return
	 */
	public List<Article> getCategoryHomeArticle(Category category) {
		Page<Article> page = new Page<Article>(10);
		page = entityDao.findByCriteria(page, Restrictions.gt("categoryHome", 0), Restrictions.in("category", category
				.getChild()));
		return page.getResult();
	}

	/**
	 * 位置：搜索 搜索所有新闻
	 * 
	 * @param newsContent
	 * @return
	 */
	public Page getAllSearchArticle(String newsContent, Page page) {
		page = entityDao.findByCriteria(page, Restrictions.like("title", "%" + newsContent + "%"));
		return page;
	}

	/**
	 * 位置：文章页面-相关文章
	 * 
	 * @param tag
	 * @return
	 */
	public List<Article> getTagCorrelationTen(String title) {
		Page<Article> page = new Page<Article>(10);
		if (title != null && !title.equals("")) {
			page = entityDao.findByCriteria(page, Restrictions.like("title", "%" + title + "%"));
		}
		return page.getResult();
	}

	/**
	 * 位置：搜索 按栏目搜索文章
	 * 
	 * @param newsContent
	 * @return
	 */
	public Page getSearchArticle(String newsContent, Category category, Page page) {
		page = entityDao.findByCriteria(page, Restrictions.like("title", "%" + newsContent + "%"), Restrictions.in(
				"category", category.getChild()));
		return page;
	}

	/**
	 * 位置：首页 网站首页图片文章
	 * 
	 * @param category
	 * @return
	 */
	public List<Article> getSiteHomeArticle() {
		Page<Article> page = new Page<Article>(10);
		page = entityDao.findByCriteria(page, Restrictions.gt("siteHome", 0));
		return page.getResult();
	}

	/**
	 * 位置： 推荐文章
	 * 
	 * @return
	 */
	public List<Article> getRecommendArticle() {
		Page<Article> page = new Page<Article>(10);
		page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0));
		return page.getResult();
	}

	/**
	 * 位置：首页-热门文章 首页热门文章
	 * 
	 * @return
	 * @author liudingkun
	 */
	public List<Article> getHotArticle() {
		Page<Article> page = new Page<Article>(4);
		page.setOrderBy("readCount");
		page.setOrder(page.DESC);
		page = entityDao.findByCriteria(page);
		return page.getResult();
	}

	/**
	 * 获取最新发布. iscms 表明是发布到CMS的秀场，以区分用户发布的秀场，但用户发布的秀场经过管理审核后，ISCMS为1，也可以显示前台
	 * 
	 * @return
	 * @author liudingkun
	 * @param category
	 *            拦目id
	 * @param rows
	 *            新闻条数
	 */
	public List<Article> getNewArticle(Category category, int rows) {
		Page<Article> page = new Page<Article>(rows);
		page.setOrderBy("createDate");
		page.setOrder(page.DESC);
		if (category.getChild() != null && category.getChild().size() > 0) {
			page = this.entityDao.findByCriteria(page, Restrictions.in("category", category.getChild()), Restrictions
					.eq("iscms", '1'));
		} else {
			page = this.entityDao.findByCriteria(page, Restrictions.eq("category", category), Restrictions.eq("iscms",
					'1'));
		}
		return page.getResult();
	}

	/**
	 * 位置：页面右边 圈网精选
	 * 
	 * @param category
	 * @return
	 */
	public List<Article> getCircleRecommendArticle(Category category) {
		Page<Article> page = new Page<Article>(20);
		page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0), Restrictions.in("category", category
				.getChild()));
		return page.getResult();
	}

	/**
	 * 随机取新闻 location:contenet
	 * 
	 * @param category
	 * @return
	 */
	public Article getRandomFourArticle(Category category) {
		//		Page<Article> page = new Page<Article>(20);
		//	    page = this.entityDao.findByCriteria(page, Restrictions.eq("category", category)); //防止所有新闻数据存在List，用Page取20条，减少服务器压力，对于以后数据量多了，建议使用。
		List<Article> pagelist = this.entityDao.findByProperty("category", category);

		Article article;
		Random rand = new Random();
		if (pagelist.size() == 0) {
			article = null;
		} else {
			int i = rand.nextInt(pagelist.size());
			if (i != 0) {
				i = i - 1;
			}
			article = pagelist.get(i);
		}
		return article;
	}

	/**
	 * 判断用户是否有该条新闻的权限
	 * 
	 * @param id
	 * @param uid
	 * @return
	 */
	public boolean isMemberArticle(Long id, Member member) {
		boolean bool = false;
		List list = entityDao.findByCriteria(Restrictions.eq("id", id), Restrictions.eq("member", member));
		if (list.size() > 0) {
			bool = true;
		}

		return bool;
	}
}
