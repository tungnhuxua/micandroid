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

import static org.light.portal.util.Constants._CHANNEL_BLOG;
import static org.light.portal.util.Constants._BLOG_INDEX;
import static org.light.portal.util.Constants._BLOG_URL_PREFIX;
import static org.light.portal.util.Constants._PORTAL_INDEX;
import static org.light.portal.util.Constants._REQUEST_SUFFIX;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.model.Portal;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portlets.blog.Blog;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlogController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		int index = uri.lastIndexOf("/");		
		String path = uri.substring(index+1);
		
		if(uri.indexOf(_BLOG_URL_PREFIX) >= 0 && (uri.indexOf(_REQUEST_SUFFIX) < 0)){
			String sub = uri.substring(uri.indexOf(_BLOG_URL_PREFIX)+_BLOG_URL_PREFIX.length());
			if(sub.indexOf("/") < 0 && sub.indexOf(".") < 0){
				doViewBlog(request, response, path);
			}else{
				response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
			}
		}
		
		chain.execute(request,response);
	}
	
	private void doViewBlog(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {		
		Blog blog = null;
		try{
			blog = this.getBlogService(request).getBlogById(Long.parseLong(path));
		}catch(Exception e){}		
		if(blog != null){				
			request.getSession().setAttribute("blog",blog);
			registerParameter(request);
			Portal portal = getPortalService(request).getPortalByChannel(_CHANNEL_BLOG,OrganizationThreadLocal.getOrganizationId());
			this.setVisitedPortal(request,portal);
			String locale = Locale.ENGLISH.toString();
			if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
			this.setLocale(request, locale);
			request.getSession().getServletContext().getRequestDispatcher(_BLOG_INDEX).forward(request,response);
		}else{
			response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
		}
	}
	
}
