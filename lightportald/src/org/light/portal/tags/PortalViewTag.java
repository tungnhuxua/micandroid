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

import static org.light.portal.util.Constants._PORTAL_INIT_LIST;
import static org.light.portal.util.Constants._ROLE_PROFILE;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.light.portal.Context;
import org.light.portal.model.PortalTab;
import org.light.portal.model.User;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.LabelBean;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalViewTag extends PortalBaseTag {

	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		User user = getVisitedUser(request);		
		this.setTheme(this.getVisitedPortal(request));
		Context.getInstance().setLocale(request);
	    try{
	    	List<LabelBean> list = new LinkedList<LabelBean>();
	    	list.add(new LabelBean("portalJSON",this.runCommand(request,response,"profilePortal").toString()));
	    	list.add(new LabelBean("tabsJSON",this.runCommand(request,response,"getPortalTabsByVisit").toString()));
	    	List<PortalTab> tabs = getPortalService(request).getUserProfilePortalTab(user.getUserId(),user.getOrgId());	    	
	    	if(tabs == null || tabs.size() == 0)
	    		tabs = getPortalService(request).getPortalTabByOwner(_ROLE_PROFILE,OrganizationThreadLocal.getOrg());	    	
	    	PortalTab defaultTab = null;
	    	while(tabs != null && tabs.size() > 0){
	    		defaultTab = tabs.get(0);
	    		tabs = this.getPortalService(request).getPortalTabByParent(defaultTab.getId());
	    		list.add(new LabelBean("tab"+defaultTab.getId()+"TabsJSON",JSONUtil.getPortalTabData(request,tabs,true,isAdmin(request),user,0).toString()));	    		
			}
	    	request.setAttribute("tabId",String.valueOf(defaultTab.getId()));	    	
	    	list.add(new LabelBean("tab"+defaultTab.getId()+"PortletsJSON",this.runCommand(request,response,"getPortletsByVisitTab").toString()));	    	
	    	request.setAttribute(_PORTAL_INIT_LIST,list);
	    }catch(Exception e){
	    	throw new RuntimeException(e);
	    }
		return SKIP_BODY;
	}
}