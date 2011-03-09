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

package org.light.portlets.rank;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.User;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserPictureRank;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class RankPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("rank".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   String score = request.getParameter("score");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
			   if(picture != null){				   
				   UserPictureRank rank = new UserPictureRank(picture.getUserId(),picture.getId(),Integer.parseInt(score),this.getUser(request).getId());
				   this.getPortalService(request).save(rank);
				   request.setAttribute("success", "This picture has been ranked successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture to rank.");		
	   }
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 List<UserPicture> userPictures = null;
	     if(this.getVisitedUser(request) != null)
			 userPictures = this.getUserService(request).getUserRankPictures(this.getVisitedUser(request).getId());
		 else
		     userPictures = this.getUserService(request).getUserRankPictures(user.getId());
		 int pictureCount =0;		 
		 if(userPictures != null && userPictures.size() > 0) {
			 pictureCount=userPictures.size();			
		 }

		 request.setAttribute("pictureCount",pictureCount);
		 request.setAttribute("userPictures",userPictures);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/rank/rankPortletView.jsp").include(request,response);  
			
	 }	
    
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		//this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/rank/rankPortletEdit.jsp").include(request,response);  
			
	 }	


}

