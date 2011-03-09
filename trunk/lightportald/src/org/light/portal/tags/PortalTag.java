 /*
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
 */

package org.light.portal.tags;

import static org.light.portal.util.Constants._CURRENT_TAB;
import static org.light.portal.util.Constants._IS_SIGN_OUT;
import static org.light.portal.util.Constants._PERSON_ID;
import static org.light.portal.util.Constants._PORTAL_INIT_LIST;
import static org.light.portal.util.Constants._REMEMBER_LOCALE;
import static org.light.portal.util.Constants._REMEMBER_USER_ID;
import static org.light.portal.util.Constants._REMEMBER_USER_PASSWORD;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.light.portal.Context;
import org.light.portal.core.PortalUtil;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.PortalTab;
import org.light.portal.model.User;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.LabelBean;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalTag extends PortalBaseTag {
	private static Logger logger = LoggerFactory.getLogger(PortalTag.class);
	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		
		this.setVisitedUser(request,null);
		this.setVisitedPortal(request,null);
		this.setVisitedGroup(request,null);
		this.setVisitedPage(request,null);
		this.setPortal(request,null);
		
		Cookie[] theCookies = request.getCookies();
		long personId = 0L;
		String rememberId = null;
		String rememberPassword = null;
		String currentTab = "";
		String rememberLocale = null;
		if (theCookies != null) {
	        for (int i =0; i< theCookies.length; i++) {
	           Cookie aCookie = theCookies[i];
	           if(aCookie.getName().equals(_PERSON_ID)){
				   if(aCookie.getValue() != null){
					   try{
						   personId =  Long.parseLong(aCookie.getValue());
					   }catch(Exception e){						   
					   }					   
				   }
	           }
	           if(aCookie.getName().equals(_CURRENT_TAB)){
				   if(aCookie.getValue() != null){
					   currentTab =  aCookie.getValue();						   						  
				   }
	           }
	           if(aCookie.getName().equals(_REMEMBER_LOCALE)){
				   if(aCookie.getValue() != null) rememberLocale =  aCookie.getValue();
	           }
	           if(aCookie.getName().equals(_REMEMBER_USER_ID)){
				   if(aCookie.getValue() != null){
					   rememberId =  PortalUtil.decode(aCookie.getValue());						   						  
				   }
	           }
	           if(aCookie.getName().equals(_REMEMBER_USER_PASSWORD)){
				   if(aCookie.getValue() != null){
					   rememberPassword =  aCookie.getValue();						   						  
				   }
	           }
	           if(aCookie.getName().equals(_IS_SIGN_OUT)){
				   if(aCookie.getValue() != null){
					   rememberId = null;
					   rememberPassword =  null;
					   currentTab = "0";
				   }
	           }
	        }
	    }
		User user = this.getUser(request);
		if(user == null || user.getId() == OrganizationThreadLocal.getOrg().getUserId()){			
			if(rememberId != null){
				User rememberUser = this.getUserService(request).getUserByUserId(rememberId,OrganizationThreadLocal.getOrganizationId());
				int status = Context.getInstance().checkLogin(request,rememberUser,rememberPassword,true);
				if(status == 1){
					user = rememberUser;
					Context.getInstance().setUser(request,user,true);
					if(user.getPersonId() > 0)
						personId = user.getPersonId();
					else{
						if(personId == 0){
							personId = PortalUtil.createPersonId();			
						}
						user.setPersonId(personId);
						this.getUserService(request).save(user);
					}	
				}
			}
		}
		if(personId == 0){
			personId = PortalUtil.createPersonId();				
		}
		Context.getInstance().setCookie(request,response,_PERSON_ID,String.valueOf(personId));		
		if(user == null){
			user = OrganizationThreadLocal.getOrg().getUser();
			if(user == null)
				user = this.getUserService(request).getUserByUserId(OrganizationThreadLocal.getDefaultUserId(),OrganizationThreadLocal.getOrganizationId());//_DEFAULT_USER);
			this.setUser(request,user);
		}
		this.setTheme(this.getPortal(request));					
		Context.getInstance().setLocale(request);	    
	    try{
	    	List<LabelBean> list = new LinkedList<LabelBean>();
	    	String[] currentTabIds = currentTab.split("-");
	    	PortalTab defaultTab = null;
	    	List<PortalTab> tabs = this.getPortalService(request).getPortalTabByUser(user,OrganizationThreadLocal.getOrg());
	    	String portalJSON = JSONUtil.getPortalData(request,this.getPortal(request),user.getDisplayName(),user,user.getId(),user,isAdmin(request)).toString();
	    	String pageRootJSON = JSONUtil.getPortalTabData(request,tabs,true,isAdmin(request),user,0).toString();	        	
	    	list.add(new LabelBean("portalJSON",portalJSON));
	    	list.add(new LabelBean("tabsJSON",pageRootJSON));
	    	if(tabs != null && tabs.size() > 0){
	    		for(int i=0;i<currentTabIds.length;i++){
	    			int tabId = -1;
		    		try{
		    			tabId = Integer.parseInt(currentTabIds[i]);
		    		}catch(Exception e){}
		    		if(tabId >= 0 && tabs.size() > tabId){
		    			defaultTab = tabs.get(tabId);
		    		}else{
			    		for(PortalTab tab : tabs){	    		
				    		if(tab.getDefaulted() == 1)
				    			defaultTab = tab;
				    	}
		    		}
		    		if(defaultTab == null) defaultTab = tabs.get(0);		    		
		    		tabs = this.getPortalService(request).getPortalTabByParent(defaultTab.getId());
		    		list.add(new LabelBean("tab"+defaultTab.getId()+"TabsJSON",JSONUtil.getPortalTabData(request,tabs,true,isAdmin(request),user,0).toString()));
		    		if(tabs == null || tabs.size() == 0) break;		    					    	
	    		}
	    		request.setAttribute("tabId",String.valueOf(defaultTab.getId()));
		    	String portletsString= this.runCommand(request,response,"getPortletsByTab").toString();		    	
		    	list.add(new LabelBean("tab"+defaultTab.getId()+"PortletsJSON",portletsString));	
	    	}	  
	    	request.setAttribute(_PORTAL_INIT_LIST,list);
	    }catch(Exception e){
	    	throw new RuntimeException(e);
	    }
		return SKIP_BODY;
	}	
}