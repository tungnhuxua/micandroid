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
//------------------------------------------------------------ light.js Mobile override
Light.getScreenWidth = function(){
	var screenWidth = document.documentElement.scrollWidth - 5;
	return screenWidth;
}
//------------------------------------------------------------ lightPortal.js Mobile override
function LightPortal() {		
  	this.layout={
  		containerLeft: 2,
  		containerTop : 0,
		containerWidth: Light.getScreenWidth(), 
	  	bodyLeft: 0,	  	
	  	bodyTop : 80,
	   	maxLeft : 0,
    	maxTop : 40, 
    	maxWidth : Light.getScreenWidth(),
    	maxHeight : getWindowHeight(),
    	minHeight : 40,
        scrollbarWidth : 0,
        barWidth : 0,
        footerMarginTop : 100
  	}
  	this.tabs= [];  
  	this.allTabs = [];
  	this.allPortlets = [];  	
  	this.latestAction = Light.getLatestActionObject();
  	this.highLightBar = Light.getHighLightBar(); 
  	this.progressBar = Light.getProgressBar();  	
  	this.moveTimer = -1;
  	this.mobile = true;
}
