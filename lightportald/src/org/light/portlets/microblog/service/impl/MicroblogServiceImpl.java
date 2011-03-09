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

package org.light.portlets.microblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.microblog.Microblog;
import org.light.portlets.microblog.dao.MicroblogDao;
import org.light.portlets.microblog.service.MicroblogService;

/**
 * 
 * @author Jianmin Liu
 **/

public class MicroblogServiceImpl extends BaseServiceImpl implements MicroblogService{
	
	MicroblogDao microblogDao;
	ConnectionService connectionService;
	
	public int getPostCount(long userId, int type){
		if(type == 1)
			return microblogDao.getPostCountByUser(userId);
		else{
			List<Connection> friends = connectionService.getBuddysByUser(userId);
			if(friends != null && friends.size() > 0){
				int len = friends.size();
				long[] userIds = new long[len];
				int i=0;
				for(Connection friend : friends){
					userIds[i++] = friend.getBuddyUserId();
				}
				if(type == 0) 
					return microblogDao.getPostCountByUserAndFriends(userId,userIds);
				else
					return microblogDao.getPostCountByFriends(userIds);
			}else{
				if(type == 0) 
					return microblogDao.getPostCountByUser(userId);
				else
					return 0;
			}
		}
	}
	
	public List<Microblog> getPosts(long userId, int type,int start,int end){
		if(type == 1)
			return microblogDao.getPostsByUser(userId,start,end);
		else{
			List<Connection> friends = connectionService.getBuddysByUser(userId);
			if(friends != null && friends.size() > 0){
				int len = friends.size();
				long[] userIds = new long[len];
				int i=0;
				for(Connection friend : friends){
					userIds[i++] = friend.getBuddyUserId();
				}
				if(type == 0) 
					return microblogDao.getPostsByUserAndFriends(userId,userIds,start,end);
				else
					return microblogDao.getPostsByFriends(userIds,start,end);
			}else{
				if(type == 0) 
					return microblogDao.getPostsByUser(userId,start,end);
				else
					return new ArrayList<Microblog>();
			}
		}
			
	}

	public Microblog getMicroblogById(long id){
		return microblogDao.getMicroblogById(id);
	}
	
	public MicroblogDao getMicroblogDao() {
		return microblogDao;
	}

	public void setMicroblogDao(MicroblogDao microblogDao) {
		this.microblogDao = microblogDao;
	}

	public ConnectionService getConnectionService() {
		return connectionService;
	}

	public void setConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}
}
