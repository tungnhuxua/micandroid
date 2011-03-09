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

//import com.mylink.kingtercms.system.TagServer;
//import com.mylink.kingtercms.system.TempletServer;
import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 
 * @author 手游戏专家
 *
 */


@Component("cmsTempletManager")
@Transactional
public class CmsTempletManagerImpl extends BaseManager<CmsTemplet,java.lang.String> 
					implements CmsTempletManager {

	private CmsTempletDao cmsTempletDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsTempletDao(CmsTempletDao dao) {
		this.cmsTempletDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cmsTempletDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsTempletDao.findByPageRequest(pr);
	}
	
	public List getList(CmsTemplet u){
		return cmsTempletDao.getList(u);
	}
	
	public void update(CmsTemplet entity) {
		super.update(entity);
//		TempletServer templetServer = (TempletServer)SpringContextUtil.getBean("templetServer");
//		templetServer.makeTemplet(entity.getId());
	}
	
	public void save(CmsTemplet entity) {
		 String id = getEntityDao().getMaxId("CmsTemplet.getMaxId");
		 entity.setId(id);
		 super.save(entity);
//		TempletServer templetServer = (TempletServer)SpringContextUtil.getBean("templetServer");
//		templetServer.makeTemplet(entity.getId());
	}
	
}
