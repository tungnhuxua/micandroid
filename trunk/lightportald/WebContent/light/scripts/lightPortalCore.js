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
//------------------------------------------------------------ lightPortalCore.js
LightPortal.prototype.startup = function(){
	this.initialize();
	this.clear();
	this.open();
	this.afterStartup();  	
}

LightPortal.prototype.initialize = function(){
	this.initEvents();
	this.initPosition();
}

LightPortal.prototype.initEvents = function(){
	var that = this;
	document.body.ondragstart = function() { 
  									return false; 
  								};
  	document.body.onmousemove = function(e) {
	    							if(that.moveTimer == -1) return;
	    							if (document.all) e = event;
								    if(that.dragDropPortlet) {
	      								that.dragDropPortlet.move(e);
	    							}
	    							return false;
  								};
  	document.body.onmouseup = function(e) {
									that.moveTimer = -1;
							    	if(document.all) e = event;
							    	if(that.dragDropPortlet) {
							      		that.dragDropPortlet.moveEnd(e);
							      		that.dragDropPortlet = null;
							      		document.body.onselectstart = null;
							      		document.body.ondragstart = null;
							    	}
							    	return false;
						  		};	
	document.onmousedown = function(){
							Light.setSessionTimer();
						};
	document.onkeydown = function(){
							Light.setSessionTimer();
						};
	window.onresize = function(){
						that.resize();
					};
	window.onorientationchange = function(){
						that.resize();
					};
}

LightPortal.prototype.initPosition = function(){
	if(this.originalLeft){
   		this.layout.containerLeft = this.originalLeft;
		this.originalLeft = null; 
 	}
}
LightPortal.prototype.clear = function(){}
LightPortal.prototype.open = function(){ 
	if(Light.needReload){
		this.load();
	}else if(Light.needRender){
		this.render(L$('portalJSON').title,L$('tabsJSON').title);
	}else{
		this.bind(L$('portalJSON').title,L$('tabsJSON').title);
	}
}

LightPortal.prototype.initUser = function(userId){
	if(userId){
		Light.userId = userId;
		Light.setCookie(Light._LOGINED_USER_ID,Light.userId);  
	}else{
		Light.deleteCookie(Light._LOGINED_USER_ID);
		Light.deleteCookie(Light._CURRENT_TAB);
	}
}
LightPortal.prototype.afterStartup = function(){	
	
}

LightPortal.prototype.load = function(){ 
	var that = this;
	var userId = Light.getRememberedUserId();
	var password = Light.getCookie(Light._REMEMBERED_USER_PASSWORD);
	log("Start Portal:"+userId);
	var params = "check=1";
	if( userId && password && !Light.getCookie(Light._IS_SIGN_OUT)){
		params = "userId="+escape(encodeURIComponent(userId))  
  	          	+"&password="+escape(encodeURIComponent(password));
	}   
	var opt = {
	    method: 'post',
	    parameters: params,
	    onSuccess: function(t) {
	        that.loadHandler(t);
	    },
	    onFailure: function(t) {
	        Light.alert('Error ' + t.status + ' -- ' + t.statusText);
	    }
	 }
	Light.ajax.sendRequest(Light._LOAD_PORTAL, opt);         
}
LightPortal.prototype.loadHandler = function(t){ 
	log("load Portal:"+t.responseText);  
	var params = JSON.parse(t.responseText);
	this.initUser(params[0].userId);
	this.render(params[0],params[1],true);
	Light.needReload = false;   
} 

LightPortal.prototype.render = function(portal,tabs,flag){ 
	this.renderPortal(portal,flag);
	this.renderPortalTab(tabs,flag);
	Light.refreshTextColor();
	this.show();		      
}

LightPortal.prototype.renderPortal = function(myPortal,flag){ 
	log("render Portal:"+myPortal);
	this.data = (flag) ? myPortal : JSON.parse(myPortal);
	this.initUser(this.data.userId);
	Light.setTextFont();
	Light.playMusic();       
  	this.container = this.createPortalContainer();
  	this.header = Light.service.getPortalHeader(this); 
  	this.menu = Light.service.getPortalMenu(this); 
  	this.body = this.createPortalBody();
  	this.content = this.createPortalContent();
  	this.footer = Light.service.getPortalFooter(this);
  	this.body.appendChild(this.content);  	
  	this.container.appendChild(this.header);
  	this.container.appendChild(this.menu);
  	this.container.appendChild(this.body);
  	this.container.appendChild(this.footer); 
  	this.hide();
  	document.body.appendChild(this.container);  	     
  	this.turnedOn= true; 
  	this.startTab=0;
  	log("render Portal:end");       	  
}

