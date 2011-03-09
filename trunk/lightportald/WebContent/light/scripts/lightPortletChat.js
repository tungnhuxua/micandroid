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
//------------------------------------------------------------ lightPortletChat.js
function PortletChatWindow (position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,refreshAtClient,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter,allowMinimized,allowMaximized,allowPopout) {
   Light.portal.allPortlets.push(this);
   this.parent = Light.currentTab;
   this.window = new WindowSkin21();
   this.popup= true;
   this.allowMove = true;
   this.mode = Light._VIEW_MODE;
   this.state = Light._NORMAL_STATE;
   this.tIndex = this.parent.index;   
   this.serverId = Date.parse(new Date());
   this.id = Light._PR_PREFIX+this.serverId;
   this.position = position;
   this.left = (width < Light.portal.layout.maxWidth) ? left : 0;
   this.top  = (width < Light.portal.layout.maxWidth) ? getScreenCenterY() - 200 : Light.portal.layout.bodyTop;
   this.width = (width < Light.portal.layout.maxWidth) ? width : Light.portal.layout.maxWidth;
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
   this.index = this.parent.getPortletIndex(this.position);
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;
   this.originalTop = this.top;
   this.originalWidth = this.width;
   this.originalLeft = this.left;
   this.parent.portlets[this.position][this.index] = this;
   this.window.render(this);
   this.minimized = false;
   this.maximized = false;   
   this.isPopout = false;
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
  document.body.onselectstart = function() { return false };
  document.body.ondragstart = function() { return false };
  if (document.all) e = event;
  var x = e.clientX;
  var y = e.clientY;
  this.focus();
  this.moveable = true;
  this.mouseDownX = x;
  this.mouseDownY = y;
  this.moveBeginX = x;
  this.moveBeginY = y;

  Light.portal.moveTimer = 0;
  this.startMoveTimer(this);
}
PortletChatWindow.prototype.moveEnd = function()
{    
   if(this.moveable){    
    this.moveable = false;  
    this.originalLeft = this.left;
    this.originalTop = this.top;     
  }
}
PortletChatWindow.prototype.move = function(e)
{ 	
   if(this.moveable){
    var x = e.clientX;
    var y = e.clientY;
    this.left += x - this.mouseDownX;
    this.top  += y - this.mouseDownY;    
    this.refreshPosition();   
    this.mouseDownX = x;
    this.mouseDownY = y;
  }
}
PortletChatWindow.prototype.startMoveTimer = function(portlet)
{
   if (Light.portal.moveTimer>=0 && Light.portal.moveTimer<10){
    Light.portal.moveTimer++;
    setTimeout((function() {portlet.startMoveTimer (portlet)}), 15);
   }
   if (Light.portal.moveTimer == 10) {
    Light.portal.dragDropPortlet = this;
    portlet.refreshPosition();
    }
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
  Light.executeRefresh(id);
}
PortletChatWindow.prototype.changePosition = function()
{
   var params = "responseId="+this.id
      		    +"&tabId="+this.parent.serverId
      		    +"&portletId="+this.serverId
                +"&column="+this.position
                +"&row="+this.index;
   Light.ajax.sendRequest(Light._CHANGE_POSITION,{parameters:params});   
   this.parent.repositionPortlets(this);    
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
    this.buttonIsClicked = true;
}
PortletChatWindow.prototype.moveAllowed = function()
{    
    this.buttonIsClicked = false;
}
PortletChatWindow.prototype.moveUp = function(){}
PortletChatWindow.prototype.moveDown = function(){}
PortletChatWindow.prototype.moveLeft = function(){}
PortletChatWindow.prototype.moveRight = function(){}
PortletChatWindow.prototype.minimize = function()
{ 
   this.minimized = !this.minimized;
   if(this.minimized){
    this.state = Light._MINIMIZED_STATE;
    if(this.maximized){
       this.maximize(false);   
       this.minimized = true;     
    }
    var empty = "<br/>";
    this.window.container.innerHTML = empty;         
  }else{    
    this.state = Light._NORMAL_STATE;
        
  }
   this.window.refreshWindow(this); 
   this.parent.repositionPortlets(this);      
   if(!this.minimized)
      this.refresh();
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
 	  Light.portal.container.style.zIndex= 1; 	  
 	  this.parent.showOtherPortlets();	
   }else{        
        this.state = Light._MAXIMIZED_STATE;       
   	    this.left = Light.portal.layout.maxLeft;
   	    this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;          
        Light.portal.container.style.zIndex= ++Light.maxZIndex; 
        Light.portal.body.style.zIndex= ++Light.maxZIndex;
        Light.portal.footer.style.zIndex= ++Light.maxZIndex;  
 	  	this.parent.hideOtherPortlets(this);  	      	
   }        
   this.window.maximize(this);  
   this.parent.repositionPortlets(this);   
   
 } 
