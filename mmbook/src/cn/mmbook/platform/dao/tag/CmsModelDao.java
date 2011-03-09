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
 * @author Administrator
 *
 */


@Component
public class CmsModelDao extends BaseIbatis3Dao<CmsModel,java.lang.String>{

	public Class getEntityClass() {
		return CmsModel.class;
	}
	
	public void saveOrUpdate(CmsModel entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsModel.pageSelect",pageRequest);
	}

	/**
	 * 得到内容类型下拉列表
	 * @param cmsModel：做条件查询用
	 */
	public List getCmsModelList(CmsModel cmsModel) {
		return getList("CmsModel.pageSelect",cmsModel);
	}
	

}
