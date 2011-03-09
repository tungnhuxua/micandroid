package cn.mmbook.platform.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import cn.mmbook.platform.model.base.BaseKeycontentReal;
import cn.mmbook.platform.model.base.BaseKeywords;


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

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.model.manage.SiteContentSort;
import cn.mmbook.platform.model.manage.SitePartContentReal;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;

/**
 * <p> BaseKeywordsAction<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class BaseKeywordsAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private BaseKeywordsManager baseKeywordsManager;
	private BaseKeycontentRealManager baseKeycontentRealManager;
	
	private BaseKeywords baseKeywords;
	java.lang.Integer id = null;
	private String[] items;
	/**
	 * 自动获取对象
	 * @exception Exception
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseKeywords = new BaseKeywords();
		} else {
			baseKeywords = (BaseKeywords)baseKeywordsManager.getById(id);
		}
	}
	
	/**
	 * 对象通过spring自动注入
	 * @param manager BaseKeywordsManager
	 *  
	 */
	public void setBaseKeywordsManager(BaseKeywordsManager manager) {
		this.baseKeywordsManager = manager;
	}
	public void setBaseKeycontentRealManager( BaseKeycontentRealManager manager) {
		this.baseKeycontentRealManager = manager;
	}

 
	
	/**
	 * struts 自动把页面提交数据转换成数据类对象赋值
	 * @return BaseKeywords baseKeywords
	 */
	public Object getModel() {
		return baseKeywords;
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
		Page pages = baseKeywordsManager.findByPageRequest(pageRequest);
		/**取出 model 数据类 LIST*/
		List<BaseKeywords> BaseKeywordslist = (List) pages.getResult();
		ListRange<BaseKeywords> resultList = new ListRange<BaseKeywords>();
		/**加载LIST数据*/
		resultList.setList(BaseKeywordslist);
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
		ArrayList<BaseKeywords> BaseKeywordslist = (ArrayList)baseKeywordsManager.findAll();
		ListRange<BaseKeywords> resultList = new ListRange<BaseKeywords>();
		resultList.setList(BaseKeywordslist);
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	

	
	/**
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 BaseKeywords 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void save() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			/**保存数据*/
			baseKeywordsManager.save(baseKeywords);
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
	 * 系统自动把页面提交数据，封装到 BaseKeywords 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != baseKeywords){
			try
			{
				/**修改数据*/
				baseKeywordsManager.update(baseKeywords);
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
				baseKeywordsManager.removeById(id);
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
		List list = baseKeywordsManager.getList(baseKeywords);
		for (int i = 0; list != null && i < list.size(); i++) {
			baseKeywords = (BaseKeywords)list.get(i);
			/**两个都是getId,把后者改成你要取的数*/
			if(i==0){
				result="[['"+baseKeywords.getId()+"','"+baseKeywords.getId()+"']";
			}else{
				result+=",['"+baseKeywords.getId()+"','"+baseKeywords.getId()+"']";
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
		Page page = baseKeywordsManager.listPageAnyTable(pr);
		
		List<BaseKeywords> BaseKeywordslist = (List) page.getResult();
		ListRange<BaseKeywords> resultList = new ListRange<BaseKeywords>();
		resultList.setList(BaseKeywordslist);
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
		BaseKeywords cmsc = new BaseKeywords();
		cmsc.setId(0);
		String result = null;
		/**查询该关键字所有的关联的网站内容数据，生成串 1,2,3,4,*/
		String contentId = this.getRequest().getParameter("contentid");
		String  array_real = "";
		if (null!=contentId&&!"".equals(contentId)) {
			BaseKeycontentReal u = new BaseKeycontentReal();
			u.setContentId(contentId);
			List list_part_soft = (List)baseKeycontentRealManager.getList(u);
			if(null!=list_part_soft&&list_part_soft.size()>0){
			  for(int kk=0;kk<list_part_soft.size();kk++){
				SitePartContentReal info_real = (SitePartContentReal)list_part_soft.get(kk);
				array_real= array_real + String.valueOf(info_real.getSortId())+",";
			  }
			}
		}
		try {
			System.out.println("### "+array_real);
			result = baseKeywordsManager.getTreeCombox(cmsc, sb, j, array_real);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.substring(0, result.lastIndexOf(",'leaf':false"));
		outString(result);
	}






}
