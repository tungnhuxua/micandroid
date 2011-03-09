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

package org.light.portal.core.dao;

import java.util.List;

import org.light.portal.layout.config.PortalLayout;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectPreferences;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;
import org.light.portal.portlet.config.Portlets;
import org.light.portal.portlet.definition.PortletApp;

/**
 * 
 * @author Jianmin Liu
 **/
public interface PortalDao extends BaseDao {
	public void init(PortletApp portletApp,Portlets portlets,PortalLayout portalLayout);
	
	public Portal createPortalByUser(User user);
	
	public Portal getPortalById(long id);
	public Portal getPortalByUser(String userId, long orgId);
	public Portal getPortalByChannel(String channel,long orgId);
	public Portal getPortalByGroup(String portalId,long orgId);	
	
	public PortalTab getPortalTabById(long id);
	public PortalTab getPortalTabByUrl(String url,long orgId);
	
	public List<PortalTab> getPortalTabByPortalId(long portalId);
	public List<PortalTab> getPortalTabByUser(User user, Organization org);
	public List<PortalTab> getPortalTabByOwner(String owner, Organization org);
	public List<PortalTab> getUserPersonalPortalTab(String userId,long orgId);
	public List<PortalTab> getUserProfilePortalTab(String userId,long orgId);
	public List<PortalTab> getPortalTabByParent(long parentId);
	
	public PortletObject getPortletById(long id);
	public List<PortletObject> getPortletsByTab(long tabId);
	
	public List<PortletObjectPreferences> getPortletPreferences(long portletId);
	
	public PortletObjectRef getPortletRefById(long id);
	public PortletObjectRef getPortletRefByName(String name);	
	
	public List<PortletObjectRef> getPortletRefsByUser(String userId);
	public List<PortletObjectRef> getPortletRefsByUserAndKeyword(String userId, String keyword);
	public List<PortletObjectRef> getMyFeed(String userId);
	public List<PortletObjectRef> getAllFeed();
	public List<PortletObjectRef> getPortletRefByRegion(String region);
	public List<PortletObjectRef> getFeedsByUrl(String url);
	public List<String> getFeedsByLanguage(String language);
	
	public int getTabPortletsMaxRowByColoumn(long tabId, int column);	
	public void deletePortletsByTab(long tabId);
	public boolean isNewUri(String uri,long orgId);
		
}
