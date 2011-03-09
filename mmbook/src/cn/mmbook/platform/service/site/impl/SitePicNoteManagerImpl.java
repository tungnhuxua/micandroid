package cn.mmbook.platform.service.site.impl;

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

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;
/**
 * <p> SitePicNote server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("sitePicNoteManager")
@Transactional
public class SitePicNoteManagerImpl extends BaseManager<SitePicNote,java.lang.String> 
					implements SitePicNoteManager {

	private SitePicNoteDao sitePicNoteDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSitePicNoteDao(SitePicNoteDao dao) {
		this.sitePicNoteDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.sitePicNoteDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return sitePicNoteDao.findByPageRequest(pr);
	}
	
	
	public List getList(SitePicNote u){
		return sitePicNoteDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return sitePicNoteDao.listPageAnyTable(pr);
	}
	
	/**
	 * 图片查询
	 */
	public SitePicNote getSitePicNoteInfo(String contentId){
		List list=sitePicNoteDao.getSitePicNoteInfo(contentId);
		return list.size()<=0?new SitePicNote():(SitePicNote)list.get(0);
	}
}
