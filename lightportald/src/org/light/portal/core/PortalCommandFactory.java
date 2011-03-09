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

package org.light.portal.core;

import static org.light.portal.util.Constants._COMMAND_LIST;

import java.util.HashMap;
import java.util.Map;

import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalCommandFactory {
	private static PortalCommandFactory instance = new PortalCommandFactory();
	private Map<String, PortalCommand> defaultCommandMaps;
	private Map<Long, Map<String, PortalCommand>> commandMaps;
		
	public static PortalCommandFactory getInstance(){		
		return instance;
	}
	
	public PortalCommand getCommand(String path){
		long orgId = OrganizationThreadLocal.getOrganizationId();
		if(orgId > 0){
			if(commandMaps.get(orgId) == null){
				synchronized(instance){
					if(commandMaps.get(orgId) == null){
						Map<String, PortalCommand> orgCommandMaps = new HashMap<String, PortalCommand>();
						String value = PropUtil.getString(_COMMAND_LIST);
						String[] lists = value.split(";");
						for(String list : lists){
							String[] unit = list.split(",");
							orgCommandMaps.put(unit[0], new PortalSimpleCommand(
															unit[1]
														   ,unit[2]
														   ));
						}
						commandMaps.put(orgId,orgCommandMaps);
					}
				}
			}
			return commandMaps.get(orgId).get(path);
		}
		else
			return defaultCommandMaps.get(path);
		
	}
	
	private PortalCommandFactory(){		
		commandMaps = new HashMap<Long, Map<String, PortalCommand>>();
		
		defaultCommandMaps = new HashMap<String, PortalCommand>();
		
		defaultCommandMaps.put("loadPortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"loadPortal"
											  ));		
		defaultCommandMaps.put("getPortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortal"
											  ));
		
		defaultCommandMaps.put("profilePortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"profilePortal"
											  ));
		defaultCommandMaps.put("visitPortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"visitPortal"
											  ));
		defaultCommandMaps.put("pagePortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"pagePortal"
											  ));
		defaultCommandMaps.put("groupPortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"groupPortal"
											  ));
		defaultCommandMaps.put("storePortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"storePortal"
											  ));
		defaultCommandMaps.put("businessPortal", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"businessPortal"
											  ));
		defaultCommandMaps.put("getPortalTabsByUser", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByUser"
											  ));
		defaultCommandMaps.put("getPortalTabsByVisit", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByVisit"
											  ));	
		defaultCommandMaps.put("getPortalTabsByPage", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByPage"
											  ));
		defaultCommandMaps.put("getPortalTabsByGroup", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByGroup"
											  ));
		defaultCommandMaps.put("getPortalTabsByStore", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByStore"
											  ));
		defaultCommandMaps.put("getPortalTabsByBusiness", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByBusiness"
											  ));
		defaultCommandMaps.put("getPortalTabsByParent", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortalTabsByParent"
											  ));
		defaultCommandMaps.put("getPortletsByTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortletsByTab"
											  ));
		defaultCommandMaps.put("getPortletsByVisitTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortletsByVisitTab"
											  ));
		defaultCommandMaps.put("getPortletsByGroupTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortletsByGroupTab"
											  ));
		defaultCommandMaps.put("getPortletTitle", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getPortletTitle"
											  ));
		defaultCommandMaps.put("getScripts", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"getScripts"
											  ));
		defaultCommandMaps.put("rememberState", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"rememberState"
											  ));
		defaultCommandMaps.put("rememberMode", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"rememberMode"
											  ));
		defaultCommandMaps.put("addSubPage", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"addSubPage"
											  ));
		defaultCommandMaps.put("changeTitle", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeTitle"
											  ));				
		defaultCommandMaps.put("changeLanguage", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeLanguage"
											  ));
		defaultCommandMaps.put("changeRegion", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeRegion"
											  ));
		defaultCommandMaps.put("changeTimeZone", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeTimeZone"
											  ));
		defaultCommandMaps.put("changeGeneral", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeGeneral"
											  ));
		defaultCommandMaps.put("changeTheme", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changeTheme"
											  ));
		defaultCommandMaps.put("addTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"addTab"
											  ));
		defaultCommandMaps.put("editTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"editTab"
											  ));
		defaultCommandMaps.put("manageTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"manageTab"
											  ));
		defaultCommandMaps.put("editTabTitle", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortalCommands"
								               ,"editTabTitle"
								               ));
		defaultCommandMaps.put("addContent", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"addContent"
											  ));
		defaultCommandMaps.put("login", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"login"
											  ));
		defaultCommandMaps.put("logout", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"logout"
											  ));
		defaultCommandMaps.put("validateUserId", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"validateUserId"
											  ));
		defaultCommandMaps.put("validateMyUri", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"validateMyUri"
											  ));
		defaultCommandMaps.put("validateMyStoreUri", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"validateMyStoreUri"
											  ));
		defaultCommandMaps.put("signUp", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"signUp"
											  ));
		defaultCommandMaps.put("profile", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"profile"
											  ));
		defaultCommandMaps.put("checkMyUrl", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"checkMyUrl"
											  ));
		defaultCommandMaps.put("saveMyUrl", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"saveMyUrl"
											  ));
		defaultCommandMaps.put("deletePortlet", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"deletePortlet"
											  ));
		defaultCommandMaps.put("deleteTab", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"deleteTab"
											  ));
		defaultCommandMaps.put("changePosition", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortalCommands"											  
											  ,"changePosition"
											  ));
		defaultCommandMaps.put("countDownload", new PortalSimpleCommand(
										       "org.light.portal.core.action.PortalCommands"             
										      ,"countDownload"
										      ));
		defaultCommandMaps.put("saveNote", new PortalSimpleCommand(
										       "org.light.portal.core.action.PortletCommands"             
										      ,"saveNote"
										      ));		
		defaultCommandMaps.put("getRssDesc", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"getRssDesc"
											  ));
		defaultCommandMaps.put("trackRssItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"trackRssItem"
								               ));	
		defaultCommandMaps.put("readPopItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"readPopItem"
								               ));
		defaultCommandMaps.put("readViewedItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"readViewedItem"
								               ));
		defaultCommandMaps.put("readRecommendedItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"readRecommendedItem"
								               ));
		defaultCommandMaps.put("getRecommendedItemDesc", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"getRecommendedItemDesc"
								               ));
		defaultCommandMaps.put("getViewedItemDesc", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"getViewedItemDesc"
								               ));
		defaultCommandMaps.put("popRssItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"popRssItem"
								               ));
		defaultCommandMaps.put("popYouTubeItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"popYouTubeItem"
								               ));
		defaultCommandMaps.put("popBlogItem", new PortalSimpleCommand(
								                "org.light.portlets.blog.action.BlogAction"
								               ,"popBlogItem"
								               ));
		defaultCommandMaps.put("popForumItem", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"popForumItem"
								               ));
		defaultCommandMaps.put("popTopicItem", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"popTopicItem"
								               ));
		defaultCommandMaps.put("popGroupForumItem", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"popGroupForumItem"
								               ));
		defaultCommandMaps.put("popGroupTopicItem", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"popGroupTopicItem"
								               ));
		defaultCommandMaps.put("popDeliItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"popDeliItem"
								               ));
		defaultCommandMaps.put("popBookmarkItem", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"popBookmarkItem"
								               ));
		defaultCommandMaps.put("forwardRssToFriend", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"forwardRssToFriend"
								               ));
		defaultCommandMaps.put("forwardYouTubeToFriend", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"forwardYouTubeToFriend"
								               ));
		defaultCommandMaps.put("forwardBlogToFriend", new PortalSimpleCommand(
								                "org.light.portlets.blog.action.BlogAction"
								               ,"forwardBlogToFriend"
								               ));
		defaultCommandMaps.put("forwardForumToFriend", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"forwardForumToFriend"
								               ));
		defaultCommandMaps.put("forwardTopicToFriend", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"forwardTopicToFriend"
								               ));
		defaultCommandMaps.put("forwardGroupForumToFriend", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"forwardGroupForumToFriend"
								               ));
		defaultCommandMaps.put("forwardGroupTopicToFriend", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"forwardGroupTopicToFriend"
								               ));
		defaultCommandMaps.put("forwardDeliToFriend", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"forwardDeliToFriend"
								               ));
		defaultCommandMaps.put("forwardBookmarkToFriend", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"forwardBookmarkToFriend"
								               ));
		defaultCommandMaps.put("saveToBackground", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"saveToBackground"
								               ));
		defaultCommandMaps.put("saveToHeader", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"saveToHeader"
								               ));
		defaultCommandMaps.put("saveToBookmark", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"saveToBookmark"
								               ));
		defaultCommandMaps.put("saveToMyPicture", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"saveToMyPicture"
								               ));		
		defaultCommandMaps.put("saveYouTubeToBookmark", new PortalSimpleCommand(
								                "org.light.portal.core.action.PortletCommands"
								               ,"saveYouTubeToBookmark"
								               ));
		defaultCommandMaps.put("saveBlogToBookmark", new PortalSimpleCommand(
								                "org.light.portlets.blog.action.BlogAction"
								               ,"saveBlogToBookmark"
								               ));
		defaultCommandMaps.put("saveForumToBookmark", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"saveForumToBookmark"
								               ));
		defaultCommandMaps.put("saveTopicToBookmark", new PortalSimpleCommand(
								                "org.light.portlets.forum.action.ForumAction"
								               ,"saveTopicToBookmark"
								               ));
		defaultCommandMaps.put("saveGroupForumToBookmark", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"saveGroupForumToBookmark"
								               ));
		defaultCommandMaps.put("saveGroupTopicToBookmark", new PortalSimpleCommand(
								                "org.light.portlets.group.action.GroupAction"
								               ,"saveGroupTopicToBookmark"
								               ));
		defaultCommandMaps.put("getBookmarkDesc", new PortalSimpleCommand(
							                "org.light.portal.core.action.PortletCommands"
							               ,"getBookmarkDesc"
							               ));	
		defaultCommandMaps.put("getDeliDesc", new PortalSimpleCommand(
							                "org.light.portal.core.action.PortletCommands"
							               ,"getDeliDesc"
							               ));	
		defaultCommandMaps.put("getInternalNewsDesc", new PortalSimpleCommand(
							                "org.light.portal.core.action.PortletCommands"
							               ,"getInternalNewsDesc"
							               ));			
		defaultCommandMaps.put("getFeedbackDesc", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"getFeedbackDesc"
											  ));
		defaultCommandMaps.put("getPopItemDesc", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"getPopItemDesc"
											  ));		
		defaultCommandMaps.put("getUserDetail", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"getUserDetail"
											  ));
		
		defaultCommandMaps.put("saveBuddyType", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"saveBuddyType"
											  ));
		defaultCommandMaps.put("deleteBuddy", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"deleteBuddy"
											  ));
		defaultCommandMaps.put("chatWithBuddy", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"chatWithBuddy"
											  ));
		defaultCommandMaps.put("listenServer", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"listenServer"
											  ));
		defaultCommandMaps.put("uploadAllOpml", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadAllOpml"
											  ));
		defaultCommandMaps.put("uploadOpml", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadOpml"
											  ));
		defaultCommandMaps.put("uploadProfilePhoto", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadProfilePhoto"
											  ));
		defaultCommandMaps.put("uploadPicture", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadPicture"
											  ));
		defaultCommandMaps.put("uploadPictures", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadPictures"
											  ));
		defaultCommandMaps.put("uploadMusic", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadMusic"
											  ));
		defaultCommandMaps.put("uploadMusics", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadMusics"
											  ));
		defaultCommandMaps.put("uploadFile", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadFile"
											  ));
		defaultCommandMaps.put("uploadGroupPicture", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"uploadGroupPicture"
											  ));
		defaultCommandMaps.put("getForumDesc", new PortalSimpleCommand(
											   "org.light.portlets.forum.action.ForumAction"											  
											  ,"getForumDesc"
											  ));
		defaultCommandMaps.put("getForumDetail", new PortalSimpleCommand(
											   "org.light.portlets.forum.action.ForumAction"											  
											  ,"getForumDetail"
											  ));
		defaultCommandMaps.put("saveForumReply", new PortalSimpleCommand(
											   "org.light.portlets.forum.action.ForumAction"											  
											  ,"saveForumReply"
											  ));
		defaultCommandMaps.put("getTodoDesc", new PortalSimpleCommand(
												"org.light.portal.core.action.PortletCommands"											  
											  ,"getTodoDesc"
											  ));		
		defaultCommandMaps.put("getProductDesc", new PortalSimpleCommand(
												"org.light.portlets.store.action.StoreAction"											  
											  ,"getProductDesc"
											  ));
		defaultCommandMaps.put("saveComment", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"saveComment"
											  ));
		defaultCommandMaps.put("saveItemComment", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"saveItemComment"
											  ));
		defaultCommandMaps.put("turnToMyAccount", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"turnToMyAccount"
											  ));
		defaultCommandMaps.put("addKeywords", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"addKeywords"
											  ));
		defaultCommandMaps.put("deleteKeywords", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"deleteKeywords"
											  ));
		defaultCommandMaps.put("addNotKeywords", new PortalSimpleCommand(
				   "org.light.portal.core.action.PortletCommands"											  
				  ,"addNotKeywords"
				  ));
		defaultCommandMaps.put("deleteNotKeywords", new PortalSimpleCommand(
				   "org.light.portal.core.action.PortletCommands"											  
				  ,"deleteNotKeywords"
				  ));
		defaultCommandMaps.put("addNotWords", new PortalSimpleCommand(
				   "org.light.portal.core.action.PortletCommands"											  
				  ,"addNotWords"
				  ));
		defaultCommandMaps.put("deleteNotWords", new PortalSimpleCommand(
				   "org.light.portal.core.action.PortletCommands"											  
				  ,"deleteNotWords"
				  ));
		defaultCommandMaps.put("configMyPicture", new PortalSimpleCommand(
				   "org.light.portal.core.action.PortletCommands"											  
				  ,"configMyPicture"
				  ));
		
		defaultCommandMaps.put("chatWithProfile", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"chatWithProfile"
											  ));		
		defaultCommandMaps.put("chatWithMember", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"chatWithMember"
											  ));
		defaultCommandMaps.put("acceptChat", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"acceptChat"
											  ));
		defaultCommandMaps.put("refuseChat", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"refuseChat"
											  ));
		defaultCommandMaps.put("closeChat", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"closeChat"
											  ));
		
		defaultCommandMaps.put("sendMessage", new PortalSimpleCommand(
											   "org.light.portal.core.action.PortletCommands"											  
											  ,"sendMessage"
											  ));
		defaultCommandMaps.put("addFriendRequest", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"addFriendRequest"
											  ));
		defaultCommandMaps.put("forwardToFriends", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"forwardToFriends"
											  ));
		defaultCommandMaps.put("addToFavorites", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"addToFavorites"
											  ));
		defaultCommandMaps.put("blockUser", new PortalSimpleCommand(
											   "org.light.portlets.chat.action.ChatAction"											  
											  ,"blockUser"
											  ));			
		defaultCommandMaps.put("getBlogDesc", new PortalSimpleCommand(
										   "org.light.portlets.blog.action.BlogAction"											  
										  ,"getBlogDesc"
										  ));
		defaultCommandMaps.put("getBlogDetail", new PortalSimpleCommand(
										   "org.light.portlets.blog.action.BlogAction"											  
										  ,"getBlogDetail"
										  ));
		defaultCommandMaps.put("getBlogComments", new PortalSimpleCommand(
										   "org.light.portlets.blog.action.BlogAction"											  
										  ,"getBlogComments"
										  ));
		defaultCommandMaps.put("saveBlogComment", new PortalSimpleCommand(
										   "org.light.portlets.blog.action.BlogAction"											  
										  ,"saveBlogComment"
										  ));
		defaultCommandMaps.put("popBlog", new PortalSimpleCommand(
										   "org.light.portlets.blog.action.BlogAction"											  
										  ,"popBlog"
										  ));
		defaultCommandMaps.put("joinToGroup", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"joinToGroup"
										  ));
		defaultCommandMaps.put("inviteToGroup", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"inviteToGroup"
										  ));
		defaultCommandMaps.put("validateGroupUri", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"validateGroupUri"
										  ));			
		defaultCommandMaps.put("resignGroup", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"resignGroup"
										  ));
		defaultCommandMaps.put("getGroupPrivacy", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"getGroupPrivacy"
										  ));
		defaultCommandMaps.put("saveGroupPrivacy", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"saveGroupPrivacy"
										  ));
		defaultCommandMaps.put("deleteGroupProfile", new PortalSimpleCommand(
										   "org.light.portlets.group.action.GroupAction"											  
										  ,"deleteGroupProfile"
										  ));
		defaultCommandMaps.put("getAdDesc", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"getAdDesc"
									  ));
		defaultCommandMaps.put("getAdComments", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"getAdComments"
									  ));
		defaultCommandMaps.put("saveAdComment", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"saveAdComment"
									  ));
		defaultCommandMaps.put("popAd", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"popAd"
									  ));
		defaultCommandMaps.put("forwardAdToFriend", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"forwardAdToFriend"
									  ));
		defaultCommandMaps.put("saveAdToBookmark", new PortalSimpleCommand(
									   "org.light.portlets.ad.action.AdAction"											  
									  ,"saveAdToBookmark"
									  ));
	}	
}
