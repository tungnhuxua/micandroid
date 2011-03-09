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

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlockUserPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("unblock".equals(action)){
		   String blockId = request.getParameter("blockId");
		   if(blockId != null){
			   UserBlock block= this.getUserService(request).getUserBlockById(Integer.parseInt(blockId));
			   if(block != null){				   				   
				   this.getPortalService(request).delete(block);
				   request.setAttribute("success", "This User has been unblocked successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this user.");		 
		   }else
			   request.setAttribute("error", "Please pick a User to unblock.");		
	   }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
	     if(this.getVisitedUser(request) != null)
	    	 user = this.getVisitedUser(request);
		 List<UserBlock> userBlocks = this.getUserService(request).getUserBlocks(user.getId());
		 int blockCount = 0;
		 if(userBlocks != null)
			 blockCount = userBlocks.size();
		 request.setAttribute("blockCount",blockCount);
		 request.setAttribute("userBlocks",userBlocks);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/blockPortletView.jsp").include(request,response);  
			
	 }	


}
