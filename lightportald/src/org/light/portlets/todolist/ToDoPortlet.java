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

package org.light.portlets.todolist;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.json.JSONObject;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ToDoPortlet extends LightGenericPortlet {
	 
	public void processAction (ActionRequest request, ActionResponse response) 
   throws PortletException, java.io.IOException {
		String action = request.getParameter("action");		
		if("edit".equals(action)){			
			request.setAttribute("edit", true);			
		}
		else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			ToDoBean todo = this.getUserService(request).getToDoById(Integer.parseInt(id));
			if(todo != null){
				this.getPortalService(request).delete(todo);	
			}
		}		
		else if("save".equals(action)){
		   String ids = request.getParameter("id");	
		   String name = request.getParameter("name");		   
		   if(StringUtil.isEmpty(name)){
			   request.setAttribute("missingField",true);
			   return;
		   }	
		   String desc = request.getParameter("description");				   		   
		   desc = HTMLUtil.disableScripts(desc);
		   String priority = request.getParameter("priority");
		   ToDoBean todo = null;
		   long id = 0;
		   try{
			   id = Long.parseLong(ids);
		   }catch(Exception e){}
		   if(id >0)
			   todo = this.getUserService(request).getToDoById(id);
		   if(todo == null) todo = new ToDoBean();
		   todo.setName(name);
		   todo.setDescription(desc);
		   todo.setUserId(this.getUser(request).getId());
		   todo.setPriority(Integer.parseInt(priority));
		   this.getPortalService(request).save(todo);
		}
		if("changeStatus".equals(action)){
			String id = request.getParameter("name");
			ToDoBean todo = this.getUserService(request).getToDoById(Long.parseLong(id));
			if(todo != null){
			   int status = todo.getStatus();
			   if(status == 0){
				   status = 1;
			   }else{
				   status = 0;
			   }
			   todo.setStatus(status);
			   this.getPortalService(request).save(todo);		
			}
		}
		else if("config".equals(action)){
			String showFinished = request.getParameter("showFinished");
			String items = request.getParameter("items");
			PortletObject portlet =getPortlet(request);		
			if(portlet != null){				
				portlet.setShowNumber(Integer.parseInt(items));
				this.getPortalService(request).save(portlet);
			}
			PortletPreferences portletPreferences = request.getPreferences();
			if(showFinished != null){
				portletPreferences.setValue("showFinished","1");
			}else{
				portletPreferences.setValue("showFinished","0");
			}			
			portletPreferences.store();			
		}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		List<ToDoBean> lists = this.getUserService(request).getToDosByUser(this.getUser(request).getId());
		List<ToDoBean> todoLists = new ArrayList<ToDoBean>();
		PortletObject todoPortlet = getPortlet(request);
		int number =0;
		PortletPreferences portletPreferences = request.getPreferences();
		String showFinished = portletPreferences.getValue("showFinished","0");
		if(todoPortlet != null
			&& request.getWindowState().equals(WindowState.NORMAL)){						
			for(ToDoBean todo : lists )
            {
				if(todoPortlet.getShowNumber() > 0 &&  number >= todoPortlet.getShowNumber()){
					request.setAttribute("showMore",true);
					break;	
				}					
				if(showFinished.equals("1") || todo.getStatus() == 0){						 
					todoLists.add(todo);
					number++;
				}	
            }	
    	}else{
    		todoLists = lists;
    	}
		request.setAttribute("todoLists", todoLists);
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/todo/todoPortletView.jsp").include(request,response);  
				
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {   
		 if(request.getParameter("add") != null){			 
			 ToDoBean todo = new ToDoBean(null,null,this.getUser(request).getId(),1);							
			 request.setAttribute("todo", todo);	
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/todo/todoPortletEdit.jsp").include(request,response);
		 }else{
			String id = request.getParameter("parameter");
			if(id != null){
				ToDoBean todo = this.getUserService(request).getToDoById(Integer.parseInt(id));							
				request.setAttribute("todo", todo);				
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/todo/todoPortletEdit.jsp").include(request,response);
			}else{
				PortletObject portlet = this.getPortlet(request);
				int showNumber = portlet.getShowNumber();
				if(showNumber <=0) showNumber = 6;
				request.setAttribute("showNumber",showNumber);
				PortletPreferences portletPreferences = request.getPreferences();
				String showFinished = portletPreferences.getValue("showFinished","0");
				request.setAttribute("showFinished",showFinished);
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/todo/todoPortletConfig.jsp").include(request,response);	 
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
			 int count = getUserService(request).getUserToDoCount(user.getId());
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
