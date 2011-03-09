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

package org.light.portlets.connection.dao;

import java.util.List;

import org.light.portal.core.dao.BaseDao;
import org.light.portlets.connection.Connection;

/**
 * 
 * @author Jianmin Liu
 **/
public interface ConnectionDao extends BaseDao {
	
	public int getBuddyCount(long userId);
	public List<Connection> getBuddysByUser(long userId);	
	public Connection getChatBuddy(long userId, long buddyUserId);	
	public List<Connection> getOnlineBuddysByUser(long userId);
	public List<Connection> getUpdatedBuddysByUser(long userId);
	public List<Connection> getBuddysByUserAndType(long userId, int type);
	public List<Connection> getBuddysByUser(long userId, String city, String province);
}
