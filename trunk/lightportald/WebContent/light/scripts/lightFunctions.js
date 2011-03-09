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
//-----------------------------------------  lightFunctions.js -- Portal System Functions
Light.securityCheck = function(){	 
  	if(!Light.isLogin()){
    	Light.showAllLogin(false);
    	return false;
  	}else
  		return true;
}
Light.signUpAfter = function(id,userId){
	if(Light.portal.latestAction.method != null){
		Light.userId = userId;
		Light.setCookie(Light._LOGINED_USER_ID,Light.userId);
		Light.deleteCookie(Light._CURRENT_TAB); 
		Light.deleteCookie(Light._IS_SIGN_OUT);     
	    Light.portal.latestAction.portlet.close(true);
	    Light.getPortletById(id).close(true);
	    Light.setSessionTimer();
	    Light.portal.refreshPortalMenu();         
	    var method = Light.portal.latestAction.method+"(null,'"+Light.portal.latestAction.id+"')";
		eval(method);
	}else{
		Light.reloadPortal();
	}
}
Light.validateOpenIdLogin= function(form){
	if(form.openid_identifier.value == null || form.openid_identifier.value.length <=8){
	 	Light.alert("Please input correct OpenID URL."); 
	 	return false;
	}else
	 	return true;
}

Light.loginPortal = function(id){
	var userId = document.forms['form_'+id]['email'].value;
	var password = document.forms['form_'+id]['password'].value;  
	if(!userId || !password){
      	Light.alert("please input User ID and password.");
      	return;
   	}
   	Light.userId = userId;
	if(!document.forms['form_'+id]['rememberMe'].checked) {     
		Light.deleteCookie(Light._REMEMBERED_USER_ID);
		Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
	}   	
  	var portlet = Light.getPortletById(id);
  	var params = "portletId="+portlet.serverId
	              +"&tabId="+portlet.parent.serverId  			 
	  			  +"&userId="+escape(encodeURIComponent(userId))
	  			  +"&password="+escape(encodeURIComponent(password))	 			  
  				;		
  	if(document.forms['form_'+id]['rememberMe'].checked) {     
     	params+="&rememberme=1";
  	} 
  	Light.ajax.sendRequest(Light._LOGIN, {parameters:params, method: 'post', onSuccess:Light.loginPortalHandler});   
}

Light.loginPortalHandler = function(t){
	var login = JSON.parse(t.responseText);
	if(login.status == "1"){
		Light.setCookie(Light._LOGINED_USER_ID,Light.userId);
		Light.deleteCookie(Light._CURRENT_TAB); 
		Light.deleteCookie(Light._IS_SIGN_OUT);
		Light.needRender = true;
  		Light.needReload = true;
  		Light.refreshPortal();     	
	}else{
		Light.userId = null;
		Light.deleteCookie(Light._REMEMBERED_USER_ID);
       	Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
		if(login.status == "0"){
		   Light.alert("This User ID is not signed up yet , please sign up first.");
		}else if(login.status == "-1"){
		    Light.alert("you input wrong password , please try again.");
	    }else if(login.status == "-2"){
		    Light.alert("you Account is disabled , please contact with Administrator.");
	    }else if(login.status == "-3"){
		    Light.alert("you Account is locked, please contact with Administrator.");
		}else{
		    Light.alert("System error, please contact with Administrator.");
		}	 			
	}    
}
Light.loginToPortal = function(id){
  	var userId = document.forms['form_'+id]['email'].value;
  	var password = document.forms['form_'+id]['password'].value;  
  	if(!userId || !password){
      	Light.alert("please input User ID and password.");
      	return;
   	}
   	Light.userId = userId;
   	if(!document.forms['form_'+id]['rememberMe'].checked) {     
		Light.deleteCookie(Light._REMEMBERED_USER_ID);
		Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
	}   
  	var portlet = Light.getPortletById(id);
  	var params = "portletId="+portlet.serverId
	              +"&tabId="+portlet.parent.serverId  			 
	  			  +"&userId="+escape(encodeURIComponent(userId))
	  			  +"&password="+escape(encodeURIComponent(password))	 			  
  				;
  	if(document.forms['form_'+id]['rememberMe'].checked) {     
     	params+="&rememberme=1";
  	} 
  	Light.ajax.sendRequest(Light._LOGIN, {parameters:params, method: 'post', onSuccess:Light.LoginToPortalHandler});   
}
Light.LoginToPortalHandler = function(t){
	var login = JSON.parse(t.responseText);
	if(login.status == "1"){
		Light.setCookie(Light._LOGINED_USER_ID,Light.userId);
		Light.deleteCookie(Light._CURRENT_TAB); 
		Light.deleteCookie(Light._IS_SIGN_OUT);     
        Light.portal.latestAction.portlet.close(true);
        Light.setSessionTimer();
        Light.portal.refreshPortalMenu();
		if(Light.portal.latestAction.method != null){
        	var method = Light.portal.latestAction.method+"(null,'"+Light.portal.latestAction.id+"')";
			eval(method);
		}else{
			Light.refreshPortal();
		}
	}else{
		Light.userId = null;
		Light.deleteCookie(Light._REMEMBERED_USER_ID);
       	Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
		if(login.status == "-1"){
		   Light.alert("This User ID is not signed up yet , please sign up first.");  
		}else if(login.status == "-2"){
		    Light.alert("you input wrong password , please try again.");
	    }else if(login.status == "-3"){
		    Light.alert("you Account is disabled , please contact with Administrator.");
	    }else if(login.status == "-4"){
		    Light.alert("you Account is locked, please contact with Administrator.");
		}else{
		    Light.alert("System error, please contact with Administrator.");
		}
	}    
}

Light.showOpenIdSignIn = function (id){
 	var portlet = new PortletPopupWindow(11,200,600,Light.getMessage('MENU_SIGN_IN'),"icons openid_small_logo","","openIdSignIn","/openIdSignIn"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"",false,false);    
    portlet.refresh();    
    Light.addFunctionHistory("Light.showOpenIdSignIn");   
}

Light.showSignUp = function (id){
 	var portlet = new PortletPopupWindow(11,200,600,Light.getMessage('TITLE_SIGN_UP'),"icons user","","registrationPortlet","/registrationPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"",false,false,true,5,2);    
    portlet.refresh();   
    Light.addFunctionHistory('Light.showSignUp');    
}

Light.showInboxMessage = function(){
 	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_MESSAGE_INBOX'),"icons inbox","","messagePortlet","/messagePortlet"+Light._REQUEST_SUFFIX,true,true,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
    Light.addFunctionHistory("Light.showInboxMessage");
}

Light.showSentMessage = function(){
 	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_MESSAGE_SENT'),"icons message","","messagePortlet","/messagePortlet"+Light._REQUEST_SUFFIX,true,true,false,false,false,false,false,0,false,'','','',"type=sent");    
    portlet.refresh();
    window.scrollTo(0,0);
    Light.addFunctionHistory("Light.showSentMessage");
}

Light.showFriendRequest = function(){
 	var portlet = new PortletPopupWindow(11,200,500,Light.getMessage('MENU_FRIEND_REQUEST'),"icons friend","","messagePortlet","/messagePortlet"+Light._REQUEST_SUFFIX,true,true,false,false,false,false,false,0,false,'','','',"type=connection");     
    portlet.refresh();
    window.scrollTo(0,0);
    Light.addFunctionHistory("Light.showFriendRequest");
}

Light.showInviteFriend = function(){
 	var portlet = new PortletPopupWindow(11,200,400,Light.getMessage('MENU_INVITE_FRIEND'),"icons friend","","inviteFriendPortlet","/inviteFriendPortlet"+Light._REQUEST_SUFFIX,true,true,false,false,false,false,false,0,false,'','','','');    
    portlet.refresh();
    window.scrollTo(0,0);
    Light.addFunctionHistory("Light.showInviteFriend");
}

Light.showUploader = function(id, name) {
	var data = {
        id : id,  	  		
        popupName : name
    };
    createPopupDiv(name,name+'.jst',640,data,null,id);         
}

Light.closeUploader = function(id, name) {
	hidePopupDiv(name);
	var portlet = Light.getPortletById(id);
	if(portlet != null){
		portlet.mode = Light._VIEW_MODE;
		portlet.lastAction = null;
		portlet.rememberMode(0);
		portlet.refresh();
	}            
}

Light.toMyAccount = function(){
	Light.setCookie(Light._CURRENT_TAB,0);
	window.location=Light.getContextPath()+"/ref/myaccount?clientUrl="+document.location.href;
}

Light.toHome = function(){
	var url = document.location.href;
	url  = "http://"+url.substr(url.indexOf(".")+1);
	if(url.indexOf("#") > 0) url = url.substring(0,url.indexOf("#"));
	window.location=url;
}

Light.showLinkAction = function (e, id, itemId, itemLink, image, name) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;       
   var old = document.getElementById(name);
   if(old != null){
      hideTopPopupDiv(name);
      image.src=Light.getContextPath()+"/light/images/showMod.gif";
      return;  
   } 
   var data = {
        id : id,
    	itemId : itemId,   	
    	itemLink : itemLink,   	   	  		
        popupName : name
 	};
	createTopPopupDiv(name,name+'.jst',10,data,e,id,true); 
	L$(name).onmouseout = function(e) { hideTopPopupDiv(name,e);}
}

Light.doLinkAction = function (id,itemId,action,name) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;    
   var pars = "responseId="+id
	  	    +"&tabId="+Light.getCurrentTab().serverId
	  	    +"&portletId="+portlet.serverId
            +"&mode="+portlet.mode
            +"&action="+action
            +"&parameter="+itemId;
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});
   hideTopPopupDiv(name);			                
}  

Light.logout = function(){
   Light.deleteCookie(Light._LOGINED_USER_ID);
   Light.deleteCookie(Light._CURRENT_TAB);    
   Light.setCookie(Light._IS_SIGN_OUT,"true");   
   var params = "action=logout";
   Light.ajax.sendRequest(Light._LOGOUT, {parameters:params,onSuccess:Light.logoutHandler});  
}

Light.logoutHandler = function(t){  
	var original = document.location.href;
	if(original.indexOf("/m.jsp") > 0)
		window.location=Light.getContextPath()+"/m.jsp";
	else
		window.location=Light.getContextPath()+"/index.jsp";
}

Light.closePortlet = function(id,flag){
    if(flag == null) flag = true;
	var portlet = Light.getPortletById(id);
    if(portlet != null){
       	portlet.close(flag);
    }
}
Light.changeLanguage = function(language){
	if(Light.getCookie(Light._USER_LOCALE)){
		Light.deleteCookie(Light._USER_LOCALE);
	}
	var date = new Date();    
	date.setFullYear(date.getFullYear()+1);
	Light.setCookie(Light._USER_LOCALE,language,date);	
	var params = "language="+language;
	Light.ajax.sendRequest(Light._CHANGE_LANGUAGE, {parameters:params, onSuccess:Light.changeLanguageHandler});    
}
Light.changeLanguageHandler = function(){
	Light.refreshPortal();
}
Light.saveLanguage = function(id,finished){
	var len = document.forms['form_'+id]['language'].length; 
	var language = null;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['language'][i].checked) {
			language = document.forms['form_'+id]['language'][i].value;
		}
	}	
	var params = "language="+language;
	if(!finished)
		Light.ajax.sendRequest(Light._CHANGE_LANGUAGE, {parameters:params, onSuccess:Light.saveOptionHandler});    
	else{
		Light.closePortlet(id);
		Light.changeLanguage(language);
	}
}
Light.saveOptionHandler = function(t){
	var data = JSON.parse(t.responseText);
	var optionsMessage = L$('optionsMessage');
	if(optionsMessage)
		optionsMessage.innerHTML= (data.status == 1) ? '<br/>Option have been saved successfully.' : '<br/>Option save failed, please try again';
}
Light.saveRegion = function(id,finished){
	var len = document.forms['form_'+id]['region'].length; 
	var region = null;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['region'][i].checked) {
			region = document.forms['form_'+id]['region'][i].value;
		}
	} 
	var params = "region="+region;
   	Light.ajax.sendRequest(Light._CHANGE_REGION, {parameters:params, onSuccess: (finished) ? Light.refreshPortal : Light.saveOptionHandler});     	
}
Light.saveTimeZone = function(id,finished){
	var len = document.forms['form_'+id]['timeZone'].length; 
	var timeZone = null;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['timeZone'][i].checked) {
			timeZone = document.forms['form_'+id]['timeZone'][i].value;
     	}
	}
	var params = "responseId="+id+"&timeZone="+timeZone;
	Light.ajax.sendRequest(Light._CHANGE_TIMEZONE, {parameters:params, onSuccess: (finished) ? Light.closeOption : Light.saveOptionHandler});       	
}
Light.closeOption = function(t){
   	Light.closePortlet(t.responseText, true);
}
Light.saveGeneral = function(id){      
  	var params = [];
  	var fontSize = document.forms['form_'+id]['ptFontSize'].value;  	
  	Light.portal.data.fontSize = fontSize;
    if(fontSize){
     	params.push("fontSize="+fontSize);
  	}
  	var textFont = document.forms['form_'+id]['pcTextFont'].value; 
  	Light.portal.data.textFont = textFont;
    if(textFont){
     	params.push("textFont="+textFont);
  	}
  	var textColor = document.forms['form_'+id]['pcTextColor'].value;
  	if(textColor){
     	Light.portal.data.textColor = textColor;
     	params.push("textColor="+textColor);
  	}
  	if(document.forms['form_'+id]['showSearchBar'].checked && !Light.portal.data.showSearchBar){
     	Light.portal.data.showSearchBar = true;
     	params.push("showSearchBar=1");
     	Light.portal.refreshPortalMenu();
   	}
   	if(!document.forms['form_'+id]['showSearchBar'].checked && Light.portal.data.showSearchBar){
     	Light.portal.data.showSearchBar = false;
     	params.push("showSearchBar=0");
     	Light.portal.refreshPortalMenu();
   	}
   	if(document.forms['form_'+id]['transparent'].checked  && !Light.portal.data.portletWindowTransparent){
     	params.push("transparent=1");    
   	} 
   	if(!document.forms['form_'+id]['transparent'].checked  && Light.portal.data.portletWindowTransparent){
     	params.push("transparent=0");    
   	} 
   	if((document.forms['form_'+id]['transparent'].checked && !Light.portal.data.portletWindowTransparent) ||
     	(!document.forms['form_'+id]['transparent'].checked && Light.portal.data.portletWindowTransparent)){
     	Light.portal.data.portletWindowTransparent =!Light.portal.data.portletWindowTransparent;
     	Light.refreshWindowTransparent();
  	}
  	Light.portal.data.maxShowTabs = document.forms['form_'+id]['ptMaxShowTabs'].value;
  	params.push("maxShowTabs="+Light.portal.data.maxShowTabs);
  	Light.refreshTextFontSize();        	
  	if(params.length > 0){
    	params.push("responseId="+id);
    	Light.portal.refreshPortalHeader(); 
    	Light.ajax.sendRequest(Light._CHANGE_GENERAL, {parameters:params.join('&'), onSuccess:Light.saveOptionHandler}); 
  	} 
}
Light.defaultGeneral = function(id){   
  	Light.portal.data.fontSize = 0;  
  	Light.portal.data.textFont = ""; 
  	Light.portal.data.textColor = ""; 
  	Light.portal.data.showSearchBar = true;
  	Light.portal.data.portletWindowTransparent = false;  
  	Light.portal.data.maxShowTabs=10;
  	document.forms['form_'+id]['ptFontSize'].value = 0;
  	document.forms['form_'+id]['showSearchBar'].checked = true;
  	document.forms['form_'+id]['transparent'].checked = false;
  	document.forms['form_'+id]['pcTextFont'].value ="";
  	document.forms['form_'+id]['pcTextColor'].value ="";  
  	var params="textFont=&textColor=&headerHeight=0&fontSize=0&showSearchBar=1&transparent=0&maxShowTabs=10&responseId="+id; 
  	Light.portal.refreshPortalHeader(); 
  	Light.refreshWindowTransparent();
  	Light.refreshTextFontSize();    
  	Light.ajax.sendRequest(Light._CHANGE_GENERAL, {parameters:params, onSuccess:Light.saveOptionHandler}); 
}
Light.showTheme = function(e,theme,caption,id){
   	var data = {
        popupName : "showTheme",
   		caption : caption,
        theme : theme
   	};
   	createTopPopupDiv('showTheme','showTheme.jst',500,data,e,id); 
}
Light.hideTheme = function(){
   	hideTopPopupDiv('showTheme'); 
}
Light.selectTheme = function(theme){
	Light.themeLink=Light.newElement({element:'link',rel:'stylesheet',type:'text/css',
					href:Light.getThemePath(theme) + ((document.all) ? '/MSIE' : '') + '/theme.css'});
	document.getElementsByTagName("head")[0].insertBefore(Light.themeLink,document.getElementsByTagName('link')[1]);    
}
Light.saveTheme = function(id,finished){
	var len =  document.forms['form_'+id]['ptTheme'].length;
	var theme = null;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptTheme'][i].checked) {
			theme = document.forms['form_'+id]['ptTheme'][i].value;
		}
	}  
	if(Light.themeLink){    
		document.getElementsByTagName('link')[2].href=Light.themeLink.href;
		document.getElementsByTagName("head")[0].removeChild(Light.themeLink);
		Light.themeLink = null;
	}
	var bgRepeat = null;
	len = document.forms['form_'+id]['ptRepeat'].length;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptRepeat'][i].checked) {
			bgRepeat = document.forms['form_'+id]['ptRepeat'][i].value;
		}
	}
	if(Light.portal.data.bgRepeat != bgRepeat){
		Light.portal.data.bgRepeat = bgRepeat;
		if(Light.portal.data.bgImage != "no"){
			 var backgroundImage = Light.portal.data.bgImage;
	         if(backgroundImage.indexOf("http") < 0)
	         	backgroundImage = Light.getContextPath()+Light.portal.data.bgImage;		        
	         if(Light.portal.data.bgRepeat == 1)
	            document.body.style.background = "url('"+backgroundImage+"') no-repeat " + Light.portal.data.bgPosition;// no-repeat left top";
	         else if(Light.portal.data.bgRepeat == 2)
	         	document.body.style.background= "url('"+backgroundImage+"') repeat-x right top";
	         else if(Light.portal.data.bgRepeat == 3)
	         	document.body.style.background= "url('"+backgroundImage+"') repeat-y left top";
	         else
	         	document.body.style.background= "url('"+backgroundImage+"')";
		}
	}		
	var bgImage = null;
	if(Light.portal.data.newBgImage != null && Light.portal.data.newBgImage.length > 0)
		bgImage = Light.portal.data.newBgImage;
	len = document.forms['form_'+id]['ptBg'].length;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptBg'][i].checked &&
               document.forms['form_'+id]['ptBg'][i].value !="more") {
			bgImage = document.forms['form_'+id]['ptBg'][i].value;
		}
	}
	if(bgImage && bgImage != Light.portal.data.bgImage){
		Light.portal.data.bgImage = bgImage;
		if(bgImage != "no"){
			 var backgroundImage = bgImage;
	         if(bgImage.indexOf("http") < 0)
	         	backgroundImage = Light.getContextPath()+bgImage;		        
	         if(Light.portal.data.bgRepeat == 1)
	            document.body.style.background = "url('"+backgroundImage+"') no-repeat " + Light.portal.data.bgPosition;// no-repeat left top";
	         else if(Light.portal.data.bgRepeat == 2)
	         	document.body.style.background= "url('"+backgroundImage+"') repeat-x right top";
	         else if(Light.portal.data.bgRepeat == 3)
	         	document.body.style.background= "url('"+backgroundImage+"') repeat-y left top";
	         else
	         	document.body.style.background= "url('"+backgroundImage+"')";
		}else{
			document.body.style.background= "#ffffff";  
		}
	}	
	var headerRepeat = null;
	len = document.forms['form_'+id]['ptHeaderRepeat'].length;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptHeaderRepeat'][i].checked) {
			headerRepeat = document.forms['form_'+id]['ptHeaderRepeat'][i].value;
		}
	}
	if(Light.portal.data.headerRepeat != headerRepeat){
		Light.portal.data.headerRepeat = headerRepeat;
		if(Light.portal.data.headerImage != null){
			Light.portal.refreshPortalHeader();
		}
	}
	var headerImage = null;
	if(Light.portal.data.newHeaderImage != null && Light.portal.data.newHeaderImage.length > 0)
		headerImage = Light.portal.data.newHeaderImage;
	len = document.forms['form_'+id]['ptHeader'].length;
	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptHeader'][i].checked &&
		document.forms['form_'+id]['ptHeader'][i].value !="more") {
			headerImage = document.forms['form_'+id]['ptHeader'][i].value;
		}
	} 
	if(headerImage){
		Light.portal.data.headerImage = headerImage;
	}else{
		Light.portal.data.headerImage = "no";
	}
	Light.portal.refreshPortalHeader();
	if((document.forms['form_'+id]['transparent'].checked && !Light.portal.data.portletWindowTransparent) ||
     (!document.forms['form_'+id]['transparent'].checked && Light.portal.data.portletWindowTransparent)){
		Light.portal.data.portletWindowTransparent =!Light.portal.data.portletWindowTransparent;
		Light.refreshWindowTransparent();
	}	
	var params="responseId="+id;
	if(theme != null){
		params = params+"&theme="+theme;
		Light.portal.data.theme = theme;
	}
	params = params+"&bgRepeat="+bgRepeat;
	if(bgImage){
	   params = params+"&bgImage="+bgImage;  
	}
	params = params+"&headerRepeat="+headerRepeat;
	if(headerImage){
		params = params+"&headerImage="+headerImage;
	}
	if(document.forms['form_'+id]['transparent'].checked){
        params = params+"&transparent=1";   
	}
	if(params.length > 0){
    	Light.ajax.sendRequest(Light._CHANGE_THEME, {parameters:params, onSuccess:Light.saveOptionHandler}); 
	}	
   	if(finished){
    	Light.closePortlet(id,true);
	}
}

