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

package org.light.portlets.blog.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.model.Organization;
import org.light.portal.model.User;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.BlogCategory;
import org.light.portlets.blog.dao.BlogDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlogDaoImpl extends BaseDaoImpl implements BlogDao{
	
	public void createUserBlog(User user){
		
	}
	
	public int getBlogsTotalByUser(long userId, Organization org){
		StringBuilder buffer = new StringBuilder();
		buffer.append("select count(*) from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(org.getId())
			  ;
		if(userId == org.getUserId())
			buffer.append(" and b.status=1 ");
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<Blog> getBlogsByUser(long userId,Organization org){
		List<Blog> list = null;
		StringBuilder buffer = new StringBuilder();
		buffer.append("select b from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(org.getId())
			  ;
		if(userId == org.getUserId())
			buffer.append(" and b.status=1 ");
		buffer.append(" order by createDate desc ");		
		list= this.getHibernateTemplate().find(buffer.toString());
		return list;
	}
	
	public List<Blog> getBlogsByUser(long userId,Organization org,String sort,int start,int end){
		List<Blog> list = null;
		StringBuilder buffer = new StringBuilder();
		buffer.append("select b from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(org.getId())
			  ;
		if(userId == org.getUserId())
			buffer.append(" and b.status=1 ");
		if(sort != null && sort.equals("2"))
			buffer.append(" order by b.score desc ");		
		else
			buffer.append(" order by createDate desc ");		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString())
					.setFirstResult(start)
					.setMaxResults(end);
					;
		list = query.list();
		session.close();
		return list;
	}
	public int getBlogsTotalByVisitor(long userId, long orgId){
		StringBuilder buffer = new StringBuilder();
		buffer.append("select count(*) from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(orgId)
			  .append(" and b.status=1 ")
			  ;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<Blog> getBlogsByVisitor(long userId,long orgId){
		List<Blog> list = null;
		StringBuilder buffer = new StringBuilder();
		buffer.append("select b from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(orgId)
			  .append(" and b.status=1 ")
			  .append("order by createDate desc ");		
		list= this.getHibernateTemplate().find(buffer.toString());
		return list;
	}
	
	public List<Blog> getBlogsByVisitor(long userId,long orgId,String sort,int start,int end){
		List<Blog> list = null;
		StringBuilder buffer = new StringBuilder();
		buffer.append("select b from Blog b where (b.userId=")
			  .append(userId)
			  .append(" or b.postedById=")
			  .append(userId)
			  .append(") and b.orgId=")
			  .append(orgId)
			  .append(" and b.status=1 ")
			  ;
		if(sort != null && sort.equals("2"))
			buffer.append(" order by b.score desc ");		
		else
			buffer.append(" order by createDate desc ");		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString())
					.setFirstResult(start)
					.setMaxResults(end);
					;
		list = query.list();
		session.close();
		return list;
	}
	public int getBlogsTotal(long orgId){
		StringBuilder buffer = new StringBuilder();
		buffer.append("select count(*) from Blog b where b.orgId=")
			  .append(orgId)
			  .append(" and b.status=1 ")
			  ;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public List<Blog> getBlogs(long orgId){
		return this.getBlogs(orgId,null);
	}
	public List<Blog> getBlogs(long orgId,String sort){
		String hql="from Blog blog where blog.orgId=? and blog.status=1 order by createDate desc";//type=1, newest 
		if("2".equals(sort))//most popular
			hql = "from Blog blog where blog.orgId=? and blog.status=1 order by score desc, createDate desc" ;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setLong(0,orgId);
		List<Blog> list = query.list();
		session.close();
		return list;
	}
	
	public List<Blog> getBlogs(long orgId,String sort, int start, int end){	
		String hql="from Blog blog where blog.orgId=? and blog.status=1 order by createDate desc";//type=1, newest
		if(sort != null && sort.equals("2"))
			hql="from Blog blog where blog.orgId=? and blog.status=1 order by score desc";//type=2, most popular
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setLong(0,orgId)
					.setFirstResult(start)
					.setMaxResults(end);
					;
		List<Blog> list = query.list();
		session.close();
		return list;
	
	}
	
	public Blog getBlogById(long id){
		return (Blog)this.getHibernateTemplate().get(Blog.class, id);
	}
	
	public List<BlogCategory> getBlogCategoriesByUser(long userId){	
		return this.getHibernateTemplate().find("from BlogCategory b where b.userId=?",userId);
	}
		
	public int getBlogsTotalByCategory(long categoryId){
		StringBuilder buffer = new StringBuilder();
		buffer.append("select count(*) from Blog b where b.categoryId=")
			  .append(categoryId)
			  .append(" and b.status=1 ")
			  ;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(buffer.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	public List<Blog> getBlogsByCategory(long categoryId, String sort, int start, int end){	
		String hql="from Blog blog where blog.categoryId=? and blog.status=1 order by createDate desc";//type=1, newest
		if(sort != null && sort.equals("2"))
			hql="from Blog blog where blog.orgId=? and blog.status=1 order by score desc";//type=2, most popular
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setLong(0,categoryId)
					.setFirstResult(start)
					.setMaxResults(end);
					;
		List<Blog> list = query.list();
		session.close();
		return list;
	
	}
}
