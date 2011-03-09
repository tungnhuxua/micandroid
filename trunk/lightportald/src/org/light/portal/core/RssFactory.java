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

package org.light.portal.core;

import static org.light.portal.util.Constants._CHARSET_UTF;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.light.portal.model.PortletObjectRef;
import org.light.portal.util.DateUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.blog.Blog;
import org.light.portlets.forum.ForumPost;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * 
 * @author Jianmin Liu
 **/
public class RssFactory {
	
	private static RssFactory instance = new RssFactory();	
	private static String feedType = "rss_2.0";
	private RssFactory(){		
	}
	
	public static RssFactory getInstance(){
		return instance;
	}
	public String getOpml(List<PortletObjectRef> list,String xml){
		try {			            
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
				  .append("\n")
				  .append("<opml version=\"1.1\">")
				  .append("<head><title>")
				  .append(OrganizationThreadLocal.getWebId())
				  .append(" Exported my feeds</title><dateCreated>")
				  .append(DateUtil.format(new Date(System.currentTimeMillis()),"EEE, MMMM dd, yyyy HH:mm aaa"))
				  .append("</dateCreated></head>")
				  .append("\n")
				  .append("<body><outline text=\"")
				  .append(OrganizationThreadLocal.getWebId())
				  .append(" Exported my feeds\">")
				  .append("\n")
				  ;
			for(PortletObjectRef myfeed : list){
				if(myfeed.getParameter() != null && myfeed.getParameter().indexOf("feed") >= 0){
					String feed = myfeed.getParameter().substring(myfeed.getParameter().indexOf("=") + 1).trim();
					String title = myfeed.getLabel();
					if(title.indexOf("&") > 0) 
						title=title.replaceAll("&","&amp;").trim();				
					if(feed.indexOf("&") > 0) 
						feed=feed.replaceAll("&","&amp;");
					
					buffer.append("<outline text=\"")
						  .append(title)
						  .append("\" ")
						  .append("title=\" ")
						  .append(title)
						  .append("\" type=\"rss\" xmlUrl=\"")
						  .append(feed)
						  .append("\" />")
						  .append("\n")
						  ;
				}
			}
			buffer.append("</outline></body></opml>");
            //final Writer writer = new FileWriter(xml);
            FileOutputStream fileoutstream = new FileOutputStream(xml);
            Writer writer = new OutputStreamWriter(fileoutstream, "UTF-8");
            writer.write(buffer.toString());
            writer.close();
        }			
        catch (Exception ex) {
            ex.printStackTrace();               
     }
		return null;
	}
	public String getAllOpml(List<PortletObjectRef> list,String xml){
		try {			            
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
				  .append("\n")
				  .append("<opml version=\"1.1\">")
				  .append("<head><title>")
				  .append(OrganizationThreadLocal.getWebId())
				  .append(" Exported my feeds</title><dateCreated>")
				  .append(DateUtil.format(new Date(System.currentTimeMillis()),"EEE, MMMM dd, yyyy HH:mm aaa"))
				  .append("</dateCreated></head>")
				  .append("\n")
				  .append("<body><outline text=\"")
				  .append(OrganizationThreadLocal.getWebId())
				  .append(" Exported my feeds\">")
				  .append("\n")
				  ;
			for(PortletObjectRef feed : list){
				if(feed.getParameter() != null && feed.getParameter().indexOf("feed") >= 0){
					String url = feed.getParameter().substring(feed.getParameter().indexOf("=") + 1).trim();
					String title = feed.getLabel();
					if(title.indexOf("&") > 0) 
						title=title.replaceAll("&","&amp;").trim();				
					if(url.indexOf("&") > 0) 
						url=url.replaceAll("&","&amp;");
					
					buffer.append("<outline ")
						  .append("text=\"")
						  .append(title)
						  .append("\" ")
						  .append("title=\" ")
						  .append(title)
						  .append("\" ")
						  .append("type=\"rss\" ")
						  .append("xmlUrl=\"")
						  .append(url)
						  .append("\" ")
						  .append("name=\"")
						  .append(feed.getName())
						  .append("\" ")
						  .append("userId=\"")
						  .append(feed.getUserId())
						  .append("\" ")
						  .append("tag=\"")
						  .append(feed.getTag())
						  .append("\" ")
						  .append("subTag=\"")
						  .append(feed.getSubTag())
						  .append("\" ")
						  .append("language=\"")
						  .append(feed.getLanguage())
						  .append("\" ")
						  .append("/>")
						  .append("\n")
						  ;
				}
			}
			buffer.append("</outline></body></opml>");
            //final Writer writer = new FileWriter(xml);
            FileOutputStream fileoutstream = new FileOutputStream(xml);
            Writer writer = new OutputStreamWriter(fileoutstream, "UTF-8");
            writer.write(buffer.toString());
            writer.close();
        }			
        catch (Exception ex) {
            ex.printStackTrace();               
     }
		return null;
	}
	public String getForumRss(String category,String categoryDesc,String id,String xml,List<ForumPost> topics,String url){
		List entries = new ArrayList();
		for(ForumPost topic : topics){
			SyndEntryImpl entry = new SyndEntryImpl();
		    entry.setAuthor(topic.getDisplayName());
	        entry.setTitle(topic.getTopic() + ((topic.getPosts() > 0) ? "("+topic.getPosts()+" posts)" : ""));
	        entry.setLink(url+"/forum/"+topic.getCategoryId()+"-"+topic.getForumId()+"-"+topic.getId());
	        entry.setPublishedDate(topic.getCreateDate());
	        SyndContentImpl description = new SyndContentImpl();
	        description.setType("text/plain");
	        description.setValue(topic.getContent());
	        entry.setDescription(description);
	       
			entries.add(entry);
		}
		doSyndication(category,url,categoryDesc,xml,entries);
		return null;
	}
	
