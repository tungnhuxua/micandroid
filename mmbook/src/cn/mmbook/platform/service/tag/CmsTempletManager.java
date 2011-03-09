package cn.mmbook.platform.service.tag;

import java.util.List;
import java.util.Map;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.*;


/**
 * 
 * @author 手游戏专家
 *
 */

public interface CmsTempletManager {
	
	CmsTemplet getById(java.lang.String id);
	
	void save(CmsTemplet u);
	
	void update(CmsTemplet u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	
	List getList(CmsTemplet u);

}
