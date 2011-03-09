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
//------------------------------------------------------------ lightPortalMenuSubNetwork.js
LightPortalMenuImpl.prototype = new LightPortalMenu;                // Define Sub Network Menu
LightPortalMenuImpl.prototype.constructor = LightPortalMenuImpl;
function LightPortalMenuImpl(){
	LightPortalMenu.call(this);
}
LightPortalMenuImpl.prototype.renderMenuItemSignIn = function(menuItems,portal){		
	if(Light.hasPermission(Light.permission.PORTAL_SIGN_IN)){	   		
		if(Light.isLogin()){
		   	menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.logout();},innerHTML:Light.getMessage('MENU_SIGN_OUT')}));			  
		}else{
		   	menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.toMyAccount();},innerHTML:Light.getMessage('MENU_HOME')}));
	       	menuItems.appendChild(Light.newElement({element:'a',onclick:function(){Light.showAllLogin(false);},innerHTML:Light.getMessage('MENU_SIGN_IN')}));
		   	menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("facebookConnect.view",null)}));	   	 
		   	menuItems.appendChild(Light.newElement({element:'span',innerHTML:Light.getViewTemplate("twitterLogin.view",null)}));  	   
		}		
		return true;
	}	
	return false;   
}