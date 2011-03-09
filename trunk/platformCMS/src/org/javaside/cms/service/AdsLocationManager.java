package org.javaside.cms.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Ads;
import org.javaside.cms.entity.AdsLocation;
import org.javaside.cms.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class AdsLocationManager extends DefaultEntityManager<AdsLocation, Long> {
	@Autowired
	private AdsManager adsManager;

	/**
	 * 获取一级广告位置
	 * 
	 * @return
	 */
	public List<AdsLocation> getLocationRoot() {
		List<AdsLocation> rs = entityDao.createCriteria(Restrictions.isNull("parent")).addOrder(Order.asc("sort"))
				.list();
		reLoadCategoryChild(rs);
		return rs;
	}

	/**
	 * 递归的把所有子广告位置重新load一次，防止新增的广告位置不再父广告位置的子广告位置集合中，以及删除了子广告位置，
	 * 父栏目的子广告位置中还存在已删除的广告位置
	 * 
	 * @param list
	 */
	private void reLoadCategoryChild(List<AdsLocation> list) {
		for (AdsLocation tmp : list) {
			List tlist = entityDao.createCriteria(Restrictions.eq("parent", tmp)).addOrder(Order.asc("sort")).list();
			if (tlist != null && tlist.size() > 0) {
				tmp.setChild(new LinkedHashSet<AdsLocation>(tlist));
				this.reLoadCategoryChild(tlist);
			}
		}
	}

	/**
	 * 分页查询指定广告位置的子位置
	 * 
	 * @param location
	 * @param page
	 * @return
	 */
	public Page getAdsLocationList(AdsLocation location, Page page) {
		if (location == null || location.getId() == null)
			return entityDao.findByCriteria(page, Restrictions.isNull("parent"));
		return entityDao.findByCriteria(page, Restrictions.eq("parent", location));
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	/**
	 * 设置广告位置排序
	 * 
	 * @param sortids
	 * @param sorts
	 */
	public void updateSort(Long[] sortids, Integer[] sorts) {
		Assert.notNull(sortids);
		Assert.notNull(sorts);
		AdsLocation location = null;
		for (int i = 0; i < sortids.length; i++) {
			location = entityDao.get(sortids[i]);
			Assert.notNull(location);
			location.setSort(sorts[i]);
			save(location);
		}
	}

	/**
	 * 根据栏目比配对应的栏目首页广告位的ID；
	 * 
	 * @param category
	 * @return
	 */
	public Long getCategoryHomeLocationId(Category category) {

		if (54 == category.getId().intValue())
			return 4L;
		else if (55 == category.getId().intValue())
			return 31L;
		else if (56 == category.getId().intValue())
			return 88L;
		else if (57 == category.getId().intValue())
			return 51L;
		else if (58 == category.getId().intValue())
			return 71L;
		return 19L;
	}

	/**
	 * 根据栏目比配对应的栏目列表广告位的ID；
	 * 
	 * @param category
	 * @return
	 */
	public Long getCategoryListLocationId(Category category) {

		if (54 == category.getId().intValue())
			return 5L;
		else if (55 == category.getId().intValue())
			return 32L;
		else if (56 == category.getId().intValue())
			return 89L;
		else if (57 == category.getId().intValue())
			return 52L;
		else if (58 == category.getId().intValue())
			return 72L;
		return 19L;
	}

	/**
	 * 根据栏目比配对应的栏目内容广告位的ID；
	 * 
	 * @param category
	 * @return
	 */
	public Long getArticleLocationId(Category category) {

		if (54 == category.getId().intValue())
			return 116L;
		else if (55 == category.getId().intValue())
			return 126L;
		else if (56 == category.getId().intValue())
			return 159L;
		else if (57 == category.getId().intValue())
			return 136L;
		else if (58 == category.getId().intValue())
			return 146L;
		return 19L;
	}

	/**
	 * 根据广告位ID查询对应子广告位的广告。
	 * 
	 * @param id
	 * @return
	 */
	public Ads[] getAdsLocation(Long id) {
		AdsLocation location = get(id);
		Ads[] rs = null;
		if (location.getChild() != null && location.getChild().size() > 0) {
			rs = new Ads[location.getChild().size()];
			int i = 0;
			//取出子广告位，并从子广告位取出第一个广告
			for (AdsLocation tmp : location.getChild()) {
				if (tmp.getAds() != null && tmp.getAds().size() > 0) {
					Ads[] tp = new Ads[tmp.getAds().size()];
					tp = tmp.getAds().toArray(tp);
					rs[i] = tp[0];
				} else {
					rs[i] = null;
				}
				i++;
			}
		}

		return rs;
	}
}
