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

import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;
import static org.light.portal.util.Constants._USER_PATH;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.json.JSONObject;
import org.light.portal.model.Portal;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserPictureGroup;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.DateUtil;
import org.light.portal.util.FileUtil;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class MyPicturePortlet extends LightGenericPortlet {
	 
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
			int status = 0;			
			if(user != null) status = user.getDefaultPictureStatus();
			URL url = new URL(pictureUrl);
			java.awt.image.BufferedImage image=javax.imageio.ImageIO.read(url);
			int width = 300;
			int height= 280;
			if(image != null){
				width = image.getWidth();
				height= image.getHeight();
			}
			UserPicture picture = new UserPicture(user.getId(),user.getOrgId(),user.getOrgId(),pictureUrl,status,width,height);			
			this.getPortalService(request).save(picture);	
			List<UserPicture> userPictures = (List<UserPicture>)request.getPortletSession().getAttribute("myPictures",PortletSession.APPLICATION_SCOPE);
		    if(userPictures == null)
		    {
		    	userPictures= new ArrayList<UserPicture>();
		    }
			userPictures.add(picture);
			request.getPortletSession().setAttribute("myPictures", userPictures, PortletSession.APPLICATION_SCOPE);
	   }
		else if("profile".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
			   if(picture != null){
				    if(!picture.getPictureUrl().toLowerCase().startsWith("http")){
					    int index = picture.getPictureUrl().indexOf("/"+_USER_PATH+"/");
					    String path = _FILE_PATH+OrganizationThreadLocal.getOrganizationId()+picture.getPictureUrl().substring(index);				    
					    if(ImageUtil.saveThumb(path)){
						   User user = this.getUser(request);
						   user.setPhotoUrl(picture.getPictureUrl());
						   user.setPhotoWidth(picture.getPictureWidth());
						   user.setPhotoHeight(picture.getPictureHeight());
						   this.getUserService(request).save(user);
						   request.setAttribute("success", "Your profile's defalut photo has been set successfully, please refresh Profile window to see the new setting.");
					    }else
					    	request.setAttribute("error", "System cannot set this picture as profile picture.");
				    }else
				    	request.setAttribute("error", "System cannot set picture URL as profile picture, please pick a uploaded picture.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		   
		   }else
			   request.setAttribute("error", "Please pick a picture first.");				   
		}
		else if("background".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
			   if(picture != null){				   
				   Portal portal = this.getPortal(request);
				   portal.setBgImage(picture.getPictureUrl());
				   portal.setBgPosition("left top");
				   portal.setBgRepeat(1);
				   portal.setHeaderImage("no");
				   portal.setTransparent(0);
				   this.getPortalService(request).save(portal);
				   request.setAttribute("success", "Your portal's background has been set successfully, please refresh portal to see the new setting.");
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
				   Portal portal = this.getPortal(request);
				   portal.setHeaderImage(picture.getPictureUrl());
				   portal.setHeaderPosition("left top");
				   portal.setHeaderRepeat(1);
				   this.getPortalService(request).save(portal);
				   request.setAttribute("success", "Your portal's header picture has been set successfully, please refresh portal to see the new setting.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");	
	   }
		else if("edit".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
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
		   String tag = request.getParameter("tag");
		   String status = request.getParameter("status");
		   String rankable = request.getParameter("rankable");
		   if(pictureId != null){
			   int id = Integer.parseInt(pictureId);
			   UserPicture picture = null;
			   List<UserPicture> userPictures = (List<UserPicture>)request.getPortletSession().getAttribute("myPictures",PortletSession.APPLICATION_SCOPE);
			   if(userPictures != null){
				   for(UserPicture pic : userPictures){
					   if(pic.getId() == id){
						   picture = pic;
						   break;
					   }   
				   }
			   }
			   if(picture == null)
				   picture= this.getUserService(request).getUserPictureById(id);
			   if(picture != null){				   
				   picture.setCaption(caption);
				   picture.setTag(tag);
				   picture.setStatus(Integer.parseInt(status));
				   if(rankable != null && "1".equals(rankable))
					   picture.setRankable(1);
				   else
					   picture.setRankable(0);
				   this.getPortalService(request).save(picture);
				   User user = this.getUser(request);
				   if(picture.getPictureUrl().equals(user.getPhotoUrl())){
					   user.setCaption(caption);					   
					   this.getUserService(request).save(user);
				   }	
				   request.setAttribute("pictureId",pictureId);
				   request.setAttribute("picture",picture);
				   request.setAttribute("success", "This picture's change has been saved successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "System cannot find this picture.");	
	   }
		else if("rank".equals(action)){
		   String pictureId = request.getParameter("pictureId");
		   if(pictureId != null){
			   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
			   if(picture != null){				   
				   picture.setRankable(1);
				   this.getPortalService(request).save(picture);
				   request.setAttribute("success", "This picture has been set to rank successfully, it's ready for public to rank.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");	
	   }
		else if("config".equals(action)){
			config(request);
		}
		else if("apply".equals(action)){
			config(request);					
			String status = request.getParameter("status");
			User user = this.getUser(request);
			if(user != null){				
				this.getUserService(request).updatePictureStatus(user.getId(),Integer.parseInt(status));
			}			
		}
		else if("delete".equals(action)){
			   String pictureId = request.getParameter("pictureId");
			   if(pictureId != null){
				   UserPicture picture= this.getUserService(request).getUserPictureById(Integer.parseInt(pictureId));
			   if(picture != null){	
				   FileUtil.deleteFile(picture.getPictureUrl(),OrganizationThreadLocal.getOrganizationId());			   				  
				   this.getPortalService(request).delete(picture);
				   request.getPortletSession().setAttribute("myPictures", null, PortletSession.APPLICATION_SCOPE);
				   request.setAttribute("success", "This picture has been deleted successfully.");
			   }else
				   request.setAttribute("error", "System cannot find this picture.");		 
		   }else
			   request.setAttribute("error", "Please pick a picture first.");
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
				   List<UserPicture> myPictures = (List<UserPicture>)request.getPortletSession().getAttribute("myPictures",PortletSession.APPLICATION_SCOPE);
				   if(myPictures != null){
					   for(UserPicture pic : myPictures){
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
		 PortletPreferences portletPreferences = request.getPreferences();
		 String type = portletPreferences.getValue("type","0");
		 String columns = portletPreferences.getValue("columns","1");
		 String size = portletPreferences.getValue("size","0");
		 String clientWidth = request.getParameter("portletClientWidth");	
		 if(clientWidth == null) 
			 clientWidth = (String)request.getPortletSession().getAttribute("clientWidth",PortletSession.PORTLET_SCOPE);
		 else
			 request.getPortletSession().setAttribute("clientWidth",clientWidth,PortletSession.PORTLET_SCOPE);
		 int width =0;
		 if(clientWidth != null && !request.getWindowState().equals(WindowState.MAXIMIZED)){
			 if(clientWidth.indexOf(".") > 0) clientWidth = clientWidth.substring(0, clientWidth.indexOf("."));
			 try { width =Integer.parseInt(clientWidth);}catch(Exception e){}
		 }
		 User user = this.getUser(request);
		 List<UserPicture> userPictures = (List<UserPicture>)request.getPortletSession().getAttribute("myPictures",PortletSession.APPLICATION_SCOPE);
		 int index = 0;
		 long userId=0;
		 if(user != null) userId= user.getId();
		 if(type.equals("1")){//community based picture viewer
			 userPictures = this.getUserService(request).getPublicPictures(this.getUser(request).getOrgId(),0,50);
		 }else if(this.getVisitedUser(request) != null && isVisitingMember(request)){
			 userPictures = this.getUserService(request).getVisitedUserPictures(userId,this.getVisitedUser(request).getId(),this.getVisitedUser(request).getOrgId());
	 	 }else if(this.getVisitedGroup(request) != null && isVisitingGroup(request)){
			 userPictures = this.getUserService(request).getOrgPictures(this.getVisitedGroup(request).getId());
	 	 }else if(userPictures == null || userPictures.size() == 0){
			 userPictures = this.getUserService(request).getUserPictures(userId,OrganizationThreadLocal.getOrganizationId());
			 request.getPortletSession().setAttribute("myPictures", userPictures, PortletSession.APPLICATION_SCOPE);
			 request.getPortletSession().setAttribute("myPicturesIndex", 0, PortletSession.APPLICATION_SCOPE);
		 }else{
			 index = (Integer)request.getPortletSession().getAttribute("myPicturesIndex", PortletSession.APPLICATION_SCOPE);
			 index++;
			 if(index >= userPictures.size()) index=0;
			 request.getPortletSession().setAttribute("myPicturesIndex", index, PortletSession.APPLICATION_SCOPE);
		 }
 
	     int pictureCount =0;
		 int showNumber = 1;
		 int autoRefresh = 1;
		 List<UserPicture> showPictures = new ArrayList<UserPicture>();
		 String pictureId = request.getParameter("pictureId");
		 if(request.getAttribute("pictureId") != null) pictureId = (String)request.getAttribute("pictureId");
		 if(userPictures != null && userPictures.size() > 0) {
			 pictureCount=userPictures.size();
			 boolean found = false;
			 if(pictureId != null){
				 int id= Integer.parseInt(pictureId);				 
				 for(UserPicture up : userPictures){
					 if(up.getId() == id){
						 if(up.isHttpUrl())
							 request.setAttribute("currentPictureUrl",up.getPictureUrl());
						 else
							 request.setAttribute("currentPictureUrl",request.getContextPath()+up.getPictureUrl());
						 request.setAttribute("currentCaption",up.getCaption());
						 request.setAttribute("currentPictureId",up.getId());
						 request.setAttribute("currentPictureWidth",up.getLargeWidth());
						 request.setAttribute("currentPictureHeight",up.getLargeHeight());
						 request.setAttribute("picture",up);
						 found = true;
						 break;
					 }					
				 }
			 }
			 if(!found){
				 if(userPictures.get(index).isHttpUrl())
					 request.setAttribute("currentPictureUrl",userPictures.get(index).getPictureUrl());
				 else
					 request.setAttribute("currentPictureUrl",request.getContextPath()+userPictures.get(index).getPictureUrl());
				 request.setAttribute("currentCaption",userPictures.get(index).getCaption());
				 request.setAttribute("currentPictureId",userPictures.get(index).getId());
				 request.setAttribute("currentPictureWidth",userPictures.get(index).getLargeWidth());
				 request.setAttribute("currentPictureHeight",userPictures.get(index).getLargeHeight());
				 request.setAttribute("picture",userPictures.get(index));
			 }
            
			 PortletObject portlet =getPortlet(request);
			 if(portlet != null) autoRefresh = portlet.getAutoRefreshed();					 
			 
			 if(portlet != null && portlet.getAutoRefreshed() == 0 && portlet.getShowNumber() > 0){
				 showNumber = portlet.getShowNumber();
				 int start = 0;
				 if(request.getPortletSession().getAttribute("start",PortletSession.PORTLET_SCOPE) != null)
					 start = (Integer)request.getPortletSession().getAttribute("start",PortletSession.PORTLET_SCOPE);
				 if(request.getParameter("next") != null) start+=showNumber;
				 if(request.getParameter("previous") != null) start-=showNumber;
				 if(start < 0) start = userPictures.size() - 1;
				 if(start > userPictures.size() - 1) start = 0;
				 request.getPortletSession().setAttribute("start",start,PortletSession.PORTLET_SCOPE);
				 for(int i=start;i<userPictures.size();i++){
					 if(i>=start + showNumber) break;
					 UserPicture pic = userPictures.get(i);					 
					 pic.setStandardSmallWidth(width,Integer.parseInt(columns));
					 showPictures.add(pic);
				 }
			 }
			 else{
				 UserPicture pic = userPictures.get(index);
				 if(clientWidth != null && !request.getWindowState().equals(WindowState.MAXIMIZED))
					 pic.setStandardSmallWidth(width);
				 showPictures.add(pic);
			 }
				 
		 }
		 if(pictureCount > showNumber)
			 request.setAttribute("showMore",true);
		 request.setAttribute("pictureCount",pictureCount);
		 request.setAttribute("autoRefresh",autoRefresh);
		 request.setAttribute("showNumber",showNumber);
		 request.setAttribute("showPictures",showPictures);
		 request.setAttribute("userPictures",userPictures);		 
		 request.setAttribute("columnNumber",Integer.parseInt(columns));
		 request.setAttribute("size",Integer.parseInt(size));
		 if(request.getWindowState().equals(WindowState.MAXIMIZED)){
			 Map<String, UserPictureGroup> dates = new HashMap<String, UserPictureGroup>();
			 Map<String, UserPictureGroup> tags = new HashMap<String, UserPictureGroup>();
			 for(int i=0;i<userPictures.size();i++){
				 UserPicture pic = userPictures.get(i);	
				 //yyyy (year only)
				 String date = DateUtil.format(pic.getCreateDate(), "yyyy");
				 String indx = DateUtil.format(pic.getCreateDate(), "yyyy");
				 if(dates.containsKey(date)){
					 UserPictureGroup group = dates.get(date);
					 group.addPicture(pic);
					 dates.remove(date);
					 dates.put(date,group);
				 }else{
					 dates.put(date,new UserPictureGroup(indx,date,pic));
				 }
				 //MMM yyyy (month, year)
				 date = DateUtil.format(pic.getCreateDate(), "MMM, yyyy");
				 indx = DateUtil.format(pic.getCreateDate(), "yyyyMM");
				 if(dates.containsKey(date)){
					 UserPictureGroup group = dates.get(date);
					 group.addPicture(pic);
					 dates.remove(date);
					 dates.put(date,group);
				 }else{
					 dates.put(date,new UserPictureGroup(indx,date,pic));
				 }
				 //MMM dd, yyyy
				 date = DateUtil.format(pic.getCreateDate(), "MMM dd, yyyy");
				 indx = DateUtil.format(pic.getCreateDate(), "yyyyMMdd");
				 if(dates.containsKey(date)){
					 UserPictureGroup group = dates.get(date);
					 group.addPicture(pic);
					 dates.remove(date);
					 dates.put(date,group);
				 }else{
					 dates.put(date,new UserPictureGroup(indx,date,pic));
				 }
				 
				 String tag = pic.getTag();
				 if(tag != null){
					 String[] tagArray = tag.split(",");
					 for(int j=0;j < tagArray.length; j++){
						 if(tags.containsKey(tagArray[j])){
							 UserPictureGroup group = tags.get(tagArray[j]);
							 group.addPicture(pic);
							 tags.remove(tagArray[j]);
							 tags.put(tagArray[j],group);
						 }else{
							 tags.put(tagArray[j],new UserPictureGroup(tagArray[j],tagArray[j],pic));
						 }
					 }
				 }
			 }
			 Collection<UserPictureGroup> pictureCollections = dates.values();
			 Collection<UserPictureGroup> pictureCollections2 = tags.values();
			 List datePictures = new ArrayList(pictureCollections);
			 List tagPictures = new ArrayList(pictureCollections2);
			 Collections.sort(datePictures);
			 Collections.reverse(datePictures);
			 Collections.sort(tagPictures);
			 request.setAttribute("datePictures",datePictures);
			 request.setAttribute("tagPictures",tagPictures);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletMaxView.jsp").include(request,response);  
		 }else
			 if(Integer.parseInt(size) == 1)
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletViewer.jsp").include(request,response);
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletView.jsp").include(request,response);  			
	 }	
     
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 if(request.getParameter("add") != null || request.getAttribute("add") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletAdd.jsp").include(request,response);  		 
		 else if(request.getAttribute("picture") != null)
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletEdit.jsp").include(request,response);
		 else{
			 PortletObject portlet = this.getPortlet(request);
			 request.setAttribute("portlet",portlet);
			 request.setAttribute("seconds",portlet.getPeriodTime() / 1000);
			 int showNumber = 0;
			 if(portlet != null)
			    showNumber = portlet.getShowNumber();
			 if(showNumber <=0) showNumber = 6;
			 request.setAttribute("showNumber",showNumber);
			 PortletPreferences portletPreferences = request.getPreferences();
			 String columns = portletPreferences.getValue("columns","1");
			 String size = portletPreferences.getValue("size","0");
			 request.setAttribute("columnNumber",Integer.parseInt(columns));
			 request.setAttribute("size",Integer.parseInt(size));
			 String defaultStatus = "0";
			 User user = this.getUser(request);
			 if(user != null) defaultStatus = String.valueOf(user.getDefaultPictureStatus());
			 request.setAttribute("defaultStatus",Integer.parseInt(defaultStatus));
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/profile/myPicturePortletConfig.jsp").include(request,response);
		 }			 
	 }	
	 
	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 int count = getUserService(request).getUserPictureCount(user.getId(),user.getOrgId());
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
		 int autoRefresh = Integer.parseInt(request.getParameter("autoRefresh"));
			String seconds = request.getParameter("seconds");
			String items = request.getParameter("items");
			String size = request.getParameter("size");
			PortletObject portlet =getPortlet(request);		
			if(portlet != null){
				portlet.setAutoRefreshed(autoRefresh);
				portlet.setPeriodTime(new Double(Double.parseDouble(seconds) * 1000).intValue());
				portlet.setShowNumber(Integer.parseInt(items));
				this.getPortalService(request).save(portlet);
			}
			String columns = request.getParameter("columns");
			if(autoRefresh >= 1) columns="1";
			PortletPreferences portletPreferences = request.getPreferences();
			portletPreferences.reset("columns");
			portletPreferences.setValue("columns",columns);		
			portletPreferences.reset("size");
			portletPreferences.setValue("size",size);	
			portletPreferences.store();
			
			String status = request.getParameter("status");
			User user = this.getUser(request);
			if(user != null){
				user.setDefaultPictureStatus(Integer.parseInt(status));
				this.getUserService(request).save(user);
			}			
	 }
}
