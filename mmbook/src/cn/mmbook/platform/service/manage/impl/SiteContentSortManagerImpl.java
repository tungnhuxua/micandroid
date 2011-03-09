package cn.mmbook.platform.service.manage.impl;


import java.util.*;

import javacommon.base.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.service.manage.*;
import cn.mmbook.platform.service.site.*;
import cn.org.rapid_framework.page.*;


/**
 * <p> SiteContentSort server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */


@Component("siteContentSortManager")
@Transactional
public class SiteContentSortManagerImpl extends BaseManager<SiteContentSort,java.lang.Integer> 
					implements SiteContentSortManager {

	private SiteContentSortDao siteContentSortDao;
	private SitePartContentRealDao sitePartContentRealDao;
	private SiteContentManager siteContentManager;
	private SitePartContentRealManager sitePartContentRealManager;
	
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSiteContentSortDao(SiteContentSortDao dao) {
		this.siteContentSortDao = dao;
	}
	public void setSitePartContentRealDao(SitePartContentRealDao dao) {
		this.sitePartContentRealDao = dao;
	}
	public void setSiteContentManager(SiteContentManager namager) {
		this.siteContentManager = namager;
	}	
	public void setSitePartContentRealManager(SitePartContentRealManager namager) {
		this.sitePartContentRealManager = namager;
	}
	
	
	
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteContentSortDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteContentSortDao.findByPageRequest(pr);
	}
	
	
	public List getList(SiteContentSort u){
		return siteContentSortDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return siteContentSortDao.listPageAnyTable(pr);
	}

	public List getListByMap(Map map){
		return siteContentSortDao.getListByMap(map);
	}
	
	/**
	 * 
	 * 得到树下拉框 qiongguo
	 * 
	 */
	public String getTreeCombox(SiteContentSort cmsc , StringBuffer sb, int j,String partId) {
		List list = new ArrayList();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", cmsc.getId());
//		map.put("cmscategoryType", cmsc.getCategoryType());
//		map.put("modelId", cmsc.getModelId());
		
		/**需要选中栏目*/
		String[] cidarr = null; 
		if (null!=partId&&!"".equals(partId)) {
			cidarr = partId.split(",");
		}
		
		list = siteContentSortDao.getListByMap(map);
		if (null != list && list.size() > 0) {
			if (j != 0) {
				sb.append(",'children':[");
			}
			j++;
			for (int i = 0; i < list.size(); i++) {
				SiteContentSort t = (SiteContentSort)list.get(i);
				boolean bool = false;

				for(int mm=0;null!=cidarr&&mm<cidarr.length;mm++){
					if((String.valueOf(t.getId())).equals(cidarr[mm])){
						bool = true;
					}
				}
				if(bool){
				   sb.append("{checked:true,'id':'").append(t.getId()).append("','text':'").append(t.getClassifyName() + "'");
				}else{
					sb.append("{checked:false,'id':'").append(t.getId()).append("','text':'").append(t.getClassifyName() + "'");
				}
				this.getTreeCombox(t, sb, j,partId);
				if (i + 1 < list.size()) {
					sb.append(",");
				}
			}
			sb.append("],'leaf':false}");
		} else {
			sb.append(",'leaf':true}");
		}
		return sb.toString();
	}
	
	/**
	 * 查询 栏目列表数据
	 * @param map
	 *   String sort 排序字符串
	 *   String sitePartId 频道ID
	 *   String page 1 分页 2 不分页
	 *   String start 当前页码
	 *   String count 返回记录数
	 * @return Map 
	 *    String size 符合条件记录总数
	 *    List<SiteContentSort> 结果集
	 */
	public Page getSoftByPartId(Map map){
		PageRequest pageRequest = new PageRequest();
		
		Map map_param = new HashMap();
		String sitePartId = (String)map.get("sitePartId");
		if(javacommon.util.StringTool.isNull(sitePartId)){
			map_param.put("sitePartId",sitePartId );
			pageRequest.setFilters(map_param);
		}
		String count = (String)map.get("count");
		int limit=10;
		if(javacommon.util.StringTool.isNull(count)){
			limit = Integer.parseInt(count);
		}
		String start = (String)map.get("start");
		if(javacommon.util.StringTool.isNull(start)){
			if(limit==0){
				pageRequest.setPageNumber(1);
			}else{
			  int pageIndex = Integer.parseInt(start);
			  pageRequest.setPageNumber(pageIndex / limit + 1);
			}
		}else{
			pageRequest.setPageNumber(1);
		}
		
		pageRequest.setPageSize(limit);
		String sort = (String)map.get("sort");
		if(javacommon.util.StringTool.isNull(sort)){
			pageRequest.setSortColumns(sort);
		}
		
		Page page = siteContentSortDao.getSoftByPartId(pageRequest);
		System.out.println("page.getPageSize()="+page.getPageSize());
		List listxx = (List)page.getResult();
		System.out.println("listxx.size()="+listxx.size());
		return page;
	}
	/**
	 * 自定义保存网站分类数据
	 * 1:保存
	 * 2:保存与栏目关联数据。
	 * 3:如果它选择了上级节点。则修改它上级节点属性(难点)
	 * 
	 */
	public void save(SiteContentSort u){
		
		int parentId = u.getParentId();
		/**选择了上级节点*/
		if(parentId!=0){
			SiteContentSort parentSort = (SiteContentSort)siteContentSortDao.getById(parentId);
			int classify_grade_ = parentSort.getClassifyGrade();
			/**修改等级*/
			u.setClassifyGrade(classify_grade_+1);
			/**修改它上级节点属性*/
			parentSort.setLowerNode(1);
			siteContentSortDao.update(parentSort);
		}
		u.setLowerNode(2);
		siteContentSortDao.save(u);
		String sortId = getEntityDao().getMaxId("SiteContentSort.getMaxId");
		if(parentId!=0){
			/**修改内容表内容对应分类ID*/
			Map map_content = new HashMap();
			map_content.put("sortId", sortId); //    内容新分类
			map_content.put("parentId", parentId);//  内容原分类
			siteContentManager.updateSort(map_content);
		}
		/**保存网站内容分类与栏目关联数据*/
		String psrtIds = u.getSitePartIds();
		String[] arrayId = psrtIds.split(",");
		for (int i = 0; null != arrayId && i < arrayId.length; i++) {
			SitePartContentReal cxl = new SitePartContentReal();
			cxl.setSortId(Integer.parseInt(sortId));
			cxl.setPartId(Integer.parseInt(arrayId[i]));
			sitePartContentRealManager.save(cxl);
		}
	}
	/**修改数据由BaseManager类实现*/
	public void update(SiteContentSort u){
		
	}
	/**删除数据由BaseManager类实现*/
	public void removeById (java.lang.Integer id){
		
	}
	
	/**
	 * 按网站内容分类ID 查询 分类信息
	 * @param SortId 网站内容分类ID
	 * @return
	 */
	public SiteContentSort getSiteContentSortById(String sortId){
		List list = siteContentSortDao.getSiteContentSortById(sortId);
		return list.size()>0?(SiteContentSort)list.get(0):new SiteContentSort();
	}

	/**
	 * 
	 * 非多选下拉框树
	 * 
	 */
	public String getComboBoxTree(SiteContentSort cmsc , StringBuffer sb, int j) {
		List list = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", cmsc.getId());
		list = siteContentSortDao.getListByMap(map);
		if (null != list && list.size() > 0) {
			if (j != 0) {
				sb.append(",'children':[");
			}
			j++;
			for (int i = 0; i < list.size(); i++) {
				SiteContentSort t = (SiteContentSort)list.get(i);
				sb.append("{'id':'").append(t.getId()).append("','text':'").append(t.getClassifyName() + "'");
				this.getComboBoxTree(t, sb, j);
				if (i + 1 < list.size()) {
					sb.append(",");
				}
			}
			sb.append("],'leaf':false}");
		} else {
			sb.append(",'leaf':true}");
		}
		return sb.toString();
	}
	
	/** * 按网站分类ID查询它所有的子类(下级)信息列表(返回网站分类表数据信息) */
	public List getSortBySortID(Map map){
		Map paraMap=new HashMap();
		int pageSize=10;
		int nowPage=1;
		
		String sortId=(String)map.get("sortId");
		String start=(String)map.get("start");
		String count=(String)map.get("count");
		String sort=(String)map.get("sort");
		
		if(null==sortId || "".equalsIgnoreCase(sortId)){
			paraMap.put("sortId", "");
		}
		else{
			paraMap.put("sortId", sortId);
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
			pageSize=10;//默认每页显示10条数据
		}
		else{
			pageSize=Integer.parseInt(count);
			paraMap.put("count", count);
			paraMap.put("begin", ((nowPage-1)*pageSize));
			paraMap.put("end", (nowPage*pageSize));
		}
		
		if(null==sort || "".equalsIgnoreCase(sort)){
			paraMap.put("sort", "");
		}
		else{
			paraMap.put("sort", sort);
		}
		
		return siteContentSortDao.getSortBySortID(paraMap);
	}
}
