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
//------------------------------------------------------------ lightPortalTab.js
LightPortalTab = function (data) {
	this.id = Light._TAB_PREFIX+data.id;
	this.serverId = data.id;
	this.label = data.label;
	this.client = data.client;
	this.tabURL = data.URL;
	this.isCloseable = data.isCloseable;
	this.isEditable = data.isEditable;
	this.isMoveable  = data.isMoveable
	this.allowAddContent = data.allowAddContent;
	this.defaulted = data.defaulted;
	this.between = data.between;
	this.widths = data.widths;
	this.fitScreen = data.fitScreen;   
	this.windowSkin = data.windowSkin;
	this.privacy = data.privacy;
   	this.parentId = data.parentId;
	var parentTab = (this.parentId) ? Light.getTabById(this.parentId) : null;
	if(parentTab){
  	  	this.parent = parentTab;
	   	this.tabList = Light._TAB_LIST+this.parent.serverId;
	    this.tabPanels = Light._TAB_PANELS+this.parent.serverId;    
	}else{
		this.parent = Light.portal;
      	this.tabList = Light._TAB_LIST;
	    this.tabPanels = Light._TAB_PANELS;
	}  
	this.index = this.parent.tabs.length;
	this.parent.tabs[this.index]=this;   	    
   	Light.portal.allTabs.push(this);
	this.portlets = [];  
   	this.tabs = [];
   	this.adjustWidth();
   	delete data;
   	log("rendered portal tab "+this.label);
}

LightPortalTab.prototype.open = function(next){	
	if(!this.container){
		var that = this;
		this.title = Light.newElement({element:'div',
										id:Light._TAB_TITLE_PREFIX + this.serverId,
									   	className:"portal-tab-handle",
									   	innerHTML:this.label,
									   	onclick:function(e){
									   			showProgressBar(e);   
											   	that.focus(); 
											   	hideProgressBar();
											},
									   	onmouseover:function () { 	   
											   	that.hover(); 	  
											},
									   	onmouseout:function () { 	   
											   	that.hoverout(); 	  
											}
										});	
		this.header = Light.newElement({element:'span',
										id:Light._TAB_HEADER_PREFIX + this.serverId});
		this.header.appendChild(this.title);
		
		this.container = Light.newElement({element:'li',
									id:this.id
									});								
		this.container.appendChild(this.header);
		if(next){
			L$(this.tabList).insertBefore(this.container,next.container);	  
		}else{
			var lastOne = (this.parentId) ? L$('tabMenuAddTab'+this.parent.serverId) : L$('tabMenuAddTab');
			if(lastOne)
				L$(this.tabList).insertBefore(this.container,lastOne);	  
			else
				L$(this.tabList).appendChild(this.container);	
		}
	}
	if(!this.panel){
		this.panel = Light.newElement({element:'div',
										id:Light._PANEL_PREFIX + this.serverId
										});
		
		if(next){
			L$(this.tabPanels).insertBefore(this.panel,next.panel);	  
		}else{	
			L$(this.tabPanels).appendChild(this.panel);	
		}
	}	
	this.opened=true;
	log("opened portal tab "+this.label);
}
	
