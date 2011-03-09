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

package org.light.portlets.connection.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.util.StringUtil;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.dao.ConnectionDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class ConnectionDaoImpl extends BaseDaoImpl implements ConnectionDao{
	
	public int getBuddyCount(long userId){			
		String hql="select count(*) from Connection buddy where buddy.userId='"+userId+"'";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<Connection> getBuddysByUser(long userId){			
		List<Connection> list = null;
		if(userId > 0)
			list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by createDate desc", userId);		
		return list;
	}
	public List<Connection> getBuddysByUser(long userId, String city, String province){			
		List<Connection> list = null;
		if(userId > 0){
			if(!StringUtil.isEmpty(city) && !StringUtil.isEmpty(province)){
				Object[] params= new Object[3];
				params[0]=userId;
				params[1]=city;
				params[2]=province;
				list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.city=? and buddy.province=? order by createDate desc", params);
			}else if(!StringUtil.isEmpty(province)){
				Object[] params= new Object[2];
				params[0]=userId;
				params[1]=province;
				list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.city is null and buddy.province=? order by createDate desc", params);
			}else if(!StringUtil.isEmpty(city)){
				Object[] params= new Object[2];
				params[0]=userId;
				params[1]=city;
				list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.city=? and buddy.province is null order by createDate desc", params);
			}else{
				list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.city is null and buddy.province is null order by createDate desc", userId);
			}
		}
		return list;
	}
	public List<Connection> getOnlineBuddysByUser(long userId){			
		List<Connection> list = null;
		if(userId > 0)
			list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.buddyCurrentStatusId = 1", userId);		
		
		return list;
	}
	public List<Connection> getUpdatedBuddysByUser(long userId){			
		List<Connection> list = null;
		if(userId > 0)
			list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? order by buddy.lastLoginDate desc", userId);		
		
		return list;
	}
	
	public List<Connection> getBuddysByUserAndType(long userId, int type){
		Object[] params = new Object[2];
		params[0]= userId;
		params[1]= type;
		List<Connection> list = null;
		if(userId > 0)
			list = this.getHibernateTemplate().find("select buddy from Connection buddy where buddy.userId =? and buddy.type =? order by createDate desc", params);		
		
		return list;
	}
		
	public Connection getChatBuddy(long userId, long buddyUserId){
		Connection buddy = null;
		Object[] params = new Object[2];
		params[0] = userId;
		params[1] = buddyUserId;
		List<Connection> list = this.getHibernateTemplate().find("select c from Connection c where c.userId=? and c.buddyUserId =?", params);		
		if(list != null && list.size() > 0) buddy = list.get(0);
		return buddy;
	}
}