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

package org.light.portal.core.service.impl;

import static org.light.portal.util.Constants._CACHE_SYNC_OBJECT_LIST;

import java.util.List;

import org.light.portal.cache.CacheService;
import org.light.portal.core.dao.BaseDao;
import org.light.portal.core.service.BaseService;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class BaseServiceImpl implements BaseService{
	
	private BaseDao baseDao;
	private CacheService cacheService;
	
	public void save(Entity entity){		
		Organization org = OrganizationThreadLocal.getOrg();
    	if(org != null && entity.getOrgId() <= 0) entity.setOrgId(org.getId()); 		
		baseDao.save(entity);
		if(isSyncCache(entity)){
			cacheService.update(entity);
		}
	}
	
	public void delete(Entity entity){
		baseDao.delete(entity);
		if(isSyncCache(entity)){
			cacheService.delete(entity);
		}
	}
	
	public Entity getEntityById(Class klass, long id){
		Entity entity = (Entity)cacheService.getObject(klass,id);
		if(entity == null){
			entity = baseDao.getEntityById(klass,id); 
			cacheService.setObject(klass,id,entity);
		}
		return entity;
	}
	
	public List<Long> getIds(long orgId,String entityName){
		return baseDao.getIds(orgId,entityName);
	}
	
	public boolean isSyncCache(Entity entity){
		return (_CACHE_SYNC_OBJECT_LIST.indexOf(entity.getClass().getName()) >= 0)
				? true : false;
	}
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}	
}