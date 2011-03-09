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

import static org.light.portal.util.Constants._PORTLET_MODE_CONFIG;
import static org.light.portal.util.Constants._PORTLET_MODE_HEADER;
import static org.light.portal.util.Constants._PORTL_SERVER_INFO;

import java.util.Enumeration;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import org.light.portal.portlet.core.InternalPortletConfig;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalContextImpl implements PortalContext
{
   private InternalPortletConfig portletConfig;
   
   public PortalContextImpl() 
   {
      
   }
   
   public PortalContextImpl(InternalPortletConfig portletConfig) 
   {
      this.portletConfig = portletConfig;
   }
   // javax.portlet.PortalContext implementation -------------------------------------------------
   public java.lang.String getProperty(java.lang.String name)
   {
       if (name == null)
       {
           throw new IllegalArgumentException("Property name == null");
       }

       return name;
   }


   public java.util.Enumeration getPropertyNames()
   {
       return null;
   }

   public java.util.Enumeration getSupportedPortletModes()
   {   
	   Enumeration enume = portletConfig.getSupportedPortletModes(); 
	   if(enume != null)
		   return enume;
	   return new java.util.Enumeration()
	      {   	         
	          private int curr = 0;
	  		  private int end = 4;
			  private PortletMode[] states=new PortletMode[]{PortletMode.VIEW,
				  											 PortletMode.EDIT,
				  											 PortletMode.HELP,
				  											 new PortletMode(_PORTLET_MODE_CONFIG),
				  											 new PortletMode(_PORTLET_MODE_HEADER)};


	          public boolean hasMoreElements()
	          {
	              return (curr < end);
	          }

	          public Object nextElement()
	          {        	 
	              if (hasMoreElements())
	              {
	            	  return states[curr++];
	              }
	              else
	              {
	                  return null;
	              }
	          }
	      };      
   }

   public java.util.Enumeration getSupportedWindowStates()
   {
	   return new java.util.Enumeration()
	      {   	         
	          private int curr = 0;
	  		  private int end = 3;
			  private WindowState[] states=new WindowState[]{WindowState.NORMAL,WindowState.MINIMIZED,WindowState.MAXIMIZED};


	          public boolean hasMoreElements()
	          {
	              return (curr < end);
	          }

	          public Object nextElement()
	          {        	 
	              if (hasMoreElements())
	              {
	            	  return states[curr++];
	              }
	              else
	              {
	                  return null;
	              }
	          }
	      };      
   }

   public String getPortalInfo()
   {
	   return _PORTL_SERVER_INFO;
   }
   
}
