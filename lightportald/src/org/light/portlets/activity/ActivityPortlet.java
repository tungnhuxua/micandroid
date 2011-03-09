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

package org.light.portlets.activity;

import static org.light.portal.util.Constants._DEFAULT_MAX_SHOW_NUMBER;
import static org.light.portal.util.Constants._DEFAULT_NORMAL_SHOW_NUMBER;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PortletObject;
import org.light.portal.model.SocialActivity;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class ActivityPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("config".equals(action)){
	           String items = request.getParameter("items"); 
	           String type = request.getParameter("type");
	           PortletObject portlet =getPortlet(request);
	           if(portlet != null){
	               portlet.setShowNumber(Integer.parseInt(items));
	               this.getPortalService(request).save(portlet);	             
		           PortletPreferences portletPreferences = request.getPreferences();		 
		   		   portletPreferences.setValue("type",type);
		   		   portletPreferences.store();
	           }
	   		   
	       }      
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 PortletPreferences portletPreferences = request.getPreferences();	
		 String  type= portletPreferences.getValue("type","1");
		 PortletObject portlet =getPortlet(request);
		 int showNumber = 0;
		 if(portlet != null) showNumber = portlet.getShowNumber();
		 if(showNumber <= 0) showNumber = _DEFAULT_NORMAL_SHOW_NUMBER;
		 if(request.getWindowState().equals(WindowState.MAXIMIZED)) showNumber = _DEFAULT_MAX_SHOW_NUMBER;
		 int page = 1;
		 String pageNumber = request.getParameter("page");
		 if(request.getPortletSession().getAttribute("page",PortletSession.PORTLET_SCOPE) != null) page = (Integer)request.getPortletSession().getAttribute("page",PortletSession.PORTLET_SCOPE);
		 if(pageNumber != null) page = Integer.parseInt(pageNumber);
		
		 int total = 0;
		 if(this.getVisitedGroup(request) != null) 
			 total = this.getUserService(request).getSocialActivitiesCountByOrg(this.getVisitedGroup(request).getId());
		 else {
			 long userId = this.getUser(request).getId();
			 if(this.getVisitedUser(request) != null) userId = this.getVisitedUser(request).getId();
			 if("0".equals(type))
				 total = this.getUserService(request).getSocialActivitiesCountByOrg(OrganizationThreadLocal.getOrganizationId());
			 else if("1".equals(type))
				 total = this.getUserService(request).getSocialActivitiesCount(userId);
			 else if("2".equals(type))
				 total = this.getUserService(request).getMyGroupsActivitiesCount(userId);
			 else if("3".equals(type))
				 total = this.getUserService(request).getMyConnectionsActivitiesCount(userId);
			 else if("4".equals(type))
				 total = this.getUserService(request).getMyActivitiesCount(userId);
			 else
				 total = this.getUserService(request).getSocialActivitiesCount(userId);
		 }
		 int totalPages = (total % showNumber == 0) ? total / showNumber : total / showNumber + 1;
		
		 if(page < 1) page = totalPages;
		 if(page > totalPages) page = 1;
		 int start = (page - 1) *  showNumber;
		 if(start < 0) start = 0;
		 int end = start + showNumber;
		 if(end > total) end = total;
		 request.setAttribute("page",page);
		 request.getPortletSession().setAttribute("page",page,PortletSession.PORTLET_SCOPE);
		 request.setAttribute("pages",totalPages);
		 request.setAttribute("start",start);		   
		 request.setAttribute("end",end);
		 request.setAttribute("total",total);
					
		 List<SocialActivity> activities = null;
		 if(this.getVisitedGroup(request) != null) 
			 activities = this.getUserService(request).getSocialActivitiesByOrg(this.getVisitedGroup(request).getId(),start,showNumber);
		 else {
			 long userId = this.getUser(request).getId();
			 if(this.getVisitedUser(request) != null) userId = this.getVisitedUser(request).getId();
			 if("0".equals(type))
				 activities = this.getUserService(request).getSocialActivitiesByOrg(OrganizationThreadLocal.getOrganizationId(),start,showNumber);
			 else if("1".equals(type))
				 activities = this.getUserService(request).getSocialActivities(userId,start,showNumber);
			 else if("2".equals(type))
				 activities = this.getUserService(request).getMyGroupsActivities(userId,start,showNumber);
			 else if("3".equals(type))
				 activities = this.getUserService(request).getMyConnectionsActivities(userId,start,showNumber);
			 else if("4".equals(type))
				 activities = this.getUserService(request).getMyActivities(userId,start,showNumber);
			 else
				 activities = this.getUserService(request).getSocialActivities(userId,start,showNumber);
		 }
		 request.setAttribute("activities",activities);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/activity/activityPortletView.jsp").include(request,response);
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
		{
		
		  PortletObject portlet = this.getPortlet(request);
		  int showNumber = 0;
		  if(portlet != null) showNumber = portlet.getShowNumber();		  		 
		  if(showNumber <=0) showNumber = _DEFAULT_NORMAL_SHOW_NUMBER;
		  request.setAttribute("showNumber",showNumber);
		  PortletPreferences portletPreferences = request.getPreferences();		 
		  String  type= portletPreferences.getValue("type","1");
		  request.setAttribute("type",type);
		  
		  this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/activity/activityPortletEdit.jsp").include(request,response);
		
		}		
}