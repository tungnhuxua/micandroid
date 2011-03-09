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
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.light.portal.portlet.core.InternalActionResponse;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ActionResponseImpl extends PortletResponseImpl implements
		ActionResponse, InternalActionResponse {
    
	private Map renderParameters = new HashMap();
	
	public ActionResponseImpl(PortletWindow portletWindow,javax.servlet.http.HttpServletRequest servletRequest,
             javax.servlet.http.HttpServletResponse servletResponse)
	{
		 super(portletWindow, servletRequest, servletResponse);
	}
	
	public void setWindowState(WindowState state) throws WindowStateException {
		this.getPortletWindow().setWindowState(state);

	}

	public void setPortletMode(PortletMode mode) throws PortletModeException {
		this.getPortletWindow().setPortletMode(mode);
	}

	public void setRenderParameters(Map parameters)
    {        
        if (parameters == null) {
            throw new IllegalArgumentException("Render parameters must not be null.");
        }
        for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry)iter.next();
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Key must not be null and of type java.lang.String.");
            }
            if (!(entry.getValue() instanceof String[])) {
                throw new IllegalArgumentException("Value must not be null and of type java.lang.String[].");
            }
        }

        renderParameters = StringUtil.copyParameters(parameters);

    }
    
	public Map getRenderParameters(){
		return renderParameters;
	}
	
	public String getRenderParameter(String key){
		String value = null;
		String[] values =(String[] )renderParameters.get(key);
		if(values != null && values.length > 0)
			value= values[0];
		return value;
	}
    public void setRenderParameter(String key, String value)
    {     
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Render parameter key or value must not be null.");
        }

        renderParameters.put(key, new String[] {value});
    }
    
    public void setRenderParameter(String key, String[] values)
    {
        if (key == null || values == null || values.length == 0) {
            throw new IllegalArgumentException("Render parameter key or value must not be null or values be an empty array.");
        }

        renderParameters.put(key, StringUtil.copy(values));

    }

}
