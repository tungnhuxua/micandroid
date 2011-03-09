/**  
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
 **/
  
/** 
 *Light Portal Framework javascript library, version 1.4.0
 **/

//------------------------------------------------------------ light.js
var Light = {
  		    Version: "1.4.0"
  		   ,_PORTAL_ON : "PORTAL_ON" 		   
  		   ,_PERSON_ID: "personId"
  		   ,_LOGINED_USER_ID: "loginedUserId"
	       ,_REMEMBERED_USER_ID: "rememberedUserId"
	       ,_REMEMBERED_USER_PASSWORD: "rememberedUserPassword"
	       ,_IS_SIGN_OUT: "isSignOut"
  		   ,_USER_LOCALE: "userLocale" 
  		   ,_CURRENT_TAB: "currentTab"
  		   ,_PRIVACY_PREFIX: "privacy_"
  		   ,_PAGE_PREFIX: "page_"
  		   ,_MODE_PREFIX: "mode_"
  		   ,_STATE_PREFIX: "state_"
  		   ,_PARAM_PREFIX: "param_"
  		   ,_FUNCTION_PREFIX: "function_"
  		   ,_PW_PREFIX: "pwid_"		     		   
  		   ,_TAB_PREFIX: "tab_"
  		   ,_TAB_HEADER_PREFIX: "tabHeader_"
  		   ,_TAB_TITLE_PREFIX: "tabTitle_"
  		   ,_TAB_LIST: "tabList"
  		   ,_TAB_PANELS: "tabPanels"
  		   ,_PANEL_PREFIX: "panel_"
  		   ,_VIEW_MODE: "view"
  		   ,_EDIT_MODE: "edit"
  		   ,_HELP_MODE: "help"
  		   ,_CONFIG_MODE: "config"
  		   ,_HEADER_MODE: "header"
  		   ,_NORMAL_STATE: "normal"
  		   ,_MINIMIZED_STATE: "minimized"
  		   ,_MAXIMIZED_STATE: "maximized"
  		   ,_INIT_VIEW: "initView"
  		   ,_PORTAL_VIEW: "portalView"
  		   ,_PORTLET_VIEW: "portletView"  		   
		   ,maxZIndex: 1
           ,cacheStorage: {}
}

L$ = function(element) {
  	if (arguments.length > 1) {
    	for (var i = 0, elements = [], length = arguments.length; i < length; i++)
      		elements.push(L$(arguments[i]));
    	return elements;
  	}
  	return document.getElementById(element);
}

L$$ = (typeof(document.querySelectorAll) != "undefined") ? 
	function(element) {
		if (arguments.length > 1) {
	    	for (var i = 0, elements = [], length = arguments.length; i < length; i++){
	    		var subs= L$$(arguments[i]);
	    		if(subs){
		    		for(var j=0,len=subs.length;j<len;j++)
		      			elements.push(subs[j]);
		      	}
	    	}
	    	return elements;
	  	}
		return document.querySelectorAll(element);
	} 
	:
	function() {
		return [];
	}

window.dhtmlHistory.create({
	toJSON: function(o) {
		return JSON.stringify(o);
	}, 
	fromJSON: function(s) {
		return JSON.parse(s);
	}
});

$(function(){
	Light.startup();
});

Light.startup = function(){
	Light.init();	
	Light.start();
}

