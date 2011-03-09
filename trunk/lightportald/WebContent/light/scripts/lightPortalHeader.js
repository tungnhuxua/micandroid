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
//------------------------------------------------------------ lightPortalHeader.js
function LightPortalHeader(){}
LightPortalHeader.prototype.render= function(){}

LightPortalHeaderImpl.prototype = new LightPortalHeader;                // Define main header
LightPortalHeaderImpl.prototype.constructor = LightPortalHeader;
function LightPortalHeaderImpl(){
	LightPortalHeader.call(this);
}
LightPortalHeaderImpl.prototype.render = function (portal){
	log("render Portal: create portal header");
	var header = document.createElement('div');
	header.id="portalHeader";
	header.className = "portal-header";   
	if(portal.data.headerImage){ 
		if(portal.data.headerImage != "no"){
		    var headerImage = portal.data.headerImage;
		    if(headerImage.indexOf("http") < 0)
		      	headerImage = Light.getContextPath()+headerImage;			
			if(portal.data.headerRepeat == 1)
				header.style.background = "url('"+headerImage+"') no-repeat " + portal.data.headerPosition;// no-repeat left top";
			else if(portal.data.headerRepeat == 2)
         		header.style.background = "url('"+headerImage+"') repeat-x right top";
         	else if(portal.data.headerRepeat == 3)
         		header.style.background = "url('"+headerImage+"') repeat-y left top";
         	else{
				header.style.background= "url('"+headerImage+"')";
				header.style.backgroundRepeat= "repeat-x";
			}
		}else{
			header.style.backgroundColor= "";         
		}  
	}else{
		header.style.backgroundColor= "";         
	}
	header.innerHTML= Light.getViewTemplate("header.view");   
   	header.style.zIndex= ++Light.maxZIndex;              
   	return header;
}