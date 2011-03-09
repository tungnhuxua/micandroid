package org.javaside.cms.service;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Ads;
import org.javaside.cms.entity.AdsLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AdsManager extends DefaultEntityManager<Ads, Long> {
	@Autowired
	private AdsLocationManager locationManager;

	/**
	 * 根据广告位查询广告，如果广告位为NULL则查询所有广告
	 * 
	 * @param location
	 * @param page
	 * @return
	 */
	public Page<Ads> getAdsByLocation(AdsLocation location, Page<Ads> page) {

		if (location == null)
			return this.entityDao.findByCriteria(page);
		else {
			Long[] ids = new Long[] { -1L };
			if (location.getAds() != null && location.getAds().size() > 0) {
				ids = new Long[location.getAds().size()];
				int i = 0;
				for (Ads ads : location.getAds()) {
					ids[i] = ads.getId();
					i++;
				}
			}
			return this.entityDao.findByCriteria(page, Restrictions.in("id", ids));
		}
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
}
