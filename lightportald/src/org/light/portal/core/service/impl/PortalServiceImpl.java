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

import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._MEMBER_SHOW_DEFAULT_PAGE;

import java.util.LinkedList;
import java.util.List;

import org.light.portal.cache.CacheService;
import org.light.portal.core.dao.PortalDao;
import org.light.portal.core.service.PortalService;
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
import org.light.portal.util.PropUtil;
/**
 * 
 * @author Jianmin Liu
 **/
public class PortalServiceImpl extends BaseServiceImpl implements PortalService {
	private PortalDao portalDao;
	
	public void init(PortletApp portletApp, Portlets portlets,PortalLayout portalLayout){
		portalDao.init(portletApp,portlets,portalLayout);
	}
	
	public Portal getPortalById(long id){
		Portal portal = getCacheService().getPortal(id);
		if(portal == null){
			portal = portalDao.getPortalById(id); 
			getCacheService().setPortal(portal);
		}
		return portal;
	}
	
	public Portal getPortalByUser(String userId, long orgId){
		Portal portal = getCacheService().getPortal(orgId+userId);
		if(portal == null){
			portal = portalDao.getPortalByUser(userId,orgId); 
			getCacheService().setPortal(portal);
		}
		return portal;
	}
	
	public Portal getPortalByGroup(String ownerId,long orgId){
		Portal portal = getCacheService().getPortal(orgId+ownerId);
		if(portal == null){
			portal = portalDao.getPortalByGroup(ownerId,orgId);
			getCacheService().setPortal(portal);			
		}
		return portal;
	}

	public Portal getPortalByChannel(String channel, long orgId){
		Portal portal = getCacheService().getPortal(orgId+channel);
		if(portal == null){
			portal = portalDao.getPortalByChannel(channel,orgId);
			getCacheService().setPortal(portal);			
		}
		return portal;		
	}
	
	public Portal createPortalByUser(User user){
		Portal portal = portalDao.createPortalByUser(user);
		getCacheService().setPortal(portal);
		return portal;
	}
		
	public List<PortalTab> getPortalTabByUser(User user, Organization org){
		List<PortalTab> tabs = getCacheService().getPortalTabs(org.getId()+CacheService.SEPARATOR+user.getId());
		if(tabs == null){
			tabs = portalDao.getPortalTabByUser(user,org);			
			getCacheService().setPortalTabs(org.getId()+CacheService.SEPARATOR+user.getId(),tabs);
		}				
		if(PropUtil.getInt(_MEMBER_SHOW_DEFAULT_PAGE) == 1 && user != null && user.getId() != org.getUserId()){
			List<PortalTab> alltabs = new LinkedList<PortalTab>();
			alltabs.addAll(tabs);
			alltabs.addAll(0,this.getPortalTabByOwner(org.getUser().getUserId(),org));	
			return alltabs;
		}
		return tabs;
	}
	public List<PortalTab> getPortalTabByOwner(String owner, Organization org){
		List<PortalTab> tabs = getCacheService().getPortalTabs(org.getId()+owner);
		if(tabs == null){
			tabs = portalDao.getPortalTabByOwner(owner,org);
			getCacheService().setPortalTabs(org.getId()+owner,tabs);
		}		
		return tabs;
	}
	public List<PortalTab> getPortalTabByGroup(long id, Organization org){
		return getPortalTabByOwner(_GROUP_PREFIX+id,org);
	}
	public List<PortalTab> getUserPersonalPortalTab(String userId,long orgId){
		String key = "Personal"+orgId+userId;
		List<PortalTab> tabs = getCacheService().getPortalTabs(key);
		if(tabs == null){
			tabs = portalDao.getUserPersonalPortalTab(userId,orgId);
			getCacheService().setPortalTabs(key,tabs);
		}
		return tabs;
	}
	public List<PortalTab> getUserProfilePortalTab(String userId,long orgId){
		String key = "Profile"+orgId+userId;
		List<PortalTab> tabs = getCacheService().getPortalTabs(key);
		if(tabs == null){
			tabs = portalDao.getUserProfilePortalTab(userId,orgId);
			getCacheService().setPortalTabs(key,tabs);
		}
		return tabs;
	}
	public List<PortalTab> getPortalTabByParent(long parentId){
		List<PortalTab> tabs = getCacheService().getPortalTabs(String.valueOf(parentId));
		if(tabs == null){
			tabs = portalDao.getPortalTabByParent(parentId);
			getCacheService().setPortalTabs(String.valueOf(parentId),tabs);
		}
		return tabs;
	}
	
