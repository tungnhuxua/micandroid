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
 * <p> SiteMessage server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("siteMessageManager")
@Transactional
public class SiteMessageManagerImpl extends BaseManager<SiteMessage,java.lang.Integer> 
					implements SiteMessageManager {

	private SiteMessageDao siteMessageDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSiteMessageDao(SiteMessageDao dao) {
		this.siteMessageDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteMessageDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteMessageDao.findByPageRequest(pr);
	}
	
	
	public List getList(SiteMessage u){
		return siteMessageDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return siteMessageDao.listPageAnyTable(pr);
	}

}
