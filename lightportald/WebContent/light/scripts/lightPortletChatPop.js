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
//------------------------------------------------------------ lightPortletChatPop.js
var Light = {
  		    Version: '1.4.0'	
  		   ,_THEME_ROOT:"/light/theme/"   	   
  		   ,_PR_PREFIX: "prid_"
  		   ,_PT_PREFIX: "ptid_"
  		   ,_VIEW_MODE: "view"
  		   ,_EDIT_MODE: "edit"
  		   ,_HELP_MODE: "help"
  		   ,_CONFIG_MODE: "config"
  		   ,_NORMAL_STATE: "normal"
  		   ,_MINIMIZED_STATE: "minimized"
  		   ,_MAXIMIZED_STATE: "maximized"
  		   ,maxZIndex: 1  		   
}

window.onload = function(){
	Light.startup()
};

Light.startup = function(){
	Light.init();	
	Light.start();
}

Light.init = function(){
	var n = L$('REQUEST_SUFFIX');
	var s = Light._REQUEST_SUFFIX = (n) ? n.title : ".lp";
	Light._CLOSE_CHAT = "/closeChat"+s;
  	Light._SHOW_INVITE_LIST = "/showInviteList"+s;
    Light._INVITE_BUDDYS_TO_CHAT = "/inviteBuddysToChat"+s;
    n = L$('CONTEXT_PATH'); 
    Light._CONTEXT_PATH = (n) ? n.title : "";
}
Light.start = function(){ 
	Light.portal = new LightPortal();  
	Light.portal.start();
	enterChattingRoom(window.opener.document.getElementById("chattingRoomId").title,window.opener.document.getElementById("chattingRoomName").title);
	Light.portal.resize();
}
window.onresize = function(){  
   Light.portal.resize();              
}
window.onbeforeunload= function(){ 
	if(Light.portal.confirmClose){ 
	    var closePortlet = Light.confirm(Light.getMessage('COMMAND_CLOSE_POPUP_PORTLET'));
		if (!closePortlet){// user cancelled close closePortlet
			return false;
		}
	}
   for(var i=0;i<Light.portal.portlets.length;i++){
       if(Light.portal.portlets[i] != null)
          Light.portal.portlets[i].close(true);
   }       
}