LightPortalTab.prototype.bind = function(){	
	var that = this;
	this.title = L$(Light._TAB_TITLE_PREFIX+this.serverId);
	if(this.title){
		this.title.onclick = function (e) { 	
			showProgressBar(e);   
		   	that.focus(); 
		   	hideProgressBar();
		}
		this.title.onmouseover = function () { 	   
		   	that.hover(); 	  
		}
		this.title.onmouseout = function () { 	   
		   that.hoverout(); 	  
		}
	}
	this.header = L$(Light._TAB_HEADER_PREFIX+this.serverId);
	this.container = L$(this.id);
	this.panel = L$(Light._PANEL_PREFIX + this.serverId);
	this.opened=true;
	if(this.parentId){
		this.parent.popupMenu=null;
		this.parent.popupMenuLoaded=false;
	}
	log("bind portal tab "+this.label);
}
LightPortalTab.prototype.insert = function(){
    this.open();
	this.focus(); 
}
LightPortalTab.prototype.hide = function(){
   	L$(this.tabList).style.visibility = "hidden";   
}
LightPortalTab.prototype.hover = function(){
	this.hoverAction = true;
	var that = this;
	setTimeout((function() {that.showSubTab ()}), 500);	    
}
LightPortalTab.prototype.hoverout = function(){	
	this.hoverAction = false;
	Light.hoverTab = null;
}
LightPortalTab.prototype.showSubTab = function(){	
	if(this.hoverAction){
		Light.hoverTab = this;
		if(this.popupMenu){
			this.popupSubTab(this.popupMenu);
		}else if(!this.popupMenuLoaded){
		    var params = "tabId="+this.serverId;
			Light.ajax.sendRequest(Light._SHOW_SUB_TAB, {parameters:params, onSuccess:Light.hoverTab.showSubTabHandler}); 
		}
	}  
}
LightPortalTab.prototype.showSubTabHandler= function(t){
	if(Light.hoverTab){
		Light.hoverTab.popupMenuLoaded = true;
		if(t.responseText){
			Light.hoverTab.popupMenu = t.responseText;
			Light.hoverTab.popupSubTab(Light.hoverTab.popupMenu);
		}
	}
}
LightPortalTab.prototype.popupSubTab= function(text){
	if(Light.hoverTab && Light.hoverTab.hoverAction && text){
		var tabs = JSON.parse(text);
		var data = {
	       title : Light.getMessage('CLOSE'),
	       tabs : tabs,
	       popupName :'viewSubTab'
	      };
	    var tab = L$(Light._TAB_HEADER_PREFIX + tabs[0].parentId);
	    var e = {
	       pageX : getX(tab)-5,
	       pageY : getY(tab)+20
	    };
	    createTopPopupDiv('viewSubTab','viewTab.jst',100,data,e,null,true);
	    L$('viewSubTab').style.padding=0;
	    document.body.onclick = function(e) {hideTopPopupDiv('viewSubTab');};
    }else
    	hideTopPopupDiv('viewSubTab');
    Light.hoverTab = null;
}
LightPortalTab.prototype.getFocusId = function(){
	var parentTab = (this.parentId > 0) ? Light.getTabById(this.parentId) : null;
	return (parentTab) ? parentTab.getFocusId()+"-"+this.index : this.index;
}
LightPortalTab.prototype.bindFocus = function(){
	var id = 'tab'+this.serverId+'PortletsJSON';
	log("bind Portal Tab Focus: "+id); 
	if(L$(id)){
		this.loaded = true;
		this.focusRender();
   		this.bindPortlets(JSON.parse(L$(id).title));   
   	}else
   		this.focus();
}
LightPortalTab.prototype.bindPortlets = function(portlets){     
	if(portlets.length > 0){
		var len = portlets.length;
		log("bind tab portlets:"+len);     
		Light.currentTab = this;
		for(var i=0;i<len;i++){    
			var noRender = (L$(Light._PR_PREFIX+portlets[i].serverId)) ? true : false; 	
			log("bind portlet:"+portlets[i]+","+noRender);           	  
			var portlet = new PortletWindow(portlets[i],false,noRender);     
			if(!noRender) portlet.refresh();   	      
		}  
	}
	this.rePositionAll(); 
}
LightPortalTab.prototype.focusRender = function(flag){
   	log("start focus render portal tab "+this.label);
   	Light.currentTab = this;
   	Light.setCookie(Light._CURRENT_TAB,this.getFocusId());
   	if(flag == null || flag){
	   	Light.addHistory();
   	}  
   	if(!this.focused){
   		this.container.className = "current";
       	this.header.className = "current";
       	this.container.style.display = "block";
       	this.panel.style.display = "block";
       	this.addButtons();
   	}else if(this.isEditable && !this.editTitle){
       	this.editTitle = true;
       	this.header.innerHTML="<input type='text' id='portalTabTitle' size='12' value=\""+this.label+"\" onchange=\"javascript:Light.currentTab.saveTitle();\" onblur=\"javascript:Light.getTabById('"+this.serverId+"').saveTitle();\"/>";                            
       	L$('portalTabTitle').focus();
   	}
    var tabs = this.parent.tabs;
    for(var i=tabs.length;i--;){
    	if(tabs[i] && tabs[i].serverId != this.serverId)
    		tabs[i].blur();
    }
	tabs = Light.portal.tabs;
	for(var i=tabs.length;i--;){
	  	if(tabs[i] && tabs[i].serverId != this.serverId && tabs[i].serverId != this.parentId)
	   		tabs[i].blur();
	}
   	this.focused = true;
   	window.scrollTo(0,0);
   	log("end focus render portal tab "+this.label);
}
LightPortalTab.prototype.focus = function(flag){
	log("start focus portal tab "+this.label);
   	if(!this.opened || !this.container) this.open();   
   	this.focusRender(flag);
   	this.refresh();
   	log("end focus portal tab "+this.label);
}
LightPortalTab.prototype.blur = function(){
	if(this.opened && (this.container && (this.focused || this.container.className == "current"))){
		this.container.className = '';
       	this.header.className = '';
       	this.header.style.paddingBottom=10;
       	this.panel.style.display = "none";
       	$(this.header).find('img,input,.icons').remove();       	
		this.focused = false;
		if(this.tabs.length > 0){
			for(var i=this.tabs.length;i--;){
			   	if(this.tabs[i]) this.tabs[i].blur();
			}
		}
	}
}
LightPortalTab.prototype.addButtons = function(){
	var that = this;
	if(this.isEditable && this.parentId == 0 && Light.hasPermission(Light.permission.PORTAL_TAB_ADD)){
		if(!this.addButton){
			this.addButton = Light.newElement({
			   					element:'input',
			   					type : "button",
								className : "icons_addTab",
								title : Light.getMessage('MENU_ADD_SUBTAB'),
								onclick : function(){
									that.addSubPage();
								},
								style:{display : "inline",
										width : 16,
										borderStyle : "none",
										borderWidth : 0
			   						   }
		   					});								
		}
		this.header.appendChild(this.addButton);
	}
	if (this.isCloseable){	
		if(!this.closeButton){	
			this.closeButton = Light.newElement({
			   					element:'input',
			   					type : "button",
								className : "icons_closeTab",
								title : Light.getMessage('CLOSE'),
								onclick : function(){
									that.close();
								},
								style:{display : "inline",
										width : 16,
										borderStyle : "none",
										borderWidth : 0
			   						   }
		   					});	         
	   	}
	  	this.header.appendChild(this.closeButton);
	}                   
}		
		
