package org.javaside.cms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.HotTag;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class TagManager extends DefaultEntityManager<Tag, Long> {

	/**
	 * 根据标签名查找标签
	 * 
	 * @param name
	 * @return
	 */
	public Tag loadTagByName(String name) {
		return this.entityDao.findUniqueByProperty("name", name);
	}

	/**
	 * 根据标签查询三月内发表或更新过的文章
	 * 
	 * @param tagName
	 * @param page
	 * @return
	 */
	public Page<Article> getArticleByTag(String tagName, Page<Article> page) {
		Tag tag = this.loadTagByName(tagName);
		if (tag != null) {
			Calendar cl = Calendar.getInstance();
			cl.add(Calendar.MONTH, -3); // 三月前的时间
			Query query = entityDao.getSession().createFilter(tag.getArticles(),
					"where this.amendDate>:begin order by this.amendDate");
			query.setDate("begin", cl.getTime());
			query.setFirstResult(page.getFirst());
			query.setMaxResults(page.getPageSize());
			page.setTotalCount(tag.getArticles().size());
			page.setResult(query.list());
		} else {
			page.setTotalCount(0);
		}
		return page;
	}

	/**
	 * 获取热门标签，以三月内文章关联标签的数量为基准。
	 * 
	 * @return
	 */
	public List<HotTag> getHotTag() {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.MONTH, -3); // 三月前的时间
		Criteria criteria = this.entityDao.createCriteria();
		criteria.createAlias("articles", "articles");
		criteria.add(Restrictions.ge("articles.amendDate", cl.getTime()));
		criteria.setProjection(Projections.projectionList().add(Projections.count("id").as("articleNum")).add(
				Projections.groupProperty("id")));
		criteria.addOrder(Order.desc("articleNum"));
		criteria.setMaxResults(10);
		List tmp = criteria.list();
		List<HotTag> hot = new ArrayList<HotTag>();
		if (tmp != null && tmp.size() > 0) {
			for (int i = 0; i < tmp.size(); i++) {
				HotTag htag = new HotTag();
				Object[] obj = (Object[]) tmp.get(i);
				htag.setCount((Integer) obj[0]);
				htag.setTag(this.get((Long) obj[1]));
				hot.add(htag);
			}
		}
		return hot;
	}

	public Page getAllTag(Page page) {
		page.setOrder(page.ASC);
		page.setOrderBy("name");
		return entityDao.findByCriteria(page);
	}

	/**
	 * 会员标签
	 * 
	 * @param page
	 * @return
	 */
	public List<HotTag> getMemberTag(Member member) {
		Criteria criteria = this.entityDao.createCriteria();
		criteria.createAlias("articles", "articles");
		criteria.add(Restrictions.eq("articles.member", member));
		criteria.setProjection(Projections.projectionList().add(Projections.count("id").as("articleNum")).add(
				Projections.groupProperty("id")));
		criteria.addOrder(Order.asc("name"));

		criteria.setMaxResults(100);
		List tmp = criteria.list();
		List<HotTag> hot = new ArrayList<HotTag>();
		Integer total = 0;
		if (tmp != null && tmp.size() > 0) {
			for (int i = 0; i < tmp.size(); i++) {
				HotTag htag = new HotTag();
				Object[] obj = (Object[]) tmp.get(i);
				htag.setCount((Integer) obj[0]);
				htag.setTag(this.get((Long) obj[1]));
				hot.add(htag);
				total += htag.getCount();
			}
			//计算标签字体大小
			for(HotTag ht:hot){
				Float t = (Float.valueOf(String.valueOf(ht.getCount()))*10/Float.valueOf(String.valueOf(total)));
				t = t*2;
				ht.setFontSize(12+t);
			}
		}
		return hot;
	}

	/**
	 * 会员标签文章
	 * 
	 * @param page
	 * @return
	 */
	public Page<Article> getMemberArticleByTag(Member member, Tag tag, Page<Article> page) {
		tag = this.get(tag.getId());
		if (tag != null) {

			Query query = entityDao.getSession().createFilter(tag.getArticles(), "where this.member.id=:member");
			query.setInteger("member", member.getId().intValue());
			query.setFirstResult(page.getFirst());
			query.setMaxResults(page.getPageSize());
			page.setTotalCount(tag.getArticles().size());
			page.setResult(query.list());
		} else {
			page.setTotalCount(0);
		}
		return page;
	}
}
