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
//-------------------------------------------------------------------------------------------------- LightPortlet.js
function LightPortlet (){}

LightPortlet.prototype.reRender = function(windowSkin){
	this.refreshWidth();
	var skin;	
	if(windowSkin){
		skin = windowSkin;
		this.windowSkin = windowSkin;
	}
	if(!this.windowSkin && this.window.type != this.parent.windowSkin)
		skin = this.parent.windowSkin;
	
	if(skin && this.window.type != skin){ 
   		this.window.close(this);
   		this.window = eval("new "+skin+"()");   	
   		this.window.render(this);
   		this.refresh();
   		return true;
  	}
	return false;
}

LightPortlet.prototype.refreshWidth = function(){
    this.width = this.parent.widths[this.position];
     //check colspan columns
    if(this.colspan){
    	for(var i=this.position+1;i<this.position+this.colspan;i++){
    		if(this.parent.widths.length > i)
    			this.width+= this.parent.between + this.parent.widths[i];
    	}
    }
}  

LightPortlet.prototype.focus = function(){
    this.window.focus(this);
}
 
LightPortlet.prototype.show = function(){
 	this.window.show(this);
}
 
LightPortlet.prototype.hide = function(){
 	this.window.hide(this);
}

LightPortlet.prototype.refreshPortletTitle = function(){
	if(!this.notFirstLoad){
		this.notFirstLoad = true;
	    var id = "portlet"+this.serverId;
	    var node = L$(id+".title");
	    if(node){	
	    	var title = node.value;
	    	if(title && title.trim().length > 0){
	    		var data = JSON.parse(title);
	    		this.setTitle(data);
	    	}
	    	node.parentNode.removeChild(node);
	    }else
	    	Light.executeRender(this.id,Light._HEADER_MODE,'','','');
	}else    
	   	Light.executeRender(this.id,Light._HEADER_MODE,'','','');
} 

LightPortlet.prototype.setTitle = function(data){    
	var flag = false;
	if(data && data.title){
		this.title = data.title;
		flag = true;  
	}else{
	    if(this.title.indexOf("(") > 0){
	  		var index = this.title.indexOf("(");
	  		this.title = this.title.substring(0,index);	
	  		flag = true;  		
	  	}
	  	if(data && data.suffix){ 
			this.title =  this.title + "(" + data.suffix + ")";   
			flag = true;  	   
	  	}
	}	
  	if(flag) this.refreshHeader(); 
}  
LightPortlet.prototype.moveBegin = function(e) {      
  	document.body.onselectstart = function() { return false };
  	document.body.ondragstart = function() { return false };
  	var x = this.left;
  	var y = this.top;
  	if(e != null){
	  	if (document.all) e = event;
	  	x = e.clientX;
	  	y = e.clientY;
  	}
  	this.window.focus(this);
  	this.moveable = true;
  	this.mouseDownX = x;
  	this.mouseDownY = y;
  	this.moveBeginX = x;
  	this.moveBeginY = y;

  	Light.portal.moveTimer = 0;
  	this.startMoveTimer();
  	var vdocument = L$(Light._PANEL_PREFIX+this.parent.serverId);
  	Light.portal.highLightBar.style.visibility = "hidden";
  	vdocument.appendChild(Light.portal.highLightBar); 
}
 
LightPortlet.prototype.moveEnd = function() {        
    if(this.moveable){
	    var xDifference = this.mouseDownX - this.moveBeginX;
	    var yDifference = this.mouseDownY - this.moveBeginY;
	    if(this.left < 0) this.left = 0;  
	    if(this.moveToColumn != this.position){
		    if(this.moveToColumn > this.position)
		      this.moveRight(this.moveToColumn);
		    else if(this.moveToColumn < this.position)
		      this.moveLeft(this.moveToColumn);
	    }
	    else {
	     if(this.mouseDownY > this.moveBeginY)
	        this.moveDown();
	      else if(this.mouseDownY < this.moveBeginY)
	        this.moveUp();
	    }
	    this.lastAction = null;
	    this.moveable = false;
	    var vdocument = L$(Light._PANEL_PREFIX+this.parent.serverId);
	    vdocument.removeChild(Light.portal.highLightBar); 
	    if(!this.minimized){ 
	    	this.refresh(this.isNew);   
	    }
	    this.popup = false;
	    if(this.isNew){
		    this.window.close(this);
			this.window = eval("new "+(this.windowSkin ? this.windowSkin : this.parent.windowSkin)+"()");   
			this.window.render(this);	    
			this.isNew = false;
	    }
	}	
}

