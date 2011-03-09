package org.javaside.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberDynamics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberDynamicsManager extends DefaultEntityManager<MemberDynamics, Long> {

	
	
	/**
	 * 获取所有用户动态信息
	 * @param page
	 * @return
	 */
	public List getMemberDynamicsList(Page page){
		String hql = "from MemberDynamics order by createDate desc";
		entityDao.find(page, hql);
		return page.getResult();
	}
	
	
	/**
	 * 获取朋友动态 按时间排序
	 */
	public List getMemberDynamicsFriendList(Page page, Long uid){
		String hql = "from MemberDynamics where uid in (select m.id from MemberGroupUser mgu,Member m where  mgu.uid ="
			+ uid + " and m.id = mgu.tid and mgu.friendState = 1) order by createDate desc";
		String countHql = "select count(*) from MemberDynamics where uid in (select m.id from MemberGroupUser mgu,Member m where  mgu.uid ="
			+ uid + " and m.id = mgu.tid and mgu.friendState = 1) order by createDate desc";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 只保存50条用户动态消息
	 * @param member
	 */
	public void delMemberDynamics50(long member){
		String hql = "from MemberDynamics where uid = "+member+" order by createDate desc";
		List list = entityDao.find(hql);
		
		if(list.size()>50){
			MemberDynamics memberDynamics = (MemberDynamics)list.get(50);
			entityDao.delete(memberDynamics.getId());
		}
	}
}
