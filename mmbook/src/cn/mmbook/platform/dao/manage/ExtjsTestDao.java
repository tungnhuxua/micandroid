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
 * <p> ExtjsTest DAO层,数据映射处理<br>
 * <p>   <br>
 * @author Felix  945166129(945166129),
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class ExtjsTestDao extends BaseIbatis3Dao<ExtjsTest,java.lang.Integer>{

	public Class getEntityClass() {
		return ExtjsTest.class;
	}
	
	public void saveOrUpdate(ExtjsTest entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("ExtjsTest.pageSelect",pageRequest);
	}
	
	
	public List getList(ExtjsTest u){
		return getSqlSessionTemplate().selectList("ExtjsTest.getExtjsTestList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("ExtjsTest.anytablelist","ExtjsTest.anytablecount",pageRequest);
	}
 
	public List getListByMap(Map map){
		return getSqlSessionTemplate().selectList("ExtjsTest.getListByMap",map);
	}	
}
