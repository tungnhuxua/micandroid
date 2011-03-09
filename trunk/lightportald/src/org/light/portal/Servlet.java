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

package org.light.portal;
import static org.light.portal.util.Constants._PORTAL_CONTROLLER_CHAIN;
import static org.light.portal.util.Constants._PORTAL_CONTROLLER_LIST;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.controller.Controller;
import org.light.portal.controller.ControllerChain;
import org.light.portal.controller.LastControllerChain;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.portlet.factory.PortletContainerFactory;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class Servlet extends HttpServlet {
	/**
     * init.
     * 
     * @param config ServletConfig
     * @throws ServletException, IOExeption
     * 
     * init portlet container and register request controller chain
     */
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);	 
	    try{	  
	    	//register controllers chain, 
			this.registerControllers();
	    	//init portlet container
			PortletContainerFactory.getPortletContainer().init(config);	
		}catch(Exception e){			
			Throwable ex = e;
			while(ex != null){
				logger.error(String.format("%s: %s",ex.getClass().toString(),ex.getMessage()));
				ex = ex.getCause();
			}
		}		
	}
	
	/**
     * Do get.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException, IOExeption
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	process(request,response);
    }
    /**
     * Do Post.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException, IOExeption
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
    }
	
	/**
     * process.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException, IOExeption
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Context.getInstance().setContentType(response);
    	chain.execute(request,response);
    }
	
    /**
     * registerControllers.
     * 
     * @throws Exception
     * 
     * using chain of responsibility design pattern to process all incoming requests. 
     * All controllers are configured at portal.properties, default configurations are:
     * 
     * portal.controller.chain=org.light.portal.controller.GenericControllerChain
     * portal.controller.list=\
	 *		   org.light.portal.controller.PortalController,\
	 *		   org.light.portal.controller.PageController,\
	 *		   org.light.portal.controller.SpaceController,\
	 *		   org.light.portal.controller.GroupController,\
	 *		   org.light.portal.controller.ViewTemplateController,\
	 *		   org.light.portal.controller.RssController,\
	 *		   org.light.portal.controller.OpmlController,\
	 *		   org.light.portal.controller.CommandController,\
	 *		   org.light.portal.controller.PortletController
	 *
     * and the configuration can be overriden in portal-ext.properties and portal-ext-[organization webId].properties which is community separated configration file
     * 
     */
    private void registerControllers() throws Exception{   	
		Class chainClass=PropUtil.getClass(_PORTAL_CONTROLLER_CHAIN);
		Class[] controllerClasses=PropUtil.getClassArray(_PORTAL_CONTROLLER_LIST);
		List<Controller> controllers = new LinkedList<Controller>();
		LinkedList<ControllerChain> chains = new LinkedList<ControllerChain>();
		for(int i=controllerClasses.length - 1;i>=0;i--){
			controllers.add((Controller)(controllerClasses[i]).newInstance());
			chains.add((ControllerChain)chainClass.newInstance());
		}
		for(int i=0;i<controllers.size();i++){
			if(i == 0){
				chains.get(i).setController(controllers.get(i));
				chains.get(i).setNextControllerChain(new LastControllerChain());
			}else{
				chains.get(i).setController(controllers.get(i));
				chains.get(i).setNextControllerChain(chains.get(i - 1));
			}
		}
		this.chain = chains.getLast();
    }
        
    private ControllerChain chain;
	private Logger logger = LoggerFactory.getLogger(Servlet.class); 
}