package cn.mmbook.platform.service.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.platform.dao.manage.SiteContentSortDao;
import cn.mmbook.platform.dao.manage.SitePartContentRealDao;
import cn.mmbook.platform.dao.manage.SitePartDao;
import cn.mmbook.platform.model.manage.ExtjsTest;
import cn.mmbook.platform.model.manage.SitePart;
import cn.mmbook.platform.model.manage.SitePartContentReal;
import cn.mmbook.platform.service.manage.SitePartManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
/**
 * <p> SitePart server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("sitePartManager")
@Transactional
public class SitePartManagerImpl extends BaseManager<SitePart,java.lang.Integer> 
					implements SitePartManager {

	private SitePartDao sitePartDao;
	private SitePartContentRealDao sitePartContentRealDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSitePartDao(SitePartDao dao) {
		this.sitePartDao = dao;
	}
	public void setSitePartContentRealDao(SitePartContentRealDao dao) {
		this.sitePartContentRealDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.sitePartDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return sitePartDao.findByPageRequest(pr);
	}
	
	public void saveInfo(SitePart u){
		/**关联网站分类字符串*/
		String relateId = u.getRelateId();
		String[] cidarr = null;
		if (null!=relateId&&!"".equals(relateId)) {
			cidarr = relateId.split(",");
		}
		if(null!=cidarr&&cidarr.length>0){
			Integer partId = u.getId();
			Map map = new HashMap();
			map.put("partId", String.valueOf(partId));
			sitePartContentRealDao.deleteByMap(map);
			for(int i=0;i<cidarr.length;i++){
				SitePartContentReal info = new SitePartContentReal();
				info.setSortId(Integer.parseInt(cidarr[i]));
				info.setPartId(partId);
				sitePartContentRealDao.save(info);
			}
		}
		save(u);
	}
	
	public void updateInfo(SitePart u){
		System.out.println("update");
		/**关联网站分类字符串*/
		String relateId = u.getRelateId();
		String[] cidarr = null;
		if (null!=relateId&&!"".equals(relateId)) {
			cidarr = relateId.split(",");
		}
		if(null!=cidarr&&cidarr.length>0){
			Integer partId = u.getId();
			Map map = new HashMap();
			map.put("partId", String.valueOf(partId));
			sitePartContentRealDao.deleteByMap(map);
			for(int i=0;i<cidarr.length;i++){
				SitePartContentReal info = new SitePartContentReal();
				info.setSortId(Integer.parseInt(cidarr[i]));
				info.setPartId(partId);
				sitePartContentRealDao.save(info);
			}
		}
		update(u);
	}		
	public List getList(SitePart u){
		return sitePartDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return sitePartDao.listPageAnyTable(pr);
	}

	/**树 数据 查询 返回字符串 */
	public String getTreeCombox(SitePart sp,StringBuffer sb,int j,String cids){
		List<SitePart> list = new ArrayList<SitePart>();
		//System.out.println("###getTreeCombox");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partParentId", sp.getId());
		list = sitePartDao.getListByMap(map);
		/**已经选中*/
		String[] cidarr = null;
		if (null != cids && !"".equals(cids)) {
			cidarr = cids.split(",");
		}

		if (null != list && list.size() > 0) {
			if (j != 0) {
				sb.append(",'children':[");
			}
			j++;
			for (int i = 0; i < list.size(); i++) {
				SitePart s = list.get(i);
				
				boolean bool = false;

				for (int mm = 0; null != cidarr && mm < cidarr.length; mm++) {
					if (s.getId().equals(cidarr[mm])) {
						bool = true;
					}
				}
				
				if (bool) {
					sb.append("{checked:true,'id':'").append(s.getId()).append(
							"','text':'").append(s.getPartName() + "'");
				} else {
					sb.append("{checked:false,'id':'").append(s.getId())
							.append("','text':'").append(
									s.getPartName() + "'");
				}
				
//				sb.append("{'id':'").append(s.getId()).append("','text':'")
//						.append(s.getPartName() + "'");
				 
				this.getTreeCombox(s, sb, j,cids);
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
	 * arye
	 * 判断栏目名是否存在
	 */
	public boolean checkSitePartNameExit(String sitePartName){
		return sitePartDao.checkSitePartNameExit(sitePartName);
	}
	
	/**
	 * arye
	 * 根据栏目名查询栏目id
	 */
	public int getSitePartIdByName(String sitePartName){
		return sitePartDao.getSitePartIdByName(sitePartName);
	}
	
	/**
	 * arye
	 * 修改栏目是否有下级节点 
	 */
	public void updateLowerNode(int id,int lowerNode){
		SitePart sp=sitePartDao.getSitePartById(id);
		//是否有下级节点不为要更新的状态时才更新
		if(sp.getLowerNode()!=lowerNode){
			sp.setLowerNode(lowerNode);//设置是否有下级节点
			 
			sitePartDao.saveOrUpdate(sp);//更新
		}
	}
	
	/**
	 * arye
	 * 重载添加方法
	 */
	public void saveInfo(SitePart u,String siteContentSort){
		/**关联网站分类字符串*/
		/*String relateId = u.getRelateId();
		String[] cidarr = null;
		if (null!=relateId&&!"".equals(relateId)) {
			cidarr = relateId.split(",");
		}
		if(null!=cidarr&&cidarr.length>0){
			Integer partId = u.getId();
			Map map = new HashMap();
			map.put("partId", String.valueOf(partId));
			sitePartContentRealDao.deleteByMap(map);
			for(int i=0;i<cidarr.length;i++){
				SitePartContentReal info = new SitePartContentReal();
				info.setContentId(Integer.parseInt(cidarr[i]));
				info.setPartId(partId);
				sitePartContentRealDao.save(info);
			}
		}*/
		save(u);//添加
		int partId=sitePartDao.getLastId();//获取最后添加的数据的id
		
		System.out.println("partId>>"+partId);
		
		siteContentSort+=",";
		String [] siteContentSorts=siteContentSort.split(",");
		int len=siteContentSorts.length;
		SitePartContentReal spcr=null;//声明栏目内容分类关联实体
		//循环添加关联信息
		for (int i = 0; i < len; i++) {
			spcr=new SitePartContentReal();
			spcr.setSortId(Integer.parseInt(siteContentSorts[i]));
			spcr.setPartId(partId);
			sitePartContentRealDao.save(spcr);
		}
	}
	
	/**	
	 * arye
	 * 根据id查询栏目信息 
	 */
	public SitePart getSitePartById(int id){
		return sitePartDao.getSitePartById(id);
	}
	
	/** 
	 * arye
	 * 判断是否还存在下级节点(true：存在 false：不存在),并修改状态 
	 */
	public void checkLowerNodeOrUpdate(SitePart sp){
		int parentId=sp.getPartParentId();
		SitePart parentSitePart=sitePartDao.getSitePartById(parentId);//获得父栏目实例
		if(null != parentSitePart){
			//判断是否存在下级节点，给属性赋值
			if(sitePartDao.checkLowerNode(parentId))
				parentSitePart.setLowerNode(1);
			else
				parentSitePart.setLowerNode(0);
			sitePartDao.update(parentSitePart);
		}
	}
	
	/**
	 * arye
	 * 验证选择的父节点是否是比自己等级低的栏目
	 * true(选择了比自己等级低的栏目)
	 */
	public boolean checkLowerMe(int parentId,int partId){
		//普通节点直接修改成顶级节点直接返回false
		if(parentId==0)
			return false;
		
		int pId=sitePartDao.getSitePartById(parentId).getPartParentId();
		SitePart sp=null;
		while(pId>0){
			if(pId==partId){
				return true;
			}
			sp=sitePartDao.getSitePartById(pId);
			pId=sp.getPartParentId();
		}
		return false;
	}
	
	/**
	 * arye
	 * 删除后将其子节点设为顶级节点
	 */
	public void updateTheChildUp(int id){
		List<SitePart> sitePartList=sitePartDao.getSitePartByParentId(id);
		int len=sitePartList.size();
		SitePart sp=null;
		for(int i=0;i<len;i++){
			sp=sitePartList.get(i);
			sp.setPartParentId(0);
			sitePartDao.update(sp);
		}
	}

	/**
	 * 查询 栏目列表数据
	 * @param map
	 *   String sort 1: 按ID排序 2,按排序值(sortValue),3 随机
	 *   String siteChannelId 频道ID
	 *   String page 1 分页 2 不分页
	 *   String count 返回记录数
	 * @return Map 
	 *    String size 符合条件记录总数
	 *    List<SiteChannels> 结果集
	 */
	public Page getSitePartList(Map map){
		PageRequest pageRequest = new PageRequest();

		Map map_param = new HashMap();
		String sitePartId = (String) map.get("channelId");
		if (javacommon.util.StringTool.isNull(sitePartId)) {
			map_param.put("channelId", sitePartId);
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

		Page page = sitePartDao.getSitePateByChannelId(pageRequest);
		System.out.println("page.getPageSize()=" + page.getPageSize());
		List listChannels = (List) page.getResult();
		System.out.println("listChannels.size()=" + listChannels.size());
		
		return page;
	}	
	
	/**查询顶级栏目列表*/
	public List getTopSitePartList(){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println("public List getTopSitePartList(){");
		/**父站栏目id，一级父id为0 partParentId*/
		result.put("partParentId", "0");
		return sitePartDao.getListByMap(result);
	}
	
	/**按栏目ID查询 下级栏目*/
	public List getSitePartListById(Map map){
		Map paraMap=new HashMap();
		int pageSize=10;
		int nowPage=1;
		
		String sitePartId=(String)map.get("sitePartId");
		String start=(String)map.get("start");
		String count=(String)map.get("count");
		String sort=(String)map.get("sort");
		
		if(null==sitePartId || "".equalsIgnoreCase(sitePartId)){
			paraMap.put("sitePartId", "");
		}
		else{
			paraMap.put("sitePartId", sitePartId);
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
		
		if(null==sort || "".equalsIgnoreCase(sort)){
			paraMap.put("sort", "");
		}
		else{
			paraMap.put("sort", sort);
		}
		
		return sitePartDao.getSitePartListById(paraMap);
	}
}