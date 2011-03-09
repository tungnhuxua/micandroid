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

package org.light.portlets.bulletin;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.json.JSONObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class BulletinPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
		   String subject = request.getParameter("subject");
		   String content = request.getParameter("content");
		   if(subject == null || subject.length() <= 0 ){
			   request.setAttribute("error","Bulletin's subject is required field.");
			   response.setPortletMode(PortletMode.EDIT);
			   return;
		   }
		   content = HTMLUtil.disableScripts(content);
		   Bulletin bulletin = null;
		   if(this.getUser(request) != null){
			   if(this.getVisitedGroup(request) != null){
				   bulletin = new Bulletin(this.getVisitedGroup(request).getId(),subject,content,this.getUser(request).getId());
			   }else{
				   bulletin = new Bulletin(subject,content,this.getUser(request).getId());
			   }
		   }else{
			   request.setAttribute("error","Please login first.");
			   response.setPortletMode(PortletMode.EDIT);
			   return;
		   }
		   
		   this.getUserService(request).sendBulletin(bulletin);
		}	
		else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			Bulletin bulletin = this.getUserService(request).getBulletinById(Integer.parseInt(id));
			if(bulletin != null){
				this.getPortalService(request).delete(bulletin);	
			}
		}
		else{
			 String showHtmlEditor = request.getParameter("htmlEditor");
			 if(showHtmlEditor != null)
				 request.setAttribute("showHtmlEditor",true);
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
		 String bulletinId = request.getParameter("bulletinId");
		 if(bulletinId != null){
			 Bulletin bulletin = this.getUserService(request).getBulletinById(Integer.parseInt(bulletinId));
			 request.setAttribute("bulletin",bulletin);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bulletin/bulletinPortletDetailView.jsp").include(request,response);
			 return;
		 }
		 User user = this.getUser(request);
	     if(this.getVisitedUser(request) != null)
	    	 user = this.getVisitedUser(request);
		 List<Bulletin> bulletins = null;
		 if(this.getVisitedGroup(request) != null)
			 bulletins = this.getUserService(request).getBulletinsByOrg(this.getVisitedGroup(request).getId());
		 else
			 bulletins = this.getUserService(request).getBulletinsByUser(user.getId());
		 request.setAttribute("bulletins",bulletins);
		 if(this.getVisitedGroup(request) != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bulletin/groupBulletinPortletView.jsp").include(request,response);			 
		 else
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bulletin/bulletinPortletView.jsp").include(request,response);
	 }	

	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bulletin/bulletinPortletEdit.jsp").include(request,response);
	 }
	 
	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count=this.getUserService(request).getUserBulletinCount(user.getId());
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
}
