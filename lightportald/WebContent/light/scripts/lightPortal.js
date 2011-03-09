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
//------------------------------------------------------------ lightPortal.js
function LightPortal() {		
  	this.layout={
  		containerLeft: Light.getContainerLeft(),
  		containerTop : 0,
		containerWidth: Light.getContainerWidth(), 
	  	bodyLeft: 0,	  	
	  	bodyTop : 80,
	   	maxLeft : 0,
    	maxTop : 40, 
    	maxWidth : Light.getContainerWidth(),
    	maxHeight : getWindowHeight(),
    	minHeight : 40,
        scrollbarWidth : Light.getScrollbarWidth(),
        barWidth : Light.getBarWidth(),
        footerMarginTop : 100
  	}
  	this.tabs= [];  
  	this.allTabs = [];
  	this.allPortlets = [];  	
  	this.latestAction = Light.getLatestActionObject();
  	this.highLightBar = Light.getHighLightBar(); 
  	this.progressBar = Light.getProgressBar();  	
  	this.moveTimer = -1;
}