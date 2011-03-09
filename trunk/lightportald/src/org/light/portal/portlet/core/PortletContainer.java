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

package org.light.portal.portlet.core;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.portlet.core.impl.PortletWindow;

/**
 * 
 * @author Jianmin Liu
 **/
public interface PortletContainer {
	public void init(ServletConfig servletConfig) throws PortletException;
	public boolean isInitialized();
	public Portlet getPortlet(String name);
	public void portletLoad(PortletWindow portletWindow, HttpServletRequest servletRequest, HttpServletResponse servletResponse ) throws PortletException;
	public void processPortletAction(PortletWindow portletWindow, HttpServletRequest servletRequest, HttpServletResponse servletResponse ) throws PortletException, IOException;
	public void renderPortlet(PortletWindow portletWindow, HttpServletRequest servletRequest, HttpServletResponse servletResponse ) throws PortletException, IOException;
	public void shutdown() throws PortletException;
	public org.light.portal.portlet.definition.Portlet getPortletDefinitionByName(String name);
}
