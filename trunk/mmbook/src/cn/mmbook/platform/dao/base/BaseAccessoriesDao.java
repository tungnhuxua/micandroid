package cn.mmbook.platform.dao.base;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> BaseAccessories DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class BaseAccessoriesDao extends BaseIbatis3Dao<BaseAccessories,java.lang.String>{

	public Class getEntityClass() {
		return BaseAccessories.class;
	}
	
	public void saveOrUpdate(BaseAccessories entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("BaseAccessories.pageSelect",pageRequest);
	}
	/**
	 *  删除 数据。
	 * @param map Map 
	 * String contentId 内容ID
	 * ...
	 */
	public int deleteByMap(Map map){
		String contentId = (String)map.get("contentId");
		/**删除记录*/
		int count = getSqlSessionTemplate().delete("BaseAccessories.deleteByMap",map);
		/**删除文件(未操作 xxj )*/
		return count;
	}
	
	public List getList(BaseAccessories u){
		return getSqlSessionTemplate().selectList("BaseAccessories.getBaseAccessoriesList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("BaseAccessories.anytablelist","BaseAccessories.anytablecount",pageRequest);
	}
		
	
}
