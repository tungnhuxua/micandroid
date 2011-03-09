package org.javaside.cms.service;

import java.util.List;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberCircle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberCircleManager extends
		DefaultEntityManager<MemberCircle, Long> {

	/****************************************** blog后台 ***********************************/
	/**
	 * 获取圈子分类列表
	 * 
	 * @return
	 */
	public List getCircleTypeList() {
		String hql = "select id,circleTypeName from MemberCircleType";
		List list = entityDao.find(hql);
		return list;

	}
	/**
	 * 获取用户申请圈子数目
	 * @param uid
	 * @return
	 */
	public int getCircleMemberManagerSize(Long uid){
		String hql = "select id from MemberCircle where uid = " + uid + "";
		List list = entityDao.find(hql);
		return list.size();
	}
	/**
	 * 获取"我的管理圈子"列表
	 * 
	 * @param uid
	 * @return
	 */
	public List getManagerCircle(Page page, Long uid) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName from MemberCircle mc,MemberCircleType mct where mc.circleType = mct.id and mc.uid = "
				+ uid + " and mc.verifyState=1";
		String countHql = "select count(*) from MemberCircle mc,MemberCircleType mct where mc.circleType = mct.id and mc.uid = "
				+ uid + " and mc.verifyState=1";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 获取圈子正式会员
	 * 
	 * @param cid
	 * @return
	 */
	public List getCircleMember(Page page, Long cid, Long uid) {
		String hql = "select m.loginName,m.name,mi.realName,mi.liveProvince,mi.liveCity,mc.circleName,mc.des,mc.id,mc.circleImage,mcu.id,mcu.state,mcu.uid,mi.headPortraitUri from Member m,MemberInfo mi,MemberCircle mc,MemberCircleUser mcu where mc.id = "
				+ cid
				+ " and mc.id = mcu.cid and m.id = mcu.uid and mi.member = mcu.uid and mcu.state = 1 and mcu.status = 0";
		String countHql = "select count(*) from Member m,MemberInfo mi,MemberCircle mc,MemberCircleUser mcu where mc.id = "
				+ cid
				+ " and mc.id = mcu.cid and m.id = mcu.uid and mi.member = mcu.uid and mcu.state = 1 and mcu.status = 0";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 获取圈子非正式会员
	 * 
	 * @param page
	 * @param cid
	 * @return
	 */
	public List getUnCircleMember(Page page, Long cid) {
		String hql = "select m.loginName,m.name,mi.realName,mi.liveProvince,mi.liveCity,mc.circleName,mc.des,mc.id,mc.circleImage,mcu.id,mcu.state,mcu.uid,mi.headPortraitUri from Member m,MemberInfo mi,MemberCircle mc,MemberCircleUser mcu where mc.id = "
				+ cid
				+ " and mc.id = mcu.cid and m.id = mcu.uid and mi.member = mcu.uid and mcu.state = 0";
		String countHql = "select count(*) from Member m,MemberInfo mi,MemberCircle mc,MemberCircleUser mcu where mc.id = "
				+ cid
				+ " and mc.id = mcu.cid and m.id = mcu.uid and mi.member = mcu.uid and mcu.state = 0";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 获取"我加入的圈"列表
	 * 
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMyCircle(Page page, Long uid) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,mcu.id,mcu.circleIndex from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mcu.uid = "
				+ uid + " and mc.id = mcu.cid and mcu.status = 0";
		String countHql = "select count(*) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mcu.uid = "
				+ uid + "  and mc.id = mcu.cid and mcu.status = 0";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * 判断管理圈子是否有权限
	 * 
	 * @param circleId
	 * @param uid
	 * @return
	 */
	public boolean isSecureManagerCircle(Long circleId, Long uid) {
		boolean bool = false;
		String hql = "select id from MemberCircle where id = " + circleId
				+ " and uid = " + uid + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	/**
	 * 判断我加入的圈子是否有权限
	 * 
	 * @param circleId
	 * @param uid
	 * @return
	 */
	public boolean isSecureCircleMember(Long circleId, Long uid) {
		boolean bool = false;
		String hql = "select id from MemberCircleUser where cid = " + circleId
				+ " and uid = " + uid + "";
		List list = entityDao.find(hql);
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}

	// 获取审核通过所有圈子
	public List getBackCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid),mc.commend from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid  group by cid";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	// 获取类型审核通过所有圈子
	public List getBackCircleType(Page page, Long tid) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid),mc.commend from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid and mct.id = "
				+ tid + " group by cid";
		String countHql = "select count(*) from MemberCircle mc,MemberCircleType mct where mc.verifyState = 1 and mc.circleType = mct.id and mct.id = "
				+ tid + "";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	// 按名称查询圈子
	public List serachCircleName(Page page, String circleName) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid),mc.commend from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid and mc.circleName like '%"
				+ circleName + "%' group by cid";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	// 获取未审核所有圈子
	public List getBackAuditingCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,m.name,m.id from MemberCircleType mct,MemberCircle mc,Member m where mc.circleType = mct.id and m.id = mc.uid and mc.verifyState = 0 order by mc.createDate desc";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 0";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/****************************************** blog前台 ***********************************/

	public List getBlogCircle(Page page, Long uid) {
			String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid),m.name from Member m,MemberCircle mc,MemberCircleUser mcu,MemberCircleType mct where  m.id = mc.uid and mc.circleType = mct.id and mcu.cid = mc.id and mcu.cid in (select cid from MemberCircleUser where uid ="+uid+" and circleIndex = 1) group by mcu.cid";
			String countHql = "select count(cid) from MemberCircleUser where uid = " + uid+ "";
			entityDao.find(page, hql);
			// 获取满足条件的总数据条数
			Long count = entityDao.findLong(countHql);
			count = count != null ? count : 0;
			// 把总数据条数赋值给page
			page.setTotalCount(count.intValue());
			return page.getResult();

	}

	/****************************************** 首页 ***********************************/
	/**
	 * location:circle-web.jsp 获取所有圈子
	 */
	public List getAllCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid group by cid";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * location:circle-web.jsp 按照类型获取圈子
	 */
	public List getCircleType(Page page, Long tid) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid and mct.id = "
				+ tid + " group by cid";
		String countHql = "select count(*) from MemberCircle mc,MemberCircleType mct where mc.verifyState = 1 and mc.circleType = mct.id and mct.id = "
				+ tid + "";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * location:circle-web.jsp 特别推荐获取圈子
	 */
	public List getCommendCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid and mc.commend = 1 group by cid";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1 and mc.commend = 1";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * location:circle-web.jsp 最新创建的圈子
	 */
	public List getTimeCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid group by cid order by mc.createDate desc";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1";
		page.setOrderBy("createDate");
		page.setOrder(page.DESC);
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

	/**
	 * location:circle-web.jsp 人气排行的圈子
	 */
	public List getTopCircle(Page page) {
		String hql = "select mc.id,mc.circleName,mc.createDate,mc.updateDate,mc.des,mc.province,mc.city,mc.county,mc.circleImage,mct.circleTypeName,count(mcu.cid) from MemberCircle mc,MemberCircleType mct,MemberCircleUser mcu where mc.circleType = mct.id and mc.id = mcu.cid group by cid order by count(mcu.cid) desc";
		String countHql = "select count(*) from MemberCircle mc where mc.verifyState = 1";
		page.setOrderBy("createDate");
		page.setOrder(page.DESC);
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}

}
