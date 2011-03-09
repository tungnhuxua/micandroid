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

package org.light.portlets.calendar;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.CalendarUtil;
import org.light.portal.util.DateUtil;
import org.light.portal.util.HTMLUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class CalendarPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String type = request.getParameter("type");
		 if(this.getPortlet(request) != null){
			 PortletPreferences portletPreferences = request.getPreferences();
			 if(type == null) type = portletPreferences.getValue("type",null);
			 if(type == null) type = "0";		 
			 if(!type.equals(portletPreferences.getValue("type",null))){		
				 portletPreferences.setValue("type",type);
				 portletPreferences.store();	 		 
			 }
		 }
		 //response.setRenderParameter("type",type);
		 String action = request.getParameter("action");
		 if("save".equals(action)){
			 String name = request.getParameter("name");
			 String location = request.getParameter("location");
			 String desc = request.getParameter("desc");
			 desc = HTMLUtil.disableScripts(desc);
			 String startMonth = request.getParameter("startMonth");
			 String startDay = request.getParameter("startDay");
			 String startYear = request.getParameter("startYear");
			 int startTime = Integer.parseInt(request.getParameter("startTime"));
			 int endTime = Integer.parseInt(request.getParameter("endTime"));
			 String allDay = request.getParameter("allDay");
			 if(allDay != null){
				 startTime = 10000;
				 endTime = 10000;
			 }
			 int state = Integer.parseInt(request.getParameter("eventState"));
			 Calendar start = Calendar.getInstance();
			 start.set(Calendar.YEAR,Integer.parseInt(startYear));
			 start.set(Calendar.MONTH,Integer.parseInt(startMonth) - 1);
			 start.set(Calendar.DAY_OF_MONTH,Integer.parseInt(startDay));
			 			 
			 User user = this.getUser(request);
			 String id = request.getParameter("id");
			 if(id == null || Integer.parseInt(id) == 0){
				 long orgId = user.getOrgId();
				 if(this.getVisitedGroup(request) != null && isVisitingGroup(request)) orgId = this.getVisitedGroup(request).getId();
				 CalendarEvent event = new CalendarEvent(name,location,desc,start.getTime(),startTime,start.getTime(),endTime,user.getId(),orgId,state);
				 this.getPortalService(request).save(event);		
				 int status = Integer.parseInt(request.getParameter("status"));
				 if(status > 0){ 
					 event.setParentId(event.getId());
					 this.getPortalService(request).save(event);
					 String endMonth = request.getParameter("endMonth");
					 String endDay = request.getParameter("endDay");
					 String endYear = request.getParameter("endYear");
					 Calendar end = Calendar.getInstance();
					 end.set(Calendar.YEAR,Integer.parseInt(endYear));
					 end.set(Calendar.MONTH,Integer.parseInt(endMonth) - 1);
					 end.set(Calendar.DAY_OF_MONTH,Integer.parseInt(endDay));
					 if(end.after(start)){
						 Calendar next = Calendar.getInstance();
						 next.setTimeInMillis(start.getTimeInMillis());						 
						 if(status == 1){
							 next.add(Calendar.DATE,1);
							 while(end.after(next)){
								 CalendarEvent eventNext = new CalendarEvent(name,location,desc,next.getTime(),startTime,next.getTime(),endTime,user.getId(),orgId,state,event.getId());
								 this.getPortalService(request).save(eventNext);
								 next.add(Calendar.DATE,1);
							 }
						 }else if(status == 2){
							 next.add(Calendar.DATE,7);
							 while(end.after(next)){
								 CalendarEvent eventNext = new CalendarEvent(name,location,desc,next.getTime(),startTime,next.getTime(),endTime,user.getId(),orgId,state,event.getId());
								 this.getPortalService(request).save(eventNext);
								 next.add(Calendar.DATE,7);
							 }
						 }else if(status == 3){
							 next.add(Calendar.MONTH,1);
							 while(end.after(next)){
								 CalendarEvent eventNext = new CalendarEvent(name,location,desc,next.getTime(),startTime,next.getTime(),endTime,user.getId(),orgId,state,event.getId());
								 this.getPortalService(request).save(eventNext);
								 next.add(Calendar.MONTH,1);
							 }
						 }
					 }
				 }
			 }else{
				 CalendarEvent event = this.getCalendarService(request).getCalendarEventById(Integer.parseInt(id));
				 event.setName(name);
				 event.setStartDate(start.getTime());
				 event.setEndDate(start.getTime());
				 event.setLocation(location);
				 event.setStartTime(startTime);
				 event.setEndTime(endTime);
				 event.setDesc(desc);
				 event.setState(state);
				 this.getPortalService(request).save(event);				 
			 }
//			 if(this.getPortlet(request) != null)
//			 	request.getPortletSession().setAttribute("currentCalendar",start,PortletSession.PORTLET_SCOPE);					
		 }
		 else if("config".equals(action)){
			 CalendarBean calendar = this.getCalendarService(request).getCalendarByUser(this.getUser(request).getId());
			 int startTime = Integer.parseInt(request.getParameter("startTime"));
			 int endTime = Integer.parseInt(request.getParameter("endTime"));
			 int state = Integer.parseInt(request.getParameter("eventState"));
			 int timeType = Integer.parseInt(request.getParameter("timeType"));
			 int interval = Integer.parseInt(request.getParameter("interval"));
			 if(calendar == null){
				 calendar = new CalendarBean(this.getUser(request).getId());				 
			 }
			 calendar.setStartTime(startTime);
			 calendar.setEndTime(endTime);
			 calendar.setState(state);
			 calendar.setType(timeType);
			 calendar.setInterval(interval);
			 
			 this.getPortalService(request).save(calendar);
		 }
		 else if("delete".equals(action)){
			 String id = request.getParameter("parameter");
			 if(id != null){
				 if(id.indexOf("-") > 0){
					 String[] ids = id.split("-");
					 int parentId= Integer.parseInt(ids[1]);
					 this.getCalendarService(request).deleteEvents(parentId);
				 }else{
					 int eventId= Integer.parseInt(id);
					 CalendarEvent event = this.getCalendarService(request).getCalendarEventById(eventId);
					 this.getPortalService(request).delete(event);
				 }
			 }
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String type = request.getParameter("type");
		 if(this.getPortlet(request) != null){
			 PortletPreferences portletPreferences = request.getPreferences();
			 if(type == null){			 
				 type = portletPreferences.getValue("type","0");			 
			 }		 		
		 }
		 if(type == null) type = "0";
		 request.setAttribute("current",type);		
		 long userId = this.getUser(request).getId();
		 if(this.getVisitedUser(request) != null){
			 userId = this.getVisitedUser(request).getId();
		 }
		 CalendarBean calendar = this.getCalendarService(request).getCalendarByUser(userId);
		 if(calendar == null){
			 calendar = new CalendarBean(userId);
			 this.getPortalService(request).save(calendar);
		 }
		 
		 if(type.equals("0")){
			 prepareCalendar(request, response, calendar ,userId);
			 if(request.getWindowState().equals(WindowState.MAXIMIZED))
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletMaxView.jsp").include(request,response);  
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletView.jsp").include(request,response);
		 }else if(type.equals("1")){
			 prepareCalendarDay(request, response, calendar ,userId);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletViewDay.jsp").include(request,response);
		 }else if(type.equals("2")){
			 prepareCalendarWeekDay(request, response, calendar ,userId);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletViewWeekDay.jsp").include(request,response);
		 }else if(type.equals("3")){
			 prepareCalendarWeek(request, response, calendar ,userId);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletViewWeek.jsp").include(request,response);
		 }else if(type.equals("4")){
			 prepareCalendarMonth(request, response, calendar ,userId);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletViewMonth.jsp").include(request,response);
		 }
		 
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String id = request.getParameter("eventId");
		 long userId = this.getUser(request).getId();
		 long orgId = this.getUser(request).getOrgId();		 	 
		 if(this.getVisitedUser(request) != null){
			 userId = this.getVisitedUser(request).getId();
			 orgId = this.getVisitedUser(request).getOrgId();
		 }
		 if(this.getVisitedGroup(request) != null && isVisitingGroup(request)) orgId = this.getVisitedGroup(request).getId();
		 CalendarBean calendar = this.getCalendarService(request).getCalendarByUser(userId);		 
		 if(id != null){
			request.setAttribute("months", CalendarUtil.getMonths());
			request.setAttribute("days", CalendarUtil.getDays());
			request.setAttribute("years", CalendarUtil.getYears());			 	
			int eventId = Integer.parseInt(id);
			CalendarEvent event = null;
			prepareEventSchedule(request, response, calendar,userId);
			Calendar current = null;
			if(this.getPortlet(request) != null)
				current	= (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);	
			if(current == null){
				current = Calendar.getInstance();
				current.setTimeInMillis(System.currentTimeMillis());
			}
			String nextDay = request.getParameter("nextDay");
			if(nextDay != null){
				int day = Integer.parseInt(nextDay);
				current.add(Calendar.DATE,day);
			}
			String time = request.getParameter("startTime");
			int startTime = calendar.getStartTime();
			if(time != null) startTime = Integer.parseInt(time);
			int endTime = startTime + 100;
			if(eventId <= 0){
				 event = new CalendarEvent(current.getTime(),startTime,current.getTime(),endTime,userId,orgId,calendar.getState());
			 }else{
				 event = this.getCalendarService(request).getCalendarEventById(eventId);
			 }
			 
			 request.setAttribute("event",event);
			 if(request.getParameter("modify") != null || eventId <= 0)
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletEdit.jsp").include(request,response);
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletDetail.jsp").include(request,response);
		 }else{
			 request.setAttribute("calendar",calendar);
			 List<TimeInterval> lists = new ArrayList<TimeInterval>();
			 lists.add(new TimeInterval(0,"0:00am"));
			 lists.add(new TimeInterval(100,"1:00am"));
			 lists.add(new TimeInterval(200,"2:00am"));
			 lists.add(new TimeInterval(300,"3:00am"));
			 lists.add(new TimeInterval(400,"4:00am"));
			 lists.add(new TimeInterval(500,"5:00am"));
			 lists.add(new TimeInterval(600,"6:00am"));
			 lists.add(new TimeInterval(700,"7:00am"));
			 lists.add(new TimeInterval(800,"8:00am"));
			 lists.add(new TimeInterval(900,"9:00am"));
			 lists.add(new TimeInterval(1000,"10:00am"));
			 lists.add(new TimeInterval(1100,"11:00am"));
			 lists.add(new TimeInterval(1200,"12:00pm"));
			 lists.add(new TimeInterval(1300,"1:00pm"));
			 lists.add(new TimeInterval(1400,"2:00pm"));
			 lists.add(new TimeInterval(1500,"3:00pm"));
			 lists.add(new TimeInterval(1600,"4:00pm"));
			 lists.add(new TimeInterval(1700,"5:00pm"));
			 lists.add(new TimeInterval(1800,"6:00pm"));
			 lists.add(new TimeInterval(1900,"7:00pm"));
			 lists.add(new TimeInterval(2000,"8:00pm"));
			 lists.add(new TimeInterval(2100,"9:00pm"));
			 lists.add(new TimeInterval(2200,"10:00pm"));
			 lists.add(new TimeInterval(2300,"11:00pm"));
			 lists.add(new TimeInterval(2400,"Midnight"));
			 request.setAttribute("lists",lists);
			 List<TimeInterval> intervals = new ArrayList<TimeInterval>();
			 //intervals.add(new TimeInterval(15,"every fifteen minutes"));
			 intervals.add(new TimeInterval(30,"every half hour"));
			 intervals.add(new TimeInterval(60,"every hour"));
			 //intervals.add(new TimeInterval(120,"every two hours"));
			 //intervals.add(new TimeInterval(240,"every four hours"));
			 request.setAttribute("intervals",intervals);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/calendar/calendarPortletConfig.jsp").include(request,response);
		 }		
	 }	
	 
	 private List<CalendarEvent> getCalendarEvents(RenderRequest request, Date date, long userId){
		 List<CalendarEvent> events = null;
		 if(this.getVisitedGroup(request) != null && isVisitingGroup(request))
			events = this.getCalendarService(request).getCalendarEventsByOrgDate(date, this.getVisitedGroup(request).getId());
		 else
			events = this.getCalendarService(request).getCalendarEventsByDate(date,userId);
		 return events;
	 }
	 
	 private boolean hasEvents(RenderRequest request, Date date,long userId){
		 boolean value = false;
		 if(this.getVisitedGroup(request) != null && isVisitingGroup(request))
			 value = this.getCalendarService(request).hasOrgEvents(date, this.getVisitedGroup(request).getId());
		 else
			 value = this.getCalendarService(request).hasEvents(date, userId);
		 return value;
	 }
	 private void prepareCalendar(RenderRequest request, RenderResponse response, CalendarBean calendar ,long userId){
		Calendar current = null;
		if(this.getPortlet(request) != null)
			current = (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);
		if(current == null){
			current = Calendar.getInstance();		
			current.setTimeInMillis(System.currentTimeMillis());
		}else{
			if(request.getParameter("previous") != null)
				current.add(Calendar.DATE ,-1);
			else if(request.getParameter("next") != null)
				current.add(Calendar.DATE ,1);
		}
		if(this.getPortlet(request) != null)
			request.getPortletSession().setAttribute("currentCalendar",current,PortletSession.PORTLET_SCOPE);
		
		String title = DateUtil.format(current.getTime(),"EEEE, MMMM dd, yyyy");
		request.setAttribute("title",title);

		Calendar first = Calendar.getInstance();		
		first.setTimeInMillis(current.getTimeInMillis());
		String today = DateUtil.format(first.getTime(),"EEEE, MMMM dd, yyyy");
		request.setAttribute("today",today);		
		List<CalendarEvent> events = this.getCalendarEvents(request,first.getTime(),userId);		
		request.setAttribute("events",events);
		
		first.add(Calendar.DATE,  1 );
		String today1 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today1",today1);	
		List<CalendarEvent> events1 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events1",events1);
		
		first.add(Calendar.DATE,  1 );
		String today2 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today2",today2);
		List<CalendarEvent> events2 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events2",events2);
		
		first.add(Calendar.DATE,  1 );
		String today3 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today3",today3);	
		List<CalendarEvent> events3 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events3",events3);
		
		first.add(Calendar.DATE,  1 );
		String today4 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today4",today4);	
		List<CalendarEvent> events4 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events4",events4);
		
		first.add(Calendar.DATE,  1 );
		String today5 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today5",today5);	
		List<CalendarEvent> events5 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events5",events5);
		
		first.add(Calendar.DATE,  1 );
		String today6 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today6",today6);	
		List<CalendarEvent> events6 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events6",events6);
			
		first.add(Calendar.DATE,  1 );
		String today7 = DateUtil.format(first.getTime(),"EEE, MMM dd");
		request.setAttribute("today7",today7);	
		List<CalendarEvent> events7 = this.getCalendarEvents(request,first.getTime(),userId);
		request.setAttribute("events7",events7);
	 }
	 private void prepareCalendarDay(RenderRequest request, RenderResponse response, CalendarBean calendar, long userId){		
		String newDay = request.getParameter("newDay");		
		String nextDay = request.getParameter("nextDay");	
		String weekDay = request.getParameter("weekDay");
		Calendar current = null;
		if(this.getPortlet(request) != null)
			current = (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);		
		if(current == null){
			current = Calendar.getInstance();		
			current.setTimeInMillis(System.currentTimeMillis());
		}else{
			if(request.getParameter("previous") != null)
				current.add(Calendar.DATE ,-1);
			else if(request.getParameter("next") != null)
				current.add(Calendar.DATE ,1);
			else if(newDay != null){
				int day = Integer.parseInt(newDay);
				current.set(Calendar.DAY_OF_MONTH,day);
			}
			else if(nextDay != null){
				int day = Integer.parseInt(nextDay);
				current.add(Calendar.DATE,day);
			}
			else if(weekDay != null){
				 current.setFirstDayOfWeek(Calendar.SUNDAY);
				 current.set(Calendar.DAY_OF_WEEK, 1);
				 current.add(Calendar.DATE,Integer.parseInt(weekDay) - 1);
			 }
		}
		if(this.getPortlet(request) != null)
			request.getPortletSession().setAttribute("currentCalendar",current,PortletSession.PORTLET_SCOPE);			
		List<CalendarEvent> events = this.getCalendarEvents(request,current.getTime(),userId);
		//request.setAttribute("events",events);
		String title = DateUtil.format(current.getTime(),"EEEE, MMMM dd, yyyy");
		request.setAttribute("title",title);
		int start = calendar.getStartTime();
		int end = calendar.getEndTime();
		int interval = calendar.getInterval();
		List<TimeInterval> lists = new ArrayList<TimeInterval>();
		TimeInterval allDay = new TimeInterval(10000,"All Day");
		for(CalendarEvent event: events){
			if(event.getStartTime()== 10000){
				//if(allDay == null) allDay = new TimeInterval(10000,"All Day");
				allDay.addEvent(event);
			}
		}
		lists.add(allDay);
		while(start < end){
			String startTime = String.valueOf(start);
			if(startTime.length() == 1) startTime = startTime +"00";
			String startDesc = startTime.substring(0,2)+":"+startTime.substring(2);
			if(startTime.length() == 3)
				startDesc= startTime.substring(0,1)+":"+startTime.substring(1);
			int start2= start + 30;
			String startTime2 = String.valueOf(start2);
			String startDesc2 = startTime2.substring(0,2)+":"+startTime2.substring(2);
			if(startTime2.length() == 3)
				startDesc2= startTime2.substring(0,1)+":"+startTime2.substring(1);
			if(calendar.getType() == 0){
				if(start < 1200){
					startDesc = startDesc+"am";
					startDesc2 = startDesc2+"am";
				}else{
					int change = start;
					if(start >= 1300){
						change = start - 1200;
					}
					String changeTime = String.valueOf(change);
					String changeTime2 = String.valueOf(change + 30);
					startDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
					if(changeTime.length() == 3)
						startDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
					startDesc2 = changeTime2.substring(0,2)+":"+changeTime2.substring(2)+"pm";
					if(changeTime2.length() == 3)
						startDesc2= changeTime2.substring(0,1)+":"+changeTime2.substring(1)+"pm"; 
				}
			}
			if(interval == 30){
				TimeInterval time1 = new TimeInterval(start,startDesc);
				TimeInterval time2 = new TimeInterval(start2,startDesc2);
				for(CalendarEvent event: events){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent(event);
					}
				}
				lists.add(time1);				
				lists.add(time2);
			}else{
				TimeInterval time1 = new TimeInterval(start,startDesc);
				for(CalendarEvent event: events){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent(event);
					}
				}
				lists.add(time1);
	 		}
			start =start+100;
		}
		request.setAttribute("lists",lists);
	 }	 
	 private void prepareCalendarWeekDay(RenderRequest request, RenderResponse response, CalendarBean calendar ,long userId){
		Calendar current = null;
		if(this.getPortlet(request) != null)
			current = (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);
		if(current == null){
			current = Calendar.getInstance();		
			current.setTimeInMillis(System.currentTimeMillis());
		}else{
			if(request.getParameter("previous") != null)
				current.add(Calendar.DATE ,-7);
			else if(request.getParameter("next") != null)
				current.add(Calendar.DATE ,7);
		}
		if(this.getPortlet(request) != null)
			request.getPortletSession().setAttribute("currentCalendar",current,PortletSession.PORTLET_SCOPE);			
		int dayOfWeek = current.get(Calendar.DAY_OF_WEEK);
		request.setAttribute("dayOfWeek",dayOfWeek);			
		Calendar monday = Calendar.getInstance();		
		monday.setTimeInMillis(current.getTimeInMillis());
		monday.setFirstDayOfWeek(Calendar.MONDAY);
		if(monday.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			monday.add(Calendar.DATE, 2);
		if(monday.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			monday.add(Calendar.DATE, 1);
		monday.add(Calendar.DATE,  (monday.getFirstDayOfWeek()-monday.get(Calendar.DAY_OF_WEEK)-7)%7  );
		List<CalendarEvent> events2 = this.getCalendarEvents(request,monday.getTime(),userId);
		
		Calendar tuesday = Calendar.getInstance();		
		tuesday.setTimeInMillis(monday.getTimeInMillis());
		tuesday.add(Calendar.DATE,  1);		
		List<CalendarEvent> events3 = this.getCalendarEvents(request,tuesday.getTime(),userId);
		
		Calendar wednesday = Calendar.getInstance();		
		wednesday.setTimeInMillis(monday.getTimeInMillis());
		wednesday.add(Calendar.DATE,  2);		
		List<CalendarEvent> events4 = this.getCalendarEvents(request,wednesday.getTime(),userId);
		
		Calendar thursday = Calendar.getInstance();		
		thursday.setTimeInMillis(monday.getTimeInMillis());
		thursday.add(Calendar.DATE,  3);		
		List<CalendarEvent> events5 = this.getCalendarEvents(request,thursday.getTime(),userId);
		
		Calendar friday = Calendar.getInstance();		
		friday.setTimeInMillis(monday.getTimeInMillis());
		friday.add(Calendar.DATE,  4);		
		List<CalendarEvent> events6 = this.getCalendarEvents(request,friday.getTime(),userId);
		
		String title = DateUtil.format(monday.getTime(),"EEE, MMM dd, yyyy")+" - "+DateUtil.format(friday.getTime(),"EEE, MMM dd, yyyy");
		request.setAttribute("title",title);
		int start = calendar.getStartTime();
		int end = calendar.getEndTime();
		int interval = calendar.getInterval();
		List<TimeInterval> lists = new ArrayList<TimeInterval>();
		TimeInterval allDay = null;
		for(CalendarEvent event: events2){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent2(event);
			}
		}
		for(CalendarEvent event: events3){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent3(event);
			}
		}
		for(CalendarEvent event: events4){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent4(event);
			}
		}
		for(CalendarEvent event: events5){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent5(event);
			}
		}
		for(CalendarEvent event: events6){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent6(event);
			}
		}
		lists.add(allDay);
		while(start < end){
			String startTime = String.valueOf(start);
			if(startTime.length() == 1) startTime = startTime +"00";
			String startDesc = startTime.substring(0,2)+":"+startTime.substring(2);
			if(startTime.length() == 3)
				startDesc= startTime.substring(0,1)+":"+startTime.substring(1);
			int start2= start + 30;
			String startTime2 = String.valueOf(start2);
			String startDesc2 = startTime2.substring(0,2)+":"+startTime2.substring(2);
			if(startTime2.length() == 3)
				startDesc2= startTime2.substring(0,1)+":"+startTime2.substring(1);
			if(calendar.getType() == 0){
				if(start < 1200){
					startDesc = startDesc+"am";
					startDesc2 = startDesc2+"am";
				}else{
					int change = start;
					if(start >= 1300){
						change = start - 1200;
					}
					String changeTime = String.valueOf(change);
					String changeTime2 = String.valueOf(change + 30);
					startDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
					if(changeTime.length() == 3)
						startDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
					startDesc2 = changeTime2.substring(0,2)+":"+changeTime2.substring(2)+"pm";
					if(changeTime2.length() == 3)
						startDesc2= changeTime2.substring(0,1)+":"+changeTime2.substring(1)+"pm"; 
				}
			}
			if(interval == 30){
				TimeInterval time1 = new TimeInterval(start,startDesc);
				TimeInterval time2 = new TimeInterval(start2,startDesc2);
				for(CalendarEvent event: events2){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent2(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent2(event);
					}
				}				
				for(CalendarEvent event: events3){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent3(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent3(event);
					}
				}
				for(CalendarEvent event: events4){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent4(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent4(event);
					}
				}
				for(CalendarEvent event: events5){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent5(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent5(event);
					}
				}
				for(CalendarEvent event: events6){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent6(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent6(event);
					}
				}
				lists.add(time1);				
				lists.add(time2);
			}else{
				TimeInterval time1 = new TimeInterval(start,startDesc);
				for(CalendarEvent event: events2){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent2(event);
					}
				}
				for(CalendarEvent event: events3){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent3(event);
					}
				}
				for(CalendarEvent event: events4){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent4(event);
					}
				}
				for(CalendarEvent event: events5){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent5(event);
					}
				}
				for(CalendarEvent event: events6){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent6(event);
					}
				}				
				lists.add(time1);
	 		}
			start =start+100;
		}
		request.setAttribute("lists",lists);
	 }
	 private void prepareCalendarWeek(RenderRequest request, RenderResponse response, CalendarBean calendar ,long userId){
		Calendar current = null;
		if(this.getPortlet(request) != null)
			current = (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);
		if(current == null){
			current = Calendar.getInstance();		
			current.setTimeInMillis(System.currentTimeMillis());
		}else{
			if(request.getParameter("previous") != null)
				current.add(Calendar.DATE ,-7);
			else if(request.getParameter("next") != null)
				current.add(Calendar.DATE ,7);
		}
		if(this.getPortlet(request) != null)
			request.getPortletSession().setAttribute("currentCalendar",current,PortletSession.PORTLET_SCOPE);			
		int dayOfWeek = current.get(Calendar.DAY_OF_WEEK);
		request.setAttribute("dayOfWeek",dayOfWeek);
		Calendar first = Calendar.getInstance();		
		first.setTimeInMillis(current.getTimeInMillis());
		first.add(Calendar.DATE,  (first.getFirstDayOfWeek()-first.get(Calendar.DAY_OF_WEEK)-7)%7  );
		List<CalendarEvent> events1 = this.getCalendarEvents(request,first.getTime(),userId);
		
		Calendar second = Calendar.getInstance();		
		second.setTimeInMillis(first.getTimeInMillis());
		second.add(Calendar.DATE,  1);		
		List<CalendarEvent> events2 = this.getCalendarEvents(request,second.getTime(),userId);
		
		Calendar third = Calendar.getInstance();		
		third.setTimeInMillis(first.getTimeInMillis());
		third.add(Calendar.DATE,  2);		
		List<CalendarEvent> events3 = this.getCalendarEvents(request,third.getTime(),userId);
		
		Calendar fourth = Calendar.getInstance();		
		fourth.setTimeInMillis(first.getTimeInMillis());
		fourth.add(Calendar.DATE,  3);		
		List<CalendarEvent> events4 = this.getCalendarEvents(request,fourth.getTime(),userId);
		
		Calendar fifth = Calendar.getInstance();		
		fifth.setTimeInMillis(first.getTimeInMillis());
		fifth.add(Calendar.DATE,  4);		
		List<CalendarEvent> events5 = this.getCalendarEvents(request,fifth.getTime(),userId);
		
		Calendar sixth = Calendar.getInstance();		
		sixth.setTimeInMillis(first.getTimeInMillis());
		sixth.add(Calendar.DATE,  5);		
		List<CalendarEvent> events6 = this.getCalendarEvents(request,sixth.getTime(),userId);
		
		Calendar last = Calendar.getInstance();		
		last.setTimeInMillis(first.getTimeInMillis());
		last.add(Calendar.DATE,  6);	
		List<CalendarEvent> events7 = this.getCalendarEvents(request,last.getTime(),userId);
		
		String title = DateUtil.format(first.getTime(),"EEE, MMM dd, yyyy")+" - "+DateUtil.format(last.getTime(),"EEE, MMM dd, yyyy");
		request.setAttribute("title",title);
		if(first.getFirstDayOfWeek() == Calendar.MONDAY)
			request.setAttribute("firstDay",1);
		else
			request.setAttribute("firstDay",0);
		int start = calendar.getStartTime();
		int end = calendar.getEndTime();
		int interval = calendar.getInterval();
		List<TimeInterval> lists = new ArrayList<TimeInterval>();
		TimeInterval allDay = null;
		for(CalendarEvent event: events1){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent1(event);
			}
		}
		for(CalendarEvent event: events2){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent2(event);
			}
		}
		for(CalendarEvent event: events3){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent3(event);
			}
		}
		for(CalendarEvent event: events4){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent4(event);
			}
		}
		for(CalendarEvent event: events5){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent5(event);
			}
		}
		for(CalendarEvent event: events6){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent6(event);
			}
		}
		for(CalendarEvent event: events7){
			if(event.getStartTime()== 10000){
				if(allDay == null) allDay = new TimeInterval(0,"All Day");
				allDay.addEvent7(event);
			}
		}
		lists.add(allDay);
		while(start < end){
			String startTime = String.valueOf(start);
			if(startTime.length() == 1) startTime = startTime +"00";
			String startDesc = startTime.substring(0,2)+":"+startTime.substring(2);
			if(startTime.length() == 3)
				startDesc= startTime.substring(0,1)+":"+startTime.substring(1);
			int start2= start + 30;
			String startTime2 = String.valueOf(start2);
			String startDesc2 = startTime2.substring(0,2)+":"+startTime2.substring(2);
			if(startTime2.length() == 3)
				startDesc2= startTime2.substring(0,1)+":"+startTime2.substring(1);
			if(calendar.getType() == 0){
				if(start < 1200){
					startDesc = startDesc+"am";
					startDesc2 = startDesc2+"am";
				}else{
					int change = start;
					if(start >= 1300){
						change = start - 1200;
					}
					String changeTime = String.valueOf(change);
					String changeTime2 = String.valueOf(change + 30);
					startDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
					if(changeTime.length() == 3)
						startDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
					startDesc2 = changeTime2.substring(0,2)+":"+changeTime2.substring(2)+"pm";
					if(changeTime2.length() == 3)
						startDesc2= changeTime2.substring(0,1)+":"+changeTime2.substring(1)+"pm"; 
				}
			}
			if(interval == 30){
				TimeInterval time1 = new TimeInterval(start,startDesc);
				TimeInterval time2 = new TimeInterval(start2,startDesc2);
				for(CalendarEvent event: events1){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent1(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent1(event);
					}
				}
				for(CalendarEvent event: events2){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent2(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent2(event);
					}
				}				
				for(CalendarEvent event: events3){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent3(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent3(event);
					}
				}
				for(CalendarEvent event: events4){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent4(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent4(event);
					}
				}
				for(CalendarEvent event: events5){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent5(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent5(event);
					}
				}
				for(CalendarEvent event: events6){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent6(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent6(event);
					}
				}
				for(CalendarEvent event: events7){
					if(event.getStartTime()>= start && event.getStartTime() < start + 30){
						time1.addEvent7(event);
					}
					if(event.getStartTime()>= start2 && event.getStartTime() < start + 100){
						time2.addEvent7(event);
					}
				}
				lists.add(time1);				
				lists.add(time2);
			}else{
				TimeInterval time1 = new TimeInterval(start,startDesc);
				for(CalendarEvent event: events1){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent1(event);
					}
				}
				for(CalendarEvent event: events2){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent2(event);
					}
				}
				for(CalendarEvent event: events3){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent3(event);
					}
				}
				for(CalendarEvent event: events4){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent4(event);
					}
				}
				for(CalendarEvent event: events5){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent5(event);
					}
				}
				for(CalendarEvent event: events6){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent6(event);
					}
				}	
				for(CalendarEvent event: events7){
					if(event.getStartTime()>= start && event.getStartTime() < start + 100){
						time1.addEvent7(event);
					}
				}
				lists.add(time1);
	 		}
			start =start+100;
		}
		request.setAttribute("lists",lists);
	 }
	 private void prepareCalendarMonth(RenderRequest request, RenderResponse response, CalendarBean calendar ,long userId){
		Calendar current = null;
		if(this.getPortlet(request) != null)
			current = (Calendar)request.getPortletSession().getAttribute("currentCalendar",PortletSession.PORTLET_SCOPE);
		if(current == null){
			current = Calendar.getInstance();		
			current.setTimeInMillis(System.currentTimeMillis());
		}else{
			if(request.getParameter("previous") != null)
				current.add(Calendar.MONTH ,-1);
			else if(request.getParameter("next") != null)
				current.add(Calendar.MONTH ,1);
		}
		if(this.getPortlet(request) != null)
			request.getPortletSession().setAttribute("currentCalendar",current,PortletSession.PORTLET_SCOPE);								
		String title = DateUtil.format(current.getTime()," MMMM, yyyy");
		request.setAttribute("title",title);
		int today = current.get(Calendar.DAY_OF_MONTH);
		request.setAttribute("today",today);
		Calendar first = Calendar.getInstance();		
		first.setTimeInMillis(current.getTimeInMillis());
		first.set(Calendar.DATE,1);
		int todayOfWeek = first.get(Calendar.DAY_OF_WEEK); 
		int maxDayOfMonth = first.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<TimeInterval> lists = new ArrayList<TimeInterval>();
		if(current.getFirstDayOfWeek() == Calendar.MONDAY){
			request.setAttribute("firstDay",1);
			for(int i=2;i<todayOfWeek;i++){
				lists.add(new TimeInterval(0,null));		
			}
			for(int i= 1;i<=maxDayOfMonth;i++){				
				Calendar day = first;
				if(i != 1)
					day.set(Calendar.DAY_OF_MONTH,i);
				TimeInterval timeInterval;
				if(this.hasEvents(request,day.getTime(),this.getUser(request).getId()))
					timeInterval = new TimeInterval(i,String.valueOf(i));	
				else
					timeInterval = new TimeInterval(i,null);
				List<CalendarHoliday> holidays = this.getCalendarService(request).getCalendarHoliday(day.getTime());
				if(holidays != null)					
					timeInterval.setHolidays(holidays);
				lists.add(timeInterval);
			}
			
		}else{
			request.setAttribute("firstDay",0);
			for(int i=1;i<todayOfWeek;i++){
				lists.add(new TimeInterval(0,null));		
			}
			for(int i= 1;i<=maxDayOfMonth;i++){
				Calendar day = first;
				if(i != 1)
					day.set(Calendar.DAY_OF_MONTH,i);
				TimeInterval timeInterval;
				if(this.hasEvents(request,day.getTime(),this.getUser(request).getId()))
					timeInterval = new TimeInterval(i,String.valueOf(i));	
				else
					timeInterval = new TimeInterval(i,null);
				List<CalendarHoliday> holidays = this.getCalendarService(request).getCalendarHoliday(day.getTime());
				if(holidays != null)
					timeInterval.setHolidays(holidays);
				lists.add(timeInterval);				
			}
			
		}	
		int total = lists.size();
		for(int i= total % 7;i<7;i++){
			lists.add(new TimeInterval(0,null));	
		}
		request.setAttribute("lists",lists);
	 }	
	 private void prepareEventSchedule(RenderRequest request, RenderResponse response, CalendarBean calendar, long userId){		
			
			int start = calendar.getStartTime();
			int end = calendar.getEndTime();
			int interval = calendar.getInterval();
			List<TimeInterval> lists = new ArrayList<TimeInterval>();
			lists.add(null);
			while(start <= end){
				String startTime = String.valueOf(start);
				if(startTime.length() == 1) startTime = startTime +"00";
				String startDesc = startTime.substring(0,2)+":"+startTime.substring(2);
				if(startTime.length() == 3)
					startDesc= startTime.substring(0,1)+":"+startTime.substring(1);
				int start2= start + 30;
				String startTime2 = String.valueOf(start2);
				String startDesc2 = startTime2.substring(0,2)+":"+startTime2.substring(2);
				if(startTime2.length() == 3)
					startDesc2= startTime2.substring(0,1)+":"+startTime2.substring(1);
				if(calendar.getType() == 0){
					if(start < 1200){
						startDesc = startDesc+"am";
						startDesc2 = startDesc2+"am";
					}else{
						int change = start;
						if(start >= 1300){
							change = start - 1200;
						}
						String changeTime = String.valueOf(change);
						String changeTime2 = String.valueOf(change + 30);
						startDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
						if(changeTime.length() == 3)
							startDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
						startDesc2 = changeTime2.substring(0,2)+":"+changeTime2.substring(2)+"pm";
						if(changeTime2.length() == 3)
							startDesc2= changeTime2.substring(0,1)+":"+changeTime2.substring(1)+"pm"; 
					}
				}
				if(start == 2400) startDesc="Midnight";
				if(interval == 30){
					TimeInterval time1 = new TimeInterval(start,startDesc);
					lists.add(time1);	
					if(start < 2400){
						TimeInterval time2 = new TimeInterval(start2,startDesc2);			
						lists.add(time2);
					}
				}else{
					TimeInterval time1 = new TimeInterval(start,startDesc);					
					lists.add(time1);
		 		}
				start =start+100;
			}
			request.setAttribute("lists",lists);
		 }	 
}