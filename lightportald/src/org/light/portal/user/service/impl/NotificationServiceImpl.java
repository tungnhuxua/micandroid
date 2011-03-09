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

package org.light.portal.user.service.impl;

import static org.light.portal.util.Constants._FACEBOOK_API_KEY;
import static org.light.portal.util.Constants._FACEBOOK_AUTO_POST;
import static org.light.portal.util.Constants._FACEBOOK_PUBLISH_TO_ACCOUNT;
import static org.light.portal.util.Constants._FACEBOOK_SECRET_KEY;
import static org.light.portal.util.Constants._FACEBOOK_SESSION_KEY;
import static org.light.portal.util.Constants._FACEBOOK_TAGET_ID;
import static org.light.portal.util.Constants._MESSAGE_EVENT_FORUM_CATEGORY;
import static org.light.portal.util.Constants._MESSAGE_EVENT_FORUM_SUB_CATEGORY;
import static org.light.portal.util.Constants._MESSAGE_EVENT_TYPE_REQUEST;
import static org.light.portal.util.Constants._NOTIFICATION_ENABLED;
import static org.light.portal.util.Constants._OBJECT_TYPE_BLOG;
import static org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK;
import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._STATUS_APPROVED;
import static org.light.portal.util.Constants._STATUS_STOP;
import static org.light.portal.util.Constants._TWITTER_ACCESS_TOKEN;
import static org.light.portal.util.Constants._TWITTER_ACCESS_TOKEN_SECRET;
import static org.light.portal.util.Constants._TWITTER_AUTO_POST;
import static org.light.portal.util.Constants._TWITTER_CONSUMER_KEY;
import static org.light.portal.util.Constants._TWITTER_CONSUMER_SECRET;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.light.portal.core.service.PortalService;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.model.Subscriber;
import org.light.portal.model.User;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserInvite;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.service.BlogService;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.forum.service.ForumService;
import org.light.portlets.internal.InternalNews;
import org.light.portlets.message.Message;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.google.code.facebookapi.Attachment;
import com.google.code.facebookapi.FacebookXmlRestClient;

/**
 * 
 * @author Jianmin Liu
 **/

public class NotificationServiceImpl implements NotificationService {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private PortalService portalService;
	private UserService userService;
	private ForumService forumService;
	private BlogService blogService;
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	
	public void notify(Entity entity) {
		if(entity == null) return;
		Organization org = userService.getOrgById(entity.getOrgId());
		if(org == null) return;
		if(!PropUtil.getBoolean(_NOTIFICATION_ENABLED,org.getWebId())) return;
		try{			
			if(entity instanceof User) notify((User)entity, org);
			else if(entity instanceof UserInvite) notify((UserInvite)entity, org);
			else if(entity instanceof ForumCategory) notify((ForumCategory)entity, org);
			else if(entity instanceof Forum) notify((Forum)entity, org);
			else if(entity instanceof ForumPost) notify((ForumPost)entity, org);
			else if(entity instanceof UserComment) notify((UserComment)entity, org);
			else if(entity instanceof Feedback) notify((Feedback)entity, org);
			else if(entity instanceof Message) notify((Message)entity, org);
			else if(entity instanceof InternalNews) notify((InternalNews)entity, org);
			else if(entity instanceof Subscriber) notify((Subscriber)entity, org);
		}catch(Exception e){
			logger.error(String.format("notification failed for %s: %s",entity.toString(),e.getMessage()));
		}
	}

	protected void notify(UserInvite invite, Organization org) throws Exception{
		String from = org.getEmail();
		String to = invite.getInviteEmail();
		User user = this.getUserService().getUserById(invite.getUserId());
		String subject = user.getName()+ " invite you to join "+org.getWebId()+" .";
		Map<String,String> model = new HashMap<String,String>();
        model.put("name", org.getUser().getName());
        model.put("webId", org.getWebId());
        model.put("subject",subject);
        this.send(from,to,subject,"template.invitation",org.getWebId(),model);		
	}
	
	protected void notify(User user, Organization org) throws Exception{
		if(user.getVersion() <= 0 && !user.userDisabled()){			
			String subject = "Welcome to " + org.getWebId();
			Map<String,String> model = new HashMap<String,String>();
            model.put("name", user.getName());
            model.put("webId", org.getWebId());
			Message message = new Message(subject,getContent("template.welcome",org.getWebId(),model),user.getId(),org.getAdminId(),org.getId());			
			this.getPortalService().save(message);			
			//String from = org.getEmail();
			//String to = user.getEmail();
			//this.send(from,to,subject,"template.welcome",org.getWebId(),model);	            
									 
			List<UserInvite> invites = this.getUserService().getUserInviteByEmail(user.getUserId());
			if(invites != null){
				for(UserInvite invite : invites){
					invite.setStatus(1);
					this.getPortalService().save(invite);
					Message cmessage = new Message(user.getDisplayName()+" has accepted your invitation."
							,"<a href='http://"+user.getUri()+"."+org.getHost()+"'>"+user.getDisplayName()+"</a> has accepted your invitation."
							,invite.getUserId(),user.getId(),org.getId());
					this.getPortalService().save(cmessage);
				}
			}
		}
	}
		
