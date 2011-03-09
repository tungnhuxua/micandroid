package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.MemberEnjoy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberEnjoyManager extends DefaultEntityManager<MemberEnjoy, Long> {

	/**
	 * 判断是否已经欣赏过了
	 * 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean isEnjoy(Long uid, Long tid) {
		boolean bool = false;
		String hql = "select uid,tid from MemberEnjoy where uid = " + uid + " and tid = " + tid + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;

	}

	/**
	 * 判断两人是否都被欣赏了，若互相欣赏则加为好友
	 * 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean isTwoEnjoy(Long uid, Long tid) {
		boolean bool = false;
		String hql1 = "select uid,tid from MemberEnjoy where uid = " + uid + " and tid = " + tid + "";
		String hql2 = "select uid,tid from MemberEnjoy where uid = " + tid + " and tid = " + uid + "";
		List list1 = entityDao.find(hql1);
		List list2 = entityDao.find(hql2);

		if (list2.size() == 1) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 获取欣赏匹配的一条记录
	 * 
	 * @return
	 */
	public List getByMemberEnjoy(Long uid, Long tid) {
		String hql = "select id,tid,uid from MemberEnjoy where uid = " + uid + " and tid = " + tid + "";
		List list = entityDao.find(hql);
		return list;

	}
}
