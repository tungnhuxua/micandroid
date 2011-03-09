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

package org.light.portlets.blog.service;

import java.util.List;

import org.light.portal.core.service.BaseService;
import org.light.portlets.blog.Blog;
import org.light.portlets.blog.BlogCategory;

/**
 * 
 * @author Jianmin Liu
 **/
public interface BlogService extends BaseService {
	public int getBlogsTotalByUser(long userId, long orgId);
	public List<Blog> getBlogsByUser(long userId, long orgId);	
	public List<Blog> getBlogsByUser(long userId, long orgId,String sort,int start, int end);
	public int getBlogsTotalByVisitor(long userId, long orgId);
	public List<Blog> getBlogsByVisitor(long userId, long orgId);
	public List<Blog> getBlogsByVisitor(long userId, long orgId,String sort,int start, int end);
	public Blog getBlogById(long id);
	public int getBlogsTotal(long orgId);
	public List<Blog> getBlogs(long orgId);
	public List<Blog> getBlogs(long orgId,String sort);
	public List<Blog> getBlogs(long orgId, int start, int end);
	public List<Blog> getBlogs(long orgId,String sort, int start, int end);
	public List<BlogCategory> getBlogCategoriesByUser(long userId);
	public List<Blog> getBlogsByCategory(long categoryId, String sort, int start, int end);
	public int getBlogsTotalByCategory(long categoryId);
}
