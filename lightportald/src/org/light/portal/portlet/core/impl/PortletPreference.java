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

import org.light.portal.portlet.definition.Value;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletPreference {
	
	private String name;
	private String[] values;	
	private boolean readOnly;
	private boolean selected;
	private int index;
	
	public PortletPreference(String name, String[] values){
		this.name = name;
		this.values = values;
	}
	public PortletPreference(String name, String value){
		this.name = name;
		String[] values = new String[1];
		values[0]=value;
		this.values = values;
	}
	public PortletPreference(String name, String value, int index){
		this.name = name;
		String[] values = new String[1];
		values[0]=value;
		this.values = values;
		this.index = index;
	}
	public PortletPreference(String name, String value, boolean selected, int index){
		this.name = name;
		String[] values = new String[1];
		values[0]=value;
		this.values = values;
		this.selected = selected;
		this.index = index;
	}
	public PortletPreference(String name, Value[] value, boolean readOnly){
		this.name = name;
		if(value != null && value.length >0){
			String[] values = new String[value.length];
			for(int i=0;i<value.length;i++){
				values[i]= value[i].getContent();
			}
			this.values = values;
		}		
		this.readOnly = readOnly;		
	}
	
	public String toString(){		
		return name;
	}
	
	 /**
	  * @see java.lang.Object#hashCode()
	  */
	public int hashCode() {
		
		return getName().hashCode();
	} 
   
   	/**
   	 * @see java.lang.Object#equals(Object)
   	 */
    public boolean equals(Object other) {
        if ( !(other instanceof PortletPreference) ) return false;
        PortletPreference castOther = (PortletPreference) other;
        if(this.hashCode()!=castOther.hashCode()) return false;
        return true;
    }
    
//    public String getSelected() {
//		return selected ? "yes":"no";
//	}
    
    public String getValue() {
		return values == null ? null:values[0];
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getValues() {
		return values;
	}

	public void setValue(String[] values) {
		this.values = values;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isSelected() {
		return selected;
	}
	public int getIndex() {
		return index;
	}
	
}