LightPortlet.prototype.move = function(e) { 	
    if(this.moveable){
    var x = e.clientX;
    var y = e.clientY;
    this.left += x - this.mouseDownX;
    this.top  += y - this.mouseDownY;
    var direction = "left";
    if(x > this.mouseDownX) direction = "right";
    this.refreshPosition(this);
    this.mouseDownX = x;
    this.mouseDownY = y;

    var xDifference = this.mouseDownX - this.moveBeginX;
    var yDifference = this.mouseDownY - this.moveBeginY;
	
	var moveTo= 0;
	var moveToColumn = -1;
	for(var i=0;i<this.parent.widths.length;i++) {  
	  if(i > 0)
	     moveTo+= this.parent.widths[i - 1]+this.parent.between * (i - 1);             
      if(direction =="left"){
	      if(this.left < moveTo + this.parent.widths[i]){       
	         moveToColumn = i;
	         break;
	      }  
      }else{
      	  if(this.left + this.width > moveTo){       
	         moveToColumn = i;
	      }
	  }
    }
    this.moveToColumn = moveToColumn;
    Light.portal.highLightBar.style.visibility = "visible";
    
    if(moveToColumn != this.position){    
	    if(moveToColumn > this.position)      
	         this.highLightBarX(1,moveToColumn);//right
	    else if(moveToColumn < this.position)
	         this.highLightBarX(2,moveToColumn);//left	    
    }else {
     if(this.mouseDownY > this.moveBeginY)
        this.highLightBarY(3);//down
     else if(this.mouseDownY < this.moveBeginY)
        this.highLightBarY(4);//up
    }
  }
}
 
LightPortlet.prototype.startMoveTimer = function()  {
   	if (Light.portal.moveTimer>=0 && Light.portal.moveTimer<10){
    	Light.portal.moveTimer++;
    	var that = this;
    	setTimeout((function() {that.startMoveTimer ()}), 15);
   	}
   	if (Light.portal.moveTimer == 10) {
    	Light.portal.dragDropPortlet = this;
    	this.refreshPosition();
    }
}

LightPortlet.prototype.refreshPosition = function() { 
   	this.window.position(this);
   	this.focus();
}
 
LightPortlet.prototype.autoRefresh = function() {
   	if(this.mode != Light._VIEW_MODE) return;
   	if(this.autoRefreshed){       
       	if(this.firstTimeAutoRefreshed){
       	  	this.firstTimeAutoRefreshed = false;
       	}else{
       	  	this.selfRefresh();
       	}
       	var that = this;
       	this.timer = setTimeout((function() {that.autoRefresh ()}), this.periodTime);
    }
}

LightPortlet.prototype.selfRefresh = function() {   	
   	if(!this.minimized && (!this.maximized || this.autoRefreshWhenMaximized)){
	   	if(this.mode == Light._VIEW_MODE){
	    	if(this.refreshAtClient){
	      		Light.refreshAtClient(this);	        
	    	}else{
          		this.refreshAction = true;
	      		Light.executeRefresh(this.id);
	    	}
	   	}
   	}
}

LightPortlet.prototype.refresh = function(flag) {  
	this.setTitle({});
 	if(this.minimized){
 		this.window.container.innerHTML = Light.empty;  		
 		this.refreshPortletTitle();
 		return;
 	}
 	if(this.type == 3){
 		if(!Light.executeAtClient(this)){
 			var that = this;
 			setTimeout((function() {that.refresh ()}), 100);
 		}
 		return;
 	}
    if(Light.executeAtClient(this)) return;  
    if(flag == null || flag == true){
   		this.window.container.innerHTML = Light.loading;
   		this.parent.repositionPortlets(this);
   	}   	   
    this.refreshAction = true; 
   	Light.executeRefresh(this.id);
   	if(this.autoRefreshed){
   	  	this.firstTimeAutoRefreshed = true;
   	  	this.autoRefresh();
	}  
}

LightPortlet.prototype.changePosition = function(){
   	var params = "responseId="+this.id
      		    +"&tabId="+this.parent.serverId
      		    +"&portletId="+this.serverId
                +"&column="+this.position
                +"&row="+this.index;
   	Light.ajax.sendRequest(Light._CHANGE_POSITION,{parameters:params});   
   	this.parent.repositionPortlets(this);    
}

LightPortlet.prototype.rememberMode = function(mode){
   	//if(Light.isLogin()){
	//  	var params = "mode="+mode+"&portletId="+this.serverId;
	//  	Light.ajax.sendRequest(Light._REMEBER_STATE, {parameters:params});
   	//}
}

LightPortlet.prototype.rememberState = function(state){
	if(Light.isLogin()){
		var params = "state="+state+"&portletId="+this.serverId;
		Light.ajax.sendRequest(Light._REMEBER_STATE, {parameters:params});     
	}
} 

LightPortlet.prototype.refreshButtons = function() {
	this.window.refreshButtons(this);
	if(this.mode == Light._EDIT_MODE){        
		this.rememberMode(1);        
	}else if(this.mode == Light._HELP_MODE){        
		this.rememberMode(2);        
	}else{        
		this.rememberMode(0);
	}                       
}
 
LightPortlet.prototype.edit = function() { 
	if(this.editMode){ 
    	this.mode= Light._EDIT_MODE;
    	this.cancelMinimized();
    	Light.executePortlet(this.id);       	
    	this.refreshButtons();
   	}
}

LightPortlet.prototype.cancelEdit = function() {
	if(this.editMode){
    	this.mode= Light._VIEW_MODE;
    	this.cancelMinimized();
    	this.lastAction = null;
    	this.refresh(false);
   		this.refreshButtons();
   	}
}
 
LightPortlet.prototype.help = function() {
	if(this.helpMode){ 
        this.mode = Light._HELP_MODE; 
        this.cancelMinimized();
        Light.executePortlet(this.id); 
        this.refreshButtons(); 
    }
}
 
