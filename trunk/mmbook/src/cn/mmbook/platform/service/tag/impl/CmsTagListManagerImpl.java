package cn.mmbook.platform.service.tag.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;
import javacommon.util.SpringContextUtil;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.platform.dao.tag.CmsTagListDao;
import cn.mmbook.platform.facade.TagService;
import cn.mmbook.platform.model.tag.CmsTagContent;
import cn.mmbook.platform.model.tag.CmsTagList;
import cn.mmbook.platform.model.tag.CmsTemplet;
import cn.mmbook.platform.service.tag.CmsSkinsManager;
import cn.mmbook.platform.service.tag.CmsTagListManager;
import cn.mmbook.platform.service.tag.CmsTempletManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;


/**
 * 内容列表标签 接口实现类
 * @author 手游戏专家  2010-06-11
 * 
 */


@Component("cmsTagListManager")
@Transactional
public class CmsTagListManagerImpl extends BaseManager<CmsTagList,java.lang.String> 
					implements CmsTagListManager {

	private CmsTagListDao cmsTagListDao;
	private CmsTempletManager cmsTempletManager;
	private CmsSkinsManager cmsSkinsManager;
	private TagService tagService;
	
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsTagListDao(CmsTagListDao dao) {
		this.cmsTagListDao = dao;
	}
	public void setCmsTempletManager(CmsTempletManager manager) {
		this.cmsTempletManager = manager;
	}
	public void setCmsSkinsManager(CmsSkinsManager manager) {
		this.cmsSkinsManager = manager;
	}
	public void setTagService(TagService manager) {
		this.tagService = manager;
	}	
	
	
	public EntityDao getEntityDao() {
		return this.cmsTagListDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest<Map> q) {
		return cmsTagListDao.findByPageRequest(q);
	}
	/**
	 * 查询 内容标签 列表(不分页)
	 * @author 手游戏专家 add
	 * @param CmsTagList u 
	 * @return
	 */
	public List getList(CmsTagList u){
		return cmsTagListDao.getList(u);
	}
	
	public CmsTagList getCmsTagList(CmsTagList u){
		return cmsTagListDao.getCmsTagList(u);
	}
	
	public void update(CmsTagList entity) {
		super.update(entity);
		CmsTemplet cmsTemplet = cmsTempletManager.getById(entity.getTempletId());
		entity.setCmsTemplet(cmsTemplet);
		entity.setTagListInfo(getTagInfoList(entity));
		String templetPath = cmsSkinsManager.getTempletPath(entity.getVersionId());
		cmsTemplet.setTempletPath(templetPath);
		DaoGenerator daoGenerator = (DaoGenerator)SpringContextUtil.getBean("contentlist"); 
		/**生成标签*/
		daoGenerator.init(entity);
		daoGenerator.generate(); 
	}
	
	public void save(CmsTagList entity) {
		 String id = getEntityDao().getMaxId("CmsTagList.getMaxId");
		 entity.setId(id);
		 super.save(entity);
			CmsTemplet cmsTemplet = cmsTempletManager.getById(entity.getTempletId());
			entity.setCmsTemplet(cmsTemplet);
			entity.setTagListInfo(getTagInfoList(entity));
			String templetPath = cmsSkinsManager.getTempletPath(entity.getVersionId());
			cmsTemplet.setTempletPath(templetPath);
			DaoGenerator daoGenerator = (DaoGenerator)SpringContextUtil.getBean("contentlist"); 
			/**生成标签*/
			daoGenerator.init(entity);
			daoGenerator.generate(); 
	}
 
	public CmsTagContent getCmsTagContent(CmsTagList u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 通过分类ID查询内容列表
	 * @param entity
	 * @return
	 */
	private List getTagInfoList(CmsTagList entity){
		Map map = new HashMap();
		Page page = tagService.getSiteContentAllList( map);
		List list_SiteContent = new ArrayList();
		if(null!=page){
			list_SiteContent =  (List) page.getResult();
			return list_SiteContent;
		}else{
		    return null;
		}
	}
	
}
