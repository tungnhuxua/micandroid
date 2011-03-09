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

package org.light.portal.core.dao.hibernate;

import static org.light.portal.util.Constants._CHANNEL_PREFIX;
import static org.light.portal.util.Constants._DEFAULT_LANGUAGE;
import static org.light.portal.util.Constants._DEFAULT_ORG_EMAIL;
import static org.light.portal.util.Constants._DEFAULT_ORG_LOGO;
import static org.light.portal.util.Constants._DEFAULT_ORG_LOGOICON;
import static org.light.portal.util.Constants._DEFAULT_ORG_MX;
import static org.light.portal.util.Constants._DEFAULT_ORG_TITLE;
import static org.light.portal.util.Constants._DEFAULT_ORG_VIRTUALHOST;
import static org.light.portal.util.Constants._DEFAULT_ORG_WEBID;
import static org.light.portal.util.Constants._DEFAULT_ROLE;
import static org.light.portal.util.Constants._DEFAULT_THEME;
import static org.light.portal.util.Constants._GROUP_PREFIX;
import static org.light.portal.util.Constants._LANGUAGE_EN;
import static org.light.portal.util.Constants._PERMISSIONS_SUFFIX;
import static org.light.portal.util.Constants._PORTAL_ROLES;
import static org.light.portal.util.Constants._PORTAL_TITLE_LOGIN_KEY;
import static org.light.portal.util.Constants._PRIVACY_PROFILE;
import static org.light.portal.util.Constants._ROLE_GROUP;
import static org.light.portal.util.Constants._ROLE_PREFIX;
import static org.light.portal.util.Constants._ROLE_USER;
import static org.light.portal.util.Constants._TABLE_RECREATE;
import static org.light.portal.util.Constants._TITLE_SUFFIX;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.DialectFactory;
import org.light.portal.core.dao.PortalDao;
import org.light.portal.layout.config.PortalLayout;
import org.light.portal.layout.config.PortalUser;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.Permission;
import org.light.portal.model.Portal;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectPreferences;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.Subdomain;
import org.light.portal.model.User;
import org.light.portal.model.UserExtRole;
import org.light.portal.model.UserObjectRole;
import org.light.portal.portlet.config.Parameter;
import org.light.portal.portlet.config.Portlets;
import org.light.portal.portlet.definition.PortletApp;
import org.light.portal.util.Constants;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PermissionUtil;
import org.light.portal.util.PropUtil;
import org.light.portlets.group.Group;

/**
 * 
 * @author Jianmin Liu
 **/
public class PortalDaoImpl extends BaseDaoImpl implements PortalDao {
	
	public void init(PortletApp portletApp, Portlets portlets,PortalLayout portalLayout) {			
		//check tables, create it if not exists
		if(_TABLE_RECREATE){
			Configuration config = new Configuration().configure();
			String sqls[] = config.generateSchemaCreationScript(DialectFactory.buildDialect(config.getProperty("dialect")));
			Session session= this.getHibernateTemplate().getSessionFactory().openSession();			
			for(String sql : sqls){
				try{
					Query query =session.createSQLQuery(sql);
					query.executeUpdate();
				}catch(Exception e){
					//table exists, ignore it.
				}
			}
			session.close();
		}
		this.getIdGenereator().init();
		this.createDefaultRef(_DEFAULT_ROLE,portlets,portletApp);	
		this.createDefaultPortalByUser(portalLayout);
		this.createDefaultPortalSecurity();		
	}
    		
	public Portal getPortalById(long id){
		return (Portal)this.getHibernateTemplate().get(Portal.class,id);
	}
	
	public Portal getPortalByUser(String userId,long orgId){
		Organization org = OrganizationThreadLocal.getOrg();
		if(org == null) org = this.getUserDao().getOrgById(orgId);
		return getPortalByUser(userId,org);
	}
	
	public Portal getPortalByUser(String userId, Organization org){
		Portal portal = findPortalByUser(userId, org);	
		if(portal == null){	 		
			String title="";
			boolean isMember = false;
			User user = this.getUserDao().getUserByUserId(userId,org.getId());
			if(user != null && user.getId() != org.getUserId()){			
				if(user.getDisplayName() != null) title = user.getDisplayName()+"_"+_PORTAL_TITLE_LOGIN_KEY;
				isMember = true;
			}
			portal = this.createDefaultPortalByUser(userId,org,title,isMember);			
		}
		return portal;
	}
	
	private Portal findPortalByUser(String userId, Organization org){
		List<Portal> portals =this.getHibernateTemplate().find("from Portal where ownerId=? and (orgId=? or orgId=0) order by orgId desc",new Object[]{userId,org.getId()});
		Portal portal = null;
		if(portals != null && portals.size() > 0) 
			portal = portals.get(0);		
		return portal;
	}
	
