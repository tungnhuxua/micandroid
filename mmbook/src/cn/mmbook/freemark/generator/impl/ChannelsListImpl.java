package cn.mmbook.freemark.generator.impl;

import java.util.*;
import javacommon.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.freemark.constant.DaoConstant;
import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.freemark.model.DaoModel;
import cn.mmbook.platform.facade.TagService;
import cn.mmbook.platform.model.manage.SiteChannels;
import cn.org.rapid_framework.page.Page;


@Component("channelsList")
@Transactional
public class ChannelsListImpl  extends AbstractGenerator implements DaoGenerator{


	private TagService tagService;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setTagService(TagService manager) {
		this.tagService = manager;
	}
 
	
	
	private String fileName="";
	private String filePath="";
	private String templateFileName="";
	public void init(Object obj) {
		// TODO Auto-generated method stub
		fileName = "channel_list.jsp";
		filePath=new String("web/site/"+"page/web/default"+"/"+fileName);
		templateFileName="GenTemplate/web/default/channel_list.ftl";
	}
	public void generate() {
		// TODO Auto-generated method stub
	    
		Map map_param = new HashMap();
		map_param.put("siteId", "1");
 		map_param.put("count", "10");
		map_param.put("page", "2");
		map_param.put("sortColumns", " insert_time_ desc"); 
		Page page_info = tagService.getSiteChannelList(map_param);
		
	    Map<String, Object> data = new HashMap<String, Object>();
	    List list_info = page_info.getResult();
	    System.out.println(list_info.size());
	    data.put("SiteChannelList", list_info);
	    map_param = new HashMap();
	    map_param.put("siteId", "1");
	    List channelAndpart = tagService.getAllChannelPart(map_param); 
	    data.put("channelAndpart", channelAndpart);
	    super.generate(data, filePath ,templateFileName);
	}


}
