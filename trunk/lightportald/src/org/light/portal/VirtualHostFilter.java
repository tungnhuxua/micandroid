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

import static org.light.portal.util.Constants._DEFAULT_GROUP_PORTRAIT;
import static org.light.portal.util.Constants._DEFAULT_USER_FEMALE_PORTRAIT;
import static org.light.portal.util.Constants._DEFAULT_USER_MALE_PORTRAIT;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_HEIGHT;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_WIDTH;
import static org.light.portal.util.Constants._DEFAULT_USER_URL_PREFIX;
import static org.light.portal.util.Constants._ORGANIZATION;
import static org.light.portal.util.Constants._PORTAL_URL_FORMAT;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portal.util.DomainUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author Jianmin Liu
 **/
public class VirtualHostFilter implements Filter {
	   
	   public void init(FilterConfig config) throws ServletException {

	   }

	   public void destroy() {
		   
	   }

	   public void doFilter(ServletRequest request, 
	      ServletResponse response, FilterChain chain) 
	      throws IOException, ServletException {
		   if (!(request instanceof HttpServletRequest)) {
	         chain.doFilter(request, response);
	         return;
	       }		   
	       HttpServletRequest httpRequest = (HttpServletRequest) request;	    
	       Organization org = (Organization)httpRequest.getSession().getAttribute(_ORGANIZATION);
	       String fullDomain = DomainUtil.getFullDomain(httpRequest);
	       if(org == null || (org != null && !fullDomain.equalsIgnoreCase(org.getVirtualHost()) && !fullDomain.equalsIgnoreCase(org.getWebId()))){
	    	   org = getOrganization(httpRequest);	
	    	   if(org != null){
	    		   org.setDefaultGroupPortrait(PropUtil.getString(_DEFAULT_GROUP_PORTRAIT,org.getWebId()));
	    		   org.setDefaultMalePortrait(PropUtil.getString(_DEFAULT_USER_MALE_PORTRAIT,org.getWebId()));
	    		   org.setDefaultFemalePortrait(PropUtil.getString(_DEFAULT_USER_FEMALE_PORTRAIT,org.getWebId()));
	    		   org.setThumbWidth(PropUtil.getInt(_DEFAULT_USER_PORTRAIT_WIDTH,org.getWebId()));
	    		   org.setThumbHeight(PropUtil.getInt(_DEFAULT_USER_PORTRAIT_HEIGHT,org.getWebId()));
	    		   String space = httpRequest.getHeader("Host");
	    		   space += httpRequest.getContextPath();
	    		   space += PropUtil.getString(_DEFAULT_USER_URL_PREFIX);
	    		   org.setUserSpacePrefix(space);
	    		   space = httpRequest.getHeader("Host");
	    		   space += httpRequest.getContextPath();
	    		   space += PropUtil.getString("portal.group.url.prefix");
	    		   org.setGroupSpacePrefix(space);
	    		   httpRequest.getSession().setAttribute(_ORGANIZATION,org);
	    		   org.setHost(DomainUtil.getFullHost(httpRequest));
	    		   if(this.getUser(httpRequest) == null){
	    			   this.setUser(httpRequest, org.getUser());
	    		   }
	    		   checkHost(httpRequest,org);
			   }
	       }
	       OrganizationThreadLocal.setOrganization(org);
	       
	       chain.doFilter(request, response);
	       return;
	   }
	   
