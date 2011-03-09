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

package org.light.portlets.microblog.dao;

import java.util.List;

import org.light.portal.core.dao.BaseDao;
import org.light.portlets.microblog.Microblog;

/**
 * 
 * @author Jianmin Liu
 **/

public interface MicroblogDao extends BaseDao{
	int getPostCountByUser(long userId);
	List<Microblog> getPostsByUser(long userId,int start,int end);
	
	int getPostCountByUserAndFriends(long userId, long[] userIds);
	List<Microblog> getPostsByUserAndFriends(long userId, long[] userIds,int start,int end);
	
	int getPostCountByFriends(long[] userIds);
	List<Microblog> getPostsByFriends(long[] userIds,int start,int end);
	
	Microblog getMicroblogById(long id);
}
