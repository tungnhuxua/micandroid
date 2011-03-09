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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class InviteFriendPortlet extends LightGenericPortlet {
	 
	public void processAction (ActionRequest request, ActionResponse response) 
throws PortletException, java.io.IOException {
	String action = request.getParameter("action");
	if("send".equals(action)){
	   String to = request.getParameter("to");
	   if(to == null || to.length() <= 0 ){
		   request.setAttribute("error","your friends' email address are required.");
		   return;
	   }
	   String content = request.getParameter("content");	  	   		   	
	      
	   this.getUserService(request).inviteFriends(to,content,this.getUser(request));
	   String[] mails = to.split(",");
	   StringBuffer info = new StringBuffer();
	   info.append("you have invited following friends successfully:<br/>");
	   for(int i=0;i<mails.length;i++){
		   info.append("<br/>"+mails[i]);
	   }
	   info.append("<br/>");
	   request.setAttribute("success",info.toString());
	}	
	
  }
 
 protected void doView (RenderRequest request, RenderResponse response)
   throws PortletException, java.io.IOException
 {
	 
	 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/inviteFriendPortletView.jsp").include(request,response);  
		
 }

}
