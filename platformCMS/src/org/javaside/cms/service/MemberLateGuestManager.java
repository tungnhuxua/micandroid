package org.javaside.cms.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberLateGuest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberLateGuestManager extends DefaultEntityManager<MemberLateGuest, Long>{

	
	/**
	 * 根据用户ID获取最近访客
	 * @param uid
	 * @return
	 */
	public List getMemberLateGuest(Long uid){
		
		String hql = "from MemberLateGuest where uid = "+uid+" order by createDate desc";
		return entityDao.find(hql);
	}
	
	/**
	 * 查询是否有重复最近访客记录
	 * @param uid
	 * @param tomember
	 * @return
	 */
	public MemberLateGuest getOneMemberLateGuest(Long uid ,Member tomember){
		MemberLateGuest memberLateGuest = null;
		List list  = entityDao.findByCriteria(Restrictions.eq("uid", uid),Restrictions.eq("tomember", tomember));
		if(list.size()>0)
			memberLateGuest = (MemberLateGuest) list.get(0);
		
		return memberLateGuest;
	}
}
