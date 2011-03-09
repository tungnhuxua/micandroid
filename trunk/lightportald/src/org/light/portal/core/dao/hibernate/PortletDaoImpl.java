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

package org.light.portal.core.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.PortletDao;
import org.light.portal.model.FlashGame;
import org.light.portal.model.PopularItem;
import org.light.portal.model.RecommendedItem;
import org.light.portal.model.ViewedItem;
import org.light.portal.model.ViewedItemUser;
import org.light.portlets.internal.InternalNews;
import org.light.portlets.widget.Widget;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletDaoImpl extends BaseDaoImpl implements PortletDao {
		
	public PopularItem getPopularItemById(long id){
		return (PopularItem)this.getHibernateTemplate().get(PopularItem.class, id);
	}
	
	public List<PopularItem> getPopularItems(long orgId,int start, int max, String locale){
       String hql="select pop from PopularItem pop where pop.orgId="+orgId+" and pop.locale ='all' or pop.locale='"+locale+"' order by pop.popCount desc, createDate desc";
       Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(start)
					.setMaxResults(max);
	   List<PopularItem> list = query.list();
	   session.close();
       return list;    
	}
	public int getPopularItemTotal(long orgId,String locale){
		String hql="select count(*) from PopularItem where orgId="+orgId+" and locale ='all' or locale='"+locale+"'";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public PopularItem getPopularItemByLink(long orgId, String link){
	       PopularItem item = null;
	       List<PopularItem> popularItem=this.getHibernateTemplate().find("select pop from PopularItem pop where pop.orgId=? and pop.link= ?", new Object[]{orgId,link});
	       if(popularItem != null && popularItem.size() > 0)
	           item = popularItem.get(0);
	       return item;
	}
	
	public ViewedItem getViewedItemById(long id){
		return (ViewedItem)this.getHibernateTemplate().get(ViewedItem.class, id);
	}
	
	public ViewedItem getViewedItemNext(long id, long userId, String locale){
	   StringBuffer hql=new StringBuffer();
       if(userId > 0){ 
    	   hql.append("select viewed from ViewedItem viewed, ViewedItemUser user where ")
    	      .append("user.personId="+userId)
			  .append(" and ")
			  .append("user.itemId=viewed.id")
			  .append(" and ")
			  .append("viewed.id >="+id)
			  .append(" order by viewed.createDate asc")
			  ;
       }else{
    	   hql.append("select viewed from ViewedItem viewed where ")
    	   	  .append("viewed.locale='"+locale+"'")
    	   	  .append(" and ")
			  .append("viewed.id >="+id)
			  .append(" order by createDate asc")
			  ;
       };
       Session session= this.getHibernateTemplate().getSessionFactory().openSession();
	   Query query =session.createQuery(hql.toString())
					.setFirstResult(0)
					.setMaxResults(1);
	   List<ViewedItem> list = query.list();
	   session.close();
	   ViewedItem item = null;
	   if(list != null && list.size() > 0) item = list.get(0);
       return item;       
	}
	
	public List<ViewedItem> getViewedItems(int start, int max, String locale, long userId){       
       StringBuffer hql=new StringBuffer();
       
       if(userId > 0){ 
    	   hql.append("select viewed from ViewedItem viewed, ViewedItemUser user where ")
    	      .append("user.personId="+userId)
			  .append(" and ")
			  .append("user.itemId=viewed.id")
			  .append(" order by viewed.modifiedDate desc, viewed.createDate desc")
			  ;
       }else{
    	   hql.append("select viewed from ViewedItem viewed where ")
    	   		.append("viewed.locale='"+locale+"'")
    	   		.append(" order by modifiedDate desc, createDate desc")
    	   		;
       }

       Session session= this.getHibernateTemplate().getSessionFactory().openSession();
	   Query query =session.createQuery(hql.toString())
					.setFirstResult(start)
					.setMaxResults(max);
	   List<ViewedItem> list = query.list();
	   session.close();
       return list;       
	}
	public int getViewedItemTotal(String locale, long userId){
		StringBuffer hql=new StringBuffer();
	    
		if(userId > 0){ 
			hql.append("select count(item.id) from ViewedItem item, ViewedItemUser user where ")
			   .append("user.personId="+userId)
			   .append(" and ")
			   .append("user.itemId=item.id")
			   ;
		}else{
			hql.append("select count(*) from ViewedItem where ");
			hql.append("locale='"+locale+"'");
		}
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql.toString());
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
		
	public ViewedItem getViewedItemByLink(String link){
	   ViewedItem item = null;	  
       List<ViewedItem> viewedItem=this.getHibernateTemplate().find("select viewed from ViewedItem viewed where viewed.link= ?", link);
       if(viewedItem != null && viewedItem.size() > 0)
           item = viewedItem.get(0);
       return item;
	}
	public ViewedItemUser getViewedItemUser(long itemId, long userId){
	   ViewedItemUser user = null;
	   Object[] params = new Object[2];
	   params[0] = itemId;
	   params[1] = userId;
       List<ViewedItemUser> viewedUser=this.getHibernateTemplate().find("select viewed from ViewedItemUser viewed where viewed.itemId= ? and viewed.personId=?", params);
       if(viewedUser != null && viewedUser.size() > 0)
    	   user = viewedUser.get(0);
       return user;
	}	
	public List<RecommendedItem> getRecommendedItems(int start,int max, long userId){
		String hql="select pop from RecommendedItem pop where pop.personId="+userId+" and pop.read=0 order by createDate desc"; //pop.weight desc
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
						.setFirstResult(start)
						.setMaxResults(max);
	   List<RecommendedItem> list = query.list();
	   session.close();
       return list;   
	}
	public void deleteRecommendedItems(long userId){
		String hql="delete from RecommendedItem where personId="+userId;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
			   .executeUpdate();
		session.close();
	}
	public int getRecommendedItemTotal( long userId){
		String hql="select count(*) from RecommendedItem where personId="+userId+" and read=0";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count.intValue();
	}
	public RecommendedItem getRecommendedItemById(long id){
		return (RecommendedItem)this.getHibernateTemplate().get(RecommendedItem.class, id);
	}
	public RecommendedItem getRecommendedItemNext(long id, long userId, String locale){
	   StringBuffer hql=new StringBuffer();
	   hql.append("select viewed from RecommendedItem viewed where ")
	      .append("viewed.personId="+userId)
		  .append(" and ")				  
		  .append("viewed.id >="+id)
		  .append(" order by createDate asc");
       Session session= this.getHibernateTemplate().getSessionFactory().openSession();
	   Query query =session.createQuery(hql.toString())
					.setFirstResult(0)
					.setMaxResults(1);
	   List<RecommendedItem> list = query.list();
	   session.close();
	   RecommendedItem item = null;
	   if(list != null && list.size() > 0) item = list.get(0);
       return item;       
	}
	public RecommendedItem getRecommendedItemByLink(String link){
		RecommendedItem item = null;	  
	       List<RecommendedItem> recommendedItem=this.getHibernateTemplate().find("select viewed from RecommendedItem viewed where viewed.link= ?", link);
	       if(recommendedItem != null && recommendedItem.size() > 0)
	           item = recommendedItem.get(0);
	       return item;
	}
	
	
	public List<FlashGame> getFlashGames(long orgId){
	       List<FlashGame> games=this.getHibernateTemplate().find("select game from FlashGame game where game.orgId = ? order by game.popCount asc, createDate desc", orgId);	       
	       return games;
	}
	public Widget getWidgetByPortletId(long portletId){
		 List<Widget> widgets=this.getHibernateTemplate().find("select widget from Widget widget where widget.portletId=?",portletId);	       
	     if(widgets != null && widgets.size() > 0)
	    	 return widgets.get(0);
	     else
	    	 return null;
	}
	
	public InternalNews getInternalNewsById(long id){
		return (InternalNews)this.getHibernateTemplate().get(InternalNews.class,id);
	}
	public List<InternalNews> getInternalNews(long orgId){
		List<InternalNews> news = this.getHibernateTemplate().find("select news from InternalNews news where orgId=? order by createDate desc",orgId);
		return news;
	}
}