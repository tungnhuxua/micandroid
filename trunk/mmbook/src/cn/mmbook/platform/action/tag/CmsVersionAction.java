package cn.mmbook.platform.action.tag;

import java.io.IOException;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.CmsVersion;
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
import cn.mmbook.platform.model.tag.*;
import cn.mmbook.platform.dao.tag.*;
import cn.mmbook.platform.service.tag.impl.*;
import cn.mmbook.platform.service.tag.*;


/**
 * 
 * @author xxj
 *
 */


public class CmsVersionAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsVersionManager cmsVersionManager;
	
	private CmsVersion cmsVersion;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsVersion = new CmsVersion();
		} else {
			cmsVersion = (CmsVersion)cmsVersionManager.getById(id);
		}
	}
	
	/** 通过spring自动注入 */
	public void setCmsVersionManager(CmsVersionManager manager) {
		this.cmsVersionManager = manager;
	}	
	
	public Object getModel() {
		return cmsVersion;
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
		Page page = cmsVersionManager.findByPageRequest(pr);
		
		List<CmsVersion> CmsVersionlist = (List) page.getResult();
		ListRange<CmsVersion> resultList = new ListRange<CmsVersion>();
		resultList.setList(CmsVersionlist);
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
			cmsVersionManager.save(cmsVersion);
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
		if(null != cmsVersion){
			try
			{
				cmsVersionManager.update(cmsVersion);
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
				cmsVersionManager.removeById(id);
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
	 * 查询 模板版本 下拉框数据
	 * @author xxj 
	 * @throws IOException
	 */
	public void getCmsVersionComboBox() throws IOException { 
        String result=null;
		List<CmsVersion> cmsVersionlist = (List) cmsVersionManager.getCombox();
		try{
		if(null!=cmsVersionlist){
			for(int i=0,n=cmsVersionlist.size();i<n;i++){
				cmsVersion=cmsVersionlist.get(i);
				if(i==0){
					result="[['"+cmsVersion.getId()+"','"+cmsVersion.getVersionName()+"']";
				}else{
					result+=",['"+cmsVersion.getId()+"','"+cmsVersion.getVersionName()+"']";
				}
			}
			if (!"".equals(result)) {
				result+="]";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		outJsonString(result);
	}
}
