package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberCollectionManager extends DefaultEntityManager<MemberCollection, Long> {

	/**
	 * 获取用户收藏列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMemberCollectionList(Page page, Long uid) {
		//	String hql = "select a.id,a.title,a.createDate,a.readCount,a.imageLink,a.category,mc.id,count(c.article) from MemberCollection mc,Comment c, Article a where mc.articleid = a.id and c.article = a.id and c.article = mc.articleid  and mc.uid ="+uid+" group by c.article order by mc.createDate desc,mc.orderid desc";
		String hql = "from MemberCollection where uid=" + uid +" order by createDate desc,orderid desc";
		String countHql = "select count(*) from MemberCollection where uid = " + uid + "";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 是否已经有收藏记录了 return:true 已经有收藏记录
	 * 
	 * @param articleid
	 * @param uid
	 * @return
	 */
	public boolean isCollection(Long articleid, Long uid) {
		boolean bool = false;
		String hql = "select id,uid from MemberCollection where articleid = " + articleid + " and uid = " + uid + "";
		if (entityDao.find(hql).size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 用户是否有权限 return:true 有权限
	 * 
	 * @param articleid
	 * @param uid
	 * @return
	 */
	public boolean isCollectionSecure(Long id, Long uid) {
		boolean bool = false;
		String hql = "select id,uid from MemberCollection where id = " + id + " and uid = " + uid + "";
		if (entityDao.find(hql).size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 获取排序最大值
	 * 
	 * @param uid
	 * @return
	 */
	public Long getMaxMemberCollectionOrder(Long uid) {
		String hql = "select max(orderid) from MemberCollection where uid = " + uid + "";
		Long maxId = 0l;
		List list = entityDao.find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			maxId = (Long) list.get(0);
		}
		return maxId;
	}
}
