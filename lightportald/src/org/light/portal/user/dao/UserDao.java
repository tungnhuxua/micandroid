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

package org.light.portal.user.dao;

import java.sql.Timestamp;
import java.util.List;

import org.light.portal.model.Keyword;
import org.light.portal.model.NotKeyword;
import org.light.portal.model.NotWord;
import org.light.portal.model.ObjectRole;
import org.light.portal.model.OrgProfile;
import org.light.portal.model.Organization;
import org.light.portal.model.PicturePositionTag;
import org.light.portal.model.SocialActivity;
import org.light.portal.model.Subscriber;
import org.light.portal.model.TagFilter;
import org.light.portal.model.User;
import org.light.portal.model.UserBlock;
import org.light.portal.model.UserComment;
import org.light.portal.model.UserExtRole;
import org.light.portal.model.UserFavourite;
import org.light.portal.model.UserFile;
import org.light.portal.model.UserInvite;
import org.light.portal.model.UserKeyword;
import org.light.portal.model.UserMusic;
import org.light.portal.model.UserObjectRole;
import org.light.portal.model.UserPicture;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
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
public interface UserDao {
	public List<User> viewUsersByOrg(Organization org);
	public Organization getOrgByVirtualHost(String host);
	public Organization getOrgById(long id);
	public OrgProfile getOrgProfileByOrgId(long orgId, String language);
	public List<OrgProfile> getOrgProfiles(long orgId);
	public List<Organization> getOrganizations();	
	public void createOrganization(Organization org,OrgProfile profile);
	public void createOrganization(Organization org,OrgProfile profile,boolean flag);
	public void createSubOrganization(Organization org,OrgProfile profile);
	public boolean isOrgDefaultUser(long userId);
	public List<User> getUsersByOrgId(long orgId);
	public User getUserById(long id);
	public List<User> getAllUsers();
	public long getAdminUserId(long orgId);
	public User getUserByUserId(String userId,long orgId);	
	public User getUserByUri(String uri,long orgId);
	public UserProfile getUserProfileById(long userId);	
	public List<User> getCoolNewPeople(int start, int max);
	public List<User> getOnlinePeople(int start, int max);
	public int getOnlinePeopleTotal();
	public int getCoolNewPeopleTotal();
	public List<UserInvite> getUserInviteByEmail(String email);
	public List<UserComment> getUserComments(long userId);
	public List<UserComment> getCommentsByType(long objectId, int objectType);
	public UserComment getUserCommentsById(long id);
	public void deleteUserCommentsById(long id);
	public boolean hasUserTag(long objectId, int objectType, String tag);
	public List<UserTag> getUserTags(long objectId, int objectType);
	public UserTag getUserTagById(long id);
	public long getMaxUserTagScore(long orgId, int objectType);
	public void updatePictureStatus(long userId,int status);
	public void updateMusicStatus(long userId,int status);
	public void updateFileStatus(long userId,int status);
	public List<UserPicture> getUserPictures(long userId,long orgId);
	public List<UserPicture> getUserPicturesByStatus(long userId, int status,long orgId);	
	public List<UserPicture> getOrgPictures(long orgId);
	public List<UserPicture> getAllPictures(long orgId);
	public List<UserPicture> getPublicPictures(long orgId, int start,int max);
	public int getOrgPictureCount(long orgId);
	public List<UserPicture> getUserRankPictures(long userId);
	public UserPicture getUserPictureById(long id);
	public List<PicturePositionTag> getPicturePositionTags(long pictureId);
	public List<PicturePositionTag> getPicturePositionTags(String pictureUrl);
	public PicturePositionTag getPicturePositionTagById(long id);
	public List<UserFile> getUserFiles(long userId);
	public List<UserFile> getUserFilesByStatus(long userId, int status);
	public UserFile getUserFileById(long id);
	public List<Bulletin> getBulletinsByUser(long userId);
	public List<Bulletin> getBulletinsByOrg(long orgId);
	public Bulletin getBulletinById(long id);
	public Message getMessageById(long id);
	public List<Message> getMessagesBySender(long userId);
	public List<Message> getMessagesByUser(long userId);
	public List<Message> getConnectionRequestMessagesByUser(long userId);
	public int getNewMessageCountByUser(long userId);
	public int getNewConnectionRequestCountByUser(long userId);
	public List<UserFavourite> getUserFavourites(long userId);
	public List<UserBlock> getUserBlocks(long userId);
	public List<UserInvite> getUserInvites(long userId);
	public UserBlock getUserBlockById(long id);
	public UserBlock getUserBlockByUser(long userId, long blockId);
	public UserMusic getUserMusicById(long id);
	public List<UserMusic> getUserMusics(long userId);
	public List<UserMusic> getUserRankMusics(long userId);
	public List<UserMusic> getUserMusicsByStatus(long userId, int status);
	public void deleteUser(User user);
	public List<UserExtRole> getUserExtRole(long userId);
	public AddressBook getAddressBookById(long id);
	public List<AddressBook> getAddressBooksByUser(long userId);
	public List<AddressBook> getAddressBooksByUser(long userId, String group);
	public List<String> getAddressBookGroupByUser(long userId);
	public List<Feedback> getFeedback(long orgId);
	public Feedback getFeedbackById(long id);	
	public Bookmark getBookmarkById(long id);	
	public List<Bookmark> getBookmarksByUser(long userId);
	public List<Bookmark> getBookmarksByTag(long userId, String tag);
	public List<ToDoBean> getToDosByUser(long userId);
	public ToDoBean getToDoById(long id);
	public Note getNoteByUser(long userId);
	public int getUserPictureCount(long userId,long orgId);
	public int getUserMusicCount(long userId);
	public int getUserFileCount(long userId);
	public int getUserToDoCount(long userId);
	public int getUserBulletinCount(long userId);
	public Horoscope getUserHoroscope(int month, int day);
	public List<Horoscope> getHoroscopes();
	public String getUserHoroscopeWeeklyInfo(long horoscopeId, String language);
	public int getUserCommentsCount(long userId);
	public List<UserComment> getUserComments(long userId, int showNumber);
	public int getMyActivitiesCount(long userId);
	public int getMyConnectionsActivitiesCount(long userId);
	public int getMyGroupsActivitiesCount(long userId);
	public int getSocialActivitiesCount(long userId);
	public int getSocialActivitiesCountByOrg(long orgId);
	public List<SocialActivity> getMyActivities(long userId, int start, int total);
	public List<SocialActivity> getMyConnectionsActivities(long userId, int start, int total);
	public List<SocialActivity> getMyGroupsActivities(long userId, int start, int total);
	public List<SocialActivity> getSocialActivities(long userId, int start, int total);
	public List<SocialActivity> getSocialActivitiesByOrg(long orgId, int start, int total);
	public List<ObjectRole> getAllRoles(long orgId);
	public List<UserObjectRole> getUserRoles(long userId,long orgId);		
	public Keyword getKeyword(String word, int type, long orgId);
	public List<UserKeyword> getUserKeywords(long personId);
	public List<String> getUserInputKeywords(long personId,int type);
	public void clearUserInputKeywords(long personId,int type);	
	public UserKeyword getUserKeyword(long keywordId,long userId);
	public UserKeyword getUserKeywordById(long id);	
	public List<String> getKeywordsString(int type, long orgId);
	public List<Keyword> getKeywords(int type, long orgId);
	public List<String> getKeywords(int type, String input,long orgId);
	public List<String> getTopKeywords(int type, long orgId, int max);
	public List<Long> getPersonIdsByKeyword(long orgId);
	public List<TagFilter> getTagFilters(long orgId);
	public NotKeyword getNotKeyword(String word);
	public List<NotKeyword> getNotKeywords();
	public List<NotWord> getNotWords();
	public NotWord getNotWord(String word);
	public List<Timestamp> getUserKeywordDates(long keywordId,int type);
	public Subscriber getSubscriber(String email, int type,long orgId);	
	public List<Subscriber> getSubscribers(int type,long orgId);
}