Light.init = function(){
	//permission values are power of 2					
	Light.permission={
			VIEW: 1 << 0,
			EDIT: 1 << 1,
			HELP: 1 << 2,
			CONFIG: 1 << 3,
			ADD: 1 << 4,
			UPDATE: 1 << 5,
			DELETE: 1 << 6,
			PORTAL_SIGN_UP: 1 << 7,
			PORTAL_SIGN_IN: 1 << 8,
			PORTAL_CHANGE_LANGUAGE: 1 << 9,
			PORTAL_TURN_OFF: 1 << 10,
			PORTAL_TITLE: 1 << 11,
			PORTAL_OPTIONS: 1 << 12,
			PORTAL_TAB_ADD: 1 << 13,
			PORTAL_TAB_DELETE: 1 << 14,
			PORTAL_TAB_DELETE_ALL: 1 << 15,
			PORTAL_CONTENT_ADD: 1 << 16,
			PORTAL_CONTENT_DELETE: 1 << 17,
			PORTAL_CONTENT_DELETE_ALL: 1 << 18
		};
	Light.needRender = (L$('portalContainer')) ? false : true;
  	var n = L$('REQUEST_SUFFIX');
	var s = Light._REQUEST_SUFFIX = (n) ? n.title : ".lp";	   
    Light._GET_INIT_VIEW_TMPL = "getInitViewTemplates"+s; 
    if(!L$(Light._INIT_VIEW)) Light.getInitViewTemplates(false); 
    Light.needReload = !Light.needRender ? false : ((L$('portalJSON')) ? false : true);
  	if(!Light.needRender) Light.maxZIndex=1000;  			 
 	Light._LOAD_PORTAL = "/loadPortal"+s;
  	Light._LOAD_TAB_CONTENT = "/loadPortalTabContent"+s;	   
    Light._GET_TAB_BY_ID = "/getPortalTabById"+s;           
    Light._GET_TABS_BY_PARENT = "/getPortalTabsByParent"+s;
  	Light._SHOW_SUB_TAB = "/showSubTab"+s;
    Light._GET_PORTLETS_BY_TAB = "/getPortletsByTab"+s;  		   
    Light._GET_PORTAL_VIEW_TMPL = "/getPortalViewTemplates"+s;
    Light._GET_PORTLET_VIEW_TMPL = "/getPortletViewTemplates"+s;
    Light._CHANGE_TITLE = "/changeTitle"+s;
    Light._CHANGE_LANGUAGE = "/changeLanguage"+s;
    Light._CHANGE_REGION = "/changeRegion"+s;
    Light._CHANGE_TIMEZONE = "/changeTimeZone"+s;
    Light._CHANGE_GENERAL = "/changeGeneral"+s;
  	Light._CHANGE_THEME = "/changeTheme"+s;
  	Light._ADD_TAB = "/addTab"+s;    
  	Light._MANAGE_TAB = "/manageTab"+s;
  	Light._ADD_SUB_TAB = "/addSubTab"+s;     
  	Light._EDIT_TAB_TITLE = "/editTabTitle"+s;
  	Light._DELETE_TAB = "/deleteTab"+s;
    Light._ADD_CONTENT = "/addContent"+s;
  	Light._CONFIG_PORTLET = "/configPortlet"+s;
  	Light._LOGIN = "/login"+s;
  	Light._LOGOUT = "/logout"+s;
  	Light._DELETE_PORTLET = "/deletePortlet"+s;
  	Light._CHANGE_POSITION = "/changePosition"+s; 
    Light._GET_USER_DETAIL = "/getUserDetail"+s;
	Light._REMEBER_STATE = "/rememberState"+s;
    Light._REMEBER_MODE = "/rememberMode"+s;
    Light._CHAT_WITH_BUDDY = "/chatWithBuddy"+s;
    Light._CHAT_WITH_MEMBER = "/chatWithMember"+s;
    Light._CHAT_WITH_PROFILE = "/chatWithProfile"+s;
    Light._ACCEPT_CHAT  = "/acceptChat"+s;
    Light._REFUSE_CHAT  = "/refuseChat"+s;
    Light._CLOSE_CHAT  = "/closeChat"+s;
	Light._DELETE_BUDDY = "/deleteBuddy"+s;
    Light._LISTEN_SERVER = "/listenServer"+s;	  
    Light._CHECK_MY_URL = "/checkMyUrl"+s;
    Light._SAVE_MY_URL = "/saveMyUrl"+s;
    Light._GET_OBJECT_DESC = "/getObjectDesc"+s; 
    Light._SAVE_OBJECT_COMMENT = "/saveObjectComment"+s;
    Light._GET_OBJECT_COMMENTS = "/getObjectComments"+s;
    Light._SEND_MESSAGE = "/sendMessage"+s;
    Light._ADD_FRIEND = "/addFriendRequest"+s;
    Light._ADD_TO_FAVORITES = "/addToFavorites"+s;
    Light._FORWARD_TO_FRIENDS = "/forwardToFriends"+s;
    Light._BLOCK_USER = "/blockUser"+s;
    Light._JOIN_TO_GROUP = "/joinToGroup"+s;
    Light._INVITE_TO_GROUP = "/inviteToGroup"+s;
    Light._VALIDATE_USER_ID = "/validateUserId"+s;
    Light._VALIDATE_INTERNAL_URI = "/validateInternalUri"+s;
    Light._RESIGN_GROUP = "/resignGroup"+s;
    Light._GET_GROUP_PRIVACY = "/getGroupPrivacy"+s;
    Light._SAVE_GROUP_PRIVACY = "/saveGroupPrivacy"+s;
    Light._DELETE_GROUP_PROFILE = "/deleteGroupProfile"+s;
    Light._SAVE_NOTE = "/saveNote"+s;
    Light._TRACK_RSS_ITEM = "/trackRssItem"+s;
    Light._READ_RECOMMENDED_ITEM = "/readRecommendedItem"+s;
    Light._READ_VIEWED_ITEM = "/readViewedItem"+s;
    Light._READ_POP_ITEM = "/readPopItem"+s;
    Light._POP_BLOG = "/popBlog"+s;
    Light._POP_AD = "/popAd"+s;
    Light._POP_RSS_ITEM = "/popRssItem"+s;
    Light._POP_BLOG_ITEM = "/popBlogItem"+s;
    Light._POP_DELI_ITEM = "/popDeliItem"+s;
    Light._POP_BOOKMARK_ITEM = "/popBookmarkItem"+s;
    Light._POP_YOUTUBE_ITEM = "/popYouTubeItem"+s;
    Light._POP_FORUM_ITEM = "/popForumItem"+s;
    Light._POP_TOPIC_ITEM = "/popTopicItem"+s;
    Light._FWD_RSS_FRIEND = "/forwardAdToFriend"+s;
    Light._FWD_BLOG_FRIEND = "/forwardBlogToFriend"+s; 
    Light._FWD_BOOKMARK_FRIEND = "/forwardBookmarkToFriend"+s; 
    Light._FWD_DELI_FRIEND = "/forwardDeliToFriend"+s;
    Light._FWD_FORUM_FRIEND = "/forwardForumToFriend"+s; 
    Light._FWD_TOPIC_FRIEND = "/forwardTopicToFriend"+s; 
    Light._FWD_YOUTUBE_FRIEND = "/forwardYouTubeToFriend"+s; 
    Light._SAVE_AD_BOOKMARK  = "/saveAdToBookmark"+s;
    Light._SAVE_BLOG_BOOKMARK = "/saveBlogToBookmark"+s;
	Light._SAVE_FORUM_BOOKMARK  = "/saveForumToBookmark"+s;
    Light._SAVE_TOPIC_BOOKMARK  = "/saveTopicToBookmark"+s;
    Light._SAVE_YOUTUBE_BOOKMARK  = "/saveYouTubeToBookmark"+s;      
    Light._SAVE_TO_HEADER = "/saveToHeader"+s;
	Light._SAVE_TO_BACKGROUND = "/saveToBackground"+s;
	Light._SAVE_TO_BOOKMARK = "/saveToBookmark"+s;
	Light._SAVE_TO_MY_PICTURE = "/saveToMyPicture"+s;
	Light._SAVE_BUDDY_TYPE = "/saveBuddyType"+s;
    Light._CONFIG_MY_PICTURE = "/configMyPicture"+s;           
    Light._GET_PPT_CONTENT = "/getPicturePositionTaggingContent"+s;
    Light._GET_PPT_EDIT = "/getPicturePositionTaggingEdit"+s;
    Light._SAVE_PPT = "/savePPT"+s;
    Light._DELETE_PPT = "/deletePPT"+s;
    Light._SAVE_PPT_COORDINATE = "/savePPTCoordinate"+s;
    Light._SHOW_INVITE_LIST = "/showInviteList"+s;
    Light._INVITE_BUDDYS_TO_CHAT = "/inviteBuddysToChat"+s;
    Light._ENTER_CHATTING_ROOM = "/enterChattingRoom"+s;
    Light._ADD_USER_TAG = "/addUserTag"+s;
    Light._SUBSCRIBE = "/subscribe"+s;
    Light._CROP_PROFILE_PHOTO = "/cropProfilePhoto"+s;
	n = L$(Light._PORTAL_ON);
	Light._ON = (n) ? parseInt(n.title) : 1;
	var portalMode= Light.getCookie(Light._PORTAL_ON);
	if(portalMode) Light._ON = parseInt(portalMode);
	n = L$('PORTAL_WIDTH');
	Light._PORTAL_WIDTH = (n) ? parseInt(n.title) : 1016;
	if(isIPad()) Light._PORTAL_WIDTH = Light.getScreenWidth() - Light.getBarWidth();
	n = L$('PORTAL_BAR_WIDTH');
	Light._BAR_WIDTH= (n) ? parseInt(n.title) : 120;	
	n = L$('PORTLET_RENDER_ID_PREFIX');
	Light._PR_PREFIX= (n) ? n.title : "prid_";	
	n = L$('PORTLET_TITLE_ID_PREFIX');	     		   
	Light._PT_PREFIX= (n) ? n.title : "ptid_";
	n = L$('PORTLET_HEADER_ID_PREFIX');	     		   
	Light._PH_PREFIX= (n) ? n.title : "phid_";
	n = L$('PORTLET_BUTTON_ID_PREFIX');	     		   
	Light._PB_PREFIX= (n) ? n.title : "pbid_";	
	n = L$('LISTEN_SERVER_INTERVAL');
	Light.LISTEN_SERVER_INTERVAL= (n) ? parseInt(n.title) : 10000;	
	n = L$('SESSION_TIMEOUT');
	Light._SESSION_TIMEOUT= (n) ? parseInt(n.title) : 1800000;
	n = L$('SESSION_TIMEOUT_WARNING');
  	Light._SESSION_TIMEOUT_WARNING= (n) ? parseInt(n.title) : 120000;
  	n = L$('REQUEST_TIMEOUT');
  	Light._REQUEST_TIMEOUT= (n) ? parseInt(n.title) : 10000;
  	n = L$('FONT_SIZE');
  	Light._FONT_SIZE= (n) ? parseInt(n.title) : 12;
  	n = L$('THEME_ROOT');
  	Light._THEME_ROOT= (n) ? n.title : "/light/theme/";
  	n = L$('EMAIL_PATTERN');
  	Light._EMAIL_PATTERN= (n) ? n.title : "/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/";
  	n = L$('URI_PATTERN');
  	Light._URI_PATTERN= (n) ? n.title : "/^([a-zA-Z0-9]{3,})+$/";
  	n = L$('PASSWORD_PATTERN');
  	Light._PASSWORD_PATTERN= (n) ? n.title : "/^([a-zA-Z0-9]{3,})+$/";
  	n = L$('DOMAIN_LIST');
  	Light._DOMAIN_LIST= (n) ? n.title : ".com,.net,.me,.info,.org,.mobi,.biz,.us,.ca,.asia,.in,.ws,.ag,.com.ag,.net.ag,.org.ag,.at,.be,.cc,.cn,.com.cn,.org.cn,.net.cn,.de,.eu,.fm,.gs,.co.in,.firm.in,.gen.in,.ind.in,.net.in,.org.in,.jobs,.jp,.ms,.nu,.co.nz,.net.nz,.org.nz,.tc,.tv,.tw,.com.tw,.org.tw,.idv.tw,.co.uk,.me.uk,.org.uk,.vg";    
    n = L$('JS_HISTORY_DISABLE_LIST');
    Light._JS_HISTORY_DISABLE_LIST = (n) ? n.title : false;
    n = L$('IM_RING_TONE');
    Light._IM_RING_TONE = (n) ? n.title : "/ringtone/default.mp3";  
    n = L$('JAVASCRIPT_LIBRARYS'); 
    Light._JAVASCRIPT_LIBRARYS = (n) ? n.title : "";  
    n = L$('CONTEXT_PATH'); 
    Light._CONTEXT_PATH = (n) ? n.title : "";
    n = L$('PORTAL_COMMON_THEME'); 
    Light._PORTAL_COMMON_THEME = (n) ? n.title : "common.css";
    n = L$('PORTAL_THEME_BASE'); 
    Light._PORTAL_THEME_BASE = (n) ? n.title : "theme.css";
    n = L$('PORTAL_THEME'); 
    Light._PORTAL_THEME = (n) ? n.title : "theme1";  
    if($('link[href$=/theme/common.css]').length == 0){
    	Light.addCSS(Light.getContextPath()+Light._THEME_ROOT+(isIE() ? 'MSIE/' : isOpera() ? 'Opera/' : '')+Light._PORTAL_COMMON_THEME);
    	Light.addCSS(Light.getThemePath(Light._PORTAL_THEME)+(isIE() ? '/MSIE/' : '/') + Light._PORTAL_THEME_BASE);
	}
    Light.getPortalViewTemplates(); 
 	Light.getPortletViewTemplates(); 
 	Light.getScripts();  	
 	Light.loading = Light.getViewTemplate("loadingPortlet.view");
 	Light.empty = "<br/>";
}

