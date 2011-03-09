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

package org.light.portlets.forum.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.forum.dao.ForumDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumDaoImpl extends BaseDaoImpl implements ForumDao{
	
	public List<ForumPost> getTopics(long orgId){
		List<ForumPost> list = this.getHibernateTemplate().find("select forum from ForumPost forum where forum.orgId=? and forum.topId=0 order by createDate desc ",orgId);		
		return list;
	}
	public List<ForumPost> getPosts(long orgId){
		List<ForumPost> list = this.getHibernateTemplate().find("select forum from ForumPost forum where forum.orgId=? order by createDate desc ",orgId);		
		return list;
	}
	
	public int getTopicsCountByForum(long forumId){
		String hql="select count(*) from ForumPost forum where forum.forumId="+forumId+" and forum.topId= 0 ";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<ForumPost> getTopicsByForum(long forumId,int pageId,int maxRow){
		String hql="select forum from ForumPost forum where forum.forumId="+forumId+" and forum.topId= 0 order by createDate desc ";
		int first = pageId * maxRow;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(first)
					.setMaxResults(maxRow);
		List<ForumPost> list = query.list();
		session.close();
		return list;
		
	}
	
	public int getPostsCountByTopic(long topicId){
		String hql="select count(*) from ForumPost forum where forum.topId="+topicId+" or forum.id= "+topicId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<ForumPost> getPostsByTopic(long topicId,int pageId,int maxRow){
		String hql="select forum from ForumPost forum where forum.topId="+topicId+" or forum.id= "+topicId+" order by createDate asc";
		int first = pageId * maxRow;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(first)
					.setMaxResults(maxRow);
		List<ForumPost> list = query.list();
		session.close();		
		return list; 
	}
	
	public List<ForumPost> getPostsByTopic(long topicId){
		String hql="select forum from ForumPost forum where forum.topId="+topicId+" or forum.id= "+topicId+" order by createDate asc";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		List<ForumPost> list = query.list();
		session.close();		
		return list; 
	}
	
//	public void deleteTopic(long topicId){
//		String hql="delete ForumPost where topId="+topicId+" or id= "+topicId;
//		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
//		session.createQuery(hql)
//		 	   .executeUpdate();
//		session.close();
//		
//	}
	public ForumPost getPostById(long id){
		return (ForumPost)this.getHibernateTemplate().get(ForumPost.class, id);
	}
		
	public ForumCategory getForumCategoryById(long id){
		return (ForumCategory)this.getHibernateTemplate().get(ForumCategory.class, id);
	}
	
	public Forum getForumById(long id){
		return (Forum)this.getHibernateTemplate().get(Forum.class, id);
	}
	
	public List<ForumCategory> getForumCategories(String lanuage, long orgId){
		List<ForumCategory> list = this.getHibernateTemplate().find("select forum from ForumCategory forum where forum.language=? and forum.orgId=? and forum.status=1 order by createDate asc ",new Object[]{lanuage,orgId});		
		for(ForumCategory c : list){
			if(c.getLastForumId() != null){
				c.setLastForum(this.getPostById(Integer.parseInt(c.getLastForumId())));
			}
		}
		return list;
	}
	
	public List<Forum> getForumByCategory(long categoryId){
		ForumCategory category = (ForumCategory)this.getHibernateTemplate().get(ForumCategory.class, categoryId);
		List<Forum> list = this.getHibernateTemplate().find("select forum from Forum forum where forum.category=? and forum.status=1 order by createDate asc ",category);		
		for(Forum c : list){
			if(c.getLastForumId() != null){
				c.setLastForum(this.getPostById(Integer.parseInt(c.getLastForumId())));
			}
		}
		return list;
	}
	
}
