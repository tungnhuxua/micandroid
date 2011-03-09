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

package org.light.portal.portlet.contentlibrary.service.impl;

import java.util.List;

import org.light.portal.core.service.impl.BaseServiceImpl;
import org.light.portal.portlet.contentlibrary.dao.ContentLibraryDao;
import org.light.portal.portlet.contentlibrary.entity.CLFile;
import org.light.portal.portlet.contentlibrary.entity.CLFolder;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFileException;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFolderException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFileException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFolderException;
import org.light.portal.portlet.contentlibrary.service.ContentLibraryService;

public class ContentLibraryServiceImpl extends BaseServiceImpl implements ContentLibraryService {
	
	private ContentLibraryDao contentLibraryDao;

	
	public boolean addFile(long orgId, long userId, CLFile file)
			throws DuplicateFileException {
		
		return contentLibraryDao.addFile(orgId, userId, file);
	}

	
	public boolean addFolder(long orgId, long userId, CLFolder folder)
			throws DuplicateFolderException {

		return contentLibraryDao.addFolder(orgId, userId, folder);
	}

	
	public boolean copyFile(long orgId, long userId, CLFile sourceFile,
			CLFile destFile) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean copyFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deleteFile(long orgId, long userId, CLFile file,
			boolean isSoftDelete) throws NoSuchFileException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean deleteFolder(long orgId, long userId, CLFolder folder,
			boolean isSoftDelete) throws NoSuchFolderException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int getCounts(long folderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public CLFile getFile(long orgId, long userId, CLFile file)
			throws NoSuchFileException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<CLFile> getFilesByFolderId(long orgId, long userId,
			long folderId, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<CLFolder> getSubFoldersByParentId(long orgId, long userId,
			long parentFolderId, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean moveFile(long orgId, long userId, CLFile sourceFile,
			CLFile destFile) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean moveFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean updateFile(long orgId, long userId, CLFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean updateFolder(long orgId, long userId, CLFolder folder) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getTreeSource(long orgId, long userId, long parentFolderId) {
		StringBuffer res = new StringBuffer();
		res.append("{ identifier: 'name', label: 'name', items: [");
		
		List<CLFolder> folders = contentLibraryDao.getSubFoldersByParentId(orgId, userId, parentFolderId, -1, -1);
		for(int i=0; i<folders.size(); i++) {
			CLFolder f = folders.get(i);
			res.append("{ name:'");
			res.append(f.getName());
			res.append("', type:'folder' }");
			
			if (i < folders.size() - 1) {
				res.append(",");
			}
		}
		
		res.append("]}");
		return res.toString();
	}
	
	public ContentLibraryDao getContentLibraryDao() {
		return contentLibraryDao;
	}
	
	public void setContentLibraryDao(ContentLibraryDao contentLibraryDao) {
		this.contentLibraryDao = contentLibraryDao;
	}

}
