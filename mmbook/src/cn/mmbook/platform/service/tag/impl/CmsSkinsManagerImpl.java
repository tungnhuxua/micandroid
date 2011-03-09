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
 * @author 自强  zqiangzhang@gmail.com
 *
 */


@Component("cmsSkinsManager")
@Transactional
public class CmsSkinsManagerImpl extends BaseManager<CmsSkins,java.lang.String> 
					implements CmsSkinsManager {

	private CmsSkinsDao cmsSkinsDao;
	private CmsVersionManager cmsVersionManager;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setCmsSkinsDao(CmsSkinsDao dao) {
		this.cmsSkinsDao = dao;
	}
	public void setCmsVersionManager(CmsVersionManager manager) {
		this.cmsVersionManager = manager;
	}
	
	
	public EntityDao getEntityDao() {
		return this.cmsSkinsDao;
	}
	
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return cmsSkinsDao.findByPageRequest(pr);
	}
	/**修改默认网站皮肤*/
	public int updateDefult(CmsSkins u){
		return cmsSkinsDao.updateDefult(u);
	}
	public void save(CmsSkins u){
		 String id = getEntityDao().getMaxId("CmsSkins.getMaxId");
		 u.setId(id);
		 cmsSkinsDao.save(u);
	}
	

	/**
	 * 通过 版本ID 查询 模板URL
	 * 实现：通过 版本ID查询版本URL，再通过默认皮肤查询皮肤URL，两者组成 '模板URL'
	 * 模板版本与皮肤组成的模板URL如: web/default (版本URL+皮肤URL)
	 * @author 最爱清风
	*/
	public String getTempletPath(String versionId){
		return (String)cmsSkinsDao.getTempletPath(versionId);
	}
	
}
