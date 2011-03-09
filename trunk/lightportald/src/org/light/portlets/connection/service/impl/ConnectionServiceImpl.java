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

package org.light.portlets.connection.service.impl;

import java.util.List;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.dao.ConnectionDao;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class ConnectionServiceImpl extends BaseServiceImpl implements ConnectionService{
			
	public List<Connection> getBuddysByUser(long userId){
		List<Connection> connections = (List<Connection>)getCacheService().getList(Connection.class,userId);
		if(connections == null){
			connections = connectionDao.getBuddysByUser(userId);
			getCacheService().setList(Connection.class,userId,connections);
		}
		return connections;
	}
	
	public List<Connection> getBuddysByUser(long userId, String city, String province){
		String key = userId+city+province;
		List<Connection> connections = (List<Connection>)getCacheService().getList(Connection.class,key);
		if(connections == null){
			connections = connectionDao.getBuddysByUser(userId,city,province);
			getCacheService().setList(Connection.class,key,connections,true);
		}
		return connections;
	}
	
	public int getBuddyCount(long userId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Connection.class,key);
		if(count == null){
			count = connectionDao.getBuddyCount(userId);
			getCacheService().setObject(Connection.class,key,count);
		}
		return count;
	}
	
	public List<Connection> getOnlineBuddysByUser(long userId){
		String key = userId+CacheService.ONLINE;
		List<Connection> connections = (List<Connection>)getCacheService().getList(Connection.class,key);
		if(connections == null){
			connections = connectionDao.getOnlineBuddysByUser(userId);
			getCacheService().setList(Connection.class,key,connections,true);
		}
		return connections;
	}
	public List<Connection> getBuddysByUserAndType(long userId, int type){
		String key = userId+CacheService.TYPE+type;
		List<Connection> connections = (List<Connection>)getCacheService().getList(Connection.class,key);
		if(connections == null){
			connections = connectionDao.getBuddysByUserAndType(userId,type);
			getCacheService().setList(Connection.class,key,connections,true);
		}
		return connections;
	}
	public List<Connection> getUpdatedBuddysByUser(long userId){
		String key = userId+CacheService.UPDATED;
		List<Connection> connections = (List<Connection>)getCacheService().getList(Connection.class,key);
		if(connections == null){
			connections = connectionDao.getUpdatedBuddysByUser(userId);
			getCacheService().setList(Connection.class,key,connections,true);
		}
		return connections;
	}
	
	public Connection getChatBuddy(long userId, long buddyUserId){
		String key = userId+CacheService.SEPARATOR+buddyUserId;
		Connection connection = (Connection)getCacheService().getObject(Connection.class,key);
		if(connection == null){
			connection = connectionDao.getChatBuddy(userId,buddyUserId);
			getCacheService().setObject(Connection.class,key,connection);
		}
		return connection;
	}
	
	public boolean approveConnection(Message message){		
		if(this.getChatBuddy(message.getUserId(), message.getPostById()) ==  null){
			Connection myFriend = new Connection(message.getUserId(), message.getPostById());
			this.save(myFriend);
		}
		if(this.getChatBuddy(message.getPostById(), message.getUserId()) ==  null){
			Connection friendToYou = new Connection(message.getPostById(), message.getUserId());
			this.save(friendToYou);
		}
		return true;
	}
	
	public void deleteUser(long userId){
		List<Connection> list = getBuddysByUser(userId);
		if(list != null){
			for(Connection cb : list){
				this.delete(cb);
			}
		}
	}
	public ConnectionDao getConnectionDao() {
		return connectionDao;
	}
	
	public void setconnectionDao(ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}
	private ConnectionDao connectionDao;
}
