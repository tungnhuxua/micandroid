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

package org.light.portal.user.search;

import static org.light.portal.util.Constants._OBJECT_TYPE_USER;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.model.User;
import org.light.portal.model.UserProfile;
import org.light.portal.model.UserTag;
import org.light.portal.search.GenericIndexer;
import org.light.portal.search.Indexer;
import org.light.portal.util.DateUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserIndexer extends GenericIndexer implements Indexer{
	
	public final static String _TYPE = "usr";
	public final static String _TYPE_ENTITY = "User";
	
	public String getType(){
		return _TYPE;
	}
	
	protected List<Long> getIds(long orgId){
		return getUserService().getIds(orgId,_TYPE_ENTITY);
	}
	
	protected void update(long orgId, long id){
		User user = getUserService().getUserById(id);
		if(user != null)
			updateIndex(user);
	}
	
	public void deleteIndex(Entity entity){
		if(!(entity instanceof User)) return;		
		deleteIndex(entity,getType(),entity.getOrgId());
	}
	public void  updateIndex(Entity entity){
		if(!(entity instanceof User)) return;
		User user = (User)entity;
		Organization org = this.getUserService().getOrgById(user.getOrgId());
		try {
			deleteIndex(entity);
		}catch (Exception e) {
		}	
		if(user.getId() == org.getUserId()) return;
		addIndex(user, user.getOrgId());
	}

	protected void addIndex(User user, long orgId){
		addIndex(getEntryDocuments(user),orgId);
	}
	
	private  List<Document> getEntryDocuments(List<User> users) {
		List<Document> docs = new LinkedList<Document>();
		for(User user : users){
			docs.add(this.getPublicDocument(user));
		}		
		return docs;
	}	
	
	private  List<Document> getEntryDocuments(User user) {
		List<Document> docs = new LinkedList<Document>();
		try{			
			docs.add(this.getPublicDocument(user));
		}catch(Exception e){
			
		}
		return docs;
	}	
		
	private  Document getPublicDocument(User user) {
		try{			
			Document doc = new Document();	
			String entryId = getType()+String.valueOf(user.getId());	
			doc.add(new Field(_TYPE_ID,getType(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field(_ENTRY_ID,entryId, Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("id",String.valueOf(user.getId()), Store.YES, Index.NOT_ANALYZED));			
			Field userIdField = new Field("userId",user.getUserId(), Store.YES, Index.NOT_ANALYZED);
			userIdField.setBoost(10.0F);
			doc.add(userIdField);
			Field emailField = new Field("email",(user.getEmail() != null) ? user.getEmail() : user.getUserId() , Store.YES, Index.NOT_ANALYZED);
			emailField.setBoost(10.0F);
			doc.add(emailField);
			Field uriField = new Field("uri",user.getUri().toLowerCase(), Store.YES, Index.NOT_ANALYZED);
			uriField.setBoost(10.0F);
			doc.add(uriField);
			Field displayNameField = new Field("displayName",user.getName(), Store.YES, Index.ANALYZED);
			displayNameField.setBoost(10.0F);
			doc.add(displayNameField);	
			doc.add(new Field("createDate",DateUtil.format(user.getCreateDate(),"yyyy/MM/dd HH:mm:ss"),Store.YES,Index.NOT_ANALYZED));
			doc.add(new Field("date",DateUtil.format(user.getLastLoginDate(),"yyyy/MM/dd HH:mm:ss"),Store.YES,Index.NOT_ANALYZED));
			UserProfile profile = getUserService().getUserProfileById(user.getId());
			if(profile != null && StringUtils.isNotEmpty(profile.getFirstName())){
				Field nameField = new Field("name",profile.getFirstName(), Store.YES, Index.ANALYZED);
				nameField.setBoost(10.0F);
				doc.add(nameField);
				doc.add(new Field("sortName",profile.getFirstName().toLowerCase(), Store.YES, Index.NOT_ANALYZED));
			}else{
				Field nameField = new Field("name",user.getName(), Store.YES, Index.ANALYZED);
				nameField.setBoost(10.0F);
				doc.add(nameField);
				doc.add(new Field("sortName",user.getName().toLowerCase(), Store.YES, Index.NOT_ANALYZED));
			}
			
			if(user.getPhotoUrl() != null){
				doc.add(new Field("photoUrl",user.getPhotoUrl(), Store.YES, Index.NO));				
			}
			
			if(profile != null){
				if(StringUtils.isNotEmpty(profile.getHeadline())){
					doc.add(new Field("headline",profile.getHeadline(), Store.NO, Index.ANALYZED));		
				}	
				if(StringUtils.isNotEmpty(profile.getAboutMe())){
					doc.add(new Field("aboutMe",profile.getAboutMe(), Store.NO, Index.ANALYZED));				
				}							
				if(StringUtils.isNotEmpty(profile.getHometown())){
					doc.add(new Field("hometown",profile.getHometown(), Store.NO, Index.ANALYZED));	
				}
				if(StringUtils.isNotEmpty(profile.getInterests())){
					doc.add(new Field("interests",profile.getInterests(), Store.NO, Index.ANALYZED));		
				}
				if(StringUtils.isNotEmpty(profile.getLikeToMeet())){
					doc.add(new Field("likeToMeet",profile.getLikeToMeet(), Store.NO, Index.ANALYZED));	
				}
				if(StringUtils.isNotEmpty(profile.getOccupation())){
					doc.add(new Field("occupation",profile.getOccupation(), Store.NO, Index.ANALYZED));				
				}
				if(StringUtils.isNotEmpty(profile.getReligion())){
					doc.add(new Field("religion",profile.getReligion(), Store.NO, Index.ANALYZED));
				}
				if(StringUtils.isNotEmpty(profile.getMovies())){
					doc.add(new Field("movie",profile.getMovies(), Store.NO, Index.ANALYZED));					
				}
				if(StringUtils.isNotEmpty(profile.getMusic())){
					doc.add(new Field("music",profile.getMusic(), Store.NO, Index.ANALYZED));		
				}
				if(StringUtils.isNotEmpty(profile.getTelevision())){
					doc.add(new Field("television",profile.getTelevision(), Store.NO, Index.ANALYZED));		
				}
				if(StringUtils.isNotEmpty(profile.getBooks())){
					doc.add(new Field("book",profile.getBooks(), Store.NO, Index.ANALYZED));	
				}
				if(StringUtils.isNotEmpty(profile.getHeroes())){
					doc.add(new Field("hero",profile.getHeroes(), Store.NO, Index.ANALYZED));			
				}		
			}
			List<UserTag> tags = this.getUserService().getUserTags(user.getId(),_OBJECT_TYPE_USER);
			for(UserTag tag : tags){
				if(StringUtils.isNotEmpty(tag.getTag())){
					doc.add(new Field("tag",tag.getTag(), Store.NO, Index.ANALYZED));
				}
			}
			return doc;
		}catch(Exception e){
			return null;
		}
	}
}
