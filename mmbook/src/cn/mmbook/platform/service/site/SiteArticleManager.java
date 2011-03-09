
package cn.mmbook.platform.service.site;

import java.util.List;
import java.util.Map;

import cn.mmbook.platform.model.base.BaseAccessories;
import cn.mmbook.platform.model.site.SiteArticle;
import cn.mmbook.platform.model.site.SiteContent;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
/**
 * <p> SiteArticle server 接口<br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public interface SiteArticleManager {
	/**按ID 查询对象*/
	SiteArticle getById(java.lang.String id);
	/**查询所有数据*/
	List findAll();
	/**查询数据返回LIST*/
	List getList(SiteArticle u);
	/**保存数据 由BaseManager类实现*/
	void save(SiteArticle u);
	void saveInfo(SiteArticle u);
	/**修改数据由BaseManager类实现*/
	void update(SiteArticle u);
	/**删除数据由BaseManager类实现*/
	void removeById (java.lang.String id);
	/**单表分页查询*/
	Page findByPageRequest(PageRequest<Map> q);
	/**多表分页查询*/
	Page listPageAnyTable(PageRequest<Map> q);
	/**修改信息*/
	void updateInfo(SiteArticle siteArticle,SiteContent siteContent,BaseAccessories baseAccessories);
	/**  资讯内容查询 */
	public SiteArticle getSiteArticleInfo(String contentId);
}
