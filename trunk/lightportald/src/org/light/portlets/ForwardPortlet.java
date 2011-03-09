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

package org.light.portlets;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForwardPortlet extends LightGenericPortlet {
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		String url = request.getParameter("url");
		if(url != null){
			request.setAttribute("url",url);
			this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/docViewer.jsp").include(request,response);  			  
			return;
		}
		PortletPreferences portletPreferences = request.getPreferences();
		String viewJSP = portletPreferences.getValue("view","");
		if(request.getWindowState().equals(WindowState.MAXIMIZED)){
			viewJSP =  portletPreferences.getValue("maximizedView",viewJSP);
		}
		this.getPortletContext().getRequestDispatcher(viewJSP).include(request,response);  
				
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		PortletPreferences portletPreferences = request.getPreferences();
		String editJSP = portletPreferences.getValue("edit","");
		this.getPortletContext().getRequestDispatcher(editJSP).include(request,response); 	
	 }	
	 
}

