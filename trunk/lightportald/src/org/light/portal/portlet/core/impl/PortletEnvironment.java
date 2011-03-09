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

package org.light.portal.portlet.core.impl;

import java.util.List;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.Context;
import org.light.portal.core.service.PortalService;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletEnvironment {
		
	private PortletWindow portletWindow;
	
	public PortletEnvironment(HttpServletRequest request,
                             HttpServletResponse response){
		String name = (String)request.getAttribute("portletName");
		String state= request.getParameter("state");
	    String mode = request.getParameter("mode");
		if(request.getAttribute("mode") != null)
			mode = (String)request.getAttribute("mode");
	    if(state == null || "".equals(state))
			 state = "normal";
		 if(mode == null || "".equals(mode))
			 mode = "view";
		 
		WindowState windowState = new WindowState(state);
		PortletMode portletMode = new PortletMode(mode);
		PortletObject portletObject = this.getPortlet(request);
	    this.portletWindow = new PortletWindow(name, windowState, portletMode, portletObject);
			    
		request.setAttribute("state",state);
		request.setAttribute("mode",mode);
		request.setAttribute("portletObject",portletObject);				        		
	}
	
	protected Portal getPortal(HttpServletRequest request){
		return Context.getInstance().getPortal(request);
	}
	
    protected PortalTab getPortalTab(HttpServletRequest request){
    	return Context.getInstance().getPortalTab(request);
	}
    
    protected List<PortletObject> getPortletsByTab(HttpServletRequest request,int id){
    	return Context.getInstance().getPortletsByTab(request,id);
    }
    
	protected PortletObject getPortlet(HttpServletRequest request){
		return Context.getInstance().getPortlet(request);
	}
	
	public PortletWindow getPortletWindow() {
		return portletWindow;
	}
	
}
