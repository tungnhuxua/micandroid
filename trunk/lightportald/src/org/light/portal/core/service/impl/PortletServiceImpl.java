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

package org.light.portal.core.service.impl;

import java.util.List;

import org.light.portal.core.dao.PortletDao;
import org.light.portal.core.service.PortletService;
import org.light.portal.model.FlashGame;
import org.light.portal.model.PopularItem;
import org.light.portal.model.RecommendedItem;
import org.light.portal.model.ViewedItem;
import org.light.portal.model.ViewedItemUser;
import org.light.portlets.internal.InternalNews;
import org.light.portlets.widget.Widget;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortletServiceImpl extends BaseServiceImpl implements PortletService {
	private PortletDao portletDao;
	
	public PopularItem getPopularItemById(long id){
		PopularItem entity = (PopularItem)getCacheService().getObject(PopularItem.class,id);
		if(entity == null){
			entity = portletDao.getPopularItemById(id);
			getCacheService().setObject(PopularItem.class,id,entity);
		}
		return entity;
	}
	
	public List<PopularItem> getPopularItems(long orgId, int start, int max, String locale){
	    return portletDao.getPopularItems(orgId, start, max, locale);
	}
	public int getPopularItemTotal(long orgId, String locale){
		return portletDao.getPopularItemTotal(orgId, locale);
	}
	public PopularItem getPopularItemByLink(long orgId, String link){
		PopularItem entity = (PopularItem)getCacheService().getObject(PopularItem.class,link);
		if(entity == null){
			entity = portletDao.getPopularItemByLink(orgId, link);
			getCacheService().setObject(PopularItem.class,link,entity);
		}
		return entity;
	}
	public List<ViewedItem> getViewedItems(int start, int max,String locale, long userId){
		return portletDao.getViewedItems(start,max,locale,userId);
	}
	public int getViewedItemTotal(String locale, long userId){
		return portletDao.getViewedItemTotal(locale,userId);
	}
	public ViewedItem getViewedItemNext(long id, long userId, String locale){
		return portletDao.getViewedItemNext(id,userId,locale);
	}
	public ViewedItem getViewedItemById(long id){
		ViewedItem entity = (ViewedItem)getCacheService().getObject(ViewedItem.class,id);
		if(entity == null){
			entity = portletDao.getViewedItemById(id);
			getCacheService().setObject(ViewedItem.class,id,entity);
		}
		return entity;
	}
	public ViewedItem getViewedItemByLink(String link){
		ViewedItem entity = (ViewedItem)getCacheService().getObject(ViewedItem.class,link);
		if(entity == null){
			entity = portletDao.getViewedItemByLink(link);
			getCacheService().setObject(ViewedItem.class,link,entity);
		}
		return entity;
	}
	public ViewedItemUser getViewedItemUser(long itemId, long userId){
		String key = userId+"_"+itemId;
		ViewedItemUser entity = (ViewedItemUser)getCacheService().getObject(ViewedItemUser.class,key);
		if(entity == null){
			entity = portletDao.getViewedItemUser(itemId,userId);
			getCacheService().setObject(ViewedItemUser.class,key,entity);
		}
		return entity;
	}
	
	public List<RecommendedItem> getRecommendedItems(int start,int max, long userId){
		return portletDao.getRecommendedItems(start,max,userId);
	}
	public void deleteRecommendedItems(long userId){
		portletDao.deleteRecommendedItems(userId);
	}
	public int getRecommendedItemTotal(long userId){
		return portletDao.getRecommendedItemTotal(userId);
	}
	public RecommendedItem getRecommendedItemNext(long id, long userId, String locale){
		return portletDao.getRecommendedItemNext(id, userId, locale);
	}
	public RecommendedItem getRecommendedItemById(long id){
		RecommendedItem entity = (RecommendedItem)getCacheService().getObject(RecommendedItem.class,id);
		if(entity == null){
			entity = portletDao.getRecommendedItemById(id);
			getCacheService().setObject(RecommendedItem.class,id,entity);
		}
		return entity;
	}
	public RecommendedItem getRecommendedItemByLink(String link){
		RecommendedItem entity = (RecommendedItem)getCacheService().getObject(RecommendedItem.class,link);
		if(entity == null){
			entity = portletDao.getRecommendedItemByLink(link);
			getCacheService().setObject(RecommendedItem.class,link,entity);
		}
		return entity;
	}
	public List<FlashGame> getFlashGames(long orgId){
		List<FlashGame> list = (List<FlashGame>)getCacheService().getList(FlashGame.class,orgId);
		if(list == null){
			list = portletDao.getFlashGames(orgId);
			getCacheService().setList(FlashGame.class,orgId,list);
		}
		return list;
	}
	public InternalNews getInternalNewsById(long id,long orgId){
		InternalNews entry = (InternalNews)getCacheService().getObject(InternalNews.class, id);
		if(entry == null){
			entry = portletDao.getInternalNewsById(id);
			getCacheService().setObject(InternalNews.class, id, entry);
		}
		return entry;
	}
	public List<InternalNews> getInternalNews(long orgId){
		List<InternalNews> news = (List<InternalNews>)getCacheService().getList(InternalNews.class, orgId);
		if(news == null){
			news = portletDao.getInternalNews(orgId);
			getCacheService().setList(InternalNews.class,orgId,news);
		}
		return news;
	}
		
	public Widget getWidgetByPortletId(long portletId){
		Widget entity = (Widget)getCacheService().getObject(Widget.class,portletId);
		if(entity == null){
			entity = portletDao.getWidgetByPortletId(portletId);
			getCacheService().setObject(Widget.class,portletId,entity);
		}
		return entity;
	}
		
	public PortletDao getPortletDao() {
		return portletDao;
	}

	public void setPortletDao(PortletDao portletDao) {
		this.portletDao = portletDao;
	}

}
