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
 * <p> SitePart DAO层,数据映射处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component
public class SitePartDao extends BaseIbatis3Dao<SitePart,java.lang.Integer>{

	public Class getEntityClass() {
		return SitePart.class;
	}
	
	public void saveOrUpdate(SitePart entity) {
		if(entity.getId() == 0) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findByPageRequest(PageRequest pageRequest) {
		return pageQuery("SitePart.pageSelect",pageRequest);
	}
	
	
	public List getList(SitePart u){
		return getSqlSessionTemplate().selectList("SitePart.getSitePartList",u);
	}
	public List getList(Map map){
		return getSqlSessionTemplate().selectList("SitePart.getSitePartList",map);
	}	
	/**
	 * 
	 * 要自己写出 查询关联表，及记录数 
	 * @param pageRequest
	 * @return
	 */
	public Page listPageAnyTable(PageRequest pageRequest) {
		return pageQuery("SitePart.anytablelist","SitePart.anytablecount",pageRequest);
	}
		
	public List getListByMap(Map map){
		System.out.println("public List getListByMap(Map map){");
		return getSqlSessionTemplate().selectList("SitePart.getListByMap",map);
	}
	
	/**
	 * arye
	 * 判断栏目名是否存在
	 */
	public boolean checkSitePartNameExit(String sitePartName){
		List list=getSqlSessionTemplate().selectList("SitePart.checkSitePartNameExit", sitePartName);
		int partFlag=0;
		if(null != list)
			partFlag=Integer.parseInt(list.get(0).toString());
		if(partFlag>0)
			return true;
		return false;
	}
	
	/**
	 * arye
	 * 根据栏目名查询栏目id
	 */
	public int getSitePartIdByName(String sitePartName){
		List list=getSqlSessionTemplate().selectList("SitePart.getSitePartIdByName", sitePartName);
		if(null != list && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * arye
	 * 根据id查询栏目
	 */
	public SitePart getSitePartById(int id){
		List list=getSqlSessionTemplate().selectList("SitePart.getSitePartById", id);
		if(null!=list && list.size()>0){
			return (SitePart)list.get(0);
		}
		return null;
	}
	
	/**
	 * arye
	 * 查询最后添加的数据的id
	 */
	public int getLastId(){
		List list=getSqlSessionTemplate().selectList("SitePart.getLastId",null);
		if(null != list && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * arye
	 * 判断是否还存在下级节点(true：存在 false：不存在)
	 */
	public boolean checkLowerNode(int parentId){
		List list=getSqlSessionTemplate().selectList("SitePart.checkLowerNode",parentId);
		int childCount=0;
		if(null != list && list.size()>0){
			childCount = Integer.parseInt(list.get(0).toString());
		}
		
		if(childCount>0)
			return true;
		return false;
	}
	
	/**
	 * arye
	 * 根据parent_id查询id_
	 * @param parentId(父栏目id)
	 * @return(栏目id)
	 */
	public int getPartIdByParentId(int parentId){
		List list=getSqlSessionTemplate().selectList("SitePart.getPartIdByParentId",parentId);
		if(null != list && list.size()>0){
			return Integer.parseInt(list.get(0).toString());
		}
		return -1;
	}
	
	/**
	 * 根据parent_id查询
	 */
	public List<SitePart> getSitePartByParentId(int parentId){
		List<SitePart> list=new ArrayList<SitePart>();
		list=getSqlSessionTemplate().selectList("SitePart.getSitePartByParentId",parentId);
		return list;
	}
	/**
	 * 根据parent_id查询
	 */
	public List getTagSitePartList(Map map){
		return getSqlSessionTemplate().selectList("SitePart.getTagSitePartList",map);
	}	
	
	/**
	 * 查询一级栏目列表数据
	 * @param pageRequest
	 * @return
	 */
	public Page getSitePateByChannelId(PageRequest pageRequest){
		return pageQueryMysql("SiteChannels.getSitePageByChannelIdlist","SiteChannels.getSitePartByChannelIdcount",pageRequest);
	}
	
	/**按栏目ID查询 下级栏目*/
	public List getSitePartListById(Map paraMap){
		return getSqlSessionTemplate().selectList("SitePart.getSitePartListById",paraMap);
	}
}
