package cn.mmbook.platform.action.tag;

import static javacommon.util.extjs.Struts2JsonHelper.outJson;
import static javacommon.util.extjs.Struts2JsonHelper.outJsonString;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javacommon.base.BaseStruts2Action;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.CmsModel;
import cn.mmbook.platform.service.tag.CmsModelManager;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * 
 * @author Administrator
 *
 */


public class CmsModelAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsModelManager cmsModelManager;
	
	private CmsModel cmsModel;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsModel = new CmsModel();
		} else {
			cmsModel = (CmsModel)cmsModelManager.getById(id);
		}
	}
	
	/** 通过spring自动注入 */
	public void setCmsModelManager(CmsModelManager manager) {
		this.cmsModelManager = manager;
	}	
	
	public Object getModel() {
		return cmsModel;
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
		Page page = cmsModelManager.findByPageRequest(pr);
		
		List<CmsModel> CmsModellist = (List) page.getResult();
		ListRange<CmsModel> resultList = new ListRange<CmsModel>();
		resultList.setList(CmsModellist);
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
			cmsModelManager.save(cmsModel);
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
		if(null != cmsModel){
			try
			{
				cmsModelManager.update(cmsModel);
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
				cmsModelManager.removeById(id);
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
	 * 内容类型下拉框
	 */
	public void getCmsModelComboBox() throws IOException
	{
		List list =null;
		String result =null;
		CmsModel cmsModel =new CmsModel();
		list = cmsModelManager.getCmsModelList(cmsModel);
		for (int i = 0; list != null && i < list.size(); i++) {
			cmsModel = (CmsModel)list.get(i);
			if(i==0){
				result="[['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}else{
				result+=",['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		System.out.println(result);
		outJsonString(result);
	}
	
}