LightPortalTab.prototype.saveTitle = function(){
	var title = L$('portalTabTitle');
	if(title){
		this.editTitle=false;
		this.label = title.value;
		this.refreshTitle();
	    var params = "title="+encodeURIComponent(this.label)
	               +"&tabId="+Light.currentTab.serverId
	               ;
	    Light.ajax.sendRequest(Light._EDIT_TAB_TITLE,{parameters:params});
	}
}
LightPortalTab.prototype.refreshTitle = function(){	
	if(this.container){
		this.title.innerHTML = this.label;
		this.header.innerHTML = "";
		this.header.appendChild(this.title);
		if(this.focused) this.addButtons();
	}
}
LightPortalTab.prototype.refresh = function(){
	if(!this.loaded){
	   	this.load(); 
	    this.loaded = true;
	}else{
		this.refreshPortletMethod();
		this.rePositionAll();
	}
	Light.portal.refreshPortalMenu();
}
LightPortalTab.prototype.load = function(){
   	var tabId = this.serverId;
   	if(L$('tab'+tabId+'TabsJSON')){
   		this.renderSubTabs(JSON.parse(Light.getMessage('tab'+tabId+'TabsJSON')), true);
   	}else if(this.parentId > 0){
   		this.getPortletsByTab();
   	}else{
	   	var opt = {
	    	method: 'post',
	    	asynchronous: false,
	    	postBody: 'parentId='+tabId
	   	}	   	
	   	this.render(Light.ajax.sendRequest(Light._LOAD_TAB_CONTENT, opt)); 
	}
}
LightPortalTab.prototype.render = function(data){
	var index = data.lastIndexOf("</textarea>");  
	if(index > 0){
		var content = data.substring(0,index+11);
		$('<div>').html(content).appendTo('body');		
		data = data.substr(index+11);
	}
	var contents = JSON.parse(data);
	this.portletsJSON = contents.portlets;
	this.renderSubTabs(contents.subTabs);
}
LightPortalTab.prototype.getSubTabs = function(){
   	var tabId = this.serverId;
   	if(L$('tab'+tabId+'TabsJSON')){
   		this.renderSubTabs(Light.getMessage('tab'+tabId+'TabsJSON'));
   		return;
   	}
   	var currentTab = this;
   	var opt = {
    	method: 'post',
    	asynchronous: false,
    	postBody: 'parentId='+tabId
   	}
   	this.renderSubTabs(Light.ajax.sendRequest(Light._GET_TABS_BY_PARENT, opt)); 
}
LightPortalTab.prototype.renderSubTabs = function(subTabs){  
	if(subTabs.length <= 0){
		this.getPortletsByTab();
   		return;
   	}
	log("render sub tabs:"+subTabs);     
	this.panel.innerHTML="<div id='tabs"+this.serverId+"' class='portal-tabs-sub' ><ul id='tabList"+this.serverId+"'></ul></div><div id='tabPanels"+this.serverId+"' class='portal-tab-panels' ></div>";
    var defaultTab = 0;
    for(var i=0,len=subTabs.length;i<len;i++){ 
        var tab = new LightPortalTab(subTabs[i]);          
        tab.open();
        if(tab.defaulted)
    	    defaultTab = i;       
    }          
    this.addTabMenu();
    if(this.defaultTab){
        if(this.defaultTab.indexOf("-") > 0){
	    	var defaultTabs = this.defaultTab.split("-");
	        if(defaultTabs[0] < subTabs.length){
	      	    defaultTab = defaultTabs[0];
	      	}
	      	if(defaultTabs.length > 1){  
	      	    var defaultSubTabs = "";
	      	    for(var i=1;i<defaultTabs.length;i++){
	      	       if(i == 1)
	      	           defaultSubTabs=defaultTabs[i];
	      	       else
	      	           defaultSubTabs+="-"+defaultTabs[i];
	      	    }
	      		this.tabs[defaultTab].defaultTab = defaultSubTabs;
	      	}
	    }else{
	         defaultTab = this.defaultTab;
	    }	  
	}    	 
    this.tabs[defaultTab].focus(); 
    if(subTabs.length <= 1 && !Light.hasPermission(Light.permission.PORTAL_TAB_ADD))
		this.tabs[0].hide(); 
}

