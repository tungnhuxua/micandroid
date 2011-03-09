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

package org.light.portlets.group;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupCategory extends Entity {

	private String name;
	private long orgId;
	//read only
	private int groupCount;
	
	public GroupCategory(){
		super();
	}
	
	public GroupCategory(String name,long orgId){
		this();
		this.name = name;
		this.orgId = orgId;
	}
	
	public String getDisplayName(){
		String displayName = PortalContextFactory.getPortalContext().getMessageByKey(this.name);
		return displayName;
	}
		

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupCount() {
		return groupCount;
	}
	

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	

}
