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
import org.springframework.stereotype.Component;


/**
 * 
 * @author xxj
 *
 */


@Component
public class CmsTagListDao extends BaseIbatis3Dao<CmsTagList,java.lang.String>{

	public Class getEntityClass() {
		return CmsTagList.class;
	}
	
	public void saveOrUpdate(CmsTagList entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsTagList.pageSelect",pageRequest);
	}
	
	/**
	 * 查询 内容标签 列表(不分页)
	 * @author xxj add
	 * @param CmsTagContent u 
	 * @return
	 */
	public List getList(CmsTagList u){
		return getList("CmsTagList.getList",u);
	}
	
	public CmsTagList getCmsTagList(CmsTagList u){
		return (CmsTagList) getSqlSessionTemplate().selectOne(
				"CmsTagList.getList", u);
	}
}
