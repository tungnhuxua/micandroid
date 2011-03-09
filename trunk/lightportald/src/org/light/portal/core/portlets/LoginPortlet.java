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

package org.light.portal.core.portlets;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class LoginPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		 String action = request.getParameter("action");
		 if("request".equals(action)){
			 String userId = request.getParameter("email");
			 if( userId == null || userId.length() <= 0){
				   request.setAttribute("error","Please input your Email address.");
				   response.setPortletMode(PortletMode.EDIT);
				   return;
			   }	
			 User user = this.getUserService(request).getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
			 if(user == null){
				 request.setAttribute("error","System didn't find your account,please input Email address and birthday correctly.");
				 response.setPortletMode(PortletMode.EDIT);
				 return;
			 }

			boolean flag = this.getUserService(request).sendNewPassword(user);
			if(flag)
				request.setAttribute("success","your new temporary password has been sent to your email account successfully.");
			else
				request.setAttribute("error","System error, please try later.");
		 } 		 
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
	 }	
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		 try{
		 	JSONObject json = new JSONObject();  	    
		    String success="";
		    if(request.getAttribute("success") != null) success = (String)request.getAttribute("success"); 
		    json.put("success", success);
		    String error="";
		    if(request.getAttribute("error") != null) error = (String)request.getAttribute("error"); 
		    json.put("error", error);
		    
		    response.getWriter().write(json.toString());
		 }catch(Exception e){}
	 }	
}