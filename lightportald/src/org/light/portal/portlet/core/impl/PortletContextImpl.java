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

import static org.light.portal.util.Constants._PORTL_SERVER_INFO;

import java.net.MalformedURLException;

import javax.portlet.PortletContext;
import javax.servlet.RequestDispatcher;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletContextImpl implements PortletContext
{   
    private javax.servlet.ServletContext servletContext;

    public PortletContextImpl(javax.servlet.ServletContext servletContext)
    {
        this.servletContext = servletContext;        
    }

    // javax.portlet.PortletContext implementation ------------------------------------------------
    public String getServerInfo()
    {
        return servletContext.getServerInfo()+" "+_PORTL_SERVER_INFO;
    }

    public javax.portlet.PortletRequestDispatcher getRequestDispatcher(String path)
    {
		try {
	        RequestDispatcher rd = servletContext.getRequestDispatcher(path);
            return rd == null?null:new PortletRequestDispatcherImpl(rd);
        } catch (Exception e) {
    		// need to catch exception because of tomcat 4.x bug
    		// tomcat throws an exception instead of return null
    		// if the path was not found
    		return null;
    	}

    }

    public javax.portlet.PortletRequestDispatcher getNamedDispatcher(String name)
    {
       	javax.servlet.RequestDispatcher rd = servletContext.getNamedDispatcher(name);
       	return rd != null ? new PortletRequestDispatcherImpl(rd)
           	              : null;
    }

    public java.io.InputStream getResourceAsStream(String path)
    {
        return servletContext.getResourceAsStream(path);
    }
    
    public int getMajorVersion()
    {
        return 1;//Environment.getMajorSpecificationVersion();
    }

    public int getMinorVersion()
    {
        return 1;//Environment.getMinorSpecificationVersion();
    }

    public String getMimeType(String file)
    {
        return servletContext.getMimeType(file);
    }

    public String getRealPath(String path)
    {
        return servletContext.getRealPath(path);
    }

    public java.util.Set getResourcePaths(String path)
    {
        return servletContext.getResourcePaths(path);
    }

    public java.net.URL getResource(String path) throws java.net.MalformedURLException
    {
        if (path == null || !path.startsWith("/"))
        {
            throw new MalformedURLException("path must start with a '/'");
        }
        return servletContext.getResource(path);
    }

    public java.lang.Object getAttribute(java.lang.String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Attribute name == null");
        }

        return servletContext.getAttribute(name);
    }

    public java.util.Enumeration getAttributeNames()
    {
        return servletContext.getAttributeNames();
    }

    public java.lang.String getInitParameter(java.lang.String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Parameter name == null");
        }

        return servletContext.getInitParameter(name);
    }

    public java.util.Enumeration getInitParameterNames()
    {
        return servletContext.getInitParameterNames();
    }

    public void log(java.lang.String msg)
    {
        servletContext.log(msg);
    }

    public void log(java.lang.String message, java.lang.Throwable throwable)
    {
        servletContext.log(message, throwable);
    }

    public void removeAttribute(java.lang.String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Attribute name == null");
        }

        servletContext.removeAttribute(name);
    }

    public void setAttribute(java.lang.String name, java.lang.Object object)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("Attribute name == null");
        }

        servletContext.setAttribute(name, object);
    }

    public String getPortletContextName()
    {
        return servletContext.getServletContextName();
    }
    
    public javax.servlet.ServletContext getServletContext()
    {
        return servletContext;
    }

}