function LightPortal() {
  var screenWidth = document.documentElement.scrollWidth;
  var barWidth = 0;  
  var windowHeight = document.documentElement.scrollHeight;  
  this.layout={
	  	containerLeft: 0,
	  	containerTop : 0, 
		containerWidth: screenWidth, 
	  	bodyLeft: barWidth / 2,
	  	bodyTop : 80,
	   	maxLeft  : 0,
    	maxTop   : 40, 
    	maxWidth : screenWidth - barWidth,
    	maxHeight : windowHeight,
    	minHeight : 40,
        scrollbarWidth : 21,
        footerMarginTop : 100,
        barWidth : barWidth    
  } 
  this.portlets = []; 
  this.confirmClose = true;
}
LightPortal.prototype.start = function(){
  this.container = this.createPortalContainer();
  document.body.appendChild(this.container);     
}
LightPortal.prototype.createPortalContainer = function (){ 
	 var container = Light.newElement({
	   					element:'div',
	   					id:'portalContainer',
	   					className:'portal-container',
	   					style:{position : 'absolute',
	   						   zIndex : ++Light.maxZIndex,
	   						   fontSize : 12,
	   						   width : this.layout.containerWidth,
	   						   height : this.layout.maxHeight}
   					});     
   container.style.backgroundColor= "#ffffff";
   document.body.style.background= "#ffffff";     
   setPosition(container,this.layout.containerLeft,this.layout.containerTop);
   return container;   
}
Light.getUserId = function(){
	return(Light.userId) ? Light.userId : Light.getCookie(Light._LOGINED_USER_ID);
}
Light.executePortlet = function(id) {  
  var portlet = Light.getPortletById(id);  
  var pars = "responseId="+id
	  	    +"&tabId=0"
	  	    +"&portletId="+portlet.serverId
            +"&mode="+portlet.mode
            +"&isRenderUrl=true"
            +"&disablePageRefresh=1";
  if(portlet.parameter.length > 0)
      	pars = pars+"&"+ portlet.parameter;
  if(portlet.maximized)
     pars = pars+"&state=maximized";
  if(portlet.refreshAction){
      portlet.refreshAction = false;
      pars =  pars+"&refresh=true";
  }  
  if(portlet.state == Light._MINIMIZED_STATE && !portlet.minimized)
     portlet.minimize();
  else if(portlet.state == Light._MAXIMIZED_STATE && !portlet.maximized)
     portlet.maximize();
  else{
     Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});       
  }
}
Light.getPortletById = function(id) {
  var portletIds = id.split("_");
  for(var i=0;i<Light.portal.portlets.length;i++){
       if(Light.portal.portlets[i] != null && Light.portal.portlets[i].serverId == portletIds[1])
          return Light.portal.portlets[i];
  }
}
Light.getContextPath = function(){
	var n = L$('CONTEXT_PATH'); 
    return (n) ? n.title : Light._CONTEXT_PATH;
}
Light.getViewTemplate = function(viewId,data){
   var view="";
   if(document.getElementById(viewId) != null)
     view=TrimPath.processDOMTemplate(viewId, data);
   return view;
}
Light.getThemePath = function(theme){
	if(theme == null) theme = L$('theme').title;
	return Light.getContextPath()+Light._THEME_ROOT+theme;
}
Light.setPortletContent= function(responseId,inHTML){
   var portlet = Light.getPortletById(responseId);   
   if(!portlet.minimized){
	   if(portlet.allowJS){
	       var scriptFragment= '(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)';
	       var matchAll = new RegExp(scriptFragment, 'img');
	       var scripts = inHTML.match(matchAll);
	       if(scripts != null){
	       	   portlet.setContent(inHTML.replace(matchAll,''));	
			   for(var i=0;i<scripts.length;i++){
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
				   for(var i=0;i<scripts.length;i++){
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
  }
}
Light.refreshTextFontSize = function (){ 
    var size = 12; 
    $('#portalContainer').find('*').css({fontSize:size+"px"});	    
}
LightPortal.prototype.resize = function (){
	var screenWidth = document.documentElement.scrollWidth;
	if(document.all)
		screenWidth = screenWidth - Light.portal.layout.scrollbarWidth;
	var barWidth = 0; 
  	var windowWidth = screenWidth - barWidth;
	Light.portal.layout.maxWidth = windowWidth;
	Light.portal.layout.maxHeight = document.documentElement.scrollHeight;
	Light.portal.layout.containerWidth = screenWidth;
	Light.portal.layout.barWidth = barWidth;
	Light.portal.layout.bodyLeft= barWidth / 2;
	Light.portal.container.style.width = Light.portal.layout.containerWidth;
	Light.portal.container.style.height = Light.portal.layout.maxHeight;
	for(var i=0;i<this.portlets.length;i++) {   
   	  if(this.portlets[i] != null){          	
      	this.portlets[i].width = Light.portal.layout.maxWidth;
	    this.portlets[i].left = 0;
	    this.portlets[i].height = Light.portal.layout.maxHeight;
	    this.portlets[i].refreshPosition();
      }
   }
}
function PortletChatWindow (position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,refreshAtClient,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter,allowMinimized,allowMaximized,allowPopout) {
   Light.portal.portlets[Light.portal.portlets.size()]=this;
   this.window = new WindowSkin21();
   this.parent = null;
   this.popup= true;
   this.allowMove = true;
   this.mode = Light._VIEW_MODE;
   this.state = Light._NORMAL_STATE;
   this.tIndex = 0;   
   this.serverId = Date.parse(new Date());
   this.id = Light._PR_PREFIX+this.serverId;
   this.position = position;
   this.left = left;
   this.top  = left;
   this.width = width;
   this.title = title;
   this.icon = icon;
   this.url = url;
   this.name = request;
   this.requestUrl = requestUrl;
   this.closeable = closeable;
   this.refreshMode = refreshMode;
   this.editMode = editMode;
   this.helpMode = helpMode;
   this.configMode = configMode;
   this.allowMinimized = true; 
   if(allowMinimized != null && !allowMinimized) this.allowMinimized = false;
   this.allowMaximized = true;
   if(allowMaximized != null && !allowMaximized) this.allowMaximized = false;
   this.allowPopout = true;
   if(allowPopout != null && !allowPopout) this.allowPopout = false;
   this.autoRefreshed = autoRefreshed;
   this.refreshAtClient = refreshAtClient;
   this.periodTime = periodTime;
   this.allowJS = allowJS;
   this.barBgColor = barBgColor;
   this.barFontColor = barFontColor;   
   this.className = "portlet-popup";
   this.contentBgColor ='#EEF6FF';
   if(contentBgColor !='') this.contentBgColor = contentBgColor;
   this.parameter = parameter;   
   this.index = 0;
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;
   this.originalTop = this.top;
   this.originalWidth = this.width;
   this.originalLeft = this.left;
   this.minimized = false;
   this.maximized = true;   
   this.isPopout = true;
   this.window.render(this);
   this.moveable = false;
   this.autoRefreshWhenMaximized = true;
   this.autoShow = false;
   this.opacity = 0;
   this.fade="in";
   this.myPictureIndex = 0;
   this.myPictures= [];
   this.autoShowId = null;   
   this.mouseDownX = 0;
   this.mouseDownY = 0;
   this.refreshPosition();   
   this.focus();
   if(this.autoRefreshed){
   	  this.firstTimeAutoRefreshed = true;
   	  this.autoRefresh(this);
   }
 }  
PortletChatWindow.prototype.rememberMode = function(mode){  
}
PortletChatWindow.prototype.focus = function()
{
    this.window.focus(this);
}
PortletChatWindow.prototype.show = function(){
 	this.window.show(this);
}
PortletChatWindow.prototype.hide = function(){
 	this.window.hide(this);
}
PortletChatWindow.prototype.moveBegin = function(e)
{  
}
PortletChatWindow.prototype.moveEnd = function()
{         
}
PortletChatWindow.prototype.move = function(e)
{ 	
}
PortletChatWindow.prototype.startMoveTimer = function(portlet)
{
}
PortletChatWindow.prototype.refreshPosition = function()
{ 
	this.window.position(this);
    this.focus();
}
PortletChatWindow.prototype.autoRefresh = function(portlet)
{
   if(portlet.autoRefreshed){       
       if(portlet.firstTimeAutoRefreshed){
       	  portlet.firstTimeAutoRefreshed = false;
       }else{
       	  portlet.selfRefresh();
       }
       portlet.timer = setTimeout((function() {portlet.autoRefresh (portlet)}), portlet.periodTime);
    }
}
PortletChatWindow.prototype.selfRefresh = function()
{   	
	if(!this.minimized && (!this.maximized || this.autoRefreshWhenMaximized)){
		if(this.mode == Light._VIEW_MODE){
			this.refresh();
		}
	}
} 
PortletChatWindow.prototype.refresh = function(flag)
{ 
  var id = this.id;
  this.refreshAction = true;
  Light.executePortlet(id);
}
PortletChatWindow.prototype.changePosition = function()
{   
}
PortletChatWindow.prototype.edit = function()
 {
   if(this.editMode){ 
        this.mode= Light._EDIT_MODE;
        var id = this.id;
        Light.executePortlet(id);
        this.window.refreshButtons(this);
    }
}
PortletChatWindow.prototype.cancelEdit = function()
 {
   if(this.editMode){
    this.mode= Light._VIEW_MODE;
    var id = this.id;
    Light.executePortlet(id);
	this.window.refreshButtons(this);
   }
}
PortletChatWindow.prototype.help = function()
 {
   if(this.helpMode){ 
        this.mode = Light._HELP_MODE;   
        var id = this.id;
        Light.executePortlet(id);
    	this.window.refreshButtons(this);
    }
} 
PortletChatWindow.prototype.cancelHelp = function()
 {
   if(this.helpMode){
    this.mode = Light._VIEW_MODE;
    var id = this.id;
    Light.executePortlet(id);
    this.window.refreshButtons(this);
   }
}
PortletChatWindow.prototype.config = function()
 {
   if(this.configMode){ 
        this.mode = Light._CONFIG_MODE;   
    	this.window.refreshButtons(this);
    	var data = {	     
		     id : this.id,
		     title : this.title,
		     barBgColor : this.barBgColor,
		     barFontColor :this.barFontColor,
		     contentBgColor : this.contentBgColor,
		     textColor : this.textColor,
		     transparent : this.transparent
		   };
		   
	   Light.setPortletContent(data.id,Light.getViewTemplate("configMode.jst",data));
	   this.refreshButtons(); 
    }
}
PortletChatWindow.prototype.cancelConfig = function()
 {
   if(this.configMode){
    this.mode = Light._VIEW_MODE;
    var id = this.id;
    Light.executePortlet(id);
    this.window.refreshButtons(this);
   }
}
PortletChatWindow.prototype.moveCancel = function()
{    
}
PortletChatWindow.prototype.moveAllowed = function()
{    
}
PortletChatWindow.prototype.moveUp = function(){}
PortletChatWindow.prototype.moveDown = function(){}
PortletChatWindow.prototype.moveLeft = function(){}
PortletChatWindow.prototype.moveRight = function(){}
PortletChatWindow.prototype.minimize = function()
{ 
}
PortletChatWindow.prototype.maximize = function(flag)
{ 
   this.windowMaximize(flag);
   this.refresh(); 
}
PortletChatWindow.prototype.windowMaximize = function(flag)
 { 
   this.maximized = !this.maximized; 
   if(!this.maximized){  
      this.state = Light._NORMAL_STATE;         
      this.top  = this.originalTop; 	
	  this.width = this.originalWidth;
      this.left = this.originalLeft;     	  
   }else{        
        this.state = Light._MAXIMIZED_STATE;       
   	    this.left = Light.portal.layout.maxLeft;
   	    this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;             	
   }        
   this.window.maximize(this);  
   
 } 
PortletChatWindow.prototype.popout = function()
{ 
	this.isPopout = !this.isPopout;
	if(!this.isPopout){
		Light.portal.confirmClose = false;
		window.opener.popinChatRoom(this.parameter,this.title);
		window.close();
	}else{
	
	}
}
PortletChatWindow.prototype.close = function(flag)
{ 
	if(flag == null || !flag){
		var closePortlet = Light.confirm(Light.getMessage('COMMAND_CLOSE_POPUP_CHAT'));
		if (!closePortlet){ // user cancelled close closePortlet		
			return;
		}
	}
	if(this.maximized){
	    this.windowMaximize();
	}
	if(this.isPopout)
		Light.ajax.sendRequest(Light._CLOSE_CHAT,{parameters:this.parameter}); 
	for(var i=0;i<Light.portal.portlets.length;i++){
       if(Light.portal.portlets[i].serverId == this.serverId)
          Light.portal.portlets[i] = null;
    }
    if(this.timer) clearTimeout(this.timer);
	this.window.close(this);	
	window.close();
} 
PortletChatWindow.prototype.refreshButtons = function()
{
	this.window.refreshButtons(this);
}
PortletChatWindow.prototype.refreshWindow= function () { 
	this.window.refreshWindow(this);
}
PortletChatWindow.prototype.refreshHeader= function () {
 	this.window.refreshHeader(this);
}
PortletChatWindow.prototype.refreshWindowTransparent = function () {}
PortletChatWindow.prototype.refreshTextColor = function () {}
PortletChatWindow.prototype.getTop = function(){
   return this.window.top;
}
PortletChatWindow.prototype.getHeight = function(){
   return this.window.container.clientHeight;
}
PortletChatWindow.prototype.setContent = function(content){
	var objChat = this.window.container.container;
	objChat.innerHTML = content;
	setTimeout((function() {
		if(objChat.scrollHeight > 0)
			objChat.scrollTop = objChat.scrollHeight;
		else
			objChat.scrollTop = objChat.offsetHeight;
		objChat = null;
	}), 100);	
}
//------------------------------------------------------------------------------------------------------ WindowSkinChat.js 
function WindowSkin()     
{
	this.left = 0;
	this.top = 0;
}
WindowSkin.prototype.render= function(portlet){}
WindowSkin.prototype.focus = function(portlet){}
WindowSkin.prototype.show = function(portlet){}
WindowSkin.prototype.hide = function(portlet){}
WindowSkin.prototype.position = function(portlet){}
WindowSkin.prototype.minimize = function(portlet){}
WindowSkin.prototype.maximize = function(portlet){}
WindowSkin.prototype.close = function(portlet){}
WindowSkin.prototype.refreshWindow= function (portlet){}
WindowSkin.prototype.refreshHeader= function (portlet) {}
WindowSkin.prototype.refreshButtons = function(portlet){}
WindowSkin.prototype.setContent = function(content){
	this.container.innerHTML = content;	
}
WindowSkin21.prototype = new WindowSkin;       // Define sub-class
WindowSkin21.prototype.constructor = WindowSkin21;

function WindowSkin21()
{
	this.type = "WindowSkin2";
}
WindowSkin21.prototype.render= function(portlet)
{
	this.window = this.createPortletWindow(portlet);
	this.container = this.createPortletContainer(portlet); 
	this.header = this.createPortletHeader(portlet,this);     
	this.headerButton = this.createPortletHeaderButton(portlet);          		
	if(this.headerButton != null)
		this.header.appendChild(this.headerButton);     
	this.window.appendChild(this.header); 
	this.window.appendChild(this.container); 
	Light.portal.container.appendChild(this.window);
}

WindowSkin21.prototype.focus = function(portlet)
 {
    var index = ++Light.maxZIndex;
    this.window.style.zIndex= index;
 }
 
WindowSkin21.prototype.show = function(portlet)
{
 	this.window.style.visibility = "visible";
 }
 
WindowSkin21.prototype.hide = function(portlet)
{
 	this.window.style.visibility = "hidden";  
 }
 
WindowSkin21.prototype.position = function(portlet)
{ 
	this.window.style.width = portlet.width; 
	this.header.style.width = portlet.width; 
	this.container.style.width = portlet.width; 
	if (document.all) {	
		this.window.style.posLeft = portlet.left;
		this.window.style.posTop = portlet.top;       	   
	}    
	else {        
		this.window.style.left = portlet.left;
		this.window.style.top = portlet.top;
	}
	this.focus(portlet);
 }
 
WindowSkin21.prototype.minimize = function(portlet)
 {     
  this.window.removeChild(this.header);
  this.header = this.createPortletHeader(portlet,this);
  this.headerButton = this.createPortletHeaderButton(portlet);
  this.header.appendChild(this.headerButton);
  this.window.insertBefore(this.header, this.container);  

  this.position(portlet);  

 } 

WindowSkin21.prototype.maximize = function(portlet)
 {     
   this.window.removeChild(this.header);
   this.header = this.createPortletHeader(portlet,this);
   this.headerButton = this.createPortletHeaderButton(portlet);
   this.header.appendChild(this.headerButton);
   this.window.insertBefore(this.header, this.container);  
   
   this.position(portlet);  
 }
 
WindowSkin21.prototype.close = function(portlet)
 {   
	this.window.removeChild(this.header);
	this.window.removeChild(this.container);
	Light.portal.container.removeChild(this.window);
 } 
 
WindowSkin21.prototype.refreshWindow= function (portlet) 
{ 
   this.window.removeChild(this.header);
   this.window.removeChild(this.container);
   this.header = this.createPortletHeader(portlet,this);
   this.headerButton = this.createPortletHeaderButton(portlet);
   this.container = this.createPortletContainer(portlet);
   this.header.appendChild(this.headerButton); 
   this.window.appendChild(this.header); 
   this.window.appendChild(this.container); 
   this.position(portlet);
 }
 
WindowSkin21.prototype.refreshHeader= function (portlet) 
{
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(portlet,this);
	this.headerButton = this.createPortletHeaderButton(portlet);
	this.header.appendChild(this.headerButton); 
	this.window.insertBefore(this.header, this.container);  
}

WindowSkin21.prototype.refreshButtons = function(portlet)
{	
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(portlet,this);
	this.headerButton = this.createPortletHeaderButton(portlet);
	this.header.appendChild(this.headerButton);      
	this.window.insertBefore(this.header, this.container);
	this.position(portlet);
 }
 
WindowSkin21.prototype.createPortletWindow = function (portlet) 
{
	var vWindow = document.createElement('div');
	vWindow.id = "portlet_"+portlet.serverId;
	vWindow.style.position = "absolute";
	vWindow.className = "portlet2";     
	vWindow.style.zIndex= ++Light.maxZIndex;
	if(portlet && portlet.contentBgColor && portlet.contentBgColor.length > 0)
   	  vWindow.style.backgroundColor = portlet.contentBgColor;
    else if(Light.portal.data.portletWindowTransparent == false && portlet.transparent == false)
      vWindow.style.backgroundColor = "#ffffff";
	return vWindow;
}
  
WindowSkin21.prototype.createPortletHeader= function (portlet,window) 
{
   var vHeader = document.createElement('div');
   if(!portlet.minimized){
		vHeader.className = "portlet2-header";
		if(portlet.barBgColor){
	        vHeader.style.backgroundImage="none";
	   	 	vHeader.style.backgroundColor = portlet.barBgColor;   
	   }
   }else{
   		vHeader.className = "portlet2-header-min";
   }
   vHeader.onmousedown = function(e){
      		portlet.focus();   
   }       
   var vTitle = document.createElement('span');
   var inner = "";
   if(portlet.icon.length > 0){
   	  if(portlet.icon.indexOf("/") >= 0){
	      if(portlet.icon.substring(0,4) == "http")
	      	inner = "<img src='"+portlet.icon+"' height='16' width='16' />";
	      else
	        inner = "<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";
	  }else{
	  		var css = portlet.icon.split(" ");
	  		var cssParent ="";
	  		if(css.length > 1) cssParent = css[0];
	  		inner = "<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";
	  }
   }
   if(portlet.url){
      inner = inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";
      if(portlet.barFontColor.length > 0)
         inner = inner+"<font color='"+portlet.barFontColor+"'>";
      inner = inner+portlet.title;
      if(portlet.barFontColor.length > 0)
         inner = inner+"</font>";
      inner = inner+"</a>";
   }else
      inner = inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";
   vTitle.innerHTML = inner;
   vTitle.className = "portlet2-header-title";
   if(portlet.barFontColor.length > 0)
   	  vTitle.style.color = portlet.barFontColor;       
   vHeader.appendChild(vTitle);
   
   return vHeader;
  }

WindowSkin21.prototype.createPortletContainer = function (portlet) {
   var vContainer = document.createElement('div');
   vContainer.id = portlet.id+"_container";
   vContainer.onmousedown = function(){
      portlet.focus();   
   }
   if(portlet && portlet.contentBgColor && portlet.contentBgColor.length > 0)
   	  vContainer.style.backgroundColor = portlet.contentBgColor; 
   var vContent = document.createElement('div');
   vContent.className="chattingBox";
   vContent.id = portlet.id;
   vContainer.container = vContent;
   vContainer.appendChild(vContent);
   if(!portlet.minimized){
	   var vInput = document.createElement('div');
	   var data = {        
  	 	id : portlet.id
       };      
	   vInput.innerHTML=Light.getViewTemplate("chattingInput.jst",data);
	   vContainer.appendChild(vInput);
	}
   return vContainer;
}
WindowSkin21.prototype.createPortletHeaderButton= function (portlet) 
  {   
   var strIcoRefresh="<div title='"+Light.getMessage('REFRESH_PORTLET')+"' class='icons refresh_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		   
   var strIcoEdit="<div title='"+Light.getMessage('EDIT_MODE')+"' class='icons edit_on'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		   
   var strIcoCancelEdit="<div title='"+Light.getMessage('HELP_MODE')+"' class='icons leave_edit_on'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		                
   var strIcoHelp="<div title='"+Light.getMessage('HELP_MODE')+"' class='icons help_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		   
   var strIcoCancelHelp="<div title='"+Light.getMessage('VIEW_MODE')+"'  class='icons leave_help_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
   
   var strIcoConfig="<div title='"+Light.getMessage('CONFIG_MODE')+"' class='icons config_on'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
			                
   var strIcoCancelConfig="<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_config_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	                        
                
   var strIcoMin= "<div title='"+Light.getMessage('MINIMIZED')+"' class='icons min_on'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		   
   var strIcoRestore= "<div title='"+Light.getMessage('RESTORE')+"' class='icons restore_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		                
   var strIcoMax= "<div title='"+Light.getMessage('MAXIMIZED')+"' class='icons max_on'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
		
   var strIcoPop= "<div title='"+Light.getMessage('POPIN')+"' class='icons pop_in'"
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
                                
   var strIcoCls= "<div title='"+Light.getMessage('CLOSE')+"' class='icons close_on'" 
		+ " style='this.style.MozOpacity=1;this.filters.alpha.opacity=100'></div>";	
                
   var vButton = document.createElement('div');
   vButton.className = "portlet2-header-button";
	   
   if(portlet.closeable){
	   var clsA = document.createElement('span');
	   clsA.className = "icons";
	   clsA.innerHTML = strIcoCls;
	   clsA.onclick = function(){
	     portlet.close();
           portlet.moveAllowed();
	   }
       clsA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(clsA);
   } 
   if(portlet.allowPopout){
	   var popA = document.createElement('span');
	   popA.className = "icons";
	   if(portlet.isPopout){	   	  
	      popA.innerHTML = strIcoPop;
	   }
	   popA.onclick = function(){
	     	portlet.popout();
            portlet.moveAllowed();
	   }
       popA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(popA);
   }   
   if(portlet.allowMaximized){
	   var maxA = document.createElement('span');
	   maxA.className = "icons";
	   if(portlet.maximized){
	   	  maxA.innerHTML = strIcoRestore;
	   }else{
	      maxA.innerHTML = strIcoMax;
	   }
	   maxA.onclick = function(){
	     portlet.maximize();
	     portlet.moveAllowed();
	   }
	   maxA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(maxA);
   }
   if(portlet.allowMinimized){
	   var minA = document.createElement('span');
	   minA.className = "icons";
	   if(portlet.minimized){
		   minA.innerHTML = strIcoRestore;	   
	   }else{
	       minA.innerHTML = strIcoMin;	   
	   }
	   minA.onclick = function(){
	     portlet.minimize();
	     portlet.moveAllowed();
	   }
	   minA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(minA);
   }
   if(portlet.configMode){
	   var config = document.createElement('span');	
	   config.className = "icons";   
	   if(portlet.mode == Light._CONFIG_MODE){
	       config.innerHTML = strIcoCancelConfig;
		   config.onclick = function(){
		     portlet.cancelConfig();
                 portlet.moveAllowed();
		   }
	   }else{
	       config.innerHTML = strIcoConfig;
	   	   config.onclick = function(){
		     portlet.config();
                 portlet.moveAllowed();
		   }	   
	   }
         config.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(config);
   } 
   if(portlet.helpMode){
	   var help = document.createElement('span');	
	   help.className = "icons";   
	   if(portlet.mode == Light._HELP_MODE){
	       help.innerHTML = strIcoCancelHelp;
		   help.onclick = function(){
		     portlet.cancelHelp();
                 portlet.moveAllowed();
		   }
	   }else{
	       help.innerHTML = strIcoHelp;
	   	   help.onclick = function(){
		     portlet.help();
                 portlet.moveAllowed();
		   }	   
	   }
           help.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(help);
   }
   if(portlet.editMode){
       var edit = document.createElement('span');
       edit.className = "icons";
       if(portlet.mode == Light._EDIT_MODE){
       	   edit.innerHTML = strIcoCancelEdit;
       	   edit.onclick = function(){
		     portlet.cancelEdit();
		     portlet.moveAllowed();
		   }
       }else{		   
		   edit.innerHTML = strIcoEdit;
		   edit.onclick = function(){
		     portlet.edit();
                 portlet.moveAllowed();
		   }
	   }
           edit.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(edit);
   }
   if(portlet.refreshMode){
	   var refresh = document.createElement('span');
	   refresh.className = "icons";
	   refresh.innerHTML = strIcoRefresh;
	   refresh.onclick = function(){
	       portlet.refresh();
           portlet.moveAllowed();
	   }     
       refresh.onmousedown = function(){ portlet.moveCancel();}    
	   vButton.appendChild(refresh);
   }
   return vButton;
}
//------------------------------------------------------------ PortletChatWindow.js
enterChattingRoom = function (parameter,title) {  
	var	portlet = new PortletChatWindow(12,0,300,title,"icons chat","","chattingPortlet",Light.getContextPath()+"/chattingPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,false,5000,true,'','','',parameter,false,false); 
    portlet.refresh();
    document.forms['form_'+portlet.id]['chat'].focus();
}
keyDownChat  = function (e,id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){   
	    sendChatMessage(id);    
	  }
	  return !(keyID == 13);
}
sendChatMessage = function (id) {  
   var chat  = document.forms['form_'+id]['chat'].value;
   var portlet = Light.getPortletById(id);
    var pars = "responseId="+id
  	    +"&tabId=0"
  	    +"&portletId="+portlet.serverId
        +"&action=send"
        +"&"+portlet.parameter	
	    +"&chat="+encodeURIComponent(chat); 
    Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: false, parameters: pars}); 			         
   document.forms['form_'+id]['chat'].value ="";
   document.forms['form_'+id]['chat'].focus();
}
showInviteList = function(id){    
    var portlet = Light.getPortletById(id);
    var pars = portlet.parameter;//"chattingId="+id;
	Light.ajax.sendRequest(Light._SHOW_INVITE_LIST, {parameters:pars, onSuccess:showInviteListHandler});   
}
showInviteListHandler = function(t){
    var content = t.responseText;
    var data = {
       title : Light.getMessage('CLOSE'),
       ok : Light.getMessage('BUTTON_OK'),
       content : content,
       popupName :'inviteList'
      };
    createTopPopupDiv('inviteList','inviteList.jst',300,data,null);

}
inviteBuddysToChat = function(form){
    var chattingId = form['chattingId'].value;
	var userIds = "";
	for(var i = 0; i < form.elements.length; i++) {  
		if(form.elements[i].type=="checkbox" && form.elements[i].checked && !form.elements[i].disabled) {
			userIds+=form.elements[i].value+",";
		}
	}
  	var params="chattingId="+chattingId+"&userIds="+userIds;
    Light.ajax.sendRequest(Light._INVITE_BUDDYS_TO_CHAT, {parameters:params}); 
}
  createTopPopupDiv = function (name,templateName,width,data,e,id,flag) {
      var old = document.getElementById(name);
      if(old != null && flag != null && flag)
      	hideTopPopupDiv(name);
      else if(old != null)
      	return;          
      var vPopupDiv = document.createElement('div');
      vPopupDiv.id = name;
      vPopupDiv.style.position = "absolute";      
      vPopupDiv.className = "portlet-popup";  
      if(width != null)      
	  vPopupDiv.style.width= width;      
      vPopupDiv.innerHTML = TrimPath.processDOMTemplate(templateName, data);          
      var x = getMousePositionX(e)+10;
      var y = getMousePositionY(e);
	  if(y <= 0 && id != null){
          portlet = Light.getPortletById(id); 
          if(portlet != null){
          	x = portlet.left+100;
          	y = portlet.top;
          }	
      }   
      if(parseInt(x)+parseInt(width) > document.body.clientWidth)
          x = parseInt(x) - parseInt(width);
      setPosition(vPopupDiv,x,y);
      var zIndex = ++Light.maxZIndex+1000;     
      vPopupDiv.style.zIndex= zIndex; 
      document.body.appendChild(vPopupDiv); 
      vPopupDiv = null;
}
function hideTopPopupDiv(name){
   var old = document.getElementById(name);
   if(old != null){
	document.body.removeChild(old);
	document.body.onclick = null;
	return true;
   }
   return false;
}