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

import static org.light.portal.util.Constants._EVENTS_STARTUP;
import static org.light.portal.util.Constants._EVENTS_SHUTDOWN;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.light.portal.core.event.StartupEvent;
import org.light.portal.core.event.ShutdownEvent;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class ApplicationListener implements ServletContextListener{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	/* Application Startup Event */
	public void	contextInitialized(ServletContextEvent ce) {	
		try{
			for(String event : _EVENTS_STARTUP){
				Object eventObject = Class.forName(event).newInstance();;
				if(eventObject instanceof StartupEvent){
					StartupEvent startupEvent = (StartupEvent)eventObject;
					startupEvent.execute(ce.getServletContext());
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	/* Application Shutdown	Event */
	public void	contextDestroyed(ServletContextEvent ce) {
		try{
			for(String event : _EVENTS_SHUTDOWN){
				Object eventObject = Class.forName(event).newInstance();;
				if(eventObject instanceof ShutdownEvent){
					ShutdownEvent shutdownEvent = (ShutdownEvent)eventObject;
					shutdownEvent.execute(ce.getServletContext());
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

}