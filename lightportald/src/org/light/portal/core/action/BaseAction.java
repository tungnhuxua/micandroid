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

package org.light.portal.core.action;

import static org.light.portal.util.Constants._ORGANIZATION;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.light.portal.Context;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.PortletService;
import org.light.portal.core.service.StatusService;
import org.light.portal.core.task.AfterTaskService;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portlets.ad.service.AdService;
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
public class BaseAction {
	
	protected Locale getLocale(HttpServletRequest request){
		return Context.getInstance().getLocale(request);
	}
	protected boolean setLocale(HttpServletRequest request,String locale){
		return Context.getInstance().setLocale(request,locale);
    }
	protected boolean isAdmin(HttpServletRequest request){
		return Context.getInstance().isAdmin(request,this.getUser(request));
	}
	protected boolean isOwner(HttpServletRequest request){
		return Context.getInstance().isOwner(request);
	}
	protected boolean isPageOwner(HttpServletRequest request, long tabId){
		return Context.getInstance().isPageOwner(request,tabId);
	}
	protected boolean isPageOwner(HttpServletRequest request, PortalTab tab){
		return Context.getInstance().isPageOwner(request,tab);
	}
	protected boolean isAuthorized(User user){
		return Context.getInstance().isAuthorized(user);
	}
	protected boolean isGroupTabOwner(HttpServletRequest request,PortalTab tab, User user){
		return Context.getInstance().isGroupTabOwner(request,tab,user);
	}
	protected boolean isValidInteralUri(HttpServletRequest request, String uri){
		return Context.getInstance().isValidInteralUri(request,uri);
	}
	protected boolean  isVisitingMember(HttpServletRequest request){	
		return Context.getInstance().isVisitingMember(request);		
	}	 
	protected boolean  isVisitingGroup(HttpServletRequest request){
		return Context.getInstance().isVisitingMember(request);
	}
	protected User getUser(HttpServletRequest request){
		return Context.getInstance().getUser(request);
	}	
	protected User getVisitedUser(HttpServletRequest request){				
		return Context.getInstance().getVisitedUser(request);
	}
	protected Portal getVisitedPortal(HttpServletRequest request){				
		return Context.getInstance().getVisitedPortal(request);
	}
	protected PortalTab getVisitedPage(HttpServletRequest request){				
		return Context.getInstance().getVisitedPage(request);
	}
	protected void removeVisitedUser(HttpServletRequest request){				
		Context.getInstance().removeVisitedUser(request);
	}
	protected void setVisitedPage(HttpServletRequest request,PortalTab page){		
		Context.getInstance().setVisitedPage(request,page);
	}
	protected void setVisitedUser(HttpServletRequest request,User user){		
		Context.getInstance().setVisitedUser(request,user);
	}	
	protected void setVisitedGroup(HttpServletRequest request,Group group){		
		Context.getInstance().setVisitedGroup(request,group);
	}	
	protected Group getVisitedGroup(HttpServletRequest request){				
		return Context.getInstance().getVisitedGroup(request);
	}	
	protected void removeVisitedGroup(HttpServletRequest request){				
		Context.getInstance().removeVisitedGroup(request);
	}
	protected void setUser(HttpServletRequest request,User user, boolean login){
		Context.getInstance().setUser(request,user,login);
	}
	protected void setUser(HttpServletRequest request,User user){
		Context.getInstance().setUser(request,user);
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
	protected PortletObject getPortlet(HttpServletRequest request){	
		return Context.getInstance().getPortlet(request);		
	}	
	protected String getOrgWebId(HttpServletRequest request){		
		Organization org = (Organization)request.getSession().getAttribute(_ORGANIZATION);
		return (org != null) ? org.getWebId() : "";
	}
	
	protected PortalService getPortalService(HttpServletRequest request) {			
		return Context.getInstance().getPortalService(request);
	}
	
	protected PortletService getPortletService(HttpServletRequest request) {			
		return Context.getInstance().getPortletService(request);
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
	
	protected StatusService getStatusService(HttpServletRequest request) {			
		return Context.getInstance().getStatusService(request);
	}
	
	protected AfterTaskService getAfterTaskService(HttpServletRequest request) {			
		return Context.getInstance().getAfterTaskService(request);
	}
	
	protected AdService getAdService(HttpServletRequest request) {			
		return Context.getInstance().getAdService(request);
	}
	
	protected NotificationService geNotificationService(HttpServletRequest request){
		return Context.getInstance().geNotificationService(request);
	}
}
