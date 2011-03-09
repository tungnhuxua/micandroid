
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
 * <p> SiteNovelNote server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SiteNovelNoteManager {
	/**按ID 查询对象*/
	SiteNovelNote getById(java.lang.String id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SiteNovelNote u);
	/**保存数据 由BaseManager类实现*/
	void save(SiteNovelNote u);
	/**修改数据由BaseManager类实现*/
	void update(SiteNovelNote u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.String id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	/**自定义新增方法*/
	void saveInfo(SiteNovelNote entity);
	/** 图片查询 */
	public SiteNovelNote getSiteNovelNoteInfo(String contentId);
}
