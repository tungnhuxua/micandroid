package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberLink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberLinkManager extends DefaultEntityManager<MemberLink, Long> {

	/**********************************后台管理************************************/
	
	/**
	 * 
	 *  获取用户链接列表
	 */
	public List getLinkList(Page page,Long uid){
		String hql = "select id,linkName,linkUri,linkOrder,linkInfo,clickNumer from MemberLink where uid = "+uid+" order by linkOrder ";
		String countHql = "select count(*) from MemberLink where uid = "+uid+" order by linkOrder";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}
	
	/**
	 * 获取用户链接排序字段
	 * 
	 * @param uid
	 * @return
	 */
	public Long getMaxMemberLinkOrder(Long uid) {
		String hql = "select max(linkOrder) from MemberLink where uid = " + uid + "";
		Long maxId = 0l;
		List list = entityDao.find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			maxId = (Long) list.get(0);
		}
		return maxId;
	}
	
	/**
	 * 判断是否有链接管理权限
	 * 
	 * @param uid
	 * @param id
	 * @return
	 */
	public boolean isMemberLinkManager(Long uid, Long id) {
		boolean bool = false;
		String hql = "select id,uid from MemberLink where uid = " + uid + " and id = " + id + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;

	}
	
	/**
	 * 获取用户链接总数，一个用户只允许加20条
	 * 
	 * @param uid
	 * @return
	 */
	public int getMemberLinkAllRows(Long uid) {
		String hql = "select id from MemberLink where uid = " + uid + "";
		List list = entityDao.find(hql);
		return list.size();
	}
}
