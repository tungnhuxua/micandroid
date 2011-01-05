package net.nbol.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.nbol.core.utils.Page;
import net.nbol.core.utils.PropertyFilter;
import net.nbol.core.utils.ReflectionUtils;
import net.nbol.core.utils.PropertyFilter.MatchType;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

/**
 * 功能描述：扩展Hibernat范型基类. 扩展功能包括分页查询,按属性过滤条件列表查询等.可在Service层直接使用,也可以扩展范型DAO子类使用.
 * 创建时间：2011-1-5 上午02:59:40
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */

public class HibernateDao<T,PK extends Serializable> extends BaseHibernateDao<T,PK> {
	
	/**
	 * 用于扩展的DAO子类使用的构造函数. 通过子类的范型定义取得对象类型Class. 
	 * eg. public class UserDao extends HibernateDao<User, Long>
	 */
	public HibernateDao() {
		super();
	}

	/**
	 * 用于Service层直接使用HibernateDAO的构造函数. 
	 * eg. HibernateDao<User, Long> userDao = new HibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public HibernateDao(SessionFactory sessionFactory,Class<T> entityClass){
		super(sessionFactory,entityClass) ;
	}

	/**
	 * 功能：分页获取全部对象.
	 * @param page
	 * @return Page<T>
	 */
	public Page<T> getAll(final Page<T> page) {
		return findByCriteria(page);
	}
	
	/**
	 * 功能：设置分页参数到Criteria对象
	 * @param c
	 * @param page
	 * @return Criteria
	 * 
	 */
	protected Criteria setPageParameter(final Criteria c,final Page<T> page){
		c.setFirstResult(page.getFirst()) ;
		c.setMaxResults(page.getPageSize()) ;
		
		if(page.isOrderBySetted()){
			String[] orderbyArray = StringUtils.split(page.getOrderBy(), ",") ;
			String[] orderArray = StringUtils.split(page.getOrder(),",") ;
			
			Assert.isTrue(orderbyArray.length == orderArray.length,"分页多重排序参数中,排序字段与排序方向的个数不相等") ;
			
			for(int i=0;i<orderbyArray.length;i++){
				if(Page.ASC.equals(orderArray[i])){
					c.addOrder(Order.asc(orderbyArray[i])) ;
				}else{
					c.addOrder(Order.desc(orderbyArray[i])) ;
				}
			}
		}
		return c ;
	}
	
	/**
	 * 功能：设置分页参数到Query对象。()
	 * @param q
	 * @param page
	 * @return Query
	 */
	protected Query setPageparameter(final Query q,final Page<T> page){
		q.setFirstResult(page.getFirst()) ;
		q.setMaxResults(page.getPageSize()) ;
		
		return q ;
	}
	
	/**
	 * 功能：按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *            其中autoCount指定是否动态获取总结果数.
	 * @param Criteria 数量可变的Criterion.
	 * @return Page (分页查询结果.附带结果列表及所有查询时的参数.)
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findByCriteria(final Page<T> page,final Criterion... criterions){
		Assert.notNull(page,"page对象为空");
		
		Criteria c = createCriteria(criterions) ;
		
		if(page.isAutoCount()){
			int countTotal = countCriteriaResult(c,page) ;
			page.setTotalCount(countTotal) ;
		}
		
		setPageParameter(c, page) ;
		List<T> result = c.list() ;
		page.setResult(result) ;
		return page ;
	}
	
	/**
	 * 功能：执行count查询获得本次Criteria查询所能获得的对象总数.
	 * @param c
	 * @param page
	 * @return int
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected int countCriteriaResult(final Criteria c, final Page<T> page) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}
	
	/**
	 * 功能：按HQL分页查询. 不支持自动获取总结果数,需用户另行执行查询.
	 * 
	 * @param page
	 *            分页参数.仅支持pageSize 和firstResult,忽略其他参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数.
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> find(final Page<T> page, final String hql, final Object... values) {
		Assert.notNull(page,"page对象为空") ;
		
		Query query = createQuery(hql, values) ;
		/**
		 * List<T> tempList = query.list() ;
		 * int totalCount = 0 ;
		 * if(tempList != null){
		 * 		totalCount = tempList.size() ;
		 * 	    if(page.isAutoCount){
		 * 	        page.setTotalCount(totalCount) ;
		 *   }
		 * }
		 *
		 * 
		 */
		setPageparameter(query, page) ;
		List<T> result = query.list() ;
		page.setResult(result) ;
		
		return page ;
	}
	
	/**
	 * 功能：按属性条件参数创建Criterion,辅助方法
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @param matchType 匹配模式
	 * @return Criterion
	 * 
	 */
	protected Criterion buildPropertyCriterion(final String propertyName,Object value,MatchType matchType){
		Assert.hasText(propertyName, "过滤的属性名不能为空") ;
		Criterion criterion = null ;
		
		if(MatchType.EQUAL.equals(matchType)){
			criterion = Restrictions.eq(propertyName, value) ;
		}
		
		if(MatchType.LIKE.equals(matchType)){
			criterion = Restrictions.like(propertyName, (String)value, MatchMode.ANYWHERE) ;
		}
		
		return criterion ;
	}
	
	/**
	 * 功能：按属性条件列表创建Criterion数组
	 * @param filters 过滤的属性集合
	 * @return Criterion[]
	 * 
	 */
	protected Criterion[] buildPropertyFilterCriterions(List<PropertyFilter> filters) {
		Assert.notNull(filters) ;
		List<Criterion> criterionList = new ArrayList<Criterion>() ;
		for(PropertyFilter filter : filters){
			String propertyName = filter.getPropertyName() ;
			
			boolean isMultProperty = StringUtils.contains(propertyName, "|") ;
			if(!isMultProperty){//properNameName中只有一个属性的情况.
				Criterion criterion = buildPropertyCriterion(propertyName,filter.getValue(),filter.getMatchType()) ;
				criterionList.add(criterion) ;
			}else{//properName中包含多个属性的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction() ;
				
				String[] params = StringUtils.split(propertyName, "|") ;
				for(String param : params){
					Criterion criterion = buildPropertyCriterion(param,filter.getValue(), filter.getMatchType()) ;
					disjunction.add(criterion) ;
				}
				criterionList.add(disjunction) ;
			}
		}
		
		return criterionList.toArray(new Criterion[criterionList.size()]) ;
	}
	
	
	/**
	 * 功能：按属性查找对象列表,支持多种匹配方式.
	 * @param propertyName
	 * @param value
	 * @param matchType
	 *            目前支持的取值为"EQUAL"与"LIKE".
	 * @return List<T>
	 */
	public List<T> findByProperty(final String propertyName, final Object value, String matchTypeStr) {
		MatchType matchType = Enum.valueOf(MatchType.class, matchTypeStr);
		Criterion criterion = buildPropertyCriterion(propertyName, value, matchType);
		return findByCriteria(criterion);
	}

	/**
	 * 功能：按属性过滤条件列表查找对象列表.
	 * @param filters 过滤条件集合
	 * @return List<T>
	 */
	public List<T> findByFilters(final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriteria(criterions);
	}

	/**
	 * 功能：按属性过滤条件列表分页查找对象.
	 * @param page 分页对象
	 * @param filters 过滤条件集合
	 * @return Page<T>
	 */
	public Page<T> findByFilters(final Page<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriteria(page, criterions);
	}
	
	
}
