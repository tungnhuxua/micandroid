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

package org.light.portal.util;

import static org.light.portal.util.Constants._FILE_PATH;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 
 * @author Jianmin Liu
 **/

public class FileUtil {

	public static String saveFile(byte[] dataBytes, String contentType, String dir) throws Exception{
		String file = new String(dataBytes,"Cp1250");
		String fileName = new String(dataBytes,"UTF-8");
		String fileSaveName = fileName.substring(file.indexOf("filename=\"") + 10);
		fileSaveName = fileSaveName.substring(0, fileSaveName.indexOf("\n"));
		fileSaveName = fileSaveName.substring(fileSaveName.lastIndexOf("\\") + 1,fileSaveName.indexOf("\""));	
		int lastIndex = contentType.lastIndexOf("=");
		String boundary = contentType.substring(lastIndex + 1,contentType.length());
		int pos;
		pos = file.indexOf("filename=\"");
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;
		pos = file.indexOf("\n", pos) + 1;			
		int boundaryLocation = file.indexOf(boundary, pos) - 4;
		int startPos = ((file.substring(0, pos)).getBytes("Cp1250")).length;
		int endPos = ((file.substring(0, boundaryLocation)).getBytes("Cp1250")).length;		
		(new File(dir)).mkdirs();
		FileOutputStream fileOut = new FileOutputStream(new File(dir+fileSaveName));
		fileOut.write(dataBytes, startPos, (endPos - startPos));
		fileOut.flush();
		fileOut.close();
		return fileSaveName;
	}
	
	public static boolean deleteFile(String fileName,long orgId){
		if(fileName.toLowerCase().startsWith("http")) return true;
		return (new File(getPathPrefix(orgId)+fileName)).delete();
	}
	
	public static String getPathPrefix(long orgId){
		return _FILE_PATH+orgId;
	}
	
	public static String getPath(String root,long id, String type, String prefix){
		String separator = System.getProperty("file.separator");
		return root+OrganizationThreadLocal.getOrganizationId()+separator+type+separator+id+separator+prefix+separator;
	}
	
}