	public PortalTab getPortalTabById(long id){
		PortalTab tab = getCacheService().getPortalTab(id);
		if(tab == null){
			tab = portalDao.getPortalTabById(id);
			getCacheService().setPortalTab(tab);
		}
		return tab;
	}
	public PortalTab getPortalTabByUrl(String url,long orgId){
		PortalTab tab = getCacheService().getPortalTab(url);
		if(tab == null){
			tab = portalDao.getPortalTabByUrl(url,orgId);
			getCacheService().setPortalTab(tab);
		}
		return tab;		
	}
	public List<PortletObject> getPortletsByTab(long tabId){
		List<PortletObject> portlets = getCacheService().getPortlets(tabId);
		if(portlets == null){
			portlets = portalDao.getPortletsByTab(tabId);
			getCacheService().setPortlets(tabId,portlets);
		}
		return portlets;		
	}
	public PortletObject getPortletById(long id){
		Object obj = getCacheService().getObject(PortletObject.class,id);
		if(obj == null){
			PortletObject portlet = portalDao.getPortletById(id);
			if(portlet != null)
				getCacheService().setObject(PortletObject.class,id,portlet);
			else
				getCacheService().setObject(PortletObject.class,id,CacheService.NULL);
			return portlet;
		}else{
			if(obj instanceof PortletObject)
				return (PortletObject)obj;
			else
				return null;
		}
	}
	public List<PortletObjectPreferences> getPortletPreferences(long portletId){
		List<PortletObjectPreferences> preferences = getCacheService().getPortletPreferences(portletId);
		if(preferences == null){
			preferences = portalDao.getPortletPreferences(portletId);
			getCacheService().setPortletPreferences(portletId,preferences);
		}
		return preferences;
	}
	public int getTabPortletsMaxRowByColoumn(long tabId, int column){
		return portalDao.getTabPortletsMaxRowByColoumn(tabId,column);
	}
	public List<PortletObjectRef> getPortletRefsByUser(String userId){
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(userId);
		if(refs == null){
			refs = portalDao.getPortletRefsByUser(userId);
			getCacheService().setPortletRefs(userId,refs);
		}
		return refs;		 
	}	
	public List<PortletObjectRef> getPortletRefsByUserAndKeyword(String userId, String keyword){
		String key = userId+keyword;
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(key);
		if(refs == null){
			refs = portalDao.getPortletRefsByUserAndKeyword(userId,keyword);
			getCacheService().setList(PortletObjectRef.class,key,refs,true);
		}
		return refs;	
	}
	public List<PortletObjectRef> getMyFeed(String userId){
		String key = "myfeed"+userId;
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(key);
		if(refs == null){
			refs = portalDao.getMyFeed(userId);
			getCacheService().setList(PortletObjectRef.class,key,refs,true);
		}
		return refs;	
	}
	public List<PortletObjectRef> getAllFeed(){
		String key = "allfeed";
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(key);
		if(refs == null){
			refs = portalDao.getAllFeed();
			getCacheService().setList(PortletObjectRef.class,key,refs,true);
		}
		return refs;	 
	}
	
	public List<PortletObjectRef>getPortletRefByRegion(String region){
		String key = "region"+region;
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(key);
		if(refs == null){
			refs = portalDao.getPortletRefByRegion(region);
			getCacheService().setList(PortletObjectRef.class,key,refs,true);
		}
		return refs;	
	}
		
	public List<PortletObjectRef> getFeedsByUrl(String url){
		String key = "url"+url;
		List<PortletObjectRef> refs = getCacheService().getPortletRefs(key);
		if(refs == null){
			refs = portalDao.getFeedsByUrl(url);
			getCacheService().setList(PortletObjectRef.class,key,refs,true);
		}
		return refs;
	}
	
	public PortletObjectRef getPortletRefById(long id){
		PortletObjectRef ref = getCacheService().getPortletRef(String.valueOf(id));
		if(ref == null){
			ref = portalDao.getPortletRefById(id);
			getCacheService().setPortletRef(String.valueOf(id),ref);
			getCacheService().setPortletRef(ref.getName(),ref);
		}
		return ref;
	}
	
	public PortletObjectRef getPortletRefByName(String name){
		PortletObjectRef ref = getCacheService().getPortletRef(name);
		if(ref == null){
			ref = portalDao.getPortletRefByName(name);
			getCacheService().setPortletRef(String.valueOf(ref.getId()),ref);
			getCacheService().setPortletRef(name,ref);
		}
		return ref;
	}				
	
	public List<String> getFeedsByLanguage(String language){
		String key = "FeedsByLanguage"+language;
		List<String> feeds = (List<String>)getCacheService().getList(key);
		if(feeds == null){
			feeds = portalDao.getFeedsByLanguage(language);
			getCacheService().setList(key,feeds);
		}
		return feeds;
	}
	public Entity getEntityById(Class klass, long id){
		Entity entity = (Entity)getCacheService().getObject(klass,id);
		if(entity == null){
			entity = portalDao.getEntityById(klass,id);
			getCacheService().setObject(klass,id,entity);
		}
		return entity;
	}
	public boolean isNewUri(String uri,long orgId){
		return portalDao.isNewUri(uri,orgId);
	}

	public void deletePortal(String userId, Organization org){
		Portal portal = this.getPortalByUser(userId,org.getId());
		if(portal != null){
			 List<PortalTab> tabs = this.getPortalTabByOwner(userId,org);
			 for(PortalTab tab : tabs){
				 if(portal.getOwnerId().equals(userId)){					 
					 this.deletePortalTab(tab);
				 }
			 }			 
			 this.delete(portal);			
		}
	}
	
	public void deletePortalTab(PortalTab tab){
		this.delete(tab);
		portalDao.deletePortletsByTab(tab.getId());
	}
	
	public PortalDao getPortalDao() {
		return portalDao;
	}

	public void setPortalDao(PortalDao portalDao) {
		this.portalDao = portalDao;
	}
	
}