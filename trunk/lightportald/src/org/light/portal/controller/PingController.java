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

import static org.light.portal.util.Constants._PING_URL_PREFIX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Jianmin Liu
 **/
public class PingController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
				
		if(uri.toLowerCase().indexOf(_PING_URL_PREFIX) >= 0){
			doPing(request, response);
		}else		
			chain.execute(request,response);
	}
	
	private void doPing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		boolean ready = getPingService(request).isReady();
		if(ready){
			response.getWriter().append("200 -- Server is healthy");
		}else{
			response.setStatus(503);
			response.getWriter().append("503 --  Server is Unavailable");
		}
		
	}
	
}
