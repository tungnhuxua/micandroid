package cn.mmbook.platform.service.tag;

import java.util.List;
import java.util.Map;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.*;


/**
 * 
 * @author Administrator
 *
 */


public interface CmsModelfieldManager {
	
	CmsModelfield getById(java.lang.String id);
	
	void save(CmsModelfield u);
	
	void update(CmsModelfield u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	
	List getList(CmsModelfield u);

}
