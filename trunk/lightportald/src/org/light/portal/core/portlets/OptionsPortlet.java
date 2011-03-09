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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.core.PortalCommandUtil;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.ValidationUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class OptionsPortlet  extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("editPageUrl".equals(action)){
			 request.setAttribute("option","page");
			 String url = request.getParameter("url");
			 if(!ValidationUtil.isValidUrl(url)){
				 request.setAttribute("error", "The page URL is not allowed, please input a new page URL.");
				 return;
			 }
			 if(this.getPortalService(request).getPortalTabByUrl(url, OrganizationThreadLocal.getOrganizationId()) != null){
				 request.setAttribute("error", "The page URL has been taken already, please input a new page URL.");
				 return;
			 }
			 String tabId = request.getParameter("tabId");
			 long id = 0;
			 try{
					id = Long.parseLong(tabId);
				}catch(Exception e){}			
			 if(id > 0){
				PortalTab tab = this.getPortalService(request).getPortalTabById(id);
				if(tab != null){
					tab.setUrl(url);
					this.getPortalService(request).save(tab);
				}
			 }
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
        User user = this.getUser(request);      
        request.setAttribute("user", user);
		String option = request.getParameter("action");
		if(request.getAttribute("option") != null) option = (String)request.getAttribute("option");
		request.setAttribute("current",option);		
		if(option == null) option= "general";			
		if(option.equals("language")){
			optionLanguage(request,response);
		}else if(option.equals("localContent")){
			optionLocalContent(request,response);
		}else if(option.equals("timeZone")){
			optionTimeZone(request,response);
		}else if(option.equals("theme")){	
			optionTheme(request,response);
	 	}else if(option.equals("page")){
	 		optionPage(request,response);
	 	}else if(option.equals("parentPage")){
	 		optionParentPage(request,response);
	 	}else{	 		
	 		optionGeneral(request,response);  
	 	}			
	 }	
	 
	 private void optionLanguage(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 try{
		 	JSONObject json = new JSONObject();  
		 	json.put("view", "optionsLanguage.view");
		 	json.put("current","language");
		 	response.getWriter().write(json.toString());
		 }catch(Exception e){}
	 }
	 
	 private void optionLocalContent(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 try{
		 	JSONObject json = new JSONObject();  
		    json.put("view", "optionsLocalContent.view");
		 	json.put("current","localContent");
		 	json.put("region",this.getUser(request).getRegion());
		 	response.getWriter().write(json.toString());
		 }catch(Exception e){}
	 }
	 
	 private void optionTimeZone(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 try{
		 	JSONObject json = new JSONObject();  
		    json.put("view", "optionsTimeZone.view");
		 	json.put("current","timeZone");
		 	json.put("timeZone",(this.getUser(request).getTimeZone() != null) ? this.getUser(request).getTimeZone() : "");
		 	response.getWriter().write(json.toString());
		 }catch(Exception e){}
	 }
	 
	 private void optionTheme(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 	String commandName = "getPortal";
		 	if(isAdmin(request,this.getUser(request)) && this.getVisitedPortal(request) !=null) commandName = "visitPortal";
		 	try{
			 	JSONObject json = (JSONObject)PortalCommandUtil.runCommand(request,response,commandName);
			    json.put("view", "optionsTheme.view");
			 	json.put("current","theme");
			 	response.getWriter().write(json.toString());
		 	}catch(Exception e){}
		 	
	 }
	 
	 private void optionPage(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 PortalTab tab = getPortalTab(request);
		 this.optionPage(request,response,tab);
	 }
	 
	 private void optionParentPage(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 PortalTab tab = getPortalTab(request);
		 if(tab.getParentId() > 0){
 			PortalTab portalTab = this.getPortalService(request).getPortalTabById(tab.getParentId());
 			this.optionPage(request,response,portalTab);
		 }
	 }
	 
	 private void optionPage(RenderRequest request, RenderResponse response, PortalTab tab)
	   throws PortletException, java.io.IOException {		 
		 User user = this.getUser(request);
		 Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
		 try{
		 	JSONObject json = new JSONObject();  
		    json.put("view", "optionsPage.view");
		 	json.put("current","page");
		 	json.put("authorized",
		 			(user != null && user.getId()!=OrganizationThreadLocal.getOrg().getUserId() && 
					(user.getUserId().equals(portal.getOwnerId()) 
							|| isAdmin(request, user)
							|| isGroupTabOwner(request,tab, user))) ? 1 : 0);
		 	json.put("tabId",tab.getId());
			json.put("title",tab.getTitle());
			json.put("client",tab.getClient());
			json.put("hasUrl",tab.isHasUrl() ? 1 : 0);
			json.put("tabUrl",tab.getUrl());
			json.put("closeable",tab.getCloseable());
			json.put("editable",tab.getEditable());
			json.put("moveable",tab.getMoveable());
			json.put("allowAddContent",tab.getAllowAddContent());
			json.put("color",tab.getColor());
			json.put("defaulted",tab.getDefaulted());
			json.put("status",tab.getStatus());
			json.put("between",tab.getBetween());
			json.put("widths",tab.getWidths());
			json.put("columnTotal",tab.getColumnTotal());
			json.put("fitScreen",tab.getFitScreen());
			json.put("windowSkin",tab.getWindowSkin());
			json.put("parentId",tab.getParentId());	
			String error="";
			if(request.getAttribute("error") != null) error = (String)request.getAttribute("error");
			json.put("error",error);
			response.getWriter().write(json.toString());
	 	}catch(Exception e){}
	 }
	 private void optionGeneral(RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException {
		 	String commandName = "getPortal";
		 	if(isAdmin(request,this.getUser(request)) && this.getVisitedPortal(request) !=null) commandName = "visitPortal";
		 	try{
			 	JSONObject json = (JSONObject)PortalCommandUtil.runCommand(request,response,commandName);
			    json.put("view", "optionsGeneral.view");
			 	json.put("current","general");
			 	response.getWriter().write(json.toString());
		 	}catch(Exception e){}
	 }
}