LightPortlet.prototype.cancelHelp = function() {
	if(this.helpMode){
		this.mode = Light._VIEW_MODE;
		this.cancelMinimized();
		this.lastAction = null;
		this.refresh(false);	 
    	this.refreshButtons();     
	}
}

LightPortlet.prototype.config = function() {
	if(this.configMode){ 
		this.mode = Light._CONFIG_MODE;   
		this.cancelMinimized();       
        var data = {	     
		     id : this.id,
		     title : this.title,
		     barBgColor : this.barBgColor,
		     barFontColor :this.barFontColor,
		     contentBgColor : this.contentBgColor,
		     textColor : this.textColor,
		     transparent : this.transparent,
		     autoRefreshed : this.autoRefreshed ? 1 : 0,
  			 periodTime : parseInt(this.periodTime / 1000),
  			 showNumber : this.showNumber,
		     skin : this.window.type,
		     windowStatus : (this.maximized) ? 2 : ((this.minimized) ? 1 : 0),
		     colspan : this.colspan,
		     marginTop : this.marginTop,
		     maxColumns : this.parent.widths.length,
		     showIcon : this.showIcon,
		     client : this.client
		   };  
		 Light.setPortletContent(data.id,Light.getViewTemplate("configMode.jst",data));
		 this.refreshButtons();  
    }
}
 
LightPortlet.prototype.cancelConfig = function() {
	if(this.configMode){
		this.mode = Light._VIEW_MODE;
		this.cancelMinimized();
		this.lastAction = null;
		this.refresh(false);	    
		this.refreshWindow();  
	}
}
LightPortlet.prototype.cancelMinimized = function() {
	if(this.minimized){
		this.minimized = false;
		this.state = Light._NORMAL_STATE;
		this.window.minimize(this);
	}
}
LightPortlet.prototype.highLightBarX = function(pos,moveToColumn) {
   	var temp = null;   
   	var temp2 = null;  
   	var showHighLightBar = true;
   	var columnLeft=0;
   	if(this.parent.portlets[moveToColumn] == null)
       	this.parent.portlets[moveToColumn] = [];
   	if(this.parent.portlets[moveToColumn] != null){
        var column = moveToColumn;              
        var len = this.parent.portlets[column].length;   
        for(var i=0;i<len;i++){
          	if(this.parent.portlets[column][i] != null){
             	if(columnLeft == 0) columnLeft= this.parent.portlets[column][i].left;
             	if(this.top < this.parent.portlets[column][i].top){
	            	temp = this.parent.portlets[column][i];         
	            	break;
	            }              
	    	}
	   	} 
    	if(temp == null){
    		for(var i=len;i--;){
          		if(this.parent.portlets[column][i] != null){	             
        			temp2 = this.parent.portlets[column][i];     
            		break;                  
          		}
        	} 
    	}
 	}else
       showHighLightBar= false;                         

	if(showHighLightBar){
     	var highLeft = columnLeft;
     	var highTop = this.window.top; 
     	var highWidth = this.parent.widths[moveToColumn];
     	var highHeight = 5;
     
     	if(temp != null){           
        	highTop = temp.top - this.marginTop + 2;     
        	highLeft = temp.left;        
        	highWidth = temp.width;             
     	}else if(temp2 != null){ 
        	highTop = temp2.top + temp2.getHeight() + 2;
     	}else{
     		for(var i=0;i<moveToColumn;i++) { 
        		highLeft += this.parent.widths[i] + this.parent.between;
    	  	}
     	} 
     
     	if (document.all) {	
         	Light.portal.highLightBar.style.posLeft = highLeft;
       	 	Light.portal.highLightBar.style.posTop = highTop;
     	}else {        
         	Light.portal.highLightBar.style.left = highLeft;
         	Light.portal.highLightBar.style.top = highTop;
     	}
     	Light.portal.highLightBar.style.width = highWidth;
     	Light.portal.highLightBar.style.height= highHeight;
     	Light.portal.highLightBar.style.zIndex= ++Light.maxZIndex; 
   	}else
     	Light.portal.highLightBar.style.visibility = "hidden";   
}

LightPortlet.prototype.highLightBarY = function(pos) {
   var temp = null;   
   var showHighLightBar = true;
   //down
   if(pos == 3){
       var started = this.index + 1;                  
       for(var i=started,len=this.parent.portlets[this.position].length;i<len;i++){
       	 var p = this.parent.portlets[this.position][i];
         if(p){
           temp = p;
           break;
         }     
       }
       if(temp == null)
          showHighLightBar= false;   
     } 
    //up
    if(pos == 4){
     if(this.index > 0){               
        for(var i=this.index;i--;){
        	var p = this.parent.portlets[this.position][i];
           	if(P){
             	temp = P;
             	break;
           }
        }
        //if(temp == null)
        //  showHighLightBar= false;
      }
      //else
      // showHighLightBar= false;          
    }  

    if(showHighLightBar){
     var highLeft;
     var highTop=this.window.top + 5; 
     var highWidth = this.parent.widths[this.position];
     var highHeight = 5;
     if(temp != null){   
        if(pos != 3)
          highTop = temp.top - this.marginTop + 2; 
        else{
           var temp2 = null;
           var started = temp.index + 1; 
           for(var i=started,len=this.parent.portlets[this.position].length;i<len;i++){
	       	 var p = this.parent.portlets[this.position][i];
	         if(p){
	           temp2 = p;
	           break;
	         }     
	       }                 	       
	       if(temp2 != null)
	          highTop = temp2.top - this.marginTop + 2; 
	       else{           
              highTop = temp.top + temp.getHeight() + 2;    
          }     
        }
        highLeft = temp.left;        
        highWidth = temp.width;             
     }else{
     	for(var i=0;i<this.position;i++) { 
        	highLeft += this.parent.widths[i] + this.parent.between;
    	}
     }     
       
     if (document.all) {	
         Light.portal.highLightBar.style.posLeft = highLeft;
       	 Light.portal.highLightBar.style.posTop = highTop;
     }else {        
         Light.portal.highLightBar.style.left = highLeft;
         Light.portal.highLightBar.style.top = highTop;
     }
     Light.portal.highLightBar.style.width = highWidth;
     Light.portal.highLightBar.style.height= highHeight;
     Light.portal.highLightBar.style.zIndex= ++Light.maxZIndex; 
     }else
        Light.portal.highLightBar.style.visibility = "hidden";  
}