	public Portal getPortalByGroup(String portalId,long orgId){
		Portal portal = getPortalByUser(portalId,orgId);
		if(portal == null){				
			String[]id= portalId.split("_");
			long groupId = Long.parseLong(id[1]);
			Group group = (Group)this.getHibernateTemplate().get(Group.class,groupId);
			portal = this.createDefaultPortalByGroup(portalId,group.getDisplayName(),group.getOrgId());			
		}
		return portal;
	}
	
	public Portal getPortalByChannel(String channel,long orgId){
		Portal portal = getPortalByUser(channel,orgId);		
		if(portal == null) portal = getPortalByUser(channel,0L);
		return portal;
	}
	
	public Portal createPortalByUser(User user){
		return getPortalByUser(user.getUserId(),user.getOrgId());		
	}
		
	protected List<PortalTab> getPortalTabByOwner(String owner){		
		Organization org= OrganizationThreadLocal.getOrg();
		if(org == null) org = this.getUserDao().getOrganizations().get(0);
		return getPortalTabByOwner(owner,org);
	}
	
	public List<PortalTab> getPortalTabByOwner(String owner, Organization org){				
		User user = null;
		if(!owner.startsWith(_ROLE_PREFIX) && !owner.startsWith(_CHANNEL_PREFIX) && !owner.startsWith(_GROUP_PREFIX)) 
			user = this.getUserDao().getUserByUserId(owner,org.getId());
		if(user != null){			
			return getPortalTabByUser(user,org);
		}else{
//			if(owner.startsWith(_GROUP_PREFIX)){
//				List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.parentId= 0 order by tab.id", owner);
//				return list;
//			}else{
				List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.orgId= ? and tab.parentId= 0 order by tab.id", new Object[]{owner,org.getId()});
				if(list == null || list.size() ==0)
					list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.orgId= ? and tab.parentId= 0 order by tab.id", new Object[]{owner,0L});
				return list;
//			}
		}
	}
	
	public List<PortalTab> getPortalTabByPortalId(long portalId){
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.portalId= ? and tab.parentId= 0 order by tab.id", portalId);
		return list;
	}
	
	public List<PortalTab> getPortalTabByUser(User user, Organization org){
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.orgId=? and tab.parentId= 0 order by tab.id", new Object[] {user.getUserId(),user.getOrgId()});		
		if(user != null && user.getId() != org.getUserId() && !user.getUserId().startsWith(_GROUP_PREFIX)){			
			String defaultRole = (user.getId() == org.getUserId()) ? org.getRole() : _DEFAULT_ROLE;			
			List<UserObjectRole> listRole= new LinkedList<UserObjectRole>();		
			for(UserObjectRole role : user.getRoles()){
				if(!_ROLE_USER.equalsIgnoreCase(role.getName()) 
						&& !defaultRole.equalsIgnoreCase(role.getName()))
					listRole.add(role);
			}
			if(listRole != null && listRole.size()>0){	
				List<PortalTab> listsByRole	= null;	
				if(listRole.size() == 1){
					listsByRole= this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.parentId= 0 order by tab.id", listRole.get(0).getName());
				}else{
					String group = "";					
					for(UserObjectRole userRole :listRole){
						String role= userRole.getName();						
						if(role != null && role.startsWith(_CHANNEL_PREFIX) && !_LANGUAGE_EN.equals(user.getRegion())){
							String localeRole = role+"_"+user.getRegion();
							Portal channelPortal = getPortalByUser(localeRole,org.getId());
							if(channelPortal != null)
								role = localeRole;
						}
						if(group.equals(""))
							group+="'" + role +"'";
						else
							group+=",'" + role +"'";
					}
					listsByRole= this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId in ("+group+") and tab.parentId= 0 order by tab.id");
				}
				for(PortalTab tab : listsByRole){					
					list.add(0,tab);	
				}
							
			}
			/* existing system's Role*/
			List<UserExtRole> listUserRoles = this.getUserDao().getUserExtRole(user.getId());
			if(listUserRoles != null && listUserRoles.size()>0){	
				List<PortalTab> listsByRole	= null;	
				if(listUserRoles.size() == 1){
					listsByRole= this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.parentId= 0 order by tab.id", listUserRoles.get(0).getRoleId());
				}else{
					String group = "";
					for(UserExtRole userRole :listUserRoles){
						if(group.equals(""))
							group+="'" + userRole.getRoleId()+"'";
						else
							group+=",'" + userRole.getRoleId()+"'";
					}
					listsByRole= this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId in ("+group+") and tab.parentId= 0 order by tab.id");
				}
				list.addAll(listsByRole);			
			}
		}
		return list;
	}
	
	public List<PortalTab> getUserPersonalPortalTab(String userId,long orgId){
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.orgId=? and tab.parentId=0 order by tab.id", new Object[]{userId,orgId});				
		return list;
	}
	
	public List<PortalTab> getUserProfilePortalTab(String userId,long orgId){
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.ownerId= ? and tab.orgId=? and tab.parentId=0 and  tab.status = "+_PRIVACY_PROFILE+" order by tab.id", new Object[]{userId,orgId});				
		return list;
	}

