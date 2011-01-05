package net.nbol.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述：反射的Utils函数集合. 提供访问私有变量,获取范型类型Class,提取集合中元素的属性等Utils函数.
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
	
}
