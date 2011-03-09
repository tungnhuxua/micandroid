
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
 * <p> SiteContentSort server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SiteContentSortManager {
	/**按ID 查询对象*/
	SiteContentSort getById(java.lang.Integer id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SiteContentSort u);
	/**保存数据 由BaseManager类实现*/
	void save(SiteContentSort u);
	/**修改数据由BaseManager类实现*/
	void update(SiteContentSort u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.Integer id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	/**多选下拉框树*/
	String getTreeCombox(SiteContentSort cmsc,StringBuffer sb,int j,String partId);
	/**按Map条件查询数据,返回List 对象*/
	List getListByMap(Map map);
	Page getSoftByPartId(Map map);
	/** 按网站内容分类ID 查询 分类信息 */
	public SiteContentSort getSiteContentSortById(String sortId);
	/**非多选下拉框树*/
	String getComboBoxTree(SiteContentSort cmsc,StringBuffer sb,int j );
	
	/** * 按网站分类ID查询它所有的子类(下级)信息列表(返回网站分类表数据信息) */
	public List getSortBySortID(Map map);
}
