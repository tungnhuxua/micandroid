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
 * @author xxj
 *
 */

@Component
public class CmsSkinsDao extends BaseIbatis3Dao<CmsSkins,java.lang.String>{

	public Class getEntityClass() {
		return CmsSkins.class;
	}
	
	public void saveOrUpdate(CmsSkins entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("CmsSkins.pageSelect",pageRequest);
	}
	
	/**修改默认网站皮肤*/
	public int updateDefult(CmsSkins u){
		 return update("CmsSkins.updateDefult",u);
	}

	/**
	 * 通过 版本ID 查询 模板URL
	 * 实现：通过 版本ID查询版本URL，再通过默认皮肤查询皮肤URL，两者组成 '模板URL'
	 * 模板版本与皮肤组成的模板URL如: web/default (版本URL+皮肤URL)
	 * @author 最爱清风
	*/
	public String getTempletPath(String versionId){
		CmsSkins info = (CmsSkins)getSqlSessionTemplate().selectOne("CmsSkins.getTempletPath", versionId);
		CmsVersion cmsVersion = info.getCmsVersion();
		String templetPath = cmsVersion.getVersionDir()+"/"+info.getSkinDir();
		return templetPath;
	}	
}