LightPortlet.prototype.moveCancel = function(){    
    this.buttonIsClicked = true;
}
 
LightPortlet.prototype.moveAllowed = function(){    
    this.buttonIsClicked = false;
}

LightPortlet.prototype.moveUp = function() {
   if(this.index > 0){
    var temp = null;
    var upIndex = 0;
    var currentIndex = this.index;               
    for(var i=this.index;i--;){
    	var p = this.parent.portlets[this.position][i];
       	if(p){
         temp = p;
         upIndex = i;
         break;
       }
    }               
    if(temp != null){
      temp.index = this.index;
      this.index = upIndex;      
      this.parent.portlets[this.position][upIndex] = this;
      this.parent.portlets[this.position][currentIndex] = temp; 
      temp.changePosition(); 
      temp.lastAction = null;                  
      this.left = 0;
	  for(var i=0;i<this.position;i++) { 
    	this.left += this.parent.widths[i] + this.parent.between;
	  }
      this.changePosition();      
      this.parent.repositionPortlets(this);
    }     
   }
}
 
LightPortlet.prototype.moveDown = function() {
    var temp = null;
    var downIndex = 0;
    var currentIndex = this.index;
    var started = this.index + 1;                  
    for(var i=started,len=this.parent.portlets[this.position].length;i<len;i++){
       	var p = this.parent.portlets[this.position][i];
       	if(p){
         temp = p;
         downIndex = i;
         break;
       }       
    }               
    if(temp != null){
      temp.index = this.index;
      this.index = downIndex;       
      this.parent.portlets[this.position][downIndex] = this;
      this.parent.portlets[this.position][currentIndex] = temp;          
      this.changePosition();
      this.left = 0;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += this.parent.widths[i] + this.parent.between;
    	  }
      temp.changePosition();
      temp.lastAction = null; 
      temp.parent.repositionPortlets(temp);
    }    
}
 
