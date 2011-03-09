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

import static org.light.portal.util.Constants._CHARSET_UTF;
import static org.light.portal.util.Constants._EVENTS_LOGIN_AFTER;
import static org.light.portal.util.Constants._EVENTS_LOGIN_BEFORE;
import static org.light.portal.util.Constants._EVENTS_LOGOUT_AFTER;
import static org.light.portal.util.Constants._EVENTS_LOGOUT_BEFORE;
import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._JAVASCRIPT_LIBRARYS;
import static org.light.portal.util.Constants._LANGUAGE_ALL;
import static org.light.portal.util.Constants._MY_FEED_TITLE;
import static org.light.portal.util.Constants._MY_FILE_PATH;
import static org.light.portal.util.Constants._MY_IMAGE_PATH;
import static org.light.portal.util.Constants._MY_MUSIC_PATH;
import static org.light.portal.util.Constants._PERSON_ID;
import static org.light.portal.util.Constants._PORTLET_MINIMIZED;
import static org.light.portal.util.Constants._PORTLET_MODE_HEADER;
import static org.light.portal.util.Constants._PORTLET_RENDER_ID_PREFIX;
import static org.light.portal.util.Constants._PORTLET_SYNC_LOAD;
import static org.light.portal.util.Constants._PRIVACY_CONNECTION;
import static org.light.portal.util.Constants._PRIVACY_HIDDEN;
import static org.light.portal.util.Constants._PRIVACY_MEMBER;
import static org.light.portal.util.Constants._PRIVACY_PROFILE;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import static org.light.portal.util.Constants._REMEMBER_USER_ID;
import static org.light.portal.util.Constants._REMEMBER_USER_PASSWORD;
import static org.light.portal.util.Constants._REPLICATION_ENABLED_FILE;
import static org.light.portal.util.Constants._ROLE_NO_PROFILE;
import static org.light.portal.util.Constants._ROLE_PROFILE;
import static org.light.portal.util.Constants._STATUS_OFFLINE;
import static org.light.portal.util.Constants._STATUS_ONLINE;
import static org.light.portal.util.Constants._USER;
import static org.light.portal.util.Constants._USER_PATH;
import static org.light.portal.util.Constants._VISITED_GROUP;
import static org.light.portal.util.Constants._VISITED_STORE;
import static org.light.portal.util.Constants._VISITED_USER;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.portlet.Portlet;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.core.PortalUtil;
import org.light.portal.core.event.EventHandler;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;
import org.light.portal.model.UserFile;
import org.light.portal.model.UserMusic;
import org.light.portal.model.UserPicture;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.portlet.core.impl.PortletWindow;
import org.light.portal.portlet.factory.PortletContainerFactory;
import org.light.portal.util.FileUtil;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.chat.Chatting;
import org.light.portlets.chat.service.ChatService;
import org.light.portlets.connection.Connection;
import org.light.portlets.group.Group;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
/**
 * 
 * @author Jianmin Liu
 **/
public class PortalCommands extends BaseAction{

	/**
     * login service which can be called using /login.lp?userId=?&password=?
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return integer: 1 succeed; -1 user not found; -2 wrong password; -3 user disabbled; -4 user locked
     */	
	public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 EventHandler.invoke(_EVENTS_LOGIN_BEFORE,request,response);
		
		 String userId = request.getParameter("userId");
		 String password = request.getParameter("password");	
		 String rememberme = request.getParameter("rememberme");
		 if(userId != null) userId = URLDecoder.decode(userId,_CHARSET_UTF);		   	
		 if(password != null) password = URLDecoder.decode(password,_CHARSET_UTF);  