Light.start = function(){
	if(Light._ON){
		if(Light.needRender) Light.showLoadingBar();
		Light.portal = new LightPortal();
		Light.portal.startup();
		Light.afterStartup();
		if(Light.needRender) Light.hideLoadingBar();
	}else{
		Light.offMode();
	}
}

Light.onMode = function(){
	Light._ON = true;
	Light.setCookie(Light._PORTAL_ON,1);
	$('#offMode').remove();
	Light.start();
}

Light.offMode = function(){
	Light._ON = false;
	Light.setCookie(Light._PORTAL_ON,0);
	$('<span>',{id:'offMode'}).html(Light.getViewTemplate("offMode.view",null)).appendTo('body');
}

Light.afterStartup = function(){
  	dhtmlHistory.initialize();
	dhtmlHistory.addListener(Light.historyListener);
	setTimeout((function() {Light.refreshAd()}), 1000); 
	Light.autoListenServer();  
	Light.setSessionTimer();
}

Light.historyListener = function(location, historyData) {
	if(Light.disableHistory()) return;
	if(isSafari()){
		var url = document.location.href;
		var i = url.indexOf("#");
		location = (i >= 0) ? url.substr(i+1) : "";
	}
	if(location.indexOf(Light._PRIVACY_PREFIX) >= 0){
	    var index = location.indexOf(Light._PRIVACY_PREFIX);
    	var privacy = location.substr(index+Light._PRIVACY_PREFIX.length,1);
    	if(privacy > 0 && !Light.isLogin()){
    		Light.portal.allTabs[0].focus(false);
    		Light.showLogin(false);
		    return;
    	}
    }
    for(var i=Light.portal.allPortlets.length;i--;){
		var portlet = Light.portal.allPortlets[i];
		if(portlet){
		 	if(portlet.isPopupWindow){
				portlet.close(true);
			}
			break;
		}
	}
	var actions = location.split("/");
	var pid;
	var mode = '';
	var state = '';
	var params = '';
	var done;
	len = actions.length;
	for(var i=0;i<len;i++){
	    if(actions[i].indexOf(Light._PAGE_PREFIX) >= 0){
			var tabIds = actions[i].substr(Light._PAGE_PREFIX.length).split("-");
			var found = false;
			var tLen = tabIds.length;
			for(var j=0;j<tLen;j++){
				var tab = Light.getTabById(tabIds[j]);
				if(tab){
					var segment = L$(Light._TAB_HEADER_PREFIX + tab.serverId);
					if(segment){
						found = true;
				 		if(segment.className != "current"){
				 			tab.focus(false);
				 		}
				 	}
				}
			}
			if(!found && tLen > 0){
				var opt = {
				    method: 'post',
				    asynchronous: false,
				    postBody: 'tabId='+tabIds[tLen - 1]
				   }
				var tabJSON = Light.ajax.sendRequest(Light._GET_TAB_BY_ID, opt); 
				if(tabJSON && tabJSON.length > 10){
					tabDatas = JSON.parse(tabJSON);
					var tab = new LightPortalTab(tabDatas[0]);        
	  				tab.open();
	  				Light.portal.tabs.push(tab);  
	  				tab.focus(false);
	  				found = true;
	  			}	 
			}
			if(!found && Light.portal.tabs.length > 0) Light.portal.tabs[0].focus();
		}		
		else if(actions[i].indexOf(Light._PR_PREFIX) >= 0){
			pid = actions[i];
		}
		else if(actions[i].indexOf(Light._MODE_PREFIX) >= 0){
			mode = actions[i].substr(Light._MODE_PREFIX.length);	
		}
		else if(actions[i].indexOf(Light._STATE_PREFIX) >= 0){
			state = actions[i].substr(Light._STATE_PREFIX.length);
		}
		else if(actions[i].indexOf(Light._PARAM_PREFIX) >= 0){
			params = actions[i].substr(Light._PARAM_PREFIX.length);						
		}
		else if(actions[i].indexOf(Light._FUNCTION_PREFIX) >= 0){
			var method = actions[i].substr(Light._FUNCTION_PREFIX.length);	
			try{
				eval(method+"()");
				done = true;
			}catch(err){
				setTimeout((function() {Light.getCurrentTab().refreshPortlets()}), 200);	
			}			
		}	
	}
	if(pid){
		if(!done)
			setTimeout((function() {Light.executeRender(pid,mode,state,params)}), 200);			
	}else{
		var tab = Light.getCurrentTab();
		if(tab) tab.normalAll();
	}
}

