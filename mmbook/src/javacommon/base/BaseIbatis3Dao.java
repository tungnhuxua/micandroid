package javacommon.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import cn.org.rapid_framework.beanutils.BeanUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

/**
 * @author badqiu
 * @version 1.0
 */
public abstract class BaseIbatis3Dao<E, PK extends Serializable> extends
		DaoSupport implements EntityDao<E, PK> {
	protected final Log log = LogFactory.getLog(getClass());
 
	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionTemplate sqlSessionTemplate;

	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull("sqlSessionFactory must be not null");
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	
	public int delete(final String statement, final Object parameter) {
		return getSqlSessionTemplate().delete(statement, parameter);
	}	
	public int save(final String statement, final Object parameter) {
		return getSqlSessionTemplate().insert(statement,
				parameter);
	}	
	public int update(final String statement, final Object parameter) {
		return getSqlSessionTemplate().update(statement,
				parameter);
	}
	
	
	/**
	 * 查询表主键ID最新值
	 */
	public String getMaxId(String sql_statement) {
		String id=null;
		try{
			id=(String)this.getSqlSessionTemplate().selectOne(sql_statement,null);
			if(null!=id&&id.length()>0){
				id = String.valueOf(Integer.parseInt(id)+1);
			}else{
				id = "11";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 查询表主键ID最新值
	 */
	public String getMaxId() {
		String id=null;
		try{
			id=(String)this.getSqlSessionTemplate().selectOne(geQuerytMaxId(),null);
			if(null!=id&&id.length()>0){
				id = String.valueOf(Integer.parseInt(id)+1);
			}else{
				id = "11";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	}

	public abstract Class getEntityClass();

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public Object getById(PK primaryKey) {
		Object object = getSqlSessionTemplate().selectOne(
				getFindByPrimaryKeyQuery(), primaryKey);
		return object;
	}

	public void deleteById(PK id) {
		getSqlSessionTemplate().delete(getDeleteQuery(), id);
	}
	public int removeByInfo(final String statement, final Object parameter) {
		return getSqlSessionTemplate().delete(statement, parameter);
	}
	public void save(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		Object primaryKey = getSqlSessionTemplate().insert(getInsertQuery(),
				entity);
	}

	public List getList(final String statement, final Object parameter) {
		List list = getSqlSessionTemplate().selectList(statement, parameter);
		return list;
	}

	public void update(E entity) {
		prepareObjectForSaveOrUpdate(entity);
		Object primaryKey = getSqlSessionTemplate().update(getUpdateQuery(),
				entity);
	}

	/**
	 * 用于子类覆盖,在insert,update之前调用
	 * 
	 * @param o
	 */
	protected void prepareObjectForSaveOrUpdate(E o) {
	}

	public String getFindByPrimaryKeyQuery() {
		return getEntityClass().getSimpleName() + ".getById";
	}

	public String getInsertQuery() {
		return getEntityClass().getSimpleName() + ".insert";
	}

	public String getUpdateQuery() {
		return getEntityClass().getSimpleName() + ".update";
	}
	public String getQueryfindAll() {
		return getEntityClass().getSimpleName() + ".findAll";
	}
	public String getDeleteQuery() {
		return getEntityClass().getSimpleName() + ".delete";
	}
	public String geQuerytMaxId() {
		return getEntityClass().getSimpleName() + ".getMaxId";
	}
	public String getCountQuery() {
		return getEntityClass().getSimpleName() + ".count";
	}

	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		System.out.println("pageQuery");
		System.out.println(getCountQuery());
		
		Number totalCount = (Number) this.getSqlSessionTemplate().selectOne(
				getCountQuery(), pageRequest.getFilters());
		System.out.println(totalCount);
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}
		System.out.println("xxxxdsafa");
		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());
		System.out.println("rrrr");
		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		if (pageRequest.getFilters() instanceof Map) {
			filters.putAll((Map) pageRequest.getFilters());
		} else {
			Map parameterObject = BeanUtils.describe(pageRequest.getFilters());
			filters.putAll(parameterObject);
		}
		System.out.println("xxxx");
		List list = getSqlSessionTemplate().selectList(statementName, filters,
				page.getFirstResult(), page.getPageSize());
		page.setResult(list);
		System.out.println("hhh");
		return page;
	}

	/**
	 * 自己定义实现分页查询
	 * @param statementName 查询数据SQL
	 * @param pageRequest
	 * @param countSql  查询分页总记录数 sql 
	 * @return
	 */
	protected Page pageQuery(String statementName,String countSql, PageRequest pageRequest) {
		Number totalCount = (Number) getSqlSessionTemplate().selectOne(countSql,pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}
		
		Page page = new Page(pageRequest, totalCount.intValue());
		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());

		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		if (pageRequest.getFilters() instanceof Map) {
			filters.putAll((Map) pageRequest.getFilters());
		} else {
			Map parameterObject = cn.org.rapid_framework.beanutils.BeanUtils.describe(pageRequest.getFilters());
			filters.putAll(parameterObject);
		}

		List list = getSqlSessionTemplate().selectList(statementName, filters,
				page.getFirstResult(), page.getPageSize());
		page.setResult(list);
		return page;
	}
	
	/**
	 * 自己定义实现分页查询
	 * @param statementName 查询数据SQL
	 * @param pageRequest
	 * @param countSql  查询分页总记录数 sql
	 * 
	 * @return
	 * @author 自强  zqiangzhang@gmail.com(525091883) add
	 */
	protected Page  pageQueryMysql(String statementName,String countSql, PageRequest pageRequest) {
		Number totalCount = (Number) getSqlSessionTemplate().selectOne(countSql,pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}
		boolean ispage = true;
		if(pageRequest.getPageSize()==0){
			ispage = false;
			pageRequest.setPageSize(1);
		}
		Page page = new Page(pageRequest, totalCount.intValue());
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());

		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		if (pageRequest.getFilters() instanceof Map) {
			filters.putAll((Map) pageRequest.getFilters());
		} else {
			Map parameterObject = cn.org.rapid_framework.beanutils.BeanUtils.describe(pageRequest.getFilters());
			filters.putAll(parameterObject);
		}
		//直接调用SqlSession.selectList(statement, parameter, new RowBounds(offset,limit))即可使用物理分页
		List list = new ArrayList();
		if(ispage){
		  list = getSqlSessionTemplate().selectList(statementName, filters,
				page.getFirstResult(), page.getPageSize());
		}else{
			list = getSqlSessionTemplate().selectList(statementName, filters);
		}
		page.setResult(list);
		return page;
	}
	/**
	 * 查询所有数据 
	 * @author qiongguo update 2009-10-11
	 */
	public List findAll() {
		List list = getSqlSessionTemplate().selectList(getQueryfindAll(), null);
		return list;
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}

	public void flush() {
		// ignore
	}

	public static class SqlSessionTemplate {
		SqlSessionFactory sqlSessionFactory;

		public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			this.sqlSessionFactory = sqlSessionFactory;
		}

		public Object execute(SqlSessionCallback action) {
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				Object result = action.doInSession(session);
				return result;
			} finally {
				if (session != null)
					session.close();
			}
		}

		public Object selectOne(final String statement, final Object parameter) {
			return execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectOne(statement, parameter);
				}
			});
		}

		public List selectList(final String statement, final Object parameter,
				final int offset, final int limit) {
			return (List) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, parameter,
							new RowBounds(offset, limit));
				}
			});
		}
		
	 	
		public List selectList(final String statement, final Object parameter) {
			return (List) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.selectList(statement, parameter);
				}
			});
		}

		public int delete(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.delete(statement, parameter);
				}
			});
		}

		public int update(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.update(statement, parameter);
				}
			});
		}

		public int insert(final String statement, final Object parameter) {
			return (Integer) execute(new SqlSessionCallback() {
				public Object doInSession(SqlSession session) {
					return session.insert(statement, parameter);
				}
			});
		}
	}

	public static interface SqlSessionCallback {

		public Object doInSession(SqlSession session);

	}

}
