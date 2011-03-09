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

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;

import org.light.portal.portlet.core.InternalPortletConfig;
import org.light.portal.portlet.definition.InitParam;
import org.light.portal.portlet.definition.SupportedLocale;
import org.light.portal.portlet.definition.Supports;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletConfigImpl implements PortletConfig,InternalPortletConfig
{
    private javax.servlet.ServletConfig servletConfig;
    private PortletContext portletContext;
    private org.light.portal.portlet.definition.Portlet portletDefinition;

    public PortletConfigImpl(javax.servlet.ServletConfig servletConfig,
                             PortletContext portletContext
                             ,org.light.portal.portlet.definition.Portlet portletDefinition
                             )
    {
        this.servletConfig = servletConfig;
        this.portletContext = portletContext;
        this.portletDefinition = portletDefinition;
    }

    // javax.portlet.PortletConfig implementation -------------------------------------------------
    public String getPortletName()
    {
        return portletDefinition.getPortletName().getContent();
    }

    public PortletContext getPortletContext()
    {
        return portletContext;
    }

    public ResourceBundle getResourceBundle(java.util.Locale locale)
    {   
    	String rb = portletDefinition.getPortletTypeChoice().getPortletTypeChoiceSequence().getResourceBundle().getContent();
    	if(locale == null){ 
			SupportedLocale[] locales =portletDefinition.getSupportedLocale();
			if(locales != null && locales.length > 0)
				locale = new Locale(locales[0].getContent());
			if(locale == null)
				locale = Locale.getDefault();
    	}
    	ResourceBundle res = ResourceBundle.getBundle(rb,locale);
    	if (res == null)
    	{
          res = ResourceBundle.getBundle(rb,Locale.getDefault());  
    	}
        return res; 
    }

    public String getInitParameter(String name)
    {	
		if (name == null)
        {
            throw new IllegalArgumentException("Parameter name == null");
        }
		String value=null;
    	InitParam[] initParam = portletDefinition.getInitParam();
    	if(initParam != null && initParam.length > 0){
    		for(int i=0;i<initParam.length;i++){
    			if(initParam[i].getName().getContent().equals(name)){
    				value = initParam[i].getValue().getContent();
    				break;
    			}
    		}
    	}
    	return value;
    	
    }

    public java.util.Enumeration getInitParameterNames()
    {
    	return new java.util.Enumeration()
      {   
          private InitParam[] initParam = portletDefinition.getInitParam();
          private int curr = 0;
  		  private int end = portletDefinition.getInitParam().length;

          public boolean hasMoreElements()
          {
              return (curr < end);
          }

          public Object nextElement()
          {        	 
              if (hasMoreElements())
              {
            	  return initParam[curr++].getName().getContent();
              }
              else
              {
                  return null;
              }
          }
      };      
    }
    //-----------------------------internal
	public java.util.Enumeration getSupportedPortletModes(){
		Supports[] supports = portletDefinition.getSupports();
		if(supports == null || supports.length == 0) return null;
		return new java.util.Enumeration()
	      {   
			Supports[] supports = portletDefinition.getSupports();
	          private int curr = 0;
	  		  private int end = supports[0].getPortletModeCount();

	          public boolean hasMoreElements()
	          {
	              return (curr < end);
	          }

	          public Object nextElement()
	          {        	 
	              if (hasMoreElements())
	              {
	            	  return new PortletMode(supports[0].getPortletMode(curr++).getContent());
	              }
	              else
	              {
	                  return null;
	              }
	          }
	      }; 
	}
	public java.util.Enumeration getSupportedWindowStates(){
		return null;
	}
    public javax.servlet.ServletConfig getServletConfig()
    {
        return servletConfig;
    }

}
