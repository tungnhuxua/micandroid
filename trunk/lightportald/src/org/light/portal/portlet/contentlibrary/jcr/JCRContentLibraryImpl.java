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

package org.light.portal.portlet.contentlibrary.jcr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
import javax.jcr.version.Version;
import javax.naming.directory.SearchResult;

import org.apache.jackrabbit.core.TransientRepository;
import org.light.portal.portlet.contentlibrary.ContentLibraryImpl;
import org.light.portal.portlet.contentlibrary.entity.CLFile;
import org.light.portal.portlet.contentlibrary.entity.CLFolder;
import org.light.portal.portlet.contentlibrary.entity.CLMimeType;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFileException;
import org.light.portal.portlet.contentlibrary.exception.DuplicateFolderException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFileException;
import org.light.portal.portlet.contentlibrary.exception.NoSuchFolderException;


/**
 *   root
 *     |_ orgId
 *          |_ userId
 *                |_ folder(s) / file(s)
 *                           |_ ...
 *                           
 * @author stanley 11-8-2008
 *
 */
public class JCRContentLibraryImpl extends ContentLibraryImpl {
	
	@Override
	public boolean _addFile(long orgId, long userId, CLFile file) throws DuplicateFileException{
		
		Session session = null;

		try {
			//dumpRoot();
			
			Repository repository = new TransientRepository();

		     // Login to the default workspace
		     //session = repository.login();
			session = repository.login(
		            new SimpleCredentials("username", "password".toCharArray()));

			Node repositoryNode = getRootNode(session, orgId, userId);

			if (repositoryNode.hasNode(file.getRelPath() + "/" + file.getName())) {
				throw new DuplicateFileException(file.getRelPath() + "/" + file.getName());
			}
			else {
				Node fileNode = repositoryNode.addNode(
						file.getRelPath() + "/" + file.getName(), JCRConstants.NT_FILE);

				Node contentNode = fileNode.addNode(JCRConstants.JCR_CONTENT, JCRConstants.NT_RESOURCE);

				contentNode.addMixin(JCRConstants.MIX_VERSIONABLE);
				contentNode.setProperty(JCRConstants.JCR_MIME_TYPE, getFileMimeType(file.getMimeType(), file.getName()));
				contentNode.setProperty(JCRConstants.JCR_LAST_MODIFIED, Calendar.getInstance());
				contentNode.setProperty(JCRConstants.JCR_DATA, file.getInputStream());
				
				session.save();

				Version version = contentNode.checkin();

				contentNode.getVersionHistory().addVersionLabel(
					version.getName(), String.valueOf(JCRConstants.DEFAULT_VERSION), false);
			}
		}
		catch (RepositoryException re) {
			re.printStackTrace();
			return false;
		}
		catch (IOException e) {
			e.printStackTrace();
			// log.error();
			return false;
		}
		catch (Exception ex) { // for test only
			ex.printStackTrace();
		}
		finally {
			if (session != null) {
				session.logout();
			}
		}
		
		return true;
	}
	
	private String getFileMimeType(String mimeType, String fileName) {
		if (mimeType == null) {
			int ind = fileName.indexOf(".");
			String type = fileName.substring(ind+1);
			
			mimeType = CLMimeType.getMimeTypeByExt(type);
		}
		
		return mimeType;
	}

	@Override
	public boolean _addFolder(long orgId, long userId, CLFolder folder) throws DuplicateFolderException {

		Session session = null;
		
		try {
			 Repository repository = new TransientRepository();

		     // Login to the default workspace
		     //session = repository.login();
		     session = repository.login(
			            new SimpleCredentials("username", "password".toCharArray()));

			Node repositoryNode = getRootNode(session, orgId, userId);

			if (repositoryNode.hasNode(folder.getFullPath())) {
				throw new DuplicateFolderException(folder.getFullPath());
			}
			else {
				String[] dirNameArray = folder.getFullPath().split("/");

				Node dirNode = repositoryNode;

				for (int i = 0; i < dirNameArray.length; i++) {
					if (dirNameArray[i] != null && dirNameArray[i].length() > 0) {
						if (dirNode.hasNode(dirNameArray[i])) {
							dirNode = dirNode.getNode(dirNameArray[i]);
						}
						else {
							dirNode = dirNode.addNode(
								dirNameArray[i], JCRConstants.NT_FOLDER);
						}
					}
				}

				session.save();
			}
		}
		catch (RepositoryException re) {
			// log.error();
			re.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// log.error();
			return false;
		}
		finally {
			if (session != null) {
				session.logout();
			}
		}
		
		// index folder and it's metadata
		
		return true;
	}

