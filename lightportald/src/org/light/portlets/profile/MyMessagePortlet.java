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

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.cache.CacheService;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyMessagePortlet extends LightGenericPortlet {
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
	     if(this.getVisitedUser(request) != null)
	    	 user = this.getVisitedUser(request);
	     if(user != null){
			 int messageCount=this.getUserService(request).getNewMessageCountByUser(user.getId());
			 int friendRequestCount = this.getUserService(request).getNewConnectionRequestCountByUser(user.getId());
			 try{
			     JSONObject json = new JSONObject(); 
			     json.put("view", "myMessage.view");
			     JSONObject userJSON = new JSONObject();
			     if(messageCount > 0) userJSON.put("messageCount", messageCount);
			     if(friendRequestCount > 0)userJSON.put("friendRequestCount", friendRequestCount);			     
		    	 json.put("user", userJSON);
				 response.getWriter().write(json.toString());	
		     }catch(Exception e){
		     }
	     }
	 }	

	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count=this.getUserService(request).getNewMessageCountByUser(user.getId());
			 Context.getInstance().getCacheService(request).setObject(Message.class,user.getId()+CacheService.LAST_COUNT,count);
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
	 
	 public Object doNotification(HttpServletRequest request)
	 {
		 JSONObject json = new JSONObject();
		 try{
			 int count = 0;
			 User user = Context.getInstance().getUser(request);
			 if(user != null)
			 	count = Context.getInstance().getUserService(request).getNewMessageCountByUser(user.getId());
			 Integer lcount = (Integer)Context.getInstance().getCacheService(request).getObject(Message.class,user.getId()+CacheService.LAST_COUNT);
			 if(lcount != null && lcount.intValue() != count){
				 Context.getInstance().getCacheService(request).setObject(Message.class,user.getId()+CacheService.LAST_COUNT,count);
				 json.put("refresh",1);
			 }
			 if(count>0) json.put("notification",count);
		 }catch(Exception e){} 
		 return json;
	 }
}
