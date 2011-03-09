package cn.mmbook.platform.dao.tag;

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
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> TagModel DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class TagModelDao extends BaseIbatis3Dao<TagModel,java.lang.String>{

	public Class getEntityClass() {
		return TagModel.class;
	}
	
	public void saveOrUpdate(TagModel entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("TagModel.pageSelect",pageRequest);
	}
	
	
	public List getList(TagModel u){
		return getSqlSessionTemplate().selectList("TagModel.getTagModelList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("TagModel.anytablelist","TagModel.anytablecount",pageRequest);
	}
		
	
}