	@Override
	public boolean _copyFile(long orgId, long userId, CLFile sourceFile, CLFile destFile) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean _updateFolder(long orgId, long userId, CLFolder folder) {
		return false;
	}

	@Override
	public boolean _copyFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _deleteFile(long orgId, long userId, CLFile file, boolean isSoftDelete) 
		throws NoSuchFileException {
		Session session = null;
		
		try {
			 Repository repository = new TransientRepository();

		     // Login to the default workspace
		     //session = repository.login();
		     session = repository.login(
			            new SimpleCredentials("username", "password".toCharArray()));

			Node repositoryNode = getRootNode(session, orgId, userId);
			
			if (! repositoryNode.hasNode(file.getRelPath() + "/" + file.getName())) {
				throw new NoSuchFileException(file.getRelPath() + "/" + file.getName());
			}
			else {
				Node folderNode = repositoryNode.getNode(file.getRelPath() + "/" + file.getName());
				folderNode.remove();
				
				session.save();
			}
		}
		catch (RepositoryException re) {
			// log.error();
			re.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// log.error();
			return false;
		}
		finally {
			if (session != null) {
				session.logout();
			}
		}
		
		return true;
	}

	@Override
	public boolean _deleteFolder(long orgId, long userId, CLFolder folder, boolean isSoftDelete) throws NoSuchFolderException{
		Session session = null;
		
		try {
			 Repository repository = new TransientRepository();

		     // Login to the default workspace
		     //session = repository.login();
		     session = repository.login(
			            new SimpleCredentials("username", "password".toCharArray()));

			Node repositoryNode = getRootNode(session, orgId, userId);
			
			if (! repositoryNode.hasNode(folder.getFullPath())) {
				throw new NoSuchFolderException(folder.getFullPath());
			}
			else {
				Node folderNode = repositoryNode.getNode(folder.getFullPath());
				folderNode.remove();
				
				session.save();
			}
		}
		catch (RepositoryException re) {
			// log.error();
			re.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// log.error();
			return false;
		}
		finally {
			if (session != null) {
				session.logout();
			}
		}
		
		return true;
	}

