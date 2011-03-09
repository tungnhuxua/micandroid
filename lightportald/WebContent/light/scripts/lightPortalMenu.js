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
//------------------------------------------------------------ lightPortalMenu.js
function LightPortalMenu(){}

LightPortalMenu.prototype.render = function(portal){	
	var menu = Light.newElement({element:'div',id:'portalMenu',className:'portal-header-menu'});	
	this.renderMenuItems(menu,portal);		
	menu.style.zIndex= ++Light.maxZIndex; 	
	return menu;
}

LightPortalMenu.prototype.renderSearchBar = function(menu,portal){
	if(portal.data.showSearchBar){
		menu.appendChild(Light.newElement({element:'span',className:'portal-header-search',innerHTML:Light.getViewTemplate("searchBar.view",null)}));
	}    
}

LightPortalMenu.prototype.renderMenuItems = function(menu,portal){	
	var menuItems = Light.newElement({element:'span',className:'portal-header-menu-item'});        
	this.renderMenuItemUser(menuItems,portal);
	this.renderMenuItemOptions(menuItems,portal);
	this.renderMenuItemContent(menuItems,portal);
	this.renderMenuItemSignUp(menuItems,portal);  
	this.renderMenuItemSignIn(menuItems,portal);  	
    this.renderMenuItemClose(menuItems,portal);
    //this.renderMenuItemCollapse(menuItems,portal);
	this.renderMenuItemLanguage(menuItems,portal); 
	this.renderSearchBar(menuItems,portal);
	menu.appendChild(menuItems);        
}

LightPortalMenu.prototype.renderMenuItemUser = function(menuItems,portal){		
	if(Light.isLogin()){
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:portal.data.title}));
	   //menuItems.appendChild(Light.newElement({element:'span',className:'portal-header-menu-item-separater'}));
	   return true;
	}    
	return false;   
}

LightPortalMenu.prototype.renderMenuItemOptions = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_OPTIONS)){
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showChangeOptions();},innerHTML:Light.getMessage('MENU_OPTIONS')}));
	   return true;
	}    
	return false;   
}
LightPortalMenu.prototype.renderMenuItemContent = function(menuItems,portal){	
	tab = Light.currentTab;	
	if(Light.hasPermission(Light.permission.PORTAL_CONTENT_ADD) && ( tab == null || tab.allowAddContent)){  	   
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showAddContent();},innerHTML:Light.getMessage('MENU_ADD_CONTENT')}));
	   return true;
	}    
	return false;   
}
LightPortalMenu.prototype.renderMenuItemSignUp = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_SIGN_UP) && !Light.isLogin()){	   
	   	menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showSignUp();},innerHTML:Light.getMessage('MENU_SIGN_UP')}));	
	   	menuItems.appendChild(Light.newElement({element:'span',className:'portal-header-menu-item-separater'}));   		   
	  	return true;
	}	
	return false;   
}
LightPortalMenu.prototype.renderMenuItemSignIn = function(menuItems,portal){		
	if(Light.isLogin()){
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showMyAccount();},innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.logout();},innerHTML:Light.getMessage('MENU_SIGN_OUT')}));		    		   
   }else if(Light.hasPermission(Light.permission.PORTAL_SIGN_IN)){	
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showLogin(false);},innerHTML:Light.getMessage('MENU_SIGN_IN')}));	   	   	   
	   menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("facebookConnect.view",null)}));
	   menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("twitterLogin.view",null)}));
   }   
}

LightPortalMenu.prototype.renderMenuItemNoMenu = function(menuItems,portal){		
	menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));	
}

LightPortalMenu.prototype.renderMenuItemClose = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_TURN_OFF) || L$('WIDGET_MODE') != null){   	   
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.portal.shutdown();},innerHTML:Light.getMessage('MENU_TURN_OFF')}));   
	}
}

LightPortalMenu.prototype.renderMenuItemCollapse = function(menuItems,portal){
	if(Light.isLogin()){
	   menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.portal.collapseAll();},innerHTML:"<input type='button' title='"+Light.getMessage('MENU_COLLAPSE_ALL')+"' class='icons_collapseAll' value=''></input>"}));	   
	   menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.portal.expandAll();},innerHTML:"<input type='button' title='"+Light.getMessage('MENU_EXPAND_ALL')+"' class='icons_expandAll' value=''></input>"}));
	   menuItems.appendChild(Light.newElement({element:'span',className:'portal-header-menu-item-separater'}));	   
	}   
}

