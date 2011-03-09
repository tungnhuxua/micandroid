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

import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;
import static org.light.portal.util.Constants._USER_PATH;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.Organization;
import org.light.portal.model.PicturePositionTag;
import org.light.portal.model.Portal;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.model.UserPicture;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.FileUtil;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.OrganizationThreadLocal;


/**
 * 
 * @author Jianmin Liu
 **/
public class GroupPicturePortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("addUrl".equals(action)){
			String pictureUrl = request.getParameter("pictureUrl");
			if(pictureUrl == null ||"".equals(pictureUrl) ){
				request.setAttribute("error", "Please input picture URL.");
				request.setAttribute("add","true");
				response.setPortletMode(PortletMode.EDIT);
				return;
	        }
			User user = this.getUser(request);
			URL url = new URL(pictureUrl);
			java.awt.image.BufferedImage image=javax.imageio.ImageIO.read(url);
			int width = 300;
			int height= 280;
			if(image != null){
				width = image.getWidth();
				height= image.getHeight();
			}
			UserPicture picture = new UserPicture(user.getId(),this.getVisitedGroup(request).getId(),user.getOrgId(),pictureUrl,_PRIVACY_PUBLIC,width,height);			
			this.getPortalService(request).save(picture);	
			List<UserPicture> userPictures = (List<UserPicture>)request.getPortletSession().getAttribute("myPictures",PortletSession.PORTLET_SCOPE);
		    if(userPictures == null)
		    {
		    	userPictures= new ArrayList<UserPicture>();
		    }
			userPictures.add(picture);
			request.getPortletSession().setAttribute("myPictures", userPictures, PortletSession.PORTLET_SCOPE);
	   }
		else if("profile".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){
				   boolean flag = true;
				   if(!picture.getPictureUrl().startsWith("http")){
					   int index = picture.getPictureUrl().indexOf("/"+_USER_PATH+"/");
					   String path = _FILE_PATH+OrganizationThreadLocal.getOrganizationId()+picture.getPictureUrl().substring(index);
					   if(!ImageUtil.saveThumb(path)) flag = false;
				   }	   
				   if(flag){
					   Group group = this.getVisitedGroup(request);
					   if(group != null){
						   group.setPhotoUrl(picture.getPictureUrl());
						   group.setPhotoWidth(picture.getPictureWidth());
						   group.setPhotoHeight(picture.getPictureHeight());
						   group.setCaption(picture.getCaption());
						   this.getPortalService(request).save(group);
						   request.setAttribute("success", "This Group profile's defalut photo has been set successfully, please refresh Profile window to see the new setting.");
					   }else
						   request.setAttribute("error", "System cannot find this group.");	
				   }else
				    	request.setAttribute("error", "System cannot set this picture as group picture.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		   
		   }else
			   request.setAttribute("error", "Please pick a picture first.");				   
		}
		else if("background".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){
				   if(this.getVisitedGroup(request) != null){
					   Organization org = this.getUserService(request).getOrgById(this.getVisitedGroup(request).getId());					   
					   Portal portal = this.getPortalService(request).getPortalByUser(org.getUser().getUserId(),org.getId());
					   portal.setBgImage(picture.getPictureUrl());
					   portal.setBgPosition("left top");
					   portal.setBgRepeat(1);
					   portal.setHeaderImage("no");
					   portal.setTransparent(0);
					   this.getPortalService(request).save(portal);
					   request.setAttribute("success", "Your portal's background has been set successfully, please refresh portal to see the new setting.");
				   }
				   else
					   request.setAttribute("error", "group missing.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");	
	   }
	   else if("header".equals(action)){
			   String pictureId = request.getParameter("pictureId");
			   if(pictureId != null){
				   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
				   if(picture != null){				   
					   if(this.getVisitedGroup(request) != null){
						   Organization org = this.getUserService(request).getOrgById(this.getVisitedGroup(request).getId());					   
						   Portal portal = this.getPortalService(request).getPortalByUser(org.getUser().getUserId(),org.getId());
						   portal.setHeaderImage(picture.getPictureUrl());
						   portal.setHeaderPosition("left top");
						   portal.setHeaderRepeat(1);
						   this.getPortalService(request).save(portal);
						   request.setAttribute("success", "Your portal's header picture has been set successfully, please refresh portal to see the new setting.");
					   }else
						   request.setAttribute("error", "group missing.");
				   }else
					   request.setAttribute("error", "System cannot find this picture.");		 
			   }else
				   request.setAttribute("error", "Please pick a picture first.");	
		   }
		else if("edit".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){				   
				   request.setAttribute("picture",picture);
				   response.setPortletMode(PortletMode.EDIT);
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");	
		}
		else if("save".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   String caption = request.getParameter("caption");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){				   
				   picture.setCaption(caption);
				   this.getPortalService(request).save(picture);				   
				   Group group = this.getVisitedGroup(request);
				   if(group != null && picture.getPictureUrl().equals(group.getPhotoUrl())){
					   group.setCaption(caption);
					   this.getPortalService(request).save(group);
				   }
				   List<UserPicture> groupPictures = (List<UserPicture>)request.getPortletSession().getAttribute("groupPictures",PortletSession.PORTLET_SCOPE);
				   if(groupPictures != null){
					   for(int i=0;i<groupPictures.size();i++){
						   if(groupPictures.get(i).getId() == picture.getId()){							   							  
							   groupPictures.remove(i);
							   groupPictures.add(i,picture);
							   break;
						   }
					   }
				   }
				   request.setAttribute("pictureId",pictureId);
				   request.setAttribute("picture",picture);
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }
	   }
	   else if("deleteTag".equals(action)){
			   String pictureId = request.getParameter("pictureId");
			   String tagId = request.getParameter("parameter");
			   if(pictureId != null){
				   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){					   
				   request.setAttribute("picture",picture);
				   if(tagId != null){
					   PicturePositionTag dtag = this.getUserService(request).getPicturePositionTagById(Long.parseLong(tagId));
					   this.getPortalService(request).delete(dtag);
					   List<UserPicture> groupPictures = (List<UserPicture>)request.getPortletSession().getAttribute("groupPictures",PortletSession.PORTLET_SCOPE);
					   if(groupPictures != null){
						   for(UserPicture pic : groupPictures){
							   if(pic.getId() == picture.getId()){
								   List<PicturePositionTag> tags = this.getUserService(request).getPicturePositionTags(pic.getId());
								   Set<PicturePositionTag> tagSet = pic.getTaggings();
								   tagSet.clear();
								   for(PicturePositionTag tag : tags){
									   tagSet.add(tag);
								   }
								   pic.setTaggings(tagSet);
								   break;
							   }
						   }
					   }
				   }
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
			   }else
				   request.setAttribute("error", "Please pick a picture first.");
			   response.setPortletMode(PortletMode.EDIT);
	   }
	   else if("delete".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
		   if(picture != null){
			   FileUtil.deleteFile(picture.getPictureUrl(),OrganizationThreadLocal.getOrganizationId());
			   this.getPortalService(request).delete(picture);
			   List<UserPicture> groupPictures = (List<UserPicture>)request.getPortletSession().getAttribute("groupPictures",PortletSession.PORTLET_SCOPE);
			   if(groupPictures != null){
				   for(UserPicture pic : groupPictures){
					   if(pic.getId() == picture.getId()){						 
						   groupPictures.remove(pic);
						   break;
					   }
				   }
			   }
			   request.setAttribute("success", "This picture has been deleted successfully.");
		   }else
			   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");
	  }
	   else if("config".equals(action)){
			String items = request.getParameter("items");
			PortletObject portlet =getPortlet(request);		
			if(portlet != null){			
				portlet.setShowNumber(Integer.parseInt(items));
				this.getPortalService(request).save(portlet);
			}
			String columns = request.getParameter("columns");
			PortletPreferences portletPreferences = request.getPreferences();
			portletPreferences.reset("columns");
			portletPreferences.setValue("columns",columns);		   
			portletPreferences.store();
			response.setPortletMode(PortletMode.VIEW);
		}
	   else if("score".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   long id =0;
		   try{
			   id = Long.parseLong(pictureId);
		   }catch(Exception e){}
		   if(id > 0){
			   UserPicture picture = this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			   if(picture != null){
				   picture.setScore(picture.getScore()+1);
				   this.getPortalService(request).save(picture);
				   List<UserPicture> groupPictures = (List<UserPicture>)request.getPortletSession().getAttribute("groupPictures",PortletSession.PORTLET_SCOPE);
				   if(groupPictures != null){
					   for(UserPicture pic : groupPictures){
						   if(pic.getId() == picture.getId()){
							   pic.setScore(picture.getScore());
						   }
					   }
				   }
			   }					  
		   }
	   }
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String clientWidth = request.getParameter("portletClientWidth");		 
		 String groupId = request.getParameter("groupId");
		 long id = 0L;
		 if(groupId != null){
			 try{
				 id = Long.parseLong(groupId);
			 }catch(NumberFormatException e){}
		 }
		 if(id <= 0 && this.getVisitedGroup(request) != null) id = this.getVisitedGroup(request).getId();
		 if(id > 0){
			 if((this.getVisitedGroup(request) != null && this.getVisitedGroup(request).getHiddenGroup() == 0) || this.getGroupService(request).getUserGroup(this.getUser(request).getId(),id) != null){
				 List<UserPicture> groupPictures = (List<UserPicture>)request.getPortletSession().getAttribute("groupPictures",PortletSession.PORTLET_SCOPE);
				 int index = 0;
				 Object flag = request.getPortletSession().getAttribute("refreshGroupPictures",PortletSession.APPLICATION_SCOPE);
				 if(flag != null) request.getPortletSession().removeAttribute("refreshGroupPictures",PortletSession.APPLICATION_SCOPE);
				 if(groupPictures == null || flag != null){
					 groupPictures = this.getUserService(request).getOrgPictures(id);
					 request.getPortletSession().setAttribute("groupPictures", groupPictures, PortletSession.PORTLET_SCOPE);
					 request.getPortletSession().setAttribute("groupPicturesIndex", 0, PortletSession.PORTLET_SCOPE);
				 }else{
					 index = (Integer)request.getPortletSession().getAttribute("groupPicturesIndex", PortletSession.PORTLET_SCOPE);
					 if(request.getParameter("previous") != null)
						 index--;
					 else
						 index++;
					 if(index<0) index = groupPictures.size() - 1;
					 if(index >= groupPictures.size()) index=0;
					 request.getPortletSession().setAttribute("groupPicturesIndex", index, PortletSession.PORTLET_SCOPE);
				 }

				 int pictureCount =0;				 
				 List<UserPicture> showPictures = new ArrayList<UserPicture>();
				 String pictureId = request.getParameter("pictureId");
				 if(request.getAttribute("pictureId") != null) pictureId = (String)request.getAttribute("pictureId");
				 PortletPreferences portletPreferences = request.getPreferences();
				 String columns = portletPreferences.getValue("columns","4");
				 int column=Integer.parseInt(columns);
				 int showNumber = column;
				 if(groupPictures != null && groupPictures.size() > 0) {
					 pictureCount=groupPictures.size();
					 if(pictureId == null){
						 request.setAttribute("currentPictureUrl",groupPictures.get(0).getPictureUrl());
						 request.setAttribute("currentCaption",groupPictures.get(0).getCaption());
						 request.setAttribute("currentPictureId",groupPictures.get(0).getId());
						 request.setAttribute("currentPictureWidth",groupPictures.get(0).getLargeWidth());
						 request.setAttribute("currentPictureHeight",groupPictures.get(0).getLargeHeight());
					 }else{
						 int id2= Integer.parseInt(pictureId);
						 for(UserPicture up : groupPictures){
							 if(up.getId() == id2){
								 request.setAttribute("currentPictureUrl",up.getPictureUrl());
								 request.setAttribute("currentCaption",up.getCaption());
								 request.setAttribute("currentPictureId",up.getId());
								 request.setAttribute("currentPictureWidth",up.getLargeWidth());
								 request.setAttribute("currentPictureHeight",up.getLargeHeight());
								 break;
							 }					
						 }
					 }
					 
					 PortletObject portlet =getPortlet(request);
					 int width =0;
					 if(clientWidth != null && !request.getWindowState().equals(WindowState.MAXIMIZED)){
						 if(clientWidth.indexOf(".") > 0) clientWidth = clientWidth.substring(0, clientWidth.indexOf("."));
						 try { width =Integer.parseInt(clientWidth);}catch(Exception e){}
					 }						 
					 if(portlet == null){
						 for(int i=0;i<groupPictures.size();i++){
							 UserPicture pic = groupPictures.get(i);					 
							 pic.setStandardSmallWidth(width,Integer.parseInt(columns));
							 showPictures.add(pic);
						 }
					 }else{
						 if(portlet.getAutoRefreshed() == 0 && portlet.getShowNumber() > 0){
							 showNumber = portlet.getShowNumber();
							 for(int i=0;i<groupPictures.size();i++){
								 if(i>=showNumber) break;
								 UserPicture pic = groupPictures.get(i);					 
								 pic.setStandardSmallWidth(width,Integer.parseInt(columns));
								 showPictures.add(pic);
							 }
						 }
						 else{							 
							 UserPicture pic = groupPictures.get(index);
							 if(clientWidth != null && !request.getWindowState().equals(WindowState.MAXIMIZED))
								 pic.setStandardSmallWidth(width);
							 showPictures.add(pic);
							 request.setAttribute("pictureNavigator",true);
						 }
					 }
				 }
				 Group group = this.getGroupService(request).getGroupById(id);
				 request.setAttribute("group",group);
				 if(pictureCount > showNumber)
					 request.setAttribute("showMore",true);
				 request.setAttribute("pictureCount",pictureCount);
				 request.setAttribute("showPictures",showPictures);
				 request.setAttribute("groupPictures",groupPictures);				 
				 request.setAttribute("columnNumber",column);
				 if(request.getWindowState().equals(WindowState.MAXIMIZED))
					 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletMaxView.jsp").include(request,response);  
				 else
					 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletView.jsp").include(request,response);  						
			 }else{
				 request.setAttribute("error", "The Group Pictures are only available for group members to view.");	
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/error/error.jsp").include(request,response);  					
			 }
		 }else{
			 request.setAttribute("error", "System cannot find this group.");	
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/error/error.jsp").include(request,response);
		 }
		
	 }	
    
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		  
		 if(request.getParameter("add") != null || request.getAttribute("add") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletAdd.jsp").include(request,response);  		 
		 else if(request.getAttribute("picture") != null){
			 UserPicture picture = (UserPicture)request.getAttribute("picture");
			 if(picture.getTaggings() != null && picture.getTaggings().size() > 0){
				 int i=1;
				 for(PicturePositionTag tag: picture.getTaggings()){
					 request.setAttribute("tag"+i,tag);
					 i++;
				 }
			 }
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletEdit.jsp").include(request,response);
		 }
		 else if(request.getParameter("pictureId") != null){
			 String pictureId = request.getParameter("pictureId");
			 UserPicture picture = this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			 request.setAttribute("picture",picture);
			 if(picture.getTaggings() != null && picture.getTaggings().size() > 0){
				 int i=1;
				 for(PicturePositionTag tag: picture.getTaggings()){
					 request.setAttribute("tag"+i,tag);
					 i++;
				 }
			 }
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletEdit.jsp").include(request,response);
	 	 }
		 else{
			 PortletPreferences portletPreferences = request.getPreferences();
			 String columns = portletPreferences.getValue("columns","1");
			 int column=Integer.parseInt(columns);
			 int showNumber = column;
			 PortletObject portlet = this.getPortlet(request);
			 if(portlet != null && portlet.getShowNumber() > showNumber)
				 showNumber = portlet.getShowNumber();		
			 request.setAttribute("showNumber",showNumber);			 
			 request.setAttribute("columnNumber",column);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/group/groupPicturePortletConfig.jsp").include(request,response);
		 }			
	 }	
	 
}
