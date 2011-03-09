package org.javaside.cms.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.BlogArticleAccessMember;
import org.javaside.cms.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class BlogArticleAccessMemberManager extends DefaultEntityManager<BlogArticleAccessMember, Long> {

	/**
	 * 根据文章ID获取最近访客
	 * 
	 * @param id
	 * @return
	 */
	public List getMemberLateGuest(Article article) {
		return entityDao.findByCriteria(Restrictions.eq("article", article));
	}

	/**
	 * 查询是否有重复最近访客记录
	 * 
	 * @param uid
	 * @param tomember
	 * @return
	 */
	public BlogArticleAccessMember getOneMemberLateGuest(Article article, Member member) {
		BlogArticleAccessMember memberLateGuest = null;
		List list = entityDao.findByCriteria(Restrictions.eq("article", article), Restrictions.eq("tomember", member));
		if (list.size() > 0)
			memberLateGuest = (BlogArticleAccessMember) list.get(0);

		return memberLateGuest;
	}
}
