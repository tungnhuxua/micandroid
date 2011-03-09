package cn.mmbook.platform.service.site.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
/**
 * <p> SiteContent server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("siteContentManager")
@Transactional
public class SiteContentManagerImpl extends BaseManager<SiteContent,java.lang.String> 
					implements SiteContentManager {

	private SiteContentDao siteContentDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSiteContentDao(SiteContentDao dao) {
		this.siteContentDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteContentDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteContentDao.findByPageRequest(pr);
	}
	
	
	public List getList(SiteContent u){
		return siteContentDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return siteContentDao.listPageAnyTable(pr);
	}
	
	/**
	 * 修改内容信息
	 */
	public void updateSort(Map map){
		siteContentDao.updateSort(map);
	}
	
	/**
	 * 按内容ID查询内容主表信息
	 * @param contentId
	 * @return
	 */
	public SiteContent getSiteContentById(String contentId){
		List list = siteContentDao.getSiteContentById(contentId);
		return list.size()>0?(SiteContent)list.get(0):new SiteContent();		
	}
	
	public Page getListByMap(Map map){
		PageRequest pageRequest = new PageRequest(); 
		Map map_param = new HashMap();
		String SortId = (String) map.get("SortId");
		if (javacommon.util.StringTool.isNull(SortId)) {
			map_param.put("sortId", SortId);
			pageRequest.setFilters(map_param);
		}
		String count = (String) map.get("count");
		int limit = 10;
		if (javacommon.util.StringTool.isNull(count)) {
			limit = Integer.parseInt(count);
		}
		String start = (String) map.get("start");
		if (javacommon.util.StringTool.isNull(start)) {
			if (limit == 0) {
				pageRequest.setPageNumber(1);
			} else {
				int pageIndex = Integer.parseInt(start);
				pageRequest.setPageNumber(pageIndex / limit + 1);
			}
		} else {
			pageRequest.setPageNumber(1);
		}

		pageRequest.setPageSize(limit);
		String sort = (String) map.get("sort");
		if (javacommon.util.StringTool.isNull(sort)) {
			pageRequest.setSortColumns(sort);
		}

		Page page = siteContentDao.getListByMap(pageRequest);
//		System.out.println("page.getPageSize()=" + page.getPageSize());
//		List listChannels = (List) page.getResult();
//		System.out.println("listChannels.size()=" + listChannels.size());
		
		return page; 
	}
	
	
	/**
	* 搜索内容
	* */
	public List sreachSiteContent(Map map){
		Map paraMap=new HashMap();
		int pageSize=10;
		int nowPage=1;
		
		String search=(String)map.get("sreach");
		String start=(String)map.get("start");
		String count=(String)map.get("count");
		
		if(null==search || "".equalsIgnoreCase(search)){
			paraMap.put("sreach", "");
		}
		else{
			paraMap.put("sreach", search);
		}
		
		if(null==start || "".equalsIgnoreCase(start)){
			paraMap.put("start", "");
		}
		else{
			paraMap.put("start", start);
			nowPage=Integer.parseInt(start);
		}
		
		if(null==count || "".equalsIgnoreCase(count)){
			paraMap.put("count", "");
			pageSize=10;//默认每页显示1条数据
		}
		else{
			pageSize=Integer.parseInt(count);
			paraMap.put("count", count);
			paraMap.put("begin", ((nowPage-1)*pageSize));
			paraMap.put("end", (nowPage*pageSize));
		}
		
		return siteContentDao.sreachSiteContent(paraMap);
	}
}
