package cn.mmbook.freemark.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.util.SpringContextUtil;
import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mmbook.platform.facade.TagService;
import cn.mmbook.platform.service.manage.SitePartManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;



public class Test extends TestCase {

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
		//infoText(); 
		//getSiteChannelList();
		//getSoftByPartId();
		//testSitePart();
		testContentList();
	}
	/**
	 * 测试 入口
	 */
 
//	public void getSiteChannelList(){
//		TagService tagService= (TagService)springContextUtil.getBean("tagService");
//		/**条件自己写*/
//		Map map_param = new HashMap();
//
//		 map_param.put("siteId", "1");
//		 List channelAndpart = tagService.getAllChannelPart(map_param); 
//	}
//	public void getSoftByPartId(){
//		TagService tagService= (TagService)springContextUtil.getBean("tagService");
//		Map map_param = new HashMap();
// 
//			map_param.put("sitePartId", "3");
//	 		map_param.put("count", "0");
//			map_param.put("start", "1");
//	 		map_param.put("sort", " t1.id_ desc"); 
//			Page map_info = tagService.getSoftByPartId(map_param);
//	}
//
//	public void testSitePart(){
//		SitePartManager sitePartManager= (SitePartManager)springContextUtil.getBean("sitePartManager");
// 
//	 		PageRequest pagex = new PageRequest();
//	 		pagex.setPageNumber(1);
//	 		pagex.setPageSize(10);
//	 		 
//			Page map_info = sitePartManager.findByPageRequest(pagex);
//	}
	public void testContentList(){
		TagService tagService= (TagService)springContextUtil.getBean("tagService");
		/**条件自己写*/
		Map map_param = new HashMap();
		map_param.put("sort", " id_ desc ");
		map_param.put("SortId", "5");
		map_param.put("start", "0");
		map_param.put("count", "10"); 
	    Page map_xxinfo = tagService.getSiteContentBySortId(map_param);
	}
}
