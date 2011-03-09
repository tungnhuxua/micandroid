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

import static org.light.portal.util.Constants._CHANNEL_FORUM;
import static org.light.portal.util.Constants._FORUM_INDEX;
import static org.light.portal.util.Constants._FORUM_URL_PREFIX;
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
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumPost;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		int index = uri.lastIndexOf("/");		
		String path = uri.substring(index+1);
		
		if(uri.indexOf(_FORUM_URL_PREFIX) >= 0 && (uri.indexOf(_REQUEST_SUFFIX) < 0)){
			String sub = uri.substring(uri.indexOf(_FORUM_URL_PREFIX)+_FORUM_URL_PREFIX.length());
			if(sub.indexOf("/") < 0 && sub.indexOf(".") < 0){
				doViewForum(request, response, path);
			}else{
				response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
			}
		}
		
		chain.execute(request,response);
	}
	
	private void doViewForum(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		String[] ids = path.split("-");
		Forum forum = null;
		ForumPost post = null;
		try{
			forum = this.getForumService(request).getForumById(Long.parseLong(ids[1]));
		}catch(Exception e){}
		try{
			post = this.getForumService(request).getPostById(Long.parseLong(ids[2]));
		}catch(Exception e){}		
		if(forum != null || post != null){				
			request.getSession().setAttribute("topic",post);
			request.getSession().setAttribute("categoryId",ids[0]);
			request.getSession().setAttribute("forumId",ids[1]);
			if(post != null) request.getSession().setAttribute("topicId",ids[2]);
			registerParameter(request);
			Portal portal = getPortalService(request).getPortalByChannel(_CHANNEL_FORUM,OrganizationThreadLocal.getOrganizationId());
			this.setVisitedPortal(request,portal);
			String locale = Locale.ENGLISH.toString();
			if(this.getUser(request) != null) locale = this.getUser(request).getLanguage();
			this.setLocale(request, locale);
			request.getSession().getServletContext().getRequestDispatcher(_FORUM_INDEX).forward(request,response);
		}else{
			response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
		}
	}
	
}
