package org.light.portlets.group.search;

import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.light.portal.core.service.ServiceContext;
import org.light.portal.model.Entity;
import org.light.portal.model.Organization;
import org.light.portal.search.GenericIndexer;
import org.light.portal.search.Indexer;
import org.light.portal.util.DateUtil;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;

public class GroupIndexer extends GenericIndexer implements Indexer{
	
	public final static String _TYPE = "grp";
	public final static String _TYPE_ENTITY = "Group";
	
	public String getType(){
		return _TYPE;
	}
	
	protected List<Long> getIds(long orgId){
		return getGroupService().getIds(orgId,_TYPE_ENTITY);
	}
	
	protected void update(long orgId, long id){
		Group group = getGroupService().getGroupById(id);
		if(group != null)
			updateIndex(group);
	}
	
	public void deleteIndex(Entity entity){
		if(!(entity instanceof Group)) return;		
		deleteIndex(entity,getType(),entity.getOrgId());
	}
	
	public void updateIndex(Entity entity){
		if(!(entity instanceof Group)) return;
		Group group = (Group)entity;
		try {
			deleteIndex(entity);
		}
		catch (Exception e) {
		}
		addIndex(group);
	}
	
	protected void addIndex(Group group){
		addIndex(getEntryDocuments(group),group.getOrgId());
	}
	
	private List<Document> getEntryDocuments(List<Group> groups) {
		List<Document> docs = new LinkedList<Document>();
		for(Group group : groups){
			docs.add(this.getPublicDocument(group));
		}		
		return docs;
	}	
		
	private List<Document> getEntryDocuments(Group group) {
		List<Document> docs = new LinkedList<Document>();
		try{			
			docs.add(this.getPublicDocument(group));
		}catch(Exception e){
			
		}
		return docs;
	}	
		
	private Document getPublicDocument(Group group) {
		try{			
			Document doc = new Document();	
			Organization org = this.getUserService().getOrgById(group.getOrgId());
			String entryId = getType()+String.valueOf(group.getId());	
			doc.add(new Field(_TYPE_ID,getType(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field(_ENTRY_ID,entryId, Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("id",String.valueOf(group.getId()), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("userId",String.valueOf(group.getLeaderId()), Store.YES, Index.NOT_ANALYZED));		
			doc.add(new Field("uri",group.getUri().toLowerCase(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("name",group.getDisplayName(), Store.YES, Index.ANALYZED));
			doc.add(new Field("sortName",group.getDisplayName(), Store.YES, Index.NOT_ANALYZED));					
			doc.add(new Field("content",group.getShortDesc(), Store.YES, Index.ANALYZED));			
			doc.add(new Field("link","http://"+group.getUri()+"."+org.getWebId(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("date",DateUtil.format(group.getModifiedDate(),"yyyy/MM/dd HH:mm:ss"),Store.YES,Index.NOT_ANALYZED));
			if(group.getPhotoUrl() != null){
				doc.add(new Field("photoUrl",group.getPhotoUrl(), Store.YES, Index.NO));				
			}
			if(group.getCategoryName() != null){
				doc.add(new Field("category",group.getCategoryName(), Store.YES, Index.ANALYZED));					
			}
			if(group.getDesc() != null){
				doc.add(new Field("description",group.getDesc(), Store.YES, Index.ANALYZED));				
			}						
			
			return doc;
		}catch(Exception e){
			return null;
		}
	}
	
	protected GroupService getGroupService() {
		return (GroupService)ServiceContext.getInstance().getContext().getBean("groupService");
	}
}

