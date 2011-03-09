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
public class PortalRole extends Entity {
	private String roleId;
	private int allowLookAndFeel;
	private int allowLayout;
	private int allowAddTab;
	private int allowAddContent;
	private int allowSignIn;
	private int allowTurnOff;
	private String title;
	private String theme;
	
	public PortalRole(){
		super();
	}
	public PortalRole(String roleId,int allowLookAndFeel,int allowLayout,int allowAddTab
			,int allowAddContent,int allowSignIn,int allowTurnOff, String title, String theme){
		this();
		this.roleId = roleId;
		this.allowLookAndFeel = allowLookAndFeel;
		this.allowLayout = allowLayout;
		this.allowAddTab = allowAddTab;
		this.allowAddContent = allowAddContent;
		this.allowSignIn = allowSignIn;
		this.allowTurnOff = allowTurnOff;
		this.title = title;
		this.theme = theme;
	}
	public int getAllowAddContent() {
		return allowAddContent;
	}
	public void setAllowAddContent(int allowAddContent) {
		this.allowAddContent = allowAddContent;
	}
	public int getAllowAddTab() {
		return allowAddTab;
	}
	public void setAllowAddTab(int allowAddTab) {
		this.allowAddTab = allowAddTab;
	}
	public int getAllowLayout() {
		return allowLayout;
	}
	public void setAllowLayout(int allowLayout) {
		this.allowLayout = allowLayout;
	}
	public int getAllowLookAndFeel() {
		return allowLookAndFeel;
	}
	public void setAllowLookAndFeel(int allowLookAndFeel) {
		this.allowLookAndFeel = allowLookAndFeel;
	}
	public int getAllowSignIn() {
		return allowSignIn;
	}
	public void setAllowSignIn(int allowSignIn) {
		this.allowSignIn = allowSignIn;
	}
	public int getAllowTurnOff() {
		return allowTurnOff;
	}
	public void setAllowTurnOff(int allowTurnOff) {
		this.allowTurnOff = allowTurnOff;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
}
