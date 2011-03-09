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

package org.light.portlets.profile;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.model.UserMusic;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyMusicPlayerPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 long userId=0;
		 if(user != null) userId= user.getId();
		 List<UserMusic> userMusics = null;
	     if(this.getVisitedUser(request) != null)
	    	 userMusics = this.getUserService(request).getVisitedUserMusics(userId,this.getVisitedUser(request).getId());
		 else
			 userMusics = this.getUserService(request).getUserMusics(userId);
	     try{
		     JSONObject json = new JSONObject();
			 JSONArray jArray = new JSONArray();
			 for(UserMusic music : userMusics){
				 if(music.isHttpUrl())
					 jArray.put(music.getMusicUrl());
				 else
					 jArray.put("http://"+OrganizationThreadLocal.getOrg().getHost()+music.getMusicUrl());
			 }
			 json.put("songs",jArray);			 
			 response.getWriter().write(json.toString());
	     }catch(Exception e){} 		
	 }	
    
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 doView(request,response);			
	 }	

	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count = getUserService(request).getUserMusicCount(user.getId());
			 if(count > 0){
				try{
					 JSONObject json = new JSONObject();
					 json.put("id",request.getParameter("responseId"));
					 json.put("suffix",count);
					 response.getWriter().write(json.toString());
				 }catch(Exception e){			 
				 }
			 }				              			 
		 }
	 }
}