PortletChatWindow.prototype.popout = function()
{ 
	this.isPopout = !this.isPopout;
	if(this.isPopout){
	    if(L$('chattingRoomId') == null){
			var chatRoomId= document.createElement('var');
			chatRoomId.id='chattingRoomId';
			chatRoomId.title=this.parameter;
			document.body.appendChild(chatRoomId);   
			var chatRoomName= document.createElement('var');
			chatRoomName.id='chattingRoomName';
			chatRoomName.title=this.title;
			document.body.appendChild(chatRoomName); 
		}else{
			L$('chattingRoomId').title=this.parameter;
			L$('chattingRoomName').title=this.title;
		}    
		var width= 600; var height= 420; var x= getScreenCenterX() - width / 2; var y = getScreenCenterY() - height / 2;
		this.chatWindow= window.open(Light.getContextPath()+"/chat.jsp","mywindow","location=0,menubar=0,status=0,resizable=1,width="+width+",height="+height+",screenX="+x+",screenY="+y); 
		this.window.close(this);
		this.parent.portlets[this.position][this.index] = null; 
	}else{
	
	}
}
PortletChatWindow.prototype.close = function()
{ 
	var closePortlet = Light.confirm(Light.getMessage('COMMAND_CLOSE_POPUP_CHAT'));
	if (!closePortlet) // user cancelled close closePortlet
	{
		return;
	}
	if(this.maximized){
	    this.windowMaximize();
	}
	clearTimeout(this.timer);
	this.window.close(this);
	this.parent.portlets[this.position][this.index] = null;   
	this.parent.repositionPortlets(this);           
	this.parent.repositionFooter();
	Light.ajax.sendRequest(Light._CLOSE_CHAT,{parameters:this.parameter}); 
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
//------------------------------------------------------------ ChatFunction.js
chatWithBuddy = function(userId){
    hideBuddyDetail();
  	var params="userId="+userId;
    Light.ajax.sendRequest(Light._CHAT_WITH_BUDDY, {parameters:params, onSuccess:responseChatWithBuddy}); 
}
responseChatWithBuddy = function(t){
    var chatParams = t.responseText.split(",");
    if(chatParams[1] == 0){
	var data = {
         title : Light.getMessage('CLOSE'),
         ok : Light.getMessage('BUTTON_OK'),    
         userName : chatParams[0],	 
         popupName :'isBlockUser'
        };
        createPopupDiv('isBlockUser','isBlockUser.jst',360,data,null,null); 
        return;
    }
    if(chatParams[1] == "n"){
	var data = {
         title : Light.getMessage('CLOSE'),
         ok : Light.getMessage('BUTTON_OK'),    
         userName : chatParams[0],	 
         popupName :'noIM'
        };
        createPopupDiv('noIM','noIM.jst',360,data,null,null); 
        return;
    }
    if(chatParams[1] == "f"){
	var data = {
         title : Light.getMessage('CLOSE'),
         ok : Light.getMessage('BUTTON_OK'),    
         userName : chatParams[0],	 
         popupName :'friendOnlyIM'
        };
        createPopupDiv('friendOnlyIM','friendOnlyIM.jst',360,data,null,null); 
        return;
    }	
    openChatWindow(Light.getMessage('TITLE_CHAT')+chatParams[0],chatParams[1]);
}
enterChattingRoom = function (id,name) { 
    var params="chattingId="+id; 
    Light.ajax.sendRequest(Light._ENTER_CHATTING_ROOM, {parameters:params}); 
    openChatWindow(Light.getMessage('TITLE_CHAT')+name,id);
}
popinChatRoom = function (parameter,title) {  
    var params = parameter.split("=");
    openChatWindow(title,params[1]);
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
  	    +"&tabId="+portlet.parent.serverId
  	    +"&portletId="+portlet.serverId
        +"&action=send"
        +"&"+portlet.parameter	
	    +"&chat="+encodeURIComponent(chat); 
    Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: false, parameters: pars}); 			         
   document.forms['form_'+id]['chat'].value ="";
   document.forms['form_'+id]['chat'].focus();
}

acceptChat = function(chattingId,chattingWith){
	hideResponseChat();
	Light.ajax.sendRequest(Light._ACCEPT_CHAT,{parameters:"chattingId="+chattingId}); 
	openChatWindow(Light.getMessage('TITLE_CHAT')+chattingWith,chattingId);
}
openChatWindow = function(title,id){
	var width = 400;
	var x = getScreenCenterX() - width / 2 - Light.portal.layout.containerLeft - Light.portal.layout.bodyLeft;
	var	portlet = new PortletChatWindow(12,x,width,title,"icons chat","","chattingPortlet","/chattingPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,false,5000,true,'','','',"chattingId="+id); 
	portlet.refresh();
	document.forms['form_'+portlet.id]['chat'].focus();
}
refuseChat = function(chattingId){
  hideResponseChat();
  Light.ajax.sendRequest(Light._REFUSE_CHAT,{parameters:"chattingId="+chattingId}); 
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
    createPopupDiv('inviteList','inviteList.jst',300,data,null);

}
inviteBuddysToChat = function(form){
    var chattingId = 0;//form['chattingId'].value;
	var userIds = "";
	for(var i = 0; i < form.elements.length; i++) {
		if(form.elements[i].name=="chattingId") chattingId = parseInt(form.elements[i].value);
		if(form.elements[i].type=="checkbox" && form.elements[i].checked && !form.elements[i].disabled) {
			userIds+=form.elements[i].value+",";
		}
	}
	if(chattingId > 0){
	  	var params="chattingId="+chattingId+"&userIds="+userIds;
	    Light.ajax.sendRequest(Light._INVITE_BUDDYS_TO_CHAT, {parameters:params}); 
	}
}
hideResponseChat = function(){
	hidePopupDiv('instantMessage');
}
