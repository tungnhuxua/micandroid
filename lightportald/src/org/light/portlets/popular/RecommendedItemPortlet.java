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

import static org.light.portal.util.Constants._OBJECT_TYPE_NEWS;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.Context;
import org.light.portal.model.PopularItem;
import org.light.portal.model.PortletObject;
import org.light.portal.model.RecommendedItem;
import org.light.portal.model.User;
import org.light.portal.model.UserKeyword;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.portlet.core.impl.PortletRequestImpl;
import org.light.portal.portlet.core.impl.PortletResponseImpl;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.connection.Connection;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class RecommendedItemPortlet  extends LightGenericPortlet {

    public void processAction (ActionRequest request, ActionResponse response)
       throws PortletException, java.io.IOException {
        String action = request.getParameter("action");
        if("config".equals(action)){
           String items = request.getParameter("items"); 
           String max = request.getParameter("maxItems");
           PortletObject portlet =getPortlet(request);
           if(portlet != null){
               portlet.setShowNumber(Integer.parseInt(items));
               this.getPortletService(request).save(portlet);
           	           
	           PortletPreferences portletPreferences = request.getPreferences();		 
	   		   portletPreferences.setValue("maxViewed",max);
	   		   portletPreferences.store();
           }
   		   String growKeyword = request.getParameter("growKeyword");   		   
		   User user = this.getUser(request);
		   if(user != null){
			   if(growKeyword != null){
				   user.setGrowKeyword(1);
			   }else{
				   user.setGrowKeyword(0);
			   }
			   this.getUserService(request).save(user);
   		   }
		   request.getPortletSession().setAttribute("showNumber",items,PortletSession.APPLICATION_SCOPE);
		   request.getPortletSession().setAttribute("maxViewed",max,PortletSession.APPLICATION_SCOPE);
           //request.setAttribute("mode",PortletMode.VIEW.toString());
           response.setPortletMode(PortletMode.VIEW);
       }      
        else if("pop".equals(action)){
        	String id = request.getParameter("parameter");
        	if(id != null){
        		RecommendedItem vItem = this.getPortletService(request).getRecommendedItemById(Integer.parseInt(id));
        		if(vItem != null){
	        		PopularItem item = this.getPortletService(request).getPopularItemByLink(OrganizationThreadLocal.getOrganizationId(),vItem.getLink());
	        		if(item != null){
		        		item.popIt();
	        		}else{
	        			item = new PopularItem(vItem.getLink(),vItem.getTitle(),vItem.getDesc(),vItem.getTag(),vItem.getLocale(),this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
	        		}
		        	this.getPortletService(request).save(item);
		        	request.setAttribute("success", "You have voted this news successfully.");	        		
        		}
        	}
        }
        else if("forward".equals(action)){
        	String id = request.getParameter("parameter");
        	if(id != null){
        		RecommendedItem item = this.getPortletService(request).getRecommendedItemById(Integer.parseInt(id));
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
	     		RecommendedItem item = this.getPortletService(request).getRecommendedItemById(Integer.parseInt(id));
	     		if(item != null){
	     			Bookmark bookmark = new Bookmark(item.getTitle(),item.getLink(),item.getTag(),item.getTag(),item.getDesc(),this.getUser(request).getId());
		            this.getPortletService(request).save(bookmark);
		            request.getPortletSession().removeAttribute("bookmarkTags", PortletSession.APPLICATION_SCOPE);					   
		            request.getPortletSession().removeAttribute("defaultBookmarks", PortletSession.APPLICATION_SCOPE);
		            request.setAttribute("success", "You have saved this news to your bookmarks successfully.");
	     		}
	     	}
	    }
	    else if("delete".equals(action)){
	     	String id = request.getParameter("parameter");
	     	if(id != null){
	     		RecommendedItem item = this.getPortletService(request).getRecommendedItemById(Integer.parseInt(id));
	     		if(item != null){	     			
		            this.getPortletService(request).delete(item);
		            request.setAttribute("success", "You have deleted this recommended news.");
	     		}
	     	}
	    }
	    else if("deleteAll".equals(action)){	     	
	     	if(this.getUser(request) != null){
	     		this.getPortletService(request).deleteRecommendedItems(this.getUser(request).getId());
	     		request.setAttribute("success", "You have deleted all recommended news.");	     		
	     	}
	    }
	    else if("saveKeywords".equals(action)){
        	String words = request.getParameter("words");
        	HttpServletRequest httpRequest = ((PortletRequestImpl)request).getHttpServletRequest();
	    	HttpServletResponse httpResponse = ((PortletResponseImpl)response).getHttpServletResponse();	    	  
	    	long personId = Context.getInstance().getPersonId(httpRequest,httpResponse);
	    	this.getUserService(request).trackUserKeywords(words,this.getUser(request),personId,_OBJECT_TYPE_NEWS,100);
	    	response.setPortletMode(PortletMode.EDIT);
        }
        else if("deleteKeywords".equals(action)){
        	String[] ids = request.getParameterValues("keywords");
        	if(ids != null){
	    		for(int i=0;i<ids.length;i++){
	    			if(ids[i].trim().length() > 0){
	    				long id = Long.parseLong(ids[i]);
	    				UserKeyword word = this.getUserService(request).getUserKeywordById(id);
	    				this.getPortletService(request).delete(word);
	    			}
	    		}
        	}  
        	response.setPortletMode(PortletMode.EDIT);
        }
     }

    protected void doView (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {	 
    	 long personId = this.getUser(request).getPersonId();
		 if(this.getVisitedUser(request) != null)
			 personId = this.getVisitedUser(request).getPersonId();
		 String region = "en";
		 if(this.getUser(request) != null){
			 region = this.getUser(request).getRegion();
		 }
		 String indx= request.getParameter("index");
		 if(indx != null){
			long id = Long.parseLong(indx);
			String previous = request.getParameter("previous");
			String next = request.getParameter("next");
			if(previous != null) id++;
			if(next != null) id--;
			RecommendedItem item = this.getPortletService(request).getRecommendedItemNext(id, personId,region);
			if(item != null){
				request.setAttribute("item",item);
				request.setAttribute("readerHeight",300);
				request.setAttribute("readerMaxHeight",1000);
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/recommendedItemReader.jsp").include(request,response);
				return;
			}
		 }
    	 PortletObject portlet = this.getPortlet(request);
		 int start = 0;
		 int page = 1;
		 int showNumber = 6;
		 if(portlet != null && portlet.getShowNumber() > 0){
			 showNumber = portlet.getShowNumber();
		 }
		 if(request.getPortletSession().getAttribute("showNumber",PortletSession.APPLICATION_SCOPE) != null)
			 showNumber = Integer.parseInt((String)request.getPortletSession().getAttribute("showNumber",PortletSession.APPLICATION_SCOPE));		  
		 PortletPreferences portletPreferences = request.getPreferences();	

		 if(request.getWindowState().equals(WindowState.MAXIMIZED)){		   
		   String  max= portletPreferences.getValue("maxViewed","10");
		   if(request.getPortletSession().getAttribute("maxViewed",PortletSession.APPLICATION_SCOPE) != null)
			   max= (String)request.getPortletSession().getAttribute("maxViewed",PortletSession.APPLICATION_SCOPE);
		   showNumber = Integer.parseInt(max);
		 }
		 String pageParam = request.getParameter("page"); 
		 if(pageParam != null) page = Integer.parseInt(pageParam);			   	
		 int total = this.getPortletService(request).getRecommendedItemTotal(personId);
		 int pages  = total / showNumber;
		 if(total % showNumber != 0) pages++;
		 if(page > pages) page = pages;
		 start = (page - 1) *  showNumber;
		 if(start < 0)start = 0;
		 int end = start + showNumber;
		 if(end > total) end = total;
		 request.setAttribute("page",page);
		 request.setAttribute("start",start);		   
		 request.setAttribute("end",end);
		 request.setAttribute("total",total);
		 request.setAttribute("pages",pages);
		
		 List<RecommendedItem> list =this.getPortletService(request).getRecommendedItems(start - 1,showNumber,personId);		 
		 request.setAttribute("lists",list);
		 if(list != null && list.size() > showNumber)
			 request.setAttribute("showMore",true);
		 if(request.getWindowState().equals(WindowState.MAXIMIZED))
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/recommendedItemPortletMaxView.jsp").include(request,response);
		 else
			 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/recommendedItemPortletView.jsp").include(request,response);        
	}
	
	
	protected void doEdit (RenderRequest request, RenderResponse response)
	throws PortletException, java.io.IOException
	{
	
	  PortletObject portlet = this.getPortlet(request);
	  int showNumber = 0;
	  if(portlet != null) showNumber = portlet.getShowNumber();
	  if(request.getPortletSession().getAttribute("showNumber",PortletSession.APPLICATION_SCOPE) != null)
		 showNumber = Integer.parseInt((String)request.getPortletSession().getAttribute("showNumber",PortletSession.APPLICATION_SCOPE));		  		 
	  if(showNumber <=0) showNumber = 6;
	  request.setAttribute("showNumber",showNumber);
	  PortletPreferences portletPreferences = request.getPreferences();		 
	  String  max= portletPreferences.getValue("maxViewed","10");
	  if(request.getPortletSession().getAttribute("maxViewed",PortletSession.APPLICATION_SCOPE) != null)
		 max= (String)request.getPortletSession().getAttribute("maxViewed",PortletSession.APPLICATION_SCOPE);
	  request.setAttribute("maxNumber",max);
	  if(this.getUser(request) != null && this.getVisitedUser(request) == null){
		  List<UserKeyword> keywords = this.getUserService(request).getUserKeywords(this.getUser(request).getPersonId());
		  request.setAttribute("keywords",keywords);		  
		  request.setAttribute("growKeyword",this.getUser(request).getGrowKeyword());
	  }	 
	  
	  this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/popular/recommendedItemPortletEdit.jsp").include(request,response);
	
	}		
}


