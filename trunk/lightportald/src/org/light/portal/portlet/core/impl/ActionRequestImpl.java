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

import static org.light.portal.util.Constants.METHOD_ACTION;

import java.io.IOException;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;

import org.light.portal.portlet.factory.PortletPreferencesFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class ActionRequestImpl extends PortletRequestImpl implements
		ActionRequest {
		
	public ActionRequestImpl(PortletWindow portletWindow,javax.servlet.http.HttpServletRequest servletRequest) {
		super(portletWindow, servletRequest);	
	}
	
	public PortletPreferences getPreferences() {	
		return PortletPreferencesFactory.getInstance().getPortletPreferences(METHOD_ACTION, this, this.getPortletWindow());//new PortletPreferencesImpl(METHOD_ACTION, this, this.getPortletWindow());
	}
	
	public InputStream getPortletInputStream() throws IOException {
		javax.servlet.http.HttpServletRequest servletRequest = (javax.servlet.http.HttpServletRequest) super.getRequest();

        if (servletRequest.getMethod().equals("POST"))
        {
            String contentType=servletRequest.getContentType();
            if (contentType==null||contentType.equals("application/x-www-form-urlencoded"))
            {
                throw new java.lang.IllegalStateException(
                                                         "User request HTTP POST data is of type application/x-www-form-urlencoded. This data has been already processed by the portal/portlet-container and is available as request parameters."
                                                         );
            }
        }
        return servletRequest.getInputStream();
	}

}
