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
//------------------------------------------------------------ lightWindow.js
function WindowSkin(){
	this.left = 0;
	this.top = 30 + (isIE() ? 10 : 0);
}
WindowSkin.prototype.bind= function(portlet){}
WindowSkin.prototype.render= function(portlet){}
WindowSkin.prototype.focus = function(portlet){}
WindowSkin.prototype.show = function(portlet){}
WindowSkin.prototype.hide = function(portlet){}
WindowSkin.prototype.position = function(portlet){}
WindowSkin.prototype.minimize = function(portlet){}
WindowSkin.prototype.maximize = function(portlet){}
WindowSkin.prototype.close = function(portlet){}
WindowSkin.prototype.refreshWindow= function (portlet){}
WindowSkin.prototype.refreshHeader= function (portlet) {}
WindowSkin.prototype.refreshButtons = function(portlet){}
WindowSkin.prototype.setContent = function(content){
	this.container.innerHTML = content;	
}
WindowSkin.prototype.getRefreshButton = function(){
	return "<div title='"+Light.getMessage('REFRESH_PORTLET')+"' class='icons refresh_on'" 
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
WindowSkin.prototype.getEditButton = function(){
	return "<div title='"+Light.getMessage('EDIT_MODE')+"' class='icons edit_on'"
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";  
}
WindowSkin.prototype.getCancelEditButton = function(){
	return "<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_edit_on'"
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";   
}
WindowSkin.prototype.getHelpButton = function(){
	return "<div title='"+Light.getMessage('HELP_MODE')+"' class='icons help_on'" 
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";   
}
WindowSkin.prototype.getCancelHelpButton = function(){
	return "<div title='"+Light.getMessage('VIEW_MODE')+"'  class='icons leave_help_on'" 
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";    
}
WindowSkin.prototype.getConfigButton = function(){
	return "<div title='"+Light.getMessage('CONFIG_MODE')+"' class='icons config_on'"
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";  
}
WindowSkin.prototype.getCancelConfigButton = function(){
	return "<div title='"+Light.getMessage('VIEW_MODE')+"' class='icons leave_config_on'" 
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
WindowSkin.prototype.getMinButton = function(){
	return "<div title='"+Light.getMessage('MINIMIZED')+"' class='icons min_on'"
			+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
			+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";  
}
WindowSkin.prototype.getMaxButton = function(){
	return "<div title='"+Light.getMessage('MAXIMIZED')+"' class='icons max_on'"
		+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
		+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";  
}
WindowSkin.prototype.getRestoreButton = function(){
	return "<div title='"+Light.getMessage('RESTORE')+"' class='icons restore_on'" 
		+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
		+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
WindowSkin.prototype.getPopoutButton = function(){
	return "<div title='"+Light.getMessage('POPOUT')+"' class='icons pop_out'"
		+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
		+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
WindowSkin.prototype.getPopinButton = function(){
	return "<div title='"+Light.getMessage('POPIN')+"' class='icons pop_in'"
		+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
		+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
WindowSkin.prototype.getCloseButton = function(){
	return "<div title='"+Light.getMessage('CLOSE')+"' class='icons close_on'" 
		+ " onmouseover='this.style.MozOpacity=1;if(this.filters)this.filters.alpha.opacity=100'"
		+ " onmouseout='this.style.MozOpacity=0.5;if(this.filters)this.filters.alpha.opacity=50'></div>";
}
//------------------------------------------------------------ WindowSkin1.js

WindowSkin1.prototype = new WindowSkin;                // Define sub-class
WindowSkin1.prototype.constructor = WindowSkin1;

function WindowSkin1(){
	WindowSkin.call(this);
	this.type = "WindowSkin1";
	this.top = 40 + (isIE() ? 10 : 0);
	this.layout={	     	    
	    titleRelativeLeft : 10,
	    titleRelativeTop : -8, 
	    buttonRelative : 16,
	    upRelativeRight : 94,
	    downRelativeRight : 78, 
	    minRelativeRight : 52,
	    maxRelativeRight : 36,
	    closeRelativeRight : 20,
	    buttonRelativeTop : -6
	}
}
WindowSkin1.prototype.render= function(portlet){
   	this.renderContainer(portlet);    
   	this.renderHeader(portlet);    
}
WindowSkin1.prototype.renderContainer= function(portlet){
   	this.container = this.createPortletContainer(portlet);    
   	this.window = this.container;
   	var vdocument = L$('panel_'+portlet.parent.serverId);
   	vdocument.appendChild(this.container); 
}
WindowSkin1.prototype.renderHeader= function(portlet){  
	this.header = this.createPortletHeader(portlet);   
	var vdocument = L$('panel_'+portlet.parent.serverId);
	vdocument.appendChild(this.header);  
	if(portlet.closeable){
   	  	this.closeButton = this.createPortletCloseButton(portlet);
   	  	vdocument.appendChild(this.closeButton);
   	}
   	if(portlet.allowMaximized){
   		if(portlet.maximized){
      		this.maxButton = this.createPortletRestoreMaxButton(portlet); 
      		this.createPortletMaxButton(portlet);
  		}else{
  			this.maxButton = this.createPortletMaxButton(portlet); 
      		this.createPortletRestoreMaxButton(portlet);
  		}
      	vdocument.appendChild(this.maxButton);  
   	}
   	if(portlet.allowMinimized){
   		if(portlet.minimized){
      		this.minButton = this.createPortletRestoreMinButton(portlet);
      		this.createPortletMinButton(portlet);
  		}else{
  			this.minButton = this.createPortletMinButton(portlet);
      		this.createPortletRestoreMinButton(portlet);
  		}      	
      	vdocument.appendChild(this.minButton);
   	}
   if(portlet.configMode){
      if(portlet.mode == Light._CONFIG_MODE){
       	this.configButton = this.createPortletCancelConfigButton(portlet);         
       	this.createPortletConfigButton(portlet);
      }else{
       	this.configButton = this.createPortletConfigButton(portlet);         
       	this.createPortletCancelConfigButton(portlet);
      }       
      vdocument.appendChild(this.configButton);
   }   
   if(portlet.helpMode){
      if(portlet.mode == Light._HELP_MODE){
       	this.helpButton = this.createPortletCancelHelpButton(portlet);         
       	this.createPortletHelpButton(portlet);
      }else{
       	this.helpButton = this.createPortletHelpButton(portlet);         
       	this.createPortletCancelHelpButton(portlet);
      }                 
      vdocument.appendChild(this.helpButton);
   }   
   if(portlet.editMode){
      if(portlet.mode == Light._EDIT_MODE){
       	this.editButton = this.createPortletCancelEditButton(portlet);         
       	this.createPortletEditButton(portlet);
      }else{
       	this.editButton = this.createPortletEditButton(portlet);         
       	this.createPortletCancelEditButton(portlet);
      }                
      vdocument.appendChild(this.editButton);      
   }
   if(portlet.refreshMode){
      this.refreshButton = this.createPortletRefreshButton(portlet);   
      vdocument.appendChild(this.refreshButton);
   }
}
WindowSkin1.prototype.focus = function(portlet){
	var index = ++Light.maxZIndex;
    this.container.style.zIndex= index;
    Light.maxZIndex++;
    this.header.style.zIndex= ++index; 
    if(portlet.refreshMode){
    	this.refreshButton.style.zIndex= index;
    }
    if(portlet.editMode){
    	this.editButton.style.zIndex= index;
    }
    if(portlet.helpMode){
    	this.helpButton.style.zIndex= index;
    }
    if(portlet.configMode){
    	this.configButton.style.zIndex= index;
    }   
    if(portlet.allowMinimized){
    	this.minButton.style.zIndex= index;
    }
    if(portlet.allowMaximized){
    	this.maxButton.style.zIndex= index;     
    }
    if(portlet.closeable){
      this.closeButton.style.zIndex= index;   	  
    } 
}
WindowSkin1.prototype.show = function(portlet){
	this.hidden = false;
 	this.container.style.visibility = "visible";
 	this.header.style.visibility = "visible";
    if(portlet.refreshMode){
    	this.refreshButton.style.visibility = "visible";
    }
    if(portlet.editMode){
    	this.editButton.style.visibility = "visible";
    }
    if(portlet.helpMode){
    	this.helpButton.style.visibility = "visible";
    }
    if(portlet.configMode){
    	this.configButton.style.visibility = "visible";
    }
    if(portlet.allowMinimized){
    	this.minButton.style.visibility = "visible";
    }
    if(portlet.allowMaximized){
    	this.maxButton.style.visibility = "visible";    
    }
    if(portlet.closeable){
      this.closeButton.style.visibility = "visible";
    } 
}
 
WindowSkin1.prototype.hide = function(portlet){
	this.hidden = true;
 	this.container.style.visibility = "hidden";
 	this.header.style.visibility = "hidden";
    if(portlet.refreshMode){
    	this.refreshButton.style.visibility = "hidden";
    }
    if(portlet.editMode){
    	this.editButton.style.visibility = "hidden";
    }
    if(portlet.helpMode){
    	this.helpButton.style.visibility = "hidden";
    }
    if(portlet.configMode){
    	this.configButton.style.visibility = "hidden";
    } 
    if(portlet.allowMinimized){
    	this.minButton.style.visibility = "hidden";
    }
    if(portlet.allowMaximized){
    	this.maxButton.style.visibility = "hidden";
    }
    if(portlet.closeable){
      this.closeButton.style.visibility = "hidden";    
    } 
}
 
WindowSkin1.prototype.position = function(portlet){ 
   this.container.style.width = portlet.width; 
   if (document.all) {	
           this.container.style.posLeft = portlet.left;
           if(portlet.popup != null)
       	      portlet.top = portlet.top - portlet.marginTop;
       	   this.container.style.posTop = portlet.top;
           this.header.style.posLeft = portlet.left + this.layout.titleRelativeLeft;
       	   this.header.style.posTop = portlet.top + this.layout.titleRelativeTop;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
       	   if(portlet.configMode){
           	  this.configButton.style.posLeft = portlet.left + portlet.width - RelativeRight;
           	  this.configButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(portlet.helpMode){
           	  this.helpButton.style.posLeft = portlet.left + portlet.width - RelativeRight;
           	  this.helpButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(portlet.editMode){
           	  this.editButton.style.posLeft = portlet.left + portlet.width - RelativeRight;
           	  this.editButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(portlet.refreshMode){
           	  this.refreshButton.style.posLeft = portlet.left + portlet.width - RelativeRight;
           	  this.refreshButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
           }
           if(portlet.allowMinimized){
		   	   this.minButton.style.posLeft = portlet.left + portlet.width - this.layout.minRelativeRight;
	           this.minButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
           }
           if(portlet.allowMaximized){
	           this.maxButton.style.posLeft = portlet.left + portlet.width - this.layout.maxRelativeRight;
	           this.maxButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;                      
           }
           if(portlet.closeable){
              this.closeButton.style.posLeft = portlet.left + portlet.width - this.layout.closeRelativeRight;
      		  this.closeButton.style.posTop = portlet.top + this.layout.buttonRelativeTop;
    	   } 
    	}    
    	else {        
           this.container.style.left = portlet.left;
           this.container.style.top = portlet.top;
           this.header.style.left = portlet.left + this.layout.titleRelativeLeft;
       	   this.header.style.top = portlet.top + this.layout.titleRelativeTop;
       	   var RelativeRight = this.layout.minRelativeRight + this.layout.buttonRelative;
           if(portlet.configMode){
           	  this.configButton.style.left = portlet.left + portlet.width - RelativeRight;
           	  this.configButton.style.top = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(portlet.helpMode){
           	  this.helpButton.style.left = portlet.left + portlet.width - RelativeRight;
           	  this.helpButton.style.top = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
       	   if(portlet.editMode){
           	  this.editButton.style.left = portlet.left + portlet.width - RelativeRight;
           	  this.editButton.style.top = portlet.top + this.layout.buttonRelativeTop;
           	  RelativeRight = RelativeRight + this.layout.buttonRelative;
           }
           if(portlet.refreshMode){
           	  this.refreshButton.style.left = portlet.left + portlet.width - RelativeRight;
           	  this.refreshButton.style.top = portlet.top + this.layout.buttonRelativeTop;
           }
           if(portlet.allowMinimized){
	           this.minButton.style.left = portlet.left + portlet.width - this.layout.minRelativeRight;
	           this.minButton.style.top = portlet.top + this.layout.buttonRelativeTop;
           }
           if(portlet.allowMaximized){
	           this.maxButton.style.left = portlet.left + portlet.width - this.layout.maxRelativeRight;
	           this.maxButton.style.top = portlet.top + this.layout.buttonRelativeTop;           
           }
           if(portlet.closeable){
              this.closeButton.style.left = portlet.left + portlet.width - this.layout.closeRelativeRight;
              this.closeButton.style.top = portlet.top + this.layout.buttonRelativeTop;
    	   } 
    	}
    	this.focus(portlet);
}
 
WindowSkin1.prototype.minimize = function(portlet){    
   var vdocument = L$('panel_'+portlet.parent.serverId);
   if(portlet.minimized){    
    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletRestoreMinButton(portlet);
    vdocument.appendChild(this.minButton);        
  }else{      
    vdocument.removeChild(this.minButton);
    this.minButton = this.createPortletMinButton(portlet);
    vdocument.appendChild(this.minButton);        
  }  
  this.position(portlet);  
} 

WindowSkin1.prototype.maximize = function(portlet){     
   var vdocument = L$('panel_'+portlet.parent.serverId);   
   if(!portlet.maximized){        
		vdocument.removeChild(this.maxButton);
		this.maxButton = this.createPortletMaxButton(portlet);
		vdocument.appendChild(this.maxButton);
   }else{                
        vdocument.removeChild(this.maxButton);
    	this.maxButton = this.createPortletRestoreMaxButton(portlet);
    	vdocument.appendChild(this.maxButton);                      	
   }         
   this.position(portlet);  
}
 
WindowSkin1.prototype.close = function(portlet){   
   var vdocument = L$('panel_'+portlet.parent.serverId); 
   vdocument.removeChild(this.container);
   vdocument.removeChild(this.header);
   if(portlet.refreshMode){
      vdocument.removeChild(this.refreshButton);
   }
   if(portlet.editMode){
      vdocument.removeChild(this.editButton);
   }
   if(portlet.helpMode){
      vdocument.removeChild(this.helpButton);
   }
   if(portlet.configMode){
      vdocument.removeChild(this.configButton);
   }
   if(portlet.allowMinimized){
   	  vdocument.removeChild(this.minButton);
   }
   if(portlet.allowMaximized){
      vdocument.removeChild(this.maxButton);
   }
   vdocument.removeChild(this.closeButton);     
 } 
 
WindowSkin1.prototype.refreshWindow= function (portlet) { 
   var vdocument = L$('panel_'+portlet.parent.serverId);
   vdocument.removeChild(this.container);
   this.container = this.createPortletContainer(portlet);     
   vdocument.appendChild(this.container);
   this.refreshHeader(portlet);
   this.refreshButtons(portlet);
   this.position(portlet);
 }
 
WindowSkin1.prototype.refreshHeader= function (portlet) {
   var vdocument = L$('panel_'+portlet.parent.serverId);
   vdocument.removeChild(this.header);
   this.header = this.createPortletHeader(portlet);
   if (document.all) {	          
      this.header.style.posLeft = portlet.left + this.layout.titleRelativeLeft;
      this.header.style.posTop = portlet.top + this.layout.titleRelativeTop;
   }else{
      this.header.style.left = portlet.left + this.layout.titleRelativeLeft;
      this.header.style.top = portlet.top + this.layout.titleRelativeTop;
   }
   vdocument.appendChild(this.header);
   if(this.hidden) this.hide(portlet); 
}

WindowSkin1.prototype.refreshButtons = function(portlet){
 	var vdocument = L$('panel_'+portlet.parent.serverId);
    if(portlet.editMode){
    	vdocument.removeChild(this.editButton);
    	if(portlet.mode == Light._EDIT_MODE){
	    	this.editButton = this.createPortletCancelEditButton(portlet);                       
	    }else{
		    this.editButton = this.createPortletEditButton(portlet);
		}
		this.editButton.style.visibility = "hidden";
    }	
    if(portlet.helpMode){
    	vdocument.removeChild(this.helpButton);
    	if(portlet.mode == Light._HELP_MODE){
	    	this.helpButton = this.createPortletCancelHelpButton(portlet);                       
	    }else{
		    this.helpButton = this.createPortletHelpButton(portlet);
		}
		this.helpButton.style.visibility = "hidden";
    }
    if(portlet.configMode){
   		vdocument.removeChild(this.configButton);
   		if(portlet.mode == Light._CONFIG_MODE){
	    	this.configButton = this.createPortletCancelConfigButton(portlet);                       
	    }else{
		    this.configButton = this.createPortletConfigButton(portlet);		    
		}
		this.configButton.style.visibility = "hidden";
   	}
	if(portlet.editMode) vdocument.appendChild(this.editButton);
	if(portlet.helpMode) vdocument.appendChild(this.helpButton);
	if(portlet.configMode) vdocument.appendChild(this.configButton);
	this.position(portlet);
	if(portlet.editMode)  this.editButton.style.visibility = "visible";   
	if(portlet.helpMode) this.helpButton.style.visibility = "visible";        
	if(portlet.configMode) this.configButton.style.visibility = "visible";                
}

WindowSkin1.prototype.createPortletContainer = function (portlet){   
	var vContainer = Light.newElement({element:'div',
										id:portlet.id,
										className: (portlet.className) ? portlet.className : "portlet",
										style:{position:"absolute",
											width:portlet.width,
											zIndex:++Light.maxZIndex
										}
									});      
   	if(portlet){
    	if(portlet.contentBgColor)
   	  		vContainer.style.backgroundColor = portlet.contentBgColor;
   		else if(Light.portal.data.portletWindowTransparent == false && portlet.transparent == false)
      		vContainer.style.backgroundColor = "#ffffff";
   	}
   	return vContainer;
} 
 
WindowSkin1.prototype.createPortletHeader = function (portlet) {
	var vHeader = Light.newElement({element:'div',
									className:"portlet-header",
									style:{position:"absolute"
										}
								});									   
   	if(Light.getCurrentTab().isMoveable){
   		vHeader.onmousedown = function(e){ portlet.moveBegin(e);}   	
        vHeader.style.cursor= "move";
   	}
   	var inner = "";
   	if(portlet.showIcon && portlet.icon){
      	if(portlet.icon.indexOf("/") >= 0){
	      	if(portlet.icon.substring(0,4) == "http")
	      		inner = "<img src='"+portlet.icon+"' height='16' width='16' />";
	      	else
	        	inner = "<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";
	  	}else{
	  		var css = portlet.icon.split(" ");
	  		var cssParent ="";
	  		if(css.length > 1) cssParent = css[0];
	  		inner = "<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";
	  	}
   	}
   	if(portlet.url){
      	inner = inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";
      	if(portlet.barFontColor)
         	inner = inner+"<font color='"+portlet.barFontColor+"'>";
      	inner = inner+portlet.title;
      	if(portlet.barFontColor)
         	inner = inner+"</font>";
      	inner = inner+"</a>";
   	}else
      	inner = inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";   
   	vHeader.innerHTML = inner;
   	vHeader.style.zIndex= Light.maxZIndex++; 
   	if(portlet.barBgColor){
      	vHeader.style.backgroundImage="none";
   	  	vHeader.style.backgroundColor = portlet.barBgColor;
   	}
   	if(portlet.barFontColor)
   	  	vHeader.style.color = portlet.barFontColor;    
   return vHeader;
}
   
WindowSkin1.prototype.createPortletRefreshButton = function (portlet){ 
   	var vButton = Light.newElement({element:'div',className:"portlet-header-button",style:{zIndex:Light.maxZIndex}});					
   	var varA = Light.newElement({element:'span',
								className:"icons",
								innerHTML:this.getRefreshButton(),
								onclick:function(){
							     	portlet.refresh();
							   	}
								});	
   	vButton.appendChild(varA);
   	return vButton;
}
  
WindowSkin1.prototype.createPortletEditButton = function (portlet){ 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getEditButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.edit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
  
WindowSkin1.prototype.createPortletCancelEditButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getCancelEditButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.cancelEdit();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}

WindowSkin1.prototype.createPortletHelpButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getHelpButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.help();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
  
WindowSkin1.prototype.createPortletCancelHelpButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getCancelHelpButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.cancelHelp();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}

WindowSkin1.prototype.createPortletConfigButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getConfigButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.config();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
  
WindowSkin1.prototype.createPortletCancelConfigButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getCancelConfigButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.cancelConfig();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
    
WindowSkin1.prototype.createPortletMinButton = function (portlet){ 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getMinButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
  
WindowSkin1.prototype.createPortletRestoreMinButton = function (portlet) { 
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getRestoreButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.minimize();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}

WindowSkin1.prototype.createPortletMaxButton = function (portlet){   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getMaxButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
  }
  
WindowSkin1.prototype.createPortletRestoreMaxButton = function (portlet){   
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getRestoreButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.maximize();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
WindowSkin1.prototype.createPortletCloseButton = function (portlet){      
   var vButton = document.createElement('div');
   vButton.className = "portlet-header-button";
   var varA = document.createElement('span');
   varA.innerHTML = this.getCloseButton();
   varA.className = "icons";
   varA.onclick = function(){
     portlet.close();
   }
   vButton.appendChild(varA);
   vButton.style.zIndex= Light.maxZIndex;
   return vButton;
}
WindowSkin1.prototype.setContent = function(content){
	this.container.innerHTML = "<br/>"+content;	
}
//------------------------------------------------------------------------------------------------------ WindowSkin2.js 
WindowSkin2.prototype = new WindowSkin;       // Define sub-class
WindowSkin2.prototype.constructor = WindowSkin2;

function WindowSkin2(){
	this.type = "WindowSkin2";	
}
WindowSkin2.prototype.render= function(portlet){
	this.window = this.createPortletWindow(portlet);
	this.container = this.createPortletContainer(portlet); 
	this.header = this.createPortletHeader(portlet,this);     
	this.headerButton = this.createPortletHeaderButton(portlet);          		
	if(this.headerButton != null)
		this.header.appendChild(this.headerButton);     
	this.window.appendChild(this.header); 
	this.window.appendChild(this.container); 
	var vdocument = L$('panel_'+portlet.parent.serverId);   
	if(typeof portlet.popup != "undefined" && !portlet.popup){
		if(typeof portlet.location != "undefined" && portlet.location == 1){
			Light.portal.container.appendChild(this.window);
			Light.portal.content.style.marginLeft= '0px';
		}else if(typeof portlet.location != "undefined" && portlet.location == 0){
			Light.portal.container.insertBefore(this.window, Light.portal.header);
			Light.portal.content.className='';			
		}else
			vdocument.appendChild(this.window);
	}else if(portlet.popup && portlet.location && portlet.location == 4){
		this.window.style.zIndex= Light.maxZIndex+10000;
		document.body.appendChild(this.window);	
	}else
		vdocument.appendChild(this.window);
}
WindowSkin2.prototype.bind= function(portlet){
	this.window = L$(Light._PW_PREFIX+portlet.serverId);
	this.container = L$(portlet.id); 
	this.header = L$(Light._PH_PREFIX+portlet.serverId);    
	this.headerButton = L$(Light._PB_PREFIX+portlet.serverId);	
}
WindowSkin2.prototype.focus = function(portlet){
    var index = ++Light.maxZIndex;
	if(portlet.popup != null && portlet.popup && portlet.isNew == null)
	 	index = index + 1000;
	if(portlet.location != 1)
	    this.window.style.zIndex= index;
	else
		this.window.style.zIndex= 0;
}
 
WindowSkin2.prototype.show = function(portlet){
	this.hidden = false;
 	this.window.style.visibility = "visible";
}
 
WindowSkin2.prototype.hide = function(portlet){
	this.hidden = true;
 	this.window.style.visibility = "hidden";  
}
 
WindowSkin2.prototype.position = function(portlet){ 
	this.window.style.width = portlet.width; 
	this.header.style.width = portlet.width; 
	this.container.style.width = portlet.width; 
	var top = portlet.top;
    if(typeof portlet.popup != "undefined")
    	if(!portlet.popup){
    		if(typeof portlet.location != "undefined" && portlet.location ==1)
    			top = portlet.top + 20;
    	}//else			
       		//top = portlet.top - portlet.marginTop;  
	if (document.all) {	
		this.window.style.posLeft = portlet.left;
		this.window.style.posTop = top;       	   
	}    
	else {        
		this.window.style.left = portlet.left;
		this.window.style.top = top;
	}
	this.focus(portlet);
 }
 
WindowSkin2.prototype.minimize = function(portlet){     
  this.window.removeChild(this.header);
  this.header = this.createPortletHeader(portlet,this);
  this.headerButton = this.createPortletHeaderButton(portlet);
  this.header.appendChild(this.headerButton);
  this.window.insertBefore(this.header, this.container);  

  this.position(portlet);  

 } 

WindowSkin2.prototype.maximize = function(portlet){     
   this.window.removeChild(this.header);
   this.header = this.createPortletHeader(portlet,this);
   this.headerButton = this.createPortletHeaderButton(portlet);
   this.header.appendChild(this.headerButton);
   this.window.insertBefore(this.header, this.container);  
   
   this.position(portlet);  
 }
 
WindowSkin2.prototype.close = function(portlet){   
	var vdocument = L$('panel_'+portlet.parent.serverId); 
	this.window.removeChild(this.header);
	this.window.removeChild(this.container);
	Light.portal.content.className='portal-content';
	if(typeof portlet.popup != "undefined" && !portlet.popup && portlet.isNew == null){
		Light.portal.container.removeChild(this.window);
	}else if(portlet.popup && portlet.location && portlet.location == 4){
		document.body.removeChild(this.window);
	}else
		vdocument.removeChild(this.window);
 } 
 
WindowSkin2.prototype.refreshWindow= function (portlet){ 
   this.window.removeChild(this.header);
   this.window.removeChild(this.container);
   this.header = this.createPortletHeader(portlet,this);
   this.headerButton = this.createPortletHeaderButton(portlet);
   this.container = this.createPortletContainer(portlet);
   this.header.appendChild(this.headerButton); 
   this.window.appendChild(this.header); 
   this.window.appendChild(this.container); 
   this.position(portlet);
 }
 
WindowSkin2.prototype.refreshHeader= function (portlet){
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(portlet,this);
	this.headerButton = this.createPortletHeaderButton(portlet);
	this.header.appendChild(this.headerButton); 
	this.window.insertBefore(this.header, this.container); 
	if(this.hidden) this.hide(portlet); 
}

WindowSkin2.prototype.refreshButtons = function(portlet){	
	this.window.removeChild(this.header);
	this.header = this.createPortletHeader(portlet,this);
	this.headerButton = this.createPortletHeaderButton(portlet);
	this.header.appendChild(this.headerButton);      
	this.window.insertBefore(this.header, this.container);
	this.position(portlet);
 }
 
WindowSkin2.prototype.createPortletWindow = function (portlet){
	var vWindow = document.createElement('div');
	vWindow.id = Light._PW_PREFIX+portlet.serverId;
	if(typeof portlet.popup != "undefined" && !portlet.popup && typeof portlet.location != "undefined" && portlet.location != 1)
		vWindow.style.margin = "0px ";
	else
   		vWindow.style.position = "absolute";
	vWindow.className = "portlet2"; 
	if(portlet.location != 1)   
		vWindow.style.zIndex= ++Light.maxZIndex;
	if(portlet && portlet.contentBgColor && portlet.contentBgColor.length > 0)
   	  vWindow.style.backgroundColor = portlet.contentBgColor;
    else if(Light.portal.data.portletWindowTransparent == false && portlet.transparent == false)
      vWindow.style.backgroundColor = "#ffffff";
	return vWindow;
}
  
WindowSkin2.prototype.createPortletHeader= function (portlet,window){
   var vHeader = document.createElement('div');
   vHeader.id=Light._PH_PREFIX+portlet.serverId;
   if(!portlet.minimized){
		vHeader.className = "portlet2-header";
		if(portlet.barBgColor){
	        vHeader.style.backgroundImage="none";
	   	 	vHeader.style.backgroundColor = portlet.barBgColor;   
	   }
   }else{
   		vHeader.className = "portlet2-header-min";
   }
   if((Light.currentTab.isMoveable && portlet.allowMove) || (typeof portlet.popup != "undefined" && portlet.popup)){
   	  vHeader.onmousedown = function(e){ 
   	  	portlet.focus();
   	  	portlet.moveBegin(e);
   	  }   	
      vHeader.style.cursor= "move";
   }else{
   		vHeader.onmousedown = function(e){
      		portlet.focus();   
   		}
   }
   vHeader.onmouseover = function(e){    		
   		window.headerButton.style.visibility = "visible";
   } 
   vHeader.onmouseout = function(e){ 
   		window.headerButton.style.visibility = "hidden";
   }
               
   var vTitle = document.createElement('span');
   var inner = "";
   if(portlet.showIcon && portlet.icon){
   	  if(portlet.icon.indexOf("/") >= 0){
	      if(portlet.icon.substring(0,4) == "http")
	      	inner = "<img src='"+portlet.icon+"' height='16' width='16' />";
	      else
	        inner = "<img src='"+Light.getContextPath()+portlet.icon+"' height='16' width='16'/>";
	  }else{
	  		var css = portlet.icon.split(" ");
	  		var cssParent ="";
	  		if(css.length > 1) cssParent = css[0];
	  		inner = "<span class='"+cssParent+"'><span class='"+portlet.icon+"'></span></span>";
	  }
   }
   if(portlet.url){
      inner = inner+"<a href='"+portlet.url+"' target='_blank' style='margin-left:5px;'>";
      if(portlet.barFontColor)
         inner = inner+"<font color='"+portlet.barFontColor+"'>";
      inner = inner+portlet.title;
      if(portlet.barFontColor)
         inner = inner+"</font>";
      inner = inner+"</a>";
   }else
      inner = inner+"<label style='margin-left:5px;'>"+portlet.title+"</label>";
   vTitle.innerHTML = inner;
   vTitle.className = "portlet2-header-title";
   if(portlet.barFontColor)
   	  vTitle.style.color = portlet.barFontColor;       
   vHeader.appendChild(vTitle);
   
   return vHeader;
  }

  WindowSkin2.prototype.createPortletHeaderButton= function (portlet){ 
   var vButton = document.createElement('div');
   vButton.id=Light._PB_PREFIX+portlet.serverId;
   vButton.className = "portlet2-header-button";
   vButton.style.visibility = "hidden";
	   
   if(portlet.closeable){
	   var clsA = document.createElement('span');
	   clsA.className = "icons";
	   clsA.innerHTML = this.getCloseButton();
	   clsA.onclick = function(){
	     portlet.close();
           portlet.moveAllowed();
	   }
       clsA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(clsA);
   }   
   if(portlet.allowMaximized){
	   var maxA = document.createElement('span');
	   maxA.className = "icons";
	   if(portlet.maximized){
	   	  maxA.innerHTML = this.getRestoreButton();
	   }else{
	      maxA.innerHTML = this.getMaxButton();
	   }
	   maxA.onclick = function(){
	     portlet.maximize();
	     portlet.moveAllowed();
	   }
	   maxA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(maxA);
   }
   if(portlet.allowMinimized){
	   var minA = document.createElement('span');
	   minA.className = "icons";
	   if(portlet.minimized){
		   minA.innerHTML = this.getRestoreButton();	   
	   }else{
	       minA.innerHTML = this.getMinButton();	   
	   }
	   minA.onclick = function(){
	     portlet.minimize();
	     portlet.moveAllowed();
	   }
	   minA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(minA);
   }
   if(portlet.configMode){
	   var config = document.createElement('span');	
	   config.className = "icons";   
	   if(portlet.mode == Light._CONFIG_MODE){
	       config.innerHTML = this.getCancelConfigButton();
		   config.onclick = function(){
		     portlet.cancelConfig();
                 portlet.moveAllowed();
		   }
	   }else{
	       config.innerHTML = this.getConfigButton();
	   	   config.onclick = function(){
		     portlet.config();
                 portlet.moveAllowed();
		   }	   
	   }
         config.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(config);
   } 
   if(portlet.helpMode){
	   var help = document.createElement('span');	
	   help.className = "icons";   
	   if(portlet.mode == Light._HELP_MODE){
	       help.innerHTML = this.getCancelHelpButton();
		   help.onclick = function(){
		     portlet.cancelHelp();
                 portlet.moveAllowed();
		   }
	   }else{
	       help.innerHTML = this.getHelpButton();
	   	   help.onclick = function(){
		     portlet.help();
                 portlet.moveAllowed();
		   }	   
	   }
           help.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(help);
   }
   if(portlet.editMode){
       var edit = document.createElement('span');
       edit.className = "icons";
       if(portlet.mode == Light._EDIT_MODE){
       	   edit.innerHTML = this.getCancelEditButton();
       	   edit.onclick = function(){
		     portlet.cancelEdit();
		     portlet.moveAllowed();
		   }
       }else{		   
		   edit.innerHTML = this.getEditButton();
		   edit.onclick = function(){
		     portlet.edit();
                 portlet.moveAllowed();
		   }
	   }
           edit.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(edit);
   }
   if(portlet.refreshMode){
	   var refresh = document.createElement('span');
	   refresh.className = "icons";
	   refresh.innerHTML = this.getRefreshButton();
	   refresh.onclick = function(){
	       portlet.refresh();
           portlet.moveAllowed();
	   }     
       refresh.onmousedown = function(){ portlet.moveCancel();}    
	   vButton.appendChild(refresh);
   }
   return vButton;
}
WindowSkin2.prototype.createPortletContainer = function (portlet){   
   var vContainer = document.createElement('div');
   vContainer.id = portlet.id;
   vContainer.onmousedown = function(){
      portlet.focus();   
   }   
   return vContainer;
}

//------------------------------------------------------------------------------------------------------ WindowSkin21.js 
WindowSkin21.prototype = new WindowSkin2;                // Define sub-class for chatting
WindowSkin21.prototype.constructor = WindowSkin21;

function WindowSkin21(){
	WindowSkin2.call(this);
}
WindowSkin21.prototype.createPortletContainer = function (portlet) {
   var vContainer = document.createElement('div');
   vContainer.id = portlet.id+"_container";
   vContainer.onmousedown = function(){
      portlet.focus();   
   }
   var vContent = document.createElement('div');
   vContent.className="chattingBox";
   vContent.id = portlet.id;
   vContainer.container = vContent;
   vContainer.appendChild(vContent);
   if(!portlet.minimized){
	   var vInput = document.createElement('div');
	   var data = {        
  	 		id : vContent.id
       };      
	   vInput.innerHTML=Light.getViewTemplate("chattingInput.jst",data);
	   vContainer.appendChild(vInput);
	}
   return vContainer;
}
WindowSkin21.prototype.createPortletHeaderButton= function (portlet) 
{  
   var vButton = document.createElement('div');
   vButton.className = "portlet2-header-button";
   vButton.id=Light._PB_PREFIX+portlet.serverId;   
   if(portlet.closeable){
	   var clsA = document.createElement('span');
	   clsA.className = "icons";
	   clsA.innerHTML = this.getCloseButton();
	   clsA.onclick = function(){
	     	portlet.close();
           	portlet.moveAllowed();
	   }
       clsA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(clsA);
   } 
   if(portlet.allowPopout){
	   var popA = document.createElement('span');
	   popA.className = "icons";
	   if(portlet.isPopout){
	   	  	popA.innerHTML = this.getRestoreButton();
	   }else{
	      	popA.innerHTML = this.getPopoutButton();
	   }
	   popA.onclick = function(){
	     	portlet.popout();
            portlet.moveAllowed();
	   }
       popA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(popA);
   }   
   if(portlet.allowMaximized){
	   var maxA = document.createElement('span');
	   maxA.className = "icons";
	   if(portlet.maximized){
	   	  	maxA.innerHTML = this.getRestoreButton();
	   }else{
	      	maxA.innerHTML = this.getMaxButton();
	   }
	   maxA.onclick = function(){
	     	portlet.maximize();
	    	portlet.moveAllowed();
	   }
	   maxA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(maxA);
   }
   if(portlet.allowMinimized){
	   var minA = document.createElement('span');
	   minA.className = "icons";
	   if(portlet.minimized){
		   	minA.innerHTML = this.getRestoreButton();
	   }else{
	      	minA.innerHTML = this.getMinButton();
	   }
	   minA.onclick = function(){
	     	portlet.minimize();
	     	portlet.moveAllowed();
	   }
	   minA.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(minA);
   }
   if(portlet.configMode){
	   var config = document.createElement('span');	
	   config.className = "icons";   
	   if(portlet.mode == Light._CONFIG_MODE){
	       config.innerHTML = this.getCancelConfigButton();
		   config.onclick = function(){
		     	portlet.cancelConfig();
                portlet.moveAllowed();
		   }
	   }else{
	       config.innerHTML = this.getConfigButton();
	   	   config.onclick = function(){
		     	portlet.config();
                portlet.moveAllowed();
		   }	   
	   }
         config.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(config);
   } 
   if(portlet.helpMode){
	   var help = document.createElement('span');	
	   help.className = "icons";   
	   if(portlet.mode == Light._HELP_MODE){
	       help.innerHTML = this.getCancelHelpButton();
		   help.onclick = function(){
		     	portlet.cancelHelp();
                portlet.moveAllowed();
		   }
	   }else{
	       help.innerHTML = this.getHelpButton();
	   	   help.onclick = function(){
		     	portlet.help();
                portlet.moveAllowed();
		   }	   
	   }
           help.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(help);
   }
   if(portlet.editMode){
       var edit = document.createElement('span');
       edit.className = "icons";
       if(portlet.mode == Light._EDIT_MODE){
       	   edit.innerHTML = this.getCancelEditButton();
       	   edit.onclick = function(){
		     	portlet.cancelEdit();
		     	portlet.moveAllowed();
		   }
       }else{		   
		   edit.innerHTML = this.getEditButton();
		   edit.onclick = function(){
		     	portlet.edit();
                portlet.moveAllowed();
		   }
	   }
           edit.onmousedown = function(){ portlet.moveCancel();}
	   vButton.appendChild(edit);
   }
   if(portlet.refreshMode){
	   var refresh = document.createElement('span');
	   refresh.className = "icons";
	   refresh.innerHTML = this.getRefreshButton();
	   refresh.onclick = function(){
	       portlet.refresh();
           portlet.moveAllowed();
	   }     
       refresh.onmousedown = function(){ portlet.moveCancel();}    
	   vButton.appendChild(refresh);
   }
   return vButton;
}
//------------------------------------------------------------------------------------------------------ WindowSkin3.js 
WindowSkin3.prototype = new WindowSkin2;                // Define sub-class for not header button
WindowSkin3.prototype.constructor = WindowSkin3;

function WindowSkin3(){
	WindowSkin2.call(this);
	this.type = "WindowSkin3";
}
WindowSkin3.prototype.createPortletHeaderButton= function (portlet){   
   	portlet.allowMinimized=false;             
   	var vButton = document.createElement('div');
   	vButton.id=Light._PB_PREFIX+portlet.serverId;
   	vButton.className = "portlet2-header-button";
   	vButton.style.visibility = "hidden";
	 
   	if(portlet.configMode){
	   	var config = document.createElement('span');	
	   	config.className = "icons";   
	   	if(portlet.mode == Light._CONFIG_MODE){
	       	config.innerHTML = this.getCancelConfigButton();
		   	config.onclick = function(){
		     	portlet.cancelConfig();
                portlet.moveAllowed();
		   	}
	   	}else{
	       	config.innerHTML = this.getConfigButton();
	   	   	config.onclick = function(){
		     	portlet.config();
                portlet.moveAllowed();
		   	}	   
	   	}
       	config.onmousedown = function(){ portlet.moveCancel();}
	   	vButton.appendChild(config);
   	} 
   
   	if(portlet.refreshMode){
	   var refresh = document.createElement('span');
	   refresh.className = "icons";
	   refresh.innerHTML = this.getRefreshButton();
	   refresh.onclick = function(){
	       portlet.refresh();
           portlet.moveAllowed();
	   }     
       refresh.onmousedown = function(){ portlet.moveCancel();}    
	   vButton.appendChild(refresh);
   	}
   	return vButton;
}
WindowSkin3.prototype.minimize = function(portlet){}
//------------------------------------------------------------------------------------------------------ WindowSkin4.js 
WindowSkin4.prototype = new WindowSkin3;       // Define sub-class for border only
WindowSkin4.prototype.constructor = WindowSkin4;

function WindowSkin4(){
	WindowSkin3.call(this);
	this.type = "WindowSkin4";
} 

WindowSkin4.prototype.createPortletWindow = function (portlet){
	var vWindow = document.createElement('div');
	vWindow.id = Light._PW_PREFIX+portlet.serverId;
	vWindow.style.position = "absolute";
	vWindow.className = "portlet2";    
	vWindow.style.zIndex= ++Light.maxZIndex;
	if(portlet && portlet.contentBgColor && portlet.contentBgColor.length > 0)
   	  	vWindow.style.backgroundColor = portlet.contentBgColor;
    else if(Light.portal.data.portletWindowTransparent == false && portlet.transparent == false)
      	vWindow.style.backgroundColor = "#ffffff";
   	vWindow.onmouseover = function(){
		portlet.window.header.style.visibility = "visible";
   	}
   	vWindow.onmouseout = function(){
		portlet.window.header.style.visibility = "hidden";
   	}    
   	return vWindow;
}
WindowSkin4.prototype.createPortletHeader= function (portlet,window){  
    var header = Light.newElement({element:'div',
								id:Light._PH_PREFIX+portlet.serverId,
								style:{cssText:'visibility:hidden;position:absolute;right:0px;'}
							});
	if(portlet.configMode){ 	   	
		var configOn = (portlet.mode == Light._CONFIG_MODE) ? true : false;
	   	var config = Light.newElement({element:'input',
	   									type:'image',
	   									style:{cssText:'border:0px;-moz-opacity:1;filter:alpha(opacity=100); height:14; width:14;'},
	   									title:(configOn) ? Light.getMessage('VIEW_MODE') : Light.getMessage('CONFIG_MODE'),
	   									src:Light.getThemePath()+ ((configOn) ? "/images/leave_config_on.gif" : "/images/config_on.gif"),
	   									onclick:function(){
									     	if(configOn)
									     		portlet.cancelConfig();
									     	else
									     		portlet.config();
									 	}
	   							});
       header.appendChild(config);
   }
   return header; 
}

WindowSkin4.prototype.createPortletHeaderButton= function (portlet){
 	portlet.allowMinimized=false;
 	return Light.newElement({element:'div',
								id:Light._PB_PREFIX+portlet.serverId
							});
}

WindowSkin4.prototype.position = function(portlet){ 
	this.window.style.width = portlet.width; 	
	this.container.style.width = portlet.width; 
	var top = portlet.top;
    if(typeof portlet.popup != "undefined"){
    	if(!portlet.popup){
    		if(typeof portlet.location != "undefined" && portlet.location ==1)
    			top = portlet.top + 20;
    	}
	}
	setPosition(this.window,portlet.left,top);
	this.focus(portlet);
 }
 
//------------------------------------------------------------------------------------------------------ WindowSkin5.js 
WindowSkin5.prototype = new WindowSkin4;                // Define sub-class for not border, not header
WindowSkin5.prototype.constructor = WindowSkin5;

function WindowSkin5(){
	WindowSkin4.call(this);
	this.type = "WindowSkin5";
} 
 
WindowSkin5.prototype.createPortletWindow = function (portlet){
	return Light.newElement({element:'div',
									id:Light._PW_PREFIX+portlet.serverId,
									style:{cssText:'position:absolute;z-index:'+(++Light.maxZIndex)
											+((portlet && portlet.contentBgColor) ? ';backgroundColor:'+portlet.contentBgColor : 
											((Light.portal.data.portletWindowTransparent == false && portlet.transparent == false) ? ';backgroundColor:#ffffff' : ''))
										},
									onmouseover:function(){
										portlet.window.header.style.visibility = 'visible';
								   	},
								   	onmouseout:function(){
										portlet.window.header.style.visibility = 'hidden';
								   	}
								});    
}