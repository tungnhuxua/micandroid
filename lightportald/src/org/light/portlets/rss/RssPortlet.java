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

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.light.portal.model.PopularItem;
import org.light.portal.model.PortletObject;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.StringUtil;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.connection.Connection;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class RssPortlet extends LightGenericPortlet {
	
	public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
		String action = request.getParameter("action");
		if("edit".equals(action)){
			String feed = request.getParameter("feed");
			String title = request.getParameter("title");
			String icon = request.getParameter("icon");
			String url = request.getParameter("url");
			String autoRefresh = request.getParameter("autoRefresh");
			String minute = request.getParameter("minute");
			String items = request.getParameter("items");
			String showType = request.getParameter("showType");
			PortletObject rssPortlet =getPortlet(request);		
			if(rssPortlet != null){
				rssPortlet.setLabel(title);
				rssPortlet.setIcon(icon);
				rssPortlet.setUrl(url);
				rssPortlet.setAutoRefreshed(Integer.parseInt(autoRefresh));
				rssPortlet.setPeriodTime(Integer.parseInt(minute) * 60000);
				rssPortlet.setShowNumber(Integer.parseInt(items));
				rssPortlet.setShowType(Integer.parseInt(showType));
				rssPortlet.setParameter("feed="+feed);
				this.getPortalService(request).save(rssPortlet);
			}
			response.setPortletMode(PortletMode.VIEW);
		}
		else if("pop".equals(action)){
	       	String id = request.getParameter("parameter");
	       	if(id != null){
	       		int indx = Integer.parseInt(id);
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
                this.getPortalService(request).save(item);
                request.setAttribute("success", "You have voted this news successfully.");
	          }
	       	}
	       }
	       else if("forward".equals(action)){
	       	String id = request.getParameter("parameter");
	       	if(id != null){
	       		int indx = Integer.parseInt(id);
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
                        Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
                                "<a href='"+link+"' target='_blank'>"+title+"</a><br/><br/>"+desc,friend.getBuddyUserId(),user.getId(),user.getOrgId());
                        this.getUserService(request).sendMessage(message);
                    }
                }
                request.setAttribute("success", "You have forwarded this news to your friends successfully.");
               }               
	       	}
	       }
		   else if("bookmark".equals(action)){
	     	String id = request.getParameter("parameter");
	     	if(id != null){
	     		int indx = Integer.parseInt(id);
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
		           this.getPortalService(request).save(bookmark);
		           request.getPortletSession().removeAttribute("bookmarkTags",PortletSession.APPLICATION_SCOPE);
				   request.getPortletSession().removeAttribute("defaultBookmarks",PortletSession.APPLICATION_SCOPE);	
				   request.setAttribute("success", "You have saved this news to your bookmarks successfully.");
		        }
	     	}
	    }
	    else if("delete".equals(action)){
			
		}
	  }
 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		PortletObject portlet = this.getPortlet(request);
		String parameter = portlet.getParameter();
		int ind = parameter.indexOf("=");
		String feed = parameter.substring(ind + 1);
		if(StringUtil.isEmpty(feed)){
			return;
		}
		List<RssBean> lists = RssCacheFactory.getInstance().getRss(feed);
				
		String indx= request.getParameter("index");
		if(indx != null){
			int index = Integer.parseInt(indx);
			String previous = request.getParameter("previous");
			String next = request.getParameter("next");
			if(previous != null) index--;
			if(next != null) index++;
			if(index < 0) index = lists.size() - 1;
			if(index >= lists.size()) index = 0;
			RssBean item = null;
			for(RssBean rss : lists){
				if(rss.getIndex() == index){
					item = rss;
					break;
				}
			}
			request.setAttribute("item",item);
			request.setAttribute("readerHeight",300);
			request.setAttribute("readerMaxHeight",600);
			this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/rss/rssReader.jsp").include(request,response);
			return;
		}
		
		int showNumber = (portlet != null) ? portlet.getShowNumber() : 6;
		if(lists != null){		
			int page = 1;
			String pageNumber = request.getParameter("page");
			if(request.getPortletSession().getAttribute("page",PortletSession.PORTLET_SCOPE) != null) page = (Integer)request.getPortletSession().getAttribute("page",PortletSession.PORTLET_SCOPE);
			if(pageNumber != null) page = Integer.parseInt(pageNumber);
			
			int total = lists.size();
			int totalPages = (total % showNumber == 0) ? total / showNumber : total / showNumber + 1;
			
			if(page < 1) page = totalPages;
			if(page > totalPages) page = 1;
			int start = (page - 1) *  showNumber;
			if(start < 0) start = 0;
			int end = start + showNumber;
			if(end > total) end = total;
			request.setAttribute("page",page);
			request.getPortletSession().setAttribute("page",page,PortletSession.PORTLET_SCOPE);
			request.setAttribute("pages",totalPages);
			request.setAttribute("start",start);		   
			request.setAttribute("end",end);
			request.setAttribute("total",total);
			if(request.getWindowState().equals(WindowState.NORMAL)){
				request.setAttribute("rssLists",lists.subList(start, end));
			}else{
				request.setAttribute("rssLists",lists);
			}
		}
		request.setAttribute("portlet",portlet);
		if(portlet != null && lists != null && portlet.getShowNumber() > 0 && portlet.getShowNumber() < lists.size())
			request.setAttribute("showMore",true);			
		
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/rss/rssPortletView.jsp").include(request,response);
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  
		PortletObject rssPortlet =getPortlet(request);		
		if(rssPortlet != null){
			String feed = rssPortlet.getParameter().substring(5,rssPortlet.getParameter().length());
			request.setAttribute("feed",feed);
			request.setAttribute("portlet",rssPortlet);
			request.setAttribute("minute",rssPortlet.getPeriodTime() / 60000);
		}
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/rss/rssPortletEdit.jsp").include(request,response);
	 }	
	 	 
}
