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

package org.light.portlets.internal;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class InternalArticlePortlet extends LightGenericPortlet {
	 
	public void processAction (ActionRequest request, ActionResponse	response)
	throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
			PortletPreferences prefs = request.getPreferences();
	        String url = request.getParameter("url"); 
	        prefs.setValue("url", url);
            prefs.store();
            response.setPortletMode(PortletMode.VIEW);
		}
	}
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		 PortletPreferences portletPreferences = request.getPreferences();
		 String url = portletPreferences.getValue("url","");	
		 if(!StringUtil.isEmpty(url)){
			this.getPortletContext().getRequestDispatcher(url).include(request,response);  			  
		 }else{
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/article/config.jsp").include(request,response);  
		 }		
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		PortletPreferences portletPreferences = request.getPreferences();
		String url = portletPreferences.getValue("url","");
		request.setAttribute("url",url);
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/article/config.jsp").include(request,response);  
	 }	
	 
}