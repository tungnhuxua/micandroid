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

package org.light.portal.portlet.contentlibrary.service;

import java.util.List;

import org.light.portal.portlet.contentlibrary.entity.CLFile;
import org.light.portal.portlet.contentlibrary.entity.CLFolder;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFileException;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFolderException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFileException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFolderException;

public interface ContentLibraryService {
	public boolean addFolder(long orgId, long userId, CLFolder folder) throws DuplicateFolderException;
	
	public boolean updateFolder(long orgId, long userId, CLFolder folder);
	
	public boolean copyFolder(long orgId, long userId, CLFolder sourceFolder, CLFolder destFolder);
	
	public boolean moveFolder(long orgId, long userId, CLFolder sourceFolder, CLFolder destFolder);
	
	public boolean deleteFolder(long orgId, long userId, CLFolder folder, boolean isSoftDelete) throws NoSuchFolderException;
	
	public List<CLFolder> getSubFoldersByParentId(long orgId, long userId, long parentFolderId, int start, int end);
	
	public List<CLFile> getFilesByFolderId(long orgId, long userId, long folderId, int start, int end);
	
	public boolean addFile(long orgId, long userId, CLFile file)  throws DuplicateFileException;
	
	public boolean updateFile(long orgId, long userId, CLFile file);
	
	public boolean copyFile(long orgId, long userId, CLFile sourceFile, CLFile destFile);
	
	public boolean moveFile(long orgId, long userId, CLFile sourceFile, CLFile destFile);
	
	public boolean deleteFile(long orgId, long userId, CLFile file, boolean isSoftDelete) throws NoSuchFileException;
	
	public CLFile getFile(long orgId, long userId, CLFile file) throws NoSuchFileException;
	
	public int getCounts(long folderId);
	
	public String getTreeSource(long orgId, long userId, long parentFolderId);
}
