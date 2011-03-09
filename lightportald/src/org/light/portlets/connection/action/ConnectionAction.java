package org.light.portlets.connection.action;

import static org.light.portal.util.Constants._MESSAGE_EVENT_CONNECTION;
import static org.light.portal.util.Constants._MESSAGE_EVENT_TYPE_REQUEST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.light.portal.core.action.BaseAction;
import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.model.UserFavourite;
import org.light.portal.util.MessageUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.connection.Connection;
import org.light.portlets.message.Message;

public class ConnectionAction extends BaseAction{
		
	public Object getUserDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userId = request.getParameter("userId");
		String responseId = (String)request.getParameter("responseId");
		JSONObject json = new JSONObject();
		if(userId != null){
			User user = this.getUserService(request).getUserById(Long.parseLong(userId));
			int isFriend = 0;
			Connection buddy =this.getConnectionService(request).getChatBuddy(this.getUser(request).getId(),user.getId());
			if(buddy != null)
				isFriend = 1;			
			int status = 0;
			if(user != null) status = user.getCurrentStatus();
			try{			 
				 json.put("id", responseId);
				 json.put("userId",userId);
				 json.put("name",user.getName());
				 json.put("email",user.getEmail());
				 json.put("isFriend",isFriend);
				 json.put("status",status);
				 if(buddy != null) json.put("type",buddy.getType());						
			 }catch(Exception e){			 
			 }			 
		}
		return json;
	}
		
	public Object addFriendRequest(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String sbuddyId = request.getParameter("buddyId");
		long buddyId = 0;
		if(sbuddyId == null || sbuddyId.equals("0"))
			buddyId= this.getVisitedUser(request).getId();
		else
			buddyId= Long.parseLong(sbuddyId);
		User user = this.getUser(request);
		if(buddyId > 0 && user != null){
			if(this.getConnectionService(request).getChatBuddy(buddyId,this.getUser(request).getId()) == null){
				Message message = new Message(
						 this.getUser(request).getName()+" sent you a friend request."
						,this.getUser(request).getName()+" want to add you as friend."
						,buddyId
						,user.getId()
						,user.getOrgId()
						,_MESSAGE_EVENT_TYPE_REQUEST
						,_MESSAGE_EVENT_CONNECTION
						,0L);
				this.getUserService(request).sendMessage(message);
			}
		}
		return request.getParameter("responseId");
	}
	
	public Object addToFavorites(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		User user = this.getVisitedUser(request);
		UserFavourite userFavourite = new UserFavourite(this.getUser(request).getId(),user.getId());				  
		this.getChatService(request).save(userFavourite);
		return request.getParameter("responseId");
	}
	
	public Object forwardToFriends(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		User visitor = this.getVisitedUser(request);
		User user = this.getUser(request);
        if(visitor != null && user != null){
            List<Connection> userFriends =this.getConnectionService(request).getBuddysByUser(user.getId());            
            String subject = user.getDisplayName()+MessageUtil.getMessage("portlet.message.sentlink",this.getLocale(request));
            String webId = OrganizationThreadLocal.getOrg().getWebId();
            String url = "http://"+OrganizationThreadLocal.getOrg().getUserSpacePrefix()+visitor.getUri();
            String photoUrl = "http://"+OrganizationThreadLocal.getOrg().getHost();
            int photoWidth=75;
            int photoHeight=75;
            if(visitor.getPhotoUrl() == null){
            	photoUrl+="/light/images/no_pic.gif";
            }else{
            	photoUrl+=visitor.getPhotoUrl();
            	photoWidth=visitor.getPhotoSmallWidth();
            	photoHeight=visitor.getPhotoSmallHeight();
            }
            for(Connection friend : userFriends){
            	Map<String,String> model = new HashMap<String,String>();
	            model.put("name", friend.getDisplayName());
	            model.put("webId", webId);
	            model.put("subject", subject);
	            model.put("url", url);
	            model.put("photoUrl", photoUrl);
	            model.put("photoWidth", String.valueOf(photoWidth));
	            model.put("photoHeight", String.valueOf(photoHeight));
	            Message message = new Message(subject,geNotificationService(request).getContent("template.forwardMember",webId,model),friend.getBuddyUserId(),user.getId(),user.getOrgId());
	    		this.getUserService(request).save(message);               
            }
        }
        return request.getParameter("responseId");
	}
	 
	public Object blockUser(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		User user = this.getVisitedUser(request);
		if(this.getUserService(request).getUserBlockByUser(this.getUser(request).getId(),user.getId()) == null){
			UserBlock userBlock = new UserBlock(this.getUser(request).getId(),user.getId());				  
			this.getChatService(request).save(userBlock);
		}
		return request.getParameter("responseId");
	}
	
	public Object deleteBuddy(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		long buddyUserId = Long.parseLong(request.getParameter("userId"));
		Connection buddy = this.getConnectionService(request).getChatBuddy(this.getUser(request).getId(),buddyUserId);
		this.getChatService(request).delete(buddy);
		return (String)request.getParameter("responseId");
	}
		
	public Object saveBuddyType(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		long buddyUserId = Long.parseLong(request.getParameter("userId"));
		String type = request.getParameter("type");
		Connection buddy = this.getConnectionService(request).getChatBuddy(this.getUser(request).getId(),buddyUserId);
		if(buddy != null && type != null){
			buddy.setType(Integer.parseInt(type));
			this.getChatService(request).save(buddy);
		}		
		return (String)request.getParameter("responseId");
	}
}

