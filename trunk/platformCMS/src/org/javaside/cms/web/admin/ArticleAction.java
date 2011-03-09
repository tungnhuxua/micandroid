package org.javaside.cms.web.admin;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Category;
import org.javaside.cms.entity.Tag;
import org.javaside.cms.service.ArticleManager;
import org.javaside.cms.service.CategoryManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.TagManager;
import org.javaside.cms.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( {
		@Result(name = CRUDActionSupport.RELOAD, location = "article.action?page.pageRequest=${page.pageRequest}&categoryTmp.id=${categoryTmp.id}", type = "redirect"),
		@Result(name = "bloglist", location = "article!bloglist.action?page.pageRequest=${page.pageRequest}&categoryTmp.id=${categoryTmp.id}&iscms=${iscms}&isblog=${isblog}", type = "redirect") })
public class ArticleAction extends CRUDActionSupport<Article> {
	@Autowired
	private ArticleManager articleManager;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private TagManager tagManager;

	// 基本属性
	private Article entity;
	private Long id;
	private Long[] ids; //文章ID数组
	private Page<Article> page = new Page<Article>(10);//每页5条记录
	private Category categoryTmp;
	private List<Category> categoryList;
	private Boolean sitHome; //网站首页图片滚动复选框
	private Boolean catHome; //栏目首页图片滚动复选框
	private Boolean recom; //是否是推荐文章
	private String artRandom = ""; //文章随机数
	private String newsContent; //新闻搜索
	private String tagStr; //标签

	private String iscms; //通过审核的用户发表的日志或秀场
	private String isblog; //1:用户日志，2:用户秀场

	private Character cmsflag = '0';

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = articleManager.get(id);
		} else {
			entity = new Article();
		}
	}

	@Override
	public String delete() throws Exception {
		entity = articleManager.get(id);
		if (entity == null || !artRandom.equals(entity.getRandom())) {
			addActionMessage("权限不够，请与管理员联系！");
			return RELOAD;
		}
		articleManager.delete(id);
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		articleManager.deleteBatch(ids);
		return RELOAD;
	}

	/**
	 * 审核用户的日志或秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rerify() throws Exception {
		entity = articleManager.get(id);
		if (entity == null || !artRandom.equals(entity.getRandom())) {
			addActionMessage("权限不够，请与管理员联系！");
			return "bloglist";
		}
		entity.setIscms(cmsflag);
		articleManager.save(entity);
		return "bloglist";
	}

	/**
	 * 审核用户的日志或秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rerifyBatch() throws Exception {
		articleManager.verifyBatch(ids, cmsflag);
		return "bloglist";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		//文章管理的当前 位置中显示改栏目
		if (categoryTmp != null && categoryTmp.getId() != null) {
			categoryTmp = categoryManager.get(categoryTmp.getId());
		}
		page = articleManager.getAdminArticleOfCategory(categoryTmp, page);
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		if (!artRandom.equals(entity.getRandom())) {
			addActionMessage("非法操作!");
			return RELOAD;
		}
		//网站首页图片复选框
		if (sitHome) {
			entity.setSiteHome(1);
		} else {
			entity.setSiteHome(0);
		}
		//栏目图片
		if (catHome) {
			entity.setCategoryHome(1);
		} else {
			entity.setCategoryHome(0);
		}
		//文章推荐
		if (recom) {
			entity.setRecommend(1);
		} else {
			entity.setRecommend(0);
		}

		if (tagStr != null && !"".equals(tagStr)) {
			tagStr = tagStr.replace("，", ",");
			String[] tags = tagStr.split(",");
			Set<Tag> set = new LinkedHashSet<Tag>();
			//entity.getTags().clear();
			for (String tag : tags) {
				Tag tmp = tagManager.loadTagByName(tag);
				if (tmp == null) {
					tmp = new Tag();
					tmp.setName(tag);
				}
				set.add(tmp);
			}
			entity.setTags(set);
		}

		//重新指定文章所属栏目，防止出现修改所属栏目是出现修改栏目ID而报错
		if (categoryTmp != null && categoryTmp.getId() != null) {
			entity.setCategory(categoryTmp);
		}
		if (entity.getId() == null && entity.getMember() == null) {
			//设置文章录入的会员
			entity.setMember(memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()));
		}
		//entity.setCreateDate(new Date());
		entity.setAmendDate(new Date());
		entity.setRandom(RandomStringUtils.random(20, StrUtils.N36_CHARS));//重设随机数
		articleManager.save(entity);
		categoryTmp = entity.getCategory();
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		categoryList = categoryManager.getCategoryRoot();
		if (entity.getCategory() != null) {
			categoryTmp = entity.getCategory();
		} else if (categoryTmp != null && categoryTmp.getId() != null) {
			categoryTmp = categoryManager.get(categoryTmp.getId());
		}
		return INPUT;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		entity = articleManager.get(id);
		return "view";
	}

	/**
	 * 用户列出日志，秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bloglist() throws Exception {
		//文章管理的当前 位置中显示改栏目
		if (categoryTmp != null && categoryTmp.getId() != null) {
			categoryTmp = categoryManager.get(categoryTmp.getId());
		}
		page = articleManager.getAdminBlogArticleOfCategory(categoryTmp, page, iscms, isblog);
		return "blog";
	}

	/**
	 * 新闻搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchNews() throws Exception {
		page = articleManager.getAllSearchArticle(newsContent, page);
		return SUCCESS;
	}

	public Article getModel() {
		return entity;
	}

	public Article getEntity() {
		return entity;
	}

	public void setEntity(Article entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Article> getPage() {
		return page;
	}

	public void setPage(Page<Article> page) {
		this.page = page;
	}

	public Category getCategoryTmp() {
		return categoryTmp;
	}

	public void setCategoryTmp(Category categoryTmp) {
		this.categoryTmp = categoryTmp;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Boolean getSitHome() {
		return sitHome;
	}

	public void setSitHome(Boolean sitHome) {
		this.sitHome = sitHome;
	}

	public Boolean getCatHome() {
		return catHome;
	}

	public void setCatHome(Boolean catHome) {
		this.catHome = catHome;
	}

	public Boolean getRecom() {
		return recom;
	}

	public void setRecom(Boolean recom) {
		this.recom = recom;
	}

	public String getArtRandom() {
		return artRandom;
	}

	public void setArtRandom(String artRandom) {
		this.artRandom = artRandom;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getTagStr() {
		return tagStr;
	}

	public void setTagStr(String tagStr) {
		this.tagStr = tagStr;
	}

	public String getIscms() {
		return iscms;
	}

	public void setIscms(String iscms) {
		this.iscms = iscms;
	}

	public String getIsblog() {
		return isblog;
	}

	public void setIsblog(String isblog) {
		this.isblog = isblog;
	}

	public Character getCmsflag() {
		return cmsflag;
	}

	public void setCmsflag(Character cmsflag) {
		this.cmsflag = cmsflag;
	}
}
