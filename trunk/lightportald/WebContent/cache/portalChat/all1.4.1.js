
var Light={Version:'1.4.0',_THEME_ROOT:"/light/theme/",_PR_PREFIX:"prid_",_PT_PREFIX:"ptid_",_VIEW_MODE:"view",_EDIT_MODE:"edit",_HELP_MODE:"help",_CONFIG_MODE:"config",_NORMAL_STATE:"normal",_MINIMIZED_STATE:"minimized",_MAXIMIZED_STATE:"maximized",maxZIndex:1}
L$=function(element){if(arguments.length>1){for(var i=0,elements=[],length=arguments.length;i<length;i++)
elements.push(L$(arguments[i]));return elements;}
return document.getElementById(element);}
L$$=(typeof(document.querySelectorAll)!="undefined")?function(element){if(arguments.length>1){for(var i=0,elements=[],length=arguments.length;i<length;i++){var subs=L$$(arguments[i]);if(subs){for(var j=0,len=subs.length;j<len;j++)
elements.push(subs[j]);}}
return elements;}
return document.querySelectorAll(element);}:function(){return[];}
window.onload=function(){Light.startup()};Light.startup=function(){Light.init();Light.start();}
Light.init=function(){var n=L$('REQUEST_SUFFIX');var s=Light._REQUEST_SUFFIX=(n)?n.title:".lp";Light._CLOSE_CHAT="/closeChat"+s;Light._SHOW_INVITE_LIST="/showInviteList"+s;Light._INVITE_BUDDYS_TO_CHAT="/inviteBuddysToChat"+s;n=L$('CONTEXT_PATH');Light._CONTEXT_PATH=(n)?n.title:"";}
Light.start=function(){Light.portal=new LightPortal();Light.portal.start();enterChattingRoom(window.opener.document.getElementById("chattingRoomId").title,window.opener.document.getElementById("chattingRoomName").title);Light.portal.resize();}
window.onresize=function(){Light.portal.resize();}
window.onbeforeunload=function(){if(Light.portal.confirmClose){var closePortlet=Light.confirm(Light.getMessage('COMMAND_CLOSE_POPUP_PORTLET'));if(!closePortlet){return false;}}
for(var i=0;i<Light.portal.portlets.length;i++){if(Light.portal.portlets[i]!=null)
Light.portal.portlets[i].close(true);}}
function LightPortal(){var screenWidth=document.documentElement.scrollWidth;var barWidth=0;var windowHeight=document.documentElement.scrollHeight;this.layout={containerLeft:0,containerTop:0,containerWidth:screenWidth,bodyLeft:barWidth/2,bodyTop:80,maxLeft:0,maxTop:40,maxWidth:screenWidth-barWidth,maxHeight:windowHeight,minHeight:40,scrollbarWidth:21,footerMarginTop:100,barWidth:barWidth}
this.portlets=[];this.confirmClose=true;}
LightPortal.prototype.start=function(){this.container=this.createPortalContainer();document.body.appendChild(this.container);}
LightPortal.prototype.createPortalContainer=function(){var container=Light.newElement({element:'div',id:'portalContainer',className:'portal-container',style:{position:'absolute',zIndex:++Light.maxZIndex,fontSize:12,width:this.layout.containerWidth,height:this.layout.maxHeight}});container.style.backgroundColor="#ffffff";document.body.style.background="#ffffff";setPosition(container,this.layout.containerLeft,this.layout.containerTop);return container;}
Light.getUserId=function(){return(Light.userId)?Light.userId:Light.getCookie(Light._LOGINED_USER_ID);}
Light.executePortlet=function(id){var portlet=Light.getPortletById(id);var pars="responseId="+id
+"&tabId=0"
+"&portletId="+portlet.serverId
+"&mode="+portlet.mode
+"&isRenderUrl=true"
+"&disablePageRefresh=1";if(portlet.parameter.length>0)
pars=pars+"&"+portlet.parameter;if(portlet.maximized)
pars=pars+"&state=maximized";if(portlet.refreshAction){portlet.refreshAction=false;pars=pars+"&refresh=true";}
if(portlet.state==Light._MINIMIZED_STATE&&!portlet.minimized)
portlet.minimize();else if(portlet.state==Light._MAXIMIZED_STATE&&!portlet.maximized)
portlet.maximize();else{Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts:portlet.allowJS,parameters:pars});}}
Light.getPortletById=function(id){var portletIds=id.split("_");for(var i=0;i<Light.portal.portlets.length;i++){if(Light.portal.portlets[i]!=null&&Light.portal.portlets[i].serverId==portletIds[1])
return Light.portal.portlets[i];}}
Light.getContextPath=function(){var n=L$('CONTEXT_PATH');return(n)?n.title:Light._CONTEXT_PATH;}
Light.getViewTemplate=function(viewId,data){var view="";if(document.getElementById(viewId)!=null)
view=TrimPath.processDOMTemplate(viewId,data);return view;}
Light.getThemePath=function(theme){if(theme==null)theme=L$('theme').title;return Light.getContextPath()+Light._THEME_ROOT+theme;}
Light.setPortletContent=function(responseId,inHTML){var portlet=Light.getPortletById(responseId);if(!portlet.minimized){if(portlet.allowJS){var scriptFragment='(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)';var matchAll=new RegExp(scriptFragment,'img');var scripts=inHTML.match(matchAll);if(scripts!=null){portlet.setContent(inHTML.replace(matchAll,''));for(var i=0;i<scripts.length;i++){var script=scripts[i];var scriptBegin='(?:<script.*?>)';script=script.replace(new RegExp(scriptBegin,'img'),'');var scriptEnd='(?:<\/script>)';script=script.replace(new RegExp(scriptEnd,'img'),'');try{eval(script);}catch(e){}}}else{var scriptFragment2='(?:<script.*?>)';matchAll=new RegExp(scriptFragment2,'im');var scripts=inHTML.match(matchAll);if(scripts!=null){portlet.setContent(inHTML.replace(matchAll,''));for(var i=0;i<scripts.length;i++){var script=scripts[i];var s=document.createElement('script');s.type='text/javascript';var indx=script.indexOf("src=");script=script.substr(indx+5);indx=script.indexOf(".js");script=script.substring(0,indx+3);s.src=script;document.getElementsByTagName('head')[0].appendChild(s);}}else
portlet.setContent(inHTML);}}else
portlet.setContent(inHTML);Light.refreshTextFontSize();}}
Light.refreshTextFontSize=function(){var newSize=12;document.body.style.fontSize=newSize+"px";var elements=L$('a','label','li','td','text','textarea','select');for(var i=0;i<elements.length;i++){elements[i].style.fontSize=newSize+"px";}
elements=null;}
LightPortal.prototype.resize=function(){var screenWidth=document.documentElement.scrollWidth;if(document.all)
screenWidth=screenWidth-Light.portal.layout.scrollbarWidth;var barWidth=0;var windowWidth=screenWidth-barWidth;Light.portal.layout.maxWidth=windowWidth;Light.portal.layout.maxHeight=document.documentElement.scrollHeight;Light.portal.layout.containerWidth=screenWidth;Light.portal.layout.barWidth=barWidth;Light.portal.layout.bodyLeft=barWidth/2;Light.portal.container.style.width=Light.portal.layout.containerWidth;Light.portal.container.style.height=Light.portal.layout.maxHeight;for(var i=0;i<this.portlets.length;i++){if(this.portlets[i]!=null){this.portlets[i].width=Light.portal.layout.maxWidth;this.portlets[i].left=0;this.portlets[i].height=Light.portal.layout.maxHeight;this.portlets[i].refreshPosition();}}}
function PortletChatWindow(position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,refreshAtClient,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter,allowMinimized,allowMaximized,allowPopout){Light.portal.portlets.push(this);this.window=new WindowSkin21();this.parent=null;this.popup=true;this.allowMove=true;this.mode=Light._VIEW_MODE;this.state=Light._NORMAL_STATE;this.tIndex=0;this.serverId=Date.parse(new Date());this.id=Light._PR_PREFIX+this.serverId;this.position=position;this.left=left;this.top=left;this.width=width;this.title=title;this.icon=icon;this.url=url;this.name=request;this.requestUrl=requestUrl;this.closeable=closeable;this.refreshMode=refreshMode;this.editMode=editMode;this.helpMode=helpMode;this.configMode=configMode;this.allowMinimized=true;if(allowMinimized!=null&&!allowMinimized)this.allowMinimized=false;this.allowMaximized=true;if(allowMaximized!=null&&!allowMaximized)this.allowMaximized=false;this.allowPopout=true;if(allowPopout!=null&&!allowPopout)this.allowPopout=false;this.autoRefreshed=autoRefreshed;this.refreshAtClient=refreshAtClient;this.periodTime=periodTime;this.allowJS=allowJS;this.barBgColor=barBgColor;this.barFontColor=barFontColor;this.className="portlet-popup";this.contentBgColor='#EEF6FF';if(contentBgColor!='')this.contentBgColor=contentBgColor;this.parameter=parameter;this.index=0;var height=0;var maxIndex=0;var nullNum=0;this.originalTop=this.top;this.originalWidth=this.width;this.originalLeft=this.left;this.minimized=false;this.maximized=true;this.isPopout=true;this.window.render(this);this.moveable=false;this.autoRefreshWhenMaximized=true;this.autoShow=false;this.opacity=0;this.fade="in";this.myPictureIndex=0;this.myPictures=[];this.autoShowId=null;this.mouseDownX=0;this.mouseDownY=0;this.refreshPosition();this.focus();if(this.autoRefreshed){this.firstTimeAutoRefreshed=true;this.autoRefresh(this);}}
PortletChatWindow.prototype.rememberMode=function(mode){}
PortletChatWindow.prototype.focus=function()
{this.window.focus(this);}
PortletChatWindow.prototype.show=function(){this.window.show(this);}
PortletChatWindow.prototype.hide=function(){this.window.hide(this);}
PortletChatWindow.prototype.moveBegin=function(e)
{}
PortletChatWindow.prototype.moveEnd=function()
{}
PortletChatWindow.prototype.move=function(e)
{}
PortletChatWindow.prototype.startMoveTimer=function(portlet)
{}
PortletChatWindow.prototype.refreshPosition=function()
{this.window.position(this);this.focus();}
PortletChatWindow.prototype.autoRefresh=function(portlet)
{if(portlet.autoRefreshed){if(portlet.firstTimeAutoRefreshed){portlet.firstTimeAutoRefreshed=false;}else{portlet.selfRefresh();}
portlet.timer=setTimeout((function(){portlet.autoRefresh(portlet)}),portlet.periodTime);}}
PortletChatWindow.prototype.selfRefresh=function()
{if(!this.minimized&&(!this.maximized||this.autoRefreshWhenMaximized)){if(this.mode==Light._VIEW_MODE){this.refresh();}}}
PortletChatWindow.prototype.refresh=function(flag)
{var id=this.id;this.refreshAction=true;Light.executePortlet(id);}
PortletChatWindow.prototype.changePosition=function()
{}
PortletChatWindow.prototype.edit=function()
{if(this.editMode){this.mode=Light._EDIT_MODE;var id=this.id;Light.executePortlet(id);this.window.refreshButtons(this);}}
PortletChatWindow.prototype.cancelEdit=function()
{if(this.editMode){this.mode=Light._VIEW_MODE;var id=this.id;Light.executePortlet(id);this.window.refreshButtons(this);}}
PortletChatWindow.prototype.help=function()
{if(this.helpMode){this.mode=Light._HELP_MODE;var id=this.id;Light.executePortlet(id);this.window.refreshButtons(this);}}
PortletChatWindow.prototype.cancelHelp=function()
{if(this.helpMode){this.mode=Light._VIEW_MODE;var id=this.id;Light.executePortlet(id);this.window.refreshButtons(this);}}
PortletChatWindow.prototype.config=function()
{if(this.configMode){this.mode=Light._CONFIG_MODE;this.window.refreshButtons(this);var data={id:this.id,title:this.title,barBgColor:this.barBgColor,barFontColor:this.barFontColor,contentBgColor:this.contentBgColor,textColor:this.textColor,transparent:this.transparent};Light.setPortletContent(data.id,Light.getViewTemplate("configMode.jst",data));this.refreshButtons();}}
PortletChatWindow.prototype.cancelConfig=function()
{if(this.configMode){this.mode=Light._VIEW_MODE;var id=this.id;Light.executePortlet(id);this.window.refreshButtons(this);}}
PortletChatWindow.prototype.moveCancel=function()
{}
PortletChatWindow.prototype.moveAllowed=function()
{}
PortletChatWindow.prototype.moveUp=function(){}
PortletChatWindow.prototype.moveDown=function(){}
PortletChatWindow.prototype.moveLeft=function(){}
PortletChatWindow.prototype.moveRight=function(){}
PortletChatWindow.prototype.minimize=function()
{}
PortletChatWindow.prototype.maximize=function(flag)
{this.windowMaximize(flag);this.refresh();}
PortletChatWindow.prototype.windowMaximize=function(flag)
{this.maximized=!this.maximized;if(!this.maximized){this.state=Light._NORMAL_STATE;this.top=this.originalTop;this.width=this.originalWidth;this.left=this.originalLeft;}else{this.state=Light._MAXIMIZED_STATE;this.left=Light.portal.layout.maxLeft;this.top=Light.portal.layout.maxTop;this.width=Light.portal.layout.maxWidth;}
this.window.maximize(this);}
PortletChatWindow.prototype.popout=function()
{this.isPopout=!this.isPopout;if(!this.isPopout){Light.portal.confirmClose=false;window.opener.popinChatRoom(this.parameter,this.title);window.close();}else{}}
PortletChatWindow.prototype.close=function(flag)
{if(flag==null||!flag){var closePortlet=Light.confirm(Light.getMessage('COMMAND_CLOSE_POPUP_CHAT'));if(!closePortlet){return;}}
if(this.maximized){this.windowMaximize();}
if(this.isPopout)
Light.ajax.sendRequest(Light._CLOSE_CHAT,{parameters:this.parameter});for(var i=0;i<Light.portal.portlets.length;i++){if(Light.portal.portlets[i].serverId==this.serverId)
Light.portal.portlets[i]=null;}
if(this.timer)clearTimeout(this.timer);this.window.close(this);window.close();}
PortletChatWindow.prototype.refreshButtons=function()
{this.window.refreshButtons(this);}
PortletChatWindow.prototype.refreshWindow=function(){this.window.refreshWindow(this);}
PortletChatWindow.prototype.refreshHeader=function(){this.window.refreshHeader(this);}
PortletChatWindow.prototype.refreshWindowTransparent=function(){}
PortletChatWindow.prototype.refreshTextColor=function(){}
PortletChatWindow.prototype.getTop=function(){return this.window.top;}
PortletChatWindow.prototype.getHeight=function(){return this.window.container.clientHeight;}
PortletChatWindow.prototype.setContent=function(content){var objChat=this.window.container.container;objChat.innerHTML=content;setTimeout((function(){if(objChat.scrollHeight>0)
objChat.scrollTop=objChat.scrollHeight;else
objChat.scrollTop=objChat.offsetHeight;objChat=null;}),100);}
function WindowSkin()
{this.left=0;this.top=0;}
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
WindowSkin21.prototype=new WindowSkin;WindowSkin21.prototype.constructor=WindowSkin21;function WindowSkin21()
{this.type="WindowSkin2";}
WindowSkin21.prototype.render=function(portlet)
{this.window=this.createPortletWindow(portlet);this.container=this.createPortletContainer(portlet);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);if(this.headerButton!=null)
this.header.appendChild(this.headerButton);this.window.appendChild(this.header);this.window.appendChild(this.container);Light.portal.container.appendChild(this.window);}
WindowSkin21.prototype.focus=function(portlet)
{var index=++Light.maxZIndex;this.window.style.zIndex=index;}
WindowSkin21.prototype.show=function(portlet)
{this.window.style.visibility="visible";}
WindowSkin21.prototype.hide=function(portlet)
{this.window.style.visibility="hidden";}
WindowSkin21.prototype.position=function(portlet)
{this.window.style.width=portlet.width;this.header.style.width=portlet.width;this.container.style.width=portlet.width;if(document.all){this.window.style.posLeft=portlet.left;this.window.style.posTop=portlet.top;}
else{this.window.style.left=portlet.left;this.window.style.top=portlet.top;}
this.focus(portlet);}
WindowSkin21.prototype.minimize=function(portlet)
{this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin21.prototype.maximize=function(portlet)
{this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin21.prototype.close=function(portlet)
{this.window.removeChild(this.header);this.window.removeChild(this.container);Light.portal.container.removeChild(this.window);}
WindowSkin21.prototype.refreshWindow=function(portlet)
{this.window.removeChild(this.header);this.window.removeChild(this.container);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.container=this.createPortletContainer(portlet);this.header.appendChild(this.headerButton);this.window.appendChild(this.header);this.window.appendChild(this.container);this.position(portlet);}
WindowSkin21.prototype.refreshHeader=function(portlet)
{this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);}
WindowSkin21.prototype.refreshButtons=function(portlet)
{this.window.removeChild(this.header);this.header=this.createPortletHeader(portlet,this);this.headerButton=this.createPortletHeaderButton(portlet);this.header.appendChild(this.headerButton);this.window.insertBefore(this.header,this.container);this.position(portlet);}
WindowSkin21.prototype.createPortletWindow=function(portlet)
{var vWindow=document.createElement('div');vWindow.id="portlet_"+portlet.serverId;vWindow.style.position="absolute";vWindow.className="portlet2";vWindow.style.zIndex=++Light.maxZIndex;if(portlet&&portlet.contentBgColor&&portlet.contentBgColor.length>0)
vWindow.style.backgroundColor=portlet.contentBgColor;else if(Light.portal.data.portletWindowTransparent==false&&portlet.transparent==false)
vWindow.style.backgroundColor="#ffffff";return vWindow;}
WindowSkin21.prototype.createPortletHeader=function(portlet,window)
{var vHeader=document.createElement('div');if(!portlet.minimized){vHeader.className="portlet2-header";if(portlet.barBgColor){vHeader.style.backgroundImage="none";vHeader.style.backgroundColor=portlet.barBgColor;}}else{vHeader.className="portlet2-header-min";}
vHeader.onmousedown=function(e){portlet.focus();}
var vTitle=document.createElement('span');var inner="";if(portlet.icon.length>0){if(portlet.icon.indexOf("/")>=0){if(portlet.icon.substring(0,4)=="http")
inner="<img src='"+portlet.icon+"' height='16' width='16' />";else
inner="<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";}else{var css=portlet.icon.split(" ");var cssParent="";if(css.length>1)cssParent=css[0];inner="<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";}}
if(portlet.url){inner=inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";if(portlet.barFontColor.length>0)
inner=inner+"<font color='"+portlet.barFontColor+"'>";inner=inner+portlet.title;if(portlet.barFontColor.length>0)
inner=inner+"</font>";inner=inner+"</a>";}else
inner=inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";vTitle.innerHTML=inner;vTitle.className="portlet2-header-title";if(portlet.barFontColor.length>0)
vTitle.style.color=portlet.barFontColor;vHeader.appendChild(vTitle);return vHeader;}
WindowSkin21.prototype.createPortletContainer=function(portlet){var vContainer=document.createElement('div');vContainer.id=portlet.id+"_container";vContainer.onmousedown=function(){portlet.focus();}
if(portlet&&portlet.contentBgColor&&portlet.contentBgColor.length>0)
vContainer.style.backgroundColor=portlet.contentBgColor;var vContent=document.createElement('div');vContent.className="chattingBox";vContent.id=portlet.id;vContainer.container=vContent;vContainer.appendChild(vContent);if(!portlet.minimized){var vInput=document.createElement('div');var data={id:portlet.id};vInput.innerHTML=Light.getViewTemplate("chattingInput.jst",data);vContainer.appendChild(vInput);}
return vContainer;}
WindowSkin21.prototype.createPortletHeaderButton=function(portlet)
{var strIcoRefresh="<div title='"+Light.getMessage('REFRESH_PORTLET')+"' class='icons refresh_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoEdit="<div title='"+Light.getMessage('EDIT_MODE')+"' class='icons edit_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoCancelEdit="<div title='"+Light.getMessage('HELP_MODE')+"' class='icons leave_edit_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoHelp="<div title='"+Light.getMessage('HELP_MODE')+"' class='icons help_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoCancelHelp="<div title='"+Light.getMessage('VIEW_MODE')+"'  class='icons leave_help_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoConfig="<div title='"+Light.getMessage('CONFIG_MODE')+"' class='icons config_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoCancelConfig="<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_config_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoMin="<div title='"+Light.getMessage('MINIMIZED')+"' class='icons min_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoRestore="<div title='"+Light.getMessage('RESTORE')+"' class='icons restore_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoMax="<div title='"+Light.getMessage('MAXIMIZED')+"' class='icons max_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoPop="<div title='"+Light.getMessage('POPIN')+"' class='icons pop_in'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var strIcoCls="<div title='"+Light.getMessage('CLOSE')+"' class='icons close_on'"
+" style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";var vButton=document.createElement('div');vButton.className="portlet2-header-button";if(portlet.closeable){var clsA=document.createElement('span');clsA.className="icons";clsA.innerHTML=strIcoCls;clsA.onclick=function(){portlet.close();portlet.moveAllowed();}
clsA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(clsA);}
if(portlet.allowPopout){var popA=document.createElement('span');popA.className="icons";if(portlet.isPopout){popA.innerHTML=strIcoPop;}
popA.onclick=function(){portlet.popout();portlet.moveAllowed();}
popA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(popA);}
if(portlet.allowMaximized){var maxA=document.createElement('span');maxA.className="icons";if(portlet.maximized){maxA.innerHTML=strIcoRestore;}else{maxA.innerHTML=strIcoMax;}
maxA.onclick=function(){portlet.maximize();portlet.moveAllowed();}
maxA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(maxA);}
if(portlet.allowMinimized){var minA=document.createElement('span');minA.className="icons";if(portlet.minimized){minA.innerHTML=strIcoRestore;}else{minA.innerHTML=strIcoMin;}
minA.onclick=function(){portlet.minimize();portlet.moveAllowed();}
minA.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(minA);}
if(portlet.configMode){var config=document.createElement('span');config.className="icons";if(portlet.mode==Light._CONFIG_MODE){config.innerHTML=strIcoCancelConfig;config.onclick=function(){portlet.cancelConfig();portlet.moveAllowed();}}else{config.innerHTML=strIcoConfig;config.onclick=function(){portlet.config();portlet.moveAllowed();}}
config.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(config);}
if(portlet.helpMode){var help=document.createElement('span');help.className="icons";if(portlet.mode==Light._HELP_MODE){help.innerHTML=strIcoCancelHelp;help.onclick=function(){portlet.cancelHelp();portlet.moveAllowed();}}else{help.innerHTML=strIcoHelp;help.onclick=function(){portlet.help();portlet.moveAllowed();}}
help.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(help);}
if(portlet.editMode){var edit=document.createElement('span');edit.className="icons";if(portlet.mode==Light._EDIT_MODE){edit.innerHTML=strIcoCancelEdit;edit.onclick=function(){portlet.cancelEdit();portlet.moveAllowed();}}else{edit.innerHTML=strIcoEdit;edit.onclick=function(){portlet.edit();portlet.moveAllowed();}}
edit.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(edit);}
if(portlet.refreshMode){var refresh=document.createElement('span');refresh.className="icons";refresh.innerHTML=strIcoRefresh;refresh.onclick=function(){portlet.refresh();portlet.moveAllowed();}
refresh.onmousedown=function(){portlet.moveCancel();}
vButton.appendChild(refresh);}
return vButton;}
enterChattingRoom=function(parameter,title){var portlet=new PortletChatWindow(12,0,300,title,"icons chat","","chattingPortlet",Light.getContextPath()+"/chattingPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,false,5000,true,'','','',parameter,false,false);portlet.refresh();document.forms['form_'+portlet.id]['chat'].focus();}
keyDownChat=function(e,id){var KeyID;if(window.event){keyID=window.event.keyCode;}else{keyID=e.which;}
if(keyID==13){sendChatMessage(id);}
return!(keyID==13);}
sendChatMessage=function(id){var chat=document.forms['form_'+id]['chat'].value;var portlet=Light.getPortletById(id);var pars="responseId="+id
+"&tabId=0"
+"&portletId="+portlet.serverId
+"&action=send"
+"&"+portlet.parameter
+"&chat="+encodeURIComponent(chat);Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts:false,parameters:pars});document.forms['form_'+id]['chat'].value="";document.forms['form_'+id]['chat'].focus();}
showInviteList=function(id){var portlet=Light.getPortletById(id);var pars=portlet.parameter;Light.ajax.sendRequest(Light._SHOW_INVITE_LIST,{parameters:pars,onSuccess:showInviteListHandler});}
showInviteListHandler=function(t){var content=t.responseText;var data={title:Light.getMessage('CLOSE'),ok:Light.getMessage('BUTTON_OK'),content:content,popupName:'inviteList'};createTopPopupDiv('inviteList','inviteList.jst',300,data,null);}
inviteBuddysToChat=function(form){var chattingId=form['chattingId'].value;var userIds="";for(var i=0;i<form.elements.length;i++){if(form.elements[i].type=="checkbox"&&form.elements[i].checked&&!form.elements[i].disabled){userIds+=form.elements[i].value+",";}}
var params="chattingId="+chattingId+"&userIds="+userIds;Light.ajax.sendRequest(Light._INVITE_BUDDYS_TO_CHAT,{parameters:params});}
createTopPopupDiv=function(name,templateName,width,data,e,id,flag){var old=document.getElementById(name);if(old!=null&&flag!=null&&flag)
hideTopPopupDiv(name);else if(old!=null)
return;var vPopupDiv=document.createElement('div');vPopupDiv.id=name;vPopupDiv.style.position="absolute";vPopupDiv.className="portlet-popup";if(width!=null)
vPopupDiv.style.width=width;vPopupDiv.innerHTML=TrimPath.processDOMTemplate(templateName,data);var x=getMousePositionX(e)+10;var y=getMousePositionY(e);if(y<=0&&id!=null){portlet=Light.getPortletById(id);if(portlet!=null){x=portlet.left+100;y=portlet.top;}}
if(parseInt(x)+parseInt(width)>document.body.clientWidth)
x=parseInt(x)-parseInt(width);setPosition(vPopupDiv,x,y);var zIndex=++Light.maxZIndex+1000;vPopupDiv.style.zIndex=zIndex;document.body.appendChild(vPopupDiv);vPopupDiv=null;}
function hideTopPopupDiv(name){var old=document.getElementById(name);if(old!=null){document.body.removeChild(old);document.body.onclick=null;return true;}
return false;}
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