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

package org.light.portlets.message;

import static org.light.portal.util.Constants.*;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.StringUtil;
import org.light.portlets.connection.Connection;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.group.UserGroup;


/**
 * 
 * @author Jianmin Liu
 **/
public class MessagePortlet extends LightGenericPortlet {
		 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
		   String to = request.getParameter("to");
		   String subject = request.getParameter("subject");
		   String content = request.getParameter("content");
		   String sformat = request.getParameter("format");
		   if(subject == null || subject.length() <= 0 || to == null || to.length() <= 0 ){
			   request.setAttribute("error","Message's to and subject are required field.");
			   response.setPortletMode(PortletMode.EDIT);
			   return;
		   }
		   if(this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()){
			   request.setAttribute("error","Session expired, please login again.");
			   response.setPortletMode(PortletMode.EDIT);
			   return;
		   }
		   long toUserId = 0;
		   if(to != null) toUserId = Long.parseLong(to);	
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
		   User user = this.getUser(request);
		   Message message = new Message(subject,content,toUserId,user.getId(),user.getOrgId(),format);			   
		   this.getUserService(request).sendMessage(message);
		   Message sent = new Message(subject,content,toUserId,user.getId(),user.getOrgId(),format,1);			   
		   this.getUserService(request).sendMessage(sent);
		}
		else if("reply".equals(action)){
			response.setPortletMode(PortletMode.EDIT);
		}
		else if("forward".equals(action)){
			response.setPortletMode(PortletMode.EDIT);
		}
		else if("approve".equals(action)){
			String id = request.getParameter("parameter");
			Message message = this.getUserService(request).getMessageById(Integer.parseInt(id));
			if(message != null){
				//group
				if(message.getEvent() ==_MESSAGE_EVENT_GROUP){
				  //invite me
				  if(message.getType() ==_MESSAGE_EVENT_TYPE_INVITE){
					  UserGroup userGroup = new UserGroup(this.getUser(request).getId(),message.getEventId());
					  this.getPortalService(request).save(userGroup);
				  }
				  //member request
				  if(message.getType() ==_MESSAGE_EVENT_TYPE_REQUEST){
					  UserGroup userGroup = new UserGroup(message.getPostById(),message.getEventId());
					  this.getPortalService(request).save(userGroup);
				  }
				}
				//TODO:calendar
				if(message.getEvent() ==_MESSAGE_EVENT_CALENDAR){
					
				}
				//comments
				if(message.getEvent() ==_MESSAGE_EVENT_COMMENT){
					UserComment comments = this.getUserService(request).getUserCommentsById(message.getEventId());
					comments.setStatus(_STATUS_APPROVED);
					this.getPortalService(request).save(comments);
				}
				//friend request
				if(message.getEvent() ==_MESSAGE_EVENT_CONNECTION){
					this.getUserService(request).approveConnection(message);
				}
				//forum category
				if(message.getEvent() ==_MESSAGE_EVENT_FORUM_CATEGORY){
					ForumCategory forumCategory = this.getForumService(request).getForumCategoryById(message.getEventId());
					forumCategory.setStatus(1);
					this.getPortalService(request).save(forumCategory);
				}
				//forum sub category
				if(message.getEvent() ==_MESSAGE_EVENT_FORUM_SUB_CATEGORY){
					Forum forumSubCategory = this.getForumService(request).getForumById(message.getEventId());
					forumSubCategory.setStatus(1);
					this.getPortalService(request).save(forumSubCategory);
				}
				
				this.getPortalService(request).delete(message);	
			}
		}
		else if("deny".equals(action)){
			String id = request.getParameter("parameter");
			Message message = this.getUserService(request).getMessageById(Integer.parseInt(id));
			if(message != null){
				//group
				if(message.getEvent() ==_MESSAGE_EVENT_GROUP){
				  //invite me
				  if(message.getType() ==_MESSAGE_EVENT_TYPE_INVITE){
					  
				  }
				  //member request
				  if(message.getType() ==_MESSAGE_EVENT_TYPE_REQUEST){
					  
				  }
				}
				//TODO:calendar
				if(message.getEvent() ==_MESSAGE_EVENT_CALENDAR){
						
				}
				//comments
				if(message.getEvent() ==_MESSAGE_EVENT_COMMENT){
					UserComment comments = this.getUserService(request).getUserCommentsById(message.getEventId());						
					comments.setStatus(_STATUS_NOT_APPROVED);
					this.getPortalService(request).save(comments);
				}
				//friend request
				if(message.getEvent() ==_MESSAGE_EVENT_CONNECTION){
					
				}
				//forum category
				if(message.getEvent() ==_MESSAGE_EVENT_FORUM_CATEGORY){
					ForumCategory forumCategory = this.getForumService(request).getForumCategoryById(message.getEventId());
					this.getPortalService(request).delete(forumCategory);
				}
				//forum sub category
				if(message.getEvent() ==_MESSAGE_EVENT_FORUM_SUB_CATEGORY){
					Forum forumSubCategory = this.getForumService(request).getForumById(message.getEventId());
					this.getPortalService(request).delete(forumSubCategory);
				}
				
				this.getPortalService(request).delete(message);	
			}				
		}
		else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			Message message = this.getUserService(request).getMessageById(Integer.parseInt(id));
			if(message != null){
				this.getPortalService(request).delete(message);	
			}
		}
		else{
			 String showHtmlEditor = request.getParameter("htmlEditor");
			 if(showHtmlEditor != null)
				 request.setAttribute("showHtmlEditor",true);
			 String to = request.getParameter("to");
			 if(to != null){
				 request.setAttribute("toUser",to);
			 }
			 String toUserName = request.getParameter("toUserName");
			 if(toUserName != null){
				 request.setAttribute("toUserName",toUserName);
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
		 String messageId = request.getParameter("messageId");
		 String type = request.getParameter("type");
		 if(StringUtil.isEmpty(type))			    
			 type = "in";
		 request.setAttribute("type",type);
		 
		 if(messageId != null){
			 Message message = this.getUserService(request).getMessageById(Long.parseLong(messageId));
			 request.setAttribute("message",message);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/message/messagePortletDetailView.jsp").include(request,response);
			 return;
		 }
		 User user = this.getUser(request);		     
		 			 
		 List<Message> messages = null;
		 if("sent".equals(type))
		     messages =this.getUserService(request).getMessagesBySender(user.getId());
		 else if("connection".equals(type))
		     messages =this.getUserService(request).getConnectionRequestMessagesByUser(user.getId());
		 else{
			 messages =this.getUserService(request).getMessagesByUser(user.getId()); 
		 }				 
		 request.setAttribute("messages",messages);
		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/message/messagePortletView.jsp").include(request,response);
	 }	

	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {   
		 String subject = (String)request.getAttribute("subject");			 
		 String content = (String)request.getAttribute("content");
		 if(request.getParameter("visit") != null && this.getVisitedUser(request) != null){
			User user = this.getVisitedUser(request);
			request.setAttribute("toUser",user.getId());
			request.setAttribute("toUserName",user.getDisplayName());
		 }
		 if(request.getParameter("member") != null && request.getParameter("userId") != null){
				User user = this.getUserService(request).getUserById(Long.parseLong(request.getParameter("userId")));
				request.setAttribute("toUser",user.getId());
				request.setAttribute("toUserName",user.getDisplayName());
			 }
		 if(request.getParameter("forward") != null && this.getVisitedUser(request) != null){
			User user = this.getVisitedUser(request);
			subject="Please take a look at my friend's profile";
			content="Check this link: <a href=\"http://www.myportal.com/person/"+user.getUri()+"\">http://www.myportal.com/person/"+user.getUri()+"</a>";				
		 }
		 if(request.getParameter("parameter") != null){
			String action = request.getParameter("action"); 
		    String id = request.getParameter("parameter");
			Message message = this.getUserService(request).getMessageById(Integer.parseInt(id));
			if("reply".equals(action)){
				request.setAttribute("toUser",message.getPostById());
				request.setAttribute("toUserName",message.getFromDisplayName());					
				subject="Re: "+message.getSubject();
				content = message.getContent();
			}else if("forward".equals(action)){					
				subject="Fwd: "+message.getSubject();
				content = message.getContent();
			}else{
			}
		 }	
		 List<Connection> buddys = this.getConnectionService(request).getBuddysByUser(this.getUser(request).getId());
		 request.setAttribute("buddys",buddys); 	
		 request.setAttribute("subject",subject);
		 request.setAttribute("content",content);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/message/messagePortletEdit.jsp").include(request,response);
	 }

}