Light.showAddUserTag = function(e,id,objectType,objectId,parameterName){
	var data = {
   		id : id,
   		objectType: objectType,
   		objectId: objectId
	};
	if(parameterName){
		var portlet = Light.getPortletById(id);
		if(portlet != null)
			portlet.parameter = parameterName+"="+objectId;	
	}
	createPopupDiv('addUserTag','addUserTag.jst',400,data,e,id);
}

Light.addUserTag = function(form){
	var tag = form['tag'].value;
	var id = form['id'].value;
	var objectType = form['objectType'].value;
	var objectId = form['objectId'].value;
	var portlet = Light.getPortletById(id);
	if(portlet != null && tag.length > 0 ){
		var params="tag="+tag+"&responseId="+id+"&objectType="+objectType+"&objectId="+objectId;
		Light.ajax.sendRequest(Light._ADD_USER_TAG, {parameters:params, onSuccess:Light.addUserTagHandler}); 
	}
}

Light.addUserTagHandler = function(t){
	var id = t.responseText;
	if(id != null && id.length > 0){
		var portlet = Light.getPortletById(id);
		if(portlet != null){
			portlet.refresh(false);
		}
	}
}

Light.editTabUrl = function(id){
	var tabId = document.forms['form_'+id]['tabId'].value;
	var url = document.forms['form_'+id]['url'].value;
	var pars = "responseId="+id
		  	    +"&tabId="+tabId
		  	    +"&url="+url
		  	    +"&action=editPageUrl" 
		  	    ;
	var portlet = Light.getPortletById(id);
	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});
}

function changeTabColumns(id){
  var columns = document.forms['form_'+id]['ptColumns'].value;
  var portlet = Light.getPortletById(id);
  var pars = "responseId="+id
	  	    +"&columns="+columns;
  Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});
}
function changeCurrentTabColumns(id){
  var columns = document.forms['form_'+id]['ptColumns'].value;
  var portlet = Light.getPortletById(id);
  var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&columns="+columns;
  Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});
}

function addAutoTab(id){
	var title = Light.getMessage('LABEL_NEW_TAB');
  	var windowSkin = "WindowSkin2";
  	var columns = 3;
  	var between = 10;
  	var widths ="&width0=300&width1=300&width2=300"; 
  	var closeable = "1";
  	var defaulted = "0";
  	var params = "title="+encodeURIComponent(title)
	  			  +"&windowSkin="+windowSkin
	  			  +"&columns="+columns
	  			  +widths
	  			  +"&between="+between
	  			  +"&closeable="+closeable
	  			  +"&defaulted="+defaulted
	  			  +"&parentId="+id
  				;
  	Light.ajax.sendRequest(Light._ADD_TAB, {parameters:params, onSuccess:responseAddTab}); 
}  

function addTab(id){
  	var title = document.forms['form_'+id]['ptTitle'].value;
  	var windowSkin = document.forms['form_'+id]['ptWindow'].value;
  	var columns = parseInt(document.forms['form_'+id]['ptColumns'].value);
  	var between = document.forms['form_'+id]['ptBetween'].value;
  	var widths ="";
  	for(var i=0;i<columns;i++){
   		widths +="&width"+i+"="+document.forms['form_'+id]['ptWidth'+i].value;
  	} 
  	var closeable = "0";
  	if(document.forms['form_'+id]['ptClose'].checked)
     	closeable = "1";
  	var defaulted = "0";
  	if(document.forms['form_'+id]['ptDefault'].checked)
     	defaulted = "1";
  	var params = "title="+encodeURIComponent(title)
	  			  +"&windowSkin="+windowSkin
	  			  +"&columns="+columns
	  			  +widths
	  			  +"&between="+between
	  			  +"&closeable="+closeable
	  			  +"&defaulted="+defaulted
  				;
  	Light.ajax.sendRequest(Light._ADD_TAB, {parameters:params, onSuccess:responseAddTab}); 
  	Light.closePortlet(id); 
} 
 
function responseAddTab(t){     
	var tabs = JSON.parse(t.responseText);
	var tab = new LightPortalTab(tabs[0]);
	tab.insert();
}
  
Light.autoChangeTabWidths = function(id){
	var columns = parseInt(document.forms['form_'+id]['ptColumns'].value);
	var width = parseInt(1000 / columns);
	var widths = [];
	for(var i = 0; i<columns; i++) {
		widths.push(width);
	}
	document.forms['form_'+id]['ptWidths'].value= widths.join(',');
} 

Light.manageTab = function(id){
	var tabId = document.forms['form_'+id]['tabId'].value; 
  	var title = document.forms['form_'+id]['ptTitle'].value;
  	var windowSkin = document.forms['form_'+id]['ptWindow'].value;
   	var client = document.forms['form_'+id]['ptClient'].value;
  	var columns = parseInt(document.forms['form_'+id]['ptColumns'].value);
  	var between = document.forms['form_'+id]['ptBetween'].value;  
  	var widths = document.forms['form_'+id]['ptWidths'].value;  
  	var closeable = "0";
  	if(document.forms['form_'+id]['ptClose'].checked)
     	closeable = "1";
  	var defaulted = "0";
  	if(document.forms['form_'+id]['ptDefault'].checked)
     	defaulted = "1"; 
  	var status = 0;
  	var len =  document.forms['form_'+id]['ptStatus'].length;
  	for(var i = 0; i<len; i++) {
		if(document.forms['form_'+id]['ptStatus'][i].checked) {
			status = parseInt(document.forms['form_'+id]['ptStatus'][i].value);
		}
  	}
  	var fitScreen = 0;
  	if(document.forms['form_'+id]['ptFitScreen'].checked)
     	fitScreen = 1;   
     
  var tab = Light.getCurrentTab();
  tab.label = title;
  tab.client = client;
  tab.isCloseable = closeable;
  tab.defaulted = defaulted;
  tab.between = parseInt(between); 
  tab.widths = widths.split(',');
  tab.fitScreen = fitScreen;
  tab.privacy = (status > 2) ? 0 : 1;  
  tab.windowSkin = windowSkin;
  
  var params = "title="+encodeURIComponent(title)
              +"&tabId="+tabId
  			  +"&windowSkin="+windowSkin
  			  +"&client="+client
  			  +"&columns="+columns
  			  +"&widths="+widths
  			  +"&between="+between
  			  +"&closeable="+closeable
  			  +"&defaulted="+defaulted
  			  +"&status="+status
  			  +"&fitScreen="+fitScreen
  			  ;
  Light.ajax.sendRequest(Light._MANAGE_TAB, {parameters:params, onSuccess:manageTabHandler}); 
}   
function manageTabHandler(){   
	//Light.refreshPortal();
	//Light.getInitViewTemplates(false);
	//Light.portal.shutdown();
	//Light.portal.startup();
	Light.getCurrentTab().reRender();
}
function addContent(id,name,e){
	if(Light.hasPermission(Light.permission.PORTAL_CONTENT_ADD) && Light.getCurrentTab().allowAddContent){  	   
	  	Light.e = e;
	  	var column = 1;  
	  	var portlet = Light.getPortletById(id);
	  	var params = "portletObjectRefName="+name
	    	        +"&tabId="+Light.getCurrentTab().serverId  			 
	  				+"&column="+column  			  
					;
	  	Light.ajax.sendRequest(Light._ADD_CONTENT, {parameters:params, onSuccess:addContentHandler});
  	}else{
  		Light.alert("Sorry, you don't have permission to add content to the current page.");
  	}     
}  
function addContentHandler(t){   
	var portlets = JSON.parse(t.responseText);
  	var portlet = new PortletWindow(portlets[0],true); 
	//portlet.moveBegin();	               
	//portlet.refresh();

}   
function showAddAllFeed(e,id){      
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;                  
      var data = {
  	 	id : id,
        popupName :'addAllFeed'
      };
      createTopPopupDiv('addAllFeed','addAllFeed.jst',320,data,e,id); 
}
function showAddFeed(e,id){      
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;                  
      var data = {
  	 	id : id,
        popupName :'addFeed'
      };
      createTopPopupDiv('addFeed','addFeed.jst',320,data,e,id); 
}

function addFeed(id){
	var feed = document.forms['myFeedForm']['pcFeed'].value;
  	var portlet = Light.getPortletById(id);
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=addFeed"
	  	    +"&feed="+encodeURIComponent(feed);
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});  		         
 	hideTopPopupDiv('addFeed');
}

function showMyFeed(id){  
  	var portlet = Light.getPortletById(id); 
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=showMyFeed";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function hideMyFeed(id){  
  	var portlet = Light.getPortletById(id); 
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=hideMyFeed";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});   
}

function showAddFeaturedFeed(e,id){      
	var portlet = Light.getPortletById(id);     
	if(portlet == null) return;                  
	var data = {
   		id : id,
        popupName :'addFeaturedFeed'
	};
	createTopPopupDiv('addFeaturedFeed','addFeaturedFeed.jst',300,data,e,id); 
}

function addFeaturedFeed(id){
  	var feed = document.forms['myFeaturedFeedForm']['pcFeed'].value;
  	var portlet = Light.getPortletById(id);
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=addFeaturedFeed"
	  	    +"&feed="+feed;
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});  
  	hideTopPopupDiv('addFeaturedFeed');
}

function showFeatured(id){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=showFeatured";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});  
}

function hideFeatured(id){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=hideFeatured";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function showAddCategoryFeed(e,id,tag){      
	var portlet = Light.getPortletById(id);     
	if(portlet == null) return;                  
	var data = {
   		id : id,
   		tag : tag,
        popupName :'addCategoryFeed'
	};
	createTopPopupDiv('addCategoryFeed','addCategoryFeed.jst',300,data,e,id); 
}

function addCategoryFeed(id,tag){
  	var feed = document.forms['myCategoryFeedForm']['pcFeed'].value;
  	var portlet = Light.getPortletById(id);
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=addCategoryFeed"
	  	    +"&tag="+tag
	  	    +"&feed="+feed;
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
  	hideTopPopupDiv('addCategoryFeed');
}

function showCategory(id){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=showCategory";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function hideCategory(id){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=hideCategory";
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function showAddSubCategoryFeed(e,id,tag,subtag){      
	var portlet = Light.getPortletById(id);     
	if(portlet == null) return;                  
	var data = {
   		id : id,
   		tag : tag,
   		subtag : subtag,
        popupName :'addSubCategoryFeed'
	};
	createTopPopupDiv('addSubCategoryFeed','addSubCategoryFeed.jst',300,data,e,id); 
}

function addSubCategoryFeed(id,tag,subtag){
  	var feed = document.forms['mySubCategoryFeedForm']['pcFeed'].value;
  	var portlet = Light.getPortletById(id);
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=addSubCategoryFeed"
	  	    +"&tag="+tag
	  	    +"&subtag="+subtag
	  	    +"&feed="+feed;
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
  	hideTopPopupDiv('addSubCategoryFeed');
}

function showSubCategory(id,name){  
  	var portlet = Light.getPortletById(id);
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=showSubCategory"
	  	    +"&name="+encodeURIComponent(name);
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});   
}