Light.addHistory = function(action){
	if(Light.disableHistory()) return;
	if(isSafari() && typeof action != "undefined" && action.indexOf(Light._FUNCTION_PREFIX) >= 0) return; //funtion bookmark is not working under Safari
	var tab = Light.getCurrentTab();
	var historyId = Light._PRIVACY_PREFIX+tab.privacy+"/"+Light._PAGE_PREFIX+((tab.parentId > 0) ? tab.parentId+"-"+tab.serverId : tab.serverId);
	if(typeof action != "undefined" && action.length > 0) historyId += "/"+action;	
	dhtmlHistory.add(historyId,"");	
}

Light.addFunctionHistory = function(name){
	Light.addHistory(Light._FUNCTION_PREFIX+name);
}

Light.disableHistory = function(){
	if(!Light._ON) return true;
	if(Light._JS_HISTORY_DISABLE_LIST){
		var patterns = Light._JS_HISTORY_DISABLE_LIST.split(',');
		var url = document.location.href;
		for(var i in patterns){
			if(url.indexOf(patterns[i]) > 0) return true;
		}
	}
	return false;
}

Light.refreshPortal = function(flag) {
   	Light.reloadPortal(flag);
}

Light.reloadPortal = function(flag) {
	if(flag){
		var url = document.location.href;
		if(url.indexOf("#") > 0) url = url.substring(0,url.indexOf("#"));
		window.location=url;
	}else
	   window.location.reload(true); 
}

Light.getContextPath = function(){
	var n = L$('CONTEXT_PATH'); 
    return (n) ? n.title : Light._CONTEXT_PATH;
}

Light.getAllViewTemplates = function(){
	Light.getInitViewTemplates();
	Light.getPortalViewTemplates();
	Light.getPortletViewTemplates(); 
}

Light.getInitViewTemplates = function(flag){
	var value = Light.ajax.sendRequest(Light._GET_INIT_VIEW_TMPL, {method: 'get',asynchronous:(flag == null || flag) ? true : false,onSuccess:Light.getInitViewTemplatesHandler});   
	if(value) Light.setInitViewTemplates(value);
}

Light.getInitViewTemplatesHandler = function(t){
	Light.setInitViewTemplates(t.responseText);
}

Light.setInitViewTemplates = function(tmpl){
	$('#'+Light._INIT_VIEW).remove();
	$('<div>',{id:Light._INIT_VIEW}).html(tmpl).appendTo('body');
}

Light.getPortalViewTemplates = function(){
	Light.ajax.sendRequest(Light._GET_PORTAL_VIEW_TMPL, {method:'get',onSuccess:Light.getPortalViewTemplatesHandler});   
}

Light.getPortalViewTemplatesHandler = function(t){
	$('#'+Light._PORTAL_VIEW).remove();
	$('<div>',{id:Light._PORTAL_VIEW}).html(t.responseText).appendTo('body');
}

