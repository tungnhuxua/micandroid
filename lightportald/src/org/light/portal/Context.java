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

package org.light.portal;

import static org.light.portal.util.Constants._CHARSET_UTF;
import static org.light.portal.util.Constants._CURRENT_LOCALE;
import static org.light.portal.util.Constants._DEFAULT_LANGUAGE;
import static org.light.portal.util.Constants._DEFAULT_LOCALE;
import static org.light.portal.util.Constants._DEFAULT_RESOURCE_BUNDLE;
import static org.light.portal.util.Constants._GROUP_INDEX_PATTERN;
import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._GROUP_URL_PREFIX;
import static org.light.portal.util.Constants._LIGHT_PORTAL;
import static org.light.portal.util.Constants._LIGHT_PORTAL_USER_ID;
import static org.light.portal.util.Constants._LOGGED_IN;
import static org.light.portal.util.Constants._MEMBER_INDEX_PATTERN;
import static org.light.portal.util.Constants._PERSON_ID;
import static org.light.portal.util.Constants._REMEMBER_LOCALE;
import static org.light.portal.util.Constants._SPACE_URL_PREFIX;
import static org.light.portal.util.Constants._STATUS_ONLINE;
import static org.light.portal.util.Constants._USER;
import static org.light.portal.util.Constants._VISITED_GROUP;
import static org.light.portal.util.Constants._VISITED_PAGE;
import static org.light.portal.util.Constants._VISITED_PORTAL;
import static org.light.portal.util.Constants._VISITED_USER;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import org.light.portal.cache.CacheService;
import org.light.portal.core.PortalContextFactory;
import org.light.portal.core.PortalUtil;
import org.light.portal.core.service.PingService;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.PortletService;
import org.light.portal.core.service.StatusService;
import org.light.portal.core.task.AfterTaskService;
import org.light.portal.distribute.ReplicationServer;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.contentlibrary.service.ContentLibraryService;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portal.util.DomainUtil;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.HashUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.ad.service.AdService;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.calendar.service.CalendarService;
import org.light.portlets.chat.service.ChatService;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;
import org.light.portlets.microblog.service.MicroblogService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author Jianmin Liu
 **/
public class Context {
	private static Logger logger = LoggerFactory.getLogger(Context.class);
	
	public int checkLogin(HttpServletRequest request, User user, String password, boolean encrypted){
		if(!encrypted)
			password = HashUtil.MD5Hashing(password);
		int status = 0;
		if(user != null){			 
			if(!user.getPassword().equals(password)){
				status= -1;
			}else if(user.userDisabled()){
				status= -2;
			}else if(user.userLocked()){
				status= -3;
			}else{	
				if(user.getCurrentStatus() != _STATUS_ONLINE){
					user.setCurrentStatus(_STATUS_ONLINE);
					this.getUserService(request).save(user);
				}
				status = 1;
			}
		}
		return status;
	}
	
	public void setContentType(HttpServletResponse response){
		response.setContentType("text/xml;charset=UTF-8");		
	}
	
