package org.javaside.cms.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.DownloadType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class DownloadTypeManager extends DefaultEntityManager<DownloadType, Long> {
	/**
	 * 获取一级栏目
	 * 
	 * @return
	 */
	public List<DownloadType> getDownloadTypeRoot() {
		List<DownloadType> rs = entityDao.createCriteria(Restrictions.isNull("parent")).addOrder(Order.asc("sort"))
				.list();
		reLoadDownloadTypeChild(rs);
		return rs;
	}

	/**
	 * 递归的把所有子分类重新load一次，防止新增的分类不再父分类的子分类集合中，以及删除了子分类，父分类的子分类中还存在，已删除的分类
	 * 
	 * @param list
	 */
	private void reLoadDownloadTypeChild(List<DownloadType> list) {
		for (DownloadType tmp : list) {
			List tlist = entityDao.createCriteria(Restrictions.eq("parent", tmp)).addOrder(Order.asc("sort")).list();
			if (tlist != null && tlist.size() > 0) {
				tmp.setChild(new LinkedHashSet<DownloadType>(tlist));
				this.reLoadDownloadTypeChild(tlist);
			}
		}
	}

	/**
	 * 根据分类获取子分类.
	 * 
	 * @param type
	 * @param page
	 * @return
	 */
	public Page getDownloadTypeList(DownloadType type, Page page) {
		if (type == null || type.getId() == null)
			return entityDao.findByCriteria(page, Restrictions.isNull("parent"));
		return entityDao.findByCriteria(page, Restrictions.eq("parent", type));
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
	 * 设置栏目排序
	 * 
	 * @param sortids
	 * @param sorts
	 */
	public void updateSort(Long[] sortids, Integer[] sorts) {
		Assert.notNull(sortids);
		Assert.notNull(sorts);
		DownloadType type = null;
		for (int i = 0; i < sortids.length; i++) {
			type = entityDao.get(sortids[i]);
			Assert.notNull(type);
			type.setSort(sorts[i]);
			save(type);
		}
	}

}
