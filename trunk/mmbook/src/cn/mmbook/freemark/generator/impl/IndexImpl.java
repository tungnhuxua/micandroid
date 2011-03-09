package cn.mmbook.freemark.generator.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.platform.facade.TagService;
import cn.org.rapid_framework.page.Page;



@Component("index")
@Transactional
public class IndexImpl  extends AbstractGenerator implements DaoGenerator{


	private TagService tagService;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTagService(TagService manager) {
		this.tagService = manager;
	}
	
	
	
	public void genDaoInterface(String fileName){
	}
	public void genDaoImpl(String fileName){
	}
	public void genAll(String objectName) {
	}
	public void genSitePartPage() {
	}
	public void genIndex() {
	}
	
	
	
	private String fileName="";
	private String filePath="";
	private String templateFileName="";
	public void init(Object obj) {
		// TODO Auto-generated method stub
		fileName = "index.jsp";
		filePath=new String("web/site/"+"page/web/default"+"/"+fileName);
		templateFileName="GenTemplate/web/default/index.ftl";
	}
	public void generate() {
		// TODO Auto-generated method stub
	    Map<String, Object> data = new HashMap<String, Object>();

	    super.generate(templateFileName, data, filePath);
	}


}
