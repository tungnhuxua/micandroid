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

import static org.light.portal.util.Constants._PAGE_ERROR_NOTFOUND;
import static org.light.portal.util.Constants._PAGE_ERROR_PERMISSION;
import static org.light.portal.util.Constants._PAGE_INDEX;
import static org.light.portal.util.Constants._PAGE_URL_PREFIX;
import static org.light.portal.util.Constants._REQUEST_SUFFIX;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import static org.light.portal.util.Constants._PRIVACY_PROFILE;
import static org.light.portal.util.Constants._PRIVACY_MEMBER;
import static org.light.portal.util.Constants._PRIVACY_CONNECTION;
import static org.light.portal.util.Constants._PRIVACY_ONLYME;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.User;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.connection.Connection;

/**
 * 
 * @author Jianmin Liu
 **/
public class PageController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		if(uri.indexOf(_PAGE_URL_PREFIX) >= 0 && (uri.indexOf(_REQUEST_SUFFIX) < 0)){
			String path = uri.substring(uri.indexOf(_PAGE_URL_PREFIX)+_PAGE_URL_PREFIX.length());
			if(path.indexOf("/") < 0)
				doPage(request, response, path);			
		}
		
		chain.execute(request,response);
	}
	
	private void doPage(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
		long orgId = OrganizationThreadLocal.getOrganizationId();
		if(request.getParameter("orgId") != null){
			try{
				orgId = Long.parseLong(request.getParameter("orgId"));
				request.getSession().setAttribute("orgId",orgId);
			}catch(Exception e){}
		}
		PortalTab tab = null;		
		long id = 0;
		try{
			id = Long.parseLong(uri);
		}catch(Exception e){}			
		if(id > 0){
			tab = this.getPortalService(request).getPortalTabById(id);
		}else{
			tab = this.getPortalService(request).getPortalTabByUrl(uri, orgId);
		}		
		if(tab != null){	
			registerParameter(request);
			Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
			User user = this.getUserService(request).getUserByUserId(portal.getOwnerId(),orgId);
			Connection connection = null;
			if(user != null) connection = this.getConnectionService(request).getChatBuddy(user.getId(),this.getUser(request).getId());
			if(isAdmin(request,this.getUser(request)) 
						|| (tab.getStatus() == _PRIVACY_PUBLIC) 
						|| (tab.getStatus() == _PRIVACY_MEMBER && this.isAuthorized(this.getUser(request)))
				        || (tab.getStatus() == _PRIVACY_CONNECTION && this.getUser(request) != null &&  connection != null)
				        || (tab.getStatus() == _PRIVACY_PROFILE && user.getProfileFriendViewOnly() == 0) 
				        || (tab.getStatus() == _PRIVACY_PROFILE && user.getProfileFriendViewOnly() == 1 && connection != null) 
				        || (tab.getStatus() == _PRIVACY_ONLYME && this.getUser(request) != null && portal.getOwnerId().equals(this.getUser(request).getUserId()))){
				this.setVisitedPortal(request,portal);
				this.setVisitedPage(request,tab);
				String locale = Locale.ENGLISH.toString();
				if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
				this.setLocale(request, locale);
				request.getSession().getServletContext().getRequestDispatcher(_PAGE_INDEX).forward(request,response);
			}else{
				response.sendRedirect(request.getContextPath()+_PAGE_ERROR_PERMISSION);
			}
		}else{
			response.sendRedirect(request.getContextPath()+_PAGE_ERROR_NOTFOUND);
		}
	}
}