LightPortlet.prototype.moveLeft = function(moveToColumn) {
   if(this.position > 0){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = moveToColumn; 
     if(this.parent.portlets[column] == null)
        this.parent.portlets[column] = [];
     var len = this.parent.portlets[column].length;   
     for(var i=0;i<len;i++){
       if(this.parent.portlets[column][i] != null 
       && this.top < this.parent.portlets[column][i].top){
            temp = this.parent.portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }                    
                
        if(temp != null){
          for(var i= len;i--;){
          	var p = this.parent.portlets[column][i];
	       	if(p){
	          var temp2 = p;
	          temp2.index++; 
	          this.parent.portlets[column][i + 1]= temp2;
	          this.parent.portlets[column][i + 1].changePosition();
	          this.parent.portlets[column][i]= null;
	          temp2.lastAction = null;
	          if(!temp2.minimized){ 
			    temp2.refresh(false);   
			  }  
              if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
          this.index = newIndex;                                     
          this.parent.portlets[this.position][this.index] = this;          
          this.parent.portlets[currentPosition][currentIndex] = null; 
          this.left = 0;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += this.parent.widths[i] + this.parent.between;
    	  }
          this.refreshWidth();  
          this.changePosition();                     
        }else{
          this.position = column;  
          var isEmpty = true;
          for(var i=len;i--;){
          	var p = this.parent.portlets[column][i];
	       	if(p){	       
	          var temp3 = p;
              this.index = temp3.index + 1;
              isEmpty = false;
	          break;	         
	        }
	 	   }          
          if(isEmpty) this.index = 0;
          this.parent.portlets[this.position][this.index] = this;   
          this.parent.portlets[currentPosition][currentIndex] = null;        
          this.left = 0;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += this.parent.widths[i] + this.parent.between;
    	  }
	  	  this.refreshWidth();
          this.changePosition();        
        }
      var length = this.parent.portlets[currentPosition].length;   
      for(var i=currentIndex+1;i<length;i++){
       if(this.parent.portlets[currentPosition][i] != null){
         var next = this.parent.portlets[currentPosition][i];
         this.parent.repositionPortlets(next);
         break;
       }
     }          
   }
}
 
LightPortlet.prototype.moveRight = function(moveToColumn) {      
  columns = this.parent.widths.length;
  if(this.position + 1 < columns 
    && this.parent.portlets[this.position + 1] == null)
    this.parent.portlets[this.position + 1] = [];
  if(this.parent.portlets[this.position + 1] != null){
     var temp = null;
     var newIndex = 0;
     var currentPosition = this.position;
     var currentIndex = this.index;
     var column = moveToColumn;              
     var len = this.parent.portlets[column].length;   
     for(var i=0;i<len;i++){
       if(this.parent.portlets[column][i] != null
       && this.top < this.parent.portlets[column][i].top){
            temp = this.parent.portlets[column][i];
            newIndex = temp.index;
            break;
       }
     }               
     if(temp != null){                    
          for(var i=len;i--;){
          	var p = this.parent.portlets[column][i];
	       	if(p){	
	      	  var temp2 = p;
	          temp2.index++;
	          this.parent.portlets[column][i + 1]= temp2;
	          this.parent.portlets[column][i + 1].changePosition();
	          this.parent.portlets[column][i]= null;
	          temp2.lastAction = null;
	          if(!temp2.minimized){ 
			    temp2.refresh(false);   
			  }  
	          if(temp2.serverId == temp.serverId) break;	         
	       }
	      }   
	      this.position = column;
          this.index = newIndex;                    
          this.parent.portlets[this.position][this.index] = this;          
	  	  this.parent.portlets[currentPosition][currentIndex] = null; 
          this.left = 0;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += this.parent.widths[i] + this.parent.between;
    	  }
          this.refreshWidth();
          this.changePosition();    
        }else{
          this.position = column;
          var isEmpty = true;
          for(var i= len - 1;i>=0;i--){
	       if(this.parent.portlets[column][i] != null){
	          var temp3 = this.parent.portlets[column][i];
                  this.index = temp3.index + 1;
                  isEmpty = false;
	          break;	         
	       }
	  }          
          if(isEmpty) this.index = 0;         
          this.parent.portlets[this.position][this.index] = this;
          this.parent.portlets[currentPosition][currentIndex] = null; 
          this.left = 0;
    	  for(var i=0;i<this.position;i++) { 
        	this.left += this.parent.widths[i] + this.parent.between;
    	  }
	      this.refreshWidth();
          this.changePosition();          
        }    
     var length = this.parent.portlets[currentPosition].length;   
     for(var i=currentIndex+1;i<length;i++){
     	var p = this.parent.portlets[currentPosition][i];
	    if(p){
         var next = p;
         this.parent.repositionPortlets(next);
         break;
       }
     }      
   }
}

LightPortlet.prototype.minimize = function() { 
   if(this.allowMinimized){
	   this.minimized = !this.minimized;
	   if(this.minimized){
	    this.state = Light._MINIMIZED_STATE;
	    if(this.maximized){
	       this.maximize(false);   
	       this.minimized = true;     
	    }	   
	    this.window.container.innerHTML = Light.empty;         
	  }else{    
	    this.state = Light._NORMAL_STATE;     
	  }
	   this.window.minimize(this);
	   this.parent.repositionPortlets(this);  	   	   
	   this.rememberState((this.minimized) ? 1 : 0);
	   if(!this.minimized)
	      this.refresh()
   }
 }
 
LightPortlet.prototype.maximize = function(flag) { 
   	if(this.allowMaximized){
	   	this.windowMaximize(flag);
	   	this.refresh();
	   	if(!this.isPopupWindow && this.mode != Light._HEADER_MODE && !isSafari()){
			if(this.maximized)
				Light.addHistory(this.id+"/"+Light._MODE_PREFIX+this.mode+"/"+Light._STATE_PREFIX+this.state+"/");
			else
				Light.addHistory();
		}
   	} 
}
  
LightPortlet.prototype.windowMaximize = function(flag) { 
   this.maximized = !this.maximized; 
   if(!this.maximized){  
      this.state = Light._NORMAL_STATE;   
      var height = 0;
      var maxIndex = 0;
      var nullNum = 0;      
      for(var i=0;i<this.parent.portlets[this.position].length;i++) {        
          if(i == this.index){
             break;
          }
 	  	  if(this.parent.portlets[this.position][i] != null && !this.parent.portlets[this.position][i].maximized){
             height += this.parent.portlets[this.position][i].getHeight();   
          } 
          if(this.parent.portlets[this.position][i] == null){
             nullNum++;
          }
          if(this.parent.portlets[this.position][i] != null && this.parent.portlets[this.position][i].maximized){
             height = this.parent.portlets[this.position][i].getHeight();     
             maxIndex = i;
             nullNum = 0;
          }              
    	}  
    	this.top  = this.window.top + height + this.marginTop * (i - maxIndex - nullNum); 	
    	this.refreshWidth();
	    this.left = 0;
	    for(var i=0;i<this.position;i++) { 
	        this.left += this.parent.widths[i] + this.parent.between;
	    }  
     	Light.portal.container.style.zIndex= 1;
		this.parent.showOtherPortlets();	
   }else{        
        this.state = Light._MAXIMIZED_STATE;  
        this.parent.hideOtherPortlets(this);        
   	    this.left = Light.portal.layout.maxLeft;
   	    this.top = Light.portal.layout.maxTop;
        this.width = Light.portal.layout.maxWidth;        	       
        Light.portal.container.style.zIndex= ++Light.maxZIndex; 
        Light.portal.body.style.zIndex= ++Light.maxZIndex;
        Light.portal.footer.style.zIndex= ++Light.maxZIndex; 
        window.scrollTo(0,0);     	
   }        
   this.window.maximize(this);  
   this.parent.repositionPortlets(this);   
   if(flag == null || flag == true){   	
		this.rememberState((this.maximized) ? 2 : 0);   	
	} 
}

