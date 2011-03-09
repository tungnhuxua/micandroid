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

import static org.light.portal.util.Constants._DEFAULT_THEME;
import static org.light.portal.util.Constants._PORTAL_TITLE_DEFAULT_KEY;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Portal extends Entity{
	private String ownerId;
	private long orgId;
	private String title = _PORTAL_TITLE_DEFAULT_KEY;
	private String theme = PropUtil.getString(_DEFAULT_THEME);
	private String bgImage;
	private String bgPosition;
	private int bgRepeat; //0 repeat 1 no repeat 2 repeat x 3 repeat y
	private String headerImage;
	private String headerPosition;
	private int headerRepeat; //0 repeat 1 no repeat 2 repeat x 3 repeat y
	private int headerHeight;
	private String textFont;
	private int fontSize;
	private String textColor;	
	private int transparent = 0;
	private int showSearchBar = PropUtil.getInt("default.user.searchBar.show");
	private int maxShowTabs = PropUtil.getInt("default.user.maxShowTabs");
	
	public Portal(){
		super();		
	}
	
	public Portal( String ownerId, long orgId, String theme){
		this();
		this.ownerId = ownerId;
		this.orgId = orgId;
		if(theme != null && !"".equals(theme.trim())) this.theme = theme;	
	}
	
	public Portal( String ownerId, long orgId, String title, String theme){
		this(ownerId,orgId,theme);
		if(title != null && !"".equals(title.trim())) this.title = title;	
	}
	
	public String getPortalTitle(){
		String title = this.title;
		String[] titles = this.title.split("_");
		if(titles.length > 1){
			String title1= PortalContextFactory.getPortalContext().getMessageByKey(titles[1]);			
			title = titles[0] + title1;
		}else{
			title = PortalContextFactory.getPortalContext().getMessageByKey(this.title);				
		}

		return title;
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

	public String getBgImage() {
		return bgImage;
	}
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getHeaderHeight() {
		return headerHeight;
	}
	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}
	public String getHeaderImage() {
		return headerImage;
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	public int getTransparent() {
		return transparent;
	}
	public void setTransparent(int transparent) {
		this.transparent = transparent;
	}
	public int getShowSearchBar() {
		return showSearchBar;
	}
	public void setShowSearchBar(int showSearchBar) {
		this.showSearchBar = showSearchBar;
	}
	public String getTextColor() {
		return (textColor == null ? "":textColor);
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public String getBgPosition() {
		return bgPosition;
	}
	public void setBgPosition(String bgPosition) {
		this.bgPosition = bgPosition;
	}
	public int getBgRepeat() {
		return bgRepeat;
	}
	public void setBgRepeat(int bgRepeat) {
		this.bgRepeat = bgRepeat;
	}
	public String getHeaderPosition() {
		return headerPosition;
	}
	public void setHeaderPosition(String headerPosition) {
		this.headerPosition = headerPosition;
	}
	public int getHeaderRepeat() {
		return headerRepeat;
	}
	public void setHeaderRepeat(int headerRepeat) {
		this.headerRepeat = headerRepeat;
	}
	public String getTextFont() {
		return (textFont == null ? "":textFont);
	}
	public void setTextFont(String textFont) {
		this.textFont = textFont;
	}
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getMaxShowTabs() {
		return maxShowTabs;
	}

	public void setMaxShowTabs(int maxShowTabs) {
		this.maxShowTabs = maxShowTabs;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
}
