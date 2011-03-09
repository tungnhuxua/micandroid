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

package org.light.portal;

import static org.light.portal.util.Constants._CHARSET_UTF;
import static org.light.portal.util.Constants._PORTAL_INDEX;
import static org.light.portal.util.Constants._GROUP_PATH;
import static org.light.portal.util.Constants._SITE_MAP_PATH;
import static org.light.portal.util.Constants._USER_PATH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Calendar;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portal.util.FileUtil;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.group.Group;
import org.light.portlets.group.service.GroupService;

/**
 * 
 * @author Jianmin Liu
 **/
public class FileDispatcherServlet extends HttpServlet{
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	process(request,response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
    }
	
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String uri = request.getRequestURI();
	   Calendar current = Calendar.getInstance();
	   current.add(Calendar.YEAR,10);
	   response.setDateHeader("Expires",current.getTime().getTime());
	   if(uri.indexOf("/"+_USER_PATH+"/") >= 0 && uri.indexOf(".lp") < 0){
		   int index = uri.indexOf("/"+_USER_PATH+"/");
		   String path = uri.substring(index);
		   if(path != null) path = URLDecoder.decode(path,_CHARSET_UTF);
		   String path2 = path.substring(_USER_PATH.length()+2);
		   index = path2.indexOf("/");
		   String userId = path2.substring(0,index);
		   long id = 0L;
		   try{
			   id = Long.parseLong(userId);
		   }catch(Exception e){}
		   User user = this.getUser(request);
		   if(user == null || id != user.getId())
			   user = this.getUserService(request).getUserById(id);
		   if(user != null){
			   if(user.getNoPicForward() == 1 && this.getUser(request) == null && this.getVisitedUser(request) == null){					   
				   response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
				   return;
			   }else{	
				   File file = new File(getPathPrefix()+path);
			   	   //Set content type
				   String mime;
				   if(path.indexOf("images") > 0)
					  mime = "image/gif";
				   else if(path.indexOf("musics") > 0)
					  mime = "audio";
				   else
					   mime = new MimetypesFileTypeMap().getContentType(file); 
				   response.setContentType(mime);
			    
			        // Set content size			        
			        response.setContentLength((int)file.length());
			    
			        // Open the file and output streams
			        FileInputStream in = new FileInputStream(file);
			        OutputStream out = response.getOutputStream();
			    
			        // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			            out.write(buf, 0, count);
			        }
			        in.close();
			        out.close();
				    return;
			   }
				   
		   }			
		}			
	   if(uri.indexOf("/"+_GROUP_PATH+"/") >= 0 && uri.indexOf(".lp") < 0){
		   int index = uri.indexOf("/"+_GROUP_PATH+"/");
		   String path = uri.substring(index);
		   if(path != null) path = URLDecoder.decode(path,_CHARSET_UTF);
		   String path2 = path.substring(_GROUP_PATH.length()+2);
		   index = path2.indexOf("/");
		   String groupId = path2.substring(0,index);
		   long id = 0L;
		   try{
			   id = Long.parseLong(groupId);
		   }catch(Exception e){}
		   Group group = this.getGroupService(request).getGroupById(id);
		   if(group != null){
			   if(group.getNoPicForward() == 1 && this.getUser(request) == null && this.getVisitedGroup(request) == null){
				   response.sendRedirect(request.getContextPath()+_PORTAL_INDEX);
				   return;
			   }else{					   
				   File file = new File(getPathPrefix()+path);
				   // Set content type
				   String mime;
				   if(path.indexOf("images") > 0)
					  mime = "Image";
				   else if(path.indexOf("musics") > 0)
					  mime = "Audio";
				   else
					   mime = new MimetypesFileTypeMap().getContentType(file); 
				   response.setContentType(mime);
			    
			        // Set content size			        
			        response.setContentLength((int)file.length());
			    
			        // Open the file and output streams
			        FileInputStream in = new FileInputStream(file);
			        OutputStream out = response.getOutputStream();
			    
			        // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			            out.write(buf, 0, count);
			        }
			        in.close();
			        out.close();			
				   return;
			   }
				   
		   }			
		}
	    if(uri.indexOf("/"+_SITE_MAP_PATH+"/") >= 0 && uri.indexOf(".lp") < 0){
	    	int index = uri.indexOf("/"+_SITE_MAP_PATH+"/");
	    	String path = uri.substring(index+_SITE_MAP_PATH.length()+2);
	    	if(path != null) path = URLDecoder.decode(path,_CHARSET_UTF);
		 
	    	File file = new File(getPathPrefix()+System.getProperty("file.separator")+_SITE_MAP_PATH+System.getProperty("file.separator")+path);
	    	// Set content type
	    	String mime = new MimetypesFileTypeMap().getContentType(file); 
	    	response.setContentType(mime);
	    
	        // Set content size			        
	        response.setContentLength((int)file.length());
	    
	        // Open the file and output streams
	        FileInputStream in = new FileInputStream(file);
	        OutputStream out = response.getOutputStream();
	    
	        // Copy the contents of the file to the output stream
	        byte[] buf = new byte[1024];
	        int count = 0;
	        while ((count = in.read(buf)) >= 0) {
	            out.write(buf, 0, count);
	        }
	        in.close();
	        out.close();	
	        return;
	    }
	    if(uri.indexOf("/robots.txt") >= 0 && uri.indexOf(".lp") < 0){
	    	int index = uri.indexOf("/"+_SITE_MAP_PATH+"/");
	    	String path = uri.substring(index+_SITE_MAP_PATH.length()+2);
	    	if(path != null) path = URLDecoder.decode(path,_CHARSET_UTF);
		 
	    	File file = new File(getPathPrefix()+System.getProperty("file.separator")+"robots.txt");
	    	// Set content type
	    	String mime = new MimetypesFileTypeMap().getContentType(file); 
	    	response.setContentType(mime);
	    
	        // Set content size			        
	        response.setContentLength((int)file.length());
	    
	        // Open the file and output streams
	        FileInputStream in = new FileInputStream(file);
	        OutputStream out = response.getOutputStream();
	    
	        // Copy the contents of the file to the output stream
	        byte[] buf = new byte[1024];
	        int count = 0;
	        while ((count = in.read(buf)) >= 0) {
	            out.write(buf, 0, count);
	        }
	        in.close();
	        out.close();	
	        return;
	    }
        return;
    }
    private String getPathPrefix(){
		return FileUtil.getPathPrefix(OrganizationThreadLocal.getOrganizationId());
	}
    protected User getUser(HttpServletRequest request){
		return Context.getInstance().getUser(request);
	}
    protected User getVisitedUser(HttpServletRequest request){		
    	return Context.getInstance().getVisitedUser(request);
	}	
	
	protected Group getVisitedGroup(HttpServletRequest request){		
		return Context.getInstance().getVisitedGroup(request);
	}
    protected UserService getUserService(HttpServletRequest request) {			
		return Context.getInstance().getUserService(request);
	}
    
    protected GroupService getGroupService(HttpServletRequest request) {			
		return Context.getInstance().getGroupService(request);
	}	
}
