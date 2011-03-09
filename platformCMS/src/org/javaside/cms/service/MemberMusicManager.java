package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberMusic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberMusicManager extends DefaultEntityManager<MemberMusic, Long> {

	/**
	 * 获取音乐排序字段最大值
	 * 
	 * @param uid
	 * @return
	 */
	public Long getMaxMemberMusicOrder(Long uid) {
		String hql = "select max(musicOrder) from MemberMusic where uid = " + uid + "";
		Long maxId = 0l;
		List list = entityDao.find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			maxId = (Long) list.get(0);
		}
		return maxId;
	}

	/**
	 * 获取用户音乐列表
	 * 
	 * @param uid
	 * @return
	 */
	public List getMemberMusicUidList(Page page, Long uid) {
		String hql = "select id,musicName,musicUri,musicOrder from MemberMusic where uid = " + uid
				+ " order by musicOrder desc";
		String countHql = "select count(*) from MemberMusic where uid = " + uid
		+ " order by musicOrder desc";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 获取用户音乐总条数，一个用户只能添加十首音乐
	 * 
	 * @param uid
	 * @return
	 */
	public int getMemberMusicAllRows(Long uid) {
		String hql = "select id from MemberMusic where uid = " + uid + "";
		List list = entityDao.find(hql);
		return list.size();
	}

	/**
	 * 判断是否有音乐管理权限
	 * 
	 * @param uid
	 * @param id
	 * @return
	 */
	public boolean isMemberMusicManager(Long uid, Long id) {
		boolean bool = false;
		String hql = "select id,uid from MemberMusic where uid = " + uid + " and id = " + id + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;

	}
}
