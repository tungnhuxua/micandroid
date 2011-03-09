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

package org.light.portlets.calendar.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.light.portlets.calendar.CalendarBean;
import org.light.portlets.calendar.CalendarEvent;
import org.light.portlets.calendar.CalendarHoliday;

/**
 * 
 * @author Jianmin Liu
 **/
public interface CalendarService {
	
	public CalendarBean getCalendarByUser(long userId);
	public CalendarEvent getCalendarEventById(long id);
	public Map<String,CalendarHoliday> getCalendarHolidays();
	public List<CalendarHoliday> getCalendarHoliday(Date date);
	public List<CalendarEvent> getCalendarEventsByDate(Date date,long userId);
	public List<CalendarEvent> getCalendarEventsByOrgDate(Date date,long orgId);
	public boolean hasEvents(Date date,long userId);
	public boolean hasOrgEvents(Date date,long orgId);
	public void deleteEvents(long id);
}
