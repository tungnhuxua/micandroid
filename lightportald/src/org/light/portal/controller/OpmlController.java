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

package org.light.portal.controller;

import static org.light.portal.util.Constants._OPML_URL_PREFIX;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.RssFactory;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;

/**
 * 
 * @author Jianmin Liu
 **/
public class OpmlController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		int index = uri.lastIndexOf("/");		
		String path = uri.substring(index+1);
		
		if(uri.indexOf(_OPML_URL_PREFIX) >= 0){
			doOpml(request, response, path);
		}	
		
		chain.execute(request,response);
	}
	

	private void doOpml(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "no-store");
        User user = this.getUser(request);
        if(user != null){
	        if(uri.indexOf("allfeeds") > 0){
	        	if(this.isAdmin(request,user)){
	        		List<PortletObjectRef> list = this.getPortalService(request).getAllFeed();
	        		if(list != null){				
						String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/";
						(new File(dir)).mkdirs();
						String xml = dir+"allfeeds.opml";
						RssFactory.getInstance().getAllOpml(list,xml);
						request.getSession().getServletContext().getRequestDispatcher("/feed/allfeeds.opml").forward(request,response);				
					}else{
						response.getWriter().print("you don't have any feeds to export.");
					}
	        	}else{
	    			response.getWriter().print("this is unauthorized action.");
	    		}
	        }else{
	        	List<PortletObjectRef> list = this.getPortalService(request).getMyFeed(user.getUserId());			
				if(list != null){				
					String dir = request.getSession().getServletContext().getRealPath("/")+"/feed/";
					(new File(dir)).mkdirs();
					String xml = dir+"myfeeds.opml";
					RssFactory.getInstance().getOpml(list,xml);
					request.getSession().getServletContext().getRequestDispatcher("/feed/myfeeds.opml").forward(request,response);				
				}else{
					response.getWriter().print("you don't have any feeds to export.");
				}
			}
        }else{
			response.getWriter().print("this user is not available.");
		}
	}
}
