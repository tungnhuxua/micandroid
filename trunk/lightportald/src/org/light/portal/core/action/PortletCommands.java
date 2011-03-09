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

package org.light.portal.core.action;

import static org.light.portal.util.Constants._CHARSET_UTF;
import static org.light.portal.util.Constants._MESSAGE_EVENT_COMMENT;
import static org.light.portal.util.Constants._MESSAGE_EVENT_TYPE_REQUEST;
import static org.light.portal.util.Constants._UNSUBSCRIBE_INDEX;
import static org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK;
import static org.light.portal.util.Constants._OBJECT_TYPE_NEWS;
import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._OBJECT_TYPE_RSS_ITEM;
import static org.light.portal.util.Constants._OBJECT_TYPE_POPULAR_ITEM;
import static org.light.portal.util.Constants._OBJECT_TYPE_RECOMMENDED_ITEM;
import static org.light.portal.util.Constants._OBJECT_TYPE_VIEWED_ITEM;
import static org.light.portal.util.Constants._OBJECT_TYPE_AD;
import static org.light.portal.util.Constants._OBJECT_TYPE_BLOG;
import static org.light.portal.util.Constants._OBJECT_TYPE_BOOKMARK;
import static org.light.portal.util.Constants._OBJECT_TYPE_DELI_ITEM;
import static org.light.portal.util.Constants._OBJECT_TYPE_FORUM;
import static org.light.portal.util.Constants._OBJECT_TYPE_INTERNAL_NEWS;
import static org.light.portal.util.Constants._OBJECT_TYPE_TODO_ITEM;
import static org.light.portal.util.Constants._STATUS_APPROVED;
import static org.light.portal.util.Constants._STATUS_STOP;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.core.PortalContextFactory;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.PopularItem;
import org.light.portal.model.Portal;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.RecommendedItem;
import org.light.portal.model.Subscriber;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserTag;
import org.light.portal.model.ViewedItem;
import org.light.portal.model.ViewedItemUser;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portlets.ad.CategoryAd;
import org.light.portlets.blog.Blog;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.bookmark.BookmarkTag;
import org.light.portlets.connection.Connection;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.internal.InternalNews;
import org.light.portlets.message.Message;
import org.light.portlets.note.Note;
import org.light.portlets.rss.RssBean;
import org.light.portlets.rss.RssCacheFactory;
import org.light.portlets.todolist.ToDoBean;
import org.light.portlets.youtube.VideoBean;
/**
 * 
 * @author Jianmin Liu
 **/
public class PortletCommands extends BaseAction{
	
	public Object getObjectDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{		 
		 String obj = request.getParameter("obj");
		 int objType = Integer.parseInt(obj);
		 switch (objType){
			 case _OBJECT_TYPE_AD:
				 return this.getAdDesc(request,response);
			 case _OBJECT_TYPE_BLOG:
				 return this.getBlogDesc(request,response);
			 case _OBJECT_TYPE_BOOKMARK:
				 return this.getBookmarkDesc(request,response);
			 case _OBJECT_TYPE_DELI_ITEM:
				 return this.getDeliDesc(request,response);
			 case _OBJECT_TYPE_FEEDBACK:
				 return this.getFeedbackDesc(request,response);
			 case _OBJECT_TYPE_FORUM:
				 return this.getForumDesc(request,response);
			 case _OBJECT_TYPE_INTERNAL_NEWS:
				 return this.getInternalNewsDesc(request,response);
			 case _OBJECT_TYPE_POPULAR_ITEM:
				 return this.getPopItemDesc(request,response);
			 case _OBJECT_TYPE_RECOMMENDED_ITEM:
				 return this.getRecommendedItemDesc(request,response);
			 case _OBJECT_TYPE_VIEWED_ITEM:
				 return this.getViewedItemDesc(request,response);
			 case _OBJECT_TYPE_RSS_ITEM:
				 return this.getRssDesc(request,response);
			 case _OBJECT_TYPE_TODO_ITEM:
				 return this.getTodoDesc(request,response);
			 default:
				 return null;
		 }
	}
	
