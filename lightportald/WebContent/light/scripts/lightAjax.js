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
//------------------------------------------------------------  lightAjax.js
// Create the loadXML method and xml getter for Mozilla
if ( window.DOMParser &&
	  window.XMLSerializer &&
	  window.Node && Node.prototype && Node.prototype.__defineGetter__ ) {

   if (!Document.prototype.loadXML) {
      Document.prototype.loadXML = function (s) {
         var doc2 = (new DOMParser()).parseFromString(s, "text/xml");
         while (this.hasChildNodes())
            this.removeChild(this.lastChild);

         for (var i = 0; i < doc2.childNodes.length; i++) {
            this.appendChild(this.importNode(doc2.childNodes[i], true));
         }
      };
	}

	Document.prototype.__defineGetter__( "xml",
	   function () {
		   return (new XMLSerializer()).serializeToString(this);
	   }
	 );
}

Light.ajax  = {
	sendRequestAndUpdate : function(requestName,container,options) {
      	var request = this.getXmlHttpObject();
      	if(!options.method) options.method="post";
      	if(options.asynchronous == null) options.asynchronous=true;
      	var url= (requestName.lastIndexOf("/") <= 0) ? Light.getContextPath()+requestName : requestName;
      	if(options.method == 'get'){
      		if(url.indexOf("?") > 0)
      			url+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      		else
      			url+="?_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	}
      	request.open(options.method, url, options.asynchronous);
      	if(options.asynchronous){
	      	request.onreadystatechange = function(){
	          	if(request.readyState == 4){
	             	Light.ajax.onRequestComplete(request);                    
	          	}
	      	};
	  	}
      	if(options.method == 'post')
         	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
      	else
         	request.setRequestHeader("Content-Type","text/xml");
      	var parameter = options.parameters;
      	if(options.postBody) parameter = options.postBody;
      	if(parameter) 
      		parameter+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	else
      		parameter="_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	request.send(options.method == 'post' ? parameter : null);
      	log("url: "+url+" method: "+options.method+ ((options.method == 'post') ? " parameter: "+parameter : "") +" asyn: "+options.asynchronous); 
	  	setTimeout((function() {Light.ajax.timeout(request,container)}), Light._REQUEST_TIMEOUT);
	  	if(!options.asynchronous) Light.ajax.onRequestComplete(request);
	},
	timeout : function(request,container) {
	   //The request status of the AJAX object: Successful is 2xx
	   if(request){
	   		if(request.status<200||request.status>=300){
			   request.abort();
			   Light.setPortletContent(container, "Content is not available.");
			   request = null;
		   	}
	   }else{
	   		Light.setPortletContent(container, "Content is not available.");
	   }
	},
	sendRequest : function (requestName, options) {		 
      	var request = this.getXmlHttpObject();
      	if(!options.method) options.method = 'post';
      	if(options.asynchronous == null) options.asynchronous=true;
      	var context = Light.getContextPath();
      	var url= (requestName.lastIndexOf("/") <= 0) ? ( (context) ? context+requestName : requestName) : requestName;
      	if(options.method == 'get'){
      		if(url.indexOf("?") > 0)
      			url+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      		else
      			url+="?_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	}
      	request.open(options.method, url, options.asynchronous);
      	if(options.asynchronous){
	      	request.onreadystatechange = function(){
	          if(request.readyState == 4 && request.status == 200){
	             if(options.onSuccess != null)
	             	options.onSuccess(request);
	             	request = null;
	          }
	      	};
      	}
      	if(options.method == 'post')
         	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
      	else
         	request.setRequestHeader("Content-Type","text/xml");
      	var parameter = options.parameters;
      	if(options.postBody) parameter = options.postBody;
      	if(parameter) 
      		parameter+="&_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	else
      		parameter="_requestOwnerId="+Light.getUserId()+"&clientUrl="+document.location.href;
      	request.send(options.method == 'post' ? parameter : null);
      	log("url: "+url+" method: "+options.method+ ((options.method == 'post') ? " parameter: "+parameter : "") +" asyn: "+options.asynchronous); 
      	if(!options.asynchronous) return request.responseText;
   },
   
	onRequestComplete : function(request) {
		if(!request) return;
		// User can set an onFailure option - which will be called by prototype
		if (request.status != 200) return;
		if(request.responseText){
			var content = request.responseText;
			var index   = content.indexOf("id='");
			while(index > 0){
				var portletContent = content.substr(index+4);
	      		index = portletContent.indexOf("'")
		      	var responseId = portletContent.substring(0,index);
		      	index = portletContent.indexOf("'>");
		      	portletContent = portletContent.substr(index+2);
		      	index = portletContent.indexOf("</response>");
		      	var pContent = portletContent.substring(0,index);
		      	if(responseId.startsWith(Light._PT_PREFIX)){
		      		if(pContent && pContent.trim().length>0){
		      			var data = JSON.parse(pContent);		      			
		      			Light.setPortletTitle(responseId, data);
		      		}
		      	}else
			      	Light.setPortletContent(responseId, pContent);
		      	content = portletContent.substr(index);
		      	index  = content.indexOf("id='");
	      	}
		}else if(request.responseXML){
			var response = request.responseXML.getElementsByTagName("ajax-response");
			if (response)
				Light.ajax.processAjaxResponse( response[0].childNodes );
		}
		request = null;
	},

	processAjaxResponse : function( xmlResponseElements ) {
      for(var i = 0 ; i < xmlResponseElements.length ; i++ ) {
         var responseElement = xmlResponseElements[i];
         // only process nodes of type element.....
         if ( responseElement.nodeType != 1 )
            continue;            
         var responseId = responseElement.getAttribute("id"); 
         var content = Light.ajax.getContentAsString(responseElement);  
         if(responseId.startsWith(Light._PT_PREFIX)){
         	if(content && content.trim().length>0){
         		var data = JSON.parse(content);
		    	Light.setPortletTitle(responseId, data);
		    }
      	 }else
	      	Light.setPortletContent(responseId, content);        
      }
	},
   
	getContentAsString : function( parentNode ) {
      return parentNode.xml != undefined ? 
         Light.ajax.getContentAsStringIE(parentNode) :
         Light.ajax.getContentAsStringMozilla(parentNode);
	},

	getContentAsStringIE : function(parentNode) {
     var contentStr = "";
     for ( var i = 0 ; i < parentNode.childNodes.length ; i++ ) {
         var n = parentNode.childNodes[i];
         if (n.nodeType == 4) {
             contentStr += n.nodeValue;
         }
         else {
           contentStr += n.xml;
       }
     }
     return contentStr;
	},

	getContentAsStringMozilla : function(parentNode) {
     var xmlSerializer = new XMLSerializer();
     var contentStr = "";
     for ( var i = 0 ; i < parentNode.childNodes.length ; i++ ) {
          var n = parentNode.childNodes[i];
          if (n.nodeType == 4) { // CDATA node
              contentStr += n.nodeValue;
          }
          else {
            contentStr += xmlSerializer.serializeToString(n);
        }
     }
     return contentStr;
  	},
     
	getXmlHttpObject : function() {
	  var xmlhttp;
	  try {
	    xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  } catch (e) {
	     try {
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	     } catch (E) {
	        xmlhttp = false;
	     }
	  }	
	  if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
	    try {
	      xmlhttp = new XMLHttpRequest();
	    } catch (e) {
	      xmlhttp = false;
	    }
	  }	
	  return xmlhttp;
	}
}