	public void setLocale(HttpServletRequest request){
		Locale currentLocale = (Locale) Config.get(request.getSession(),Config.FMT_LOCALE);
		if(currentLocale == null){
			String locale = PropUtil.getString(_DEFAULT_LOCALE);
			Cookie[] theCookies =request.getCookies();
			if (theCookies != null) {
		        for (int i =0; i< theCookies.length; i++) {
		           Cookie aCookie = theCookies[i];
		           if(aCookie.getName().equals(_REMEMBER_LOCALE)){
					   if(aCookie.getValue() != null) locale =  aCookie.getValue();
					   break;
		           }
		        }
		    }
			if(this.getUser(request) != null){	
				locale = this.getUser(request).getLanguage();
			}
			String[] localeParams = locale.split("_");
			if(localeParams.length > 1)
				 currentLocale = new Locale(localeParams[0],localeParams[1]);
			else
				 currentLocale = new Locale(localeParams[0]);
			Config.set(request.getSession(), Config.FMT_LOCALE, currentLocale);	
			request.getSession().setAttribute(_CURRENT_LOCALE,currentLocale.toString());			
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle(_DEFAULT_RESOURCE_BUNDLE, currentLocale);
		PortalContextFactory.getPortalContext().setCurrentLocale(currentLocale);
		PortalContextFactory.getPortalContext().setResourceBundle(resourceBundle);		
	}
	
	public boolean setLocale(HttpServletRequest request,String locale){
		 boolean ret = false;			 		 
		 if(locale != null){
			 String[] localeParams = locale.split("_");
			 Locale newLocale = null;
			 if(localeParams.length > 1)
				 newLocale = new Locale(localeParams[0],localeParams[1]);
			 else
				 newLocale = new Locale(localeParams[0]);
			 if(newLocale != null){
				Config.set(request.getSession(), Config.FMT_LOCALE, newLocale);
				request.getSession().setAttribute(_CURRENT_LOCALE,newLocale.toString());
				ResourceBundle resourceBundle = ResourceBundle.getBundle(_DEFAULT_RESOURCE_BUNDLE, newLocale);
				PortalContextFactory.getPortalContext().setCurrentLocale(newLocale);
				PortalContextFactory.getPortalContext().setResourceBundle(resourceBundle);		
				ret = true;
			 }
		 }
		 return ret;
   }
	public Locale getLocale(HttpServletRequest request){
    	Locale currentLocale = (Locale) Config.get(request.getSession(),Config.FMT_LOCALE);
		if(currentLocale == null){
	    	String locale = PropUtil.getString(_DEFAULT_LOCALE);
	    	if(request.getSession().getAttribute("currentLocale") != null)
	    		locale = (String)request.getSession().getAttribute("currentLocale");
	    	
	    	String[] localeParams = locale.split("_");
			if(localeParams.length > 1)
				 currentLocale = new Locale(localeParams[0],localeParams[1]);
			else
				 currentLocale = new Locale(localeParams[0]);
			ResourceBundle resourceBundle = ResourceBundle.getBundle(_DEFAULT_RESOURCE_BUNDLE, currentLocale);
			PortalContextFactory.getPortalContext().setCurrentLocale(currentLocale);
			PortalContextFactory.getPortalContext().setResourceBundle(resourceBundle);	
		}
		return currentLocale;
    }
	public long getPersonId(HttpServletRequest request,HttpServletResponse response){
		long personId = 0L;
		try{
			User user = this.getUser(request);
			if(isAuthorized(user)){
				if(user.getPersonId() > 0)
					personId = user.getPersonId();
				else{
					try{
						personId = Long.parseLong(getCookie(request,_PERSON_ID));
					}catch(Exception e){}
					if(personId <=0){
						personId = PortalUtil.createPersonId();
						setCookie(request,response,_PERSON_ID,String.valueOf(personId));
					}
					user.setPersonId(personId);
					this.getUserService(request).save(user);
				}
			}else{
				try{
					personId = Long.parseLong(getCookie(request,_PERSON_ID));
				}catch(Exception e){}
				if(personId <=0){
					personId = PortalUtil.createPersonId();
					setCookie(request,response,_PERSON_ID,String.valueOf(personId));
				}
				
			}
		}catch(Exception e){}
		return personId;
	}
	public String getCookie(HttpServletRequest request, String name){
		Cookie[] theCookies = request.getCookies();
		String personId = null;		
		if (theCookies != null) {
	        for (int i =0; i< theCookies.length; i++) {
	           Cookie aCookie = theCookies[i];
	           if(aCookie.getName().equals(name)){
				   if(aCookie.getValue() != null){
					   personId =  aCookie.getValue();	
					   break;					   
				   }
	           }
	        }
		}
		return personId;
	}
	
	public void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value){
		Cookie c = new Cookie(name, value);
		if(!"localhost".equalsIgnoreCase(request.getServerName()) && !ValidationUtil.isIPAddress(request.getServerName()))
			c.setDomain(DomainUtil.getCookieDomain(request));
        c.setMaxAge(365*24*60*60);
		response.addCookie(c);
	}
		