	protected void notify(ForumCategory category, Organization org) throws Exception{
		//only send notification email for new record
		if(category.getVersion() > 0) return;
		User admin = this.getUserService().getUserById(org.getAdminId());
		User poster = this.getUserService().getUserById(category.getOwnerId());
		String subject = poster.getName()+" want to add a forum category.";
		Map<String,String> model = new HashMap<String,String>();
		model.put("name", admin.getName());
		model.put("poster", poster.getName());
		model.put("webId", org.getWebId());
		model.put("category", category.getName());
		model.put("categoryDesc", category.getDisplayDesc());
        Message message = new Message(subject,getContent("template.forum.category",org.getWebId(),model),org.getAdminId(),poster.getId(),org.getId(),_MESSAGE_EVENT_TYPE_REQUEST,_MESSAGE_EVENT_FORUM_CATEGORY,category.getId());
		this.getPortalService().save(message);
	}
	
	protected void notify(Forum forum, Organization org) throws Exception{
		//only send notification email for new record
		if(forum.getVersion() > 0) return;
		ForumCategory forumCategory = this.getForumService().getForumCategoryById(forum.getCategoryId());
		User admin = this.getUserService().getUserById(org.getAdminId());
		User poster = this.getUserService().getUserById(forum.getOwnerId());
		String subject = poster.getName()+" want to add a forum.";
		Map<String,String> model = new HashMap<String,String>();
		model.put("name", admin.getName());
		model.put("poster", poster.getName());
		model.put("webId", org.getWebId());
		model.put("forum", forum.getName());
		model.put("forumDesc", forum.getDisplayDesc());
		model.put("category", forumCategory.getName());
		model.put("categoryDesc", forumCategory.getDisplayDesc());
        Message message = new Message(subject,getContent("template.forum",org.getWebId(),model),org.getAdminId(),poster.getId(),org.getId(),_MESSAGE_EVENT_TYPE_REQUEST,_MESSAGE_EVENT_FORUM_SUB_CATEGORY,forum.getId());
		this.getPortalService().save(message);
	}
	
	protected void notify(ForumPost post, Organization org) throws Exception{
		//only send notification email for new record
		if(post.getVersion() > 0) return;
		//send notificaiton email to topic owner and all the posters
		String topic = post.getTopic();
		if(post.getTopId() != 0){			
			List<ForumPost> posts = this.getForumService().getPostsByTopic(post.getTopId(),0,100);
			if(posts != null){
				Set<Long> set = new HashSet<Long>();
				for(ForumPost tpost : posts){
					if(tpost.getTopic() != null) topic = tpost.getTopic();
					if(tpost.getPostById() != post.getPostById())
						set.add(tpost.getPostById());
				}
				for(long userId : set){
					User user = this.getUserService().getUserById(userId);
					if(user != null){
						try{
							notifyForum(user, org, post, topic, topic);		
						}catch(Exception e){
							logger.error(String.format("Notification failed for %s: %s",post.toString(),e.getMessage()));
						}
					}
				}
			}
		}
		//send notificaiton email to the forum owner if it is a new topic 
		else{
			Forum forum = this.getForumService().getForumById(post.getForumId());
			if(forum != null && forum.getOwnerId() > 0){
				User user = this.getUserService().getUserById(forum.getOwnerId());
				if(user != null){
					notifyForum(user, org, post, forum.getDisplayName(), topic);		
					return;
				}
			}
			ForumCategory category = this.getForumService().getForumCategoryById(post.getCategoryId());
			if(category != null && category.getOwnerId() > 0){
				User user = this.getUserService().getUserById(category.getOwnerId());
				if(user != null){
					notifyForum(user, org, post, category.getDisplayName(), topic);					
					return;
				}
			}
		}
	}
	protected void notifyForum(User user, Organization org, ForumPost post, String forum, String topic) throws Exception{		
		if(user != null && user.getNotification() == 1){		
			User poster = this.getUserService().getUserById(post.getPostById());
			String subject = poster.getName()+" add a post to the forum: "+forum;
			String content = post.getContent();
			Map<String,String> model = new HashMap<String,String>();
			model.put("name", user.getName());
			model.put("poster", poster.getName());
			model.put("webId", org.getWebId());
			model.put("forum", forum);
			model.put("topic", topic);
			model.put("content", content);
			Message message = new Message(subject,getContent("template.forum.post",org.getWebId(),model),user.getId(),org.getAdminId(),org.getId());			
			this.getPortalService().save(message);
		}
	}
	
