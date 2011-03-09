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

/**
 * 
 * @author Jianmin Liu
 **/
import static org.light.portal.util.Constants._DEFAULT_THEME;
import static org.light.portal.util.Constants._MOBILE_MODE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.light.portal.Context;
import org.light.portal.core.PortalCommandUtil;
import org.light.portal.model.Portal;
import org.light.portal.util.ConfigurationUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;

public class PortalBaseTag extends BaseTag{
	
	public int doEndTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		Context.getInstance().readOrganization(request);
		checkMedia(request);
		return SKIP_BODY;
	}
	
	protected void checkMedia(HttpServletRequest request){
		String browserInfo = request.getHeader("User-Agent");
		if(ValidationUtil.isMobile(browserInfo)){
			request.setAttribute(_MOBILE_MODE,true);
		}		
	}
	
	protected Object runCommand(HttpServletRequest request,	HttpServletResponse response, String name) throws Exception{    	
    	return PortalCommandUtil.runCommand(request,response,name);
	}
	
	protected void setTheme(Portal portal){
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String theme =(String)pageContext.getSession().getAttribute("theme");
		if(portal != null && portal.getTheme() != null) theme = portal.getTheme();		
		if(ConfigurationUtil.allowRandomTheme()
				&& this.getVisitedUser(request) == null 
				&& this.getVisitedGroup(request) == null
				&& this.getVisitedPage(request) == null
				&& this.getVisitedPortal(request) == null
				&& this.getUser(request) != null && this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()) theme = ConfigurationUtil.getRandomTheme();
		if(theme == null) theme = PropUtil.getString(_DEFAULT_THEME);		
		String browserInfo = request.getHeader("User-Agent");
		if(browserInfo != null && browserInfo.indexOf("MSIE") >= 0 ){
			theme+="/MSIE";
			pageContext.getSession().setAttribute("browser","/MSIE");
		}
		else if(browserInfo != null && browserInfo.toLowerCase().indexOf("opera") >= 0 ){
			pageContext.getSession().setAttribute("browser","/Opera");
		}
		pageContext.getSession().setAttribute("theme",theme);
	}		
}