	public boolean isAdmin(HttpServletRequest request,User user){
		if(user == null) return false;
		if(user.getId() == OrganizationThreadLocal.getOrg().getUserId()) return false;
		return user.isAdmin();
	}
	public boolean isOwner(HttpServletRequest request){
		User user = this.getUser(request);
		User visited = this.getVisitedUser(request);
		if(user != null && visited != null
				&& user.getId() ==  visited.getId()) return true;		
		return false;
	}
	public boolean isPageOwner(HttpServletRequest request, long tabId){
		User user = this.getUser(request);
		PortalTab tab = this.getPortalService(request).getPortalTabById(tabId);
		Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
		if(user != null && tab != null
				&& user.getUserId().equals(portal.getOwnerId())) return true;		
		return false;
	}
	public boolean isPageOwner(HttpServletRequest request, PortalTab tab){
		User user = this.getUser(request);
		Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
		if(user != null && tab != null
				&& user.getUserId().equals(portal.getOwnerId())) return true;		
		return false;
	}
	public boolean isAuthorized(HttpServletRequest request){
		return this.isAuthorized(this.getUser(request));
	}
	public boolean isAuthorized(User user){
		if(user == null) return false;
		if(user.getId() == OrganizationThreadLocal.getOrg().getUserId()) return false;
		return true;
	}
	
	public boolean isGroupTabOwner(PortletRequest request,PortalTab tab, User user){
		return isGroupTabOwner(this.getServletRequest(request),tab,user);
	}
	
	public boolean isGroupTabOwner(HttpServletRequest request,PortalTab tab, User user){
		boolean value = false;
		Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
		if(portal.getOwnerId().startsWith(_GROUP_PREFIX)){
			int index = portal.getOwnerId().indexOf("_");
			try{
				long id = Long.parseLong(portal.getOwnerId().substring(index + 1));
				Group group = this.getGroupService(request).getGroupById(id);
				if(group != null && group.getLeaderId() == user.getId()){
					value = true;
				}
			}catch(Exception e){}	
		}
		return value;
	}
	
	public boolean  isVisitingMember(HttpServletRequest request){	
		String clientUrl = request.getParameter("clientUrl");
		return DomainUtil.isSubDomain(request) 
				|| request.getRequestURI().indexOf(_MEMBER_INDEX_PATTERN) >= 0 
				|| request.getRequestURI().indexOf(_SPACE_URL_PREFIX) >= 0
				|| (clientUrl != null && clientUrl.indexOf(_MEMBER_INDEX_PATTERN) >= 0) 
				|| (clientUrl != null && clientUrl.indexOf(_SPACE_URL_PREFIX) >= 0) 
				;	
	}
	 
	public boolean  isVisitingGroup(HttpServletRequest request){
		String clientUrl = request.getParameter("clientUrl");
		return  DomainUtil.isSubDomain(request) 
				|| request.getRequestURI().indexOf(_GROUP_INDEX_PATTERN) >= 0 
				|| request.getRequestURI().indexOf(_GROUP_URL_PREFIX) >= 0
				|| (clientUrl != null && clientUrl.indexOf(_GROUP_INDEX_PATTERN) >= 0) 
				||(clientUrl != null &&  clientUrl.indexOf(_GROUP_URL_PREFIX) >= 0)
				;
	}
	
	public boolean isValidInteralUri(HttpServletRequest request, String uri){
		//user and sub organization share same uri as subdomain name, so it has to be unique per user and sub organization
		return this.getPortalService(request).isNewUri(uri,OrganizationThreadLocal.getOrganizationId());
	}
	
