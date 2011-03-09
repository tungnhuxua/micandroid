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

package org.light.portlets.forum.dao;

import java.util.List;

import org.light.portlets.forum.Forum;
import org.light.portlets.forum.ForumCategory;
import org.light.portlets.forum.ForumPost;

/**
 * 
 * @author Jianmin Liu
 **/
public interface ForumDao {
	public List<ForumPost> getPosts(long orgId);
	public int getTopicsCountByForum(long forumId);
	public List<ForumPost> getPostsByTopic(long topicId);
	public List<ForumPost> getTopicsByForum(long forumId,int pageId,int maxRow);
	public int getPostsCountByTopic(long topicId);
	public List<ForumPost> getPostsByTopic(long topicId,int pageId,int maxRow);
	public ForumPost getPostById(long id);
	public ForumCategory getForumCategoryById(long id);
	public Forum getForumById(long id);
	public List<ForumCategory> getForumCategories(String lanuage,long orgId);
	public List<Forum> getForumByCategory(long categoryId);
	public List<ForumPost> getTopics(long orgId);
}