Light.getPortletViewTemplates = function(){
	Light.ajax.sendRequest(Light._GET_PORTLET_VIEW_TMPL, {method:'get',onSuccess:Light.getPortletViewTemplatesHandler});   
}

Light.getPortletViewTemplatesHandler = function(t){
	$('#'+Light._PORTLET_VIEW).remove();
	$('<div>',{id:Light._PORTLET_VIEW}).html(t.responseText).appendTo('body');
}

Light.getScripts = function(){
	if(Light._JAVASCRIPT_LIBRARYS){	
		var params = Light._JAVASCRIPT_LIBRARYS.split(",");
		for(var i=0;i<params.length;i++){
			if(params[i]){
				var script =document.createElement("script");
			    script.setAttribute("language", "JavaScript");		     
			    script.setAttribute("src", (params[i].startsWith("http")) ? params[i] : document.location.protocol+"//"+document.domain+((document.location.port) ? ":"+document.location.port : "")+Light.getContextPath()+"/"+params[i]);
			    document.getElementsByTagName("head")[0].appendChild(script);				    
		    }	    
	    }
	}
}

Light.getViewTemplate = function(viewId,data){
   	return (L$(viewId)) ? TrimPath.processDOMTemplate(viewId, data) : "";
}

Light.isLogin = function(){
    return (Light.getCookie(Light._LOGINED_USER_ID)) ? true : false;
}

Light.getUserId = function(){
	return(Light.userId) ? Light.userId : Light.getCookie(Light._LOGINED_USER_ID);
}

Light.getRememberedUserId = function(){
	var userId = Light.getCookie(Light._REMEMBERED_USER_ID);
    return (userId) ? Base64.decode(userId) : "";
}

Light.getCurrentTab = function (){
    return (Light.currentTab) ? Light.currentTab : Light.portal.allTabs.get(Light.getCurrentTabId(),'serverId');
}

Light.getTabById = function (id){
	return Light.portal.allTabs.get(id,'serverId');
}

Light.getTabByName = function (name){
	return Light.portal.allTabs.get(name,'label');
}

Light.getPortletById = function(id) {
	var portletIds = id.split("_");
	if(portletIds.length > 2){
		var tIndex = portletIds[1];      
	  	var position = portletIds[2];
	  	var index = portletIds[3];
	  	var portlet = Light.portal.tabs[tIndex].portlets[position][index];
	  	return portlet;
  	}else if(portletIds.length == 2){
  		return Light.portal.allPortlets.get(portletIds[1],'serverId');  		
  	}
}

Light.getPortletByName = function(name){
	var tab = Light.getCurrentTab();
	for(var i=tab.portlets.length;i--;){
		var portlets = tab.portlets[i];
       	if(portlets){
       		var portlet = portlets.get(name,'name');  	
       		if(portlet){          	
		    	return portlet;
	       	}
	   	}
	}
	return Light.portal.allPortlets.get(name,'name');  	
}

Light.getPopupPortletByName = function(name){
	for(var i=Light.portal.allPortlets.length;i--;){
		var portlet = Light.portal.allPortlets[i];
		if(portlet && portlet.isPopupWindow && portlet.name == name){
			return portlet;
		}
	}
}

Light.noticePortlet = function(name,mode,state,param,doRender,asynchronous) {
	var portlet = Light.getPortletByName(name);
	if(portlet){
   		if(doRender == null) doRender = true;	
   	  	if(asynchronous == null) asynchronous = true;	       	  	      	
    	Light.executeRender(portlet.id,mode,state,param,'',true,doRender,asynchronous);
	}	         
}

Light.createParams = function(id,portlet){
	var aparams = [];
	aparams.push("responseId="+id);
	aparams.push("tabId="+Light.getCurrentTab().serverId);
	aparams.push("portletId="+portlet.serverId);
	aparams.push("userId="+Light.getUserId());	  	
	aparams.push("mode="+portlet.mode);
  	if(portlet.maximized)
     	aparams.push("state=maximized");
    if(portlet.parameter)
      	aparams.push(portlet.parameter);
  	return aparams;
}

Light.executeAction = function(id,form,action,actionName,parameter,mode,state,param) {
	var portlet = Light.getPortletById(id);
	if(!portlet) return;
	if(document.state) state = document.state;
	if(state){ 
	    if(((state == Light._MAXIMIZED_STATE && !portlet.maximized) 
					|| (state == Light._NORMAL_STATE && portlet.maximized))
		 	&& portlet.allowMaximized){
			portlet.windowMaximize();
    	}else if(state == Light._MINIMIZED_STATE && portlet.allowMinimized){
	       	portlet.windowMinimize(portlet);
	    }
  	}
  	portlet.mode = (document.mode) ? document.mode : ((mode) ? mode : Light._VIEW_MODE);  	
  	portlet.refreshButtons();  	
	var aparams = Light.createParams(id,portlet);
	if(!form) form = $('#'+id+' form');		
  	if(form){
  		var fields = $(form).serializeArray();
      	$.each(fields, function(i, field){
      		aparams.push(field.name+"="+escape(encodeURIComponent(field.value)));
      	});  		
  	}   	
  	if(action) aparams.push("action="+action);	    
  	if(parameter) aparams.push("parameter="+escape(encodeURIComponent(parameter)));   
	if(param){	
		var serverParam = param.split(";");		
	    for(var i = 0; i < serverParam.length; i++) {
		  	if(serverParam[i].length > 0){
		  		var pair = serverParam[i].split("=");
		  		if(pair.length == 2){	
		  			aparams.push(pair[0]+"="+escape(encodeURIComponent(pair[1])));	
		  		}
		  	}
		}
  	}	
  	var pars = aparams.join("&");
	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id, {method:'post',evalScripts: portlet.allowJS, parameters: pars});
	if(document.resetLastAction) portlet.lastAction = null;
	document.currentForm = null;
	document.pressed = null;
	document.pressedName = null;
	document.parameter = null;
	document.mode = null;
	document.state = null;
	document.resetLastAction = null;
}

