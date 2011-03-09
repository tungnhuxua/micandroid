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

package org.light.portlets.profile;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class PeoplePortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
        if("config".equals(action)){
           String items = request.getParameter("items");
           PortletObject portlet =getPortlet(request);
           if(portlet != null){
               portlet.setShowNumber(Integer.parseInt(items));
               this.getPortalService(request).save(portlet);
           }
           response.setPortletMode(PortletMode.VIEW);
       }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	     
		 PortletPreferences portletPreferences = request.getPreferences();
		 PortletObject portlet =getPortlet(request);
		 int showNumber = portlet.getShowNumber();
		 if(showNumber <=0) showNumber = Integer.parseInt(portletPreferences.getValue("showNumber","8"));
		 int page = 0;
		 if(request.getPortletSession().getAttribute("memberPage", PortletSession.APPLICATION_SCOPE) != null)
			 page = (Integer)request.getPortletSession().getAttribute("memberPage", PortletSession.PORTLET_SCOPE);
		 if(request.getParameter("previous") != null) page--;
		 if(request.getParameter("next") != null) page++;
		 List<User> users = this.getUserService(request).viewUsersByOrgId(this.getUser(request).getOrgId());
		 int total = users.size();
		 if(page <0) page = 0;
		 int start = page * showNumber;
		 int end = start + showNumber;
		 if(end >= total){
			 request.setAttribute("memberPageLast",true);
		 }
		 int pages  = total / showNumber;
	     if(total % showNumber != 0) pages++;
		 request.setAttribute("page",page+1);
		 request.setAttribute("start",start);		   
		 request.setAttribute("end",end);
		 request.setAttribute("total",total);
		 request.setAttribute("pages",pages);
		 request.getPortletSession().setAttribute("memberPage", page, PortletSession.APPLICATION_SCOPE);
		 request.setAttribute("coolNewPeople",users.subList(start,(end < total) ? end : total));
		 request.setAttribute("columnNumber",showNumber);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/peoplePortletView.jsp").include(request,response);
			
	 }	

	 protected void doEdit (RenderRequest request, RenderResponse response)
     throws PortletException, java.io.IOException
     {
		 PortletPreferences portletPreferences = request.getPreferences();
		 PortletObject portlet =getPortlet(request);
		 int showNumber = portlet.getShowNumber();
		 if(showNumber <=0) showNumber = Integer.parseInt(portletPreferences.getValue("showNumber","8"));
		 request.setAttribute("showNumber",showNumber);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/peoplePortletEdit.jsp").include(request,response);

     }
}
