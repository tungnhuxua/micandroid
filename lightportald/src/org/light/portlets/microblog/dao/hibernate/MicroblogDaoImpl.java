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

package org.light.portlets.microblog.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portlets.microblog.Microblog;
import org.light.portlets.microblog.dao.MicroblogDao;

/**
 * 
 * @author Jianmin Liu
 **/

public class MicroblogDaoImpl extends BaseDaoImpl implements MicroblogDao {
	
	public int getPostCountByUser(long userId){
		String hql="select count(*) from Microblog where userId="+userId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<Microblog> getPostsByUser(long userId,int start,int end){
		return getPosts("from Microblog where userId="+userId+" order by createDate desc",start,end);
	}
	
	public int getPostCountByUserAndFriends(long userId, long[] userIds){
		if(userIds == null || userIds.length == 0)
			return getPostCountByUser(userId);
		else if(userIds.length == 1)
			return this.getHibernateTemplate().find("select distinct mblog from Microblog mblog where userId="+userId+" or (userId = postById and postById ="+userIds[0]+")").size();
		else{
			StringBuilder buffer = new StringBuilder();
			for(long id : userIds){
				if(buffer.length()>0) buffer.append(",");
				buffer.append(id);					  
			}
			return this.getHibernateTemplate().find("select distinct mblog from Microblog mblog where userId="+userId+" or (userId = postById and postById in ("+buffer.toString()+"))").size();
		}
	}
	
	public List<Microblog> getPostsByUserAndFriends(long userId, long[] userIds,int start,int end){
		if(userIds == null || userIds.length == 0)
			return getPostsByUser(userId,start,end);
		else if(userIds.length == 1)
			return getPosts("select distinct mblog from Microblog mblog where userId="+userId+" or (userId = postById and postById ="+userIds[0]+") order by createDate desc",start,end);
		else{
			StringBuilder buffer = new StringBuilder();
			for(long id : userIds){
				if(buffer.length()>0) buffer.append(",");
				buffer.append(id);					  
			}
			return getPosts("select distinct mblog from Microblog mblog where userId="+userId+" or (userId = postById and postById in ("+buffer.toString()+")) order by createDate desc",start,end);
		}
	}
	
	public int getPostCountByFriends(long[] userIds){
		if(userIds != null && userIds.length > 0){
			if(userIds.length == 1)
				return this.getHibernateTemplate().find("select mblog from Microblog mblog where userId = postById and postById ="+userIds[0]).size();
			else{
				StringBuilder buffer = new StringBuilder();
				for(long id : userIds){
					if(buffer.length()>0) buffer.append(",");
					buffer.append(id);					  
				}
				return this.getHibernateTemplate().find("select mblog from Microblog mblog where userId = postById and postById in ("+buffer.toString()+")").size();				
			}
		}
		return 0;
	}
	
	public List<Microblog> getPostsByFriends(long[] userIds,int start,int end){
		if(userIds != null && userIds.length > 0){
			if(userIds.length == 1)
				return getPosts("select mblog from Microblog mblog where userId = postById and postById ="+userIds[0]+" order by createDate desc",start,end);
			else{
				StringBuilder buffer = new StringBuilder();
				for(long id : userIds){
					if(buffer.length()>0) buffer.append(",");
					buffer.append(id);					  
				}
				return getPosts("select mblog from Microblog mblog where userId = postById and postById in ("+buffer.toString()+") order by createDate desc",start,end);				
			}
		}
		return null;
	}
	
	protected List<Microblog> getPosts(String hql,int start,int end){
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(end);
		List<Microblog> list = query.list();
		session.close();		
		return list;
	}
	
	public Microblog getMicroblogById(long id){
		return (Microblog)this.getHibernateTemplate().get(Microblog.class,id);
	}
}
