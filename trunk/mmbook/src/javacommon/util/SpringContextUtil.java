package javacommon.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring上下文
 * 
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	/**
	 * @return spring上下文(通过getBean("beanName"))
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	/**
	 * @return spring管理的对象
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	
	/**
	 * 创建对象
	 * @param beanName
	 * @param agrs 构造函数参数
	 * @return 根据beanName创建的对象
	 * @author 
	 * @Modify time 2010.09.09
	 */
	public static Object getBean(String beanName,Object[] agrs) {
		return context.getBean(beanName,agrs);
	}
}