LightPortal.prototype.renderPortalTab = function(myPortalTabs,flag){ 
	log("render Portal Tab:"+myPortalTabs);
	var portalTabs = (flag) ? myPortalTabs : JSON.parse(myPortalTabs);
	var defaultTab = null;
  	var defaultTabs = null;
  	if(Light.getCookie(Light._CURRENT_TAB)){
		defaultTab = Light.getCookie(Light._CURRENT_TAB);
		defaultTabs = defaultTab.split("-");
		if(defaultTabs.length > 0 && defaultTabs[0] < portalTabs.length){
			defaultTab = parseInt(defaultTabs[0]);
		}    	  
  	}
  	if(defaultTab && defaultTab >= portalTabs.length) defaultTab = 0;
  	if(defaultTab && defaultTab >= this.data.maxShowTabs) this.startTab=defaultTab + 1 - this.data.maxShowTabs;
  	if(portalTabs.length > this.data.maxShowTabs) this.addLeftButton();
  	
  	for(var i=0,len=portalTabs.length;i<len;i++){ 
    	var tab = new LightPortalTab(portalTabs[i]);
        if(i >= this.startTab && i < this.data.maxShowTabs + this.startTab)      
          	tab.open();
        if(tab.defaulted && defaultTab == null)
        	defaultTab = i;       
  	} 
  	
  	if(portalTabs.length > this.data.maxShowTabs) this.addRightButton();         
  	this.addTabMenu();
    log("render Portal defaut:"+defaultTabs);  
  	if(defaultTabs && defaultTabs.length > 1){  
  	     var defaultSubTabs = "";
  	     for(var i=1;i<defaultTabs.length;i++){
  	        if(i == 1)
  	           defaultSubTabs=defaultTabs[i];
  	        else
  	           defaultSubTabs+="-"+defaultTabs[i];
  	     }
  	  	 this.tabs[defaultTab].defaultTab = defaultSubTabs;
	}
	if(!defaultTab || (defaultTab && !this.tabs[defaultTab])) defaultTab=0;  	  
         	  
	var url = document.location.href;
	var i = url.indexOf("#");
	var location = (i >= 0) ? url.substr(i+1) : "";
	if(location.indexOf(Light._PAGE_PREFIX) >= 0){
		Light.historyListener(location);
	}else{
	    this.tabs[defaultTab].focus(); 
	}
	if(portalTabs.length <= 1 && (!Light.hasPermission(Light.permission.PORTAL_TAB_ADD) || !L$('tabMenuAddTab')))
      	this.tabs[0].hide(); 
	log("render Portal Tab: done"); 
}

LightPortal.prototype.bind = function(portal,tabs,flag){ 
	this.bindPortal(portal,flag);
	this.bindPortalTab(tabs,flag);
	Light.refreshTextColor();		      
}

LightPortal.prototype.bindPortal = function(myPortal,flag){ 
	log("bind Portal:"+myPortal);
	this.data = (flag) ? myPortal : JSON.parse(myPortal);
	this.initUser(this.data.userId);
	Light.setTextFont();
	Light.playMusic();       
  	this.container = L$('portalContainer');
  	this.header = L$('portalHeader'); 
  	this.menu = L$('portalMenu'); 
  	this.body = L$('portalBody');
  	this.content = L$('portalContent');
  	this.footer = L$('portalFooter');  	  
  	this.turnedOn= true; 
  	this.startTab=0;
  	log("bind Portal:end");       	  
}