LightPortalTab.prototype.addTabMenu = function(){
   	if(this.isEditable && Light.hasPermission(Light.permission.PORTAL_TAB_ADD)) Light.portal.addTabMenu(this.serverId,this.parentId);   
}

LightPortalTab.prototype.getPortletsByTab = function(){
   	var tabId = this.serverId;
   	if(this.portletsJSON){
   		this.renderPortlets(this.portletsJSON);
   	}else if(this.parentId > 0 && this.parent.portletsJSON){
   		this.renderPortlets(this.parent.portletsJSON);
   		delete this.parent.portletsJSON;
   	}else if(L$('tab'+tabId+'PortletsJSON')){
   		this.renderPortlets(JSON.parse(L$('tab'+tabId+'PortletsJSON').title));   		
   	}else{
	   	var currentTab = this;
	   	var opt = {
			method: 'post',
	    	asynchronous: false,
	    	postBody: 'tabId='+tabId
	   	}
	   	this.renderPortlets(JSON.parse(Light.ajax.sendRequest(Light._GET_PORTLETS_BY_TAB, opt))); 
	}
}
LightPortalTab.prototype.renderPortlets = function(portlets){     
	if(portlets.length > 0){
		var len = portlets.length;
		log("render tab portlets:"+len);     
		var maxPortlet = null;
		Light.currentTab = this;
		for(var i=0;i<len;i++){     	        	  
			var portlet = new PortletWindow(portlets[i]);        	      
			if(portlet.state !=  Light._MAXIMIZED_STATE)
				portlet.refresh();
			else
				maxPortlet = portlet;
		} 
		if(maxPortlet){
			maxPortlet.refresh();
		}   
	}else
		this.repositionFooter(); 
}
LightPortalTab.prototype.addSubPage = function(){  
	var params = "tabId="+this.serverId;
    Light.ajax.sendRequest(Light._ADD_SUB_TAB,{parameters:params, onSuccess:Light.refreshPortal});
}
LightPortalTab.prototype.resize = function(){  
	this.adjustWidth();
   	this.reLayout();
   	Light.portal.refreshPortalMenu();
}
LightPortalTab.prototype.reRender = function(){
	this.adjustWidth();
	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){  
	       	for(var j=p.length;i--;){  
	       		var portlet = p[j];
	       	  	if(portlet && !portlet.isPopupWindow){          	
			    	portlet.reRender();
	          	}
	       	}
	   	}
	}
}

