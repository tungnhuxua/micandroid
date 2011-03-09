package cn.mmbook.platform.service.base.impl;

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

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;
/**
 * <p> BaseAccessoriesAtegory server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("baseAccessoriesAtegoryManager")
@Transactional
public class BaseAccessoriesAtegoryManagerImpl extends BaseManager<BaseAccessoriesAtegory,java.lang.Integer> 
					implements BaseAccessoriesAtegoryManager {

	private BaseAccessoriesAtegoryDao baseAccessoriesAtegoryDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setBaseAccessoriesAtegoryDao(BaseAccessoriesAtegoryDao dao) {
		this.baseAccessoriesAtegoryDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.baseAccessoriesAtegoryDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return baseAccessoriesAtegoryDao.findByPageRequest(pr);
	}
	
	
	public List getList(BaseAccessoriesAtegory u){
		return baseAccessoriesAtegoryDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return baseAccessoriesAtegoryDao.listPageAnyTable(pr);
	}

}
