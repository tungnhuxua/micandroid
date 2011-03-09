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
//------------------------------------------------------------ lightPortalCore.js  Mobile override

LightPortal.prototype.resize = function (){
	if(!this.turnedOn) return;	
	this.layout.containerWidth = Light.getScreenWidth();
	this.layout.maxWidth = this.layout.containerWidth;
	this.layout.maxHeight = getWindowHeight();
	this.container.style.width = this.layout.containerWidth;
	this.container.style.height = this.layout.maxHeight;
	this.content.style.width = this.layout.maxWidth;
	var currentTab = Light.getCurrentTab();  
	this.refreshPortalMenu();
	currentTab.resize();
}
LightPortal.prototype.addTabMenu = function(serverId,tabParentId){
}

LightPortal.prototype.addLeftButton = function(){	
}

LightPortal.prototype.addRightButton = function(){
	if(!L$('rightMoreTabButton')){
		var container = Light.newElement({
									element:'span',
									id:'rightMoreTabButton',
									className:'portal-tab-handle',
									style:{marginLeft:'10'},
									innerHTML:Light.getViewTemplate('more.menu'),
									onclick:function(){									
										showMoreTab();
									}
							});	
		L$('tabs').appendChild(container);	
	}
}

showMoreTab= function(){	
	var data = {
       	tabs : Light.portal.tabs
	};
	var tab = L$('rightMoreTabButton');

    var e = {
       pageX : getX(tab)-5,
       pageY : getY(tab)+20
    };       
    var width = 150;
    if(e.pageX + width > Light.getScreenWidth())
		e.pageX = Light.getScreenWidth() - width;
    createTopPopupDiv('viewMoreTab','viewTab.jst',width,data,e,null,true);
    L$('viewMoreTab').style.padding=0;
    setTimeout((function() {addEventHandler(document,"click",hideMoreTab)}), 1000);
	setTimeout((function() {hideMoreTab();}), 8000);	  
}

hideMoreTab= function(){	
	hideTopPopupDiv('viewMoreTab');
	removeEvent(document,"click",hideMoreTab);  
}
