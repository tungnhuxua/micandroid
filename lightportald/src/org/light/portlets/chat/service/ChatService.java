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

package org.light.portlets.chat.service;

import java.util.List;

import org.light.portal.core.service.BaseService;
import org.light.portlets.chat.Chatting;
import org.light.portlets.chat.ChattingRecord;
import org.light.portlets.chat.ChattingUser;

/**
 * 
 * @author Jianmin Liu
 **/
public interface ChatService extends BaseService{

	public List<ChattingUser> getChattingUsersById(long chattingId);
	public ChattingUser getChattingUsersById(long chattingId, long userId);
	public List<ChattingRecord> getChattingRecordsById(long chattingId);
	public List<ChattingRecord> getChattingRecordsById(long chattingId,int maxRow);
	public Chatting getChattingByUser(long userId);
	public Chatting getChattingById(long chattingId);
	public void leaveChattingByUser(long userId);
	public List<Chatting> getChattingByOrgId(long orgId);
	public boolean deleteChatRoom(long id);
}
