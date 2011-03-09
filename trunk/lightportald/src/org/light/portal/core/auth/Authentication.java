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

package org.light.portal.core.auth;

import static org.light.portal.util.Constants._LIGHT_PORTAL;
import static org.light.portal.util.Constants._LIGHT_PORTAL_USER_ID;
import static org.light.portal.util.Constants._USER;
import static org.light.portal.util.Constants._VISITED_GROUP;
import static org.light.portal.util.Constants._VISITED_STORE;
import static org.light.portal.util.Constants._VISITED_USER;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.light.portal.Context;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.PortletService;
import org.light.portal.core.task.AfterTaskService;
import org.light.portal.model.Portal;
import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portlets.chat.service.ChatService;

/**
 * 
 * @author Jianmin Liu
 **/
public class Authentication {

	/**
	 * @param request
	 * @param identifier
	 */
	protected void setupUser(HttpServletRequest request, String identifier, String name){		
		if(identifier.indexOf("http") > 0) identifier = identifier.substring(identifier.indexOf("http"));
		if(identifier.indexOf("#") > 0) identifier = identifier.substring(0,identifier.indexOf("#"));
		
		Enumeration attributes=request.getSession().getAttributeNames();
		while (attributes.hasMoreElements()){
             String attribute = (String)attributes.nextElement();
             if(!_VISITED_USER.equals(attribute)         	  
           	  && !_VISITED_GROUP.equals(attribute) 
           	  && !_VISITED_STORE.equals(attribute))
           	  request.getSession().removeAttribute(attribute); 
		 }
		 User user = this.getUserService(request).getUserByUserId(identifier,OrganizationThreadLocal.getOrganizationId());
		 if(user == null){
			 User newUser = new User(identifier,PropUtil.getString("portal.openid.user.default.password"),name,null,OrganizationThreadLocal.getOrganizationId());
			 user = this.getUserService(request).signUp(newUser,OrganizationThreadLocal.getOrganizationId());
			 if(user != null){			 			 				 
				 Portal portal = this.getPortalService(request).createPortalByUser(user);
				 portal.setTitle(PropUtil.getString("portal.openid.user.default.title"));
				 this.getPortalService(request).save(portal);
				 this.setPortal(request,portal);				 
			 }
		 }
		 this.setUser(request ,user);			 
		 this.setLocale(request, user.getLanguage());
		
		 //start after login task thread
		 this.getAfterTaskService(request).afterLogin(user);
	}
	
	protected void setUser(HttpServletRequest request,User user){
		Map<String,String> userData = new HashMap<String,String>();
		userData.put(_LIGHT_PORTAL_USER_ID,user.getUserId());
		request.getSession().setAttribute(PortletRequest.USER_INFO,userData);
		request.getSession().setAttribute(_USER,user);
	}
	
	protected void setPortal(HttpServletRequest request,Portal portal){
		request.getSession().setAttribute(_LIGHT_PORTAL,portal);
	}
	
	protected boolean setLocale(HttpServletRequest request,String locale){
		return Context.getInstance().setLocale(request,locale);
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
	
	protected ChatService getChatService(HttpServletRequest request) {			
		return Context.getInstance().getChatService(request);
	}
	
	protected AfterTaskService getAfterTaskService(HttpServletRequest request) {			
		return Context.getInstance().getAfterTaskService(request);
	}
}
