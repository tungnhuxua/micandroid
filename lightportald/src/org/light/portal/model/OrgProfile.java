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
public class OrgProfile extends Entity{

	private long orgId;
	private String language;
	private String meta;
	private String view;
	private String maxView;
	
	public OrgProfile(){
		super();
	}
	
	public OrgProfile(String language, String meta, String view, String maxView){
		this.language = language;
		this.meta = meta;
		this.view = view;
		this.maxView = maxView;
	}
	
	public OrgProfile(long orgId,String language, String meta, String view, String maxView){
		this.orgId = orgId;
		this.language = language;
		this.meta = meta;
		this.view = view;
		this.maxView = maxView;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMaxView() {
		return maxView;
	}

	public void setMaxView(String maxView) {
		this.maxView = maxView;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
}
