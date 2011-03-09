package cn.mmbook.platform.action.tag;

import java.io.IOException;
import java.util.*;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.*;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import static javacommon.util.extjs.Struts2JsonHelper.*;
import javacommon.base.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 
 * @author xxj
 *
 */


public class CmsTempletAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsTempletManager cmsTempletManager;
	private CmsTagContentManager cmsTagContentManager;
	private CmsTagCategoryManager cmsTagCategoryManager;
	private CmsTagListManager cmsTagListManager;
	//private CmsTagRmdationManager cmsTagRmdationManager;
	
	private CmsTemplet cmsTemplet;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsTemplet = new CmsTemplet();
		} else {
			cmsTemplet = (CmsTemplet)cmsTempletManager.getById(id);
		}
	}
	
	/** 通过spring自动注入 */
	public void setCmsTempletManager(CmsTempletManager manager) {
		this.cmsTempletManager = manager;
	}	
	
	public void setCmsTagContentManager(CmsTagContentManager manager) {
		this.cmsTagContentManager = manager;
	}
	
	public void setCmsTagCategoryManager(CmsTagCategoryManager manager) {
		this.cmsTagCategoryManager = manager;
	}
	public void setCmsTagListManager(CmsTagListManager manager) {
		this.cmsTagListManager = manager;
	}
	
