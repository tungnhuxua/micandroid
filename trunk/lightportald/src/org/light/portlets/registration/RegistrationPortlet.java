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

package org.light.portlets.registration;

import static org.light.portal.util.Constants._OBJECT_TYPE_ORG;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.User;
import org.light.portal.model.User;
import org.light.portal.model.UserObjectRole;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.ValidationUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class RegistrationPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		 String action = request.getParameter("action");
		 if("step1".equals(action)){
			 String userId = request.getParameter("email");
			 String password = request.getParameter("password");
			 String displayName = request.getParameter("displayName");		 		
			 String chosedUri=  request.getParameter("iUri"); 
			 if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(password) || StringUtils.isEmpty(displayName) || StringUtils.isEmpty(chosedUri)){
				 request.setAttribute("error","Please input all required fields.");
				 request.setAttribute("step", "1");
				 return;
			 }
			 if(!ValidationUtil.isValidEmail(userId)){
				 request.setAttribute("error","Please input valid Email address.");
				 request.setAttribute("step", "1");
				 return;
			 }
			 if(!ValidationUtil.isValidUrl(chosedUri)){
				 request.setAttribute("error","Please input valid URI.");
				 request.setAttribute("step", "1");
				 return;
			 }
			 User newUser = new User(userId,password,displayName,userId,chosedUri.trim(),OrganizationThreadLocal.getOrganizationId());
			 try{
				 User user = this.getUserService(request).signUp(newUser,OrganizationThreadLocal.getOrganizationId());			 			
				 this.setUser(request,user);
				 this.getPortalService(request).createPortalByUser(user);
				 request.setAttribute("step", "2");
			 }catch(Exception e){
				 request.setAttribute("error","System error, please register later.");
				 request.setAttribute("step", "1");
			 }			 
		 }
		 else if("step2".equals(action)){
			 String language = request.getParameter("language");
			 String region = language;
			 String timeZone=  request.getParameter("timeZone");
			 String birthM = request.getParameter("birthM");
			 String birthD = request.getParameter("birthD");
			 String birthY = request.getParameter("birthY");
			 String gender = request.getParameter("gender");
			 String country = request.getParameter("country");
			 String province = request.getParameter("province");
			 String city = request.getParameter("city");
			 String postalCode = request.getParameter("postalCode");
			 User user = this.getUserService(request).getUserById(this.getUser(request).getId());
			 user.setLanguage(language);
			 user.setRegion(region);
			 user.setTimeZone(timeZone);
			 String birth = birthY+"/"+birthM+"/"+birthD;
			 if(birth.length() == 10)
				 user.setBirth(birthY+"/"+birthM+"/"+birthD);
			 user.setGender(gender);
			 user.setCountry(country);
			 user.setProvince(province);
			 user.setCity(city);
			 user.setPostalCode(postalCode);
			 try{
				 this.getUserService(request).save(user);
				 this.setUser(request,user);
				 request.setAttribute("step", "3");
			 }catch(Exception e){
				 request.setAttribute("error","System error, please try again.");
				 request.setAttribute("step", "2");
			 }
			 
		 }
		 else if("skip2".equals(action)){
			 request.setAttribute("step", "3");
		 }
		 else if("step3".equals(action)){
			 String[] channels = request.getParameterValues("channels");
			 try{
				 if(channels != null && channels.length > 0){
					 User user = this.getUser(request);
					 for(int i=0;i<channels.length;i++){
						 if(StringUtils.isNotEmpty(channels[i])){
							 ObjectRole channelRole = this.getUserService(request).getRoleByName(channels[i],0L);
							 UserObjectRole roleChannel = new UserObjectRole(user.getId(),user.getOrgId(),user.getOrgId(),_OBJECT_TYPE_ORG,channelRole.getId());				 
							 this.getPortalService(request).save(roleChannel);					
						 }
					 }
				 }
				 request.setAttribute("step", "L");
			 }catch(Exception e){
				 request.setAttribute("error","System error, please try again.");
				 request.setAttribute("step", "3");
			 }
		 }
		 else if("skip3".equals(action)){
			 request.setAttribute("step", "L");
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		String step = request.getParameter("step");
		if(step == null) step =(String)request.getAttribute("step");
		if(step == null) step ="1";
		
		try{
		 	JSONObject json = new JSONObject();  	    
		    String success="";
		    if(request.getAttribute("success") != null) success = (String)request.getAttribute("success"); 
		    json.put("success", success);
		    String error="";
		    if(request.getAttribute("error") != null) error = (String)request.getAttribute("error"); 
		    json.put("error", error);
		
		    if( step.equals("2")){
		    	json.put("view","registrationStep2.view");			
		    }else if( step.equals("3")){
		    	json.put("view","registrationStep3.view");
			}else if(step.equals("L")){
				json.put("view","registrationLastStep.view");
			}
		
	    	response.getWriter().write(json.toString());		    	
		}catch(Exception e){}
			
	}	
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
	 }
}