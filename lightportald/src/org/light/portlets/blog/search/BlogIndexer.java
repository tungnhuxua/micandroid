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

package org.light.portlets.blog.search;

import static org.light.portal.util.Constants._OBJECT_TYPE_BLOG;

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
import org.light.portal.model.UserComment;
import org.light.portal.search.GenericIndexer;
import org.light.portal.search.Indexer;
import org.light.portal.util.DateUtil;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.service.BlogService;

/**
 * 
 * @author Jianmin Liu
 **/
public class BlogIndexer extends GenericIndexer implements Indexer{

	public final static String _TYPE = "blg";
	public final static String _TYPE_ENTITY = "Blog";
	
	public String getType(){
		return _TYPE;
	}
	
	protected List<Long> getIds(long orgId){
		return getBlogService().getIds(orgId,_TYPE_ENTITY);
	}
	
	protected void update(long orgId, long id){
		Blog blog = getBlogService().getBlogById(id);
		if(blog != null)
			updateIndex(blog);
	}
	
	public void deleteIndex(Entity entity){
		if(!(entity instanceof Blog)) return;
		deleteIndex(entity,getType(),entity.getOrgId());
	}
	
	public void updateIndex(Entity entity){
		if(!(entity instanceof Blog)) return;
		Blog blog = (Blog)entity;		
		try {
			deleteIndex(entity);
		}
		catch (Exception e) {
		}
	
		addIndex(blog,blog.getOrgId());
	}

	protected void addIndex(Blog blog, long orgId){
		addIndex(getEntryDocuments(blog),orgId);
	}
		
	private  List<Document> getEntryDocuments(List<Blog> blogs) {
		List<Document> docs = new LinkedList<Document>();
		for(Blog blog : blogs){
			docs.add(this.getPublicDocument(blog));
		}		
		return docs;
	}	
	
	private  List<Document> getEntryDocuments(Blog blog) {
		List<Document> docs = new LinkedList<Document>();
		try{			
			docs.add(this.getPublicDocument(blog));
		}catch(Exception e){
			
		}
		return docs;
	}	
		
	private  Document getPublicDocument(Blog blog) {
		try{			
			Document doc = new Document();	
			Organization org = this.getUserService().getOrgById(blog.getOrgId());
			String entryId = getType()+String.valueOf(blog.getId());	
			doc.add(new Field(_TYPE_ID,getType(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field(_ENTRY_ID,entryId, Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("id",String.valueOf(blog.getId()), Store.YES, Index.NOT_ANALYZED));			
			doc.add(new Field("subject",blog.getTitle(), Store.YES, Index.NOT_ANALYZED));			
			if(blog.getSummary() != null)
				doc.add(new Field("summary",blog.getSummary(), Store.YES, Index.NOT_ANALYZED));			
			if(blog.getContent() != null)
				doc.add(new Field("content",blog.getContent(), Store.YES, Index.NOT_ANALYZED));			
			doc.add(new Field("userId",String.valueOf(blog.getPostedById()), Store.YES, Index.NOT_ANALYZED));			
			doc.add(new Field("link","http://www."+org.getWebId()+"/blog/"+blog.getId(), Store.YES, Index.NOT_ANALYZED));
						
			User user = this.getUserService().getUserById(blog.getPostedById());
			doc.add(new Field("name",user.getName(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("sortName",user.getName().toLowerCase(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("uri",user.getUri().toLowerCase(), Store.YES, Index.NOT_ANALYZED));		
			doc.add(new Field("date",DateUtil.format(blog.getCreateDate(),"yyyy/MM/dd HH:mm:ss"),Store.YES,Index.NOT_ANALYZED));			
			if(user.getPhotoUrl() != null){
				doc.add(new Field("photoUrl",user.getPhotoUrl(), Store.YES, Index.NO));
			}
			
			doc.add(new Field("keyword",blog.getTitle(), Store.NO, Index.ANALYZED));
			if(blog.getSummary() != null)
				doc.add(new Field("keyword",blog.getSummary(), Store.NO, Index.ANALYZED));
			if(blog.getContent() != null)
				doc.add(new Field("keyword",blog.getContent(), Store.NO, Index.ANALYZED));
			
			List<UserComment> comments = this.getUserService().getCommentsByType(blog.getId(), _OBJECT_TYPE_BLOG);	
			for(UserComment comment : comments){
				doc.add(new Field("keyword",comment.getComment(), Store.NO, Index.ANALYZED));
			}
			
			return doc;
		}catch(Exception e){
			return null;
		}
		
		
	}
	
	protected BlogService getBlogService(){
		return (BlogService)ServiceContext.getInstance().getContext().getBean("blogService");
	}
	
}