LightPortal.prototype.bindPortalTab = function(myPortalTabs,flag){ 
	log("bind Portal Tab:"+myPortalTabs);
	var portalTabs = (flag) ? myPortalTabs : JSON.parse(myPortalTabs);
	var defaultTab = null;
  	var defaultTabs = null;
  	if(Light.getCookie(Light._CURRENT_TAB)){
		defaultTab = Light.getCookie(Light._CURRENT_TAB);
		defaultTabs = defaultTab.split("-");
		if(defaultTabs.length > 0 && defaultTabs[0] < portalTabs.length){
			defaultTab = parseInt(defaultTabs[0]);
		}    	  
  	}
  	if(defaultTab && defaultTab >= portalTabs.length) defaultTab = 0;
  	if(defaultTab && defaultTab >= this.data.maxShowTabs) this.startTab=defaultTab + 1 - this.data.maxShowTabs;
  	if(portalTabs.length > this.data.maxShowTabs) this.addLeftButton();
  	
  	for(var i=0;i<portalTabs.length;i++){ 
    	var tab = new LightPortalTab(portalTabs[i]);
        if(i >= this.startTab && i < this.data.maxShowTabs + this.startTab)      
          	tab.bind();   
        if(tab.defaulted && defaultTab == null)
        	defaultTab = i;       
  	} 
  	
  	if(portalTabs.length > this.data.maxShowTabs) this.addRightButton();         
  	this.addTabMenu();
    log("bind Portal defaut:"+defaultTabs);  
  	if(defaultTabs && defaultTabs.length > 1){  
  	     var defaultSubTabs = "";
  	     for(var i=1;i<defaultTabs.length;i++){
  	        if(i == 1)
  	           defaultSubTabs=defaultTabs[i];
  	        else
  	           defaultSubTabs+="-"+defaultTabs[i];
  	     }
  	  	 this.tabs[defaultTab].defaultTab = defaultSubTabs;
	}
	if(!defaultTab || (defaultTab && !this.tabs[defaultTab])) defaultTab=0;  	  
    this.tabs[defaultTab].bindFocus(); 
	if(portalTabs.length <= 1 && (!Light.hasPermission(Light.permission.PORTAL_TAB_ADD) || !L$('tabMenuAddTab')))
      	this.tabs[0].hide(); 
	log("render Portal Tab: done"); 
}

LightPortal.prototype.addLeftButton = function(){	
	var container = Light.newElement({
							element:'span',
						   	id:'leftTabButton',
						   	className:'portal-tab-button',		   
						   	style:{background:'no-repeat',marginTop:'8px',marginLeft:'0',marginRight:'0',padding:'0'},
						   	onclick:function(){
						   		Light.focusLeftTab();
				   			}
		   			});
	var button = Light.newElement({
							element:'img',
						   	src:Light.getThemePath()+'/images/left.png',
						   	title:Light.getMessage('BUTTON_PREVIOUS'),
						   	align:'middle',
						   	style:{height:16,width:16}
			 				});
	container.appendChild(button);
	if(!this.startTab){
		container.className = "";
		container.onclick="";
		container.style.MozOpacity=0.3;
		if(container.filters) container.filters.alpha.opacity=30;
	}
	L$('tabs').insertBefore(container,L$(Light._TAB_LIST));		
}

LightPortal.prototype.addRightButton = function(){
	var container = Light.newElement({
								element:'span',
								id:'rightTabButton',
								style:{background:'no-repeat',marginTop:'5px',marginLeft:'0',marginRight:'0',padding:'0'},
								onclick:function(){
									Light.focusRightTab();
								}
						});
	var button = Light.newElement({
							element:'img',
						   	src:Light.getThemePath()+'/images/right.png',
						   	title:Light.getMessage('BUTTON_NEXT'),
						   	className:'portal-tab-button',			   
						   	align:'middle',
						   	style:{height:16,width:16}
						   	});
	container.appendChild(button);
	L$('tabs').appendChild(container);	
}

LightPortal.prototype.addTabMenu = function(serverId,tabParentId){
   if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD)){			   	
		var button = Light.newElement({
							element:'input',
						   	type:'button',
						   	title:Light.getMessage('MENU_ADD_SUBTAB'),
						   	className:'icons_addTab',			   
						   	style:{
						   		width:16,
						   		display:'inline',
						   		borderStyle:'none',
						   		borderWidth:0
						   		}
						});
						   	
		var newTab = document.createElement('li');
		newTab.appendChild(button);	
		if(serverId == null){
		    newTab.id = "tabMenuAddTab";
			newTab.setAttribute("id", "tabMenuAddTab");
			newTab.setAttribute("tabId", "");
			newTab.onclick = function () { 	   
			   addAutoTab(0); 
			}			
			L$(Light._TAB_LIST).appendChild(newTab);
		}else{
			newTab.id = "tabMenuAddTab"+serverId;
			newTab.setAttribute("tabId", serverId);
			newTab.setAttribute("parentId", tabParentId);	
			newTab.onclick = function () { 
			   addAutoTab(serverId); 
			}
			L$('tabList'+serverId).appendChild(newTab);	
		}	
    }	
}

