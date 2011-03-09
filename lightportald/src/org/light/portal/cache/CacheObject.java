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

package org.light.portal.cache;

import java.io.Serializable;

/**
 * 
 * @author Jianmin Liu
 **/
public class CacheObject<T> implements Serializable{
	
	private Serializable key;
	private T t;
	private long timeToLive;
	private long timeToMinLive;
	private long timeCreated;
	private long timeAccessedLast;
	private boolean fixedTime;
	
	public CacheObject(Serializable key, T t, long timeToMaxLive, long timeToMinLive){
		this.key = key;
		this.t = t;
		this.timeToLive = timeToMaxLive;
		this.timeToMinLive = timeToMinLive;
		this.timeCreated = System.currentTimeMillis();		
		this.timeAccessedLast = this.timeCreated;
	}
	
	public CacheObject(Serializable key, T t, long timeToLive, boolean fixedTime){
		this(key,t,timeToLive,timeToLive);
		this.fixedTime=fixedTime;
	}
	public T get(){
		this.timeAccessedLast = System.currentTimeMillis();
		return t;
	}
	
	public boolean hasExpired(){
		long currentTime = System.currentTimeMillis();
		if(fixedTime){
			return (currentTime - timeCreated > timeToLive) 
					? true : false;
		}else
			return (currentTime - timeAccessedLast > timeToLive) 
					? true : false;
	}
	
	public boolean fastExpired(){
		timeToLive = timeToLive / 2;
		if(timeToLive < timeToMinLive) timeToLive = timeToMinLive;
		return hasExpired();
	}
	
	public long getIdleTime(){
		return System.currentTimeMillis() - this.timeAccessedLast;
	}
	
	public long getStaleTime(){
		return System.currentTimeMillis() - this.timeCreated;
	}

	public Serializable getKey() {
		return key;
	}

	public void setKey(Serializable key) {
		this.key = key;
	}
}
