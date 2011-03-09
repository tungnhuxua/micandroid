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

package org.light.portlets.weather;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class WeatherPortlet extends LightGenericPortlet {
	 
	private Map<String,WeatherLocations> locSearchResults = new Hashtable<String,WeatherLocations>();
	private Map<String,Weather> weatherMaps = new Hashtable<String,Weather>();
		
	public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("select".equals(action)){
			 String locId = request.getParameter("locId");
			 String unit = request.getParameter("unit");
			 this.saveLocation(request,locId,unit, null);
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		StringBuffer resultBuffer = new StringBuffer();		
		String uri = this.getInitParameter("url");
		String partnerId = this.getInitParameter("partnerId");
		String licence = this.getInitParameter("licence");
		 
		if(uri == null || uri.trim().length() == 0  
		  || partnerId == null || partnerId.trim().length() == 0 
		  || licence == null || licence.trim().length() == 0){
			resultBuffer.append("<span class='portlet-rss' style='margin:10px 0;'>")
						.append("Please config your weather services information in portlet.xml. If you don't register in The Weather Channel, please register ")
						.append("<a href='https://registration.weather.com/registration/xmloap/step1' target='_blank' >here</a>.")
						.append("</span>")
						;
			response.getWriter().print(resultBuffer.toString());
			return;
		}
			
		PortletObject wPortlet = getPortlet(request);		
		String param = wPortlet.getParameter();		
		if(param == null || "".equals(param)){
			String responseId = (String)request.getAttribute("responseId");		
			boolean continued = true;
			User user = this.getUser(request);
			String locName = request.getParameter("locName");
			if(locName != null){				
				resultBuffer.append(getSelectLocationView(request, responseId, locName, null ,request.getPortletMode()));
			}else{
				if(user != null){
					String weatherLoc = user.getCity();
					if("US".equals(user.getCountry()) && user.getPostalCode() != null)
						weatherLoc = user.getPostalCode();
					if(weatherLoc != null){
						resultBuffer.append(getSelectLocationView(request, responseId, weatherLoc,user.getCountry(), request.getPortletMode()));
						continued = false;
					}					
				}
				if(continued){
					resultBuffer.append(getSearchLocationView(responseId, request.getPortletMode()));
				}
			}
		}else{
			resultBuffer.append(this.getWeather(request));
		}
		resultBuffer.append("<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;'>")
					.append("<tr>")
					.append("<td class='portlet-footer' >")
					.append("<a href='http://www.weather.com/?prod=xoap&#38;par=1021498252' target= '_blank' >Powered by: The Weather Channel</a>")
					.append("</td>")
					.append("<td class='cright' >")
//					.append("<a href='http://www.weather.com/?prod=xoap&#38;par=1021498252' target= '_blank' ><img src='"+request.getContextPath()+"/light/images/weather/logos/TWClogo_32px.png' style='border: 0px' alt='' /></a>")
					.append("</td>")
					.append("</tr>")
					.append("</table>");				
		response.getWriter().print(resultBuffer.toString());
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		StringBuffer resultBuffer = new StringBuffer();		
		PortletObject wPortlet =getPortlet(request);		
		String param = wPortlet.getParameter();				
		String responseId = (String)request.getAttribute("responseId");
		String locName = request.getParameter("locName");
		if(locName == null){
			resultBuffer.append(getSearchLocationView(responseId, request.getPortletMode()));
		}else{
			resultBuffer.append(getSelectLocationView(request,responseId, locName, null, request.getPortletMode()));			
		}
		
		resultBuffer.append("<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;'>")
					.append("<tr>")
					.append("<td class='portlet-footer' >")
					.append("<a href='http://www.weather.com/?prod=xoap&#38;par=1021498252' target= '_blank' >Powered by: The Weather Channel</a>")
					.append("</td>")
					.append("<td class='cright' >")
//					.append("�<a href='http://www.weather.com/?prod=xoap&#38;par=1021498252' target= '_blank' ><img src='"+request.getContextPath()+"/light/images/weather/logos/TWClogo_32px.png' style='border: 0px' alt='' /></a>")
					.append("</td>")
					.append("</tr>")
					.append("</table>");		
		response.getWriter().print(resultBuffer.toString());
	 }	
	 
	 private String getSearchLocationView(String responseId, PortletMode mode) {
		 StringBuffer resultBuffer = new StringBuffer();
		 
		 resultBuffer.append("<form name='form_"+responseId+"'>")
		 			.append("<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;'>")
					.append("<tr>")
					.append("<td class='portlet-table-td-left'>")
					.append("Location:")
					.append("</td>")
					.append("<td class='portlet-table-td-left'>")
					.append("<input type='text' name='pwLocation' value='' class='portlet-form-input-field' size='16' ")
					
					;
		 if(mode.equals(PortletMode.VIEW)){
			 resultBuffer.append(" onkeypress='return keyDownSearchWeather(event,\""+responseId+"\");' /> ")
						 .append("<input name='Submit' type='button' value='Search' class='portlet-form-button'")
						 .append(" onclick=\"javascript:searchWeatherLocation('"+responseId+"');\" />");
		 }else{
			 resultBuffer.append(" onkeypress='return keyDownEditWeather(event,\""+responseId+"\");' /> ")
						 .append("<input name='Submit' type='button' value='Search' class='portlet-form-button'")
						 .append(" onclick=\"javascript:editWeatherLocation('"+responseId+"');\" />");
		 }
		 resultBuffer.append("</td>")
					.append("</tr>")
					.append("</table>")
					.append("</form>")
					;
		 return resultBuffer.toString();
	 }
	 private String getSelectLocationView(RenderRequest request, String responseId, String locName, String country, PortletMode mode) {
		 StringBuffer resultBuffer = new StringBuffer();
		 WeatherLocations locs = locSearchResults.get(locName);
		 //http://xoap.weather.com/weather/local/30339?cc=*&dayf=5&link=xoap&prod=xoap&par=1021498252&key=4c7efa4d82cd2b90
		 String searchUrl ="http://xoap.weather.com/search/search?where="+locName;
		if(locs == null){
			try{
				Document document = parse(searchUrl);
				locs = new WeatherLocations(document);
				locSearchResults.put(locName,locs);
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}else{
			Calendar lastTime = Calendar.getInstance();
			lastTime.setTimeInMillis(locs.getLastRefreshTime());
			lastTime.add(Calendar.MINUTE, 15);
			Calendar current = Calendar.getInstance();
			current.setTimeInMillis(System.currentTimeMillis());
			
			if(current.after(lastTime)){					
				try{
					Document document = parse(searchUrl);
					locs = new WeatherLocations(document);
					locSearchResults.put(locName,locs);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
		if(!locs.isErrorFound()){
			if(locs.getLocations().size() == 1){
				this.saveLocation(request,locs.getLocations().get(0).getId(),null,country);
				return this.getWeather(request);			
			}
			resultBuffer.append("<form name='form_"+responseId+"'>");
			resultBuffer.append("<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;'>")
			 			.append("<tr>")
						.append("<td class='portlet-table-td-left'>")
						.append("Unit: ")
						.append("<select name='pwUnit' size='1'  class='portlet-form-select'>")
						.append("<option value='m'>Celsius</option>")
						.append("<option value='s'>Fahrenheit</option>")
						.append("</select>")
						.append("</td>")
						.append("</tr>")								
						.append("<tr>")
						.append("<td class='portlet-table-td-left'>")
						.append("Please choose your Location:")
						.append("</td>")
						.append("</tr>")
						;
			for(WeatherLocation loc : locs.getLocations()){
				resultBuffer.append("<tr>")
							.append("<td class='portlet-table-td-left'>")
							.append("<INPUT TYPE='radio' NAME='pwLocId' value='"+loc.getId()+"'>")
							.append(loc.getName())
							.append("</INPUT>")
							.append("</td>")
							.append("</tr>")
							;
			}
			
			resultBuffer.append("<tr>")
						.append("<td class='cright'>")
						.append("<input name='Submit' type='button' value='OK' class='portlet-form-button'")
						.append(" onclick=\"javascript:selectWeatherLocation('"+responseId+"');\" />")
						.append("</td>")
						.append("</tr>")
						.append("</table>")
						.append("</form>")
						;
		}else{
			resultBuffer.append(locs.getError());
			resultBuffer.append(getSearchLocationView(responseId, mode));
		}
		return resultBuffer.toString();
	}
	 private Document parse(String url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
     }
	 
	 private String getWeather(RenderRequest request){
		StringBuffer resultBuffer = new StringBuffer();
		String uri = this.getInitParameter("url");
		String partnerId = this.getInitParameter("partnerId");
		String licence = this.getInitParameter("licence");
		PortletObject wPortlet =getPortlet(request);		
		String param = wPortlet.getParameter();	
		 try{
				String[] params=  param.split("-");
				Weather weather = weatherMaps.get(params[0]);
				String url = uri+params[0]+"?cc=*&dayf=1&link=xoap&prod=xoap&par="+partnerId+"&key="+licence+"&unit="+params[1];
				if(weather == null){								
					Document document = parse(url);
					weather = new Weather(document);
					weatherMaps.put(params[0],weather);
				}else{
					Calendar lastTime = Calendar.getInstance();
					lastTime.setTimeInMillis(weather.getLastRefreshTime());
					lastTime.add(Calendar.MINUTE, 30);
					Calendar current = Calendar.getInstance();
					current.setTimeInMillis(System.currentTimeMillis());
					
					if(current.after(lastTime)){					
						Document document = parse(url);
						weather.setDocument(document);
						weatherMaps.put(params[0],weather);
					}
				}
				if(!weather.isErrorFound()){
					String icon =weather.getIcon();
					if(icon.equals("-")) icon="0";
					resultBuffer.append("<span class='portlet-rss' style='margin:10px 0 0 0;'>")
					            .append(weather.getLocation()+" "+weather.getTime())
					            .append("</span>")
					            .append("<table border='0' cellpadding='0' cellspacing='0'>")
								.append("<tr>")
								.append("<td>")
									.append("<table border='0' cellpadding='0' cellspacing='0'>")
									.append("<tr>")
									.append("<td class='portlet-table-td-left'>")
						            .append("<img src='"+request.getContextPath()+"/light/images/weather/64x64/"+icon+".png' style='border: 0px' alt='' width='64' height='64' />")
									.append("</td>")
						            .append("</tr>")						
									.append("</table>")
								.append("</td>")
								.append("<td>")
									.append("<table border='0' cellpadding='0' cellspacing='0'>")
									.append("<tr>")
									.append("<td class='portlet-table-td-left' style='font-size:larger'>")
									.append(weather.getTmp()+" "+weather.getTmpUnit())
									.append("</td>")
						            .append("</tr>")
									.append("<tr>")
									.append("<td class='portlet-table-td-left'>")
						            .append(weather.getStatus())
						            .append("</td>")
						            .append("</tr>")
									.append("</table>")
								.append("</td>")
								.append("<td>")
									.append("<table border='0' cellpadding='0' cellspacing='0'>")
									.append("<tr>")
									.append("<td class='portlet-table-td-left'>")
						            .append("Wind:")
									.append("</td>")
									.append("<td class='portlet-table-td-left'>")
						            .append(weather.getTrend()+" "+weather.getSpeed()+" "+weather.getSpeedUnit())
									.append("</td>")
						            .append("</tr>")	
						            .append("<tr>")
									.append("<td class='portlet-table-td-left'>")
						            .append("Humidity:")
									.append("</td>")
									.append("<td class='portlet-table-td-left'>")
						            .append(weather.getHmid())
									.append("</td>")
						            .append("</tr>")
						            .append("<tr>")
									.append("<td class='portlet-table-td-left'>")
						            .append("Visibility:")
									.append("</td>")
									.append("<td class='portlet-table-td-left'>")
						            .append(weather.getVis()+" km")
									.append("</td>")
						            .append("</tr>")
									.append("</table>")
								.append("</td>")
								.append("</tr>")
								.append("</table>")
					            ;
				}else{
					resultBuffer.append(weather.getError());
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
			return resultBuffer.toString();
	 }
	 private void saveLocation(PortletRequest request, String locId, String unit, String country){
		 if(unit == null){
			 if("US".equals(country))
				 unit = "s";
			 else
				 unit = "m";
		 }
		 String param = locId+"-"+unit;
		 PortletObject wPortlet =getPortlet(request);
		 wPortlet.setParameter(param);
		 this.getPortalService(request).save(wPortlet);
	 }
}