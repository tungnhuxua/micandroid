package cn.mmbook.platform.dao.base;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> BaseKeywords DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class BaseKeywordsDao extends BaseIbatis3Dao<BaseKeywords,java.lang.Integer>{

	public Class getEntityClass() {
		return BaseKeywords.class;
	}
	
	public void saveOrUpdate(BaseKeywords entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("BaseKeywords.pageSelect",pageRequest);
	}
	
	
	public List getList(BaseKeywords u){
		return getSqlSessionTemplate().selectList("BaseKeywords.getBaseKeywordsList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("BaseKeywords.anytablelist","BaseKeywords.anytablecount",pageRequest);
	}
	
	public List getListByMap(Map map){
		return  getSqlSessionTemplate().selectList("BaseKeywords.getListByMap",map);
	}
	
}
