package cn.mmbook.platform.action.tag;

import java.io.IOException;
import java.util.*;
import javacommon.util.extjs.ExtJsPageHelper;
import javacommon.util.extjs.ListRange;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import cn.mmbook.platform.model.tag.CmsSkins;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import static javacommon.util.extjs.Struts2JsonHelper.*;
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
 * 网站皮肤
 * @author xxj
 *
 */


public class CmsSkinsAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsSkinsManager cmsSkinsManager;
	
	private CmsSkins cmsSkins;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsSkins = new CmsSkins();
		} else {
			cmsSkins = (CmsSkins)cmsSkinsManager.getById(id);
		}
	}
	
	/** 通过spring自动注入 */
	public void setCmsSkinsManager(CmsSkinsManager manager) {
		this.cmsSkinsManager = manager;
	}	
	
	public Object getModel() {
		return cmsSkins;
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
		Page page = cmsSkinsManager.findByPageRequest(pr);
		
		List<CmsSkins> CmsSkinslist = (List) page.getResult();
		ListRange<CmsSkins> resultList = new ListRange<CmsSkins>();
		resultList.setList(CmsSkinslist);
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
			String isdefulte = (null==cmsSkins.getIsdefault()) ? "0" : cmsSkins.getIsdefault() ;
			if("1".equals(isdefulte)){
			    cmsSkinsManager.updateDefult(cmsSkins);
			}
			cmsSkinsManager.save(cmsSkins);
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
		if(null != cmsSkins){
			try
			{
				String isdefulte = (null==cmsSkins.getIsdefault()) ? "0" : cmsSkins.getIsdefault() ;
				if("1".equals(isdefulte)){
				    cmsSkinsManager.updateDefult(cmsSkins);
				}
				cmsSkinsManager.update(cmsSkins);
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
				cmsSkinsManager.removeById(id);
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
	 * 设成默认
	 * extGrid修改
	 * @throws IOException
	 */
	public void moren() throws IOException
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != cmsSkins){
			try
			{
				String ids = (null==getRequest().getParameter("ids")) ? "" : getRequest().getParameter("ids") ;
				String v = (null==getRequest().getParameter("v")) ? "" : getRequest().getParameter("v") ;
				CmsSkins cmsSkins = (CmsSkins)cmsSkinsManager.getById(ids);
				cmsSkins.setIsdefault("1");
				cmsSkins.setVersionId(v);
				/**将所有数据 ISDEFAULT_ 修改为 0 (不默认)*/
				cmsSkinsManager.updateDefult(cmsSkins);
				/**将 ID 为'ids' 数据 ISDEFAULT_ 修改为 库 (默认)*/
				cmsSkinsManager.update(cmsSkins);
				result.put("success", true);
				result.put("msg", "设成默认成功!");
			}
			catch (Exception e)
			{
				result.put("failure", true);
				result.put("msg", "设成默认失败!");
				e.printStackTrace();
			}
		}else{
			result.put("failure", true);
			result.put("msg", "数据不存在!");
		}
		outJson(result);
	}
	
	
	
	

}
