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
import static org.light.portal.util.Constants._MAX_ROW_PER_CHAT;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChattingPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
	    String action = request.getParameter("action");
		if("send".equals(action)){
			   String chatting = request.getParameter("chat");
			   String displayName = this.getUser(request).getDisplayName();
			   String id = request.getParameter("chattingId");
			   int chattingId = Integer.parseInt(id);
			   if(chatting != null) {
				   ChattingRecord record = new ChattingRecord(chattingId, this.getUser(request).getId(), displayName, chatting);				  
				   this.getChatService(request).save(record);
			   }
		}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException{	  
		String id = request.getParameter("chattingId");
	    long chattingId = 0L;
		try{
			chattingId = Long.parseLong(id);
		}catch(Exception e){}
		if(chattingId > 0){
			List<ChattingRecord> records = this.getChatService(request).getChattingRecordsById(chattingId,_MAX_ROW_PER_CHAT);	
			String displayName = this.getUser(request).getUserId();
			request.setAttribute("chattingId",chattingId);
			request.setAttribute("displayName",displayName);
			request.setAttribute("records",records);
			this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/chat/chattingPortletView.jsp").include(request,response);
		}
	 }	
}