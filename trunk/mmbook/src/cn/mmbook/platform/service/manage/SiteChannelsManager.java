
package cn.mmbook.platform.service.manage;

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

import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.service.manage.impl.*;
import cn.mmbook.platform.service.manage.*;
/**
 * <p> SiteChannels server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SiteChannelsManager {
	/**按ID 查询对象*/
	SiteChannels getById(java.lang.Integer id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SiteChannels u);
	/**保存数据 由BaseManager类实现*/
	void save(SiteChannels u);
	/**修改数据由BaseManager类实现*/
	void update(SiteChannels u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.Integer id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	Page getSiteChannelList(Map map);
	List getAllChannelPart(Map map);
}
