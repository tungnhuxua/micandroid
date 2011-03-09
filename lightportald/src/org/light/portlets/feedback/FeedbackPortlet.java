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

package org.light.portlets.feedback;

import static org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class FeedbackPortlet extends LightGenericPortlet {
  
 public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
	  String action = request.getParameter("action");
	  if("save".equals(action)){
	     String subject = request.getParameter("subject");
	     String content = request.getParameter("content"); 
	     if(subject == null || subject.length() <= 0 ){
	      request.setAttribute("error","Feedback's subject is required field.");
	      response.setPortletMode(PortletMode.EDIT);
	      return;
	     }
	     Feedback feedback = new Feedback(this.getUser(request).getId(),subject,content,this.getUser(request).getUserId(),OrganizationThreadLocal.getOrganizationId());
	     this.getPortalService(request).save(feedback);
	  }  
	  else if("delete".equals(action)){
		String id = request.getParameter("parameter");
		Feedback feedback = this.getUserService(request).getFeedbackById(Long.parseLong(id),OrganizationThreadLocal.getOrganizationId());
		if(feedback != null){
			this.getPortalService(request).delete(feedback);	
		}
	  }
	  else if("deleteComment".equals(action)){
		String id = request.getParameter("parameter");
		this.getUserService(request).deleteUserCommentsById(Long.parseLong(id));
	  }
 }
  
  protected void doView (RenderRequest request, RenderResponse response)
    throws PortletException, java.io.IOException
  {
	  String feedbackId = request.getParameter("feedbackId");
	  if(feedbackId == null){
		   List<Feedback> list = this.getUserService(request).getFeedback(OrganizationThreadLocal.getOrganizationId());		   
		   request.setAttribute("feebackLists",list);
		   this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/feedback/feedbackPortletView.jsp").include(request,response);
	  }else{
		  Feedback feedback = this.getUserService(request).getFeedbackById(Long.parseLong(feedbackId),OrganizationThreadLocal.getOrganizationId());
		  request.setAttribute("feedback",feedback);
		  if(feedback != null && feedback.getCommentCount() > 0){
				 List<UserComment> comments = this.getUserService(request).getCommentsByType(feedback.getId(),_OBJECT_TYPE_FEEDBACK);
				 request.setAttribute("comments",comments);
			 }		 
		  this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/feedback/feedbackPortletDetail.jsp").include(request,response);
	  }
  } 
  
  protected void doEdit (RenderRequest request, RenderResponse response)
    throws PortletException, java.io.IOException
  {     
   this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/feedback/feedbackPortletEdit.jsp").include(request,response);
  } 
  
  protected void doHelp (RenderRequest request, RenderResponse response)
    throws PortletException, java.io.IOException
  {     
   this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/feedback/feedbackPortletHelp.jsp").include(request,response);
  } 
}