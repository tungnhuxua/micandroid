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

package org.light.portal.portlet.factory;

import static org.light.portal.util.Constants.PORTLET_REQUEST;
import static org.light.portal.util.Constants.SERVLET_REQUEST;

import javax.portlet.RenderRequest;

import org.light.portal.portlet.core.impl.PortletWindow;
import org.light.portal.portlet.core.impl.RenderRequestImpl;

/**
 * 
 * @author Jianmin Liu
 **/
public class RenderRequestFactory {
	private static RenderRequestFactory instance = new RenderRequestFactory();
	public static RenderRequestFactory getInstance(){
		return instance;
}
	 public RenderRequest getRenderRequest(PortletWindow portletWindow, javax.servlet.http.HttpServletRequest request){		 
		 RenderRequest renderRequest = new RenderRequestImpl(portletWindow, request);
		 renderRequest.setAttribute(SERVLET_REQUEST,request);
		 request.setAttribute(PORTLET_REQUEST,renderRequest);
		 return renderRequest;
	 }
}