LightPortalMenu.prototype.renderMenuItemLanguage = function(menuItems,portal){
	if(Light.hasPermission(Light.permission.PORTAL_CHANGE_LANGUAGE)){
		menuItems.appendChild(Light.newElement({element:'span',onclick:function(){Light.showChangeLanguage();},innerHTML:"<input type='button' title='"+Light.getMessage('TITLE_LANGUAGE')+"' class='icons_world' value=''></input>"}));	   	   
	}
}

Light.showLogin = function(flag){
    if(flag == null || flag) Light.addFunctionHistory("Light.showLogin");
    if(Light.showCurrentPopupWindow("login")) return;
	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","loginPortlet","/loginPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,true,5,2);    
    portlet.refresh();
}

Light.showLocalLogin = function(flag){
    if(flag == null || flag) Light.addFunctionHistory("Light.showLocalLogin");
 	if(Light.showCurrentPopupWindow("localLogin")) return;
	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","localLogin","/localLogin.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);    
    portlet.refresh();
    Light.portal.latestAction.portlet= portlet;
}

Light.showAllLogin = function(flag){
    if(flag == null || flag) Light.addFunctionHistory("Light.showAllLogin");
	if(Light.showCurrentPopupWindow("allLogin")) return;
 	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_SIGN_IN'),"icons user","","allLogin","/loginPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);    
    portlet.refresh();
    window.scrollTo(0,0);
    Light.portal.latestAction.portlet= portlet;
}

Light.showMyAccount = function(){
 	//Light.addFunctionHistory("Light.showMyAccount");
 	if(Light.showCurrentPopupWindow("accountPortlet")) return;
 	var portlet = new PortletPopupWindow(11,50,1000,Light.getMessage('MENU_MY_ACCOUNT'),"icons user","","accountPortlet","/accountPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,0,2);    
    portlet.refresh();    
}
Light.showChangeLanguage= function(){
    Light.addFunctionHistory("Light.showChangeLanguage");
    if(Light.showCurrentPopupWindow("changeLanguagePortlet")) return;
 	var portlet = new PortletPopupWindow(11,200,750,Light.getMessage('TITLE_LANGUAGE'),"icons language","","changeLanguagePortlet","/changeLanguagePortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false);    
    portlet.refresh();
}
Light.showChangeOptions = function(){
   // Light.addFunctionHistory("Light.showChangeOptions");
    if(Light.showCurrentPopupWindow("optionsPortlet")) return;
	var portlet = new PortletPopupWindow(11,50,1000,Light.getMessage('MENU_OPTIONS'),"icons options","","optionsPortlet","/optionsPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,0,2);    
    portlet.refresh();
}
Light.showAddContent = function(){ 
    //Light.addFunctionHistory("Light.showAddContent");
    if(Light.showCurrentPopupWindow("contentPortlet")) return;
 	var portlet = new PortletPopupWindow(11,0,400,Light.getMessage('MENU_ADD_CONTENT'),"icons application_add","","contentPortlet","/contentPortlet.lp",true,false,false,false,false,false,false,0,false,'','','','',false,false,false,1,2);    
    portlet.refresh();
}

Light.showCurrentPopupWindow = function(name){
	var tab = Light.getCurrentTab();
	var portlet = Light.getPopupPortletByName(name);
	if(portlet){
		if(portlet.parent.serverId != tab.serverId){
			portlet.close();
			return false;
		}else{
			if(portlet.popup){
				portlet.show();
				portlet.parent.hideOtherPortlets(portlet);
			}			
			Light.portal.allPortlets[i] = null;
			window.scrollTo(0,0);
			Light.portal.allPortlets.push(portlet);		
			return true;
		}
	}
	return false;
}
Light.globalSearch= function(keyword,type){
	Light.addFunctionHistory('Light.globalSearch');
	if(Light.showCurrentPopupWindow("globalSearchPortlet")) return;
	var portlet = new PortletPopupWindow(11,0,400,Light.getMessage('MENU_SEARCH'),"icons search","","globalSearchPortlet","/globalSearchPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','','',false,false,true,5,2);  
	var param = 'action=find';
	if(type) param+=';type='+type;
	if(keyword) param+=';keyword='+keyword;
    Light.executeRender(portlet.id,"view","maximized",param,null,true,false);    
}