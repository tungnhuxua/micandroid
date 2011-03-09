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


@Component
public class CmsModelfieldDao extends BaseIbatis3Dao<CmsModelfield,java.lang.String>{

	public Class getEntityClass() {
		return CmsModelfield.class;
	}
	
	public void saveOrUpdate(CmsModelfield entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsModelfield.pageSelect",pageRequest);
	}
	
	public List getList(CmsModelfield u){
		return getList("CmsModelfield.getList",u);
	}

}