LightPortalTab.prototype.adjustWidth = function(){
	if(this.fitScreen == 1){      	   
   		var totalWidth = 0;
	    var len = this.widths.length;
	   	for(var i=len;i--;){
			totalWidth+=parseInt(this.widths[i]);
			if(i) totalWidth+=this.between;
	   	}
	   	var max = Light.portal.layout.maxWidth;	
   		var diff = max - totalWidth;
	   	if(diff){	   		
	     	var eachD = parseInt(diff / len);
	     	for(var i=len;i--;){
		       	this.widths[i]+=eachD;
		       	if(this.widths[i]>max)
		       		this.widths[i]=max;
		    }
		}		
   	}
}  	
	
LightPortalTab.prototype.reLayout = function(){   
   	for(var i=this.portlets.length;i--;) {
   		var p = this.portlets[i];
       	if(p){    
	       	for(var j=p.length;j--;) {  
	       		var portlet = p[j];  
	       	  	if(portlet && !portlet.maximized && !portlet.isNew){          	
	          		portlet.width = this.widths[i];
			    	portlet.left = 0;
			    	for(var k=0;k<portlet.position;k++) { 
			        	portlet.left += this.widths[k] + this.between;
			    	}
	          	}
	       	}
	   	}
   	}   
   	this.rePositionAll();
}

LightPortalTab.prototype.refreshPortletMethod = function(){   
   	for(var i=this.portlets.length;i--;) {
   		var p = this.portlets[i];
       	if(p){    
	       	for(var j=p.length;j--;) {  
	       		var portlet = p[j];
          		if(portlet && portlet.data && portlet.data.method){ 			
					eval(portlet.data.method+"('"+portlet.data.id+"')");
				}
	   		}
		}
	}   
}

