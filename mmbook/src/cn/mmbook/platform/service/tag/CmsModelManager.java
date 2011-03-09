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
 * @author Administrator
 *
 */


public interface CmsModelManager {
	
	CmsModel getById(java.lang.String id);
	
	void save(CmsModel u);
	
	void update(CmsModel u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);

	/**
	 * see cmsModelDao.getcmsModelList(CmsModel cmsModel)
	 */
	List getCmsModelList(CmsModel cmsModel);

}
