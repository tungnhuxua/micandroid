package cn.mmbook.platform.dao.site;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * <p> SiteContent DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SiteContentDao extends BaseIbatis3Dao<SiteContent,java.lang.String>{

	public Class getEntityClass() {
		return SiteContent.class;
	}
	
	public void saveOrUpdate(SiteContent entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteContent.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteContent u){
		return getSqlSessionTemplate().selectList("SiteContent.getSiteContentList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SiteContent.anytablelist","SiteContent.anytablecount",pageRequest);
	}
	/**
	 * 修改内容 所属分类
	 * @param Map
	 *  String sortId   内容新分类
	 *  String parentId 内容原分类
	 */
	public void updateSort(Map map){
		int count = getSqlSessionTemplate().update("SiteContent.updateSort",map);
	}
	
	public Page getListByMap(PageRequest pageRequest){
		return pageQueryMysql("SiteContent.getListByMapList","SiteContent.getListByMapCount",pageRequest);
	}
 
	/**
	 * 按内容ID查询内容主表信息
	 * @param contentId
	 * @return
	 */
	public List getSiteContentById(String contentId){
		return getSqlSessionTemplate().selectList("SiteContent.getSiteContentById",contentId);
	}
	
	/**
	 * 搜索内容
	 */
	public List sreachSiteContent(Map paraMap){
		return getSqlSessionTemplate().selectList("SiteContent.sreachSiteContent",paraMap);
	}
}
