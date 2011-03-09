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

package org.light.portlets.photo;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.light.portal.model.PortletObject;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portlets.rss.RssBean;
import org.light.portlets.rss.RssCacheFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class PhotoPortlet extends LightGenericPortlet {
	
	public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("edit".equals(action)){
			String feed = request.getParameter("feed");
			String title = request.getParameter("title");
			String icon = request.getParameter("icon");
			String url = request.getParameter("url");						
			PortletObject rssPortlet =getPortlet(request);		
			if(rssPortlet != null){
				rssPortlet.setLabel(title);
				rssPortlet.setIcon(icon);
				rssPortlet.setUrl(url);				
				rssPortlet.setParameter("feed="+feed);
				this.getPortalService(request).save(rssPortlet);
			}
		}
		
	  }
 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {		
		StringBuffer resultBuffer = new StringBuffer();
		PortletObject portlet = this.getPortlet(request);
		String parameter = portlet.getParameter();
		int ind = parameter.indexOf("=");
		String feed = parameter.substring(ind + 1);
		if(feed != null && !"".equals(feed)){
			HttpServletRequest httpServletRequest = (HttpServletRequest)request.getAttribute("httpServletRequest");
			String proxySet = httpServletRequest.getSession().getServletContext().getInitParameter("proxySet");
			if("true".equals(proxySet)){
				String proxyHost = httpServletRequest.getSession().getServletContext().getInitParameter("proxyHost");
				String proxyPort = httpServletRequest.getSession().getServletContext().getInitParameter("proxyPort");
				System.getProperties().put( "proxySet", proxySet );
				System.getProperties().put( "proxyHost", proxyHost );
				System.getProperties().put( "proxyPort", proxyPort );
			}
			try{
				List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
				String num = request.getParameter("number");
	        	int number = 0;
	        	if(num != null)
	        		number = Integer.parseInt(num);
	        	if(number >= lists.size()) number = 0;
	        	if(number < 0 ) number = lists.size() - 1;

	        	if(lists.get(number) != null){
		        	String img=lists.get(number).getDesc().toString();
		        	int index = img.indexOf("<a");
		        	String a = img.substring(index + 2);
		        	index = a.indexOf("<a");
		        	a = a.substring(index);
		        	index = a.indexOf(">");
		        	a = a.substring(0,index);
		        	index = img.indexOf("<img");
		        	img = img.substring(index);
		        	index = img.indexOf("/>");
		        	img = img.substring(0,index+2);
		        	request.setAttribute("currentNumber",number);
		        	request.setAttribute("currentTitle",lists.get(number).getTitle());
		        	request.setAttribute("currentImage",img);
		        	request.setAttribute("currentImageLink",lists.get(number).getLink());
		        	request.getPortletSession().setAttribute(feed,lists,PortletSession.APPLICATION_SCOPE);
		        	this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/photo/photoPortletView.jsp").include(request,response);
		        }	
			}catch(Exception e){
				resultBuffer.append(e.getMessage());
				response.getWriter().print(resultBuffer.toString());
			}
		}				
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		PortletObject portlet =getPortlet(request);		
		if(portlet != null){
			String feed = portlet.getParameter().substring(5,portlet.getParameter().length());			
			request.setAttribute("feed",feed);
			request.setAttribute("portlet",portlet);
			
		}
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/photo/photoPortletEdit.jsp").include(request,response);
	 }	
	 
}
