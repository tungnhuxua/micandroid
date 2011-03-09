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

import static org.light.portal.util.Constants.PORTLET_CONFIG;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.exolab.castor.xml.Unmarshaller;
import org.light.portal.Context;
import org.light.portal.layout.config.PortalLayout;
import org.light.portal.portlet.config.Portlets;
import org.light.portal.portlet.core.PortletContainer;
import org.light.portal.portlet.definition.PortletApp;
import org.light.portal.portlet.factory.ActionRequestFactory;
import org.light.portal.portlet.factory.ActionResponseFactory;
import org.light.portal.portlet.factory.RenderRequestFactory;
import org.light.portal.portlet.factory.RenderResponseFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletContainerImpl implements PortletContainer {
	
	private HashMap<String, Portlet> portlets = new HashMap<String, Portlet>();	
	private ServletConfig servletConfig;
	private Map<String,PortletConfig> portletConfigs = new HashMap<String,PortletConfig>();
	private PortletApp portletApp;
	private HashMap<String, org.light.portal.portlet.definition.Portlet> portletDefinitions = new HashMap<String, org.light.portal.portlet.definition.Portlet>();

	public void init(ServletConfig servletConfig) throws PortletException {
		try{
			InputStream stream = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/portlet.xml");
		    InputStreamReader rdr = new InputStreamReader( stream );
			PortletApp portletApp = (PortletApp)Unmarshaller.unmarshal(PortletApp.class, rdr);
			if(portletApp == null)
				throw new RuntimeException("Portal.xml configure error: null object.");
			
			InputStream stream2 = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/portlet-config.xml");
		    InputStreamReader rdr2 = new InputStreamReader( stream2 );
			Portlets portlets = (Portlets)Unmarshaller.unmarshal(Portlets.class, rdr2);	
			if(portlets == null)
				throw new RuntimeException("Portal-config.xml configure error: null object.");
			
			InputStream stream3 = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/portal-layout.xml");
			PortalLayout portalLayout = null;
		    try{
		    	javax.xml.bind.JAXBContext jc = javax.xml.bind.JAXBContext.newInstance( "org.light.portal.layout.config" );
				javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
				java.io.Reader reader = new InputStreamReader( stream3 );
				portalLayout = (PortalLayout)u.unmarshal(reader); 			
			}catch(Exception e){		
			}			
			if(portalLayout == null)
				throw new RuntimeException("Portal-layout.xml configure error: null object.");
			
			Context.getInstance().getPortalService(servletConfig.getServletContext()).init(portletApp,portlets,portalLayout);
			Context.getInstance().getReplicationServer(servletConfig.getServletContext()).init();
			this.init(servletConfig,portletApp);
		}catch(Exception e){
			throw new PortletException(e);
		}
	}
	
	private void init(ServletConfig servletConfig,PortletApp portletApp) throws PortletException{		
		this.servletConfig = servletConfig;
		this.portletApp = portletApp;
		org.light.portal.portlet.definition.Portlet[] portletDefinitionArrays = portletApp.getPortlet();
		if(portletDefinitions != null){
			if(portletDefinitionArrays.length > 0){
				for (int i=0;i<portletDefinitionArrays.length;i++ ) {		            	            		          
		            try{
		            	Portlet portlet = (Portlet)Class.forName(portletDefinitionArrays[i].getPortletClass()).newInstance();
		            	PortletConfig portletConfig = new PortletConfigImpl(
			        						servletConfig
			         					   ,new PortletContextImpl(servletConfig.getServletContext())
			         					   ,portletDefinitionArrays[i]);
						portletConfigs.put(portletDefinitionArrays[i].getPortletName().getContent(),portletConfig);
						portlet.init(portletConfig);
		            	portlets.put(portletDefinitionArrays[i].getPortletName().getContent(),portlet);
		            	portletDefinitions.put(portletDefinitionArrays[i].getPortletName().getContent(),portletDefinitionArrays[i]);		            	
		            }catch(Exception e){
						throw new PortletException(e);
					}
					
		        }
			}
		}
	}
	
	public boolean isInitialized() {		
		return (servletConfig == null)? false:true;
	}
	
	public org.light.portal.portlet.definition.Portlet getPortletDefinitionByName(String name){
		return this.portletDefinitions.get(name);
	}
	public void portletLoad(PortletWindow portletWindow,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws PortletException {
	}

	public void processPortletAction(PortletWindow portletWindow,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws PortletException, IOException {
		String name = (String)servletRequest.getAttribute("portletName"); 
		Portlet portlet = this.getPortlet(name);
		if(portlet != null){
			portlet.processAction(ActionRequestFactory.getInstance().getActionRequest(portletWindow, servletRequest)
							 ,ActionResponseFactory.getInstance().getActionResponse(portletWindow,servletRequest,servletResponse));
		}
	}

	public void renderPortlet(PortletWindow portletWindow,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws PortletException, IOException {
		String name = portletWindow.getName();
		PortletConfig portletConfig = portletConfigs.get(name);
		servletRequest.setAttribute(PORTLET_CONFIG,portletConfig);
		
		Portlet portlet = this.getPortlet(name);		
		if(portlet != null){			
			portlet.render(RenderRequestFactory.getInstance().getRenderRequest(portletWindow, servletRequest)
						  ,RenderResponseFactory.getInstance().getRenderResponse(portletWindow, servletRequest,servletResponse));
		}
		
	}

	public void shutdown() throws PortletException {
		
	}
    
	public Portlet getPortlet(String name){
		Portlet portlet = portlets.get(name);		
		if(portlet == null){
			synchronized (this){
				if(portlet == null){
					try{
						org.light.portal.portlet.definition.Portlet portletDefinition = portletDefinitions.get(name);				
						if(portletDefinition != null){
							portlet = (Portlet)Class.forName(portletDefinition.getPortletName().getContent()).newInstance();
							PortletConfig portletConfig = portletConfigs.get(name);
							if(portletConfig == null){
								portletConfig = new PortletConfigImpl(
					    						servletConfig
					     					   ,new PortletContextImpl(servletConfig.getServletContext())
					     					   ,portletDefinition);
								portletConfigs.put(name,portletConfig);
							}
							portlet.init(portletConfig);
							portlets.put(name,portlet);
						}				            	
					}catch(Exception e){
						
					}
				}
			}
		}
		return portlet;
	}
		
}
