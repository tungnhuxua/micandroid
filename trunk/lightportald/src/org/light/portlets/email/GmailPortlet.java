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

package org.light.portlets.email;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
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

import org.light.portal.model.UserProfile;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class GmailPortlet extends LightGenericPortlet {
		  
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
	       if("send".equals(action)){
	    	   String toEmail = request.getParameter("toEmail");
	    	   String cc = request.getParameter("cc");
	    	   String bcc = request.getParameter("bcc");
			   String subject = request.getParameter("subject");
			   String content = request.getParameter("content");
			   if(subject == null || subject.length() <= 0 || toEmail == null || toEmail.length() <= 0 ){
				   request.setAttribute("error","Message's to and subject are required field.");
				   response.setPortletMode(PortletMode.EDIT);
				   response.setRenderParameter("type","create");
				   return;
			   }
			   try{
				   	 PortletPreferences portletPreferences = request.getPreferences();
				     String fullName = portletPreferences.getValue("fullName",null);
					 String email = portletPreferences.getValue("email",null);
					 String password = portletPreferences.getValue("password",null);					 
					 GmailClient client = new GmailClient(fullName,email,password);
					 client.sendMessage(toEmail,cc,bcc,subject,content);				     				
				 }catch(Exception e){
					 request.setAttribute("error",e.getMessage());				 
				 }
	       }else if("reply".equals(action)){
	    	   String page = request.getParameter("pageId");
			   if(page == null) page = "1";
			   int pageId = Integer.parseInt(page);
			   String toEmail = request.getParameter("toEmail");
			   String subject = request.getParameter("subject");
			   String content = request.getParameter("content");			   
			   request.setAttribute("toEmail",toEmail);
			   request.setAttribute("subject","Re: "+subject);
			   request.setAttribute("content",content);
			   request.setAttribute("pageId",pageId);
			   response.setPortletMode(PortletMode.EDIT);
			   response.setRenderParameter("type","create");				 
	       }else if("config".equals(action)){	
	    	   String fullName = request.getParameter("fullName");
	    	   String email = request.getParameter("email");
	    	   String password = request.getParameter("password");
	    	   String number = request.getParameter("number");
	    	   if( email == null || email.length() <= 0
	    		 || password == null || password.length() <=0){
				   request.setAttribute("error","Please input all required fields.");
				   request.setAttribute("mode","config");
				   return;
			   }
	    	   PortletPreferences portletPreferences = request.getPreferences();
	    	   portletPreferences.setValue("fullName",fullName);	
			   portletPreferences.setValue("email",email);	
			   portletPreferences.setValue("password",password);
			   portletPreferences.setValue("number",number);
			   portletPreferences.store();
			   request.getPortletSession().removeAttribute("mails",PortletSession.PORTLET_SCOPE);
	       }else if("delete".equals(action)){
	    	   if(request.getParameter("parameter") != null){
				 int index = Integer.parseInt(request.getParameter("parameter"));
				 List<MailBean> mails = (List<MailBean>)request.getPortletSession().getAttribute("mails",PortletSession.PORTLET_SCOPE);
				 PortletPreferences portletPreferences = request.getPreferences();				 
				 String email = portletPreferences.getValue("email",null);
				 String password = portletPreferences.getValue("password",null);				
				 GmailClient client = new GmailClient(email,password);
				 try{
					 client.deleteMessage(mails.get(index).getMsg());
					 request.getPortletSession().removeAttribute("mails",PortletSession.PORTLET_SCOPE);
				 }catch(Exception e){
					 request.setAttribute("error",e.getMessage());				 
				 }
			 }   
	       }else{
				 String showHtmlEditor = request.getParameter("htmlEditor");
				 if(showHtmlEditor != null)
					 request.setAttribute("showHtmlEditor",true);
				 String toEmail = request.getParameter("toEmail");
				 if(toEmail != null){
					 request.setAttribute("toEmail",toEmail);
				 }				 
				 String subject = request.getParameter("subject");
				 if(subject != null){
					 request.setAttribute("subject",subject);
				 }
				 String content = request.getParameter("content");
				 if(content != null){
					 request.setAttribute("content",content);
				 }
				 response.setRenderParameter("type","create");
			}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		 String page = request.getParameter("pageId");
		 if(page == null || page.trim().length()<1) page = "1";
		 int pageId = Integer.parseInt(page);
		 if(request.getPortletSession().getAttribute("mails",PortletSession.PORTLET_SCOPE) == null 
		   || request.getParameter("refresh") != null
		   || request.getParameter("newPage") != null){
			 PortletPreferences portletPreferences = request.getPreferences();	
			 UserProfile userProfile = this.getUserService(request).getUserProfileById(this.getUser(request).getId());
			 String name = null;
			 if(userProfile != null) name = userProfile.getName();
			 if((name == null || name.length() <=0) && this.getUser(request) != null)
				 name = this.getUser(request).getDisplayName();
			 String fullName = portletPreferences.getValue("fullName",name);
			 String email = portletPreferences.getValue("email",null);
			 String password = portletPreferences.getValue("password",null);
			 String number = portletPreferences.getValue("number","10");		
			 if(email == null || password == null){
				 request.setAttribute("number",number);
				 request.setAttribute("fullName",fullName);
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletConfig.jsp").include(request,response);
			 }else{
				 int row = Integer.parseInt(number);
				 if(request.getParameter("refresh") != null) pageId =1;
				 List<MailBean> mails = null;				 
				 GmailClient client = new GmailClient(email,password);
				 try{
					 mails = client.getMessage(EmailClient.SHOW_MESSAGES,pageId,row);
				 }catch(Exception e){
					 request.setAttribute("error",e.getMessage());				 
				 }
				 int count = 0;
				 int unread = 0;				 
				 if(mails.size() > 0){
					 count = mails.get(0).getTotal();
					 unread = mails.get(0).getUnreadCount();
				 }
			     int pages = count / row;
			     if( count % row != 0)
			    	 pages++;		
			     request.getPortletSession().setAttribute("mails",mails,PortletSession.PORTLET_SCOPE);
				 request.getPortletSession().setAttribute("totalCount",count,PortletSession.PORTLET_SCOPE);
				 request.getPortletSession().setAttribute("unreadCount",unread,PortletSession.PORTLET_SCOPE);
				 request.getPortletSession().setAttribute("pages",pages,PortletSession.PORTLET_SCOPE);
				 request.getPortletSession().setAttribute("row",row,PortletSession.PORTLET_SCOPE);
				 request.setAttribute("pageId",pageId);
				 request.setAttribute("totalCount",count);
				 request.setAttribute("unreadCount",unread);
				 request.setAttribute("pages",pages);
				 request.setAttribute("row",row);
				 request.setAttribute("mails",mails);
				
				 if(request.getWindowState().equals(WindowState.MAXIMIZED))
					 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletMaxView.jsp").include(request,response);  
				 else
					 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletView.jsp").include(request,response);				 
			 }
		 }else{
			 if(request.getParameter("mailId") != null){
				 int index = Integer.parseInt(request.getParameter("mailId"));
				 List<MailBean> mails = (List<MailBean>)request.getPortletSession().getAttribute("mails",PortletSession.PORTLET_SCOPE);								 
				 MailBean mail = mails.get(index);
				 if(mail.getContent() == null){
					 PortletPreferences portletPreferences = request.getPreferences();			
					 String email = portletPreferences.getValue("email",null);
					 String password = portletPreferences.getValue("password",null);
					 String number = portletPreferences.getValue("number","10");						 
					 GmailClient client = new GmailClient(email,password);					 
					 try{
						 mail = client.getMessageDetail(mails.get(index).getMsg());		
						 int unread = (Integer)request.getPortletSession().getAttribute("unreadCount",PortletSession.PORTLET_SCOPE);
						 if(mails.get(index).getFlag() == 1) unread--;
						 if(unread < 0) unread = 0;
						 request.getPortletSession().setAttribute("unreadCount",unread,PortletSession.PORTLET_SCOPE);
					 }catch(Exception e){
						 request.setAttribute("error",e.getMessage());				 
					 }
					 mails.set(index,mail);
				 }
				 request.setAttribute("currentMail",mail);				 
				 request.setAttribute("pageId",pageId);
			 }else{
				List<MailBean> mails = (List<MailBean>)request.getPortletSession().getAttribute("mails",PortletSession.PORTLET_SCOPE);
				int row = (Integer)request.getPortletSession().getAttribute("row",PortletSession.PORTLET_SCOPE);
				int count = (Integer)request.getPortletSession().getAttribute("totalCount",PortletSession.PORTLET_SCOPE);
				int unread = (Integer)request.getPortletSession().getAttribute("unreadCount",PortletSession.PORTLET_SCOPE);
				int pages = (Integer)request.getPortletSession().getAttribute("pages",PortletSession.PORTLET_SCOPE);
				List<MailBean> pageMails = new ArrayList<MailBean>();			   
			    request.setAttribute("pageId",pageId);
			    request.setAttribute("totalCount",count);
			    request.setAttribute("unreadCount",unread);
				request.setAttribute("pages",pages);
				request.setAttribute("row",row);
				request.setAttribute("mails",mails);
			 }
			 if(request.getWindowState().equals(WindowState.MAXIMIZED))
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletMaxView.jsp").include(request,response);  
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletView.jsp").include(request,response);			 
		 }
	 }
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		
		 if(request.getParameter("type") != null 
		   && request.getParameter("type").equals("create")){
			 if(request.getWindowState().equals(WindowState.MAXIMIZED))
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletMaxEdit.jsp").include(request,response);  
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletEdit.jsp").include(request,response);			 
		 }else{
			 PortletPreferences portletPreferences = request.getPreferences();	
			 String fullName = portletPreferences.getValue("fullName",null);
			 String email = portletPreferences.getValue("email",null);
			 String password = portletPreferences.getValue("password",null);			 
			 String number = portletPreferences.getValue("number","10");	
			 request.setAttribute("fullName",fullName);
			 request.setAttribute("email",email);
			 request.setAttribute("password",password);
			 request.setAttribute("number",number);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/email/gmailPortletConfig.jsp").include(request,response);
		 }
	 }
}
