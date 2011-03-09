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

package org.light.portal.portlet.contentlibrary;

import java.io.InputStream;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.light.portal.portlet.contentlibrary.entity.CLFile;
import org.light.portal.portlet.contentlibrary.entity.CLFolder;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFileException;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFolderException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFileException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFolderException;

public abstract class ContentLibraryImpl implements ContentLibrary {

	
	public boolean addFile(long orgId, long userId, CLFile file)
			throws DuplicateFileException {
		
		return _addFile(orgId, userId, file);
	}

	
	public boolean addFolder(long orgId, long userId, CLFolder folder)
			throws DuplicateFolderException {

		return _addFolder(orgId, userId, folder);
	}

	
	public boolean copyFile(long orgId, long userId, CLFile sourceFile,
			CLFile destFile) {

		return _copyFile(orgId, userId, sourceFile, destFile);
	}

	
	public boolean copyFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {

		return _copyFolder(orgId, userId, sourceFolder, destFolder);
	}

	
	public boolean deleteFile(long orgId, long userId, CLFile file,
			boolean isSoftDelete) throws NoSuchFileException{

		
		return _deleteFile(orgId, userId, file,	isSoftDelete);
	}

	
	public boolean deleteFolder(long orgId, long userId, CLFolder folder,
			boolean isSoftDelete) throws NoSuchFolderException {

		return _deleteFolder(orgId, userId, folder, isSoftDelete);
	}

	
	public boolean exists(String name, int contentType) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public CLFile getFile(long orgId, long userId, CLFile file)
			throws NoSuchFileException {

		return _getFile(orgId, userId, file);
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

	
	public boolean indexFile(long orgId, long userId, CLFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean indexFolder(long orgId, long userId, CLFolder folder) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean moveFile(long orgId, long userId, CLFile sourceFile,
			CLFile destFile) {

		return _moveFile(orgId, userId, sourceFile, destFile);
	}

	
	public boolean moveFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {

		return _moveFolder(orgId, userId, sourceFolder, destFolder);
	}

	
	public String parseText(InputStream file, String fileExt) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<SearchResult> search(long orgId, long userId, String query,
			int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean updateFile(long orgId, long userId, CLFile file) {

		return _updateFile(orgId, userId, file);
	}

	
	public boolean updateFolder(long orgId, long userId, CLFolder folder) {

		return _updateFolder(orgId, userId, folder);
	}
	
	public int getCounts(long folderId) {
		return 0;
	}
	
	// list of functions that needs to be implemented by subclass
	public abstract boolean _addFolder(long orgId, long userId, CLFolder folder) throws DuplicateFolderException;
	
	public abstract boolean _moveFolder(long orgId, long userId, CLFolder sourceFolder, CLFolder destFolder);
	
	public abstract boolean _copyFolder(long orgId, long userId, CLFolder sourceFolder, CLFolder destFolder);
	
	public abstract boolean _updateFolder(long orgId, long userId, CLFolder folder);
	
	public abstract boolean _deleteFolder(long orgId, long userId, CLFolder folder, boolean isSoftDelete) throws NoSuchFolderException;
	
	public abstract boolean _addFile(long orgId, long userId, CLFile file)  throws DuplicateFileException;
	
	public abstract boolean _moveFile(long orgId, long userId, CLFile sourceFile, CLFile destFile);
	
	public abstract boolean _updateFile(long orgId, long userId, CLFile file);
	
	public abstract boolean _copyFile(long orgId, long userId, CLFile sourceFile, CLFile destFile);
	
	public abstract boolean _deleteFile(long orgId, long userId, CLFile file, boolean isSoftDelete) throws NoSuchFileException;
	
	public abstract CLFile _getFile(long orgId, long userId, CLFile file) throws NoSuchFileException;

}
