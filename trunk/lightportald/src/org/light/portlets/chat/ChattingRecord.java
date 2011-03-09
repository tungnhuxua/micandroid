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
public class ChattingRecord extends Entity{

	private long chattingId;
	private long userId;
	private String name;
	private String content;
	private boolean systemAdd;
	
	public ChattingRecord(){
		super();
	}
	
	public ChattingRecord(long chattingId, long userId, String name, String content){
		this();
		this.chattingId = chattingId;
		this.userId = userId;
		this.name = name;
		this.content = content;
	}
	
	public boolean isSystemAdd(){
		boolean result = false;
		if(this.content != null && this.content.length() > 3 && this.content.subSequence(0,3).equals("***"))
			result = true;
		return result;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getChattingId() {
		return chattingId;
	}
	

	public void setChattingId(long chattingId) {
		this.chattingId = chattingId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
	
}
