package cn.mmbook.platform.service.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javacommon.base.*;
import javacommon.util.*;
import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;
import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.dao.manage.*;
import cn.mmbook.platform.service.manage.impl.*;
import cn.mmbook.platform.service.manage.*;
/**
 * <p> ExtjsTest server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("extjsTestManager")
@Transactional
public class ExtjsTestManagerImpl extends BaseManager<ExtjsTest,java.lang.Integer> 
					implements ExtjsTestManager {

	private ExtjsTestDao extjsTestDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setExtjsTestDao(ExtjsTestDao dao) {
		this.extjsTestDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.extjsTestDao;
	}
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return extjsTestDao.findByPageRequest(pr);
	}
	
	
	public List getList(ExtjsTest u){
		return extjsTestDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return extjsTestDao.listPageAnyTable(pr);
	}
	/**树 数据 查询 返回字符串 */
	public String getTreeCombox(ExtjsTest cmsc,StringBuffer sb,int j){
		List<ExtjsTest> list = new ArrayList<ExtjsTest>();
		System.out.println("###getTreeCombox");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", cmsc.getId());
		list = extjsTestDao.getListByMap(map);
		if (null != list && list.size() > 0) {
			if (j != 0) {
				sb.append(",'children':[");
			}
			j++;
			for (int i = 0; i < list.size(); i++) {
				ExtjsTest t = list.get(i);
				sb.append("{'id':'").append(t.getId()).append("','text':'")
						.append(t.getFunctionName() + "'");
				 
				this.getTreeCombox(t, sb, j);
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
