package cn.mmbook.platform.action.site;

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

import cn.mmbook.platform.model.site.SiteArticle;
import cn.mmbook.platform.model.site.SiteContent;
import cn.mmbook.platform.model.base.BaseAccessories;

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

import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.dao.site.*;
import cn.mmbook.platform.service.site.impl.*;
import cn.mmbook.platform.service.site.*;

/**
 * <p> SiteArticleAction<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class SiteArticleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private SiteArticleManager siteArticleManager;
	
	/**文章\资讯\短文 数据类*/
	private SiteArticle siteArticle;
	/**基础内容表 数据类*/
	private SiteContent siteContent;
	private BaseAccessories baseAccessories;
	java.lang.String id = null;
	private String[] items;
	/**
	 * 自动获取对象
	 * @exception Exception
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			siteArticle = new SiteArticle();
		} else {
			siteArticle = (SiteArticle)siteArticleManager.getById(id);
		}
	}
	
	/**
	 * 对象通过spring自动注入
	 * @param manager SiteArticleManager
	 *  
	 */
	public void setSiteArticleManager(SiteArticleManager manager) {
		this.siteArticleManager = manager;
	}
	/**
	 * struts 自动把页面提交数据转换成数据类对象赋值
	 * @return SiteArticle siteArticle
	 */
	public Object getModel() {
		return siteArticle;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public SiteContent getSiteContent() {
		return siteContent;
	}

	public void setSiteContent(SiteContent siteContent) {
		this.siteContent = siteContent;
	}


	public BaseAccessories getBaseAccessories() {
		return baseAccessories;
	}

	public void setBaseAccessories(BaseAccessories baseAccessories) {
		this.baseAccessories = baseAccessories;
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
		Page pages = siteArticleManager.findByPageRequest(pageRequest);
		/**取出 model 数据类 LIST*/
		List<SiteArticle> SiteArticlelist = (List) pages.getResult();
		ListRange<SiteArticle> resultList = new ListRange<SiteArticle>();
		/**加载LIST数据*/
		resultList.setList(SiteArticlelist);
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
		ArrayList<SiteArticle> SiteArticlelist = (ArrayList)siteArticleManager.findAll();
		ListRange<SiteArticle> resultList = new ListRange<SiteArticle>();
		resultList.setList(SiteArticlelist);
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	

	
	/**
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 SiteArticle 对象
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
			siteArticleManager.save(siteArticle);
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
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 SiteArticle 对象
     * @author admin,
     * @version 1.0. 2007-07-08
	 * @throws IOException
	 */
	public void saveInfo() throws IOException
	{ 
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			/**保存数据*/ 
			siteArticleManager.saveInfo(siteArticle);
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
	 * 系统自动把页面提交数据，封装到 SiteArticle 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != siteArticle){
			try
			{
				/**修改数据*/
				siteArticleManager.update(siteArticle);
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
	 * 修改数据
	 * 系统自动把页面提交数据，封装到 SiteArticle 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void updateInfo() throws IOException
	{
		System.out.println("updateInfo");
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != siteArticle){
			try
			{
				/**修改数据*/
				siteArticleManager.updateInfo(siteArticle,siteContent,baseAccessories);
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
				java.lang.String id = new java.lang.String((String)idarray[i]);
				siteArticleManager.removeById(id);
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
		List list = siteArticleManager.getList(siteArticle);
		for (int i = 0; list != null && i < list.size(); i++) {
			siteArticle = (SiteArticle)list.get(i);
			/**两个都是getId,把后者改成你要取的数*/
			if(i==0){
				result="[['"+siteArticle.getId()+"','"+siteArticle.getId()+"']";
			}else{
				result+=",['"+siteArticle.getId()+"','"+siteArticle.getId()+"']";
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
		Page page = siteArticleManager.listPageAnyTable(pr);
		
		List<SiteArticle> SiteArticlelist = (List) page.getResult();
		ListRange<SiteArticle> resultList = new ListRange<SiteArticle>();
		resultList.setList(SiteArticlelist);
		resultList.setTotalSize(page.getTotalCount());
		resultList.setMessage("ok");
		resultList.setSuccess(true);
		outJson(resultList);
	}





}
