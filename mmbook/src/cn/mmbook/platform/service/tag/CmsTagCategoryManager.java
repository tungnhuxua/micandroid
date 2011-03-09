package cn.mmbook.platform.service.tag;



import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
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


/**
 *  栏目标签
 * @author 手游戏专家
 */

public interface CmsTagCategoryManager {
	
	CmsTagCategory getById(java.lang.String id);
	
	void save(CmsTagCategory u);
	
	void update(CmsTagCategory u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	
	/**
	 * 查询 栏目标签 列表(不分页)
	 * @author 手游戏专家 add
	 * @param CmsTagCategory u 
	 * @return
	 */
	List getList(CmsTagCategory u);
	
	/**
	 * 栏目标表(tg_cms_tag_category) 栏目(tg_cms_category)  模板(tg_cms_templet) 三表关联查询
	 * @author 手游戏专家 add
	 * @param q
	 * @return
	 */
	Page findByPageRequestNew(PageRequest<Map> q);

	/**
	 * 按栏目名称查询栏目对象
	 * 栏目标表(tg_cms_tag_category)  
	 * @author 手游戏专家 add
	 * @param tagName 栏目名称
	 * @return
	 */
	CmsTagCategory getCmsTagCategoryByTagName(String tagname);


	void saveCmsTagCategory(CmsTagCategory u);
	
	void updateCmsTagCategory(CmsTagCategory u);	
	
}
