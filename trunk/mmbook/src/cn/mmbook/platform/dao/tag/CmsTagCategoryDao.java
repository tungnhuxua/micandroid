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
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author xxj
 *
 */


@Component
public class CmsTagCategoryDao extends BaseIbatis3Dao<CmsTagCategory,java.lang.String>{

	public Class getEntityClass() {
		return CmsTagCategory.class;
	}
	
	public void saveOrUpdate(CmsTagCategory entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsTagCategory.pageSelect",pageRequest);
	}
	
	/**
	 * 查询 栏目标签 列表(不分页)
	 * @author xxj
	 * @param CmsTagCategory u 
	 * @return
	 */
	public List getList(CmsTagCategory u){
		return getList("CmsTagCategory.getList",u);
	}

	/**
	 * 栏目标表(tg_cms_tag_category) 栏目(tg_cms_category)  模板(tg_cms_templet) 三表关联查询
	 * @author xxj add
	 * @param q
	 * @return
	 */
	public Page findByPageRequestNew(PageRequest pageRequest) {
		return pageQuery("CmsTagCategory.pageSelectNew","CmsTagCategory.countNew",pageRequest);
	}
	/**
	 * 按栏目名称查询栏目对象
	 * 栏目标表(tg_cms_tag_category)  
	 * @author xxj add
	 * @param tagName 栏目名称
	 * @return
	 */
	public CmsTagCategory getCmsTagCategoryByTagName(String tagName){
			return (CmsTagCategory)getSqlSessionTemplate().selectOne("CmsTagCategory.getCmsTagCategoryByTagName", tagName);
	}


}
