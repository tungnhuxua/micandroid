package org.javaside.cms.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class CategoryManager extends DefaultEntityManager<Category, Long> {

	/**
	 * 获取一级栏目
	 * 
	 * @return
	 */
	public List<Category> getCategoryRoot() {
		List<Category> rs = entityDao.createCriteria(Restrictions.isNull("parent")).addOrder(Order.asc("sort")).list();
		reLoadCategoryChild(rs);
		return rs;
	}

	/**
	 * 递归的把所有子栏目重新load一次，防止新增的栏目不再父栏目的子栏目集合中，以及删除了子栏目，父栏目的子栏目中还存在，已删除的栏目
	 * 
	 * @param list
	 */
	private void reLoadCategoryChild(List<Category> list) {
		for (Category tmp : list) {
			List tlist = entityDao.createCriteria(Restrictions.eq("parent", tmp)).addOrder(Order.asc("sort")).list();
			if (tlist != null && tlist.size() > 0) {
				tmp.setChild(new LinkedHashSet<Category>(tlist));
				this.reLoadCategoryChild(tlist);
			}
		}
	}

	public Page getCategoryList(Category category, Page page) {
		if (category == null || category.getId() == null)
			return entityDao.findByCriteria(page, Restrictions.isNull("parent"));
		return entityDao.findByCriteria(page, Restrictions.eq("parent", category));
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
		Category category = null;
		for (int i = 0; i < sortids.length; i++) {
			category = entityDao.get(sortids[i]);
			Assert.notNull(category);
			category.setSort(sorts[i]);
			save(category);
		}
	}

	/**
	 * 根据action转换成栏目ID
	 * 
	 * @param action
	 * @return
	 */
	public Long getCategoryIdByAction(String action) {
		if ("/news".equals(action) || "/newslist".equals(action) || "/newscontent".equals(action)
				|| "/newspiclist".equals(action) || "/newssearcharticle".equals(action)
				|| "/searcharticlecontent".equals(action))
			return 54L;
		if ("/person".equals(action) || "/personlist".equals(action) || "/personcontent".equals(action)
				|| "/personpiclist".equals(action) || "/personsearcharticle".equals(action))
			return 55L;
		if ("/show".equals(action) || "/showlist".equals(action) || "/showcontent".equals(action)
				|| "/showsearcharticle".equals(action))
			return 56L;
		if ("/special".equals(action) || "/speciallist".equals(action) || "/specialcontent".equals(action)
				|| "/specialpiclist".equals(action) || "/specialsearcharticle".equals(action))
			return 57L;
		if ("/circle".equals(action) || "/circlelist".equals(action) || "/circlecontent".equals(action)
				|| "/circlepiclist".equals(action) || "/circlesearcharticle".equals(action))
			return 58L;
		return 54L;
	}
}
