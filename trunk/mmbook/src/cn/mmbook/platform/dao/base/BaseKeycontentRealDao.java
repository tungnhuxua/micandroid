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
 * <p> BaseKeycontentReal DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class BaseKeycontentRealDao extends BaseIbatis3Dao<BaseKeycontentReal,java.lang.Integer>{

	public Class getEntityClass() {
		return BaseKeycontentReal.class;
	}
	
	public void saveOrUpdate(BaseKeycontentReal entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("BaseKeycontentReal.pageSelect",pageRequest);
	}
	/**
	 *  删除 内容与关键字联数据。
	 * @param map Map 
	 * String contentId 内容ID
	 * String keywordsId 关键字ID
	 */
	public int deleteByMap(Map map){
		return getSqlSessionTemplate().delete("BaseKeycontentReal.deleteByMap",map);
	}
	/**
	 * 保存 多个内容与关键字关联数据
	 * @param keyStr 关联字串
	 * @param contentId 内容ID
	 */
	public void saveMoer(String keyStr,String contentId) {
		if(null!=keyStr&&keyStr.length()>0){
		 String[] keywordArray = keyStr.split(",");
		 for(int i=0;null!=keywordArray&&i<keywordArray.length;i++){		//插入关键字关联表
			BaseKeycontentReal baseKeycontentReal = new BaseKeycontentReal();
			baseKeycontentReal.setContentId(contentId);
			if(null!=keywordArray[i]&&keywordArray[i].length()>0){
			  baseKeycontentReal.setKeywordsId(Integer.parseInt(keywordArray[i]));
			  save(baseKeycontentReal);
			}
		 }
		}
	}	
	
	public List getList(BaseKeycontentReal u){
		return getSqlSessionTemplate().selectList("BaseKeycontentReal.getBaseKeycontentRealList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("BaseKeycontentReal.anytablelist","BaseKeycontentReal.anytablecount",pageRequest);
	}
		
	public List getListByMap(Map map){
		return  getSqlSessionTemplate().selectList("BaseKeycontentReal.getListByMap",map);
	}
}
