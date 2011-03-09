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

package org.light.portal.core;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.portlet.core.impl.PortletRequestImpl;
import org.light.portal.portlet.core.impl.PortletResponseImpl;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalCommandUtil {
	public static Object runCommand(HttpServletRequest request,	HttpServletResponse response, String name) throws Exception{
    	Object result = null;
		PortalCommand pCommand = PortalCommandFactory.getInstance().getCommand(name);
    	if(pCommand != null){        		 
    		result = pCommand.executeWithResult(request,response);
    	}        
    	return result;
	}
	public static Object runCommand(PortletRequest request, PortletResponse response, String name) throws PortletException{
		try{
    		return runCommand(((PortletRequestImpl)request).getHttpServletRequest(),((PortletResponseImpl)response).getHttpServletResponse(),name);
		}catch(Exception e){
			throw new PortletException(e);
		}
	}
	
}
