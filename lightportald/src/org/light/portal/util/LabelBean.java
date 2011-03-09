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

package org.light.portal.util;

import java.io.Serializable;

import org.light.portal.core.PortalContextFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class LabelBean implements Serializable{
	
	private String id;
	private String desc;
	private boolean defaulted;
	
	public LabelBean(){
		super();
	}
	
	public LabelBean(String id){
		this();
		this.id = id;
		this.desc = id;		
	}
	
	public LabelBean(String id,String desc){
		this();
		this.id = id;
		this.desc = desc;	
		this.defaulted = false;
	}
	
	public LabelBean(String id,String desc, boolean defaulted){
		this();
		this.id = id;
		this.desc = desc;	
		this.defaulted = defaulted;
	}
	public String getName(){
		return id;
	}
	public int getSelected(){
		return this.defaulted ? 1 : 0;
	}
	public String getDesc() {
		return PortalContextFactory.getPortalContext().getMessageByKey(desc);
	}
	

	public void setDesc(String desc) {
		this.desc = desc;
	}	

	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDefaulted() {
		return defaulted;
	}

	public void setDefaulted(boolean defaulted) {
		this.defaulted = defaulted;
	}
	
}