	public void readOrganization(HttpServletRequest request){	   
       request.setAttribute("orgTitle",OrganizationThreadLocal.getWebId());
       String logoIcon = OrganizationThreadLocal.getLogoIcon();          
       String logo = OrganizationThreadLocal.getLogo();       
       if(this.getVisitedGroup(request) != null){
    	   Organization org = this.getUserService(request).getOrgById(this.getVisitedGroup(request).getId());
    	   if(org != null && !StringUtil.isEmpty(org.getLogoIcon()))
    		   logoIcon = org.getLogoIcon();
    	   if(org != null && !StringUtil.isEmpty(org.getLogoUrl()))
    		   logo = org.getLogoUrl();
       }
       if(!logoIcon.toLowerCase().startsWith("http")) logoIcon = request.getContextPath() + logoIcon;
       request.setAttribute("orgIcon",logoIcon);       
       if(!logo.toLowerCase().startsWith("http")) logo = request.getContextPath() + logo;
       request.setAttribute("orgLogo",logo);
	   OrgProfile orgProfile = OrganizationThreadLocal.getOrg().getProfileMap().get((String)request.getSession().getAttribute(_CURRENT_LOCALE));	   
	   if(orgProfile == null){
		   orgProfile = OrganizationThreadLocal.getOrg().getProfileMap().get(PropUtil.getString(_DEFAULT_LANGUAGE));
	   }
	   if(orgProfile != null){		  
		   request.setAttribute("orgKeywords",orgProfile.getMeta());
		   request.setAttribute("orgMeta",HTMLUtil.removeHTML(orgProfile.getView()));
		   if(!StringUtil.isEmpty(orgProfile.getView())) request.setAttribute("orgView", orgProfile.getView());
		   if(!StringUtil.isEmpty(orgProfile.getMaxView()))request.setAttribute("orgMaxView", orgProfile.getMaxView());
	   }
	}
	public User getVisitedUser(HttpServletRequest request){				
		return isVisitingMember(request) ? (User)request.getSession().getAttribute(_VISITED_USER) : null;
	}
	public void setVisitedUser(HttpServletRequest request,User user){		
		request.getSession().setAttribute(_VISITED_USER,user);
	}
	public void removeVisitedUser(HttpServletRequest request){				
		request.getSession().removeAttribute(_VISITED_USER);
	}
	public Portal getVisitedPortal(HttpServletRequest request){				
		return (Portal)request.getSession().getAttribute(_VISITED_PORTAL);
	}
	public void setVisitedPortal(HttpServletRequest request,Portal portal){		
		request.getSession().setAttribute(_VISITED_PORTAL,portal);
	}
	public void setVisitedPage(HttpServletRequest request,PortalTab tab){		
		request.getSession().setAttribute(_VISITED_PAGE,tab);
	}
	public void setVisitedGroup(HttpServletRequest request,Group group){		
		request.getSession().setAttribute(_VISITED_GROUP,group);
	}
	public void removeVisitedGroup(HttpServletRequest request){				
		request.getSession().removeAttribute(_VISITED_GROUP);
	}
	public PortalTab getVisitedPage(HttpServletRequest request){				
		return (PortalTab)request.getSession().getAttribute(_VISITED_PAGE);
	}
	public Group getVisitedGroup(HttpServletRequest request){		
		return (Group)request.getSession().getAttribute(_VISITED_GROUP);
	}
	public Portal getPortal(HttpServletRequest request){
		Portal portal = (Portal)request.getSession().getAttribute(_LIGHT_PORTAL);
		if(portal == null){			
			portal = this.getPortalService(request).getPortalByUser(this.getUser(request).getUserId(),this.getUser(request).getOrgId());
			setPortal(request,portal);
		}
		return portal;		
	}
	public void setPortal(HttpServletRequest request,Portal portal){
		request.getSession().setAttribute(_LIGHT_PORTAL,portal);
	}
    public PortalTab getPortalTab(HttpServletRequest request){
		String tabId = (String)request.getParameter("tabId");
		PortalTab portalTab = null;
		if(tabId != null){
			long tId = Long.parseLong(tabId);			
			portalTab =  getPortalService(request).getPortalTabById(tId);
		}
		return portalTab;
	}
    
    public List<PortletObject> getPortletsByTab(HttpServletRequest request,long id){    	
		return getPortalService(request).getPortletsByTab(id);
    }
    
	public PortletObject getPortlet(HttpServletRequest request){
		String portletId = request.getParameter("portletId");
		if(request.getAttribute("portletId") != null) portletId = (String)request.getAttribute("portletId");
		long id= 0L;
		try{
			id = Long.parseLong(portletId);
		}catch(Exception e){
			return null;
		}
		PortletObject portlet= getPortalService(request).getPortletById(id);			
		return portlet;
	}
	