	public String getForumTopicRss(String category,String categoryDesc,String xml,List<ForumPost> posts,String url){
		List entries = new ArrayList();
		for(ForumPost post : posts){
			SyndEntryImpl entry = new SyndEntryImpl();
		    entry.setAuthor(post.getDisplayName());
		    if(post.getTopId() == 0)
		    	entry.setTitle(post.getTopic());
		    else{
		    	if(post.getContent().length() > 30)
		    		entry.setTitle(post.getContent().substring(0,30));
		    	else
		    		entry.setTitle(post.getContent());
		    }
		    entry.setLink(url+"/forum/"+post.getCategoryId()+"-"+post.getForumId()+"-"+((post.getTopicId() > 0) ? post.getTopicId() : post.getId()));
	        entry.setPublishedDate(post.getCreateDate());
	        SyndContentImpl description = new SyndContentImpl();
	        description.setType("text/plain");
	        description.setValue(post.getContent());
	        entry.setDescription(description);
	       
			entries.add(entry);
		}
		doSyndication(category,url,categoryDesc,xml,entries);
		return null;
	}
	
	public String getBlogRss(String title,String titleDesc,String xml,List<Blog> blogs,String url){
		List entries = new ArrayList();
		for(Blog blog : blogs){
			SyndEntryImpl entry = new SyndEntryImpl();
		    entry.setAuthor(blog.getDisplayName());
	        entry.setTitle(blog.getTitle() + ((blog.getCommentCount() > 0) ? "("+blog.getCommentCount()+" comments)" : ""));
	        entry.setLink(url+"/blog/"+blog.getId());
	        entry.setPublishedDate(blog.getCreateDate());
	        SyndContentImpl description = new SyndContentImpl();
	        description.setType("text/plain");
	        description.setValue(blog.getSummary());
	        entry.setDescription(description);	       
			entries.add(entry);
		}
		doSyndication(title,url,titleDesc,xml,entries);
		return null;
	}
	
	/**
	 * This method is called last after you have added all your entries and have specified your
	 * feed type and filename. This actually does the work
	 * <p>
	 * NOTE: This has static content entered in to the fields! You must have access to the source
	 * code edit this method or else you will be publishing content as the Post Modern Banter Blog
	 * Yes, I should change this immediately. Ideally, it would take values from the web.xml file itself.
	 * <p>
	 * @throws Exception
	 */
	public  void doSyndication(String title, String link, String description_loc ,String xml, List entries ) {		 
		
            try {
 
                final SyndFeed feed = new SyndFeedImpl();
                feed.setFeedType(feedType);
                feed.setEncoding(_CHARSET_UTF);
                feed.setTitle(title);
                feed.setLink(link);
                feed.setDescription(description_loc);
                String copyright = "Copyright (c) "+DateUtil.format(new Date(),"yyyy")+" "+OrganizationThreadLocal.getWebId()+". All rights reserved.";
			    feed.setCopyright(copyright);
				
                feed.setEntries(entries);
			   
                final OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(xml),"UTF-8");
                final SyndFeedOutput output = new SyndFeedOutput();
                output.output(feed,out);
                out.close();
            }			
            catch (Exception ex) {
                ex.printStackTrace();               
         }

	}
}
