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

package org.light.portlets.internal;

import java.sql.Timestamp;
import java.util.Calendar;

import org.light.portal.model.Entity;
import org.light.portal.util.DateUtil;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class InternalNews extends Entity {

	private long postById;
	private long orgId;
	private String subject;
	private String content;
	private Timestamp  createDate = new Timestamp(System.currentTimeMillis());
	
	public InternalNews(){
		super();
	}
    
	public InternalNews(String subject,String content,long userId, long orgId){
		this();
		this.subject = subject;
		this.content = content;
		this.postById = userId;
		this.orgId = orgId;
	}
	
	public boolean isNew(){
		Calendar now = Calendar.getInstance();		
		now.setTimeInMillis(System.currentTimeMillis());
		Calendar created = Calendar.getInstance();		
		created.setTimeInMillis(createDate.getTime());
		created.add(Calendar.DATE,PropUtil.getInt("portal.internal.news.days.new"));
		return now.before(created);
	}
	public String getDate(){
		return DateUtil.format(createDate,"MMM dd, yyyy HH:mm");
	}
	public String getContent() {
		return content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}
	

	public Timestamp getCreateDate() {
		return createDate;
	}
	

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	

	public long getPostById() {
		return postById;
	}
	

	public void setPostById(long postById) {
		this.postById = postById;
	}
	

	public String getSubject() {
		return subject;
	}
	

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	

}