	private List<PortalTab> getPortalTabByName(String name, long portalId){		
		Object[] params = new Object[2];
		params[0] = name;
		params[1] = portalId;
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.label =? and tab.portalId= ?", params);		
		return list;
	}
	
	public List<PortalTab> getPortalTabByParent(long parentId){		
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.parentId= ?", parentId);		
		return list;
	}
	
	public PortalTab getPortalTabById(long id){
		return (PortalTab)this.getHibernateTemplate().get(PortalTab.class,id);
	}
	
	public PortalTab getPortalTabByUrl(String url,long orgId){
		List<PortalTab> list = this.getHibernateTemplate().find("select tab from PortalTab tab where tab.url=?", url);		
		PortalTab tab = null;
		if(list != null && list.size() > 0) tab = list.get(0);
		return tab;
	}
	
	public List<PortletObject> getPortletsByTab(long tabId){
		List<PortletObject> list= this.getHibernateTemplate().find("select portlet from PortletObject portlet where portlet.tabId= ? order by portlet.column, portlet.row", tabId);		
		return list;
	}
	
	public int getTabPortletsMaxRowByColoumn(long tabId, int column){			
		String hql="select max(portlet.row) from PortletObject portlet where portlet.tabId= "+tabId+" and portlet.column="+column;
		Session session= this.getHibernateTemplate().getSessionFactory().openSession();
		Query query =session.createQuery(hql);
		Object result = query.uniqueResult();
		int max=0;
		if(result != null)
			max = (Integer)result + 1;
		session.close();
		return max;
	}
	
	public PortletObject getPortletById(long id){
		PortletObject po = (PortletObject)this.getHibernateTemplate().get(PortletObject.class,id);		
		return  po;
	}
	
	public List<PortletObjectPreferences> getPortletPreferences(long portletId){
		return this.getHibernateTemplate().find("select preference from PortletObjectPreferences preference where preference.portletId= ?", portletId);
	}
	
	public List<PortletObjectRef> getPortletRefsByUser(String userId){
		User user =this.getUserDao().getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
		List<UserObjectRole> roles = this.getUserDao().getUserRoles(user.getId(),OrganizationThreadLocal.getOrganizationId());		
		String language = user.getLanguage();		
		StringBuffer query = new StringBuffer();
		query.append("select ref from PortletObjectRef ref where ");
		long orgId = OrganizationThreadLocal.getOrganizationId();
		query.append("(ref.orgId= 0 or ref.orgId="+orgId+") and ");
		if(language == null) language = PropUtil.getString(_DEFAULT_LANGUAGE);
		query.append("(ref.language= '"+language+"'");
		if(language.indexOf("_") > 0){
			String prefix = language.substring(0,language.indexOf("_"));
			query.append(" or ref.language= '"+prefix+"'");
		}
		query.append(" or ref.language= 'all') and (")
		     .append("ref.userId= '"+userId+"'")
		     .append(" or ref.userId= '" + _DEFAULT_ROLE+"'")
		     ;
		if(roles != null){
			for(UserObjectRole role : roles){
				query.append("or ref.userId= '"+role.getName()+"'");
			}
		}
		query.append(")");
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());
		
