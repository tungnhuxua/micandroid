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

import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._PORTAL_URL_FORMAT;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ProfilePortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("delete".equals(action)){
			String id = request.getParameter("parameter");
			UserTag tag = this.getUserService(request).getUserTagById(Long.parseLong(id));
			if(tag != null){
				this.getPortalService(request).delete(tag);	
			}
		 }		 
		 response.setPortletMode(PortletMode.VIEW);
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
	     User user = this.getUser(request);
	     if(getVisitedUser(request) != null){
	    	 user = this.getVisitedUser(request); 
	     }
	     if(user != null){
		     UserProfile userProfile = this.getUserService(request).getUserProfileById(user.getId());		                 	
		     List<UserTag> tags = this.getUserService(request).getUserTags(user.getId(),_OBJECT_TYPE_USER);
		     try{
			     JSONObject json = new JSONObject(); 
			     json.put("view", "myProfile.view");
			     JSONObject userJSON = new JSONObject();
			     userJSON.put("id", user.getId());
			     userJSON.put("name", user.getName());
			     userJSON.put("photoUrl", (user.getPhotoThumbUrl() != null) ? user.getPhotoThumbUrl() : "");
			     if(user.getPhotoThumbUrl() != null && user.getPhotoThumbUrl().startsWith("http")) userJSON.put("httpPhotoUrl", 1);
			     userJSON.put("photoSmallWidth", user.getPhotoSmallWidth());
			     userJSON.put("photoSmallHeight", user.getPhotoSmallHeight());
			     userJSON.put("occupation", (userProfile.getOccupation() != null) ? userProfile.getOccupation() : "");
			     userJSON.put("currentStatus", user.getCurrentStatus());
			     userJSON.put("genderName", user.getGenderName());
			     userJSON.put("age", user.getAge());
			     userJSON.put("birthday", (user.getShowBirthToFriend() == 1) ? user.getBirthday() : "");
			     userJSON.put("city", user.getCity() != null ? user.getCity() : "");
			     userJSON.put("province", user.getProvince() != null ? user.getProvince() : "");
			     userJSON.put("country", user.getCountry() != null ? user.getCountry() : "");
			     userJSON.put("uriType", user.getUriType());
			     userJSON.put("visitCount", user.getVisitCount());
			     userJSON.put("lastDate", user.getLastDate());
			     String url;
			     if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
			    	 url = "http://"+user.getUri()+"."+OrganizationThreadLocal.getOrg().getHost();
			     else
			    	 url = "http://"+OrganizationThreadLocal.getOrg().getUserSpacePrefix()+user.getUri();
			     userJSON.put("url", url);		    	 
		    	 JSONArray jArray = new JSONArray();
		    	 for(UserTag tag : tags){
		    		 JSONObject jsonTag = new JSONObject(); 
		    		 jsonTag.put("id",tag.getId());
		    		 jsonTag.put("tag",tag.getTag());
		    		 jsonTag.put("score",tag.getScore());
		    		 jsonTag.put("size",tag.getSize());
		    		 jArray.put(jsonTag);
		    	 }
		    	 userJSON.put("tags", jArray);
		    	 json.put("user", userJSON);
				 response.getWriter().write(json.toString());	
		     }catch(Exception e){
		     }	  		 
	     }	
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 doView(request,response);	
	 }	

	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String title = getUser(request).getDisplayName();		
		 if(getVisitedUser(request) != null){
			title = getVisitedUser(request).getDisplayName();		
		 }
		 try{
			 JSONObject json = new JSONObject();
			 json.put("id",request.getParameter("responseId"));
			 json.put("title",title);
			 response.getWriter().write(json.toString());
		 }catch(Exception e){			 
		 }
	 }
}