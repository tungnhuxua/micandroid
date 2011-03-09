package org.light.portal.social.impl;

import org.light.portal.core.dao.hibernate.BaseDaoImpl;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.model.SocialActivity;
import org.light.portal.model.User;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserProfile;
import org.light.portal.social.ActivityUpdater;
import org.light.portal.user.service.UserService;
import org.light.portlets.blog.Blog;
import org.light.portlets.connection.Connection;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.group.Group;
import org.light.portlets.group.UserGroup;
import org.light.portlets.group.service.GroupService;

public class ActivityUpdaterImpl  extends BaseDaoImpl implements ActivityUpdater {
	
	public ActivityUpdaterImpl(){
		
	}
	public void addActivity(SocialActivity activity){
		this.save(activity);
	}
	public void addActivity(Entity entity){
		if(entity instanceof User) add((User)entity);
		else if(entity instanceof UserProfile) add((UserProfile)entity);
		else if(entity instanceof UserPicture) add((UserPicture)entity);
		else if(entity instanceof Connection) add((Connection)entity);
		else if(entity instanceof Group) add((Group)entity);
		else if(entity instanceof UserGroup) add((UserGroup)entity);
		else if(entity instanceof ForumPost) add((ForumPost)entity);
		else if(entity instanceof Blog) add((Blog)entity);
	}
	
	public void deleteActivity(Entity entity){
		if(entity instanceof Connection) delete((Connection)entity);
		else if(entity instanceof Group) delete((Group)entity);
		else if(entity instanceof UserGroup) delete((UserGroup)entity);
	}
	
	protected void add(User user){		
		if(user.getVersion() <= 0 && user.getDisabled() == 0){
			Organization org = userService.getOrgById(user.getOrgId());
			StringBuilder content = new StringBuilder();
			content.append(user.getName())
				   .append(" has just signed up to ")
				   .append(org.getWebId())
				   .append(".");		
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
			this.save(activity);
		}
	}
	
	protected void add(UserProfile userProfile){
		if(userProfile.getVersion() > 0){
			User user= userService.getUserById(userProfile.getUserId());
			StringBuilder content = new StringBuilder();
			content.append(user.getName())
				   .append(" updated personal information.");
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
			this.save(activity);
		}
	}
	
	protected void add(UserPicture userPicture){
		if(userPicture.getVersion() <= 0){
			User user= userService.getUserById(userPicture.getUserId());
			StringBuilder content = new StringBuilder();
			content.append(user.getName())
				   .append(" uploaded a new picture.");
			Group group = groupService.getGroupById(userPicture.getOrgId());
			if(group != null){
				content = new StringBuilder();
				content.append(user.getName())
					   .append(" uploaded a new picture to group ")
					   .append(group.getDisplayName())
					   .append(".");
				SocialActivity activity2 = new SocialActivity(user.getId(),group.getId(),content.toString());;
				this.save(activity2);
			}
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
			this.save(activity);
		}
	}
	
	protected void add(Connection connection){
		User user= userService.getUserById(connection.getUserId());
		User buddy = userService.getUserById(connection.getBuddyUserId());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" connected with ")
			   .append(buddy.getName())
			   .append(".");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		this.save(activity);
	}
	protected void add(Group group){
		if(group.getVersion() <= 0){
			User user= userService.getUserById(group.getOwnerId());
			StringBuilder content = new StringBuilder();
			content.append(user.getName())
				   .append(" created group ")
				   .append(group.getDisplayName())
				   .append(".");
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
			SocialActivity activity2 = new SocialActivity(user.getId(),group.getId(),content.toString());
			this.save(activity);
			this.save(activity2);
		}
	}
	protected void add(UserGroup userGroup){
		if(userGroup.getVersion() <= 0){
			User user= userService.getUserById(userGroup.getUserId());
			Group group = groupService.getGroupById(userGroup.getGroupId());
			StringBuilder content = new StringBuilder();
			content.append(user.getName())
				   .append(" joined group ")
				   .append(group.getDisplayName())
				   .append(".");
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
			SocialActivity activity2 = new SocialActivity(user.getId(),userGroup.getGroupId(),content.toString());
			this.save(activity);
			this.save(activity2);
		}
	}
	
	protected void add(ForumPost obj){
		User user= userService.getUserById(obj.getPostById());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" added a new forum entry.");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		this.save(activity);
	}
	
	protected void add(Blog obj){
		User user= userService.getUserById(obj.getPostedById());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" added a new blog.");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		this.save(activity);
	}
	
	protected void delete(Connection connection){
		User user= userService.getUserById(connection.getUserId());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" disconnected with ")
			   .append(connection.getDisplayName())
			   .append(".");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		this.save(activity);
	}
	protected void delete(Group group){
		User user= userService.getUserById(group.getOwnerId());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" deleted group ")
			   .append(group.getDisplayName())
			   .append(".");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		SocialActivity activity2 = new SocialActivity(user.getId(),group.getId(),content.toString());
		this.save(activity);
		this.save(activity2);
	}
	protected void delete(UserGroup userGroup){
		User user= userService.getUserById(userGroup.getUserId());
		Group group = groupService.getGroupById(userGroup.getGroupId());
		StringBuilder content = new StringBuilder();
		content.append(user.getName())
			   .append(" resigned from group ")
			   .append(group.getDisplayName())
			   .append(".");
		SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),content.toString());
		SocialActivity activity2 = new SocialActivity(user.getId(),userGroup.getGroupId(),content.toString());
		this.save(activity);
		this.save(activity2);
	}

	public GroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private UserService userService;
	private GroupService groupService;
	
}
