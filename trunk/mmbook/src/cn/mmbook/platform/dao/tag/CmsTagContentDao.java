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
public class CmsTagContentDao extends BaseIbatis3Dao<CmsTagContent,java.lang.String>{

	public Class getEntityClass() {
		return CmsTagContent.class;
	}
	
	public void saveOrUpdate(CmsTagContent entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsTagContent.pageSelect",pageRequest);
	}
	
	/**
	 * 查询 内容标签 列表(不分页)
	 * @author xxj add
	 * @param CmsTagContent u 
	 * @return
	 */
	public List getList(CmsTagContent u){
		return getList("CmsTagContent.getList",u);
	}
	
	public CmsTagContent getCmsTagContent(CmsTagContent u){
		return (CmsTagContent) getSqlSessionTemplate().selectOne(
				"CmsTagContent.getList", u);
	}
}