Light.executeRender = function(id,mode,state,param,parameter,supportRefresh,doRender,asynchronous) {  
	if(supportRefresh == null) supportRefresh = true;
	if(doRender == null) doRender = true;
	if(asynchronous == null) asynchronous = true;
	var portlet = Light.getPortletById(id);   
	if(!portlet) return;
	if(state){
	    if(((state == Light._MAXIMIZED_STATE && !portlet.maximized) 
				|| (state == Light._NORMAL_STATE && portlet.maximized))
		 	&& portlet.allowMaximized){
			portlet.windowMaximize();
    	}
	    if(state == Light._MINIMIZED_STATE){
			if(!portlet.maximized && portlet.allowMinimized)
				portlet.windowMaximize();
			return;
		}
	} 
	if(!mode){
		portlet.mode = Light._VIEW_MODE;  
	}else if(mode != Light._HEADER_MODE){
		portlet.mode = mode; 
		portlet.refreshButtons();
	}
	var viewId = portlet.name+"."+portlet.mode; 
	if(portlet.maximized){   
		if(L$(portlet.name+".max."+portlet.mode)){
			viewId = portlet.name+".max."+portlet.mode;
			var data = {	     
		     id : portlet.id,
		     userId : Light.getRememberedUserId()
		   };
		   Light.setPortletContent(data.id, Light.getViewTemplate(viewId, data));	   
		   return;
		}		
	}
	if(L$(viewId)){
	   var data = {	     
	     id : portlet.id,
	     userId : Light.getRememberedUserId() 
	   };
	   Light.setPortletContent(data.id, Light.getViewTemplate(viewId, data));
	   return;
	} 
	var aparams = Light.createParams(id,portlet);
	if(doRender)
		aparams.push("isRenderUrl=true");
	if(param){ 
	    var serverParam = param.split(";");		
	    for(var i = 0; i < serverParam.length; i++) {
		  	if(serverParam[i].length > 0){
		  		var pair = serverParam[i].split("=");
		  		if(pair.length == 2){	
		  			aparams.push(pair[0]+"="+escape(encodeURIComponent(pair[1])));	
		  		}
		  	}           	
	    }
	}
	if(parameter)
		aparams.push("parameter="+escape(encodeURIComponent(parameter)));
	var pars = aparams.join("&");
	if(supportRefresh && portlet.mode != Light._HEADER_MODE)
	 	 portlet.lastAction = pars;
	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
					  {asynchronous: asynchronous, evalScripts: portlet.allowJS, parameters: pars});  
	document.parameter = null;
	if(!portlet.isPopupWindow && portlet.mode != Light._HEADER_MODE && !isSafari()){
		Light.addHistory(id+"/"+Light._MODE_PREFIX+portlet.mode+"/"+Light._STATE_PREFIX+state+"/"+Light._PARAM_PREFIX+((param) ? param : ""));
	}
}

Light.executeRefresh = function(id) {  
	var portlet = Light.getPortletById(id); 
	if(!portlet) return;
  	if(portlet.lastAction){
     	var params = portlet.lastAction.split("&");     	
     	for(var i=params.length;i--;){
         	if(params[i].indexOf("state") >= 0 
         		|| params[i].indexOf("portletClientWidth") >= 0){
            	delete params[i];
         	}          	
     	}
     	if(portlet.maximized)
        	params.push("state=maximized"); 
     	if(portlet.refreshAction){
        	portlet.refreshAction = false;
        	params.push("refresh=true");
     	}  
     	params.push("portletClientWidth="+portlet.width,"disablePageRefresh=1");
     	var pars =params.join('&');     	
     	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
										{evalScripts: portlet.allowJS, parameters: pars});    
  	}else{      		
		Light.executePortlet(id);   
  	}  	
}

Light.executePortlet = function(id) {  
	var portlet = Light.getPortletById(id);  
  	if(!portlet) return;
  	var params = Light.createParams(id,portlet);
	params.push("isRenderUrl=true","disablePageRefresh=1");
  	if(portlet.refreshAction){
      	portlet.refreshAction = false;
      	params.push("refresh=true");
  	}  
  	var pars =params.join('&');
    portlet.lastAction = pars;
  	pars += "&portletClientWidth="+portlet.width;
  	if(portlet.state == Light._MINIMIZED_STATE && !portlet.minimized)
     	portlet.minimize();
  	else if(portlet.state == Light._MAXIMIZED_STATE && !portlet.maximized)
     	portlet.maximize();
  	else{
  		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});       
  	}
}

Light.executeAtClient = function (portlet) {
	if(!portlet.notFirstLoad){
	    var id = "portlet"+portlet.serverId;
	    var node = L$(id+".title");
	    if(node){	
	    	var title = node.value;
	    	if(title && title.trim().length > 0){
	    		var data = JSON.parse(title);
	    		Light.setPortletTitle(portlet.id, data);
	    	}
	    }
	    if(portlet.type == 2){
	    	var dataId = id+".data";
		    if(portlet.maximized && L$(id+".max.data")){
		    	dataId = id+".max.data";
		    }
		    node = L$(dataId);
		    if(node){ 
				try{
					var content = Light.getViewTemplate(dataId, null);
					var data = (content) ? JSON.parse(content) : {};
					if(typeof data.id == "undefined") data.id = portlet.id;
					if(typeof data.userId == "undefined") data.userId = Light.getRememberedUserId();
					if(typeof data.view == "undefined") data.view = portlet.name+"."+portlet.mode;
					if(typeof data.success == "undefined") data.success = "";
					if(typeof data.error == "undefined") data.error = "";
					portlet.data = data;
					if(L$(data.view)){			
						Light.setPortletContent(data.id, Light.getViewTemplate(data.view, data));
						portlet.notFirstLoad = true;
						return true;
					}
				}catch(err){
				}
			}	
		}		
	    if(portlet.maximized && L$(id+".max."+portlet.mode)){
	    	id = id+".max."+portlet.mode;
	    }else{
	    	id = id+"."+portlet.mode;    
	    }    
	    if(L$(id)){
	    	Light.setPortletContent(portlet.id, L$(id).value);
			Light.refreshTextFontSize();
			portlet.notFirstLoad = true;
			return true;
	    }		   
	}
    id = portlet.requestUrl;
    if(id.lastIndexOf("/") >= 0) id = id.substr(id.lastIndexOf("/")+1);
    if(id.indexOf(".") > 0) id = id.substring(0,id.indexOf("."));
    if(portlet.maximized && L$(id+".max."+portlet.mode)){
    	id = id+".max."+portlet.mode;
    }else{
    	id = id+"."+portlet.mode;    
    }    
    if(L$(id)){
       var data = {	     
	     id : portlet.id ,
 		 success : "",
	     error : "",
	     userId : Light.getRememberedUserId()
	   };
       Light.setPortletContent(data.id, Light.getViewTemplate(id, data));
       return true;
    }    
    return false;       	
}

