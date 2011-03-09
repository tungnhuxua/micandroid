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
 * <p> SiteSubject DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SiteSubjectDao extends BaseIbatis3Dao<SiteSubject,java.lang.Integer>{

	public Class getEntityClass() {
		return SiteSubject.class;
	}
	
	public void saveOrUpdate(SiteSubject entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteSubject.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteSubject u){
		return getSqlSessionTemplate().selectList("SiteSubject.getSiteSubjectList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SiteSubject.anytablelist","SiteSubject.anytablecount",pageRequest);
	}
		
	
}
