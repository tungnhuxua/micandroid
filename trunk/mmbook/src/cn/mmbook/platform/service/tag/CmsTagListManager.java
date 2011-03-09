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
 * 
 * @author 手游戏专家
 *
 */


public interface CmsTagListManager {
	
	CmsTagList getById(java.lang.String id);
	
	CmsTagList getCmsTagList(CmsTagList u);
	
	void save(CmsTagList u);
	
	void update(CmsTagList u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	/**
	 * 查询 内容标签 列表(不分页)
	 * @author 手游戏专家 add
	 * @param CmsTagList u 
	 * @return
	 */
	List getList(CmsTagList u);
}
