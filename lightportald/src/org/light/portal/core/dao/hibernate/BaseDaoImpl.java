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

package org.light.portal.core.dao.hibernate;

import java.sql.Timestamp;
import java.util.List;

import org.light.portal.core.dao.BaseDao;
import org.light.portal.core.dao.IdGenereator;
import org.light.portal.core.permission.dao.PermissionDao;
import org.light.portal.core.permission.dao.UserPermissionDao;
import org.light.portal.core.task.AfterTaskService;
import org.light.portal.model.Entity;
import org.light.portal.user.dao.UserDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author Jianmin Liu
 **/
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
    
	private IdGenereator idGenereator;
	private UserDao userDao;
	private PermissionDao permissionDao;
    private UserPermissionDao userPermissionDao;
    private AfterTaskService afterTaskService;
    
    public BaseDaoImpl(){
		this.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
    }
    
    public Entity getEntityById(Class klass, long id){
		return (Entity)this.getHibernateTemplate().get(klass, id);
	}
    
    public Entity create(Entity entity){  
    	this.save(entity);
    	return entity;
	}
    
    public void save(Entity entity){
    	if(entity.getId() == 0) entity.setId(getId());
    	entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    	this.save(entity,true);	
	}
    
    public void save(Entity entity, boolean flag){
    	if(entity.getId() == 0) entity.setId(getId());
    	if(entity.getModifiedDate() == null) entity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    	if(entity.isNew())
    		this.getHibernateTemplate().save(entity);
    	else
    		this.getHibernateTemplate().saveOrUpdate(entity);		
    	if(flag)
    		afterTaskService.afterSave(entity);		
    }
    
    public void delete(Entity entity){
		this.delete(entity,true);
	}
    
    public void delete(Entity entity, boolean flag){
		this.getHibernateTemplate().delete(entity);		
		if(flag)
			afterTaskService.afterDelete(entity);
    }
	
    public List<Long> getIds(long orgId,String entityName){
		List<Long> list = this.getHibernateTemplate().find(String.format("select id from %s where orgId=%d order by id desc",entityName,orgId));  
		return list;
	}
    
	public void execute(String sql){
		this.getHibernateTemplate().bulkUpdate(sql);
	}
	
	protected long getId(){
		return idGenereator.getId();
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserPermissionDao getUserPermissionDao() {
		return userPermissionDao;
	}
	
	public void setUserPermissionDao(UserPermissionDao userPermissionDao) {
		this.userPermissionDao = userPermissionDao;
	}
	
	public PermissionDao getPermissionDao() {
		return permissionDao;
	}
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	public AfterTaskService getAfterTaskService() {
		return afterTaskService;
	}
	public void setAfterTaskService(AfterTaskService afterTaskService) {
		this.afterTaskService = afterTaskService;
	}

	public IdGenereator getIdGenereator() {
		return idGenereator;
	}

	public void setIdGenereator(IdGenereator idGenereator) {
		this.idGenereator = idGenereator;
	}	
	
}