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
 * <p> SitePart server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SitePartManager {
	/**按ID 查询对象*/
	SitePart getById(java.lang.Integer id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SitePart u);
	/**保存数据 由BaseManager类实现*/
	void save(SitePart u);
	void saveInfo(SitePart u);
	/**修改数据由BaseManager类实现*/
	void update(SitePart u);
	void updateInfo(SitePart u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.Integer id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	/**树 数据 查询 返回字符串 */
	String getTreeCombox(SitePart sp,StringBuffer sb,int j,String cids);
	
	/**判断栏目名是否存在*/
	boolean checkSitePartNameExit(String sitePartName);
	
	/** 根据栏目名查询栏目id */
	int getSitePartIdByName(String sitePartName);
	/** 修改栏目是否有下级节点 */
	void updateLowerNode(int id,int lowerNod);
	
	/** 重载添加方法 */
	void saveInfo(SitePart u,String siteContentSort);
	
	/**	根据id查询栏目信息 */
	SitePart getSitePartById(int id);
	
	/** 判断是否还存在下级节点(true：存在 false：不存在),并修改状态 */
	void checkLowerNodeOrUpdate(SitePart sp);
	
	/** arye  根据parent_id查询id_@return(栏目id) */
	boolean checkLowerMe(int parentId,int partId);
	
	/** arye 删除后将其子节点设为顶级节点 */
	void updateTheChildUp(int id);
	/**查询顶级栏目列表*/
	List getTopSitePartList();
	Page getSitePartList(Map map);
	
	/**按栏目ID查询 下级栏目*/
	public List getSitePartListById(Map map);
}
