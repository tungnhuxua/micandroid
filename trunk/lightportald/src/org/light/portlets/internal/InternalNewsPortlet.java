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

package org.light.portlets.internal;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.sql.Timestamp;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PortletObject;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class InternalNewsPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
		   String subject = request.getParameter("subject");
		   String content = request.getParameter("content");
		   if(content != null) content = content.replaceAll("\n","").trim();
		   if(subject == null || subject.length() <= 0 || content == null || content.length() <= 0 ){
			   request.setAttribute("error","News's subject and content are required field.");
			   response.setPortletMode(PortletMode.EDIT);
			   request.setAttribute("subject",subject);
			   request.setAttribute("content",content);
			   return;
		   }
		   String newsId = request.getParameter("newsId");
		   long id = 0;
		   try{
			   id = Long.parseLong(newsId);			   
		   }catch(Exception e){}
		   
		   InternalNews news = null;
		   if(id > 0)
			   news = this.getPortletService(request).getInternalNewsById(id,OrganizationThreadLocal.getOrganizationId());
		   if(news == null){
			   news = new InternalNews(subject,content,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
		   }else{
			   news.setSubject(subject);
			   news.setContent(content);
			   news.setCreateDate(new Timestamp(System.currentTimeMillis()));
		   }
		   this.getPortalService(request).save(news);
		}			
		else if("edit".equals(action)){
			String id = request.getParameter("parameter");
			InternalNews news = this.getPortletService(request).getInternalNewsById(Long.parseLong(id),OrganizationThreadLocal.getOrganizationId());
			if(news != null){
				request.setAttribute("newsId",news.getId());
				request.setAttribute("subject",news.getSubject());
				request.setAttribute("content",news.getContent());
			}
		}
		else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			InternalNews news = this.getPortletService(request).getInternalNewsById(Long.parseLong(id),OrganizationThreadLocal.getOrganizationId());
			if(news != null){
				this.getPortalService(request).delete(news);	
			}
		}
		else{
			 String showHtmlEditor = request.getParameter("htmlEditor");
			 if(showHtmlEditor != null)
				 request.setAttribute("showHtmlEditor",true);
			 String newsId = request.getParameter("newsId");
			 if(newsId != null){	   			   
				 request.setAttribute("newsId",newsId);
			 }
			 String subject = request.getParameter("subject");
			 if(subject != null){
				 request.setAttribute("subject",subject);
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
		 String newsId = request.getParameter("newsId");		 		 
		 if(newsId != null){
			 InternalNews news = this.getPortletService(request).getInternalNewsById(Long.parseLong(newsId),OrganizationThreadLocal.getOrganizationId());
			 request.setAttribute("news",news);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/internal/newsDetailView.jsp").include(request,response);
			 return;
		 }
	     		 			 
		 List<InternalNews> list =this.getPortletService(request).getInternalNews(OrganizationThreadLocal.getOrganizationId()); 
		 PortletObject portlet = this.getPortlet(request);
		 int showNumber = 6;
		 if(portlet.getShowNumber() > 0) showNumber = portlet.getShowNumber();
		 if(list.size() > showNumber && request.getWindowState().equals(WindowState.NORMAL)){
			request.setAttribute("showMore",true);
			request.setAttribute("newsList",list.subList(0,showNumber));	 
		 }else{
			 request.setAttribute("newsList",list);	 
		 }
		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/internal/newsView.jsp").include(request,response);
	 }	

	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {   		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/internal/newsEdit.jsp").include(request,response);
	 }

}
