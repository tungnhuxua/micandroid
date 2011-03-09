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

import static org.light.portal.util.Constants._OBJECT_TYPE_ORG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.light.portal.model.ObjectRole;
import org.light.portal.model.User;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class AuthorizeTag extends BaseTag {
	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	private String role;
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		User user = getUser((HttpServletRequest)pageContext.getRequest());
		if(user == null) return SKIP_BODY;
		ObjectRole objRole = OrganizationThreadLocal.getOrg().getRole(role);
		if(objRole == null) return SKIP_BODY;
		if(user.hasRole(objRole.getId(),user.getOrgId(),_OBJECT_TYPE_ORG))
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;		   
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}	
}