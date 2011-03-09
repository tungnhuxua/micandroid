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

package org.light.portlets.forum;

import static org.light.portal.util.Constants._MAX_ROW_PER_FORUM_PAGE;

import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
		   String topic = request.getParameter("topic");
		   String sformat = request.getParameter("format");
		   String content = request.getParameter("content");
		   String forumId = request.getParameter("forumId");
		   String categoryId = request.getParameter("categoryId");
		   if(topic == null || topic.length() <= 0 || content == null || content.length() <= 0){
			   request.setAttribute("error","Forum's topic and content are required field.");
			   response.setPortletMode(PortletMode.EDIT);
			   return;
		   }
		   String showHtmlEditor = request.getParameter("htmlEditor");
		   int format = 0;
		   try{format = Integer.parseInt(sformat);}catch(Exception e){}
		   if(content != null){
			   if(format == 1){
				   content = HTMLUtil.disableHTML(content);
			   }else{
				   content = HTMLUtil.disableScripts(content);				   
			   }
			   content = content.replaceAll("\n","<br/>").trim();
		   }   	
		   long orgId = OrganizationThreadLocal.getOrganizationId();
           if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
		   ForumPost forum = new ForumPost(topic,content,0,Long.parseLong(forumId),Long.parseLong(categoryId),this.getUser(request).getId(),orgId);
		   this.getPortalService(request).save(forum);
		   request.setAttribute("topicId",String.valueOf(forum.getTopicId()));
		}else if("reply".equals(action)){
			   String topicId = request.getParameter("topicId");			   
			   String sformat = request.getParameter("format");
			   String content = request.getParameter("content");
			   if(content == null || content.length() <= 0){
				   request.setAttribute("error","Forum's reply content is required field.");
				   response.setPortletMode(PortletMode.EDIT);
				   return;
			   }
			   String showHtmlEditor = request.getParameter("htmlEditor");
			   int format = 0;
			   try{format = Integer.parseInt(sformat);}catch(Exception e){}
			   if(content != null){
				   if(format == 1){
					   content = HTMLUtil.disableHTML(content);
				   }else{
					   content = HTMLUtil.disableScripts(content);				   
				   }
				   content = content.replaceAll("\n","<br/>").trim();
			   }   	
			   String forumId = request.getParameter("forumId");
			   String categoryId = request.getParameter("categoryId");
			   long orgId = OrganizationThreadLocal.getOrganizationId();
	           if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
			   ForumPost forum = new ForumPost(null,content,Long.parseLong(topicId),Long.parseLong(forumId),Long.parseLong(categoryId),this.getUser(request).getId(),orgId);
			   this.getPortalService(request).save(forum);
			   ForumPost topForum = this.getForumService(request).getPostById(Integer.parseInt(topicId));
			   topForum.setLastPostId(forum.getId());
			   topForum.setLastPostById(forum.getPostById());
			   this.getPortalService(request).save(topForum);			   
		}else if("category".equals(action)){
			   String category = request.getParameter("category");			   
			   String categoryDesc = request.getParameter("categoryDesc");
			   if(category == null || category.length() <= 0 || categoryDesc == null || categoryDesc.length() <= 0){
				   request.setAttribute("error","Forum's category and description are required fields.");
				   request.setAttribute("category",category);
				   request.setAttribute("categoryDesc",categoryDesc);
				   response.setPortletMode(PortletMode.EDIT);
				   response.setRenderParameter("add","category");
				   return;
			   }			   			  
			   String locale = Locale.ENGLISH.toString(); 
               if(this.getUser(request) != null) locale = this.getUser(request).getRegion();  
               long orgId = OrganizationThreadLocal.getOrganizationId();
               if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
			   ForumCategory forumCategory = new ForumCategory(category,categoryDesc,locale,orgId,this.getUser(request).getId());
			   this.getPortalService(request).save(forumCategory);			   
			   request.setAttribute("success","Your new forum category request has been sent to admin successfully, waiting for approve.");
		}else if("forum".equals(action)){
			   String forum = request.getParameter("forum");			   
			   String forumDesc = request.getParameter("forumDesc");
			   String categoryId = request.getParameter("categoryId");
			   if(forum == null || forum.length() <= 0 ){
				   request.setAttribute("error","Forum's name is required field.");
				   request.setAttribute("forum",forum);
				   request.setAttribute("forumDesc",forum);
				   response.setPortletMode(PortletMode.EDIT);
				   request.setAttribute("categoryId",categoryId);
				   response.setRenderParameter("add","sub");
				   return;
			   }	
			   ForumCategory forumCategory = this.getForumService(request).getForumCategoryById(Integer.parseInt(categoryId));
			   long orgId = OrganizationThreadLocal.getOrganizationId();
	           if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
			   Forum newForum = new Forum(forum,forumDesc,forumCategory,this.getUser(request).getId(),orgId);
			   this.getPortalService(request).save(newForum);			  
			   request.setAttribute("success","Your new forum request has been sent to admin successfully, waiting for approve.");			   
		}else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			ForumPost forum = this.getForumService(request).getPostById(Integer.parseInt(id));
			if(forum != null){
				this.getPortalService(request).delete(forum);	
			}
		}else if("deleteTopic".equals(action)){
			String id = request.getParameter("parameter");
			this.getForumService(request).deleteTopic(Integer.parseInt(id));	
		}else{
			 String showHtmlEditor = request.getParameter("htmlEditor");
			 if(showHtmlEditor != null)
				 request.setAttribute("showHtmlEditor",true);
			 String topic = request.getParameter("topic");
			 if(topic != null){
				 request.setAttribute("topic",topic);
			 }
			 String content = request.getParameter("content");
			 if(content != null){
				 request.setAttribute("content",content);
			 }
			 String topicId = request.getParameter("topicId");
			 if(topicId != null){				 
				 request.setAttribute("topicId",topicId);
			 }
		}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 try{
			 JSONObject json = new JSONObject();
			 JSONObject forumJson = new JSONObject();
			 String categoryId = request.getParameter("categoryId");
			 if(categoryId == null) {
				 categoryId = (String)request.getPortletSession().getAttribute("categoryId",PortletSession.APPLICATION_SCOPE);
				 if(categoryId != null) request.getPortletSession().removeAttribute("categoryId",PortletSession.APPLICATION_SCOPE);
			 }
			 String forumId = request.getParameter("forumId");
			 if(forumId == null) {
				 forumId = (String)request.getPortletSession().getAttribute("forumId",PortletSession.APPLICATION_SCOPE);	
				 if(forumId != null) request.getPortletSession().removeAttribute("forumId",PortletSession.APPLICATION_SCOPE);
			 }
			 forumJson.put("categoryId",categoryId);
			 forumJson.put("forumId",forumId);
			 ForumCategory category = null;
			 Forum forum = null;
			 if(categoryId != null){
				 category = this.getForumService(request).getForumCategoryById(Long.parseLong(categoryId));
				 if(category != null){
					 forumJson.put("categoryName",category.getDisplayName());
				 }
			 }
			 if(forumId != null){
				 forum = this.getForumService(request).getForumById(Integer.parseInt(forumId));
				 if(forum != null){
					 forumJson.put("forumName",forum.getDisplayName());
				 }
			 }
			 if(categoryId == null && forumId == null){
				 viewCategories(request,response,json,forumJson);
			 }else if(categoryId != null && forumId == null){
				 viewForum(request,response,category,json,forumJson);
			 }else if(forumId != null){
				 viewTopic(request,response,forum,json,forumJson);		 
			 }
			 json.put("forum",forumJson);
			 response.getWriter().write(json.toString());
		 }catch(Exception e){
			throw new PortletException(e);			
		 }
	 }	
	 
	 private void viewCategories (RenderRequest request, RenderResponse response,JSONObject json,JSONObject forumJson)
	 	throws Exception
	 {
		 String locale = Locale.ENGLISH.toString(); 	     
	     long orgId = OrganizationThreadLocal.getOrganizationId();
	     if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
		 List<ForumCategory> listCategory = this.getForumService(request).getForumCategories(locale,orgId);
		 if(listCategory != null && listCategory.size() == 1){				 
			 viewForum(request,response,listCategory.get(0),json,forumJson);
		 }else{
			 forumJson.put("categories",JSONUtil.getForumCategoryJSONArray(listCategory));
			 json.put("view","forumCategories.view");
		 }			 
	 }
	 
	 private void viewForum (RenderRequest request, RenderResponse response,ForumCategory category,JSONObject json,JSONObject forumJson)
	  throws Exception
	 {
		 if(category != null){
			 String locale = Locale.ENGLISH.toString(); 	     
		     long orgId = OrganizationThreadLocal.getOrganizationId();
		     if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
			 List<ForumCategory> listCategory = this.getForumService(request).getForumCategories(locale,orgId);
			 if(listCategory != null && listCategory.size() > 1){
				 forumJson.put("back",1);
			 }
			 forumJson.put("categoryId",category.getId());
			 forumJson.put("categoryName",category.getDisplayName());
			 List<Forum> listForum = this.getForumService(request).getForumByCategory(category.getId());
			 forumJson.put("forumLists",JSONUtil.getForumJSONArray(listForum));
			 String direction = request.getParameter("direction");
			 if(direction != null && direction.equals("down")){
				 if(listForum != null && listForum.size() == 1){
					 viewTopic(request,response,listForum.get(0),json,forumJson);
				 }
				 else
					 json.put("view","forums.view");					 
			 }else{
				 if(listForum != null && listForum.size() == 1){					 
					 if(listCategory != null && listCategory.size() == 1){
						 forumJson.put("categoryId",listCategory.get(0).getId());
						 forumJson.put("categoryName",listCategory.get(0).getDisplayName());
						 viewTopic(request,response,listForum.get(0),json,forumJson);
					 }else{
						 forumJson.put("categories",JSONUtil.getForumCategoryJSONArray(listCategory));
						 json.put("view","forumCategories.view");
					 }						 						 
				 }else
					 json.put("view","forums.view");					 
			 }
		 }
	 }
	 
	 private void viewTopic (RenderRequest request, RenderResponse response,Forum forum,JSONObject json,JSONObject forumJson)
	  throws Exception
	 {
		 int max = _MAX_ROW_PER_FORUM_PAGE;
		 if(forum != null){
			 String locale = Locale.ENGLISH.toString(); 	     
		     long orgId = OrganizationThreadLocal.getOrganizationId();
		     if(this.getVisitedGroup(request) !=  null) orgId = this.getVisitedGroup(request).getId();
			 List<ForumCategory> listCategory = this.getForumService(request).getForumCategories(locale,orgId);
			 List<Forum> listForum = this.getForumService(request).getForumByCategory(forum.getCategoryId());
			 if((listCategory != null && listCategory.size() > 1) || (listForum != null && listForum.size() > 1)){
				 forumJson.put("back",1);
			 }
			 forumJson.put("forumId",forum.getId());
			 forumJson.put("forumName",forum.getDisplayName());			 
			 String topicId = request.getParameter("topicId");
			 if(topicId == null) {
				 topicId = (String)request.getPortletSession().getAttribute("topicId",PortletSession.APPLICATION_SCOPE);	
				 if(topicId != null) request.getPortletSession().removeAttribute("topicId",PortletSession.APPLICATION_SCOPE);
			 }
			 if(topicId == null) topicId = (String)request.getAttribute("topicId");
			 if(topicId == null){
				 String pageId = request.getParameter("pageId");				 
				 String pages = request.getParameter("pages");
				 if(pageId == null){
				    int count = this.getForumService(request).getTopicsCountByForum(forum.getId());				    
				    pageId ="1";
				    int newPages = count / _MAX_ROW_PER_FORUM_PAGE;
				    if( count % _MAX_ROW_PER_FORUM_PAGE != 0)
				    	newPages++;
				    pages = String.valueOf(newPages);
				 }
				 forumJson.put("pageId",Integer.parseInt(pageId));
				 forumJson.put("pages",pages);//JSONUtil.getNumberArray(1,Integer.parseInt(pages)));
				 List<ForumPost> list = this.getForumService(request).getTopicsByForum(forum.getId(),Integer.parseInt(pageId) - 1,max);
				 forumJson.put("topicLists",JSONUtil.getForumPostJSONArray(list));
				 json.put("view","forumTopics.view");				 
			 }else{
				 String pageId = request.getParameter("pageId");				 
				 String pages = request.getParameter("pages");
				 if(pageId == null){
				    int count = this.getForumService(request).getPostsCountByTopic(Long.parseLong(topicId));				    
				    pageId ="1";
				    int newPages = count / max;
				    if( count % max != 0)
				    	newPages++;
				    pages = String.valueOf(newPages);
				 }
				 forumJson.put("pageId",Integer.parseInt(pageId));
				 forumJson.put("pages",pages);//JSONUtil.getNumberArray(1,Integer.parseInt(pages)));
				 List<ForumPost> list = this.getForumService(request).getPostsByTopic(Long.parseLong(topicId),Integer.parseInt(pageId) - 1,max);
				 if(list != null && list.size() > 0) 
					 forumJson.put("topicName",list.get(0).getTopic());
				 forumJson.put("topicId",topicId);
				 forumJson.put("postLists",JSONUtil.getForumPostJSONArray(list));
				 json.put("view","forumTopicPosts.view");				 
			 }
		 }
	 }

	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  		 
		 try{
			 JSONObject json = new JSONObject();
			 JSONObject forumJson = new JSONObject();
			 String categoryId = request.getParameter("categoryId");
			 String forumId = request.getParameter("forumId");
			 String stopicId = request.getParameter("topicId");
			 String topicName = request.getParameter("topicName");
			 forumJson.put("categoryId",categoryId);
			 forumJson.put("forumId",forumId);		
			 forumJson.put("topicId",stopicId);	
			 forumJson.put("topicName",topicName);
			 if(categoryId != null){
				 ForumCategory category = this.getForumService(request).getForumCategoryById(Integer.parseInt(categoryId));
				 if(category != null){
					 forumJson.put("categoryName",category.getDisplayName());
				 }
			 }
			 if(!StringUtil.isEmpty(forumId)){
				 Forum forum = this.getForumService(request).getForumById(Long.parseLong(forumId));
				 if(forum != null){
					 forumJson.put("forumName",forum.getDisplayName());
				 }
			 }
			 String action = request.getParameter("action");
			 if("category".equals(action)){
				 json.put("view","forumCategory.edit");
			 }else if("forum".equals(action)){
				 json.put("view","forum.edit");
			 }else{
				 String postId = request.getParameter("parameter");
				 if(postId != null){
					 ForumPost post = this.getForumService(request).getPostById(Integer.parseInt(postId));
					 long topicId= post.getTopId();
					 if(topicId == 0){
						 topicId = post.getId();
						 forumJson.put("topicName",post.getTopic());
					 }else{
						 ForumPost topic = this.getForumService(request).getPostById(topicId);
						 forumJson.put("topicName",topic.getTopic());
					 }		
					 forumJson.put("topicId",topicId);
					 if("quote".equals(action)){
						 StringBuffer content = new StringBuffer();
						 content.append("<span class='portlet'>")
						        .append(post.getDisplayName()+" wrote: ")
						        .append("<p/>")
						        .append(post.getContent())
						        .append("</span>")
						        ;
						 forumJson.put("content",content.toString());
					 }
					 
				 }				 
				 json.put("view","forumPost.edit");				 
			 }
			 json.put("success",request.getAttribute("success"));
			 json.put("error",request.getAttribute("error"));
			 json.put("forum",forumJson);
			 response.getWriter().write(json.toString());
		 }catch(Exception e){
			 throw new PortletException(e);			
		 }
	 }
	 
	 protected void doHelp (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  		 
		 try{
			 JSONObject json = new JSONObject();
			 json.put("view","forum.help");
			 response.getWriter().write(json.toString());
		 }catch(Exception e){
			throw new PortletException(e);			
		 }
	 }
}
