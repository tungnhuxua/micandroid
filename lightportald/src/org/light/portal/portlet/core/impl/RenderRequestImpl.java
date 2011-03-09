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

import static org.light.portal.util.Constants.ACTION_RESPONSE;
import static org.light.portal.util.Constants.METHOD_RENDER;
import static org.light.portal.util.Constants._CHARSET_UTF;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;

import org.light.portal.portlet.core.InternalActionResponse;
import org.light.portal.portlet.factory.PortletPreferencesFactory;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class RenderRequestImpl extends PortletRequestImpl implements
		RenderRequest {
	
	public RenderRequestImpl(PortletWindow portletWindow,javax.servlet.http.HttpServletRequest servletRequest)
	{
		super( portletWindow, servletRequest);
		servletRequest.setAttribute("javax.portlet.request",this);
	}
	
	public PortletPreferences getPreferences() {	
		return PortletPreferencesFactory.getInstance().getPortletPreferences(METHOD_RENDER, this, this.getPortletWindow());		
	}
	public String getParameter(String name) {
		InternalActionResponse actionResponse = (InternalActionResponse)this.getAttribute(ACTION_RESPONSE);
		String value = null;
		if(actionResponse != null)
			value = actionResponse.getRenderParameter(name);
		if(value == null)
			value = this.getHttpServletRequest().getParameter(name);
		if (value != null){
			try{
				value = URLDecoder.decode(value,_CHARSET_UTF);
			}catch(Exception e){}
		}
		return value;
	}

	public Enumeration getParameterNames() {		
		return new java.util.Enumeration()
	      {   
			
	          private int curr = 0;
	  		  private int end = getParameterMap().size();

	          public boolean hasMoreElements()
	          {  
	              return (curr < end);
	          }

	          public Object nextElement()
	          {        	 
	              if (hasMoreElements())
	              {
	            	  return getParameterMap().keySet().toArray()[curr++];
	              }
	              else
	              {
	                  return null;
	              }
	          }
	      };      
	}

	public String[] getParameterValues(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter name == null");
		}

		String[] values = (String[]) getParameterMap().get(name);
		if (values != null)
			values = StringUtil.copy(values);
		if (values != null){
			for(int i=0;i<values.length;i++){
				try{
					values[i] = URLDecoder.decode(values[i],_CHARSET_UTF);
				}catch(Exception e){}
			}
			values = StringUtil.copy(values);
		}
		return values;
	}

	public Map getParameterMap() {
		Map requestParameters = this.getHttpServletRequest().getParameterMap();
		Map result = StringUtil.copyParameters(requestParameters);
		InternalActionResponse actionResponse = (InternalActionResponse)this.getAttribute(ACTION_RESPONSE);
		if(actionResponse != null){
			Map renderParameters = actionResponse.getRenderParameters();		
			result.putAll(renderParameters);
		}
		return result;
	}
}