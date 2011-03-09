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

package org.light.portlets.calendar.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.light.portal.core.PortalContextFactory;
import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.util.DateUtil;
import org.light.portlets.calendar.CalendarBean;
import org.light.portlets.calendar.CalendarEvent;
import org.light.portlets.calendar.CalendarHoliday;
import org.light.portlets.calendar.dao.CalendarDao;

/**
 * 
 * @author Jianmin Liu
 **/
public class CalendarDaoImpl extends BaseDaoImpl implements CalendarDao{
	
	public Map<String,CalendarHoliday> holidays;
	
	public CalendarBean getCalendarByUser(long userId){
		List<CalendarBean> list = this.getHibernateTemplate().find("select c from CalendarBean c where c.userId= ?", userId);
		CalendarBean calendar = null;
		if(list != null && list.size() > 0) calendar = list.get(0);
		return calendar;
	}
	
	public CalendarEvent getCalendarEventById(long id){
		return (CalendarEvent)this.getHibernateTemplate().get(CalendarEvent.class, id);
	}
		
	public List<CalendarEvent> getCalendarEventsByDate(Date date,long userId){
		//String start = DateFormatter.format(date,"yyyy-MM-dd");
		Object[] params = new Object[2];
		params[0] = date;
		params[1] = userId;
		List<CalendarEvent> list = this.getHibernateTemplate().find("select event from CalendarEvent event where event.startDate =? and event.userId= ? order by event.startTime", params);		
		List<CalendarHoliday> holidays = this.getCalendarHoliday(date);
		if(holidays != null){
			for(CalendarHoliday holiday : holidays){
				list.add(0,new CalendarEvent(PortalContextFactory.getPortalContext().getMessageByKey(holiday.getName()),holiday.getCountry(),holiday.getDesc(),holiday.getLink(),null,10000,null,10000,0,0,3));
			}
		}		
		return list;
	}
	
	public List<CalendarEvent> getCalendarEventsByOrgDate(Date date,long orgId){
		String start = DateUtil.format(date,"yyyy-MM-dd");
		Object[] params = new Object[2];
		params[0] = start;
		params[1] = orgId;
		List<CalendarEvent> list = this.getHibernateTemplate().find("select event from CalendarEvent event where event.startDate =? and event.orgId= ? order by event.startTime", params);		
		List<CalendarHoliday> holidays = this.getCalendarHoliday(date);
		if(holidays != null){
			for(CalendarHoliday holiday : holidays){
				list.add(0,new CalendarEvent(PortalContextFactory.getPortalContext().getMessageByKey(holiday.getName()),holiday.getCountry(),holiday.getDesc(),holiday.getLink(),null,10000,null,10000,0,0,3));
			}
		}		
		return list;
	}
	
	public Map<String,CalendarHoliday> getCalendarHolidays(){
		if(holidays == null){
			holidays = new HashMap<String, CalendarHoliday>();
			List<CalendarHoliday> list = this.getHibernateTemplate().find("select event from CalendarHoliday event  order by event.holiday");
			for(CalendarHoliday holiday : list){
				holidays.put(holiday.getHoliday(), holiday);
			}
		}
		return holidays;
	}
	public List<CalendarHoliday> getCalendarHoliday(Date date){
		if(holidays == null){
			getCalendarHolidays();
		}
		CalendarHoliday holiday2 = holidays.get(DateUtil.format(date,"yyyy-MM-dd"));
		CalendarHoliday holiday1 = holidays.get(DateUtil.format(date,"MM-dd"));
		List<CalendarHoliday> list = null;
		if(holiday1 != null){
			list = new ArrayList<CalendarHoliday>();
			list.add(holiday1);			
		}
		if(holiday2 != null){
			if(list == null)
				list = new ArrayList<CalendarHoliday>();
			list.add(holiday2);			
		}
		return list;
	}
	public boolean hasEvents(Date date,long userId){
		String hql="select count(event.id) from CalendarEvent event where event.startDate =? and event.userId= ?";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Object obj = session
					.createQuery(hql)
					.setDate(0,date)
					.setLong(1,userId)
					.uniqueResult();
		Long count = (Long) obj;
		session.close();
		boolean hasEvent = false;
		if(count > 0) hasEvent = true;
		return hasEvent;
	}
	
	public boolean hasOrgEvents(Date date,long orgId){
		String hql="select count(event.id) from CalendarEvent event where event.startDate =? and event.orgId= ?";
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Object obj = session
					.createQuery(hql)
					.setDate(0,date)
					.setLong(1,orgId)
					.uniqueResult();
		Long count = (Long) obj;
		session.close();
		boolean hasEvent = false;
		if(count > 0) hasEvent = true;
		return hasEvent;
	}
	
	public void deleteEvents(long id){
		String hql="delete from CalendarEvent where id="+id+" or parentId="+id;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery(hql)
		 	   .executeUpdate();
		session.close();
	}
}
