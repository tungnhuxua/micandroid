package org.javaside.cms.service;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberTypeManager extends DefaultEntityManager<MemberType, Long> {

	/**
	 * 获取二级分类
	 * 
	 * @param type
	 * @param page
	 * @return
	 */
	public Page getTypeList(MemberType type, Page page) {
		return entityDao.findByCriteria(page, Restrictions.eq("parent", type));
	}

	/**
	 * 根据栏目ID集合批量删除栏目
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

}
