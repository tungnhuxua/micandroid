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

package org.light.portlets.group.action;

import static org.light.portal.util.Constants._MESSAGE_EVENT_GROUP;
import static org.light.portal.util.Constants._MESSAGE_EVENT_TYPE_INVITE;
import static org.light.portal.util.Constants._MESSAGE_EVENT_TYPE_REQUEST;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.action.BaseAction;
import org.light.portal.model.PopularItem;
import org.light.portal.model.User;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.ValidationUtil;
import org.light.portlets.group.Group;
import org.light.portlets.group.UserGroup;
import org.light.portlets.message.Message;

/**
 * 
 * @author Jianmin Liu
 **/
public class GroupAction extends BaseAction{
	
	public Object validateGroupUri(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String uri = request.getParameter("uri");
		 if(!ValidationUtil.isValidUrl(uri)) return 1;
		 if(!isValidInteralUri(request, uri)) return 1;		 		 
		 return 0;	
	}
	
	public Object joinToGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String responseId = request.getParameter("responseId");
		 if(this.getUser(request) == null || (this.getUser(request) != null && this.getUser(request).getId() == OrganizationThreadLocal.getOrg().getUserId()))
			 return "9;"+responseId;
		 Group group =  this.getVisitedGroup(request);
		 if(group == null){
			 String groupId = request.getParameter("groupId");		
			 try{
				 long id = Long.parseLong(groupId);
				 group = this.getGroupService(request).getGroupById(id);
			 }catch(Exception e){}			 
		 }
		 if(group != null){
			 if(group.getOpenJoin() == 1){
				 UserGroup userGroup = this.getGroupService(request).getUserGroup(this.getUser(request).getId(),group.getId());
				 if(userGroup == null){
					 userGroup = new UserGroup(this.getUser(request).getId(),group.getId());
					 this.getPortalService(request).save(userGroup);
					 return "1;"+responseId;
				 }else{
					 return "2;"+responseId;
				 }
			 }
			 if(group.getOpenJoin() == 0){		
				 //send request message to the owner.
				 User user = this.getUser(request);
				 Message message = new Message(
						 user.getDisplayName()+" request to join to the group "+group.getDisplayName()
						 ,user.getDisplayName()+" request to join to the group "+group.getDisplayName()
						 ,group.getOwnerId(),user.getId(),user.getOrgId(),_MESSAGE_EVENT_TYPE_REQUEST,_MESSAGE_EVENT_GROUP,group.getId());
				 this.getUserService(request).sendMessage(message);
				 return "3;"+responseId;
			 }
			 
		 }			 
		 return "0;"+responseId;	
	}
	
	public Object inviteToGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String groupId = request.getParameter("groupId");
		 String responseId = request.getParameter("responseId");
		 int id = Integer.parseInt(groupId);
		 Group group = this.getGroupService(request).getGroupById(id);
		 StringBuffer friendNames=new StringBuffer("<br/>");
		 if(group != null){
			 User user = this.getUser(request);
			 String friends = request.getParameter("friends");
			 if(friends != null){
				 String[] friendArray = friends.split(";");				 
				 //send invite message to friends.
				 for(int i=0;i<friendArray.length;i++){					 
					 Message message = new Message(
							 user.getDisplayName()+" would like to invite you to join the group "+group.getDisplayName()
							 ,user.getDisplayName()+" would like to invite you to join the group "+group.getDisplayName()							 
							 ,Long.parseLong(friendArray[i]),user.getId(),user.getOrgId(),_MESSAGE_EVENT_GROUP,_MESSAGE_EVENT_TYPE_INVITE,group.getId());
					 this.getUserService(request).sendMessage(message);
					 User f = this.getUserService(request).getUserById(Long.parseLong(friendArray[i]));
					 friendNames.append(f.getDisplayName())
					            .append("<br/>");
					 
				 }
			 }
			 
		 }			 
		 return friendNames.toString()+";"+responseId;	
	}
	
	public Object resignGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String groupId = request.getParameter("groupId");
		 String responseId = request.getParameter("responseId");
		 int id = Integer.parseInt(groupId);
		 UserGroup userGroup = this.getGroupService(request).getUserGroup(this.getUser(request).getId(),id);
		 if(userGroup != null){
			 this.getPortalService(request).delete(userGroup);			 
		 }
		 return responseId;	
	}
	
	public Object getGroupPrivacy(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String groupId = request.getParameter("groupId");		 
		 long id = Long.parseLong(groupId);
		 UserGroup userGroup = this.getGroupService(request).getUserGroup(this.getUser(request).getId(),id);
		 if(userGroup == null){
			 groupId+=";"+userGroup.getAcceptLeaderBulletin()+";"+userGroup.getAcceptMembersBulletin();
		 }
		 return groupId;	
	}
	
	public Object saveGroupPrivacy(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String groupId = request.getParameter("groupId");		 
		 long id = Long.parseLong(groupId);
		 UserGroup userGroup = this.getGroupService(request).getUserGroup(this.getUser(request).getId(),id);
		 if(userGroup != null){			 
			 if(request.getParameter("lBulletin") != null)
			    userGroup.setAcceptLeaderBulletin(1);
			 else
				 userGroup.setAcceptLeaderBulletin(0);
			 if(request.getParameter("mBulletin") != null)
			    userGroup.setAcceptMembersBulletin(1);
			 else
				userGroup.setAcceptMembersBulletin(0);
			 this.getPortalService(request).save(userGroup);
		 }
		 return groupId;	
	}
	
	public Object deleteGroupProfile(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		 String groupId = request.getParameter("groupId");	
		 String responseId = request.getParameter("responseId");
		 long id = Long.parseLong(groupId);
		 List<UserGroup> userGroup = this.getGroupService(request).getUsersByGroup(id);
		 if(userGroup != null && userGroup.size() > 1){
			 groupId="0";
		 }else{
			 Group group = this.getGroupService(request).getGroupById(id);
			 this.getPortalService(request).delete(group);
			 for(UserGroup ug : userGroup)
				 this.getPortalService(request).delete(ug);
			 
		 }
		 return groupId+";"+responseId;	
	}
	public Object popGroupForumItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("index");
        String pageId = request.getParameter("pageId");
        Group group = this.getGroupService(request).getGroupById(Integer.parseInt(id));
        if(group != null){        		 
        	String title = group.getDisplayName();
			String desc = group.getShortDesc();
			String url = "http://www."+OrganizationThreadLocal.getOrg().getWebId()+"/groupforum.do?id="+group.getId()+"p"+pageId;
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
	
}
