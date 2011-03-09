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

package org.light.portal.search.impl;

import java.util.List;

import org.light.portal.model.Entity;
import org.light.portal.search.GenericIndexer;
import org.light.portal.search.Indexer;
import org.light.portal.search.SearchFactory;

/**
 * 
 * @author Jianmin Liu
 **/
public class IndexerImpl extends GenericIndexer implements Indexer{

	public String getType(){
		return null;
	}
	
	protected List<Long> getIds(long orgId){
		return null;
	}
	
	protected void update(long orgId, long id){
		
	}
	protected void doReIndex(long orgId){
		for(Indexer indexer : SearchFactory.getInstance().getIndexers()){
			indexer.reIndex(orgId,false);
		}		
	}
	
	protected void doReIndex(Class klass, long orgId){
		Indexer indexer = SearchFactory.getInstance().getIndexer(klass);
		if(indexer != null){
			indexer.reIndex(orgId,false);			
		}
	}
	
	public void updateIndex(Entity entity){
		Indexer indexer = SearchFactory.getInstance().getIndexer(entity.getClass());
		if(indexer != null){
			indexer.updateIndex(entity);			
		}
	}
		
	public void deleteIndex(Entity entity){
		Indexer indexer = SearchFactory.getInstance().getIndexer(entity.getClass());
		if(indexer != null){
			indexer.deleteIndex(entity);			
		}
	}

}