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

package org.light.portlets.ad.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.action.BaseAction;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.util.DateUtil;
import org.light.portal.util.MessageUtil;
import org.light.portlets.ad.CategoryAd;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.connection.Connection;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class AdAction extends BaseAction{
		
	public Object popAd(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		String strAdId = request.getParameter("adId");
		String responseId = request.getParameter("responseId");
		int id = Integer.parseInt(strAdId);
		CategoryAd ad = this.getAdService(request).getAdById(id);
		ad.setScore(ad.getScore() + 1);
		this.getPortalService(request).save(ad);
		return responseId;
	}
	
	public Object forwardAdToFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String index = request.getParameter("index");
        int id = Integer.parseInt(index);
        CategoryAd ad = this.getAdService(request).getAdById(id);
		if(ad != null){
			String desc = ad.getContent();
			if(desc == null) desc="";
			User user = this.getUser(request);
	           if(user != null){
	               List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());
	               for(Connection friend : userFriends){
	                   Message message = new Message(user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request)),
	                		   "Location: "+ad.getLocation()+
	                		   "<br/>"+
	                		   "Effective Date: "+DateUtil.format(ad.getEffDate())+"-"+DateUtil.format(ad.getEndEffDate())+
	                		   "<br/>"+
	                		   "Ad Url: <a href='"+ad.getAdUrl()+"' target='_blank'>"+ad.getAdUrl()+"</a>"+
	                		   "<br/>"+
	                		   "Ad Detail:<br/>"+
	                		   desc,friend.getBuddyUserId(),user.getId(),user.getOrgId());
	                   this.getUserService(request).sendMessage(message);
	               }
	           }
        }
        return request.getParameter("responseId");
   }
	public Object saveAdToBookmark(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			String index = request.getParameter("index");
	        int id = Integer.parseInt(index);
	        String link = null;
	        String title = null;
	        String desc = null;
	        CategoryAd ad = this.getAdService(request).getAdById(id);
			if(ad != null){
	         link = ad.getAdUrl();
	         title = ad.getTitle();
	         desc= ad.getContent();
			}
	        if(desc == null) desc="ad description is unavaliable.";
	        if(title != null){
	           String tag = "portlet.tag.title.ad";
	           PortletObject po= this.getPortlet(request);
	           if(po != null)
	        	   tag = po.getLabel();
	           Bookmark bookmark = new Bookmark(title,link,tag,tag,desc,this.getUser(request).getId());
	           this.getPortalService(request).save(bookmark);
	           request.getSession().removeAttribute("bookmarkTags");
			   request.getSession().removeAttribute("defaultBookmarks");			   
	        }
	        return request.getParameter("responseId");
	   }
}
