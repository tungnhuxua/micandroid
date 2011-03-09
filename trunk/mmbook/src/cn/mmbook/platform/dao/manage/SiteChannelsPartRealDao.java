package cn.mmbook.platform.dao.manage;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.service.manage.impl.*;
import cn.mmbook.platform.service.manage.*;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> SiteChannelsPartReal DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SiteChannelsPartRealDao extends BaseIbatis3Dao<SiteChannelsPartReal,java.lang.Integer>{

	public Class getEntityClass() {
		return SiteChannelsPartReal.class;
	}
	
	public void saveOrUpdate(SiteChannelsPartReal entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteChannelsPartReal.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteChannelsPartReal u){
		return getSqlSessionTemplate().selectList("SiteChannelsPartReal.getSiteChannelsPartRealList",u);
	}
	public List getList(Map map){
		return getSqlSessionTemplate().selectList("SiteChannelsPartReal.getSiteChannelsPartRealList",map);
	}	
	/**
	 * 删除关联表数据
	 * @author Stilfler  673211682 add
	 */
	public void removeByInfo (SiteChannelsPartReal u){
		int count = removeByInfo("SiteChannelsPartReal.removeByInfo",  u) ;
		System.out.println("public void removeByInfo (SiteChannelsPartReal u){:"+count);
	}
	
}