	   private Organization getOrganization(HttpServletRequest request){
		   String fullDomain = DomainUtil.getFullDomain(request);
		   Organization org = this.getUserService(request).getOrgByVirtualHost(fullDomain);
		   if(org == null){
			   String host = DomainUtil.getHost(request);
			   org = this.getUserService(request).getOrgByVirtualHost(host);
		   }
		   if(org == null){
			   org = this.getUserService(request).getOrganizations().get(0);
			   org = this.getUserService(request).getOrgById(org.getId());
		   }
		   return org;
	   }
	   private void checkHost(HttpServletRequest request,Organization org){
		   String fullDomain = DomainUtil.getFullDomain(request);
		   if(!fullDomain.equalsIgnoreCase(org.getVirtualHost()) && !fullDomain.equalsIgnoreCase(org.getWebId())){
			   String host = request.getHeader("Host");
			   if (host != null) {
					String domain = DomainUtil.getDomain(host.toLowerCase());
					if(!domain.startsWith("www.")){	
						String domainName = DomainUtil.getDomainName(domain);
						if(!ValidationUtil.isIPAddress(domainName) && domainName.indexOf(".") > 0){
							String uri = domainName.substring(0,domainName.indexOf("."));
							doViewGroup(request, uri);
							doViewMember(request, uri);
						}else{
							if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1){
								Context.getInstance().setVisitedPortal(request,null);
								Context.getInstance().setVisitedGroup(request,null);	
								Context.getInstance().setVisitedUser(request,null);	
							}
						}
					}else{
						if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1){
							Context.getInstance().setVisitedPortal(request,null);
							Context.getInstance().setVisitedGroup(request,null);	
							Context.getInstance().setVisitedUser(request,null);	
						}
					}
				}
		   }
		}		   
	  			
		private boolean doViewGroup(HttpServletRequest request, String uri){		
			if(this.getVisitedGroup(request) == null || !this.getVisitedGroup(request).getUri().equals(uri)){
				Group group = this.getGroupService(request).getGroupByUri(uri,OrganizationThreadLocal.getOrganizationId());
				if(group != null){
					registerParameter(request);
					Context.getInstance().setVisitedGroup(request ,group);					
					String locale = Locale.ENGLISH.toString();
					if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
					Context.getInstance().setLocale(request, locale);
					return true;
				}else{
					if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
						Context.getInstance().setVisitedGroup(request,null);	
					return false;
				}
			}else{
				if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
					Context.getInstance().setVisitedGroup(request,null);	
				return false;
			}
				
		}
				
		private boolean doViewMember(HttpServletRequest request, String uri){		
			if(this.getVisitedUser(request) == null || !this.getVisitedUser(request).getUri().equals(uri)){
				User user = this.getUserService(request).getUserByUri(uri,OrganizationThreadLocal.getOrganizationId());
				if(user != null){
					registerParameter(request);
					Context.getInstance().setVisitedUser(request ,user);
					Portal portal = Context.getInstance().getPortalService(request).getPortalByUser(user.getUserId(),user.getOrgId());
					Context.getInstance().setVisitedPortal(request,portal);
					String locale = Locale.ENGLISH.toString();
					if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
					Context.getInstance().setLocale(request, locale);
					return true;
				}else{
					if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1){
						Context.getInstance().setVisitedPortal(request,null);
						Context.getInstance().setVisitedUser(request,null);
					}							
					return false;
				}
			}else{
				if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1){
					Context.getInstance().setVisitedPortal(request,null);
					Context.getInstance().setVisitedUser(request,null);
				}
				return false;
			}
				
		}
		
		protected void registerParameter(HttpServletRequest request){
	    	Enumeration parameters = request.getParameterNames();
	    	while(parameters.hasMoreElements()){
	    		String name = (String)parameters.nextElement();
	    		request.getSession().setAttribute(name,request.getParameter(name));
	    	}
	    }
		
	   	protected User getUser(HttpServletRequest request){
			return Context.getInstance().getUser(request);
		}	
	   	protected void setUser(HttpServletRequest request, User user){
	   		Context.getInstance().setUser(request,user);
		}	
	    protected User getVisitedUser(HttpServletRequest request){		
	    	return Context.getInstance().getVisitedUser(request);
		}	
		protected Group getVisitedGroup(HttpServletRequest request){		
			return Context.getInstance().getVisitedGroup(request);
		}
	    protected UserService getUserService(HttpServletRequest request) {			
			return (UserService)getService(request, "userService");
		}
	    
	    protected GroupService getGroupService(HttpServletRequest request) {			
			return (GroupService)getService(request, "groupService");
		}
				
		private Object getService(HttpServletRequest request, String serviceName) {
			ApplicationContext ctx = (ApplicationContext) request.getSession().getServletContext()		
	        							.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);		
			return ctx.getBean(serviceName);
		}			
}