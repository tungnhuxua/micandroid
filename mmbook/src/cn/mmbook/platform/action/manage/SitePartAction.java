package cn.mmbook.platform.action.manage;

import static javacommon.util.extjs.Struts2JsonHelper.outJson;
import static javacommon.util.extjs.Struts2JsonHelper.outJsonString;
import static javacommon.util.extjs.Struts2JsonHelper.outString;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javacommon.base.BaseStruts2Action;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import cn.mmbook.platform.model.manage.ExtjsTest;
import cn.mmbook.platform.model.manage.SitePart;
import cn.mmbook.platform.service.manage.SitePartContentRealManager;
import cn.mmbook.platform.service.manage.SitePartManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import freemarker.template.SimpleDate;

/**
 * <p> SitePartAction<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SitePartAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private SitePartManager sitePartManager;
	private SitePartContentRealManager sitePartContentRealManager;
	
	private SitePart sitePart;
	java.lang.Integer id = null;
	private String[] items;
	/**
	 * 自动获取对象
	 * @exception Exception
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			sitePart = new SitePart();
		} else {
			sitePart = (SitePart)sitePartManager.getById(id);
		}
	}
	
	/**
	 * 对象通过spring自动注入
	 * @param manager SitePartManager
	 *  
	 */
	public void setSitePartManager(SitePartManager manager) {
		this.sitePartManager = manager;
	}
	/**
	 * struts 自动把页面提交数据转换成数据类对象赋值
	 * @return SitePart sitePart
	 */
	public Object getModel() {
		return sitePart;
	}
	
	public void setId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}



	/**
	 * 分页显示数据,通过JSON串返回给页面
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void list() throws IOException
	{
		System.out.println("List XXX *");
		/**自动获取页面数据存在PageRequest对象里*/
		PageRequest<Map> pageRequest = ExtJsPageHelper.createPageRequestForExtJs(getRequest(), DEFAULT_SORT_COLUMNS);
		System.out.println("pageRequest.getFilters()>>"+pageRequest.getFilters());
		/**分页查询数据封装在Page对象里*/
		Page pages = sitePartManager.findByPageRequest(pageRequest);
		/**取出 model 数据类 LIST*/
		List<SitePart> SitePartlist = (List) pages.getResult();
		ListRange<SitePart> resultList = new ListRange<SitePart>();
		/**加载LIST数据*/
		resultList.setList(SitePartlist);
		/**加载记录数*/
		resultList.setTotalSize(pages.getTotalCount());
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	/**
	 * 返回所有数据
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void findAll() throws IOException
	{
		/**查询所有数据*/
		ArrayList<SitePart> SitePartlist = (ArrayList)sitePartManager.findAll();
		ListRange<SitePart> resultList = new ListRange<SitePart>();
		resultList.setList(SitePartlist);
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	

	
	/**
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 SitePart 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void save() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String parentSitePartName = getRequest().getParameter("pigSource");//获得页面上选择的栏目名
			String sitePateName=getRequest().getParameter("partName");//获得页面上输入的栏目名
			String siteContentSort=getRequest().getParameter("softid");//获得选择的站点内容
			String showType=getRequest().getParameter("showType");//获得显示方式
			String sortValue=getRequest().getParameter("sortValue");//获得排序值

			if("".equals(showType)||null==showType )
				sitePart.setShowType(0);
			if("".equals(sortValue)||null==sortValue)
				sitePart.setSortValue(0);
			
			boolean sitePateNameFlag=sitePartManager.checkSitePartNameExit(sitePateName);//判断栏目名是否已经存在（存在：true 不存在：false）
			
			//栏目名不存在时才允许添加
			if(!sitePateNameFlag){
				//根据选择的所属栏目获得栏目的id作为新栏目的父节点
				if("请选择...".equalsIgnoreCase(parentSitePartName) || null == parentSitePartName || "".equalsIgnoreCase(parentSitePartName)){
					sitePart.setPartParentId(0);
				}
				else{
					int parentId=sitePartManager.getSitePartIdByName(parentSitePartName);
					sitePart.setPartParentId(parentId);
					sitePartManager.updateLowerNode(parentId,1);//修改被选中的栏目的是否有下级节点属性
				}
				
				String dateStr=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
				sitePart.setInsertTime(java.sql.Date.valueOf(dateStr));//日期默认为系统时间
				/**是否有下级节点:1有，2没有*/
				sitePart.setLowerNode(2);
				sitePart.setPartName(sitePateName);
				/**保存数据*/
				sitePartManager.saveInfo(sitePart,siteContentSort);
				/**加载操作状态*/
				result.put("success", true);
				/**加载页面提示信息*/
				result.put("msg", "数据添加成功!");
			}
			else{
				result.put("failure", true);
				result.put("msg", "该栏目名已经存在!");
			}
		}
		catch (Exception e)
		{
			result.put("failure", true);
			result.put("msg", "数据添加失败!");
			e.printStackTrace();
		}
		outJson(result);
	}
	
	/**
	 * 修改数据
	 * 系统自动把页面提交数据，封装到 SitePart 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != sitePart){
			try
			{
				//System.out.println("sitePart.getId()>>"+sitePart.getId());
				int sitePartId=sitePart.getId();//获得修改前的栏目id
				SitePart oldSitePart=sitePartManager.getSitePartById(sitePartId);//获得修改前的栏目实例
				String parentSitePartName = getRequest().getParameter("parentPartName");//获得页面上选择的栏目名
				String sitePateName=getRequest().getParameter("partName");//获得页面上输入的栏目名
				String siteContentSort=getRequest().getParameter("softid");//获得选择的站点内容

				boolean sitePateNameFlag=sitePartManager.checkSitePartNameExit(sitePateName);//判断栏目名是否已经存在（存在：true 不存在：false）
				
				String oldPartName=oldSitePart.getPartName();//获得修改前的栏目名
				
				//栏目名不存在时才允许添加（除去原来自己的栏目名）
				if(!sitePateNameFlag || oldPartName.equals(sitePateName)){
					int parentId=0;//父节点id
					//System.out.println("parentSitePartName>>"+parentSitePartName);
					//为选择则修改为顶级节点
					if("请选择...".equalsIgnoreCase(parentSitePartName) || null == parentSitePartName || "".equalsIgnoreCase(parentSitePartName)){
						parentId=0;
						//System.out.println("parentId if>>"+parentId);
					}
					else{
						parentId=sitePartManager.getSitePartIdByName(parentSitePartName);//获得选择的父栏目的id
						//System.out.println("parentId else>>"+parentId);
					}
					
					//判断是否是选择的自己作为的父栏目
					if(oldSitePart.getPartName().equals(parentSitePartName)){
						result.put("msg", "不能选择自己作为父栏目!");
					}
					else if(sitePartManager.checkLowerMe(parentId,sitePartId)){
						result.put("msg", "不能选择等级比自己低的栏目作为父节点!");
					}
					else{
						//根据选择的所属栏目获得栏目的id作为新栏目的父节点
						if("请选择...".equalsIgnoreCase(parentSitePartName) || null == parentSitePartName || "".equalsIgnoreCase(parentSitePartName)){
							sitePart.setPartParentId(0);
						}
						else{
							sitePart.setPartParentId(parentId);
							sitePartManager.updateLowerNode(parentId,1);//修改被选中的栏目的是否有下级节点属性为1
						}
						
						String dateStr=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
						sitePart.setInsertTime(java.sql.Date.valueOf(dateStr));//日期默认为系统时间
						
						sitePart.setPartName(sitePateName);
						/**修改数据*/
						sitePartManager.updateInfo(sitePart);
						
						sitePartManager.checkLowerNodeOrUpdate(oldSitePart);//判断修改后以前的父节点是否还存在下级节点，并修改状态
						
						/**加载操作状态*/
						result.put("success", true);
						/**加载页面提示信息*/
						result.put("msg", "数据修改成功!");
					}
				}
				else{
					result.put("failure", true);
					result.put("msg", "该栏目名已经存在!");
				}
			}
			catch (Exception e)
			{
				result.put("failure", true);
				result.put("msg", "数据修改失败!");
				e.printStackTrace();
			}
		}else{
			result.put("failure", true);
			result.put("msg", "数据不存在!");
		}
		outJson(result);
	}
	
	/**
	 * 删除单条或多条数据
	 * 页面把需要删除的数据的ID存放在　参数 ids 中。
	 * Action 把IDS 分割,得到要删除对象主键ID进行删除
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void delete() throws IOException
	{
		String ids = getRequest().getParameter("ids");
		String[] idarray = ids.split(",");
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			for (int i = 0; i < idarray.length; i++)
			{
				java.lang.Integer id = new java.lang.Integer((String)idarray[i]);
				SitePart sp=sitePartManager.getSitePartById(id);//根据id获得删除前栏目实例
				sitePartManager.removeById(id);
				sitePartManager.updateTheChildUp(id);//删除后将其子栏目设置为顶级栏目
				sitePartManager.checkLowerNodeOrUpdate(sp);//判断删除后是否还存在下级节点，并修改状态
				sitePartContentRealManager.deleteByPartId(id);
			}
			result.put("success", true);
			result.put("msg", "数据删除成功");
		}
		catch (Exception e)
		{
			result.put("failure", true);
			result.put("msg", "数据删除失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	/**
	 * 下拉框所用
	 * 返回格式: ['1','name1'],['2','name2'],['3','kep']
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void getComboBox() throws IOException
	{
		
		String result =null; 
		List list = sitePartManager.getList(sitePart);
		for (int i = 0; list != null && i < list.size(); i++) {
			sitePart = (SitePart)list.get(i);
			/**两个都是getId,把后者改成你要取的数*/
			if(i==0){
				result="[['"+sitePart.getId()+"','"+sitePart.getId()+"']";
			}else{
				result+=",['"+sitePart.getId()+"','"+sitePart.getId()+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		outJsonString(result);
	}

	/**
	 * 多表关联查询
	 * 分页显示数据
	 * 列表
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void listAnyTable() throws IOException
	{
		PageRequest<Map> pr = ExtJsPageHelper.createPageRequestForExtJs(getRequest(), DEFAULT_SORT_COLUMNS);
		Page page = sitePartManager.listPageAnyTable(pr);
		
		List<SitePart> SitePartlist = (List) page.getResult();
		ListRange<SitePart> resultList = new ListRange<SitePart>();
		resultList.setList(SitePartlist);
		resultList.setTotalSize(page.getTotalCount());
		resultList.setMessage("ok");
		resultList.setSuccess(true);
		outJson(resultList);
	}
	
	/**
	 * 一次性展开树
	 * 树功能数据查询 测试用
     * @author admin,
     * @version 1.0. 2006-07-08 xxj
	 * @throws IOException
	 */
	public void getSitePartTree() throws IOException {
	    System.out.println("SitePart.getSitePartTree");
		String parentId = (null==getRequest().getParameter("parentId")) ? "0"  : getRequest().getParameter("parentId");
		StringBuffer stringBuffer = new StringBuffer("[");
		ExtjsTest info = new ExtjsTest();
		info.setId(Integer.parseInt(parentId));
		String result = null;
		result ="[{id:9,text:'756gh',parentId:0,children:[{id:10,text:'text1',parentId:9,children:[{id:11,text:'text2',parentId:10,leaf:true}]}]},"
			+"{id:1,text:'00000',parentId:0,children:[{id:2,text:'xxx1',parentId:1,children:[{id:4,text:'xxx12',parentId:2,leaf:true},{id:5,text:'xxx13',parentId:2,leaf:true}," 
			+"{id:6,text:'xxx21',parentId:2,children:[{id:7,text:'aaaa1',parentId:6,leaf:true},{id:8,text:'werq11',parentId:6,leaf:true}]}]},{id:3,text:'756gh',parentId:1,children:[{id:9,text:'text1',parentId:3,children:[{id:10,text:'text2',parentId:9,leaf:true}]}]}]}]" ;
		outString(result);
	}

	/**
	 * 加载下拉树菜单数据
	 * @throws IOException
	 */
	public void getSynTree() throws IOException { 
		int j = 0;
		StringBuffer sb = new StringBuffer("[");
		SitePart sp = new SitePart();
		sp.setId(0);
		String result = null;
		String cids = this.getRequest().getParameter("value");
		try { 
			result = sitePartManager.getTreeCombox(sp, sb, j,cids); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.substring(0, result.lastIndexOf(",'leaf':false"));
		outString(result);
	}

	public void setSitePartContentRealManager(
			SitePartContentRealManager sitePartContentRealManager) {
		this.sitePartContentRealManager = sitePartContentRealManager;
	}
	/**
	 * 下拉列表
	 * 查询一级栏目
	 * @author Stilfler  673211682 2010.8.30
	 */
	public void getLovcombo() {
		List list = new ArrayList();
		list = sitePartManager.getTopSitePartList();
		ListRange resultList = new ListRange();
		resultList.setList(list);
		resultList.setTotalSize(list.size());
		outJson(resultList);
	}
}
