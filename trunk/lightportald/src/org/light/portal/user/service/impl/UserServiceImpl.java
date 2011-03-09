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

import static org.light.portal.util.Constants._DEFAULT_TAG_CLOUD_FONT_SIZE_MAX;
import static org.light.portal.util.Constants._DEFAULT_TAG_CLOUD_FONT_SIZE_MIN;
import static org.light.portal.util.Constants._OBJECT_TYPE_ORG;
import static org.light.portal.util.Constants._OBJECT_TYPE_USER;
import static org.light.portal.util.Constants._PRIVACY_CONNECTION;
import static org.light.portal.util.Constants._PRIVACY_MEMBER;
import static org.light.portal.util.Constants._PRIVACY_PUBLIC;
import static org.light.portal.util.Constants._ROLE_MEMBER;
import static org.light.portal.util.Constants._ROLE_USER;
import static org.light.portal.util.Constants._STATUS_ONLINE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.light.portal.cache.CacheService;
import org.light.portal.core.permission.dao.UserPermissionDao;
import org.light.portal.core.service.PortalService;
import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.model.Keyword;
import org.light.portal.model.NotKeyword;
import org.light.portal.model.NotWord;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.Permission;
import org.light.portal.model.PicturePositionTag;
import org.light.portal.model.SocialActivity;
import org.light.portal.model.Subdomain;
import org.light.portal.model.Subscriber;
import org.light.portal.model.TagFilter;
import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserFavourite;
import org.light.portal.model.UserFile;
import org.light.portal.model.UserInvite;
import org.light.portal.model.UserKeyword;
import org.light.portal.model.UserMusic;
import org.light.portal.model.UserObjectRole;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
import org.light.portal.search.Indexer;
import org.light.portal.search.Searcher;
import org.light.portal.search.SearcherUtil;
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portal.user.dao.UserDao;
import org.light.portal.user.service.NotificationService;
import org.light.portal.user.service.UserService;
import org.light.portal.util.Constants;
import org.light.portal.util.DateUtil;
import org.light.portal.util.HashUtil;
import org.light.portal.util.LabelBean;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portal.util.StringUtil;
import org.light.portlets.addressbook.AddressBook;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.bulletin.Bulletin;
import org.light.portlets.connection.Connection;
import org.light.portlets.connection.service.ConnectionService;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.group.Group;
import org.light.portlets.group.UserGroup;
import org.light.portlets.group.service.GroupService;
import org.light.portlets.horoscope.Horoscope;
import org.light.portlets.message.Message;
import org.light.portlets.note.Note;
import org.light.portlets.todolist.ToDoBean;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserServiceImpl  extends BaseServiceImpl implements UserService{
	private UserDao userDao;
	private UserPermissionDao userPermissionDao;
	private PortalService portalService;	
	private ConnectionService connectionService;
	private GroupService groupService;
	private NotificationService notificationService;
	private Indexer indexer;
	private Searcher searcher;
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	public List<User> getUsersByOrgId(long orgId){
		return userDao.getUsersByOrgId(orgId);
	}
	
	public User getUserById(long id){
		User user = getCacheService().getUser(id);
		if(user == null){
			user = userDao.getUserById(id);
			getCacheService().setUser(user);
		}
		return user;
	}
	
	public User getUserByUserId(String userId, long orgId){
		User user = getCacheService().getUser(userId,orgId);
		if(user == null){
			user = userDao.getUserByUserId(userId, orgId);
			getCacheService().setUser(user);
		}
		return user;
	}
	
	public User getUserByUri(String uri,long orgId){
		User user = getCacheService().getUser(uri,orgId);
		if(user == null){
			user = userDao.getUserByUri(uri,orgId);
			getCacheService().setUser(user);
		}
		return user;
	}
		
//	public List<Permission> getUserPermissions(long orgId, long userId){
//		String key = ""+orgId+"_"+userId;
//		List<Permission> permissions = (List<Permission>)getCacheService().getList(Permission.class,key);
//		if(permissions == null){
//			permissions = userPermissionDao.getAllPermissions(orgId, userId, orgId, Constants._OBJECT_TYPE_ORG);
//			getCacheService().setList(Permission.class,key,permissions);
//		}
//		return permissions;
//	}
	
	public boolean saveUserUrl(User user, String uri){
		user.setUri(uri);
		user.setUriType(1);
		Subdomain sd = new Subdomain(uri,user.getOrgId());
		this.save(sd);
		this.save(user);
		return true;
	}
	public boolean saveUserCaption(User user, String caption){
		user.setCaption(caption);
		this.save(user);
		if(!StringUtil.isEmpty(caption)){
			SocialActivity activity = new SocialActivity(user.getId(),user.getOrgId(),caption);
			this.save(activity);
		}
		return true;
	}
	public List<User> viewUsersByOrgId(long orgId){
		List<User> users = getCacheService().getUsers(orgId);
		if(users == null){				
			Organization org = OrganizationThreadLocal.getOrg();
			if(org == null) org = this.getOrgById(orgId);
			users = userDao.viewUsersByOrg(org); 
			getCacheService().setUsers(orgId,users);
		}		
		return users; 
	}	
	
	public List<Organization> getOrganizations(){
		List<Organization> orgs = (List<Organization>)getCacheService().getOrgs();
		if(orgs == null){
			orgs = userDao.getOrganizations();
			getCacheService().setOrgs(orgs);
		}
		return orgs;
	}
	public Organization getOrgByVirtualHost(String host){
		Organization org = getCacheService().getOrg(host);
		if(org == null){
			org = userDao.getOrgByVirtualHost(host);
			if(org != null){
				loadOrgDependents(org);
				getCacheService().setOrg(org);
			}
		}
		return org;
	}
	public Organization getOrgById(long id){
		Organization org = getCacheService().getOrg(String.valueOf(id));
		if(org == null){
			org = userDao.getOrgById(id);
			if(org != null){
				loadOrgDependents(org);
				getCacheService().setOrg(org);
			}
		}
		return org;		
	}
	private void loadOrgDependents(Organization org){
		List<OrgProfile> profiles = userDao.getOrgProfiles(org.getId());
		Map<String,OrgProfile> profileMap = new HashMap<String,OrgProfile>();
		for(OrgProfile profile : profiles){
			profileMap.put(profile.getLanguage(),profile);
		}
		org.setProfileMap(profileMap);
		org.setUser(this.getUserById(org.getUserId()));				
		org.setPortal(portalService.getPortalByUser(org.getUser().getUserId(),org.getId()));
		List<ObjectRole> roles = userDao.getAllRoles(org.getId());
		Map<String, ObjectRole> roleMap = new HashMap<String, ObjectRole>();
		for(ObjectRole role : roles){
			roleMap.put(role.getName().toLowerCase(),role);
		}
		org.setRoleMap(roleMap);
		List<LabelBean> channels = new LinkedList<LabelBean>();
		String[] channelName = PropUtil.getStringArray(Constants._CHANNEL_LIST_NAME,org.getWebId());
		String[] channelDesc = PropUtil.getStringArray(Constants._CHANNEL_LIST_DESC,org.getWebId());
		for(int i=0;i<channelName.length;i++){
			String name = channelName[i];			
			String desc = (channelDesc.length > i) ? channelDesc[i] : name;		
			channels.add((new LabelBean(name,desc)));
		}
		org.setChannels(channels);
	}
	
	public OrgProfile getOrgProfileByOrgId(long orgId, String language){
		String key = orgId+language;
		OrgProfile profile = (OrgProfile)getCacheService().getObject(OrgProfile.class,key);
		if(profile == null){
			profile = userDao.getOrgProfileByOrgId(orgId,language);
			getCacheService().setObject(OrgProfile.class,key,profile);
		}
		return profile;
	}
	public void createOrganization(Organization org,OrgProfile profile){
		userDao.createOrganization(org,profile);
	}
	public void createSubOrganization(Organization org,OrgProfile profile){
		userDao.createSubOrganization(org,profile);
	}	
	
	public void reIndex(long orgId){		
		indexer.reIndex(orgId);
		
	}
	public void reIndex(Class klass, long orgId){		
		indexer.reIndex(klass,orgId);		
	}
	
	public SearchResult searchUser(SearchCriteria criteria ) throws Exception{
		return searcher.search(User.class,criteria);
	}
	
	public long getAdminUserId(long orgId){
		return userDao.getAdminUserId(orgId);
	}
	
	public int getUserPictureCount(long userId,long orgId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(UserPicture.class,key);
		if(count == null){
			count = userDao.getUserPictureCount(userId,orgId);
			getCacheService().setObject(UserPicture.class,key,count);
		}
		return count;		 
	}
	public int getUserMusicCount(long userId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(UserMusic.class,key);
		if(count == null){
			count = userDao.getUserMusicCount(userId);
			getCacheService().setObject(UserMusic.class,key,count);
		}
		return count;		
	}
	public int getUserFileCount(long userId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(UserFile.class,key);
		if(count == null){
			count = userDao.getUserFileCount(userId);
			getCacheService().setObject(UserFile.class,key,count);
		}
		return count;		
	}
	public boolean inviteFriends(String mailList,String desc,User user){
	   String[] mails = mailList.split(",");
	   for(int i=0;i<mails.length;i++){
		UserInvite invite = new UserInvite(user.getId(),mails[i],desc);
		this.save(invite);
	   }
	   return true;
	}
	
	public boolean sendNewPassword(User user){		
		if(user != null){	
			String current = String.valueOf(System.currentTimeMillis()).substring(0,8);
			user.setPassword(current);
			this.save(user);
			
			try{
				final Organization org = OrganizationThreadLocal.getOrg();	
				final String name = user.getDisplayName();
				final String from = org.getEmail();
				final String to = user.getEmail();
				final String subject = user.getName() + ", you request a new password.";
				final String content = current;
				Map<String,String> model = new HashMap<String,String>();
	            model.put("name", name);
	            model.put("webId", org.getWebId());
	            model.put("subject", subject);
	            model.put("content", content);
				notificationService.send(from,to,subject,"template.newPassword",org.getWebId(),model);				
			}catch(Exception e){
				logger.error(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public List<User> getCoolNewPeople(int start, int max){
		return userDao.getCoolNewPeople(start, max);
	}
	
	public List<User> getOnlinePeople(int start, int max){
		return userDao.getOnlinePeople(start, max);
	}
	public int getOnlinePeopleTotal(){
		return userDao.getOnlinePeopleTotal();
	}
	public int getCoolNewPeopleTotal(){
		return userDao.getCoolNewPeopleTotal();
	}
	public UserProfile getUserProfileById(long userId){
		UserProfile profile = (UserProfile)getCacheService().getObject(UserProfile.class,userId);
		if(profile == null){
			profile = userDao.getUserProfileById(userId);
			getCacheService().setObject(UserProfile.class,userId,profile);
		}
		return profile;
	}
	
	public List<UserComment> getUserComments(long userId){
		return getCommentsByType(userId,_OBJECT_TYPE_USER);
	}
	
	public List<UserComment> getCommentsByType(long objectId, int objectType){
		String key = objectType+CacheService.SEPARATOR+objectId;
		List<UserComment> comments = (List<UserComment>)getCacheService().getList(UserComment.class,key);
		if(comments == null){
			comments = userDao.getCommentsByType(objectId,objectType);
			getCacheService().setList(UserComment.class,key,comments);
		}
		return comments;
	}
	public int getUserCommentsCount(long userId){
		return getUserComments(userId).size();
	}
	public UserComment getUserCommentsById(long id){
		return userDao.getUserCommentsById(id);
	}
	public void deleteUserCommentsById(long id){
		userDao.deleteUserCommentsById(id);
	}
	public List<UserTag> getUserTags(long objectId, int objectType){
		return getUserTags(objectId,objectType,true);
	}
	public List<UserTag> getUserTags(long objectId, int objectType,boolean flag){
		String key = objectId+CacheService.SEPARATOR+objectType;
		List<UserTag> tags = (List<UserTag>)getCacheService().getList(UserTag.class,key);
		if(tags == null){
			tags = userDao.getUserTags(objectId,objectType);
			if(tags != null && tags.size() > 0 && flag){
				long maxScore = userDao.getMaxUserTagScore(tags.get(0).getOrgId(), objectType);
				long minSize = PropUtil.getLong(_DEFAULT_TAG_CLOUD_FONT_SIZE_MIN,10);
				long maxSize = PropUtil.getLong(_DEFAULT_TAG_CLOUD_FONT_SIZE_MAX,20);
				long size;
				for(UserTag tag : tags){
					size = minSize;
					if(tag.getScore() > 1){
						if(maxScore < maxSize)
							size = minSize + tag.getScore() - 1;
						else
							size = minSize + maxSize * tag.getScore() / maxScore;
					}
					tag.setSize(size);
				}
			}
			if(flag) getCacheService().setList(UserTag.class,key,tags);
		}
		return tags;
	}
	public UserTag getUserTagById(long id){
		return userDao.getUserTagById(id);
	}
	public boolean hasUserTag(long objectId, int objectType, String tag){
		return userDao.hasUserTag(objectId,objectType,tag);
	}
	public List<UserPicture> getUserPictures(long userId,long orgId){
		List<UserPicture> pictures = (List<UserPicture>)getCacheService().getList(UserPicture.class,userId);
		if(pictures == null){
			pictures = userDao.getUserPictures(userId,orgId);
			getCacheService().setList(UserPicture.class,userId,pictures);
		}
		return pictures;
	}
	public void updatePictureStatus(long userId,int status){
		userDao.updatePictureStatus(userId,status);
	}
	public void updateMusicStatus(long userId,int status){
		userDao.updateMusicStatus(userId,status);
	}
	public void updateFileStatus(long userId,int status){
		userDao.updateFileStatus(userId,status);
	}
	public List<UserBlock> getUserBlocks(long userId){
		return userDao.getUserBlocks(userId);
	}
	public List<UserInvite> getUserInvites(long userId){
		return userDao.getUserInvites(userId);
	}
	public List<UserInvite> getUserInviteByEmail(String email){
		return userDao.getUserInviteByEmail(email);
	}
	public UserBlock getUserBlockById(long id){
		return userDao.getUserBlockById(id);
	}
	
	public UserBlock getUserBlockByUser(long userId, long blockId){
		return userDao.getUserBlockByUser(userId,blockId);
	}
	
	public List<UserPicture> getVisitedUserPictures(long userId,long visitedId,long orgId){
		if(visitedId == userId)
			return this.getUserPictures(userId,orgId);
		int status = _PRIVACY_PUBLIC;//pictures for pubic
		if(userId != OrganizationThreadLocal.getOrg().getUserId())
			status = _PRIVACY_MEMBER;//pictures for member
		if(connectionService.getChatBuddy(userId,visitedId) != null)
			status = _PRIVACY_CONNECTION;//picutres for friends
		
		return this.getUserPicturesByStatus(visitedId,status,orgId);			
	}
	protected List<UserPicture> getUserPicturesByStatus(long userId,int status,long orgId){
		String key = userId+"_"+status;
		List<UserPicture> pictures = (List<UserPicture>)getCacheService().getList(UserPicture.class,key);
		if(pictures == null){
			pictures = userDao.getUserPicturesByStatus(userId,status,orgId);		
			getCacheService().setList(UserPicture.class,key,pictures);
		}
		return pictures;
	}
	
	public List<UserPicture> getUserRankPictures(long userId){
		return userDao.getUserRankPictures(userId);
	}
	
	public UserPicture getUserPictureById(long id){
		UserPicture pic = (UserPicture)getCacheService().getObject(UserPicture.class,id);
		if(pic == null){
			pic = userDao.getUserPictureById(id);
			getCacheService().setObject(UserPicture.class,id,pic);
		}
		return pic;
	}
	
	public List<UserPicture> getPublicPictures(long orgId, int start,int max){
		return userDao.getPublicPictures(orgId,start,max);
	}
	
	public List<UserPicture> getAllPictures(long orgId){
		return userDao.getAllPictures(orgId);
	}
	public List<UserPicture> getOrgPictures(long orgId){
		return userDao.getOrgPictures(orgId);
	}
	public int getOrgPictureCount(long orgId){
		return userDao.getOrgPictureCount(orgId);
	}
	public List<PicturePositionTag> getPicturePositionTags(long pictureId){
		return userDao.getPicturePositionTags(pictureId);
	}
	public List<PicturePositionTag> getPicturePositionTags(String pictureUrl){
		return userDao.getPicturePositionTags(pictureUrl);
	}
	public PicturePositionTag getPicturePositionTagById(long id){
		return userDao.getPicturePositionTagById(id);
	}
	public List<Bulletin> getBulletinsByUser(long userId){
		return userDao.getBulletinsByUser(userId);
	}
	
	public List<Bulletin> getBulletinsByOrg(long orgId){
		return userDao.getBulletinsByOrg(orgId);
	}
	public Bulletin getBulletinById(long id){
		return userDao.getBulletinById(id);
	}
	public int getUserBulletinCount(long userId){
		return userDao.getUserBulletinCount(userId);
	}
	public void sendBulletin(Bulletin bulletin){
		this.save(bulletin);
		if(bulletin.getType() == Bulletin._TYPE_USER){
			List<Connection> userFriends = connectionService.getBuddysByUser(bulletin.getUserId());
			for(Connection friend : userFriends){
				Bulletin sendToFriend = new Bulletin(bulletin.getSubject(),bulletin.getContent(),friend.getBuddyUserId(),bulletin.getUserId());
				this.save(sendToFriend);			
			}
		}
		if(bulletin.getType() == Bulletin._TYPE_ORG){
			List<UserGroup> userGroups = groupService.getUsersByGroup(bulletin.getOrgId());
			Group group = groupService.getGroupById(bulletin.getOrgId());
			for(UserGroup member : userGroups){
				if(bulletin.getPostById()==group.getLeaderId() && member.getAcceptLeaderBulletin() == 1){
					Bulletin sendToMember = new Bulletin(bulletin.getSubject(),bulletin.getContent(),member.getUserId(),bulletin.getPostById());
					this.save(sendToMember);	
					break;
				}
				if(member.getAcceptMembersBulletin() == 1){
					Bulletin sendToMember = new Bulletin(bulletin.getSubject(),bulletin.getContent(),member.getUserId(),bulletin.getPostById());
					this.save(sendToMember);	
				}
			}
		}
	}
	
	public void sendMessage(Message message){
		this.save(message);
	}
	public Message getMessageById(long id){
		return userDao.getMessageById(id);
	}
	
	public List<Message> getMessagesBySender(long userId){
		return userDao.getMessagesBySender(userId);
	}
	
	public List<Message> getMessagesByUser(long userId){
		return userDao.getMessagesByUser(userId);
	}
	public List<Message> getConnectionRequestMessagesByUser(long userId){
		return userDao.getConnectionRequestMessagesByUser(userId);
	}
	
	public int getNewMessageCountByUser(long userId){
		String key = userId+CacheService.SEPARATOR+CacheService.TYPE1+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Message.class,key);
		if(count == null){
			count = userDao.getNewMessageCountByUser(userId);
			getCacheService().setObject(Message.class,key,count);
		}
		return count;
	}
		
	public int getNewConnectionRequestCountByUser(long userId){
		String key = userId+CacheService.SEPARATOR+CacheService.TYPE2+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(Message.class,key);
		if(count == null){
			count = userDao.getNewConnectionRequestCountByUser(userId);
			getCacheService().setObject(Message.class,key,count);
		}
		return count;
	}
		
	public List<UserFavourite> getUserFavourites(long userId){
		return userDao.getUserFavourites(userId);
	}
	public boolean approveConnection(Message message){
		return connectionService.approveConnection(message);
	}
	public List<UserMusic> getUserMusics(long userId){
		List<UserMusic> list = (List<UserMusic>)getCacheService().getList(UserMusic.class,userId);
		if(list == null){
			list = userDao.getUserMusics(userId);
			getCacheService().setList(UserMusic.class,userId,list);
		}
		return list;
	}
	
	public List<UserMusic> getUserRankMusics(long userId){		
		return userDao.getUserRankMusics(userId);
	}
	public List<UserMusic> getVisitedUserMusics(long userId,long visitedId){		
		if(visitedId == userId)
			return userDao.getUserMusics(userId);
		int status = _PRIVACY_PUBLIC;//musics for pubic
		if(userId != OrganizationThreadLocal.getOrg().getUserId())
			status = _PRIVACY_MEMBER;//musics for member
		if(connectionService.getChatBuddy(userId,visitedId) != null)
			status = _PRIVACY_CONNECTION;//musics for friends	
		return userDao.getUserMusicsByStatus(visitedId,status);
	}
	public UserMusic getUserMusicById(long id){
		return userDao.getUserMusicById(id);
	}
	
	public List<UserFile> getUserFiles(long userId){
		List<UserFile> list = (List<UserFile>)getCacheService().getList(UserFile.class,userId);
		if(list == null){
			list = userDao.getUserFiles(userId);
			getCacheService().setList(UserFile.class,userId,list);
		}
		return list;
	}
	public List<UserFile> getVisitedUserFiles(long userId,long visitedId){		
		if(visitedId == userId)
			return userDao.getUserFiles(userId);
		int status = _PRIVACY_PUBLIC;//files for pubic
		if(userId != OrganizationThreadLocal.getOrg().getUserId())
			status = _PRIVACY_MEMBER;//files for member
		if(connectionService.getChatBuddy(userId,visitedId) != null)
			status = _PRIVACY_CONNECTION;//files for friends
		return userDao.getUserFilesByStatus(visitedId,status);
			
	}
	public UserFile getUserFileById(long id){
		return userDao.getUserFileById(id);
	}
		
	public AddressBook getAddressBookById(long id){
		return userDao.getAddressBookById(id);
	}
	public List<AddressBook> getAddressBooksByUser(long userId){
		return userDao.getAddressBooksByUser(userId);
	}
	public List<String> getAddressBookGroupByUser(long userId){
		return userDao.getAddressBookGroupByUser(userId);
	}
	public List<AddressBook> getAddressBooksByUser(long userId, String group){
		return userDao.getAddressBooksByUser(userId, group);
	}
	public List<Feedback> getFeedback(long orgId){
		List<Feedback> feedbacks = (List<Feedback>)getCacheService().getList(Feedback.class,orgId);
		if(feedbacks == null){
			feedbacks = userDao.getFeedback(orgId);
			getCacheService().setList(Feedback.class,orgId,feedbacks);
		}
		return feedbacks;
	}
	
	public Feedback getFeedbackById(long id, long orgId){
		List<Feedback> feedbacks = getFeedback(orgId);
		for(Feedback feedback : feedbacks){
			if(feedback.getId() == id) return feedback;
		}
		return userDao.getFeedbackById(id);				
	}
	public Bookmark getBookmarkById(long id){
		return userDao.getBookmarkById(id);
	}
	public List<Bookmark> getBookmarksByUser(long userId){
		return userDao.getBookmarksByUser(userId);
	}
	public List<Bookmark> getBookmarksByTag(long userId, String tag){
		return userDao.getBookmarksByTag(userId,tag);
	}
	public int getUserToDoCount(long userId){
		String key = userId+CacheService.COUNT;
		Integer count = (Integer)getCacheService().getObject(ToDoBean.class,key);
		if(count == null){
			count = userDao.getUserToDoCount(userId);
			getCacheService().setObject(ToDoBean.class,key,count);
		}
		return count;
	}
	public List<ToDoBean> getToDosByUser(long userId){
		List<ToDoBean> list = (List<ToDoBean>)getCacheService().getList(ToDoBean.class,userId);
		if(list == null){
			list = userDao.getToDosByUser(userId);
			getCacheService().setList(ToDoBean.class,userId,list);
		}
		return list;
	}	
	public ToDoBean getToDoById(long id){
		ToDoBean entity = (ToDoBean)getCacheService().getObject(ToDoBean.class,id);
		if(entity == null){
			entity = userDao.getToDoById(id);
			getCacheService().setObject(ToDoBean.class,id,entity);
		}
		return entity;
	}
	public Note getNoteByUser(long userId){
		Note entity = (Note)getCacheService().getObject(Note.class,userId);
		if(entity == null){
			entity = userDao.getNoteByUser(userId);
			getCacheService().setObject(Note.class,userId,entity);
		}
		return entity;
	}
	
	public User signUp(User user, long orgId) {		
		if(this.getUserByUserId(user.getUserId(),orgId) == null){	
			Subdomain sd = new Subdomain(user.getUri(),OrganizationThreadLocal.getOrganizationId());
			this.save(sd); 
			String ePassword = HashUtil.MD5Hashing(user.getPassword());		
			user.setPassword(ePassword);
			user.setCurrentStatus(_STATUS_ONLINE);
			this.save(user);
				
			UserProfile userProfile = new UserProfile(user.getId());
			this.save(userProfile);
			List<UserObjectRole> roles = new LinkedList<UserObjectRole>();
			ObjectRole roleUser = OrganizationThreadLocal.getOrg().getRole(_ROLE_USER);
			UserObjectRole userRole = new UserObjectRole(user.getId(),user.getOrgId(),user.getOrgId(),_OBJECT_TYPE_ORG,roleUser.getId());
			this.save(userRole);
			roles.add(userRole);
			ObjectRole roleMember = OrganizationThreadLocal.getOrg().getRole(_ROLE_MEMBER);
			UserObjectRole memberRole = new UserObjectRole(user.getId(),user.getOrgId(),user.getOrgId(),_OBJECT_TYPE_ORG,roleMember.getId());
			this.save(memberRole);
			roles.add(memberRole);			
		}			 			 	
		return this.getUserById(user.getId());
	}

	public Horoscope getUserHoroscope(int month, int day){
		return userDao.getUserHoroscope(month,day);
	}
	public String getUserHoroscopeWeeklyInfo(long horoscopeId, String language){
		return userDao.getUserHoroscopeWeeklyInfo(horoscopeId, language);
	}
	public List<Horoscope> getHoroscopes(){
		return userDao.getHoroscopes();
	}
	
	public void deleteUser(User user){
		if(user != null){
			userDao.deleteUser(user);			
			connectionService.deleteUser(user.getId());
			List<UserObjectRole> roles = this.getUserRoles(user.getId(),user.getOrgId());
			for(UserObjectRole role : roles){
				 this.delete(role);
			}
			this.getPortalService().deletePortal(user.getUserId(),OrganizationThreadLocal.getOrg());
		}
	}
	
	public int getMyActivitiesCount(long userId){
		return userDao.getMyActivitiesCount(userId);
	}
	public int getMyConnectionsActivitiesCount(long userId){
		return userDao.getMyConnectionsActivitiesCount(userId);
	}
	public int getMyGroupsActivitiesCount(long userId){
		return userDao.getMyGroupsActivitiesCount(userId);
	}
	public int getSocialActivitiesCount(long userId){
		return userDao.getSocialActivitiesCount(userId);
	}
	public int getSocialActivitiesCountByOrg(long orgId){
		return userDao.getSocialActivitiesCountByOrg(orgId);
	}
	
	public List<SocialActivity> getMyActivities(long userId, int start, int total){
		return userDao.getMyActivities(userId,start,total);
	}
	public List<SocialActivity> getMyConnectionsActivities(long userId, int start, int total){
		return userDao.getMyConnectionsActivities(userId,start,total);
	}
	public List<SocialActivity> getMyGroupsActivities(long userId, int start, int total){
		return userDao.getMyGroupsActivities(userId,start,total);
	}
	public List<SocialActivity> getSocialActivities(long userId, int start, int total){
		return userDao.getSocialActivities(userId,start,total);
	}
	public List<SocialActivity> getSocialActivitiesByOrg(long orgId, int start, int total){
		return userDao.getSocialActivitiesByOrg(orgId,start,total);
	}
	public List<ObjectRole> getAllRoles(long orgId){
		return userDao.getAllRoles(orgId);
	}
	public List<UserObjectRole> getUserRoles(long userId, long orgId){
		List<UserObjectRole> roles = (List<UserObjectRole>)getCacheService().getList(UserObjectRole.class,userId);
		if(roles == null){
			roles = userDao.getUserRoles(userId,orgId);
			getCacheService().setList(UserObjectRole.class,userId,roles);
		}
		return roles;
	}
	
	public List<UserObjectRole> getUserChannels(long userId, long orgId){
		List<UserObjectRole> list = this.getUserRoles(userId,orgId);
		List<UserObjectRole> channels = new ArrayList<UserObjectRole>();
		for(UserObjectRole role : list){
			if(role.getName().indexOf("channel_") >= 0){
				channels.add(role);
			}
		}
		return channels;
	}
	
	public List<UserObjectRole> getUserChannel(long userId,long orgId){
		List<UserObjectRole> list = this.getUserPermissionDao().getRolesByUserId(orgId,userId,orgId,_OBJECT_TYPE_ORG);		
		List<UserObjectRole> roles= new ArrayList<UserObjectRole>();		
		return roles;
	}
	public void trackInputKeywords(String input, User user, long personId, int type, int weight){		
		if(!StringUtil.isEmpty(input)){
			long orgId = user.getOrgId();
			Keyword inputKeyword = this.getKeyword(input,type,orgId);
			boolean newKeyword = false;
			if(inputKeyword == null){
				inputKeyword = new Keyword(input,weight,type,orgId);
				this.save(inputKeyword);
				newKeyword= true;
			}
			UserKeyword userKeyword = new UserKeyword(inputKeyword.getId(),personId,orgId, weight,type,1);
			this.save(userKeyword);
			
			if(!newKeyword){
				rankKeyword(inputKeyword,type);
			}
			
			String[] keywords = SearcherUtil.escape(input).split(" ");
	        for(int i=0;i<keywords.length;i++){
	        	String word = keywords[i].trim();
	        	if(word.length() > 0){
		        	word = keepWordOnly(word);		        	
		        	if(this.isKeyword(word)){
		        		saveKeyword(word,user,personId,type,weight);
		        	}
		        }
			}
		}
	}
	public void rankKeyword(Keyword keyword,int type){
		if(keyword.getModifiedDate() != null){
			Date current = new Date(System.currentTimeMillis());
			int days = DateUtil.calculateDifferenceDays(current, new Date(keyword.getModifiedDate().getTime()));
			if(days < 100){
				List<Timestamp> dates = this.getUserDao().getUserKeywordDates(keyword.getId(),type);
				int score = 0;
				for(Timestamp date : dates){
					days = DateUtil.calculateDifferenceDays(current, new Date(date.getTime()));
					score+=(days<100) ? (100-days) : 0;
				}
				if(keyword.getWeight() != score){
					keyword.setWeight(score);										
					this.save(keyword);			
				}
			}
		}
	}
	public void trackUserKeywords(String input, User user, long personId, int type, int weight){		
		if(!StringUtil.isEmpty(input)){			
			String[] keywords = SearcherUtil.escape(input).split(" ");
	        for(int i=0;i<keywords.length;i++){
	        	String word = keywords[i].trim();
	        	if(word.length() > 0){
		        	word = keepWordOnly(word);		        	
		        	if(this.isKeyword(word)){
		        		saveKeyword(word,user,personId,type,weight);
		        	}
	        	}
	        }
		}
	}
	private void saveKeyword(String word, User user, long personId, int type, int weight){
		if(!StringUtil.isEmpty(word)){
			long orgId = OrganizationThreadLocal.getOrganizationId();
			Keyword keyword = this.getKeyword(word,type,orgId);
			if(keyword == null){
				keyword = new Keyword(word,weight,type,orgId);
				this.save(keyword);
				if(user.getGrowKeyword() == 1){
					UserKeyword userKeyword = new UserKeyword(keyword.getId(),personId,orgId,weight,type);
					this.save(userKeyword);
				}
			}else{
				if(weight <= keyword.getWeight())
					keyword.weightIt();
				else
					keyword.weightIt(weight);
				this.save(keyword);
				if(user.getGrowKeyword() == 1){
					UserKeyword userKeyword = this.getUserKeyword(keyword.getId(),personId);
					if(userKeyword == null){
						userKeyword = new UserKeyword(keyword.getId(),personId,orgId,weight,type);    						
					}else{
						if(weight <= userKeyword.getWeight())
							userKeyword.weightIt();
						else
							userKeyword.weightIt(weight);
					}
					this.save(userKeyword);
				}
			}
		}
	}
	public boolean isKeyword(String word){
		word = word.trim().toLowerCase();
		boolean keyword = true;		
		try{
			Integer.parseInt(word);
			keyword = false;
		}catch(Exception e){}
		if(keyword){					
			if(this.getNotKeyword(word) != null) keyword= false;			
		}
		if(keyword && word.endsWith("s")){	
			if(this.getNotKeyword(word.substring(0,word.length() - 1)) != null) keyword= false;			
		}
		return keyword;
	}
		
	public List<NotKeyword> getNotKeywords(){
		List<NotKeyword> list = (List<NotKeyword>)getCacheService().getList(NotKeyword.class,CacheService.ALL);
		if(list == null){
			list = userDao.getNotKeywords();
			getCacheService().setList(NotKeyword.class,CacheService.ALL,list);
			HashMap<String,NotKeyword> map = new HashMap<String,NotKeyword>();
			for(NotKeyword word : list){
				map.put(word.getWord(),word);
			}
			getCacheService().setObject(NotKeyword.class,CacheService.ALL,map);
		}
		return list;
	}
	
	public NotKeyword getNotKeyword(String word){
		if(getCacheService().getList(NotKeyword.class,CacheService.ALL) == null) this.getNotKeywords();
		HashMap<String,NotKeyword> map = (HashMap<String,NotKeyword>)getCacheService().getObject(NotKeyword.class,CacheService.ALL);
		if(map == null){
			List<NotKeyword> list = (List<NotKeyword>)getCacheService().getList(NotKeyword.class,CacheService.ALL);
			if(list != null){
				map = new HashMap<String,NotKeyword>();
				for(NotKeyword bean : list){
					map.put(bean.getWord(),bean);
				}
				getCacheService().setObject(NotKeyword.class,CacheService.ALL,map);
			}
		}
		return (map != null) ? map.get(word) : null;
	}
	
	public List<NotWord> getNotWords(){
		List<NotWord> list = (List<NotWord>)getCacheService().getList(NotWord.class,CacheService.ALL);
		if(list == null){
			list = userDao.getNotWords();
			getCacheService().setList(NotWord.class,CacheService.ALL,list);
			HashMap<String,NotWord> map = new HashMap<String,NotWord>();
			for(NotWord word : list){
				map.put(word.getWord(),word);
			}
			getCacheService().setObject(NotWord.class,CacheService.ALL,map);
		}
		return list;
	}
	public NotWord getNotWord(String word){
		if(getCacheService().getList(NotWord.class,CacheService.ALL) == null) this.getNotWords();
		Map<String,NotWord> map = (Map<String,NotWord>)getCacheService().getObject(NotWord.class,CacheService.ALL);
		return map.get(word);
	}
	
	private String keepWordOnly(String keyword){
		List<NotWord> notWords= this.getNotWords();
		for(NotWord notWord : notWords){
			if(keyword.endsWith(notWord.getWord())) keyword= keyword.substring(0,keyword.length() - 1);
			if(keyword.startsWith(notWord.getWord())) keyword= keyword.substring(1);
		}
		return keyword;
	}
	
	public Set<String> getSimilarKeywords(int type, long orgId, String word){
		String key = "SimilarKeywords"+orgId+type+word;
		HashSet<String> similars = (HashSet<String>)getCacheService().getObject(Keyword.class, key);
		if(similars == null){
			logger.info("get Similar Keywords for "+word);
			similars = new HashSet<String>();		
			if(!StringUtil.isEmpty(word) && word.length() >= 3){
				List<String> keywords = this.getKeywordsString(type,orgId);		
				int diff = 1;		
				while(similars.size() == 0 && diff <=2){
					for(String keyword : keywords){
						if(!keyword.equalsIgnoreCase(word) 
								&& (word.length() > diff || keyword.length() > diff)
								&& (Math.abs(word.length() - keyword.length()) <= diff)
								&& StringUtil.distanceIgnoreCase(word,keyword) <= diff){
							similars.add(keyword);
							if(similars.size() >= 4){
								getCacheService().setObject(Keyword.class, key, similars);
								return similars;
							}
						}
					}
					diff++;
				}
			}
		}
		getCacheService().setObject(Keyword.class, key, similars);
		return similars;
	}

	public List<String> getKeywordsString(int type, long orgId){
		String key = "String"+orgId+type;
		List<String> keywords = (List<String>)getCacheService().getList(Keyword.class, key);		
		if(keywords == null){
			keywords = userDao.getKeywordsString(type,orgId);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(Keyword.class, key,keywords,true);
		}
		return keywords;
	}
	
	public List<Keyword> getKeywords(int type, long orgId){
		List<Keyword> keywords = (List<Keyword>)getCacheService().getList(Keyword.class, ""+orgId+type);		
		if(keywords == null){
			keywords = userDao.getKeywords(type,orgId);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(Keyword.class, ""+orgId+type,keywords,true);
		}
		return keywords;
	}
		
	public List<String> getTopKeywords(int type, long orgId, int max){
		List<String> keywords = (List<String>)getCacheService().getList(Keyword.class, ""+orgId+type+max);		
		if(keywords == null){
			keywords = userDao.getTopKeywords(type,orgId,max);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(Keyword.class, ""+orgId+type+max,keywords,true);		
		}
		return keywords;
	}
	
	public Keyword getKeyword(String word, int type, long orgId){		
		return userDao.getKeyword(word,type,orgId);
	}
	public List<String> getKeywords(int type, String input,long orgId){
		List<String> keywords = (List<String>)getCacheService().getList(Keyword.class, ""+orgId+type+input);		
		if(keywords == null){
			keywords = userDao.getKeywords(type,input,orgId);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(Keyword.class, ""+orgId+type+input,keywords);		
		}
		return keywords;
	}
	public List<UserKeyword> getUserKeywords(long personId){
		List<UserKeyword> keywords = (List<UserKeyword>)getCacheService().getList(UserKeyword.class, personId);		
		if(keywords == null){
			keywords = userDao.getUserKeywords(personId);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(UserKeyword.class, personId,keywords);		
		}
		return keywords;
	}
	
	public List<String> getUserInputKeywords(long personId,int type){
		List<String> keywords = (List<String>)getCacheService().getList(UserKeyword.class, personId+CacheService.SEPARATOR+type);		
		if(keywords == null){
			keywords = userDao.getUserInputKeywords(personId,type);
			if(keywords != null && keywords.size() > 0)
				getCacheService().setList(UserKeyword.class, personId+CacheService.SEPARATOR+type, keywords);		
		}
		return keywords;
	}
	
	public void clearUserInputKeywords(long personId,int type){
		userDao.clearUserInputKeywords(personId,type);
	}
	public UserKeyword getUserKeyword(long keywordId,long personId){
		String key = keywordId+CacheService.SEPARATOR+personId;
		UserKeyword keyword = (UserKeyword)getCacheService().getObject(UserKeyword.class,key);
		if(keyword == null){
			keyword = userDao.getUserKeyword(keywordId,personId);
			getCacheService().setObject(UserKeyword.class,key,keyword);
		}
		return keyword;
	}
	public UserKeyword getUserKeywordById(long id){
		UserKeyword keyword = (UserKeyword)getCacheService().getObject(UserKeyword.class,id);
		if(keyword == null){
			keyword = userDao.getUserKeywordById(id);
			getCacheService().setObject(UserKeyword.class,id,keyword);
		}
		return keyword; 
	}
	public List<Long> getPersonIdsByKeyword(long orgId){
		List<Long> ids = (List<Long>)getCacheService().getList(UserKeyword.class, orgId);		
		if(ids == null){
			ids = userDao.getPersonIdsByKeyword(orgId);
			if(ids != null && ids.size() > 0)
				getCacheService().setList(UserKeyword.class, orgId, ids);
		}
		return ids;
	}
	
	public ObjectRole getRoleByName(String name, long orgId){
		ObjectRole role = getCacheService().getRole(name,orgId);
		if(role == null){
			role = userPermissionDao.getRoleByName(name,orgId);
			if(role != null) getCacheService().setRole(role,orgId);
		}
		return role;
	}
		
	public List<TagFilter> getTagFilters(long orgId){
		return userDao.getTagFilters(orgId);
	}
	
	public Subscriber getSubscriber(String email, int type,long orgId){
		return userDao.getSubscriber(email,type,orgId);
	}
	
	public List<Subscriber> getSubscribers(int type,long orgId){
		return userDao.getSubscribers(type,orgId);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Indexer getIndexer() {
		return indexer;
	}
	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}
	public Searcher getSearcher() {
		return searcher;
	}
	public void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}
	public ConnectionService getConnectionService() {
		return connectionService;
	}
	public void setConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}
	public GroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public UserPermissionDao getUserPermissionDao() {
		return userPermissionDao;
	}
	public void setUserPermissionDao(UserPermissionDao userPermissionDao) {
		this.userPermissionDao = userPermissionDao;
	}
		
	public PortalService getPortalService() {
		return portalService;
	}

	public void setPortalService(PortalService portalService) {
		this.portalService = portalService;
	}	
}