	@Override
	public CLFile _getFile(long orgId, long userId, CLFile file) throws NoSuchFileException{
		try {
			Repository repository = new TransientRepository();
	
		     // Login to the default workspace
		     //session = repository.login();
			Session session = repository.login(
		            new SimpleCredentials("username", "password".toCharArray()));
	
			Node rootNode = getRootNode(session, orgId, userId);
			Node node = getFolderNode(rootNode, file.getRelPath() + "/" + file.getName());
					
			System.out.println(node.getPath());

	        // Then output the properties
	        PropertyIterator properties = node.getProperties();
	        while (properties.hasNext()) {
	            Property property = properties.nextProperty();
	            if (property.getDefinition().isMultiple()) {
	                // A multi-valued property, print all values
	                Value[] values = property.getValues();
	                for (int i = 0; i < values.length; i++) {
	                    System.out.println(
	                        property.getPath() + " = " + values[i].getString());
	                }
	            } else {
	                // A single-valued property
	                System.out.println(
	                    property.getPath() + " = " + property.getString());
	            }
	        }
	        
	        NodeIterator nodes = node.getNodes();
	        while (nodes.hasNext()) {
	        	getContent(nodes.nextNode(), file);
	        }
		}
		catch (Exception e) {
			// log.error()
			e.printStackTrace();
		}
		return file;
	}

	
	@Override
	public boolean indexFile(long orgId, long userId, CLFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean indexFolder(long orgId, long userId, CLFolder folder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _moveFile(long orgId, long userId, CLFile sourceFile, CLFile destFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean _moveFolder(long orgId, long userId, CLFolder sourceFolder,
			CLFolder destFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String parseText(InputStream file, String fileExt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SearchResult> search(long orgId, long userId, String query, int start,
			int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean _updateFile(long orgId, long userId, CLFile file) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Node getRootNode(Session session, long orgId, long userId)
		throws RepositoryException {
	
		Node companyNode = getFolderNode(session.getRootNode(), orgId);
	
		return getFolderNode(companyNode, userId);
	}	
	
	private Node getFolderNode(Node node, long name)
		throws RepositoryException {
	
		return getFolderNode(node, String.valueOf(name));
	}

	private Node getFolderNode(Node node, String name)
		throws RepositoryException {
	
		Node folderNode = null;
	
		if (node.hasNode(name)) {
			folderNode = node.getNode(name);
		}
		else {
			folderNode = node.addNode(name, JCRConstants.NT_FOLDER);
		}
	
		return folderNode;
	}
	
	private void getContent(Node node, CLFile file) throws RepositoryException {
		
		// First output the node path
        System.out.println(node.getPath());
        // Skip the virtual (and large!) jcr:system subtree
        if (node.getName().equals(JCRConstants.JCR_SYSTEM)) {
            return;
        }

        // Then output the properties
        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            if (property.getDefinition().isMultiple()) {
                // A multi-valued property, print all values
                Value[] values = property.getValues();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(
                        property.getPath() + " = " + values[i].getString());
                }
            } else {
                // A single-valued property
            	if (property.getDefinition().getName().equals(JCRConstants.JCR_DATA)) {
            		file.setInputStream(property.getStream());
            	}
            	else if (property.getDefinition().getName().equals(JCRConstants.JCR_LAST_MODIFIED)) {
            		file.setLastUpdated(property.getDate().getTime());
            	}
            	else if (property.getDefinition().getName().equals(JCRConstants.JCR_VERSION_HISTORY)) {
            		file.setVersion(Integer.valueOf(property.getString()));
            	}
            	else {
	                System.out.println(
	                    property.getPath() + " = " + property.getString());
            	}
            }
        }
    }
	
	////// for testing only //////
	public void dumpRoot()  throws Exception {
		Repository repository = new TransientRepository();

	     // Login to the default workspace
	     //session = repository.login();
		Session session = repository.login(
	            new SimpleCredentials("username", "password".toCharArray()));

		Node repositoryNode = getRootNode(session, 123, 777);
		
		dump(repositoryNode);
	}
	
	 /** Recursively outputs the contents of the given node. */
    private void dump(Node node) throws RepositoryException {
        // First output the node path
        System.out.println(node.getPath());
        // Skip the virtual (and large!) jcr:system subtree
        if (node.getName().equals("jcr:system")) {
            return;
        }

        // Then output the properties
        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            if (property.getDefinition().isMultiple()) {
                // A multi-valued property, print all values
                Value[] values = property.getValues();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(
                        property.getPath() + " = " + values[i].getString());
                }
            } else {
                // A single-valued property
                System.out.println(
                    property.getPath() + " = " + property.getString());
            }
        }

        // Finally output all the child nodes recursively
        NodeIterator nodes = node.getNodes();
        while (nodes.hasNext()) {
            dump(nodes.nextNode());
        }
    }

	
	public static final void main(String[] args) {
		JCRContentLibraryImpl cl = new JCRContentLibraryImpl();
		
		try {
//			CLFolder folder = new CLFolder();
//			folder.setFullPath("a/b");
//			
//			cl.addFolder(123, 777, folder);
//			
//			CLFile file = new CLFile();
//			file.setRelPath(folder.getFullPath());
//			file.setMimeType(CLFile.MIME_PDF);
//			file.setName("t2.pdf");
//			file.setUploadedPath("C:/Users/stanley/Documents/avt-9.pdf");
//			
//			cl.addFile(123, 777, file);
			
			// display everything from user root
			cl.dumpRoot();
			
			
			// test get file
//			CLFile file2 = new CLFile();
//			file2.setRelPath("a/b");
//			file2.setName("t2.pdf");
//			CLFile res = cl.getFile(123, 777, file2);

			// test delete folder
//			CLFolder folder = new CLFolder();
//			folder.setFullPath("a/c");
//			cl.addFolder(123, 777, folder);
//			cl.deleteFolder(123, 777, folder, false);
			
			// test delete file
//			CLFile file = new CLFile();
//			file.setRelPath("a/b");
//			file.setMimeType(CLFile.MIME_PDF);
//			file.setName("t3.pdf");
////			file.setUploadedPath("C:/Users/stanley/Documents/avt-9.pdf");
//			//cl.addFile(123, 777, file);
//			cl.deleteFile(123, 777, file, false);
			
			System.out.println("Exit");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	


}