	protected void notify(UserComment comment, Organization org) throws Exception{
		String subject = null;
		String to = null;
		long toUserId=0;
		String name = "";
		User user = this.getUserService().getUserById(comment.getUserId());
		if(user != null && user.getNotification() == 1){
			switch(comment.getObjectType()){
				case _OBJECT_TYPE_USER:				
					subject = user.getName()+ " post a comment to your wall";
					User receiver = this.getUserService().getUserById(comment.getObjectId());
					to = receiver.getEmail();
					toUserId = receiver.getId();
					name = receiver.getName();
					break;
				case _OBJECT_TYPE_BLOG:
					Blog blog = this.getBlogService().getBlogById(comment.getObjectId());
					if(blog == null) break;
					subject = user.getName()+ " post a comment to your Blog"+blog.getTitle(); 			
					User bloger = this.getUserService().getUserById(blog.getUserId());
					to = bloger.getEmail();
					toUserId = bloger.getId();
					name = bloger.getName();
					break;
				case _OBJECT_TYPE_FEEDBACK:				
					subject = user.getName()+" post a comment to feedback";
					Feedback feedback = this.getUserService().getFeedbackById(comment.getObjectId(),org.getId());
					if(feedback.getUserId() != org.getUserId()){
						User feedbackUser = this.getUserService().getUserById(feedback.getUserId());
						to = feedbackUser.getEmail();
						toUserId = feedbackUser.getId();
						name = feedbackUser.getName();
					}else{
						to = org.getReceiveEmail();
						toUserId = org.getAdminId();
					}
					break;				
			}
			if(toUserId > 0 && subject != null){
				Map<String,String> model = new HashMap<String,String>();
	            model.put("name", name);
	            model.put("webId", org.getWebId());
	            model.put("subject",subject);
	            model.put("content",comment.getComment());
	            Message message = new Message(subject,getContent("template.comment",org.getWebId(),model),toUserId,org.getAdminId(),org.getId());
				this.getPortalService().save(message);
			}
		}
	}
	
	protected void notify(Feedback feedback, Organization org) throws Exception{		
		User user = this.getUserService().getUserById(feedback.getUserId());			 
		String subject = feedback.getSubject();
		String content = feedback.getContent();		
		Map<String,String> model = new HashMap<String,String>();
        model.put("name", user.getName());
        model.put("poster", user.getName());
        model.put("webId", org.getWebId());
        model.put("subject", subject);
        model.put("content", content);
        Message message = new Message(subject,getContent("template.feedback",org.getWebId(),model),org.getAdminId(),org.getAdminId(),org.getId());
		this.getPortalService().save(message);
	}
	
	protected void notify(Message msg, Organization org) throws Exception{
		if(msg.getStatus() == 0 && msg.getDirection() == 0){
			User user = this.getUserService().getUserById(msg.getUserId());	
			if(user != null && user.getNotification() == 1){						
				User poster = this.getUserService().getUserById(msg.getPostById());
				String from = org.getEmail();
				String to = user.getEmail();
				String subject = msg.getSubject();	
				String content = msg.getContent();
				Map<String,String> model = new HashMap<String,String>();
	            model.put("name", user.getName());
	            model.put("poster", poster.getName());
	            model.put("webId", org.getWebId());
	            model.put("subject", subject);
	            model.put("content", content);
	            this.send(from,to,subject,"template.message",org.getWebId(),model);	            				
			}
		}
	}
	

