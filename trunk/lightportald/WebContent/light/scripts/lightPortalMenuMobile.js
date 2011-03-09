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
//------------------------------------------------------------ lightPortalMenuView.js
LightPortalMenuImpl.prototype = new LightPortalMenu;                // Define View Menu
LightPortalMenuImpl.prototype.constructor = LightPortalMenuImpl;
function LightPortalMenuImpl(){
	LightPortalMenu.call(this);
}

LightPortalMenu.prototype.render = function(portal){	
	var menu = Light.newElement({element:'div',id:'portalMenu',className:'portal-header-menu'});	
	this.renderMenuItems(menu,portal);		
	menu.style.zIndex= ++Light.maxZIndex; 	
	menu.style.marginRight= 0; 
	return menu;
}

LightPortalMenu.prototype.renderMenuItemSignUp = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_SIGN_UP) && !Light.isLogin()){	   
	   menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showSignUp();},innerHTML:Light.getMessage('TITLE_SIGN_UP')}));	
	   return true;
	}	
	return false;   
}

LightPortalMenuImpl.prototype.renderMenuItemSignIn = function(menuItems,portal){		  
	if(Light.isLogin()){
		menuItems.appendChild(Light.newElement({
									element:'a',
									onclick:function(){Light.showMyAccount();},
									innerHTML:"<img src='"+Light.getContextPath()+"/light/images/myaccount.gif"+"' width='16' height='16' title='"+Light.getMessage('MENU_MY_ACCOUNT')+"' />"}));			  
	   
	   	menuItems.appendChild(Light.newElement({
	   								element:'a',
	   								onclick:function(){Light.logout();},
	   								innerHTML:"<img src='"+Light.getContextPath()+"/light/images/signout.gif"+"' width='16' height='16' title='"+Light.getMessage('MENU_SIGN_OUT')+"' />"}));			  
	}else if(Light.hasPermission(Light.permission.PORTAL_SIGN_IN)){	 
	   	menuItems.appendChild(Light.newElement({element:'a',style:{padding:0},onclick:function(){Light.showLogin(false);},innerHTML:Light.getMessage('MENU_SIGN_IN')}));	   	   	   
	   	menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("facebookConnect.view",null)}));	 
	   	menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("twitterLogin.view",null)})); 
	}
}

LightPortalMenu.prototype.renderMenuItemOptions = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_OPTIONS)){
	   menuItems.appendChild(Light.newElement({
	   							element:'a',
	   							onclick:function(){Light.showChangeOptions();},
	   							innerHTML:"<img src='"+Light.getContextPath()+"/light/images/options.png"+"' width='16' height='16' title='"+Light.getMessage('MENU_OPTIONS')+"' />"}));			     	   		   
	   return true;
	}    
	return false;   
}
LightPortalMenu.prototype.renderMenuItemContent = function(menuItems,portal){	
	return false;   
}

LightPortalMenu.prototype.renderMenuItemNoMenu = function(menuItems,portal){		
	menuItems.appendChild(Light.newElement({
							element:'a',
							onclick:function(){Light.toMyAccount();},
							innerHTML:Light.getMessage('MENU_MY_ACCOUNT')}));	
}

LightPortalMenu.prototype.renderMenuItemLanguage = function(menuItems,portal){
	if(Light.hasPermission(Light.permission.PORTAL_CHANGE_LANGUAGE)){
		menuItems.appendChild(Light.newElement({
	   								element:'a',
	   								onclick:function(){Light.showChangeLanguage();},
	   								innerHTML:"<img src='"+Light.getContextPath()+"/light/images/world.png"+"' width='16' height='16' title='"+Light.getMessage('TITLE_LANGUAGE')+"' />"}));		
	}
}

LightPortalMenu.prototype.renderMenuItemClose = function(menuItems,portal){		
	
}