		return list;
	}
	
	public List<PortletObjectRef> getPortletRefsByUserAndKeyword(String userId, String keyword){
		User user =this.getUserDao().getUserByUserId(userId,OrganizationThreadLocal.getOrganizationId());
		List<UserObjectRole> roles = this.getUserDao().getUserRoles(user.getId(),OrganizationThreadLocal.getOrganizationId());		
		String locale = user.getRegion();		
		long orgId = OrganizationThreadLocal.getOrganizationId();
		StringBuffer query = new StringBuffer();
		query.append("select ref from PortletObjectRef ref where ")
			 .append("(ref.orgId= 0 or ref.orgId="+orgId+") and ")
		     .append("(ref.language= '"+locale+"'")
		     ;
		if(locale.indexOf("_") > 0){
			String parentLocale = locale.substring(0,locale.indexOf("_"));
			query.append(" or ref.language= '"+parentLocale+"'");
		}
		query.append(" or ref.language= 'all') and (")
		     .append("ref.userId= '"+userId+"'")
		     .append(" or ref.userId= '" + _DEFAULT_ROLE+"'")
		     ;
		if(roles != null){
			for(UserObjectRole role : roles){
				query.append("or ref.userId= '"+role.getName()+"'");
			}
		}
		query.append(")");
		query.append(" and (ref.label like '%"+keyword+"%' or ref.keywords like '%"+keyword+"%')");
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());
		
		return list;
	}
	
	public List<PortletObjectRef> getMyFeed(String userId){
		StringBuffer query = new StringBuffer();
		query.append("select ref from PortletObjectRef ref where ")
		     .append("ref.userId= '"+userId+"'")
		     ;		
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());
		return list;
	}
	
	public List<PortletObjectRef> getAllFeed(){
		StringBuffer query = new StringBuffer();
		query.append("select ref from PortletObjectRef ref where ")
		     .append("ref.parameter like 'feed=%'")
		     ;		
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());	
		return list;
	}
	
	public PortletObjectRef getPortletRefById(long id){
		return (PortletObjectRef)this.getHibernateTemplate().get(PortletObjectRef.class,id);
	}
	
	public PortletObjectRef getPortletRefByName(String name){
		List<PortletObjectRef> refs = this.getHibernateTemplate().find("select ref from PortletObjectRef ref where ref.name=?",name);
		PortletObjectRef ref = null;
		if(refs != null && refs.size() > 0) ref = refs.get(0);
		return ref;
	}
	
	public boolean isNewUri(String uri,long orgId){
		List<Subdomain> uris = this.getHibernateTemplate().find("select subdomain from Subdomain subdomain where subdomain.uri=? and subdomain.orgId=?",new Object[]{uri,orgId});
		return (uris != null && uris.size() > 0) ? false : true;
	}
	
	public List<String> getFeedsByLanguage(String language){
		StringBuffer query = new StringBuffer();
		query.append("select distinct ref.parameter from PortletObjectRef ref where ")
		     .append("ref.language= '"+language+"'")
		     .append(" and ")
		     .append("ref.parameter like 'feed=%'")
		     ;		
		List<String> list= this.getHibernateTemplate().find(query.toString());	
		return list;
	}
	
	public List<PortletObjectRef> getFeedsByUrl(String url){
		StringBuffer query = new StringBuffer();
		query.append("select ref from PortletObjectRef ref where ")	
		     .append("ref.parameter = 'feed=")
		     .append(url)
		     .append("'")
		     ;		
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());	
		return list;
	}	
	
	public List<PortletObjectRef>getPortletRefByRegion(String region){
		String locale = region;
		StringBuffer query = new StringBuffer();
		long orgId = OrganizationThreadLocal.getOrganizationId();
		query.append("select ref from PortletObjectRef ref where ")
			 .append("(ref.orgId= 0 or ref.orgId="+orgId+") and ")
		     .append("(ref.language= '"+locale+"'")
		     ;
		if(locale.indexOf("_") > 0){
			String parentLocale = locale.substring(0,locale.indexOf("_"));
			query.append(" or ref.language= '"+parentLocale+"'");
		}
		query.append(" or ref.language= 'all') and ")		    
		     .append(" ref.userId= '" + _DEFAULT_ROLE+"'")		     
		     ;		
		List<PortletObjectRef> list= this.getHibernateTemplate().find(query.toString());
		
		return list;
	}

	public void deletePortletsByTab(long tabId){		
		List<PortletObject> portlets = this.getPortletsByTab(tabId);
		for(PortletObject portlet : portlets){
			this.delete(portlet);
		}
	}
	
	private List<PortletObject> getPortletByTabAndName(String name, long tabId){
		Object[] params = new Object[2];
		params[0] = name;
		params[1] = tabId;
		List<PortletObject> list= this.getHibernateTemplate().find("select po from PortletObject po where po.name= ? and po.tabId= ?", params);		
		return list;
	}
		
	private long createDefaultPage(org.light.portal.layout.config.PortalTab ptab, Portal portal, long parentId){
		int closeable = 0;
		int editable = 0;
		int moveable = 0;
		int allowAddContent = 0;
		int defaulted = 0;
		if(ptab.isCloseable()) closeable = 1;
		if(ptab.isEditable()) editable = 1;
		if(ptab.isMoveable()) moveable = 1;
		if(ptab.isAllowAddContent()) allowAddContent = 1;
		if(ptab.isDefaulted()) defaulted = 1;
		List<PortalTab> tabs = this.getPortalTabByName(ptab.getTitle(),portal.getId());
		PortalTab tab=null;
		if(tabs == null || tabs.size() == 0){
			tab= new PortalTab(
						ptab.getTitle()
					   ,ptab.getUrl()
					   ,closeable
					   ,editable
					   ,moveable
					   ,allowAddContent
					   ,ptab.getColor()
					   ,defaulted
					   ,ptab.getBetween().intValue()
					   ,ptab.getWidths()
					   ,ptab.getPortletWindow()
					   ,parentId
					   ,portal.getId()
					   ,portal.getOwnerId()
					   ,portal.getOrgId());
			this.save(tab,false);
		}else{	
			tab = tabs.get(0);
			tab.setCloseable(closeable);
			tab.setEditable(editable);
			tab.setMoveable(moveable);
			tab.setAllowAddContent(allowAddContent);
			tab.setDefaulted(defaulted);
			tab.setWidths(ptab.getWidths());
			tab.setBetween(ptab.getBetween().intValue());
			tab.setWindowSkin(ptab.getPortletWindow());
			this.save(tab,false);
		}
		
		if(ptab.getPortlets() != null){
			for(org.light.portal.layout.config.Portlet portlet : ptab.getPortlets().getPortlet()){
				PortletObjectRef ref = this.getPortletRefByName(portlet.getName());
				List<PortletObject> po = this.getPortletByTabAndName(portlet.getName(),tab.getId());
				if(ref != null){
					if(po == null || po.size() == 0){
						PortletObject portletObject =new PortletObject(
									tab.getId()
								   ,portlet.getColumn().intValue()
								   ,portlet.getRow().intValue()
								   ,(portlet.isNotCloseable() != null) ? portlet.isNotCloseable() : false
								   ,(portlet.isNoEditMode() != null) ? portlet.isNoEditMode() : false
								   ,(portlet.isNoConfigMode() != null) ? portlet.isNoConfigMode() : false
								   ,portlet.getBarBgColor()
								   ,portlet.getBarFontColor()
								   ,portlet.getContentBgColor()
								   ,ref);
						this.save(portletObject,false);
					}
				}
			}
		}
		return tab.getId();
	}
	
	private Portal createDefaultPortalByGroup(String ownerId, String title, long orgId){
		logger.info("createDefaultPortalByGroup for "+ownerId);
		Portal portal = null;
		String defaultRole = _ROLE_GROUP;
		Portal defaultPortal = getPortalByUser(defaultRole,orgId);
		if(defaultPortal == null) defaultPortal = getPortalByUser(defaultRole,0L);
		if(defaultPortal != null){
			String portalTitle= title;
			String portalTheme = PropUtil.getString(_DEFAULT_THEME);
			if(portalTitle == null || "".equals(portalTitle.trim())) portalTitle = defaultPortal.getTitle();
			if(portalTheme == null || "".equals(portalTheme.trim())) portalTheme = defaultPortal.getTheme();
			Portal existPortal = getPortalByUser(ownerId,orgId);
			if(existPortal != null) return existPortal;
			portal = new Portal(ownerId, orgId, portalTheme);
			this.save(portal);		
		
			List<PortalTab> tabs = getPortalTabByOwner(defaultRole);
			for(PortalTab tab : tabs){
				PortalTab newTab= new PortalTab(tab.getLabel()
											   ,tab.getUrl()
											   ,tab.getCloseable()
											   ,tab.getEditable()
											   ,tab.getMoveable()
											   ,tab.getAllowAddContent()
											   ,tab.getColor()
											   ,0
											   ,tab.getBetween()
											   ,tab.getWidths()
											   ,tab.getWindowSkin()
											   ,0
											   ,portal.getId()
											   ,ownerId
											   ,portal.getOrgId());
				this.save(newTab);
				List<PortletObject> portletObjects = this.getPortletsByTab(tab.getId());
				for(PortletObject portletObject : portletObjects){
					PortletObject newPortletObject =new PortletObject(newTab.getId(), portletObject);
					this.save(newPortletObject);						   
				}
			}
		}else{
			portal = new Portal(ownerId, orgId, PropUtil.getString(_DEFAULT_THEME));
			this.save(portal);				
		}
		return portal;
	}
		
	private Portal createDefaultPortalByUser(String userId, Organization org, String title, boolean isMember){
		logger.info("createDefaultPortalByUser for "+userId+" at org "+org.getId());
		String defaultRole = org.getRole();
		if(isMember) defaultRole = _ROLE_USER;		
		if(userId.startsWith(_GROUP_PREFIX)) defaultRole = _ROLE_GROUP;
		Portal portal = null;
		Portal defaultPortal = org.getPortal();
		if(defaultPortal == null)
			defaultPortal = this.findPortalByUser(defaultRole,org);
		if(defaultPortal != null) logger.info("default portal "+defaultPortal.getOwnerId()+" at org "+defaultPortal.getOrgId());
		String theme = (defaultPortal != null) ?  defaultPortal.getTheme() : PropUtil.getString(_DEFAULT_THEME,org.getWebId());
		portal = new Portal(userId, org.getId(), title,theme);							
		logger.info("createDefaultPortalByUser for "+userId+" at org "+org.getId()+" with theme "+theme);
		this.save(portal);
		List<PortalTab> tabs = getPortalTabByOwner(defaultRole);
		for(PortalTab tab : tabs){
			logger.info("create tab: "+tab.getLabel());
			PortalTab newTab= new PortalTab(tab.getLabel()
										   ,tab.getUrl()
										   ,tab.getCloseable()
										   ,tab.getEditable()
										   ,tab.getMoveable()
										   ,tab.getAllowAddContent()
										   ,tab.getColor()
										   ,0
										   ,tab.getBetween()
										   ,tab.getWidths()
										   ,tab.getWindowSkin()
										   ,0
										   ,portal.getId()
										   ,userId
										   ,portal.getOrgId());
			this.save(newTab);
			List<PortletObject> portletObjects = this.getPortletsByTab(tab.getId());
			for(PortletObject portletObject : portletObjects){
				PortletObject newPortletObject =new PortletObject(newTab.getId(), portletObject);
				this.save(newPortletObject);						   
			}
		}			
		return portal;
	}
	
	private void createDefaultRef(String userId, Portlets portlets, PortletApp portletApp){
		org.light.portal.portlet.config.Portlet[] portletArray = portlets.getPortlet();		
		for(int i=0;i<portletArray.length;i++){
			int refreshMode= 0;
			int editMode= 0;
			int helpMode= 0;
			int configMode= 0;
			int minimized = 1;
			int maximized = 1;
			int autoRefreshed= 0;
			int periodTime= 0;
			int allowJS= 0;
			int pageRefreshed= 0;
			int showNumber= 0;
			int showType= 0;
			int windowStatus =0;
			int mode = 0;
			int type = 0;
			String parameter= "";
			if(portletArray[i].getRefreshMode()) refreshMode = 1;
			if(portletArray[i].getEditMode()) editMode = 1;
			if(portletArray[i].getHelpMode()) helpMode = 1;
			if(portletArray[i].getConfigMode()) configMode = 1;
			if(portletArray[i].hasMinimized() && !portletArray[i].getMinimized()) minimized = 0;
			if(portletArray[i].hasMaximized() && !portletArray[i].getMaximized()) maximized = 0;
			if(portletArray[i].getAutoRefreshed()) autoRefreshed = 1;
			periodTime = portletArray[i].getPeriodTime();
			if(portletArray[i].getAllowJS()) allowJS = 1;
			if(portletArray[i].getPageRefreshed()) pageRefreshed = 1;
			showNumber = portletArray[i].getShowNumber();
			showType = portletArray[i].getShowType();
			windowStatus = portletArray[i].getWindowStatus();
			mode = portletArray[i].getMode();
			type = portletArray[i].getType();			
			if(portletArray[i].getParameter() != null){
				Parameter[] parameters = portletArray[i].getParameter();
				for(int p=0;p<parameters.length;p++){
					if(parameter.length() == 0)
						parameter= parameters[p].getName()+"="+parameters[p].getValue();
					else
						parameter+="&"+ parameters[p].getName()+"="+parameters[p].getValue();
				}				
			}
			
			if(portletArray[i].getRoles() != null && portletArray[i].getRoles().getRole() != null){
				for(int j=0;j<portletArray[i].getRoles().getRole().length;j++){
					String roleId = portletArray[i].getRoles().getRole()[j];
					PortletObjectRef roleBasedRef =this.getPortletRefByName(portletArray[i].getName());
					if(roleBasedRef == null){
						roleBasedRef = new PortletObjectRef(
															portletArray[i].getName()
														   ,portletArray[i].getPath()
														   ,portletArray[i].getTitle()
														   ,portletArray[i].getIcon()
														   ,portletArray[i].getIconCssSprite()
														   ,portletArray[i].getUrl()
														   ,portletArray[i].getSubTag()
														   ,portletArray[i].getTag()
														   ,portletArray[i].getLanguage()
														   ,refreshMode
														   ,editMode
														   ,helpMode
														   ,configMode
														   ,minimized
														   ,maximized
														   ,portletArray[i].getWindowSkin()
														   ,autoRefreshed
														   ,periodTime
														   ,allowJS
														   ,pageRefreshed
														   ,showNumber
														   ,showType
														   ,windowStatus
														   ,mode
														   ,type
														   ,parameter
														   ,roleId);
					}
					else{
						roleBasedRef.setPath(portletArray[i].getPath());
						roleBasedRef.setLabel(portletArray[i].getTitle());
						roleBasedRef.setIcon(portletArray[i].getIcon());
						roleBasedRef.setIconCssSprite(portletArray[i].getIconCssSprite());
						roleBasedRef.setUrl(portletArray[i].getUrl());
						roleBasedRef.setSubTag(portletArray[i].getSubTag());
						roleBasedRef.setTag(portletArray[i].getTag());
						roleBasedRef.setLanguage(portletArray[i].getLanguage());
						roleBasedRef.setRefreshMode(refreshMode);
						roleBasedRef.setEditMode(editMode);
						roleBasedRef.setHelpMode(helpMode);
						roleBasedRef.setConfigMode(configMode);
						roleBasedRef.setAutoRefreshed(autoRefreshed);
						roleBasedRef.setPeriodTime(periodTime);
						roleBasedRef.setAllowJS(allowJS);
						roleBasedRef.setPageRefreshed(pageRefreshed);
						roleBasedRef.setShowNumber(showNumber);
						roleBasedRef.setShowType(showType);
						roleBasedRef.setWindowStatus(windowStatus);
						roleBasedRef.setWindowSkin(portletArray[i].getWindowSkin());
						roleBasedRef.setMode(mode);
						roleBasedRef.setType(type);
						roleBasedRef.setParameter(parameter);
						roleBasedRef.setUserId(roleId);
					}
					this.save(roleBasedRef,false);	
				}
			}else{
				PortletObjectRef ref =this.getPortletRefByName(portletArray[i].getName());
				if(ref == null){
					ref = new PortletObjectRef(
											portletArray[i].getName()
										   ,portletArray[i].getPath()
										   ,portletArray[i].getTitle()
										   ,portletArray[i].getIcon()
										   ,portletArray[i].getIconCssSprite()
										   ,portletArray[i].getUrl()
										   ,portletArray[i].getSubTag()
										   ,portletArray[i].getTag()
										   ,portletArray[i].getLanguage()
										   ,refreshMode
										   ,editMode
										   ,helpMode
										   ,configMode
										   ,minimized
										   ,maximized
										   ,portletArray[i].getWindowSkin()
										   ,autoRefreshed
										   ,periodTime
										   ,allowJS
										   ,pageRefreshed
										   ,showNumber
										   ,showType
										   ,windowStatus
										   ,mode
										   ,type
										   ,parameter
										   ,userId);
				}else{
					ref.setPath(portletArray[i].getPath());
					ref.setLabel(portletArray[i].getTitle());
					ref.setIcon(portletArray[i].getIcon());
					ref.setIconCssSprite(portletArray[i].getIconCssSprite());
					ref.setUrl(portletArray[i].getUrl());
					ref.setSubTag(portletArray[i].getSubTag());
					ref.setTag(portletArray[i].getTag());
					ref.setLanguage(portletArray[i].getLanguage());
					ref.setRefreshMode(refreshMode);
					ref.setEditMode(editMode);
					ref.setHelpMode(helpMode);
					ref.setConfigMode(configMode);
					ref.setAutoRefreshed(autoRefreshed);
					ref.setPeriodTime(periodTime);
					ref.setAllowJS(allowJS);
					ref.setPageRefreshed(pageRefreshed);
					ref.setShowNumber(showNumber);
					ref.setShowType(showType);
					ref.setWindowStatus(windowStatus);
					ref.setWindowSkin(portletArray[i].getWindowSkin());
					ref.setMode(mode);
					ref.setType(type);
					ref.setParameter(parameter);
					ref.setUserId(userId);
				}   					
				this.save(ref,false);	
			}
		}
		
		org.light.portal.portlet.definition.Portlet[] portletDefinitionArrays = portletApp.getPortlet();
		if(portletDefinitionArrays.length > 0){
			for (int i=0;i<portletDefinitionArrays.length;i++ ) {		            	            		          
	            try{	            	
					StringBuilder keywords = new StringBuilder();
					if(portletDefinitionArrays[i].getPortletTypeChoice() != null && portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence() != null && portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo() != null){
						if(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getShortTitle() != null)
							keywords.append(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getShortTitle().getContent());
						if(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getTitle() != null){
							if(keywords.length() > 0) keywords.append(",");
							keywords.append(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getTitle().getContent());
						}
						if(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getKeywords() != null){
							if(keywords.length() > 0) keywords.append(",");
							keywords.append(portletDefinitionArrays[i].getPortletTypeChoice().getPortletTypeChoiceSequence().getPortletInfo().getKeywords().getContent());
						}
					}
	            	PortletObjectRef ref =this.getPortletRefByName(portletDefinitionArrays[i].getPortletName().getContent());
	            	if(ref != null && keywords.toString().length() > 0){
	            		if(ref.getKeywords() == null || ref.getKeywords().indexOf(keywords.toString()) < 0){
		            		ref.setKeywords((ref.getKeywords() != null) ? ref.getKeywords()+"," : ""+keywords.toString());
		            		this.save(ref,false);
	            		}
	            	}
	            }catch(Exception e){
					e.printStackTrace();
				}	
	        }
		}
//		List<Permission> permissions = this.getPermissionDao().getPermissions(0L);
//		String[] permissionName = PropUtil.getStringArray(Constants._PERMISSION_LIST_NAME);
//		String[] permissionDesc = PropUtil.getStringArray(Constants._PERMISSION_LIST_DESC);
//		for(int i=0;i<permissionName.length;i++){
//			String name = permissionName[i];
//			String desc = (permissionDesc.length > i) ? permissionDesc[i] : name;
//			boolean found = false;
//			for(Permission p : permissions){
//				if(p.getName().equals(name)){
//					found = true;
//					break;
//				}
//			}
//			if(!found){
//				Permission permission = new Permission(name,desc);
//				this.save(permission,false);
//			}
//		}
		
		String[] channelName = PropUtil.getStringArray(Constants._CHANNEL_LIST_NAME);
		String[] channelDesc = PropUtil.getStringArray(Constants._CHANNEL_LIST_DESC);
		for(int i=0;i<channelName.length;i++){
			String name = channelName[i];			
			String desc = (channelDesc.length > i) ? channelDesc[i] : name;			
			ObjectRole role = this.getUserPermissionDao().getRoleByName(name,0L);
			if(role == null){				
				role = new ObjectRole(name,desc, 0L,0L);
				this.save(role,false);
			}
		}				
	}

	private void createDefaultPortalByUser(PortalLayout portalLayout){
		List<PortalUser> users = portalLayout.getPortalUser();
		if(users != null){
			for(PortalUser puser : users){
				String userId = puser.getUser();
				if(userId == null || "".equals(userId))
					userId = puser.getRole();
				if(userId == null || "".equals(userId))
					throw new RuntimeException("Portal-layout.xml configure error: user name and role name can not both null.");
				List<Portal> portals = this.getHibernateTemplate().find("from Portal where ownerId=?",userId);
				Portal portal = null;
				if(portals != null && portals.size() > 0) portal = portals.get(0);
				if(portal == null){
					String theme =PropUtil.getString(_DEFAULT_THEME);
					String roleTheme = PropUtil.getString(userId+".theme");
					if(roleTheme != null) theme = roleTheme;					
					portal = new Portal(userId,0L,theme);
					this.save(portal,false);
				}
				List<org.light.portal.layout.config.PortalTab> tabs = puser.getPortalTab();
				for(org.light.portal.layout.config.PortalTab ptab : tabs){
					long tabId = createDefaultPage(ptab,portal,0);
					List<org.light.portal.layout.config.PortalTab> subTabs = ptab.getPortalTab(); 
					for(org.light.portal.layout.config.PortalTab subTab : subTabs){
						createDefaultPage(subTab,portal,tabId);						
					}		
				}					
			}			
		}
		List<Organization> orgs  = this.getHibernateTemplate().find("from Organization ref");
		if(orgs == null || orgs.size() == 0){
			Organization org = new Organization(_DEFAULT_ORG_WEBID,_DEFAULT_ORG_TITLE,_DEFAULT_ORG_VIRTUALHOST,_DEFAULT_ORG_MX,_DEFAULT_ORG_EMAIL,_DEFAULT_ORG_EMAIL, _DEFAULT_ORG_LOGO,_DEFAULT_ORG_LOGOICON,Organization._TYPE_SUPER_ORGANIZATION);
			this.getUserDao().createOrganization(org, new OrgProfile(PropUtil.getString(_DEFAULT_LANGUAGE),"","",""),false);
		}	
	}
	
	private void createDefaultPortalSecurity(){
		List<Organization> orgs = this.getOrganizations();
		for(Organization org : orgs){
			String[] roles = PropUtil.getStringArray(_PORTAL_ROLES,org.getWebId());
			if(roles != null){	
				List<Permission> permissions = this.getPermissionDao().getPermissions(org.getId());
				Map<Long,Permission> permissionIdMap = new HashMap<Long,Permission>();
				Map<String,Permission> permissionNameMap = new HashMap<String,Permission>();
				for(Permission p : permissions){
					permissionIdMap.put(p.getId(),p);
					permissionNameMap.put(p.getName(),p);
				}
				for(String srole : roles){
					String title = PropUtil.getString(srole+_TITLE_SUFFIX);			
					ObjectRole role = this.getUserPermissionDao().getRoleByName(srole,org.getId());
					String[] rolePermissions = PropUtil.getStringArray(srole+_PERMISSIONS_SUFFIX,org.getWebId());
					long permission = PermissionUtil.getPermission(rolePermissions);
					if(role == null){	
						role = new ObjectRole(srole,title,org.getId(),permission);
						this.save(role,false);
					}else if(role.getPermission() != permission){
						role.setPermission(permission);
						this.save(role,false);
					}
//					List<RolePermission> rPermissions = this.getPermissionDao().getRolePermissions(role.getId());
//					if(rPermissions != null && rPermissions.size() > 0){
//						RolePermission rp = rPermissions.get(0);
//						if(rp.getPermissionId() != permission){
//							if(permission > 0){
//								rp.setPermissionId(permission);
//								this.save(rp,false);
//							}else{
//								this.delete(rp,false);
//							}
//						}
//					}else if(permission > 0){
//						RolePermission rolePermission = new RolePermission(role.getId(),permission);
//						this.save(rolePermission,false);
//					}
						
//					for(RolePermission rp : rPermissions){
//						Permission p = permissionIdMap.get(rp.getPermissionId());
//						boolean found = false;
//						for(String permission : rolePermissions){
//							if(permission.equals(p.getName())){
//								found = true;
//								break;
//							}
//						}
//						if(!found){
//							this.delete(rp,false);
//						}
//					}
//					for(String permission : rolePermissions){
//						Permission p = permissionNameMap.get(permission);
//						if(p != null){
//							rPermissions = this.getUserPermissionDao().getRolePermissionById(role.getId(),p.getId());
//							if(rPermissions == null || rPermissions.size() == 0){
//								RolePermission rolePermission = new RolePermission(role.getId(),p.getId());
//								this.save(rolePermission,false);
//							}
//						}
//					}									
				}
			}
		}
	}

	private List<Organization> getOrganizations(){
		List<Organization> orgs = this.getHibernateTemplate().find("select org from Organization org where org.parentId = 0 order by org.id");
		return orgs;
	}	
}