LightPortlet.prototype.close = function(){ 
   	var closePortlet = Light.confirm(Light.getMessage('COMMAND_CLOSE_PORTLET'));
   	if (!closePortlet) // user cancelled close closePortlet
	{
		return;
	}
   	if(this.maximized)   
   		this.windowMaximize(); 
   	if(this.timer) clearTimeout(this.timer);   
   	this.window.close(this);
   	this.parent.portlets[this.position][this.index] = null;   
   	for(var i=Light.portal.allPortlets.length;i--;){
		if(Light.portal.allPortlets[i] && Light.portal.allPortlets[i].serverId == this.serverId){
			Light.portal.allPortlets[i] = null;
			break;
		}
	}
   	this.parent.repositionPortlets(this); 
   	Light.ajax.sendRequest(Light._DELETE_PORTLET, {parameters:'portletId='+this.serverId});       
} 

LightPortlet.prototype.refreshWindow= function () { 
	this.window.refreshWindow(this);
}

LightPortlet.prototype.refreshHeader= function () {
 	this.window.refreshHeader(this);
}

LightPortlet.prototype.refreshWindowTransparent = function () {    
	var bg = "transparent";
   	if(!Light.portal.data.portletWindowTransparent && !this.transparent){
      	if(this.contentBgColor)
   	  		bg = this.contentBgColor;
   	  	else
      		bg = "#ffffff";
   	}
   	this.window.container.style.backgroundColor = bg;         
} 

LightPortlet.prototype.refreshTextColor = function () {  
    var id = this.window.container.id;
    if(this.textColor){
    	$('#'+id).find('*').css({color:this.textColor});    
	 }
	 if(this.contentBgColor){
	 	$('#'+id+' textarea').css({backgroundColor:this.contentBgColor});  
	 }
}

LightPortlet.prototype.getTop = function(){
   return this.window.top;
}

LightPortlet.prototype.getHeight = function(){
   var clientHeight = this.window.window.clientHeight;
   var clientHeight2 = this.window.container.clientHeight;
   return (clientHeight > clientHeight2) ? clientHeight : clientHeight2;
   
}

LightPortlet.prototype.setContent = function(content){
	if(this.type == 2){
		try{
			var data;
			if(content){				
				data = JSON.parse(content);
			}else{
				data = {
			        id : this.id,
			        userId : Light.getRememberedUserId(),  
			        success : "",
			        error : "",
			        view : this.name+"."+this.mode
			    };
			}
			
			if(typeof data.id == "undefined") data.id = this.id;
			if(typeof data.userId == "undefined") data.userId = Light.getRememberedUserId();
			if(typeof data.view == "undefined") data.view = this.name+"."+this.mode.toLowerCase();
			if(typeof data.success == "undefined") data.success = "";
			if(typeof data.error == "undefined") data.error = "";
			if(data && data.method) { this.data = data;}
			if(document.getElementById(data.view) != null){			
				this.window.setContent(Light.getViewTemplate(data.view,data));				
			}else{
				var that = this;
				setTimeout((function() {that.refreshData(data)}), 100);
			}
		}catch(err){
			this.window.setContent(content);
		}
		if(this.data && this.data.method){
			eval(this.data.method+"('"+this.data.id+"')");
		}
	}else
		this.window.setContent(content);	
}

LightPortlet.prototype.refreshData = function(data){
	if(document.getElementById(data.view) != null){
		this.window.setContent(Light.getViewTemplate(data.view,data));
		this.parent.repositionPortlets(this);	   
	}else{
		var that = this;
		setTimeout((function() {that.refreshData(data)}), 100);
	}
}
//------------------------------------------------------------------------------------------------------ PortletWindow.js
PortletWindow.prototype = new LightPortlet;                
PortletWindow.prototype.constructor = PortletWindow;

