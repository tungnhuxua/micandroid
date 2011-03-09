package cn.mmbook.platform.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import cn.mmbook.platform.model.base.*;
import cn.mmbook.platform.model.manage.SiteContentSort;
import cn.mmbook.platform.dao.base.*;
import cn.mmbook.platform.service.base.impl.*;
import cn.mmbook.platform.service.base.*;
/**
 * <p> BaseKeywords server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("baseKeywordsManager")
@Transactional
public class BaseKeywordsManagerImpl extends BaseManager<BaseKeywords,java.lang.Integer> 
					implements BaseKeywordsManager {

	private BaseKeywordsDao baseKeywordsDao;
	private BaseKeycontentRealDao baseKeycontentRealDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setBaseKeywordsDao(BaseKeywordsDao dao) {
		this.baseKeywordsDao = dao;
	}
	public void setBaseKeycontentRealDao(BaseKeycontentRealDao dao) {
		this.baseKeycontentRealDao = dao;
	}
	
	
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.baseKeywordsDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return baseKeywordsDao.findByPageRequest(pr);
	}
	
	
	public List getList(BaseKeywords u){
		return baseKeywordsDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return baseKeywordsDao.listPageAnyTable(pr);
	}
 	 /**
		 * 
		 * 得到树下拉框 admin
		 * 
	*/
	public String getTreeCombox(BaseKeywords cmsc , StringBuffer sb,
                int j,String partId) {
			List list = new ArrayList();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", cmsc.getId());
//			map.put("cmscategoryType", cmsc.getCategoryType());
//			map.put("modelId", cmsc.getModelId());
			
			/**需要选中栏目*/
			String[] cidarr = null; 
			if (null!=partId&&!"".equals(partId)) {
				cidarr = partId.split(",");
			}
			
			list = baseKeywordsDao.getListByMap(map);
			if (null != list && list.size() > 0) {
				if (j != 0) {
					sb.append(",'children':[");
				}
				j++;
				for (int i = 0; i < list.size(); i++) {
					BaseKeywords t = (BaseKeywords)list.get(i);
					boolean bool = false;

					for(int mm=0;null!=cidarr&&mm<cidarr.length;mm++){
						if((String.valueOf(t.getId())).equals(cidarr[mm])){
							bool = true;
						}
					}
					if(bool){
					   sb.append("{checked:true,'id':'").append(t.getId()).append("','text':'").append(t.getKeywordsValue() + "'");
					}else{
						sb.append("{checked:false,'id':'").append(t.getId()).append("','text':'").append(t.getKeywordsValue() + "'");
					}
					this.getTreeCombox(t, sb, j,partId);
					if (i + 1 < list.size()) {
						sb.append(",");
					}
				}
				sb.append("],'leaf':false}");
			} else {
				sb.append(",'leaf':true}");
			}
			return sb.toString();
		 
	}
  

}
