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

package org.light.portlets.chat.service.impl;

import java.util.List;

import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portlets.chat.Chatting;
import org.light.portlets.chat.ChattingRecord;
import org.light.portlets.chat.ChattingUser;
import org.light.portlets.chat.dao.ChatDao;
import org.light.portlets.chat.service.ChatService;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChatServiceImpl extends BaseServiceImpl implements ChatService{
	private ChatDao chatDao;
	
	public List<ChattingUser> getChattingUsersById(long chattingId){
		return chatDao.getChattingUsersById(chattingId);
	}
	public ChattingUser getChattingUsersById(long chattingId, long userId){
		return chatDao.getChattingUsersById(chattingId,userId);
	}
	public List<ChattingRecord> getChattingRecordsById(long chattingId){
		return chatDao.getChattingRecordsById(chattingId);
	}
	public List<ChattingRecord> getChattingRecordsById(long chattingId,int maxRow){
		return chatDao.getChattingRecordsById(chattingId, maxRow);
	}
	
	public Chatting getChattingByUser(long userId){
		List<ChattingUser> list = (List<ChattingUser>)getCacheService().getList(ChattingUser.class,userId);
		if(list == null){
			list = chatDao.getChattingUsers(userId);
			getCacheService().setList(ChattingUser.class,userId,list);
		}
		Chatting chatting = null;
		if(list != null && list.size() > 0) {
			ChattingUser cuser = list.get(0);			
			cuser.setStatus(1);
			this.save(cuser);
			chatting = this.getChattingById(cuser.getChattingId());
		}
		return chatting;
	}
	
	public Chatting getChattingById(long chattingId){
		return chatDao.getChattingById(chattingId);
	}
	public List<Chatting> getChattingByOrgId(long orgId){
		return chatDao.getChattingByOrgId(orgId);
	}
	public void leaveChattingByUser(long userId){
		chatDao.leaveChattingByUser(userId);
	}
	
	public boolean deleteChatRoom(long id){
		return chatDao.deleteChatRoom(id);
	}
	
	public ChatDao getChatDao() {
		return chatDao;
	}

	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
}