function hideSubCategory(id,name){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=hideSubCategory"
	  	    +"&name="+encodeURIComponent(name);
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});   
}
function showCategoryContent(id,name){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=showCategoryContent"
	  	    +"&name="+encodeURIComponent(name);
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function hideCategoryContent(id,name){  
  	var portlet = Light.getPortletById(id);  
  	var pars = "responseId="+id
            +"&tabId="+Light.getCurrentTab().serverId	
	  	    +"&portletId="+portlet.serverId
	  	    +"&action=hideCategoryContent"
	  	    +"&name="+encodeURIComponent(name);
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function configPortlet(id){
  	var portlet = Light.getPortletById(id);
  	var title = document.forms['form_'+id]['pcTitle'].value;  
  	var windowSkin = document.forms['form_'+id]['windowSkin'].value; 
  	var windowStatus = document.forms['form_'+id]['windowStatus'].value; 
  	var autoRefreshed = document.forms['form_'+id]['auto'].value;
  	var periodTime = document.forms['form_'+id]['second'].value;
  	var showNumber = document.forms['form_'+id]['showNumber'].value;
  	var colspan = document.forms['form_'+id]['colspan'].value;
  	var marginTop = document.forms['form_'+id]['marginTop'].value;
  	var transparent = (portlet.transparent) ? 1 : 0;
  	var showIcon = (portlet.showIcon) ? 1 : 0;
  	var client = document.forms['form_'+id]['pcClient'].value;  
  	portlet.title = title;
  	portlet.autoRefreshed = (autoRefreshed == 1) ? true : false;
  	portlet.periodTime = parseInt(periodTime) * 1000;  
  	portlet.showNumber = showNumber;
  	portlet.colspan = parseInt(colspan);
  	portlet.marginTop = parseInt(marginTop);
  	portlet.client = client;
  	portlet.reRender(windowSkin); 	
  	portlet.cancelConfig();
  	var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId
            +"&portletId="+portlet.serverId
			+"&title="+encodeURIComponent(title)
			+"&barBgColor="+portlet.barBgColor
			+"&barFontColor="+portlet.barFontColor
			+"&contentBgColor="+portlet.contentBgColor
			+"&textColor="+portlet.textColor
			+"&transparent="+transparent
			+"&showIcon="+showIcon
			+"&windowSkin="+windowSkin
			+"&autoRefreshed="+autoRefreshed
			+"&periodTime="+periodTime	
			+"&showNumber="+showNumber	
			+"&colspan="+colspan
			+"&marginTop="+marginTop	
			+"&client="+client
			+"&windowStatus="+windowStatus;
  	Light.ajax.sendRequest(Light._CONFIG_PORTLET, {parameters:params}); 
}

function defaultConfigPortlet(id){
	var title = document.forms['form_'+id]['pcTitle'].value;  
  	var portlet = Light.getPortletById(id);
  	portlet.title = title;
  	portlet.barBgColor = "";
  	portlet.barFontColor = "";
  	portlet.contentBgColor = "";
  	portlet.textColor = "";
  	portlet.transparent = false; 
  	portlet.showIcon = true;
  	portlet.autoRefreshed = false;
  	portlet.colspan = 0;
  	portlet.client = 0;
  	portlet.periodTime = 0; 
  	portlet.windowSkin= "";
  	if(!portlet.reRender())	portlet.refreshWindow();
  	portlet.cancelConfig();
  	var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId
            +"&portletId="+portlet.serverId
			+"&title="+encodeURIComponent(title)		
			+"&transparent=0"
			+"&showIcon=1"
			+"&autoRefreshed=0"
			+"&colspan=0"
			+"&periodTime=0";
  	Light.ajax.sendRequest(Light._CONFIG_PORTLET, {parameters:params, onSuccess:null}); 
}
function validateConfigStore(form){
	var name = form['name'].value;
	var uri = form['uri'].value;
	var returnval;
	if (name && uri){       
		returnval = true;      
	}else{
		Light.alert("All * fields are required.");
		returnval = false;
	}
	return returnval;
}
function validateSignUp(form){
	var userId = form['email'].value;
	var password = form['password'].value;
	var cpassword = form['confirmPassword'].value;  
	var displayName = form['displayName'].value;  
	var uri = form['iUri'].value;
	var returnval;
	if (displayName && userId && password && cpassword && uri){  
		var filter = eval(Light._PASSWORD_PATTERN);
	   	if (!filter.test(password)) {
	  		Light.alert(Light.getMessage("ERROR_PASSWORD_FORMAT"));
			return false;
	   	}  
	   	if(password != cpassword){
	  	  	Light.alert(Light.getMessage('ERROR_PASSWORD_NOT_EQUAL'));
	  	  	returnval = false;
	   	}else{
	      	returnval = true;      
	   	}
	}else{
	   Light.alert("All * fields are required.");
	   returnval = false;
	}
	if(returnval)
	   Light.deleteCookie(Light._REMEMBERED_USER_ID);
	   Light.deleteCookie(Light._REMEMBERED_USER_PASSWORD);
	return returnval;
}
function validateUserId(email,id){
	if (!validateEmail(email)) {
		document.forms['form_'+id]['email'].value ="";      
		document.forms['form_'+id]['email'].focus();
		Light.alert(Light.getMessage("ERROR_EMAIL_FORMAT"));		
		return false;
	}
	var params = "email="+email
                +"&responseId="+id;
	Light.ajax.sendRequest(Light._VALIDATE_USER_ID, {parameters:params,onSuccess:validateUserIdHandler}); 
}

function validateEmail(email){
	var filter = eval(Light._EMAIL_PATTERN);///^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return filter.test(email);
}

function validateUserIdHandler(t){      
   var data = JSON.parse(t.responseText);  
   if(data.id != 1){
      document.forms['form_'+data.id]['email'].value ="";      
      Light.alert(Light.getMessage("ERROR_EMAIL_DUPLICATED"));
      document.forms['form_'+data.id]['email'].focus();
   }
}

function validateInternalUri(uri,id){
	var filter = eval(Light._URI_PATTERN);///^([a-zA-Z0-9]{3,})+$/;
	if (!filter.test(uri)) {
		document.forms['form_'+id]['iUri'].value ="";      
		document.forms['form_'+id]['iUri'].focus();
		Light.alert(Light.getMessage("ERROR_URI_FORMAT"));
		return false;
	}
   	var params = "uri="+uri
               +"&responseId="+id;
   	Light.ajax.sendRequest(Light._VALIDATE_INTERNAL_URI, {parameters:params,asynchronous:true,onSuccess:validateInternalUriHandler});    
}

function validateInternalUriHandler(t){      
	var data = JSON.parse(t.responseText);   
   	if(data.id != 1){
      	document.forms['form_'+data.id]['iUri'].value ="";      
      	Light.alert(Light.getMessage("ERROR_URI_DUPLICATED"));
      	document.forms['form_'+data.id]['iUri'].focus();
   	}
}

Light.showMoreBgImage = function(e, id){      
	var portlet = Light.getPortletById(id);     
	if(portlet == null) return;       
	var data = {
        bgImage : Light.portal.data.bgImage,
    	ok : Light.getMessage('BUTTON_OK'),    	
    	cancel : Light.getMessage('BUTTON_CANCEL'),
  	 	id : id
	};      
	createTopPopupDiv('moreBgImage','moreBgImage.jst',null,data,e,id);      
}

function saveBgImage(id){  
  	var len = document.forms['form_moreBgImage']['ptBg'].length;
  	var bgImage="";
  	for(var i = 0; i < len; i++) {
		if(document.forms['form_moreBgImage']['ptBg'][i].checked) {
			bgImage = document.forms['form_moreBgImage']['ptBg'][i].value;
		}
	}
   	Light.portal.data.newBgImage=bgImage;   
   	if(bgImage.length > 0){
    	var len = document.forms['form_'+id]['ptBg'].length;
    	for(var i = 0; i < len; i++) {
			if(document.forms['form_'+id]['ptBg'][i].value="more")
		   		document.forms['form_'+id]['ptBg'][i].checked = true;
			else
           		document.forms['form_'+id]['ptBg'][i].checked = false;  
		}
   	}
   	cancelBgImage();
}

function cancelBgImage(){
	hideTopPopupDiv('moreBgImage');  
}

Light.showMoreHeaderImage = function(e, id){      
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;       
      var data = {
        headerImage : Light.portal.data.headerImage,
    	ok : Light.getMessage('BUTTON_OK'),    	
    	cancel : Light.getMessage('BUTTON_CANCEL'),
   		id : id
      };
      createTopPopupDiv('moreHeaderImage','moreHeaderImage.jst',null,data,e,id); 

}

function saveHeaderImage(id){  
	var len = document.forms['form_moreHeaderImage']['ptHeader'].length;
	var headerImage="";
	for(var i = 0; i < len; i++) {
		if(document.forms['form_moreHeaderImage']['ptHeader'][i].checked) {
			headerImage = document.forms['form_moreHeaderImage']['ptHeader'][i].value;
		}
	}
	Light.portal.data.newHeaderImage=headerImage;   
	if(headerImage.length > 0){
    var len = document.forms['form_'+id]['ptHeader'].length;
    for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['ptHeader'][i].value="more")
			document.forms['form_'+id]['ptHeader'][i].checked = true;
		else
			document.forms['form_'+id]['ptHeader'][i].checked = false;  
		}
	}
	cancelHeaderImage();
}

function cancelHeaderImage(){
	hideTopPopupDiv('moreHeaderImage');
}

function subscribeChannels(id){
  	var len =  document.forms['form_'+id]['channels'].length;
  	var channels="";
  	for(var i = 0; i < len; i++) {
		if(document.forms['form_'+id]['channels'][i].checked) {
			channels += document.forms['form_'+id]['channels'][i].value+",";
		}
  	}
  	var portlet = Light.getPortletById(id); 
  	var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId           
			+"&portletId="+portlet.serverId
			+"&action=channels"
			+"&channels="+channels;
  	Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts: portlet.allowJS, parameters: params});    
}
//----------------------------------------------------  lightUtilityPortlet.js  
function trackRssItem(index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._TRACK_RSS_ITEM, {parameters:params});
}

function readRecommendedItem(itemId, id) {
   var portlet = Light.getPortletById(id);
   var params="itemId="+itemId
               +"&responseId="+id
               ;
   Light.ajax.sendRequest(Light._READ_RECOMMENDED_ITEM, {parameters:params});
}

function readPopItem(itemId, id) {
   var portlet = Light.getPortletById(id);
   var params="itemId="+itemId
               +"&responseId="+id
               ;
   Light.ajax.sendRequest(Light._READ_POP_ITEM, {parameters:params});
}

function readViewedItem(itemId, id) {
   var portlet = Light.getPortletById(id);
   var params="itemId="+itemId
               +"&responseId="+id
               ;
   Light.ajax.sendRequest(Light._READ_VIEWED_ITEM, {parameters:params});
}

function popRssItem( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._POP_RSS_ITEM, {parameters:params, onSuccess:responsePopItem});
 }

function popBlogItem( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._POP_BLOG_ITEM, {parameters:params, onSuccess:responsePopItem});
 }
 
function popYouTubeItem( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._POP_YOUTUBE_ITEM, {parameters:params, onSuccess:responsePopItem});
 }

function popForumItem( e, index,pageId, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&pageId="+pageId
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._POP_FORUM_ITEM, {parameters:params, onSuccess:responsePopItem});
 }
 
function popTopicItem(e, index, pageId, id) {
   	var portlet = Light.getPortletById(id);
   	if(portlet){
   		var params="index="+index
               +"&"+portlet.parameter
               +"&pageId="+pageId
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   		Light.ajax.sendRequest(Light._POP_TOPIC_ITEM, {parameters:params, onSuccess:responsePopItem});
   	}
}

function popDeliItem(e, index, id) {
	var portlet = Light.getPortletById(id);
	if(portlet){
		var params="index="+index
	               +"&"+portlet.parameter
	               +"&responseId="+id
	               +"&portletId="+portlet.serverId
	               ;
		Light.ajax.sendRequest(Light._POP_DELI_ITEM, {parameters:params, onSuccess:responsePopItem});
	}
}

function popBookmarkItem(e, index, id) {
	var portlet = Light.getPortletById(id);
	if(portlet){
		var params="index="+index
	               +"&"+portlet.parameter
	               +"&responseId="+id
	               +"&portletId="+portlet.serverId
	               ;
		Light.ajax.sendRequest(Light._POP_BOOKMARK_ITEM, {parameters:params, onSuccess:responsePopItem});
	}
}

function responsePopItem(t){
	var id = t.responseText;
    var data = {
       popupName :'popItem'
    };
    createPopupDiv('popItem','popItem.jst',280,data,null,id);
}

function forwardRssToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_RSS_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }

function forwardBlogToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_BLOG_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }
 
 function forwardYouTubeToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_YOUTUBE_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }
 
 function forwardForumToFriend( e, index, pageId, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&pageId="+pageId
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_FORUM_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }
 
 function forwardTopicToFriend( e, index, pageId, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&pageId="+pageId
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_TOPIC_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }

function forwardDeliToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_DELI_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }

function forwardBookmarkToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._FWD_BOOKMARK_FRIEND,{parameters:params, onSuccess:responseForwardToFriend});
 }

function forwardAdToFriend( e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light.forwardAdToFriend,{parameters:params, onSuccess:responseForwardToFriend});
 }

function responseForwardToFriend(t){
      var id = t.responseText;
      var data = {
       popupName :'forwardToFriend'
      };

createPopupDiv('forwardToFriend','forwardToFriend.jst',280,data,null,id);

}

function saveToHeader(e, index, id){
	var portlet = Light.getPortletById(id);
	if(portlet){
    	var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   		Light.ajax.sendRequest(Light._SAVE_TO_HEADER, {parameters:params, onSuccess:responseSaveToHeader});
   	}
}

function responseSaveToHeader(t){
	var headerImage = t.responseText;
	Light.portal.data.headerImage = headerImage;
	Light.portal.refreshPortalHeader();
}

function saveToBackground(e, index, id){
	var portlet = Light.getPortletById(id);
	if(portlet){
    	var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   		Light.ajax.sendRequest(Light._SAVE_TO_BACKGROUND,{parameters:params, onSuccess:responseSaveToBackground});
   	}
}

function responseSaveToBackground(t){
	var bgImage = t.responseText;
	Light.portal.data.bgImage = bgImage;
	var backgroundImage = bgImage;
    if(bgImage.indexOf("http") < 0)
      	backgroundImage = Light.getContextPath()+bgImage;
    if(Light.portal.data.bgRepeat == 1)
        document.body.style.background = "url('"+backgroundImage+"') no-repeat " + Light.portal.data.bgPosition;// no-repeat left top";
	else
		document.body.style.background= "url('"+backgroundImage+"')";
}

function saveToBookmark(e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_TO_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }

function saveToMyPicture(e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_TO_MY_PICTURE,{parameters:params, onSuccess:responseSaveToPicture});
 }
 
function saveBlogToBookmark(e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_BLOG_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }

function saveYouTubeToBookmark(e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_YOUTUBE_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }
 
 function saveForumToBookmark(e, index, pageId, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&pageId="+pageId
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_FORUM_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }
 
 function saveTopicToBookmark(e, index, pageId, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&pageId="+pageId
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_TOPIC_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }
 
function saveAdToBookmark(e, index, id) {
   var portlet = Light.getPortletById(id);
   if(portlet == null) return;
   var params="index="+index
               +"&"+portlet.parameter
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._SAVE_AD_BOOKMARK,{parameters:params, onSuccess:responseSaveToBookmark});
 }

function responseSaveToBookmark(t){
      var id = t.responseText;
      var data = {
       popupName :'saveToBookmark'
      };

createPopupDiv('saveToBookmark','saveToBookmark.jst',280,data,null,id);

}
function responseSaveToPicture(t){
      var id = t.responseText;
      var data = {
       popupName :'saveToPicture'
      };
	createPopupDiv('saveToPicture','saveToPicture.jst',280,data,null,id);
}

function editRssPortlet(id){
   var feed  = document.forms['form_'+id]['prFeed'].value;
   var title  = document.forms['form_'+id]['prTitle'].value;
   var icon  = document.forms['form_'+id]['prIcon'].value;
   var url  = document.forms['form_'+id]['prUrl'].value;
   var autoRefresh  = 0;
   var len =  document.forms['form_'+id]['prAuto'].length;
   for(var i = 0; i < len; i++) {
	if(document.forms['form_'+id]['prAuto'][i].checked) {	
		autoRefresh = document.forms['form_'+id]['prAuto'][i].value;		 
	}
   }
   var minute  = document.forms['form_'+id]['prMinute'].value;
   var showType  = document.forms['form_'+id]['prShowType'].value;
   var items  = document.forms['form_'+id]['prItems'].value;
   
   if(autoRefresh == '1' && minute == '0'){
      Light.alert(Light.getMessage('ERROR_MINUTE_RATE'));
      return;
   }
   var portlet = Light.getPortletById(id);
   portlet.title = title;
   portlet.icon = icon;
   portlet.url = url;
   portlet.parameter = "feed="+feed;
   portlet.mode=Light._VIEW_MODE;  
   portlet.refreshHeader();
   portlet.refreshButtons(); 
   portlet.rememberMode(0);
   portlet.lastAction = null;
   if(autoRefresh== "1")
      portlet.autoRefreshed = true;
   else
      portlet.autoRefreshed = false;
   portlet.periodTime = minute * 60000;
   portlet.autoRefresh();
   
   var pars = "responseId="+id
  	    +"&tabId="+Light.getCurrentTab().serverId
  	    +"&portletId="+portlet.serverId
        +"&action=edit"
	    +"&title="+title
	    +"&url="+url
	    +"&icon="+icon
        +"&autoRefresh="+autoRefresh
        +"&minute="+minute
        +"&showType="+showType
	    +"&items="+items
	    +"&feed="+encodeURIComponent(feed);
  
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});     
}

function keyDownSearchWeather(e,id) {  
  var KeyID;
  if (window.event) {	
	keyID = window.event.keyCode;
  } else {
        keyID = e.which;
  } 
  if ( keyID == 13){   
    searchWeatherLocation(id);    
  }
  return !(keyID == 13);
 }
 
function searchWeatherLocation(id){
   var locName  = document.forms['form_'+id]['pwLocation'].value;  
   var portlet = Light.getPortletById(id);
   var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId           
			+"&portletId="+portlet.serverId
			+"&locName="+locName;
  Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts: portlet.allowJS, parameters: params});    
}

function selectWeatherLocation(id){  
  var locId  =  document.forms['form_'+id]['pwLocId'].value;
  if(!locId){      
      var len =  document.forms['form_'+id]['pwLocId'].length;
	  for(var i = 0; i < len; i++) {
			if(document.forms['form_'+id]['pwLocId'][i].checked) {
				locId = document.forms['form_'+id]['pwLocId'][i].value;
			}
		}
   }
   var unit =  document.forms['form_'+id]['pwUnit'].value;
   var portlet = Light.getPortletById(id);
   var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId           
			+"&portletId="+portlet.serverId
			+"&action=select"
			+"&locId="+locId
			+"&unit="+unit;
  Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts: portlet.allowJS, parameters: params});    
}

function keyDownEditWeather(e,id) {  
  var KeyID;
  if (window.event) {	
	keyID = window.event.keyCode;
  } else {
        keyID = e.which;
  } 
  if ( keyID == 13){   
    editWeatherLocation(id);    
  }
  return !(keyID == 13);
 }
 
function editWeatherLocation(id){
   var locName  = document.forms['form_'+id]['pwLocation'].value;  
   var portlet = Light.getPortletById(id);
   var params="responseId="+id
			+"&tabId="+Light.getCurrentTab().serverId           
			+"&portletId="+portlet.serverId
			+"&mode=EDIT"
			+"&locName="+locName;
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts: portlet.allowJS, parameters: params});    
}

