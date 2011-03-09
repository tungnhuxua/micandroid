package cn.mmbook.platform.dao.site;

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
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> SiteNovelContent DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SiteNovelContentDao extends BaseIbatis3Dao<SiteNovelContent,java.lang.Integer>{

	public Class getEntityClass() {
		return SiteNovelContent.class;
	}
	
	public void saveOrUpdate(SiteNovelContent entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteNovelContent.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteNovelContent u){
		return getSqlSessionTemplate().selectList("SiteNovelContent.getSiteNovelContentList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SiteNovelContent.anytablelist","SiteNovelContent.anytablecount",pageRequest);
	}
		
	
}
