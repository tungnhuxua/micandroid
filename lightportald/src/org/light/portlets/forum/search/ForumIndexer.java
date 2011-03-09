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

package org.light.portlets.forum.search;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.light.portal.core.service.ServiceContext;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.model.User;
import org.light.portal.search.GenericIndexer;
import org.light.portal.search.Indexer;
import org.light.portal.util.DateUtil;
import org.light.portlets.forum.ForumPost;
import org.light.portlets.forum.service.ForumService;

/**
 * 
 * @author Jianmin Liu
 **/
public class ForumIndexer extends GenericIndexer implements Indexer{
	
	public final static String _TYPE = "frm";
	public final static String _TYPE_ENTITY = "Forum";
	public final static String _TYPE_OBJECT = "ForumPost";
	
	public String getType(){
		return _TYPE;
	}
	
	protected List<Long> getIds(long orgId){
		return getForumService().getIds(orgId,_TYPE_OBJECT);
	}
	
	protected void update(long orgId, long id){
		ForumPost post = getForumService().getPostById(id);
		if(post != null)
			updateIndex(post);
	}
		
	public void deleteIndex(Entity entity){
		if(!(entity instanceof ForumPost)) return;		
		deleteIndex(entity,getType(),entity.getOrgId());
	}
		
	public void  updateIndex(Entity entity){
		if(!(entity instanceof ForumPost)) return;
		ForumPost post = (ForumPost)entity;		
		try {
			deleteIndex(entity);
		}
		catch (Exception e) {
		}
	
		addIndex(post);
	}
		
	protected void addIndex(ForumPost post){
		addIndex(getEntryDocuments(post),post.getOrgId());
	}
		
	private  List<Document> getEntryDocuments(List<ForumPost> posts) {
		List<Document> docs = new LinkedList<Document>();
		for(ForumPost post : posts){
			docs.add(this.getPublicDocument(post));
		}		
		return docs;
	}	
	
	private  List<Document> getEntryDocuments(ForumPost post) {
		List<Document> docs = new LinkedList<Document>();
		try{			
			docs.add(this.getPublicDocument(post));
		}catch(Exception e){
			
		}
		return docs;
	}	
		
	private  Document getPublicDocument(ForumPost post) {
		try{			
			Document doc = new Document();	
			Organization org = this.getUserService().getOrgById(post.getOrgId());
			String entryId = getType()+String.valueOf(post.getId());	
			doc.add(new Field(Indexer._TYPE_ID,getType(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field(_ENTRY_ID,entryId, Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("id",String.valueOf(post.getId()), Store.YES, Index.NOT_ANALYZED));
			if(post.getTopic() != null)
				doc.add(new Field("subject",post.getTopic(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("content",post.getContent(), Store.YES, Index.NOT_ANALYZED));			
			doc.add(new Field("link","http://www."+org.getWebId()+"/forum/"+post.getCategoryId()+"-"+post.getForumId()+"-"+((post.getTopicId() > 0) ? post.getTopicId() : post.getId()), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("userId",String.valueOf(post.getPostById()), Store.YES, Index.NOT_ANALYZED));			
						
			User user = this.getUserService().getUserById(post.getPostById());
			doc.add(new Field("name",user.getName(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("sortName",user.getName().toLowerCase(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("uri",user.getUri().toLowerCase(), Store.YES, Index.NOT_ANALYZED));		
			doc.add(new Field("date",DateUtil.format(post.getCreateDate(),"yyyy/MM/dd HH:mm:ss"),Store.YES,Index.NOT_ANALYZED));
			if(user.getPhotoUrl() != null){
				doc.add(new Field("photoUrl",user.getPhotoUrl(), Store.YES, Index.NO));				
			}
			if(post.getTopic() != null)
				doc.add(new Field("keyword",post.getTopic(), Store.NO, Index.ANALYZED));			
			doc.add(new Field("keyword",post.getContent(), Store.NO, Index.ANALYZED));
						
			return doc;
		}catch(Exception e){
			return null;
		}
	}
	
	private ForumService getForumService(){
		return (ForumService)ServiceContext.getInstance().getContext().getBean("forumService");
	}
}