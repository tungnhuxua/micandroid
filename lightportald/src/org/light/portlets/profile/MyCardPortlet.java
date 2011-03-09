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

import static org.light.portal.util.Constants._PORTAL_URL_FORMAT;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portlets.microblog.Microblog;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyCardPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("save".equals(action)){
			String caption = request.getParameter("caption");
			Microblog mblog = new Microblog(this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId(),caption);
			this.getUserService(request).save(mblog);	
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 int permission = 1;
	     if(this.getVisitedUser(request) != null){
	    	 user = this.getVisitedUser(request);
	    	 permission = 0;
	     }
	     try{
		     JSONObject json = new JSONObject(); 
		     json.put("view", "myCard.view");
		     json.put("permission", permission);
			 json.put("name", user.getName());
			 json.put("image", (user.getPhotoThumbUrl() != null) ? user.getPhotoThumbUrl() : OrganizationThreadLocal.getOrg().getDefaultMalePortrait());
			 List<Microblog> mblogs = getMicroblogService(request).getPosts(user.getId(),1,0,1);
			 String caption = "";
			 if(mblogs.size() > 0)
				 caption = mblogs.get(0).getContent();
			 else
				 caption = PortalContextFactory.getPortalContext().getMessageByKey("message.userStatus");
			 json.put("caption", caption);
		     String url;
		     if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
		    	 url = "http://"+user.getUri()+"."+OrganizationThreadLocal.getOrg().getHost();
		     else
		    	 url = "http://"+OrganizationThreadLocal.getOrg().getUserSpacePrefix()+user.getUri();
	    	 json.put("url", url);			
			 response.getWriter().write(json.toString());	
	     }catch(Exception e){
	     }
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 int permission = 1;
	     if(this.getVisitedUser(request) != null){
	    	 user = this.getVisitedUser(request);
	    	 permission = 0;
	     }
	     try{
		     JSONObject json = new JSONObject(); 
		     json.put("view", "myCard.edit");
		     json.put("permission", permission);
			 json.put("name", user.getName());
			 json.put("image", (user.getPhotoThumbUrl() != null) ? user.getPhotoThumbUrl() : OrganizationThreadLocal.getOrg().getDefaultMalePortrait());
			 json.put("caption", (user.getCaption() != null) ? user.getCaption() : "");
			 String url;
		     if(PropUtil.getInt(_PORTAL_URL_FORMAT) == 1)
		    	 url = "http://"+user.getUri()+"."+OrganizationThreadLocal.getOrg().getHost();
		     else
		    	 url = "http://"+OrganizationThreadLocal.getOrg().getUserSpacePrefix()+user.getUri();
		     json.put("url", url);			
			 response.getWriter().write(json.toString());	
	     }catch(Exception e){
	     }
	 }	

}
