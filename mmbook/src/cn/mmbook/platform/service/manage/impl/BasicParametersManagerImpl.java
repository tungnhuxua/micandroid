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
 * <p> BasicParameters server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("basicParametersManager")
@Transactional
public class BasicParametersManagerImpl extends BaseManager<BasicParameters,java.lang.Integer> 
					implements BasicParametersManager {

	private BasicParametersDao basicParametersDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setBasicParametersDao(BasicParametersDao dao) {
		this.basicParametersDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.basicParametersDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return basicParametersDao.findByPageRequest(pr);
	}
	
	
	public List getList(BasicParameters u){
		return basicParametersDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return basicParametersDao.listPageAnyTable(pr);
	}
	
	/**验证查询*/
	public List verification(BasicParameters u) {
		return basicParametersDao.verification(u);
	}
}
