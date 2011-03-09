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

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalSimpleCommand implements PortalCommand{
	private String className;
	private String method;
	
	public PortalSimpleCommand(String className){
		this.className = className;		
	}
	
	public PortalSimpleCommand(String className, String method){
		this.className = className;
		this.method = method;
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{		

		Object action = Class.forName(this.getClassName()).newInstance();
		Object result;
		if(action instanceof PortalAction){
			PortalAction portalAction = (PortalAction)action;
			result = portalAction.execute(request,response);
		}else{
			Class partypes[] = new Class[2];
			partypes[0] = HttpServletRequest.class;
			partypes[1] = HttpServletResponse.class;
			Object arglist[] = new Object[2];
			arglist[0] = request;
			arglist[1] = response;
	    	Method method= Class.forName(this.getClassName()).getMethod(this.getMethod(),partypes);					
	    	result = method.invoke(Class.forName(this.getClassName()).newInstance(),arglist);	
		}
	    
		response.getWriter().print(result);
	}
	
	public Object executeWithResult(HttpServletRequest request, HttpServletResponse response) throws Exception{		

		Object action = Class.forName(this.getClassName()).newInstance();
		Object result;
		if(action instanceof PortalAction){
			PortalAction portalAction = (PortalAction)action;
			result = portalAction.execute(request,response);
		}else{
			Class partypes[] = new Class[2];
			partypes[0] = HttpServletRequest.class;
			partypes[1] = HttpServletResponse.class;
			Object arglist[] = new Object[2];
			arglist[0] = request;
			arglist[1] = response;
	    	Method method= Class.forName(this.getClassName()).getMethod(this.getMethod(),partypes);					
	    	result = method.invoke(Class.forName(this.getClassName()).newInstance(),arglist);	
		}
	    
		return result;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}

