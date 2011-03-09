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

package org.light.portlets.ad;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.DateUtil;
import org.light.portal.util.LocaleUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class AdPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 	String action = request.getParameter("action");			
			if("save".equals(action)){
			   String title = request.getParameter("title");
			   String content = request.getParameter("content");
			   String country = request.getParameter("country");
			   String province = request.getParameter("province");
			   String city = request.getParameter("city");
			   String adUrl = request.getParameter("adUrl");
			   String effDate = request.getParameter("effDate");
			   String endEffDate = request.getParameter("endEffDate");
			   if( title == null || title.length() <= 0 || content == null || content.length() <= 0
			    || effDate == null || effDate.length() != 10
			    || endEffDate == null || endEffDate.length() != 10){
				   request.setAttribute("error","Please input all required fields correctly.");
				   request.setAttribute("title",title);
				   request.setAttribute("content",content);
				   request.setAttribute("country",country);
				   request.setAttribute("province",province);
				   request.setAttribute("city",city);
				   request.setAttribute("adUrl",adUrl);
				   request.setAttribute("effDate",effDate);
				   request.setAttribute("endEffDate",endEffDate);
				   //request.setAttribute("mode",PortletMode.EDIT.toString());
				   response.setPortletMode(PortletMode.EDIT);
				   return;
			   }	
			   PortletObject portlet = this.getPortlet(request);
			   String category = portlet.getParameter();
			   Date currentDate = new Date(System.currentTimeMillis());
			   Calendar effCalendar = GregorianCalendar.getInstance();				
				int  year = Integer.parseInt(effDate.substring(0,4));
				int  month = Integer.parseInt(effDate.substring(5,7)) - 1;
				int  day = Integer.parseInt(effDate.substring(8));
				effCalendar.set(year,month,day);
				Calendar endEffCalendar = GregorianCalendar.getInstance();				
				int  year2 = Integer.parseInt(endEffDate.substring(0,4));
				int  month2 = Integer.parseInt(endEffDate.substring(5,7)) - 1;
				int  day2 = Integer.parseInt(endEffDate.substring(8));
				endEffCalendar.set(year2,month2,day2);
			   if(category != null) category = category.substring(category.indexOf("=")+1);
			   CategoryAd ad = new CategoryAd(title,content,Integer.parseInt(category),country,province,city,adUrl,1,this.getUser(request).getId(),currentDate,new Date(effCalendar.getTime().getTime()),new Date(endEffCalendar.getTime().getTime()));
			   
			   this.getPortalService(request).save(ad);
			}	
			if("delete".equals(action)){
				String adId = request.getParameter("parameter");
				CategoryAd ad = this.getAdService(request).getAdById(Integer.parseInt(adId));
				if(ad != null){
					this.getPortalService(request).delete(ad);	
				}
			}	
			if("config".equals(action)){
			   String country = request.getParameter("country");
			   String province = request.getParameter("province");
			   String city = request.getParameter("city");
			   String type = request.getParameter("type");
			   if("1".equals(type)){
				   request.getPortletSession().setAttribute("country",country,PortletSession.PORTLET_SCOPE);
				   request.getPortletSession().setAttribute("province",province,PortletSession.PORTLET_SCOPE);
				   request.getPortletSession().setAttribute("city",city,PortletSession.PORTLET_SCOPE);
			   }else{
				   request.getPortletSession().setAttribute("country",country,PortletSession.APPLICATION_SCOPE);
				   request.getPortletSession().setAttribute("province",province,PortletSession.APPLICATION_SCOPE);
				   request.getPortletSession().setAttribute("city",city,PortletSession.APPLICATION_SCOPE);
			   }
			}else{
			   String showHtmlEditor = request.getParameter("htmlEditor");
			   if(showHtmlEditor != null)
					 request.setAttribute("showHtmlEditor",true);
			   String title = request.getParameter("title");
			   String content = request.getParameter("content");
			   String country = request.getParameter("country");
			   String province = request.getParameter("province");
			   String city = request.getParameter("city");
			   String adUrl = request.getParameter("adUrl");
			   String effDate = request.getParameter("effDate");
			   String endEffDate = request.getParameter("endEffDate");
			   request.setAttribute("title",title);
			   request.setAttribute("content",content);
			   request.setAttribute("country",country);
			   request.setAttribute("province",province);
			   request.setAttribute("city",city);
			   request.setAttribute("adUrl",adUrl);
			   request.setAttribute("effDate",effDate);
			   request.setAttribute("endEffDate",endEffDate);

			}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 String adId = request.getParameter("adId");
		 if(adId == null){
			 PortletObject portlet = this.getPortlet(request);
			 String category = portlet.getParameter();
			 if(category != null) category = category.substring(category.indexOf("=")+1);
			 int showNumber = portlet.getShowNumber();
			 if(showNumber <=0) showNumber = 6;
			 List<CategoryAd> list = null;
			 String country = null;
			 String province = null;
			 String city = null;
			 User user = this.getUser(request);
			 if(user != null){
				 country= user.getCountry();
				 province = user.getProvince();
				 city = user.getCity();
			 }
			 if(request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE) != null)
			 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE);
			 if(request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE) != null)
			 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE);
			 if(request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE) != null)
			 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE);			 
			 if(request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE) != null)
		 	 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE);			 
			 if(request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE) != null)
			 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE);
			 if(request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE) != null)
		 	 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE);
			 request.setAttribute("country",country);
			 request.setAttribute("province",province);
			 request.setAttribute("city",city);
			 if(request.getWindowState().equals(WindowState.MAXIMIZED))
				showNumber = 0; 
			 list = this.getAdService(request).getAdsByCategory(Integer.parseInt(category),showNumber,country,province,city);			
			 request.setAttribute("adLists",list);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/ad/adPortletView.jsp").include(request,response);
		 }else{		 
			 CategoryAd ad = this.getAdService(request).getAdById(Integer.parseInt(adId));
			 request.setAttribute("ad",ad);
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/ad/adPortletDetail.jsp").include(request,response);
		 }		
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		Date effDate = new Date(System.currentTimeMillis());
		Calendar endEffTime = Calendar.getInstance();
		endEffTime.setTimeInMillis(System.currentTimeMillis());
		endEffTime.add(Calendar.DATE, 7);
		String country = null;
		 String province = null;
		 String city = null;
		 User user = this.getUser(request);
		 if(user != null){
			 country= user.getCountry();
			 province = user.getProvince();
			 city = user.getCity();
		 }
		 if(request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE) != null)
		 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE);
		 if(request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE) != null)
		 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE);
		 if(request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE) != null)
		 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE);			 
		 if(request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE) != null)
	 	 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE);			 
		 if(request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE) != null)
		 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE);
		 if(request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE) != null)
	 	 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE);

		request.setAttribute("country",country);
		request.setAttribute("countries", LocaleUtil.getSupportedCountry(this.getLocale(request)));
		request.setAttribute("province",province);
		request.setAttribute("city",city);
		request.setAttribute("effDate",DateUtil.format(effDate,"yyyy/MM/dd"));
		request.setAttribute("endEffDate",DateUtil.format(endEffTime.getTime(),"yyyy/MM/dd"));
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/ad/adPortletEdit.jsp").include(request,response);
		 	
	 }
	 
	 protected void doConfig (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		 String country = null;
		 String province = null;
		 String city = null;
		 User user = this.getUser(request);
		 if(user != null){
			 country= user.getCountry();
			 province = user.getProvince();
			 city = user.getCity();
		 }
		 if(request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE) != null);
		 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.PORTLET_SCOPE);
		 if(request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE) != null);
		 	country=(String)request.getPortletSession().getAttribute("country",PortletSession.APPLICATION_SCOPE);
		 if(request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE) != null);
		 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.APPLICATION_SCOPE);			 
		 if(request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE) != null);
	 	 	province=(String)request.getPortletSession().getAttribute("province",PortletSession.PORTLET_SCOPE);			 
		 if(request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE) != null);
		 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.APPLICATION_SCOPE);
		 if(request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE) != null);
	 	 	city=(String)request.getPortletSession().getAttribute("city",PortletSession.PORTLET_SCOPE);

		request.setAttribute("country",country);
		request.setAttribute("countries", LocaleUtil.getSupportedCountry(this.getLocale(request)));
		request.setAttribute("province",province);
		request.setAttribute("city",city);

		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/ad/adPortletConfig.jsp").include(request,response);
		 	
	 }
	 
	 protected void doHelp (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  		 
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/ad/adPortletHelp.jsp").include(request,response);
	 }

}

