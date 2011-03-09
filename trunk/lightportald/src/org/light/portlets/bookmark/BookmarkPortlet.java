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

package org.light.portlets.bookmark;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class BookmarkPortlet extends LightGenericPortlet {

   public void processAction (ActionRequest request, ActionResponse response)
   throws PortletException, java.io.IOException {
       String action = request.getParameter("action");
       if("add".equals(action)){
          String name = request.getParameter("name");
          if(name == null ||"".equals(name) ){
        	  request.setAttribute("error", "Please input bookmark's name.");
              return;
          }
          String url = request.getParameter("url");
          if(url == null ||"".equals(url) ){
        	  request.setAttribute("error", "Please input bookmark's URL.");
              return;
          }
          String tagId = request.getParameter("tag");
          String tagName = request.getParameter("tag");
          String desc = request.getParameter("desc");
          desc = HTMLUtil.removeScripts(desc);
          Bookmark bookmark = new Bookmark(name,url,tagId,tagName,desc,this.getUser(request).getId());
          this.getPortalService(request).save(bookmark);
          if(tagId != null){
              List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
              if(bookmarkTags != null){
               boolean contained = false;
               for(BookmarkTag tag : bookmarkTags){
                   if(tag.getTagId().equals(tagId)){
                       tag.addBookmark(bookmark);
                       contained = true;
                       break;
                   }
               }
               if(!contained){
                   BookmarkTag tag = new BookmarkTag(tagId,tagName);
                   tag.addBookmark(bookmark);
                   bookmarkTags.add(tag);
               }
              }else{
                  bookmarkTags = new ArrayList<BookmarkTag>();
                  BookmarkTag tag = new BookmarkTag(tagId,tagName);
                  tag.addBookmark(bookmark);
                  bookmarkTags.add(tag);

                  request.getPortletSession().setAttribute("bookmarkTags", bookmarkTags,PortletSession.APPLICATION_SCOPE);
              }
          }else{
              List<Bookmark> bookmarks=(List<Bookmark>)request.getPortletSession().getAttribute("defaultBookmarks",PortletSession.APPLICATION_SCOPE);
              if(bookmarks != null)
                  bookmarks.add(bookmark);
              else{
                  bookmarks = new ArrayList<Bookmark>();
                  bookmarks.add(bookmark);

                  request.getPortletSession().setAttribute("defaultBookmarks", bookmarks,PortletSession.APPLICATION_SCOPE);
              }
          }
          request.setAttribute("success", "You have added a bookmark successfully.");
       }
       if("modify".equals(action)){
           String id = request.getParameter("bookmarkId");
           String name = request.getParameter("name");
          if(name == null ||"".equals(name) ){
        	  request.setAttribute("error", "Please input bookmark's name.");
              return;
          }
          String url = request.getParameter("url");
          if(url == null ||"".equals(url) ){
        	  request.setAttribute("error", "Please input bookmark's URL.");
              return;
          }
          String tagId = request.getParameter("tagId");
          String tagIdNew = request.getParameter("tag");
          String tagName = request.getParameter("tag");
          String desc = request.getParameter("desc");
          Bookmark bookmark = null;
          if(tagId == null){
        	  List<Bookmark> bookmarks=(List<Bookmark>)request.getPortletSession().getAttribute("defaultBookmarks",PortletSession.APPLICATION_SCOPE);
        	  for(Bookmark bm : bookmarks){
                  if(bm.getId() == Integer.parseInt(id)){
                	  bookmark = bm;
                	  if(tagIdNew != null){
                		  bookmarks.remove(bm);
                		  List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
                          if(bookmarkTags != null){
                           boolean contained = false;
                           for(BookmarkTag tag : bookmarkTags){
                               if(tag.getTagId().equals(tagIdNew)){
                                   tag.addBookmark(bookmark);
                                   contained = true;
                                   break;
                               }
                           }
                           if(!contained){
                               BookmarkTag tag = new BookmarkTag(tagIdNew,tagName);
                               tag.addBookmark(bookmark);
                               bookmarkTags.add(tag);
                           }
                          }else{
                              bookmarkTags = new ArrayList<BookmarkTag>();
                              BookmarkTag tag = new BookmarkTag(tagIdNew,tagName);
                              tag.addBookmark(bookmark);
                              bookmarkTags.add(tag);
                              request.getPortletSession().setAttribute("bookmarkTags", bookmarkTags,PortletSession.APPLICATION_SCOPE);
                          }
                	  }
                	  break;
                  }
              }        	  
          }else{
        	  boolean changeTag = false;
        	  List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
        	  for(BookmarkTag tag : bookmarkTags){
                  if(tag.getTagId().equals(tagId)){
                	  for(Bookmark bm : tag.getBookmarks()){
                          if(bm.getId() == Integer.parseInt(id)){
                        	  bookmark = bm;
                        	  if(! tagId.equals(tagIdNew)){
                        		  tag.removeBookmark(bm);
                        		  changeTag = true;
                        	  }
                        	  break;
                          }
                      }
                  }
              }
        	  if(changeTag){
	        	  boolean contained = false;
	              for(BookmarkTag tag2 : bookmarkTags){
	                  if(tag2.getTagId().equals(tagIdNew)){
	                      tag2.addBookmark(bookmark);
	                      contained = true;
	                      break;
	                  }
	              }
	              if(!contained){
	                  BookmarkTag tag2 = new BookmarkTag(tagIdNew,tagName);
	                  tag2.addBookmark(bookmark);
	                  bookmarkTags.add(tag2);
	              } 
        	  }
          }          
           if(bookmark != null){
               bookmark.setName(name);
               bookmark.setUrl(url);
               bookmark.setTagId(tagIdNew);
               bookmark.setTagName(tagName);
               bookmark.setDesc(desc);
               this.getPortalService(request).save(bookmark);
               request.setAttribute("success", "You have modified a bookmark successfully.");
           }
           
       }
       if("delete".equals(action)){
           String id = request.getParameter("parameter");
           Bookmark bookmark  =this.getUserService(request).getBookmarkById(Integer.parseInt(id));
           if(bookmark != null){
               if(bookmark.getTagId() != null){
                   List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
                   for(BookmarkTag tag : bookmarkTags){
                       if(tag.getTagId().equals(bookmark.getTagId())){
                           tag.removeBookmark(bookmark);

                           break;
                       }
                   }
               }else{
                   List<Bookmark> bookmarks=(List<Bookmark>)request.getPortletSession().getAttribute("defaultBookmarks",PortletSession.APPLICATION_SCOPE);
                   bookmarks.remove(bookmark);
               }
               this.getPortalService(request).delete(bookmark);
               request.setAttribute("success", "You have deleted a bookmark successfully.");
           }          
       }
       if("cancel".equals(action)){
    	   response.setPortletMode(PortletMode.VIEW);
       }
       if("deleteTag".equals(action)){
           String tag = request.getParameter("parameter");
           List<Bookmark> bookmarks  =this.getUserService(request).getBookmarksByTag(this.getUser(request).getId(),tag);
           List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
           bookmarkTags.remove(new BookmarkTag(tag,tag));
           for(Bookmark bookmark : bookmarks){
               this.getPortalService(request).delete(bookmark);
           }
           request.setAttribute("success", "You have deleted a bookmark tag and this tag's all bookmarks successfully.");
       }
       if("expand".equals(action)){
           String tagId = request.getParameter("parameter");
           List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
           for(BookmarkTag tag : bookmarkTags){
               if(tag.getTagId().equals(tagId)){
                   tag.setExpanded(true);
                   break;
               }
           }
       }
       if("close".equals(action)){
           String tagId = request.getParameter("parameter");
           List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
           for(BookmarkTag tag : bookmarkTags){
               if(tag.getTagId().equals(tagId)){
                   tag.setExpanded(false);
                   break;
               }
           }
       }
     }

    protected void doView (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {
       List<BookmarkTag> bookmarkTags=(List<BookmarkTag>)request.getPortletSession().getAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
	   List<Bookmark> defaultBookmarks = (List<Bookmark>)request.getPortletSession().getAttribute("defaultBookmarks",PortletSession.APPLICATION_SCOPE);
	   if((bookmarkTags == null && defaultBookmarks == null) || this.getVisitedUser(request) != null){
		   long userId = this.getUser(request).getId();
		   if(this.getVisitedUser(request) != null)
		      userId = this.getVisitedUser(request).getId();
           List<Bookmark> bookmarks =this.getUserService(request).getBookmarksByUser(userId);
           List<BookmarkTag> tags = null;           
           for(Bookmark bookmark : bookmarks){
               if(bookmark.getTagId() != null){
                   if(tags == null) tags = new ArrayList<BookmarkTag>();
                   BookmarkTag tag = new BookmarkTag(bookmark.getTagId(),bookmark.getTagName());
                   if(!tags.contains(tag)){
                       tag.addBookmark(bookmark);
                       tags.add(tag);
                   }else{
                       for(BookmarkTag bTag : tags){
                           if(bTag.equals(tag)){
                               bTag.addBookmark(bookmark);
                               break;
                           }
                       }
                   }
               }else{
                   if(defaultBookmarks == null) defaultBookmarks = new ArrayList<Bookmark>();
                   defaultBookmarks.add(bookmark);
               }
           }
           request.getPortletSession().setAttribute("bookmarkTags",tags, PortletSession.APPLICATION_SCOPE);
           request.getPortletSession().setAttribute("defaultBookmarks",defaultBookmarks, PortletSession.APPLICATION_SCOPE);
       }

       this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bookmark/bookmarkPortletView.jsp").include(request,response);


    }

    protected void doEdit (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {
       String id = request.getParameter("parameter");
       if(id != null){
           Bookmark bookmark  =this.getUserService(request).getBookmarkById(Integer.parseInt(id));
           if(bookmark != null)
               request.setAttribute("bookmark", bookmark);
       }

       this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/bookmark/bookmarkPortletEdit.jsp").include(request,response);

    }

}