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

package org.light.portal.model;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletObjectPreferences extends Entity {

	private PortletObject portletObject;
	private String name;
	private String value;
	private int status;
	private long portletId;
	
	public PortletObjectPreferences(){
		
	}
	
	public PortletObjectPreferences(String name, String value, long portletId){
		this.name = name;
		this.value = value;
		this.portletId = portletId;
	}
	
	public PortletObjectPreferences(String name, String value, long portletId, int status){
		this.name = name;
		this.value = value;
		this.portletId = portletId;
		this.status = status;
	}
	
	public PortletObjectPreferences(String name, String value, PortletObject portletObject){
		this.name = name;
		this.value = value;
		this.portletObject = portletObject;
	}
	
	public boolean isRemoved(){
		return status == 1 ? true:false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PortletObject getPortletObject() {
		return portletObject;
	}

	public void setPortletObject(PortletObject portletObject) {
		this.portletObject = portletObject;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getPortletId() {
		return portletId;
	}

	public void setPortletId(long portletId) {
		this.portletId = portletId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
