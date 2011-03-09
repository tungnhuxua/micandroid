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

package org.light.portlets.chat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.action.BaseAction;
import org.light.portal.model.User;
import org.light.portal.util.DomainUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portlets.chat.Chatting;
import org.light.portlets.chat.ChattingRecord;
import org.light.portlets.chat.ChattingUser;
import org.light.portlets.connection.Connection;

/**
 * 
 * @author Jianmin Liu
 **/
public class ChatAction extends BaseAction{
				
	public Object chatWithBuddy(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		long buddyUserId = Long.parseLong(request.getParameter("userId"));
		long userId = this.getUser(request).getId();
		User buddy = this.getUserService(request).getUserById(buddyUserId);	
		if(buddy.getImprivacy() == 2){
			return buddy.getDisplayName()+",n";
		}
		if(this.getUserService(request).getUserBlockByUser(buddyUserId,userId) == null){			
			Chatting chatting = new Chatting("private chatting",userId,this.getUser(request).getOrgId(),1);
			this.getPortalService(request).save(chatting);
			ChattingUser cUser1 = new ChattingUser(chatting.getId(),userId,1);
			this.getPortalService(request).save(cUser1);
			ChattingUser cUser2 = new ChattingUser(chatting.getId(),buddyUserId);
			this.getPortalService(request).save(cUser2);
			ChattingRecord record = new ChattingRecord(chatting.getId(), 0L, "", "*** Waiting for "+buddy.getDisplayName()+" to accept.");				  
			this.getChatService(request).save(record);
			return buddy.getDisplayName()+","+chatting.getId();
		}else{
			return buddy.getDisplayName()+","+0;
		}
	}
	
	public Object chatWithProfile(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		User user = this.getVisitedUser(request);
		long buddyUserId = user.getId();
		long userId = this.getUser(request).getId();
		if(user.getImprivacy() == 2){
			return user.getDisplayName()+",n";
		}
		if(user.getImprivacy() == 1 && this.getConnectionService(request).getChatBuddy(buddyUserId,userId) == null){
			return user.getDisplayName()+",f";
		}
		if(this.getUserService(request).getUserBlockByUser(buddyUserId,userId) == null){
			Chatting chatting = new Chatting("private chatting",userId,this.getUser(request).getOrgId(),1);
			this.getPortalService(request).save(chatting);
			ChattingUser cUser1 = new ChattingUser(chatting.getId(),userId,1);
			this.getPortalService(request).save(cUser1);
			ChattingUser cUser2 = new ChattingUser(chatting.getId(),buddyUserId);
			this.getPortalService(request).save(cUser2);
			ChattingRecord record = new ChattingRecord(chatting.getId(), 0L, "", "*** Waiting for "+user.getDisplayName()+" to accept.");				  
			this.getChatService(request).save(record);
			return user.getDisplayName()+","+chatting.getId();
		}else{
			return user.getDisplayName()+","+0;
		}
	}
	
	public Object chatWithMember(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		long memberId = Long.parseLong(request.getParameter("userId"));
		User user = this.getUserService(request).getUserById(memberId);
		long userId = this.getUser(request).getId();
		if(user.getImprivacy() == 2){
			return user.getDisplayName()+",n";
		}
		if(user.getImprivacy() == 1 && this.getConnectionService(request).getChatBuddy(memberId,userId) == null){
			return user.getDisplayName()+",f";
		}
		if(this.getUserService(request).getUserBlockByUser(memberId,userId) == null){
			Chatting chatting = new Chatting("private chatting",userId,this.getUser(request).getOrgId(),1);
			this.getPortalService(request).save(chatting);
			ChattingUser cUser1 = new ChattingUser(chatting.getId(),userId,1);
			this.getPortalService(request).save(cUser1);
			ChattingUser cUser2 = new ChattingUser(chatting.getId(),memberId);
			this.getPortalService(request).save(cUser2);
			ChattingRecord record = new ChattingRecord(chatting.getId(), 0L, "", "*** Waiting for "+user.getDisplayName()+" to accept.");				  
			this.getChatService(request).save(record);
			return user.getDisplayName()+","+chatting.getId();
		}else{
			return user.getDisplayName()+","+0;
		}
	}
	
