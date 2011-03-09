package cn.mmbook.platform.dao.manage;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.service.manage.impl.*;
import cn.mmbook.platform.service.manage.*;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> SiteMessage DAO层,数据映射处理<br>
 * <p>   <br>
 * @author Felix  945166129(945166129),
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SiteMessageDao extends BaseIbatis3Dao<SiteMessage,java.lang.Integer>{

	public Class getEntityClass() {
		return SiteMessage.class;
	}
	
	public void saveOrUpdate(SiteMessage entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteMessage.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteMessage u){
		return getSqlSessionTemplate().selectList("SiteMessage.getSiteMessageList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SiteMessage.anytablelist","SiteMessage.anytablecount",pageRequest);
	}
		
	
}
