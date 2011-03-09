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

package org.light.portal.tags;

import static org.light.portal.util.Constants._GROUP_MOBILE_INDEX;
import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._GROUP_SUBDOMAIN_INDEX;
import static org.light.portal.util.Constants._MEMBER_SUBDOMAIN_INDEX;
import static org.light.portal.util.Constants._NETWORK_ERROR_NOTFOUND;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_VERSION;
import static org.light.portal.util.Constants._SPACE_MOBILE_INDEX;


import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Portal;
import org.light.portal.model.User;
import org.light.portal.util.DomainUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.group.Group;


/**
 * 
 * @author Jianmin Liu
 **/
public class SubdomainTag extends BaseTag {

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		String fullDomain = DomainUtil.getFullDomain(request);
		if(!fullDomain.equalsIgnoreCase(OrganizationThreadLocal.getOrg().getWebId())
				&& !fullDomain.equalsIgnoreCase(OrganizationThreadLocal.getOrg().getVirtualHost())
				&& DomainUtil.isSubDomain(request)){
			String host = request.getHeader("Host");		
			if (host != null) {
				String domain = DomainUtil.getDomain(host.toLowerCase());						
				if(!domain.startsWith("www.")){	
					String domainName = DomainUtil.getDomainName(domain);				
					if(!ValidationUtil.isIPAddress(domainName) && domainName.lastIndexOf(".") > 0){
						String path = domainName.substring(0,domainName.lastIndexOf("."));					
						if(doViewGroup(request, response, path))
							return SKIP_BODY;					
						else if(doViewMember(request, response, path))
							return SKIP_BODY;
						else{
							host += request.getContextPath();
							host = host.substring(domainName.lastIndexOf(".")+1);
							try{
								request.getSession().getServletContext().getRequestDispatcher(_NETWORK_ERROR_NOTFOUND).forward(request,response);
								return SKIP_BODY;
							}catch(Exception e){}
						}						
					}
				}
			}
		}		
		return EVAL_BODY_INCLUDE;
	}	
		
	private boolean doViewGroup(HttpServletRequest request, HttpServletResponse response, String uri){		
		Group group = this.getGroupService(request).getGroupByUri(uri,OrganizationThreadLocal.getOrganizationId());
		if(group != null){
			registerParameter(request);
			this.setVisitedGroup(request ,group);
			Portal portal = getPortalService(request).getPortalByGroup(_GROUP_PREFIX+group.getId(),group.getId());
			this.setPortal(request,portal);
			String locale = Locale.ENGLISH.toString();
			if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
			this.setLocale(request, locale);
			try{
				String browserInfo = request.getHeader("User-Agent");
				if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && ValidationUtil.isSmallMobile(browserInfo)){
					request.getSession().getServletContext().getRequestDispatcher(_GROUP_MOBILE_INDEX).forward(request,response);
				}else{
					request.getSession().getServletContext().getRequestDispatcher(_GROUP_SUBDOMAIN_INDEX).forward(request,response);
				}
				return true;
			}catch(Exception e){
				Throwable ex = e;
				while(ex != null){
					logger.error(String.format("%s: %s",ex.getClass().toString(),ex.getMessage()));
					ex = ex.getCause();
				}
				return false;
			}
		}else{
			return false;
		}
	}
		
	private boolean doViewMember(HttpServletRequest request, HttpServletResponse response, String uri){		
		User user = this.getUserService(request).getUserByUri(uri,OrganizationThreadLocal.getOrganizationId());
		if(user != null){
			registerParameter(request);
			this.setVisitedUser(request ,user);
			Portal portal = getPortalService(request).getPortalByUser(user.getUserId(),OrganizationThreadLocal.getOrganizationId());
			this.setVisitedPortal(request,portal);
			String locale = Locale.ENGLISH.toString();
			if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
			this.setLocale(request, locale);
			try{
				String browserInfo = request.getHeader("User-Agent");
				if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && ValidationUtil.isSmallMobile(browserInfo)){
					request.getSession().getServletContext().getRequestDispatcher(_SPACE_MOBILE_INDEX).forward(request,response);
				}else{
					request.getSession().getServletContext().getRequestDispatcher(_MEMBER_SUBDOMAIN_INDEX).forward(request,response);
				}
				return true;
			}catch(Exception e){
				Throwable ex = e;
				while(ex != null){
					logger.error(String.format("%s: %s",ex.getClass().toString(),ex.getMessage()));
					ex = ex.getCause();
				}
				return false;
			}
		}else{
			return false;
		}
	}
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
		
	private static Logger logger = LoggerFactory.getLogger(SubdomainTag.class); 
}
