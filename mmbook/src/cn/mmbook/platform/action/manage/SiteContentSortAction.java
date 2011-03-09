package cn.mmbook.platform.action.manage;

import java.io.IOException;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.manage.ExtjsTest;
import cn.mmbook.platform.model.manage.SiteContentSort;
import cn.mmbook.platform.model.manage.SitePartContentReal;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import static javacommon.util.extjs.Struts2JsonHelper.*;

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

/**
 * <p> SiteContentSortAction<br> 
 * cn.mmbook.platform.action.manage.SiteContentSort.getTreeCombox
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteContentSortAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private SiteContentSortManager siteContentSortManager;
	
	private SiteContentSort siteContentSort;
	java.lang.Integer id = null;
	private String[] items;
	/**
	 * 自动获取对象
	 * @exception Exception
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			siteContentSort = new SiteContentSort();
		} else {
			siteContentSort = (SiteContentSort)siteContentSortManager.getById(id);
		}
	}

	private SitePartContentRealManager sitePartContentRealManager;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
 
	public void setSitePartContentRealManager(SitePartContentRealManager dao) {
		this.sitePartContentRealManager = dao;
	}
	/**
	 * 对象通过spring自动注入
	 * @param manager SiteContentSortManager
	 *  
	 */
	public void setSiteContentSortManager(SiteContentSortManager manager) {
		this.siteContentSortManager = manager;
	}
	/**
	 * struts 自动把页面提交数据转换成数据类对象赋值
	 * @return SiteContentSort siteContentSort
	 */
	public Object getModel() {
		return siteContentSort;
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
		/**自动获取页面数据存在PageRequest对象里*/
		PageRequest<Map> pageRequest = ExtJsPageHelper.createPageRequestForExtJs(getRequest(), DEFAULT_SORT_COLUMNS);
		/**分页查询数据封装在Page对象里*/
		Page pages = siteContentSortManager.findByPageRequest(pageRequest);
		/**取出 model 数据类 LIST*/
		List<SiteContentSort> SiteContentSortlist = (List) pages.getResult();
		ListRange<SiteContentSort> resultList = new ListRange<SiteContentSort>();
		/**加载LIST数据*/
		resultList.setList(SiteContentSortlist);
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
		ArrayList<SiteContentSort> SiteContentSortlist = (ArrayList)siteContentSortManager.findAll();
		ListRange<SiteContentSort> resultList = new ListRange<SiteContentSort>();
		resultList.setList(SiteContentSortlist);
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	

	
	/**
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 SiteContentSort 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void save() throws IOException
	{
		System.out.println("save");
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			/**保存数据*/
			siteContentSortManager.save(siteContentSort);
			/**加载操作状态*/
			result.put("success", true);
			/**加载页面提示信息*/
			result.put("msg", "数据添加成功!");
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
	 * 系统自动把页面提交数据，封装到 SiteContentSort 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != siteContentSort){
			try
			{
				/**修改数据*/
				siteContentSortManager.update(siteContentSort);
				/**加载操作状态*/
				result.put("success", true);
				/**加载页面提示信息*/
				result.put("msg", "数据修改成功!");
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
				siteContentSortManager.removeById(id);
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
		List list = siteContentSortManager.getList(siteContentSort);
		for (int i = 0; list != null && i < list.size(); i++) {
			siteContentSort = (SiteContentSort)list.get(i);
			/**两个都是getId,把后者改成你要取的数*/
			if(i==0){
				result="[['"+siteContentSort.getId()+"','"+siteContentSort.getId()+"']";
			}else{
				result+=",['"+siteContentSort.getId()+"','"+siteContentSort.getId()+"']";
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
		Page page = siteContentSortManager.listPageAnyTable(pr);
		
		List<SiteContentSort> SiteContentSortlist = (List) page.getResult();
		ListRange<SiteContentSort> resultList = new ListRange<SiteContentSort>();
		resultList.setList(SiteContentSortlist);
		resultList.setTotalSize(page.getTotalCount());
		resultList.setMessage("ok");
		resultList.setSuccess(true);
		outJson(resultList);
	}

	/**
	 * 多选下拉框树
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void getTreeCombox() throws IOException {

		int j = 0;
		StringBuffer sb = new StringBuffer("[");
		SiteContentSort cmsc = new SiteContentSort();
		cmsc.setId(0);
		String result = null;
		/**查询该栏目所有的关联的网站分类数据，生成串 1,2,3,4,*/
		String partId = this.getRequest().getParameter("partId");
		//System.out.println("partId>>"+partId);
		String  array_real = "";
		if (null!=partId&&!"".equals(partId)) {
			SitePartContentReal u = new SitePartContentReal();
			u.setPartId(Integer.parseInt(partId));
			List list_part_soft = (List)sitePartContentRealManager.getList(u);
			if(null!=list_part_soft&&list_part_soft.size()>0){
			  for(int kk=0;kk<list_part_soft.size();kk++){
				SitePartContentReal info_real = (SitePartContentReal)list_part_soft.get(kk);
				array_real= array_real + String.valueOf(info_real.getSortId())+",";
			  }
			}
		}
		try { 
			result = siteContentSortManager.getTreeCombox(cmsc, sb, j, array_real);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.substring(0, result.lastIndexOf(",'leaf':false"));
		outString(result);
	}

	/**
	 * 非多选下拉框树
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void getComboBoxTree() throws IOException {
		int j = 0;
		StringBuffer sb = new StringBuffer("[");
		SiteContentSort cmsc = new SiteContentSort();
		cmsc.setId(0);
		String result = null;
		try { 
			result = siteContentSortManager.getComboBoxTree(cmsc, sb, j);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.substring(0, result.lastIndexOf(",'leaf':false"));
		outString(result);
	}
 

}