	public Object acceptChat(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String chattingId = request.getParameter("chattingId");
		long id = Long.parseLong(chattingId);
		User user = this.getUser(request);
		ChattingRecord record = new ChattingRecord(id, 0L, "", "*** "+user.getDisplayName()+"'s IM window is open.");				  
		this.getChatService(request).save(record);
		return null;
	}
	
	public Object refuseChat(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String chattingId = request.getParameter("chattingId");
		long id = Long.parseLong(chattingId);
		User user = this.getUser(request);
		ChattingRecord record = new ChattingRecord(id, 0L, "", "*** "+user.getDisplayName()+" refused to IM you.");				  
		this.getChatService(request).save(record);
		
		ChattingUser cUser = this.getChatService(request).getChattingUsersById(id,user.getId());
		if(cUser != null){
			cUser.setStatus(-1);
			this.getChatService(request).save(cUser);
		}
		return null;
	}
	
	public Object showInviteList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String chattingId= request.getParameter("chattingId");
		List<Connection> buddys = this.getConnectionService(request).getBuddysByUser(this.getUser(request).getId());
		List<ChattingUser> cUsers = this.getChatService(request).getChattingUsersById(Long.parseLong(chattingId));
		Map<Long,ChattingUser> maps = new HashMap<Long,ChattingUser>();
		for(ChattingUser cUser : cUsers){
			maps.put(cUser.getUserId(),cUser);
		}
		StringBuilder buffer = new StringBuilder();
		if(buddys == null || buddys.size() ==0) 
			return buffer.append("<table border='0' cellpadding='0' cellspacing='0' >")
						 .append("<tr>")
						 .append("<td class='portlet-table-td-center'>")
						 .append("<label>You don't have any friends to invite yet.</label>")
						 .append("</td>")
					     .append("</tr>")
					     .append("</table>");
		String host = DomainUtil.getFullHost(request);
		buffer.append("<input type='hidden' name='chattingId' value='"+chattingId+"'/>")
			  .append("<table border='0' cellpadding='0' cellspacing='0' >");		
		
