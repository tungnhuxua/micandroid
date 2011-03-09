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

package org.light.portal.core.portlets;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.CalendarUtil;
import org.light.portal.util.HashUtil;
import org.light.portal.util.LabelBean;
import org.light.portal.util.LocaleUtil;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserManagePortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		 String action = request.getParameter("action");
		 if("search".equals(action)){
			 String userId = request.getParameter("email");
			 request.setAttribute("email",userId);
			 if(userId != null ){
				 long id = 0;
				 try{
					 id = Long.parseLong(userId);
				 }catch(Exception e){}
				 User user = null;
				 if(id > 0)
					 user = this.getUserService(request).getUserById(id);
				 else
					 user = this.getUserService(request).getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
				 if(user != null){
					  request.setAttribute("searchedUser",user);
				 }else{
					  request.setAttribute("error",String.format("System didn't find any records match email %s.",userId));
				 }
			 }else{
				 request.setAttribute("error","Please input email address first.");
			 }
			 
		 }else if("save".equals(action)){
			 String userId = request.getParameter("id");
			 long id=0L;
			 if(userId != null) id = Long.parseLong(userId);
			 User user = this.getUserService(request).getUserById(id);
			 if(user != null){
				 String password = request.getParameter("password");
				 String cpassword = request.getParameter("confirmPassword");
				 String displayName = request.getParameter("displayName");
				 String disabled = request.getParameter("disabled");
				 String locked = request.getParameter("locked");
				 String language = request.getParameter("language");
				 String region = language;
				 String timeZone=  request.getParameter("timeZone");
				 String birth = request.getParameter("birthY")+"/"+request.getParameter("birthM")+"/"+request.getParameter("birthD");
				 String gender = request.getParameter("gender");
				 String country = request.getParameter("country");
				 String province = request.getParameter("province");
				 String city = request.getParameter("city");
				 String postalCode = request.getParameter("postalCode");
				 
				 if(password.equals(cpassword)) 
					 user.setPassword(HashUtil.MD5Hashing(password));
				 user.setDisplayName(displayName);
				 user.setBirth(birth);
				 user.setCountry(country);
				 user.setLanguage(language);
				 user.setRegion(region);
				 user.setProvince(province);
				 user.setCity(city);
				 user.setPostalCode(postalCode);
				 user.setTimeZone(timeZone);
				 user.setGender(gender);				 
				 if(disabled != null){
					 user.setDisabled(1);
				 }else{
					 user.setDisabled(0);
				 }
				 if(locked != null){
					 user.setLocked(1);
				 }else{
					 user.setLocked(0);
				 }
				 this.getUserService(request).save(user);
			 }
		 }else if("delete".equals(action)){
			 String userId = request.getParameter("id");
			 long id=0L;
			 if(userId != null) id = Long.parseLong(userId);
			 User user = this.getUserService(request).getUserById(id);			 
			 if(user != null){				 
				 this.getUserService(request).deleteUser(user);
				 request.setAttribute("success",String.format("User %s has been deleted successfully.",user.getDisplayName()));
			 }
			 
		 }
	 }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 if(request.getAttribute("searchedUser") != null){
			request.setAttribute("months", CalendarUtil.getMonths());
			request.setAttribute("days", CalendarUtil.getDays());
			request.setAttribute("years", CalendarUtil.getYears());		
			request.setAttribute("languages", LocaleUtil.getSupportedLanguages());
			request.setAttribute("countries", LocaleUtil.getSupportedCountry(this.getLocale(request)));			
			request.setAttribute("timeZones", LocaleUtil.getSupportedTimeZone(this.getLocale(request)));
		 }
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/userManage.jsp").include(request,response);
	 }
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		request.setAttribute("months", CalendarUtil.getMonths());
		request.setAttribute("days", CalendarUtil.getDays());
		request.setAttribute("years", CalendarUtil.getYears());
	
		List<LabelBean> channels = OrganizationThreadLocal.getOrg().getChannels();
		request.setAttribute("channels", channels);
		request.setAttribute("languages", LocaleUtil.getSupportedLanguages());
		request.setAttribute("countries", LocaleUtil.getSupportedCountry(this.getLocale(request)));
		request.setAttribute("timeZones", LocaleUtil.getSupportedTimeZone(this.getLocale(request)));
		response.getWriter().print("TODO");  
	 }
}