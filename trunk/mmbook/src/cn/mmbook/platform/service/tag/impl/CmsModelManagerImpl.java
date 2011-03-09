package cn.mmbook.platform.service.tag.impl;

import java.util.List;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import cn.mmbook.platform.dao.tag.CmsModelDao;
import cn.mmbook.platform.model.tag.CmsModel;
import cn.mmbook.platform.service.tag.CmsModelManager;


/**
 * 
 * @author Administrator
 *
 */


@Component("cmsModelManager")
@Transactional
public class CmsModelManagerImpl extends BaseManager<CmsModel,java.lang.String> 
					implements CmsModelManager {

	private CmsModelDao cmsModelDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsModelDao(CmsModelDao dao) {
		this.cmsModelDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cmsModelDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsModelDao.findByPageRequest(pr);
	}

	public List getCmsModelList(CmsModel cmsModel) {
		return cmsModelDao.getCmsModelList(cmsModel);
	}
	
	
	
	
	
}
