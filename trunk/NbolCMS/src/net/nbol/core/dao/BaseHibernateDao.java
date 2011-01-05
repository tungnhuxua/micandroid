package net.nbol.core.dao;

import java.io.Serializable;
import java.util.List;

import net.nbol.core.utils.ReflectionUtils;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * 功能描述：封装Hibernate原生API的CRUD范型基类. 可在Service层直接使用,也可以扩展范型DAO子类使用.(参看springside)
 * 创建时间：2011-1-4 上午08:59:52
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */

public class BaseHibernateDao<T,PK extends Serializable> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SessionFactory sessionFactory ;
	
	protected Class<T> entityClass ;
	
	
	/**
	 * 功能：用于扩展的DAO子类使用的构造函数. 通过子类的范型定义取得对象类型Class 
	 * eg. public class UserDao extends BaseHibernateDao<User, Long>
	 */
	@SuppressWarnings("unchecked")
	public BaseHibernateDao(){
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass()) ;
	}
	
	/**
	 * 用于Service层直接使用BaseHibernateDAO的构造函数. 
	 * eg. BaseHibernateDao<User, Long> userDao = new BaseHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public BaseHibernateDao(final SessionFactory sessionFactory,final Class<T> entityClass){
		this.sessionFactory = sessionFactory ;
		this.entityClass = entityClass ;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
	 * @param sessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession() ;
	}
	
	/**
	 * 功能：保存新增或修改的对象.
	 * @param entity
	 */
	public void save(T entity){
		Assert.notNull(entity,"保存/更新的对象不能为空。") ;
		getSession().saveOrUpdate(entity) ;
		logger.debug("save entity:{}",entity) ;
	}
	
	/**
	 * 功能：删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity) {
		Assert.notNull(entity,"删除的对象不存在");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 功能：按id删除对象.
	 * @param id
	 */
	public void delete(final PK id) {
		Assert.notNull(id,"删除对象的ID不存在");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * 功能：按id获取对象.
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		Assert.notNull(id,"需加载的id不存在");
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 功能：获取全部对象.
	 * @return List<T>
	 */
	public List<T> getAll() {
		return findByCriteria();
	}
	
	/**
	 * 功能：根据查询HQL与参数列表创建Query对象. 返回对象类型不是Entity时可用此函数灵活查询.
	 * @param hql
	 * @param values 数量可变的参数
	 * @return Query对象
	 */
	public Query createQuery(final String hql, final Object... values) {
		Assert.hasText(hql,"查询的HQL语句不能为空。") ;
		Query query = getSession().createQuery(hql) ;
		if(values != null){
			for(int i=0,j=values.length;i < j ;i++){
				query.setParameter(i,values[i]) ;
			}
		}
		return query ;
	}
	
	/**
	 * 功能：按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数
	 * @return List<T>          
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}
	

	/**
	 * 功能：按HQL查询唯一对象.
	 * @param hql
	 * @param values 数量可变的参数
	 * @return Object
	 */
	public Object findUnique(final String hql, final Object... values) {
		return createQuery(hql, values).uniqueResult();
	}
	
	/**
	 * 功能：按HQL查询Integer类型结果.
	 * @param hql
	 * @param values 数量可变的参数
	 * @return Integer
	 */
	public Integer findInt(final String hql, final Object... values) {
		return (Integer) findUnique(hql, values);
	}

	/**
	 * 功能：按HQL查询Long类型结果.
	 * @param hql
	 * @param values 数量可变的参数
	 * @return Long
	 */
	public Long findLong(final String hql, final Object... values) {
		return (Long) findUnique(hql, values);
	}
	
	/**
	 * 功能：根据Criterion条件创建Criteria. 返回对象类型不是Entity时可用此函数灵活查询.
	 * @param criterions
	 *            数量可变的Criterion.
	 * @return Criteria
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 功能：按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 * @return List<T>           
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criterion... criterions) {
		return createCriteria(criterions).list() ;
	}
	
	/**
	 * 功能：按属性查找对象列表,匹配方式为相等.
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @return List<T>
	 */
	public List<T> findByProperty(String propertyName,Object value){
		Assert.hasText(propertyName,"需要查询的属性名不存在") ;
		Criterion criterion = Restrictions.eq(propertyName, value) ;
		return findByCriteria(criterion) ;
	}
	
	/**
	 * 功能：按属性查找唯一对象,匹配方式为相等.
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @return T 
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName,"需要查询的属性名不存在");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}
	
	/**
	 * 功能：判断对象的属性值在数据库内是否唯一. 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(oldValue)则不作比较.
	 * @param propertyName 属性名
	 * @param newValue 新的属性值
	 * @param oldValue 原来属性值
	 * @return boolean 
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue))
			return true;
		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}

	/**
	 * 功能：取得对象的主键名.
	 * 
	 * @return String
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass.getSimpleName() + " not define in HibernateSessionFactory.");
		return meta.getIdentifierPropertyName();
	}

}
