package org.javaside.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Category;
import org.javaside.cms.entity.Comment;
import org.javaside.cms.util.DateUtil;
import org.springframework.stereotype.Service;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
public class CommentManager extends DefaultEntityManager<Comment, Long> {
	/**
	 * 获取文章评论
	 * 
	 * @param article
	 * @param page
	 * @return
	 */
	public Page getCommentsByArticle(Article article, Page page) {
		page.setOrderBy("createDate");
		page.setOrder(Page.DESC);
		return this.entityDao.findByCriteria(page, Restrictions.eq("article", article));
	}

	/**
	 * 热点评论
	 * 
	 * @param time
	 *            week,month.
	 * @return
	 */
	public List<Article> getHotCommentArticleByTime(Category category, String time) {
		Date date = new Date();
		if ("week".equals(time)) {
			date = DateUtil.getFirstDayOfWeek(date);
		} else if ("month".equals(time)) {
			date = DateUtil.getFirtsDayOfMonth(date);
		}
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.ge("createDate", date));
		criteria.createAlias("article", "article");
		//criteria.createAlias("article.category", "category");
		criteria.add(Restrictions.in("article.category", category.getChild()));
		criteria.setProjection(
				Projections.projectionList().add(Projections.count("article").as("articleNum")).add(
						Projections.groupProperty("article"))).addOrder(Order.desc("articleNum"));
		criteria.setMaxResults(10);
		List tmp = criteria.list();
		List<Article> rs = new ArrayList<Article>();
		if (tmp != null && tmp.size() > 0) {
			for (int i = 0; i < tmp.size(); i++) {
				Object[] object = (Object[]) tmp.get(i);
				rs.add((Article) object[1]);
			}
		}
		return rs;
	}
}
