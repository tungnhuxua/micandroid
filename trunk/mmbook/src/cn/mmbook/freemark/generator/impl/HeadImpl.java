package cn.mmbook.freemark.generator.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.platform.facade.TagService;


@Component("head")
@Transactional
public class HeadImpl    extends AbstractGenerator implements DaoGenerator{


	private TagService tagService;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTagService(TagService manager) {
		this.tagService = manager;
	}
	
	
 
	
	private String fileName="";
	private String filePath="";
	private String templateFileName="";
	private String siteName="";
	public void init(Object obj) {
		// TODO Auto-generated method stub
		fileName = "head.jsp";
		filePath=new String("web/site/"+"page/web/default"+"/"+fileName);
		templateFileName="GenTemplate/web/default/head.ftl";
		siteName="MMBOOK";
	}
	public void generate() {
		// TODO Auto-generated method stub 
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("siteName", siteName);
	    super.generate(templateFileName, data, filePath);
	}


}
