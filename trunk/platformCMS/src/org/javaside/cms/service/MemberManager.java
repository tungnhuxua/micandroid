package org.javaside.cms.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.ServiceException;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberManager extends DefaultEntityManager<Member, Long> {

	public Page getColumnsMember(Page page) {
		return this.entityDao.findByCriteria(page, Restrictions.eq("state", 1), Restrictions.eq("colPce", 1));
	}

	/**
	 * 获取大侠
	 * 
	 * @param page
	 * @return
	 */
	public Page getHeroMember(Page page) {
		return this.entityDao.findByCriteria(page, Restrictions.eq("state", 1), Restrictions.isNotNull("memberType"));
	}

	/**
	 * 获取指定类型的会员
	 * 
	 * @param page
	 * @param type
	 * @return
	 */
	public Page getMemberByType(Page page, MemberType type) {
		if (type == null)
			return page;
		return this.entityDao.findByCriteria(page, Restrictions.eq("state", 1), Restrictions.eq("memberType", type));
	}

	/**
	 * 最新加入
	 * 
	 * @param page
	 * @return
	 */
	public Page getNewMember(Page page) {
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		return this.entityDao.findByCriteria(page, Restrictions.eq("state", 1));
	}

	/**
	 * 人气排行
	 * 
	 * @param page
	 * @return
	 */
	public Page getHotMember(Page page) {
		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("state", 1));
		criteria.createAlias("info", "info");
		//	criteria.createAlias("accessing", "accessing");
		page.setOrder(page.DESC);
		page.setOrderBy("info.accessing");
		return this.entityDao.findByCriteria(page, criteria);
	}

	/**
	 * 所有激活会员
	 * 
	 * @param page
	 * @return
	 */
	public Page getAllMember(Page page) {

		return this.entityDao.findByCriteria(page, Restrictions.eq("state", 1));
	}

	public Member getMemberByLoginName(String name) {
		return this.entityDao.findUniqueByProperty("loginName", name);
	}

	public Member getMemberId(Long uid) {
		return this.entityDao.findUniqueByProperty("id", uid);
	}

	/**
	 * 重载delte函数,演示异常处理及用户行为日志.
	 */
	@Override
	public void delete(Long id) {
		if (id.intValue() == 1) {
			logger.warn("操作员{}尝试删除超级管理员用户", SpringSecurityUtils.getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}

		entityDao.delete(id);
	}

	/**
	 * 根据ID集合批量删除会员
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			entityDao.delete(id);
		}
	}

	/**
	 * 检查用户名是否唯一.
	 * 
	 * @return loginName在数据库中唯一或等于orgLoginName时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isLoginNameUnique(String loginName, String orgLoginName) {
		return entityDao.isPropertyUnique("loginName", loginName, orgLoginName);
	}

	/**
	 * 用户搜索
	 * 
	 * @param page
	 * @param username
	 * @return
	 */
	public Page getSearchUserName(Page page, String username, String searchType) {
		if (searchType.equals("1")) {
			page = entityDao.findByCriteria(page, Restrictions.like("loginName", "%" + username + "%")); //登录名搜索
		} else {
			page = entityDao.findByCriteria(page, Restrictions.like("name", "%" + username + "%")); //姓名搜索
		}
		return page;

	}

	/**
	 * 用户非会员注册验证
	 * 
	 * @param uid
	 * @param usercode
	 * @return
	 */
	public boolean isUidUsercode(Long uid, String usercode) {
		boolean bool = false;
		List list = entityDao.findByCriteria(Restrictions.eq("id", uid), Restrictions.eq("usercode", usercode));
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 登录名唯一
	 * 
	 * @param loginName
	 * @return
	 */
	public boolean isLoginName(String loginName) {
		boolean bool = true;
		List list = entityDao.findByCriteria(Restrictions.eq("loginName", loginName));
		if (list.size() > 0) {
			bool = false;
		}
		return bool;
	}
}
