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

package org.light.portlets.popular;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PopularItem;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.connection.Connection;
import org.light.portlets.message.Message;
import static org.light.portal.util.Constants._OBJECT_TYPE_POPULAR_ITEM;
/**
 * 
 * @author Jianmin Liu
 **/
public class PopularItemPortlet extends LightGenericPortlet {

    public void processAction (ActionRequest request, ActionResponse response)
       throws PortletException, java.io.IOException {
        String action = request.getParameter("action");
        if("config".equals(action)){
           String items = request.getParameter("items");
           PortletObject portlet =getPortlet(request);
           if(portlet != null){
               portlet.setShowNumber(Integer.parseInt(items));
               this.getPortalService(request).save(portlet);
           }
           String max = request.getParameter("maxItems");
           PortletPreferences portletPreferences = request.getPreferences();		 
   		   portletPreferences.setValue("maxViewed",max);
   		   portletPreferences.store();
           //request.setAttribute("mode",PortletMode.VIEW.toString());
           response.setPortletMode(PortletMode.VIEW);
       }
        else if("pop".equals(action)){
        	String id = request.getParameter("parameter");
        	if(id != null){
        		PopularItem item = this.getPortletService(request).getPopularItemById(Integer.parseInt(id));
        		if(item != null){
	        		item.popIt();
	        		this.getPortalService(request).save(item);
	        		request.setAttribute("success", "You have voted this news successfully.");
        		}
        	}
        }
        else if("forward".equals(action)){
        	String id = request.getParameter("parameter");
        	if(id != null){
        		PopularItem item = this.getPortletService(request).getPopularItemById(Integer.parseInt(id));
        		if(item != null){
	        		User user = this.getUser(request);
	                if(user != null){
	                    List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
	                    for(Connection friend : userFriends){
	                        Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
	                                "<a href='"+item.getLink()+"' target='_blank'>"+item.getTitle()+"</a><br/><br/>"+item.getDesc(),friend.getBuddyUserId(),user.getId(),user.getOrgId());
	                        this.getUserService(request).sendMessage(message);
	                    }
	                    request.setAttribute("success", "You have forwarded this news to your friends successfully.");
	                }
        		}
        	}
        }
	    else if("bookmark".equals(action)){
	     	String id = request.getParameter("parameter");
	     	if(id != null){
	     		PopularItem item = this.getPortletService(request).getPopularItemById(Integer.parseInt(id));
	     		if(item != null){
	     			Bookmark bookmark = new Bookmark(item.getTitle(),item.getLink(),item.getTag(),item.getTag(),item.getDesc(),this.getUser(request).getId());
		            this.getPortalService(request).save(bookmark);
		            request.getPortletSession().removeAttribute("bookmarkTags", PortletSession.APPLICATION_SCOPE);					   
		            request.getPortletSession().removeAttribute("defaultBookmarks", PortletSession.APPLICATION_SCOPE);
		            request.setAttribute("success", "You have saved this news to your bookmarks successfully.");
	     		}
	     	}
	    }
	    else if("delete".equals(action)){
	     	String id = request.getParameter("parameter");
	     	if(id != null){
	     		PopularItem item = this.getPortletService(request).getPopularItemById(Integer.parseInt(id));
	     		if(item != null){	     			
		            this.getPortalService(request).delete(item);
		            request.setAttribute("success", "You have deleted this voted news.");
	     		}
	     	}
	    }
     }

    protected void doView (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {	 String showComments = request.getParameter("showComments");
         if(showComments == null){
			 PortletObject portlet = this.getPortlet(request);
			 int start = 1;
			 int page = 1;
			 int showNumber = portlet.getShowNumber();
			 if(showNumber <=0) showNumber = 6;
			 PortletPreferences portletPreferences = request.getPreferences();		 
			 if(request.getWindowState().equals(WindowState.MAXIMIZED)){		   
			   String pageParam = request.getParameter("page"); 
			   String  max= portletPreferences.getValue("maxViewed","10");
			   showNumber = Integer.parseInt(max);
			   if(pageParam != null) page = Integer.parseInt(pageParam);			   
		       int total = this.getPortletService(request).getPopularItemTotal(OrganizationThreadLocal.getOrganizationId(),this.getUser(request).getRegion());
		       int pages  = total / showNumber;
			   if(total % showNumber != 0) pages++;
			   if(page > pages) page = pages;
			   start = (page - 1) *  showNumber + 1;
			   if(start < 1)start = 1;
			   int end = start + showNumber - 1;
			   if(end > total) end = total;
			   request.setAttribute("page",page);
			   request.setAttribute("start",start);		   
			   request.setAttribute("end",end);
			   request.setAttribute("total",total);
			   request.setAttribute("pages",pages);
			 }
			 String region = "en";
			 if(this.getUser(request) != null)
				 region = this.getUser(request).getRegion();
			 List<PopularItem> list =this.getPortletService(request).getPopularItems(OrganizationThreadLocal.getOrganizationId(),start - 1,showNumber,region);		 
			 request.setAttribute("lists",list);
			 request.setAttribute("showMore",true);
			 if(request.getWindowState().equals(WindowState.MAXIMIZED))
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/popularItemPortletMaxView.jsp").include(request,response);
			 else
				 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/popularItemPortletView.jsp").include(request,response);
         }else{
        	String id = request.getParameter("itemId"); 
        	if(id != null){
        		PopularItem item = this.getPortletService(request).getPopularItemById(Integer.parseInt(id));
        		request.setAttribute("item",item);        		
        		List<UserComment> comments = this.getUserService(request).getCommentsByType(Long.parseLong(id), _OBJECT_TYPE_POPULAR_ITEM);
        		request.setAttribute("comments",comments);
        		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/popularItemPortletViewComments.jsp").include(request,response);
        	}else{
        		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/popularItemPortletView.jsp").include(request,response);
        	}
         }
    }

    protected void doEdit (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {

        PortletObject portlet = this.getPortlet(request);
        int showNumber = portlet.getShowNumber();
        if(showNumber <=0) showNumber = 6;
        request.setAttribute("showNumber",showNumber);
        PortletPreferences portletPreferences = request.getPreferences();		 
		String  max= portletPreferences.getValue("maxViewed","10");
		request.setAttribute("maxNumber",max);		
        this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/popularItemPortletEdit.jsp").include(request,response);

    }

}