function refreshTodoPortletTitle(portlet,status){      
	if(portlet.title.indexOf("(") > 0){
		var index = portlet.title.indexOf("(");
     	var oldTitle = portlet.title;
     	portlet.title = oldTitle.substring(0,index);	
     	var count = parseInt(oldTitle.substring(index+1,oldTitle.length)); 
     	if(!status) status = 1;     	
     	if(parseInt(status) == 1)
			count++;
     	else
        	count--;
     	portlet.title = portlet.title+"("+count+")";
  	}else{
     	portlet.title = portlet.title+"(1)";
  	}
  	portlet.refreshHeader(); 
}

function increaseTodoPortletTitle(id,todoId){   
  if(todoId == 0){
	  var portlet = Light.getPortletById(id);    
	  if(portlet.title.indexOf("(") > 0){
	     var index = portlet.title.indexOf("(");
	     var oldTitle = portlet.title;
	     portlet.title = oldTitle.substring(0,index);	
	     var count = parseInt(oldTitle.substring(index+1,oldTitle.length));      	
	     count++;    
	     portlet.title = portlet.title+"("+count+")";
	  }else{
	     portlet.title = portlet.title+"(1)";
	  }
	  portlet.refreshHeader(); 
  }
}

function decreasePortletTitle(id){   
  var portlet = Light.getPortletById(id);    
  if(portlet.title.indexOf("(") > 0){
     var index = portlet.title.indexOf("(");
     var oldTitle = portlet.title;
     portlet.title = oldTitle.substring(0,index);	
     var count = parseInt(oldTitle.substring(index+1,oldTitle.length));      	
     count--;    
     if(count !=0)
        portlet.title = portlet.title+"("+count+")";
     portlet.refreshHeader(); 
  }
  
}
changeStatus  = function (id, name, status) {  
  var portlet = Light.getPortletById(id);  
  var params = "responseId="+id
	         +"&tabId="+Light.getCurrentTab().serverId
             +"&portletId="+portlet.serverId
             +"&state="+portlet.state
	         +"&action=changeStatus"
	         +"&name="+encodeURIComponent(name);
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {method:'post',evalScripts: portlet.allowJS, parameters: params});
   refreshTodoPortletTitle(portlet,status);			         
}  

function previousItem(id,number){
  //var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) - 1;
  var portlet = Light.getPortletById(id); 
  var params = "responseId="+id
	         +"&tabId="+Light.getCurrentTab().serverId
             +"&portletId="+portlet.serverId
             +"&"+portlet.parameter
	         +"&action=changeStatus"
	         +"&number="+number;
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {method:'post',evalScripts: portlet.allowJS, parameters: params});
}

function nextItem(id,number){
  //var number = document.forms['form_'+id]['pvNumber'].value;  
  number = parseInt(number) + 1;
  var portlet = Light.getPortletById(id);  
  var params = "responseId="+id
	         +"&tabId="+Light.getCurrentTab().serverId
             +"&portletId="+portlet.serverId
             +"&"+portlet.parameter
	         +"&action=changeStatus"
	         +"&number="+number;
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {method:'post',evalScripts: portlet.allowJS, parameters: params});
  
}

function editViewerPortlet(id){
   var feed  = document.forms['form_'+id]['prFeed'].value;
   var title  = document.forms['form_'+id]['prTitle'].value;
   var icon  = document.forms['form_'+id]['prIcon'].value;
   var url  = document.forms['form_'+id]['prUrl'].value;
   var portlet = Light.getPortletById(id);
   portlet.title = title;
   portlet.icon = icon;
   portlet.url = url;
   portlet.parameter = "feed="+feed;  
   portlet.refreshHeader();
   var params = "responseId="+id
	         +"&tabId="+Light.getCurrentTab().serverId
             +"&portletId="+portlet.serverId
             +"&action=edit"
	         +"&mode=EDIT"
	         +"&title="+title
	         +"&url="+url
	         +"&icon="+icon
	         +"&feed="+feed;
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {method:'post',evalScripts: portlet.allowJS, parameters: params});   
}

function editMyUrl(e,id) {   	 
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;       
      var data = {
   		id : id
  	  };
  	  createPopupDiv('editMyUrl','editMyUrl.jst',280,data,e,id);       
  }
  
function hideEditMyUrl () {        
      var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('editMyUrl');
      if(old != null) vdocument.removeChild(old);
  }
  function keyDownSaveMyUrl (e, id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){   
	    saveMyUrl(id);    
	  }
	  return !(keyID == 13);
  }
  function saveMyUrl(id) { 
	  var uri = document.forms['editMyUrlForm']['uri'].value;
	  if(uri == null || uri.length ==0){
	  	Light.alert("please input your desired URI.");
	  	return;
	  }
	  var params="uri="+uri+"&id="+id;
	  Light.ajax.sendRequest(Light._CHECK_MY_URL, {parameters:params, onSuccess:checkMyUrlHandler}); 
  }
 
  function checkMyUrlHandler(t) {
    var data = JSON.parse(t.responseText);   
    if(data.id == "-1"){
      Light.alert("this uri is not available, please input another desired URI.");
  	  return;
    }
    if(data.id == "0"){
      Light.alert("this uri is not allowed, please input another desired URI.");
  	  return;
    }
    var warnyou = Light.confirm("Do you really want to change URL, you only can change one time!");
    if (!warnyou) // user cancelled close closePortlet
	{
		return;
	}
    var uri = document.forms['editMyUrlForm']['uri'].value;
    var params="uri="+uri+"&id="+data.id;
    Light.ajax.sendRequest(Light._SAVE_MY_URL, {parameters:params, onSuccess:saveMyUrlHandler});
  }
  
function saveMyUrlHandler(t) {
	  var data = JSON.parse(t.responseText); 
	  var portlet = Light.getPortletById(data.id);
	  portlet.refresh();
	  hideEditMyUrl();
}
    
function editProfilePhoto(e,id) {   	  
      var portlet = Light.getPortletById(id);     
      if(portlet != null){    
	      var data = {
	   		id : id
	  	  };
	      createPopupDiv('editProfilePhoto','editProfilePhoto.jst',300,data,e,id);
	  }
}
  
hideEditProfilePhoto  = function () { 
	hidePopupDiv('editProfilePhoto');       
}
hideCropProfilePhoto  = function () {        
	hidePopupDiv('cropProfilePhoto');      
}

cropProfilePhoto = function (id,photoUrl) {  
	var data = {
   		id : id,
   		value : photoUrl
	};
	createPopupDiv('cropProfilePhoto','cropProfilePhoto.jst',800,data,null,id);
	Light.cropId = id;
	//setTimeout((function() {
        jQuery('#cropbox_'+id).Jcrop({
            onSelect: trackCropPhoto,
            onChange: trackCropPhoto,
            aspectRatio: 1	            
        });
	//}),500);
}
function trackCropPhoto(c){
	Light.crop = {
		x : c.x,
		y : c.y,
		w : c.w,
		h : c.h
	};	
	var rx = 100 / c.w;
	var ry = 100 / c.h;
	var img_width = jQuery('#cropbox_'+Light.cropId).width();
	var img_height = jQuery('#cropbox_'+Light.cropId).height();
	jQuery('#preview_'+Light.cropId).css({
		width: Math.round(rx * img_width) + 'px',
		height: Math.round(ry * img_height) + 'px',
		marginLeft: '-' + Math.round(rx * c.x) + 'px',
		marginTop: '-' + Math.round(ry * c.y) + 'px'
	});
			    
}

submitCropProfilePhoto = function(id){
	if(!Light.crop){   
		Light.alert("please select the area of the photo for your profile");
   		return false;
   	}
   	var params = [];
   	params.push("responseId="+id);
   	params.push("x="+Light.crop.x);
   	params.push("y="+Light.crop.y);
   	params.push("w="+Light.crop.w);
   	params.push("h="+Light.crop.h);
    Light.ajax.sendRequest(Light._CROP_PROFILE_PHOTO, {parameters:params.join("&"), onSuccess:submitCropProfilePhotoHandler});
   	
}
submitCropProfilePhotoHandler= function(t){
	var data = JSON.parse(t.responseText); 
	var portlet = Light.getPortletById(data.id);
	if(portlet)	portlet.refresh();
	hideCropProfilePhoto();	
	Light.crop = null;
	Light.cropId = null;
	window.scrollTo(0,0);	
}
setColor = function (id,which,color) {
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;                  
      if(which == 1){
	  	  portlet.barBgColor = color;      
	      portlet.refreshHeader();
	  }
	  if(which == 2){
	  	  portlet.barFontColor = color;     
	      portlet.refreshHeader();
	  }
	  if(which == 3){
	  	  portlet.contentBgColor = color;     
	      var pcContentBgColor = document.forms['form_'+id];
		  pcContentBgColor.style.backgroundColor = color;
	  }
	  if(which == 4){
	      portlet.textColor = color;   
	  	  portlet.refreshTextColor();
	  }      
}

switchPortletTransparence = function(id,value){
  var portlet = Light.getPortletById(id);
  if(portlet == null) return;  
  if(value.checked) 
     portlet.transparent = true;
  else
     portlet.transparent = false;
  portlet.refreshWindowTransparent();
}

switchPortletIcon = function(id,value){
  var portlet = Light.getPortletById(id);
  if(portlet == null) return;  
  if(value.checked) 
     portlet.showIcon = true;
  else
     portlet.showIcon = false;
  portlet.refreshHeader();
}

