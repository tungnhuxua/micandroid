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

import java.util.Calendar;
import java.util.Date;

import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class CalendarEvent extends Entity {
	
	private String name;
	private String location;
	private String desc;
	private Date startDate;
	private int startTime;
	private Date endDate;
	private int endTime;
	private long userId;
	private long orgId;
	private int state;//0 private; 1 friend only; 2 public
	private long parentId;//daily or weekly event if not 0
	private String link; //event's related link
	//read only
	private int type;
	
	public CalendarEvent(){
		super();
	}
	
	public CalendarEvent(Date startDate, int startTime, Date endDate, int endTime, long userId, long orgId, int state){
		this();
		this.userId = userId;
		this.orgId = orgId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.state = state;
	}
	public CalendarEvent(String name,String location,String desc,Date startDate, int startTime, Date endDate, int endTime, long userId, long orgId, int state){
		this();
		this.name =name;
		this.location = location;
		this.desc =desc;
		this.userId = userId;
		this.orgId = orgId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.state = state;
	}
	public CalendarEvent(String name,String location,String desc,Date startDate, int startTime, Date endDate, int endTime, long userId, long orgId, int state, long parentId){
		this();
		this.name =name;
		this.location = location;
		this.desc =desc;
		this.userId = userId;
		this.orgId = orgId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.state = state;
		this.parentId = parentId;
	}
	public CalendarEvent(String name,String location,String desc, String link,Date startDate, int startTime, Date endDate, int endTime, long userId, long orgId, int state){
		this();
		this.name =name;
		this.location = location;
		this.desc =desc;
		this.link =link;
		this.userId = userId;
		this.orgId = orgId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.state = state;		
	}
	public String getTime(){
		String time;
		if(this.startTime == 10000)
			time = "All Day";
		else
			time = this.getStartHour() + " - " + this.getEndHour();
		return time;
	}
	
	public String getShortName(){
		String shortName = this.name;
		if(shortName.length() > 5)
			shortName = shortName.substring(0,5)+"...";
		return shortName;
	}
	public String getStartMonth(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.startDate.getTime());
		int month = current.get(Calendar.MONTH) + 1;
		String mon = String.valueOf(month);
		if(month < 10) mon = "0"+mon;
		return mon;
	}
	public String getStartDay(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.startDate.getTime());
		int day = current.get(Calendar.DAY_OF_MONTH);
		String date = String.valueOf(day);
		if(day < 10) date = "0"+date;
		return date;
	}
	public String getStartYear(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.startDate.getTime());
		int year = current.get(Calendar.YEAR);		
		return String.valueOf(year);
	}
	public String getStartHour(){
		String start = String.valueOf(startTime);
		if(start.length() == 1) start = start +"00";
		String startDesc = start.substring(0,2)+":"+start.substring(2);
		if(start.length() == 3)
			startDesc= start.substring(0,1)+":"+start.substring(1);		
		if(this.type == 0){
			if(startTime < 1200){
				startDesc = startDesc+"am";
			}else{
				int change = startTime;
				if(startTime >= 1300){
					change = startTime - 1200;
				}
				String changeTime = String.valueOf(change);
				startDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
				if(changeTime.length() == 3)
					startDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
			}
		}
		return startDesc;
	}
	public String getEndMonth(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.endDate.getTime());
		int month = current.get(Calendar.MONTH) + 1;
		String mon = String.valueOf(month);
		if(month < 10) mon = "0"+mon;
		return mon;
	}
	public String getEndDay(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.endDate.getTime());
		int day = current.get(Calendar.DAY_OF_MONTH);
		String date = String.valueOf(day);
		if(day < 10) date = "0"+date;
		return date;
	}
	public String getEndYear(){
		Calendar current = Calendar.getInstance();		
		current.setTimeInMillis(this.endDate.getTime());
		int year = current.get(Calendar.YEAR);		
		return String.valueOf(year);
	}
	public String getEndHour(){
		String end = String.valueOf(endTime);
		if(end.length() == 1) end = end +"00";
		String endDesc = end.substring(0,2)+":"+end.substring(2);
		if(end.length() == 3)
			endDesc= end.substring(0,1)+":"+end.substring(1);		
		if(this.type == 0){
			if(endTime < 1200){
				endDesc = endDesc+"am";
			}else{
				int change = endTime;
				if(endTime >= 1300){
					change = endTime - 1200;
				}
				String changeTime = String.valueOf(change);
				endDesc = changeTime.substring(0,2)+":"+changeTime.substring(2)+"pm";
				if(changeTime.length() == 3)
					endDesc= changeTime.substring(0,1)+":"+changeTime.substring(1)+"pm";
			}
		}
		return endDesc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	
}
