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

package org.light.portal.core.task;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.light.portal.cache.CacheService;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.PortletService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.distribute.Event;
import org.light.portal.distribute.Message;
import org.light.portal.distribute.ReplicationPublisher;
import org.light.portal.model.Entity;
import org.light.portal.model.RecommendedItem;
import org.light.portal.model.User;
import org.light.portal.model.UserKeyword;
import org.light.portal.model.ViewedItem;
import org.light.portal.search.Indexer;
import org.light.portal.social.ActivityUpdater;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * 
 * @author Jianmin Liu
 **/
public class AfterTaskServiceImpl extends BaseServiceImpl implements  AfterTaskService {
    
	private PortalService portalService;
    private PortletService portletService;
    private UserService userService;
    private CacheService cacheService;
    private NotificationService notificationService;
    private Indexer indexer;
    private ActivityUpdater activityUpdater;    
    private ReplicationPublisher replicationPublisher;
    private ThreadPoolExecutor tpeLogin = new ThreadPoolExecutor(1,1,50000L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue(10000));	
    private ThreadPoolExecutor tpe = new ThreadPoolExecutor(1,100,50000L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue(10000));
    
    public void afterLogin(User user){
    	tpeLogin.execute(new AfterLoginTask(user));	
    }
    
    public void afterSave(Entity entity){
    	tpe.execute(new AfterSaveTask(entity));	
	}
	
	public void afterDelete(Entity entity){
		tpe.execute(new AfterDeleteTask(entity));	
	}
	
	class AfterLoginTask implements Runnable{
		 
		private User user;
		
		public AfterLoginTask(User user){
			this.user = user;
		}
		
		public void run() {
			user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));			 
			userService.save(user);
			if(user.getGrowKeyword() != 1) return;
			List<UserKeyword> keywords = userService.getUserKeywords(user.getPersonId());
			if(keywords != null && keywords.size() > 0){
				List<String> lists = portalService.getFeedsByLanguage(user.getLanguage());
				for(String param : lists){
					Thread.yield();
					int index = param.indexOf("feed=");
					String feed = param.substring(index+5).trim();
					try{
			            URL feedUrl = new URL(feed);
			            SyndFeedInput input = new SyndFeedInput();
			            SyndFeed rss = input.build(new XmlReader(feedUrl));
			            Collection items = rss.getEntries();
			            if(items != null && !items.isEmpty()){
			                for(Iterator i = items.iterator(); i.hasNext(); ){
			                    SyndEntry item = (SyndEntry)i.next();	                        
		                        String title = item.getTitle();
		                        if(title != null){
		                        	for(UserKeyword keyword : keywords){
		                        		if(title.toLowerCase().indexOf(keyword.getKeyword().toLowerCase()) > 0){
		                        			String link =item.getLink();
		                        			RecommendedItem news = portletService.getRecommendedItemByLink(link);
		                        			ViewedItem viewed = portletService.getViewedItemByLink(link);
		                        			if(news == null){
		                        				//don't recommend to user if user already read the news
		                        				if(viewed == null){
			                        				String desc = item.getDescription().getValue();
			                        				news = new RecommendedItem(link,title,desc,"portlet.title.recommendedItem",user.getLanguage(),user.getPersonId());
			                        				portalService.save(news);
		                        				}
		                        			}                        				     		                        
		                        		}
		                        	}
			                       
		                        }
			                }
			            }
			         }catch(Exception e){
			             
			         }
				}
			}
		}
	}
	
	class AfterSaveTask implements Runnable{
		 
		private Entity entity;
		
		public AfterSaveTask(Entity entity){
		 	this.entity = entity;
		}
		
		public void run(){
			try{
				if(!isSyncCache(entity)) cacheService.update(entity);
			}catch(Throwable e){}
			try{
				indexer.updateIndex(entity);
			}catch(Throwable e){}
			try{
				activityUpdater.addActivity(entity);				
			}catch(Throwable e){}
			try{
				notificationService.notify(entity);
			}catch(Throwable e){}
			try{
				replicationPublisher.process(new Message(Event.ENTITY_UPDATE, entity.getOrgId(),entity));
			}catch(Throwable e){}
		}	
	}
	
	class AfterDeleteTask implements Runnable{
		 
		private Entity entity; 		
		
		public AfterDeleteTask(Entity entity){
		 	this.entity = entity;
		}
		
		public void run(){
			if(!isSyncCache(entity)) cacheService.delete(entity);
			indexer.deleteIndex(entity);
			activityUpdater.deleteActivity(entity);
			replicationPublisher.process(new Message(Event.ENTITY_DELETE, entity.getOrgId(), entity));
		}	
	}
	
	public Indexer getIndexer() {
		return indexer;
	}

	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}
	
	public ActivityUpdater getActivityUpdater() {
		return activityUpdater;
	}
	public void setActivityUpdater(ActivityUpdater activityUpdater) {
		this.activityUpdater = activityUpdater;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public ReplicationPublisher getReplicationPublisher() {
		return replicationPublisher;
	}

	public void setReplicationPublisher(ReplicationPublisher replicationPublisher) {
		this.replicationPublisher = replicationPublisher;
	}

	public PortalService getPortalService() {
		return portalService;
	}

	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
	}

	public PortletService getPortletService() {
		return portletService;
	}

	public void setPortletService(PortletService portletService) {
		this.portletService = portletService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
