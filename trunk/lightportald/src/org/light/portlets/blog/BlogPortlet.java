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

package org.light.portlets.blog;

import static org.light.portal.util.Constants._OBJECT_TYPE_BLOG;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;
import static org.light.portal.util.Constants._STATUS_DRAFT;
import static org.light.portal.util.Constants._STATUS_PUBLISHED;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlogPortlet extends LightGenericPortlet {
	private final static  String _BLOG_USER 	= "0";
	private final static  String _BLOG_ORG 		= "1";
	private final static  String _BLOG_GROUP	= "2";
	private final static  String _BLOG_VIEW		= "3";
	
	public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
	String action = request.getParameter("action");
	if("draft".equals(action)){
	   PortletPreferences portletPreferences = request.getPreferences();
	   String category = portletPreferences.getValue("category","0");
	   if(request.getParameter("category") != null) category = request.getParameter("category");
	   String subject = request.getParameter("subject");
	   String summary = request.getParameter("summary");
	   String content = request.getParameter("content");
	   if(subject == null || subject.length() <= 0 ){
		   request.setAttribute("error","Blog's subject is field.");
		   request.setAttribute("subject",subject);
		   request.setAttribute("summary",summary);
		   request.setAttribute("content",content);
		   request.setAttribute("mode",PortletMode.EDIT.toString());
		   return;
	   }
	   String showHtmlEditor = request.getParameter("htmlEditor");
	   if(showHtmlEditor == null)
		   content = content.replaceAll("\n","<br/>");
	   content = HTMLUtil.disableScripts(content);
	   Blog blog = null;
	   String blogId = request.getParameter("blogId");
	   if(blogId != null) blog = this.getBlogService(request).getBlogById(Long.parseLong(blogId));
	   if(blog == null){
		   String type = portletPreferences.getValue("type",_BLOG_USER);
		   long userId = this.getUser(request).getId();
		   long orgId = OrganizationThreadLocal.getOrg().getId();
		   if(_BLOG_ORG.equals(type)){
			   userId = OrganizationThreadLocal.getOrg().getUserId();  
		   }else if(_BLOG_GROUP.equals(type)){
			   if(this.getVisitedGroup(request) != null) orgId = this.getVisitedGroup(request).getId();			   
		   }  
		   blog = new Blog(userId,this.getUser(request).getId(),orgId,subject,summary,content,0); 
		   blog.setCategoryId(Long.parseLong(category));
	   }else{
		   blog.setTitle(subject);
		   blog.setContent(content);
		   blog.setStatus(_STATUS_DRAFT);
		   blog.setCategoryId(Long.parseLong(category));
	   }
	   this.getPortalService(request).save(blog);
	}	
	else if("save".equals(action)){
	   PortletPreferences portletPreferences = request.getPreferences();
	   String category = portletPreferences.getValue("category","0");
	   if(request.getParameter("category") != null) category = request.getParameter("category");
	   String subject = request.getParameter("subject");
	   String summary = request.getParameter("summary");
	   String content = request.getParameter("content");
	   if(subject == null || subject.length() <= 0 || content == null || content.length() <= 0){
		   request.setAttribute("error","Blog's subject and content are required field.");
		   request.setAttribute("subject",subject);
		   request.setAttribute("summary",summary);
		   request.setAttribute("content",content);		   
		   response.setPortletMode(PortletMode.EDIT);
		   response.setRenderParameter("add","add");
		   return;
	   }	
	   String showHtmlEditor = request.getParameter("htmlEditor");
	   if(showHtmlEditor == null)
		   content = content.replaceAll("\n","<br/>");
	   content = HTMLUtil.disableScripts(content);
	   Blog blog = null;
	   String blogId = request.getParameter("blogId");
	   if(blogId != null) blog = this.getBlogService(request).getBlogById(Long.parseLong(blogId));
	   if(blog == null){
		   String type = portletPreferences.getValue("type",_BLOG_USER);
		   long userId = this.getUser(request).getId();		   
		   long orgId = OrganizationThreadLocal.getOrg().getId();
		   if(_BLOG_ORG.equals(type)){
			   userId = OrganizationThreadLocal.getOrg().getUserId();  
		   }else if(_BLOG_GROUP.equals(type)){
			   if(this.getVisitedGroup(request) != null) orgId = this.getVisitedGroup(request).getId();			   
		   }  
		   blog = new Blog(userId,this.getUser(request).getId(),orgId,subject,summary,content,_STATUS_PUBLISHED); 	
		   blog.setCategoryId(Long.parseLong(category));
	   }else{
		   blog.setTitle(subject);
		   blog.setSummary(summary);
		   blog.setContent(content);
		   blog.setStatus(1);
		   blog.setCategoryId(Long.parseLong(category));
	   }
	   this.getPortalService(request).save(blog);
	}	
	else if("delete".equals(action)){
		String blogId = request.getParameter("parameter");
		Blog blog = this.getBlogService(request).getBlogById(Long.parseLong(blogId));
		if(blog != null){
			this.getPortalService(request).delete(blog);	
		}
	}	
	else if("config".equals(action)){
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String items = request.getParameter("items");
		String sort = request.getParameter("sortType");
		String showLevel = request.getParameter("showLevel");
		PortletObject portlet = getPortlet(request);		
		if(portlet != null){			
			PortletPreferences portletPreferences = request.getPreferences();
			portletPreferences.setValue("title",title);
			portletPreferences.setValue("category",category);
			portletPreferences.setValue("showLevel",showLevel);
			portletPreferences.store();
			int number = Integer.parseInt(items);
			String parameter = "sort="+sort;
			if(portlet.getShowNumber() != number || !parameter.equals(portlet.getParameter())){
				portlet.setShowNumber(number);
				portlet.setParameter(parameter);
				this.getPortalService(request).save(portlet);
			}
		}
		response.setPortletMode(PortletMode.VIEW);		
	}
	else if("category".equals(action)){
		String category = request.getParameter("newCategory");
		if(category != null){
			BlogCategory blogCategory = new BlogCategory(category,null,this.getUser(request).getId(),this.getUser(request).getOrgId());
			this.getPortalService(request).save(blogCategory);
		}else
			request.setAttribute("error","Please input a new Blog Category first.");
		String subject = request.getParameter("subject");
	    String summary = request.getParameter("summary");
	    String content = request.getParameter("content");
	    request.setAttribute("subject",subject);
		request.setAttribute("summary",summary);
	    request.setAttribute("content",content);		   
	    response.setPortletMode(PortletMode.EDIT);
	    response.setRenderParameter("add","add");
	    return;
	}
	else{
		 String showHtmlEditor = request.getParameter("htmlEditor");
		 if(showHtmlEditor != null)
			 request.setAttribute("showHtmlEditor",true);
		 String blogId = request.getParameter("blogId");
		 if(blogId != null){			    			   
			 request.setAttribute("blogId",blogId);
		 }
		 String subject = request.getParameter("subject");
		 if(subject != null){			 		   			   
			 request.setAttribute("subject",subject);
		 }
		 String summary = request.getParameter("summary");
		 if(summary != null){
			 request.setAttribute("summary",summary);
		 }
		 String content = request.getParameter("content");
		 if(content != null){
			 request.setAttribute("content",content);
		 }			 			 
	}
  }
 
 protected void doView (RenderRequest request, RenderResponse response)
   throws PortletException, java.io.IOException
 {
	 String blogId = request.getParameter("blogId");
	 Blog blog = null;
	 if(blogId == null){
		 if(request.getPortletSession().getAttribute("blog",PortletSession.APPLICATION_SCOPE) != null){
			 blog = (Blog)request.getPortletSession().getAttribute("blog",PortletSession.APPLICATION_SCOPE);
			 request.getPortletSession().removeAttribute("blog",PortletSession.APPLICATION_SCOPE);
		 }
	 }
	 if(blogId == null && blog == null){		 
		 PortletPreferences portletPreferences = request.getPreferences();
		 String type = portletPreferences.getValue("type",_BLOG_USER);
		 String category = portletPreferences.getValue("category","0");
		 String title = portletPreferences.getValue("title",null);
		 request.setAttribute("type",type);
		 request.setAttribute("title",title);
		 PortletObject portlet = this.getPortlet(request);
		 String sort = (portlet != null) ? portlet.getParameter() : null;
		 if(sort != null && sort.length() > 0)
			 sort = sort.substring(sort.length() - 1);
		 else
			 sort=null;
		 request.setAttribute("sort",sort);
		 User user = this.getUser(request);
		 long userId = user.getId();
		 long orgId = OrganizationThreadLocal.getOrg().getId();
		 int showNumber = (portlet != null && portlet.getShowNumber() >= 0) ? portlet.getShowNumber() : 6;
		 if(showNumber<=0) showNumber = 6;
		 int page = 1;
		 String pageNumber = request.getParameter("page");
		 if(pageNumber != null) page = Integer.parseInt(pageNumber);
		 int total = 0;
		 if("0".equals(category)){
			 if(_BLOG_ORG.equals(type)){
			 	userId = OrganizationThreadLocal.getOrg().getUserId(); 		 	
			 	total = this.getBlogService(request).getBlogsTotalByUser(userId,orgId);		 			 		
		     }else if(_BLOG_GROUP.equals(type)){
			 	if(this.getVisitedGroup(request) != null) orgId = this.getVisitedGroup(request).getId();			   		 	
			 	total = this.getBlogService(request).getBlogsTotal(orgId);		 	
			 }else if(_BLOG_VIEW.equals(type)){
				 total = this.getBlogService(request).getBlogsTotal(OrganizationThreadLocal.getOrganizationId());			 
		 	 }else{
				 if(this.getVisitedUser(request) != null){
					 userId = this.getVisitedUser(request).getId();
					 total = this.getBlogService(request).getBlogsTotalByVisitor(userId,OrganizationThreadLocal.getOrganizationId());
				 }else
					 total = this.getBlogService(request).getBlogsTotalByUser(userId,OrganizationThreadLocal.getOrganizationId());
			 }
		 }else
			 total = this.getBlogService(request).getBlogsTotalByCategory(Long.parseLong(category));
		 if(page < 1) page = 1;
		 int start = (page - 1) * showNumber;
		 int end = start + showNumber;
		 if(end > total) end = total;
		 if(start > end) start = end - showNumber;
		 int totalPages = (total % showNumber == 0) ? total / showNumber : total / showNumber + 1;
		 request.setAttribute("page",page);
		 request.setAttribute("pages",totalPages);
		 request.setAttribute("start",start);		   
		 request.setAttribute("end",end);
		 request.setAttribute("total",total);
			
		 List<Blog> list = null;	
		 if("0".equals(category)){
		     if(_BLOG_ORG.equals(type)){
			 	userId = OrganizationThreadLocal.getOrg().getUserId(); 
			 	list = this.getBlogService(request).getBlogsByUser(userId,orgId,sort,start,end);
		     }else if(_BLOG_GROUP.equals(type)){
			 	if(this.getVisitedGroup(request) != null) orgId = this.getVisitedGroup(request).getId();			   
			 	request.setAttribute("orgId",orgId);
			 	list = this.getBlogService(request).getBlogs(orgId,sort,start,end);
			 }else if(_BLOG_VIEW.equals(type)){
				list = this.getBlogService(request).getBlogs(OrganizationThreadLocal.getOrganizationId(),sort,start,end);
		 	 }else{
				 if(this.getVisitedUser(request) != null){
					 userId = this.getVisitedUser(request).getId();
					 list = this.getBlogService(request).getBlogsByVisitor(userId,OrganizationThreadLocal.getOrganizationId(),sort,start,end);
				 }else
			    	 list = this.getBlogService(request).getBlogsByUser(userId,OrganizationThreadLocal.getOrganizationId(),sort,start,end);
			 }
		 }else
			 list = this.getBlogService(request).getBlogsByCategory(Long.parseLong(category),sort,start,end);
	     
	     request.setAttribute("userId",userId);	     	     		  
	     request.setAttribute("blogLists",list);
	     request.setAttribute("showLevel",portletPreferences.getValue("showLevel","0"));
		 if(showNumber < total)
			 request.setAttribute("showMore",true);
		 if(request.getWindowState().equals(WindowState.MAXIMIZED))
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletMaxView.jsp").include(request,response);
		 else
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletView.jsp").include(request,response);
	 }else{		 
		 if(blog == null) blog = this.getBlogService(request).getBlogById(Long.parseLong(blogId));
		 request.setAttribute("blog",blog);
		 if(blog != null && blog.getCommentCount() > 0){
			 List<UserComment> comments = this.getUserService(request).getCommentsByType(blog.getId(),_OBJECT_TYPE_BLOG);
			 request.setAttribute("comments",comments);
		 }		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletDetail.jsp").include(request,response);
	 }
 }	

 protected void doEdit (RenderRequest request, RenderResponse response)
   throws PortletException, java.io.IOException
 {  
	 PortletPreferences portletPreferences = request.getPreferences();
	 String category = portletPreferences.getValue("category","0");
	 String title = portletPreferences.getValue("title",null);;
	 request.setAttribute("title",title);
	 request.setAttribute("category",category);
	 List<BlogCategory> categories = this.getBlogService(request).getBlogCategoriesByUser(this.getUser(request).getId());
	 request.setAttribute("categories",categories);
	 
	 if(request.getParameter("add") != null)
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletEdit.jsp").include(request,response);
	 else if("edit".equals(request.getParameter("action")) && request.getParameter("parameter") != null){
		 String blogId = request.getParameter("parameter");
		 Blog blog = this.getBlogService(request).getBlogById(Long.parseLong(blogId));
		 request.setAttribute("blog",blog);
		 request.setAttribute("content",blog.getContent());
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletEdit.jsp").include(request,response);
	 }else{
		 PortletObject portlet = this.getPortlet(request);
		 int showNumber = (portlet != null && portlet.getShowNumber() >= 0) ? portlet.getShowNumber() : 6;
		 String sort = (portlet != null) ? portlet.getParameter() : null;
		 if(sort != null && sort.length() > 0)
			 sort = sort.substring(sort.length() - 1);
		 else
			 sort=null;
		 request.setAttribute("sort",sort);
		 request.setAttribute("showNumber",showNumber);
		 request.setAttribute("showLevel",portletPreferences.getValue("showLevel","0"));
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletConfig.jsp").include(request,response);
	 }			 
 }
 
 protected void doHelp (RenderRequest request, RenderResponse response)
   throws PortletException, java.io.IOException
 {  		 
	 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/blog/blogPortletHelp.jsp").include(request,response);
 }
}
