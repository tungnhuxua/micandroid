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

package org.light.portlets.group;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupProfilePortlet extends LightGenericPortlet {
	
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
	     Group group = this.getVisitedGroup(request);
	     if(group != null){
			 User user = this.getUserService(request).getUserById(group.getLeaderId());	     
			 request.setAttribute("user",user);
			 request.setAttribute("group",group);
	     }
 		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/profilePortletView.jsp").include(request,response);  	
	 }	
	 
	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		 Group group = getVisitedGroup(request);
		 if(group != null){
			 String title = group.getDisplayName();	
			 try{
				 JSONObject json = new JSONObject();
				 json.put("id",request.getParameter("responseId"));
				 json.put("title",title);
				 response.getWriter().write(json.toString());
			 }catch(Exception e){			 
			 }
		 }
	 }
	 
}