function PortletWindow (data,isNew,noRender) {   
   Light.portal.allPortlets.push(this);
   this.parent = Light.currentTab;
   this.windowSkin = data.windowSkin;   
   if(isNew)
   		this.window = new WindowSkin2();
   else	
   		this.window = eval("new "+(this.windowSkin ? this.windowSkin : this.parent.windowSkin)+"()");   
   this.allowMove = true;
   this.mode = Light._VIEW_MODE;
   if(data.initMode == 1) this.mode = Light._EDIT_MODE;
   if(data.initMode == 2) this.mode = Light._HELP_MODE;
   if(data.initMode == 3) this.mode = Light._CONFIG_MODE;
   this.state = Light._NORMAL_STATE;
   this.minimized = false;
   this.maximized = false;
   if(data.initState == 1){
   		this.state = Light._MINIMIZED_STATE;  
   		this.minimized = true;
   }
   if(data.initState == 2){
   		this.state = Light._MAXIMIZED_STATE;
   		this.maximized = true;
   }      
   this.serverId = data.serverId;
   this.id = Light._PR_PREFIX+this.serverId;
   this.position = data.column;
   this.colspan = data.colspan;
   this.title = data.title;
   this.icon = data.icon;
   this.url = data.url;
   this.name = data.name;
   this.requestUrl = data.path;
   this.closeable = data.closeable;
   this.refreshMode = data.refreshMode;
   this.editMode = data.editMode;
   this.helpMode = data.helpMode;
   this.configMode = data.configMode;
   this.allowMinimized = data.allowMinimized;
   this.allowMaximized = data.allowMaximized;
   this.autoRefreshed = data.autoRefreshed;
   this.refreshAtClient = data.refreshAtClient;
   this.periodTime = data.periodTime;
   this.showNumber = data.showNumber;
   this.allowJS = data.allowJS;
   this.barBgColor = data.barBgColor;
   this.barFontColor = data.barFontColor;
   this.contentBgColor = data.contentBgColor;
   this.textColor = data.textColor;
   this.parameter = data.parameter;
   this.marginTop = data.marginTop;
   this.transparent = false;
   if(data.transparent == 1) this.transparent= true;
   this.showIcon = true;
   if(data.showIcon == 0) this.showIcon= false;
   this.client = data.client;
   this.type = data.type;
   this.index = this.parent.getPortletIndex(this.position);
   data = null;
   var height = 0;
   var maxIndex = 0;
   var nullNum = 0;        
   for(var i=0;i<this.parent.portlets[this.position].length;i++){
		var vPortlet = this.parent.portlets[this.position][i];     
      	if(i == this.index){
         	break;
      	}
      	if(vPortlet && !vPortlet.maximized){
         	height += vPortlet.getHeight()+vPortlet.marginTop;   
      	} 
      	if(!vPortlet){
         	nullNum++;
      	}
      	if(vPortlet && vPortlet.maximized){
         	height = vPortlet.getHeight()+vPortlet.marginTop;     
         	maxIndex = i;
         	nullNum = 0;
      	}  
      	//check colspan columns    
      	if(this.position > 0){
	      	for(var j=0;j<this.position;j++){
	      		var portlets = this.parent.portlets[j];
	      		if(portlets && portlets[i]){
	      			if(portlets[i].colspan + portlets[i].position > this.position){
	      				height += portlets[i].getHeight()+portlets[i].marginTop;
	      			}
	      		}
	      	}
      	}        
    }
    this.refreshWidth();
    this.isNew = isNew;
    if(isNew){
    	this.top = getMousePositionY(Light.e) - 120;
    	this.left = 0 - parseInt(Light.portal.body.style.marginLeft) + getMousePositionX(Light.e);
    	this.popup = true;
    }else{
    	if(height > 0)
	    	this.top = this.window.top + height + this.marginTop;
	    else
	    	this.top = this.window.top + this.marginTop + this.parent.getTop();    
	    this.left = this.window.left;
	    for(var i=0;i<this.position;i++) { 
	        this.left += this.parent.widths[i] + this.parent.between;
	    }
    }
    this.parent.portlets[this.position][this.index] = this;     
    if(!noRender){
    	this.window.render(this);  
    	this.window.container.innerHTML = Light.empty;
    	this.parent.repositionPortlets(this); 
    	this.refreshPosition();
    }else
    	this.window.bind(this);
    this.moveable = false;
    this.autoRefreshWhenMaximized = false;
    this.autoShow = false;
    this.opacity = 0;
    this.fade="in";
    this.myPictureIndex = 0;
    this.myPictures= [];
    this.autoShowId = null;
    this.mouseDownX = 0;
    this.mouseDownY = 0;    
	this.content = null;   	
    if(this.autoRefreshed){
   	  	this.firstTimeAutoRefreshed = true;
   	  	this.autoRefresh();
    }
}
//------------------------------------------------------------------------------------------------------- PortletPopupWindow.js
PortletPopupWindow.prototype = new LightPortlet;                
PortletPopupWindow.prototype.constructor = PortletPopupWindow;
function PortletPopupWindow (position,left,width,title,icon,url,request,requestUrl,closeable,refreshMode,editMode,helpMode,configMode,autoRefreshed,refreshAtClient,periodTime,allowJS,barBgColor,barFontColor,contentBgColor,parameter,allowMinimized,allowMaximized,allowPopup,location,type,top) {
   	Light.portal.allPortlets.push(this);
   	this.parent = Light.currentTab;
   	this.window = new WindowSkin2();
   	this.allowMove = true;
   	this.isPopupWindow = true;
   	this.popup= true;
   	if(typeof allowPopup != "undefined" && !allowPopup)
   		this.popup= false;
   	if(typeof location != "undefined"){
	   	this.location=location;
	    if(this.location ==1){
	    	Light.portal.originalLeft = Light.portal.layout.containerLeft;
	    	Light.portal.originalHeaderLeft = Light.portal.header.style.marginLeft;
	    	Light.portal.originalBodyLeft = Light.portal.body.style.marginLeft;
	    	Light.portal.layout.containerLeft = width + 10;
	    	Light.portal.header.style.marginLeft = Light.portal.layout.containerLeft;
	    	Light.portal.body.style.marginLeft = Light.portal.layout.containerLeft;
	    	this.allowMove = false;
	    }	
   	}
   	this.mode = Light._VIEW_MODE;
   	this.state = Light._NORMAL_STATE;  
   	this.serverId = Date.parse(new Date());
   	this.id = Light._PR_PREFIX+this.serverId;
   	this.position = position;
   	this.left = left;
   	this.top  = left;
   	if(top) this.top = top;
   	this.width = width;
   	this.title = title;
   	this.icon = icon;
   	this.url = url;
   	this.name = request;
   	this.requestUrl = requestUrl;
   	this.closeable = closeable;
   	this.refreshMode = refreshMode;
   	this.editMode = editMode;
   	this.helpMode = helpMode;
   	this.configMode = configMode;
   	this.allowMinimized = true; 
   	if(allowMinimized != null && !allowMinimized) this.allowMinimized = false;
   	this.allowMaximized = true;
   	if(allowMaximized != null && !allowMaximized) this.allowMaximized = false;
   	this.autoRefreshed = autoRefreshed;
   	this.refreshAtClient = refreshAtClient;
   	this.periodTime = periodTime;
   	this.showNumber = 10;
   	this.allowJS = allowJS;
   	this.barBgColor = barBgColor;
   	this.barFontColor = barFontColor;   
   	this.className = "portlet-popup";
   	this.contentBgColor ='#ffffff';
   	if(contentBgColor !='') this.contentBgColor = contentBgColor;
   	this.parameter = parameter;   
   	this.index = this.parent.getPortletIndex(this.position);
   	this.type = 1;
   	if(type != null) this.type = type;
   	this.marginTop = 10;
   	var height = 0;
   	var maxIndex = 0;
   	var nullNum = 0;        
   
   	this.originalTop = this.top;
   	this.originalWidth = this.width;
   	this.originalLeft = this.left;

   	this.parent.portlets[this.position][this.index] = this;
   
   	this.window.render(this);
   
   	this.minimized = false;
   	this.maximized = false;   
   	this.moveable = false;
   	this.autoRefreshWhenMaximized = false;
   	this.autoShow = false;
   	this.opacity = 0;
   	this.fade="in";
   	this.myPictureIndex = 0;
   	this.myPictures= [];
   	this.autoShowId = null;   
   	this.mouseDownX = 0;
   	this.mouseDownY = 0;
   	this.refreshPosition();
   	this.window.container.innerHTML = Light.loading;
   	this.focus();
   	if(this.autoRefreshed){
   	  	this.firstTimeAutoRefreshed = true;
   	  	this.autoRefresh(this);
   	}
   	if(this.location == null || this.location == 5){
  		this.windowMaximize();
  	}
}  
PortletPopupWindow.prototype.rememberMode = function(mode){}
PortletPopupWindow.prototype.rememberState = function(state){}    
PortletPopupWindow.prototype.moveBegin = function(e){  
  	document.body.onselectstart = function() { return false };
  	document.body.ondragstart = function() { return false };
  	if (document.all) e = event;
  	var x = e.clientX;
  	var y = e.clientY;
  	this.focus();
  	this.moveable = true;
  	this.mouseDownX = x;
  	this.mouseDownY = y;
  	this.moveBeginX = x;
  	this.moveBeginY = y;

  	Light.portal.moveTimer = 0;
  	this.startMoveTimer(this);
}
 
