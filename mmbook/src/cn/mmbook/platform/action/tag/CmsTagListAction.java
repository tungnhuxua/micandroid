package cn.mmbook.platform.action.tag;

import java.io.IOException;
import java.sql.Timestamp;
import javacommon.util.extjs.*;
import javax.servlet.http.*;
import org.apache.commons.lang.StringUtils;
import cn.org.rapid_framework.page.*;
import cn.mmbook.platform.model.tag.*;
import com.opensymphony.xwork2.*;
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


public class CmsTagListAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsTagListManager cmsTagListManager;
	private CmsModelManager cmsModelManager;
	private CmsModelfieldManager cmsModelfieldManager;
	//private CmsPositionManager cmsPositionManager;
	
	
	private CmsTagList cmsTagList;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsTagList = new CmsTagList();
		} else {
			cmsTagList = (CmsTagList)cmsTagListManager.getById(id);
		}
	}
	
//	public void setCmsPositionManager(CmsPositionManager cmsPositionManager) {
//		this.cmsPositionManager = cmsPositionManager;
//	}
	
	public void setCmsModelfieldManager(CmsModelfieldManager cmsModelfieldManager) {
		this.cmsModelfieldManager = cmsModelfieldManager;
	}

	/** 通过spring自动注入 */
	public void setCmsTagListManager(CmsTagListManager manager) {
		this.cmsTagListManager = manager;
	}	
	
	public void setCmsModelManager(CmsModelManager cmsModelManager) {
		this.cmsModelManager = cmsModelManager;
	}
	
	public Object getModel() {
		return cmsTagList;
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
		Page page = cmsTagListManager.findByPageRequest(pr);
		
		List<CmsTagList> CmsTagListlist = (List) page.getResult();
		ListRange<CmsTagList> resultList = new ListRange<CmsTagList>();
		resultList.setList(CmsTagListlist);
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
			cmsTagListManager.save(cmsTagList);
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
		if(null != cmsTagList){
			try
			{ 
				cmsTagListManager.update(cmsTagList);
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
				cmsTagListManager.removeById(id);
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
	 * 验证标签名称 
	 * @throws IOException
	 */
	public void tagNameText() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		String textvalue = this.getRequest().getParameter("textvalue");
		String versionId = this.getRequest().getParameter("versionId");

		if (versionId==null ||"".equals(versionId)) {
			new Exception("模板版本ID为空!");
		}
		
		if(null!=textvalue && !"".equals(textvalue)){
			try {
				CmsTagList obj_info = new CmsTagList();
				obj_info.setTagName(textvalue);
				obj_info.setVersionId(versionId);
				List list = cmsTagListManager.getList(obj_info);
				if(list!=null && list.size()>0){
					result.put("failure", true);
					result.put("msg", "标签名称已经被使用");
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
	 * 内容类型下拉框 
	 * 返回字符串并有加“无限”
	 */
	public String getCmsModelComboString() throws IOException
	{
		List list =null;
		String result =null;
		CmsModel cmsModel =new CmsModel();
		list = cmsModelManager.getCmsModelList(cmsModel);
		for (int i = 0; list != null && i < list.size(); i++) {
			cmsModel = (CmsModel)list.get(i);
			if(i==0){
				result="[['0','无限'],['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}else{
				result+=",['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		return result;
	}
	
	/**
	 * 内容类型下拉框 
	 * 返回json并有加“无限”
	 */
	public void getCmsModelCombo() throws IOException
	{
		List list =null;
		String result =null;
		CmsModel cmsModel =new CmsModel();
		list = cmsModelManager.getCmsModelList(cmsModel);
		for (int i = 0; list != null && i < list.size(); i++) {
			cmsModel = (CmsModel)list.get(i);
			if(i==0){
				result="[['0','无限'],['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}else{
				result+=",['"+cmsModel.getId()+"','"+cmsModel.getModelName()+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		outJsonString(result);
	}
	
	/**
	 * 推荐位下拉框 
	 */
	public void getPosCombo() throws IOException
	{
		List list = new ArrayList();
		String result =null;
		//CmsPosition cmsPosition =new CmsPosition();
		//list = cmsPositionManager.getList(cmsPosition);
		//for (int i = 0; list != null && i < list.size(); i++) {
		for (int i = 0; i < 4; i++) {	
			//cmsPosition = (CmsPosition)list.get(i);
			if(i==0){
				//result="[['','请选择'],['"+cmsPosition.getId()+"','"+cmsPosition.getPosName()+"']";
				result="[['','请选择'],['"+i+"','"+i+"']";
			}else{
				//result+=",['"+cmsPosition.getId()+"','"+cmsPosition.getPosName()+"']";
				result+=",['"+i+"','"+i+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		outJsonString(result);
	}
	
	/**
	 * 推荐位下拉框 
	 */
	public String getPosComboString() throws IOException
	{
		List list =new ArrayList();
		String result =null;
		//CmsPosition cmsPosition =new CmsPosition();
		//list = cmsPositionManager.getList(cmsPosition);
		//for (int i = 0; list != null && i < list.size(); i++) {
		for (int i = 0;  i < 4; i++) {
			//cmsPosition = (CmsPosition)list.get(i);
			if(i==0){
				//result="[['','请选择'],['"+cmsPosition.getId()+"','"+cmsPosition.getPosName()+"']";
				result="[['','请选择'],['"+i+"','"+i+"']";
			}else{
				//result+=",['"+cmsPosition.getId()+"','"+cmsPosition.getPosName()+"']";
				result+=",['"+i+"','"+i+"']";
			}
		}
		if (!"".equals(result)) {
			result+="]";
		}
		return result;
	}
	
	/**
	 * 获取模型字段动态复选框 
	 * 10	资讯
	 * 11	书籍
	 * 12	下载
	 * 13	音乐
	 * 15	图片 
	 * @throws IOException
	 */
	public void getCheckbox() throws IOException
	{
 
		String modelId = this.getRequest().getParameter("modelId");
		
		if (modelId==null ||"".equals(modelId)) {
			new Exception("内容类型ID为空!");
		}
		
		String result="{root:[";
	 
		 if(!"13".equals(modelId)){
			     result=result+"{boxLabel:'序号',name: 'fieldsCode',inputValue:'id_',checked: false,disabled:true}";
				 result=result+",{boxLabel:'短标题',name: 'fieldsCode',inputValue:'title_',checked: false,disabled:true}";
				 result=result+",{boxLabel:'长标题',name: 'fieldsCode',inputValue:'title_full_',checked: false,disabled:true}";
				 result=result+",{boxLabel:'缩略图',name: 'fieldsCode',inputValue:'previews_img_url_',checked: false,disabled:true}";
		 }else{
		     result=result+"{boxLabel:'序号',name: 'fieldsCode',inputValue:'id_',checked: false,disabled:true}";
			 result=result+",{boxLabel:'短标题',name: 'fieldsCode',inputValue:'title_',checked: false,disabled:true}";
			 result=result+",{boxLabel:'长标题',name: 'fieldsCode',inputValue:'title_full_',checked: false,disabled:true}";
		}
				 
		 
		if (!"".equals(result)) {
			result+="]}";
		}
		outJsonString(result);
	}
	/**
	 * 返回栏目标签所有数据
	 * @author qiongguo
	 * @throws IOException
	 */
	public void getList() throws IOException {
			CmsTagList obj_info = new CmsTagList();
			List<CmsTagCategory> cmsTagCategorylist = (List) cmsTagListManager.getList(obj_info);
			ListRange<CmsTagCategory> resultList = new ListRange<CmsTagCategory>();
			resultList.setList(cmsTagCategorylist);
			resultList.setTotalSize(cmsTagCategorylist.size());
			resultList.setMessage("ok");
			resultList.setSuccess(true);
			outJson(resultList);
	} 
	
	public String updateBaset() throws IOException
	{
		
		String u_id = (null==this.getRequest().getParameter("u")) ? "" : this.getRequest().getParameter("u");
		String v_id = (null==this.getRequest().getParameter("v")) ? "" : this.getRequest().getParameter("v");
		if(null != cmsTagList){
			cmsTagList.setCmsModelStore(getCmsModelComboString());
			cmsTagList.setPosStore(getPosComboString());
			getRequest().setAttribute("cmsTagList", cmsTagList);
			return "/admin/tag/CmsTagList/update.jsp?u="+u_id+"&v="+v_id;
		}else{
			return "!system/Error/error.do?msg="+java.net.URLEncoder.encode("内容标签数据不存在 请查证!","UTF-8"); 
		}
	}

	

	

}
