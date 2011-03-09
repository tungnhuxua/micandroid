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
 * <p> SitePartContentReal DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SitePartContentRealDao extends BaseIbatis3Dao<SitePartContentReal,java.lang.Integer>{

	public Class getEntityClass() {
		return SitePartContentReal.class;
	}
	
	public void saveOrUpdate(SitePartContentReal entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SitePartContentReal.pageSelect",pageRequest);
	}
	
	
	public List getList(SitePartContentReal u){
		return getSqlSessionTemplate().selectList("SitePartContentReal.getSitePartContentRealList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SitePartContentReal.anytablelist","SitePartContentReal.anytablecount",pageRequest);
	}
	
	/**
	 * 按栏目ID删除关联表数据
	 * @param partId 栏目ID
	 */
	public void deleteByMap(java.util.Map map) {
		getSqlSessionTemplate().delete("SitePartContentReal.deleteByMap", map);
	}
	
	/**
	 * arye
	 * 根据栏目id删除关联信息
	 */
	public void deleteByPartId(int partId){
		getSqlSessionTemplate().delete("SitePartContentReal.deleteByPartId", partId);
	}
}
