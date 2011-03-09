package org.javaside.cms.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.ShowCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class ShowCategoryManager extends DefaultEntityManager<ShowCategory, Long> {

	/**
	 * 验证分类名是否唯一，相当同一会员。唯一返回TRUE,否则返回false
	 * 
	 * @param member
	 * @param categoryName
	 * @return
	 */
	public boolean uniqueCategory(Member member, String categoryName) {
		Criteria criteria = this.entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("name", categoryName));
		List rs = criteria.list();
		if (rs != null && rs.size() > 0)
			return false;

		return true;
	}

	/**
	 * 获取分类
	 * 
	 * @param member
	 * @return
	 */
	public List<ShowCategory> getShowCate(Member member) {
		return this.entityDao.findByCriteria(Restrictions.eq("member", member));
	}
}
