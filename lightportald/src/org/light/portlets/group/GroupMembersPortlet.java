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

package org.light.portlets.group;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupMembersPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
	    String action = request.getParameter("action");		
				
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		String groupId= request.getParameter("groupId");
		long id = 0L;
		if(groupId != null){
			 try{
				 id = Long.parseLong(groupId);
			 }catch(NumberFormatException e){}
		}
		Group group = null;
		if(id > 0){
			group = this.getGroupService(request).getGroupById(id);
		}
		if(group == null){
			group = this.getVisitedGroup(request);
		}		
		if(group != null){
			List<UserGroup> groupMembers = this.getGroupService(request).getUsersByGroup(group.getId());
			int memberCount = 0;
			if(groupMembers != null) memberCount = groupMembers.size();
			request.setAttribute("memberCount", memberCount);
			request.setAttribute("groupMembers", groupMembers);
			User user = this.getUserService(request).getUserById(group.getLeaderId());	  
			request.setAttribute("moderator",user);
			if(request.getWindowState().equals(WindowState.MAXIMIZED)){
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPortletMembersMaxView.jsp").include(request,response);
			}else{
				if(memberCount > 9){
					List<UserGroup> shows = new ArrayList<UserGroup>();
					for(int i=0;i<9;i++){
						shows.add(groupMembers.get(i));
					}
					request.setAttribute("groupMembers", shows);
				}
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPortletMembers.jsp").include(request,response);
			}
		}else{
			request.setAttribute("error", "System cannot find this group.");	
			this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/error/error.jsp").include(request,response);
		}
	}	
	 
}

