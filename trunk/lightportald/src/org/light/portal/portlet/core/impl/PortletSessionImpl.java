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

import java.util.Enumeration;
import java.util.Vector;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;
import javax.portlet.PortletSessionUtil;

import org.light.portal.model.PortletObject;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletSessionImpl implements PortletSession, javax.servlet.http.HttpSession
{
    private final int DEFAULT_SCOPE = PortletSession.PORTLET_SCOPE;

    private javax.servlet.http.HttpSession httpSession;
    private PortletContext portletContext = null;

    private PortletObject portletObject;

    public PortletSessionImpl(PortletObject portletObject,
                              javax.servlet.http.HttpSession httpSession)
    {
        this.portletObject = portletObject;
        this.httpSession = httpSession;
    }

    // javax.portlet.PortletSession and javax.servlet.http.HttpSession implementation -------------
    public Object getAttribute(String name)
    {
        return this.getAttribute(name, DEFAULT_SCOPE);
    }

    public Enumeration getAttributeNames()
    {
        return this.getAttributeNames(DEFAULT_SCOPE);
    }

    public long getCreationTime() throws java.lang.IllegalStateException
    {
        return httpSession.getCreationTime();
    }

    public String getId() throws java.lang.IllegalStateException
    {
        return httpSession.getId();
    }

    public long getLastAccessedTime() throws java.lang.IllegalStateException
    {
        return httpSession.getLastAccessedTime();
    }

    public int getMaxInactiveInterval()
    {
        return httpSession.getMaxInactiveInterval();
    }

    public void invalidate() throws java.lang.IllegalStateException
    {
        httpSession.invalidate();
    }

    public boolean isNew() throws java.lang.IllegalStateException
    {
        return httpSession.isNew();
    }

    public void removeAttribute(String name)
    {
        this.removeAttribute(name, DEFAULT_SCOPE);
    }

    public void setAttribute(String name, Object value)
    {
        this.setAttribute(name, value, DEFAULT_SCOPE);
    }

    public void setMaxInactiveInterval(int interval)
    {
        httpSession.setMaxInactiveInterval(interval);
    }
    // --------------------------------------------------------------------------------------------

    // javax.portlet.PortletSession implementation ------------------------------------------------
    public java.lang.Object getAttribute(String name, int scope) throws java.lang.IllegalStateException
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name must not be null");
        }
        if (scope==PortletSession.APPLICATION_SCOPE)
        {
            return httpSession.getAttribute(name);
        }
        else
        {
            Object attribute = null;
            if(portletObject != null)
            	attribute = httpSession.getAttribute("javax.portlet.p."+portletObject.getId()+"?"+name);
            if(attribute == null)
            {
                // not sure, if this should be done for all attributes or only javax.servlet.
                attribute = httpSession.getAttribute(name);
            }
            return attribute;
        }
    }

    public java.util.Enumeration getAttributeNames(int scope)
    {
        if (scope==PortletSession.APPLICATION_SCOPE)
        {
            return httpSession.getAttributeNames();
        }
        else
        {
            Enumeration attributes = httpSession.getAttributeNames();

            Vector portletAttributes = new Vector();

	        /* Fix that ONLY attributes of PORTLET_SCOPE are returned. */
            int prefix_length = "javax.portlet.p.".length();
            String portletWindowId = "";
            if(portletObject != null) 
            	portletWindowId = String.valueOf(portletObject.getId());
            
            while (attributes.hasMoreElements())
            {
                String attribute = (String)attributes.nextElement();

                int attributeScope = PortletSessionUtil.decodeScope(attribute);
                
                if (attributeScope == PortletSession.PORTLET_SCOPE && attribute.startsWith(portletWindowId, prefix_length))
                {
                	String portletAttribute = PortletSessionUtil.decodeAttributeName(attribute);
                	
                	if (portletAttribute!=null)
                	{ // it is in the portlet's namespace
                		portletAttributes.add(portletAttribute);
                	}
                }
           }

           return portletAttributes.elements();
        }
    }

    public void removeAttribute(String name, int scope) throws java.lang.IllegalStateException
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name must not be null");
        }
        if (scope == PortletSession.APPLICATION_SCOPE)
        {
            httpSession.removeAttribute(name);
        }
        else
        {
        	if(portletObject != null)
        		httpSession.removeAttribute("javax.portlet.p."+portletObject.getId()+"?"+name);
        	else
        		httpSession.removeAttribute(name);
        }
    }

    public void setAttribute(java.lang.String name, java.lang.Object value, int scope) throws IllegalStateException
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name must not be null");
        }
        if (scope==PortletSession.APPLICATION_SCOPE)
        {
            httpSession.setAttribute(name,value);
        }
        else
        {
        	if(portletObject != null)
        		httpSession.setAttribute("javax.portlet.p."+portletObject.getId()+"?"+name, value);
        	else
        		httpSession.setAttribute(name,value);
        }
    }

    public PortletContext getPortletContext()
    {
        return getInternalPortletContext();
    }
    // --------------------------------------------------------------------------------------------

    // javax.servlet.http.HttpSession implementation ----------------------------------------------
    public javax.servlet.ServletContext getServletContext()
    {
        // TBD, open issue. it would be good if we could also implement the ServletContext interface at the PortletContextImpl
        return httpSession.getServletContext();
    }
    
    /**
     * @deprecated As of Java(tm) Servlet API 2.1 for security reasons,
     * with no replacement. This interface will be removed 
     * in a future version of this API.
     */
    public javax.servlet.http.HttpSessionContext getSessionContext()
    {
        return httpSession.getSessionContext();
    }

    public Object getValue(String name)
    {
        return this.getAttribute(name, DEFAULT_SCOPE);
    }

    public String[] getValueNames()
    {
        // TBD
        return null;
    }

    public void putValue(String name, Object value)
    {
        this.setAttribute(name, value, DEFAULT_SCOPE);
    }

    public void removeValue(String name)
    {
        this.removeAttribute(name, DEFAULT_SCOPE);
    }
    // --------------------------------------------------------------------------------------------

    // internal methods ---------------------------------------------------------------------------
    private synchronized PortletContext getInternalPortletContext()
    {
        if (this.portletContext == null)
        {
            this.portletContext = new PortletContextImpl(getServletContext());
        }
        return this.portletContext;
    }
    // --------------------------------------------------------------------------------------------
}
