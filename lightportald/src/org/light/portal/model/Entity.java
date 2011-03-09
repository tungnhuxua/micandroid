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

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Jianmin Liu
 **/
public class Entity implements Serializable {
	
	private long id;
	private long orgId;
	private int version = -1;
	private Timestamp createDate = new Timestamp(System.currentTimeMillis());	
	private Timestamp modifiedDate;
	
	public boolean isNew(){
		return this.version == -1 ? true : false; 
	}
	
	/**
    * @see java.lang.Object#equals(Object)
    */
	public boolean equals(Object other) {
       if (!(other.getClass().equals(this.getClass()))) return false;
       if(this.hashCode()!=other.hashCode()) return false;
       return true;
	}
	
	/**
    * @see java.lang.Object#hashCode()
    */
	public int hashCode() {    	
        return new Long(id).hashCode();
    }
	
	/**
    * @see java.lang.Object#toString()
    */
	public String toString() {
       return this.getClass().getName()+getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
