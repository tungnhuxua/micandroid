package ningbo.media.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


import ningbo.media.core.dao.BaseDao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

public class BaseDaoImpl<E, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDao<E, PK> {

	private Class<E> entityClass;

	public BaseDaoImpl() {

	}
	

	public BaseDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void setEntity(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E get(PK id) {
		Assert.notNull(id, "id is required.");
		return (E) getHibernateTemplate().get(this.entityClass, id);
	}

	public E load(PK id) {
		Assert.notNull(id, "id is required.");
		return (E) getHibernateTemplate().load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<E> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty!");
		String hql = "from " + entityClass.getName()
				+ " as b where b.id in(:ids) ";
		return getSession().createQuery(hql).setParameterList("ids", ids)
				.list();
	}

	@SuppressWarnings("unchecked")
	public E get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return (E) findUnique(hql, value);
	}

	public List<E> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return findByHql(hql, value);
	}

	public List<E> getAll() {
		String hql = "from " + entityClass.getName() ;
		return findByHql(hql);
	}

	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName() ;
		return (Long)findUnique(hql);
	}


	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		E entity = get(propertyName,value) ;
		return (entity != null);
	}

	@SuppressWarnings("unchecked")
	public PK save(E entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getHibernateTemplate().save(entity);
	}

	public void update(E entity) {
		Assert.notNull(entity, "entity is required");
		getHibernateTemplate().update(entity);

	}

	public void delete(E entity) {
		Assert.notNull(entity, "entity is required");
		getHibernateTemplate().delete(entity);

	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		getHibernateTemplate().delete(load(id));
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			E entity = load(id);
			getHibernateTemplate().delete(entity);
		}
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public void evict(Object object) {
		getHibernateTemplate().evict(object);
	}

	private Object findUnique(final String hql, final Object... values) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0, j = values.length; i < j; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}

		});
	}
	
	private List<E> findByHql(final String hql,final Object... values){
		return getHibernateTemplate().execute(new HibernateCallback<List<E>>(){

			@SuppressWarnings("unchecked")
			public List<E> doInHibernate(Session session) throws HibernateException,
					SQLException { 
				Query query = session.createQuery(hql) ;
				if(values != null){
					for (int i = 0, j = values.length; i < j; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list() ;
			}
			
		}) ;
	}

}
