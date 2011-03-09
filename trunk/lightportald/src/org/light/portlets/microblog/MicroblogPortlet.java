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
package org.light.portlets.microblog;

import static org.light.portal.util.Constants._MAX_ROW_PER_MICROBLOG_PAGE;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.StringUtil;
import org.light.portlets.microblog.service.MicroblogService;

/**
 * 
 * @author Jianmin Liu
 **/
public class MicroblogPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");		
		 if("add".equals(action)){
			 String content = request.getParameter("content");			   
			 if(StringUtil.isEmpty(content)){
				 request.setAttribute("missingField",true);
				 return;
			 }	  		
			 User user = this.getUser(request);
			 if(user != null && user.getId() != OrganizationThreadLocal.getOrg().getUserId()){
				 long toUserId = user.getId();
				 if(this.getVisitedUser(request) != null) toUserId = this.getVisitedUser(request).getId();
				 Microblog mblog = new Microblog(toUserId,user.getId(),user.getOrgId(),content);	
				 this.getPortalService(request).save(mblog);
			 }
		 }else if("delete".equals(action)){
			String mblogId = request.getParameter("parameter");
			Microblog mblog = this.getMicroblogService(request).getMicroblogById(Long.parseLong(mblogId));
			if(mblog != null){
				this.getPortalService(request).delete(mblog);	
			}
		 }else if("deleteComment".equals(action)){
			String commentId = request.getParameter("parameter");
			UserComment comment = this.getUserService(request).getUserCommentsById(Long.parseLong(commentId));
			if(comment != null){
				this.getPortalService(request).delete(comment);	
			}
		 }else if("config".equals(action)){
			String type = request.getParameter("type");
			PortletPreferences portletPreferences = request.getPreferences();
			portletPreferences.setValue("type",type);
			portletPreferences.store();
		}		
	 }
	 
	 public final static String _TYPE_ALL 		= "0";
	 public final static String _TYPE_ME		= "1";
	 public final static String _TYPE_FRIEND 	= "2";
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 PortletPreferences portletPreferences = request.getPreferences();
		 String type = portletPreferences.getValue("type",_TYPE_ALL);		 
		 User user =this.getUser(request);	
		 int max = _MAX_ROW_PER_MICROBLOG_PAGE;
		 if(this.getVisitedUser(request) != null){
			 user = this.getVisitedUser(request);
			 type = _TYPE_ME;
		 }
		 if(user != null){
			 int page = 1;
			 String pageNumber = request.getParameter("page");
			 if(pageNumber != null) page = Integer.parseInt(pageNumber);
			 int total = this.getMicroblogService(request).getPostCount(user.getId(),Integer.parseInt(type));			    
			 if(page < 1) page = 1;
			 int start = (page - 1) * max;
			 int end = start + max;
			 if(end > total) end = total;
			 if(start > end) start = end - max;
			 int totalPages = (total % max == 0) ? total / max : total / max + 1;
			
			 List<Microblog> list = getMicroblogService(request).getPosts(user.getId(),Integer.parseInt(type),start,end);
			 
			 JSONObject json = new JSONObject();
			 try{
				json.put("view", "status.view");
				json.put("type",Integer.parseInt(type));
				json.put("microblogs", (total > 0) ? getStatusJSON(list) : 0);
				json.put("total", total);
				json.put("pages",totalPages);
				json.put("page",page);
				json.put("start",start);
				json.put("end",end);
				int begin = page;
				if(begin - 5 > 0) 
					begin -= 5;
				else
					begin = 1;
				int finish = totalPages;
				if(finish >= begin+10) finish = begin+9;
				json.put("pageNumbers",JSONUtil.getNumberArray(begin,finish));
			 }catch(Exception e){}				
			 response.getWriter().write(json.toString());	
 		 }
	 }
	 
	 protected JSONArray getStatusJSON(List<Microblog> list){
			JSONArray jArray = new JSONArray();
			try{						
				for(Microblog item : list){
					JSONObject jItem = getJSON(item);
					jArray.put(jItem);
				}		
			}catch(Exception e){}
			return jArray;
		}
		
		protected JSONObject getJSON(Microblog item){
			JSONObject jItem = new JSONObject();
			try{			
				jItem.put("id",item.getId());
				jItem.put("userId",item.getUserId());
				jItem.put("postById",item.getPostById());
				jItem.put("displayName",item.getDisplayName());
				jItem.put("uri",item.getUri());
				jItem.put("photoUrl",item.getPhotoUrl());
				jItem.put("content",item.getContent());
				jItem.put("date",item.getFullDate());
				jItem.put("comments", JSONUtil.getCommentJSONArray(new ArrayList(item.getComments())));		
			}catch(Exception e){}
			return jItem;
		}
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {   		 
		 
	 }
	 
	 protected MicroblogService getMicroblogService(PortletRequest request) {			
		return (MicroblogService)Context.getInstance().getService(this.getServletRequest(request),"microblogService");
	 }
}

