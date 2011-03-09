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

package org.light.portal.user.service;

import java.util.List;
import java.util.Set;

import org.light.portal.core.service.BaseService;
import org.light.portal.model.Keyword;
import org.light.portal.model.NotKeyword;
import org.light.portal.model.NotWord;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.Permission;
import org.light.portal.model.PicturePositionTag;
import org.light.portal.model.SocialActivity;
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
import org.light.portal.search.model.SearchCriteria;
import org.light.portal.search.model.SearchResult;
import org.light.portlets.addressbook.AddressBook;
import org.light.portlets.bookmark.Bookmark;
import org.light.portlets.bulletin.Bulletin;
import org.light.portlets.feedback.Feedback;
import org.light.portlets.horoscope.Horoscope;
import org.light.portlets.message.Message;
import org.light.portlets.note.Note;
import org.light.portlets.todolist.ToDoBean;

/**
 * 
 * @author Jianmin Liu
 **/
public interface UserService extends BaseService{
	
	public User signUp(User newUser,long orgId);	
	public void deleteUser(User user);	
	public User getUserById(long id);	
	public User getUserByUserId(String userId, long orgId);	
	public User getUserByUri(String uri,long orgId);	
	public List<User> viewUsersByOrgId(long orgId);
	public List<User> getUsersByOrgId(long orgId);
	public List<User> getCoolNewPeople(int start, int max);
	public List<User> getOnlinePeople(int start, int max);
	public UserProfile getUserProfileById(long userId);	
	
	public long getAdminUserId(long orgId);
	public boolean sendNewPassword(User user);
	public boolean saveUserUrl(User user, String uri);
	public boolean saveUserCaption(User user, String caption);
	public int getOnlinePeopleTotal();
	public int getCoolNewPeopleTotal();
	public List<Long> getPersonIdsByKeyword(long orgId);
	
	public Organization getOrgByVirtualHost(String host);
	public Organization getOrgById(long id);
	public List<Organization> getOrganizations();	
	public OrgProfile getOrgProfileByOrgId(long orgId, String language);
	public void createOrganization(Organization org,OrgProfile profile);
	public void createSubOrganization(Organization org,OrgProfile profile);
	
	public void reIndex(long orgId);
	public void reIndex(Class klass, long orgId);
	public SearchResult searchUser(SearchCriteria criteria) throws Exception;
	
	//public List<Permission> getUserPermissions(long orgId, long userId);
	
	
	public UserComment getUserCommentsById(long id);
	public List<UserComment> getUserComments(long userId);
	public List<UserComment> getCommentsByType(long objectId, int objectType);
	public void deleteUserCommentsById(long id);
	public int getUserCommentsCount(long userId);
	
	public UserTag getUserTagById(long id);
	public boolean hasUserTag(long objectId, int objectType, String tag);
	public List<UserTag> getUserTags(long objectId, int objectType);
	public List<UserTag> getUserTags(long objectId, int objectType,boolean flag);
	
	public UserPicture getUserPictureById(long id);	
	public List<UserPicture> getPublicPictures(long orgId, int start,int max);
	public List<UserPicture> getOrgPictures(long orgId);
	public List<UserPicture> getAllPictures(long orgId);
	public List<UserPicture> getUserPictures(long userId,long orgId);
	public List<UserPicture> getVisitedUserPictures(long userId,long visitedId,long orgId);
	public List<UserPicture> getUserRankPictures(long userId);
	public List<PicturePositionTag> getPicturePositionTags(long pictureId);
	public List<PicturePositionTag> getPicturePositionTags(String pictureUrl);
	public PicturePositionTag getPicturePositionTagById(long id);
	public int getOrgPictureCount(long orgId);
	public void updatePictureStatus(long userId,int status);
	public int getUserPictureCount(long userId,long orgId);
	
	public UserMusic getUserMusicById(long id);
	public List<UserMusic> getUserMusics(long userId);
	public List<UserMusic> getUserRankMusics(long userId);
	public List<UserMusic> getVisitedUserMusics(long userId,long visitedId);	
	public void updateMusicStatus(long userId,int status);
	public int getUserMusicCount(long userId);
	
	public UserFile getUserFileById(long id);
	public List<UserFile> getUserFiles(long userId);
	public List<UserFile> getVisitedUserFiles(long userId,long visitedId);
	public void updateFileStatus(long userId,int status);
	public int getUserFileCount(long userId);
	
