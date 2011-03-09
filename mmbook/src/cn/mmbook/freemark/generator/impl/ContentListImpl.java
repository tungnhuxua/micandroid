package cn.mmbook.freemark.generator.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.freemark.constant.DaoConstant;
import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.platform.facade.TagService;
import cn.mmbook.platform.model.tag.CmsTagList;
import cn.mmbook.platform.model.tag.CmsTemplet;



@Component("contentlist")
@Transactional
public class ContentListImpl   extends AbstractGenerator implements DaoGenerator{


	private TagService tagService;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTagService(TagService manager) {
		this.tagService = manager;
	}
 
	
	
	Map<String, Object> data = new HashMap<String, Object>();
	
	/**生成的JSP文件名称*/
	private String fileName="";
	/**生成JSP文件绝对路径*/
	private String filePath="";
	/**模板相对路径*/
	private String templateFileName="";
	/**模板相对路径*/
	private String templateContent="";
	/**版本与皮肤组成的模板路径 如： web/default*/
	private String templetPath="";
	public void init(Object obj)   {
		CmsTagList info = (CmsTagList)obj;
		CmsTemplet cmsTemplet = info.getCmsTemplet();
		fileName = cmsTemplet.getFileName();
		
		templetPath=cmsTemplet.getTempletPath();
		List list = info.getTagListInfo();
		data.put("listData", list);
		filePath=new String( DaoConstant.JSPPATH+templetPath+"/"+fileName+"_"+info.getId()+".jsp");
		
		templateContent = cmsTemplet.getTempletContentString();
		System.out.println("filePath="+filePath);
		System.out.println("templateContent="+templateContent.toString());
	}
	public void generate() {
		// TODO Autso-generated method stub
	    super.generate(data, filePath,templateContent);
	}
	

}

