package cn.mmbook.platform.service.site.impl;

import java.util.List;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.platform.dao.base.BaseKeycontentRealDao;
import cn.mmbook.platform.dao.site.SiteContentDao;
import cn.mmbook.platform.dao.site.SiteNovelNoteDao;
import cn.mmbook.platform.model.base.BaseKeycontentReal;
import cn.mmbook.platform.model.site.SiteContent;
import cn.mmbook.platform.model.site.SiteNovelNote;
import cn.mmbook.platform.service.site.SiteNovelNoteManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
/**
 * <p> 小说基础信息 (tb_site_novel_note_info)
 * SiteNovelNote server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("siteNovelNoteManager")
@Transactional
public class SiteNovelNoteManagerImpl extends BaseManager<SiteNovelNote,java.lang.String> 
					implements SiteNovelNoteManager {

	private SiteNovelNoteDao siteNovelNoteDao;
	private SiteContentDao siteContentDao;
	private BaseKeycontentRealDao baseKeycontentRealDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/

	public void setBaseKeycontentRealDao(BaseKeycontentRealDao dao) {
		this.baseKeycontentRealDao = dao;
	}
	public void setSiteNovelNoteDao(SiteNovelNoteDao dao) {
		this.siteNovelNoteDao = dao;
	}
	public void setSiteContentDao(SiteContentDao dao) {
		this.siteContentDao = dao;
	}
	
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteNovelNoteDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteNovelNoteDao.findByPageRequest(pr);
	}
	
	
	public List getList(SiteNovelNote u){
		return siteNovelNoteDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return siteNovelNoteDao.listPageAnyTable(pr);
	}
	
	/**自定义新增方法*/
	public void saveInfo(SiteNovelNote entity){
		SiteContent siteContent = entity.getSiteContent();
		String id = this.getMaxId("SiteContent.getMaxId");
		System.out.println("#### "+id);
		siteContent.setId(id);
		entity.setId(id);
		entity.setContentId(id);
		siteContentDao.save(siteContent);
		siteNovelNoteDao.save(entity);

		
		/**关键字关联 处理  (关键字与内容关联表tb_base_keycontent_real)*/
		if(null!=siteContent.getKeywordsValue()){ 
			  baseKeycontentRealDao.saveMoer(siteContent.getKeywordsValue(),id); 
		}
	}
	
	/** 
	 * 图片查询 
	 * */
	public SiteNovelNote getSiteNovelNoteInfo(String contentId){
		List list=siteNovelNoteDao.getSiteNovelNoteInfo(contentId);
		return list.size()>0?(SiteNovelNote)list.get(0):new SiteNovelNote();
	}
}
