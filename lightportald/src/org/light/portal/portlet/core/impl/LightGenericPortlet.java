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

import static org.light.portal.util.Constants._CURRENT_LOCALE;
import static org.light.portal.util.Constants._PORTLET_MODE_CONFIG;
import static org.light.portal.util.Constants._PORTLET_MODE_HEADER;

import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.light.portal.Context;
import org.light.portal.core.PortalContextFactory;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.PortletService;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.contentlibrary.service.ContentLibraryService;
import org.light.portal.user.service.UserService;
import org.light.portlets.ad.service.AdService;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.calendar.service.CalendarService;
import org.light.portlets.chat.service.ChatService;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;
import org.light.portlets.microblog.service.MicroblogService;

/**
 * 
 * @author Jianmin Liu
 **/
public abstract class LightGenericPortlet extends GenericPortlet {
	
	public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
		
	}
	
	protected void doDispatch (RenderRequest request,
			  RenderResponse response) throws PortletException,java.io.IOException
	{ 
	  WindowState state = request.getWindowState();
	  PortletMode mode = request.getPortletMode();
	  if(!request.isWindowStateAllowed(state))
		  throw new PortletException("unknown portlet window state: " + state);
	  if (mode.equals(new PortletMode(_PORTLET_MODE_HEADER))) {
		  doHeader(request, response);
		  return;
	  }	  
	  if (!state.equals(WindowState.MINIMIZED)) {
		  if (mode.equals(new PortletMode(_PORTLET_MODE_CONFIG))) {
			  doConfig (request, response);
		  }
		  else{
			  if(!request.isPortletModeAllowed(mode))
				  throw new PortletException("This portlet doesn't support mode: " + mode);
			
			  if (mode.equals(PortletMode.VIEW)) {
				  doView (request, response);
			  }
			  else if (mode.equals(PortletMode.EDIT)) {
				  doEdit (request, response);
			  }
			  else if (mode.equals(PortletMode.HELP)) {
				  doHelp (request, response);
			  }
			  else {
				  throw new PortletException("unknown portlet mode: " + mode);
			  }
		  }
	  }	
	}
	protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		
	 }		
	
	protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		
	 }		
	
	protected void doHelp (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		
	 }		
	
	protected void doConfig (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		
		
	 }		
	
	protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		
		
	 }
	
	public Object doNotification(HttpServletRequest request)	
	 {
		return null;
	 }
	protected HttpServletRequest getServletRequest(PortletRequest request){
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getAttribute("httpServletRequest");		
		return httpServletRequest;
	}
	protected HttpServletResponse getServletResponse(PortletRequest request){
		HttpServletResponse httpServletResponse = (HttpServletResponse)request.getAttribute("httpServletResponse");		
		return httpServletResponse;
	}
	protected HttpSession getServletSession(PortletRequest request){			
		HttpSession session = getServletRequest(request).getSession();
		return session;
	}
	
	protected String getActionName(PortletRequest request){
		String action = request.getParameter("submit");
		if(request.getParameter("action") != null) action = request.getParameter("action");
		if(action != null) action = PortalContextFactory.getPortalContext().getActionName(action);
		return action;
	}
	protected void setUser(PortletRequest request, User user){				
		Context.getInstance().setUser(this.getServletRequest(request),user);		
	}	
	protected User getUser(PortletRequest request){				
		return Context.getInstance().getUser(this.getServletRequest(request));
	}
	protected User getVisitedUser(PortletRequest request){				
		return Context.getInstance().getVisitedUser(this.getServletRequest(request));
	}
	protected Portal getVisitedPortal(PortletRequest request){				
		return Context.getInstance().getVisitedPortal(this.getServletRequest(request));
	}
	protected Group getVisitedGroup(PortletRequest request){				
		return Context.getInstance().getVisitedGroup(this.getServletRequest(request));
	}	
	protected boolean isAdmin(PortletRequest request,User user){
		return Context.getInstance().isAdmin(this.getServletRequest(request),user);
	}
	protected boolean isAuthorized(User user){
		return Context.getInstance().isAuthorized(user);
	}
	protected Portal getPortal(PortletRequest request){
		return Context.getInstance().getPortal(this.getServletRequest(request));		
	}	
	protected void setPortal(PortletRequest request,Portal portal){
		Context.getInstance().setPortal(this.getServletRequest(request),portal);
	}
	protected void setLocale(PortletRequest request,String language){
		String[] localeParams = language.split("_");
		Locale currentLocale = null;
		if(localeParams.length > 1)
			 currentLocale = new Locale(localeParams[0],localeParams[1]);
		else
			 currentLocale = new Locale(localeParams[0]);
		Config.set(this.getServletRequest(request).getSession(), Config.FMT_LOCALE, currentLocale);	
		this.getServletRequest(request).getSession().setAttribute(_CURRENT_LOCALE,currentLocale.toString());
	}
	protected Locale getLocale(PortletRequest request){
		return Context.getInstance().getLocale(this.getServletRequest(request));
	}
	protected PortalTab getPortalTab(PortletRequest request){
		return Context.getInstance().getPortalTab(this.getServletRequest(request));
	}
	
	protected PortletObject getPortlet(PortletRequest request){		
		return Context.getInstance().getPortlet(this.getServletRequest(request));
	}
	
	protected boolean  isVisitingMember(PortletRequest request){	
		return Context.getInstance().isVisitingMember(this.getServletRequest(request));	
	}
	
	protected boolean  isVisitingGroup(PortletRequest request){
		return Context.getInstance().isVisitingGroup(this.getServletRequest(request));	
	}

	protected java.lang.String getTitle(RenderRequest request) {
	    return getPortletConfig().getResourceBundle(request.getLocale()).getString("javax.portlet.title");
	}
		
	protected boolean isGroupTabOwner(PortletRequest request,PortalTab tab, User user){
		return Context.getInstance().isGroupTabOwner(request,tab,user);
	}
	
	protected PortalService getPortalService(PortletRequest request) {			
		return Context.getInstance().getPortalService(this.getServletRequest(request));
	}
	protected PortletService getPortletService(PortletRequest request) {			
		return Context.getInstance().getPortletService(this.getServletRequest(request));
	}
	protected UserService getUserService(PortletRequest request) {			
		return Context.getInstance().getUserService(this.getServletRequest(request));
	}	
	protected ChatService getChatService(PortletRequest request) {			
		return Context.getInstance().getChatService(this.getServletRequest(request));
	}	
	protected ConnectionService getConnectionService(PortletRequest request) {			
		return Context.getInstance().getConnectionService(this.getServletRequest(request));
	}
	protected GroupService getGroupService(PortletRequest request) {			
		return Context.getInstance().getGroupService(this.getServletRequest(request));
	}	
	protected ForumService getForumService(PortletRequest request) {			
		return Context.getInstance().getForumService(this.getServletRequest(request));
	}	
	protected BlogService getBlogService(PortletRequest request) {			
		return Context.getInstance().getBlogService(this.getServletRequest(request));
	}	
	protected AdService getAdService(PortletRequest request) {			
		return Context.getInstance().getAdService(this.getServletRequest(request));
	}
	protected CalendarService getCalendarService(PortletRequest request) {			
		return Context.getInstance().getCalendarService(this.getServletRequest(request));
	}
	protected MicroblogService getMicroblogService(PortletRequest request) {			
		return Context.getInstance().getMicroblogService(this.getServletRequest(request));
	}
	protected ContentLibraryService getContentLibraryService(PortletRequest request) {			
		return Context.getInstance().getContentLibraryService(this.getServletRequest(request));
	}
		
}
