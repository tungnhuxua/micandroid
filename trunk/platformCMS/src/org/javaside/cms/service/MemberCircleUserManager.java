package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.MemberCircleUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberCircleUserManager extends DefaultEntityManager<MemberCircleUser, Long> {
	/**
	 * 判断该用户是否加入圈子
	 * 
	 * @param uid
	 * @param cid
	 * @return
	 */
	public boolean isEnterCircle(Long uid, Long cid) {
		boolean bool = false;
		String hql = "select id from MemberCircleUser where uid = " + uid + " and cid =" + cid + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 获取相关联的圈子用户，全部删除
	 * 
	 * @param id
	 * @return
	 */
	public List getRelatingMemberCircleUser(Long id) {
		String hql = "select id,cid from MemberCircleUser where cid = " + id + "";
		List list = entityDao.find(hql);
		return list;
	}
	

}
