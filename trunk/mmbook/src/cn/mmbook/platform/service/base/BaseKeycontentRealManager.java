
package cn.mmbook.platform.service.base;

import java.util.Map;
import java.util.List;


import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;
/**
 * <p> BaseKeycontentReal server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface BaseKeycontentRealManager {
	/**按ID 查询对象*/
	BaseKeycontentReal getById(java.lang.Integer id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(BaseKeycontentReal u);
	/**保存数据 由BaseManager类实现*/
	void save(BaseKeycontentReal u);
	/**修改数据由BaseManager类实现*/
	void update(BaseKeycontentReal u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.Integer id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);

}
