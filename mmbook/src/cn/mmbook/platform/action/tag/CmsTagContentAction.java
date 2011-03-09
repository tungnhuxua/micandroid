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


public class CmsTagContentAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private CmsTagContentManager cmsTagContentManager;
	private CmsModelManager cmsModelManager;
	private CmsModelfieldManager cmsModelfieldManager;
	//private CmsPositionManager cmsPositionManager;
	
	
	private CmsTagContent cmsTagContent;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			cmsTagContent = new CmsTagContent();
		} else {
			cmsTagContent = (CmsTagContent)cmsTagContentManager.getById(id);
		}
	}
	
//	public void setCmsPositionManager(CmsPositionManager cmsPositionManager) {
//		this.cmsPositionManager = cmsPositionManager;
//	}
	
	public void setCmsModelfieldManager(CmsModelfieldManager cmsModelfieldManager) {
		this.cmsModelfieldManager = cmsModelfieldManager;
	}

	/** 通过spring自动注入 */
	public void setCmsTagContentManager(CmsTagContentManager manager) {
		this.cmsTagContentManager = manager;
	}	
	
	public void setCmsModelManager(CmsModelManager cmsModelManager) {
		this.cmsModelManager = cmsModelManager;
	}
	
	public Object getModel() {
		return cmsTagContent;
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
		Page page = cmsTagContentManager.findByPageRequest(pr);
		
		List<CmsTagContent> CmsTagContentlist = (List) page.getResult();
		ListRange<CmsTagContent> resultList = new ListRange<CmsTagContent>();
		resultList.setList(CmsTagContentlist);
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
			//Timestamp inserttiem = new Timestamp(System.currentTimeMillis());
			//cmsTagContent.setInsertTime(inserttiem);
			cmsTagContentManager.save(cmsTagContent);
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
		if(null != cmsTagContent){
			try
			{
				//Timestamp updatetiem = new Timestamp(System.currentTimeMillis());
				//cmsTagContent.setUpdateTime(updatetiem);
				cmsTagContentManager.update(cmsTagContent);
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
				cmsTagContentManager.removeById(id);
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
				CmsTagContent obj_info = new CmsTagContent();
				obj_info.setTagName(textvalue);
				obj_info.setVersionId(versionId);
				List list = cmsTagContentManager.getList(obj_info);
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
	 * @throws IOException
	 */
	public void getCheckbox() throws IOException
	{

		String fieldsCode = this.getRequest().getParameter("fieldsCode");
		String modelId = this.getRequest().getParameter("modelId");
		
		if (modelId==null ||"".equals(modelId)) {
			new Exception("内容类型ID为空!");
		}
		
		List list =null;
		CmsModelfield u =new CmsModelfield();
		u.setModelId(modelId);
		list =cmsModelfieldManager.getList(u);
		
		String result="{root:[";
		for (int i = 0; list != null && i < list.size(); i++) {
			u = (CmsModelfield)list.get(i);
			int index = -1;
			if(null!=fieldsCode&&!"".equals(fieldsCode)){
				index =fieldsCode.indexOf(u.getTableName()+"."+u.getFieldName());
			}
			if(index!=-1){
				if(i!=0){
					if("1".equals(u.getIsselect())){
						result=result+",{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:true}";
					}else{
						result=result+",{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:false}";
					}
				}else{
					if("1".equals(u.getIsselect())){
						result=result+"{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:true}";
					}else{
						result=result+"{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:false}";
					}
				}
			}else{
				if(i!=0){
					if("1".equals(u.getIsselect())){
						result=result+",{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:true}";
					}else{
						result=result+",{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: false,disabled:false}";
					}
				}else{
					if("1".equals(u.getIsselect())){
						result=result+"{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: true,disabled:true}";
					}else{
						result=result+"{boxLabel:'"+u.getFieldRemark()+"',name: 'TTypeID',inputValue:'"+u.getTableName()+"."+u.getFieldName()+"',checked: false,disabled:false}";
					}
				}
			}
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
			CmsTagContent obj_info = new CmsTagContent();
			List<CmsTagCategory> cmsTagCategorylist = (List) cmsTagContentManager.getList(obj_info);
			ListRange<CmsTagCategory> resultList = new ListRange<CmsTagCategory>();
			resultList.setList(cmsTagCategorylist);
			resultList.setTotalSize(cmsTagCategorylist.size());
			resultList.setMessage("ok");
			resultList.setSuccess(true);
			outJson(resultList);
	} 
	
	public String updateBaset() throws IOException
	{
		String u_id = (null==this.getRequest().getParameter("u_id")) ? "" : this.getRequest().getParameter("u_id");
		String v_id = (null==this.getRequest().getParameter("v_id")) ? "" : this.getRequest().getParameter("v_id");
		if(null != cmsTagContent){
			cmsTagContent.setCmsModelStore(getCmsModelComboString());
			cmsTagContent.setPosStore(getPosComboString());
			getRequest().setAttribute("cmsTagContent", cmsTagContent);
			return "/admin/tag/CmsTagContent/update.jsp?u_id="+u_id+"&v_id="+v_id;
		}else{
			return "!system/Error/error.do?msg="+java.net.URLEncoder.encode("内容标签数据不存在 请查证!","UTF-8"); 
		}
	}

	

	

}
