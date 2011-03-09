package cn.mmbook.platform.action.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
 * <p> ExtjsTestAction<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class ExtjsTestAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private ExtjsTestManager extjsTestManager;
	
	private ExtjsTest extjsTest;
	java.lang.Integer id = null;
	private String[] items;
	/**
	 * 自动获取对象
	 * @exception Exception
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			extjsTest = new ExtjsTest();
		} else {
			extjsTest = (ExtjsTest)extjsTestManager.getById(id);
		}
	}
	
	/**
	 * 对象通过spring自动注入
	 * @param manager ExtjsTestManager
	 *  
	 */
	public void setExtjsTestManager(ExtjsTestManager manager) {
		this.extjsTestManager = manager;
	}
	/**
	 * struts 自动把页面提交数据转换成数据类对象赋值
	 * @return ExtjsTest extjsTest
	 */
	public Object getModel() {
		return extjsTest;
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
		Page pages = extjsTestManager.findByPageRequest(pageRequest);
		/**取出 model 数据类 LIST*/
		List<ExtjsTest> ExtjsTestlist = (List) pages.getResult();
		ListRange<ExtjsTest> resultList = new ListRange<ExtjsTest>();
		/**加载LIST数据*/
		resultList.setList(ExtjsTestlist);
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
		ArrayList<ExtjsTest> ExtjsTestlist = (ArrayList)extjsTestManager.findAll();
		ListRange<ExtjsTest> resultList = new ListRange<ExtjsTest>();
		resultList.setList(ExtjsTestlist);
		/**加载页面提示信息*/
		resultList.setMessage("ok");
		/**加载功能状态*/
		resultList.setSuccess(true);
		/**返回页面 JSOM 串 */
		outJson(resultList);
	}
	

	
	/**
	 * 保存数据
	 * 系统自动把页面提交数据，封装到 ExtjsTest 对象
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
			extjsTestManager.save(extjsTest);
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
	 * 系统自动把页面提交数据，封装到 ExtjsTest 对象
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != extjsTest){
			try
			{
				/**修改数据*/
				extjsTestManager.update(extjsTest);
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
				extjsTestManager.removeById(id);
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
		List list = extjsTestManager.getList(extjsTest);
		for (int i = 0; list != null && i < list.size(); i++) {
			extjsTest = (ExtjsTest)list.get(i);
			/**两个都是getId,把后者改成你要取的数*/
			if(i==0){
				result="[['"+extjsTest.getId()+"','"+extjsTest.getId()+"']";
			}else{
				result+=",['"+extjsTest.getId()+"','"+extjsTest.getId()+"']";
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
		Page page = extjsTestManager.listPageAnyTable(pr);
		
		List<ExtjsTest> ExtjsTestlist = (List) page.getResult();
		ListRange<ExtjsTest> resultList = new ListRange<ExtjsTest>();
		resultList.setList(ExtjsTestlist);
		resultList.setTotalSize(page.getTotalCount());
		resultList.setMessage("ok");
		resultList.setSuccess(true);
		outJson(resultList);
	}


	/**
	 * 一次性展开树
	 * 树功能数据查询
	 * 递归查询
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void getSynTree() throws IOException { 
		int j = 0;
		StringBuffer sb = new StringBuffer("[");
		ExtjsTest info = new ExtjsTest();
		info.setId(0);
		String result = null;
		 
		try { 
			result = extjsTestManager.getTreeCombox(info, sb, j); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = result.substring(0, result.lastIndexOf(",'leaf':false"));
		System.out.println("Test Result>>"+result);
		outString(result);
	}
	
	/**
	 * 测试远程取下拉框数据,从数据库取数据部分省略
	 * @throws IOException
	 */
	public void getTypesCombox() throws IOException{
		String result = null;
		for (int i = 1; i < 4; i++) { 
			if (i == 1) {
				result = "[['" + i+ "','功能类型" + i+ "']";
			} else {
				result += ",['" + i+ "','功能类型" + i+ "']";
			}
		}
		if (!"".equals(result)) {
			result += "]";
		}
		outJsonString(result);
	}


	/**
	 * 异步加载树
	 * @throws IOException
	 */
	public void getAsyncTree() throws IOException { 
		System.out.println("XXX");
		List<ExtjsTest> roleList = null;
		ArrayList treeNodeArray = new ArrayList();
		/**按父节点查询它的下级节点*/
		roleList = extjsTestManager.getList(extjsTest);
//		Hashtable userRolehash = new Hashtable(); //角色权限哈希
//		String idStr = (null==this.getRequest().getParameter("ids")) ? "0" : this.getRequest().getParameter("ids"); //权限元素
//		int id =  Integer.parseInt(idStr);
//		ExtjsTest extjsTest_info = new ExtjsTest();
//		extjsTest_info.setId(id);
//		List userRoleList =extjsTestManager.getList(extjsTest_info);
//		
//		for (int i = 0; userRoleList != null && i < userRoleList.size(); i++) {
//			UserRole rserRole = (UserRole )userRoleList.get(i);
//			userRolehash.put(rserRole.getRoleId(), rserRole);
//			System.out.println("^^^ RoleAction getRoleTree rserRole.getId() = "+rserRole.getId());
//		}
		
		for (int i = 0; roleList != null && i < roleList.size(); i++) {
			ExtjsTest role = (ExtjsTest)roleList.get(i);
			JSONTreeCheckedNode TreeNode = new JSONTreeCheckedNode();
            TreeNode.setId(String.valueOf(role.getId()));
            TreeNode.setArr("&roleid="+role.getId()+"&parentid="+role.getId());
            TreeNode.setText(role.getFunctionName());
            TreeNode.setIconCls("icon-treepage");
			//TreeNode.setHref("");//leaf
			//TreeNode.setHrefTarget("");
			//System.out.println("@@@ RoleAction getRoleTree role.getId() = "+role.getId()+ "," +userRolehash.get(role.getId()));
//			if(userRolehash.containsKey(role.getId())){
//				TreeNode.setChecked(true);
//			}else{
//				TreeNode.setChecked(false);
//			}
			TreeNode.setLeaf(true); //是否叶子
			TreeNode.setIconCls("icon-treep");
			//TreeNode.setExpandable(false);//展开节点 expandable
			treeNodeArray.add(TreeNode);
		}
		outJson(treeNodeArray);
	}

	/**
	 * 一次性展开树
	 * 树功能数据查询
     * @author admin,
     * @version 1.0. 2010-07-08
	 * @throws IOException
	 */
	public void getMenuTree() throws IOException { 
		String node_id = (null==getRequest().getParameter("node_id")) ? "0"  : getRequest().getParameter("node_id");
		System.out.println("###$ "+node_id);
		String result = null;
//		try {
//			result = extjsTestManager.getTreeCombox(info, stringBuffer, 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		result=result.substring(0, result.lastIndexOf(",'leaf':false"));
		result = 
			 "["
			+"{'id':'1110000','text':'测试菜单"+node_id+"','children':"
			+"["
			+"{'id':'1110100','text':'测试管理','children':"
			+"["
			+"{'id':'1110101','text':'新增操作','leaf':true,'url':'admin/manage/ExtjsTest/add.jsp','iconCls':'icon-nav-p1'},"
			+"{'id':'1110102','text':'列表操作','leaf':true,'url':'admin/manage/ExtjsTest/index.jsp','iconCls':'icon-nav-p1'}"
			+"],'leaf':false,'iconCls':'icon-nav-1'}"
			+"],'leaf':false,'iconCls':'icon-nav-1'}"
			+","
			+"{'id':'1120000','text':'系统管理','children':"
			+"["
			+"{'id':'1129100','text':'辅助管理','children':"
			+"["
			+"{'id':'1129101','text':'新增配置参数','leaf':true,'url':'admin/manage/BasicParameters/add.jsp','iconCls':'icon-nav-p1'},"
			+"{'id':'1129102','text':'配置参数列表','leaf':true,'url':'admin/manage/BasicParameters/index.jsp','iconCls':'icon-nav-p1'}"
			+"],'leaf':false,'iconCls':'icon-nav-1'}"
			+"],'leaf':false,'iconCls':'icon-nav-1'}"
			+"]"
			;
	 
		outString(result);
	}
	
	/**
	 * 下拉列表
	 * 
	 * @author pch 2010.7.12
	 */
	public void getLovcombo() {
		List list = new ArrayList();
		list = extjsTestManager.getList(extjsTest);
		ListRange resultList = new ListRange();
		resultList.setList(list);
		resultList.setTotalSize(list.size());
		outJson(resultList);
	}
	
	
}
