
package cn.mmbook.platform.service.site;

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

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;
/**
 * <p> SiteContent server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SiteContentManager {
	/**按ID 查询对象*/
	SiteContent getById(java.lang.String id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SiteContent u);
	/**保存数据 由BaseManager类实现*/
	void save(SiteContent u);
	/**修改数据由BaseManager类实现*/
	void update(SiteContent u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.String id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	void updateSort(Map map);
	/** 按内容ID查询内容主表信息 */
	public SiteContent getSiteContentById(String contentId);
	Page getListByMap(Map map);
	
	/** 搜索内容 */
	public List sreachSiteContent(Map paraMap);
}
