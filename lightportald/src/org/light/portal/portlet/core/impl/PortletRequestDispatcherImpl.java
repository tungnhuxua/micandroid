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

package org.light.portal.portlet.core.impl;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletRequestDispatcherImpl implements PortletRequestDispatcher
{

    private RequestDispatcher requestDispatcher;

    public PortletRequestDispatcherImpl(RequestDispatcher requestDispatcher)
    {
        this.requestDispatcher = requestDispatcher;
    }


    // javax.portlet.PortletRequestDispatcher implementation --------------------------------------
    public void include(RenderRequest request, RenderResponse response) throws PortletException, java.io.IOException
    {
        
        try{        	
            this.requestDispatcher.include(getServletRequest(request),getServletResponse(request));
        }catch (java.io.IOException e){
            throw e;
        }catch (javax.servlet.ServletException e){
            if (e.getRootCause()!=null){
                throw new PortletException(e.getRootCause());
            }else{
                throw new PortletException(e);
            }
        } finally {
            
        }
    }
    // --------------------------------------------------------------------------------------------

    protected HttpServletRequest getServletRequest(PortletRequest request){
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getAttribute("httpServletRequest");		
		return httpServletRequest;
	}
	protected HttpServletResponse getServletResponse(PortletRequest request){
		HttpServletResponse httpServletResponse = (HttpServletResponse)request.getAttribute("httpServletResponse");		
		return httpServletResponse;
	}
}
