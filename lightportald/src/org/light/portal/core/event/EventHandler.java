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
package org.light.portal.core.event;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
/**
 * 
 * @author Jianmin Liu
 **/
public class EventHandler {
	private static Logger logger = LoggerFactory.getLogger(EventHandler.class);
	
	 public static void invoke(String eventName, HttpServletRequest request,HttpServletResponse response){
		List<Event> events = EventFactory.getInstance().getEvents(eventName);
		if(events != null){
			for(Event event : events){
				try{
					event.execute(request,response);
				}catch(Exception ex){
					logger.error(ex);
				}
			}
		}
	}
}
