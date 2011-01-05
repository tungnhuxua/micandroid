package net.nbol.core.manager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import net.nbol.core.dao.HibernateDao;

/**
 * 功能描述：
 * 创建时间：2011-1-5 上午03:05:27
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */
@Transactional
public class DefaultEntityManager<T,PK extends java.io.Serializable> extends EntityManager<T,PK> {

	protected HibernateDao<T,PK> entityDao ;
	
	@Override
	protected HibernateDao<T, PK> getEntityDao() {
		return entityDao;
	}
	/**
	 * 通过注入的sessionFactory初始化默认DAO.
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		entityDao = new HibernateDao<T,PK>(sessionFactory,entityClass) ;
	}

}
