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

package org.light.portal.core.portlets;

import static org.light.portal.util.Constants._CURRENT_LOCALE;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.OrgProfile;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class InfoPortlet  extends LightGenericPortlet  {
	
	protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		long orgId = OrganizationThreadLocal.getOrganizationId();			
		if(request.getPortletSession().getAttribute("orgId",PortletSession.APPLICATION_SCOPE) != null){
			orgId = (Long)request.getPortletSession().getAttribute("orgId",PortletSession.APPLICATION_SCOPE);
		}
		String locale = (String)request.getPortletSession().getAttribute(_CURRENT_LOCALE, PortletSession.APPLICATION_SCOPE);
		OrgProfile profile = OrganizationThreadLocal.getOrg().getProfileMap().get(locale);
		if(profile == null){
			profile = this.getUserService(request).getOrgProfileByOrgId(orgId,locale);
			if(profile != null) OrganizationThreadLocal.getOrg().getProfileMap().put(locale,profile);
		}
		if(profile != null){
			if(request.getWindowState().equals(WindowState.NORMAL))
				response.getWriter().print(profile.getView());
			if(request.getWindowState().equals(WindowState.MAXIMIZED))
				response.getWriter().print(profile.getMaxView());
		}		
	 }

}
