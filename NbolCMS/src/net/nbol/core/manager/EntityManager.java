package net.nbol.core.manager;

import java.io.Serializable;
import java.util.List;

import net.nbol.core.dao.HibernateDao;
import net.nbol.core.utils.Page;
import net.nbol.core.utils.PropertyFilter;
import net.nbol.core.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述：领域对象的业务管理类基类. 提供了领域对象的简单CRUD方法.
 * 创建时间：2011-1-4 上午04:42:39
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 * 
 */

@Transactional
public abstract class EntityManager<T,PK extends Serializable> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass()) ;
	
	protected Class<T> entityClass ;
	
	
	/**
	 * 通过子类的范型定义取得领域对象类型Class. 
	 * eg. public class UserManager extends EntityManager<User, Long>
	 */
	@SuppressWarnings("unchecked")
	public EntityManager() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * 在子类实现的回调函数,为EntityManager提供默认CRUD操作所需的DAO.
	 */
	protected abstract HibernateDao<T, PK> getEntityDao();
	
	
	@Transactional(readOnly = true)
	public T get(PK id) {
		return getEntityDao().get(id);
	}

	@Transactional(readOnly = true)
	public Page<T> getAll(Page<T> page) {
		return getEntityDao().getAll(page);
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getEntityDao().getAll();
	}

	@Transactional(readOnly = true)
	public Page<T> search(Page<T> page, List<PropertyFilter> filters) {
		return getEntityDao().findByFilters(page, filters);
	}

	public void save(T entity) {
		getEntityDao().save(entity);
	}

	public void delete(PK id) {
		getEntityDao().delete(id);
	}

}
