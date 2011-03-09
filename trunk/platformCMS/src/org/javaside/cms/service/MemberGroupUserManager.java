package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberGroupUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MemberGroupUserManager extends DefaultEntityManager<MemberGroupUser, Long> {

	/********************************************* 后台 ************************************************************/
	/**
	 * 获取好友列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getFriendList(Page page, Long uid) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mg.groupType,mg.groupName,mgu.tid,mgu.id,mgu.blogIndex from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid + " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and mg.uid = " + uid + "";
		String countHql = "select count(*) from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid + " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and mg.uid = " + uid + "";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 获取在线好友列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getOnlineFriendList(Page page, Long uid) {
		String hql = "select m.id,m.name,mif.realName,mif.headPortraitUri from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and m.online = 1 and mg.uid = "
				+ uid + "";
		entityDao.find(page, hql);
		return page.getResult();
	}

	/**
	 * 获取分类列表
	 * 
	 * @param uid
	 * @return
	 */
	public List getFriendGroupList(Long uid) {
		String hql = "select groupName,groupType from MemberGroup where uid = " + uid + "";
		List list = entityDao.find(hql);
		return list;
	}

	/**
	 * 查询分类好友
	 * 
	 * @param page
	 * @param uid
	 * @param groupType
	 * @return
	 */
	public List getFriendGroupQuery(Page page, Long uid, Long groupType) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mg.groupType,mg.groupName,mgu.tid,mgu.id,mgu.blogIndex from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mg.groupType = "
				+ groupType
				+ " and mg.uid = " + uid + "";
		String countHql = "select count(*) from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid + " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and mg.uid = " + uid + "";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 查询好友
	 * 
	 * @param page
	 * @param uid
	 * @param name
	 * @return
	 */
	public List searchFriend(Page page, Long uid, String name) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mg.groupType,mg.groupName,mgu.tid,mgu.id,mgu.blogIndex from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mgu.uid = "
				+ uid
				+ " and mg.groupType = mgu.groupType and mg.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and m.name like '%"
				+ name
				+ "%'";
		String countHql = "select count(*) from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mgu.uid = "
				+ uid
				+ " and mg.groupType = mgu.groupType and mg.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and m.name like '%"
				+ name
				+ "%'";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 查询粉丝团
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getFansList(Page page, Long uid) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mgu.tid,mgu.id from MemberEnjoy mgu,Member m,MemberInfo mif where  mgu.uid ="
				+ uid + " and m.id = mgu.tid and mgu.tid = mif.member";
		String countHql = "select count(*) from MemberEnjoy mgu,Member m,MemberInfo mif where  mgu.uid =" + uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member";
		entityDao.find(page, hql);

		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 删除好友，查看好友是否在好友列表，才允许删除。否则无权限。
	 * 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean isFriend(Long uid, Long tid) {
		boolean bool = false;
		String hql = "select id from MemberGroupUser where uid = " + uid + " and tid = " + tid + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 查询是否为好友
	 * 
	 * @param uid
	 * @param tid
	 * @return
	 */
	public boolean isFriendRelation(Long uid, Long tid) {
		boolean bool = false;
		String hql = "select id from MemberGroupUser where uid = " + uid + " and tid = " + tid + " and friendState = 1";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 获取好友匹配的一条记录
	 * 
	 * @return
	 */
	public List getByMemberGroupUser(Long uid, Long tid) {
		String hql = "select id,uid from MemberGroupUser where uid = " + uid + " and tid = " + tid + "";
		List list = entityDao.find(hql);
		return list;

	}

	/**
	 * 获取分类最大值
	 * 
	 * @param uid
	 * @return
	 */
	public Long getMaxGroupType(Long uid) {
		String hql = "select max(groupType) from MemberGroup where uid = " + uid + "";
		Long maxId = 0L;
		List list = entityDao.find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			maxId = (Long) list.get(0);
		}
		return maxId;
	}

	/**
	 * 获取申请好友列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getRequestFriend(Page page, Long uid) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mg.groupType,mg.groupName,mgu.uid,mgu.id from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.tid ="
				+ uid
				+ " and m.id = mgu.uid and mgu.uid = mif.member and mgu.friendState = 0  and mg.uid = "
				+ uid
				+ "";
		String countHql = "select count(*) from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.tid ="
				+ uid
				+ " and m.id = mgu.uid and mgu.uid = mif.member and mgu.friendState = 0  and mg.uid = "
				+ uid
				+ "";
		entityDao.find(page, hql);
		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();

	}

	/**
	 * 获取申请好友总人数
	 * 
	 * @param uid
	 * @return
	 */
	public Long getCountRequestFriend(Long tid) {
		String countHql = "select count(*) from MemberGroupUser mgu where mgu.friendState = 0 and mgu.tid = " + tid
				+ "";
		Long count = entityDao.findLong(countHql);
		return count;

	}

	/*************************************** 前台 ***********************************************/

	/**
	 * 获取会员朋友
	 */
	public List getMemberFriend(Page page, Long uid) {
		String hql = "select m.name,mif.realName,mif.gender,mif.liveProvince,mif.liveCity,mif.headPortraitUri,mg.groupType,mg.groupName,mgu.tid,mgu.id from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and mg.uid = "
				+ uid
				+ " and mgu.blogIndex = 1";
		String countHql = "select count(*) from MemberGroup mg,MemberGroupUser mgu,Member m,MemberInfo mif where mg.groupType = mgu.groupType and mgu.uid ="
				+ uid
				+ " and m.id = mgu.tid and mgu.tid = mif.member and mgu.friendState = 1 and mg.uid = "
				+ uid
				+ " and mgu.blogIndex = 1";
		entityDao.find(page, hql);

		//获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		//把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();

	}
}
