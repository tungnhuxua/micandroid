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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class EventFactory {
	private static EventFactory instance = new EventFactory();
	private static Logger logger = LoggerFactory.getLogger(EventFactory.class);
	private Map<Long, Map<String,List<Event>>> eventsMaps;
	
	public static EventFactory getInstance(){		
		return instance;
	}
	public List<Event> getEvents(String eventName){
		List<Event> events = null;
		long orgId = OrganizationThreadLocal.getOrganizationId();
		if(orgId > 0){
			if(eventsMaps.get(orgId) == null){
				synchronized(instance){
					if(eventsMaps.get(orgId) == null){
						logger.debug(String.format("register events map for Organization %s",OrganizationThreadLocal.getWebId()));						
						Map<String, List<Event>> eventsMap = new HashMap<String, List<Event>>();
						eventsMaps.put(orgId,eventsMap);				
					}
				}
			}
			events = eventsMaps.get(orgId).get(eventName);
			if(events == null){
				events = registerEvents(eventsMaps.get(orgId),eventName); 						
			}
		}
		return events;
	}
	
	private synchronized List<Event> registerEvents(Map<String, List<Event>> eventsMap, String eventName){
		if(eventsMap.get(eventName) == null){
			logger.debug(String.format("register events for event %s at Organization %s",eventName, OrganizationThreadLocal.getWebId()));
			List<Event> events = new LinkedList<Event>();
			try{
				for(String name : PropUtil.getStringArray(eventName)){
					Object eventObject = Class.forName(name).newInstance();;
					if(eventObject instanceof Event){
						events.add((Event)eventObject);
					}
				}
			}catch(Exception e){
				logger.error(e.getMessage());
			}
			eventsMap.put(eventName,events);
		}
		return eventsMap.get(eventName);
	}
	private EventFactory(){
		eventsMaps = new HashMap<Long, Map<String, List<Event>>>();
	}
}
