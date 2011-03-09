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

package org.light.portlets.rss;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.light.portal.util.HTMLUtil;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * 
 * @author Jianmin Liu
 **/
public class RssCacheFactory {

	private static RssCacheFactory instance = new RssCacheFactory();
	private HashMap<String, RssCache> rssMaps;
	
	private RssCacheFactory(){	
		rssMaps = new HashMap<String, RssCache> ();
	}
	
	public static RssCacheFactory getInstance(){
		return instance;
	}
	
	public List<RssBean> getRss(String feed){
		boolean reload;
		RssCache cache = this.rssMaps.get(feed);
		if(cache != null){
			Calendar lastTime = Calendar.getInstance();
			lastTime.setTimeInMillis(cache.getLastRefreshTime());
			lastTime.add(Calendar.MINUTE, cache.getRefreshRate());
			Calendar current = Calendar.getInstance();
			current.setTimeInMillis(System.currentTimeMillis());			
			if(current.after(lastTime)){
				reload = true;
			}
			else{
				reload = false;
			}
		}else{
			reload = true;
		}
		if(reload){
			try{
				URL feedUrl = new URL(feed);
	            SyndFeedInput input = new SyndFeedInput();
	            SyndFeed rss = input.build(new XmlReader(feedUrl));
	            List items = rss.getEntries();
		        if(items != null && !items.isEmpty())
		        {  	
					List<RssBean> lists = new ArrayList<RssBean>();	 					
	        		for(int i=0; i<items.size(); i++ )
		            {
	        			SyndEntry item = (SyndEntry)items.get(i);
						String link = item.getLink();
						String title = item.getTitle();
						String author = item.getAuthor();
						Date date = item.getPublishedDate();
						String desc = (item.getDescription() != null) ? item.getDescription().getValue() : null;							
						desc = HTMLUtil.disableScripts(desc);
						boolean addLink = true;						
						lists.add(new RssBean(i,link,title,desc,author,date,addLink));
		            }	
	        		cache = new RssCache(lists);	
	        		this.rssMaps.remove(feed);
	        		this.rssMaps.put(feed,cache);
		        }
			}catch(Exception e){	
			}
		}
		List<RssBean> lists = null;
		if(cache != null) lists = cache.getNews();
		return lists;
	}
	public List<RssBean> getCachedRss(String feed){
		RssCache cache = this.rssMaps.get(feed);		
		List<RssBean> lists = null;
		if(cache != null) lists = cache.getNews();
		return lists;
	}
}
