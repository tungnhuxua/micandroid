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

package org.light.portal.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.Context;
import org.light.portal.core.PortalCommand;
import org.light.portal.core.PortalCommandFactory;
import org.light.portal.core.service.PingService;
import org.light.portal.core.service.PortalService;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.chat.service.ChatService;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;

/**
 * 
 * @author Jianmin Liu
 **/
public abstract class GenericController implements Controller {
	

    /**
     * registerParameter.
     * 
     * @param request HttpServletRequest
     */
	protected void registerParameter(HttpServletRequest request){
    	Enumeration parameters = request.getParameterNames();
    	while(parameters.hasMoreElements()){
    		String name = (String)parameters.nextElement();
    		request.getSession().setAttribute(name,request.getParameter(name));
    	}
    }
    
	protected void setLocale(HttpServletRequest request){
		Context.getInstance().setLocale(request);
	}
	
	protected boolean setLocale(HttpServletRequest request,String locale){
		return Context.getInstance().setLocale(request,locale);
    }
	protected Locale getLocale(HttpServletRequest request){
		return Context.getInstance().getLocale(request);
    }
	protected boolean isAdmin(HttpServletRequest request,User user){
		return Context.getInstance().isAdmin(request,user);
	}
	protected boolean isAuthorized(User user){
		return Context.getInstance().isAuthorized(user);
	}
	protected User getVisitedUser(HttpServletRequest request){		
		return Context.getInstance().getVisitedUser(request);
	}
	protected void setVisitedUser(HttpServletRequest request,User user){		
		Context.getInstance().setVisitedUser(request,user);
	}
	protected void setVisitedPortal(HttpServletRequest request,Portal portal){		
		Context.getInstance().setVisitedPortal(request,portal);
	}
	protected void setVisitedPage(HttpServletRequest request,PortalTab tab){		
		Context.getInstance().setVisitedPage(request,tab);
	}
	protected void setVisitedGroup(HttpServletRequest request,Group group){		
		Context.getInstance().setVisitedGroup(request,group);
	}
	protected PortalTab getVisitedPage(HttpServletRequest request){				
		return Context.getInstance().getVisitedPage(request);
	}
	protected Group getVisitedGroup(HttpServletRequest request){		
		return Context.getInstance().getVisitedGroup(request);
	}
	protected Portal getPortal(HttpServletRequest request){		
		return Context.getInstance().getPortal(request);
	}
	protected void setPortal(HttpServletRequest request,Portal portal){
		Context.getInstance().setPortal(request,portal);
	}
    protected PortalTab getPortalTab(HttpServletRequest request){
    	return Context.getInstance().getPortalTab(request);
	}
    
    protected List<PortletObject> getPortletsByTab(HttpServletRequest request,long id){
    	return getPortalService(request).getPortletsByTab(id);
    }
    
	protected PortletObject getPortlet(HttpServletRequest request){
		return Context.getInstance().getPortlet(request);		
	}

	protected User getUser(HttpServletRequest request){
		return Context.getInstance().getUser(request);		
	}	
	protected PingService getPingService(HttpServletRequest request) {			
		return Context.getInstance().getPingService(request);
	}
	protected PortalService getPortalService(HttpServletRequest request) {			
		return Context.getInstance().getPortalService(request);
	}
	protected UserService getUserService(HttpServletRequest request) {			
		return Context.getInstance().getUserService(request);
	}
	
	protected ConnectionService getConnectionService(HttpServletRequest request) {			
		return Context.getInstance().getConnectionService(request);
	}
	
	protected GroupService getGroupService(HttpServletRequest request) {			
		return Context.getInstance().getGroupService(request);
	}
	
	protected ForumService getForumService(HttpServletRequest request) {			
		return Context.getInstance().getForumService(request);
	}
	
	protected BlogService getBlogService(HttpServletRequest request) {			
		return Context.getInstance().getBlogService(request);
	}
	
	protected ChatService getChatService(HttpServletRequest request) {			
		return Context.getInstance().getChatService(request);
	}	
	protected Object runCommand(HttpServletRequest request,	HttpServletResponse response, String name) throws Exception{
    	Object result = null;
		PortalCommand pCommand = PortalCommandFactory.getInstance().getCommand(name);
    	if(pCommand != null){        		 
    		result = pCommand.executeWithResult(request,response);
    	}        
    	return result;
	}
}