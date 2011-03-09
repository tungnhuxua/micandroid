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
import org.light.portal.model.UserMusic;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.FileUtil;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyMusicPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");		
		if("background".equals(action)){
		   String musicId = request.getParameter("musicId");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){				   
				   User user = this.getUser(request);
				   user.setMusicUrl(music.getMusicUrl());
				   this.getUserService(request).save(user);
				   request.setAttribute("success", "Your background Music has been set successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
	    }
		else if("ring".equals(action)){
		   String musicId = request.getParameter("musicId");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){				   
				   User user = this.getUser(request);
				   user.setRingToneUrl(music.getMusicUrl());
				   this.getUserService(request).save(user);
				   request.setAttribute("success", "Your Instant Message Ring Tone has been set successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
	    }
		else if("select".equals(action)){
		   String musicId = request.getParameter("musicId");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){				   
				   request.setAttribute("music",music);
				   response.setPortletMode(PortletMode.EDIT);
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
		}
		else if("config".equals(action)){
			config(request);
		}
	    else if("apply".equals(action)){
			config(request);					
			String status = request.getParameter("status");
			User user = this.getUser(request);
			if(user != null){				
				this.getUserService(request).updateMusicStatus(user.getId(),Integer.parseInt(status));
			}			
		}
	    else if("save".equals(action)){
		   String musicId = request.getParameter("musicId");
		   String caption = request.getParameter("caption");
		   String status = request.getParameter("status");
		   String rankable = request.getParameter("rankable");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){		
				   if(rankable != null && "1".equals(rankable))
					   music.setRankable(1);
				   else
					   music.setRankable(0);
				   music.setCaption(caption);
				   music.setStatus(Integer.parseInt(status));
				   this.getPortalService(request).save(music);				   			  
				   request.setAttribute("success", "This music's caption has been saved successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }
	    }
	    else if("rank".equals(action)){
			String musicId = request.getParameter("musicId");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){				   
				   music.setRankable(1);
				   this.getPortalService(request).save(music);
				   request.setAttribute("success", "This music has been set to rank successfully, it's ready for public to rank.");
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
	    }
	    else if("play".equals(action)){
			String musicId = request.getParameter("parameter");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){				   
				   request.setAttribute("musicUrl",music.getMusicUrl());
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
	    }
	    else if("stop".equals(action)){
			request.removeAttribute("musicUrl");	
	    }
	    else if("delete".equals(action)){
			String musicId = request.getParameter("musicId");
		   if(musicId != null){
			   UserMusic music= this.getUserService(request).getUserMusicById(Integer.parseInt(musicId));
			   if(music != null){
				   User user = this.getUser(request);
				   if(music.getMusicUrl().equals(user.getMusicUrl())){
					   user.setMusicUrl(null);
					   this.getUserService(request).save(user);
				   }
				   if(music.getMusicUrl().equals(user.getRingToneUrl())){
					   user.setRingToneUrl(null);
					   this.getUserService(request).save(user);
				   }
				   FileUtil.deleteFile(music.getMusicUrl(),OrganizationThreadLocal.getOrganizationId());				   
				   this.getPortalService(request).delete(music);
				   request.setAttribute("success", "This music has been delete successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this music.");		 
		   }else
			   request.setAttribute("error", "Please pick a music first.");	
	    }
	    else if("addUrl".equals(action)){
			String musicUrl = request.getParameter("musicUrl");
			if(musicUrl == null ||"".equals(musicUrl) ){
				request.setAttribute("error", "Please input music URL.");
				request.setAttribute("add","true");
				response.setPortletMode(PortletMode.EDIT);
				return;
	        }
			String musicCaption = request.getParameter("musicCaption");
			if(musicCaption == null) 			
				musicCaption = musicUrl;
			User user = this.getUser(request);
			int status = 0;			
			if(user != null) status = user.getDefaultMusicStatus();
			UserMusic music = new UserMusic(user.getId(),musicUrl,musicCaption,status);			
			this.getPortalService(request).save(music);	
	   }
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 long userId=0;
		 if(user != null) userId= user.getId();
		 List<UserMusic> userMusics = null;
	     if(this.getVisitedUser(request) != null)
	    	 userMusics = this.getUserService(request).getVisitedUserMusics(userId,this.getVisitedUser(request).getId());
		 else
			 userMusics = this.getUserService(request).getUserMusics(userId);
		 int musicCount =userMusics.size();
		 
		 if(!request.getWindowState().equals(WindowState.MAXIMIZED)){
			 PortletObject portlet =getPortlet(request);		 
			 int showNumber = 4;
			 List<UserMusic> showMusics = new ArrayList<UserMusic>();
			 if(portlet != null && portlet.getShowNumber() > 0){
				 showNumber = portlet.getShowNumber();
			 }
			 if(musicCount > showNumber)
				 request.setAttribute("showMore",true);
			 for(int i=0;i<userMusics.size();i++){
				 if(i>=showNumber) break;
				 showMusics.add(userMusics.get(i));
			 }			 
			 request.setAttribute("userMusics",showMusics);
		 }else{
			 request.setAttribute("userMusics",userMusics);
		 }
		 request.setAttribute("musicCount",musicCount);
		 
		 if(request.getWindowState().equals(WindowState.MAXIMIZED))
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myMusicPortletMaxView.jsp").include(request,response);  
		 else
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myMusicPortletView.jsp").include(request,response);  
			
	 }	
    
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 if(request.getParameter("add") != null || request.getAttribute("add") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myMusicPortletAdd.jsp").include(request,response);  		 
		 else if(request.getAttribute("music") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myMusicPortletEdit.jsp").include(request,response);
		 else{
			 PortletObject portlet = this.getPortlet(request);
			 int showNumber = 0;
			 if(portlet != null)
			    showNumber = portlet.getShowNumber();
			 if(showNumber <=0) showNumber = 6;
			 request.setAttribute("showNumber",showNumber);
			 String defaultStatus = "0";
			 User user = this.getUser(request);
			 if(user != null) defaultStatus = String.valueOf(user.getDefaultMusicStatus());
			 request.setAttribute("defaultStatus",Integer.parseInt(defaultStatus));
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myMusicPortletConfig.jsp").include(request,response);
		 }					
	 }	

	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count = getUserService(request).getUserMusicCount(user.getId());
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
			user.setDefaultMusicStatus(Integer.parseInt(status));
			this.getUserService(request).save(user);
		}
	}
}
