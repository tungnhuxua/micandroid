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

import java.util.ArrayList;
import java.util.List;

import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class TimeInterval extends Entity{
		
	private int time;
	private String desc;
	private List<CalendarHoliday> holidays;
	private List<CalendarEvent> events = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events1 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events2 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events3 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events4 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events5 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events6 = new ArrayList<CalendarEvent>();
	private List<CalendarEvent> events7 = new ArrayList<CalendarEvent>();
	public TimeInterval(){
		super();
	}
	
	public TimeInterval(int time, String desc){
		this();
		this.time = time;
		this.desc = desc;
	}
	
	public void addEvent(CalendarEvent event){
		this.events.add(event);
	}
	public void addEvent1(CalendarEvent event){
		this.events1.add(event);
	}
	public void addEvent2(CalendarEvent event){
		this.events2.add(event);
	}
	public void addEvent3(CalendarEvent event){
		this.events3.add(event);
	}
	public void addEvent4(CalendarEvent event){
		this.events4.add(event);
	}
	public void addEvent5(CalendarEvent event){
		this.events5.add(event);
	}
	public void addEvent6(CalendarEvent event){
		this.events6.add(event);
	}
	public void addEvent7(CalendarEvent event){
		this.events7.add(event);
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<CalendarEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CalendarEvent> events) {
		this.events = events;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<CalendarEvent> getEvents1() {
		return events1;
	}

	public void setEvents1(List<CalendarEvent> events1) {
		this.events1 = events1;
	}

	public List<CalendarEvent> getEvents2() {
		return events2;
	}

	public void setEvents2(List<CalendarEvent> events2) {
		this.events2 = events2;
	}

	public List<CalendarEvent> getEvents3() {
		return events3;
	}

	public void setEvents3(List<CalendarEvent> events3) {
		this.events3 = events3;
	}

	public List<CalendarEvent> getEvents4() {
		return events4;
	}

	public void setEvents4(List<CalendarEvent> events4) {
		this.events4 = events4;
	}

	public List<CalendarEvent> getEvents5() {
		return events5;
	}

	public void setEvents5(List<CalendarEvent> events5) {
		this.events5 = events5;
	}

	public List<CalendarEvent> getEvents6() {
		return events6;
	}

	public void setEvents6(List<CalendarEvent> events6) {
		this.events6 = events6;
	}

	public List<CalendarEvent> getEvents7() {
		return events7;
	}

	public void setEvents7(List<CalendarEvent> events7) {
		this.events7 = events7;
	}

	public List<CalendarHoliday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<CalendarHoliday> holidays) {
		this.holidays = holidays;
	}

}