showMyPicture = function(){  
 	var portlet = new PortletPopupWindow(11,280,400,Light.getMessage('MENU_MY_PICTURE'),"icons picture","","myPicturePortlet","/myPicturePortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,true,50,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}

showMyMusic = function(){
 	var portlet = new PortletPopupWindow(11,280,600,Light.getMessage('MENU_MY_MUSIC'),"icons music","","myMusicPortlet","/myMusicPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}
openMyMusicPlayer = function(){
	var portlet = Light.getPopupPortletByName("myMusicPortlet");
    if(portlet) portlet.close();
 	portlet = new PortletPopupWindow(11,280,320,Light.getMessage('MENU_MY_MUSIC_PLAYER'),"icons music","","myMusicPlayerPortlet","/myMusicPlayerPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','','',false,false,true,4,2);
    portlet.refresh();
}
closeMyMusicPlayer = function(){
 	var portlet = Light.getPopupPortletByName("myMusicPlayerPortlet");
    if(portlet) portlet.close(true);
}
showMyFile = function(){
	if(!Light.isLogin()){
    	Light.portal.latestAction.event =null;
    	Light.portal.latestAction.id = null;
    	Light.portal.latestAction.method="showMyFile";
    	Light.showAllLogin(false);
    	return;
	}
 	var portlet = new PortletPopupWindow(11,280,600,Light.getMessage('MENU_MY_FILE'),"icons file","","myFilePortlet","/myFilePortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}
showMyFavorites = function(){  
 	var portlet = new PortletPopupWindow(11,280,400,Light.getMessage('MENU_MY_FAVOURITE'),"icons user","","favouritePortlet","/favouritePortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}

showMyBlog = function(){   
 	var portlet = new PortletPopupWindow(11,200,500,Light.getMessage('MENU_MY_BLOG'),"icons blog","","blogPortlet","/blogPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}

showMyViewed= function(){   
 	var portlet = new PortletPopupWindow(11,200,500,Light.getMessage('MENU_MY_VIEWED'),"icons viewed","","myViewedItemPortlet","/myViewedItemPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}

showMyRecommended= function(){   
 	var portlet = new PortletPopupWindow(11,200,500,Light.getMessage('MENU_MY_RECOMMENDED'),"icons recommended","","recommendedItemPortlet","/recommendedItemPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
    window.scrollTo(0,0);
}

Light.showSummary = function(e, id, obj, index, cacheId) { 
	  if(typeof cacheId === "undefined") cacheId = obj+""+index;    	    
      var currentTabId = Light.getCurrentTabId();
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;      
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('summaryId');
      if(old != null) document.body.removeChild(old);
      var vPopup = document.createElement('div');
      vPopup.id="summaryId";
      vPopup.name= cacheId;
      vPopup.style.position = "absolute";    
      vPopup.onmouseout  = function(){ Light.hideSummary();}  
      var x = getMousePositionX(e) + 10;
      var y = getMousePositionY(e) + 10;
      setPosition(vPopup,x,y);
      vPopup.style.zIndex= Light.maxZIndex+1000; 
      if(Light.cacheStorage[cacheId]){
        var width = 320;
        if(parseInt(x)+width > document.body.clientWidth)
          x = parseInt(x) - width - 40;
        vPopup.style.width=width;
        vPopup.className = "portlet-popup";      
        vPopup.innerHTML = Light.cacheStorage[cacheId];                     
        if(y <=100) y= 100;
        setPosition(vPopup,x,y);
      }
      document.body.appendChild(vPopup); 
      vPopup = null;
      if(!Light.cacheStorage[cacheId]){
	      var params="index="+index
	      			+"&obj="+obj
	                +"&"+portlet.parameter;
	      Light.ajax.sendRequest(Light._GET_OBJECT_DESC, {parameters:params, onSuccess:Light.showSummaryHandler}); 
	  }
  } 
  
Light.hideSummary = function () {        
      var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('summaryId');
      if(old != null) document.body.removeChild(old);
  }
 
Light.showSummaryHandler= function(t){
      var desc = t.responseText;             
      var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('summaryId');
      var x= 100;
      var y= 100;
      if(old != null){
      	cacheId = old.name;
        if(!Light.cacheStorage[cacheId]){
           Light.cacheStorage[cacheId] =desc;
        }
        x = getPositionX(old);
        y = getPositionY(old);
        var width = 320;
        if(x+width > document.body.clientWidth)
          x = x - width - 40;
        document.body.removeChild(old);      
        var vPopup = document.createElement('div');
        vPopup.id="summaryId";
        vPopup.style.position = "absolute";
        vPopup.style.width=width;
        vPopup.className = "portlet-popup";
        vPopup.onmouseout  = function(){ Light.hideSummary();}       
        vPopup.innerHTML = desc;      
        setPosition(vPopup,x,y);
        vPopup.style.zIndex= Light.maxZIndex+1000; 
        document.body.appendChild(vPopup); 
        vPopup = null;
     }
}

function showObjectComments(e,id,objectId,objectType){
      if(Light.portal == null) return;           
      var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('objectComments');
      if(old != null) document.body.removeChild(old);
      var vPopup = document.createElement('div');
      vPopup.id="objectComments";
      vPopup.style.position = "absolute";      
      var x= 120;
      var y= 120;      
      if (window.event) {	    	  
     	   y = event.clientY + document.body.scrollTop;
      }else {
           y = e.pageY;
      }   
      setPosition(vPopup,x,y);
      vPopup.style.zIndex= Light.maxZIndex+1000; 
      document.body.appendChild(vPopup); 
      vPopup = null;
      var params="responseId="+id
                 +"&objectId="+objectId
                 +"&objectType="+objectType;
      Light.ajax.sendRequest(Light._GET_OBJECT_COMMENTS, {parameters:params, onSuccess:showObjectCommentsHandler}); 
} 
  
hideObjectComments = function () {        
	var currentTabId = Light.getCurrentTabId();
    var vdocument = document.getElementById('panel_'+currentTabId);
    var old = document.getElementById('objectComments');
    if(old != null) document.body.removeChild(old);
}
 
function showObjectCommentsHandler(t){
      var desc = t.responseText;             
      var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);
      var old = document.getElementById('objectComments');
      var x= 120;
      var y= 120;
      if(old != null){
        x = getPositionX(old);
        y = getPositionY(old);
        document.body.removeChild(old);      
        var vPopup = document.createElement('div');
        vPopup.id="objectComments";
        vPopup.style.position = "absolute";
        vPopup.style.width=800;
        vPopup.className = "portlet-popup3";      
        vPopup.innerHTML = desc;      
        setPosition(vPopup,x,y);     
        vPopup.style.zIndex= Light.maxZIndex+1000;
        document.body.appendChild(vPopup); 
        vPopup = null;
     }
}

function popBlog(blogId,portletId){
  var params="&blogId="+blogId
            +"&responseId="+portletId;
      Light.ajax.sendRequest(Light._POP_BLOG, {parameters:params, onSuccess:responsePopBlog});
}

function responsePopBlog(t){
      Light.alert("This Blog has been Poped.");        
}

function addObjectComment(e,id,objectId,objectType,parentId,parameterName){
	if(!parentId) parentId = 0;
	var data = {
      	id : id,
      	objectId : objectId,
      	objectType : objectType,
      	parentId : parentId,
        popupName : 'objectComment'
	};
	if(parameterName){
		var portlet = Light.getPortletById(id);
		if(portlet != null)
			portlet.parameter = parameterName+"="+objectId;	
	}		
	createPopupDiv('objectComment','objectComment.jst',400,data,e,id); 
}

saveObjectComment = function (id) {  
	var comment  = document.forms['objectCommentForm_'+id]['comment'].value;     
	if(!comment){
		Light.alert("Please input comment first.");
     	return;
 	}
	var objectId  = document.forms['objectCommentForm_'+id]['objectId'].value;
	var objectType  = document.forms['objectCommentForm_'+id]['objectType'].value;
	var parentId  = document.forms['objectCommentForm_'+id]['parentId'].value;
	var portlet = Light.getPortletById(id);
	if(portlet != null){
		portlet.parameter = "itemId="+objectId;	
		var params="&comment="+escape(encodeURIComponent(comment))
	                +"&objectId="+objectId   
	                +"&objectType="+objectType                
	                +"&parentId="+parentId               
	                +"&responseId="+id; 	
		Light.ajax.sendRequest(Light._SAVE_OBJECT_COMMENT, {parameters:params, onSuccess:saveObjectCommentHandler});  
	}
} 
  
function saveObjectCommentHandler(t){
	var data = JSON.parse(t.responseText); 
	var portlet = Light.getPortletById(data.id);
	if(portlet){
		if(portlet.lastAction)
			portlet.lastAction="&parameter="+data.objectId;
		else{
			if(portlet.parameter)
				portlet.parameter="&parameter="+data.objectId;
			else
				portlet.parameter=data.objectId;
		}
		portlet.refresh(false);
	}
	hidePopupDiv('objectComment');  
	if(data.status == 0)
		Light.alert(L$('MESSAGE_COMMENT_NEED_APPROVE').title);     
}

function popAd(adId,portletId){
	var params="&adId="+adId
            +"&responseId="+portletId;
	Light.ajax.sendRequest(Light._POP_AD, {parameters:params, onSuccess:popAdHandler});
}

function popAdHandler(t){
	Light.alert("This Ad has been Poped.");        
}

function validateGroup(form){
	var displayName = form['displayName'].value;
	var uri = form['iUri'].value;
	var shortDesc = form['shortDesc'].value;
	var returnval;
	if(displayName && uri && shortDesc)    
	   returnval = true;
	else{
	   Light.alert("All * fields are required.");
	   returnval = false;
   	}	
	return returnval;
}

function joinToGroup(e,groupId,responseId){
   if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = responseId;
     Light.portal.latestAction.groupId = groupId;
     Light.portal.latestAction.method="joinToGroup";
     Light.showAllLogin(false);
     return;
  }
  if(groupId == null){
  	 if(Light.portal.latestAction.groupId != null)
  	 	groupId = Light.portal.latestAction.groupId;
  	 else
  	 	groupId = 0;
  }        
   var params = "groupId="+groupId+"&responseId="+responseId;
   Light.ajax.sendRequest(Light._JOIN_TO_GROUP, {parameters:params,onSuccess:joinToGroupHandler}); 
}

function joinToGroupHandler(t){      
      var params = t.responseText.split(";");    
      if(params[0] == '1'){                 
       var data = {
        popupName :'joinToGroup'
       };
        createPopupDiv('joinToGroup','joinToGroup1.jst',280,data,null,params[1]); 
      }
      if(params[0] == '2'){                 
       var data = {
        popupName :'joinToGroup'
       };
        createPopupDiv('joinToGroup','joinToGroup2.jst',280,data,null,params[1]); 
      }
      if(params[0] == '0'){                 
       var data = {
        popupName :'joinToGroup'
       };
        createPopupDiv('joinToGroup','joinToGroup0.jst',280,data,null,params[1]); 
      }
     
}

function selectInvitedFriends(id){           
   var listField = document.forms['form_'+id]['from'];
   if ( listField.length == -1) {  // If the list is empty
      Light.alert("There are no friends which can be removed from invited list!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         Light.alert("You must select an friend to be removed!");
      } else {  // Build arrays with the text and values to remain
         newValue = listField.options[selected].value;
         newText = listField.options[selected].text;
         var replaceTextArray = new Array(listField.length-1);
         var replaceValueArray = new Array(listField.length-1);
         for (var i = 0; i < listField.length; i++) {
            // Put everything except the selected one into the array
            if ( i < selected) { replaceTextArray[i] = listField.options[i].text; }
            if ( i > selected ) { replaceTextArray[i-1] = listField.options[i].text; }
            if ( i < selected) { replaceValueArray[i] = listField.options[i].value; }
            if ( i > selected ) { replaceValueArray[i-1] = listField.options[i].value; }
         }
         listField.length = replaceTextArray.length;  // Shorten the input list
         for (i = 0; i < replaceTextArray.length; i++) { // Put the array back into the list
            listField.options[i].value = replaceValueArray[i];
            listField.options[i].text = replaceTextArray[i];
         }
         var len = document.forms['form_'+id]['to'].length++; 
         document.forms['form_'+id]['to'].options[len].value = newValue;
         document.forms['form_'+id]['to'].options[len].text = newText;
         //document.forms['form_'+id]['to'].selectedIndex = len; 
      } // Ends the check to make sure something was selected
   } // Ends the check for there being none in the list
}

function unselectInvitedFriends(id) {   
   var listField = document.forms['form_'+id]['to'];
   if ( listField.length == -1) {  // If the list is empty
      Light.alert("There are no friends which can be removed from invited list!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         Light.alert("You must select an friend to be removed!");
      } else {  // Build arrays with the text and values to remain
         newValue = listField.options[selected].value;
         newText = listField.options[selected].text;
         var replaceTextArray = new Array(listField.length-1);
         var replaceValueArray = new Array(listField.length-1);
         for (var i = 0; i < listField.length; i++) {
            // Put everything except the selected one into the array
            if ( i < selected) { replaceTextArray[i] = listField.options[i].text; }
            if ( i > selected ) { replaceTextArray[i-1] = listField.options[i].text; }
            if ( i < selected) { replaceValueArray[i] = listField.options[i].value; }
            if ( i > selected ) { replaceValueArray[i-1] = listField.options[i].value; }
         }
         listField.length = replaceTextArray.length;  // Shorten the input list
         for (i = 0; i < replaceTextArray.length; i++) { // Put the array back into the list
            listField.options[i].value = replaceValueArray[i];
            listField.options[i].text = replaceTextArray[i];
         }
         var len = document.forms['form_'+id]['from'].length++; 
         document.forms['form_'+id]['from'].options[len].value = newValue;
         document.forms['form_'+id]['from'].options[len].text = newText;
         //document.forms['form_'+id]['from'].selectedIndex = len; 
      } // Ends the check to make sure something was selected
   } // Ends the check for there being none in the list
}

function inviteToGroup(e,id){           
   var params = "groupId="+document.forms['form_'+id]['groupId'].value+"&responseId="+id;
   var len = document.forms['form_'+id]['to'].length;
   if(len <= 0){
      Light.alert("You have to choose your friends first.");
      return;
   }
   var friends="";
   for(var i = 0; i < len; i++) {
      friends+=document.forms['form_'+id]['to'][i].value+";"
   }
   params+="&friends="+friends;
   Light.ajax.sendRequest(Light._INVITE_TO_GROUP, {parameters:params,onSuccess:responseInviteToGroup}); 
}
function responseInviteToGroup(t){      
      var params = t.responseText.split(";");                         
       var data = {
        friendsName : params[0],
        popupName :'inviteToGroup'
       };
       createPopupDiv('inviteToGroup','inviteToGroup.jst',280,data,null,params[1]); 
       var portlet = Light.getPortletById(params[1]); 
      if(portlet != null) portlet.close(true);           
}
function viewGroupPictures(e,groupId,id){
    var portlet = new PortletPopupWindow(11,300,400,Light.getMessage('MENU_GROUP_PICTURES'),"icons picture","","groupPicturePortlet","/groupPicturePortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,true,10000,false,'','','',"groupId="+groupId);    
    portlet.refresh();
}
function viewGroupMembers(e,groupId,id){
  var portlet = new PortletPopupWindow(11,300,400,Light.getMessage('MENU_GROUP_MEMBERS'),"icons user","","groupMembersPortlet","/groupMembersPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"groupId="+groupId);    
  portlet.refresh();
}
function viewGroupBulletins(e,groupId,id){
  var portlet = new PortletPopupWindow(11,300,400,Light.getMessage('MENU_GROUP_BULLETIN'),"icons bulletin","","groupBulletinPortlet","/groupBulletinPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"groupId="+groupId);    
   portlet.refresh();
}
function inviteOthers(e,groupId,id){
  var portlet = new PortletPopupWindow(11,300,400,Light.getMessage('MENU_GROUP_INVITE'),"icons user","","groupInvitePortlet","/groupInvitePortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"groupId="+groupId);    
  portlet.refresh();
}
function resign(e,groupId,id){                      
       var data = {
        popupName :'resign',
        id : id,
        groupId : groupId
       };
        createPopupDiv('resign','resign.jst',280,data,e,id); 
}
function resignGroup(groupId,responseId){ 
  var params = "groupId="+groupId+"&responseId="+responseId;
  Light.ajax.sendRequest(Light._RESIGN_GROUP, {parameters:params,onSuccess:responseResignGroup}); 
}
function editGroupProfile(e,groupId,id){
  var portlet = new PortletPopupWindow(11,300,500,Light.getMessage('MENU_GROUP_EDIT'),"icons group","","groupEditPortlet","/groupEditPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"groupId="+groupId);    
  portlet.refresh();
}
function deleteGroupProfile(e,groupId,name,id){                      
       var data = {
        popupName :'deleteGroupProfile',
        id : id,
        groupId : groupId,
        groupName : name
       };
        createPopupDiv('deleteGroupProfile','deleteGroupProfile.jst',280,data,e,id); 
}
function confirmDeleteGroupProfile(groupId,responseId){ 
  var params = "&groupId="+groupId+"&responseId"+responseId;
  Light.ajax.sendRequest(Light._DELETE_GROUP_PROFILE, {parameters:params,onSuccess:responseDeleteGroupProfile}); 
}
function showInstantMessageMember(e,userId,responseId){
  var params = "&userId="+userId+"&responseId"+responseId;
  Light.ajax.sendRequest(Light._CHAT_WITH_MEMBER, {parameters:params, onSuccess:responseChatWithMember}); 
}
responseChatWithMember = function(t){
    var chatParams = t.responseText.split(",");
    if(chatParams[1] == 0){
	var data = {    
         userName : chatParams[0],	 
         popupName :'isBlockUser'
        };
        createPopupDiv('isBlockUser','isBlockUser.jst',360,data,null,null); 
        return;
    }	
    if(chatParams[1] == "n"){
	var data = {  
         userName : chatParams[0],	 
         popupName :'noIM'
        };
        createPopupDiv('noIM','noIM.jst',360,data,null,null); 
        return;
    }
    if(chatParams[1] == "f"){
	var data = {  
         userName : chatParams[0],	 
         popupName :'friendOnlyIM'
        };
        createPopupDiv('friendOnlyIM','friendOnlyIM.jst',360,data,null,null); 
        return;
    }	
    var currentTab = Light.getCurrentTab();
    var	portlet = new PortletChatWindow(12,200,300,Light.getMessage('TITLE_CHAT')+chatParams[0],"","","chattingPortlet","/chattingPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,false,5000,true,'','','',"chattingId="+chatParams[1]); 
    var id = portlet.id;
    var params="responseId="+id
			+"&tabId="+currentTab.serverId           
			+"&portletId="+portlet.serverId
			+"&"+portlet.parameter; 
    Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,{evalScripts: portlet.allowJS, parameters: params});      
   
    document.forms['form_'+portlet.id]['chat'].focus();
  }

showSendMessageMember= function(id,buddyId,buddyName,e){  
	var data = {
	     id:id,
	     popupName :'sendMessage',
	     buddyId : buddyId,
	     buddyName : buddyName
	};
	createPopupDiv('sendMessage','sendMessage.jst',400,data,e,id); 
}
viewPicture= function(id,pictureUrl,caption,pictureId,width,height){
   picSpan= document.getElementById("picture_"+id);
   var data = {
     id : id,
     url : Light.getContextPath()+pictureUrl,
     caption : caption,
     width: width,
     height: height
   };
   var portlet = Light.getPortletById(id);
   portlet.parameter="pictureId="+pictureId;
   picSpan.innerHTML = TrimPath.processDOMTemplate("viewPicture.jst",data);     
      
} 

function createPopupPic(name,templateName,width,data,e,id) {
      var old = document.getElementById(name);
      if(old != null) hidePopupDiv(name);   
	  var currentTabId = Light.getCurrentTabId();   
      var vdocument = document.getElementById('panel_'+currentTabId);            
      var vPopupDiv = document.createElement('div');
      vPopupDiv.id = name;
      vPopupDiv.style.position = "absolute";      
      vPopupDiv.className = "portlet-popup4";  
      if(width != null)      
	  vPopupDiv.style.width= width; 
	  var portlet = Light.getPortletById(id);     
	  if(portlet.myPictures[data.url] != null){
	     portlet.myPictures[data.url].width=data.width;
	     portlet.myPictures[data.url].height=data.height;
	     portlet.myPictures[data.url].style.cursor= "url('light/images/zoomout.cur'), pointer";
	     vPopupDiv.appendChild(portlet.myPictures[data.url]);	   
	     vPopupDiv.innerHTML += "<br/>"+data.caption;
	   }else
         vPopupDiv.innerHTML = TrimPath.processDOMTemplate(templateName, data);    
      
      var x = 200;
      var y = 200;
      if (window.event) {	
    	   x = event.clientX + document.body.scrollLeft + 10;
     	   y = event.clientY + document.body.scrollTop - 300;
      }else if(e != null){
           x = e.pageX + 10;
           y = e.pageY - 300;
      }else if(id != null){
          portlet = Light.getPortletById(id); 
          x = portlet.left+100;
          y = portlet.top+50;
      }   
      if(parseInt(x)+width > document.body.clientWidth)
          x = parseInt(x) - width - 150;
      if(x < 0) x = 200;
      setPosition(vPopupDiv,x,y);
      var portlet = null;     
      var zIndex = Light.maxZIndex+1000;      
      vPopupDiv.style.zIndex= zIndex; 
      vPopupDiv.onclick = function(e){ hidePopupDiv(name);}
      vdocument.appendChild(vPopupDiv);
      vPopupDiv = null; 
  }

viewMaxPictureAtClient= function(e,id,pictureId,pictureUrl,caption,width,height,tagging){
   var tags = new Object();
   if(tagging){
	   var stags = tagging.split("-");
	   for(var i=0;i<stags.length;i++){
	   	  	 var tag = stags[i].split("+");
	   	  	 tags[i] = new Object();
	   	  	 tags[i].id = tag[0];
	   	  	 tags[i].positionX = tag[1];
	   	  	 tags[i].positionY = tag[2];
	   }
	}
   var data = {
      	id : id, 
      	pictureId : pictureId,       
        url : pictureUrl,
        caption : caption,
        width: width,
        height: height,
        tags : (tagging) ? tags : '',
        popupName :'viewMaxPicture'
   };
   createPopupPic('viewMaxPicture','viewMaxPicture.jst',width,data,e,id);
}
 
viewMaxPictureAtClientById= function(e,id){
   var portlet = Light.getPortletById(id);
   var pic = document.getElementById("userPicture_"+portlet.myPictureIndex);	
   var params = pic.name.split(";");  
   var tagging = params[6]  
   var tags = new Object();
   var stags = tagging.split("-");
   for(var i=0;i<stags.length;i++){
   	  	 var tag = stags[i].split("+");
   	  	 tags[i] = new Object();
   	  	 tags[i].id = tag[0];
   	  	 tags[i].positionX = tag[1];
   	  	 tags[i].positionY = tag[2];
   }
   var data = {
      	id : id,        
        url : params[5],
        caption : pic.value,
        width: params[3],
        height: params[4],
        tags : tags,
        popupName :'viewMaxPicture'
   };
   createPopupPic('viewMaxPicture','viewMaxPicture.jst',params[3],data,e,id);
} 

viewGroupPicture= function(id,groupId,pictureUrl,caption,pictureId,width,height){
   picSpan= document.getElementById("picture_"+id);
   var data = {
     id : id,
     url : Light.getContextPath()+pictureUrl,
     caption : caption,
     width: width,
     height: height
   };
   var portlet = Light.getPortletById(id);
   portlet.parameter="pictureId="+pictureId+"&groupId="+groupId;
   picSpan.innerHTML = TrimPath.processDOMTemplate("viewPicture.jst",data);     
      
} 
/*
viewMaxGroupPicture= function(id,groupId,pictureId){
   var portlet = Light.getPortletById(id);
   portlet.parameter="pictureId="+pictureId+"&groupId="+groupId;
   portlet.mode = Light._VIEW_MODE;
   portlet.state = Light._MAXIMIZED_STATE;
   portlet.lastAction == null;
   Light.executePortlet(id);
}
*/
viewMaxGroupPictureAtClient= function(e,id,pictureId,pictureUrl,caption,width,height,tagging){
   var tags = new Object();
   var stags = tagging.split("-");
   for(var i=0;i<stags.length;i++){
   	   var tag = stags[i].split("+");
   	   tags[i] = new Object();
   	   tags[i].id = tag[0];
   	   tags[i].positionX = tag[1];
   	   tags[i].positionY = tag[2];
   }
   var data = {
      	id : id,
      	pictureId : pictureId,        
        url : Light.getContextPath()+pictureUrl,
        caption : caption,
        width: width,
        height: height,
        tags : tags,
        mode : 0,
        popupName :'viewMaxPicture'
   };
   createPopupPic('viewMaxPicture','viewMaxPicture.jst',width,data,e,id);
}

saveNote= function(id){
  var content = document.forms['form_'+id]['content'].value;
  var params="content="+encodeURIComponent(content);
  Light.ajax.sendRequest(Light._SAVE_NOTE,{parameters:params, onSuccess:null});
}

changeNoteRow= function(e,id){
  var note = document.forms['form_'+id]['content'];
  var content = note.value;
  var row = parseInt(content.length / note.cols);
  for(var i=0;i<content.length;i++){
     if(content.charAt(i) == '\n') row++;
  }
  note.rows=row + 2;
  var portlet = Light.getPortletById(id);
  Light.getCurrentTab().repositionPortlets(portlet);

}

function configNote(id){
 var title = document.forms['form_'+id]['pcTitle'].value;
 var barBgColor = document.forms['form_'+id]['pcBarBgColor'].value;
 var barFontColor = document.forms['form_'+id]['pcBarFontColor'].value;
 var contentBgColor = document.forms['form_'+id]['pcContentBgColor'].value;
 var textColor = document.forms['form_'+id]['pcTextColor'].value;
 var portlet = Light.getPortletById(id);
 portlet.title = title;
 portlet.barBgColor = barBgColor;
 portlet.barFontColor = barFontColor;
 portlet.contentBgColor = contentBgColor;
 portlet.refreshWindow();
 portlet.mode = Light._VIEW_MODE; 
 portlet.refreshButtons();
 var pars = "responseId="+id
  	    +"&tabId="+Light.getCurrentTab().serverId
  	    +"&portletId="+portlet.serverId
        +"&action=config"
	    +"&title="+encodeURIComponent(title)
	    +"&barBgColor="+barBgColor
	    +"&barFontColor="+barFontColor
        +"&contentBgColor="+contentBgColor
        +"&textColor="+textColor;
  
 Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function resetNote(id){
 var title = document.forms['form_'+id]['pcTitle'].value;
 var portlet = Light.getPortletById(id);
 portlet.title = title;
 portlet.barBgColor = "";
 portlet.barFontColor = "";
 portlet.contentBgColor = "";
 portlet.refreshWindow();
 portlet.mode = Light._VIEW_MODE; 
 portlet.refreshButtons();
 var pars = "responseId="+id
  	    +"&tabId="+Light.getCurrentTab().serverId
  	    +"&portletId="+portlet.serverId
        +"&action=reset"
	    +"&title="+encodeURIComponent(title); 
 Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars}); 
}

function changeEmailConfig(form){
  if(form['incomingType'].value == "pop3"){
    if(form['incomingSSL'].checked)
       form['incomingPort'].value = 995;
    else
       form['incomingPort'].value = 110;
  }else{
    if(form['incomingSSL'].checked)
       form['incomingPort'].value = 993;
    else
       form['incomingPort'].value = 143;
  }
  if(form['outgoingSSL'].checked)
    form['outgoingPort'].value = 465;
  else
   form['outgoingPort'].value = 25;
}
function popupToDo(id){
   var portlet = new PortletPopupWindow(11,150,400,Light.getMessage('MENU_TODO'),"icons todolist","","todoListPortlet","/todoListPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
   portlet.refresh();
}
function popupCalendar(id){
 	var portlet = new PortletPopupWindow(11,150,600,Light.getMessage('MENU_CALENDAR'),"icons calendar","","calendarPortlet","/calendarPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
    portlet.refresh();
}
function validateCalendarEvent(form){
	if(form.name.value == null || form.name.value.length <=0){
	 	Light.alert(" Please input event name first."); 
	 	return false;
	}else
	 	return true;
}
function validateForumReply(form){
	if(form.newTopic.value == 1 && (form.topic.value == null || form.topic.value.length <=0) ){
	 	Light.alert(" Please input topic first."); 
	 	return false;
	}
	if(form.content.value == null || form.content.value.length <=0){
	 	Light.alert(" Please input content first."); 
	 	return false;
	}
	return true;
}
function validateMessage(form){
	if(form.subject.value == null || form.subject.value.length <=0){
	 	Light.alert(" Please input message subject first."); 
	 	return false;
	}else
	 	return true;
}
function validateTodo(form){
	if(form.name.value == null || form.name.value.length <=0){
	 	Light.alert(" Please input event name first."); 
	 	return false;
	}else
	 	return true;
}

function validateBlogAction(form){
	if(document.pressed == "delete"){
		var confirmDelete = Light.confirm(Light.getMessage('DELETE_BLOG'));
	    if (!confirmDelete) // user cancelled delete blog
		   return false;
	    else
	 	   return true;
	}
	return true;
}
function editPortletRef(id){
 var refs = document.forms['form_'+id]['refs'];
 var len =  refs.length;
 for(var i = 0; i < len; i++) {
       if(refs.options[i].selected) {
          aText = refs.options[i].text;
          aValue = refs.options[i].value;
          var portlet = Light.getPortletById(id);
          
          Light.ajax.sendRequest(portlet.requestUrl
                     , "responseId="+id
                     , "tabId="+Light.getCurrentTab().serverId
             		 , "portletId="+portlet.serverId
                     , "mode=edit"
                     , "name="+aValue
                       );
          break;
       }
 }
}

function beforeUpload(id){
	var portlet = Light.getPortletById(id);
	portlet.rememberMode(0);
	return true;
}

function startCallback(id) {
	// make something useful before submit (onStart)
	var portlet = Light.getPortletById(id);
	if(portlet){
		portlet.mode=Light._VIEW_MODE;
		portlet.refreshButtons();
		portlet.lastAction=null;	
	}
	return true;
}

function completeCallback(id,value) {
	// make something useful after (onComplete)
	var portlet = Light.getPortletById(id);
	if(portlet != null){
		if(portlet.name == 'profilePortlet')
			cropProfilePhoto(id,value);
		else
			Light.executeRender(id,'view','normal','');
	}
	hideEditProfilePhoto();
	hideTopPopupDiv("addFeed");
	hideTopPopupDiv("addAllFeed");
	hideTopPopupDiv("addDealFeed");
}

Light.addKeywords = function(e,id){      
	var portlet = Light.getPortletById(id);     
	if(portlet == null) return;                  
	var data = {
      	id : id,
        popupName :'addKeywords'
	};
	createPopupDiv('addKeywords','addKeywords.jst',500,data,e,id); 
}

Light.saveKeywords = function(id,form){
  var keywords = form['keywords'].value;
  if(keywords != null && keywords.length > 0){
     var portlet = Light.getPortletById(id);     
     if(portlet != null){
        var pars = "responseId="+id
	  	    +"&tabId="+Light.getCurrentTab().serverId
	  	    +"&portletId="+portlet.serverId
            +"&action=saveKeywords"
            +"&words="+keywords                      
            ;			        
   		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});     
	 }				  
     hidePopupDiv('addKeywords');
  }else{
    Light.alert("Please input keywords first.");
  }
}

Light.addNotKeywords = function(e,id){      
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;                  
      var data = {
      	id : id,
        popupName :'addNotKeywords'
      };
      createPopupDiv('addNotKeywords','addNotKeywords.jst',500,data,e,id); 
}

Light.saveNotKeywords = function(id,form){
  var words = form['words'].value;
  if(words != null && words.length > 0){
     var portlet = Light.getPortletById(id);     
     if(portlet != null){
        var pars = "responseId="+id
	  	    +"&tabId="+Light.getCurrentTab().serverId
	  	    +"&portletId="+portlet.serverId
            +"&action=saveNotKeywords"
            +"&words="+words                      
            ;			        
   		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});     
	 }				  
	 hidePopupDiv('addNotKeywords');
  }else{
    Light.alert("Please input non keywords first.");
  } 
}

Light.addNotWords = function(e,id){      
      var portlet = Light.getPortletById(id);     
      if(portlet == null) return;                  
      var data = {
      	id : id,
        popupName :'addNotWords'
      };
      createPopupDiv('addNotWords','addNotWords.jst',500,data,e,id); 
}

Light.saveNotWords = function(id,form){
  var words = form['words'].value;
  if(words != null && words.length > 0){
     var portlet = Light.getPortletById(id);     
     if(portlet != null){
        var pars = "responseId="+id
	  	    +"&tabId="+Light.getCurrentTab().serverId
	  	    +"&portletId="+portlet.serverId
            +"&action=saveNotWords"
            +"&words="+words                      
            ;			        
   		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});     
	 }				  
	 hidePopupDiv('addNotWords');
  }else{
    Light.alert("Please input non words first.");
  }   
}

Light.keyDownLogin  = function (e, id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){   
	    Light.loginPortal(id);    
	  }
	  return !(keyID == 13);
}

Light.keyDownLoginTo  = function (e, id) {  
	  var KeyID;
	  if (window.event) {	
		keyID = window.event.keyCode;
	  } else {
	    keyID = e.which;
	  } 
	  if ( keyID == 13){   
	    Light.loginToPortal(id);    
	  }
	  return !(keyID == 13);
}
deleteCalendarEvent= function (id, eventId, eventParentId) {
   var deleteEvent = Light.confirm(Light.getMessage('COMMAND_DELETE_CALENDAR_EVENT'));
   if (!deleteEvent) // user cancelled delete Event
	{
		return;
	}
   var portlet = Light.getPortletById(id);
   var parameter=eventId;   
   if(eventParentId != 0){
     var deleteEvents = Light.confirm(Light.getMessage('COMMAND_DELETE_CALENDAR_EVENTS'));
     if (deleteEvents) // user only delete current Event
	 {
		parameter+="-"+eventParentId
	 }  
   }   
   var pars = "responseId="+id
	  	    +"&tabId="+Light.getCurrentTab().serverId
	  	    +"&portletId="+portlet.serverId
            +"&action=delete"
            +"&parameter="+parameter;			        
   Light.ajax.sendRequestAndUpdate(portlet.requestUrl,id,
				  {evalScripts: portlet.allowJS, parameters: pars});  			        
}

Light.stopMusic= function (url) {
    if(Light.music != null){      
      document.body.removeChild(Light.music);   
      Light.music = null;                
    }
}

Light.checkMusic= function(player) {
  //Light.alert(plyer.readyState);
}

nextSlideShow = function(id, count){
    stopSlideShow(id);
    var portlet = Light.getPortletById(id);
    var picSpan= document.getElementById("picture_"+id);
    picSpan.innerHTML="";
    if(portlet.myGroupPictureName != null){
        portlet.myGroupPictureIndex++;
		if(portlet.myGroupPictureIndex >= portlet.myGroupPictureCount) portlet.myGroupPictureIndex = 0;		
	    var pic = document.getElementById(portlet.myGroupPictureName+"_"+portlet.myGroupPictureIndex);
	    var params = pic.name.split(";");  
	    portlet.parameter="pictureId="+params[0];
	    var data = {	
	     id : id,
	     pictureId : params[0],
	     width : params[1],
	     height : params[2],
	     url :params[3],
	     tagging : params[6],
	     caption : pic.value
	   };
	  if(portlet.myPictures[data.url] == null){
	      portlet.myPictures[data.url]= new Image(data.width,data.height);
	      portlet.myPictures[data.url].id='currentMyPicture_'+id;
          portlet.myPictures[data.url].src=data.url; 
          portlet.myPictures[data.url].className='portlet2';
          portlet.myPictures[data.url].style.border= "0px";           
	   }
	   picSpan.innerHTML="";
	   if(portlet.myPictures[data.url] != null){
	     portlet.myPictures[data.url].width=data.width;
	     portlet.myPictures[data.url].height=data.height;
	     portlet.myPictures[data.url].style.cursor= "";	 
	     picSpan.appendChild(portlet.myPictures[data.url]);	   
	     picSpan.innerHTML += "<br/>"+data.caption;
	   }else	     
	     picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data); 
    }else{
	    portlet.myPictureIndex++;
		if(portlet.myPictureIndex >= count) portlet.myPictureIndex = 0;		
	    var pic = document.getElementById("pictures_"+portlet.myPictureIndex);
	    var params = pic.name.split(";");
	    portlet.parameter="pictureId="+params[0];
	    var data = {
	     id : id,
	     pictureId : params[0],	     
	     width : params[1],
	     height : params[2],
	     url : params[3],
	     tagging : params[6],
	     caption : pic.value
	    };
	   if(portlet.myPictures[data.url] == null){
	      portlet.myPictures[data.url]= new Image(data.width,data.height);
	      portlet.myPictures[data.url].id='currentMyPicture_'+id;
          portlet.myPictures[data.url].src=data.url; 
          portlet.myPictures[data.url].className='portlet2';
          portlet.myPictures[data.url].style.border= "0px";                  
	   }
	   if(portlet.myPictures[data.url] != null){
	     portlet.myPictures[data.url].width=data.width;
	     portlet.myPictures[data.url].height=data.height;
	     portlet.myPictures[data.url].style.cursor= "";	     
	     picSpan.appendChild(portlet.myPictures[data.url]);	   
	     picSpan.innerHTML += "<br/>"+data.caption;
	   }else	     
	     picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data); 
   }
   Light.getCurrentTab().repositionFooter();
}

previousSlideShow = function(id, count)
 {
    stopSlideShow(id);
    var portlet = Light.getPortletById(id);
    var picSpan= document.getElementById("picture_"+id);
    picSpan.innerHTML="";
    if(portlet.myGroupPictureName != null){
        portlet.myGroupPictureIndex--;
		if(portlet.myGroupPictureIndex < 0) portlet.myGroupPictureIndex = portlet.myGroupPictureCount - 1;	
	    var pic = document.getElementById(portlet.myGroupPictureName+"_"+portlet.myGroupPictureIndex);
	    var params = pic.name.split(";");
	    portlet.parameter="pictureId="+params[0];
	    var data = {
	     id : id,
	     pictureId : params[0],	  	     
	     width : params[1],
	     height : params[2],
	     url : params[3],
	     tagging : params[6],
	     caption : ((pic.value) ? pic.value : "")
	   };
	   if(portlet.myPictures[data.url] == null){
	      portlet.myPictures[data.url]= new Image(data.width,data.height);
	      portlet.myPictures[data.url].id='currentMyPicture_'+id;
          portlet.myPictures[data.url].src=data.url; 
          portlet.myPictures[data.url].className='portlet2';
          portlet.myPictures[data.url].style.border= "0px";           
	   }
	   if(portlet.myPictures[data.url] != null){
	     portlet.myPictures[data.url].width=data.width;
	     portlet.myPictures[data.url].height=data.height;
	     portlet.myPictures[data.url].style.cursor= "";	 
	     picSpan.appendChild(portlet.myPictures[data.url]);	   
	     picSpan.innerHTML += "<br/>"+data.caption;
	   }else
	     picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data); 
    }else{
	    portlet.myPictureIndex--;
		if(portlet.myPictureIndex < 0) portlet.myPictureIndex = count - 1;
	    var pic = document.getElementById("pictures_"+portlet.myPictureIndex);
	    var params = pic.name.split(";");
	    portlet.parameter="pictureId="+params[0];
	    var data = {	
	     id : id,
	     pictureId : params[0],	  	      
	     width : params[1],
	     height : params[2],
	     url : params[3],
	     tagging : params[6],
	     caption : ((pic.value) ? pic.value : "")
	   };
	   if(portlet.myPictures[data.url] == null){
	      portlet.myPictures[data.url]= new Image(data.width,data.height);
	      portlet.myPictures[data.url].id='currentMyPicture_'+id;
          portlet.myPictures[data.url].src=data.url; 
          portlet.myPictures[data.url].className='portlet2';
          portlet.myPictures[data.url].style.border= "0px";           
	   }
	   if(portlet.myPictures[data.url] != null){
	     portlet.myPictures[data.url].width=data.width;
	     portlet.myPictures[data.url].height=data.height;
	     portlet.myPictures[data.url].style.cursor= "";	 
	     picSpan.appendChild(portlet.myPictures[data.url]);	   
	     picSpan.innerHTML += "<br/>"+data.caption;
	   }else
	     picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data); 
	}
	Light.getCurrentTab().repositionFooter();
}
startAllSlideShow = function(id, count)
 {
   stopSlideShow(id);
   var portlet = Light.getPortletById(id);   
   portlet.myGroupPictureName = null;
   portlet.myGroupPictureCount = -1;
   if(!portlet.autoShow){    
       portlet.autoShow = true;     
       autoSlideShow(id, count);      
    }
}

startSlideShow = function(id, count)
 {
   stopSlideShow(id);
   var portlet = Light.getPortletById(id);   
   if(!portlet.autoShow){    
       portlet.autoShow = true;  
       if(portlet.myGroupPictureName != null)
          autoGroupSlideShow(id, portlet.myGroupPictureName, portlet.myGroupPictureCount);  
       else         
          autoSlideShow(id, count);
            
    }
}
 
autoSlideShow = function(id, count){
	var portlet = Light.getPortletById(id);
	if(portlet.autoShow){
		var picSpan= document.getElementById("picture_"+id);
		if(portlet.fade == "out")
		   portlet.opacity = portlet.opacity - 1;
		else
		   portlet.opacity = portlet.opacity + 1;
		if(portlet.opacity>=100)
		   portlet.fade="out";
		picSpan.style.filter="alpha(opacity="+portlet.opacity+")";
		picSpan.style.MozOpacity = portlet.opacity / 100;
		if(portlet.opacity<=0){
		    portlet.fade="in";
		    portlet.myPictureIndex++;
		    if(portlet.myPictureIndex >= count) portlet.myPictureIndex = 0;
		    var pic = document.getElementById("pictures_"+portlet.myPictureIndex);
		    var params = pic.name.split(";");
		    portlet.parameter="pictureId="+params[0];
		    var data = {	     
		     width : params[1],
		     height : params[2],
		     url :params[3],
		     caption : ((pic.value) ? pic.value : "")
		   };
		   picSpan.innerHTML="";
		   if(portlet.myPictures[data.url] == null){
		      portlet.myPictures[data.url]= new Image(data.width,data.height);
		      portlet.myPictures[data.url].id='currentMyPicture_'+id;
	          portlet.myPictures[data.url].src=data.url; 
	          portlet.myPictures[data.url].className='portlet2';
	          portlet.myPictures[data.url].style.border= "0px";           
		   }
		   if(portlet.myPictures[data.url] != null){
		     portlet.myPictures[data.url].width=data.width;
		     portlet.myPictures[data.url].height=data.height;
		     portlet.myPictures[data.url].style.cursor= "";	 
		     picSpan.appendChild(portlet.myPictures[data.url]);	   
		     picSpan.innerHTML += "<br/>"+data.caption;
		   }else	
		   	 picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data);
		 } 
		 portlet.autoShowId = setTimeout((function() {autoSlideShow (id, count)}), 50);
		 Light.getCurrentTab().repositionFooter();
	} 	
}

startGroupSlideShow= function(id, name, count){
   stopSlideShow(id);
   var portlet = Light.getPortletById(id);   
   portlet.myGroupPictureIndex = -1;
   portlet.myGroupPictureName = name;
   portlet.myGroupPictureCount = count;
   if(!portlet.autoShow){    
       portlet.autoShow = true;    
       autoGroupSlideShow(id, name, count);       
    }
}

autoGroupSlideShow = function(id, name, count){
	var portlet = Light.getPortletById(id);
	if(portlet.autoShow){		
		var picSpan= document.getElementById("picture_"+id);
		if(portlet.fade == "out")
		   portlet.opacity = portlet.opacity - 1;
		else
		   portlet.opacity = portlet.opacity + 1;
		if(portlet.opacity>=100)
		   portlet.fade="out";
		picSpan.style.filter="alpha(opacity="+portlet.opacity+")";
		picSpan.style.MozOpacity = portlet.opacity / 100;
		if(portlet.opacity<=0){
		    portlet.fade="in";
		    portlet.myGroupPictureIndex++;
			if(portlet.myGroupPictureIndex >= count) portlet.myGroupPictureIndex = 0;
		    var pic = document.getElementById(name+"_"+portlet.myGroupPictureIndex);
		    var params = pic.name.split(";");
		    portlet.parameter="pictureId="+params[0];
		    var data = {	     
		     width : params[1],
		     height : params[2],
		     url :params[3],
		     caption : pic.value
		   };
		   picSpan.innerHTML="";
		   if(portlet.myPictures[data.url] == null){
		      portlet.myPictures[data.url]= new Image(data.width,data.height);
		      portlet.myPictures[data.url].id='currentMyPicture_'+id;
	          portlet.myPictures[data.url].src=data.url; 
	          portlet.myPictures[data.url].className='portlet2';
	          portlet.myPictures[data.url].style.border= "0px";           
		   }
		   if(portlet.myPictures[data.url] != null){
		     portlet.myPictures[data.url].width=data.width;
		     portlet.myPictures[data.url].height=data.height;
		     portlet.myPictures[data.url].style.cursor= "";	 
		     picSpan.appendChild(portlet.myPictures[data.url]);	   
		     picSpan.innerHTML += "<br/>"+data.caption;
		   }else	
		   	 picSpan.innerHTML = TrimPath.processDOMTemplate("slidePicture.jst",data); 
		 }
	     portlet.autoShowId = setTimeout((function() {autoGroupSlideShow (id, name, count)}), 50);	
	     Light.getCurrentTab().repositionFooter();
	} 	
}
stopSlideShow = function(id){	
	var portlet = Light.getPortletById(id);
    portlet.fade="out";
    var picSpan= document.getElementById("picture_"+id);
    picSpan.style.filter="alpha(opacity=100)";
    picSpan.style.MozOpacity = 1.0;
	portlet.autoShow = false;		
	if(portlet.autoShowId != null){
		clearTimeout(portlet.autoShowId);		
    }
}
function refreshAfterConfig(id){
	var portlet = Light.getPortletById(id);
	portlet.refresh();
}
function ConfigAllViewedPic(id, count){
   var portlet = Light.getPortletById(id);
   var cpic = document.getElementById("userCurrentPicture");
   var params = cpic.name.split(";");
   var  cdata = {	
         pictureId : params[0],     
	     width : params[1],
	     height : params[2],
	     url : params[3],
	     caption : cpic.value	     
	   };
   if(portlet.myPictures[cdata.url] == null){
      portlet.myPictures[cdata.url]= new Image(cdata.width,cdata.height);
      portlet.myPictures[cdata.url].id='currentMyPicture_'+id;
      portlet.myPictures[cdata.url].src=cdata.url;  
      portlet.myPictures[cdata.url].className='portlet2';
      portlet.myPictures[cdata.url].style.border= "0px"; 
      portlet.myPictures[cdata.url].style.cursor= "url('light/images/zoomin.cur'), pointer";   
    }
   var data = [];
   for(var i=0;i<count;i++){
     var pic = document.getElementById("pictures_"+i);
     var params = pic.name.split(";");
     data[i] = {	
         pictureId : params[0],     
	     width : params[1],
	     height : params[2],
	     url : params[3],
	     status : params[4],
	     tag : params[5],
	     caption : pic.value	     
	   };
   }
   var innerHtml="";
   innerHtml+="<span title='' width='100%' style='clear: both;display: block; text-align:right;'>";
   innerHtml+="<a href='javascript:;' onclick=\"javascript:hidePopupDiv('configAll');refreshAfterConfig('"+id+"');\">";
   innerHtml+="<img src='light/images/close_on.gif'/></a>";
   innerHtml+="</span>";   
   innerHtml+="<table border='0' cellpadding='0' cellspacing='0' width='100%'>";   
   var count=0;
   for(var j=0;j<data.length;j++){     
     if(portlet.myPictures[data[j].url] != null){
     	 count++;
     	 if(count % 3 == 1) innerHtml+="<tr valign='top'>";
	     innerHtml+="<td class='portlet-table-td-left'>";     
         portlet.myPictures[data[j].url].width=data[j].width / 3;
	     portlet.myPictures[data[j].url].height=data[j].height / 3;
	     portlet.myPictures[data[j].url].style.cursor= "";	
	     var picSpan = document.createElement('span');  
	     picSpan.appendChild(portlet.myPictures[data[j].url]);  
	     innerHtml+= picSpan.innerHTML;  
	     innerHtml+= "<br/>";
	     innerHtml+= "Caption:<br/>"+"<input type='text'  id='caption"+j+"' class='portlet-form-input-field' value='"+data[j].caption+"' size='28' onchange=\"ConfigMyPicture('"+id+"','caption"+j+"',"+data[j].pictureId+","+j+");\"/>";
	     innerHtml+= "<br/>";
	     innerHtml+= "Tag:<br/>"+"<input type='text' id='tag"+j+"' class='portlet-form-input-field' value='"+data[j].tag+"' size='28' onchange=\"ConfigMyPicture('"+id+"','tag"+j+"',"+data[j].pictureId+","+j+");\"/>";
	     innerHtml+= "<br/>";
	     innerHtml+= "Privacy:<br/>";
	     innerHtml+= "<input type='radio' id='status0"+j+"' name='status"+j+"' value='0' class='portlet-form-radio' onchange=\"ConfigMyPicture('"+id+"','status0"+j+"',"+data[j].pictureId+","+j+");\"";
	     if(data[j].status == 0) innerHtml+= " checked='checked'";
		 innerHtml+= ">Only me</input><br/>";
	     innerHtml+= "<input type='radio' id='status1"+j+"' name='status"+j+"' value='1' class='portlet-form-radio' onchange=\"ConfigMyPicture('"+id+"','status1"+j+"',"+data[j].pictureId+","+j+");\"";
		 if(data[j].status == 1) innerHtml+= " checked='checked'";
	 	 innerHtml+= ">Available to my friends</input><br/>";
		 innerHtml+= "<input type='radio' id='status2"+j+"' name='status"+j+"' value='2' class='portlet-form-radio' onchange=\"ConfigMyPicture('"+id+"','status2"+j+"',"+data[j].pictureId+","+j+");\"";
		 if(data[j].status == 2) innerHtml+= " checked='checked'";
	 	 innerHtml+= ">Available to members</input><br/>";
	 	 innerHtml+= "<input type='radio' id='status4"+j+"' name='status"+j+"' value='4' class='portlet-form-radio' onchange=\"ConfigMyPicture('"+id+"','status4"+j+"',"+data[j].pictureId+","+j+");\"";
		 if(data[j].status == 4) innerHtml+= " checked='checked'";
	 	 innerHtml+= ">Open to public</input><br/>";
	 	 innerHtml+="<span id= 'configPictureHeader"+j+"' width='100%' style='text-align:left; color: #FF0000;'></span>";
	 	 if(count % 3 == 0) innerHtml+="</tr>";
	 }	 
   }
   if(count > 0 && count % 3 != 0) innerHtml+="</tr>";
   innerHtml+="</table>";
   createPopupDiv2("configAll",1000,innerHtml,id);   
}

function ConfigMyPicture(id,name, pictureId, index){
   document.getElementById('configPictureHeader'+index).innerHTML = "saving";
   var portlet = Light.getPortletById(id);
   portlet.pictureConfigIndex = index;
   var value = document.getElementById(name).value;   
   var params="pictureId="+pictureId
               +"&name="+name
               +"&value="+value
               +"&responseId="+id
               +"&portletId="+portlet.serverId
               ;
   Light.ajax.sendRequest(Light._CONFIG_MY_PICTURE,{parameters:params, onSuccess:responseConfigMyPicture});
}

function responseConfigMyPicture(t){
      var id = t.responseText;
      var portlet = Light.getPortletById(id);
      document.getElementById('configPictureHeader'+portlet.pictureConfigIndex).innerHTML = "saved successfully";
      portlet.autoShowId = setTimeout((function() {resetConfigMyPictureStatus (id)}), 1000);
}

function resetConfigMyPictureStatus(id){
      var portlet = Light.getPortletById(id);
      document.getElementById('configPictureHeader'+portlet.pictureConfigIndex).innerHTML = "";
}
//------------------------------------------------------------ lightPortalView.js
Light.showInstantMessage= function(e,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="showInstantMessage";
     Light.showAllLogin(false);
     return;
  }
  chatWithProfile();
}
chatWithProfile = function(){
    Light.ajax.sendRequest(Light._CHAT_WITH_PROFILE, {parameters:null, onSuccess:responseChatWithProfile}); 
}
  
responseChatWithProfile = function(t){
    var chatParams = t.responseText.split(",");	
    if(chatParams[1] == 0){
	var data = {
         userName : chatParams[0],	 
         popupName :'isBlockUser'
        };
        createPopupDiv('isBlockUser','isBlockUser.jst',360,data,null,null); 
        return;
    }
    if(chatParams[1] == "n"){
	var data = {
         userName : chatParams[0],	 
         popupName :'noIM'
        };
        createPopupDiv('noIM','noIM.jst',360,data,null,null); 
        return;
    }
    if(chatParams[1] == "f"){
	var data = {   
         userName : chatParams[0],	 
         popupName :'friendOnlyIM'
        };
        createPopupDiv('friendOnlyIM','friendOnlyIM.jst',360,data,null,null); 
        return;
    }	
    var	portlet = new PortletChatWindow(12,200,300,Light.getMessage('TITLE_CHAT')+chatParams[0],"","","chattingPortlet","/chattingPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,true,false,5000,true,'','','',"chattingId="+chatParams[1]); 
	portlet.refresh();
    document.forms['form_'+Light.getCurrentTabId()]['chat'].focus();
  }

Light.showSendMessage= function(e,id,buddyId,buddyName){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.buddyId = buddyId;
     Light.portal.latestAction.buddyName = buddyName;
     Light.portal.latestAction.method="Light.showSendMessage";
     Light.showAllLogin(false);
     return;
  }
  if(!buddyId){
  	 if(Light.portal.latestAction.buddyId)
  	 	buddyId = Light.portal.latestAction.buddyId;
  	 else
  	 	buddyId = 0;
  }
  if(!buddyName && Light.portal.latestAction.buddyName)
	buddyName= Light.portal.latestAction.buddyName;
  var data = {
     id:id,
     popupName :'sendMessage',
     buddyId : buddyId,
     buddyName : buddyName
  };
  createPopupDiv('sendMessage','sendMessage.jst',400,data,e,id); 
}

Light.sendMessageAction= function(id,buddyId,popupName){
	var form = document.forms['mForm_'+id];
	var subject = form['subject'].value;
	if(!subject){
         Light.alert("Please input subject.");
         return;
	}
	var content  = form['content'].value;
	if(!content){
         Light.alert("Please input content.");
         return;
	}
	var format = 0
	var len = form['format'].length;
  	for(var i = 0; i<len; i++) {
		if(form['format'][i].checked) {
			format = parseInt(form['format'][i].value);
		}
 	}
	var params="responseId="+id  
                +"&toUserId="+buddyId
                +"&subject="+escape(encodeURIComponent(subject))
                +"&content="+escape(encodeURIComponent(content))
                +"&format="+format;
                
	Light.ajax.sendRequest(Light._SEND_MESSAGE, {method:'post', parameters:params, onSuccess:Light.sendMessageActionHandler});  
	hidePopupDiv(popupName);
}

Light.sendMessageActionHandler  = function(t){  
	var data = {
     popupName :'responseSendMessageAction',
     value:t.responseText
    };
	createPopupDiv(data.popupName,data.popupName+'.jst',280,data,null,null); 
}

Light.showForwardToFriends=function(e,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="Light.showForwardToFriends";
     Light.showAllLogin(false);;
     return;
  }
  var data = {
     id:id,
     popupName :'forwardToFriends'
  };  
  createPopupDiv('forwardToFriends','forwardToFriends.jst',280,data,e,id); 
}
Light.saveForwardToFriends = function (id) {  
 var params="responseId="+id;      
 Light.ajax.sendRequest(Light._FORWARD_TO_FRIENDS, {parameters:params, onSuccess:Light.saveForwardToFriendsHandler}); 
}
Light.saveForwardToFriendsHandler = function(t){  
  var data = {
     popupName :'responseForwardToFriends'
  };
  createPopupDiv('responseForwardToFriends','responseForwardToFriends.jst',280,data,null,t.responseText); 
}

Light.showAddToFriend= function(e,id,buddyId,buddyName){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.buddyId = buddyId;
     Light.portal.latestAction.buddyName = buddyName;
     Light.portal.latestAction.method="Light.showAddToFriend";
     Light.showAllLogin(false);
     return;
  }
  if(buddyId == null){
  	 if(Light.portal.latestAction.buddyId != null)
  	 	buddyId = Light.portal.latestAction.buddyId;
  	 else
  	 	buddyId = 0;
  }
  if(buddyName == null && Light.portal.latestAction.buddyName != null)
	buddyName= Light.portal.latestAction.buddyName;
  var data = {
     id:id,
     popupName :'addToFriend',
     buddyId : buddyId,
     buddyName : buddyName
  };
  createPopupDiv('addToFriend','addToFriend.jst',360,data,e,id); 
}

Light.saveAddToFriend = function (id,buddyId) {  
 var params="responseId="+id
           +"&buddyId="+buddyId;   
 Light.ajax.sendRequest(Light._ADD_FRIEND, {parameters:params, onSuccess:Light.saveAddToFriendHandler}); 
}
Light.saveAddToFriendHandler = function(t){  
  var data = {
     popupName :'responseAddToFriend'
  };
  createPopupDiv('responseAddToFriend','responseAddToFriend.jst',280,data,null,t.responseText); 
}
Light.showAddToFavorites=function(e,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="Light.showAddToFavorites";
     Light.showAllLogin(false);
     return;
  }
  var data = {
     id:id,
     popupName :'addToFavorites'
  };  
  createPopupDiv('addToFavorites','addToFavorites.jst',280,data,e,id); 
}
Light.saveAddToFavorites = function (id) {  
 var params="responseId="+id;      
 Light.ajax.sendRequest(Light._ADD_TO_FAVORITES, {parameters:params, onSuccess:Light.saveAddToFavoritesHandler}); 
}
Light.saveAddToFavoritesHandler = function(t){  
  var data = {
     popupName :'responseAddToFavorites'
  };
  createPopupDiv('responseAddToFavorites','responseAddToFavorites.jst',280,data,null,t.responseText); 
}
Light.showRankUser= function(e,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="Light.showRankUser";
     Light.showAllLogin(false);
     return;
  }
  var portlet = new PortletPopupWindow(11,300,400,Light.getMessage('MENU_RANK'),"icons rank","","rankPortlet","/rankPortlet"+Light._REQUEST_SUFFIX,true,false,false,false,false,false,false,0,false,'','','',"");    
  portlet.refresh();
}
Light.showBlockUser=function(e,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="Light.showBlockUser";
     Light.showAllLogin(false);
     return;
  }
  var data = {
     id:id,
     popupName :'blockUser'
  };  
  createPopupDiv('blockUser','blockUser.jst',280,data,e,id); 
}
Light.saveBlockUser = function (id) {  
 var params="responseId="+id;      
 Light.ajax.sendRequest(Light._BLOCK_USER, {parameters:params, onSuccess:Light.saveBlockUserHandler}); 
}
Light.saveBlockUserHandler = function(t){  
  var data = {
     popupName :'responseBlockUser'
  };
  createPopupDiv('responseBlockUser','responseBlockUser.jst',280,data,null,t.responseText); 
}