	public Object getAdDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String index = request.getParameter("index");
		 int id = Integer.parseInt(index);
		 String content= null;
		 CategoryAd ad = this.getAdService(request).getAdById(id);
		 if(ad != null) content = ad.getContent();		 
		 if(content == null || content.equals("")) content="this Ad's content is unavaliable.";
		 return content;	
	}
	

	public Object getBlogDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String id = request.getParameter("index");		 
		 String desc= null;
		 Blog blog = this.getBlogService(request).getBlogById(Long.parseLong(id));
		 if(blog != null){
			 desc = blog.getSummary();
			 if(desc == null || desc.trim().length() <= 0){
				 desc = blog.getContent();		
				 if(desc != null && desc.length() > 200) 
					 desc = desc.substring(0,200)+"...";
			 }
		 }
		 if(desc == null || desc.equals("")) desc="this blog's summary is unavaliable.";		 
		 return desc;	
	}
	
	public Object getRssDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{		 
		 String index = request.getParameter("index");
		 int indx = Integer.parseInt(index);
		 String feed = request.getParameter("feed");
		 if(feed != null) feed = URLDecoder.decode(feed,_CHARSET_UTF);
		 StringBuilder desc= new StringBuilder();
		 List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
		 for(RssBean rss : lists){         
	         if(rss.getIndex() == indx){
	        	 desc.append(rss.getTitle())
	        	 	 .append("<br/>")
	        	 	 .append("<span class='portlet-note' style='margin-left:0px;'>")
	        	 	 .append(!StringUtil.isEmpty(rss.getAuthor()) ? MessageUtil.getMessage("portlet.label.postedBy",this.getLocale(request))+": "+rss.getAuthor()+" " : "")
	        	 	 .append(MessageUtil.getMessage("portlet.label.date",this.getLocale(request))+": "+rss.getDate())
	        	 	 .append("</span>")
	        	 	 .append("<br/><br/>")
	        	 	 .append(rss.getDesc())
	        	 	 ;
		        break;
	         }
        }
		 return desc.toString();	
	}
	
	public Object getFeedbackDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String index = request.getParameter("index");
		 long id = Long.parseLong(index);
		 StringBuilder content= new StringBuilder();
		 Feedback feedback = this.getUserService(request).getFeedbackById(id,OrganizationThreadLocal.getOrganizationId());
		 if(feedback != null && !StringUtil.isEmpty(feedback.getContent())) 
			 content.append(feedback.getContent());
		 else
			 content.append("this feedback's content is unavaliable.");
		 content.append(this.getObjectComments(id, _OBJECT_TYPE_FEEDBACK, request));		 
		 return content.toString();	
	}
	

	public Object getForumDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String index = request.getParameter("index");
		 int id = Integer.parseInt(index);
		 String content= null;
		 ForumPost forum = this.getForumService(request).getPostById(id);
		 if(forum != null) content = forum.getContent();
		 if(content == null || content.equals("")) content="this forum's content is unavaliable.";
		 return content;	
	}
	
	public Object configMyPicture(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pictureId = request.getParameter("pictureId");	
		String name = request.getParameter("name");			
		String value = request.getParameter("value");
		String responseId = request.getParameter("responseId");
		if(pictureId != null){
			UserPicture pic =this.getUserService(request).getUserPictureById(Long.parseLong(pictureId));
			if(name.startsWith("caption")) pic.setCaption(value);
			if(name.startsWith("tag")) pic.setTag(value);
			if(name.startsWith("status")) pic.setStatus(Integer.parseInt(value));
			this.getPortletService(request).save(pic);
			List<UserPicture> userPictures = (List<UserPicture>)request.getSession().getAttribute("myPictures");
		    if(userPictures != null)
		    {
		    	userPictures.remove(pic);
		    	userPictures.add(pic);
			    request.getSession().setAttribute("myPictures", userPictures);
		    }			
		}
		return responseId;
	}
	
	public Object getTodoDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String index = request.getParameter("index");
		 int id = Integer.parseInt(index);
		 String desc= null;
		 ToDoBean todo = this.getUserService(request).getToDoById(id);
		 if(todo != null) desc = todo.getDescription();
		 if(desc == null || desc.equals("")) desc="this To Do's discription is unavaliable.";
		 return desc;	
	}
	
	public Object saveNote(HttpServletRequest request, HttpServletResponse
			response) throws Exception{
      String content = request.getParameter("content");
      if(content != null){
          content = URLDecoder.decode(content,_CHARSET_UTF);
      }
      Note note = this.getUserService(request).getNoteByUser(this.getUser(request).getId());
      if(note != null){
          note.setContent(content);
          this.getPortletService(request).save(note);
      }
      return null;
	}
	
	public Object trackRssItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long personId = Context.getInstance().getPersonId(request,response);
        String index = request.getParameter("index");
        int indx = Integer.parseInt(index);
        String feed = request.getParameter("feed");
        String link = feed;
        String title = null;
        String desc= null;
        PortletObject po= this.getPortlet(request);
        if(po != null){ 	        	  
        	 String parameter = po.getParameter();
        	 int ind = parameter.indexOf("=");
     		 feed = parameter.substring(ind + 1);
        }
        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
        for(RssBean rss : lists){         
	         if(rss.getIndex() == indx){
	        	 title = rss.getTitle();
	        	 desc = rss.getDesc();
	        	 if(rss.getLink() != null) link = rss.getLink();
	        	 break;
	         }    
        }        
        if(title != null){        			
        	ViewedItem item= this.getPortletService(request).getViewedItemByLink(link);
            if(item == null){
            	String tag = "portlet.tag.title.news";
            	String locale = "en";
	            if(po != null){ 	        	  
	        	  PortletObjectRef ref=this.getPortalService(request).getPortletRefByName(po.getName());
	        	  tag = ref.getTag();
	        	  locale = ref.getLanguage();
	            }
                item = new ViewedItem(link,title,desc,tag,locale);                
            }else
                item.popIt();
            this.getPortletService(request).save(item);
            
            ViewedItemUser user = this.getPortletService(request).getViewedItemUser(item.getId(),personId);
            if(user == null){
            	user = new ViewedItemUser(item.getId(),personId);
            }else{
            	user.popIt();
            }
            this.getPortletService(request).save(user);
            
            trackUserKeywords(request,title,personId,_OBJECT_TYPE_NEWS);            
        }
        return 1;
   }
	
	private void trackUserKeywords(HttpServletRequest request, String title, long personId, int type){
		this.getUserService(request).trackUserKeywords(title,this.getUser(request),personId,type,1);
	}
	
	public Object readPopItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long personId = Context.getInstance().getPersonId(request,response);
        String itemId = request.getParameter("itemId");
        if(itemId != null){
	        long id = Long.parseLong(itemId);
	        PopularItem item = this.getPortletService(request).getPopularItemById(id);
	        if(item != null && this.getUser(request) != null){	        	
	        	ViewedItem vitem= this.getPortletService(request).getViewedItemByLink(item.getLink());
	            if(vitem == null){
	                vitem = new ViewedItem(item.getLink(),item.getTitle(),item.getDesc(),item.getTag(),item.getLocale());                
	            }else
	                vitem.popIt();
	            this.getPortletService(request).save(vitem);
	            ViewedItemUser user = this.getPortletService(request).getViewedItemUser(vitem.getId(),personId);
	            if(user == null){
	            	user = new ViewedItemUser(vitem.getId(),personId);
	            }else{
	            	user.popIt();
	            }
	            this.getPortletService(request).save(user);
	            
	            trackUserKeywords(request,item.getTitle(),personId,_OBJECT_TYPE_NEWS);    
	        }
        }
        return 1;
	}
	public Object readViewedItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long personId = Context.getInstance().getPersonId(request,response);
        String itemId = request.getParameter("itemId");
        if(itemId != null){
	        int id = Integer.parseInt(itemId);
	        ViewedItem item = this.getPortletService(request).getViewedItemById(id);
	        if(item != null && this.getUser(request) != null){	 
	            ViewedItemUser user = this.getPortletService(request).getViewedItemUser(item.getId(),personId);
	            if(user == null){
	            	user = new ViewedItemUser(item.getId(),personId);
	            }else{
	            	user.popIt();
	            }
	            this.getPortletService(request).save(user);
	            
	            trackUserKeywords(request,item.getTitle(),personId,_OBJECT_TYPE_NEWS);   
	        }
        }
        return 1;
	}
	public Object readRecommendedItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long personId = Context.getInstance().getPersonId(request,response);
        String itemId = request.getParameter("itemId");
        if(itemId != null){
	        long id = Long.parseLong(itemId);
	        RecommendedItem item = this.getPortletService(request).getRecommendedItemById(id);
	        if(item != null){
	        	if(personId == item.getPersonId()){
		        	item.setRead(1);
		        	this.getPortletService(request).save(item);		        
		        	ViewedItem vitem= this.getPortletService(request).getViewedItemByLink(item.getLink());
		            if(vitem == null){
		            	String tag = "portlet.title.recommendedItem";
		            	String locale = "en";
		            	locale = this.getUser(request).getLanguage();
		                vitem = new ViewedItem(item.getLink(),item.getTitle(),item.getDesc(),tag,locale);                
		            }else
		                vitem.popIt();
		            this.getPortletService(request).save(vitem);
		            ViewedItemUser user = this.getPortletService(request).getViewedItemUser(vitem.getId(),personId);
		            if(user == null){
		            	user = new ViewedItemUser(vitem.getId(),personId);
		            }else{
		            	user.popIt();
		            }
		            this.getPortletService(request).save(user);
		            trackUserKeywords(request,item.getTitle(),personId,_OBJECT_TYPE_NEWS);   
	        	}
	        }
        }
        return 1;
	}
	
	public Object popRssItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        int indx = Integer.parseInt(index);
        String feed = request.getParameter("feed");
        String link = feed;
        String title = null;
        String desc= null;
        PortletObject po= this.getPortlet(request);
        if(po != null){ 	        	  
        	 String parameter = po.getParameter();
        	 int ind = parameter.indexOf("=");
     		 feed = parameter.substring(ind + 1);
        }
        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
        for(RssBean rss : lists){         
	         if(rss.getIndex() == indx){
	        	 title = rss.getTitle();
	        	 desc = rss.getDesc();
	        	 if(rss.getLink() != null) link = rss.getLink();
	        	 break;
	         }    
       }
        if(title != null){
            PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),link);
            if(item == null){
               String tag = "portlet.tag.title.news";
               String locale = "en"; 	          
 	           if(po != null){ 	        	  
 	        	  PortletObjectRef ref=this.getPortalService(request).getPortletRefByName(po.getName());
 	        	  tag = ref.getTag();
 	        	  locale = ref.getLanguage();
 	           }
               item = new PopularItem(link,title,desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
            }else
                item.popIt();
            this.getPortletService(request).save(item);
        }
        return request.getParameter("responseId");
   }
	
	public Object popDeliItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String index = request.getParameter("index");
        int id = Integer.parseInt(index);
		List<BookmarkTag> deliciousTags=(List<BookmarkTag>)request.getSession().getAttribute("deliciousTags");
		List<Bookmark> delicious= (List<Bookmark>)request.getSession().getAttribute("delicious");
		String name=null;
		String desc=null;
		String url = null;
		String tagName = null;
		for(BookmarkTag tag : deliciousTags){
			for(Bookmark bm : tag.getBookmarks()){
	            if(bm.getId() == id){
	                name = bm.getName();
					url = bm.getUrl();
					desc = bm.getDesc();
					tagName = bm.getTagName();
	                break;
	            }
			}
        }
		if(name == null){
			for(Bookmark bm : delicious){
				if(bm.getId() == id){
	                name = bm.getName();
					desc = bm.getDesc();
					url = bm.getUrl();
					tagName = bm.getTagName();
	                break;
	            }
			}
		}
		if(name != null){
            PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),url);
            if(item == null){
               String tag = "portlet.title.delicious";
               String locale = this.getUser(request).getRegion();   	          
               item = new PopularItem(url,name,desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
            }else
                item.popIt();
            this.getPortletService(request).save(item);
        }
        return request.getParameter("responseId");
   }
	
	public Object popBookmarkItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        Bookmark bookmark = this.getUserService(request).getBookmarkById(id);
		if(bookmark != null){
			String desc = bookmark.getDesc();
            PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),bookmark.getUrl());
            if(item == null){
            	String tag = "portlet.title.bookmark";
                String locale = this.getUser(request).getRegion();   	
                item = new PopularItem(bookmark.getUrl(),bookmark.getName(),desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
            }else
                item.popIt();
            this.getPortletService(request).save(item);
        }
        return request.getParameter("responseId");
   }
	
	public Object popYouTubeItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        List<VideoBean> videos =(List<VideoBean>)request.getSession().getAttribute("youTubes");        
		if(videos != null){
			for(VideoBean video : videos){
				if(video.getId().equals(id)){
					PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),video.getVideoUrl());
					if(item == null){
						String tag = "YouTube";
			            String locale = this.getUser(request).getRegion();   	
						String desc = video.getDesc()+"<br/><img src='"+video.getPicUrl()+"' style='border: 0px;' />";
		                item = new PopularItem(video.getVideoUrl(),video.getTitle(),desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
					}else
		                item.popIt();
					this.getPortletService(request).save(item);
					break;
				}
			}           

        }
        return request.getParameter("responseId");
   }
	public Object forwardRssToFriend(HttpServletRequest request,
		HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        int indx = Integer.parseInt(index);
        String feed = request.getParameter("feed");
        String link = feed;
        String title = null;
        String desc= null;
        PortletObject po= this.getPortlet(request);
        if(po != null){ 	        	  
        	 String parameter = po.getParameter();
        	 int ind = parameter.indexOf("=");
     		 feed = parameter.substring(ind + 1);
        }
        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
        for(RssBean rss : lists){         
	         if(rss.getIndex() == indx){
	        	 title = rss.getTitle();
	        	 desc = rss.getDesc();
	        	 if(rss.getLink() != null) link = rss.getLink();
	        	 break;
	         }    
       }
       if(title != null){
           User user = this.getUser(request);
           if(user != null){
               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
               for(Connection friend : userFriends){
                   Message message = new Message(
                		   user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
                           "<a href='"+link+"' target='_blank'>"+title+"</a><br/><br/>"+desc
                           ,friend.getBuddyUserId()
                           ,user.getId()
                           ,OrganizationThreadLocal.getOrganizationId());
                   this.getUserService(request).sendMessage(message);
               }
           }
        }
        return request.getParameter("responseId");
   }
	
	public Object forwardDeliToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String index = request.getParameter("index");
        int id = Integer.parseInt(index);
		List<BookmarkTag> deliciousTags=(List<BookmarkTag>)request.getSession().getAttribute("deliciousTags");
		List<Bookmark> delicious= (List<Bookmark>)request.getSession().getAttribute("delicious");
		String name=null;
		String desc=null;
		String url=null;
		for(BookmarkTag tag : deliciousTags){
			for(Bookmark bm : tag.getBookmarks()){
	            if(bm.getId() == id){
	                name = bm.getName();
					desc = bm.getDesc();
					url = bm.getUrl();
	                break;
	            }
			}
        }
		if(name == null){
			for(Bookmark bm : delicious){
				if(bm.getId() == id){
	                name = bm.getName();
					desc = bm.getDesc();
					url = bm.getUrl();
	                break;
	            }
			}
		}
		if(name != null){
			if(desc == null) desc="";
			User user = this.getUser(request);
	           if(user != null){
	               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
	               for(Connection friend : userFriends){
	                   Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
	                           "<a href='"+url+"' target='_blank'>"+name+"</a><br/><br/>"+desc
	                           ,friend.getBuddyUserId()
	                           ,user.getId()
	                           ,OrganizationThreadLocal.getOrganizationId());
	                   this.getUserService(request).sendMessage(message);
	               }
	           }
        }
        return request.getParameter("responseId");
   }
	
	public Object forwardBookmarkToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        Bookmark bookmark = this.getUserService(request).getBookmarkById(id);
		if(bookmark != null){
		   String desc = bookmark.getDesc();
		   if(desc == null) desc="";
		   User user = this.getUser(request);
           if(user != null){
               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
               for(Connection friend : userFriends){
                   Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
                           "<a href='"+bookmark.getUrl()+"' target='_blank'>"+bookmark.getName()+"</a><br/><br/>"+desc
                           ,friend.getBuddyUserId()
                           ,user.getId()
                           ,OrganizationThreadLocal.getOrganizationId());
                   this.getUserService(request).sendMessage(message);
               }
           }
        }
        return request.getParameter("responseId");
   }
	
	public Object forwardYouTubeToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        List<VideoBean> videos =(List<VideoBean>)request.getSession().getAttribute("youTubes");        
		if(videos != null){
			for(VideoBean video : videos){
				if(video.getId().equals(id)){
				   User user = this.getUser(request);
		           if(user != null){
		               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
		               for(Connection friend : userFriends){
		                   Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
		                		   video.getTitle()+"<br><a href='"+video.getVideoUrl()+"' target='_blank'><img src='"+video.getPicUrl()+"' style='border: 0px;' title='"+video.getDesc()+"' /></a><br/><br/>"
		                		   ,friend.getBuddyUserId()
		                		   ,user.getId()
		                		   ,OrganizationThreadLocal.getOrganizationId());
		                   this.getUserService(request).sendMessage(message);
		               }
		           }
					break;
				}
			}           

        }
        return request.getParameter("responseId");
   }
	
	public Object saveYouTubeToBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        List<VideoBean> videos =(List<VideoBean>)request.getSession().getAttribute("youTubes");        
		if(videos != null){
			for(VideoBean video : videos){
				if(video.getId().equals(id)){
					String desc = video.getDesc()+"<br/><img src='"+video.getPicUrl()+"' style='border: 0px;' />";	                
					 Bookmark bookmark = new Bookmark(video.getTitle(),video.getVideoUrl(),video.getTags(),video.getTags(),desc,this.getUser(request).getId());
			         this.getPortletService(request).save(bookmark);
			         request.getSession().removeAttribute("bookmarkTags");
 				     request.getSession().removeAttribute("defaultBookmarks");	
					break;
				}
			}           

        }
        return request.getParameter("responseId");
   }
	
	public Object saveToHeader(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	        String index = request.getParameter("index");
	        int indx = Integer.parseInt(index);
	        String feed = request.getParameter("feed");
	        String desc= null;
	        PortletObject po= this.getPortlet(request);
	        if(po != null){ 	        	  
	        	 String parameter = po.getParameter();
	        	 int ind = parameter.indexOf("=");
	     		 feed = parameter.substring(ind + 1);
	        }
	        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
	        for(RssBean rss : lists){         
		         if(rss.getIndex() == indx){
		        	 desc = rss.getDesc();
		        	 break;
		         }    
	        }
	        String value = request.getParameter("responseId");
	        if(desc != null){
	        	if(desc.indexOf("img") > 0){
	        		String temp = desc.substring(desc.indexOf("img"));
	        		if(temp.indexOf("http") > 0){
	        			temp = temp.substring(temp.indexOf("http"));
	        			temp = temp.substring(0,temp.indexOf("\""));
	        			if(this.getPortal(request) != null){
	        				Portal portal = this.getPortal(request);
	        				portal.setHeaderImage(temp);
	        				this.getPortletService(request).save(portal);
	        				value= temp;
	        			}
	        		}
	        	}
	        }
	        return  value;
	}
	
	public Object saveToBackground(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	        String index = request.getParameter("index");
	        int indx = Integer.parseInt(index);
	        String feed = request.getParameter("feed");
	        String desc= null;
	        PortletObject po= this.getPortlet(request);
	        if(po != null){ 	        	  
	        	 String parameter = po.getParameter();
	        	 int ind = parameter.indexOf("=");
	     		 feed = parameter.substring(ind + 1);
	        }
	        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
	        for(RssBean rss : lists){         
		         if(rss.getIndex() == indx){
		        	 desc = rss.getDesc();
		        	 break;
		         }    
	        }
	        String value = request.getParameter("responseId");
	        if(desc != null){
	        	if(desc.indexOf("img") > 0){
	        		String temp = desc.substring(desc.indexOf("img"));
	        		if(temp.indexOf("http") > 0){
	        			temp = temp.substring(temp.indexOf("http"));
	        			temp = temp.substring(0,temp.indexOf("\""));
	        			if(this.getPortal(request) != null){
	        				Portal portal = this.getPortal(request);
	        				portal.setBgImage(temp);
	        				portal.setBgRepeat(0);
	        				this.getPortletService(request).save(portal);
	        				value= temp;
	        			}
	        		}
	        	}
	        }
	        return  value;
	}
	
	public Object saveToBookmark(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	        String index = request.getParameter("index");
	        int indx = Integer.parseInt(index);
	        String feed = request.getParameter("feed");
	        String link = feed;
	        String title = null;
	        String desc= null;
	        PortletObject po= this.getPortlet(request);
	        if(po != null){ 	        	  
	        	 String parameter = po.getParameter();
	        	 int ind = parameter.indexOf("=");
	     		 feed = parameter.substring(ind + 1);
	        }
	        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
	        for(RssBean rss : lists){         
		         if(rss.getIndex() == indx){
		        	 title = rss.getTitle();
		        	 desc = rss.getDesc();
		        	 if(rss.getLink() != null) link = rss.getLink();
		        	 break;
		         }    
	        }
	        if(desc == null) desc="news description is unavaliable.";
	        if(title != null){
	           String tag = "portlet.tag.title.news";	           
	           if(po != null)
	        	   tag = po.getLabel();
	           Bookmark bookmark = new Bookmark(title,link,tag,tag,desc,this.getUser(request).getId());
	           this.getPortletService(request).save(bookmark);
	           request.getSession().removeAttribute("bookmarkTags");
			   request.getSession().removeAttribute("defaultBookmarks");			   
	        }
	        return request.getParameter("responseId");
	   }
	
	public Object saveToMyPicture(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
	        String index = request.getParameter("index");
	        int indx = Integer.parseInt(index);
	        String feed = request.getParameter("feed");
	        String link = feed;
	        String title = null;
	        String desc= null;
	        PortletObject po= this.getPortlet(request);
	        if(po != null){ 	        	  
	        	 String parameter = po.getParameter();
	        	 int ind = parameter.indexOf("=");
	     		 feed = parameter.substring(ind + 1);
	        }
	        List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
	        for(RssBean rss : lists){         
		         if(rss.getIndex() == indx){
		        	 title = rss.getTitle();
		        	 desc = rss.getDesc();
		        	 if(rss.getLink() != null) link = rss.getLink();
		        	 break;
		         }    
	        }
	        if(title != null){
	        	User user = this.getUser(request);
     			if(user != null){
     				int i = desc.indexOf("<img");
     				String img = desc.substring(i);
     				i = img.indexOf("src=");
     				img = img.substring(i+5);
     				i =img.indexOf("\"");
     				String image = img.substring(0,i);     				
	    			int width = 300;
	    			int height= 280;
	    			i = img.indexOf("width");
	    			if( i > 0){
	    				String wid = img.substring(i+7);
	    				i = wid.indexOf("\"");
	    				width = Integer.parseInt(wid.substring(0, i));
	    			}
	    			i = img.indexOf("height");
	    			if( i > 0){
	    				String wid = img.substring(i+8);
	    				i = wid.indexOf("\"");
	    				height = Integer.parseInt(wid.substring(0, i));
	    			}
	    			int status = user.getDefaultPictureStatus();
	    			UserPicture picture = new UserPicture(user.getId(),user.getOrgId(),user.getOrgId(),image,status,width,height,title);			
	    			this.getPortletService(request).save(picture);	
	    			List<UserPicture> userPictures = (List<UserPicture>)request.getSession().getAttribute("myPictures");
	    		    if(userPictures == null)
	    		    {
	    		    	userPictures= new ArrayList<UserPicture>();
	    		    }
	    			userPictures.add(picture);
	    			request.getSession().setAttribute("myPictures", userPictures);		     					     		
		            request.setAttribute("success", "You have saved this picture to your pictures successfully.");
     			}
	        }
	        return request.getParameter("responseId");
	   }
	public Object getRecommendedItemDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        RecommendedItem item = this.getPortletService(request).getRecommendedItemById(id);
        String desc = null;
        if(item != null)
        	desc = item.getDesc();
		if(desc == null)
			desc = "No description available";
        return desc;
	}
	
	public Object getViewedItemDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        ViewedItem item = this.getPortletService(request).getViewedItemById(id);
        String desc = null;
        if(item != null)
        	desc = item.getDesc();
		if(desc == null)
			desc = "No description available";
        return desc;
	}
		
	public Object getPopItemDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        PopularItem item = this.getPortletService(request).getPopularItemById(id);
        String desc = null;
        if(item != null)
        	desc = item.getDesc();
		if(desc == null)
			desc = "No description available";
        return desc;
	}
	
	public Object getBookmarkDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        Bookmark item = this.getUserService(request).getBookmarkById(id);
        String desc = null;
        if(item != null)
        	desc = item.getDesc();
		if(desc == null)
			desc = "No description available";
        return desc;
	}
	
	public Object getInternalNewsDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        long id = Long.parseLong(index);
        InternalNews news = this.getPortletService(request).getInternalNewsById(id,OrganizationThreadLocal.getOrganizationId());
		String desc = null;
        if(news != null)
        	desc = news.getContent();
		if(desc == null)
			desc = "No description available";
        return desc;
	}
	public Object getDeliDesc(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String index = request.getParameter("index");
        int id = Integer.parseInt(index);
		List<BookmarkTag> deliciousTags=(List<BookmarkTag>)request.getSession().getAttribute("deliciousTags");
		List<Bookmark> delicious= (List<Bookmark>)request.getSession().getAttribute("delicious");
		String name=null;
		String desc=null;
		for(BookmarkTag tag : deliciousTags){
			for(Bookmark bm : tag.getBookmarks()){
	            if(bm.getId() == id){
	                name = bm.getName();
					desc = bm.getDesc();
	                break;
	            }
			}
        }
		if(name == null){
			for(Bookmark bm : delicious){
				if(bm.getId() == id){
					desc = bm.getDesc();
	                break;
	            }
			}
		}
		if(desc == null)
			desc = "No description available";
        return desc;
	}
	
	public Object sendMessage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String responseId = request.getParameter("responseId");
		String toUserId = request.getParameter("toUserId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String sformat = request.getParameter("format");
		if(this.getUser(request) != null 
		  && ! StringUtil.isEmpty(toUserId)){
			try{
				toUserId = URLDecoder.decode(toUserId,_CHARSET_UTF);	
				if(subject != null) subject = URLDecoder.decode(subject,_CHARSET_UTF);		   	
				if(content != null) content = URLDecoder.decode(content,_CHARSET_UTF);					
				int format = 0;
				try{format = Integer.parseInt(sformat);}catch(Exception e){}
			    if(content != null){
				   if(format == 1){
					   content = HTMLUtil.disableHTML(content);
				   }else{
					   content = HTMLUtil.disableScripts(content);				   
				   }
				   content = content.replaceAll("\n","<br/>").trim();
			    }
				Message message = new Message(subject,content,Long.parseLong(toUserId),this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId(),format);
				this.getUserService(request).sendMessage(message);
				Message sent = new Message(subject,content,Long.parseLong(toUserId),this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId(),format,1);
				this.getUserService(request).sendMessage(sent);
				return responseId;
			}catch(Exception e){
				return -1;
			}
		}else{
			return 0;
		}
		
	}
	public Object getPicturePositionTaggingContent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String responseId = request.getParameter("responseId");
		String tagId = request.getParameter("tagId");
		String pointX = request.getParameter("pointX");
		String pointY = request.getParameter("pointY");
		StringBuilder buffer = new StringBuilder();
		buffer.append(responseId)
			  .append("^")
			  .append(tagId)
			  .append("^")
			  .append(pointX)
			  .append("^")
			  .append(pointY)
			  .append("^")
			  ;
		
		return buffer.toString();
	}
	public Object getObjectComments(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		String objectId = request.getParameter("objectId");
		String objectType = request.getParameter("objectType"); 
		long id = Long.parseLong(objectId);
		int type = Integer.parseInt(objectType);
		StringBuilder content = new StringBuilder();
		content.append("<span title='"+PortalContextFactory.getPortalContext().getMessageByKey("portlet.button.close")+"'  width='100%' style='clear: both;  display: block; text-align:right;'><a href='javascript:void(0);' onclick='javascript:hideObjectComments();'><img src='"+request.getContextPath()+"/light/images/close_on.gif' style='border: 0px;'/></a></span>")
		 	   .append(this.getObjectComments(id,type,request))
		 	   ;
		return content.toString();
	}
	private String getObjectComments(long ojbectId, int objectType, HttpServletRequest request){
		 StringBuilder content= new StringBuilder();
		 List<UserComment> comments = this.getUserService(request).getCommentsByType(ojbectId, objectType);
		 if(comments != null && comments.size() > 0) {
			 content.append("<table border='0' cellpadding='0' cellspacing='0'>")
			 		.append("<tr>")
			 		.append("<td class='portlet-table-td-left'>")
			 		.append("<br/><b><label>")
			 		.append(comments.size())
			 		.append(" ")
			 		.append(PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.comments"))
			 		.append(":</label></b><br/>")
			 		.append("</td>")
			 		.append("</tr>")
					;
			 for(UserComment comment : comments){	
				 content.append("<tr>")
				 		.append("<td class='portlet-table-td-center'>")
				 		.append("<br/><hr/>")
				 		.append("</td>")
				 		.append("</tr>")
				 		;
				 String displayName = comment.getDisplayName();
				 if(displayName == null)
					 displayName = PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.anonym");
				 content.append("<tr>")
						.append("<td class='portlet-table-td-left'>")
						.append("<span class='portlet-note' style='padding:0;'>")					
						.append(PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.commentedBy")+" ")
						;
				 if(comment.getUserId() != OrganizationThreadLocal.getOrg().getUserId()){
					 content.append("<a href='http://");
					 if(PropUtil.getInt("portal.url.format") == 1)
						 content.append(comment.getUri())
							  	  .append(".")
							      .append(OrganizationThreadLocal.getOrg().getHost())
							      ;
					 else
						content.append(OrganizationThreadLocal.getOrg().getUserSpacePrefix())
					      	   .append(comment.getUri())
					      	   ;
					 content.append("' target='_blank'>")
						    .append(displayName)
						    .append("</a>")
						    ;
				 }else
					 content.append(PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.anonym")+ " ");
				 content
					    .append(PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.at")+" "+comment.getDate())
						.append("</span>")					
						.append("</td>")
						.append("</tr>")
					    .append("<tr>")
						.append("<td class='portlet-table-td-left'>")
						.append("<label>"+comment.getComment()+"</label>")
						.append("</td>")					
						.append("</tr>")
						;
			 }  
			 content.append("<tr>")
			 		.append("<td class='portlet-table-td-center'>")
			 		.append("<br/><hr/>")
			 		.append("</td>")
			 		.append("</tr>")
			 		.append("</table>")	
					;
		 }
		 return content.toString();	
	}
	public Object saveObjectComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String content = request.getParameter("comment");
		String objectId = request.getParameter("objectId");
		String objectType = request.getParameter("objectType");
		String parentId = request.getParameter("parentId");
		String responseId = request.getParameter("responseId");		
		int status = 1;
		if(content != null){
			content = URLDecoder.decode(content,_CHARSET_UTF);
			content = HTMLUtil.disableScripts(content);
			UserComment comment = new UserComment(this.getUser(request).getId(),Long.parseLong(objectId),Integer.parseInt(objectType),content);
			comment.setParentId(Long.parseLong(parentId));
			User user = null;
			if(Integer.parseInt(objectType) == _OBJECT_TYPE_USER){
				user = this.getUserService(request).getUserById(Long.parseLong(objectId));
				if(user != null && user.getCommentNeedApprove() == 0)
					comment.setStatus(_STATUS_APPROVED);				
			}else
				comment.setStatus(_STATUS_APPROVED);
			this.getPortletService(request).save(comment);
			if(user != null && user.getCommentNeedApprove() == 1){	
				String name = this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()
						? PortalContextFactory.getPortalContext().getMessageByKey("portlet.label.anonym")
						: this.getUser(request).getName(); 
				Message message = new Message(
						 name +" post a comment to your wall, need you to approve."
						,content
						,user.getId()
						,this.getUser(request).getId()
						,user.getOrgId()
						,_MESSAGE_EVENT_TYPE_REQUEST
						,_MESSAGE_EVENT_COMMENT
						,comment.getId());
				this.getUserService(request).sendMessage(message);
				status = 0;
			}
		}
		JSONObject json = new JSONObject();
		json.put("id",responseId);
		json.put("objectId",objectId);
		json.put("status",status);
		return json;
	}
	public Object deleteObjectComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("commentId");		
		String responseId = request.getParameter("responseId");
		if(id != null){			
			this.getUserService(request).deleteUserCommentsById(Long.parseLong(id));
		}
		return responseId;
	}
	
	public Object addUserTag(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tag = request.getParameter("tag");
		String objectType = request.getParameter("objectType");
		String objectId = request.getParameter("objectId");
		String responseId = request.getParameter("responseId");
		if(!StringUtil.isEmpty(tag)){
			User user = this.getUser(request);
			String[] tags = tag.split(",");
			for(String t : tags){
				if(t.trim().length() > 0){
					if(!this.getUserService(request).hasUserTag(Long.parseLong(objectId),Integer.parseInt(objectType),tag)){
						UserTag userTag = new UserTag(t.trim(),Long.parseLong(objectId),Integer.parseInt(objectType),user.getId(),user.getOrgId());
						this.getPortletService(request).save(userTag);
					}
				}
			}
			return responseId;
		}else
			return "";
	}
	public Object subscribe(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		JSONObject json = new JSONObject();
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		if(email != null && type != null){
			try{
				int t = 0;
				try{ t= Integer.parseInt(type);}catch(Exception e){}
				long orgId = OrganizationThreadLocal.getOrganizationId();
				Subscriber sub = this.getUserService(request).getSubscriber(email,t,orgId);
				if(sub == null){
					sub = new Subscriber(email, t, orgId,_STATUS_APPROVED);
					this.getPortalService(request).save(sub);			
				}else{
					if(sub.getStatus() != _STATUS_APPROVED){
						sub.setStatus(_STATUS_APPROVED);
						this.getPortalService(request).save(sub);			
					}
				}
				json.put("id",sub.getId());
			}catch(Exception e){
				//ignore exception
			}
		}
		return json;	
	}		
	public Object unsubscribe(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		JSONObject json = new JSONObject();
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		if(email != null && type != null){
			try{
				int t = 0;
				try{ t= Integer.parseInt(type);}catch(Exception e){}
				long orgId = OrganizationThreadLocal.getOrganizationId();
				Subscriber sub = this.getUserService(request).getSubscriber(email,t,orgId);
				if(sub != null){					
					if(sub.getStatus() != _STATUS_STOP){
						sub.setStatus(_STATUS_STOP);
						this.getPortalService(request).save(sub);			
					}
					json.put("id",sub.getId());
				}
				
			}catch(Exception e){
				//ignore exception
			}
		}
		response.sendRedirect(request.getContextPath()+_UNSUBSCRIBE_INDEX);
		return json;	
	}		
	private static Logger logger = LoggerFactory.getLogger(PortletCommands.class);
}