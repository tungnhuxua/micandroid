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

package org.light.portal.core.service;

import java.util.List;

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
public interface PortletService extends BaseService {
	
	public int getPopularItemTotal(long orgId, String locale);	
	public PopularItem getPopularItemById(long id);	
	public PopularItem getPopularItemByLink(long orgId, String link);	
	public List<PopularItem> getPopularItems(long orgId, int start,int max,String locale);	
	
	public int getViewedItemTotal(String locale, long userId);	
	public ViewedItem getViewedItemById(long id);	
	public ViewedItem getViewedItemNext(long id, long userId, String locale);	
	public ViewedItem getViewedItemByLink(String link);
	public ViewedItemUser getViewedItemUser(long itemId, long userId);	
	public List<ViewedItem> getViewedItems(int start,int max,String locale, long userId);
	
	public int getRecommendedItemTotal(long userId);		
	public RecommendedItem getRecommendedItemById(long id);
	public RecommendedItem getRecommendedItemNext(long id, long userId, String locale);
	public RecommendedItem getRecommendedItemByLink(String link);
	public List<RecommendedItem> getRecommendedItems(int start,int max, long userId);
	public void deleteRecommendedItems(long userId);
	
	public InternalNews getInternalNewsById(long id,long orgId);
	public List<InternalNews> getInternalNews(long orgId);
	
	public List<FlashGame> getFlashGames(long orgId);	
	
	public Widget getWidgetByPortletId(long portletId);
}
