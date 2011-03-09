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
public class CmsTempletDao extends BaseIbatis3Dao<CmsTemplet,java.lang.String>{

	public Class getEntityClass() {
		return CmsTemplet.class;
	}
	
	public void saveOrUpdate(CmsTemplet entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsTemplet.pageSelect",pageRequest);
	}
	
	public List getList(CmsTemplet u){
		return getList("CmsTemplet.getList",u);
	}

}
