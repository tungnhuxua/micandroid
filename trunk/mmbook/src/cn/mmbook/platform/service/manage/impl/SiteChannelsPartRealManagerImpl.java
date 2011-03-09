package cn.mmbook.platform.service.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
 * <p> SiteChannelsPartReal server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("siteChannelsPartRealManager")
@Transactional
public class SiteChannelsPartRealManagerImpl extends BaseManager<SiteChannelsPartReal,java.lang.Integer> 
					implements SiteChannelsPartRealManager {

	private SiteChannelsPartRealDao siteChannelsPartRealDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSiteChannelsPartRealDao(SiteChannelsPartRealDao dao) {
		this.siteChannelsPartRealDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteChannelsPartRealDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteChannelsPartRealDao.findByPageRequest(pr);
	}
	
	public List getList(Map map){
		return siteChannelsPartRealDao.getList(map);
	}
	public List getList(SiteChannelsPartReal u){
		return siteChannelsPartRealDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return null;
	}
	/**
	 * 删除关联表数据
	 * @author Stilfler  673211682 add
	 */
	public void removeByInfo (SiteChannelsPartReal u){
		siteChannelsPartRealDao.removeByInfo(u);
	}
}