LightPortal.prototype.show = function(){
	this.container.style.visibility = "visible";
}

LightPortal.prototype.hide = function(){
	this.container.style.visibility='hidden';
}

LightPortal.prototype.createPortalContainer = function (){ 
	log("render Portal:create portal container"); 
	var container = Light.newElement({
	   					element:'div',
	   					id:'portalContainer',
	   					className:'portal-container',
	   					style:{position : 'absolute',
	   						   zIndex : ++Light.maxZIndex,
	   						   fontSize : 12 + parseInt(this.data.fontSize),
	   						   width : this.layout.containerWidth,
	   						   left:this.layout.containerLeft, 
	   						   height : this.layout.maxHeight}
   					});     
	var bgImage = this.data.bgImage;
   	if(bgImage && bgImage != "no"){ 
  		if(bgImage.indexOf("http") < 0)
         	bgImage = Light.getContextPath()+bgImage;
        var bg = "#ffffff";
		if(this.data.bgRepeat == 1)
			bg = "url('"+bgImage+"') no-repeat " + this.data.bgPosition;// no-repeat left top";
		else if(this.data.bgRepeat == 2)
         	bg= "url('"+bgImage+"') repeat-x right top";
		else if(this.data.bgRepeat == 3)
         	bg= "url('"+bgImage+"') repeat-y left top";
		else
         	bg= "url('"+bgImage+"')";      
	}
	document.body.style.backgroundColor= bg; 
   	setPosition(container,this.layout.containerLeft,this.layout.containerTop);
   	return container;   
}

LightPortal.prototype.createPortalBody = function (){
	log("render Portal:create portal body");
   	return Light.newElement({
	   					element:'div',
	   					id:'portalBody',
	   					className:'portal-body'
	   				});
}

LightPortal.prototype.createPortalContent = function (){
	log("render Portal:create portal content");
	return Light.newElement({
	   					element:'div',
	   					id:'portalContent',
	   					className:'portal-content',
	   					innerHTML:"<div id='tabs' class='portal-tabs'><ul id='tabList'></ul></div><div id='tabPanels' class='portal-tab-panels' ></div>",
	   					style:{position:'absolute',
	   						   zIndex:++Light.maxZIndex,
	   						   width:this.layout.maxWidth
	   						   }
   					});
}

LightPortal.prototype.refreshPortalHeader = function(){
	this.container.removeChild(this.header);	
	this.header = Light.service.getPortalHeader(this);
	this.container.insertBefore(this.header,this.menu);
	Light.refreshTextColor();
}

LightPortal.prototype.refreshPortalMenu = function(){
	this.container.removeChild(this.menu);
	this.menu = Light.service.getPortalMenu(this); 
	this.container.insertBefore(this.menu,this.body);
	Light.refreshTextFontSize();
}
LightPortal.prototype.resize = function (){
	if(!this.turnedOn) return;	
	this.layout.containerLeft= Light.getContainerLeft();
	setPosition(this.container,this.layout.containerLeft,this.layout.containerTop);
	var currentTab = Light.getCurrentTab();  
	this.refreshPortalMenu();
	currentTab.resize();
}

LightPortal.prototype.collapseAll = function (){	
 	Light.getCurrentTab().collapseAll(); 	
}

LightPortal.prototype.expandAll = function (){
 	Light.getCurrentTab().expandAll(); 	
}

LightPortal.prototype.shutdown = function(){
	var len = this.allPortlets.length;
	for(var i=0;i<len;i++){
		var portlet = this.allPortlets[i];
		if(portlet.popup && portlet.location && portlet.location == 4)
			portlet.close();
	}
	this.container.removeChild(this.header);
	this.container.removeChild(this.menu);
	this.container.removeChild(this.body);
	this.container.removeChild(this.footer);
	document.body.removeChild(this.container);               
	document.body.onselectstart = null;
	document.body.ondragstart = null;
	document.body.onmousemove = null;
	document.body.onmouseup = null;
	document.body.style.backgroundColor= ""; 
	this.tabs = [];
	this.allTabs = [];
	this.allPortlets = [];
	this.turnedOn= false;	
	Light.offMode();
}