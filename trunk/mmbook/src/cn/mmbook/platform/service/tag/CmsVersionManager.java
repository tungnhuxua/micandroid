package cn.mmbook.platform.service.tag;

import java.util.Map;
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


public interface CmsVersionManager {
	
	CmsVersion getById(java.lang.String id);
	
	void save(CmsVersion u);
	
	void update(CmsVersion u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	
	/**查询 模板版本 下拉框数据*/
	List getCombox();
}
