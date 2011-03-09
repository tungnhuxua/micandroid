package cn.mmbook.platform.service.tag.impl;

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
 * 
 * @author 手游戏专家
 *
 */

@Component("cmsVersionManager")
@Transactional
public class CmsVersionManagerImpl extends BaseManager<CmsVersion,java.lang.String> 
					implements CmsVersionManager {

	private CmsVersionDao cmsVersionDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsVersionDao(CmsVersionDao dao) {
		this.cmsVersionDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cmsVersionDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsVersionDao.findByPageRequest(pr);
	}
	/**查询 模板版本 下拉框数据 手游戏专家*/
	public List getCombox(){
		return cmsVersionDao.getCombox();
	}
}