	public ReplicationServer getReplicationServer(ServletContext context) {			
		return (ReplicationServer)getService(context, "replicationServer");
	}
	public CacheService getCacheService(HttpServletRequest request) {			
		return (CacheService)getService(request, "cacheService");
	}
	public CacheService getCacheService(PortletRequest request) {			
		return (CacheService)getService(getServletRequest(request), "cacheService");
	}
	public PingService getPingService(HttpServletRequest request) {			
		return (PingService)getService(request, "pingService");
	}
	public PortalService getPortalService(HttpServletRequest request) {			
		return (PortalService)getService(request, "portalService");
	}
	
	public PortalService getPortalService(ServletContext context) {			
		return (PortalService)getService(context, "portalService");
	}
	
	public PortletService getPortletService(HttpServletRequest request) {			
		return (PortletService)getService(request, "portletService");
	}
	
	public UserService getUserService(HttpServletRequest request) {			
		return (UserService)getService(request, "userService");
	}
	
	public ConnectionService getConnectionService(HttpServletRequest request) {			
		return (ConnectionService)getService(request, "connectionService");
	}
	
	public GroupService getGroupService(HttpServletRequest request) {			
		return (GroupService)getService(request, "groupService");
	}
	
	public ForumService getForumService(HttpServletRequest request) {			
		return (ForumService)getService(request, "forumService");
	}
	
	public BlogService getBlogService(HttpServletRequest request) {			
		return (BlogService)getService(request, "blogService");
	}
	
	public ChatService getChatService(HttpServletRequest request) {			
		return (ChatService)getService(request, "chatService");
	}
	
	public StatusService getStatusService(HttpServletRequest request) {			
		return (StatusService)getService(request, "statusService");
	}
	
	public AdService getAdService(HttpServletRequest request) {			
		return (AdService)getService(request, "adService");
	}
	
	public CalendarService getCalendarService(HttpServletRequest request) {			
		return (CalendarService)getService(request, "calendarService");
	}
	
	public MicroblogService getMicroblogService(HttpServletRequest request) {			
		return (MicroblogService)getService(request, "microblogService");
	}
	
	public ContentLibraryService getContentLibraryService(HttpServletRequest request) {			
		return (ContentLibraryService)getService(request, "contentLibraryService");
	}
	
	public NotificationService geNotificationService(HttpServletRequest request) {			
		return (NotificationService)getService(request, "notificationService");
	}
	public AfterTaskService getAfterTaskService(HttpServletRequest request) {			
		return (AfterTaskService)getService(request, "afterTaskService");
	}
	
	public Object getService(HttpServletRequest request, String serviceName) {
		ApplicationContext ctx = (ApplicationContext) request.getSession().getServletContext()		
        							.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);		
		return ctx.getBean(serviceName);
	}
	
	public Object getService(ServletContext context, String serviceName) {
		ApplicationContext ctx = (ApplicationContext) context		
        							.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);		
		return ctx.getBean(serviceName);
	}
	public HttpServletRequest getServletRequest(PortletRequest request){
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getAttribute("httpServletRequest");		
		return httpServletRequest;
	}
	public User getUser(HttpServletRequest request){
		String userId = request.getParameter("_requestOwnerId");
		if(userId != null) try{userId = URLDecoder.decode(userId,_CHARSET_UTF);}catch(Exception e){}		
		User user = (User)request.getSession().getAttribute(_USER);		
		if(userId != null && !"null".equalsIgnoreCase(userId) && (user == null || !userId.equals(user.getUserId()))){
			User newUser = this.getUserService(request).getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
			if(newUser != null){
				user = newUser;
				this.setUser(request,user,true);
			}		
		}
		return user;
	}
	
	public void setUser(HttpServletRequest request,User user){
		if(user != null){
			Map<String,String> userData = new HashMap<String,String>();
			userData.put(_LIGHT_PORTAL_USER_ID,user.getUserId());
			request.getSession().setAttribute(PortletRequest.USER_INFO,userData);
			request.getSession().setAttribute(_USER,user);
		}
	}
	public void setUser(HttpServletRequest request,User user, boolean login){
		this.setUser(request,user);
		if(user != null && login){			
			request.getSession().setAttribute(_LOGGED_IN,true);
		}
	}
	
	
	private static Context _instance = new Context();
	public static Context getInstance(){
		return _instance;
	}
	private Context(){
		
	}
}
