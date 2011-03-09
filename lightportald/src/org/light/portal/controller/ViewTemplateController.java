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

package org.light.portal.controller;

import static org.light.portal.util.Constants._MOBILE;
import static org.light.portal.util.Constants._MOBILE_INDEX;
import static org.light.portal.util.Constants._PORTAL_INIT_LIST;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_VERSION;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.Context;
import org.light.portal.model.Organization;
import org.light.portal.model.PortalTab;
import org.light.portal.util.LabelBean;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class ViewTemplateController extends GenericController {

	public void execute(HttpServletRequest request,
			HttpServletResponse response, ControllerChain chain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();			
		String clientUrl = request.getParameter("clientUrl");
		String browserInfo = request.getHeader("User-Agent");
		boolean mobile =  PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION) && (ValidationUtil.isSmallMobile(browserInfo) || request.getAttribute(_MOBILE) != null || (clientUrl != null && clientUrl.indexOf(PropUtil.getString(_MOBILE_INDEX)) > 0));
		if(uri.indexOf("getInitViewTemplates.lp") >= 0){
			getInitView(request, response, mobile);
		}else if(uri.indexOf("getPortalViewTemplates.lp") >= 0){
		    if(mobile)
		    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/portalMobileViews.jsp").forward(request,response);
		    else
		    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/portalViews.jsp").forward(request,response);
		}else if(uri.indexOf("getPortletViewTemplates.lp") >= 0){
		    if(mobile)
		    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/portletMobileViews.jsp").forward(request,response);
		    else
		    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/portletViews.jsp").forward(request,response);
		}
		
		chain.execute(request,response);
	}
	

	private void getInitView(HttpServletRequest request, HttpServletResponse response, boolean mobile) throws ServletException, IOException {
		try{ 
			Organization org = OrganizationThreadLocal.getOrg();
	    	String portalString = this.runCommand(request,response,"getPortal").toString();
	    	String pageString = this.runCommand(request,response,"getPortalTabsByUser").toString();
	    	List<PortalTab> tabs = this.getPortalService(request).getPortalTabByUser(org.getUser(),org);
	    	request.setAttribute("tabId",String.valueOf(tabs.get(0).getId()));
	    	String portletsString= this.runCommand(request,response,"getPortletsByTab").toString();
	    	List<LabelBean> list = new LinkedList<LabelBean>();
	    	if(this.getUser(request) != null && this.getUser(request).getId() != org.getUser().getId())
	    		list.add(new LabelBean("loginUserId",this.getUser(request).getUserId()));
	    	else
	    		list.add(new LabelBean("loginUserId","0"));		    	
	    	list.add(new LabelBean("portalJSON",portalString));
	    	list.add(new LabelBean("tabsJSON",pageString));
	    	list.add(new LabelBean("tab"+tabs.get(0).getId()+"PortletsJSON",portletsString));
	    	request.setAttribute(_PORTAL_INIT_LIST,list);
	    	Context.getInstance().readOrganization(request);
	    }catch(Exception e){
	    	throw new RuntimeException(e);
	    }
	    if(mobile)
	    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/initMobileViews.jsp").forward(request,response);
	    else
	    	request.getSession().getServletContext().getRequestDispatcher("/WEB-INF/view/initViews.jsp").forward(request,response);
	}
}