function moveSliderBarBegin(e,slider,sliderBar,begin,end){
	if(slider.move != null && slider.move) return;
	sliderBar.move=true;	
	slider.oldLeft = slider.style.left;
	moveSliderBar(e,slider,sliderBar,begin,end);
}
function moveSliderBar(e,slider,sliderBar,begin,end,positionX){
	if(sliderBar.move == null || !sliderBar.move) return;
	if(slider.move != null && slider.move) return;
	var left=0;
	if(positionX != null){
		left = positionX;
	}else{
		left = getMousePositionX(e);
	}	
	var newPositonX = left;
	left -= getX(sliderBar);
	var original = parseInt(slider.style.left);
	if(left > original)
		left = original + 10;
	else
		left= original - 10;	
	if(left < begin) left = begin;
	if(left > end) left = end;
	slider.style.left = left  + "px";	
	setTimeout((function() {moveSliderBar(e,slider,sliderBar,begin,end,newPositonX)}), 100);
}
function moveSliderBarEnd(slider,sliderBar,id,page,pages,begin,end,viewType){	
	if(slider.move != null && slider.move) return;
	if(sliderBar.move != null && sliderBar.move){
		sliderBar.move=false;
		var now = parseInt(slider.style.left);
		var each = parseInt((end - begin) / pages);
		var newPage = parseInt((now - begin) / each);
		if(now <= begin)
			newpage = 0;
		else if(now >= end)
			newPage = pages -1;
		else{
			var old = parseInt(slider.oldLeft);
			if(now < old && newPage >= page)
				newPage = page - 1;
			if(now > old && newPage <= page)
				newPage = page + 1;			
		}				
		if(newPage < 0) newPage = 0;
		if(newPage >= pages) newPage = pages - 1;
		Light.executeRender(id,'','maximized','viewType='+viewType+';page='+newPage);
	}
}

