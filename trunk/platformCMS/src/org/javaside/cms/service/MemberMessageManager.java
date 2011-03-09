package org.javaside.cms.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberMessageManager extends DefaultEntityManager<MemberMessage, Long> {



	/**
	 * 获取用户留言信息
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMemberMessage(Page page, Long uid){
		
		String hql = "from MemberMessage where type = 1 and uid = "+uid+" order by createDate desc";
		String countHql = "select count(*) from MemberMessage where type = 1 and uid = "+uid+"";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}
	
	/**
	 * 根据用户回复信息，获取留言
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMemberMyMessage(Long id){
		
		String hql = "from MemberMessage where id = "+id+"";
		List list = entityDao.find(hql);
		return list;
	}
	
	/**
	 * 未查看留言统计
	 */
	public int getQuietlyCount(Long uid){
		String hql ="from MemberMessage where uid = "+uid+" and quietly = 1";
		List list = entityDao.find(hql);
		return list.size();
	}
	/**
	 * 获取自己留言的回复
	 * @param uid
	 * @return
	 */
	public List getMemberMessageAnswer( Long uid){
		String hql = "from MemberMessage where type = 2 and uid = "+uid+" order by createDate desc";
		return entityDao.find( hql);
	}
	
	/**
	 * 获取自己留言的回复
	 * @param uid
	 * @return
	 */
	public List getMemberMessageAnswerId(Long connectionId){
		String hql = "select id,uid from MemberMessage where connectionId = "+connectionId+"";
		return entityDao.find(hql);
	}
	
	/**
	 * 用户是否有操作留言权限 return:true 有权限
	 * 
	 * @param articleid
	 * @param uid
	 * @return
	 */
	public boolean isMessageSecure(Long id, Long uid) {
		boolean bool = false;
		String hql = "select id,uid from MemberMessage where id = " + id + " and uid = " + uid + "";
		if (entityDao.find(hql).size() > 0) {
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 取得用户留言设置类型
	 * @param uid
	 * @return
	 */
	public int getMemberMessageSetup(Long uid){
		String hql = "select messageType from MemberMessageSetup where  uid = "+uid+"";
		List list = entityDao.find(hql);
		int messageType = 1;
		if(list != null && list.size() > 0 && list.get(0) != null){
			messageType = (Integer)list.get(0);
		}
		return messageType;
	}
	
	/**
	 * 取得用户最后一次发布时间，防止灌水
	 * @param uid
	 * @return
	 */
	public Date timeSeconds(Long member){
		String hql = "select max(createDate) from MemberMessage where tid = "+member+"";
		List list = entityDao.find(hql);
		Date date = new Date();
		if(list != null && list.size() > 0 && list.get(0) != null){
			date = (Date)list.get(0);
		}else{
			date = new Date(new Date().getTime()-1000*60);
		}
		return date;
	}
	/**
	 * 将未查看的留言设置已查看
	 * @param uid
	 */
	public void setupExamine(Long uid){
		String hql ="from MemberMessage where uid = "+uid+" and examine = 1 and type = 1";
		List list = entityDao.find(hql);
		if(list != null && list.size() > 0 && list.get(0) != null){
			for(int i=0; i<list.size();i++){
				MemberMessage memberMessage = (MemberMessage)list.get(i);
				memberMessage.setExamine(0);
				save(memberMessage);
			}
		}
	}
	
	/**
	 * 未查看留言统计
	 */
	public int notExamineCount(Long uid){
		String hql ="from MemberMessage where uid = "+uid+" and examine = 1 and type = 1";
		List list = entityDao.find(hql);
		return list.size();
	}
	
	/**
	 * 未查看留言回复统计
	 */
	public int notAnswerCount(Member tid){
		List list = entityDao.findByCriteria(Restrictions.eq("member", tid),Restrictions.eq("examine", 1),Restrictions.eq("type", 2l));
		return list.size();
	}
	
	/**
	 * 未查看留言回复
	 * @param page
	 * @param tid
	 * @return
	 */
	public Page getnotAnswer(Page page, Member tid){

		Criteria criteria = entityDao.createCriteria();
		criteria.add(Restrictions.eq("member", tid));
		criteria.add(Restrictions.eq("examine", 1));
		criteria.add(Restrictions.eq("type", 2l));
		criteria.addOrder(Order.desc("createDate"));
		return entityDao.findByCriteria(page, criteria);
	}
	
	/**
	 * 将未查看的留言回复设置已查看
	 * @param uid
	 */
	public void setupNotAnser(Member tid){
		List list = entityDao.findByCriteria(Restrictions.eq("member", tid),Restrictions.eq("examine", 1),Restrictions.eq("type", 2l));
		if(list != null && list.size() > 0 && list.get(0) != null){
			for(int i=0; i<list.size();i++){
				MemberMessage memberMessage = (MemberMessage)list.get(i);
				memberMessage.setExamine(0);
				save(memberMessage);
			}
		}
	}
	
	/***********************前台***************************/
	/**
	 * 获取用户留言信息
	 * @param page
	 * @param uid
	 * @return
	 */
	public List getMemberMessageFront(Page page, Long uid){
		
		String hql = "from MemberMessage where type = 1 and uid = "+uid+" and quietly = 0 order by createDate desc";
		String countHql = "select count(*) from MemberMessage where type = 1 and uid = "+uid+" and quietly = 0";
		entityDao.find(page, hql);
		// 获取满足条件的总数据条数
		Long count = entityDao.findLong(countHql);
		count = count != null ? count : 0;
		// 把总数据条数赋值给page
		page.setTotalCount(count.intValue());
		return page.getResult();
	}
}
