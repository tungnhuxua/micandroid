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

package org.light.portal.portlet.core.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.factory.PortletURLFactory;


/**
 * 
 * @author Jianmin Liu
 **/
public class RenderResponseImpl extends PortletResponseImpl implements
		RenderResponse {

	 public RenderResponseImpl(PortletWindow portletWindow,javax.servlet.http.HttpServletRequest servletRequest,
             javax.servlet.http.HttpServletResponse servletResponse)
	{
		 super(portletWindow, servletRequest, servletResponse);
		 servletRequest.setAttribute("javax.portlet.response",this);
	}
	 
	 public PortletURL createRenderURL(){
        PortletURL url = createURL(false);
        return url;
    }

    public PortletURL createActionURL(){
        PortletURL url = createURL(true);
        return url;
    }

	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTitle(String arg0) {
		// TODO Auto-generated method stub

	}

	public OutputStream getPortletOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;//getHttpServletResponse().getOutputStream();
	}
	
	private PortletURL createURL(boolean isAction)
    {
        return PortletURLFactory.getInstance().getPortletURL(getPortletWindow(),
                                                 getHttpServletRequest(),
                                                 getHttpServletResponse(),
                                                 isAction);
    }
}
