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

package org.light.portlets.widget;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.PortletObject;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.portlet.core.impl.RenderRequestImpl;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class WidgetPortlet  extends LightGenericPortlet {
	private static final String defaultHeight = "200px";
	private static final String defaultMaxHeight = "1000px";
	private static final String defaultWidth = "100%";
	private static final String defaultNonWidgetMessage = "please paste your widget code.";
	  
	public void processAction (ActionRequest request, ActionResponse	response)
		throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("save".equals(action)){          
            String title = request.getParameter("title");           
            String width = request.getParameter("width");
            String height = request.getParameter("height");
            String maxHeight = request.getParameter("maxHeight");
            String summary = request.getParameter("summary");
            String widgetCode = request.getParameter("widgetCode");
            boolean px = false;
            boolean save = true;
            StringBuffer message = new StringBuffer();            
            if(title != null){
            	PortletObject po = this.getPortlet(request);
            	po.setLabel(title);
            	this.getPortalService(request).save(po);
            }
            if((height != null) && (width != null) && (widgetCode != null)){
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
               		Widget widget = this.getPortletService(request).getWidgetByPortletId(this.getPortlet(request).getId());
            		if(widget != null){
            			widget.setTitle(title);
            			widget.setSummary(summary);
            			widget.setContent(widgetCode);
            			widget.setWidth(px ? width + "px" : width + "%");
            			widget.setHeight(height + "px");
            			widget.setMaxHeight(maxHeight + "px");
            		}else{
               			widget = new Widget(this.getUser(request).getId(),this.getPortlet(request).getId(),OrganizationThreadLocal.getOrganizationId(),
               				title,summary,widgetCode,px ? width + "px" : width + "%",height + "px",maxHeight + "px",0);
            		}
            		this.getPortalService(request).save(widget);
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
    	if(request.getAttribute("widgetCode") != null && ((String)request.getAttribute("widgetCode")).length() > 0)    	 
    		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/widget/view.jsp").include(request,response);
    	else
    		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/widget/edit.jsp").include(request,response);
	}

	protected void doEdit (RenderRequest request, RenderResponse response)
		throws PortletException, java.io.IOException
	{
    	setRenderAttributes(request);
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/widget/edit.jsp").include(request,response);
	}
	private String getUrl(RenderRequest request){
		StringBuilder buffer = new StringBuilder();
		buffer.append("http://")
			  .append(((RenderRequestImpl)request).getHttpServletRequest().getHeader("Host"))
			  .append(((RenderRequestImpl)request).getHttpServletRequest().getContextPath())
			  .append(PropUtil.getString("default.widget.page"))
			  .append("?id="+this.getPortlet(request).getId())
			  ;
		return buffer.toString();
	}
	private void setRenderAttributes(RenderRequest request)
    {
		request.setAttribute("iframeUrl", getUrl(request));
		request.setAttribute("iframeTitle", this.getPortlet(request).getTitle());
		Widget widget = this.getPortletService(request).getWidgetByPortletId(this.getPortlet(request).getId());
		if(widget != null){
			request.setAttribute("iframeHeight", widget.getHeight());
			request.setAttribute("iframeMaxHeight", widget.getMaxHeight());
			request.setAttribute("iframeWidth", widget.getWidth());
			request.setAttribute("widgetCode",widget.getContent());
		}else{
			request.setAttribute("iframeHeight", WidgetPortlet.defaultHeight);
			request.setAttribute("iframeMaxHeight", WidgetPortlet.defaultMaxHeight);
			request.setAttribute("iframeWidth", WidgetPortlet.defaultWidth);
		}
    }
}
