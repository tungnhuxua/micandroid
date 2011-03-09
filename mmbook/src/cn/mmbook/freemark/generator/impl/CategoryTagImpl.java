package cn.mmbook.freemark.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.mmbook.freemark.constant.DaoConstant;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.freemark.util.ConfigurationHelper;
import cn.mmbook.platform.facade.TagService;
import cn.mmbook.platform.model.tag.CmsTagCategory;
import cn.mmbook.platform.model.tag.CmsTemplet;
import freemarker.template.Template;


/**
 * 生成频道、栏目、类别 标签 功能实现类
 * @author qiongguo
 *
 */


@Component("categorytag")
@Transactional
public class CategoryTagImpl  extends AbstractGenerator implements DaoGenerator{


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
		CmsTagCategory info = (CmsTagCategory)obj;
		CmsTemplet cmsTemplet = info.getCmsTemplet();
		fileName = cmsTemplet.getFileName();
		
		templetPath=cmsTemplet.getTempletPath();
		List list = info.getTagListInfo();
		data.put("listData", list);
		filePath=new String( DaoConstant.JSPPATH+templetPath+"/"+fileName+".jsp");
		
		templateContent = cmsTemplet.getTempletContentString();
		
	}
	public void generate() {
		// TODO Autso-generated method stub
	    super.generate(data, filePath,templateContent);
	}
	


	
}



