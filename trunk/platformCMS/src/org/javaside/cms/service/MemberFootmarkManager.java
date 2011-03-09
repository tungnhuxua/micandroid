package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberFootmark;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberFootmarkManager extends DefaultEntityManager<MemberFootmark, Long> {

	
	/**
	 * 获取用户足迹列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMemberFootmarkList(Page page, Long uid) {
		String hql = "from MemberFootmark where uid=" + uid +" order by createDate desc";
		String countHql = "select count(*) from MemberFootmark where uid = " + uid + "";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}
	
	
	/**
	 * 用户是否有足迹权限 return:true 有权限
	 * 
	 * @param articleid
	 * @param uid
	 * @return
	 */
	public boolean isFootmarkSecure(Long id, Long uid) {
		boolean bool = false;
		String hql = "select id,uid from MemberFootmark where id = " + id + " and uid = " + uid + "";
		if (entityDao.find(hql).size() > 0) {
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 是否已经有足迹记录了 return:true 已经有足迹记录
	 * 
	 * @param articleid
	 * @param uid
	 * @return
	 */
	public boolean isFootmark(Long articleid, Long uid) {
		boolean bool = false;
		String hql = "select id,uid from MemberFootmark where articleid = " + articleid + " and uid = " + uid + "";
		if (entityDao.find(hql).size() > 0) {
			bool = true;
		}
		return bool;
	}
	/**
	 * 足迹最多允许20条记录，超出20记录，按时间排序删除最后的一条
	 * @param uid
	 */
	public void getMemberFootmarkSize(Long uid){
		String Hql = "select id,uid from MemberFootmark where uid = "+uid+" order by createDate desc";
		List list = entityDao.find(Hql);
		
		if(list.size()>20){
			Object[] object = (Object[])list.get(20);
			String id = object[0].toString();
			delete(Long.valueOf(id));
		}
	}
}