	public Bulletin getBulletinById(long id);	
	public List<Bulletin> getBulletinsByUser(long userId);
	public List<Bulletin> getBulletinsByOrg(long orgId);
	public void sendBulletin(Bulletin bulletin);
	public int getUserBulletinCount(long userId);
	
	public boolean inviteFriends(String mailList,String desc,User user);
	public int getNewConnectionRequestCountByUser(long userId);
	public boolean approveConnection(Message message);	
	
	public void sendMessage(Message message);
	public Message getMessageById(long id);
	public List<Message> getMessagesBySender(long userId);
	public List<Message> getMessagesByUser(long userId);
	public List<Message> getConnectionRequestMessagesByUser(long userId);
	public int getNewMessageCountByUser(long userId);
	
	public List<UserFavourite> getUserFavourites(long userId);
	public List<UserBlock> getUserBlocks(long userId);
	public List<UserInvite> getUserInvites(long userId);	
	public List<UserInvite> getUserInviteByEmail(String email);
	public UserBlock getUserBlockById(long id);
	public UserBlock getUserBlockByUser(long userId, long blockId);
	
	public AddressBook getAddressBookById(long id);
	public List<AddressBook> getAddressBooksByUser(long userId);
	public List<AddressBook> getAddressBooksByUser(long userId, String group);
	public List<String> getAddressBookGroupByUser(long userId);
	
	public List<Feedback> getFeedback(long orgId);
	public Feedback getFeedbackById(long id, long orgId);	
	
	public Bookmark getBookmarkById(long id);		
	public List<Bookmark> getBookmarksByUser(long userId);
	public List<Bookmark> getBookmarksByTag(long userId, String tag);
	
	public List<ToDoBean> getToDosByUser(long userId);
	public ToDoBean getToDoById(long id);
	public int getUserToDoCount(long userId);
	
	public Note getNoteByUser(long userId);
	
	public Horoscope getUserHoroscope(int month, int day);
	public List<Horoscope> getHoroscopes();
	public String getUserHoroscopeWeeklyInfo(long horoscopeId, String language);
	
	public int getMyActivitiesCount(long userId);
	public int getMyConnectionsActivitiesCount(long userId);
	public int getMyGroupsActivitiesCount(long userId);
	public int getSocialActivitiesCount(long userId);
	public int getSocialActivitiesCountByOrg(long orgId);
	public List<SocialActivity> getMyActivities(long userId, int start, int total);
	public List<SocialActivity> getMyConnectionsActivities(long userId, int start, int total);
	public List<SocialActivity> getMyGroupsActivities(long userId, int start, int total);
	public List<SocialActivity> getSocialActivities(long userId, int start, int end);
	public List<SocialActivity> getSocialActivitiesByOrg(long orgId, int start, int total);
	
	public List<ObjectRole> getAllRoles(long orgId);
	public ObjectRole getRoleByName(String name, long orgId);
	public List<UserObjectRole> getUserRoles(long userId, long orgId);
	public List<UserObjectRole> getUserChannels(long userId,long orgId);	
	
	public List<UserKeyword> getUserKeywords(long personId);
	public List<String> getUserInputKeywords(long personId,int type);
	public void clearUserInputKeywords(long personId,int type);
	public UserKeyword getUserKeyword(long keywordId,long userId);
	public UserKeyword getUserKeywordById(long id);
	public Keyword getKeyword(String word, int type, long orgId);
	public List<Keyword> getKeywords(int type, long orgId);
	public List<String> getTopKeywords(int type, long orgId, int max);
	public List<String> getKeywords(int type, String input,long orgId);
	public Set<String> getSimilarKeywords(int type, long orgId, String word);
	public void trackInputKeywords(String input, User user, long personId, int type, int weight);
	public void rankKeyword(Keyword keyword,int type);
	public void trackUserKeywords(String title, User user, long personId, int type, int weight);
	public List<NotKeyword> getNotKeywords();
	public NotKeyword getNotKeyword(String word);
	public NotWord getNotWord(String word);
	public List<NotWord> getNotWords();
	public boolean isKeyword(String word);
	public List<TagFilter> getTagFilters(long orgId);
	public Subscriber getSubscriber(String email, int type,long orgId);	
	public List<Subscriber> getSubscribers(int type,long orgId);
}