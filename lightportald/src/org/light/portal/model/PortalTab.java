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

import static org.light.portal.util.Constants._CLIENT_ALL;
import static org.light.portal.util.Constants._CLIENT_BROWSER;
import static org.light.portal.util.Constants._CLIENT_MOBILE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.util.StringUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalTab extends Entity {

	private String label;
	private String url;
	private int closeable;
	private int editable;
	private int moveable;
	private int allowAddContent;
	private String color;
	private int defaulted;
	private int between;
	private String widths;
	private String windowSkin;		
	private int fitScreen =1;
	private int status;//see Constants started with _PRIVACY_;
	private long parentId;//0 top level page;	
	private long portalId;
	private long orgId;	
	private String ownerId;
	private int client; //see Constants started with _CLIENT_;
	
	private Portal portal;
	private Set<PortletObject> portlets = new HashSet<PortletObject>();
	
	public PortalTab(){
		super();		
	}

	public PortalTab(String label
			,String url
			,int closeable			
			,String color
			,int defaulted
			,int between
			,String widths
			,String windowSkin					
			,long portalId
			,String ownerId
			,long orgId
			){
		this();
		try{			
			this.label =label;
			if(url == null)
				this.url="";
			else
				this.url = url;
			this.closeable = closeable;
			this.editable = 1;
			this.moveable = 1;
			this.allowAddContent = 1;
			if(color == null)
				this.color="";
			else
				this.color = color;
			this.defaulted = defaulted;
			this.between = between;
			this.widths = widths;
			this.windowSkin = windowSkin;		
			this.portalId = portalId;
			this.ownerId = ownerId;
			this.orgId = orgId;
		}catch(Exception e){			
		}
					
	}
	
	public PortalTab(String label
			,String url
			,int closeable
			,int editable
			,int moveable
			,int allowAddContent
			,String color
			,int defaulted
			,int between
			,String widths
			,String windowSkin					
			,long parentId
			,long portalId
			,String ownerId
			,long orgId
			){
			this();
			try{			
				this.label = label;
				if(url == null)
					this.url="";
				else
					this.url = url;
				this.closeable = closeable;
				this.editable = editable;
				this.moveable = moveable;
				this.allowAddContent = allowAddContent;
				if(color == null)
					this.color="";
				else
					this.color = color;
				this.defaulted = defaulted;
				this.between = between;
				this.widths = widths;
				this.windowSkin = windowSkin;						
				this.parentId = parentId;
				this.portalId = portalId;
				this.ownerId = ownerId;
				this.orgId = orgId;
			}catch(Exception e){
				
			}
					
	}
	public PortalTab(String label
			,String url
			,int closeable			
			,String color
			,int defaulted
			,int between
			,String widths
			,String windowSkin					
			,long parentId
			,long portalId
			,String ownerId
			,long orgId
			){
		this();
		try{			
			this.label =label;
			if(url == null)
				this.url="";
			else
				this.url = url;
			this.closeable = closeable;
			this.editable = 1;
			this.moveable = 1;
			this.allowAddContent = 1;
			if(color == null)
				this.color="";
			else
				this.color = color;
			this.defaulted = defaulted;
			this.between = between;
			this.widths = widths;
			this.windowSkin = windowSkin;					
			this.parentId = parentId;
			this.portalId = portalId;
			this.ownerId = ownerId;
			this.orgId = orgId;
		}catch(Exception e){			
		}
					
	}
	public PortalTab( PortalTab parent){
		this();
		this.label = parent.getLabel();
		this.url = parent.getUrl();
		this.closeable = parent.getCloseable();
		this.allowAddContent = parent.getAllowAddContent();
		this.between = parent.getBetween();
		this.color = parent.getColor();
		this.defaulted = parent.getDefaulted();
		this.editable = parent.getEditable();
		this.fitScreen = parent.getFitScreen();
		this.moveable = parent.getMoveable();
		this.parentId = parent.getId();
		this.status = parent.getStatus();
		this.windowSkin = parent.getWindowSkin();
		this.widths = parent.getWidths();
		this.portalId = parent.getPortalId();
		this.ownerId = parent.getOwnerId();
		this.orgId = parent.getOrgId();
	}
	
	public boolean isSupportMobile(){
		boolean ret = false;
		if(this.client == _CLIENT_ALL || this.client == _CLIENT_MOBILE)
			ret = true;
		
		return ret;
    }
	public boolean isSupportBrowser(){
		boolean ret = false;
		if(this.client == _CLIENT_ALL || this.client == _CLIENT_BROWSER)
			ret = true;
		
		return ret;
    }
	public boolean isHasUrl(){
		return StringUtil.isEmpty(url) ? false : true;
	}
	public String getTitle(){
		String title = PortalContextFactory.getPortalContext().getMessageByKey(this.label);
		return title;
	}
	public int getColumnTotal(){
		String[] widthCurrentArray = widths.split(",");
		return widthCurrentArray.length;
	}
	public List<String> getColumnWidths(){
		String[] widthCurrentArray = widths.split(",");
		return java.util.Arrays.asList(widthCurrentArray);
	}
	public boolean isTabCloseable(){
		boolean ret = false;
		if(this.closeable == 1)
			ret = true;
		return ret;
	}
	public boolean isTabEditable(){
		boolean ret = false;
		if(this.editable == 1)
			ret = true;
		return ret;
	}	
	public boolean isTabMoveable(){
		boolean ret = false;
		if(this.moveable == 1)
			ret = true;
		return ret;
	}
	public boolean isTabAllowAddContent(){
		boolean ret = false;
		if(this.allowAddContent == 1)
			ret = true;
		return ret;
	}
	public boolean isTabDefaulted(){
		boolean ret = false;
		if(this.defaulted == 1)
			ret = true;
		return ret;
	}
	
	public int getCloseable() {
		return closeable;
	}
	
	public void setCloseable(int closeable) {
		this.closeable = closeable;
	}
	public int getDefaulted() {
		return defaulted;
	}
	
	public void setDefaulted(int defaulted) {
		this.defaulted = defaulted;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Set<PortletObject> getPortlets() {
		return portlets;
	}
	public void setPortlets(Set<PortletObject> portlets) {
		this.portlets = portlets;
	}
	public int getBetween() {
		return between;
	}
	public void setBetween(int between) {
		this.between = between;
	}
	public String getWidths() {
		return widths;
	}
	public void setWidths(String widths) {
		this.widths = widths;
	}
	
	public Portal getPortal() {
		return portal;
	}
	public void setPortal(Portal portal) {
		this.portal = portal;
	}
	
	public int getAllowAddContent() {
		return allowAddContent;
	}

	public void setAllowAddContent(int allowAddContent) {
		this.allowAddContent = allowAddContent;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	public int getMoveable() {
		return moveable;
	}
	

	public void setMoveable(int moveable) {
		this.moveable = moveable;
	}

	public int getFitScreen() {
		return fitScreen;
	}

	public void setFitScreen(int fitScreen) {
		this.fitScreen = fitScreen;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getWindowSkin() {
		return windowSkin;
	}

	public void setWindowSkin(String windowSkin) {
		this.windowSkin = windowSkin;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getPortalId() {
		return portalId;
	}

	public void setPortalId(long portalId) {
		this.portalId = portalId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}
	

}
