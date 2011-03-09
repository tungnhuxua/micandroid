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
//------------------------------------------------------------ lightPortalGroup.js: Light Portal Group specific functions
LightPortal.prototype.clear = function(){ 	
	Light.deleteCookie(Light._CURRENT_TAB); 
}

function joinToGroup2(e,id){
   if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="joinToGroup2";
     Light.showAllLogin(false);
     return;
  }        
   var params = "&responseId="+id;
   Light.ajax.sendRequest(Light._JOIN_TO_GROUP, {parameters:params,onSuccess:responseJoinToGroup2}); 
}

function responseJoinToGroup2(t){      
      var params = t.responseText.split(";");  
      if(params[0] == 1){
        window.location.reload(true);
      }else{
       var data = {
    	title : L$('CLOSE').title,
        ok : L$('BUTTON_OK').title,    	
        cancel : L$('BUTTON_CANCEL').title,
        popupName :'joinToGroup'
       };                       
       createPopupDiv('joinToGroup','joinToGroup'+params[0]+'.jst',280,data,null,null); 
     }
}

function uploadGroupPictures2(e,groupId,id){
  var portlet = new PortletPopupWindow(11,300,400,L$('MENU_GROUP_UPLOAD_PICTURE').title,"","","groupPicturePortlet","/groupPicturePortlet.lp",true,false,false,false,false,false,false,0,false,'','','',"groupId="+groupId);    
  portlet.refresh();
}

function groupPrivacy(e,groupId,id){      
       
  var params = "&groupId="+groupId;
  Light.ajax.sendRequest(Light._GET_GROUP_PRIVACY, {parameters:params,onSuccess:responseGroupPrivacy});           
       
}

function responseGroupPrivacy(t){ 
   var params = t.responseText.split(";");    
   var data = {
    	title : L$('CLOSE').title,
        ok : L$('BUTTON_OK').title,    	
        cancel : L$('BUTTON_CANCEL').title,
        popupName :'groupPrivacy',
        groupId : params[0],
        acceptLeaderBulletin : params[1],
        acceptMembersBulletin : params[2]
       };
   createPopupDiv('groupPrivacy','groupPrivacy.jst',280,data,null,null); 
}
function saveGroupPrivacy(id,groupId){ 
   
   var params = "groupId="+groupId;
   if(document.forms['form_'+id]['lBulletin'].checked)
     params+="&lBulletin="+document.forms['form_'+id]['lBulletin'].value;
   if(document.forms['form_'+id]['mBulletin'].checked)
     params+="&mBulletin="+document.forms['form_'+id]['mBulletin'].value;
   Light.ajax.sendRequest(Light._SAVE_GROUP_PRIVACY, {parameters:params,onSuccess:responseSaveGroupPrivacy});      
}

function responseSaveGroupPrivacy(t){ 
   var params = t.responseText.split(";");    
   var data = {
    	title : L$('CLOSE').title,
        ok : L$('BUTTON_OK').title,    	
        cancel : L$('BUTTON_CANCEL').title,
        popupName :'savePrivacy' 
       };
   createPopupDiv('savePrivacy','savePrivacy.jst',280,data,null,null); 
}

function responseResignGroup(t){    
  Light.reloadPortal();
}

function responseDeleteGroupProfile(t){    
  var params = t.responseText.split(";");    
  if(params[0] != 0)
    Light.portal.logout();
  else
    Light.alert("You cannot delete this group, because this group has members.");
}

function viewGroupCategory(e,categoryId,id){
  if(!Light.isLogin()){
     Light.portal.latestAction.event =e;
     Light.portal.latestAction.id = id;
     Light.portal.latestAction.method="viewGroupCategory";
     Light.showAllLogin(false);
     return;
  }       	
  var portlet = new PortletPopupWindow(11,300,400,L$('MENU_GROUP').title,"","","groupPortlet","/groupPortlet.lp",true,false,false,false,false,false,false,0,false,'','','',"type=joinDetail&parameter="+categoryId);    
  portlet.mode =Light._EDIT_MODE;
  portlet.refresh();
}
