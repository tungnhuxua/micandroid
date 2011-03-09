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

import static org.light.portal.util.Constants._GUEST_INDEX;
import static org.light.portal.util.Constants._MAIN_INDEX;
import static org.light.portal.util.Constants._IPAD_GUEST_INDEX;
import static org.light.portal.util.Constants._IPAD_INDEX;
import static org.light.portal.util.Constants._MOBILE_GUEST_INDEX;
import static org.light.portal.util.Constants._MOBILE_INDEX;
import static org.light.portal.util.Constants._PORTAL_MOBILE_BROWSER_VERSION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.light.portal.Context;
import org.light.portal.util.PropUtil;
import org.light.portal.util.ValidationUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class MediaTag extends BaseTag {

	   
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();		
		HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		String browserInfo = request.getHeader("User-Agent");
		try{
			if(PropUtil.getBoolean(_PORTAL_MOBILE_BROWSER_VERSION)){
				if(ValidationUtil.isSmallMobile(browserInfo)){			
					if(Context.getInstance().isAuthorized(request))
						request.getRequestDispatcher(PropUtil.getString(_MOBILE_INDEX)).forward(request,response);
					else
						request.getRequestDispatcher(PropUtil.getString(_MOBILE_GUEST_INDEX)).forward(request,response);
				}else if(ValidationUtil.isIPad(browserInfo)){
					if(Context.getInstance().isAuthorized(request))
						request.getRequestDispatcher(PropUtil.getString(_IPAD_INDEX)).forward(request,response);
					else
						request.getRequestDispatcher(PropUtil.getString(_IPAD_GUEST_INDEX)).forward(request,response);
					
				}else{
					if(Context.getInstance().isAuthorized(request))
						request.getRequestDispatcher(PropUtil.getString(_MAIN_INDEX)).forward(request,response);
					else
						request.getRequestDispatcher(PropUtil.getString(_GUEST_INDEX)).forward(request,response);
				}
			}else{
				if(Context.getInstance().isAuthorized(request))
					request.getRequestDispatcher(PropUtil.getString(_MAIN_INDEX)).forward(request,response);
				else
					request.getRequestDispatcher(PropUtil.getString(_GUEST_INDEX)).forward(request,response);
			}
		}catch(Exception e){}	
		return SKIP_BODY;
	}	
}