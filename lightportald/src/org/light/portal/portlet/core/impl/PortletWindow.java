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

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import org.light.portal.model.PortletObject;
import org.light.portal.portlet.factory.PortletContainerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletWindow {
	private String name;
	private WindowState windowState;
	private PortletMode portletMode;
	private PortletObject portletObject;
	
	public PortletWindow (String name, WindowState windowState, PortletMode portletMode, PortletObject portletObject) {
		this.name = name;
		this.windowState = windowState;
		this.portletMode = portletMode;
		this.portletObject = portletObject;
	}
	
	public String getName(){
		return this.name;
	}
	
	public org.light.portal.portlet.definition.Portlet getDefition(){
		return PortletContainerFactory.getPortletContainer().getPortletDefinitionByName(this.name);
	}
	
	public PortletMode getPortletMode() {
		return this.portletMode;
	}

	public void setPortletMode(PortletMode portletMode) {
		this.portletMode = portletMode;
	}

	public PortletObject getPortletObject() {
		return portletObject;
	}

	public void setPortletObject(PortletObject portletObject) {
		this.portletObject = portletObject;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void setWindowState(WindowState windowState) {
		this.windowState = windowState;
	}
}
