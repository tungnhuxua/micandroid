package cn.mmbook.platform.service.tag.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javacommon.base.*;
import javacommon.util.SpringContextUtil;

import cn.org.rapid_framework.page.*;

//import com.mylink.kingtercms.system.TagServer;
import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 
 * @author 手游戏专家  
 *
 */


@Component("cmsTagContentManager")
@Transactional
public class CmsTagContentManagerImpl extends BaseManager<CmsTagContent,java.lang.String> 
					implements CmsTagContentManager {

	private CmsTagContentDao cmsTagContentDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsTagContentDao(CmsTagContentDao dao) {
		this.cmsTagContentDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.cmsTagContentDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsTagContentDao.findByPageRequest(pr);
	}
	/**
	 * 查询 内容标签 列表(不分页)
	 * @author 手游戏专家 add
	 * @param CmsTagContent u 
	 * @return
	 */
	public List getList(CmsTagContent u){
		return cmsTagContentDao.getList(u);
	}
	
	public CmsTagContent getCmsTagContent(CmsTagContent u){
		return cmsTagContentDao.getCmsTagContent(u);
	}
	
	public void update(CmsTagContent entity) {
		super.update(entity);
//		TagServer tagServer = (TagServer)SpringContextUtil.getBean("contentTag");
//		tagServer.createTag(entity);
	}
	
	public void save(CmsTagContent entity) {
		super.save(entity);
//		TagServer tagServer = (TagServer)SpringContextUtil.getBean("contentTag");
//		tagServer.createTag(entity);
	}
}