LightPortalTab.prototype.rePositionAll = function(){   
   	for(var i=this.portlets.length;i--;) {
   		var p = this.portlets[i];
       	if(p){    
	       	for(var j=p.length;j--;) {  
	       		var portlet = p[j];   
	       	  	if(portlet && !portlet.maximized && !portlet.isNew){
	          		this.repositionPortlets(portlet);
	          		break;
	       		}
	   		}
		}
	}   
   	this.repositionFooter();
}
LightPortalTab.prototype.getTop = function(){
	return (this.parentId > 0) ? 30 : 0;
}
LightPortalTab.prototype.repositionPortlets = function(portlet){
   	if(typeof portlet.popup != "undefined" && portlet.popup){
     	portlet.focus();
   	 	return;
   	}
   	var position = portlet.position;
   	var index = portlet.index;
   	var height = 0;
   	var maxIndex = 0;
   	var nullNum = 0;
    for(var i=0,len=this.portlets[position].length;i<len;i++) {    
       	var portlet = this.portlets[position][i];
       	if(portlet){ 
        	//check colspan columns    
      		if(position > 0){
	      		for(var j=0;j<position;j++){
	      			var p = this.portlets[j];
	      			if(p && p[i]){
	      				if(p[i].colspan + p[i].position > position){	      			
	      					height = p[i].top+p[i].getHeight();
	      				}
	      			}
	      		}
  			}         
        	if(i >= index && !portlet.maximized){
          		if(height > 0) 
          			portlet.top = height + portlet.marginTop;        
          		else
          			portlet.top = portlet.window.top + portlet.marginTop + this.getTop();     			
          		portlet.refreshPosition();  
        	}
	        //check colspan columns, make this portlet's top is lower than all upper portlets and sort all the followings   
      		if(position > 0){
	      		for(var j=0;j<position;j++){
	      			var p = this.portlets[j];
	      			if(p && p[i+1]){
	      				if(p[i+1].colspan + p[i+1].position > position){
	      					if(p[i+1].top < parseInt(portlet.top) + parseInt(portlet.getHeight()) + portlet.marginTop){
	      						p[i+1].top = parseInt(portlet.top) + parseInt(portlet.getHeight()) + portlet.marginTop;
		      					p[i+1].refreshPosition();
		      					for(var k=0;k<=j;k++){
		      						var top = p[i+1].top + p[i+1].getHeight() + p[i+1].marginTop;
	    	  						if(this.portlets[k]){
	      								for(var m=i+2;m<this.portlets[k].length;m++){
	      									if(this.portlets[k][m]){
	      										this.portlets[k][m].top = top;
	      										this.portlets[k][m].refreshPosition();
	      										top = this.portlets[k][m].top + this.portlets[k][m].getHeight() + p[i+1].marginTop;
	      									}
	      								}
	      							} 
	      						}
	      					}	      			
	      				}
	      			}
	      		}
  			}  
			if(!portlet.maximized && !portlet.isNew){
          		height = portlet.top+portlet.getHeight();   
        	}            
        	if(portlet.maximized && !portlet.isNew){
          		height = portlet.getHeight();     
          		maxIndex = i;
          		nullNum = 0;
        	}
		}
    	if(portlet == null){
    		nullNum++;
    	}        
    }
    
    //check colspan columns
    if(portlet && portlet.colspan > 0){
	   	for(var i= position + 1;i< position + portlet.colspan;i++){
	   		if(this.portlets[i] && this.portlets[i][portlet.index])
   				this.repositionPortlets(this.portlets[i][portlet.index]);
	   	}
	}
    this.repositionFooter();
}
LightPortalTab.prototype.repositionFooter = function(){
   	var currentTabId = Light.getCurrentTabId();
   	if(currentTabId != this.serverId) return; 	
   	var maxHeight = this.getMaxHeight(); 
   	if (document.all) {	
       	Light.portal.footer.style.posTop = maxHeight;           
   	}else {        
       	Light.portal.footer.style.top = maxHeight;           
   	} 
   	if((maxHeight - Light.portal.layout.maxTop) > Light.portal.layout.maxHeight)
      	Light.portal.container.style.height = maxHeight - Light.portal.layout.maxTop; 
   
   	Light.portal.footer.style.width = Light.portal.layout.containerWidth;
}
LightPortalTab.prototype.getMaxHeight = function(){
   	var heights = [];
   	var maxHeight = 0;
   	for(var i=0,len=this.portlets.length;i<len;i++) { 
   		var p = this.portlets[i];
      	if(p){
	      	for(var j=0,len2=p.length;j<len2;j++) {      
		    	if(p[j]){
		    		if(p[j].maximized ){ 
			       		maxHeight = p[j].top + p[j].getHeight();         
			       		break;
			    	}else{
			      		heights[i] = p[j].top + p[j].getHeight();   
			    	}
				}
		  	}
	  	}
	  	if(maxHeight > 0)
	  		break;                        
   	}    
   	if(maxHeight == 0){
       	for(var i=0,len=heights.length;i<len;i++) {
          	if(heights[i] > maxHeight)
             	maxHeight = heights[i];
       	}	   
   	}
   	maxHeight = maxHeight + Light.portal.layout.footerMarginTop;  
   	if(maxHeight < 600) maxHeight = 600; 
   	return maxHeight;
}
LightPortalTab.prototype.getPortletIndex = function(position){
   	var addAfterLast = true;
   	var index = 0;
   	var p = this.portlets[position];
   	if(p){
	   	for(var i=0,len=p.length;i<len;i++) {
			if(p[i] == null){
	           	addAfterLast = false;
	           	index = i;
	           	break;    
	        }
	   	}
	   	if(addAfterLast) {
	     	index = p.length;  
	   	}
	}else {
	   	this.portlets[position] = [];	   
	}
   	return index;
}
LightPortalTab.prototype.showOtherPortlets = function(){
   	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {  
          		if(p[j] && p[j].maximized){       
             		p[j].show();
             		return;
          		}  
       		}
		}
	}
   	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {  
          		if(p[j]){       
            		p[j].show();
          		}  
       		}
   		}
   	}
   	Light.portal.menu.style.zIndex= ++Light.maxZIndex;   
}
LightPortalTab.prototype.hideOtherPortlets = function(portlet){
   	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) { 
          		if(p[j] && p[j].serverId != portlet.serverId){                    
					p[j].hide();
          		}  
       		}
       	}
   	}      
}
LightPortalTab.prototype.collapseAll = function(){
	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {  
	       	  	if(p[j] && p[j].minimized){          	
			    	p[j].minimize();
	          	}
	       	}
	   	}
   	}
}
LightPortalTab.prototype.expandAll = function(){
	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {    
	       	  	if(p[j] && p[j].minimized){          	
			    	p[j].minimize();
	          	}
	          	if(p[j] && p[j].maximized){          	
			    	p[j].maximize();
	          	}
	       }
		}
	}
}
LightPortalTab.prototype.normalAll = function(){
	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {  	       	  	
	          	if(p[j] && p[j].maximized){          	
			    	p[j].maximize();
	          	}
	       }
		}
	}
}
LightPortalTab.prototype.refreshWindowTransparent = function(){
	for(var i=this.portlets.length;i--;){
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {   
	       	  	 if(p[j]){   
	       	  		p[j].refreshWindowTransparent();
	          	}	         
	       	}
	   	}
   	}
}
LightPortalTab.prototype.refreshPortlets = function(){
	for(var i=this.portlets.length;i--;) {
		var p = this.portlets[i];
       	if(p){     
	       for(var j=p.length;j--;) {   
	       	  if(p[j]){   
	       	  	p[j].lastAction=null;
			    p[j].refresh(false);
	          }
	       }
	   }
   }
}
LightPortalTab.prototype.setNotification = function(notification){
	var changed = false;
	if(this.label.indexOf("(") > 0){
		var index = this.label.indexOf("(");
  		this.label = this.label.substring(0,index);	
  		changed = true;
  	}
  	if(notification) this.label += "("+notification+")";	
	if(notification || changed) this.refreshTitle();
}
LightPortalTab.prototype.close = function(){
	if (!Light.confirm(Light.getMessage('COMMAND_CLOSE_TAB'))){ // user cancelled close tab
		return;
	}	
	//Remove the tab
	L$(this.tabList).removeChild(this.container);	
	//Remove the panel
	L$(this.tabPanels).removeChild(this.panel);
	//delete tab from memory
	delete this.parent.tabs[this.index];
	
	// If we closed the tab that had focus, focus on another tab.
	var focus = false;	
	var tabs = this.parent.tabs;
	for(var i=this.index;i--;){
   		if(tabs[i])
   			tabs[i].focus();
   			focus = true;
   			break;
   }
   if(!focus){
	   for(var i=this.index+1;i<tabs.length;i++){
	   		if(tabs[i])
	   			tabs[i].focus();
	   			break;
	   }
   }
   Light.ajax.sendRequest(Light._DELETE_TAB, {parameters:'tabId='+this.serverId});      
}
Light.showTab= function(serverId){
	hideTopPopupDiv('viewMoreTab');
	var tab = Light.getTabById(serverId);
	if(tab.opened){
		tab.focus();
		return;
	}
	var tabList = L$(Light._TAB_LIST);
	var lastNode = null;
	for (var i=tabList.children.length;i--;){
		var node = tabList.children[i];
		if(!node.style.display || node.style.display != "none"){
			lastNode = node;
			break;
		}
	}
	if(lastNode){
		var id = lastNode.id;
		var ids = id.split("_");
		if(ids.length > 1) id = ids[ids.length-1];
		var oldTab = Light.getTabById(id);
		oldTab.blur();
		oldTab.opened=false;
		oldTab.container.style.display = "none";	
	}
	tab.open();
	tab.focus();
}
Light.gotoTab= function(parentId,serverId){
	if(parentId == 0) return Light.showTab(serverId);
	hideTopPopupDiv('viewSubTab');
	var tabs = Light.portal.allTabs;
	for(var i=tabs.length;i--;){
		var tab = tabs[i];
		if(tab){
			if(tab.serverId == parentId && !tab.focused){
	 			tab.focus();
	 			break;
	 		}
 		}
	}
	Light.gotoSubTab(serverId);	
}
Light.gotoSubTab= function(serverId){
	var flag = true;
	var tabs = Light.portal.allTabs;
	for(var i=tabs.length;i--;){
		var tab = tabs[i];
		if(tab.serverId == serverId) flag = false;
		if(tab.serverId == serverId && !tab.focused){
 			tab.focus();
 			break;
 		}
	}
	if(flag) setTimeout((function() {Light.gotoSubTab(serverId)}), 100);
}
Light.focusLeftTab= function(){
	var tabs = Light.portal.tabs;
	var tabList = L$(Light._TAB_LIST);
	var len=tabList.children.length;
	for (var i=0;i<len;i++){
		var node = tabList.children[i];
		if (node && node.tagName == "LI"){
			if ("current" == node.className){
				if(i > 0){
				    var previous = i + Light.portal.startTab - 1;
					tabs[previous].focus();
				}else if(i == 0){
					var previous = Light.portal.startTab - 1;
					if(previous >= 0){
						if(previous == 0){
							var left = L$('leftTabButton');
							left.className = "";
							left.onclick="";
							left.style.MozOpacity=0.3;
							if(left.filters) left.filters.alpha.opacity=30;
						}
						tabList.removeChild(tabList.children[Light.portal.data.maxShowTabs-1]);
						if(!tabs[previous].opened){
							tabs[previous].open(tabs[previous+1]);
						}else{
							tabList.insertBefore(tabs[previous].container,tabs[previous+1].container);	  
						}
						tabs[previous].focus();
						Light.portal.startTab--;						
					}
				}
			}
		}
	}
	if(Light.portal.startTab == 0){
		var left = L$('leftTabButton');
		left.className = "";
		left.onclick="";
		left.style.MozOpacity=0.3;
		if(left.filters) left.filters.alpha.opacity=30;
	}
	var last = len - 1;
	if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD) && !Light.portal.mobile) last--;				
	if(Light.portal.startTab+last<Light.portal.tabs.length){
		var right = L$('rightTabButton');
		right.className = 'portal-tab-button';
		right.onclick = function(){Light.focusRightTab();};
		right.style.MozOpacity=1;
		if(right.filters) left.filters.alpha.opacity=100;
	}
}
Light.focusRightTab= function(){
	var tabs = Light.portal.tabs;
	var tabList = L$(Light._TAB_LIST);
	for (var i=0,len=tabList.children.length;i<len;i++){
		var node = tabList.children[i];
		if (node && node.tagName == "LI" ){
			if ("current" == node.className){
				var last = tabList.children.length - 1;
				if(Light.hasPermission(Light.permission.PORTAL_TAB_ADD) && !Light.portal.mobile) last--;
				if(i < last){
				    var next = Light.portal.startTab + i + 1;
					Light.portal.tabs[next].focus();
				}else if(i == last){				
					var next = Light.portal.startTab + i + 1;
					if(next <= Light.portal.tabs.length - 1){
						if(next == Light.portal.tabs.length - 1){
							var right = L$('rightTabButton');
							right.className = "";
							right.onclick="";							
							right.style.MozOpacity=0.3;
							if(right.filters) left.filters.alpha.opacity=30;
						}
						tabList.removeChild(tabList.children[0]);						
						if(!Light.portal.tabs[next].opened){
							Light.portal.tabs[next].open();
						}else{
							var lastOne = document.getElementById('tabMenuAddTab');
							if(lastOne)
								tabList.insertBefore(Light.portal.tabs[next].container,lastOne);	  
							else
								tabList.appendChild(Light.portal.tabs[next].container);								
						}
						Light.portal.tabs[next].focus();
						Light.portal.startTab++;
					}
				}
			}
		}
	}
	if(Light.portal.startTab){
		var left = L$('leftTabButton');
		left.className = 'portal-tab-button';
		left.onclick = function(){Light.focusLeftTab();};
		left.style.MozOpacity=1;
		if(left.filters) left.filters.alpha.opacity=100;
	}
	if(Light.portal.startTab + Light.portal.data.maxShowTabs >= Light.portal.tabs.length){
		var right = L$('rightTabButton');
		right.className = "";
		right.onclick="";							
		right.style.MozOpacity=0.3;
		if(right.filters) left.filters.alpha.opacity=30;
	}
}
