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

import static org.light.portal.util.Constants._CLIENT_ALL;
import static org.light.portal.util.Constants._CLIENT_BROWSER;
import static org.light.portal.util.Constants._CLIENT_MOBILE;
import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._MOBILE;
import static org.light.portal.util.Constants._MOBILE_INDEX;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_TABS_MAX;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_TABS_MAX_DEFAULT;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_VERSION;
import static org.light.portal.util.Constants._PRIVACY_PROFILE;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import static org.light.portal.util.Constants._ROLE_NO_PROFILE;
import static org.light.portal.util.Constants._ROLE_PROFILE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.portlet.Portlet;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.model.ContentCategory;
import org.light.portal.model.ContentSubCategory;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserInvite;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.portlet.factory.PortletContainerFactory;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.ConnectionType;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;

/**
 * 
 * @author Jianmin Liu
 **/
public class JSONUtil {

	public static JSONObject parse(String jsonString){
		JSONObject json = null;
		try{
			json = new JSONObject(jsonString);  
		}catch(Exception e){}
		return json;
	}
	
	public static JSONArray parseArray(String jsonString){
		JSONArray jarray = null;
		try{
			jarray = new JSONArray(jsonString);  
		}catch(Exception e){}
		return jarray;
	}
	
	public static JSONObject getPortalData(HttpServletRequest request, Portal portal, String title, User portalOwner, long portalOwnerId, User user, boolean authorized) throws Exception{
		String browserInfo = request.getHeader("User-Agent");
		if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && ValidationUtil.isSmallMobile(browserInfo)) return getPortalData(request,portal,title,portalOwner,portalOwnerId,user,authorized,true);
		JSONObject json = new JSONObject();  
		Organization org = OrganizationThreadLocal.getOrg();
		if(org == null && user != null)
			org = Context.getInstance().getUserService(request).getOrgById(user.getOrgId());
		json.put("userId",(user != null && org != null && user.getId() != org.getUserId()) ? user.getUserId() : "");
		json.put("title", (user != null && org != null && user.getId() != org.getUserId()) ? title : "");
		json.put("transparent", (portal.getTransparent() == 1) ? true : false);
		json.put("locale", Context.getInstance().getLocale(request));
		json.put("bgImage", (portal.getBgImage()==null) ? "" : portal.getBgImage());
		json.put("bgPosition", (portal.getBgPosition()==null) ? "" : portal.getBgPosition());
		json.put("bgRepeat", portal.getBgRepeat());
		json.put("headerImage", (portal.getHeaderImage()==null) ? "" : portal.getHeaderImage());
		json.put("headerPosition", (portal.getHeaderPosition()==null) ? "" : portal.getHeaderPosition());		
		json.put("headerRepeat", portal.getHeaderRepeat());
		json.put("headerHeight", portal.getHeaderHeight());
		json.put("fontSize", portal.getFontSize());
		json.put("showSearchBar", (portal.getShowSearchBar() == 1) ? true : false);
		json.put("textColor", portal.getTextColor());
		json.put("textFont", portal.getTextFont());
		json.put("theme", (ConfigurationUtil.allowRandomTheme() && portalOwner != null && portalOwner.getId() == OrganizationThreadLocal.getOrg().getUserId()) ? ConfigurationUtil.getRandomTheme() : portal.getTheme());
		json.put("logo", OrganizationThreadLocal.getLogo());
		json.put("maxShowTabs", portal.getMaxShowTabs());
		json.put("bgMusic", (portalOwner != null && portalOwner.getMyMusicAutoPlay() != 0 && portalOwner.getMusicUrl() != null && (portalOwnerId == user.getId() || user.getOtherMusucAutoPlay() == 1)) ? portalOwner.getMusicUrl() : "none");
		json.put("permissions",user.getAllPermission());		
		return json;			
	}			
	
	public static JSONObject getPortalData(HttpServletRequest request, Portal portal, String title, User portalOwner, long portalOwnerId, User user, boolean authorized, boolean mobile) throws Exception{
		if(!mobile) return getPortalData(request,portal,title,portalOwner,portalOwnerId,user,authorized);
		JSONObject json = new JSONObject();  
		json.put("userId",(user != null && !user.getUserId().equals(OrganizationThreadLocal.getDefaultUserId()))? user.getUserId() : "");
		json.put("title", title);
		json.put("transparent", (portal.getTransparent() == 1) ? true : false);
		json.put("locale", Context.getInstance().getLocale(request));
		json.put("bgImage", (portal.getBgImage()==null) ? "" : portal.getBgImage());
		json.put("bgPosition", (portal.getBgPosition()==null) ? "" : portal.getBgPosition());
		json.put("bgRepeat", portal.getBgRepeat());
		json.put("headerImage", (portal.getHeaderImage()==null) ? "" : portal.getHeaderImage());
		json.put("headerPosition", (portal.getHeaderPosition()==null) ? "" : portal.getHeaderPosition());		
		json.put("headerRepeat", portal.getHeaderRepeat());
		json.put("headerHeight", portal.getHeaderHeight());
		json.put("fontSize", portal.getFontSize());
		json.put("showSearchBar", (portal.getShowSearchBar() == 1) ? true : false);
		json.put("textColor", portal.getTextColor());
		json.put("textFont", portal.getTextFont());
		json.put("theme", (ConfigurationUtil.allowRandomTheme() && portalOwner != null && portalOwner.getId() == OrganizationThreadLocal.getOrg().getUserId()) ? ConfigurationUtil.getRandomTheme() : portal.getTheme());
		json.put("logo", OrganizationThreadLocal.getLogo());
		json.put("maxShowTabs", PropUtil.getInt(_PORTAL_MOBILE_BROWSER_TABS_MAX,_PORTAL_MOBILE_BROWSER_TABS_MAX_DEFAULT));
		json.put("bgMusic", "none");
		json.put("permissions",user.getAllPermission());			
		return json;			
	}
	
	public static JSONArray getPortalTabData(HttpServletRequest request,List<PortalTab> tabs, boolean defaultAuthorized,boolean isAdmin, User user, int start) throws Exception{
		String browserInfo = request.getHeader("User-Agent");
		String clientUrl = request.getParameter("clientUrl");
		boolean mobile = ValidationUtil.isSmallMobile(browserInfo);
		if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && (mobile || request.getAttribute(_MOBILE) != null || (clientUrl != null && clientUrl.indexOf(PropUtil.getString(_MOBILE_INDEX)) > 0))) 
			return getPortalTabData(request,tabs,defaultAuthorized,isAdmin,user,start,true);
		int i = start;
		if(tabs != null){
			JSONArray jArray = new JSONArray();
			for(PortalTab tab : tabs){
				if(tab != null && (tab.getClient() == _CLIENT_ALL || tab.getClient() == _CLIENT_BROWSER)){
					boolean authorized = defaultAuthorized || tab.getOwnerId().equals(user.getUserId());
					int privacy = (tab.getOwnerId().equals(OrganizationThreadLocal.getDefaultUserId())
							|| tab.getOwnerId().equals(_ROLE_NO_PROFILE)
							|| tab.getOwnerId().equals(_ROLE_PROFILE)) ? 0 : 1;			
					if((privacy == 1 && (tab.getStatus() == _PRIVACY_PUBLIC) 				      
										|| (tab.getStatus() == _PRIVACY_PROFILE && user.getProfileFriendViewOnly() == 0)
										|| (tab.getOwnerId().startsWith(_GROUP_PREFIX) && Context.getInstance().getVisitedGroup(request) != null)))
						privacy = 0;				
					String[] widths = tab.getWidths().split(",");
					JSONArray widthArray = new JSONArray();
					for(String width : widths){
						widthArray.put(Integer.parseInt(width));
					}
					JSONObject json = new JSONObject();  
					json.put("index",i++);
					json.put("id",tab.getId());
					json.put("label",tab.getTitle());
					json.put("client",tab.getClient());
					json.put("URL",tab.getUrl());
					json.put("isCloseable",isAdmin ? true : (authorized ? tab.isTabCloseable() : false ));
					json.put("isEditable",isAdmin ? true : (authorized ? tab.isTabEditable() : false ));
					json.put("isMoveable",isAdmin ? true : (authorized ? tab.isTabMoveable() : false ));
					json.put("allowAddContent",isAdmin ? true : (authorized ? tab.isTabAllowAddContent() : false ));
					json.put("color",tab.getColor());
					json.put("defaulted",tab.isTabDefaulted());
					json.put("between",tab.getBetween());
					json.put("widths",widthArray);
					json.put("fitScreen",tab.getFitScreen());
					json.put("windowSkin",tab.getWindowSkin());
					json.put("parentId",tab.getParentId());		
					json.put("privacy",privacy);
					List<PortletObject> portlets = Context.getInstance().getPortalService(request).getPortletsByTab(tab.getId());
					int notifications = 0;
					for(PortletObject po : portlets){
						Portlet portlet = PortletContainerFactory.getPortletContainer().getPortlet(po.getName());
						if(portlet instanceof LightGenericPortlet){
							request.setAttribute("portletId",po.getId());
							Object notification = ((LightGenericPortlet)portlet).doNotification(request);
							if(notification instanceof JSONObject){
								JSONObject jsonNotification = (JSONObject)notification;		
								if(jsonNotification.has("notification")) notifications += jsonNotification.getInt("notification");
							}
						}
					}
					if(notifications > 0) json.put("notification",notifications);
					jArray.put(json);
				}
			}
			return 	jArray;
		}
		return new JSONArray();
	}
	
	public static JSONArray getPortalTabData(HttpServletRequest request,List<PortalTab> tabs, boolean defaultAuthorized,boolean isAdmin, User user, int start, boolean mobile) throws Exception{
		if(!mobile) return getPortalTabData(request,tabs,defaultAuthorized,isAdmin,user,start);
		int i = start;
		if(tabs != null){
			JSONArray jArray = new JSONArray();
			for(PortalTab tab : tabs){
				if(tab != null && (tab.getClient() == _CLIENT_ALL || tab.getClient() == _CLIENT_MOBILE)){
					boolean authorized = defaultAuthorized || tab.getOwnerId().equals(user.getUserId());
					int privacy = (tab.getOwnerId().equals(OrganizationThreadLocal.getDefaultUserId())
							|| tab.getOwnerId().equals(_ROLE_NO_PROFILE)
							|| tab.getOwnerId().equals(_ROLE_PROFILE)) ? 0 : 1;			
					if((privacy == 1 && (tab.getStatus() == _PRIVACY_PUBLIC) 				      
										|| (tab.getStatus() == _PRIVACY_PROFILE && user.getProfileFriendViewOnly() == 0)
										|| (tab.getOwnerId().startsWith(_GROUP_PREFIX) && Context.getInstance().getVisitedGroup(request) != null)))
						privacy = 0;				
					JSONArray widthArray = new JSONArray();
					widthArray.put(300);
					JSONObject json = new JSONObject();  
					json.put("index",i++);
					json.put("id",tab.getId());
					json.put("label",tab.getTitle());
					json.put("client",tab.getClient());
					json.put("URL",tab.getUrl());
					json.put("isCloseable",false);
					json.put("isEditable",false);
					json.put("isMoveable",false);
					json.put("allowAddContent",false);
					json.put("color",tab.getColor());
					json.put("defaulted",tab.isTabDefaulted());
					json.put("between",tab.getBetween());
					json.put("widths",widthArray);
					json.put("fitScreen",1);
					json.put("windowSkin",tab.getWindowSkin());
					json.put("parentId",tab.getParentId());		
					json.put("privacy",privacy);
					List<PortletObject> portlets = Context.getInstance().getPortalService(request).getPortletsByTab(tab.getId());
					int notifications = 0;
					for(PortletObject po : portlets){
						Portlet portlet = PortletContainerFactory.getPortletContainer().getPortlet(po.getName());
						if(portlet instanceof LightGenericPortlet){
							request.setAttribute("portletId",po.getId());
							Object notification = ((LightGenericPortlet)portlet).doNotification(request);
							if(notification instanceof JSONObject){
								JSONObject jsonNotification = (JSONObject)notification;		
								if(jsonNotification.has("notification")) notifications += jsonNotification.getInt("notification");
							}
						}
					}
					if(notifications > 0) json.put("notification",notifications);
					jArray.put(json);
				}
			}
			return 	jArray;
		}
		return new JSONArray();
	}
	
	public static JSONArray getPortletData(List<PortletObject> portlets, long tabId, boolean authorized, boolean isAdmin, User user, HttpServletRequest request) throws Exception{
		String browserInfo = request.getHeader("User-Agent");
		String clientUrl = request.getParameter("clientUrl");
		boolean mobile = ValidationUtil.isSmallMobile(browserInfo);
		if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && (mobile || request.getAttribute(_MOBILE) != null || (clientUrl != null && clientUrl.indexOf(PropUtil.getString(_MOBILE_INDEX)) > 0))) 
			return getPortletData(portlets,tabId,authorized,isAdmin,user,request,true);
		if(portlets != null){
			JSONArray jArray = new JSONArray();
			for(PortletObject portlet : portlets){	
				if(!portlet.isSupportBrowser()) continue;
				JSONObject json = new JSONObject();  
				json.put("windowSkin",(portlet.getWindowSkin() == null) ? "" : portlet.getWindowSkin());
				json.put("serverId",portlet.getId());
				json.put("column", portlet.getColumn());
				json.put("colspan",portlet.getColspan());
				json.put("title",portlet.getTitle());				
				json.put("icon",portlet.getIconImage());
				json.put("url",portlet.getUrl());
				json.put("name",portlet.getName());
				json.put("path",portlet.getPath());
				json.put("closeable",isAdmin? true : authorized ? portlet.isSupportCloseable() : false);
				json.put("refreshMode", portlet.isSupportRefreshMode());
				json.put("editMode",isAdmin? true : authorized ? portlet.isSupportEditMode() : false);
				json.put("helpMode",isAdmin? true : authorized ? portlet.isSupportHelpMode() : false);
				json.put("configMode",isAdmin? true : authorized ? portlet.isSupportConfigMode() : false);
				json.put("allowMinimized", portlet.isSupportMinimized());
				json.put("allowMaximized", portlet.isSupportMaximized());
				json.put("autoRefreshed",authorized ? portlet.isSupportAutoRefreshed() : false);
				json.put("refreshAtClient",portlet.isSupportRefreshAtClient());
				json.put("periodTime",authorized ? portlet.getPeriodTime() : 0);
				json.put("showNumber",portlet.getShowNumber());
				json.put("allowJS",portlet.isSupportJS());
				json.put("barBgColor",(portlet.getBarBgColor() == null ? "" : portlet.getBarBgColor()));
				json.put("barFontColor",(portlet.getBarFontColor() == null ? "" : portlet.getBarFontColor()));
				json.put("contentBgColor",(portlet.getContentBgColor()== null ? "" : portlet.getContentBgColor()));
				json.put("textColor",(portlet.getTextColor()== null ? "" : portlet.getTextColor()));
				json.put("parameter",(portlet.getParameter()== null ? "" : portlet.getParameter()));
				json.put("initState",portlet.getWindowStatus());
				json.put("initMode",portlet.getMode());
				json.put("transparent",portlet.getTransparent());
				json.put("showIcon",portlet.getShowIcon());
				json.put("type",portlet.getType());
				json.put("marginTop",portlet.getMarginTop());
				json.put("client",portlet.getClient());
				jArray.put(json);				
			}
			return jArray;		
		}
		return new JSONArray();
	}
	
	public static JSONArray getPortletData(List<PortletObject> portlets, long tabId, boolean authorized, boolean isAdmin, User user, HttpServletRequest request, boolean mobile) throws Exception{
		if(!mobile) return getPortletData(portlets,tabId,authorized,isAdmin,user,request);
		if(portlets != null){
			JSONArray jArray = new JSONArray();
			for(PortletObject portlet : portlets){	
				if(!portlet.isSupportMobile()) continue;
				JSONObject json = new JSONObject();  
				json.put("windowSkin",(portlet.getWindowSkin() == null) ? "" : portlet.getWindowSkin());
				json.put("serverId",portlet.getId());
				json.put("column", mobile ? 0 : portlet.getColumn());
				json.put("colspan",portlet.getColspan());
				json.put("title",portlet.getTitle());				
				json.put("icon",portlet.getIconImage());
				json.put("url",portlet.getUrl());
				json.put("name",portlet.getName());
				json.put("path",portlet.getPath());
				json.put("closeable",isAdmin? true : authorized ? portlet.isSupportCloseable() : false);
				json.put("refreshMode", portlet.isSupportRefreshMode());
				json.put("editMode",isAdmin? true : authorized ? portlet.isSupportEditMode() : false);
				json.put("helpMode",isAdmin? true : authorized ? portlet.isSupportHelpMode() : false);
				json.put("configMode",isAdmin? true : authorized ? portlet.isSupportConfigMode() : false);
				json.put("allowMinimized", true);
				json.put("allowMaximized", false);
				json.put("autoRefreshed",authorized ? portlet.isSupportAutoRefreshed() : false);
				json.put("refreshAtClient",portlet.isSupportRefreshAtClient());
				json.put("periodTime",authorized ? portlet.getPeriodTime() : 0);
				json.put("showNumber",portlet.getShowNumber());
				json.put("allowJS",portlet.isSupportJS());
				json.put("barBgColor",(portlet.getBarBgColor() == null ? "" : portlet.getBarBgColor()));
				json.put("barFontColor",(portlet.getBarFontColor() == null ? "" : portlet.getBarFontColor()));
				json.put("contentBgColor",(portlet.getContentBgColor()== null ? "" : portlet.getContentBgColor()));
				json.put("textColor",(portlet.getTextColor()== null ? "" : portlet.getTextColor()));
				json.put("parameter",(portlet.getParameter()== null ? "" : portlet.getParameter()));
				json.put("initState",portlet.getWindowStatus());
				json.put("initMode",portlet.getMode());
				json.put("transparent",portlet.getTransparent());
				json.put("showIcon",portlet.getShowIcon());
				json.put("type",portlet.getType());
				json.put("marginTop",portlet.getMarginTop());
				json.put("client",portlet.getClient());
				jArray.put(json);				
			}
			return jArray;		
		}
		return new JSONArray();
	}
	public static JSONObject getUserJSON(User user){
		 JSONObject json = new JSONObject();
		 try{		 			 	
		    json.put("id", user.getId());
		    json.put("userId", user.getUserId());
		    json.put("orgId", user.getOrgId());
		    json.put("email", user.getEmail());		
		    json.put("name", user.getName());
		    json.put("displayName", user.getDisplayName());		
		    json.put("uri", user.getUri());
		    json.put("uriType", user.getUriType());
			json.put("password", user.getPassword());
			json.put("caption", user.getCaption());				
			json.put("photoUrl", user.getPhotoUrl());
			json.put("photoHeight", user.getPhotoHeight());
			json.put("photoWidth", user.getPhotoWidth());
			json.put("gender", user.getGender());
			json.put("birth", user.getBirth());
			json.put("birthM", user.getBirthM());
			json.put("birthD", user.getBirthD());
			json.put("birthY", user.getBirthY());
			json.put("language", user.getLanguage());
			json.put("country", user.getCountry());
			json.put("province", user.getProvince());
			json.put("city", user.getCity());
			json.put("postalCode", user.getPostalCode());
			json.put("timeZone", user.getTimeZone());
			json.put("region", user.getRegion());
		    		    
		    json.put("defaultFileStatus", user.getDefaultFileStatus());
			json.put("defaultPictureStatus", user.getDefaultPictureStatus());
			json.put("defaultMusicStatus", user.getDefaultMusicStatus());
			json.put("showTitleToFriends", user.getShowTitleToFriends());
		    json.put("growKeyword", user.getGrowKeyword());
			json.put("blogCommentFriendOnly", user.getBlogCommentFriendOnly());
			json.put("commentNeedApprove", user.getCommentNeedApprove());
			json.put("fqNel", user.getFqNel());
			json.put("imprivacy", user.getImprivacy());
			json.put("newsLetter", user.getNewsLetter());
			json.put("noPicForward", user.getNoPicForward());
			json.put("notification", user.getNotification());
			json.put("profileFriendViewOnly", user.getProfileFriendViewOnly());
			json.put("showBirthToFriend", user.getShowBirthToFriend());
			json.put("videoUrl", user.getVideoUrl());
			json.put("ringToneUrl", user.getRingToneUrl());
			json.put("musicUrl", user.getMusicUrl());
			json.put("myMusicAutoPlay", user.getMyMusicAutoPlay());
			json.put("otherMusucAutoPlay", user.getOtherMusucAutoPlay());
			
			json.put("disabled", user.userDisabled() ? 1 : 0);
			json.put("locked", user.userLocked() ? 1: 0);
			json.put("lastDate", user.getLastDate());			
			json.put("visitCount", user.getVisitCount());
			json.put("createDate", DateUtil.format(user.getCreateDate()));
			
		 }catch(Exception e){}
		 return json;
	 }
	 
	public static JSONObject getUserProfileJSON(UserProfile user){
		 JSONObject json = new JSONObject();
		 try{		 			 	
		    json.put("id", user.getId());
		    json.put("userId", user.getUserId());	
		    json.put("name", user.getName());
		    json.put("firstName", user.getFirstName());
		    json.put("middleName", user.getMiddleName());
		    json.put("lastName", user.getLastName());
			json.put("occupation", user.getOccupation());
			json.put("ethnicity", user.getEthnicity());
			json.put("bodyType", user.getBodyType());
			json.put("height", user.getHeight());
			json.put("registerPurpose", user.getRegisterPurpose());
			json.put("maritalStatus", user.getMaritalStatus());
			json.put("sexualOrientation", user.getSexualOrientation());
			json.put("religion", user.getReligion());
			json.put("hometown", user.getHometown());
			json.put("smoker", user.getSmoker());
			json.put("drinker", user.getDrinker());
			json.put("childrenStatus", user.getChildrenStatus());
			json.put("education", user.getEducation());
			json.put("income", user.getIncome());
			json.put("headline", user.getHeadline());
			json.put("aboutMe", user.getAboutMe());
			json.put("likeToMeet", user.getLikeToMeet());
			json.put("interests", user.getInterests());
			json.put("music", user.getMusic());
			json.put("movies", user.getMovies());
			json.put("television", user.getTelevision());
			json.put("books", user.getBooks());
			json.put("heroes", user.getHeroes());
		 }catch(Exception e){}
		 return json;
	 }
	 
	public static JSONObject getPortletRefJSON(PortletObjectRef portlet){
		 JSONObject json = new JSONObject();
		 try{
		 	 json.put("name", portlet.getName());
			 json.put("path", portlet.getPath() != null ? portlet.getPath() : "");
			 json.put("label", portlet.getLabel() != null ? portlet.getLabel() : "");
			 json.put("icon", portlet.getIcon() != null ? portlet.getIcon() : "");
			 json.put("iconCssSprite", portlet.getIconCssSprite() != null ? portlet.getIconCssSprite() : "");
			 json.put("url", portlet.getUrl() != null ? portlet.getUrl() : "");
			 json.put("tag", portlet.getTag() != null ? portlet.getTag() : "");
			 json.put("subtag", portlet.getSubTag() != null ? portlet.getSubTag() : "");
			 json.put("language", portlet.getLanguage() != null ? portlet.getLanguage() : "");
			 json.put("refreshMode", portlet.getRefreshMode());
			 json.put("editMode", portlet.getEditMode());
			 json.put("helpMode", portlet.getHelpMode());
			 json.put("configMode", portlet.getConfigMode());
			 json.put("minimized", portlet.getMinimized());
			 json.put("maximized", portlet.getMaximized());
			 json.put("windowSkin", portlet.getWindowSkin() != null ? portlet.getWindowSkin() : "");
			 json.put("allowJS", portlet.getAllowJS());			 
			 json.put("pageRefreshed", portlet.getPageRefreshed());
			 json.put("autoRefreshed", portlet.getAutoRefreshed());
			 json.put("periodTime", portlet.getPeriodTime());
			 json.put("showNumber", portlet.getShowNumber());
			 json.put("showType", portlet.getShowType());
			 json.put("windowStatus", portlet.getWindowStatus());
			 json.put("windowMode", portlet.getMode());
			 json.put("type", portlet.getType());
			 json.put("parameter", portlet.getParameter() != null ? portlet.getParameter() : "");			 
			 json.put("keywords", portlet.getKeywords() != null ? portlet.getKeywords() : "");
			 json.put("userId", portlet.getUserId() != null ? portlet.getUserId() : "");
			 json.put("orgId", portlet.getOrgId());			 
		 }catch(Exception e){}
		 return json;
	 }
	 
	public static JSONArray getUserBlockJArray(List<UserBlock> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(UserBlock bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", bean.getId());
					 json.put("photoUrl", bean.getPhotoUrl());
					 json.put("uri", bean.getUri());
					 json.put("displayName", bean.getDisplayName());
					 
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	 }
	 
	public static JSONArray getUserInviteJArray(List<UserInvite> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(UserInvite bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", bean.getId());
					 json.put("status", bean.getStatus());
					 json.put("inviteEmail", bean.getInviteEmail());
					 
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	 }
	 
	public static JSONArray getChannels(List<LabelBean> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 int i=1;
				 for(LabelBean ref : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", i++);
					 json.put("name", ref.getId());
					 json.put("desc", ref.getDesc());
					 json.put("selected", ref.isDefaulted() ? 1 : 0);
					 
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	 }
	 
	public static JSONArray getPortletRefJArray(List<PortletObjectRef> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(PortletObjectRef ref : list){
					 JSONObject json = new JSONObject();  
					 json.put("name", ref.getName());
					 json.put("title", ref.getTitle());
					 json.put("icon", ref.getIcon());
					 json.put("needPrefix", ref.isNeedPrefix() ? 1 : 0);
					 
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	 }
	 
	public static JSONArray getContentCategoryJArray(List<ContentCategory> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(ContentCategory category : list){
					 JSONObject json = new JSONObject();  
					 json.put("showed", category.isShowed() ? 1 : 0);
					 json.put("name", category.getName());
					 json.put("title", category.getTitle());
					 if(category.getSubCategories() != null && category.getSubCategories().size() > 0){
						 JSONArray jArray1 = new JSONArray();
						 for(ContentSubCategory subCategory : category.getSubCategories()){
							 JSONObject json1 = new JSONObject();  
							 json1.put("showed", subCategory.isShowed() ? 1 : 0);
							 json1.put("name", subCategory.getName());
							 json1.put("title", subCategory.getTitle());
							 JSONArray jArray2 = new JSONArray();
							 for(PortletObjectRef ref : subCategory.getFeedLists()){
								 JSONObject json2 = new JSONObject();  
								 json2.put("name", ref.getName());
								 json2.put("title", ref.getTitle());
								 json2.put("icon", ref.getIcon());
								 json2.put("needPrefix", ref.isNeedPrefix() ? 1 : 0);
								 jArray2.put(json2);
							 }
							 json1.put("feedLists", jArray2);
							 jArray1.put(json1);
						 }
						 json.put("subCategories", jArray1);
					 }else
						 json.put("subCategories", "");
					 if(category.getFeedLists() != null && category.getFeedLists().size() > 0){
						 JSONArray jArray1 = new JSONArray();
						 for(PortletObjectRef ref : category.getFeedLists()){
							 JSONObject json1 = new JSONObject();  
							 json1.put("name", ref.getName());
							 json1.put("title", ref.getTitle());
							 json1.put("icon", ref.getIcon());
							 json1.put("needPrefix", ref.isNeedPrefix() ? 1 : 0);
							 jArray1.put(json1);
						 }
						 json.put("feedLists", jArray1);
					 }else
						 json.put("feedLists", "");
					 
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	
	public static JSONArray getTagJSONArray(List<UserTag> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(UserTag tag : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", tag.getId());
					 json.put("tag", tag.getTag());
					 json.put("score",tag.getScore());
					 json.put("size", tag.getSize());
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	public static JSONArray getCommentJSONArray(List<UserComment> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 Collections.sort(list);
			 try{
				 for(UserComment comment : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", comment.getId());
					 json.put("userId", comment.getUserId() != OrganizationThreadLocal.getOrg().getUserId()? comment.getUserId() : 0);
					 json.put("uri",comment.getUri());
					 json.put("displayName",comment.getDisplayName());
					 json.put("photoUrl",comment.getPhotoUrl());
					 json.put("date", comment.getDate());
					 json.put("comment", comment.getComment());
					 json.put("children", getCommentJSONArray(comment.getChildren()));
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}	
	public static JSONArray getConnectionJSONArray(List<Connection> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(Connection bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("displayName", bean.getDisplayName());
					 json.put("buddyCurrentStatusId", bean.getBuddyCurrentStatusId());
					 json.put("uri", bean.getUri());
					 json.put("buddyUserId", bean.getBuddyUserId());
					 json.put("photoUrl", bean.getPhotoUrl());
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	public static JSONArray getConnectionTypeJSONArray(Collection<ConnectionType> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(ConnectionType bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("type", bean.getType());
					 json.put("title", bean.getTitle());
					 json.put("count", bean.getCount());
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	public static JSONArray getForumCategoryJSONArray(List<ForumCategory> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(ForumCategory bean : list){
					 JSONObject json = new JSONObject(); 
					 json.put("id", bean.getId());
					 json.put("name", bean.getDisplayName());
					 json.put("desc", bean.getDisplayDesc());		
					 json.put("topics", bean.getTopics());
					 json.put("posts", bean.getPosts());	
					 json.put("lastForumId", bean.getLastForumId());
					 if(bean.getLastForum() != null){
						 json.put("lastForum",getForumPost(bean.getLastForum()));
					 }
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	public static JSONArray getForumJSONArray(List<Forum> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(Forum bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", bean.getId());
					 json.put("name", bean.getDisplayName());
					 json.put("desc", bean.getDisplayDesc());
					 json.put("topics",bean.getTopics());
					 json.put("posts",bean.getPosts());
					 json.put("lastForumId", bean.getLastForumId());
					 if(bean.getLastForum() != null){
						 json.put("lastForum",getForumPost(bean.getLastForum()));
					 }
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	private static JSONObject getForumPost(ForumPost post) throws Exception{
		JSONObject json = new JSONObject(); 
		json.put("forumId",post.getForumId());
		json.put("topicId",post.getTopicId());
		json.put("date",post.getDate());
		json.put("displayName",post.getDisplayName());
		json.put("uri",post.getUri());
		return json;
	}
	public static JSONArray getForumPostJSONArray(List<ForumPost> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(ForumPost bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", bean.getId());
					 json.put("topic", bean.getTopic());
					 json.put("content", bean.getContent());	
					 json.put("forumId", bean.getForumId());
					 json.put("topicId", bean.getTopicId());
					 json.put("topId", bean.getTopId());
					 json.put("posts", bean.getPosts());
					 json.put("date",bean.getDate());
					 json.put("displayName",bean.getDisplayName());
					 json.put("uri",bean.getUri());
					 json.put("photoUrl",bean.getPhotoUrl());
					 json.put("lastDate",bean.getLastDate());
					 json.put("lastDisplayName",bean.getLastDisplayName());
					 json.put("lastUri",bean.getLastUri());
					 json.put("lastPhotoUrl",bean.getLastPhotoUrl());
					 json.put("postById",bean.getPostById());
					 json.put("currentStatusId",bean.getCurrentStatusId());
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	public static JSONArray getLabelBeanJSONArray(Collection<LabelBean> list){
		 JSONArray jArray = new JSONArray();
		 if(list != null){
			 try{
				 for(LabelBean bean : list){
					 JSONObject json = new JSONObject();  
					 json.put("id", bean.getId());
					 json.put("desc", bean.getDesc());
					 jArray.put(json);
				 }
			 }catch(Exception e){}
		 }
		 return jArray;
	}
	
	public static JSONArray getNumberArray(int start, int end){
		JSONArray jArray = new JSONArray();
		for(int i=start;i<=end;i++)
			jArray.put(i);
		return jArray;
	}
}