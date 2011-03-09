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

package org.light.portal.user.search;

import static org.light.portal.util.Constants._OBJECT_TYPE_USER;

import org.light.portal.model.Entity;
import org.light.portal.model.User;
import org.light.portal.model.UserTag;
import org.light.portal.search.Indexer;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserTagIndexer extends UserIndexer implements Indexer{
		
	public synchronized void reIndex(){
				
	}
	public synchronized void reIndex(Class entityType){

	}
	public void  updateIndex(Entity entity){
		if(!(entity instanceof UserTag)) return;
		UserTag tag = (UserTag)entity;
		if(tag.getObjectType() != _OBJECT_TYPE_USER) return;
		User user = null;
		try {
			user =this.getUserService().getUserById(tag.getObjectId());
			super.deleteIndex(user);
		}
		catch (Exception e) {
		}		
		if(user != null)
			addIndex(user,entity.getOrgId());
	}
	
}