PortletPopupWindow.prototype.moveEnd = function(){    
   	if(this.moveable){    
    	this.moveable = false;  
    	this.originalLeft = this.left;
    	this.originalTop = this.top;     
  	}
}

PortletPopupWindow.prototype.move = function(e){ 	
   	if(this.moveable){
    	var x = e.clientX;
    	var y = e.clientY;
    	this.left += x - this.mouseDownX;
    	this.top  += y - this.mouseDownY;    
    	this.refreshPosition();   
    	this.mouseDownX = x;
    	this.mouseDownY = y;
  	}
}

PortletPopupWindow.prototype.changePosition = function(){ 
   	this.parent.repositionPortlets(this);    
}
  
PortletPopupWindow.prototype.moveLeft = function(){ }
PortletPopupWindow.prototype.moveRight = function(){ }

PortletPopupWindow.prototype.close = function(){      
   	this.window.close(this);
   	this.parent.portlets[this.position][this.index] = null;  
   	for(var i=Light.portal.allPortlets.length-1;i>=0;i--){
		if(Light.portal.allPortlets[i] && Light.portal.allPortlets[i].serverId == this.serverId){
			Light.portal.allPortlets[i] = null;
			break;
		}
	}
   	if(this.maximized)   
     	this.parent.showOtherPortlets();   
  	this.parent.repositionPortlets(this); 
   	this.parent.repositionFooter();
   	if(Light.portal.originalLeft != null){
   		Light.portal.layout.containerLeft = Light.portal.originalLeft;
		Light.portal.header.style.marginLeft = Light.portal.originalHeaderLeft;
		Light.portal.body.style.marginLeft = Light.portal.originalBodyLeft;
		Light.portal.originalLeft = null; 
		Light.portal.originalHeaderLeft = null;
		Light.portal.originalBodyLeft = null;
   	}
   	Light.addHistory("");
} 
 
PortletPopupWindow.prototype.refreshWindowTransparent = function () { }
PortletPopupWindow.prototype.refreshTextColor = function () {}
