package org.javaside.cms.web.front;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.HotTag;
import org.javaside.cms.entity.Tag;
import org.javaside.cms.service.ArticleManager;
import org.javaside.cms.service.TagManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class TagAction extends ActionSupport {

	@Autowired
	private TagManager tagManager;
	@Autowired
	private ArticleManager articleManager;

	private Tag tag;
	private Page<Tag> page = new Page<Tag>(20);
	private Page<Article> articlePage = new Page<Article>(20);
	private List<HotTag> hotTags; //热门标签

	@Action("/tag")
	public String tag() throws Exception {
		page = tagManager.getAllTag(page);
		hotTags = tagManager.getHotTag(); //热门标签
		return SUCCESS;
	}

	@Action("/taglist")
	public String taglist() throws Exception {
		articlePage = tagManager.getArticleByTag(tag.getName(), articlePage);
		hotTags = tagManager.getHotTag(); //热门标签
		return SUCCESS;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Page<Tag> getPage() {
		return page;
	}

	public void setPage(Page<Tag> page) {
		this.page = page;
	}

	public Page<Article> getArticlePage() {
		return articlePage;
	}

	public void setArticlePage(Page<Article> articlePage) {
		this.articlePage = articlePage;
	}

	public List<HotTag> getHotTags() {
		return hotTags;
	}

	public void setHotTags(List<HotTag> hotTags) {
		this.hotTags = hotTags;
	}
}
