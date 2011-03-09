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
//------------------------------------------------------------ lightUtil.js
function log(message) {
}

function logDebug(message) {
    if (!log.window_ || log.window_.closed) {
        var win = window.open("", null, "width=400,height=200," +
                              "scrollbars=yes,resizable=yes,status=no," +
                              "location=no,menubar=no,toolbar=no");
        if (!win) return;
        var doc = win.document;
        doc.write("<html><head><title>Debug Log</title></head>" +
                  "<body></body></html>");
        doc.close();
        log.window_ = win;
    }
    var logLine = log.window_.document.createElement("div");
    logLine.appendChild(log.window_.document.createTextNode(message));
    log.window_.document.body.appendChild(logLine);
    logLine = null;
    
}

Light.setCookie = function (name, value, expires, path, domain, secure){
	if(!domain) domain = Light.getCookieDomain();
	Light.deleteCookie(name, path, domain);
    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "; path= /") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

Light.getCookie = function (name){
    var dc = document.cookie;
    if(!dc) return null;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1){
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }else{
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1){
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

Light.deleteCookie = function(name, path, domain){
    if (Light.getCookie(name)){
    	if(!domain) domain = Light.getCookieDomain();
        document.cookie = name + "=" + 
            ((path) ? "; path=" + path : "; path= /") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}

Light.getMessage = function(key){
	var element = L$(key);
	return (element) ? element.title : "";
}

Light.getCookieDomain = function(){
	var host = window.location.hostname;
	if(host.toLowerCase().startsWith("www.")) host = host.substr(4);
	var domainList = Light._DOMAIN_LIST.split(",");
	for(var i in domainList){
		if(host.indexOf(domainList[i]) >= 0){
			var domain = host.substring(0,host.indexOf(domainList[i]));
			var parts = domain.split(".");
			if(parts.length >= 2){		
				return "."+parts[parts.length - 1]+domainList[i];
			}else if(parts.length == 1){
				return "."+domain+domainList[i];
			}else
				return null;
		}
	}
	return null;
}

Light.alert = function(message){
	alert(message);
}

Light.confirm = function(message){
	return confirm(message);
}

Light.newElement = function(data){
	if(!data.element) return null;
	var element = document.createElement(data.element);   
	for(var name in data){
		if(name != 'element'){
			if(name != 'style')
				element[name] = data[name];
			else{
				for(var style in data.style){		
					element.style[style] = data.style[style];
				}
			}
		}
	}	
	if(data.element === 'a' && !data.href) 
		element.href = 'javascript:;'; 
	return element;
}

String.prototype.isNumeric = function() {
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char;
   for (var i=0,len=this.length;i<len && IsNumber;i++){ 
      Char = this.charAt(i); 
      if (ValidChars.indexOf(Char) == -1){
         IsNumber = false;
      }
   }
   return IsNumber;
}

String.prototype.isDigit = function() {
	if(this.length>1){return false;}
	var string="1234567890";
	if(string.indexOf(this)!=-1){return true;}
	return false;
}

String.prototype.trim = function () {
    return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.startsWith = function(str){
	//return (this.match("^"+str)==str)
	return this.substring(0,str.length) == str;
}

String.prototype.endsWith = function(str){
	return (this.match(str+"$")==str)
}

if (!Array.prototype.map){
	Array.prototype.map = function(fun /*, thisp*/){
    	var len = this.length;
    	if (typeof fun != "function")
      		throw new TypeError();
    	var res = new Array(len);
    	var thisp = arguments[1];
    	for (var i = 0; i < len; i++){
      		if (i in this)
        		res[i] = fun.call(thisp, this[i], i, this);
    	}
    	return res;
  	};
}

if (!Array.prototype.every){
	Array.prototype.every = function(fun /*, thisp*/){
    	var len = this.length;
    	if (typeof fun != "function")
      		throw new TypeError();
    	var thisp = arguments[1];
    	for (var i = 0; i < len; i++){
      		if (i in this &&
          		!fun.call(thisp, this[i], i, this))
        		return false;
    	}
    	return true;
  	};
}

if (!Array.prototype.filter){
	Array.prototype.filter = function(fun /*, thisp*/){
    	var len = this.length;
    	if (typeof fun != "function")
      		throw new TypeError();
    	var res = [];
	    var thisp = arguments[1];
	    for (var i = 0; i < len; i++){
	      	if (i in this){
	        	var val = this[i]; // in case fun mutates this
	        	if (fun.call(thisp, val, i, this))
	          		res.push(val);
	      	}
	    }
    	return res;
  	};
}

if (!Array.prototype.forEach){
	Array.prototype.forEach = function(fun /*, thisp*/){
	    var len = this.length;
	    if (typeof fun != "function")
	      	throw new TypeError();
	    var thisp = arguments[1];
	    for (var i = 0; i < len; i++){
	      if (i in this)
	        fun.call(thisp, this[i], i, this);
	    }
  	};
}

if (!Array.prototype.indexOf){
  	Array.prototype.indexOf = function(elt /*, from*/){
    	var len = this.length;
	    var from = Number(arguments[1]) || 0;
	    from = (from < 0)
	         ? Math.ceil(from)
	         : Math.floor(from);
	    if (from < 0)
	      	from += len;
    	for (; from < len; from++){
      		if (from in this &&
          		this[from] === elt)
       		return from;
    	}
    	return -1;
  	};
}

if (!Array.prototype.lastIndexOf){
  	Array.prototype.lastIndexOf = function(elt /*, from*/){
    	var len = this.length;
    	var from = Number(arguments[1]);
    	if (isNaN(from)){
      		from = len - 1;
    	}
    	else{
	      	from = (from < 0)
	           ? Math.ceil(from)
	           : Math.floor(from);
	      	if (from < 0)
	        	from += len;
	     	else if (from >= len)
	        	from = len - 1;
    	}
    	for (; from > -1; from--){
      		if (from in this &&
          		this[from] === elt)
        		return from;
    	}
    	return -1;
  	};
}

if (!Array.prototype.reduce){
  	Array.prototype.reduce = function(fun /*, initial*/){
	    var len = this.length;
    	if (typeof fun != "function")
     		throw new TypeError();
	    // no value to return if no initial value and an empty array
    	if (len == 0 && arguments.length == 1)
     		throw new TypeError();
	    var i = 0;
    	if (arguments.length >= 2){
	    	var rv = arguments[1];
    	}else{
      		do{
        		if (i in this){
		          	rv = this[i++];
          			break;
        		}
        		// if array contains no values, no initial value to return
        		if (++i >= len)
          			throw new TypeError();
      		}
      		while (true);
    	}
	    for (; i < len; i++){
      		if (i in this)
        		rv = fun.call(null, rv, this[i], i, this);
    	}
    	return rv;
  	};
}

if (!Array.prototype.reduceRight){
  	Array.prototype.reduceRight = function(fun /*, initial*/){
    	var len = this.length;
    	if (typeof fun != "function")
      		throw new TypeError();
    	// no value to return if no initial value, empty array
    	if (len == 0 && arguments.length == 1)
      		throw new TypeError();
    	var i = len - 1;
    	if (arguments.length >= 2){
      		var rv = arguments[1];
    	}else{
      		do{
        		if (i in this){
          			rv = this[i--];
          			break;
        		}
        		// if array contains no values, no initial value to return
        		if (--i < 0)
          			throw new TypeError();
      		}
      		while (true);
    	}
    	for (; i >= 0; i--){
      		if (i in this)
        		rv = fun.call(null, rv, this[i], i, this);
    	}
    	return rv;
  	};
}

if (!Array.prototype.some){
  	Array.prototype.some = function(fun /*, thisp*/){
    	var len = this.length;
    	if (typeof fun != "function")
      		throw new TypeError();
    	var thisp = arguments[1];
    	for (var i = 0; i < len; i++){
      		if (i in this &&
          		fun.call(thisp, this[i], i, this))
        		return true;
    	}
    	return false;
  	};
}

Array.prototype.exists = function(value){
  for (var i=0; i<this.length; i++){
	if (typeof this[i] !== 'undefined' && this[i] != null && this[i] == value) return true;	
  }	
  return false;
} 

Array.prototype.joinValue = function(sign){
	var value = '';
  	for (var i=0; i<this.length; i++){
		if (typeof this[i] !== 'undefined' && this[i] != null && this[i].length > 0){
			if(value.length == 0)
				value = this[i];
			else
				value += sign+this[i];
		}	
  	}	
  	return value;
} 

Array.prototype.get = function(value, param){
  for (var i=0; i<this.length; i++){
  	if(typeof param != "undefined" && param != null)
  		if (typeof this[i] !== 'undefined' && this[i] != null && this[i][param] == value) return this[i];
  	else
  	    if (typeof this[i] !== 'undefined' && this[i] != null && this[i] == value) return this[i];	
  }	
  return null;
} 

function isIE(){
	return /msie/i.test(navigator.userAgent) && !/opera/i.test(navigator.userAgent);
}

function isFirefox(){
	var ua = navigator.userAgent.toLowerCase();
	return (ua.indexOf('firefox') >= 0) ? true : false;
}

function isGecko(){
	var ua = navigator.userAgent.toLowerCase();
	return (ua.indexOf('gecko') >= 0) ? true : false;
}

function isChrome(){
	var ua = navigator.userAgent.toLowerCase();
	return (ua.indexOf('chrome') >= 0) ? true : false;
}

function isSafari(){
	var ua = navigator.userAgent.toLowerCase();
	return (ua.indexOf('safari/') >= 0) ? ((ua.indexOf('chrome') >= 0) ? false : true) : false;
}

function isOpera(){
	return (typeof window.opera !== "undefined") ? true : false;
}

function isIPhone(){
	return ((navigator.userAgent.match(/iPhone/i)) && !(navigator.userAgent.match(/iPad/i)));
}

function isIPad(){
	return navigator.userAgent.match(/iPad/i);
}

function getWindowHeight(){
	return document.documentElement.scrollHeight;
}

function getMousePositionX(e){
	var x,y=0;
	if(!isIE()){
		if (e){
		    if (e.pageX || e.pageY)
		    { // this doesn't work on IE6!! (works on FF,Moz,Opera7)
		      x = e.pageX;
		      y = e.pageY;
		    }
		    else if (e.clientX || e.clientY)
		    { // works on IE6,FF,Moz,Opera7
		      x = e.clientX + document.body.scrollLeft;
		      y = e.clientY + document.body.scrollTop;
		    }
		}
	}else{
		if (window.event) {	    
			x = event.clientX  + document.body.scrollLeft;	  
	     	y = event.clientY;  
		}else if (e){
		    if (e.pageX || e.pageY)
		    { // this doesn't work on IE6!! (works on FF,Moz,Opera7)
		      x = e.pageX;
		      y = e.pageY;
		    }
		    else if (e.clientX || e.clientY)
		    { // works on IE6,FF,Moz,Opera7
		      x = e.clientX  + document.body.scrollLeft;
		      y = e.clientY;
		    }
		}
	}
	if(!x) x = 0;
	return x;
}

function getMousePositionY(e){
	var x,y=0;
	if(!isIE()){
		if (e){
		    if (e.pageX || e.pageY)
		    { // this doesn't work on IE6!! (works on FF,Moz,Opera7)
		      x = e.pageX;
		      y = e.pageY;
		    }
		    else if (e.clientX || e.clientY)
		    { // works on IE6,FF,Moz,Opera7
		      x = e.clientX + document.body.scrollLeft;
		      y = e.clientY + document.body.scrollTop;
		    }
		}
	}else{
		if (window.event) {	    
			x = event.clientX;	  
	     	y = event.clientY + document.body.scrollTop;  
		}else if (e){
		    if (e.pageX || e.pageY)
		    { // this doesn't work on IE6!! (works on FF,Moz,Opera7)
		      x = e.pageX;
		      y = e.pageY;
		    }
		    else if (e.clientX || e.clientY)
		    { // works on IE6,FF,Moz,Opera7
		      x = e.clientX;
		      y = e.clientY + document.body.scrollTop;
		    }
		}
	}
	if(!y) y = 0;
	return y;
}

function setPosition(oElement,x,y){
	if (document.all) {	
       if(typeof x != "undefined") oElement.style.posLeft = x;
   	   if(typeof y != "undefined") oElement.style.posTop = y;
    }else{
  	   if(typeof x != "undefined") oElement.style.left = x;
       if(typeof y != "undefined") oElement.style.top = y;
    }
}
//get left attribute for positioned item (relative X)
function getPositionX(oElement){
	var x = 0;
	if(oElement){
		if (document.all) {	
	       x = parseInt(oElement.style.posLeft);
	    }else{
	  	   x = parseInt(oElement.style.left);
	    }
    }
    return x;
}
//get top attribute for positioned item (relative Y)
function getPositionY(oElement){
	var y = 0;
	if(oElement){
		if (document.all) {	
	   	   y = parseInt(oElement.style.posTop);
	    }else{  	   
	       y = parseInt(oElement.style.top);
	    }
    }
    return y;
}
//get absolute X
function getX( oElement ){
	var iReturnValue = 0;
	if(!isIE()){
		while( oElement != null ) {
			iReturnValue += oElement.offsetLeft;
			oElement = oElement.offsetParent;
		}
	}else{
		var obj = oElement.getBoundingClientRect();		
		iReturnValue = obj.left;
		if(document.body.scrollLeft != null)
			iReturnValue = obj.left + document.body.scrollLeft;
	}
	return iReturnValue;
}
//get absolute Y
function getY( oElement ){
	var iReturnValue = 0;
	if(!isIE()){
		while( oElement != null ) {
			iReturnValue += oElement.offsetTop;
			oElement = oElement.offsetParent;
		}
	}else{
		var obj = oElement.getBoundingClientRect();				
		iReturnValue = obj.top;
		if(document.body.scrollTop != null)
			iReturnValue = obj.top + document.body.scrollTop;
	}
	return iReturnValue;
}

function getScreenCenterY() {     
	return getScrollOffset()+(getInnerHeight()/2);   
}  
   
function getScreenCenterX() {  
	return (document.body.clientWidth/2);  
}  
   
function getInnerHeight() {  
	 var y;  
	 if (self.innerHeight){ // all except Explorer  
	 	y = self.innerHeight;  
	 }else if (document.documentElement && document.documentElement.clientHeight){  
		// Explorer 6 Strict Mode    
		y = document.documentElement.clientHeight;  
	 }else if (document.body){ // other Explorers   
	 	y = document.body.clientHeight;  
	 }  
	 return y;  
 }  
   
function getScrollOffset() {  
	var y;  
	if (self.pageYOffset) { // all except Explorer  
		y = self.pageYOffset;  
	}  
	else if (document.documentElement && document.documentElement.scrollTop) {
		// Explorer 6 Strict  
		y = document.documentElement.scrollTop;  
	}else if (document.body){ // all other Explorers    
		y = document.body.scrollTop;  
	}  
	return y;  
 }  

function addEventHandler(obj,eventName,handler){
	removeEvent(obj,eventName,handler);
	if(document.attachEvent){
		obj.attachEvent("on"+eventName, handler);
	} else if(document.addEventListener){
		obj.addEventListener(eventName, handler, false);
	}
}

function removeEvent(obj,eventName,handler){
	try{
		if(obj.removeEventListener) obj.removeEventListener(eventName,handler,false);
	  	else if(obj.detachEvent){
	    	obj.detachEvent("on"+eventName,obj[eventName+handler]);
	    	obj[eventName+handler]=null;
	    	obj["e"+eventName+handler]=null;
	  	}
	}catch(err){}
	
}

/**
*
*  AJAX IFRAME METHOD (AIM)
*  http://www.webtoolkit.info/
*
**/

AIM = {
    portletId : null
    ,
	frame : function(c) {
		var n = 'f' + Math.floor(Math.random() * 99999);
		var d = document.createElement('DIV');
		d.innerHTML = '<iframe style="display:none" src="about:blank" id="'+n+'" name="'+n+'" onload="AIM.loaded(\''+n+'\')"></iframe>';
		document.body.appendChild(d);

		var i = document.getElementById(n);
		if (c && typeof(c.onComplete) == 'function') {
			i.onComplete = c.onComplete;
		}

		return n;
	},

	form : function(f, name) {
		f.setAttribute('target', name);
	},

	submit : function(f, c, portletId) {
	    AIM.portletId = portletId;
		AIM.form(f, AIM.frame(c));
		if (c && typeof(c.onStart) == 'function') {
			return c.onStart(portletId);
		} else {
			return true;
		}
	},

	loaded : function(id) {
		var i = document.getElementById(id);
		if (i.contentDocument) {
			var d = i.contentDocument;
		} else if (i.contentWindow) {
			var d = i.contentWindow.document;
		} else {
			var d = window.frames[id].document;
		}
		if (d.location.href == "about:blank") {
			return;
		}
		
		if (typeof(i.onComplete) == 'function') {
			i.onComplete(AIM.portletId,d.getElementsByTagName('p')[0].childNodes[0].textContent);
		}
	}
}

var Base64 = {
	// private property
	_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
 
	// public method for encoding
	encode : function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
 
		input = Base64._utf8_encode(input);
 
		while (i < input.length) {
 
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
 
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
 
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
 
			output = output +
			this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
			this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
 
		}
 
		return output;
	},
 
	// public method for decoding
	decode : function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
 
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
 
		while (i < input.length) {
 
			enc1 = this._keyStr.indexOf(input.charAt(i++));
			enc2 = this._keyStr.indexOf(input.charAt(i++));
			enc3 = this._keyStr.indexOf(input.charAt(i++));
			enc4 = this._keyStr.indexOf(input.charAt(i++));
 
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
 
			output = output + String.fromCharCode(chr1);
 
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
 
		}
 
		output = Base64._utf8_decode(output);
 
		return output;
 
	},
 
	// private method for UTF-8 encoding
	_utf8_encode : function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
 
		for (var n = 0; n < string.length; n++) {
 
			var c = string.charCodeAt(n);
 
			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
 
		return utftext;
	},
 
	// private method for UTF-8 decoding
	_utf8_decode : function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
 
		while ( i < utftext.length ) {
 
			c = utftext.charCodeAt(i);
 
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
 
		}
 
		return string;
	}
 
}