		 User user = this.getUserService(request).getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
		 int status = Context.getInstance().checkLogin(request,user,password,false);		 		
		 if(status == 1){			
			 Enumeration attributes=request.getSession().getAttributeNames();
			 while (attributes.hasMoreElements()){
	              String attribute = (String)attributes.nextElement();
	              if(!_VISITED_USER.equals(attribute)         	  
	            	  && !_VISITED_GROUP.equals(attribute) 
	            	  && !_VISITED_STORE.equals(attribute))
	            	  request.getSession().removeAttribute(attribute); 
			 }
			 long personId = 0;
			 try{
				 personId = Long.parseLong(Context.getInstance().getCookie(request,_PERSON_ID));		
			 }catch(Exception e){}
			 this.setUser(request ,user,true);			 		
			 if(user.getPersonId() == 0){
				 if(personId <= 0)
					 user.setPersonId(PortalUtil.createPersonId());
				 else
					 user.setPersonId(personId);
			 }
			 if(user.getPersonId() > 0 && user.getPersonId() != personId){
				 Context.getInstance().setCookie(request,response,_PERSON_ID,String.valueOf(user.getPersonId()));
			 }
			 user.setCurrentStatus(_STATUS_ONLINE);		
			 this.getUserService(request).save(user);
			 this.setLocale(request, user.getLanguage());			 
			 if(rememberme != null){
				 Context.getInstance().setCookie(request,response,_REMEMBER_USER_ID,PortalUtil.encode(user.getUserId()));
				 Context.getInstance().setCookie(request,response,_REMEMBER_USER_PASSWORD,user.getPassword());
			 }
			 
			 EventHandler.invoke(_EVENTS_LOGIN_AFTER,request,response);
			 //start after login task thread
			 this.getAfterTaskService(request).afterLogin(user);
		 }
		 JSONObject json = new JSONObject();
		 json.put("status",status);
		 return json;
	}
	
	/**
     * logout service which can be called using /logout.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return none
     */	
	public Object logout(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		 EventHandler.invoke(_EVENTS_LOGOUT_BEFORE,request,response);
		 User user = this.getUser(request);
		 if(user != null){
			 long userId = user.getId();			 
			 PortalTab page = this.getVisitedPage(request);
			 User visit = this.getVisitedUser(request);
			 Group group = this.getVisitedGroup(request);
			 Locale locale = this.getLocale(request);
			 request.getSession().removeAttribute(_USER);
			 request.getSession().invalidate();
			 this.setLocale(request,locale.toString());
			 if(page != null) this.setVisitedPage(request, page);
			 if(visit != null) this.setVisitedUser(request, visit);
			 if(group != null) this.setVisitedGroup(request, group);
			 Organization org = OrganizationThreadLocal.getOrg();
			 if(org == null) org = this.getUserService(request).getOrgById(user.getOrgId());
			 if(userId != org.getUserId()){
				 ChatService chatService = this.getChatService(request);
				 user = this.getUserService(request).getUserById(userId);
				 user.setCurrentStatus(_STATUS_OFFLINE);
				 this.getUserService(request).save(user);
				 chatService.leaveChattingByUser(userId); 
			 }
		 }
		 EventHandler.invoke(_EVENTS_LOGOUT_AFTER,request,response);
		 return "";	
	}
	
	/**
     * checking login service which can be called using /loadPortal.lp?userId=?&password=?
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON Array: which include userId, portal render data, portal tabs render data.
     * if logined successfully return login user's data or return default user's.
     */
	public Object loadPortal(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		this.removeVisitedUser(request);
		this.removeVisitedGroup(request);		
		User user = this.getUser(request);
		if(userId != null && password != null && !userId.equalsIgnoreCase(user.getUserId())){
			this.login(request,response);			
			user = this.getUser(request);
		}
		JSONArray jArray = new JSONArray();
		jArray.put(this.getPortal(request,response));
		jArray.put(this.getPortalTabsByUser(request,response));
		return jArray;
	}
	
	/**
     * get portal data service which can be called using /getPortal.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON: login user's or default user's 
     */
	public Object getPortal(HttpServletRequest request, HttpServletResponse response) throws Exception{						
		this.setVisitedGroup(request,null);
		User user = this.getUser(request);
		if(user == null){
			user = OrganizationThreadLocal.getOrg().getUser();
			if(user == null)
				user = this.getUserService(request).getUserByUserId(OrganizationThreadLocal.getDefaultUserId(),OrganizationThreadLocal.getOrganizationId() );
			this.setUser(request,user);
		}			
		Portal portal = this.getPortal(request);			
		return JSONUtil.getPortalData(request,portal,user.getDisplayName(),user,user.getId(),user,isAdmin(request));	
	}	
	/**
     * get user profile portal data service which can be called using /profilePortal.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON
     */
	public Object profilePortal(HttpServletRequest request, HttpServletResponse response) throws Exception{				
		User owner =this.getVisitedUser(request);
		owner.setVisitCount(owner.getVisitCount() + 1);
		this.getUserService(request).save(owner);
		Portal portal = this.getVisitedPortal(request);			
		return JSONUtil.getPortalData(request,portal,this.getUser(request).getDisplayName(),owner,owner.getId(),this.getUser(request),isAdmin(request));
	}	
	/**
     * get visiting portal data service which can be called using /visitPortal.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON
     */
	public Object visitPortal(HttpServletRequest request, HttpServletResponse response) throws Exception{				
		Portal portal = this.getVisitedPortal(request);		
		return JSONUtil.getPortalData(request,portal,this.getUser(request).getDisplayName(),null,0,this.getUser(request),isAdmin(request));		
	}	
	/**
     * get page portal data service which can be called using /pagePortal.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON
     */
	public Object pagePortal(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		Portal portal = this.getVisitedPortal(request);
		User owner = this.getUserService(request).getUserByUserId(portal.getOwnerId(),OrganizationThreadLocal.getOrganizationId() );		
		return JSONUtil.getPortalData(request,portal,this.getUser(request).getDisplayName(),owner,owner.getId(),this.getUser(request),isAdmin(request));
	}	
	/**
     * get group portal data service which can be called using /groupPortal.lp
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON
     */
	public Object groupPortal(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		Group group =this.getVisitedGroup(request);
		group.setViewCount(group.getViewCount() + 1);
		this.getPortalService(request).save(group);
		Portal portal = this.getPortal(request);
		return JSONUtil.getPortalData(request,portal,this.getUser(request).getDisplayName(),null,group.getLeaderId(),this.getUser(request),isAdmin(request));	
	}	
	
	/**
     * checking login service which can be called using /loadPortal.lp?userId=?&password=?
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exeption
     * 
     * return JSON Array: which include userId, portal render data, portal tabs render data.
     * if logined successfully return login user's data or return default user's.
     */
	public Object loadPortalTabContent(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		List<PortalTab> tabs = getPortalTabByParent(request,response);
		JSONObject json = new JSONObject();
		json.put("subTabs",JSONUtil.getPortalTabData(request,tabs, false, isAdmin(request), this.getUser(request), 0));
		String tabId = request.getParameter("parentId");
		long defaultTabId = Long.parseLong(tabId);
		if(tabs != null && tabs.size() > 0){
			defaultTabId = tabs.get(0).getId();
			for(PortalTab tab : tabs){
				if(tab.getDefaulted() == 1)
					defaultTabId = tab.getId();
			}
		}	
		request.setAttribute("tabId", String.valueOf(defaultTabId));
		json.put("portlets",this.getPortletsByTab(request,response));
		List<PortletObject> portlets = this.getPortalService(request).getPortletsByTab(defaultTabId);               
    	for(PortletObject po : portlets){		    		
    		if(po.getType() >= _PORTLET_SYNC_LOAD){
    			try{
		    		request.setAttribute("portletId",String.valueOf(po.getId()));
		    		request.setAttribute("portletObject",po);
	        		request.setAttribute("responseId",_PORTLET_RENDER_ID_PREFIX+po.getId());
	        		PortletMode mode = PortletMode.VIEW;	        		
	        		WindowState state = WindowState.NORMAL;
        		    if(po.getWindowStatus() == 1) state = WindowState.MINIMIZED;   
        		    if(po.getWindowStatus() == 2) state = WindowState.MAXIMIZED; 
        		    if(po.getMode() == 1) mode = PortletMode.EDIT;        		    
        		    if(po.getMode() == 2) mode = PortletMode.HELP;    
	        		String suffix = ".view";
	        		if(po.getType() > _PORTLET_SYNC_LOAD) suffix = ".data";
	        		if(WindowState.MAXIMIZED.equals(state))	suffix=".max"+suffix;
	        		String portletName = po.getPath();
	        		if(portletName.startsWith("/")) portletName = portletName.substring(1);
	        		if(portletName.indexOf(".") > 0) portletName = portletName.substring(0,portletName.indexOf("."));
	        		PortletWindow portletWindow = new PortletWindow(portletName, state, mode, po);
		    		if(po.getWindowStatus() != _PORTLET_MINIMIZED){
		        		response.getWriter().print("<textarea id='portlet"+po.getId()+suffix+"' style='display:none;' rows='0' cols='0'>"); 
			    		PortletContainerFactory.getPortletContainer().renderPortlet(portletWindow, request, response);
			    		response.getWriter().println("</textarea>"); 
		    		}
		    		suffix = ".title";
		    		portletWindow.setPortletMode(new PortletMode(_PORTLET_MODE_HEADER));
		    		response.getWriter().print("<textarea id='portlet"+po.getId()+suffix+"' style='display:none;' rows='0' cols='0'>"); 
		    		PortletContainerFactory.getPortletContainer().renderPortlet(portletWindow, request, response);
		    		response.getWriter().println("</textarea>"); 				    		
    			}catch(Exception e){
    				//ignore individual portlet rendering exception
    				logger.error(e);
    			}
    		}
     	}	
		return json;
	}
	
	public Object getPortalTabsByUser(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		User user = this.getUser(request);
		List<PortalTab> tabs = getPortalService(request).getPortalTabByUser(user,OrganizationThreadLocal.getOrg());
		return JSONUtil.getPortalTabData(request,tabs, true, isAdmin(request), user, 0);	
	}			
	public Object getPortalTabsByVisit(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		User user = this.getVisitedUser(request);				
		if(user.getProfileFriendViewOnly() == 1){
			User visitor = this.getUser(request);
			if(this.getConnectionService(request).getChatBuddy(user.getId(),visitor.getId()) == null){		
				List<PortalTab> noProfileTabs = getPortalService(request).getPortalTabByOwner(_ROLE_NO_PROFILE,OrganizationThreadLocal.getOrg());				
				return JSONUtil.getPortalTabData(request,noProfileTabs, false, isAdmin(request), this.getUser(request), 0);
			}			
		}
		List<PortalTab> profileTabs = getPortalService(request).getUserProfilePortalTab(user.getUserId(),user.getOrgId());
		if(profileTabs == null || profileTabs.size() == 0){
			profileTabs = getPortalService(request).getPortalTabByOwner(_ROLE_PROFILE,OrganizationThreadLocal.getOrg());			
			return JSONUtil.getPortalTabData(request,profileTabs, false, isAdmin(request), this.getUser(request), 0);
		}
		return JSONUtil.getPortalTabData(request,profileTabs, false, isAdmin(request), this.getUser(request), 0);
	}	
	public Object getPortalTabsByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{					
		PortalTab tab = this.getVisitedPage(request);	
		List<PortalTab> tabs = new ArrayList<PortalTab>(); 
		tabs.add(tab);		
		boolean authorized = tab.getOwnerId().equals(this.getUser(request).getUserId());
		return JSONUtil.getPortalTabData(request,tabs, authorized, isAdmin(request), this.getUser(request), 0);
	}	
	public Object getPortalTabsByChannel(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String channel = (String)request.getAttribute("channel");
		List<PortalTab> tabs = getPortalService(request).getPortalTabByOwner(channel,OrganizationThreadLocal.getOrg());		
		return JSONUtil.getPortalTabData(request,tabs, false, isAdmin(request), this.getUser(request), 0);					
	}
	public Object getPortalTabsByGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		Group group = this.getVisitedGroup(request);		
		List<PortalTab> tabs = getPortalService(request).getPortalTabByGroup(group.getId(),OrganizationThreadLocal.getOrg());
		boolean authorized = group.getLeaderId()==this.getUser(request).getId();
		return JSONUtil.getPortalTabData(request,tabs, authorized, isAdmin(request), this.getUser(request), 0);	
	}		
	public Object getPortalTabById(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		try{
			String tabId = request.getParameter("tabId");
			long id = 0;
			try{ id = Long.parseLong(tabId);}catch(Exception e){}
			if(id > 0){
				PortalTab tab = getPortalService(request).getPortalTabById(id);
				if(tab != null){				
					User tabUser = this.getUserService(request).getUserByUserId(tab.getOwnerId(),OrganizationThreadLocal.getOrganizationId());				
					Connection connection = this.getConnectionService(request).getChatBuddy(this.getUser(request).getId(),tabUser.getId());			
					if( this.isAdmin(request)
						|| (tab.getStatus() == _PRIVACY_PUBLIC) 				      
						|| (tab.getStatus() == _PRIVACY_PROFILE && tabUser.getProfileFriendViewOnly() == 0)
						|| (tab.getStatus() == _PRIVACY_MEMBER && this.isAuthorized(this.getUser(request)))
						|| (tab.getStatus() == _PRIVACY_CONNECTION &&  connection != null)){
						boolean authorized = isPageOwner(request,tab);
						List<PortalTab> tabs = new ArrayList<PortalTab>();
						tabs.add(tab);
						return JSONUtil.getPortalTabData(request,tabs, authorized, isAdmin(request), this.getUser(request), 0);
					}
				}
			}
		}catch(Exception e){}
		return "";
	}
	public Object getPortalTabsByParent(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		List<PortalTab> tabs = getPortalTabByParent(request,response);
		return JSONUtil.getPortalTabData(request,tabs, false, isAdmin(request), this.getUser(request), 0);
	}
	private List<PortalTab> getPortalTabByParent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String parentId = request.getParameter("parentId");
		if(parentId == null) parentId = (String)request.getAttribute("parentId");
		long id = Long.parseLong(parentId);
		return getPortalService(request).getPortalTabByParent(id);
	}
	public Object showSubTab(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String parentId = request.getParameter("tabId");
		List<PortalTab> tabs = getPortalService(request).getPortalTabByParent(Long.parseLong(parentId));
		if(tabs != null && tabs.size() > 0){
			JSONArray jArray = new JSONArray();
			for(PortalTab tab : tabs){	
				JSONObject json = new JSONObject();
				json.append("parentId",parentId);
				json.append("serverId",tab.getId());
				json.append("label",tab.getTitle());
				jArray.put(json);
			}
			return jArray;
		}
		return "";
	}
	public Object getPortletsByTab(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(this.getVisitedUser(request) != null) return this.getPortletsByVisitTab(request,response);
		if(this.getVisitedGroup(request) != null) return this.getPortletsByGroupTab(request,response);		
		String tabId = (String)request.getParameter("tabId");
		if(tabId == null) tabId = (String)request.getAttribute("tabId");
		long id = 0;
		try{ id = Long.parseLong(tabId);}catch(Exception e){}
		if(id > 0){		
			List<PortletObject> portletList = getPortalService(request).getPortletsByTab(id);
			boolean authorized = isPageOwner(request,id);								
			return JSONUtil.getPortletData(portletList, Long.parseLong(tabId), authorized, isAdmin(request), this.getUser(request), request);
		}
	    return "";					
	}	
	public Object getPortletsByVisitTab(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String tabId = (String)request.getParameter("tabId");
		if(tabId == null) tabId = (String)request.getAttribute("tabId");
		if(tabId != null){
			List<PortletObject> portletList = getPortalService(request).getPortletsByTab(Long.parseLong(tabId));
			return JSONUtil.getPortletData(portletList, Long.parseLong(tabId), (isAdmin(request) || isOwner(request)), isAdmin(request), this.getUser(request), request);
		}
	    return "";					
	}
	public Object getMobilePortletsByUser(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		User user = this.getUser(request);
		if(user != null){
			List<PortalTab> tabs = this.getPortalService(request).getUserPersonalPortalTab(user.getUserId(),user.getOrgId());
	    	if(tabs != null){
	    		List<PortletObject> portlets = new LinkedList<PortletObject>();
	    		for(PortalTab tab : tabs){
    				portlets.addAll(this.getPortalService(request).getPortletsByTab(tab.getId()));    
	    		}
	    		return JSONUtil.getPortletData(portlets, 0, (isAdmin(request) || isOwner(request)), isAdmin(request), this.getUser(request), request);
	    	} 
		}
		return "";
	}	
	public Object getPortletsByGroupTab(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		String tabId = (String)request.getParameter("tabId");
		if(tabId == null) tabId = (String)request.getAttribute("tabId");
		Group group =this.getVisitedGroup(request);
		boolean authorized = isAdmin(request) || group.getLeaderId()==this.getUser(request).getId();
		if(tabId != null){
			List<PortletObject> portletList = getPortalService(request).getPortletsByTab(Long.parseLong(tabId));	
			return JSONUtil.getPortletData(portletList, Long.parseLong(tabId), authorized, isAdmin(request), this.getUser(request), request);
		}
	    return "";					
	}
	
	public Object listenServer(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		JSONObject json = new JSONObject();		
		User user = this.getUser(request);
		if(user != null && user.getId() != OrganizationThreadLocal.getOrg().getUserId()){
			if(user.getCurrentStatus() != _STATUS_ONLINE){
				user.setCurrentStatus(_STATUS_ONLINE);
				this.getUserService(request).save(user);
			}
			this.getStatusService(request).check(user);
			Chatting chatting = this.getChatService(request).getChattingByUser(user.getId());
			if(chatting != null){
				User fromUser = this.getUserService(request).getUserById(chatting.getUserId());		
				if(fromUser != null){
					JSONObject jsonChat = new JSONObject();
					jsonChat.put("event","chat");				
					jsonChat.put("eventId",chatting.getId());
					jsonChat.put("from",fromUser.getName());
					jsonChat.put("ring",fromUser.getRingToneUrl());
					json.put("chat",jsonChat);
				}
			}
			if(this.getVisitedUser(request) == null 
				&& this.getVisitedPortal(request) == null
				&& this.getVisitedPage(request) == null
				&& this.getVisitedGroup(request) == null){
				List<PortalTab> tabs = this.getPortalService(request).getPortalTabByUser(user, OrganizationThreadLocal.getOrg());
				if(tabs != null){
					JSONArray tabNotification = new JSONArray();
					JSONArray portletNotification = new JSONArray();
					for(PortalTab tab : tabs){
						List<PortletObject> portlets = Context.getInstance().getPortalService(request).getPortletsByTab(tab.getId());
						int notifications = 0;
						for(PortletObject po : portlets){
							Portlet portlet = PortletContainerFactory.getPortletContainer().getPortlet(po.getName());
							if(portlet instanceof LightGenericPortlet){
								request.setAttribute("portletId",po.getId());
								Object notification = ((LightGenericPortlet)portlet).doNotification(request);
								if(notification instanceof JSONObject){
									JSONObject jsonNotification = (JSONObject)notification;		
									jsonNotification.put("portletId",po.getId());
									portletNotification.put(jsonNotification);
									if(jsonNotification.has("notification")) notifications += jsonNotification.getInt("notification");
								}
								
							}
						}
						if(notifications > 0){
							JSONObject jsonNotification = new JSONObject();		
							jsonNotification.put("tabId",tab.getId());
							jsonNotification.put("notification",notifications);
							tabNotification.put(jsonNotification);											
						}
					}
					json.put("tabNotification",tabNotification);
					json.put("portletNotification",portletNotification);
				}
			}
		}
		return json;
	}
	
	public Object configPortlet(HttpServletRequest request, HttpServletResponse response) throws Exception{				
		String title = request.getParameter("title");
		if(title != null) title = URLDecoder.decode(title,_CHARSET_UTF);				
		String barBgColor = request.getParameter("barBgColor");
		String barFontColor = request.getParameter("barFontColor");
		String contentBgColor = request.getParameter("contentBgColor");	
		String textColor = request.getParameter("textColor");	
		String transparent = request.getParameter("transparent");	
		String windowSkin = request.getParameter("windowSkin");
		String windowStatus = request.getParameter("windowStatus");	
		String autoRefreshed = request.getParameter("autoRefreshed");
		String periodTime = request.getParameter("periodTime");
		String showNumber = request.getParameter("showNumber");		
		String scolspan = request.getParameter("colspan");
		String smarginTop = request.getParameter("marginTop");
		String showIcon = request.getParameter("showIcon");	
		String client = request.getParameter("client");	
		int colspan = 0;
		try{
			colspan = Integer.parseInt(scolspan);
		}catch(Exception e){}
		int marginTop = 0;
		try{
			marginTop = Integer.parseInt(smarginTop);
		}catch(Exception e){}
		PortletObject portlet =getPortlet(request);		
		if(portlet != null){
			portlet.setLabel(title);
			portlet.setBarBgColor(barBgColor);
			portlet.setBarFontColor(barFontColor);
			portlet.setContentBgColor(contentBgColor);
			portlet.setTextColor(textColor);
			portlet.setTransparent((transparent == null) ? 0 : Integer.parseInt(transparent));
			portlet.setWindowSkin(windowSkin);
			portlet.setAutoRefreshed((autoRefreshed == null) ? 0 : Integer.parseInt(autoRefreshed));
			portlet.setPeriodTime((periodTime == null) ? 0 : Integer.parseInt(periodTime) * 1000);
			portlet.setShowNumber((showNumber == null) ? 0 : Integer.parseInt(showNumber));
			portlet.setColspan(colspan);
			portlet.setMarginTop(marginTop);
			portlet.setWindowStatus((windowStatus == null) ? 0 : Integer.parseInt(windowStatus));
			portlet.setShowIcon((showIcon == null) ? 1 : Integer.parseInt(showIcon));
			portlet.setClient((client == null) ? 0 : Integer.parseInt(client));
			this.getPortalService(request).save(portlet);
		}
		return "";
	}
	public Object rememberState(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		if(this.getVisitedUser(request) == null){
			String state = request.getParameter("state");				
			PortletObject portlet = this.getPortlet(request);
			if(portlet != null && state != null){
				PortalTab tab = this.getPortalService(request).getPortalTabById(portlet.getTabId());
				Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
				if(this.getUser(request).getId() != OrganizationThreadLocal.getOrg().getUserId()
						&& (this.getUser(request).getUserId().equals(portal.getOwnerId()))
							|| isAdmin(request)){										
					int s = Integer.parseInt(state);
					if(s == 0 || s == 1){
						portlet.setWindowStatus(s);
						this.getPortalService(request).save(portlet);
					}
				}
			}
		}
		return "";
	}	
	public Object rememberMode(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		if(this.getVisitedUser(request) == null){
			String mode = request.getParameter("mode");				
			PortletObject portlet = this.getPortlet(request);
			if(portlet != null && mode != null){
				PortalTab tab = this.getPortalService(request).getPortalTabById(portlet.getTabId());
				Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
				if(this.getUser(request).getId() != OrganizationThreadLocal.getOrg().getUserId()
						&& (this.getUser(request).getUserId().equals(portal.getOwnerId()))
							|| isAdmin(request)){
					int m = Integer.parseInt(mode);
					if((m == 0) || (m == 1 && portlet.isSupportEditMode())){
						portlet.setMode(m);
						this.getPortalService(request).save(portlet);
					}
				}
			}
		}
		return "";
	}
	
	public Object changeTitle (HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String title = request.getParameter("title");	
		 if(title != null){
			 if(isAuthorized(this.getUser(request))){
				 Portal portal = this.getPortal(request);
				 portal.setTitle(title);
				 this.getPortalService(request).save(portal);
				 this.setPortal(request,portal);
			 }
				 
		 }
		 return "";
    }
	
	public Object changeLanguage(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String language = request.getParameter("language");
		int returnValue=0;
		if(language != null && this.setLocale(request,language)){
			User user = this.getUser(request);
			if(isAuthorized(user)){				
				user.setLanguage(language);
				user.setRegion(language);
				try{
					this.getUserService(request).save(user);
					returnValue = 1;
				}catch(Exception e){}
			}
		}
		JSONObject json = new JSONObject();
		json.put("status",returnValue);
		return json;
	}
	
	public Object changeRegion (HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String region = request.getParameter("region");
		int returnValue=0;
		User user = this.getUser(request);
		if(region != null && isAuthorized(user)){	
			try{
				user.setRegion(region);
				this.getUserService(request).save(user);
				request.getSession().removeAttribute("defaultLists");
				returnValue = 1;
			}catch(Exception e){}
		}
		JSONObject json = new JSONObject();
		json.put("status",returnValue);
		return json;
	}
	
	public Object changeTimeZone (HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String timeZone = request.getParameter("timeZone");
		int returnValue=0;
		User user = this.getUser(request);
		if(timeZone != null && isAuthorized(user)){				
			try{
				user.setTimeZone(timeZone);
				this.getUserService(request).save(user);
				request.getSession().removeAttribute("defaultLists");
				returnValue = 1;
			}catch(Exception e){}
		}
		JSONObject json = new JSONObject();
		json.put("status",returnValue);
		return json;
	}
	
	public Object changeGeneral (HttpServletRequest request, HttpServletResponse response) throws Exception{		
		 String showSearchBar = request.getParameter("showSearchBar");
		 String headerHeight = request.getParameter("headerHeight");
		 String textFont = request.getParameter("textFont");
		 String fontSize = request.getParameter("fontSize");
		 String textColor = request.getParameter("textColor");
		 String transparent = request.getParameter("transparent");		 
		 String maxShowTabs=request.getParameter("maxShowTabs");
		 int returnValue= 0;
		 User user = this.getUser(request);
		 if(isAuthorized(user)){
			 Portal portal = this.getPortal(request);
			 if(isAdmin(request) && this.getVisitedPortal(request) !=null) portal = this.getVisitedPortal(request);
			 if(headerHeight != null && Integer.parseInt(headerHeight) != portal.getHeaderHeight()){
				 portal.setHeaderHeight(Integer.parseInt(headerHeight));				 
			 }
			 if(fontSize != null && Integer.parseInt(fontSize) != portal.getFontSize()){
				 portal.setFontSize(Integer.parseInt(fontSize));
			 }
			 portal.setTextFont(textFont); 
			 portal.setTextColor(textColor);
			 if(showSearchBar != null && "1".endsWith(showSearchBar))
				 portal.setShowSearchBar(1);
			 else
				 portal.setShowSearchBar(0);
			 if(transparent != null){				 
				 portal.setTransparent(Integer.parseInt(transparent));
			 }
			 if(!StringUtil.isEmpty(maxShowTabs))
				 portal.setMaxShowTabs(Integer.parseInt(maxShowTabs));
			 try{
				 this.getPortalService(request).save(portal);				 
				 returnValue = 1;
			 }catch(Exception e){}
		}
		JSONObject json = new JSONObject();
		json.put("status",returnValue);
		return json;
	}
		
	public Object changeTheme (HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String theme = request.getParameter("theme");	
		 String bgImage = request.getParameter("bgImage");
		 String bgRepeat = request.getParameter("bgRepeat");
		 String headerImage = request.getParameter("headerImage");
		 String headerRepeat = request.getParameter("headerRepeat");
		 String headerHeight = request.getParameter("headerHeight");
		 String transparent = request.getParameter("transparent");
		 int returnValue=1;
		 try{
			 if(theme != null){
				 request.getSession().setAttribute("theme",theme);
				 User user = this.getUser(request);
				 if(user != null){
					 Portal portal = this.getPortal(request);
					 if(isAdmin(request) && this.getVisitedPortal(request) !=null) portal = this.getVisitedPortal(request);
					 if(!theme.equals(portal.getTheme())){
						 portal.setTheme(theme);
					 }
					 if(bgImage != null && !bgImage.equals(portal.getBgImage())){
						 portal.setBgImage(bgImage);				 
					 }
					 if(bgRepeat != null && Integer.parseInt(bgRepeat) != portal.getBgRepeat() ){
						 portal.setBgRepeat(Integer.parseInt(bgRepeat));
					 }	
					 if(headerImage != null && !headerImage.equals(portal.getHeaderImage())){
						 portal.setHeaderImage(headerImage);					 
					 }
					 if(headerRepeat != null && Integer.parseInt(headerRepeat) != portal.getHeaderRepeat() ){
						 portal.setHeaderRepeat(Integer.parseInt(headerRepeat));				
					 }
					 if(headerHeight != null)
						 portal.setHeaderHeight(Integer.parseInt(headerHeight));
					 if(transparent != null){
						 portal.setTransparent(1);
					 }else{
						 portal.setTransparent(0);
					 }				 
					 this.getPortalService(request).save(portal);					 	
				 }	 
			 }
		 }catch(Exception e){
			 returnValue = 0;
			 throw e;
		 }
		JSONObject json = new JSONObject();
		json.put("status",returnValue);
		return json;
    }
	
	public Object editTabTitle (HttpServletRequest request,	HttpServletResponse response) throws Exception{
      String title = request.getParameter("title");
      if(title != null) title = URLDecoder.decode(title,_CHARSET_UTF);
	  PortalTab tab = getPortalTab(request);
	  Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());		
	  if(portal.getOwnerId().equals(this.getUser(request).getUserId())
		 || this.isAdmin(request) 
		 || isGroupTabOwner(request,tab, this.getUser(request))){
		  tab.setLabel(title);
		  this.getPortalService(request).save(tab);
	  }
      return "";
   }
	
	public Object manageTab (HttpServletRequest request, HttpServletResponse response) throws Exception{		
		 String title = request.getParameter("title");
		 if(title != null) title = URLDecoder.decode(title,_CHARSET_UTF);
		 int columns = Integer.parseInt(request.getParameter("columns"));
		 String widths= request.getParameter("widths");			 
		 String close = request.getParameter("closeable");
		 int closeable = Integer.parseInt(close);
		 String defaultedStr = request.getParameter("defaulted");
		 int defaulted = Integer.parseInt(defaultedStr);
		 String statusStr = request.getParameter("status");
		 int status = Integer.parseInt(statusStr);
		 String fitScreenStr = request.getParameter("fitScreen");
		 int fitScreen = Integer.parseInt(fitScreenStr);
		 int between = Integer.parseInt(request.getParameter("between"));	
		 int client = Integer.parseInt(request.getParameter("client"));		 
		 String windowSkin = request.getParameter("windowSkin");
		 if(windowSkin ==null)
			 windowSkin= PropUtil.getString("default.windowSkin");
		 boolean resetWidths = false;
		 if(widths != null){
			 String[] widthArray = widths.split(",");
			 if(widthArray.length != columns)
				 resetWidths = true;				 
		 }else
			 resetWidths = true;	
		 int width = 1;
		 if(fitScreen == 0) width = 300;
		 if(resetWidths){
			 widths = "";
			 for(int i=0;i<columns;i++){
				 widths+=width+",";
			 }
			 widths = widths.substring(0,widths.length() - 1);
		 }
       PortalTab tab = getPortalTab(request);
       tab.setLabel(title);
       tab.setBetween(between);
       tab.setCloseable(closeable);
       tab.setDefaulted(defaulted);
       tab.setStatus(status);
       tab.setFitScreen(fitScreen);
       tab.setWidths(widths);
       tab.setWindowSkin(windowSkin);
       tab.setClient(client);
       this.getPortalService(request).save(tab);
       return "";
	}
	public Object addSubTab (HttpServletRequest request, HttpServletResponse response) throws Exception{	
		if(this.getUser(request) == null || this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()) return "";
		String tabId = request.getParameter("tabId");
		 long id = 0;
		 try{
			 id = Long.parseLong(tabId);
		 }catch(Exception e){}
		 if(id > 0){
			 PortalTab tab = this.getPortalService(request).getPortalTabById(id);
			 PortalTab sub = new PortalTab(tab);
			 this.getPortalService(request).save(sub);
//			 List<PortletObject> portlets = this.getPortalService(request).getPortletsByTab(tab.getId());
//			 for(PortletObject portlet : portlets){
//				 portlet.setTabId(sub.getId());
//				 this.getPortalService(request).save(portlet);
//			 }
		 }
		 return "";
	}
	public Object addTab (HttpServletRequest request, HttpServletResponse response) throws Exception{
		 if(this.getUser(request) == null || this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()) return "";
		 String title = request.getParameter("title");
		 if(title != null) title = URLDecoder.decode(title,_CHARSET_UTF);		 
		 int columns = Integer.parseInt(request.getParameter("columns"));
		 String widths= "";
		 for(int i=0;i<columns;i++){
			if(i < columns - 1)
				widths+=request.getParameter("width"+i)+",";
			else
				widths+=request.getParameter("width"+i);
		 }			 
		 String close = request.getParameter("closeable");
		 int closeable = Integer.parseInt(close);
		 int editable = 1;
		 int moveable = 1;
		 int allowAddContent = 1;
		 if(isAdmin(request) && this.getVisitedPortal(request) != null){
			 closeable = 0;
			 editable = 0;
			 moveable = 0;
			 allowAddContent = 0;
		 }
		 String defaultedStr = request.getParameter("defaulted");
		 int defaulted = Integer.parseInt(defaultedStr);
		 int between = Integer.parseInt(request.getParameter("between"));
		 String windowSkin = request.getParameter("windowSkin");
		 if(windowSkin ==null)
			 windowSkin= PropUtil.getString("default.windowSkin");
		 long parentId = Long.parseLong(request.getParameter("parentId"));
		 Portal portal = this.getPortal(request);
		 String userId = portal.getOwnerId();
		 if(this.getVisitedPortal(request) != null){
			 portal = this.getVisitedPortal(request);
			 userId = portal.getOwnerId();
		 }
		 PortalTab tab =new PortalTab(title,"",closeable,editable,moveable,allowAddContent,"",defaulted,between,widths,windowSkin,parentId,portal.getId(),userId,OrganizationThreadLocal.getOrganizationId());
		 if(this.getUser(request).getId() != OrganizationThreadLocal.getOrg().getUserId()){
			 this.getPortalService(request).save(tab);	 
		 }
		 List<PortalTab> tabList = null;
		 if(parentId > 0)
			 tabList = this.getPortalService(request).getPortalTabByParent(parentId);
		 else
			 tabList = this.getPortalService(request).getPortalTabByUser(this.getUser(request),OrganizationThreadLocal.getOrg());
		 int i = tabList.size() - 1;
		 List<PortalTab> tabs = new LinkedList<PortalTab>();
		 tabs.add(tab);
		 return JSONUtil.getPortalTabData(request,tabs, true, isAdmin(request), this.getUser(request), i);		
	}
	public Object deleteTab(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String tabId = request.getParameter("tabId");
		 if(tabId != null){
			 PortalTab  tab = this.getPortalService(request).getPortalTabById(Long.parseLong(tabId));
			 Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
			 User user = this.getUser(request);
			 if(tab != null && 
					 ((user.getUserId().equals(portal.getOwnerId()) && user.getId() != OrganizationThreadLocal.getOrg().getUserId())
					  || this.isGroupTabOwner(request,tab,user)
					  || this.isAdmin(request))
					 ){				 
				 this.getPortalService(request).deletePortalTab(tab);
			 }
		 }
		 return "";	
	}
		
	public Object addContent (HttpServletRequest request, HttpServletResponse response) throws Exception{	
		 String portletObjectRefName = request.getParameter("portletObjectRefName");
		 int column = Integer.parseInt(request.getParameter("column"));
		 PortletObjectRef pRef = this.getPortalService(request).getPortletRefByName(portletObjectRefName);
		 PortalTab tab = this.getPortalTab(request);
		 int row = this.getPortalService(request).getTabPortletsMaxRowByColoumn(tab.getId(),column - 1);
		 PortletObject p = new PortletObject(tab.getId(), column - 1, row, pRef, tab.getOwnerId().equals(this.getUser(request).getUserId()),false);		
		 if(this.getUser(request).getId() != OrganizationThreadLocal.getOrg().getUserId()){
			 this.getPortalService(request).save(p);
		 }
		 List<PortletObject> list = new LinkedList<PortletObject>();list.add(p);
		 return JSONUtil.getPortletData(list,tab.getId(),true,isAdmin(request),this.getUser(request),request);		 
	}
	public Object deletePortlet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String portletId = request.getParameter("portletId");
		 if(portletId != null){
			 PortletObject portletObject = this.getPortalService(request).getPortletById(Long.parseLong(portletId));	 
			 if(portletObject != null) this.getPortalService(request).delete(portletObject);
		 }
		 return "";	
	}
	
	public Object turnToMyAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		this.removeVisitedUser(request);
		return "";
	}
	
	public Object checkMyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String uri = request.getParameter("uri");
		 String id = request.getParameter("id");
		 if(!ValidationUtil.isValidUrl(uri)) id = "0";		 
		 if(!isValidInteralUri(request, uri)) id = "-1";	
		 JSONObject json = new JSONObject();
		 json.put("id",id);
		 return json;	
	}
	
	public Object saveMyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String uri = request.getParameter("uri").trim();
		 String id = request.getParameter("id");	
		 this.getUserService(request).saveUserUrl(this.getUser(request),uri);
		 JSONObject json = new JSONObject();
		 json.put("id",id);
		 return json;	
	}
		
	public Object validateUserId(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String email = request.getParameter("email");	
		 String id = request.getParameter("responseId");		 
		 User user = this.getUserService(request).getUserByUserId(email,OrganizationThreadLocal.getOrganizationId());
		 if(user == null) id = "1";
		 JSONObject json = new JSONObject();
		 json.put("id",id);
		 return json;	
	}
	
	public Object validateInternalUri(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String uri = request.getParameter("uri");			 
		 String id = request.getParameter("responseId");
		 if(ValidationUtil.isValidUrl(uri) && isValidInteralUri(request, uri)) id = "1";
		 JSONObject json = new JSONObject();
		 json.put("id",id);
		 return json;	
	}
			
	public Object changePosition(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String portletId = request.getParameter("portletId");
		String column = request.getParameter("column");
		String row = request.getParameter("row");			
		PortletObject portlet =this.getPortalService(request).getPortletById(Long.parseLong(portletId));		
		if(portlet != null){
			portlet.setColumn(Integer.parseInt(column));
			portlet.setRow(Integer.parseInt(row));			
			this.getPortalService(request).save(portlet);			
		}
		return "";	
	}
	
	public Object uploadAllOpml(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		boolean upload = true;
		try{   
			StringBuilder buffer = new  StringBuilder();
			ServletInputStream in = request.getInputStream();			
			int i;
			boolean added = false;
			while ((i = in.read()) != -1){
					buffer.append((char) i);
			}
			String text = buffer.toString();
			int index = text.indexOf("<?xml");
			int endIndex = text.indexOf("</opml>");
			if(endIndex <= 0) return false;
			String result = text.substring(index,endIndex + 7);

			Document document = DocumentHelper.parseText(result);
			List list1 = document.selectNodes( "//opml/body/outline" );
			for (Iterator iter = list1.iterator(); iter.hasNext(); ) {
				Node node = (Node) iter.next();
				String title = node.valueOf("@title");
	            String feed = node.valueOf("@xmlUrl");
	            String name = node.valueOf("@name");
	            String userId = node.valueOf("@userId");
	            String tag = node.valueOf("@tag");
	            String subTag = node.valueOf("@subTag");
	            String language = node.valueOf("@language");
				if(feed != null && feed.trim().length() > 0)
					addFeedFromOpml(request,title,feed,name,userId,tag,subTag,language);
			}
			List list = document.selectNodes( "//opml/body/outline/outline" );
	        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
				Node node = (Node) iter.next();
				String title = node.valueOf("@title");
	            String feed = node.valueOf("@xmlUrl");
	            String name = node.valueOf("@name");
	            String userId = node.valueOf("@userId");
	            String tag = node.valueOf("@tag");
	            String subTag = node.valueOf("@subTag");
	            String language = node.valueOf("@language");
				if(feed != null && feed.trim().length() > 0)
					addFeedFromOpml(request,title,feed,name,userId,tag,subTag,language);
	        }
		}catch (Exception e){
			e.printStackTrace();
			upload = false;
		}
		return upload;
		
	}
	private void addFeedFromOpml(HttpServletRequest request, String title, String feed,String name, String userId, String tag, String subTag, String language){
		if(feed != null && !"".equals(feed)){
			List<PortletObjectRef> refs = this.getPortalService(request).getFeedsByUrl(feed);
			if(refs != null && refs.size() > 0){
				for(PortletObjectRef ref : refs){
					if(ref.getTag() != null && ref.getTag().equals(tag)) return;
				}
			}
			String proxySet = request.getSession().getServletContext().getInitParameter("proxySet");
			if("true".equals(proxySet)){
				String proxyHost = request.getSession().getServletContext().getInitParameter("proxyHost");
				String proxyPort = request.getSession().getServletContext().getInitParameter("proxyPort");
				System.getProperties().put( "proxySet", proxySet );
				System.getProperties().put( "proxyHost", proxyHost );
				System.getProperties().put( "proxyPort", proxyPort );
			}
			try{
				Portal portal = this.getPortal(request);
				URL feedUrl = new URL(feed);
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed rss = input.build(new XmlReader(feedUrl));
			    String imageUrl="";
				String feedTitle = rss.getTitle();
				if(title != null && title.trim().length() > 0) feedTitle = title;
			    if(rss.getImage() != null && rss.getImage().getUrl() != null)
			    	imageUrl = rss.getImage().getUrl().toString();
			    String feedName = StringUtil.isEmpty(name) ? String.valueOf(System.currentTimeMillis()) : name;
			    PortletObjectRef ref = new PortletObjectRef(
				    							feedName
				    						   ,"/rssPortlet.lp"
				    						   ,feedTitle
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,subTag
				    						   ,tag
				    						   ,language
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,userId);
			    this.getPortalService(request).save(ref);
			
				List<PortletObjectRef> myFeedLists =(List<PortletObjectRef>)request.getSession().getAttribute("myFeedLists");
				if(myFeedLists != null){
					myFeedLists.add(ref);
					request.getSession().setAttribute("myFeedLists",myFeedLists);
				}
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("addFeedError",e.getMessage());
			}
		}
	}
	public Object uploadOpml(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		boolean upload = true;
		try{   
			StringBuilder buffer = new  StringBuilder();
			ServletInputStream in = request.getInputStream();			
			int i;
			boolean added = false;
			while ((i = in.read()) != -1){
					buffer.append((char) i);
			}
			String text = buffer.toString();
			int index = text.indexOf("<?xml");
			int endIndex = text.indexOf("</opml>");
			if(endIndex <= 0) return false;
			String result = text.substring(index,endIndex + 7);

			Document document = DocumentHelper.parseText(result);
			List list1 = document.selectNodes( "//opml/body/outline" );
			for (Iterator iter = list1.iterator(); iter.hasNext(); ) {
				Node node = (Node) iter.next();
				String title = node.valueOf("@title");
	            String feed = node.valueOf("@xmlUrl");
				if(feed != null && feed.trim().length() > 0)
				   addMyFeedFromOpml(request,title,feed);
			}
			List list = document.selectNodes( "//opml/body/outline/outline" );
	        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
				Node node = (Node) iter.next();
				String title = node.valueOf("@title");
	            String feed = node.valueOf("@xmlUrl");
				if(feed != null && feed.trim().length() > 0)
					addMyFeedFromOpml(request,title,feed);
	        }
		}catch (Exception e){
			e.printStackTrace();
			upload = false;
		}
		return upload;
		
	}	
	private void addMyFeedFromOpml(HttpServletRequest request, String title, String feed){
		if(feed != null && !"".equals(feed)){
			String proxySet = request.getSession().getServletContext().getInitParameter("proxySet");
			if("true".equals(proxySet)){
				String proxyHost = request.getSession().getServletContext().getInitParameter("proxyHost");
				String proxyPort = request.getSession().getServletContext().getInitParameter("proxyPort");
				System.getProperties().put( "proxySet", proxySet );
				System.getProperties().put( "proxyHost", proxyHost );
				System.getProperties().put( "proxyPort", proxyPort );
			}
			try{
				Portal portal = this.getPortal(request);
				URL feedUrl = new URL(feed);
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed rss = input.build(new XmlReader(feedUrl));
			    String imageUrl="";
				String feedTitle = rss.getTitle();
				if(title != null && title.trim().length() > 0) feedTitle = title;
			    if(rss.getImage() != null && rss.getImage().getUrl() != null)
			    	imageUrl = rss.getImage().getUrl().toString();
			    String feedName = String.valueOf(System.currentTimeMillis());
			    PortletObjectRef ref = new PortletObjectRef(
				    							feedName
				    						   ,"/rssPortlet.lp"
				    						   ,feedTitle
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,null
				    						   ,_MY_FEED_TITLE
				    						   ,_LANGUAGE_ALL
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,this.getUser(request).getUserId());
			    this.getPortalService(request).save(ref);
			
				List<PortletObjectRef> myFeedLists =(List<PortletObjectRef>)request.getSession().getAttribute("myFeedLists");
				if(myFeedLists != null){
					myFeedLists.add(ref);
					request.getSession().setAttribute("myFeedLists",myFeedLists);
				}
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("addFeedError",e.getMessage());
			}
		}
	}
		
	public Object uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String photoUrl = "";
		try{   
			DataInputStream in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength();
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;
			while (totalBytesRead < formDataLength) {
				byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += byteRead;
			}
			
			String contentType = request.getContentType();
			User user = getUser(request);
			String dir = FileUtil.getPath(_FILE_PATH,user.getId(),_USER_PATH,_MY_IMAGE_PATH);
			String fileSaveName = FileUtil.saveFile(dataBytes,contentType,dir);
			File from_file=new File(dir+fileSaveName);
			java.awt.image.BufferedImage image=javax.imageio.ImageIO.read(from_file);
			int width = image.getWidth();
			int height= image.getHeight();			
			try{
				Object value = ImageUtil.reSize(image,width,height,dir+fileSaveName);
				int[] array = (int[])value;
				width = array[0];
				height = array[1];
			}catch(Exception e){}
			ImageUtil.saveThumb(image,dir+fileSaveName);
			
			photoUrl = "/user/"+user.getId()+"/"+_MY_IMAGE_PATH+"/"+fileSaveName;
			user.setPhotoUrl(photoUrl);
			user.setCaption(null);
			user.setPhotoWidth(width);
			user.setPhotoHeight(height);
			this.getUserService(request).save(user);
			this.setUser(request,user);
			UserPicture picture = new UserPicture(user.getId(),user.getOrgId(),user.getOrgId(),photoUrl,_PRIVACY_HIDDEN,width,height);
			if(_REPLICATION_ENABLED_FILE){
				picture.setFile(dataBytes);
				picture.setDir(dir);
				picture.setContentType(contentType);				
			}
			this.getPortalService(request).save(picture);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "<p>"+photoUrl+"</p>";
		
	}
	public Object cropProfilePhoto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("responseId");
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int w = Integer.parseInt(request.getParameter("w"));
		int h = Integer.parseInt(request.getParameter("h"));
		String pictureUrl = this.getUser(request).getPhotoUrl();
	
		String fileName = FileUtil.getPathPrefix(OrganizationThreadLocal.getOrganizationId())+pictureUrl;
		File file = new File(fileName);
		BufferedImage image=javax.imageio.ImageIO.read(file);
		BufferedImage snap = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = snap.createGraphics();
		g2d.drawImage(image, -x, -y, null);
		g2d.dispose();
		ImageIO.write(snap, "png", file);
		java.awt.image.BufferedImage cropimage=javax.imageio.ImageIO.read(file);
		ImageUtil.saveThumb(cropimage,fileName);
		User user = getUser(request);
		user.setPhotoWidth(w);
		user.setPhotoHeight(h);
		this.getUserService(request).save(user);
		this.setUser(request,user);
		JSONObject json = new JSONObject();
		json.put("id",id);
		return json;
	}
	public Object uploadMusics(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String upload = "SUCCESS\n";
		int formDataLength = request.getContentLength();
		if(formDataLength > 0){
			User user = getUser(request);
			if(user == null) return String.format("ERROR: %s\n","Please login first.");
			try{   
				doUploadMusic(request,response);
			}catch (Exception e){
				e.printStackTrace();
				upload = String.format("ERROR: %s\n",e.getMessage());
			}
		}
		return upload;
		
	}
	public Object uploadMusic(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		boolean upload = true;
		int formDataLength = request.getContentLength();
		if(formDataLength > 0){
			try{   
				doUploadMusic(request,response);
			}catch (Exception e){
				e.printStackTrace();
				upload = false;
			}
		}
		return upload;
		
	}
	private void doUploadMusic(HttpServletRequest request, HttpServletResponse response) throws Exception{				 
		DataInputStream in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength();
		byte dataBytes[] = new byte[formDataLength];
		int byteRead = 0;
		int totalBytesRead = 0;
		while (totalBytesRead < formDataLength) {
			byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
			totalBytesRead += byteRead;
		}
		
		String contentType = request.getContentType();
		User user = getUser(request);
		String dir = FileUtil.getPath(_FILE_PATH,user.getId(),_USER_PATH,_MY_MUSIC_PATH);
		String fileSaveName = FileUtil.saveFile(dataBytes,contentType,dir);
		String musicUrl = "/"+_USER_PATH+"/"+user.getId()+"/"+_MY_MUSIC_PATH+"/"+fileSaveName;
		int status = 0;			
		if(user != null) status = user.getDefaultMusicStatus();
		UserMusic music = new UserMusic(user.getId(),musicUrl,fileSaveName,status);		
		if(_REPLICATION_ENABLED_FILE){
			music.setFile(dataBytes);
			music.setDir(dir);
			music.setContentType(contentType);
		}
		this.getPortalService(request).save(music);			
	}
	public Object uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		boolean upload = true;
		try{   
			DataInputStream in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength();
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;
			while (totalBytesRead < formDataLength) {
				byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += byteRead;
			}
			String contentType = request.getContentType();
			User user = getUser(request);
			String dir = FileUtil.getPath(_FILE_PATH,user.getId(),_USER_PATH,_MY_FILE_PATH);
			String fileSaveName = FileUtil.saveFile(dataBytes,contentType,dir);
			String fileUrl = "/"+_USER_PATH+"/"+user.getId()+"/"+_MY_FILE_PATH+"/"+fileSaveName;
			int status = 0;			
			if(user != null) status = user.getDefaultFileStatus();
			UserFile ufile = new UserFile(user.getId(),fileUrl,fileSaveName,status);
			if(_REPLICATION_ENABLED_FILE){
				ufile.setFile(dataBytes);
				ufile.setDir(dir);
				ufile.setContentType(contentType);
			}
			this.getPortalService(request).save(ufile);
		}catch (Exception e){
			e.printStackTrace();
			upload = false;
		}
		return upload;
		
	}
	public Object uploadPictures(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		String upload = "SUCCESS\n";
		int formDataLength = request.getContentLength();
		if(formDataLength > 0){
			User user = getUser(request);
			if(user == null) return String.format("ERROR: %s\n","Please login first.");
			try{   
				doUploadPicture(request,response);
			}catch (Exception e){			
				e.printStackTrace();
				upload = String.format("ERROR: %s\n",e.getMessage());
			}
		}
		return upload;
	}	
	public Object uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		boolean upload = true;
		int formDataLength = request.getContentLength();
		if(formDataLength > 0){
			try{   
				doUploadPicture(request,response);		
			}catch (Exception e){
				logger.error(e.getMessage());
				upload = false;
			}
		}
		return upload;
	}
	private void doUploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception{				
		DataInputStream in = new DataInputStream(request.getInputStream());
		int formDataLength = request.getContentLength();
		if(formDataLength < 0) return;
		byte dataBytes[] = new byte[formDataLength];
		int byteRead = 0;
		int totalBytesRead = 0;
		while (totalBytesRead < formDataLength) {
			byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
			totalBytesRead += byteRead;
		}
		String contentType = request.getContentType();
		User user = getUser(request);
		String dir = FileUtil.getPath(_FILE_PATH,user.getId(),_USER_PATH,_MY_IMAGE_PATH);
		String fileSaveName = FileUtil.saveFile(dataBytes,contentType,dir);
		int width = 0;
		int height= 0;	
		File from_file=new File(dir+fileSaveName);
		BufferedImage image=ImageIO.read(from_file);
		width = image.getWidth();
		height= image.getHeight();
		int maxWidth = ImageUtil.getMaxWidth(width,height);
		int maxHeight = ImageUtil.getMaxHeight(width,height);
		Date takenDate = null;		
		try{				
			ImageUtil.reSize(image,width,height,dir+fileSaveName);			
			Metadata metadata = JpegMetadataReader.readMetadata(from_file);
			Iterator directories = metadata.getDirectoryIterator(); 
			while (directories.hasNext()) { 
				try{
					Directory directory = (Directory)directories.next(); 
					//36867 corresponds to the Date Taken
					takenDate = directory.getDate(36867);
				    if(takenDate != null) break;
				}catch(Exception e){
					//ignore reading image meta data exception
				}
			}
		}catch(Exception e){
			//ignore reading image meta data exception
		}		
		String photoUrl = "/"+_USER_PATH+"/"+user.getId()+"/"+_MY_IMAGE_PATH+"/"+fileSaveName;
		int status = 0;			
		if(user != null) status = user.getDefaultPictureStatus();
		long orgId = user.getOrgId();
		if(this.getVisitedGroup(request) != null){
			orgId = this.getVisitedGroup(request).getId();
			request.getSession().setAttribute("refreshGroupPictures",true);
		}
		UserPicture picture = new UserPicture(user.getId(),orgId,user.getOrgId(),photoUrl,status,maxWidth,maxHeight,takenDate);
		if(_REPLICATION_ENABLED_FILE){
			picture.setFile(dataBytes);
			picture.setDir(dir);
			picture.setContentType(contentType);
		}
		this.getPortalService(request).save(picture);		
		List<UserPicture> userPictures = (List<UserPicture>)request.getSession().getAttribute("myPictures");
	    if(userPictures != null){
		    request.getSession().removeAttribute("myPictures");
	    }			
	    if(this.getVisitedGroup(request) != null){
	    	List<UserPicture> groupPictures = (List<UserPicture>)request.getSession().getAttribute("groupPictures");
		    if(groupPictures != null){		    	
			    request.getSession().removeAttribute("groupPictures");
		    }
	    }
	}
	
	private static Logger logger = LoggerFactory.getLogger(PortalCommands.class);
}