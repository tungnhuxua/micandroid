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

package org.light.portlets.chat;

import org.light.portal.model.Entity;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChattingUser extends Entity{

	private long chattingId;
	private long userId;
	private int status; // 0 new 1 active -1 done or reject
	
	public ChattingUser(){
		super();
	}
	
	public ChattingUser(long chattingId, long userId){
		this();
		this.chattingId = chattingId;
		this.userId = userId;
	}
	
	public ChattingUser(long chattingId, long userId, int status){
		this(chattingId, userId);
		this.status = status;
	}
	
	public long getChattingId() {
		return chattingId;
	}

	public void setChattingId(long chattingId) {
		this.chattingId = chattingId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