function moveSliderBegin(e,slider,left){
	slider.move=true;
	slider.oldLeft = slider.style.left;
	slider.left=(typeof left != "undefined") ? left : getMousePositionX(e);

}
function moveSlider(e,slider,begin,end){
	if(slider.move == null || !slider.move) return;
	var left=getMousePositionX(e);
	if(slider.left == null){
		slider.left = left;
	}else{
		var temp = left;
		var original = parseInt(slider.style.left);		
		if(left < slider.left)
			left = original - (slider.left - left);
		else
			left = original + (left - slider.left);
		slider.left = temp;
	}
	if(left < begin) left = begin;
	if(left > end) left = end;
	slider.style.left = left;
	
}
function moveSliderEnd(slider,id,page,pages,begin,end,viewType){
	if(slider.move != null && slider.move){
		slider.move=false;
		var now = parseInt(slider.style.left);			
		var each = parseInt((end - begin) / pages)
		var newPage = parseInt((now - begin) / each);
		if(now <= begin)
			newpage = 0;
		else if(now >= end)
			newPage = pages -1;
		else{
			var old = parseInt(slider.oldLeft);
			if(now < old && newPage >= page)
				newPage = page - 1;
			if(now > old && newPage <= page)
				newPage = page + 1;			
		}
		if(newPage < 0) newPage = 0;
		if(newPage >= pages) newPage = pages - 1;
		Light.executeRender(id,'','maximized','viewType='+viewType+';page='+newPage);
	}
}

