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

package org.light.portlets.chat;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.internal.InternalNews;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChattingRoomPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
	    String action = request.getParameter("action");
		if("save".equals(action)){
		   String name = request.getParameter("name");
		   String desc = request.getParameter("desc");
		   if(name == null ||"".equals(name) ){
	        	  request.setAttribute("error", "Please input chat room name.");
	              return;
		   }
		   String chattingId = request.getParameter("chattingId");
		   long id = 0;
		   try{
			   id = Long.parseLong(chattingId);			   
		   }catch(Exception e){}
		   Chatting chatting = null;
		   if(id > 0)
			   chatting = this.getChatService(request).getChattingById(id);
		   if(chatting == null) 
			   chatting = new Chatting(name,desc,this.getUser(request).getId(),this.getUser(request).getOrgId());
		   else{
			   chatting.setName(name);
			   chatting.setDesc(desc);
		   }
		   this.getChatService(request).save(chatting);
		   response.setPortletMode(PortletMode.VIEW);
		}
		else if("edit".equals(action)){
			String id = request.getParameter("parameter");
			Chatting chatting = this.getChatService(request).getChattingById(Long.parseLong(id));
			if(chatting != null){
				request.setAttribute("chattingId",chatting.getId());
				request.setAttribute("name",chatting.getName());
				request.setAttribute("desc",chatting.getDesc());
			}
		}
		else if("delete".equals(action)){
			String id = request.getParameter("parameter");
			this.getChatService(request).deleteChatRoom(Long.parseLong(id));			
		}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException{	  
		Long orgId = OrganizationThreadLocal.getOrganizationId();
		if(this.getVisitedGroup(request) != null) orgId = this.getVisitedGroup(request).getId();
		List<Chatting> chattings = this.getChatService(request).getChattingByOrgId(orgId);		
		request.setAttribute("chattings",chattings);
		request.setAttribute("total",chattings.size());
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/chat/chattingRoomPortletView.jsp").include(request,response);
	 }
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException{	  
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/chat/chattingRoomPortletEdit.jsp").include(request,response);
	 }
}