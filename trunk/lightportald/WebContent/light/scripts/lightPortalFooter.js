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
//------------------------------------------------------------ lightPortalFooter.js
function LightPortalFooter(){}
LightPortalFooter.prototype.render= function(portal){}

LightPortalFooterImpl.prototype = new LightPortalFooter;                // Define sub-class
LightPortalFooterImpl.prototype.constructor = LightPortalFooter;
function LightPortalFooterImpl(){
	LightPortalFooter.call(this);
}
LightPortalFooterImpl.prototype.render = function (portal){
   	log("render Portal: create portal footer");
   	var footer = Light.newElement({
							element:'div',
						   	id:'portalFooter',
						   	className:'portal-footer',
	   						innerHTML:Light.getViewTemplate("footer.view"),
						   	style:{width:portal.layout.containerWidth,
						   		   zIndex:1}
		   		});
   	return footer;
}
Light.showAbout = function(){
	Light.addFunctionHistory("Light.showAbout");
	if(Light.showCurrentPopupWindow("aboutPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_ABOUT').title,"icons about","aboutPortlet","/aboutPortlet.lp");
}
Light.showFAQ = function(){
	Light.addFunctionHistory("Light.showFAQ");
	if(Light.showCurrentPopupWindow("faqPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_FAQ').title,"icons faq","faqPortlet","/faqPortlet.lp");
}
Light.showTerms = function(){
	Light.addFunctionHistory("Light.showTerms");
	if(Light.showCurrentPopupWindow("termsPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_TERMS').title,"icons terms","termsPortlet","/termsPortlet.lp");	
}
Light.showPrivacy = function(){
	Light.addFunctionHistory("Light.showPrivacy");
	if(Light.showCurrentPopupWindow("privacyPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_PRIVACY').title,"icons private","privacyPortlet","/privacyPortlet.lp");
}
Light.showContactUs = function(){
	Light.addFunctionHistory("Light.showContactUs");
	if(Light.showCurrentPopupWindow("contactPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_CONTACT_US').title,"icons message","contactPortlet","/contactPortlet.lp");
}
Light.showCommunityBlog = function(){
	Light.addFunctionHistory("Light.showCommunityBlog");
	if(Light.showCurrentPopupWindow("blogOrgPortlet")) return;
	Light.showPopupPortlet(150,600,L$('MENU_BLOG').title,"icons blog","blogOrgPortlet","/blogOrgPortlet.lp");
}
Light.showFeedback = function(){ 
	Light.addFunctionHistory("Light.showFeedback");
    if(Light.showCurrentPopupWindow("feedbackPortlet")) return;
 	Light.showPopupPortlet(150,600,L$('TITLE_FEEDBACK').title,"icons todolist","feedbackPortlet","/feedbackPortlet.lp",Light._EDIT_MODE);
}