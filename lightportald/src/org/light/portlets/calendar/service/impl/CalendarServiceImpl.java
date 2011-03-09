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

package org.light.portlets.calendar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.util.DateUtil;
import org.light.portlets.calendar.CalendarBean;
import org.light.portlets.calendar.CalendarEvent;
import org.light.portlets.calendar.CalendarHoliday;
import org.light.portlets.calendar.dao.CalendarDao;
import org.light.portlets.calendar.service.CalendarService;

/**
 * 
 * @author Jianmin Liu
 **/
public class CalendarServiceImpl extends BaseServiceImpl implements CalendarService {
	private CalendarDao calendarDao;
	
	public CalendarBean getCalendarByUser(long userId){
		CalendarBean entity = (CalendarBean)getCacheService().getObject(CalendarBean.class,userId);
		if(entity == null){
			entity = calendarDao.getCalendarByUser(userId);
			getCacheService().setObject(CalendarBean.class,userId,entity);
		}
		return entity;
	}
	
	public CalendarEvent getCalendarEventById(long id){
		CalendarEvent event = (CalendarEvent)getCacheService().getObject(CalendarEvent.class,id);
		if(event == null){
			event = calendarDao.getCalendarEventById(id);
			getCacheService().setObject(CalendarEvent.class,id,event);
		}
		return event;
	}
	
	public List<CalendarEvent> getCalendarEventsByDate(Date date,long userId){
		String key = userId+CacheService.SEPARATOR+DateUtil.format(date);
		List<CalendarEvent> list = (List<CalendarEvent>)getCacheService().getList(CalendarEvent.class,key);
		if(list == null){
			list = calendarDao.getCalendarEventsByDate(date,userId);
			getCacheService().setList(CalendarEvent.class,key,list);
		}
		return list;
	}
	
	public List<CalendarEvent> getCalendarEventsByOrgDate(Date date,long orgId){
		String key = DateUtil.format(date)+CacheService.SEPARATOR+orgId;
		List<CalendarEvent> list = (List<CalendarEvent>)getCacheService().getList(CalendarEvent.class,key);
		if(list == null){
			list = calendarDao.getCalendarEventsByOrgDate(date,orgId);
			getCacheService().setList(CalendarEvent.class,key,list);
		}
		return list;
	}
	
	public Map<String,CalendarHoliday> getCalendarHolidays(){
		return calendarDao.getCalendarHolidays();
	}
	public List<CalendarHoliday> getCalendarHoliday(Date date){
		return calendarDao.getCalendarHoliday(date);
	}
	public boolean hasEvents(Date date,long userId){
		return calendarDao.hasEvents(date,userId);
	}
	public boolean hasOrgEvents(Date date,long orgId){
		return calendarDao.hasOrgEvents(date,orgId);
	}
	public void deleteEvents(long id){
		calendarDao.deleteEvents(id);
	}
	public CalendarDao getCalendarDao() {
		return calendarDao;
	}

	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}
}
