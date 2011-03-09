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

package org.light.portlets.ad.dao.hibernate;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portlets.ad.CategoryAd;
import org.light.portlets.ad.dao.AdDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class AdDaoImpl extends BaseDaoImpl implements AdDao{
	
	public CategoryAd getAdById(int id){
		return (CategoryAd)this.getHibernateTemplate().get(CategoryAd.class, id);
	}
	
	public List<CategoryAd> getAdsByType(String type, int showNumber){
		Date currentDate = new Date(System.currentTimeMillis());
		String hql="from CategoryAd ad where ad.status=1 and ad.showDate <='"+currentDate+"' and ad.endEffDate >='"+currentDate+"' order by createDate desc";//type=1, newest 
		if("2".equals(type))//most popular
			hql = "from CategoryAd ad where ad.status=1 and ad.showDate <='"+currentDate+"' and ad.endEffDate >='"+currentDate+"' order by score desc, createDate desc" ;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql)
					.setFirstResult(0)
					.setMaxResults(showNumber);
		List<CategoryAd> list = query.list();
		session.close();
		return list;
	}
	
	public List<CategoryAd> getAdsByCategory(int category, int showNumber,String country,String province,String city){
		Date currentDate = new Date(System.currentTimeMillis());
		StringBuffer hql= new StringBuffer();
		hql.append("from CategoryAd ad where ad.category="+category+" and ad.showDate <='"+currentDate+"' and ad.endEffDate >='"+currentDate+"'");
		if(country != null && country.trim().length() > 0)
			hql.append(" and ad.country='"+country+"'");
		if(province != null && province.trim().length() > 0)
			hql.append(" and ad.province='"+province+"'");
		if(city != null && city.trim().length() > 0)
			hql.append(" and ad.city='"+city+"'");
		hql.append(" order by createDate desc");		
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql.toString())
							.setFirstResult(0)
							.setMaxResults(showNumber);
		if(showNumber == 0)
			query = session.createQuery(hql.toString());
		List<CategoryAd> list = query.list();
		session.close();
		return list;
	}
	
}
