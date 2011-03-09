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
 * <p> 网站内容分类tb_site_content_ sort_info(SiteContentSort) DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */


@Component
public class SiteContentSortDao extends BaseIbatis3Dao<SiteContentSort,java.lang.Integer>{

	public Class getEntityClass() {
		return SiteContentSort.class;
	}
	
	public void saveOrUpdate(SiteContentSort entity) {
		if( 0!=entity.getId() ) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SiteContentSort.pageSelect",pageRequest);
	}
	
	
	public List getList(SiteContentSort u){
		return getSqlSessionTemplate().selectList("SiteContentSort.getSiteContentSortList",u);
	}
	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SiteContentSort.anytablelist","SiteContentSort.anytablecount",pageRequest);
	}
		
	public List getListByMap(Map map){
		return  getSqlSessionTemplate().selectList("SiteContentSort.getListByMap",map);
	}
	
	/**
	 * arye
	 * 根据站点内容名查询id
	 */
	public int getSiteContentSortIdByName(String siteContentSortName){
		List list=getSqlSessionTemplate().selectList("SiteContentSort.getSiteContentSortIdByName",siteContentSortName);
		if(null !=list && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * 按ID查询顶级ID
	 * @param id
	 * @return
	 */
	public java.lang.Integer getTopId(int id){
		int topid = 0;
		for(int i=0;i<100;i++){
		   SiteContentSort info = (SiteContentSort)getSqlSessionTemplate().selectOne("SiteContentSort.getTopId",id);
		   if(null!=info){
		       id = info.getParentId();
		   }else{
			   break;
		   }
		   if(null!=info&&info.getParentId()==0){
			   topid = info.getId();
			   break;
		   }
		}
		return topid;
	}
	
	public Page getSoftByPartId(PageRequest pageRequest){
		return pageQueryMysql("SiteContentSort.getSoftByPartIdlist","SiteContentSort.getSoftByPartIdcount",pageRequest);
	}
	
	/**
	 * 按网站内容分类ID 查询 分类信息
	 * @param SortId 网站内容分类ID
	 * @return
	 */
	public List getSiteContentSortById(String sortId){
		return getSqlSessionTemplate().selectList("SiteContentSort.getSiteContentSortById",sortId);
	}
	
	/** * 按网站分类ID查询它所有的子类(下级)信息列表(返回网站分类表数据信息) */
	public List getSortBySortID(Map paraMap){
		return getSqlSessionTemplate().selectList("SiteContentSort.getSortBySortID",paraMap);
	}
}
