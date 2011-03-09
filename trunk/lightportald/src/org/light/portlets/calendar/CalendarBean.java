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

package org.light.portlets.calendar;

import static org.light.portal.util.Constants._CALENDAR_END_TIME;
import static org.light.portal.util.Constants._CALENDAR_EVENT_STATE;
import static org.light.portal.util.Constants._CALENDAR_INTERVAL;
import static org.light.portal.util.Constants._CALENDAR_START_TIME;

import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class CalendarBean extends Entity {
	
	private long userId;
	private int startTime;
	private int endTime;
	private int interval;// 60 1 hour
	private int type; //0 1:00pm,1 13:00
	private int state;//0 private; 1 friend only; 2 public
	
	public CalendarBean(){
		super();
	}
	
	public CalendarBean(long userId){
		this();
		this.userId = userId;
		this.startTime = _CALENDAR_START_TIME;
		this.endTime = _CALENDAR_END_TIME;
		this.interval = _CALENDAR_INTERVAL;
		this.state = _CALENDAR_EVENT_STATE;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
