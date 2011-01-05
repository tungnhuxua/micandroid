package net.nbol.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 功能描述：反射的Utils函数集合. 提供访问私有变量,获取范型类型Class,提取集合中元素的属性,循环向上(向下)转型等Utils函数.
 * 创建时间：2011-1-4 上午09:02:22
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */

public class ReflectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}
	
	
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(Class  clazz){
		return getSuperClassGenricType(clazz,0);
	}
	
	/**
	 * 功能：通过反射,获得定义Class时声明的父类的范型参数的类型
	 * @param
	 * @param
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(Class clazz,int index){
		Type genType = clazz.getGenericSuperclass() ;
		
		if(!(genType instanceof ParameterizedType)){
			logger.warn(clazz.getSimpleName()+ "的父类没有该类型！") ;
			return Object.class ;
		}
		
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments() ;
		if(index >= params.length || index < 0){
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		
		return (Class)params[index] ;
	}
	
	/**
	 * 功能：根据给定的对象循环向上转型，获取类的DeclaredField
	 * @param clazz 对象
	 * @param fieldName 属性名
	 * @return Field
	 */
	@SuppressWarnings("unchecked")
	protected static Field getDeclaredField(final Class clazz,final String fieldName){
		Assert.notNull(clazz,"需转型的类为空") ;
		Assert.hasText(fieldName,"参与转型的字段不存在") ;
		for(Class superclass = clazz;superclass != Object.class;superclass = superclass.getSuperclass()){
			try {
				return superclass.getDeclaredField(fieldName) ;
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				logger.error("没有匹配的字段 {}",fieldName) ;
			}
		}
		return null ;
	}
	
	/**
	 * 功能：根据给定的对象循环向上转型，获取类的DeclaredField
	 * @param object 对象
	 * @param fieldName 属性名
	 * @return Field
	 */
	protected static Field getDeclaredField(final Object object,final String fieldName){
		Assert.notNull(object,"转型对象为空") ;
		return getDeclaredField(object.getClass(),fieldName) ;
	}
	
	/**
	 * 功能：强制转换fileld可访问.
	 * @param field
	 */
	protected static void makeAccessible(Field field){
		if(!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())){
			field.setAccessible(true) ;
		}
	}
	
	/**
	 * 功能：直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * @param object 对象
	 * @param fieldName 属性名
	 * @param value 属性值
	 */
	public static void setFieldValue(final Object object,final String fieldName,Object value){
		Field field = getDeclaredField(object,fieldName) ;
		
		if(field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field) ;
		
		try {
			field.set(object, value) ;
		}  catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}
	
	/**
	 * 功能：直接设置对象属性值,无视private/protected修饰符,不经过getter函数.
	 * @param object 对象
	 * @param fieldName 属性名
	 * 
	 */
	public static Object getFieldValue(final Object object,final String fieldName){
		Field field = getDeclaredField(object,fieldName) ;
		
		if(field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		
		makeAccessible(field) ;
		
		Object result = null ;
		try {
			result = field.get(object) ;
		}  catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
		
		return result ;
	}
	
	
	/**
	 * 功能：提取集合中的对象的属性,组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection, final String propertyName)
			throws Exception {

		List list = new ArrayList();

		for (Object obj : collection) {
			list.add(PropertyUtils.getProperty(obj, propertyName));
		}

		return list;
	}

	/**
	 * 功能：提取集合中的对象的属性,组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) throws Exception {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}
	
}
