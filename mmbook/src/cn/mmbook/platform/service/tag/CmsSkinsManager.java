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


public interface CmsSkinsManager {
	
	CmsSkins getById(java.lang.String id);
	
	void save(CmsSkins u);
	
	void update(CmsSkins u);
	
	void removeById (java.lang.String id);
	
	Page findByPageRequest(PageRequest<Map> q);
	/**修改默认网站皮肤*/
	int updateDefult(CmsSkins u);
	
	/**通过 版本ID 查询 模板URL*/
	String getTempletPath(String versionId);
	
}
