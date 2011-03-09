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

import org.light.portal.core.PortalContextFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletObjectRef extends Entity {
	
	private String name;
	private String path;
	private String label;
	private String icon;
	private String iconCssSprite;
	private String url;
	private String subTag;
	private String tag;
	private String language;
	private int refreshMode;
	private int editMode;
	private int helpMode;
	private int configMode;
	private int minimized;
	private int maximized;
	private String windowSkin;
	private int autoRefreshed;
	private int allowJS;
	private int pageRefreshed;
	private int periodTime;
	private int showNumber;
	private int showType;
	private int windowStatus;
	private int mode;
	private int type;
	private String parameter;		
	private String userId;
	private String createdBy;
	private String keywords;
	private long orgId;
	
	public PortletObjectRef(){
		super();
	}
	public PortletObjectRef(String name
						,String path
						,String label
						,String icon
						,String iconCssSprite
						,String url	
						,String subTag
						,String tag	
						,String language
						,int refreshMode
						,int editMode
						,int helpMode
						,int configMode
						,int minimized
						,int maximized
						,String windowSkin
						,int autoRefreshed
						,int periodTime
						,int allowJS
						,int pageRefreshed
						,int showNumber
						,int showType
						,int windowStatus
						,int mode
						,int type
						,String parameter
						,String userId){
	   this.name = name;
	   this.path = path;
	   this.label = label;
	   this.icon = icon;
	   this.iconCssSprite = iconCssSprite;
	   this.url = url;	
	   this.subTag = subTag;
	   this.tag = tag;
	   this.language = language;
	   this.refreshMode = refreshMode;
	   this.editMode = editMode;
	   this.helpMode = helpMode;
	   this.configMode = configMode;
	   this.minimized = minimized;
	   this.maximized = maximized;
	   this.windowSkin = windowSkin;
	   this.autoRefreshed = autoRefreshed;
	   this.periodTime = periodTime;
	   this.allowJS = allowJS;
	   this.pageRefreshed = pageRefreshed;
	   this.showNumber = showNumber;
	   this.showType = showType;
	   this.windowStatus = windowStatus;
	   this.mode = mode;
	   this.type = type;
	   this.parameter = parameter;
	   this.userId = userId;
	}
	public PortletObjectRef(String name
			,String path
			,String label
			,String icon
			,String iconCssSprite
			,String url	
			,String subTag
			,String tag	
			,String language
			,int refreshMode
			,int editMode
			,int helpMode
			,int configMode
			,int minimized
			,int maximized
			,String windowSkin
			,int autoRefreshed
			,int periodTime
			,int allowJS
			,int pageRefreshed
			,int showNumber
			,int showType
			,int windowStatus
			,int mode
			,int type
			,String parameter
			,String userId
			,String createdBy){
	this(name,path,label,icon,iconCssSprite,url,subTag,tag,language
			,refreshMode,editMode,helpMode,configMode,minimized,maximized,windowSkin
			,autoRefreshed,periodTime,allowJS,pageRefreshed,showNumber,showType
			,windowStatus,mode,type,parameter,userId);
	this.createdBy = createdBy;
	}
	
	public PortletObjectRef(PortletObjectRef ref, String userId){
		this(ref.getName(),ref.getPath(),ref.getLabel(),ref.getIcon(),ref.getIconCssSprite(),ref.getUrl(),ref.getSubTag(),ref.getTag(),ref.getLanguage()
				,ref.getRefreshMode(),ref.getEditMode(),ref.getHelpMode(),ref.getConfigMode(),ref.getMinimized(),ref.getMaximized(),ref.getWindowSkin()
				,ref.getAutoRefreshed(),ref.getPeriodTime(),ref.getAllowJS(),ref.getPageRefreshed(),ref.getShowNumber(),ref.getShowType()
				,ref.getWindowStatus(),ref.getMode(),ref.getType(),ref.getParameter(),userId);
	}
	
	public boolean isNeedPrefix(){
		boolean ret = false;
		if(this.icon != null)
			ret = this.icon.startsWith("/");
		return ret;
	}
	
	public String getSubTagTitle(){
		String title = PortalContextFactory.getPortalContext().getMessageByKey(this.subTag);		
		return title;
	}
	
	public String getTagTitle(){
		String title = PortalContextFactory.getPortalContext().getMessageByKey(this.tag);		
		return title;
	}
	
	public String getTitle(){
		String title = PortalContextFactory.getPortalContext().getMessageByKey(this.label);		
		return title;
	}
	
	public int getAllowJS() {
		return allowJS;
	}

	public void setAllowJS(int allowJS) {
		this.allowJS = allowJS;
	}

	public int getAutoRefreshed() {
		return autoRefreshed;
	}

	public void setAutoRefreshed(int autoRefreshed) {
		this.autoRefreshed = autoRefreshed;
	}

	public int getEditMode() {
		return editMode;
	}

	public void setEditMode(int editMode) {
		this.editMode = editMode;
	}

	public int getHelpMode() {
		return helpMode;
	}

	public void setHelpMode(int helpMode) {
		this.helpMode = helpMode;
	}

	public int getPageRefreshed() {
		return pageRefreshed;
	}

	public void setPageRefreshed(int pageRefreshed) {
		this.pageRefreshed = pageRefreshed;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getPeriodTime() {
		return periodTime;
	}

	public void setPeriodTime(int periodTime) {
		this.periodTime = periodTime;
	}

	public int getRefreshMode() {
		return refreshMode;
	}

	public void setRefreshMode(int refreshMode) {
		this.refreshMode = refreshMode;
	}


	public int getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getConfigMode() {
		return configMode;
	}
	public void setConfigMode(int configMode) {
		this.configMode = configMode;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public int getShowType() {
		return showType;
	}
	
	public void setShowType(int showType) {
		this.showType = showType;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getWindowStatus() {
		return windowStatus;
	}
	
	public void setWindowStatus(int windowStatus) {
		this.windowStatus = windowStatus;
	}
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubTag() {
		return subTag;
	}
	
	public void setSubTag(String subTag) {
		this.subTag = subTag;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getMaximized() {
		return maximized;
	}
	public void setMaximized(int maximized) {
		this.maximized = maximized;
	}
	public int getMinimized() {
		return minimized;
	}
	public void setMinimized(int minimized) {
		this.minimized = minimized;
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getIconCssSprite() {
		return iconCssSprite;
	}
	public void setIconCssSprite(String iconCssSprite) {
		this.iconCssSprite = iconCssSprite;
	}
	
}
