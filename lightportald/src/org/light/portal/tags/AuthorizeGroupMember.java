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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.light.portal.model.User;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.group.Group;
import org.light.portlets.group.UserGroup;

/**
 * 
 * @author Jianmin Liu
 **/
public class AuthorizeGroupMember extends BaseTag {

	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	private String type; //"yes" check is a member  "no" check is not a member "leader" check is a leader
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		Group group = getVisitedGroup(request);	
		User user = getUser(request);
		if(group == null )
			return SKIP_BODY;
		UserGroup userGroup = null;
		if(user.getId() != OrganizationThreadLocal.getOrg().getUserId())
			userGroup = this.getGroupService(request).getUserGroup(user.getId(),group.getId());
		if("no".equals(type) && userGroup == null)
			return EVAL_BODY_INCLUDE;
		else if("yes".equals(type) && userGroup != null)
			return EVAL_BODY_INCLUDE;
		else if("leader".equals(type) && (userGroup != null && group.getLeaderId() == user.getId()) || isAdmin(request,user))
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;
	}	
	
	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

}