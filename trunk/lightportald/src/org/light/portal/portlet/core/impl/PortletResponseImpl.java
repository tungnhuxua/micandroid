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

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author Jianmin Liu
 **/
public abstract class PortletResponseImpl extends HttpServletResponseWrapper
		implements PortletResponse {
	private PortletWindow portletWindow;
	private javax.servlet.http.HttpServletRequest httpServletRequest;
	private javax.servlet.http.HttpServletResponse httpServletResponse;
	private Map properties;
	
	public PortletResponseImpl(PortletWindow portletWindow,javax.servlet.http.HttpServletRequest servletRequest,
            javax.servlet.http.HttpServletResponse servletResponse){
		super(servletResponse);
		this.portletWindow = portletWindow;
		this.httpServletRequest = servletRequest;
		this.httpServletResponse = servletResponse;
	}
	
	public java.lang.String getContentType() {
		return this.getHttpServletRequest().getContentType();
	}
		 
	public void addProperty(String key, String value) 
    {
        if (key == null)
        {
            throw new IllegalArgumentException("Property key == null");
        }

        Map props = getProperties();
        
        String[] oldValues = (String[]) props.get(key);
        String[] newValues = null;
        if (oldValues == null)
        {
            newValues = new String[]{value}; 
        }
        else
        {
            int len = oldValues.length;
            newValues = new String[len+1];
            System.arraycopy(oldValues, 0, newValues, 0, len);
            newValues[len] = value;
        }
        props.put(key, newValues);

    }
    
    public void setProperty(String key, String value) 
    {
        if (key == null)
        {
            throw new IllegalArgumentException("Property key == null");
        }

        Map props = getProperties();
        
        String[] newValues = new String[]{value}; 
        props.put(key, newValues);

    }
    
    public String encodeURL(String path)
    {        
        return this.getHttpServletResponse().encodeURL(path);
    }
    
	public javax.servlet.http.HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
	public javax.servlet.http.HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public PortletWindow getPortletWindow() {
		return portletWindow;
	}
	
	private Map getProperties() {
        if (properties == null)
            properties = new HashMap();
        return properties;
    }
}
