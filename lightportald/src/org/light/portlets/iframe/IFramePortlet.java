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

package org.light.portlets.iframe;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.PortletObject;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class IFramePortlet  extends LightGenericPortlet {
	private static final String defaultURL = "";
	private static final String defaultHeight = "200px";
	private static final String defaultMaxHeight = "1000px";
	private static final String defaultWidth = "100%";
	private static final String defaultNonIFrameMessage = "Your browser does not support iframes";
	  
	public void processAction (ActionRequest request, ActionResponse	response)
		throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){
			PortletPreferences prefs = request.getPreferences();
            String url = request.getParameter("url"); 
            String title = request.getParameter("title");
            String urlIsReadOnly = request.getParameter("urlIsReadOnly");  
            String width = request.getParameter("width");
            String height = request.getParameter("height");
            String maxHeight = request.getParameter("maxHeight");
            String noIFrameMessage = request.getParameter("noiframemessage");
            boolean px = false;
            boolean save = true;
            StringBuffer message = new StringBuffer();
            if("0".equals(urlIsReadOnly)){
	            if(url != null){
		            if(!url.startsWith("http://") && !url.startsWith("https://")){
	                  message.append("URLs must start with 'http://'<br/>");
	                  return;
	            	}
		            prefs.setValue("iframeUrl", url);
	            }else{
	            	request.setAttribute("message", "Please input all required fields");
	            	return;
	            }
            }
            if(title != null){
            	PortletObject po = this.getPortlet(request);
            	po.setLabel(title);
            	this.getPortalService(request).save(po);
            }
            if((height != null) && (width != null) && (noIFrameMessage != null)){
            	try{
                  if(width.endsWith("px")){
                     px = true;
                     width = width.substring(0, width.length() - 2);
                  }else if(width.endsWith("%")){
                     width = width.substring(0, width.length() - 1);
                  }
                  Integer.parseInt(width);
            	}catch(NumberFormatException nfe){
                  save = false;
                  message.append("Width must be an integer<br/>");
            	}
            	try{
                  if(height.endsWith("px")){
                     height = height.substring(0, height.length() - 2);
                  }
                  Integer.parseInt(height);
               	}catch(NumberFormatException nfe){
                  save = false;
                  message.append("Height must be an integer<br/>");
               	}
               	try{
                  if(maxHeight.endsWith("px")){
                     maxHeight = maxHeight.substring(0, maxHeight.length() - 2);
                  }else if(maxHeight.endsWith("%")){
                	  maxHeight = maxHeight.substring(0, maxHeight.length() - 1);
                  }
                  Integer.parseInt(maxHeight);
               	}catch(NumberFormatException nfe){
                  save = false;
                  message.append("Max Height must be an integer<br/>");
               	}
               	if(save){
                  prefs.setValue("iframeHeight", height + "px");
                  prefs.setValue("iframeMaxHeight", maxHeight + "px");
                  prefs.setValue("iframeWidth", px ? width + "px" : width + "%");                  
                  prefs.setValue("iframeMessage", noIFrameMessage);
                  prefs.store();
                  response.setPortletMode(PortletMode.VIEW);
                  return;
              	}else{
            	   request.setAttribute("message", message.toString());
              	}
            }else{
            	request.setAttribute("message", "Please input all required fields");
            }
		}
    }
	   
    protected void doView (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
    {
    	setRenderAttributes(request);
    	if(((String)request.getAttribute("iframeUrl")).length() > 0 || request.getAttribute("urlIsReadOnly") != null)    	 
    		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/iframe/view.jsp").include(request,response);
    	else
    		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/iframe/edit.jsp").include(request,response);
	}

	protected void doEdit (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
	{
    	setRenderAttributes(request);
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/iframe/edit.jsp").include(request,response);
	}
	private String getUrl(RenderRequest request){
		String url = request.getParameter("url");
		if(url == null){
			PortletObject portlet =this.getPortlet(request);
			String param = portlet.getParameter();
			if(param != null && param.indexOf("url=") >= 0)
				url = param.substring(4);
		}
		return url;
	}
	private void setRenderAttributes(RenderRequest request)
    {
		PortletPreferences prefs = request.getPreferences();
		String url = getUrl(request);
		if(url == null)
			url = prefs.getValue("iframeUrl", IFramePortlet.defaultURL);
		else
			request.setAttribute("urlIsReadOnly", true);
       request.setAttribute("iframeUrl", url);
       request.setAttribute("iframeTitle", this.getPortlet(request).getTitle());
       request.setAttribute("iframeHeight", prefs.getValue("iframeHeight", IFramePortlet.defaultHeight));
       request.setAttribute("iframeMaxHeight", prefs.getValue("iframeMaxHeight", IFramePortlet.defaultMaxHeight));
       request.setAttribute("iframeWidth", prefs.getValue("iframeWidth", IFramePortlet.defaultWidth));
       request.setAttribute("iframeMessage", prefs.getValue("iframeMessage", IFramePortlet.defaultNonIFrameMessage));
    }
}
