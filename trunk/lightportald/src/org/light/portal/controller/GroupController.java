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

import static org.light.portal.util.Constants._GROUP_INDEX;
import static org.light.portal.util.Constants._GROUP_MOBILE_INDEX;
import static org.light.portal.util.Constants._GROUP_URL_PREFIX;
import static org.light.portal.util.Constants._PORTAL_INDEX;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_VERSION;
import static org.light.portal.util.Constants._REQUEST_SUFFIX;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.group.Group;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		int index = uri.lastIndexOf("/");		
		String path = uri.substring(index+1);
		
		if(uri.indexOf(_GROUP_URL_PREFIX) >= 0 && (uri.indexOf(_REQUEST_SUFFIX) < 0)){
			String sub = uri.substring(uri.indexOf(_GROUP_URL_PREFIX)+_GROUP_URL_PREFIX.length());
			if(sub.indexOf("/") < 0)
				doViewGroup(request, response, path);
		}
		
		chain.execute(request,response);
	}
	
	private void doViewGroup(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {		
		Group group = this.getGroupService(request).getGroupByUri(uri,OrganizationThreadLocal.getOrganizationId());
		if(group != null && (group.getHiddenGroup() == 0 || (group.getHiddenGroup() == 1 && this.getGroupService(request).getUserGroup(this.getUser(request).getId(),group.getId()) != null))){
			registerParameter(request);
			this.setVisitedGroup(request ,group);
			Organization org = this.getUserService(request).getOrgById(group.getId());
			Portal portal = getPortalService(request).getPortalByGroup(org.getUser().getUserId(),org.getId());
			this.setPortal(request,portal);
			String locale = Locale.ENGLISH.toString();
			if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
			this.setLocale(request, locale);			
			String browserInfo = request.getHeader("User-Agent");
			if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && ValidationUtil.isSmallMobile(browserInfo)){
				request.getSession().getServletContext().getRequestDispatcher(_GROUP_MOBILE_INDEX).forward(request,response);
			}else{
				request.getSession().getServletContext().getRequestDispatcher(_GROUP_INDEX).forward(request,response);
			}
		}else{
			response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
		}
	}

}
