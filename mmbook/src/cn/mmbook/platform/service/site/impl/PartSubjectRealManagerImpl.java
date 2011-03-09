package cn.mmbook.platform.service.site.impl;

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

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;
/**
 * <p> PartSubjectReal server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("partSubjectRealManager")
@Transactional
public class PartSubjectRealManagerImpl extends BaseManager<PartSubjectReal,java.lang.Integer> 
					implements PartSubjectRealManager {

	private PartSubjectRealDao partSubjectRealDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setPartSubjectRealDao(PartSubjectRealDao dao) {
		this.partSubjectRealDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.partSubjectRealDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return partSubjectRealDao.findByPageRequest(pr);
	}
	
	
	public List getList(PartSubjectReal u){
		return partSubjectRealDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return partSubjectRealDao.listPageAnyTable(pr);
	}

}
