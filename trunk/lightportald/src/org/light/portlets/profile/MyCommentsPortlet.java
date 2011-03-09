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
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyCommentsPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("delete".equals(action)){
			String id = request.getParameter("parameter");
			this.getUserService(request).deleteUserCommentsById(Long.parseLong(id));			
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
	     if(this.getVisitedUser(request) != null)
	    	 user = this.getVisitedUser(request);		 
		 int showNumber = 6;
		 if(this.getPortlet(request) != null && this.getPortlet(request).getShowNumber() > 0) showNumber = this.getPortlet(request).getShowNumber();
		 int count = this.getUserService(request).getUserCommentsCount(user.getId());
		 if(request.getWindowState().equals(WindowState.NORMAL) && count > showNumber){
			 List<UserComment> userComments = this.getUserService(request).getUserComments(user.getId());
			 request.setAttribute("userComments",userComments.subList(0,showNumber));
		     request.setAttribute("showMore",true);
		 }else{
			 List<UserComment> userComments = this.getUserService(request).getUserComments(user.getId());
			 request.setAttribute("userComments",userComments);
		 }			 		
 		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myCommentsPortletView.jsp").include(request,response);  
			
	 }	

	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count = getUserService(request).getUserCommentsCount(user.getId());
			 if(count > 0){
				try{
					 JSONObject json = new JSONObject();
					 json.put("id",request.getParameter("responseId"));
					 json.put("suffix",count);
					 response.getWriter().write(json.toString());
				 }catch(Exception e){			 
				 }
			 }				              			 
		 }
	 }
}