	protected void notify(InternalNews news, Organization org) throws Exception{
		//updaste twitter status
		String tAutoPost = PropUtil.getString(_TWITTER_AUTO_POST,org.getWebId());
		String tConsumerKey = PropUtil.getString(_TWITTER_CONSUMER_KEY,org.getWebId());
		String tConsumerSecret = PropUtil.getString(_TWITTER_CONSUMER_SECRET,org.getWebId());
		String tAccessToken = PropUtil.getString(_TWITTER_ACCESS_TOKEN,org.getWebId());
		String tAccessTokenSecret = PropUtil.getString(_TWITTER_ACCESS_TOKEN_SECRET,org.getWebId());
		if(!StringUtil.isEmpty(tAutoPost) && !StringUtil.isEmpty(tConsumerKey) && !StringUtil.isEmpty(tConsumerSecret)
				&& !StringUtil.isEmpty(tAccessToken) && !StringUtil.isEmpty(tAccessTokenSecret)){
			try{
				Twitter twitter = new TwitterFactory().getInstance();//tUserName,tPassword);
	            twitter.setOAuthConsumer(tConsumerKey,tConsumerSecret);
	            twitter.setOAuthAccessToken(tAccessToken,tAccessTokenSecret);
	            Status status = twitter.updateStatus(news.getSubject());
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		//update facebook status
		String autoPost = PropUtil.getString(_FACEBOOK_AUTO_POST,org.getWebId());
		String apiKey = PropUtil.getString(_FACEBOOK_API_KEY,org.getWebId());
		String secretKey = PropUtil.getString(_FACEBOOK_SECRET_KEY,org.getWebId());
		String sessionKey = PropUtil.getString(_FACEBOOK_SESSION_KEY,org.getWebId());
		long targetId = PropUtil.getLong(_FACEBOOK_TAGET_ID,org.getWebId());
		boolean toAccount = PropUtil.getBoolean(_FACEBOOK_PUBLISH_TO_ACCOUNT,org.getWebId());
		if(!StringUtil.isEmpty(autoPost) && !StringUtil.isEmpty(apiKey) && !StringUtil.isEmpty(secretKey) && !StringUtil.isEmpty(sessionKey)){
			FacebookXmlRestClient frc = new FacebookXmlRestClient(apiKey, secretKey, sessionKey);
			try{
				String url = "http://"+org.getWebId();
				Attachment attachment = new Attachment();
				attachment.setCaption(org.getWebId()+" - "+news.getDate());
				attachment.setDescription(HTMLUtil.removeHTML(news.getContent()));
				attachment.setHref(url);
				attachment.setName(news.getSubject());		
				if(targetId > 0) frc.stream_publish(news.getSubject(),attachment, null, targetId,frc.users_getLoggedInUser());
				if(toAccount) frc.stream_publish(news.getSubject(),attachment, null, frc.users_getLoggedInUser(),frc.users_getLoggedInUser());
			}catch(Exception e){
				//ignore sending error
			}
		}	
		List<User> users = this.getUserService().viewUsersByOrgId(org.getId());
		String webId = org.getWebId();		
		String subject = news.getSubject();	
		String content = news.getContent();
		for(User user : users){			
			try{
				Map<String,String> model = new HashMap<String,String>();
	            model.put("name", user.getName());
	            model.put("webId", webId);
	            model.put("subject", subject);
	            model.put("content", content);
	            Message message = new Message(subject,getContent("template.internalNews",org.getWebId(),model),user.getId(),org.getAdminId(),org.getId());
	    		this.getPortalService().save(message);
			    Thread.yield();
			}catch(Exception e){
				logger.error(String.format("Notification failed for %s: %s",news.toString(),e.getMessage()));
			}
		}
	}
	public String getContent(final String template, final String webId, final Map model) throws Exception{
		String path = PropUtil.getString(template,webId);
        String text = VelocityEngineUtils.mergeTemplateIntoString(
           velocityEngine, path, model);
        return text;
	}
	
	protected void notify(Subscriber subscriber, Organization org) throws Exception{
		if(subscriber.getStatus() == _STATUS_APPROVED){
			String from = org.getEmail();
			String to = subscriber.getEmail();
			String subject = String.format("Welecome to %s",org.getWebId());	
			Map<String,String> model = new HashMap<String,String>();
            model.put("webId", org.getWebId());
            model.put("email", subscriber.getEmail());
            this.send(from,to,subject,"template.subscribe",org.getWebId(),model);	            				
		}else if(subscriber.getStatus() == _STATUS_STOP){
			String from = org.getEmail();
			String to = subscriber.getEmail();
			String subject = String.format("Thanks to subscribe %s",org.getWebId());	
			Map<String,String> model = new HashMap<String,String>();
            model.put("webId", org.getWebId());
            model.put("email", subscriber.getEmail());
            this.send(from,to,subject,"template.unsubscribe",org.getWebId(),model);
		}
	}
	
	public void send(final String from, final String to, final String subject, final String template, final String webId, final Map model) throws Exception{		
		this.send(from,to,subject,getContent(template,webId,model));
	}	
		
	public void send(final String from, final String to, final String subject, final String text) throws Exception{
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);			           
	            message.setFrom(from);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setText(text, true);
	         }
	    };
	    this.mailSender.send(preparator);
	}
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public PortalService getPortalService() {
		return portalService;
	}

	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ForumService getForumService() {
		return forumService;
	}

	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}

	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}