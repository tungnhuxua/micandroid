package org.javaside.cms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.DownloadResource;
import org.javaside.cms.entity.DownloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class DownloadResourceManager extends DefaultEntityManager<DownloadResource, Long> {

	@Autowired
	private DownloadTypeManager typeManager;

	/**
	 * 按资源分类获取资源
	 * 
	 * @param type
	 * @param page
	 * @return
	 */
	public Page getResourceOfType(DownloadType type, Page page) {
		if (type == null || type.getId() == null)
			return getAll(page);
		type = typeManager.get(type.getId());
		Set set = getTypeAllChild(type);
		return entityDao.findByCriteria(page, Restrictions.in("type", set));
	}

	/**
	 * 是否推荐
	 * 
	 * @param page
	 * @return
	 */
	public Page getRecommendResource(Page page) {
		return entityDao.findByCriteria(page, Restrictions.gt("recommend", 0));
	}

	/**
	 * 按最新下载 人气 排行
	 * 
	 * @param type
	 * @param datatype
	 *            createDate 时间
	 * @param page
	 * @return
	 */
	public Page getTopHotResource(DownloadType type, String datatype, Page page) {
		page.setOrderBy(datatype);
		page.setOrder(page.DESC);
		return entityDao.findByCriteria(page, Restrictions.in("type", type.getChild()));
	}

	/**
	 * 下载搜索 location:downloadsearch
	 * 
	 * @param page
	 * @param SearchContent
	 * @return
	 */
	public Page getResourceSearch(Page page, String SearchContent) {
		return entityDao.findByCriteria(page, Restrictions.like("name", "%" + SearchContent + "%"));
	}

	/**
	 * 按资源标题首字母查找资源
	 * 
	 * @param letter
	 * @param page
	 * @return
	 */
	public Page getResourceByLetter(String letter, Page page) {
		return entityDao.findByCriteria(page, Restrictions.like("letter", "%" + letter + "%"));
	}

	/**
	 * 获取资源分类的所有子分类,包括自身
	 * 
	 * @param type
	 * @return
	 */
	private Set<DownloadType> getTypeAllChild(DownloadType type) {
		Set<DownloadType> rs = new HashSet<DownloadType>();
		rs.add(type);
		for (DownloadType tmp : type.getChild()) {
			rs.addAll(getTypeAllChild(tmp));
		}
		return rs;
	}

	/**
	 * 根据栏目ID集合批量删除栏目
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	/**
	 * 首页 最新下载
	 * 
	 * @param type
	 * @param datatype
	 *            createDate 时间
	 * @param page
	 * @return
	 */
	public List<DownloadResource> getTopResource(DownloadType type, String datatype, int rows) {
		Page<DownloadResource> page = new Page<DownloadResource>(rows);
		page.setOrderBy(datatype);
		page.setOrder(page.DESC);
		page = entityDao.findByCriteria(page, Restrictions.in("type", type.getChild()));
		return page.getResult();
	}

	/**
	 * 精选图片 location:downloadcontent
	 * 
	 * @param type
	 * @param datatype
	 * @param rows
	 * @return
	 */
	public List<DownloadResource> getRecommendPic(DownloadType type, String datatype, int rows) {
		Page<DownloadResource> page = new Page<DownloadResource>(rows);
		page.setOrderBy(datatype);
		page.setOrder(page.DESC);
		page = entityDao.findByCriteria(page, Restrictions.gt("recommend", 0));
		return page.getResult();

	}

	/**
	 * 同类型资源 location:downloadcontent
	 * 
	 * @param type
	 * @param datatype
	 * @param rows
	 * @return
	 */
	public List<DownloadResource> getHomogeneousResource(DownloadType type, String datatype, int rows) {
		type = typeManager.get(type.getId());
		Set set = getTypeAllChild(type);
		Page<DownloadResource> page = new Page<DownloadResource>(rows);
		page.setOrder(page.DESC);
		page.setOrderBy(datatype);
		page = entityDao.findByCriteria(page, Restrictions.in("type", set));
		return page.getResult();
	}

	/**
	 * 相关主题图片 location:downloadcontent
	 * 
	 * @param type
	 * @param tag
	 * @param rows
	 * @return
	 */
	public List<DownloadResource> getCorrelationPic(DownloadType type, String tag, int rows) {
		Page<DownloadResource> page = new Page<DownloadResource>(rows);
		page.setOrder(page.DESC);
		page.setOrderBy("createDate");
		page = entityDao.findByCriteria(page, Restrictions.like("tag", "%" + tag + "%"));
		return page.getResult();
	}

}
