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

package org.light.portlets.forum.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.action.BaseAction;
import org.light.portal.model.PopularItem;
import org.light.portal.model.User;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.connection.Connection;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumAction extends BaseAction{
			
	public Object popForumItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        String pageId = request.getParameter("pageId");
        Forum forum = this.getForumService(request).getForumById(Integer.parseInt(id));
        if(forum != null){
        	ForumCategory category = this.getForumService(request).getForumCategoryById(forum.getCategoryId());		 
        	String title = category.getDisplayName()+"->"+forum.getDisplayName();
			String desc = category.getDisplayDesc()+"->"+forum.getDisplayDesc();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+forum.getCategoryId()+"-"+forum.getId();			
			PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),url);
            if(item == null){
            	String tag = "portlet.title.forum";
            	String locale = Locale.ENGLISH.toString(); 
                if(this.getUser(request) != null) locale = this.getUser(request).getRegion();  
                item = new PopularItem(url,title,desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
            }else
                item.popIt();
            this.getPortalService(request).save(item);
        }
        return request.getParameter("responseId");
   }
	
	public Object popTopicItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        String pageId = request.getParameter("pageId");
        ForumPost topic = this.getForumService(request).getPostById(Integer.parseInt(id));
		if(topic != null){			
			String desc = topic.getContent();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+topic.getCategoryId()+"-"+topic.getForumId()+"-"+((topic.getTopicId() > 0) ? topic.getTopicId() : topic.getId());			
			PopularItem item= this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),url);
            if(item == null){
            	String tag = "portlet.title.forum";
            	String locale = Locale.ENGLISH.toString(); 
                if(this.getUser(request) != null) locale = this.getUser(request).getRegion();  
                item = new PopularItem(url,topic.getTopic(),desc,tag,locale,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
            }else
                item.popIt();
            this.getPortalService(request).save(item);
        }
        return request.getParameter("responseId");
   }
	
	public Object forwardForumToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("index");
		String pageId = request.getParameter("pageId");
        Forum forum = this.getForumService(request).getForumById(Integer.parseInt(id));
        if(forum != null){
        	ForumCategory category = this.getForumService(request).getForumCategoryById(forum.getCategoryId());		 
        	String title = category.getDisplayName()+"->"+forum.getDisplayName();
			String desc = category.getDisplayDesc()+"->"+forum.getDisplayDesc();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+forum.getCategoryId()+"-"+forum.getId();			
			User user = this.getUser(request);
            if(user != null){
               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());               	              
               for(Connection friend : userFriends){
                   Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
                           "<a href='"+url+"' target='_blank'>"+title+"</a><br/><br/>"+desc,friend.getBuddyUserId(),user.getId(),user.getOrgId());
                   this.getUserService(request).sendMessage(message);
               }
           }
        }
        return request.getParameter("responseId"); 
		
   }
	
	public Object forwardTopicToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("index");
		ForumPost topic = this.getForumService(request).getPostById(Integer.parseInt(id));
		if(topic != null){			
			String desc = topic.getContent();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+topic.getCategoryId()+"-"+topic.getForumId()+"-"+((topic.getTopicId() > 0) ? topic.getTopicId() : topic.getId());			
			User user = this.getUser(request);
			if(user != null){
				List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());               	              
				for(Connection friend : userFriends){
					Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
                           "<a href='"+url+"' target='_blank'>"+topic.getTopic()+"</a><br/><br/>"+desc,friend.getBuddyUserId(),user.getId(),user.getOrgId());
					this.getUserService(request).sendMessage(message);
				}
			}
        }
        return request.getParameter("responseId");
  }
	
	public Object saveForumToBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("index");
		String pageId = request.getParameter("pageId");
        Forum forum = this.getForumService(request).getForumById(Integer.parseInt(id));
        if(forum != null){
        	ForumCategory category = this.getForumService(request).getForumCategoryById(forum.getCategoryId());		 
        	String title = category.getDisplayName()+"->"+forum.getDisplayName();
			String desc = category.getDisplayDesc()+"->"+forum.getDisplayDesc();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+forum.getCategoryId()+"-"+forum.getId();			
			String tag = category.getDisplayName();	           
	           Bookmark bookmark = new Bookmark(title,url,category.getName(),tag,desc,this.getUser(request).getId());
	           this.getPortalService(request).save(bookmark);
	           request.getSession().removeAttribute("bookmarkTags");
			   request.getSession().removeAttribute("defaultBookmarks");	
        }
        return request.getParameter("responseId"); 
		
	   }
	
	public Object saveTopicToBookmark(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("index");
		String pageId = request.getParameter("pageId");
        ForumPost topic = this.getForumService(request).getPostById(Integer.parseInt(id));
		if(topic != null){
			Forum forum = this.getForumService(request).getForumById(topic.getForumId());
			ForumCategory category = this.getForumService(request).getForumCategoryById(forum.getCategoryId());		 
        	
			String desc = topic.getContent();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/forum/"+topic.getCategoryId()+"-"+topic.getForumId()+"-"+((topic.getTopicId() > 0) ? topic.getTopicId() : topic.getId());			
			String tag = category.getDisplayName()+"->"+forum.getDisplayName();
			String tagId= category.getName()+"->"+forum.getName();
           Bookmark bookmark = new Bookmark(topic.getTopic(),url,tagId,tag,desc,this.getUser(request).getId());
           this.getPortalService(request).save(bookmark);
           request.getSession().removeAttribute("bookmarkTags");
		   request.getSession().removeAttribute("defaultBookmarks");	
        }
        return request.getParameter("responseId");
	}
}
