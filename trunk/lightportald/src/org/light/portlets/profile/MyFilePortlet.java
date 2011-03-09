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

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.json.JSONObject;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.model.UserFile;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.FileUtil;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyFilePortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");				
		if("select".equals(action)){
			   String fileId = request.getParameter("fileId");
			   if(fileId != null){
				   UserFile file= this.getUserService(request).getUserFileById(Integer.parseInt(fileId));
				   if(file != null){				   
					   request.setAttribute("file",file);
					   response.setPortletMode(PortletMode.EDIT);
				   }else
					   request.setAttribute("error", "System cannot find this file.");		 
			   }else
				   request.setAttribute("error", "Please pick a file first.");	
		}
		else if("config".equals(action)){
			config(request);
		}
	    else if("apply".equals(action)){
			config(request);					
			String status = request.getParameter("status");
			User user = this.getUser(request);
			if(user != null){				
				this.getUserService(request).updateFileStatus(user.getId(),Integer.parseInt(status));
			}			
		}
	    else if("save".equals(action)){
		   String fileId = request.getParameter("fileId");
		   String caption = request.getParameter("caption");
		   String status = request.getParameter("status");
		   if(fileId != null){
			   UserFile file= this.getUserService(request).getUserFileById(Integer.parseInt(fileId));
			   if(file != null){				   
				   file.setCaption(caption);
				   file.setStatus(Integer.parseInt(status));
				   this.getPortalService(request).save(file);				   			  
				   request.setAttribute("success", "This file's caption has been saved successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this file.");		 
		   }
	    }
	    else if("delete".equals(action)){
			String fileId = request.getParameter("fileId");
		   if(fileId != null){
			   UserFile file= this.getUserService(request).getUserFileById(Integer.parseInt(fileId));
			   if(file != null){
				   FileUtil.deleteFile(file.getFileUrl(),OrganizationThreadLocal.getOrganizationId());				   
				   this.getPortalService(request).delete(file);
				   request.setAttribute("success", "This file has been delete successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this file.");		 
		   }else
			   request.setAttribute("error", "Please pick a file first.");	
	   }
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 long userId=0;
		 if(user != null) userId= user.getId();
		 List<UserFile> userFiles = null;
	     if(this.getVisitedUser(request) != null)
	    	 userFiles = this.getUserService(request).getVisitedUserFiles(userId,this.getVisitedUser(request).getId());
		 else
			 userFiles = this.getUserService(request).getUserFiles(userId);
		 int fileCount =userFiles.size();
		 
		 if(!request.getWindowState().equals(WindowState.MAXIMIZED)){
			 PortletObject portlet =getPortlet(request);		 
			 int showNumber = 4;
			 List<UserFile> showFiles = new ArrayList<UserFile>();
			 if(portlet != null && portlet.getShowNumber() > 0){
				 showNumber = portlet.getShowNumber();
			 }
			 for(int i=0;i<userFiles.size();i++){
				 if(i>=showNumber) break;
				 showFiles.add(userFiles.get(i));
			 }

			 request.setAttribute("userFiles",showFiles);
		 }else{
			 request.setAttribute("userFiles",userFiles);
		 }
		 request.setAttribute("fileCount",fileCount);
		 
		 if(request.getWindowState().equals(WindowState.MAXIMIZED))
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myFilePortletMaxView.jsp").include(request,response);  
		 else
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myFilePortletView.jsp").include(request,response);  
			
	 }	
    
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		 
		 if(request.getParameter("add") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myFilePortletEdit.jsp").include(request,response);  		 
		 else if(request.getAttribute("file") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myFilePortletEdit.jsp").include(request,response);
		 else{
			 PortletObject portlet = this.getPortlet(request);
			 int showNumber = 0;
			 if(portlet != null)
			    showNumber = portlet.getShowNumber();
			 if(showNumber <=0) showNumber = 6;
			 request.setAttribute("showNumber",showNumber);
			 String defaultStatus = "0";
			 User user = this.getUser(request);
			 if(user != null) defaultStatus = String.valueOf(user.getDefaultFileStatus());
			 request.setAttribute("defaultStatus",Integer.parseInt(defaultStatus));
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myFilePortletConfig.jsp").include(request,response);
		 }	
	 }	
	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count = getUserService(request).getUserFileCount(user.getId());
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

	 private void config(ActionRequest request) throws PortletException, java.io.IOException{
		 String items = request.getParameter("items");
		 PortletObject portlet =getPortlet(request);		
		 if(portlet != null){			
			portlet.setShowNumber(Integer.parseInt(items));
			this.getPortalService(request).save(portlet);
		 }			
		 String status = request.getParameter("status");
		 User user = this.getUser(request);
		 if(user != null){
			user.setDefaultFileStatus(Integer.parseInt(status));
			this.getUserService(request).save(user);
		 }
	 }

}
