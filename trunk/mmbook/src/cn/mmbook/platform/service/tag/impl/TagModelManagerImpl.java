package cn.mmbook.platform.service.tag.impl;

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

import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.impl.*;
import cn.mmbook.platform.service.tag.*;
/**
 * <p> TagModel server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("tagModelManager")
@Transactional
public class TagModelManagerImpl extends BaseManager<TagModel,java.lang.String> 
					implements TagModelManager {

	private TagModelDao tagModelDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTagModelDao(TagModelDao dao) {
		this.tagModelDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.tagModelDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return tagModelDao.findByPageRequest(pr);
	}
	
	
	public List getList(TagModel u){
		return tagModelDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return tagModelDao.listPageAnyTable(pr);
	}

}
