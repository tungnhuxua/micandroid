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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.User;
import org.light.portal.util.OrganizationThreadLocal;
/**
 * 
 * @author Jianmin Liu
 **/
public class AuthorizePageTag extends BaseTag {
	
	private boolean authorized;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		PortalTab tab = (PortalTab)request.getAttribute("tab");
		Portal portal = this.getPortalService(request).getPortalById(tab.getPortalId());
		if(tab != null){
			User user = getUser(request);
			if(authorized){
				if(user != null && user.getId()!=OrganizationThreadLocal.getOrg().getUserId() && 
						(user.getUserId().equals(portal.getOwnerId()) 
						|| isAdmin(request, user)
						|| isGroupTabOwner(request,tab, user)
						))
			       return EVAL_BODY_INCLUDE;
				else
				   return SKIP_BODY;
			}else{
				if(user != null && user.getId()!=OrganizationThreadLocal.getOrg().getUserId() && 
						(user.getUserId().equals(portal.getOwnerId()) 
						|| isAdmin(request, user)
						|| isGroupTabOwner(request,tab, user)
						))
				       return SKIP_BODY;
					else
					   return EVAL_BODY_INCLUDE;
			}
		}else
			return SKIP_BODY;
	}
	public boolean isAuthorized() {
		return authorized;
	}
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}	
}