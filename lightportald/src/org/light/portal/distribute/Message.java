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

package org.light.portal.distribute;

import static org.light.portal.util.Constants._DATABASE_INSTANCE;
import static org.light.portal.util.Constants._FILE_INSTANCE;
import static org.light.portal.util.Constants._INDEX_INSTANCE;
import static org.light.portal.util.Constants._CACHE_INSTANCE;
import java.io.Serializable;

/**
 * 
 * @author Jianmin Liu
 **/
public class Message implements Serializable{
	
	private Event event;
	private long orgId;
	private String server;
	private Serializable body;
	private int databaseInstance = _DATABASE_INSTANCE;
	private int fileInstance = _FILE_INSTANCE;
	private int indexInstance = _INDEX_INSTANCE;
	private int cacheInstance = _CACHE_INSTANCE;
	
	
	public Message(Event event, long orgId){
		this.event = event;
		this.orgId = orgId;
	}
	
	public Message(Event event, long orgId, Serializable obj){
		this(event,orgId);
		this.body = obj;
	}
	
	public Message(Event event, String server, Serializable obj){
		this.event = event;
		this.server = server;
		this.body = obj;
	}
		
	public Message(Event event, long orgId, String server, Serializable obj){
		this(event,orgId,obj);
		this.server = server;
	}
	
	public String toString(){	
		return String.format("Message[event:%s, server:%s, body:%s]",event.toString(),(server != null) ? server : "", (body != null) ? body.toString() : "");
	}

	public Serializable getBody() {
		return body;
	}

	public void setBody(Serializable body) {
		this.body = body;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getCacheInstance() {
		return cacheInstance;
	}

	public int getDatabaseInstance() {
		return databaseInstance;
	}

	public int getFileInstance() {
		return fileInstance;
	}

	public int getIndexInstance() {
		return indexInstance;
	}

	public long getOrgId() {
		return orgId;
	}

}
