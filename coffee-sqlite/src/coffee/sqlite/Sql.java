package coffee.sqlite;

import java.util.Hashtable;
import java.util.Map;

public class Sql {
	/**
	 * k : beanClass
	 * v : query
	 */
	private static Map<Class<?>, String> queryMap = new Hashtable<Class<?>, String>();
	/**
	 * k : beanObject
	 * v : insert pre insert into table (..) values (?,?)
	 */
	private static Map<Object, String> insertMap = new Hashtable<Object, String>();
	/**
	 * k : beanClass
	 * v : tableName
	 */
	private static Map<Class<?>, String> tableMap = new Hashtable<Class<?>, String>();
		
	private static String SELECT_ALL_FROM = "select * from ";
	private static String WHERE = " where ";
	private static String ORDER_BY = " order by "; 
	
	public static String getQuery(Class<?> beanClass){
		String sql = queryMap.get(beanClass);
		if(sql == null){
			String tableName = tableMap.get(beanClass);
			if(tableName == null){
				tableName = TSqliteUtils.getTableName(beanClass);
				tableMap.put(beanClass, tableName);
			}
			sql = SELECT_ALL_FROM + tableName;
			queryMap.put(beanClass, sql);
		}
		return sql;
	}
	
	public static String getQuery(Class<?> beanClass, String condition){
		String sql = getQuery(beanClass);
		if(condition != null){
			sql +=  WHERE + condition;
		}
		return sql;
	}
	
	public static String getQuery(Class<?> beanClass, String condition, String orderBy){
		String sql = null;
		if(condition == null){
			sql = getQuery(beanClass);
		}else{
			sql = getQuery(beanClass, condition);
		}
		if(orderBy != null){
			sql += ORDER_BY + orderBy;
		}
		return sql;
	}
	
	
	
}
