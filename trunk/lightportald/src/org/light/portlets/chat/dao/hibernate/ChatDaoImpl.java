 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portlets.chat.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portlets.chat.Chatting;
import org.light.portlets.chat.ChattingRecord;
import org.light.portlets.chat.ChattingUser;
import org.light.portlets.chat.dao.ChatDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChatDaoImpl extends BaseDaoImpl implements ChatDao{
	
	public List<ChattingUser> getChattingUsersById(long chattingId){
		List<ChattingUser> list = this.getHibernateTemplate().find("select record from ChattingUser record where record.chattingId =? and record.status>=0 order by record.id", chattingId);		
		return list;
	}
	
	public ChattingUser getChattingUsersById(long chattingId, long userId){
		List<ChattingUser> list = this.getHibernateTemplate().find("select record from ChattingUser record where record.chattingId =? and record.userId=? order by record.id", new Object[] {chattingId,userId});		
		ChattingUser user = null;
		if(list != null && list.size() > 0) user = list.get(0);
		return user;
	}
	
	public List<ChattingRecord> getChattingRecordsById(long chattingId){
		List<ChattingRecord> list = this.getHibernateTemplate().find("select record from ChattingRecord record where record.chattingId =? order by record.id", chattingId);		
		return list;
	}
	public List<ChattingRecord> getChattingRecordsById(long chattingId,int maxRow){
		String hql="select record from ChattingRecord record where record.chattingId ="+chattingId+" order by createDate desc ";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(0)
					.setMaxResults(maxRow);
		List<ChattingRecord> list = query.list();
		session.close();
		List<ChattingRecord> ascList = new LinkedList<ChattingRecord>();
		for(int i= list.size() - 1; i>=0; i--){
			ascList.add(list.get(i));
		}
		return ascList;
	}
	
	public List<ChattingUser> getChattingUsers(long userId){ 
		return this.getHibernateTemplate().find("select c from ChattingUser c where c.userId =?  and c.status =0 order by createDate desc", userId);
	}
	
	public Chatting getChattingByUser(long userId){
		List<ChattingUser> list = getChattingUsers(userId);
		Chatting chatting = null;
		if(list != null && list.size() > 0) {
			ChattingUser cuser = list.get(0);			
			cuser.setStatus(1);
			this.getHibernateTemplate().update(cuser);
			chatting = this.getChattingById(cuser.getChattingId());
		}
		return chatting;
	}
	
	public Chatting getChattingById(long chattingId){
		Chatting chatting= (Chatting)this.getHibernateTemplate().get(Chatting.class,chattingId);
		return chatting;
	}
	public List<Chatting> getChattingByOrgId(long orgId){
		List<Chatting> list = this.getHibernateTemplate().find("select c from Chatting c where c.type =0 and c.orgId =? order by createDate desc ", orgId);
		return list;
	}
	public void leaveChattingByUser(long userId){
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createSQLQuery("update light_chatting_user set status=-1 where userId="+userId);
		query.executeUpdate();
		session.close();
	}
		
	public boolean deleteChatRoom(long id){
		Chatting chatting = this.getChattingById(id);
		if(chatting != null){
			String hql="delete from ChattingRecord where chattingId="+id;
			Session session= this.getHibernateTemplate().getSessionFactory().openSession();
			session.createQuery(hql)
				   .executeUpdate();
			hql="delete from ChattingUser where chattingId="+id;
			session.createQuery(hql)
			   	   .executeUpdate();
			session.close();
			this.delete(chatting);
			return true;
		}
		return false;
	}
}
