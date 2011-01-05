package net.nbol.core;

import java.io.Serializable;

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
	
	
	

}
