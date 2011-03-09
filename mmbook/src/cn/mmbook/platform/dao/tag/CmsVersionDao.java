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
 * @author 自强  zqiangzhang@gmail.com
 *
 */


@Component
public class CmsVersionDao extends BaseIbatis3Dao<CmsVersion,java.lang.String>{

	public Class getEntityClass() {
		return CmsVersion.class;
	}
	
	public void saveOrUpdate(CmsVersion entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsVersion.pageSelect",pageRequest);
	}
	
	/**查询 模板版本 下拉框数据 zqiangzhang */
	public List getCombox(){
		return getList("CmsVersion.getList",null);
	}
}