Light.autoListenServer = function(){
	if(Light.isLogin()){	
		Light.listenServer(); 
	}  
}

Light.listenServer = function(){
	var opt = {
    	method: 'post',
    	postBody: '',
    	onSuccess: function(t) {
        	Light.listenServerHandler(t);
    	},
    	on404: function(t) {
	        Light.serverError = true;
	        Light.alert('Error 404: location "' + t.statusText + '" was not found.');        
	    },
	    onFailure: function(t) {
	        Light.serverError = true;
	        Light.alert('Error ' + t.status + ' -- ' + t.statusText);
    	}
   	}
   	Light.ajax.sendRequest(Light._LISTEN_SERVER, opt);
}

Light.listenServerHandler = function(t){ 
  	if(!Light.serverError){
   		setTimeout("Light.autoListenServer()", Light.LISTEN_SERVER_INTERVAL);
  	} 
  	if(!t.responseText) return;  
  	var data = JSON.parse(t.responseText);	
  	if(data.chat){
  		var chat = data.chat;
		if(chat.event == "chat"){
	      	if(!chat.ring)
	         	chat.ring = Light._IM_RING_TONE;
	      	chat.yes = Light.getMessage('BUTTON_OK'),
	   	  	chat.no = Light.getMessage('BUTTON_CANCEL')
	      	createPopupDiv('instantMessage','instantMessage.jst',280,chat,null,null);  
	  	} 
  	}
  	if(data.tabNotification){
	  	Light.portal.tabs.forEach(
		    function(tab, i, ar) {
		       	tab.setNotification();
		    }
		);
	  	if(data.tabNotification.length > 0){
	  		var len = data.tabNotification.length;
	  		for(var i=0;i<len;i++){
	  			var notification = data.tabNotification[i];
	  			if(notification.tabId){
	  				var tab = Light.getTabById(notification.tabId);
	  				if(tab) tab.setNotification(notification.notification);
	  			}
	  		}
	  	}
	}
  	if(data.portletNotification && data.portletNotification.length > 0){
  		var len = data.portletNotification.length;
  		for(var i=0;i<len;i++){
  			var notification = data.portletNotification[i];
  			if(notification.portletId){
  				var portlet = Light.portal.allPortlets.get(notification.portletId,'serverId');
  				if(portlet && notification.refresh) portlet.refresh(false);
  			}
  		}
  	}
}

Light.refreshWindowTransparent = function (){
 	$('#tabPanels').find('*').css({backgroundColor:(Light.portal.data.portletWindowTransparent) ? "transparent" : ""});
    Light.getCurrentTab().refreshWindowTransparent(); 	
}

Light.refreshTextColor = function (){
	$('#tabPanels').find('*').css({color:Light.portal.data.textColor});
	for(var i=Light.portal.allPortlets.length;i--;){
		var portlet = Light.portal.allPortlets[i];
       	if(portlet){     	               	
	      	portlet.refreshTextColor();
	   	}
	}   
}

Light.setTextFont = function (){
	var size = Light._FONT_SIZE + parseInt(Light.portal.data.fontSize); 
	$('#portalContainer').css({fontSize:size+"px",fontFamily:Light.portal.data.textFont});
}

Light.refreshTextFontSize = function (){ 
    var size = Light._FONT_SIZE + parseInt(Light.portal.data.fontSize); 
    $('#portalContainer').find('*').css({fontSize:size+"px",fontFamily:Light.portal.data.textFont});	
    $('#portalFooter').find('*').css({fontSize:size * 0.8+"px"}); 
    $('#portalTitle').css({fontSize:size * 1.5+"px"});      
	Light.getCurrentTab().rePositionAll();
}

Light.getCurrentTabId = function (){
	return (Light.currentTab) ? Light.currentTab.serverId : Light.seekCurrentTabId(Light._TAB_LIST);	
}

Light.seekCurrentTabId = function (id){
    var tabList = L$(id);
    if(tabList){
    	var tab =$('#'+id+' > li.current');
    	if(tab && tab.length){
    		var serverId = tab[0].id.split("_")[1];
    		if(L$(Light._TAB_LIST+serverId)){
			   return Light.seekCurrentTabId(Light._TAB_LIST+serverId);
			}
			else{
			   return serverId;
			}
    	}	    
	}
	return 0;
}

Light.setSessionTimer = function(){   
    if(!Light.isLogin()) return;    
    if(Light._SESSION_TIMEOUT == 0) return; //session will always keep alive
    if(Light.sessionTimer != null){
         clearTimeout(Light.sessionTimer);
         Light.sessionTimer = null;
    }
    Light.sessionStartdate = new Date();
    Light.sessionTimer=setTimeout((function() {Light.checkSessionTimeout(Light.sessionStartdate)}), Light._SESSION_TIMEOUT);
}

Light.checkSessionTimeout = function(date){
    if(Light.sessionStartdate == date){
       Light.sessionTimeoutWarningTime = Light._SESSION_TIMEOUT_WARNING;
       Light.sessionTimeoutWarning();
    }
}

Light.sessionTimeoutWarning = function(){  
    if(Light.sessionTimeoutWarningTime > 0){  
	    var data = {	   		
	   		ok : Light.getMessage('BUTTON_OK'),    	
    	    cancel : Light.getMessage('BUTTON_CANCEL'),
    	    timeLeft : Light.sessionTimeoutWarningTime / 1000
	    };
	    hidePopupDiv('sessionTimeoutWarning');
	    createPopupDiv('sessionTimeoutWarning','sessionTimeoutWarning.jst',400,data,null,null); 
	    Light.sessionTimeoutWarningTime = Light.sessionTimeoutWarningTime - 1000;
	    Light.sessionWarningTimer = setTimeout((function() {Light.sessionTimeoutWarning()}), 1000);
    }else{    
       Light.sessionTimeout();
    }
}

