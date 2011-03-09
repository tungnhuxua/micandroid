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

package org.light.portal.core.portlets;

import static org.light.portal.util.Constants._CURRENT_LOCALE;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;
import static org.light.portal.util.Constants._ROLE_STORE;

import java.util.LinkedList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.PortalTab;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class OrganizationPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		 String action = request.getParameter("action");
		 if("add".equals(action)){
			 String webId = request.getParameter("webId");
			 String title = request.getParameter("title");
			 String meta = request.getParameter("meta");
			 String virtualHost = request.getParameter("virtualHost");
			 String mx = request.getParameter("mx");
			 String email = request.getParameter("email");	
			 String receiveEmail = request.getParameter("receiveEmail");	
			 String logoUrl = request.getParameter("logoUrl");	
			 String logoIcon = request.getParameter("logoIcon");
			 String view= request.getParameter("view");
			 String maxView = request.getParameter("maxView");
			 if(webId == null ||"".equals(webId) 
			   || virtualHost == null ||"".equals(virtualHost) 
			   || mx == null ||"".equals(mx)){
	        	  request.setAttribute("error", "Please input required fields.");
	              return;
	          }
			 Organization org = new Organization(webId,title,virtualHost,mx,email,receiveEmail,logoUrl,logoIcon);
			 OrgProfile profile = new OrgProfile((String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE),meta,view,maxView);
			 this.getUserService(request).createOrganization(org,profile);
			 this.getPortalService(request).createPortalByUser(org.getUser());
			 request.setAttribute("success", String.format("Organizaation %s have been added successfully.",org.getWebId()));
			 response.setPortletMode(PortletMode.VIEW);
		 } else if("modify".equals(action)){
			 String id = request.getParameter("orgId");
			 String webId = request.getParameter("webId");
			 String title = request.getParameter("title");
			 String meta = request.getParameter("meta");
			 String virtualHost = request.getParameter("virtualHost");
			 String mx = request.getParameter("mx");
			 String email = request.getParameter("email");
			 String receiveEmail = request.getParameter("receiveEmail");
			 String logoUrl = request.getParameter("logoUrl");	
			 String logoIcon = request.getParameter("logoIcon");
			 String view= request.getParameter("view");
			 String maxView = request.getParameter("maxView");
			 if(webId == null ||"".equals(webId) 
			   || virtualHost == null ||"".equals(virtualHost) 
			   || mx == null ||"".equals(mx)
			   ){
	        	  request.setAttribute("error", "Please input required fields.");
	              return;
	          }
			 long orgId = Long.parseLong(id);
			 Organization org = this.getUserService(request).getOrgById(orgId);
			 org.setWebId(webId);
			 org.setTitle(title);			 
			 org.setVirtualHost(virtualHost);
			 org.setMx(mx);
			 org.setEmail(email);
			 org.setReceiveEmail(receiveEmail);
			 org.setLogoUrl(logoUrl);
			 org.setLogoIcon(logoIcon);
			 this.getPortalService(request).save(org);
			 String locale = (String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE);				
			 OrgProfile profile = OrganizationThreadLocal.getOrg().getProfileMap().get(locale);
			 if(profile == null){
			 	profile = this.getUserService(request).getOrgProfileByOrgId(orgId,locale);
			 	if(profile != null) OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
			 }
			 if(profile == null){
				 profile = new OrgProfile(orgId,locale,meta,view,maxView);			
				 OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
			 }else{				 
				 profile.setMeta(meta);
				 profile.setView(view);
				 profile.setMaxView(maxView);
			 }
			 this.getPortalService(request).save(profile);
			 request.setAttribute("success", String.format("Organizaation %s have been modified successfully.",org.getWebId()));
			 response.setPortletMode(PortletMode.VIEW);
		 }else if("addStore".equals(action)){
			 String webId = request.getParameter("webId");
			 String title = request.getParameter("title");
			 String meta = request.getParameter("meta");
			 String virtualHost = request.getParameter("virtualHost");
			 String mx = request.getParameter("mx");	
			 String email = request.getParameter("email");
			 String receiveEmail = request.getParameter("receiveEmail");
			 String logoUrl = request.getParameter("logoUrl");	
			 String logoIcon = request.getParameter("logoIcon");
			 String view= request.getParameter("view");
			 String maxView = request.getParameter("maxView");
			 if(webId == null ||"".equals(webId) 
			   || virtualHost == null ||"".equals(virtualHost) 
			   || mx == null ||"".equals(mx)
			   ){
	        	  request.setAttribute("error", "Please input required fields.");
	              return;
	          }
			 Organization org = new Organization(webId,title,virtualHost,mx,email,receiveEmail,logoUrl,logoIcon);			 
			 org.setType(Organization._TYPE_TOP_ORGANIZATION);
			 org.setRole(_ROLE_STORE);
			 OrgProfile profile = new OrgProfile((String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE),meta,view,maxView);
			 this.getUserService(request).createOrganization(org,profile);
			 request.setAttribute("success", String.format("Organizaation %s have been added successfully.",org.getWebId()));
			 response.setPortletMode(PortletMode.VIEW);
		 } else if("modifyStore".equals(action)){
			 String id = request.getParameter("orgId");
			 String webId = request.getParameter("webId");
			 String title = request.getParameter("title");
			 String meta = request.getParameter("meta");
			 String virtualHost = request.getParameter("virtualHost");
			 String mx = request.getParameter("mx");
			 String email = request.getParameter("email");
			 String receiveEmail = request.getParameter("receiveEmail");
			 String logoUrl = request.getParameter("logoUrl");	
			 String logoIcon = request.getParameter("logoIcon");
			 String view= request.getParameter("view");
			 String maxView = request.getParameter("maxView");
			 if(webId == null ||"".equals(webId) 
			   || virtualHost == null ||"".equals(virtualHost) 
			   || mx == null ||"".equals(mx)
			   ){
	        	  request.setAttribute("error", "Please input required fields.");
	              return;
	          }
			 long orgId = Long.parseLong(id);
			 Organization org = this.getUserService(request).getOrgById(orgId);
			 org.setWebId(webId);
			 org.setTitle(title);			 
			 org.setVirtualHost(virtualHost);
			 org.setMx(mx);
			 org.setEmail(email);
			 org.setReceiveEmail(receiveEmail);
			 org.setLogoUrl(logoUrl);
			 org.setLogoIcon(logoIcon);
			 this.getPortalService(request).save(org);
			 String locale = (String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE);				
			 OrgProfile profile = OrganizationThreadLocal.getOrg().getProfileMap().get(locale);
			 if(profile == null){
			 	profile = this.getUserService(request).getOrgProfileByOrgId(orgId,locale);
			 	if(profile != null) OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
			 }
			 if(profile == null){
				 profile = new OrgProfile(orgId,locale,meta,view,maxView);			
				 OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
			 }else{				 
				 profile.setMeta(meta);
				 profile.setView(view);
				 profile.setMaxView(maxView);
			 }
			 this.getPortalService(request).save(profile);
			 request.setAttribute("success", String.format("Organizaation %s have been modified successfully.",org.getWebId()));
			 response.setPortletMode(PortletMode.VIEW);
		 }else if("delete".equals(action)){
			 String id = request.getParameter("orgId");
			 Organization org = this.getUserService(request).getOrgById(Long.parseLong(id));
			 org.setStatus(-1);
			 this.getPortalService(request).save(org); 
		 }
	 }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 List<Organization> orgs = null;
		 if(OrganizationThreadLocal.getOrganizationId() != 1){
			 orgs = new LinkedList<Organization>();
			 orgs.add(OrganizationThreadLocal.getOrg());
		 }else{
			 orgs = this.getUserService(request).getOrganizations();
		 }
		 request.setAttribute("orgs",orgs);
		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/orgManage.jsp").include(request,response);
	 }
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String id = request.getParameter("orgId");
		 if(id != null){
			 long orgId = Long.parseLong(id);
			 Organization org = this.getUserService(request).getOrgById(orgId);
			 request.setAttribute("org",org);
			 String locale = (String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE);				
			 OrgProfile profile = OrganizationThreadLocal.getOrg().getProfileMap().get(locale);
			 if(profile == null){
			 	profile = this.getUserService(request).getOrgProfileByOrgId(orgId,locale);
			 	if(profile != null) OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
			 }			 			 
			 request.setAttribute("org",org);
			 request.setAttribute("orgProfile",profile);
			 List<PortalTab> tabs = this.getPortalService(request).getPortalTabByUser(org.getUser(),org);
			 request.setAttribute("tabs",tabs);
		 }
		 String type=request.getParameter("type");
		 request.setAttribute("type",(type == null) ? "" : type);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/orgDetail.jsp").include(request,response);  
	 }
}