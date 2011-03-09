 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portlets.ad.service.impl;

import java.util.List;

import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portlets.ad.CategoryAd;
import org.light.portlets.ad.dao.AdDao;
import org.light.portlets.ad.service.AdService;

/**
 * 
 * @author Jianmin Liu
 **/
public class AdServiceImpl extends BaseServiceImpl implements AdService{
	private AdDao adDao;
	
	public CategoryAd getAdById(int id){
	    return adDao.getAdById(id);	
	}

	public List<CategoryAd> getAdsByType(String type, int showNumber){
		return adDao.getAdsByType(type,showNumber);
	}
	
	public List<CategoryAd> getAdsByCategory(int category, int showNumber,String country,String province,String city){
		return adDao.getAdsByCategory(category,showNumber,country,province,city);
	}
	
	public AdDao getAdDao() {
		return adDao;
	}
	public void setAdDao(AdDao adDao) {
		this.adDao = adDao;
	}
}