//	public void setCmsTagRmdationManager(CmsTagRmdationManager cmsTagRmdationManager) {
//		this.cmsTagRmdationManager = cmsTagRmdationManager;
//	}
	
	
	
	public Object getModel() {
		return cmsTemplet;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/**
	 * ExtGrid使用
	 * 列表
	 * @throws IOException
	 */
	public void list() throws IOException
	{
		PageRequest<Map> pr = ExtJsPageHelper.createPageRequestForExtJs(getRequest(), DEFAULT_SORT_COLUMNS);
		String versionId = this.getRequest().getParameter("versionId"); 
		if(null!=versionId&&!"".equals(versionId)){
			pr.getFilters().put("versionId", versionId);
		}
		Page page = cmsTempletManager.findByPageRequest(pr);
		
		List<CmsTemplet> CmsTempletlist = (List) page.getResult();
		ListRange<CmsTemplet> resultList = new ListRange<CmsTemplet>();
		resultList.setList(CmsTempletlist);
		resultList.setTotalSize(page.getTotalCount());
		resultList.setMessage("ok");
		resultList.setSuccess(true);
		outJson(resultList);
	}

	/**
	 * extGrid保存
	 * @throws IOException
	 */
	public void save() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			cmsTemplet.setTempletContent(cmsTemplet.getTempletContentString().getBytes("UTF-8"));
			cmsTempletManager.save(cmsTemplet);
			result.put("success", true);
			result.put("msg", "添加成功!");
		}
		catch (Exception e)
		{
			result.put("failure", true);
			result.put("msg", "添加失败!");
			e.printStackTrace();
		}
		outJson(result);
	}
	
	/**
	 * extGrid修改
	 * @throws IOException
	 */
	public void update() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != cmsTemplet){
			try
			{
				CmsTemplet obj =new CmsTemplet();
				obj.setId(cmsTemplet.getId());
				obj.setTempletName(cmsTemplet.getTempletName());
				obj.setFileName(cmsTemplet.getFileName());
				obj.setTempletContent(cmsTemplet.getTempletContentString().getBytes("UTF-8"));
				cmsTempletManager.update(obj);
				result.put("success", true);
				result.put("msg", "修改成功!");
			}
			catch (Exception e)
			{
				result.put("failure", true);
				result.put("msg", "修改失败!");
				e.printStackTrace();
			}
		}else{
			result.put("failure", true);
			result.put("msg", "数据不存在!");
		}
		outJson(result);
	}
	
	/**
	 * extGrid删除
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
				cmsTempletManager.removeById(id);
			}
			result.put("success", true);
			result.put("msg", "删除成功");
		}
		catch (Exception e)
		{
			result.put("failure", true);
			result.put("msg", "删除失败!");
			e.printStackTrace();
		}
		outJson(result);
	}
	
	/**
	 * 模板下拉框 
	 * @throws IOException
	 */
	public void getCmsTempletCombo() throws IOException
	{
		List list =null;
		String result =null;

		String versionId = this.getRequest().getParameter("versionId");
		String webTempletType = this.getRequest().getParameter("webTempletType");

		if (versionId==null ||"".equals(versionId)) {
			new Exception("模板版本ID为空!");
		}
		
		if (webTempletType==null ||"".equals(webTempletType)) {
			new Exception("模板类型为空!");
		}

		CmsTemplet u =new CmsTemplet();
		u.setVersionId(versionId);
		u.setWebTempletType(webTempletType);
		list =cmsTempletManager.getList(u);
		for (int i = 0; list != null && i < list.size(); i++) {
			u = (CmsTemplet)list.get(i);
			if(i==0){
				result="[['"+u.getId()+"','"+u.getTempletName()+"']";
			}else{
				result+=",['"+u.getId()+"','"+u.getTempletName()+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		
		//System.out.println(result);
		outJsonString(result);
	}
	
	/**
	 * 标签树 
	 * @throws IOException
	 */
	public void getTagTree() throws IOException
	{
		List list =null;
		String versionId = this.getRequest().getParameter("versionId");
		/**
		 * 1,内容标签,2,栏目标签,3,内容推荐标签,4,列表标签
		 */
		String tag = this.getRequest().getParameter("tag");

		if (versionId==null ||"".equals(versionId)) {
			new Exception("模板版本ID为空!");
		}

		ArrayList treeNodeArray = new ArrayList();
		if (tag!=null && !"".equals(tag)) {
			if("1".equals(tag)){
				CmsTagContent u =new CmsTagContent();
				list =cmsTagContentManager.getList(u);
				for (int i = 0; list != null && i < list.size(); i++) {
					u = (CmsTagContent)list.get(i);
					JSONTreeNode TreeNode = new JSONTreeNode();	

					TreeNode.setId(u.getId());
		            TreeNode.setArr("{conTag_"+u.getTagName()+"}");
		            TreeNode.setText(u.getTagName());
		            TreeNode.setIconCls("icon-treep");
		            TreeNode.setLeaf(true);
		            treeNodeArray.add(TreeNode);
				}

			}else if("2".equals(tag)){
				CmsTagCategory u =new CmsTagCategory();
				list =cmsTagCategoryManager.getList(u);
				for (int i = 0; list != null && i < list.size(); i++) {
					u = (CmsTagCategory)list.get(i);
					JSONTreeNode TreeNode = new JSONTreeNode();	
					
					TreeNode.setId(u.getId());
		            TreeNode.setArr("{catTag_"+u.getTagName()+"}");
		            TreeNode.setText(u.getTagName());
		            TreeNode.setIconCls("icon-treep");
		            TreeNode.setLeaf(true);
		            treeNodeArray.add(TreeNode);
				}
			}else if("3".equals(tag)){
				//CmsTagRmdation u =new CmsTagRmdation();
				//list =cmsTagRmdationManager.getList(u);
				//for (int i = 0; list != null && i < list.size(); i++) {
				for (int i = 0; i<4; i++) {
					//u = (CmsTagRmdation)list.get(i);
					JSONTreeNode TreeNode = new JSONTreeNode();	
					
					//TreeNode.setId(u.getId());
					TreeNode.setId(String.valueOf(i));
					TreeNode.setArr("{tset}");
		            //TreeNode.setArr("{"+ResourceBundleImp.getValue("rmdationTagPre")+u.getTagName()+"}");
		            TreeNode.setText(String.valueOf(i));
		            //TreeNode.setText(u.getTagName());
		            TreeNode.setIconCls("icon-treep");
		            TreeNode.setLeaf(true);
		            treeNodeArray.add(TreeNode);
				}
			}else if("4".equals(tag)){
				CmsTagList u =new CmsTagList();
				list =cmsTagListManager.getList(u);
				for (int i = 0; list != null && i < list.size(); i++) {
					u = (CmsTagList)list.get(i);
					JSONTreeNode TreeNode = new JSONTreeNode();
					TreeNode.setId(u.getId());
		            TreeNode.setArr("{listTag_"+u.getTagName()+"}");
		            TreeNode.setText(u.getTagName());
		            TreeNode.setIconCls("icon-treep");
		            TreeNode.setLeaf(true);
		            treeNodeArray.add(TreeNode);
				}

			}
			else{}
		}else {
			new Exception("标签为空!");
		}
		//System.out.println(result);
		outJson(treeNodeArray);
	}
	
	/**
	 * 验证文件名重复 
	 * @throws IOException
	 */
	public void fileNameText() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		String textvalue = this.getRequest().getParameter("textvalue");
		String versionId = this.getRequest().getParameter("versionId");

		if (versionId==null ||"".equals(versionId)) {
			new Exception("模板版本ID为空!");
		}
		
		if(null!=textvalue && !"".equals(textvalue)){
			try {
				CmsTemplet obj_info = new CmsTemplet();
				obj_info.setFileName(textvalue);
				obj_info.setVersionId(versionId);
				List list = cmsTempletManager.getList(obj_info);
				if(list!=null && list.size()>0){
					result.put("failure", true);
					result.put("msg", "文件名已经被使用");
				}else{
					result.put("success", true);
					result.put("msg", "验证通过成功");	
				}
			} catch (Exception e) {
				result.put("failure", true);
				result.put("msg", e.getMessage());
				e.printStackTrace();
			}
		}else {
			new Exception("文件名为空!");
		}
		outJson(result);
	}
	
	/**
	 * 模板名称重复 
	 * @throws IOException
	 */
	public void templetNameText() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		String textvalue = this.getRequest().getParameter("textvalue");
		String versionId = this.getRequest().getParameter("versionId");

		if (versionId==null ||"".equals(versionId)) {
			new Exception("模板版本ID为空!");
		}
		
		if(null!=textvalue && !"".equals(textvalue)){
			try {
				CmsTemplet obj_info = new CmsTemplet();
				obj_info.setTempletName(textvalue);
				obj_info.setVersionId(versionId);
				List list = cmsTempletManager.getList(obj_info);
				if(list!=null && list.size()>0){
					result.put("failure", true);
					result.put("msg", "模板名称已经被使用");
				}else{
					result.put("success", true);
					result.put("msg", "验证通过成功");	
				}
			} catch (Exception e) {
				result.put("failure", true);
				result.put("msg", e.getMessage());
				e.printStackTrace();
			}
		}else {
			new Exception("文件名为空!");
		}
		outJson(result);
	}
	
 
	public String updateBaset() throws IOException
	{
		System.out.println("updateBaset");
		if(null != cmsTemplet){
			getRequest().setAttribute("cmsTemplet", cmsTemplet);
			return "/admin/tag/CmsTemplet/update.jsp";
		}else{
			return "!system/Error/error.do?msg="+java.net.URLEncoder.encode("模板数据不存在 请查证!","UTF-8"); 
		}
		
	}


}