Light.sessionTimeout = function(){       
	Light.logout();
}

Light.refreshSessionTiimeout = function(){
    hidePopupDiv('sessionTimeoutWarning');
    clearTimeout(Light.sessionWarningTimer);
    Light.sessionWarningTimer = null;
	Light.setSessionTimer();
}

Light.refreshPortletTitle = function(t){    
	var data = JSON.parse(t.responseText);
	Light.setPortletTitle(data.id,data);	
}

Light.setPortletTitle= function(responseId,data){
	var portlet = Light.getPortletById(responseId);   
   	if(portlet){
   		portlet.setTitle(data);
   	}
}   	
   	
Light.setPortletContent= function(responseId,inHTML){
   	var portlet = Light.getPortletById(responseId);   
   	if(portlet && !portlet.minimized){
	   	if(portlet.allowJS){
	       	var scriptFragment= '(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)';
	       	var matchAll = new RegExp(scriptFragment, 'img');
	       	var scripts = inHTML.match(matchAll);
	       	if(scripts != null){
	       	   	portlet.setContent(inHTML.replace(matchAll,''));	
			   	for(var i=0,len=scripts.length;i<len;i++){
			       	var script= scripts[i];       
				   	var scriptBegin = '(?:<script.*?>)';		   
				   	script = script.replace(new RegExp(scriptBegin, 'img'),'');
				   	var scriptEnd = '(?:<\/script>)';		   
				   	script = script.replace(new RegExp(scriptEnd, 'img'),'');
				   	try{
				   		eval(script); 
				   	}catch(e){}
			   } 
		   }else{
			   var scriptFragment2= '(?:<script.*?>)';
		       matchAll = new RegExp(scriptFragment2, 'im');
		       var scripts = inHTML.match(matchAll);
			   if(scripts != null){ 
			   	   portlet.setContent(inHTML.replace(matchAll,''));
				   for(var i=0,len=scripts.length;i<len;i++){
				       var script= scripts[i];			       
				       var s = document.createElement('script');
					   s.type = 'text/javascript';
					   var indx = script.indexOf("src=");
					   script = script.substr(indx+5);
					   indx = script.indexOf(".js");
					   script = script.substring(0, indx+3);
					   s.src = script;
					   document.getElementsByTagName('head')[0].appendChild(s); 	       
				   } 
			   }else
			   		portlet.setContent(inHTML);	
		   }
		}else
	   	   portlet.setContent(inHTML);	      
	   Light.refreshTextFontSize();
	   Light.refreshTextColor();	  
	   setTimeout((function() {Light.repositionPortlets(portlet)}), 100);	   
	}
}

Light.repositionPortlets = function(portlet){
	portlet.parent.repositionPortlets(portlet);	 		
}

Light.getThemePath = function(theme){
	if(!theme) theme = Light.portal.data.theme;
	return Light.getContextPath()+Light._THEME_ROOT+theme;
}

Light.showLoadingBar = function(){
	$('<div>',{id:'loadingDiv',className:'loading'}).html(Light.getViewTemplate("loading.view")).appendTo('body');	
}

Light.hideLoadingBar = function() {
	$('#loadingDiv').remove();
}

Light.getScreenWidth = function(){
	var screenWidth = document.documentElement.scrollWidth;
	screenWidth = screenWidth - Light.getScrollbarWidth();
	return screenWidth;
}

Light.getBarWidth = function(){
	return Light._BAR_WIDTH; 
}

Light.getContainerWidth = function(){
	return Light._PORTAL_WIDTH;
}
Light.getContainerLeft = function(){
	if(Light.getScreenWidth() > (Light.getContainerWidth()+(Light.getBarWidth() * 2)))
		return (Light.getScreenWidth() - Light.getContainerWidth()) / 2;
	else
		return Light.getBarWidth();	  
}

Light.getScrollbarWidth = function(){
	return 24;
}

Light.getHighLightBar = function(){
	return Light.newElement({element:'div', 
	  					className:'highlight'
	  					});   
}

Light.getProgressBar = function(){
	return Light.newElement({element:'div', 
	  					className:'progressBar',
	  					innerHTML:Light.getViewTemplate("progressBar.view")
	  					});    
}

Light.getLatestActionObject = function(){
	return {
		  	event: null,
		  	id: null,
		    method: null,
		    portlet: null
	  		};
}

Light.refreshAd = function(){
	var ads = document.getElementsByTagName('iframe');
	if(ads && ads.length){
		for(var i=ads.length;i--;){		
			if(ads[i].id.startsWith('google_ads_frame')){				
				var src = ads[i].src + '&date='+new Date().getTime();
				ads[i].contentWindow.location.replace(src);
			}
		}
	}
}

Light.showPopupPortlet = function(left,width,title,image,name,request,mode){
	window.scrollTo(0,0);
	var portlet = new PortletPopupWindow(11,left,width,title,image,"",name,request,true,false,false,false,false,false,false,0,false,'','','','',false,false);    
    if(mode) portlet.mode = mode;
    portlet.refresh();
}

Light.playMusic= function (url) {
	if(!url && Light.portal.data.bgMusic && Light.portal.data.bgMusic != "none")
	 	 url = Light.portal.data.bgMusic;	
	if(url){ 
	    if(Light.music)
			document.body.removeChild(Light.music);
	    if(!url.startsWith('http') && !url.startsWith('HTTP')) url=Light._CONTEXT_PATH+url; 
	    Light.music = Light.newElement({element:'div',
	    							  innerHTML:"<embed src='"+url+"' autostart='true' loop='true' onreadystatechange='javascript:Light.checkMusic(this);' width='2' height='0'></embed><noembed><bgsound src='"+url+"' loop='1' onreadystatechange='javascript:Light.checkMusic(this);'></noembed>"
	    							  });
	    document.body.appendChild(Light.music); 
	}     
}
Light.addCSS = function(css){
	$('<link>',{rel:'stylesheet',type:'text/css',href:css}).appendTo('head');  
}
Light.hasPermission = function(permission){
	return (Light.portal.data.permissions & permission) ? true : false;
}
