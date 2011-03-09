
var Light={Version:"1.4.0",_PORTAL_ON:"PORTAL_ON",_PERSON_ID:"personId",_LOGINED_USER_ID:"loginedUserId",_REMEMBERED_USER_ID:"rememberedUserId",_REMEMBERED_USER_PASSWORD:"rememberedUserPassword",_IS_SIGN_OUT:"isSignOut",_USER_LOCALE:"userLocale",_CURRENT_TAB:"currentTab",_PRIVACY_PREFIX:"privacy_",_PAGE_PREFIX:"page_",_MODE_PREFIX:"mode_",_STATE_PREFIX:"state_",_PARAM_PREFIX:"param_",_FUNCTION_PREFIX:"function_",_PW_PREFIX:"pwid_",_TAB_PREFIX:"tab_",_TAB_HEADER_PREFIX:"tabHeader_",_TAB_TITLE_PREFIX:"tabTitle_",_TAB_LIST:"tabList",_TAB_PANELS:"tabPanels",_PANEL_PREFIX:"panel_",_VIEW_MODE:"view",_EDIT_MODE:"edit",_HELP_MODE:"help",_CONFIG_MODE:"config",_HEADER_MODE:"header",_NORMAL_STATE:"normal",_MINIMIZED_STATE:"minimized",_MAXIMIZED_STATE:"maximized",_INIT_VIEW:"initView",_PORTAL_VIEW:"portalView",_PORTLET_VIEW:"portletView",maxZIndex:1,cacheStorage:{}}
L$=function(element){if(arguments.length>1){for(var i=0,elements=[],length=arguments.length;i<length;i++)
elements.push(L$(arguments[i]));return elements;}
return document.getElementById(element);}
L$$=(typeof(document.querySelectorAll)!="undefined")?function(element){if(arguments.length>1){for(var i=0,elements=[],length=arguments.length;i<length;i++){var subs=L$$(arguments[i]);if(subs){for(var j=0,len=subs.length;j<len;j++)
elements.push(subs[j]);}}
return elements;}
return document.querySelectorAll(element);}:function(){return[];}
window.dhtmlHistory.create({toJSON:function(o){return JSON.stringify(o);},fromJSON:function(s){return JSON.parse(s);}});$(function(){Light.startup();});Light.startup=function(){Light.init();Light.start();}
Light.init=function(){Light.permission={VIEW:1<<0,EDIT:1<<1,HELP:1<<2,CONFIG:1<<3,ADD:1<<4,UPDATE:1<<5,DELETE:1<<6,PORTAL_SIGN_UP:1<<7,PORTAL_SIGN_IN:1<<8,PORTAL_CHANGE_LANGUAGE:1<<9,PORTAL_TURN_OFF:1<<10,PORTAL_TITLE:1<<11,PORTAL_OPTIONS:1<<12,PORTAL_TAB_ADD:1<<13,PORTAL_TAB_DELETE:1<<14,PORTAL_TAB_DELETE_ALL:1<<15,PORTAL_CONTENT_ADD:1<<16,PORTAL_CONTENT_DELETE:1<<17,PORTAL_CONTENT_DELETE_ALL:1<<18};Light.needRender=(L$('portalContainer'))?false:true;var n=L$('REQUEST_SUFFIX');var s=Light._REQUEST_SUFFIX=(n)?n.title:".lp";Light._GET_INIT_VIEW_TMPL="getInitViewTemplates"+s;if(!L$(Light._INIT_VIEW))Light.getInitViewTemplates(false);Light.needReload=!Light.needRender?false:((L$('portalJSON'))?false:true);if(!Light.needRender)Light.maxZIndex=1000;Light._LOAD_PORTAL="/loadPortal"+s;Light._LOAD_TAB_CONTENT="/loadPortalTabContent"+s;Light._GET_TAB_BY_ID="/getPortalTabById"+s;Light._GET_TABS_BY_PARENT="/getPortalTabsByParent"+s;Light._SHOW_SUB_TAB="/showSubTab"+s;Light._GET_PORTLETS_BY_TAB="/getPortletsByTab"+s;Light._GET_PORTAL_VIEW_TMPL="/getPortalViewTemplates"+s;Light._GET_PORTLET_VIEW_TMPL="/getPortletViewTemplates"+s;Light._CHANGE_TITLE="/changeTitle"+s;Light._CHANGE_LANGUAGE="/changeLanguage"+s;Light._CHANGE_REGION="/changeRegion"+s;Light._CHANGE_TIMEZONE="/changeTimeZone"+s;Light._CHANGE_GENERAL="/changeGeneral"+s;Light._CHANGE_THEME="/changeTheme"+s;Light._ADD_TAB="/addTab"+s;Light._MANAGE_TAB="/manageTab"+s;Light._ADD_SUB_TAB="/addSubTab"+s;Light._EDIT_TAB_TITLE="/editTabTitle"+s;Light._DELETE_TAB="/deleteTab"+s;Light._ADD_CONTENT="/addContent"+s;Light._CONFIG_PORTLET="/configPortlet"+s;Light._LOGIN="/login"+s;Light._LOGOUT="/logout"+s;Light._DELETE_PORTLET="/deletePortlet"+s;Light._CHANGE_POSITION="/changePosition"+s;Light._GET_USER_DETAIL="/getUserDetail"+s;Light._REMEBER_STATE="/rememberState"+s;Light._REMEBER_MODE="/rememberMode"+s;Light._CHAT_WITH_BUDDY="/chatWithBuddy"+s;Light._CHAT_WITH_MEMBER="/chatWithMember"+s;Light._CHAT_WITH_PROFILE="/chatWithProfile"+s;Light._ACCEPT_CHAT="/acceptChat"+s;Light._REFUSE_CHAT="/refuseChat"+s;Light._CLOSE_CHAT="/closeChat"+s;Light._DELETE_BUDDY="/deleteBuddy"+s;Light._LISTEN_SERVER="/listenServer"+s;Light._CHECK_MY_URL="/checkMyUrl"+s;Light._SAVE_MY_URL="/saveMyUrl"+s;Light._GET_OBJECT_DESC="/getObjectDesc"+s;Light._SAVE_OBJECT_COMMENT="/saveObjectComment"+s;Light._GET_OBJECT_COMMENTS="/getObjectComments"+s;Light._SEND_MESSAGE="/sendMessage"+s;Light._ADD_FRIEND="/addFriendRequest"+s;Light._ADD_TO_FAVORITES="/addToFavorites"+s;Light._FORWARD_TO_FRIENDS="/forwardToFriends"+s;Light._BLOCK_USER="/blockUser"+s;Light._JOIN_TO_GROUP="/joinToGroup"+s;Light._INVITE_TO_GROUP="/inviteToGroup"+s;Light._VALIDATE_USER_ID="/validateUserId"+s;Light._VALIDATE_INTERNAL_URI="/validateInternalUri"+s;Light._RESIGN_GROUP="/resignGroup"+s;Light._GET_GROUP_PRIVACY="/getGroupPrivacy"+s;Light._SAVE_GROUP_PRIVACY="/saveGroupPrivacy"+s;Light._DELETE_GROUP_PROFILE="/deleteGroupProfile"+s;Light._SAVE_NOTE="/saveNote"+s;Light._TRACK_RSS_ITEM="/trackRssItem"+s;Light._READ_RECOMMENDED_ITEM="/readRecommendedItem"+s;Light._READ_VIEWED_ITEM="/readViewedItem"+s;Light._READ_POP_ITEM="/readPopItem"+s;Light._POP_BLOG="/popBlog"+s;Light._POP_AD="/popAd"+s;Light._POP_RSS_ITEM="/popRssItem"+s;Light._POP_BLOG_ITEM="/popBlogItem"+s;Light._POP_DELI_ITEM="/popDeliItem"+s;Light._POP_BOOKMARK_ITEM="/popBookmarkItem"+s;Light._POP_YOUTUBE_ITEM="/popYouTubeItem"+s;Light._POP_FORUM_ITEM="/popForumItem"+s;Light._POP_TOPIC_ITEM="/popTopicItem"+s;Light._FWD_RSS_FRIEND="/forwardAdToFriend"+s;Light._FWD_BLOG_FRIEND="/forwardBlogToFriend"+s;Light._FWD_BOOKMARK_FRIEND="/forwardBookmarkToFriend"+s;Light._FWD_DELI_FRIEND="/forwardDeliToFriend"+s;Light._FWD_FORUM_FRIEND="/forwardForumToFriend"+s;Light._FWD_TOPIC_FRIEND="/forwardTopicToFriend"+s;Light._FWD_YOUTUBE_FRIEND="/forwardYouTubeToFriend"+s;Light._SAVE_AD_BOOKMARK="/saveAdToBookmark"+s;Light._SAVE_BLOG_BOOKMARK="/saveBlogToBookmark"+s;Light._SAVE_FORUM_BOOKMARK="/saveForumToBookmark"+s;Light._SAVE_TOPIC_BOOKMARK="/saveTopicToBookmark"+s;Light._SAVE_YOUTUBE_BOOKMARK="/saveYouTubeToBookmark"+s;Light._SAVE_TO_HEADER="/saveToHeader"+s;Light._SAVE_TO_BACKGROUND="/saveToBackground"+s;Light._SAVE_TO_BOOKMARK="/saveToBookmark"+s;Light._SAVE_TO_MY_PICTURE="/saveToMyPicture"+s;Light._SAVE_BUDDY_TYPE="/saveBuddyType"+s;Light._CONFIG_MY_PICTURE="/configMyPicture"+s;Light._GET_PPT_CONTENT="/getPicturePositionTaggingContent"+s;Light._GET_PPT_EDIT="/getPicturePositionTaggingEdit"+s;Light._SAVE_PPT="/savePPT"+s;Light._DELETE_PPT="/deletePPT"+s;Light._SAVE_PPT_COORDINATE="/savePPTCoordinate"+s;Light._SHOW_INVITE_LIST="/showInviteList"+s;Light._INVITE_BUDDYS_TO_CHAT="/inviteBuddysToChat"+s;Light._ENTER_CHATTING_ROOM="/enterChattingRoom"+s;Light._ADD_USER_TAG="/addUserTag"+s;Light._SUBSCRIBE="/subscribe"+s;Light._CROP_PROFILE_PHOTO="/cropProfilePhoto"+s;n=L$(Light._PORTAL_ON);Light._ON=(n)?parseInt(n.title):1;var portalMode=Light.getCookie(Light._PORTAL_ON);if(portalMode)Light._ON=parseInt(portalMode);n=L$('PORTAL_WIDTH');Light._PORTAL_WIDTH=(n)?parseInt(n.title):1016;if(isIPad())Light._PORTAL_WIDTH=Light.getScreenWidth()-Light.getBarWidth();n=L$('PORTAL_BAR_WIDTH');Light._BAR_WIDTH=(n)?parseInt(n.title):120;n=L$('PORTLET_RENDER_ID_PREFIX');Light._PR_PREFIX=(n)?n.title:"prid_";n=L$('PORTLET_TITLE_ID_PREFIX');Light._PT_PREFIX=(n)?n.title:"ptid_";n=L$('PORTLET_HEADER_ID_PREFIX');Light._PH_PREFIX=(n)?n.title:"phid_";n=L$('PORTLET_BUTTON_ID_PREFIX');Light._PB_PREFIX=(n)?n.title:"pbid_";n=L$('LISTEN_SERVER_INTERVAL');Light.LISTEN_SERVER_INTERVAL=(n)?parseInt(n.title):10000;n=L$('SESSION_TIMEOUT');Light._SESSION_TIMEOUT=(n)?parseInt(n.title):1800000;n=L$('SESSION_TIMEOUT_WARNING');Light._SESSION_TIMEOUT_WARNING=(n)?parseInt(n.title):120000;n=L$('REQUEST_TIMEOUT');Light._REQUEST_TIMEOUT=(n)?parseInt(n.title):10000;n=L$('FONT_SIZE');Light._FONT_SIZE=(n)?parseInt(n.title):12;n=L$('THEME_ROOT');Light._THEME_ROOT=(n)?n.title:"/light/theme/";n=L$('EMAIL_PATTERN');Light._EMAIL_PATTERN=(n)?n.title:"/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/";n=L$('URI_PATTERN');Light._URI_PATTERN=(n)?n.title:"/^([a-zA-Z0-9]{3,})+$/";n=L$('PASSWORD_PATTERN');Light._PASSWORD_PATTERN=(n)?n.title:"/^([a-zA-Z0-9]{3,})+$/";n=L$('DOMAIN_LIST');Light._DOMAIN_LIST=(n)?n.title:".com,.net,.me,.info,.org,.mobi,.biz,.us,.ca,.asia,.in,.ws,.ag,.com.ag,.net.ag,.org.ag,.at,.be,.cc,.cn,.com.cn,.org.cn,.net.cn,.de,.eu,.fm,.gs,.co.in,.firm.in,.gen.in,.ind.in,.net.in,.org.in,.jobs,.jp,.ms,.nu,.co.nz,.net.nz,.org.nz,.tc,.tv,.tw,.com.tw,.org.tw,.idv.tw,.co.uk,.me.uk,.org.uk,.vg";n=L$('JS_HISTORY_DISABLE_LIST');Light._JS_HISTORY_DISABLE_LIST=(n)?n.title:false;n=L$('IM_RING_TONE');Light._IM_RING_TONE=(n)?n.title:"/ringtone/default.mp3";n=L$('JAVASCRIPT_LIBRARYS');Light._JAVASCRIPT_LIBRARYS=(n)?n.title:"";n=L$('CONTEXT_PATH');Light._CONTEXT_PATH=(n)?n.title:"";n=L$('PORTAL_COMMON_THEME');Light._PORTAL_COMMON_THEME=(n)?n.title:"common.css";n=L$('PORTAL_THEME_BASE');Light._PORTAL_THEME_BASE=(n)?n.title:"theme.css";n=L$('PORTAL_THEME');Light._PORTAL_THEME=(n)?n.title:"theme1";if($('link[href$=/theme/common.css]').length==0){Light.addCSS(Light.getContextPath()+Light._THEME_ROOT+(isIE()?'MSIE/':isOpera()?'Opera/':'')+Light._PORTAL_COMMON_THEME);Light.addCSS(Light.getThemePath(Light._PORTAL_THEME)+(isIE()?'/MSIE/':'/')+Light._PORTAL_THEME_BASE);}
Light.getPortalViewTemplates();Light.getPortletViewTemplates();Light.getScripts();Light.loading=Light.getViewTemplate("loadingPortlet.view");Light.empty="<br/>";}
Light.start=function(){if(Light._ON){if(Light.needRender)Light.showLoadingBar();Light.portal=new LightPortal();Light.portal.startup();Light.afterStartup();if(Light.needRender)Light.hideLoadingBar();}else{Light.offMode();}}
Light.onMode=function(){Light._ON=true;Light.setCookie(Light._PORTAL_ON,1);$('#offMode').remove();Light.start();}
Light.offMode=function(){Light._ON=false;Light.setCookie(Light._PORTAL_ON,0);$('<span>',{id:'offMode'}).html(Light.getViewTemplate("offMode.view",null)).appendTo('body');}
Light.afterStartup=function(){dhtmlHistory.initialize();dhtmlHistory.addListener(Light.historyListener);setTimeout((function(){Light.refreshAd()}),1000);Light.autoListenServer();Light.setSessionTimer();}
Light.historyListener=function(location,historyData){if(Light.disableHistory())return;if(isSafari()){var url=document.location.href;var i=url.indexOf("#");location=(i>=0)?url.substr(i+1):"";}
if(location.indexOf(Light._PRIVACY_PREFIX)>=0){var index=location.indexOf(Light._PRIVACY_PREFIX);var privacy=location.substr(index+Light._PRIVACY_PREFIX.length,1);if(privacy>0&&!Light.isLogin()){Light.portal.allTabs[0].focus(false);Light.showLogin(false);return;}}
var len=Light.portal.allPortlets.length-1;for(var i=len;i>=0;i--){var portlet=Light.portal.allPortlets[i];if(portlet){if(portlet.isPopupWindow){portlet.close(true);}
break;}}
var actions=location.split("/");var pid;var mode='';var state='';var params='';var done;len=actions.length;for(var i=0;i<len;i++){if(actions[i].indexOf(Light._PAGE_PREFIX)>=0){var tabIds=actions[i].substr(Light._PAGE_PREFIX.length).split("-");var found=false;var tLen=tabIds.length;for(var j=0;j<tLen;j++){var tab=Light.getTabById(tabIds[j]);if(tab){var segment=L$(Light._TAB_HEADER_PREFIX+tab.serverId);if(segment){found=true;if(segment.className!="current"){tab.focus(false);}}}}
if(!found&&tLen>0){var opt={method:'post',asynchronous:false,postBody:'tabId='+tabIds[tLen-1]}
var tabJSON=Light.ajax.sendRequest(Light._GET_TAB_BY_ID,opt);if(tabJSON&&tabJSON.length>10){tabDatas=JSON.parse(tabJSON);var tab=new LightPortalTab(tabDatas[0]);tab.open();Light.portal.tabs.push(tab);tab.focus(false);found=true;}}
if(!found&&Light.portal.tabs.length>0)Light.portal.tabs[0].focus();}
else if(actions[i].indexOf(Light._PR_PREFIX)>=0){pid=actions[i];}
else if(actions[i].indexOf(Light._MODE_PREFIX)>=0){mode=actions[i].substr(Light._MODE_PREFIX.length);}
else if(actions[i].indexOf(Light._STATE_PREFIX)>=0){state=actions[i].substr(Light._STATE_PREFIX.length);}
else if(actions[i].indexOf(Light._PARAM_PREFIX)>=0){params=actions[i].substr(Light._PARAM_PREFIX.length);}
else if(actions[i].indexOf(Light._FUNCTION_PREFIX)>=0){var method=actions[i].substr(Light._FUNCTION_PREFIX.length);try{eval(method+"()");done=true;}catch(err){setTimeout((function(){Light.getCurrentTab().refreshPortlets()}),200);}}}
if(pid){if(!done)
setTimeout((function(){Light.executeRender(pid,mode,state,params)}),200);}else{var tab=Light.getCurrentTab();if(tab)tab.normalAll();}}
Light.addHistory=function(action){if(Light.disableHistory())return;if(isSafari()&&typeof action!="undefined"&&action.indexOf(Light._FUNCTION_PREFIX)>=0)return;var tab=Light.getCurrentTab();var historyId=Light._PRIVACY_PREFIX+tab.privacy+"/"+Light._PAGE_PREFIX+((tab.parentId>0)?tab.parentId+"-"+tab.serverId:tab.serverId);if(typeof action!="undefined"&&action.length>0)historyId+="/"+action;dhtmlHistory.add(historyId,"");}
Light.addFunctionHistory=function(name){Light.addHistory(Light._FUNCTION_PREFIX+name);}
Light.disableHistory=function(){if(!Light._ON)return true;if(Light._JS_HISTORY_DISABLE_LIST){var patterns=Light._JS_HISTORY_DISABLE_LIST.split(',');var url=document.location.href;for(var i in patterns){if(url.indexOf(patterns[i])>0)return true;}}
return false;}
Light.refreshPortal=function(flag){Light.reloadPortal(flag);}
Light.reloadPortal=function(flag){if(flag){var url=document.location.href;if(url.indexOf("#")>0)url=url.substring(0,url.indexOf("#"));window.location=url;}else
window.location.reload(true);}
Light.getContextPath=function(){var n=L$('CONTEXT_PATH');return(n)?n.title:Light._CONTEXT_PATH;}
Light.getAllViewTemplates=function(){Light.getInitViewTemplates();Light.getPortalViewTemplates();Light.getPortletViewTemplates();}
Light.getInitViewTemplates=function(flag){var value=Light.ajax.sendRequest(Light._GET_INIT_VIEW_TMPL,{method:'get',asynchronous:(flag==null||flag)?true:false,onSuccess:Light.getInitViewTemplatesHandler});if(value)Light.setInitViewTemplates(value);}
Light.getInitViewTemplatesHandler=function(t){Light.setInitViewTemplates(t.responseText);}
Light.setInitViewTemplates=function(tmpl){$('#'+Light._INIT_VIEW).remove();$('<div>',{id:Light._INIT_VIEW}).html(tmpl).appendTo('body');}
Light.getPortalViewTemplates=function(){Light.ajax.sendRequest(Light._GET_PORTAL_VIEW_TMPL,{method:'get',onSuccess:Light.getPortalViewTemplatesHandler});}
Light.getPortalViewTemplatesHandler=function(t){$('#'+Light._PORTAL_VIEW).remove();$('<div>',{id:Light._PORTAL_VIEW}).html(t.responseText).appendTo('body');}
Light.getPortletViewTemplates=function(){Light.ajax.sendRequest(Light._GET_PORTLET_VIEW_TMPL,{method:'get',onSuccess:Light.getPortletViewTemplatesHandler});}
Light.getPortletViewTemplatesHandler=function(t){$('#'+Light._PORTLET_VIEW).remove();$('<div>',{id:Light._PORTLET_VIEW}).html(t.responseText).appendTo('body');}
Light.getScripts=function(){if(Light._JAVASCRIPT_LIBRARYS){var params=Light._JAVASCRIPT_LIBRARYS.split(",");for(var i=0;i<params.length;i++){if(params[i]){var script=document.createElement("script");script.setAttribute("language","JavaScript");script.setAttribute("src",(params[i].startsWith("http"))?params[i]:document.location.protocol+"//"+document.domain+((document.location.port)?":"+document.location.port:"")+Light.getContextPath()+"/"+params[i]);document.getElementsByTagName("head")[0].appendChild(script);}}}}
Light.getViewTemplate=function(viewId,data){return(L$(viewId))?TrimPath.processDOMTemplate(viewId,data):"";}
Light.isLogin=function(){return(Light.getCookie(Light._LOGINED_USER_ID))?true:false;}
Light.getUserId=function(){return(Light.userId)?Light.userId:Light.getCookie(Light._LOGINED_USER_ID);}
Light.getRememberedUserId=function(){var userId=Light.getCookie(Light._REMEMBERED_USER_ID);return(userId)?Base64.decode(userId):"";}
Light.getCurrentTab=function(){return(Light.currentTab)?Light.currentTab:Light.portal.allTabs.get(Light.getCurrentTabId(),'serverId');}
Light.getTabById=function(id){return Light.portal.allTabs.get(id,'serverId');}
Light.getTabByName=function(name){return Light.portal.allTabs.get(name,'label');}
Light.getPortletById=function(id){var portletIds=id.split("_");if(portletIds.length>2){var tIndex=portletIds[1];var position=portletIds[2];var index=portletIds[3];var portlet=Light.portal.tabs[tIndex].portlets[position][index];return portlet;}else if(portletIds.length==2){return Light.portal.allPortlets.get(portletIds[1],'serverId');}}
Light.getPortletByName=function(name){var tab=Light.getCurrentTab();var len=tab.portlets.length;for(var i=0;i<len;i++){var portlets=tab.portlets[i];if(portlets){var portlet=portlets.get(name,'name');if(portlet){return portlet;}}}
return Light.portal.allPortlets.get(name,'name');}
Light.getPopupPortletByName=function(name){for(var i=Light.portal.allPortlets.length;i--;){var portlet=Light.portal.allPortlets[i];if(portlet&&portlet.isPopupWindow&&portlet.name==name){return portlet;}}}
Light.noticePortlet=function(name,mode,state,param,doRender,asynchronous){var portlet=Light.getPortletByName(name);if(portlet){if(doRender==null)doRender=true;if(asynchronous==null)asynchronous=true;Light.executeRender(portlet.id,mode,state,param,'',true,doRender,asynchronous);}}
Light.createParams=function(id,portlet){var aparams=[];aparams.push("responseId="+id);aparams.push("tabId="+Light.getCurrentTab().serverId);aparams.push("portletId="+portlet.serverId);aparams.push("userId="+Light.getUserId());aparams.push("mode="+portlet.mode);if(portlet.maximized)
aparams.push("state=maximized");if(portlet.parameter)
aparams.push(portlet.parameter);return aparams;}
Light.executeAction=function(id,form,action,actionName,parameter,mode,state,param){var portlet=Light.getPortletById(id);if(!portlet)return;if(document.state)state=document.state;if(state){if(((state==Light._MAXIMIZED_STATE&&!portlet.maximized)||(state==Light._NORMAL_STATE&&portlet.maximized))&&portlet.allowMaximized){portlet.windowMaximize();}else if(state==Light._MINIMIZED_STATE&&portlet.allowMinimized){portlet.windowMinimize(portlet);}}
portlet.mode=(document.mode)?document.mode:((mode)?mode:Light._VIEW_MODE);portlet.refreshButtons();var aparams=Light.createParams(id,portlet);if(!form)form=$('#'+id+' form');if(form){var fields=$(form).serializeArray();$.each(fields,function(i,field){aparams.push(field.name+"="+escape(encodeURIComponent(field.value)));});}
if(action)aparams.push("action="+action);if(parameter)aparams.push("parameter="+escape(encodeURIComponent(parameter)));if(param){var serverParam=param.split(";");for(var i=0;i<serverParam.length;i++){if(serverParam[i].length>0){var pair=serverParam[i].split("=");if(pair.length==2){aparams.push(pair[0]+"="+escape(encodeURIComponent(pair[1])));}}}}
var pars=aparams.join("&");Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{method:'post',evalScripts:portlet.allowJS,parameters:pars});if(document.resetLastAction)portlet.lastAction=null;document.currentForm=null;document.pressed=null;document.pressedName=null;document.parameter=null;document.mode=null;document.state=null;document.resetLastAction=null;}
Light.executeRender=function(id,mode,state,param,parameter,supportRefresh,doRender,asynchronous){if(supportRefresh==null)supportRefresh=true;if(doRender==null)doRender=true;if(asynchronous==null)asynchronous=true;var portlet=Light.getPortletById(id);if(!portlet)return;if(state){if(((state==Light._MAXIMIZED_STATE&&!portlet.maximized)||(state==Light._NORMAL_STATE&&portlet.maximized))&&portlet.allowMaximized){portlet.windowMaximize();}
if(state==Light._MINIMIZED_STATE){if(!portlet.maximized&&portlet.allowMinimized)
portlet.windowMaximize();return;}}
if(!mode){portlet.mode=Light._VIEW_MODE;}else if(mode!=Light._HEADER_MODE){portlet.mode=mode;portlet.refreshButtons();}
var viewId=portlet.name+"."+portlet.mode;if(portlet.maximized){if(L$(portlet.name+".max."+portlet.mode)){viewId=portlet.name+".max."+portlet.mode;var data={id:portlet.id,userId:Light.getRememberedUserId()};Light.setPortletContent(data.id,Light.getViewTemplate(viewId,data));return;}}
if(L$(viewId)){var data={id:portlet.id,userId:Light.getRememberedUserId()};Light.setPortletContent(data.id,Light.getViewTemplate(viewId,data));return;}
var aparams=Light.createParams(id,portlet);if(doRender)
aparams.push("isRenderUrl=true");if(param){var serverParam=param.split(";");for(var i=0;i<serverParam.length;i++){if(serverParam[i].length>0){var pair=serverParam[i].split("=");if(pair.length==2){aparams.push(pair[0]+"="+escape(encodeURIComponent(pair[1])));}}}}
if(parameter)
aparams.push("parameter="+escape(encodeURIComponent(parameter)));var pars=aparams.join("&");if(supportRefresh&&portlet.mode!=Light._HEADER_MODE)
portlet.lastAction=pars;Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{asynchronous:asynchronous,evalScripts:portlet.allowJS,parameters:pars});document.parameter=null;if(!portlet.isPopupWindow&&portlet.mode!=Light._HEADER_MODE&&!isSafari()){Light.addHistory(id+"/"+Light._MODE_PREFIX+portlet.mode+"/"+Light._STATE_PREFIX+state+"/"+Light._PARAM_PREFIX+((param)?param:""));}}
Light.executeRefresh=function(id){var portlet=Light.getPortletById(id);if(!portlet)return;if(portlet.lastAction){var params=portlet.lastAction.split("&");for(var i=params.length;i--;){if(params[i].indexOf("state")>=0||params[i].indexOf("portletClientWidth")>=0){delete params[i];}}
if(portlet.maximized)
params.push("state=maximized");if(portlet.refreshAction){portlet.refreshAction=false;params.push("refresh=true");}
params.push("portletClientWidth="+portlet.width,"disablePageRefresh=1");var pars=params.join('&');Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts:portlet.allowJS,parameters:pars});}else{Light.executePortlet(id);}}
Light.executePortlet=function(id){var portlet=Light.getPortletById(id);if(!portlet)return;var params=Light.createParams(id,portlet);params.push("isRenderUrl=true","disablePageRefresh=1");if(portlet.refreshAction){portlet.refreshAction=false;params.push("refresh=true");}
var pars=params.join('&');portlet.lastAction=pars;pars+="&portletClientWidth="+portlet.width;if(portlet.state==Light._MINIMIZED_STATE&&!portlet.minimized)
portlet.minimize();else if(portlet.state==Light._MAXIMIZED_STATE&&!portlet.maximized)
portlet.maximize();else{Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts:portlet.allowJS,parameters:pars});}}
Light.executeAtClient=function(portlet){if(!portlet.notFirstLoad){var id="portlet"+portlet.serverId;var node=L$(id+".title");if(node){var title=node.value;if(title&&title.trim().length>0){var data=JSON.parse(title);Light.setPortletTitle(portlet.id,data);}}
if(portlet.type==2){var dataId=id+".data";if(portlet.maximized&&L$(id+".max.data")){dataId=id+".max.data";}
node=L$(dataId);if(node){try{var content=Light.getViewTemplate(dataId,null);var data=(content)?JSON.parse(content):{};if(typeof data.id=="undefined")data.id=portlet.id;if(typeof data.userId=="undefined")data.userId=Light.getRememberedUserId();if(typeof data.view=="undefined")data.view=portlet.name+"."+portlet.mode;if(typeof data.success=="undefined")data.success="";if(typeof data.error=="undefined")data.error="";portlet.data=data;if(L$(data.view)){Light.setPortletContent(data.id,Light.getViewTemplate(data.view,data));portlet.notFirstLoad=true;return true;}}catch(err){}}}
if(portlet.maximized&&L$(id+".max."+portlet.mode)){id=id+".max."+portlet.mode;}else{id=id+"."+portlet.mode;}
if(L$(id)){Light.setPortletContent(portlet.id,L$(id).value);Light.refreshTextFontSize();portlet.notFirstLoad=true;return true;}}
id=portlet.requestUrl;if(id.lastIndexOf("/")>=0)id=id.substr(id.lastIndexOf("/")+1);if(id.indexOf(".")>0)id=id.substring(0,id.indexOf("."));if(portlet.maximized&&L$(id+".max."+portlet.mode)){id=id+".max."+portlet.mode;}else{id=id+"."+portlet.mode;}
if(L$(id)){var data={id:portlet.id,success:"",error:"",userId:Light.getRememberedUserId()};Light.setPortletContent(data.id,Light.getViewTemplate(id,data));return true;}
return false;}
Light.autoListenServer=function(){if(Light.isLogin()){Light.listenServer();}}
Light.listenServer=function(){var opt={method:'post',postBody:'',onSuccess:function(t){Light.listenServerHandler(t);},on404:function(t){Light.serverError=true;Light.alert('Error 404: location "'+t.statusText+'" was not found.');},onFailure:function(t){Light.serverError=true;Light.alert('Error '+t.status+' -- '+t.statusText);}}
Light.ajax.sendRequest(Light._LISTEN_SERVER,opt);}
Light.listenServerHandler=function(t){if(!Light.serverError){setTimeout("Light.autoListenServer()",Light.LISTEN_SERVER_INTERVAL);}
if(!t.responseText)return;var data=JSON.parse(t.responseText);if(data.chat){var chat=data.chat;if(chat.event=="chat"){if(!chat.ring)
chat.ring=Light._IM_RING_TONE;chat.yes=Light.getMessage('BUTTON_OK'),chat.no=Light.getMessage('BUTTON_CANCEL')
createPopupDiv('instantMessage','instantMessage.jst',280,chat,null,null);}}
if(data.tabNotification){Light.portal.tabs.forEach(function(tab,i,ar){tab.setNotification();});if(data.tabNotification.length>0){var len=data.tabNotification.length;for(var i=0;i<len;i++){var notification=data.tabNotification[i];if(notification.tabId){var tab=Light.getTabById(notification.tabId);if(tab)tab.setNotification(notification.notification);}}}}
if(data.portletNotification&&data.portletNotification.length>0){var len=data.portletNotification.length;for(var i=0;i<len;i++){var notification=data.portletNotification[i];if(notification.portletId){var portlet=Light.portal.allPortlets.get(notification.portletId,'serverId');if(portlet&&notification.refresh)portlet.refresh(false);}}}}
Light.refreshWindowTransparent=function(){$('#tabPanels').find('*').css({backgroundColor:(Light.portal.data.portletWindowTransparent)?"transparent":""});Light.getCurrentTab().refreshWindowTransparent();}
Light.refreshTextColor=function(){$('#tabPanels').find('*').css({color:Light.portal.data.textColor});for(var i=Light.portal.allPortlets.length;i--;){var portlet=Light.portal.allPortlets[i];if(portlet){portlet.refreshTextColor();}}}
Light.setTextFont=function(){var size=Light._FONT_SIZE+parseInt(Light.portal.data.fontSize);$('#portalContainer').css({fontSize:size+"px",fontFamily:Light.portal.data.textFont});}
Light.refreshTextFontSize=function(){var size=Light._FONT_SIZE+parseInt(Light.portal.data.fontSize);$('#portalContainer').find('*').css({fontSize:size+"px",fontFamily:Light.portal.data.textFont});$('#portalFooter').find('*').css({fontSize:size*0.8+"px"});$('#portalTitle').css({fontSize:size*1.5+"px"});Light.getCurrentTab().rePositionAll();}
Light.getCurrentTabId=function(){return(Light.currentTab)?Light.currentTab.serverId:Light.seekCurrentTabId(Light._TAB_LIST);}
Light.seekCurrentTabId=function(id){var tabList=L$(id);if(tabList){var len=tabList.children.length;for(i=0;i<len;i++){var node=tabList.children[i];if(node&&node.tagName=="LI"){if("current"==node.className){var tabId=node.id;var serverId=tabId.split("_")[1];if(L$(Light._TAB_LIST+serverId)){return Light.seekCurrentTabId(Light._TAB_LIST+serverId);}
else{return serverId;}}}}}
return 0;}
Light.setSessionTimer=function(){if(!Light.isLogin())return;if(Light._SESSION_TIMEOUT==0)return;if(Light.sessionTimer!=null){clearTimeout(Light.sessionTimer);Light.sessionTimer=null;}
Light.sessionStartdate=new Date();Light.sessionTimer=setTimeout((function(){Light.checkSessionTimeout(Light.sessionStartdate)}),Light._SESSION_TIMEOUT);}
Light.checkSessionTimeout=function(date){if(Light.sessionStartdate==date){Light.sessionTimeoutWarningTime=Light._SESSION_TIMEOUT_WARNING;Light.sessionTimeoutWarning();}}
Light.sessionTimeoutWarning=function(){if(Light.sessionTimeoutWarningTime>0){var data={ok:Light.getMessage('BUTTON_OK'),cancel:Light.getMessage('BUTTON_CANCEL'),timeLeft:Light.sessionTimeoutWarningTime/1000};hidePopupDiv('sessionTimeoutWarning');createPopupDiv('sessionTimeoutWarning','sessionTimeoutWarning.jst',400,data,null,null);Light.sessionTimeoutWarningTime=Light.sessionTimeoutWarningTime-1000;Light.sessionWarningTimer=setTimeout((function(){Light.sessionTimeoutWarning()}),1000);}else{Light.sessionTimeout();}}
Light.sessionTimeout=function(){Light.logout();}
Light.refreshSessionTiimeout=function(){hidePopupDiv('sessionTimeoutWarning');clearTimeout(Light.sessionWarningTimer);Light.sessionWarningTimer=null;Light.setSessionTimer();}
Light.refreshPortletTitle=function(t){var data=JSON.parse(t.responseText);Light.setPortletTitle(data.id,data);}
Light.setPortletTitle=function(responseId,data){var portlet=Light.getPortletById(responseId);if(portlet){portlet.setTitle(data);}}
Light.setPortletContent=function(responseId,inHTML){var portlet=Light.getPortletById(responseId);if(portlet&&!portlet.minimized){if(portlet.allowJS){var scriptFragment='(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)';var matchAll=new RegExp(scriptFragment,'img');var scripts=inHTML.match(matchAll);if(scripts!=null){portlet.setContent(inHTML.replace(matchAll,''));for(var i=0,len=scripts.length;i<len;i++){var script=scripts[i];var scriptBegin='(?:<script.*?>)';script=script.replace(new RegExp(scriptBegin,'img'),'');var scriptEnd='(?:<\/script>)';script=script.replace(new RegExp(scriptEnd,'img'),'');try{eval(script);}catch(e){}}}else{var scriptFragment2='(?:<script.*?>)';matchAll=new RegExp(scriptFragment2,'im');var scripts=inHTML.match(matchAll);if(scripts!=null){portlet.setContent(inHTML.replace(matchAll,''));for(var i=0,len=scripts.length;i<len;i++){var script=scripts[i];var s=document.createElement('script');s.type='text/javascript';var indx=script.indexOf("src=");script=script.substr(indx+5);indx=script.indexOf(".js");script=script.substring(0,indx+3);s.src=script;document.getElementsByTagName('head')[0].appendChild(s);}}else
portlet.setContent(inHTML);}}else
portlet.setContent(inHTML);Light.refreshTextFontSize();Light.refreshTextColor();setTimeout((function(){Light.repositionPortlets(portlet)}),100);}}
Light.repositionPortlets=function(portlet){portlet.parent.repositionPortlets(portlet);}
Light.getThemePath=function(theme){if(!theme)theme=Light.portal.data.theme;return Light.getContextPath()+Light._THEME_ROOT+theme;}
Light.showLoadingBar=function(){$('<div>',{id:'loadingDiv',className:'loading'}).html(Light.getViewTemplate("loading.view")).appendTo('body');}
Light.hideLoadingBar=function(){$('#loadingDiv').remove();}
Light.getScreenWidth=function(){var screenWidth=document.documentElement.scrollWidth;screenWidth=screenWidth-Light.getScrollbarWidth();return screenWidth;}
Light.getBarWidth=function(){return Light._BAR_WIDTH;}
Light.getContainerWidth=function(){return Light._PORTAL_WIDTH;}
Light.getContainerLeft=function(){if(Light.getScreenWidth()>(Light.getContainerWidth()+(Light.getBarWidth()*2)))
return(Light.getScreenWidth()-Light.getContainerWidth())/2;else
return Light.getBarWidth();}
Light.getScrollbarWidth=function(){return 24;}
Light.getHighLightBar=function(){return Light.newElement({element:'div',className:'highlight'});}
Light.getProgressBar=function(){return Light.newElement({element:'div',className:'progressBar',innerHTML:Light.getViewTemplate("progressBar.view")});}
Light.getLatestActionObject=function(){return{event:null,id:null,method:null,portlet:null};}
Light.refreshAd=function(){var ads=document.getElementsByTagName('iframe');if(ads&&ads.length){for(var i=ads.length;i--;){if(ads[i].id.startsWith('google_ads_frame')){var src=ads[i].src+'&date='+new Date().getTime();ads[i].contentWindow.location.replace(src);}}}}
Light.showPopupPortlet=function(left,width,title,image,name,request,mode){window.scrollTo(0,0);var portlet=new PortletPopupWindow(11,left,width,title,image,"",name,request,true,false,false,false,false,false,false,0,false,'','','','',false,false);if(mode)portlet.mode=mode;portlet.refresh();}
Light.playMusic=function(url){if(!url&&Light.portal.data.bgMusic&&Light.portal.data.bgMusic!="none")
url=Light.portal.data.bgMusic;if(url){if(Light.music)
document.body.removeChild(Light.music);if(!url.startsWith('http')&&!url.startsWith('HTTP'))url=Light._CONTEXT_PATH+url;Light.music=Light.newElement({element:'div',innerHTML:"<embed src='"+url+"' autostart='true' loop='true' onreadystatechange='javascript:Light.checkMusic(this);' width='2' height='0'></embed><noembed><bgsound src='"+url+"' loop='1' onreadystatechange='javascript:Light.checkMusic(this);'></noembed>"});document.body.appendChild(Light.music);}}
Light.addCSS=function(css){$('<link>',{rel:'stylesheet',type:'text/css',href:css}).appendTo('head');}
Light.hasPermission=function(permission){return(Light.portal.data.permissions&permission)?true:false;}
if(window.DOMParser&&window.XMLSerializer&&window.Node&&Node.prototype&&Node.prototype.__defineGetter__){if(!Document.prototype.loadXML){Document.prototype.loadXML=function(s){var doc2=(new DOMParser()).parseFromString(s,"text/xml");while(this.hasChildNodes())
this.removeChild(this.lastChild);for(var i=0;i<doc2.childNodes.length;i++){this.appendChild(this.importNode(doc2.childNodes[i],true));}};}
Document.prototype.__defineGetter__("xml",function(){return(new XMLSerializer()).serializeToString(this);});}
Light.ajax={sendRequestAndUpdate:function(requestName,container,options){var request=this.getXmlHttpObject();if(!options.method)options.method="post";if(options.asynchronous==null)options.asynchronous=true;var url=(requestName.lastIndexOf("/")<=0)?Light.getContextPath()+requestName:requestName;if(options.method=='get'){if(url.indexOf("?")>0)
url+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;else
url+="?_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;}
request.open(options.method,url,options.asynchronous);if(options.asynchronous){request.onreadystatechange=function(){if(request.readyState==4){Light.ajax.onRequestComplete(request);}};}
if(options.method=='post')
request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");else
request.setRequestHeader("Content-Type","text/xml");var parameter=options.parameters;if(options.postBody)parameter=options.postBody;if(parameter)
parameter+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;else
parameter="_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;request.send(options.method=='post'?parameter:null);log("url: "+url+" method: "+options.method+((options.method=='post')?" parameter: "+parameter:"")+" asyn: "+options.asynchronous);setTimeout((function(){Light.ajax.timeout(request,container)}),Light._REQUEST_TIMEOUT);if(!options.asynchronous)Light.ajax.onRequestComplete(request);},timeout:function(request,container){if(request){if(request.status<200||request.status>=300){request.abort();Light.setPortletContent(container,"Content is not available.");request=null;}}else{Light.setPortletContent(container,"Content is not available.");}},sendRequest:function(requestName,options){var request=this.getXmlHttpObject();if(!options.method)options.method='post';if(options.asynchronous==null)options.asynchronous=true;var context=Light.getContextPath();var url=(requestName.lastIndexOf("/")<=0)?((context)?context+requestName:requestName):requestName;if(options.method=='get'){if(url.indexOf("?")>0)
url+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;else
url+="?_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;}
request.open(options.method,url,options.asynchronous);if(options.asynchronous){request.onreadystatechange=function(){if(request.readyState==4&&request.status==200){if(options.onSuccess!=null)
options.onSuccess(request);request=null;}};}
if(options.method=='post')
request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");else
request.setRequestHeader("Content-Type","text/xml");var parameter=options.parameters;if(options.postBody)parameter=options.postBody;if(parameter)
parameter+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;else
parameter="_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;request.send(options.method=='post'?parameter:null);log("url: "+url+" method: "+options.method+((options.method=='post')?" parameter: "+parameter:"")+" asyn: "+options.asynchronous);if(!options.asynchronous)return request.responseText;},onRequestComplete:function(request){if(!request)return;if(request.status!=200)return;if(request.responseText){var content=request.responseText;var index=content.indexOf("id='");while(index>0){var portletContent=content.substr(index+4);index=portletContent.indexOf("'")
var responseId=portletContent.substring(0,index);index=portletContent.indexOf("'>");portletContent=portletContent.substr(index+2);index=portletContent.indexOf("</response>");var pContent=portletContent.substring(0,index);if(responseId.startsWith(Light._PT_PREFIX)){if(pContent&&pContent.trim().length>0){var data=JSON.parse(pContent);Light.setPortletTitle(responseId,data);}}else
Light.setPortletContent(responseId,pContent);content=portletContent.substr(index);index=content.indexOf("id='");}}else if(request.responseXML){var response=request.responseXML.getElementsByTagName("ajax-response");if(response)
Light.ajax.processAjaxResponse(response[0].childNodes);}
request=null;},processAjaxResponse:function(xmlResponseElements){for(var i=0;i<xmlResponseElements.length;i++){var responseElement=xmlResponseElements[i];if(responseElement.nodeType!=1)
continue;var responseId=responseElement.getAttribute("id");var content=Light.ajax.getContentAsString(responseElement);if(responseId.startsWith(Light._PT_PREFIX)){if(content&&content.trim().length>0){var data=JSON.parse(content);Light.setPortletTitle(responseId,data);}}else
Light.setPortletContent(responseId,content);}},getContentAsString:function(parentNode){return parentNode.xml!=undefined?Light.ajax.getContentAsStringIE(parentNode):Light.ajax.getContentAsStringMozilla(parentNode);},getContentAsStringIE:function(parentNode){var contentStr="";for(var i=0;i<parentNode.childNodes.length;i++){var n=parentNode.childNodes[i];if(n.nodeType==4){contentStr+=n.nodeValue;}
else{contentStr+=n.xml;}}
return contentStr;},getContentAsStringMozilla:function(parentNode){var xmlSerializer=new XMLSerializer();var contentStr="";for(var i=0;i<parentNode.childNodes.length;i++){var n=parentNode.childNodes[i];if(n.nodeType==4){contentStr+=n.nodeValue;}
else{contentStr+=xmlSerializer.serializeToString(n);}}
return contentStr;},getXmlHttpObject:function(){var xmlhttp;try{xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");}catch(e){try{xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}catch(E){xmlhttp=false;}}
if(!xmlhttp&&typeof XMLHttpRequest!='undefined'){try{xmlhttp=new XMLHttpRequest();}catch(e){xmlhttp=false;}}
return xmlhttp;}}
function showDealFacets(id){var portlet=Light.getPortletByName('dealPortlet');if(portlet&&portlet.data&&!Light.portal.mobile){var data=portlet.data;data.view="dealFacets.view";var name='dealFacetsPortlet';var facetsPortlet=Light.getPopupPortletByName(name);if(facetsPortlet)facetsPortlet.close();var facet=L$(name);if(facet)document.body.removeChild(facet);var width=Light.getBarWidth()-10;var left=Light.portal.layout.containerLeft-width-5;var top=L$('portalContent').offsetTop;facetsPortlet=new PortletPopupWindow(11,left,width,Light.getMessage('TITLE_SEARCH_FILTERS'),"","",name,"/dealFacetsPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,true,4,2,top);facetsPortlet.refreshData(data);}}
initMap=function(id){if(GBrowserIsCompatible()){var divMap=document.getElementById("map");divMap.style.width=280;divMap.style.height=300;var portlet=Light.getPortletById(id);portlet.parent.repositionPortlets(portlet);map=new GMap2(document.getElementById("map"));map.addControl(new GSmallMapControl());map.addControl(new GMapTypeControl());map.setCenter(new GLatLng(37.4419,-122.1419),13);geocoder=new GClientGeocoder();}}
showAddress=function(address,id){initMap(id);if(geocoder){geocoder.getLatLng(address,function(point){if(!point){alert(address+" not found");}else{map.setCenter(point,13);var marker=new GMarker(point);map.addOverlay(marker);marker.openInfoWindowHtml(address);}});}}
initMaxMap=function(){if(GBrowserIsCompatible()){map=new GMap2(document.getElementById("map"));map.addControl(new GSmallMapControl());map.addControl(new GMapTypeControl());map.setCenter(new GLatLng(37.4419,-122.1419),13);geocoder=new GClientGeocoder();}}
showMaxAddress=function(address){initMaxMap();if(geocoder){geocoder.getLatLng(address,function(point){if(!point){alert(address+" not found");}else{map.setCenter(point,13);var marker=new GMarker(point);map.addOverlay(marker);marker.openInfoWindowHtml(address);}});}}
function Marquee()
{this.ID=document.getElementById(arguments[0]);if(!this.ID)
{this.ID=-1;return;}
this.Direction=this.Width=this.Height=this.DelayTime=this.WaitTime=this.CTL=this.StartID=this.Stop=this.MouseOver=0;this.Step=1;this.Timer=30;this.DirectionArray={"top":0,"up":0,"bottom":1,"down":1,"left":2,"right":3};if(typeof arguments[1]=="number"||typeof arguments[1]=="string")this.Direction=arguments[1];if(typeof arguments[2]=="number")this.Step=arguments[2];if(typeof arguments[3]=="number")this.Width=arguments[3];if(typeof arguments[4]=="number")this.Height=arguments[4];if(typeof arguments[5]=="number")this.Timer=arguments[5];if(typeof arguments[6]=="number")this.DelayTime=arguments[6];if(typeof arguments[7]=="number")this.WaitTime=arguments[7];if(typeof arguments[8]=="number")this.ScrollStep=arguments[8];this.ID.style.overflow=this.ID.style.overflowX=this.ID.style.overflowY="hidden";this.ID.noWrap=true;this.IsNotOpera=(navigator.userAgent.toLowerCase().indexOf("opera")==-1);if(arguments.length>=7)this.Start();}
Marquee.prototype.Start=function()
{if(this.ID==-1)return;if(this.WaitTime<800)this.WaitTime=800;if(this.Timer<20)this.Timer=20;if(this.Width==0)this.Width=parseInt(this.ID.style.width);if(this.Height==0)this.Height=parseInt(this.ID.style.height);if(typeof this.Direction=="string")this.Direction=this.DirectionArray[this.Direction.toString().toLowerCase()];this.HalfWidth=Math.round(this.Width/2);this.HalfHeight=Math.round(this.Height/2);this.BakStep=this.Step;this.ID.style.width=this.Width+"px";this.ID.style.height=this.Height+"px";if(typeof this.ScrollStep!="number")this.ScrollStep=this.Direction>1?this.Width:this.Height;var templateLeft="<table cellspacing='0' cellpadding='0' style='border-collapse:collapse;display:inline;'><tr><td noWrap=true style='white-space: nowrap;word-break:keep-all;'>MSCLASS_TEMP_HTML</td><td noWrap=true style='white-space: nowrap;word-break:keep-all;'>MSCLASS_TEMP_HTML</td></tr></table>";var templateTop="<table cellspacing='0' cellpadding='0' style='border-collapse:collapse;'><tr><td>MSCLASS_TEMP_HTML</td></tr><tr><td>MSCLASS_TEMP_HTML</td></tr></table>";var msobj=this;msobj.tempHTML=msobj.ID.innerHTML;if(msobj.Direction<=1)
{msobj.ID.innerHTML=templateTop.replace(/MSCLASS_TEMP_HTML/g,msobj.ID.innerHTML);}
else
{if(msobj.ScrollStep==0&&msobj.DelayTime==0)
{msobj.ID.innerHTML+=msobj.ID.innerHTML;}
else
{msobj.ID.innerHTML=templateLeft.replace(/MSCLASS_TEMP_HTML/g,msobj.ID.innerHTML);}}
var timer=this.Timer;var delaytime=this.DelayTime;var waittime=this.WaitTime;msobj.StartID=function(){msobj.Scroll()}
msobj.Continue=function()
{if(msobj.MouseOver==1)
{setTimeout(msobj.Continue,delaytime);}
else
{clearInterval(msobj.TimerID);msobj.CTL=msobj.Stop=0;msobj.TimerID=setInterval(msobj.StartID,timer);}}
msobj.Pause=function()
{msobj.Stop=1;clearInterval(msobj.TimerID);setTimeout(msobj.Continue,delaytime);}
msobj.Begin=function()
{msobj.ClientScroll=msobj.Direction>1?msobj.ID.scrollWidth/2:msobj.ID.scrollHeight/2;if((msobj.Direction<=1&&msobj.ClientScroll<=msobj.Height+msobj.Step)||(msobj.Direction>1&&msobj.ClientScroll<=msobj.Width+msobj.Step)){msobj.ID.innerHTML=msobj.tempHTML;delete(msobj.tempHTML);return;}
delete(msobj.tempHTML);msobj.TimerID=setInterval(msobj.StartID,timer);if(msobj.ScrollStep<0)return;msobj.ID.onmousemove=function(event)
{if(msobj.ScrollStep==0&&msobj.Direction>1)
{var event=event||window.event;if(window.event)
{if(msobj.IsNotOpera)
{msobj.EventLeft=event.srcElement.id==msobj.ID.id?event.offsetX-msobj.ID.scrollLeft:event.srcElement.offsetLeft-msobj.ID.scrollLeft+event.offsetX;}
else
{msobj.ScrollStep=null;return;}}
else
{msobj.EventLeft=event.layerX-msobj.ID.scrollLeft;}
msobj.Direction=msobj.EventLeft>msobj.HalfWidth?3:2;msobj.AbsCenter=Math.abs(msobj.HalfWidth-msobj.EventLeft);msobj.Step=Math.round(msobj.AbsCenter*(msobj.BakStep*2)/msobj.HalfWidth);}}
msobj.ID.onmouseover=function()
{if(msobj.ScrollStep==0)return;msobj.MouseOver=1;clearInterval(msobj.TimerID);}
msobj.ID.onmouseout=function()
{if(msobj.ScrollStep==0)
{if(msobj.Step==0)msobj.Step=1;return;}
msobj.MouseOver=0;if(msobj.Stop==0)
{clearInterval(msobj.TimerID);msobj.TimerID=setInterval(msobj.StartID,timer);}}}
setTimeout(msobj.Begin,waittime);}
Marquee.prototype.Scroll=function()
{switch(this.Direction)
{case 0:this.CTL+=this.Step;if(this.CTL>=this.ScrollStep&&this.DelayTime>0)
{this.ID.scrollTop+=this.ScrollStep+this.Step-this.CTL;this.Pause();return;}
else
{if(this.ID.scrollTop>=this.ClientScroll)
{this.ID.scrollTop-=this.ClientScroll;}
this.ID.scrollTop+=this.Step;}
break;case 1:this.CTL+=this.Step;if(this.CTL>=this.ScrollStep&&this.DelayTime>0)
{this.ID.scrollTop-=this.ScrollStep+this.Step-this.CTL;this.Pause();return;}
else
{if(this.ID.scrollTop<=0)
{this.ID.scrollTop+=this.ClientScroll;}
this.ID.scrollTop-=this.Step;}
break;case 2:this.CTL+=this.Step;if(this.CTL>=this.ScrollStep&&this.DelayTime>0)
{this.ID.scrollLeft+=this.ScrollStep+this.Step-this.CTL;this.Pause();return;}
else
{if(this.ID.scrollLeft>=this.ClientScroll)
{this.ID.scrollLeft-=this.ClientScroll;}
this.ID.scrollLeft+=this.Step;}
break;case 3:this.CTL+=this.Step;if(this.CTL>=this.ScrollStep&&this.DelayTime>0)
{this.ID.scrollLeft-=this.ScrollStep+this.Step-this.CTL;this.Pause();return;}
else
{if(this.ID.scrollLeft<=0)
{this.ID.scrollLeft+=this.ClientScroll;}
this.ID.scrollLeft-=this.Step;}
break;}}
Light.startScroll=function(portletId,id,width){var portlet=Light.getPortletById(portletId);if(portlet==null)return;if(portlet.viewScroller==null){portlet.viewScroller=new Marquee(id,2,1,width,150,20,0,0,50);}
portlet.viewScroller.ScrollStep=50;}
Light.pauseScroll=function(portletId){var portlet=Light.getPortletById(portletId);if(portlet==null)return;if(portlet.viewScroller!=null){portlet.viewScroller.ScrollStep=0;}}
Light.turnScroll=function(portletId){var portlet=Light.getPortletById(portletId);if(portlet==null)return;if(portlet.viewScroller!=null){if(portlet.viewScroller.Direction==2)
portlet.viewScroller.Direction=3;else
portlet.viewScroller.Direction=2;}}
Light.turnScrollStart=function(e,portletId){var portlet=Light.getPortletById(portletId);if(portlet==null)return;portlet.scrollerX=getMousePositionX(e);}
Light.turnScrollEnd=function(e,portletId){var portlet=Light.getPortletById(portletId);if(portlet==null)return;x=getMousePositionX(e);if(x<portlet.scrollerX){portlet.viewScroller.Direction=2;}else{portlet.viewScroller.Direction=3;}}
LightPortal.prototype.startup=function(){this.initialize();this.clear();this.open();this.afterStartup();}
LightPortal.prototype.initialize=function(){this.initEvents();this.initPosition();}
LightPortal.prototype.initEvents=function(){var that=this;document.body.ondragstart=function(){return false;};document.body.onmousemove=function(e){if(that.moveTimer==-1)return;if(document.all)e=event;if(that.dragDropPortlet!=null){that.dragDropPortlet.move(e);}
return false;};document.body.onmouseup=function(e){that.moveTimer=-1;if(document.all)e=event;if(that.dragDropPortlet!=null){that.dragDropPortlet.moveEnd(e);that.dragDropPortlet=null;document.body.onselectstart=null;document.body.ondragstart=null;}
return false;};document.onmousedown=function(){Light.setSessionTimer();};document.onkeydown=function(){Light.setSessionTimer();};window.onresize=function(){that.resize();};window.onorientationchange=function(){that.resize();};}
LightPortal.prototype.initPosition=function(){if(this.originalLeft!=null){this.layout.containerLeft=this.originalLeft;this.originalLeft=null;}}
LightPortal.prototype.clear=function(){}
LightPortal.prototype.open=function(){if(Light.needReload){this.load();}else if(Light.needRender){this.render(L$('portalJSON').title,L$('tabsJSON').title);}else{this.bind(L$('portalJSON').title,L$('tabsJSON').title);}}
LightPortal.prototype.initUser=function(userId){if(userId){Light.userId=userId;Light.setCookie(Light._LOGINED_USER_ID,Light.userId);}else{Light.deleteCookie(Light._LOGINED_USER_ID);Light.deleteCookie(Light._CURRENT_TAB);}}
LightPortal.prototype.afterStartup=function(){}
LightPortal.prototype.load=function(){var that=this;var userId=Light.getRememberedUserId();var password=Light.getCookie(Light._REMEMBERED_USER_PASSWORD);log("Start Portal:"+userId);var params="check=1";if(userId&&password&&!Light.getCookie(Light._IS_SIGN_OUT)){params="userId="+escape(encodeURIComponent(userId))
+"&password="+escape(encodeURIComponent(password));}
var opt={method:'post',parameters:params,onSuccess:function(t){that.loadHandler(t);},onFailure:function(t){Light.alert('Error '+t.status+' -- '+t.statusText);}}
Light.ajax.sendRequest(Light._LOAD_PORTAL,opt);}
LightPortal.prototype.loadHandler=function(t){log("load Portal:"+t.responseText);var params=JSON.parse(t.responseText);this.initUser(params[0].userId);this.render(params[0],params[1],true);Light.needReload=false;}
LightPortal.prototype.render=function(portal,tabs,flag){this.renderPortal(portal,flag);this.renderPortalTab(tabs,flag);Light.refreshTextColor();this.show();}
LightPortal.prototype.renderPortal=function(myPortal,flag){log("render Portal:"+myPortal);this.data=(flag)?myPortal:JSON.parse(myPortal);this.initUser(this.data.userId);Light.setTextFont();Light.playMusic();this.container=this.createPortalContainer();this.header=Light.service.getPortalHeader(this);this.menu=Light.service.getPortalMenu(this);this.body=this.createPortalBody();this.content=this.createPortalContent();this.footer=Light.service.getPortalFooter(this);this.body.appendChild(this.content);this.container.appendChild(this.header);this.container.appendChild(this.menu);this.container.appendChild(this.body);this.container.appendChild(this.footer);this.hide();document.body.appendChild(this.container);this.turnedOn=true;this.startTab=0;log("render Portal:end");}
LightPortal.prototype.renderPortalTab=function(myPortalTabs,flag){log("render Portal Tab:"+myPortalTabs);var portalTabs=(flag)?myPortalTabs:JSON.parse(myPortalTabs);var defaultTab=null;var defaultTabs=null;if(Light.getCookie(Light._CURRENT_TAB)){defaultTab=Light.getCookie(Light._CURRENT_TAB);defaultTabs=defaultTab.split("-");if(defaultTabs.length>0&&defaultTabs[0]<portalTabs.length){defaultTab=parseInt(defaultTabs[0]);}}
if(defaultTab&&defaultTab>=portalTabs.length)defaultTab=0;if(defaultTab&&defaultTab>=this.data.maxShowTabs)this.startTab=defaultTab+1-this.data.maxShowTabs;if(portalTabs.length>this.data.maxShowTabs)this.addLeftButton();for(var i=0,len=portalTabs.length;i<len;i++){var tab=new LightPortalTab(portalTabs[i]);if(i>=this.startTab&&i<this.data.maxShowTabs+this.startTab)
tab.open();if(tab.defaulted&&defaultTab==null)
defaultTab=i;}
if(portalTabs.length>this.data.maxShowTabs)this.addRightButton();this.addTabMenu();log("render Portal defaut:"+defaultTabs);if(defaultTabs&&defaultTabs.length>1){var defaultSubTabs="";for(var i=1;i<defaultTabs.length;i++){if(i==1)
defaultSubTabs=defaultTabs[i];else
defaultSubTabs+="-"+defaultTabs[i];}
this.tabs[defaultTab].defaultTab=defaultSubTabs;}
if(!defaultTab||(defaultTab&&!this.tabs[defaultTab]))defaultTab=0;var url=document.location.href;var i=url.indexOf("#");var location=(i>=0)?url.substr(i+1):"";if(location.indexOf(Light._PAGE_PREFIX)>=0){Light.historyListener(location);}else{this.tabs[defaultTab].focus();}
if(portalTabs.length<=1&&(!Light.hasPermission(Light.permission.PORTAL_TAB_ADD)||!L$('tabMenuAddTab')))
this.tabs[0].hide();log("render Portal Tab: done");}
LightPortal.prototype.bind=function(portal,tabs,flag){this.bindPortal(portal,flag);this.bindPortalTab(tabs,flag);Light.refreshTextColor();}
LightPortal.prototype.bindPortal=function(myPortal,flag){log("bind Portal:"+myPortal);this.data=(flag)?myPortal:JSON.parse(myPortal);this.initUser(this.data.userId);Light.setTextFont();Light.playMusic();this.container=L$('portalContainer');this.header=L$('portalHeader');this.menu=L$('portalMenu');this.body=L$('portalBody');this.content=L$('portalContent');this.footer=L$('portalFooter');this.turnedOn=true;this.startTab=0;log("bind Portal:end");}
LightPortal.prototype.bindPortalTab=function(myPortalTabs,flag){log("bind Portal Tab:"+myPortalTabs);var portalTabs=(flag)?myPortalTabs:JSON.parse(myPortalTabs);var defaultTab=null;var defaultTabs=null;if(Light.getCookie(Light._CURRENT_TAB)){defaultTab=Light.getCookie(Light._CURRENT_TAB);defaultTabs=defaultTab.split("-");if(defaultTabs.length>0&&defaultTabs[0]<portalTabs.length){defaultTab=parseInt(defaultTabs[0]);}}
if(defaultTab&&defaultTab>=portalTabs.length)defaultTab=0;if(defaultTab&&defaultTab>=this.data.maxShowTabs)this.startTab=defaultTab+1-this.data.maxShowTabs;if(portalTabs.length>this.data.maxShowTabs)this.addLeftButton();for(var i=0;i<portalTabs.length;i++){var tab=new LightPortalTab(portalTabs[i]);if(i>=this.startTab&&i<this.data.maxShowTabs+this.startTab)
tab.bind();if(tab.defaulted&&defaultTab==null)
defaultTab=i;}
if(portalTabs.length>this.data.maxShowTabs)this.addRightButton();this.addTabMenu();log("bind Portal defaut:"+defaultTabs);if(defaultTabs&&defaultTabs.length>1){var defaultSubTabs="";for(var i=1;i<defaultTabs.length;i++){if(i==1)
defaultSubTabs=defaultTabs[i];else
defaultSubTabs+="-"+defaultTabs[i];}
this.tabs[defaultTab].defaultTab=defaultSubTabs;}
if(!defaultTab||(defaultTab&&!this.tabs[defaultTab]))defaultTab=0;this.tabs[defaultTab].bindFocus();if(portalTabs.length<=1&&(!Light.hasPermission(Light.permission.PORTAL_TAB_ADD)||!L$('tabMenuAddTab')))
this.tabs[0].hide();log("render Portal Tab: done");}
LightPortal.prototype.addLeftButton=function(){var container=Light.newElement({element:'span',id:'leftTabButton',className:'portal-tab-button',style:{background:'no-repeat',marginTop:'8px',marginLeft:'0',marginRight:'0',padding:'0'},onclick:function(){Light.focusLeftTab();}});var button=Light.newElement({element:'img',src:Light.getThemePath()+'/images/left.png',title:Light.getMessage('BUTTON_PREVIOUS'),align:'middle',style:{height:16,width:16}});container.appendChild(button);if(!this.startTab){container.className="";container.onclick="";container.style.MozOpacity=0.3;if(container.filters)container.filters.alpha.opacity=30;}
L$('tabs').insertBefore(container,L$(Light._TAB_LIST));}
LightPortal.prototype.addRightButton=function(){var container=Light.newElement({element:'span',id:'rightTabButton',style:{background:'no-repeat',marginTop:'5px',marginLeft:'0',marginRight:'0',padding:'0'},onclick:function(){Light.focusRightTab();}});var button=Light.newElement({element:'img',src:Light.getThemePath()+'/images/right.png',title:Light.getMessage('BUTTON_NEXT'),className:'portal-tab-button',align:'middle',style:{height:16,width:16}});container.appendChild(button);L$('tabs').appendChild(container);}
LightPortal.prototype.addTabMenu=function(serverId,tabParentId){if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD)){var button=Light.newElement({element:'input',type:'button',title:Light.getMessage('MENU_ADD_SUBTAB'),className:'icons_addTab',style:{width:16,display:'inline',borderStyle:'none',borderWidth:0}});var newTab=document.createElement('li');newTab.appendChild(button);if(serverId==null){newTab.id="tabMenuAddTab";newTab.setAttribute("id","tabMenuAddTab");newTab.setAttribute("tabId","");newTab.onclick=function(){addAutoTab(0);}
L$(Light._TAB_LIST).appendChild(newTab);}else{newTab.id="tabMenuAddTab"+serverId;newTab.setAttribute("tabId",serverId);newTab.setAttribute("parentId",tabParentId);newTab.onclick=function(){addAutoTab(serverId);}
L$('tabList'+serverId).appendChild(newTab);}}}
LightPortal.prototype.show=function(){this.container.style.visibility="visible";}
LightPortal.prototype.hide=function(){this.container.style.visibility='hidden';}
LightPortal.prototype.createPortalContainer=function(){log("render Portal:create portal container");var container=Light.newElement({element:'div',id:'portalContainer',className:'portal-container',style:{position:'absolute',zIndex:++Light.maxZIndex,fontSize:12+parseInt(this.data.fontSize),width:this.layout.containerWidth,left:this.layout.containerLeft,height:this.layout.maxHeight}});if(this.data.bgImage.length>0){if(this.data.bgImage!="no"){var backgroundImage=this.data.bgImage;if(this.data.bgImage.indexOf("http")<0)
backgroundImage=Light.getContextPath()+this.data.bgImage;if(this.data.bgRepeat==1)
document.body.style.background="url('"+backgroundImage+"') no-repeat "+this.data.bgPosition;else if(this.data.bgRepeat==2)
document.body.style.background="url('"+backgroundImage+"') repeat-x right top";else if(this.data.bgRepeat==3)
document.body.style.background="url('"+backgroundImage+"') repeat-y left top";else
document.body.style.background="url('"+backgroundImage+"')";}else{document.body.style.backgroundColor="#ffffff";}}else{document.body.style.backgroundColor="#ffffff";}
setPosition(container,this.layout.containerLeft,this.layout.containerTop);return container;}
LightPortal.prototype.createPortalBody=function(){log("render Portal:create portal body");return Light.newElement({element:'div',id:'portalBody',className:'portal-body'});}
LightPortal.prototype.createPortalContent=function(){log("render Portal:create portal content");return Light.newElement({element:'div',id:'portalContent',className:'portal-content',innerHTML:"<div id='tabs' class='portal-tabs'><ul id='tabList'></ul></div><div id='tabPanels' class='portal-tab-panels' ></div>",style:{position:'absolute',zIndex:++Light.maxZIndex,width:this.layout.maxWidth}});}
LightPortal.prototype.refreshPortalHeader=function(){this.container.removeChild(this.header);this.header=Light.service.getPortalHeader(this);this.container.insertBefore(this.header,this.menu);Light.refreshTextColor();}
LightPortal.prototype.refreshPortalMenu=function(){this.container.removeChild(this.menu);this.menu=Light.service.getPortalMenu(this);this.container.insertBefore(this.menu,this.body);Light.refreshTextFontSize();}
LightPortal.prototype.resize=function(){if(!this.turnedOn)return;this.layout.containerLeft=Light.getContainerLeft();setPosition(this.container,this.layout.containerLeft,this.layout.containerTop);var currentTab=Light.getCurrentTab();this.refreshPortalMenu();currentTab.resize();}
LightPortal.prototype.collapseAll=function(){Light.getCurrentTab().collapseAll();}
LightPortal.prototype.expandAll=function(){Light.getCurrentTab().expandAll();}
LightPortal.prototype.shutdown=function(){var len=this.allPortlets.length;for(var i=0;i<len;i++){var portlet=this.allPortlets[i];if(portlet.popup&&portlet.location&&portlet.location==4)
portlet.close();}
this.container.removeChild(this.header);this.container.removeChild(this.menu);this.container.removeChild(this.body);this.container.removeChild(this.footer);document.body.removeChild(this.container);document.body.onselectstart=null;document.body.ondragstart=null;document.body.onmousemove=null;document.body.onmouseup=null;document.body.style.backgroundColor="";this.tabs=[];this.allTabs=[];this.allPortlets=[];this.turnedOn=false;Light.offMode();}
LightPortal.prototype.editTitle=function(title){title.className='portal-header-title-edit';title.innerHTML="<input type='text' id='portalTitle' class='portal-header-title-edit' value=\""+this.data.title+"\" onchange=\"javascript:Light.portal.saveTitle();\" onblur=\"javascript:Light.portal.saveTitle();\"/>";L$('portalTitle').focus();}
LightPortal.prototype.viewTitle=function(title){title.className='portal-header-title-view';title.style.backgroundColor='';title.innerHTML=this.data.title;}
LightPortal.prototype.saveTitle=function(title){var title=L$('portalTitle').value;this.data.title=title;this.refreshPortalHeader();var params="title="+encodeURIComponent(title);Light.ajax.sendRequest(Light._CHANGE_TITLE,{parameters:params});}
function LightPortalFooter(){}
LightPortalFooter.prototype.render=function(portal){}
LightPortalFooterImpl.prototype=new LightPortalFooter;LightPortalFooterImpl.prototype.constructor=LightPortalFooter;function LightPortalFooterImpl(){LightPortalFooter.call(this);}
LightPortalFooterImpl.prototype.render=function(portal){log("render Portal: create portal footer");var footer=Light.newElement({element:'div',id:'portalFooter',className:'portal-footer',innerHTML:Light.getViewTemplate("footer.view"),style:{width:portal.layout.containerWidth,zIndex:1}});return footer;}
Light.showAbout=function(){Light.addFunctionHistory("Light.showAbout");if(Light.showCurrentPopupWindow("aboutPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_ABOUT').title,"icons about","aboutPortlet","/aboutPortlet.lp");}
Light.showFAQ=function(){Light.addFunctionHistory("Light.showFAQ");if(Light.showCurrentPopupWindow("faqPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_FAQ').title,"icons faq","faqPortlet","/faqPortlet.lp");}
Light.showTerms=function(){Light.addFunctionHistory("Light.showTerms");if(Light.showCurrentPopupWindow("termsPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_TERMS').title,"icons terms","termsPortlet","/termsPortlet.lp");}
Light.showPrivacy=function(){Light.addFunctionHistory("Light.showPrivacy");if(Light.showCurrentPopupWindow("privacyPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_PRIVACY').title,"icons private","privacyPortlet","/privacyPortlet.lp");}
Light.showContactUs=function(){Light.addFunctionHistory("Light.showContactUs");if(Light.showCurrentPopupWindow("contactPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_CONTACT_US').title,"icons message","contactPortlet","/contactPortlet.lp");}
Light.showCommunityBlog=function(){Light.addFunctionHistory("Light.showCommunityBlog");if(Light.showCurrentPopupWindow("blogOrgPortlet"))return;Light.showPopupPortlet(150,600,L$('MENU_BLOG').title,"icons blog","blogOrgPortlet","/blogOrgPortlet.lp");}
Light.showFeedback=function(){Light.addFunctionHistory("Light.showFeedback");if(Light.showCurrentPopupWindow("feedbackPortlet"))return;Light.showPopupPortlet(150,600,L$('TITLE_FEEDBACK').title,"icons todolist","feedbackPortlet","/feedbackPortlet.lp",Light._EDIT_MODE);}
function LightPortalHeader(){}
LightPortalHeader.prototype.render=function(){}
LightPortalHeaderImpl.prototype=new LightPortalHeader;LightPortalHeaderImpl.prototype.constructor=LightPortalHeader;function LightPortalHeaderImpl(){LightPortalHeader.call(this);}
LightPortalHeaderImpl.prototype.render=function(portal){log("render Portal: create portal header");var header=document.createElement('div');header.id="portalHeader";header.className="portal-header";if(portal.data.headerImage){if(portal.data.headerImage!="no"){var headerImage=portal.data.headerImage;if(headerImage.indexOf("http")<0)
headerImage=Light.getContextPath()+headerImage;if(portal.data.headerRepeat==1)
header.style.background="url('"+headerImage+"') no-repeat "+portal.data.headerPosition;else if(portal.data.headerRepeat==2)
header.style.background="url('"+headerImage+"') repeat-x right top";else if(portal.data.headerRepeat==3)
header.style.background="url('"+headerImage+"') repeat-y left top";else{header.style.background="url('"+headerImage+"')";header.style.backgroundRepeat="repeat-x";}}else{header.style.backgroundColor="";}}else{header.style.backgroundColor="";}
header.innerHTML=Light.getViewTemplate("header.view");header.style.zIndex=++Light.maxZIndex;return header;}
function LightPortalMenu(){}
LightPortalMenu.prototype.render=function(portal){var menu=Light.newElement({element:'div',id:'portalMenu',className:'portal-header-menu'});this.renderMenuItems(menu,portal);menu.style.zIndex=++Light.maxZIndex;return menu;}
LightPortalMenu.prototype.renderSearchBar=function(menu,portal){if(portal.data.showSearchBar){menu.appendChild(Light.newElement({element:'span',className:'portal-header-search',innerHTML:Light.getViewTemplate("searchBar.view",null)}));}}
LightPortalMenu.prototype.renderMenuItems=function(menu,portal){var menuItems=Light.newElement({element:'span',className:'portal-header-menu-item'});this.renderMenuItemUser(menuItems,portal);this.renderMenuItemOptions(menuItems,portal);this.renderMenuItemContent(menuItems,portal);this.renderMenuItemSignUp(menuItems,portal);this.renderMenuItemSignIn(menuItems,portal);this.renderMenuItemClose(menuItems,portal);this.renderMenuItemLanguage(menuItems,portal);this.renderSearchBar(menuItems,portal);menu.appendChild(menuItems);}
LightPortalMenu.prototype.renderMenuItemUser=function(menuItems,portal){if(Light.isLogin()){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:portal.data.title}));return true;}
return false;}
LightPortalMenu.prototype.renderMenuItemOptions=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_OPTIONS)){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showChangeOptions();},innerHTML:Light.getMessage('MENU_OPTIONS')}));return true;}
return false;}
LightPortalMenu.prototype.renderMenuItemContent=function(menuItems,portal){tab=Light.currentTab;if(Light.hasPermission(Light.permission.PORTAL_CONTENT_ADD)&&(tab==null||tab.allowAddContent)){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showAddContent();},innerHTML:Light.getMessage('MENU_ADD_CONTENT')}));return true;}
return false;}
LightPortalMenu.prototype.renderMenuItemSignUp=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_SIGN_UP)&&!Light.isLogin()){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showSignUp();},innerHTML:Light.getMessage('MENU_SIGN_UP')}));menuItems.appendChild(Light.newElement({element:'span',className:'portal-header-menu-item-separater'}));return true;}
return false;}
LightPortalMenu.prototype.renderMenuItemSignIn=function(menuItems,portal){if(Light.isLogin()){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showMyAccount();},innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.logout();},innerHTML:Light.getMessage('MENU_SIGN_OUT')}));}else if(Light.hasPermission(Light.permission.PORTAL_SIGN_IN)){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showLogin(false);},innerHTML:Light.getMessage('MENU_SIGN_IN')}));menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("facebookConnect.view",null)}));menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("twitterLogin.view",null)}));}}
LightPortalMenu.prototype.renderMenuItemNoMenu=function(menuItems,portal){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));}
LightPortalMenu.prototype.renderMenuItemClose=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_TURN_OFF)||L$('WIDGET_MODE')!=null){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.portal.shutdown();},innerHTML:Light.getMessage('MENU_TURN_OFF')}));}}
LightPortalMenu.prototype.renderMenuItemCollapse=function(menuItems,portal){if(Light.isLogin()){menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.portal.collapseAll();},innerHTML:"<input type='button' title='"+Light.getMessage('MENU_COLLAPSE_ALL')+"' class='icons_collapseAll' value=''></input>"}));menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.portal.expandAll();},innerHTML:"<input type='button' title='"+Light.getMessage('MENU_EXPAND_ALL')+"' class='icons_expandAll' value=''></input>"}));menuItems.appendChild(Light.newElement({element:'span',className:'portal-header-menu-item-separater'}));}}
LightPortalMenu.prototype.renderMenuItemLanguage=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_CHANGE_LANGUAGE)){menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.showChangeLanguage();},innerHTML:"<input type='button' title='"+Light.getMessage('TITLE_LANGUAGE')+"' class='icons_world' value=''></input>"}));}}
Light.showLogin=function(flag){if(flag==null||flag)Light.addFunctionHistory("Light.showLogin");if(Light.showCurrentPopupWindow("login"))return;var portlet=new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","loginPortlet","/loginPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,true,5,2);portlet.refresh();}
Light.showLocalLogin=function(flag){if(flag==null||flag)Light.addFunctionHistory("Light.showLocalLogin");if(Light.showCurrentPopupWindow("localLogin"))return;var portlet=new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","localLogin","/localLogin.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);portlet.refresh();Light.portal.latestAction.portlet=portlet;}
Light.showAllLogin=function(flag){if(flag==null||flag)Light.addFunctionHistory("Light.showAllLogin");if(Light.showCurrentPopupWindow("allLogin"))return;var portlet=new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","allLogin","/loginPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);portlet.refresh();window.scrollTo(0,0);Light.portal.latestAction.portlet=portlet;}
Light.showMyAccount=function(){if(Light.showCurrentPopupWindow("accountPortlet"))return;var portlet=new PortletPopupWindow(11,50,1000,Light.getMessage('MENU_MY_ACCOUNT'),"icons user","","accountPortlet","/accountPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,0,2);portlet.refresh();}
Light.showChangeLanguage=function(){Light.addFunctionHistory("Light.showChangeLanguage");if(Light.showCurrentPopupWindow("changeLanguagePortlet"))return;var portlet=new PortletPopupWindow(11,200,750,Light.getMessage('TITLE_LANGUAGE'),"icons language","","changeLanguagePortlet","/changeLanguagePortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);portlet.refresh();}
Light.showChangeOptions=function(){if(Light.showCurrentPopupWindow("optionsPortlet"))return;var portlet=new PortletPopupWindow(11,50,1000,Light.getMessage('MENU_OPTIONS'),"icons options","","optionsPortlet","/optionsPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,0,2);portlet.refresh();}
Light.showAddContent=function(){if(Light.showCurrentPopupWindow("contentPortlet"))return;var portlet=new PortletPopupWindow(11,0,400,Light.getMessage('MENU_ADD_CONTENT'),"icons application_add","","contentPortlet","/contentPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,1,2);portlet.refresh();}
Light.showCurrentPopupWindow=function(name){var tab=Light.getCurrentTab();var portlet=Light.getPopupPortletByName(name);if(portlet){if(portlet.parent.serverId!=tab.serverId){portlet.close();return false;}else{if(portlet.popup){portlet.show();portlet.parent.hideOtherPortlets(portlet);}
Light.portal.allPortlets[i]=null;window.scrollTo(0,0);Light.portal.allPortlets.push(portlet);return true;}}
return false;}
Light.globalSearch=function(keyword,type){Light.addFunctionHistory('Light.globalSearch');if(Light.showCurrentPopupWindow("globalSearchPortlet"))return;var portlet=new PortletPopupWindow(11,0,400,Light.getMessage('MENU_SEARCH'),"icons search","","globalSearchPortlet","/globalSearchPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','','',false,false,true,5,2);var param='action=find';if(type)param+=';type='+type;if(keyword)param+=';keyword='+keyword;Light.executeRender(portlet.id,"view","maximized",param,null,true,false);}
LightPortalMenuImpl.prototype=new LightPortalMenu;LightPortalMenuImpl.prototype.constructor=LightPortalMenuImpl;function LightPortalMenuImpl(){LightPortalMenu.call(this);}
LightPortalMenu.prototype.render=function(portal){var menu=Light.newElement({element:'div',id:'portalMenu',className:'portal-header-menu'});this.renderMenuItems(menu,portal);menu.style.zIndex=++Light.maxZIndex;menu.style.marginRight=0;return menu;}
LightPortalMenu.prototype.renderMenuItemSignUp=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_SIGN_UP)&&!Light.isLogin()){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showSignUp();},innerHTML:Light.getMessage('TITLE_SIGN_UP')}));return true;}
return false;}
LightPortalMenuImpl.prototype.renderMenuItemSignIn=function(menuItems,portal){if(Light.isLogin()){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showMyAccount();},innerHTML:"<img src='"+Light.getContextPath()+"/light/images/myaccount.gif"+"' width='16' height='16' title='"+Light.getMessage('MENU_MY_ACCOUNT')+"' />"}));menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.logout();},innerHTML:"<img src='"+Light.getContextPath()+"/light/images/signout.gif"+"' width='16' height='16' title='"+Light.getMessage('MENU_SIGN_OUT')+"' />"}));}else if(Light.hasPermission(Light.permission.PORTAL_SIGN_IN)){menuItems.appendChild(Light.newElement({element:'a',style:{padding:0},onclick:function(){Light.showLogin(false);},innerHTML:Light.getMessage('MENU_SIGN_IN')}));menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("facebookConnect.view",null)}));menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("twitterLogin.view",null)}));}}
LightPortalMenu.prototype.renderMenuItemOptions=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_OPTIONS)){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showChangeOptions();},innerHTML:"<img src='"+Light.getContextPath()+"/light/images/options.png"+"' width='16' height='16' title='"+Light.getMessage('MENU_OPTIONS')+"' />"}));return true;}
return false;}
LightPortalMenu.prototype.renderMenuItemContent=function(menuItems,portal){return false;}
LightPortalMenu.prototype.renderMenuItemNoMenu=function(menuItems,portal){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));}
LightPortalMenu.prototype.renderMenuItemLanguage=function(menuItems,portal){if(Light.hasPermission(Light.permission.PORTAL_CHANGE_LANGUAGE)){menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showChangeLanguage();},innerHTML:"<img src='"+Light.getContextPath()+"/light/images/world.png"+"' width='16' height='16' title='"+Light.getMessage('TITLE_LANGUAGE')+"' />"}));}}
LightPortalMenu.prototype.renderMenuItemClose=function(menuItems,portal){}
Light.getScreenWidth=function(){var screenWidth=document.documentElement.scrollWidth-5;return screenWidth;}
function LightPortal(){this.layout={containerLeft:2,containerTop:0,containerWidth:Light.getScreenWidth(),bodyLeft:0,bodyTop:80,maxLeft:0,maxTop:40,maxWidth:Light.getScreenWidth(),maxHeight:getWindowHeight(),minHeight:40,scrollbarWidth:0,barWidth:0,footerMarginTop:100}
this.tabs=[];this.allTabs=[];this.allPortlets=[];this.latestAction=Light.getLatestActionObject();this.highLightBar=Light.getHighLightBar();this.progressBar=Light.getProgressBar();this.moveTimer=-1;this.mobile=true;}
LightPortal.prototype.resize=function(){if(!this.turnedOn)return;this.layout.containerWidth=Light.getScreenWidth();this.layout.maxWidth=this.layout.containerWidth;this.layout.maxHeight=getWindowHeight();this.container.style.width=this.layout.containerWidth;this.container.style.height=this.layout.maxHeight;this.content.style.width=this.layout.maxWidth;var currentTab=Light.getCurrentTab();this.refreshPortalMenu();currentTab.resize();}
LightPortal.prototype.addTabMenu=function(serverId,tabParentId){}
LightPortal.prototype.addLeftButton=function(){}
LightPortal.prototype.addRightButton=function(){if(!L$('rightMoreTabButton')){var container=Light.newElement({element:'span',id:'rightMoreTabButton',className:'portal-tab-handle',style:{marginLeft:'10'},innerHTML:Light.getViewTemplate('more.menu'),onclick:function(){showMoreTab();}});L$('tabs').appendChild(container);}}
showMoreTab=function(){var data={tabs:Light.portal.tabs};var tab=L$('rightMoreTabButton');var e={pageX:getX(tab)-5,pageY:getY(tab)+20};var width=150;if(e.pageX+width>Light.getScreenWidth())
e.pageX=Light.getScreenWidth()-width;createTopPopupDiv('viewMoreTab','viewTab.jst',width,data,e,null,true);L$('viewMoreTab').style.padding=0;setTimeout((function(){addEventHandler(document,"click",hideMoreTab)}),1000);setTimeout((function(){hideMoreTab();}),8000);}
hideMoreTab=function(){hideTopPopupDiv('viewMoreTab');removeEvent(document,"click",hideMoreTab);}
LightPortalTab=function(data){this.id=Light._TAB_PREFIX+data.id;this.serverId=data.id;this.label=data.label;this.client=data.client;this.tabURL=data.URL;this.isCloseable=data.isCloseable;this.isEditable=data.isEditable;this.isMoveable=data.isMoveable
this.allowAddContent=data.allowAddContent;this.defaulted=data.defaulted;this.between=data.between;this.widths=data.widths;this.fitScreen=data.fitScreen;this.windowSkin=data.windowSkin;this.privacy=data.privacy;this.parentId=data.parentId;var parentTab=(this.parentId)?Light.getTabById(this.parentId):null;if(parentTab){this.parent=parentTab;this.tabList=Light._TAB_LIST+this.parent.serverId;this.tabPanels=Light._TAB_PANELS+this.parent.serverId;}else{this.parent=Light.portal;this.tabList=Light._TAB_LIST;this.tabPanels=Light._TAB_PANELS;}
this.index=this.parent.tabs.length;this.parent.tabs[this.index]=this;Light.portal.allTabs.push(this);this.portlets=[];this.tabs=[];this.adjustWidth();delete data;log("rendered portal tab "+this.label);}
LightPortalTab.prototype.open=function(next){if(!this.container){var that=this;this.title=Light.newElement({element:'div',id:Light._TAB_TITLE_PREFIX+this.serverId,className:"portal-tab-handle",innerHTML:this.label,onclick:function(e){showProgressBar(e);that.focus();hideProgressBar();},onmouseover:function(){that.hover();},onmouseout:function(){that.hoverout();}});this.header=Light.newElement({element:'span',id:Light._TAB_HEADER_PREFIX+this.serverId});this.header.appendChild(this.title);this.container=Light.newElement({element:'li',id:this.id});this.container.appendChild(this.header);if(next){L$(this.tabList).insertBefore(this.container,next.container);}else{var lastOne=(this.parentId)?L$('tabMenuAddTab'+this.parent.serverId):L$('tabMenuAddTab');if(lastOne)
L$(this.tabList).insertBefore(this.container,lastOne);else
L$(this.tabList).appendChild(this.container);}}
if(!this.panel){this.panel=Light.newElement({element:'div',id:Light._PANEL_PREFIX+this.serverId});if(next){L$(this.tabPanels).insertBefore(this.panel,next.panel);}else{L$(this.tabPanels).appendChild(this.panel);}}
this.opened=true;log("opened portal tab "+this.label);}
LightPortalTab.prototype.bind=function(){var that=this;this.title=L$(Light._TAB_TITLE_PREFIX+this.serverId);if(this.title){this.title.onclick=function(e){showProgressBar(e);that.focus();hideProgressBar();}
this.title.onmouseover=function(){that.hover();}
this.title.onmouseout=function(){that.hoverout();}}
this.header=L$(Light._TAB_HEADER_PREFIX+this.serverId);this.container=L$(this.id);this.panel=L$(Light._PANEL_PREFIX+this.serverId);this.opened=true;if(this.parentId){this.parent.popupMenu=null;this.parent.popupMenuLoaded=false;}
log("bind portal tab "+this.label);}
LightPortalTab.prototype.insert=function(){this.open();this.focus();}
LightPortalTab.prototype.hide=function(){L$(this.tabList).style.visibility="hidden";}
LightPortalTab.prototype.hover=function(){this.hoverAction=true;var that=this;setTimeout((function(){that.showSubTab()}),500);}
LightPortalTab.prototype.hoverout=function(){this.hoverAction=false;Light.hoverTab=null;}
LightPortalTab.prototype.showSubTab=function(){if(this.hoverAction){Light.hoverTab=this;if(this.popupMenu){this.popupSubTab(this.popupMenu);}else if(!this.popupMenuLoaded){var params="tabId="+this.serverId;Light.ajax.sendRequest(Light._SHOW_SUB_TAB,{parameters:params,onSuccess:Light.hoverTab.showSubTabHandler});}}}
LightPortalTab.prototype.showSubTabHandler=function(t){if(Light.hoverTab){Light.hoverTab.popupMenuLoaded=true;if(t.responseText){Light.hoverTab.popupMenu=t.responseText;Light.hoverTab.popupSubTab(Light.hoverTab.popupMenu);}}}
LightPortalTab.prototype.popupSubTab=function(text){if(Light.hoverTab&&Light.hoverTab.hoverAction&&text){var tabs=JSON.parse(text);var data={title:Light.getMessage('CLOSE'),tabs:tabs,popupName:'viewSubTab'};var tab=L$(Light._TAB_HEADER_PREFIX+tabs[0].parentId);var e={pageX:getX(tab)-5,pageY:getY(tab)+20};createTopPopupDiv('viewSubTab','viewTab.jst',100,data,e,null,true);L$('viewSubTab').style.padding=0;document.body.onclick=function(e){hideTopPopupDiv('viewSubTab');};}else
hideTopPopupDiv('viewSubTab');Light.hoverTab=null;}
LightPortalTab.prototype.getFocusId=function(){var parentTab=(this.parentId>0)?Light.getTabById(this.parentId):null;return(parentTab)?parentTab.getFocusId()+"-"+this.index:this.index;}
LightPortalTab.prototype.bindFocus=function(){var id='tab'+this.serverId+'PortletsJSON';log("bind Portal Tab Focus: "+id);if(L$(id)){this.loaded=true;this.focusRender();this.bindPortlets(JSON.parse(L$(id).title));}else
this.focus();}
LightPortalTab.prototype.bindPortlets=function(portlets){if(portlets.length>0){var len=portlets.length;log("bind tab portlets:"+len);Light.currentTab=this;for(var i=0;i<len;i++){var noRender=(L$(Light._PR_PREFIX+portlets[i].serverId))?true:false;log("bind portlet:"+portlets[i]+","+noRender);var portlet=new PortletWindow(portlets[i],false,noRender);if(!noRender)portlet.refresh();}}
this.rePositionAll();}
LightPortalTab.prototype.focusRender=function(flag){log("start focus render portal tab "+this.label);Light.currentTab=this;Light.setCookie(Light._CURRENT_TAB,this.getFocusId());if(flag==null||flag){Light.addHistory();}
if(!this.focused){this.container.className="current";this.header.className="current";this.container.style.display="block";this.panel.style.display="block";this.addButtons();}else if(this.isEditable&&!this.editTitle){this.editTitle=true;this.header.innerHTML="<input type='text' id='portalTabTitle' size='12' value=\""+this.label+"\" onchange=\"javascript:Light.currentTab.saveTitle();\" onblur=\"javascript:Light.getTabById('"+this.serverId+"').saveTitle();\"/>";L$('portalTabTitle').focus();}
var tabs=this.parent.tabs;for(var i=tabs.length;i--;){if(tabs[i]&&tabs[i].serverId!=this.serverId)
tabs[i].blur();}
tabs=Light.portal.tabs;for(var i=tabs.length;i--;){if(tabs[i]&&tabs[i].serverId!=this.serverId&&tabs[i].serverId!=this.parentId)
tabs[i].blur();}
this.focused=true;window.scrollTo(0,0);log("end focus render portal tab "+this.label);}
LightPortalTab.prototype.focus=function(flag){log("start focus portal tab "+this.label);if(!this.opened||!this.container)this.open();this.focusRender(flag);this.refresh();log("end focus portal tab "+this.label);}
LightPortalTab.prototype.blur=function(){if(this.opened&&(this.container&&(this.focused||this.container.className=="current"))){this.container.className='';this.header.className='';this.header.style.paddingBottom=10;this.panel.style.display="none";var nodes=this.header.children;for(var i=nodes.length;i--;){if(nodes[i].tagName=="img"||nodes[i].tagName=="IMG"||nodes[i].tagName=="input"||nodes[i].tagName=="INPUT"||nodes[i].className=="icons")
this.header.removeChild(nodes[i]);}
this.focused=false;if(this.tabs.length>0){for(var i=this.tabs.length;i--;){if(this.tabs[i])this.tabs[i].blur();}}}}
LightPortalTab.prototype.addButtons=function(){var that=this;if(this.isEditable&&this.parentId==0&&Light.hasPermission(Light.permission.PORTAL_TAB_ADD)){if(!this.addButton){var addA=document.createElement('input');addA.title=Light.getMessage('MENU_ADD_SUBTAB');addA.type="button";addA.className="icons_addTab";addA.style.display="inline";addA.style.width=16;addA.style.borderStyle="none";addA.style.borderWidth=0;addA.onclick=function(){that.addSubPage();}
this.addButton=addA;}
this.header.appendChild(this.addButton);}
if(this.isCloseable){if(!this.closeButton){var clsA=document.createElement('input');clsA.title=Light.getMessage('CLOSE');clsA.type="button";clsA.className="icons_closeTab";clsA.style.display="inline";clsA.style.width=16;clsA.style.borderStyle="none";clsA.style.borderWidth=0;clsA.onclick=function(){that.close();}
this.closeButton=clsA;}
this.header.appendChild(this.closeButton);}}
LightPortalTab.prototype.saveTitle=function(){if(!L$('portalTabTitle'))return;var title=L$('portalTabTitle').value;this.editTitle=false;this.label=title;this.refreshTitle();var params="title="+encodeURIComponent(title)
+"&tabId="+Light.currentTab.serverId;Light.ajax.sendRequest(Light._EDIT_TAB_TITLE,{parameters:params});}
LightPortalTab.prototype.refreshTitle=function(){if(this.container){this.title.innerHTML=this.label;this.header.innerHTML="";this.header.appendChild(this.title);if(this.focused)this.addButtons();}}
LightPortalTab.prototype.refresh=function(){if(!this.loaded){this.load();this.loaded=true;}else{this.refreshPortletMethod();this.rePositionAll();}
Light.portal.refreshPortalMenu();}
LightPortalTab.prototype.load=function(){var tabId=this.serverId;if(L$('tab'+tabId+'TabsJSON')){this.renderSubTabs(JSON.parse(Light.getMessage('tab'+tabId+'TabsJSON')),true);}else if(this.parentId>0){this.getPortletsByTab();}else{var opt={method:'post',asynchronous:false,postBody:'parentId='+tabId}
this.render(Light.ajax.sendRequest(Light._LOAD_TAB_CONTENT,opt));}}
LightPortalTab.prototype.render=function(data){var index=data.lastIndexOf("</textarea>");if(index>0){var content=data.substring(0,index+11);document.body.appendChild(Light.newElement({element:'div',innerHTML:content}));data=data.substr(index+11);}
var contents=JSON.parse(data);this.portletsJSON=contents.portlets;this.renderSubTabs(contents.subTabs);}
LightPortalTab.prototype.getSubTabs=function(){var tabId=this.serverId;if(L$('tab'+tabId+'TabsJSON')){this.renderSubTabs(Light.getMessage('tab'+tabId+'TabsJSON'));return;}
var currentTab=this;var opt={method:'post',asynchronous:false,postBody:'parentId='+tabId}
this.renderSubTabs(Light.ajax.sendRequest(Light._GET_TABS_BY_PARENT,opt));}
LightPortalTab.prototype.renderSubTabs=function(subTabs){if(subTabs.length<=0){this.getPortletsByTab();return;}
log("render sub tabs:"+subTabs);this.panel.innerHTML="<div id='tabs"+this.serverId+"' class='portal-tabs-sub' ><ul id='tabList"+this.serverId+"'></ul></div><div id='tabPanels"+this.serverId+"' class='portal-tab-panels' ></div>";var defaultTab=0;for(var i=0,len=subTabs.length;i<len;i++){var tab=new LightPortalTab(subTabs[i]);tab.open();if(tab.defaulted)
defaultTab=i;}
this.addTabMenu();if(this.defaultTab){if(this.defaultTab.indexOf("-")>0){var defaultTabs=this.defaultTab.split("-");if(defaultTabs[0]<subTabs.length){defaultTab=defaultTabs[0];}
if(defaultTabs.length>1){var defaultSubTabs="";for(var i=1;i<defaultTabs.length;i++){if(i==1)
defaultSubTabs=defaultTabs[i];else
defaultSubTabs+="-"+defaultTabs[i];}
this.tabs[defaultTab].defaultTab=defaultSubTabs;}}else{defaultTab=this.defaultTab;}}
this.tabs[defaultTab].focus();if(subTabs.length<=1&&!Light.hasPermission(Light.permission.PORTAL_TAB_ADD))
this.tabs[0].hide();}
LightPortalTab.prototype.addTabMenu=function(){if(this.isEditable&&Light.hasPermission(Light.permission.PORTAL_TAB_ADD))Light.portal.addTabMenu(this.serverId,this.parentId);}
LightPortalTab.prototype.getPortletsByTab=function(){var tabId=this.serverId;if(this.portletsJSON){this.renderPortlets(this.portletsJSON);}else if(this.parentId>0&&this.parent.portletsJSON){this.renderPortlets(this.parent.portletsJSON);delete this.parent.portletsJSON;}else if(L$('tab'+tabId+'PortletsJSON')){this.renderPortlets(JSON.parse(L$('tab'+tabId+'PortletsJSON').title));}else{var currentTab=this;var opt={method:'post',asynchronous:false,postBody:'tabId='+tabId}
this.renderPortlets(JSON.parse(Light.ajax.sendRequest(Light._GET_PORTLETS_BY_TAB,opt)));}}
LightPortalTab.prototype.renderPortlets=function(portlets){if(portlets.length>0){var len=portlets.length;log("render tab portlets:"+len);var maxPortlet=null;Light.currentTab=this;for(var i=0;i<len;i++){var portlet=new PortletWindow(portlets[i]);if(portlet.state!=Light._MAXIMIZED_STATE)
portlet.refresh();else
maxPortlet=portlet;}
if(maxPortlet){maxPortlet.refresh();}}else
this.repositionFooter();}
LightPortalTab.prototype.addSubPage=function(){var params="tabId="+this.serverId;Light.ajax.sendRequest(Light._ADD_SUB_TAB,{parameters:params,onSuccess:Light.refreshPortal});}
LightPortalTab.prototype.resize=function(){this.adjustWidth();this.reLayout();Light.portal.refreshPortalMenu();}
LightPortalTab.prototype.reRender=function(){this.adjustWidth();for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;i--;){var portlet=p[j];if(portlet&&!portlet.isPopupWindow){portlet.reRender();}}}}}
LightPortalTab.prototype.adjustWidth=function(){if(this.fitScreen==1){var totalWidth=0;var len=this.widths.length;for(var i=len;i--;){totalWidth+=parseInt(this.widths[i]);if(i)totalWidth+=this.between;}
var max=Light.portal.layout.maxWidth;var diff=max-totalWidth;if(diff){var eachD=parseInt(diff/len);for(var i=len;i--;){this.widths[i]+=eachD;if(this.widths[i]>max)
this.widths[i]=max;}}}}
LightPortalTab.prototype.reLayout=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){var portlet=p[j];if(portlet&&!portlet.maximized&&!portlet.isNew){portlet.width=this.widths[i];portlet.left=0;for(var k=0;k<portlet.position;k++){portlet.left+=this.widths[k]+this.between;}}}}}
this.rePositionAll();}
LightPortalTab.prototype.refreshPortletMethod=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){var portlet=p[j];if(portlet&&portlet.data&&portlet.data.method){eval(portlet.data.method+"('"+portlet.data.id+"')");}}}}}
LightPortalTab.prototype.rePositionAll=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){var portlet=p[j];if(portlet&&!portlet.maximized&&!portlet.isNew){this.repositionPortlets(portlet);break;}}}}
this.repositionFooter();}
LightPortalTab.prototype.getTop=function(){return(this.parentId>0)?30:0;}
LightPortalTab.prototype.repositionPortlets=function(portlet){if(typeof portlet.popup!="undefined"&&portlet.popup){portlet.focus();return;}
var position=portlet.position;var index=portlet.index;var height=0;var maxIndex=0;var nullNum=0;for(var i=0,len=this.portlets[position].length;i<len;i++){var portlet=this.portlets[position][i];if(portlet){if(position>0){for(var j=0;j<position;j++){var p=this.portlets[j];if(p&&p[i]){if(p[i].colspan+p[i].position>position){height=p[i].top+p[i].getHeight();}}}}
if(i>=index&&!portlet.maximized){if(height>0)
portlet.top=height+portlet.marginTop;else
portlet.top=portlet.window.top+portlet.marginTop+this.getTop();portlet.refreshPosition();}
if(position>0){for(var j=0;j<position;j++){var p=this.portlets[j];if(p&&p[i+1]){if(p[i+1].colspan+p[i+1].position>position){if(p[i+1].top<parseInt(portlet.top)+parseInt(portlet.getHeight())+portlet.marginTop){p[i+1].top=parseInt(portlet.top)+parseInt(portlet.getHeight())+portlet.marginTop;p[i+1].refreshPosition();for(var k=0;k<=j;k++){var top=p[i+1].top+p[i+1].getHeight()+p[i+1].marginTop;if(this.portlets[k]){for(var m=i+2;m<this.portlets[k].length;m++){if(this.portlets[k][m]){this.portlets[k][m].top=top;this.portlets[k][m].refreshPosition();top=this.portlets[k][m].top+this.portlets[k][m].getHeight()+p[i+1].marginTop;}}}}}}}}}
if(!portlet.maximized&&!portlet.isNew){height=portlet.top+portlet.getHeight();}
if(portlet.maximized&&!portlet.isNew){height=portlet.getHeight();maxIndex=i;nullNum=0;}}
if(portlet==null){nullNum++;}}
if(portlet&&portlet.colspan>0){for(var i=position+1;i<position+portlet.colspan;i++){if(this.portlets[i]&&this.portlets[i][portlet.index])
this.repositionPortlets(this.portlets[i][portlet.index]);}}
this.repositionFooter();}
LightPortalTab.prototype.repositionFooter=function(){var currentTabId=Light.getCurrentTabId();if(currentTabId!=this.serverId)return;var maxHeight=this.getMaxHeight();if(document.all){Light.portal.footer.style.posTop=maxHeight;}else{Light.portal.footer.style.top=maxHeight;}
if((maxHeight-Light.portal.layout.maxTop)>Light.portal.layout.maxHeight)
Light.portal.container.style.height=maxHeight-Light.portal.layout.maxTop;Light.portal.footer.style.width=Light.portal.layout.containerWidth;}
LightPortalTab.prototype.getMaxHeight=function(){var heights=[];var maxHeight=0;for(var i=0,len=this.portlets.length;i<len;i++){var p=this.portlets[i];if(p){for(var j=0,len2=p.length;j<len2;j++){if(p[j]){if(p[j].maximized){maxHeight=p[j].top+p[j].getHeight();break;}else{heights[i]=p[j].top+p[j].getHeight();}}}}
if(maxHeight>0)
break;}
if(maxHeight==0){for(var i=0,len=heights.length;i<len;i++){if(heights[i]>maxHeight)
maxHeight=heights[i];}}
maxHeight=maxHeight+Light.portal.layout.footerMarginTop;if(maxHeight<600)maxHeight=600;return maxHeight;}
LightPortalTab.prototype.getPortletIndex=function(position){var addAfterLast=true;var index=0;var p=this.portlets[position];if(p){for(var i=0,len=p.length;i<len;i++){if(p[i]==null){addAfterLast=false;index=i;break;}}
if(addAfterLast){index=p.length;}}else{this.portlets[position]=[];}
return index;}
LightPortalTab.prototype.showOtherPortlets=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]&&p[j].maximized){p[j].show();return;}}}}
for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]){p[j].show();}}}}
Light.portal.menu.style.zIndex=++Light.maxZIndex;}
LightPortalTab.prototype.hideOtherPortlets=function(portlet){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]&&p[j].serverId!=portlet.serverId){p[j].hide();}}}}}
LightPortalTab.prototype.collapseAll=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]&&p[j].minimized){p[j].minimize();}}}}}
LightPortalTab.prototype.expandAll=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]&&p[j].minimized){p[j].minimize();}
if(p[j]&&p[j].maximized){p[j].maximize();}}}}}
LightPortalTab.prototype.normalAll=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]&&p[j].maximized){p[j].maximize();}}}}}
LightPortalTab.prototype.refreshWindowTransparent=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]){p[j].refreshWindowTransparent();}}}}}
LightPortalTab.prototype.refreshPortlets=function(){for(var i=this.portlets.length;i--;){var p=this.portlets[i];if(p){for(var j=p.length;j--;){if(p[j]){p[j].lastAction=null;p[j].refresh(false);}}}}}
LightPortalTab.prototype.setNotification=function(notification){var changed=false;if(this.label.indexOf("(")>0){var index=this.label.indexOf("(");this.label=this.label.substring(0,index);changed=true;}
if(notification)this.label+="("+notification+")";if(notification||changed)this.refreshTitle();}
LightPortalTab.prototype.close=function(){if(!Light.confirm(Light.getMessage('COMMAND_CLOSE_TAB'))){return;}
L$(this.tabList).removeChild(this.container);L$(this.tabPanels).removeChild(this.panel);delete this.parent.tabs[this.index];var focus=false;var tabs=this.parent.tabs;for(var i=this.index;i--;){if(tabs[i])
tabs[i].focus();focus=true;break;}
if(!focus){for(var i=this.index+1;i<tabs.length;i++){if(tabs[i])
tabs[i].focus();break;}}
Light.ajax.sendRequest(Light._DELETE_TAB,{parameters:'tabId='+this.serverId});}
Light.showTab=function(serverId){hideTopPopupDiv('viewMoreTab');var tab=Light.getTabById(serverId);if(tab.opened){tab.focus();return;}
var tabList=L$(Light._TAB_LIST);var lastNode=null;for(var i=tabList.children.length;i--;){var node=tabList.children[i];if(!node.style.display||node.style.display!="none"){lastNode=node;break;}}
if(lastNode){var id=lastNode.id;var ids=id.split("_");if(ids.length>1)id=ids[ids.length-1];var oldTab=Light.getTabById(id);oldTab.blur();oldTab.opened=false;oldTab.container.style.display="none";}
tab.open();tab.focus();}
Light.gotoTab=function(parentId,serverId){if(parentId==0)return Light.showTab(serverId);hideTopPopupDiv('viewSubTab');var tabs=Light.portal.allTabs;for(var i=tabs.length;i--;){var tab=tabs[i];if(tab){if(tab.serverId==parentId&&!tab.focused){tab.focus();break;}}}
Light.gotoSubTab(serverId);}
Light.gotoSubTab=function(serverId){var flag=true;var tabs=Light.portal.allTabs;for(var i=tabs.length;i--;){var tab=tabs[i];if(tab.serverId==serverId)flag=false;if(tab.serverId==serverId&&!tab.focused){tab.focus();break;}}
if(flag)setTimeout((function(){Light.gotoSubTab(serverId)}),100);}
Light.focusLeftTab=function(){var tabs=Light.portal.tabs;var tabList=L$(Light._TAB_LIST);var len=tabList.children.length;for(var i=0;i<len;i++){var node=tabList.children[i];if(node&&node.tagName=="LI"){if("current"==node.className){if(i>0){var previous=i+Light.portal.startTab-1;tabs[previous].focus();}else if(i==0){var previous=Light.portal.startTab-1;if(previous>=0){if(previous==0){var left=L$('leftTabButton');left.className="";left.onclick="";left.style.MozOpacity=0.3;if(left.filters)left.filters.alpha.opacity=30;}
tabList.removeChild(tabList.children[Light.portal.data.maxShowTabs-1]);if(!tabs[previous].opened){tabs[previous].open(tabs[previous+1]);}else{tabList.insertBefore(tabs[previous].container,tabs[previous+1].container);}
tabs[previous].focus();Light.portal.startTab--;}}}}}
if(Light.portal.startTab==0){var left=L$('leftTabButton');left.className="";left.onclick="";left.style.MozOpacity=0.3;if(left.filters)left.filters.alpha.opacity=30;}
var last=len-1;if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD)&&!Light.portal.mobile)last--;if(Light.portal.startTab+last<Light.portal.tabs.length){var right=L$('rightTabButton');right.className='portal-tab-button';right.onclick=function(){Light.focusRightTab();};right.style.MozOpacity=1;if(right.filters)left.filters.alpha.opacity=100;}}
Light.focusRightTab=function(){var tabs=Light.portal.tabs;var tabList=L$(Light._TAB_LIST);for(var i=0,len=tabList.children.length;i<len;i++){var node=tabList.children[i];if(node&&node.tagName=="LI"){if("current"==node.className){var last=tabList.children.length-1;if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD)&&!Light.portal.mobile)last--;if(i<last){var next=Light.portal.startTab+i+1;Light.portal.tabs[next].focus();}else if(i==last){var next=Light.portal.startTab+i+1;if(next<=Light.portal.tabs.length-1){if(next==Light.portal.tabs.length-1){var right=L$('rightTabButton');right.className="";right.onclick="";right.style.MozOpacity=0.3;if(right.filters)left.filters.alpha.opacity=30;}
tabList.removeChild(tabList.children[0]);if(!Light.portal.tabs[next].opened){Light.portal.tabs[next].open();}else{var lastOne=document.getElementById('tabMenuAddTab');if(lastOne)
tabList.insertBefore(Light.portal.tabs[next].container,lastOne);else
tabList.appendChild(Light.portal.tabs[next].container);}
Light.portal.tabs[next].focus();Light.portal.startTab++;}}}}}
if(Light.portal.startTab){var left=L$('leftTabButton');left.className='portal-tab-button';left.onclick=function(){Light.focusLeftTab();};left.style.MozOpacity=1;if(left.filters)left.filters.alpha.opacity=100;}
if(Light.portal.startTab+Light.portal.data.maxShowTabs>=Light.portal.tabs.length){var right=L$('rightTabButton');right.className="";right.onclick="";right.style.MozOpacity=0.3;if(right.filters)left.filters.alpha.opacity=30;}}
function LightPortlet(){}
LightPortlet.prototype.reRender=function(windowSkin){this.refreshWidth();var skin;if(windowSkin){skin=windowSkin;this.windowSkin=windowSkin;}
if(!this.windowSkin&&this.window.type!=this.parent.windowSkin)
skin=this.parent.windowSkin;if(skin&&this.window.type!=skin){this.window.close(this);this.window=eval("new "+skin+"()");this.window.render(this);this.refresh();return true;}
return false;}
LightPortlet.prototype.refreshWidth=function(){this.width=this.parent.widths[this.position];if(this.colspan){for(var i=this.position+1;i<this.position+this.colspan;i++){if(this.parent.widths.length>i)
this.width+=this.parent.between+this.parent.widths[i];}}}
LightPortlet.prototype.focus=function(){this.window.focus(this);}
LightPortlet.prototype.show=function(){this.window.show(this);}
LightPortlet.prototype.hide=function(){this.window.hide(this);}
LightPortlet.prototype.refreshPortletTitle=function(){if(!this.notFirstLoad){this.notFirstLoad=true;var id="portlet"+this.serverId;var node=L$(id+".title");if(node){var title=node.value;if(title&&title.trim().length>0){var data=JSON.parse(title);this.setTitle(data);}
node.parentNode.removeChild(node);}else
Light.executeRender(this.id,Light._HEADER_MODE,'','','');}else
Light.executeRender(this.id,Light._HEADER_MODE,'','','');}
LightPortlet.prototype.setTitle=function(data){var flag=false;if(data&&data.title){this.title=data.title;flag=true;}else{if(this.title.indexOf("(")>0){var index=this.title.indexOf("(");this.title=this.title.substring(0,index);flag=true;}
if(data&&data.suffix){this.title=this.title+"("+data.suffix+")";flag=true;}}
if(flag)this.refreshHeader();}
LightPortlet.prototype.moveBegin=function(e){document.body.onselectstart=function(){return false};document.body.ondragstart=function(){return false};var x=this.left;var y=this.top;if(e!=null){if(document.all)e=event;x=e.clientX;y=e.clientY;}
this.window.focus(this);this.moveable=true;this.mouseDownX=x;this.mouseDownY=y;this.moveBeginX=x;this.moveBeginY=y;Light.portal.moveTimer=0;this.startMoveTimer();var vdocument=L$(Light._PANEL_PREFIX+this.parent.serverId);Light.portal.highLightBar.style.visibility="hidden";vdocument.appendChild(Light.portal.highLightBar);}
LightPortlet.prototype.moveEnd=function(){if(this.moveable){var xDifference=this.mouseDownX-this.moveBeginX;var yDifference=this.mouseDownY-this.moveBeginY;if(this.left<0)this.left=0;if(this.moveToColumn!=this.position){if(this.moveToColumn>this.position)
this.moveRight(this.moveToColumn);else if(this.moveToColumn<this.position)
this.moveLeft(this.moveToColumn);}
else{if(this.mouseDownY>this.moveBeginY)
this.moveDown();else if(this.mouseDownY<this.moveBeginY)
this.moveUp();}
this.lastAction=null;this.moveable=false;var vdocument=L$(Light._PANEL_PREFIX+this.parent.serverId);vdocument.removeChild(Light.portal.highLightBar);if(!this.minimized){this.refresh(this.isNew);}
this.popup=false;if(this.isNew){this.window.close(this);this.window=eval("new "+(this.windowSkin?this.windowSkin:this.parent.windowSkin)+"()");this.window.render(this);this.isNew=false;}}}
LightPortlet.prototype.move=function(e){if(this.moveable){var x=e.clientX;var y=e.clientY;this.left+=x-this.mouseDownX;this.top+=y-this.mouseDownY;var direction="left";if(x>this.mouseDownX)direction="right";this.refreshPosition(this);this.mouseDownX=x;this.mouseDownY=y;var xDifference=this.mouseDownX-this.moveBeginX;var yDifference=this.mouseDownY-this.moveBeginY;var moveTo=0;var moveToColumn=-1;for(var i=0;i<this.parent.widths.length;i++){if(i>0)
moveTo+=this.parent.widths[i-1]+this.parent.between*(i-1);if(direction=="left"){if(this.left<moveTo+this.parent.widths[i]){moveToColumn=i;break;}}else{if(this.left+this.width>moveTo){moveToColumn=i;}}}
this.moveToColumn=moveToColumn;Light.portal.highLightBar.style.visibility="visible";if(moveToColumn!=this.position){if(moveToColumn>this.position)
this.highLightBarX(1,moveToColumn);else if(moveToColumn<this.position)
this.highLightBarX(2,moveToColumn);}else{if(this.mouseDownY>this.moveBeginY)
this.highLightBarY(3);else if(this.mouseDownY<this.moveBeginY)
this.highLightBarY(4);}}}
LightPortlet.prototype.startMoveTimer=function(){if(Light.portal.moveTimer>=0&&Light.portal.moveTimer<10){Light.portal.moveTimer++;var that=this;setTimeout((function(){that.startMoveTimer()}),15);}
if(Light.portal.moveTimer==10){Light.portal.dragDropPortlet=this;this.refreshPosition();}}
LightPortlet.prototype.refreshPosition=function(){this.window.position(this);this.focus();}
LightPortlet.prototype.autoRefresh=function(){if(this.mode!=Light._VIEW_MODE)return;if(this.autoRefreshed){if(this.firstTimeAutoRefreshed){this.firstTimeAutoRefreshed=false;}else{this.selfRefresh();}
var that=this;this.timer=setTimeout((function(){that.autoRefresh()}),this.periodTime);}}
LightPortlet.prototype.selfRefresh=function(){if(!this.minimized&&(!this.maximized||this.autoRefreshWhenMaximized)){if(this.mode==Light._VIEW_MODE){if(this.refreshAtClient){Light.refreshAtClient(this);}else{this.refreshAction=true;Light.executeRefresh(this.id);}}}}
LightPortlet.prototype.refresh=function(flag){this.setTitle({});if(this.minimized){this.window.container.innerHTML=Light.empty;this.refreshPortletTitle();return;}
if(this.type==3){if(!Light.executeAtClient(this)){var that=this;setTimeout((function(){that.refresh()}),100);}
return;}
if(Light.executeAtClient(this))return;if(flag==null||flag==true){this.window.container.innerHTML=Light.loading;this.parent.repositionPortlets(this);}
this.refreshAction=true;Light.executeRefresh(this.id);if(this.autoRefreshed){this.firstTimeAutoRefreshed=true;this.autoRefresh();}}
LightPortlet.prototype.changePosition=function(){var params="responseId="+this.id
+"&tabId="+this.parent.serverId
+"&portletId="+this.serverId
+"&column="+this.position
+"&row="+this.index;Light.ajax.sendRequest(Light._CHANGE_POSITION,{parameters:params});this.parent.repositionPortlets(this);}
LightPortlet.prototype.rememberMode=function(mode){}
LightPortlet.prototype.rememberState=function(state){if(Light.isLogin()){var params="state="+state+"&portletId="+this.serverId;Light.ajax.sendRequest(Light._REMEBER_STATE,{parameters:params});}}
LightPortlet.prototype.refreshButtons=function(){this.window.refreshButtons(this);if(this.mode==Light._EDIT_MODE){this.rememberMode(1);}else if(this.mode==Light._HELP_MODE){this.rememberMode(2);}else{this.rememberMode(0);}}
LightPortlet.prototype.edit=function(){if(this.editMode){this.mode=Light._EDIT_MODE;this.cancelMinimized();Light.executePortlet(this.id);this.refreshButtons();}}
LightPortlet.prototype.cancelEdit=function(){if(this.editMode){this.mode=Light._VIEW_MODE;this.cancelMinimized();this.lastAction=null;this.refresh(false);this.refreshButtons();}}
LightPortlet.prototype.help=function(){if(this.helpMode){this.mode=Light._HELP_MODE;this.cancelMinimized();Light.executePortlet(this.id);this.refreshButtons();}}
LightPortlet.prototype.cancelHelp=function(){if(this.helpMode){this.mode=Light._VIEW_MODE;this.cancelMinimized();this.lastAction=null;this.refresh(false);this.refreshButtons();}}
LightPortlet.prototype.config=function(){if(this.configMode){this.mode=Light._CONFIG_MODE;this.cancelMinimized();var data={id:this.id,title:this.title,barBgColor:this.barBgColor,barFontColor:this.barFontColor,contentBgColor:this.contentBgColor,textColor:this.textColor,transparent:this.transparent,autoRefreshed:this.autoRefreshed?1:0,periodTime:parseInt(this.periodTime/1000),showNumber:this.showNumber,skin:this.window.type,windowStatus:(this.maximized)?2:((this.minimized)?1:0),colspan:this.colspan,marginTop:this.marginTop,maxColumns:this.parent.widths.length,showIcon:this.showIcon,client:this.client};Light.setPortletContent(data.id,Light.getViewTemplate("configMode.jst",data));this.refreshButtons();}}
LightPortlet.prototype.cancelConfig=function(){if(this.configMode){this.mode=Light._VIEW_MODE;this.cancelMinimized();this.lastAction=null;this.refresh(false);this.refreshWindow();}}
LightPortlet.prototype.cancelMinimized=function(){if(this.minimized){this.minimized=false;this.state=Light._NORMAL_STATE;this.window.minimize(this);}}
LightPortlet.prototype.highLightBarX=function(pos,moveToColumn){var temp=null;var temp2=null;var showHighLightBar=true;var columnLeft=0;if(this.parent.portlets[moveToColumn]==null)
this.parent.portlets[moveToColumn]=[];if(this.parent.portlets[moveToColumn]!=null){var column=moveToColumn;var len=this.parent.portlets[column].length;for(var i=0;i<len;i++){if(this.parent.portlets[column][i]!=null){if(columnLeft==0)columnLeft=this.parent.portlets[column][i].left;if(this.top<this.parent.portlets[column][i].top){temp=this.parent.portlets[column][i];break;}}}
if(temp==null){for(var i=len-1;i>=0;i--){if(this.parent.portlets[column][i]!=null){temp2=this.parent.portlets[column][i];break;}}}}else
showHighLightBar=false;if(showHighLightBar){var highLeft=columnLeft;var highTop=this.window.top;var highWidth=this.parent.widths[moveToColumn];var highHeight=5;if(temp!=null){highTop=temp.top-this.marginTop+2;highLeft=temp.left;highWidth=temp.width;}else if(temp2!=null){highTop=temp2.top+temp2.getHeight()+2;}else{for(var i=0;i<moveToColumn;i++){highLeft+=this.parent.widths[i]+this.parent.between;}}
if(document.all){Light.portal.highLightBar.style.posLeft=highLeft;Light.portal.highLightBar.style.posTop=highTop;}else{Light.portal.highLightBar.style.left=highLeft;Light.portal.highLightBar.style.top=highTop;}
Light.portal.highLightBar.style.width=highWidth;Light.portal.highLightBar.style.height=highHeight;Light.portal.highLightBar.style.zIndex=++Light.maxZIndex;}else
Light.portal.highLightBar.style.visibility="hidden";}
LightPortlet.prototype.highLightBarY=function(pos){var temp=null;var showHighLightBar=true;if(pos==3){var started=this.index+1;for(var i=started;i<this.parent.portlets[this.position].length;i++){if(this.parent.portlets[this.position][i]!=null){temp=this.parent.portlets[this.position][i];break;}}
if(temp==null)
showHighLightBar=false;}
if(pos==4){if(this.index>0){var started=this.index-1;for(var i=started;i>=0;i--){if(this.parent.portlets[this.position][i]!=null){temp=this.parent.portlets[this.position][i];break;}}}}
if(showHighLightBar){var highLeft;var highTop=this.window.top+5;var highWidth=this.parent.widths[this.position];var highHeight=5;if(temp!=null){if(pos!=3)
highTop=temp.top-this.marginTop+2;else{var temp2=null;var started=temp.index+1;for(var i=started;i<this.parent.portlets[this.position].length;i++){if(this.parent.portlets[this.position][i]!=null){temp2=this.parent.portlets[this.position][i];break;}}
if(temp2!=null)
highTop=temp2.top-this.marginTop+2;else{highTop=temp.top+temp.getHeight()+2;}}
highLeft=temp.left;highWidth=temp.width;}else{for(var i=0;i<this.position;i++){highLeft+=this.parent.widths[i]+this.parent.between;}}
if(document.all){Light.portal.highLightBar.style.posLeft=highLeft;Light.portal.highLightBar.style.posTop=highTop;}else{Light.portal.highLightBar.style.left=highLeft;Light.portal.highLightBar.style.top=highTop;}
Light.portal.highLightBar.style.width=highWidth;Light.portal.highLightBar.style.height=highHeight;Light.portal.highLightBar.style.zIndex=++Light.maxZIndex;}else
Light.portal.highLightBar.style.visibility="hidden";}
LightPortlet.prototype.moveCancel=function(){this.buttonIsClicked=true;}
LightPortlet.prototype.moveAllowed=function(){this.buttonIsClicked=false;}
LightPortlet.prototype.moveUp=function(){if(this.index>0){var temp=null;var upIndex=0;var currentIndex=this.index;var started=this.index-1;for(var i=started;i>=0;i--){if(this.parent.portlets[this.position][i]!=null){temp=this.parent.portlets[this.position][i];upIndex=i;break;}}
if(temp!=null){temp.index=this.index;this.index=upIndex;this.parent.portlets[this.position][upIndex]=this;this.parent.portlets[this.position][currentIndex]=temp;temp.changePosition();temp.lastAction=null;this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
this.changePosition();this.parent.repositionPortlets(this);}}}
LightPortlet.prototype.moveDown=function(){var temp=null;var downIndex=0;var currentIndex=this.index;var started=this.index+1;for(var i=started;i<this.parent.portlets[this.position].length;i++){if(this.parent.portlets[this.position][i]!=null){temp=this.parent.portlets[this.position][i];downIndex=i;break;}}
if(temp!=null){temp.index=this.index;this.index=downIndex;this.parent.portlets[this.position][downIndex]=this;this.parent.portlets[this.position][currentIndex]=temp;this.changePosition();this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
temp.changePosition();temp.lastAction=null;temp.parent.repositionPortlets(temp);}}
LightPortlet.prototype.moveLeft=function(moveToColumn){if(this.position>0){var temp=null;var newIndex=0;var currentPosition=this.position;var currentIndex=this.index;var column=moveToColumn;if(this.parent.portlets[column]==null)
this.parent.portlets[column]=[];var len=this.parent.portlets[column].length;for(var i=0;i<len;i++){if(this.parent.portlets[column][i]!=null&&this.top<this.parent.portlets[column][i].top){temp=this.parent.portlets[column][i];newIndex=temp.index;break;}}
if(temp!=null){for(var i=len-1;i>=0;i--){if(this.parent.portlets[column][i]!=null){var temp2=this.parent.portlets[column][i];temp2.index++;this.parent.portlets[column][i+1]=temp2;this.parent.portlets[column][i+1].changePosition();this.parent.portlets[column][i]=null;temp2.lastAction=null;if(!temp2.minimized){temp2.refresh(false);}
if(temp2.serverId==temp.serverId)break;}}
this.position=column;this.index=newIndex;this.parent.portlets[this.position][this.index]=this;this.parent.portlets[currentPosition][currentIndex]=null;this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
this.refreshWidth();this.changePosition();}else{this.position=column;var isEmpty=true;for(var i=len-1;i>=0;i--){if(this.parent.portlets[column][i]!=null){var temp3=this.parent.portlets[column][i];this.index=temp3.index+1;isEmpty=false;break;}}
if(isEmpty)this.index=0;this.parent.portlets[this.position][this.index]=this;this.parent.portlets[currentPosition][currentIndex]=null;this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
this.refreshWidth();this.changePosition();}
var length=this.parent.portlets[currentPosition].length;for(var i=currentIndex+1;i<length;i++){if(this.parent.portlets[currentPosition][i]!=null){var next=this.parent.portlets[currentPosition][i];this.parent.repositionPortlets(next);break;}}}}
LightPortlet.prototype.moveRight=function(moveToColumn){columns=this.parent.widths.length;if(this.position+1<columns&&this.parent.portlets[this.position+1]==null)
this.parent.portlets[this.position+1]=[];if(this.parent.portlets[this.position+1]!=null){var temp=null;var newIndex=0;var currentPosition=this.position;var currentIndex=this.index;var column=moveToColumn;var len=this.parent.portlets[column].length;for(var i=0;i<len;i++){if(this.parent.portlets[column][i]!=null&&this.top<this.parent.portlets[column][i].top){temp=this.parent.portlets[column][i];newIndex=temp.index;break;}}
if(temp!=null){for(var i=len-1;i>=0;i--){if(this.parent.portlets[column][i]!=null){var temp2=this.parent.portlets[column][i];temp2.index++;this.parent.portlets[column][i+1]=temp2;this.parent.portlets[column][i+1].changePosition();this.parent.portlets[column][i]=null;temp2.lastAction=null;if(!temp2.minimized){temp2.refresh(false);}
if(temp2.serverId==temp.serverId)break;}}
this.position=column;this.index=newIndex;this.parent.portlets[this.position][this.index]=this;this.parent.portlets[currentPosition][currentIndex]=null;this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
this.refreshWidth();this.changePosition();}else{this.position=column;var isEmpty=true;for(var i=len-1;i>=0;i--){if(this.parent.portlets[column][i]!=null){var temp3=this.parent.portlets[column][i];this.index=temp3.index+1;isEmpty=false;break;}}
if(isEmpty)this.index=0;this.parent.portlets[this.position][this.index]=this;this.parent.portlets[currentPosition][currentIndex]=null;this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
this.refreshWidth();this.changePosition();}
var length=this.parent.portlets[currentPosition].length;for(var i=currentIndex+1;i<length;i++){if(this.parent.portlets[currentPosition][i]!=null){var next=this.parent.portlets[currentPosition][i];this.parent.repositionPortlets(next);break;}}}}
LightPortlet.prototype.minimize=function(){if(this.allowMinimized){this.minimized=!this.minimized;if(this.minimized){this.state=Light._MINIMIZED_STATE;if(this.maximized){this.maximize(false);this.minimized=true;}
this.window.container.innerHTML=Light.empty;}else{this.state=Light._NORMAL_STATE;}
this.window.minimize(this);this.parent.repositionPortlets(this);this.rememberState((this.minimized)?1:0);if(!this.minimized)
this.refresh()}}
LightPortlet.prototype.maximize=function(flag){if(this.allowMaximized){this.windowMaximize(flag);this.refresh();if(!this.isPopupWindow&&this.mode!=Light._HEADER_MODE&&!isSafari()){if(this.maximized)
Light.addHistory(this.id+"/"+Light._MODE_PREFIX+this.mode+"/"+Light._STATE_PREFIX+this.state+"/");else
Light.addHistory();}}}
LightPortlet.prototype.windowMaximize=function(flag){this.maximized=!this.maximized;if(!this.maximized){this.state=Light._NORMAL_STATE;var height=0;var maxIndex=0;var nullNum=0;for(var i=0;i<this.parent.portlets[this.position].length;i++){if(i==this.index){break;}
if(this.parent.portlets[this.position][i]!=null&&!this.parent.portlets[this.position][i].maximized){height+=this.parent.portlets[this.position][i].getHeight();}
if(this.parent.portlets[this.position][i]==null){nullNum++;}
if(this.parent.portlets[this.position][i]!=null&&this.parent.portlets[this.position][i].maximized){height=this.parent.portlets[this.position][i].getHeight();maxIndex=i;nullNum=0;}}
this.top=this.window.top+height+this.marginTop*(i-maxIndex-nullNum);this.refreshWidth();this.left=0;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}
Light.portal.container.style.zIndex=1;this.parent.showOtherPortlets();}else{this.state=Light._MAXIMIZED_STATE;this.parent.hideOtherPortlets(this);this.left=Light.portal.layout.maxLeft;this.top=Light.portal.layout.maxTop;this.width=Light.portal.layout.maxWidth;Light.portal.container.style.zIndex=++Light.maxZIndex;Light.portal.body.style.zIndex=++Light.maxZIndex;Light.portal.footer.style.zIndex=++Light.maxZIndex;window.scrollTo(0,0);}
this.window.maximize(this);this.parent.repositionPortlets(this);if(flag==null||flag==true){this.rememberState((this.maximized)?2:0);}}
LightPortlet.prototype.close=function(){var closePortlet=Light.confirm(Light.getMessage('COMMAND_CLOSE_PORTLET'));if(!closePortlet)
{return;}
if(this.maximized)
this.windowMaximize();if(this.timer)clearTimeout(this.timer);this.window.close(this);this.parent.portlets[this.position][this.index]=null;for(var i=Light.portal.allPortlets.length;i--;){if(Light.portal.allPortlets[i]&&Light.portal.allPortlets[i].serverId==this.serverId){Light.portal.allPortlets[i]=null;break;}}
this.parent.repositionPortlets(this);Light.ajax.sendRequest(Light._DELETE_PORTLET,{parameters:'portletId='+this.serverId});}
LightPortlet.prototype.refreshWindow=function(){this.window.refreshWindow(this);}
LightPortlet.prototype.refreshHeader=function(){this.window.refreshHeader(this);}
LightPortlet.prototype.refreshWindowTransparent=function(){var bg="transparent";if(!Light.portal.data.portletWindowTransparent&&!this.transparent){if(this.contentBgColor)
bg=this.contentBgColor;else
bg="#ffffff";}
this.window.container.style.backgroundColor=bg;}
LightPortlet.prototype.refreshTextColor=function(){var id=this.window.container.id;if(this.textColor){$('#'+id).find('*').css({color:this.textColor});}
if(this.contentBgColor){$('#'+id+' textarea').css({backgroundColor:this.contentBgColor});}}
LightPortlet.prototype.getTop=function(){return this.window.top;}
LightPortlet.prototype.getHeight=function(){var clientHeight=this.window.window.clientHeight;var clientHeight2=this.window.container.clientHeight;return(clientHeight>clientHeight2)?clientHeight:clientHeight2;}
LightPortlet.prototype.setContent=function(content){if(this.type==2){try{var data;if(content){data=JSON.parse(content);}else{data={id:this.id,userId:Light.getRememberedUserId(),success:"",error:"",view:this.name+"."+this.mode};}
if(typeof data.id=="undefined")data.id=this.id;if(typeof data.userId=="undefined")data.userId=Light.getRememberedUserId();if(typeof data.view=="undefined")data.view=this.name+"."+this.mode.toLowerCase();if(typeof data.success=="undefined")data.success="";if(typeof data.error=="undefined")data.error="";if(data&&data.method){this.data=data;}
if(document.getElementById(data.view)!=null){this.window.setContent(Light.getViewTemplate(data.view,data));}else{var that=this;setTimeout((function(){that.refreshData(data)}),100);}}catch(err){this.window.setContent(content);}
if(this.data&&this.data.method){eval(this.data.method+"('"+this.data.id+"')");}}else
this.window.setContent(content);}
LightPortlet.prototype.refreshData=function(data){if(document.getElementById(data.view)!=null){this.window.setContent(Light.getViewTemplate(data.view,data));this.parent.repositionPortlets(this);}else{var that=this;setTimeout((function(){that.refreshData(data)}),100);}}
PortletWindow.prototype=new LightPortlet;PortletWindow.prototype.constructor=PortletWindow;function PortletWindow(data,isNew,noRender){Light.portal.allPortlets.push(this);this.parent=Light.currentTab;this.windowSkin=data.windowSkin;if(isNew)
this.window=new WindowSkin2();else
this.window=eval("new "+(this.windowSkin?this.windowSkin:this.parent.windowSkin)+"()");this.allowMove=true;this.mode=Light._VIEW_MODE;if(data.initMode==1)this.mode=Light._EDIT_MODE;if(data.initMode==2)this.mode=Light._HELP_MODE;if(data.initMode==3)this.mode=Light._CONFIG_MODE;this.state=Light._NORMAL_STATE;this.minimized=false;this.maximized=false;if(data.initState==1){this.state=Light._MINIMIZED_STATE;this.minimized=true;}
if(data.initState==2){this.state=Light._MAXIMIZED_STATE;this.maximized=true;}
this.serverId=data.serverId;this.id=Light._PR_PREFIX+this.serverId;this.position=data.column;this.colspan=data.colspan;this.title=data.title;this.icon=data.icon;this.url=data.url;this.name=data.name;this.requestUrl=data.path;this.closeable=data.closeable;this.refreshMode=data.refreshMode;this.editMode=data.editMode;this.helpMode=data.helpMode;this.configMode=data.configMode;this.allowMinimized=data.allowMinimized;this.allowMaximized=data.allowMaximized;this.autoRefreshed=data.autoRefreshed;this.refreshAtClient=data.refreshAtClient;this.periodTime=data.periodTime;this.showNumber=data.showNumber;this.allowJS=data.allowJS;this.barBgColor=data.barBgColor;this.barFontColor=data.barFontColor;this.contentBgColor=data.contentBgColor;this.textColor=data.textColor;this.parameter=data.parameter;this.marginTop=data.marginTop;this.transparent=false;if(data.transparent==1)this.transparent=true;this.showIcon=true;if(data.showIcon==0)this.showIcon=false;this.client=data.client;this.type=data.type;this.index=this.parent.getPortletIndex(this.position);data=null;var height=0;var maxIndex=0;var nullNum=0;for(var i=0;i<this.parent.portlets[this.position].length;i++){var vPortlet=this.parent.portlets[this.position][i];if(i==this.index){break;}
if(vPortlet&&!vPortlet.maximized){height+=vPortlet.getHeight()+vPortlet.marginTop;}
if(!vPortlet){nullNum++;}
if(vPortlet&&vPortlet.maximized){height=vPortlet.getHeight()+vPortlet.marginTop;maxIndex=i;nullNum=0;}
if(this.position>0){for(var j=0;j<this.position;j++){var portlets=this.parent.portlets[j];if(portlets&&portlets[i]){if(portlets[i].colspan+portlets[i].position>this.position){height+=portlets[i].getHeight()+portlets[i].marginTop;}}}}}
this.refreshWidth();this.isNew=isNew;if(isNew){this.top=getMousePositionY(Light.e)-120;this.left=0-parseInt(Light.portal.body.style.marginLeft)+getMousePositionX(Light.e);this.popup=true;}else{if(height>0)
this.top=this.window.top+height+this.marginTop;else
this.top=this.window.top+this.marginTop+this.parent.getTop();this.left=this.window.left;for(var i=0;i<this.position;i++){this.left+=this.parent.widths[i]+this.parent.between;}}
this.parent.portlets[this.position][this.index]=this;if(!noRender){this.window.render(this);this.window.container.innerHTML=Light.empty;this.parent.repositionPortlets(this);this.refreshPosition();}else
this.window.bind(this);this.moveable=false;this.autoRefreshWhenMaximized=false;this.autoShow=false;this.opacity=0;this.fade="in";this.myPictureIndex=0;this.myPictures=[];this.autoShowId=null;this.mouseDownX=0;this.mouseDownY=0;this.content=null;if(this.autoRefreshed){this.firstTimeAutoRefreshed=true;this.autoRefresh();}}
PortletPopupWindow.prototype=new LightPortlet;PortletPopupWindow.prototype.constructor=PortletPopupWindow;function PortletPopupWindow(position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,refreshAtClient,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter,allowMinimized,allowMaximized,allowPopup,location,type,top){Light.portal.allPortlets.push(this);this.parent=Light.currentTab;this.window=new WindowSkin2();this.allowMove=true;this.isPopupWindow=true;this.popup=true;if(typeof allowPopup!="undefined"&&!allowPopup)
this.popup=false;if(typeof location!="undefined"){this.location=location;if(this.location==1){Light.portal.originalLeft=Light.portal.layout.containerLeft;Light.portal.originalHeaderLeft=Light.portal.header.style.marginLeft;Light.portal.originalBodyLeft=Light.portal.body.style.marginLeft;Light.portal.layout.containerLeft=width+10;Light.portal.header.style.marginLeft=Light.portal.layout.containerLeft;Light.portal.body.style.marginLeft=Light.portal.layout.containerLeft;this.allowMove=false;}}
this.mode=Light._VIEW_MODE;this.state=Light._NORMAL_STATE;this.serverId=Date.parse(new Date());this.id=Light._PR_PREFIX+this.serverId;this.position=position;this.left=left;this.top=left;if(top)this.top=top;this.width=width;this.title=title;this.icon=icon;this.url=url;this.name=request;this.requestUrl=requestUrl;this.closeable=closeable;this.refreshMode=refreshMode;this.editMode=editMode;this.helpMode=helpMode;this.configMode=configMode;this.allowMinimized=true;if(allowMinimized!=null&&!allowMinimized)this.allowMinimized=false;this.allowMaximized=true;if(allowMaximized!=null&&!allowMaximized)this.allowMaximized=false;this.autoRefreshed=autoRefreshed;this.refreshAtClient=refreshAtClient;this.periodTime=periodTime;this.showNumber=10;this.allowJS=allowJS;this.barBgColor=barBgColor;this.barFontColor=barFontColor;this.className="portlet-popup";this.contentBgColor='#ffffff';if(contentBgColor!='')this.contentBgColor=contentBgColor;this.parameter=parameter;this.index=this.parent.getPortletIndex(this.position);this.type=1;if(type!=null)this.type=type;this.marginTop=10;var height=0;var maxIndex=0;var nullNum=0;this.originalTop=this.top;this.originalWidth=this.width;this.originalLeft=this.left;this.parent.portlets[this.position][this.index]=this;this.window.render(this);this.minimized=false;this.maximized=false;this.moveable=false;this.autoRefreshWhenMaximized=false;this.autoShow=false;this.opacity=0;this.fade="in";this.myPictureIndex=0;this.myPictures=[];this.autoShowId=null;this.mouseDownX=0;this.mouseDownY=0;this.refreshPosition();this.window.container.innerHTML=Light.loading;this.focus();if(this.autoRefreshed){this.firstTimeAutoRefreshed=true;this.autoRefresh(this);}
if(this.location==null||this.location==5){this.windowMaximize();}}
PortletPopupWindow.prototype.rememberMode=function(mode){}
PortletPopupWindow.prototype.rememberState=function(state){}
PortletPopupWindow.prototype.moveBegin=function(e){document.body.onselectstart=function(){return false};document.body.ondragstart=function(){return false};if(document.all)e=event;var x=e.clientX;var y=e.clientY;this.focus();this.moveable=true;this.mouseDownX=x;this.mouseDownY=y;this.moveBeginX=x;this.moveBeginY=y;Light.portal.moveTimer=0;this.startMoveTimer(this);}
PortletPopupWindow.prototype.moveEnd=function(){if(this.moveable){this.moveable=false;this.originalLeft=this.left;this.originalTop=this.top;}}
PortletPopupWindow.prototype.move=function(e){if(this.moveable){var x=e.clientX;var y=e.clientY;this.left+=x-this.mouseDownX;this.top+=y-this.mouseDownY;this.refreshPosition();this.mouseDownX=x;this.mouseDownY=y;}}
PortletPopupWindow.prototype.changePosition=function(){this.parent.repositionPortlets(this);}
PortletPopupWindow.prototype.moveLeft=function(){}
PortletPopupWindow.prototype.moveRight=function(){}
PortletPopupWindow.prototype.close=function(){this.window.close(this);this.parent.portlets[this.position][this.index]=null;for(var i=Light.portal.allPortlets.length-1;i>=0;i--){if(Light.portal.allPortlets[i]&&Light.portal.allPortlets[i].serverId==this.serverId){Light.portal.allPortlets[i]=null;break;}}
if(this.maximized)
this.parent.showOtherPortlets();this.parent.repositionPortlets(this);this.parent.repositionFooter();if(Light.portal.originalLeft!=null){Light.portal.layout.containerLeft=Light.portal.originalLeft;Light.portal.header.style.marginLeft=Light.portal.originalHeaderLeft;Light.portal.body.style.marginLeft=Light.portal.originalBodyLeft;Light.portal.originalLeft=null;Light.portal.originalHeaderLeft=null;Light.portal.originalBodyLeft=null;}
Light.addHistory("");}
PortletPopupWindow.prototype.refreshWindowTransparent=function(){}
PortletPopupWindow.prototype.refreshTextColor=function(){}
Light.service={getPortalHeader:function(portal){return new LightPortalHeaderImpl().render(portal);},getPortalMenu:function(portal){return new LightPortalMenuImpl().render(portal);},getPortalFooter:function(portal){return new LightPortalFooterImpl().render(portal);}}
function log(message){}
function logDebug(message){if(!log.window_||log.window_.closed){var win=window.open("",null,"width=400,height=200,"+"scrollbars=yes,resizable=yes,status=no,"+"location=no,menubar=no,toolbar=no");if(!win)return;var doc=win.document;doc.write("<html><head><title>Debug Log</title></head>"+"<body></body></html>");doc.close();log.window_=win;}
var logLine=log.window_.document.createElement("div");logLine.appendChild(log.window_.document.createTextNode(message));log.window_.document.body.appendChild(logLine);logLine=null;}
Light.setCookie=function(name,value,expires,path,domain,secure){if(!domain)domain=Light.getCookieDomain();Light.deleteCookie(name,path,domain);document.cookie=name+"="+escape(value)+
((expires)?"; expires="+expires.toGMTString():"")+
((path)?"; path="+path:"; path= /")+
((domain)?"; domain="+domain:"")+
((secure)?"; secure":"");}
Light.getCookie=function(name){var dc=document.cookie;if(!dc)return null;var prefix=name+"=";var begin=dc.indexOf("; "+prefix);if(begin==-1){begin=dc.indexOf(prefix);if(begin!=0)return null;}else{begin+=2;}
var end=document.cookie.indexOf(";",begin);if(end==-1){end=dc.length;}
return unescape(dc.substring(begin+prefix.length,end));}
Light.deleteCookie=function(name,path,domain){if(Light.getCookie(name)){if(!domain)domain=Light.getCookieDomain();document.cookie=name+"="+
((path)?"; path="+path:"; path= /")+
((domain)?"; domain="+domain:"")+"; expires=Thu, 01-Jan-70 00:00:01 GMT";}}
Light.getMessage=function(key){var element=L$(key);return(element)?element.title:"";}
Light.getCookieDomain=function(){var host=window.location.hostname;if(host.toLowerCase().startsWith("www."))host=host.substr(4);var domainList=Light._DOMAIN_LIST.split(",");for(var i in domainList){if(host.indexOf(domainList[i])>=0){var domain=host.substring(0,host.indexOf(domainList[i]));var parts=domain.split(".");if(parts.length>=2){return"."+parts[parts.length-1]+domainList[i];}else if(parts.length==1){return"."+domain+domainList[i];}else
return null;}}
return null;}
Light.alert=function(message){alert(message);}
Light.confirm=function(message){return confirm(message);}
Light.newElement=function(data){if(!data.element)return null;var element=document.createElement(data.element);for(var name in data){if(name!='element'){if(name!='style')
element[name]=data[name];else{for(var style in data.style){element.style[style]=data.style[style];}}}}
if(data.element==='a'&&!data.href)
element.href='javascript:;';return element;}
String.prototype.isNumeric=function(){var ValidChars="0123456789.";var IsNumber=true;var Char;for(var i=0,len=this.length;i<len&&IsNumber;i++){Char=this.charAt(i);if(ValidChars.indexOf(Char)==-1){IsNumber=false;}}
return IsNumber;}
String.prototype.isDigit=function(){if(this.length>1){return false;}
var string="1234567890";if(string.indexOf(this)!=-1){return true;}
return false;}
String.prototype.trim=function(){return this.replace(/^\s+|\s+$/g,"");}
String.prototype.startsWith=function(str){return this.substring(0,str.length)==str;}
String.prototype.endsWith=function(str){return(this.match(str+"$")==str)}
if(!Array.prototype.map){Array.prototype.map=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();var res=new Array(len);var thisp=arguments[1];for(var i=0;i<len;i++){if(i in this)
res[i]=fun.call(thisp,this[i],i,this);}
return res;};}
if(!Array.prototype.every){Array.prototype.every=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();var thisp=arguments[1];for(var i=0;i<len;i++){if(i in this&&!fun.call(thisp,this[i],i,this))
return false;}
return true;};}
if(!Array.prototype.filter){Array.prototype.filter=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();var res=[];var thisp=arguments[1];for(var i=0;i<len;i++){if(i in this){var val=this[i];if(fun.call(thisp,val,i,this))
res.push(val);}}
return res;};}
if(!Array.prototype.forEach){Array.prototype.forEach=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();var thisp=arguments[1];for(var i=0;i<len;i++){if(i in this)
fun.call(thisp,this[i],i,this);}};}
if(!Array.prototype.indexOf){Array.prototype.indexOf=function(elt){var len=this.length;var from=Number(arguments[1])||0;from=(from<0)?Math.ceil(from):Math.floor(from);if(from<0)
from+=len;for(;from<len;from++){if(from in this&&this[from]===elt)
return from;}
return-1;};}
if(!Array.prototype.lastIndexOf){Array.prototype.lastIndexOf=function(elt){var len=this.length;var from=Number(arguments[1]);if(isNaN(from)){from=len-1;}
else{from=(from<0)?Math.ceil(from):Math.floor(from);if(from<0)
from+=len;else if(from>=len)
from=len-1;}
for(;from>-1;from--){if(from in this&&this[from]===elt)
return from;}
return-1;};}
if(!Array.prototype.reduce){Array.prototype.reduce=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();if(len==0&&arguments.length==1)
throw new TypeError();var i=0;if(arguments.length>=2){var rv=arguments[1];}else{do{if(i in this){rv=this[i++];break;}
if(++i>=len)
throw new TypeError();}
while(true);}
for(;i<len;i++){if(i in this)
rv=fun.call(null,rv,this[i],i,this);}
return rv;};}
if(!Array.prototype.reduceRight){Array.prototype.reduceRight=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();if(len==0&&arguments.length==1)
throw new TypeError();var i=len-1;if(arguments.length>=2){var rv=arguments[1];}else{do{if(i in this){rv=this[i--];break;}
if(--i<0)
throw new TypeError();}
while(true);}
for(;i>=0;i--){if(i in this)
rv=fun.call(null,rv,this[i],i,this);}
return rv;};}
if(!Array.prototype.some){Array.prototype.some=function(fun){var len=this.length;if(typeof fun!="function")
throw new TypeError();var thisp=arguments[1];for(var i=0;i<len;i++){if(i in this&&fun.call(thisp,this[i],i,this))
return true;}
return false;};}
Array.prototype.exists=function(value){for(var i=0;i<this.length;i++){if(typeof this[i]!=='undefined'&&this[i]!=null&&this[i]==value)return true;}
return false;}
Array.prototype.joinValue=function(sign){var value='';for(var i=0;i<this.length;i++){if(typeof this[i]!=='undefined'&&this[i]!=null&&this[i].length>0){if(value.length==0)
value=this[i];else
value+=sign+this[i];}}
return value;}
Array.prototype.get=function(value,param){for(var i=0;i<this.length;i++){if(typeof param!="undefined"&&param!=null)
if(typeof this[i]!=='undefined'&&this[i]!=null&&this[i][param]==value)return this[i];else
if(typeof this[i]!=='undefined'&&this[i]!=null&&this[i]==value)return this[i];}
return null;}
function isIE(){return/msie/i.test(navigator.userAgent)&&!/opera/i.test(navigator.userAgent);}
function isFirefox(){var ua=navigator.userAgent.toLowerCase();return(ua.indexOf('firefox')>=0)?true:false;}
function isGecko(){var ua=navigator.userAgent.toLowerCase();return(ua.indexOf('gecko')>=0)?true:false;}
function isChrome(){var ua=navigator.userAgent.toLowerCase();return(ua.indexOf('chrome')>=0)?true:false;}
function isSafari(){var ua=navigator.userAgent.toLowerCase();return(ua.indexOf('safari/')>=0)?((ua.indexOf('chrome')>=0)?false:true):false;}
function isOpera(){return(typeof window.opera!=="undefined")?true:false;}
function isIPhone(){return((navigator.userAgent.match(/iPhone/i))&&!(navigator.userAgent.match(/iPad/i)));}
function isIPad(){return navigator.userAgent.match(/iPad/i);}
function getWindowHeight(){return document.documentElement.scrollHeight;}
function getMousePositionX(e){var x,y=0;if(!isIE()){if(e){if(e.pageX||e.pageY)
{x=e.pageX;y=e.pageY;}
else if(e.clientX||e.clientY)
{x=e.clientX+document.body.scrollLeft;y=e.clientY+document.body.scrollTop;}}}else{if(window.event){x=event.clientX+document.body.scrollLeft;y=event.clientY;}else if(e){if(e.pageX||e.pageY)
{x=e.pageX;y=e.pageY;}
else if(e.clientX||e.clientY)
{x=e.clientX+document.body.scrollLeft;y=e.clientY;}}}
if(!x)x=0;return x;}
function getMousePositionY(e){var x,y=0;if(!isIE()){if(e){if(e.pageX||e.pageY)
{x=e.pageX;y=e.pageY;}
else if(e.clientX||e.clientY)
{x=e.clientX+document.body.scrollLeft;y=e.clientY+document.body.scrollTop;}}}else{if(window.event){x=event.clientX;y=event.clientY+document.body.scrollTop;}else if(e){if(e.pageX||e.pageY)
{x=e.pageX;y=e.pageY;}
else if(e.clientX||e.clientY)
{x=e.clientX;y=e.clientY+document.body.scrollTop;}}}
if(!y)y=0;return y;}
function setPosition(oElement,x,y){if(document.all){if(typeof x!="undefined")oElement.style.posLeft=x;if(typeof y!="undefined")oElement.style.posTop=y;}else{if(typeof x!="undefined")oElement.style.left=x;if(typeof y!="undefined")oElement.style.top=y;}}
function getPositionX(oElement){var x=0;if(oElement){if(document.all){x=parseInt(oElement.style.posLeft);}else{x=parseInt(oElement.style.left);}}
return x;}
function getPositionY(oElement){var y=0;if(oElement){if(document.all){y=parseInt(oElement.style.posTop);}else{y=parseInt(oElement.style.top);}}
return y;}
function getX(oElement){var iReturnValue=0;if(!isIE()){while(oElement!=null){iReturnValue+=oElement.offsetLeft;oElement=oElement.offsetParent;}}else{var obj=oElement.getBoundingClientRect();iReturnValue=obj.left;if(document.body.scrollLeft!=null)
iReturnValue=obj.left+document.body.scrollLeft;}
return iReturnValue;}
function getY(oElement){var iReturnValue=0;if(!isIE()){while(oElement!=null){iReturnValue+=oElement.offsetTop;oElement=oElement.offsetParent;}}else{var obj=oElement.getBoundingClientRect();iReturnValue=obj.top;if(document.body.scrollTop!=null)
iReturnValue=obj.top+document.body.scrollTop;}
return iReturnValue;}
function getScreenCenterY(){return getScrollOffset()+(getInnerHeight()/2);}
function getScreenCenterX(){return(document.body.clientWidth/2);}
function getInnerHeight(){var y;if(self.innerHeight){y=self.innerHeight;}else if(document.documentElement&&document.documentElement.clientHeight){y=document.documentElement.clientHeight;}else if(document.body){y=document.body.clientHeight;}
return y;}
function getScrollOffset(){var y;if(self.pageYOffset){y=self.pageYOffset;}
else if(document.documentElement&&document.documentElement.scrollTop){y=document.documentElement.scrollTop;}else if(document.body){y=document.body.scrollTop;}
return y;}
function addEventHandler(obj,eventName,handler){removeEvent(obj,eventName,handler);if(document.attachEvent){obj.attachEvent("on"+eventName,handler);}else if(document.addEventListener){obj.addEventListener(eventName,handler,false);}}
function removeEvent(obj,eventName,handler){try{if(obj.removeEventListener)obj.removeEventListener(eventName,handler,false);else if(obj.detachEvent){obj.detachEvent("on"+eventName,obj[eventName+handler]);obj[eventName+handler]=null;obj["e"+eventName+handler]=null;}}catch(err){}}
AIM={portletId:null,frame:function(c){var n='f'+Math.floor(Math.random()*99999);var d=document.createElement('DIV');d.innerHTML='<iframe style="display:none" src="about:blank" id="'+n+'" name="'+n+'" onload="AIM.loaded(\''+n+'\')"></iframe>';document.body.appendChild(d);var i=document.getElementById(n);if(c&&typeof(c.onComplete)=='function'){i.onComplete=c.onComplete;}
return n;},form:function(f,name){f.setAttribute('target',name);},submit:function(f,c,portletId){AIM.portletId=portletId;AIM.form(f,AIM.frame(c));if(c&&typeof(c.onStart)=='function'){return c.onStart(portletId);}else{return true;}},loaded:function(id){var i=document.getElementById(id);if(i.contentDocument){var d=i.contentDocument;}else if(i.contentWindow){var d=i.contentWindow.document;}else{var d=window.frames[id].document;}
if(d.location.href=="about:blank"){return;}
if(typeof(i.onComplete)=='function'){i.onComplete(AIM.portletId,d.getElementsByTagName('p')[0].childNodes[0].textContent);}}}
var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(input){var output="";var chr1,chr2,chr3,enc1,enc2,enc3,enc4;var i=0;input=Base64._utf8_encode(input);while(i<input.length){chr1=input.charCodeAt(i++);chr2=input.charCodeAt(i++);chr3=input.charCodeAt(i++);enc1=chr1>>2;enc2=((chr1&3)<<4)|(chr2>>4);enc3=((chr2&15)<<2)|(chr3>>6);enc4=chr3&63;if(isNaN(chr2)){enc3=enc4=64;}else if(isNaN(chr3)){enc4=64;}
output=output+
this._keyStr.charAt(enc1)+this._keyStr.charAt(enc2)+
this._keyStr.charAt(enc3)+this._keyStr.charAt(enc4);}
return output;},decode:function(input){var output="";var chr1,chr2,chr3;var enc1,enc2,enc3,enc4;var i=0;input=input.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(i<input.length){enc1=this._keyStr.indexOf(input.charAt(i++));enc2=this._keyStr.indexOf(input.charAt(i++));enc3=this._keyStr.indexOf(input.charAt(i++));enc4=this._keyStr.indexOf(input.charAt(i++));chr1=(enc1<<2)|(enc2>>4);chr2=((enc2&15)<<4)|(enc3>>2);chr3=((enc3&3)<<6)|enc4;output=output+String.fromCharCode(chr1);if(enc3!=64){output=output+String.fromCharCode(chr2);}
if(enc4!=64){output=output+String.fromCharCode(chr3);}}
output=Base64._utf8_decode(output);return output;},_utf8_encode:function(string){string=string.replace(/\r\n/g,"\n");var utftext="";for(var n=0;n<string.length;n++){var c=string.charCodeAt(n);if(c<128){utftext+=String.fromCharCode(c);}
else if((c>127)&&(c<2048)){utftext+=String.fromCharCode((c>>6)|192);utftext+=String.fromCharCode((c&63)|128);}
else{utftext+=String.fromCharCode((c>>12)|224);utftext+=String.fromCharCode(((c>>6)&63)|128);utftext+=String.fromCharCode((c&63)|128);}}
return utftext;},_utf8_decode:function(utftext){var string="";var i=0;var c=c1=c2=0;while(i<utftext.length){c=utftext.charCodeAt(i);if(c<128){string+=String.fromCharCode(c);i++;}
else if((c>191)&&(c<224)){c2=utftext.charCodeAt(i+1);string+=String.fromCharCode(((c&31)<<6)|(c2&63));i+=2;}
else{c2=utftext.charCodeAt(i+1);c3=utftext.charCodeAt(i+2);string+=String.fromCharCode(((c&15)<<12)|((c2&63)<<6)|(c3&63));i+=3;}}
return string;}}
function WindowSkin(){this.left=0;this.top=30+(isIE()?10:0);}
WindowSkin.prototype.bind=function(portlet){}
WindowSkin.prototype.render=function(portlet){}
WindowSkin.prototype.focus=function(portlet){}
WindowSkin.prototype.show=function(portlet){}
WindowSkin.prototype.hide=function(portlet){}
WindowSkin.prototype.position=function(portlet){}
WindowSkin.prototype.minimize=function(portlet){}
WindowSkin.prototype.maximize=function(portlet){}
WindowSkin.prototype.close=function(portlet){}
WindowSkin.prototype.refreshWindow=function(portlet){}
WindowSkin.prototype.refreshHeader=function(portlet){}
WindowSkin.prototype.refreshButtons=function(portlet){}
WindowSkin.prototype.setContent=function(content){this.container.innerHTML=content;}
WindowSkin.prototype.getRefreshButton=function(){return"<div title='"+Light.getMessage('REFRESH_PORTLET')+"' class='icons refresh_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getEditButton=function(){return"<div title='"+Light.getMessage('EDIT_MODE')+"' class='icons edit_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getCancelEditButton=function(){return"<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_edit_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getHelpButton=function(){return"<div title='"+Light.getMessage('HELP_MODE')+"' class='icons help_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getCancelHelpButton=function(){return"<div title='"+Light.getMessage('VIEW_MODE')+"'  class='icons leave_help_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getConfigButton=function(){return"<div title='"+Light.getMessage('CONFIG_MODE')+"' class='icons config_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getCancelConfigButton=function(){return"<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_config_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getMinButton=function(){return"<div title='"+Light.getMessage('MINIMIZED')+"' class='icons min_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getMaxButton=function(){return"<div title='"+Light.getMessage('MAXIMIZED')+"' class='icons max_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getRestoreButton=function(){return"<div title='"+Light.getMessage('RESTORE')+"' class='icons restore_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getPopoutButton=function(){return"<div title='"+Light.getMessage('POPOUT')+"' class='icons pop_out'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getPopinButton=function(){return"<div title='"+Light.getMessage('POPIN')+"' class='icons pop_in'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin.prototype.getCloseButton=function(){return"<div title='"+Light.getMessage('CLOSE')+"' class='icons close_on'"
+" onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
+" onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";}
WindowSkin1.prototype=new WindowSkin;WindowSkin1.prototype.constructor=WindowSkin1;function WindowSkin1(){WindowSkin.call(this);this.type="WindowSkin1";this.top=40+(isIE()?10:0);this.layout={titleRelativeLeft:10,titleRelativeTop:-8,buttonRelative:16,upRelativeRight:94,downRelativeRight:78,minRelativeRight:52,maxRelativeRight:36,closeRelativeRight:20,buttonRelativeTop:-6}}
WindowSkin1.prototype.render=function(portlet){this.renderContainer(portlet);this.renderHeader(portlet);}
WindowSkin1.prototype.renderContainer=function(portlet){this.container=this.createPortletContainer(portlet);this.window=this.container;var vdocument=L$('panel_'+portlet.parent.serverId);vdocument.appendChild(this.container);}
WindowSkin1.prototype.renderHeader=function(portlet){this.header=this.createPortletHeader(portlet);var vdocument=L$('panel_'+portlet.parent.serverId);vdocument.appendChild(this.header);if(portlet.closeable){this.closeButton=this.createPortletCloseButton(portlet);vdocument.appendChild(this.closeButton);}
if(portlet.allowMaximized){if(portlet.maximized){this.maxButton=this.createPortletRestoreMaxButton(portlet);this.createPortletMaxButton(portlet);}else{this.maxButton=this.createPortletMaxButton(portlet);this.createPortletRestoreMaxButton(portlet);}
vdocument.appendChild(this.maxButton);}
if(portlet.allowMinimized){if(portlet.minimized){this.minButton=this.createPortletRestoreMinButton(portlet);this.createPortletMinButton(portlet);}else{this.minButton=this.createPortletMinButton(portlet);this.createPortletRestoreMinButton(portlet);}
vdocument.appendChild(this.minButton);}
if(portlet.configMode){if(portlet.mode==Light._CONFIG_MODE){this.configButton=this.createPortletCancelConfigButton(portlet);this.createPortletConfigButton(portlet);}else{this.configButton=this.createPortletConfigButton(portlet);this.createPortletCancelConfigButton(portlet);}
vdocument.appendChild(this.configButton);}
if(portlet.helpMode){if(portlet.mode==Light._HELP_MODE){this.helpButton=this.createPortletCancelHelpButton(portlet);this.createPortletHelpButton(portlet);}else{this.helpButton=this.createPortletHelpButton(portlet);this.createPortletCancelHelpButton(portlet);}
vdocument.appendChild(this.helpButton);}
if(portlet.editMode){if(portlet.mode==Light._EDIT_MODE){this.editButton=this.createPortletCancelEditButton(portlet);this.createPortletEditButton(portlet);}else{this.editButton=this.createPortletEditButton(portlet);this.createPortletCancelEditButton(portlet);}
vdocument.appendChild(this.editButton);}
if(portlet.refreshMode){this.refreshButton=this.createPortletRefreshButton(portlet);vdocument.appendChild(this.refreshButton);}}
WindowSkin1.prototype.focus=function(portlet){var index=++Light.maxZIndex;this.container.style.zIndex=index;Light.maxZIndex++;this.header.style.zIndex=++index;if(portlet.refreshMode){this.refreshButton.style.zIndex=index;}
if(portlet.editMode){this.editButton.style.zIndex=index;}
if(portlet.helpMode){this.helpButton.style.zIndex=index;}
if(portlet.configMode){this.configButton.style.zIndex=index;}
if(portlet.allowMinimized){this.minButton.style.zIndex=index;}
if(portlet.allowMaximized){this.maxButton.style.zIndex=index;}
if(portlet.closeable){this.closeButton.style.zIndex=index;}}
WindowSkin1.prototype.show=function(portlet){this.hidden=false;this.container.style.visibility="visible";this.header.style.visibility="visible";if(portlet.refreshMode){this.refreshButton.style.visibility="visible";}
if(portlet.editMode){this.editButton.style.visibility="visible";}
if(portlet.helpMode){this.helpButton.style.visibility="visible";}
if(portlet.configMode){this.configButton.style.visibility="visible";}
if(portlet.allowMinimized){this.minButton.style.visibility="visible";}
if(portlet.allowMaximized){this.maxButton.style.visibility="visible";}
if(portlet.closeable){this.closeButton.style.visibility="visible";}}
WindowSkin1.prototype.hide=function(portlet){this.hidden=true;this.container.style.visibility="hidden";this.header.style.visibility="hidden";if(portlet.refreshMode){this.refreshButton.style.visibility="hidden";}
if(portlet.editMode){this.editButton.style.visibility="hidden";}
if(portlet.helpMode){this.helpButton.style.visibility="hidden";}
if(portlet.configMode){this.configButton.style.visibility="hidden";}
if(portlet.allowMinimized){this.minButton.style.visibility="hidden";}
if(portlet.allowMaximized){this.maxButton.style.visibility="hidden";}
if(portlet.closeable){this.closeButton.style.visibility="hidden";}}
WindowSkin1.prototype.position=function(portlet){this.container.style.width=portlet.width;if(document.all){this.container.style.posLeft=portlet.left;if(portlet.popup!=null)
portlet.top=portlet.top-portlet.marginTop;this.container.style.posTop=portlet.top;this.header.style.posLeft=portlet.left+this.layout.titleRelativeLeft;this.header.style.posTop=portlet.top+this.layout.titleRelativeTop;var RelativeRight=this.layout.minRelativeRight+this.layout.buttonRelative;if(portlet.configMode){this.configButton.style.posLeft=portlet.left+portlet.width-RelativeRight;this.configButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.helpMode){this.helpButton.style.posLeft=portlet.left+portlet.width-RelativeRight;this.helpButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.editMode){this.editButton.style.posLeft=portlet.left+portlet.width-RelativeRight;this.editButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.refreshMode){this.refreshButton.style.posLeft=portlet.left+portlet.width-RelativeRight;this.refreshButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.allowMinimized){this.minButton.style.posLeft=portlet.left+portlet.width-this.layout.minRelativeRight;this.minButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.allowMaximized){this.maxButton.style.posLeft=portlet.left+portlet.width-this.layout.maxRelativeRight;this.maxButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.closeable){this.closeButton.style.posLeft=portlet.left+portlet.width-this.layout.closeRelativeRight;this.closeButton.style.posTop=portlet.top+this.layout.buttonRelativeTop;}}
else{this.container.style.left=portlet.left;this.container.style.top=portlet.top;this.header.style.left=portlet.left+this.layout.titleRelativeLeft;this.header.style.top=portlet.top+this.layout.titleRelativeTop;var RelativeRight=this.layout.minRelativeRight+this.layout.buttonRelative;if(portlet.configMode){this.configButton.style.left=portlet.left+portlet.width-RelativeRight;this.configButton.style.top=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.helpMode){this.helpButton.style.left=portlet.left+portlet.width-RelativeRight;this.helpButton.style.top=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.editMode){this.editButton.style.left=portlet.left+portlet.width-RelativeRight;this.editButton.style.top=portlet.top+this.layout.buttonRelativeTop;RelativeRight=RelativeRight+this.layout.buttonRelative;}
if(portlet.refreshMode){this.refreshButton.style.left=portlet.left+portlet.width-RelativeRight;this.refreshButton.style.top=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.allowMinimized){this.minButton.style.left=portlet.left+portlet.width-this.layout.minRelativeRight;this.minButton.style.top=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.allowMaximized){this.maxButton.style.left=portlet.left+portlet.width-this.layout.maxRelativeRight;this.maxButton.style.top=portlet.top+this.layout.buttonRelativeTop;}
if(portlet.closeable){this.closeButton.style.left=portlet.left+portlet.width-this.layout.closeRelativeRight;this.closeButton.style.top=portlet.top+this.layout.buttonRelativeTop;}}
this.focus(portlet);}
WindowSkin1.prototype.minimize=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);if(portlet.minimized){vdocument.removeChild(this.minButton);this.minButton=this.createPortletRestoreMinButton(portlet);vdocument.appendChild(this.minButton);}else{vdocument.removeChild(this.minButton);this.minButton=this.createPortletMinButton(portlet);vdocument.appendChild(this.minButton);}
this.position(portlet);}
WindowSkin1.prototype.maximize=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);if(!portlet.maximized){vdocument.removeChild(this.maxButton);this.maxButton=this.createPortletMaxButton(portlet);vdocument.appendChild(this.maxButton);}else{vdocument.removeChild(this.maxButton);this.maxButton=this.createPortletRestoreMaxButton(portlet);vdocument.appendChild(this.maxButton);}
this.position(portlet);}
WindowSkin1.prototype.close=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);vdocument.removeChild(this.container);vdocument.removeChild(this.header);if(portlet.refreshMode){vdocument.removeChild(this.refreshButton);}
if(portlet.editMode){vdocument.removeChild(this.editButton);}
if(portlet.helpMode){vdocument.removeChild(this.helpButton);}
if(portlet.configMode){vdocument.removeChild(this.configButton);}
if(portlet.allowMinimized){vdocument.removeChild(this.minButton);}
if(portlet.allowMaximized){vdocument.removeChild(this.maxButton);}
vdocument.removeChild(this.closeButton);}
WindowSkin1.prototype.refreshWindow=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);vdocument.removeChild(this.container);this.container=this.createPortletContainer(portlet);vdocument.appendChild(this.container);this.refreshHeader(portlet);this.refreshButtons(portlet);this.position(portlet);}
WindowSkin1.prototype.refreshHeader=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);vdocument.removeChild(this.header);this.header=this.createPortletHeader(portlet);if(document.all){this.header.style.posLeft=portlet.left+this.layout.titleRelativeLeft;this.header.style.posTop=portlet.top+this.layout.titleRelativeTop;}else{this.header.style.left=portlet.left+this.layout.titleRelativeLeft;this.header.style.top=portlet.top+this.layout.titleRelativeTop;}
vdocument.appendChild(this.header);if(this.hidden)this.hide(portlet);}
WindowSkin1.prototype.refreshButtons=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);if(portlet.editMode){vdocument.removeChild(this.editButton);if(portlet.mode==Light._EDIT_MODE){this.editButton=this.createPortletCancelEditButton(portlet);}else{this.editButton=this.createPortletEditButton(portlet);}
this.editButton.style.visibility="hidden";}
if(portlet.helpMode){vdocument.removeChild(this.helpButton);if(portlet.mode==Light._HELP_MODE){this.helpButton=this.createPortletCancelHelpButton(portlet);}else{this.helpButton=this.createPortletHelpButton(portlet);}
this.helpButton.style.visibility="hidden";}
if(portlet.configMode){vdocument.removeChild(this.configButton);if(portlet.mode==Light._CONFIG_MODE){this.configButton=this.createPortletCancelConfigButton(portlet);}else{this.configButton=this.createPortletConfigButton(portlet);}
this.configButton.style.visibility="hidden";}
if(portlet.editMode)vdocument.appendChild(this.editButton);if(portlet.helpMode)vdocument.appendChild(this.helpButton);if(portlet.configMode)vdocument.appendChild(this.configButton);this.position(portlet);if(portlet.editMode)this.editButton.style.visibility="visible";if(portlet.helpMode)this.helpButton.style.visibility="visible";if(portlet.configMode)this.configButton.style.visibility="visible";}
WindowSkin1.prototype.createPortletContainer=function(portlet){var vContainer=Light.newElement({element:'div',id:portlet.id,className:(portlet.className)?portlet.className:"portlet",style:{position:"absolute",width:portlet.width,zIndex:++Light.maxZIndex}});if(portlet){if(portlet.contentBgColor)
vContainer.style.backgroundColor=portlet.contentBgColor;else if(Light.portal.data.portletWindowTransparent==false&&portlet.transparent==false)
vContainer.style.backgroundColor="#ffffff";}
return vContainer;}
WindowSkin1.prototype.createPortletHeader=function(portlet){var vHeader=Light.newElement({element:'div',className:"portlet-header",style:{position:"absolute"}});if(Light.getCurrentTab().isMoveable){vHeader.onmousedown=function(e){portlet.moveBegin(e);}
vHeader.style.cursor="move";}
var inner="";if(portlet.showIcon&&portlet.icon){if(portlet.icon.indexOf("/")>=0){if(portlet.icon.substring(0,4)=="http")
inner="<img src='"+portlet.icon+"' height='16' width='16' />";else
inner="<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";}else{var css=portlet.icon.split(" ");var cssParent="";if(css.length>1)cssParent=css[0];inner="<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";}}
if(portlet.url){inner=inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";if(portlet.barFontColor)
inner=inner+"<font color='"+portlet.barFontColor+"'>";inner=inner+portlet.title;if(portlet.barFontColor)
inner=inner+"</font>";inner=inner+"</a>";}else
inner=inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";vHeader.innerHTML=inner;vHeader.style.zIndex=Light.maxZIndex++;if(portlet.barBgColor){vHeader.style.backgroundImage="none";vHeader.style.backgroundColor=portlet.barBgColor;}
if(portlet.barFontColor)
vHeader.style.color=portlet.barFontColor;return vHeader;}
WindowSkin1.prototype.createPortletRefreshButton=function(portlet){var vButton=Light.newElement({element:'div',className:"portlet-header-button",style:{zIndex:Light.maxZIndex}});var varA=Light.newElement({element:'span',className:"icons",innerHTML:this.getRefreshButton(),onclick:function(){portlet.refresh();}});vButton.appendChild(varA);return vButton;}
WindowSkin1.prototype.createPortletEditButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getEditButton();varA.className="icons";varA.onclick=function(){portlet.edit();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletCancelEditButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getCancelEditButton();varA.className="icons";varA.onclick=function(){portlet.cancelEdit();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletHelpButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getHelpButton();varA.className="icons";varA.onclick=function(){portlet.help();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletCancelHelpButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getCancelHelpButton();varA.className="icons";varA.onclick=function(){portlet.cancelHelp();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletConfigButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getConfigButton();varA.className="icons";varA.onclick=function(){portlet.config();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletCancelConfigButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getCancelConfigButton();varA.className="icons";varA.onclick=function(){portlet.cancelConfig();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletMinButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getMinButton();varA.className="icons";varA.onclick=function(){portlet.minimize();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletRestoreMinButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getRestoreButton();varA.className="icons";varA.onclick=function(){portlet.minimize();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletMaxButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getMaxButton();varA.className="icons";varA.onclick=function(){portlet.maximize();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletRestoreMaxButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getRestoreButton();varA.className="icons";varA.onclick=function(){portlet.maximize();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.createPortletCloseButton=function(portlet){var vButton=document.createElement('div');vButton.className="portlet-header-button";var varA=document.createElement('span');varA.innerHTML=this.getCloseButton();varA.className="icons";varA.onclick=function(){portlet.close();}
vButton.appendChild(varA);vButton.style.zIndex=Light.maxZIndex;return vButton;}
WindowSkin1.prototype.setContent=function(content){this.container.innerHTML="<br/>"+content;}
WindowSkin2.prototype=new WindowSkin;WindowSkin2.prototype.constructor=WindowSkin2;function WindowSkin2(){this.type="WindowSkin2";}
WindowSkin2.prototype.render=function(portlet){this.window=this.createPortletWindow(portlet);this.container=this.createPortletContainer(portlet);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);if(this.headerButton!=null)
this.header.appendChild(this.headerButton);this.window.appendChild(this.header);this.window.appendChild(this.container);var vdocument=L$('panel_'+portlet.parent.serverId);if(typeof portlet.popup!="undefined"&&!portlet.popup){if(typeof portlet.location!="undefined"&&portlet.location==1){Light.portal.container.appendChild(this.window);Light.portal.content.style.marginLeft='0px';}else if(typeof portlet.location!="undefined"&&portlet.location==0){Light.portal.container.insertBefore(this.window,Light.portal.header);Light.portal.content.className='';}else
vdocument.appendChild(this.window);}else if(portlet.popup&&portlet.location&&portlet.location==4){this.window.style.zIndex=Light.maxZIndex+10000;document.body.appendChild(this.window);}else
vdocument.appendChild(this.window);}
WindowSkin2.prototype.bind=function(portlet){this.window=L$(Light._PW_PREFIX+portlet.serverId);this.container=L$(portlet.id);this.header=L$(Light._PH_PREFIX+portlet.serverId);this.headerButton=L$(Light._PB_PREFIX+portlet.serverId);}
WindowSkin2.prototype.focus=function(portlet){var index=++Light.maxZIndex;if(portlet.popup!=null&&portlet.popup&&portlet.isNew==null)
index=index+1000;if(portlet.location!=1)
this.window.style.zIndex=index;else
this.window.style.zIndex=0;}
WindowSkin2.prototype.show=function(portlet){this.hidden=false;this.window.style.visibility="visible";}
WindowSkin2.prototype.hide=function(portlet){this.hidden=true;this.window.style.visibility="hidden";}
WindowSkin2.prototype.position=function(portlet){this.window.style.width=portlet.width;this.header.style.width=portlet.width;this.container.style.width=portlet.width;var top=portlet.top;if(typeof portlet.popup!="undefined")
if(!portlet.popup){if(typeof portlet.location!="undefined"&&portlet.location==1)
top=portlet.top+20;}
if(document.all){this.window.style.posLeft=portlet.left;this.window.style.posTop=top;}
else{this.window.style.left=portlet.left;this.window.style.top=top;}
this.focus(portlet);}
WindowSkin2.prototype.minimize=function(portlet){this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin2.prototype.maximize=function(portlet){this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin2.prototype.close=function(portlet){var vdocument=L$('panel_'+portlet.parent.serverId);this.window.removeChild(this.header);this.window.removeChild(this.container);Light.portal.content.className='portal-content';if(typeof portlet.popup!="undefined"&&!portlet.popup&&portlet.isNew==null){Light.portal.container.removeChild(this.window);}else if(portlet.popup&&portlet.location&&portlet.location==4){document.body.removeChild(this.window);}else
vdocument.removeChild(this.window);}
WindowSkin2.prototype.refreshWindow=function(portlet){this.window.removeChild(this.header);this.window.removeChild(this.container);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.container=this.createPortletContainer(portlet);this.header.appendChild(this.headerButton);this.window.appendChild(this.header);this.window.appendChild(this.container);this.position(portlet);}
WindowSkin2.prototype.refreshHeader=function(portlet){this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);if(this.hidden)this.hide(portlet);}
WindowSkin2.prototype.refreshButtons=function(portlet){this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin2.prototype.createPortletWindow=function(portlet){var vWindow=document.createElement('div');vWindow.id=Light._PW_PREFIX+portlet.serverId;if(typeof portlet.popup!="undefined"&&!portlet.popup&&typeof portlet.location!="undefined"&&portlet.location!=1)
vWindow.style.margin="0px ";else
vWindow.style.position="absolute";vWindow.className="portlet2";if(portlet.location!=1)
vWindow.style.zIndex=++Light.maxZIndex;if(portlet&&portlet.contentBgColor&&portlet.contentBgColor.length>0)
vWindow.style.backgroundColor=portlet.contentBgColor;else if(Light.portal.data.portletWindowTransparent==false&&portlet.transparent==false)
vWindow.style.backgroundColor="#ffffff";return vWindow;}
WindowSkin2.prototype.createPortletHeader=function(portlet,window){var vHeader=document.createElement('div');vHeader.id=Light._PH_PREFIX+portlet.serverId;if(!portlet.minimized){vHeader.className="portlet2-header";if(portlet.barBgColor){vHeader.style.backgroundImage="none";vHeader.style.backgroundColor=portlet.barBgColor;}}else{vHeader.className="portlet2-header-min";}
if((Light.currentTab.isMoveable&&portlet.allowMove)||(typeof portlet.popup!="undefined"&&portlet.popup)){vHeader.onmousedown=function(e){portlet.focus();portlet.moveBegin(e);}
vHeader.style.cursor="move";}else{vHeader.onmousedown=function(e){portlet.focus();}}
vHeader.onmouseover=function(e){window.headerButton.style.visibility="visible";}
vHeader.onmouseout=function(e){window.headerButton.style.visibility="hidden";}
var vTitle=document.createElement('span');var inner="";if(portlet.showIcon&&portlet.icon){if(portlet.icon.indexOf("/")>=0){if(portlet.icon.substring(0,4)=="http")
inner="<img src='"+portlet.icon+"' height='16' width='16' />";else
inner="<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";}else{var css=portlet.icon.split(" ");var cssParent="";if(css.length>1)cssParent=css[0];inner="<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";}}
if(portlet.url){inner=inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";if(portlet.barFontColor)
inner=inner+"<font color='"+portlet.barFontColor+"'>";inner=inner+portlet.title;if(portlet.barFontColor)
inner=inner+"</font>";inner=inner+"</a>";}else
inner=inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";vTitle.innerHTML=inner;vTitle.className="portlet2-header-title";if(portlet.barFontColor)
vTitle.style.color=portlet.barFontColor;vHeader.appendChild(vTitle);return vHeader;}
WindowSkin2.prototype.createPortletHeaderButton=function(portlet){var vButton=document.createElement('div');vButton.id=Light._PB_PREFIX+portlet.serverId;vButton.className="portlet2-header-button";vButton.style.visibility="hidden";if(portlet.closeable){var clsA=document.createElement('span');clsA.className="icons";clsA.innerHTML=this.getCloseButton();clsA.onclick=function(){portlet.close();portlet.moveAllowed();}
clsA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(clsA);}
if(portlet.allowMaximized){var maxA=document.createElement('span');maxA.className="icons";if(portlet.maximized){maxA.innerHTML=this.getRestoreButton();}else{maxA.innerHTML=this.getMaxButton();}
maxA.onclick=function(){portlet.maximize();portlet.moveAllowed();}
maxA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(maxA);}
if(portlet.allowMinimized){var minA=document.createElement('span');minA.className="icons";if(portlet.minimized){minA.innerHTML=this.getRestoreButton();}else{minA.innerHTML=this.getMinButton();}
minA.onclick=function(){portlet.minimize();portlet.moveAllowed();}
minA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(minA);}
if(portlet.configMode){var config=document.createElement('span');config.className="icons";if(portlet.mode==Light._CONFIG_MODE){config.innerHTML=this.getCancelConfigButton();config.onclick=function(){portlet.cancelConfig();portlet.moveAllowed();}}else{config.innerHTML=this.getConfigButton();config.onclick=function(){portlet.config();portlet.moveAllowed();}}
config.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(config);}
if(portlet.helpMode){var help=document.createElement('span');help.className="icons";if(portlet.mode==Light._HELP_MODE){help.innerHTML=this.getCancelHelpButton();help.onclick=function(){portlet.cancelHelp();portlet.moveAllowed();}}else{help.innerHTML=this.getHelpButton();help.onclick=function(){portlet.help();portlet.moveAllowed();}}
help.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(help);}
if(portlet.editMode){var edit=document.createElement('span');edit.className="icons";if(portlet.mode==Light._EDIT_MODE){edit.innerHTML=this.getCancelEditButton();edit.onclick=function(){portlet.cancelEdit();portlet.moveAllowed();}}else{edit.innerHTML=this.getEditButton();edit.onclick=function(){portlet.edit();portlet.moveAllowed();}}
edit.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(edit);}
if(portlet.refreshMode){var refresh=document.createElement('span');refresh.className="icons";refresh.innerHTML=this.getRefreshButton();refresh.onclick=function(){portlet.refresh();portlet.moveAllowed();}
refresh.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(refresh);}
return vButton;}
WindowSkin2.prototype.createPortletContainer=function(portlet){var vContainer=document.createElement('div');vContainer.id=portlet.id;vContainer.onmousedown=function(){portlet.focus();}
return vContainer;}
WindowSkin21.prototype=new WindowSkin2;WindowSkin21.prototype.constructor=WindowSkin21;function WindowSkin21(){WindowSkin2.call(this);}
WindowSkin21.prototype.createPortletContainer=function(portlet){var vContainer=document.createElement('div');vContainer.id=portlet.id+"_container";vContainer.onmousedown=function(){portlet.focus();}
var vContent=document.createElement('div');vContent.className="chattingBox";vContent.id=portlet.id;vContainer.container=vContent;vContainer.appendChild(vContent);if(!portlet.minimized){var vInput=document.createElement('div');var data={id:vContent.id};vInput.innerHTML=Light.getViewTemplate("chattingInput.jst",data);vContainer.appendChild(vInput);}
return vContainer;}
WindowSkin21.prototype.createPortletHeaderButton=function(portlet)
{var vButton=document.createElement('div');vButton.className="portlet2-header-button";vButton.id=Light._PB_PREFIX+portlet.serverId;if(portlet.closeable){var clsA=document.createElement('span');clsA.className="icons";clsA.innerHTML=this.getCloseButton();clsA.onclick=function(){portlet.close();portlet.moveAllowed();}
clsA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(clsA);}
if(portlet.allowPopout){var popA=document.createElement('span');popA.className="icons";if(portlet.isPopout){popA.innerHTML=this.getRestoreButton();}else{popA.innerHTML=this.getPopoutButton();}
popA.onclick=function(){portlet.popout();portlet.moveAllowed();}
popA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(popA);}
if(portlet.allowMaximized){var maxA=document.createElement('span');maxA.className="icons";if(portlet.maximized){maxA.innerHTML=this.getRestoreButton();}else{maxA.innerHTML=this.getMaxButton();}
maxA.onclick=function(){portlet.maximize();portlet.moveAllowed();}
maxA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(maxA);}
if(portlet.allowMinimized){var minA=document.createElement('span');minA.className="icons";if(portlet.minimized){minA.innerHTML=this.getRestoreButton();}else{minA.innerHTML=this.getMinButton();}
minA.onclick=function(){portlet.minimize();portlet.moveAllowed();}
minA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(minA);}
if(portlet.configMode){var config=document.createElement('span');config.className="icons";if(portlet.mode==Light._CONFIG_MODE){config.innerHTML=this.getCancelConfigButton();config.onclick=function(){portlet.cancelConfig();portlet.moveAllowed();}}else{config.innerHTML=this.getConfigButton();config.onclick=function(){portlet.config();portlet.moveAllowed();}}
config.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(config);}
if(portlet.helpMode){var help=document.createElement('span');help.className="icons";if(portlet.mode==Light._HELP_MODE){help.innerHTML=this.getCancelHelpButton();help.onclick=function(){portlet.cancelHelp();portlet.moveAllowed();}}else{help.innerHTML=this.getHelpButton();help.onclick=function(){portlet.help();portlet.moveAllowed();}}
help.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(help);}
if(portlet.editMode){var edit=document.createElement('span');edit.className="icons";if(portlet.mode==Light._EDIT_MODE){edit.innerHTML=this.getCancelEditButton();edit.onclick=function(){portlet.cancelEdit();portlet.moveAllowed();}}else{edit.innerHTML=this.getEditButton();edit.onclick=function(){portlet.edit();portlet.moveAllowed();}}
edit.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(edit);}
if(portlet.refreshMode){var refresh=document.createElement('span');refresh.className="icons";refresh.innerHTML=this.getRefreshButton();refresh.onclick=function(){portlet.refresh();portlet.moveAllowed();}
refresh.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(refresh);}
return vButton;}
WindowSkin3.prototype=new WindowSkin2;WindowSkin3.prototype.constructor=WindowSkin3;function WindowSkin3(){WindowSkin2.call(this);this.type="WindowSkin3";}
WindowSkin3.prototype.createPortletHeaderButton=function(portlet){portlet.allowMinimized=false;var vButton=document.createElement('div');vButton.id=Light._PB_PREFIX+portlet.serverId;vButton.className="portlet2-header-button";vButton.style.visibility="hidden";if(portlet.configMode){var config=document.createElement('span');config.className="icons";if(portlet.mode==Light._CONFIG_MODE){config.innerHTML=this.getCancelConfigButton();config.onclick=function(){portlet.cancelConfig();portlet.moveAllowed();}}else{config.innerHTML=this.getConfigButton();config.onclick=function(){portlet.config();portlet.moveAllowed();}}
config.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(config);}
if(portlet.refreshMode){var refresh=document.createElement('span');refresh.className="icons";refresh.innerHTML=this.getRefreshButton();refresh.onclick=function(){portlet.refresh();portlet.moveAllowed();}
refresh.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(refresh);}
return vButton;}
WindowSkin3.prototype.minimize=function(portlet){}
WindowSkin4.prototype=new WindowSkin3;WindowSkin4.prototype.constructor=WindowSkin4;function WindowSkin4(){WindowSkin3.call(this);this.type="WindowSkin4";}
WindowSkin4.prototype.createPortletWindow=function(portlet){var vWindow=document.createElement('div');vWindow.id=Light._PW_PREFIX+portlet.serverId;vWindow.style.position="absolute";vWindow.className="portlet2";vWindow.style.zIndex=++Light.maxZIndex;if(portlet&&portlet.contentBgColor&&portlet.contentBgColor.length>0)
vWindow.style.backgroundColor=portlet.contentBgColor;else if(Light.portal.data.portletWindowTransparent==false&&portlet.transparent==false)
vWindow.style.backgroundColor="#ffffff";vWindow.onmouseover=function(){portlet.window.header.style.visibility="visible";}
vWindow.onmouseout=function(){portlet.window.header.style.visibility="hidden";}
return vWindow;}
WindowSkin4.prototype.createPortletHeader=function(portlet,window){var header=Light.newElement({element:'div',id:Light._PH_PREFIX+portlet.serverId,style:{cssText:'visibility:hidden;position:absolute;right:0px;'}});if(portlet.configMode){var configOn=(portlet.mode==Light._CONFIG_MODE)?true:false;var config=Light.newElement({element:'input',type:'image',style:{cssText:'border:0px;-moz-opacity:1;filter:alpha(opacity=100); height:14; width:14;'},title:(configOn)?Light.getMessage('VIEW_MODE'):Light.getMessage('CONFIG_MODE'),src:Light.getThemePath()+((configOn)?"/images/leave_config_on.gif":"/images/config_on.gif"),onclick:function(){if(configOn)
portlet.cancelConfig();else
portlet.config();}});header.appendChild(config);}
return header;}
WindowSkin4.prototype.createPortletHeaderButton=function(portlet){portlet.allowMinimized=false;return Light.newElement({element:'div',id:Light._PB_PREFIX+portlet.serverId});}
WindowSkin4.prototype.position=function(portlet){this.window.style.width=portlet.width;this.container.style.width=portlet.width;var top=portlet.top;if(typeof portlet.popup!="undefined"){if(!portlet.popup){if(typeof portlet.location!="undefined"&&portlet.location==1)
top=portlet.top+20;}}
setPosition(this.window,portlet.left,top);this.focus(portlet);}
WindowSkin5.prototype=new WindowSkin4;WindowSkin5.prototype.constructor=WindowSkin5;function WindowSkin5(){WindowSkin4.call(this);this.type="WindowSkin5";}
WindowSkin5.prototype.createPortletWindow=function(portlet){return Light.newElement({element:'div',id:Light._PW_PREFIX+portlet.serverId,style:{cssText:'position:absolute;z-index:'+(++Light.maxZIndex)
+((portlet&&portlet.contentBgColor)?';backgroundColor:'+portlet.contentBgColor:((Light.portal.data.portletWindowTransparent==false&&portlet.transparent==false)?';backgroundColor:#ffffff':''))},onmouseover:function(){portlet.window.header.style.visibility='visible';},onmouseout:function(){portlet.window.header.style.visibility='hidden';}});}