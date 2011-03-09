package cn.mmbook.platform.service.tag.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javacommon.base.*;
import cn.org.rapid_framework.page.*;

import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 
 * @author Administrator
 *
 */


@Component("cmsModelfieldManager")
@Transactional
public class CmsModelfieldManagerImpl extends BaseManager<CmsModelfield,java.lang.String> 
					implements CmsModelfieldManager {

	private CmsModelfieldDao cmsModelfieldDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsModelfieldDao(CmsModelfieldDao dao) {
		this.cmsModelfieldDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cmsModelfieldDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsModelfieldDao.findByPageRequest(pr);
	}
	
	public List getList(CmsModelfield u){
		return cmsModelfieldDao.getList(u);
	}
	
}