function changeNumber(id,obj,number,original,viewType){
	if(number<original)
		moveToRight(obj);
	else
		moveToLeft(obj);
	Light.executeRender(id,'','maximized','viewType='+viewType+'&number='+number);
}

function moveToRight(obj){
	var original = parseInt(obj.style.marginLeft);
	original += 10;
	obj.style.marginLeft = original + "px";
	setTimeout((function() {moveToRight(obj)}), 10);
}


function moveToLeft(obj){
	var original = parseInt(obj.style.marginLeft);
	original -= 10;
	obj.style.marginLeft = original + "px";
	setTimeout((function() {moveToLeft(obj)}), 10);
}

function showProgressBar(e){	  
    Light.portal.progressBar.style.visibility = "visible";
    Light.portal.progressBar.style.zIndex= Light.maxZIndex+1000; 
    var x = getMousePositionX(e);
    var y = getMousePositionY(e);
    if(x < 0) x= 0;
    if(y < 0) y= 0;
    setPosition(Light.portal.progressBar,x,y);
    document.body.appendChild(Light.portal.progressBar); 
}
function hideProgressBar(){
	Light.portal.progressBar.style.visibility= "hidden";
	document.body.removeChild(Light.portal.progressBar); 
}

function responseResignGroup(t){    
  var responseId = t.responseText; 
  var portlet = Light.getPortletById(responseId);
  if(portlet != null)
     portlet.refresh(); 
}

function responseDeleteGroupProfile(t){ 
  var params = t.responseText.split(";");    
  if(params[0] != 0){
   var portlet = Light.getPortletById(params[1]);
  if(portlet != null)
     portlet.refresh(); 
  }else
    Light.alert("You cannot delete this group, because this group has members.");   
  var responseId = t.responseText; 
}


  createTopPopupDiv = function (name,templateName,width,data,e,id,flag) {
      var old = document.getElementById(name);
      if(old != null && flag != null && flag)
      	hideTopPopupDiv(name);
      else if(old != null)
      	return; 
	  var currentTabId = Light.getCurrentTabId();         
      var vPopupDiv = document.createElement('div');
      vPopupDiv.id = name;
      vPopupDiv.style.position = "absolute";      
      vPopupDiv.className = "portlet-popup";  
      if(width != null){ 
      	if(width > Light.portal.layout.maxWidth) 
      		width = Light.portal.layout.maxWidth;
	  	vPopupDiv.style.width= width;     
	  } 
      vPopupDiv.innerHTML = TrimPath.processDOMTemplate(templateName, data);          
	  var x = getMousePositionX(e);
      var y = getMousePositionY(e);
      if(x<=0){
      	x = getScreenCenterX() - width / 2;
      	y = getScreenCenterY();
      }       
      if(parseInt(x)+width > Light.getScreenWidth())
          x = Light.getScreenWidth() - width;      
      setPosition(vPopupDiv,x,y);
      var zIndex = ++Light.maxZIndex+10000;     
      vPopupDiv.style.zIndex= zIndex; 
      document.body.appendChild(vPopupDiv); 
      Light.refreshTextFontSize();
      vPopupDiv = null;
  }
  
  createPopupDiv = function (name,templateName,width,data,e,id,flag) {
      var old = document.getElementById(name);
      if(old != null && flag)
      	hidePopupDiv(name);
      else if(old != null)
      	return;   
	  var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);  
      var vPopupDiv = document.createElement('div');
      vPopupDiv.id = name;
      vPopupDiv.style.position = "absolute";      
      vPopupDiv.className = "portlet-popup";  
      if(width != null){ 
      	if(width > Light.portal.layout.maxWidth) 
      		width = Light.portal.layout.maxWidth;
	  	vPopupDiv.style.width= width;     
	  }   
      vPopupDiv.innerHTML = TrimPath.processDOMTemplate(templateName, data);    
      var x = getMousePositionX(e);
      var y = getMousePositionY(e);
      if(x<=0){
      	x = getScreenCenterX() - width / 2;
      	y = getScreenCenterY();
      }      
      if(parseInt(x)+width > Light.getScreenWidth())
          x = Light.getScreenWidth() - width; 
      x= x - Light.portal.layout.containerLeft - Light.portal.layout.bodyLeft;
      y= y - 40;            
      setPosition(vPopupDiv,x,y);
      var zIndex = ++Light.maxZIndex+10000;     
      vPopupDiv.style.zIndex= zIndex; 
      vdocument.appendChild(vPopupDiv); 
      Light.refreshTextFontSize();
      vPopupDiv = null;
  }

  createPopupDiv2 = function (name,width,content,id) {
      var old = document.getElementById(name);
      if(old != null) return;   
	  var currentTabId = Light.getCurrentTabId();
      var vdocument = document.getElementById('panel_'+currentTabId);            
      var vPopupDiv = document.createElement('div');
      vPopupDiv.id = name;
      vPopupDiv.style.position = "absolute";      
      vPopupDiv.className = "portlet-popup";  
      if(width != null){ 
      	if(width > Light.portal.layout.maxWidth) 
      		width = Light.portal.layout.maxWidth;
	  	vPopupDiv.style.width= width;     
	  }  
      vPopupDiv.innerHTML = content;    
      var x = 20;
      var y = 100;      
      setPosition(vPopupDiv,x,y);
      var portlet = null;       
      var zIndex = ++Light.maxZIndex+10000;      
      vPopupDiv.style.zIndex= zIndex; 
      vdocument.appendChild(vPopupDiv); 
      Light.refreshTextFontSize();
      vPopupDiv = null;
      Light.portal.footer.style.visibility = "hidden";
  }

function hideTopPopupDiv(name,e){
   var old = document.getElementById(name);
   if(old != null){
    var mouseX = getMousePositionX(e);
    var mouseY = getMousePositionY(e);    
    var x = getX(old);
    var y = getY(old);
    var width = old.clientWidth;
    var height = old.clientHeight;
    if(mouseX > x && mouseX < x+width && mouseY > y && mouseY < y+height) return;
	document.body.removeChild(old);
	document.body.onclick = null;
	return true;
   }
   return false;
}
 
function hidePopupDiv(name){
   var currentTabId = Light.getCurrentTabId();
   var vdocument = document.getElementById('panel_'+currentTabId);
   var old = document.getElementById(name);
   if(old != null){
	vdocument.removeChild(old);	
	return true;
   }
   Light.portal.footer.style.visibility = "visible";
   return false;
}
 
function showDesc( e, desc, id) {   
	var currentTabId = Light.getCurrentTabId();
  	var portlet = Light.getPortletById(id);     
  	if(portlet == null) return;      
  	var vdocument = document.getElementById('panel_'+currentTabId);
  	var old = document.getElementById('vDesc');
  	if(old != null) vdocument.removeChild(old);  	
  	var vDesc = document.createElement('div');
  	vDesc.id="vDesc";
  	vDesc.style.position = "absolute"; 
  	vDesc.innerHTML = desc;       
  	vDesc.onmouseout  = function(){ hideDesc();}
  	vDesc.style.width=300;
  	vDesc.className = "portlet-popup3";               
  	var x = getMousePositionX(e) - 80;
  	var y = getMousePositionY(e) - 68;
  	var width = 300;
  	if(parseInt(x)+width > document.body.clientWidth)
		x = parseInt(x) - width - 40;      
  	setPosition(vDesc,x,y);
  	vDesc.style.zIndex= Light.maxZIndex + 1000; 
  	vdocument.appendChild(vDesc); 
  	vDesc = null;   
} 
  
hideDesc  = function () {        
  	var currentTabId = Light.getCurrentTabId();
  	var vdocument = document.getElementById('panel_'+currentTabId);
  	var old = document.getElementById('vDesc');
  	if(old != null) vdocument.removeChild(old);
}

showSocialShare = function(e,id,link){
	var data = {
     	popupName :'socialShare',
     	title:L$(id+'_title').value,
     	link:link
  	};
  	createPopupDiv('socialShare','socialShare.jst',180,data,e); 
}

function showBuddyDetail( e, userId,id) {        
      var params="userId="+userId
                +"&responseId="+id;
      Light.ajax.sendRequest(Light._GET_USER_DETAIL, {parameters:params, onSuccess:responseUserDetail}); 
} 
responseUserDetail = function(t){
	var detail = JSON.parse(t.responseText);
	var data = {
		title : Light.getMessage('CLOSE'),
		buttonMessage : Light.getMessage('BUTTON_MESSAGE'),
		buttonAdd : Light.getMessage('BUTTON_ADD_BUDDY'),
		buttonChat : Light.getMessage('BUTTON_CHAT'),
		buttonSave : Light.getMessage('BUTTON_SAVE'),
		buttonDelete : Light.getMessage('BUTTON_DELETE'),
		popupName : 'buddyDetail',
		conn : detail
	};
	createPopupDiv('buddyDetail','connection.detail',400,data);
}
hideBuddyDetail  = function () {    
	hidePopupDiv('buddyDetail');    
}
saveAddBuddy = function (buddyId,id) {
	var params="responseId="+id+"&buddyId="+buddyId;
	Light.ajax.sendRequest(Light._ADD_FRIEND, {parameters:params, onSuccess:responseSaveAddBuddy});
}
responseSaveAddBuddy = function(t){
	var data = {
		title : Light.getMessage('CLOSE'),
		ok : Light.getMessage('BUTTON_OK'),
		cancel : Light.getMessage('BUTTON_CANCEL'),
		popupName :'responseAddToFriend'
		};
	createPopupDiv('responseAddToFriend','responseAddToFriend.jst',280,data,null,t.responseText);
}
saveBuddyType = function(userId,id) {
    var type = document.forms['buddyDetail_'+id]['friendType'].value;
    var params="userId="+userId
              +"&responseId="+id
              +"&type="+type;
    Light.ajax.sendRequest(Light._SAVE_BUDDY_TYPE, {parameters:params, onSuccess:responseBuddy}); 
}
deleteBuddy= function(userId,id) {
    var params="userId="+userId
              +"&responseId="+id;
    Light.ajax.sendRequest(Light._DELETE_BUDDY, {parameters:params, onSuccess:responseBuddy}); 
}
responseBuddy = function(t){
    hideBuddyDetail();
    var portlet = Light.getPortletById(t.responseText);
    portlet.refresh();
  }

Light.refreshIframeHeight = function(id){
	var height= document.getElementById(id).contentDocument.body.scrollHeight;
	document.getElementById(id).height=height;	
}

Light.validateMicroblog = function(id){
	var len=140-L$(id+'_content').value.length;
	var counter = L$(id+'_counter');
	counter.innerHTML=len;
	if(len<0)
		counter.style.color="#FF0000";
	else
		counter.style.color="#CCCCCC";
}

Light.addMicroblog = function(id){
	var counter = L$(id+'_counter');
	var len= parseInt(counter.innerHTML);
	if(len<0){
		Light.alert('Content is over 140 characters.');
		return;
	}
	var content = L$(id+'_content');	
	var portlet = Light.getPortletByName("microblogPortlet");	
	if(portlet){
		var pars = "responseId="+portlet.id
		  	    +"&content="+content.value
		  	    +"&action=add"
		  	    ;
		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,portlet.id, {method:'post',evalScripts: portlet.allowJS, parameters: pars});
		counter.innerHTML=140;
		content.value="";
	}
}

Light.configMicroblog = function(id,type){
	var portlet = Light.getPortletById(id);
	if(portlet != null){
		var pars = "responseId="+portlet.id
		  	    +"&type="+type
		  	    +"&portletId="+portlet.serverId
		  	    +"&action=config"
		  	    ;
		Light.ajax.sendRequestAndUpdate(portlet.requestUrl,portlet.id, {method:'post',evalScripts: portlet.allowJS, parameters: pars});
	}
}

Light.refreshAtClient = function(portlet){
	if(portlet && portlet.name == "myPicturePortlet"){
    	Light.refreshMyPicturesAtClient(portlet);   
  	}  
}

Light.afterRefreshAtClient = function(id){
	var portlet = Light.getPortletById(id);
   	if(portlet){
		Light.getCurrentTab().repositionPortlets(portlet);
	}    
}

Light.refreshMyPicturesAtClient = function(portlet){
	if(portlet == null) return;    
    var divPictures = L$('userPictures');
    if(divPictures == null) return; 
    var count = divPictures.getElementsByTagName("input").length;	
	var id = portlet.id;
	var picSpan= L$('picture_'+id);
	if(picSpan == null) return;
	if(portlet.fade == "out")
	   	portlet.opacity = portlet.opacity - 1;
	else
	   	portlet.opacity = portlet.opacity + 1;
	if(portlet.opacity>=100)
	   	portlet.fade="out";
	picSpan.style.filter="alpha(opacity="+portlet.opacity+")";
	picSpan.style.MozOpacity = portlet.opacity / 100;
	if(portlet.opacity<=0){	    
		portlet.fade="in";
	    portlet.myPictureIndex++;
	    if(portlet.myPictureIndex >= count) portlet.myPictureIndex = 0;
	    var pic = L$('userPicture_'+portlet.myPictureIndex);
	    var params = pic.name.split(";");
	    portlet.parameter="pictureId="+params[0];
	    var data = {
	    	swidth : params[1],
	     	sheight : params[2],	     
	     	lwidth : params[3],
	     	lheight : params[4],
	     	url : params[5],
	     	id : id,
	     	pictureId : params[0],
	     	caption : pic.value
	    };	    	   
	   	picSpan.innerHTML="";
	   	if(portlet.myPictures[data.url] == null){
	      	portlet.myPictures[data.url]= new Image(data.swidth,data.sheight);
	      	portlet.myPictures[data.url].id='currentMyPicture_'+id;
          	portlet.myPictures[data.url].src=data.url;  
          	portlet.myPictures[data.url].className='portlet2';
          	portlet.myPictures[data.url].style.border= "0px"; 
          	portlet.myPictures[data.url].style.cursor= "url('light/images/zoomin.cur'), pointer";            
	   	}
	   	if(portlet.myPictures[data.url] != null){
	     	portlet.myPictures[data.url].width=data.swidth;
	     	portlet.myPictures[data.url].height=data.sheight;
	     	portlet.myPictures[data.url].style.cursor= "url('light/images/zoomin.cur'), pointer";
	     	picSpan.appendChild(portlet.myPictures[data.url]);	   
	     	picSpan.innerHTML += "<br/>"+data.caption;
	   	}else
	     	picSpan.innerHTML = Light.getViewTemplate("refreshPicture.jst",data);	     
	   	setTimeout((function() {Light.afterRefreshAtClient(id)}), 500);
	   	var next = portlet.myPictureIndex+1;
	   	if(next >= count) next = 0;
	   	var pic2 = L$('userPicture_'+next);
       	var params2 = pic2.name.split(";");
       	var data2 = {
	     	swidth : params2[1],
	     	sheight : params2[2],	     
	     	lwidth : params2[3],
	     	lheight : params2[4],
	     	url : params2[5],
	     	id : id,
	     	pictureId : params2[0],
	     	caption : pic2.value
    	};	 
	   	if(portlet.myPictures[data2.url] == null){	      
	      	portlet.myPictures[data2.url]= new Image(data2.swidth,data2.sheight);
	     	portlet.myPictures[data2.url].id='currentMyPicture_'+id;
          	portlet.myPictures[data2.url].src=data2.url; 
          	portlet.myPictures[data2.url].className='portlet2';
          	portlet.myPictures[data2.url].style.border= "0px"; 
          	portlet.myPictures[data2.url].style.cursor= "url('light/images/zoomin.cur'), pointer";            
	   	}
	}
}