		int i=0;		
		for(Connection buddy : buddys){
			if(i++ % 3 == 0)
				buffer.append("<tr valign='top'>");
			buffer.append("<td class='portlet-table-td-center' width='70px'>");				  
			if(buddy.getPhotoUrl() == null){
					   buffer.append("<a href='http://");
					   if(PropUtil.getInt("portal.url.format") == 1)
						    buffer.append(buddy.getUri())
								  .append(".")
								  .append(host)
								  .append("' target='_blank'>")
								  ;
					   else
						    buffer.append(OrganizationThreadLocal.getOrg().getUserSpacePrefix())
						      	  .append(buddy.getUri())
								  ;
		    	buffer.append("<div style='position:relative;'>")
		    		  .append("<ul style='background: transparent url(")
		    		  .append(request.getContextPath())
		    		  .append(OrganizationThreadLocal.getOrg().getDefaultMalePortrait())
		    		  .append(") no-repeat scroll 0 0; list-style-type: none; width:50px; height:50px;margin:0 0 0 10px;padding:0;-moz-border-radius:5px;'>")
		    	 	  .append("<li>")			    	 	
		    	 	  .append("</li>")
		    	 	  .append("</ul>")
		    	 	  .append("</div>")
		    	 	  .append("</a>");
			 }else{
				 buffer.append("<a href='http://");
				 if(PropUtil.getInt("portal.url.format") == 1)
				    buffer.append(buddy.getUri())
						  .append(".")
						  .append(host)
						  ;
			     else
				    buffer.append(OrganizationThreadLocal.getOrg().getUserSpacePrefix())
				      	  .append(buddy.getUri())
						  ;
					  
			     buffer.append("' target='_blank'>")
			     	  .append("<div style='position:relative;'>")
		    		  .append("<ul style='background: transparent url(")
		    		  .append(request.getContextPath())
		    		  .append(buddy.getPhotoUrl())
		    		  .append(") no-repeat scroll 0 0; list-style-type: none; width:50px; height:50px;margin:0 0 0 10px;padding:0;-moz-border-radius:5px;'>")
		    	 	  .append("<li>")			    	 	
		    	 	  .append("</li>")
		    	 	  .append("</ul>")
		    	 	  .append("</div>")
		    	 	  .append("</a>")
		    	 	  ;
			 }
			buffer.append("<span class='portlet-item'>")
		    	  .append("<a href='http://");
			if(PropUtil.getInt("portal.url.format") == 1)
				buffer.append(buddy.getUri())
				  	  .append(".")
				      .append(host)
				      ;
			else
				buffer.append(OrganizationThreadLocal.getOrg().getUserSpacePrefix())
			      	  .append(buddy.getUri())
			      	  ;
			buffer.append("' target='_blank'>")
				  .append(buddy.getDisplayName())
				  .append("</a>")
				  .append("<br/>");
	        if(buddy.getBuddyCurrentStatusId() == 1){
				buffer.append("<img src='"+request.getContextPath()+"/light/images/online.gif' style='border: 0px' width='15' height='12' align='bottom' alt=''/>");
	        }	   
	        buffer.append("<input type='checkbox' name='buddys' value='"+buddy.getBuddyUserId()+"' class='portlet-form-checkbox'");
			//if(maps.containsKey(buddy.getBuddyUserId())){
			//	buffer.append(" checked='checked' disabled='true' ");
			//}
			buffer.append("/>");
	        buffer.append("</td>")
				  ;
			if(i % 3 == 0) buffer.append("</tr>");
		}
		if(buddys.size() % 3 != 0) buffer.append("</tr>");
		buffer.append("</table>");			  
		return buffer.toString();
	}
		
	public Object inviteBuddysToChat(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String schattingId= request.getParameter("chattingId");
		long chattingId = Long.parseLong(schattingId);
		String userIds = request.getParameter("userIds");
		String[] userIdArray = userIds.split(",");
		for(String userId : userIdArray){
			if(userId.length() > 0){
				User user = this.getUserService(request).getUserById(Long.parseLong(userId));
				ChattingUser cUser = new ChattingUser(chattingId,user.getId());
				this.getPortalService(request).save(cUser);
				ChattingRecord record = new ChattingRecord(chattingId, 0L, "", "*** Waiting for "+user.getDisplayName()+" to accept.");				  
				this.getChatService(request).save(record);				
			}
		}
		return null;
	}
	
	public Object enterChattingRoom(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String schattingId= request.getParameter("chattingId");
		long chattingId = Long.parseLong(schattingId);
		ChattingUser cUser = this.getChatService(request).getChattingUsersById(chattingId,this.getUser(request).getId());
		if(cUser == null){
			cUser= new ChattingUser(chattingId,this.getUser(request).getId(),1);
			this.getPortalService(request).save(cUser);
		}else{
			if(cUser.getStatus() != 1){
				cUser.setStatus(1);
				this.getPortalService(request).save(cUser);
			}
		}
		ChattingRecord record = new ChattingRecord(chattingId, 0L, "", "*** "+this.getUser(request).getDisplayName()+" enter this chat room.");				  
		this.getChatService(request).save(record);
		return null;
	}
	
	public Object closeChat(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String chattingId = request.getParameter("chattingId");
		long id = Long.parseLong(chattingId);
		User user = this.getUser(request);
		ChattingRecord record = new ChattingRecord(id, 0L, "", "*** "+user.getDisplayName()+"'s IM window is closed.");				  
		this.getChatService(request).save(record);
		ChattingUser cUser = this.getChatService(request).getChattingUsersById(id,this.getUser(request).getId());
		if(cUser != null){
			cUser.setStatus(-1);
			this.getChatService(request).save(cUser);
		}		
		return null;
	}
	
}

