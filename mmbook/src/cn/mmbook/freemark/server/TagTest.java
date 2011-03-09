package cn.mmbook.freemark.server;


import java.util.ArrayList;
import java.util.List;

import javacommon.util.SpringContextUtil;
import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.platform.model.tag.CmsTagCategory;
import cn.mmbook.platform.service.tag.CmsTagCategoryManager;



public class TagTest extends TestCase {

	private SpringContextUtil springContextUtil;

	protected void setUp() throws Exception {
		String[] param = new String[] {
				"spring/applicationContext-service.xml",
				"spring/applicationContext-resource.xml",
				"spring/applicationContext-ibatis3-dao.xml"  };
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				param);
		BeanFactory factory = context.getBeanFactory();
		// test = (Test) factory.getBean("testAction");
		springContextUtil = (SpringContextUtil) factory
				.getBean("SpringContextUtil");
		super.setUp();
	}

	protected void tearDown() throws Exception {
		if (null != springContextUtil) {
			springContextUtil = null;
		}
		super.tearDown();
	}

	public void testFindBusiByPageRequest() throws Exception {
		teatcc(); 
		//createTag();
		//getSiteChannelList(); 
	}
	/**
	 * 测试 入口
	 */
 
	public void createTag(){
		DaoGenerator daoGenerator= null;
		List tagList = new ArrayList();
		tagList.add((DaoGenerator)springContextUtil.getBean("head"));
		tagList.add((DaoGenerator)springContextUtil.getBean("floor"));
		tagList.add((DaoGenerator)springContextUtil.getBean("inc"));
		tagList.add((DaoGenerator)springContextUtil.getBean("contentlist"));
		tagList.add((DaoGenerator)springContextUtil.getBean("channelsList"));
		tagList.add((DaoGenerator)springContextUtil.getBean("index"));
        for (int i = 0; i < tagList.size(); i++) {
        	daoGenerator = (DaoGenerator) tagList.get(i);
    		daoGenerator.init(new Object());
    		daoGenerator.generate();
    		daoGenerator=null;
        }
	}
	public void teatcc(){
		CmsTagCategoryManager cmsTagCategoryManager = (CmsTagCategoryManager)springContextUtil.getBean("cmsTagCategoryManager"); 
		CmsTagCategory u = cmsTagCategoryManager.getById("564");
		cmsTagCategoryManager.saveCmsTagCategory(u);
	}
}
