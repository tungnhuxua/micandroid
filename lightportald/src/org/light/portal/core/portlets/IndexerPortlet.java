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

package org.light.portal.core.portlets;

import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.core.PortalContextFactory;
import org.light.portal.model.UserPicture;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.HTMLUtil;
import org.light.portal.util.ImageUtil;
import org.light.portal.util.LabelBean;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;
import org.light.portlets.blog.Blog;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;

/**
 * 
 * @author Jianmin Liu
 **/
public class IndexerPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {	
		 String action = request.getParameter("action");
		 if("reIndex".equals(action)){
			 String type = request.getParameter("type");
			 if(!type.equals("all")){	
				 try{
					 this.getUserService(request).reIndex(Class.forName(type),OrganizationThreadLocal.getOrganizationId());
				 }catch(Exception e){
					 throw new RuntimeException(e);
				 }
			 }else{
				 this.getUserService(request).reIndex(OrganizationThreadLocal.getOrganizationId());
			 }
			 List<LabelBean> beans = (List<LabelBean>)request.getPortletSession().getAttribute("searchTypes",PortletSession.APPLICATION_SCOPE);
			 for(LabelBean bean : beans){
				   if(bean.getId().equals(type))
					   bean.setDefaulted(true);
				   else
					   bean.setDefaulted(false);
			   }
			 request.setAttribute("success", "ReIndex has been successfully finished.");
		 }else if("reSize".equals(action)){
			 List<UserPicture> pictures = this.getUserService(request).getAllPictures(this.getUser(request).getOrgId());
			 for(UserPicture picture : pictures){
				 try{
					 System.out.println(String.format("System is resizing picture %s",getPathPrefix()+picture.getPictureUrl()));
					 File from_file=new File(getPathPrefix()+picture.getPictureUrl());
					 BufferedImage image=ImageIO.read(from_file);	
					 int width = image.getWidth();
					 int height= image.getHeight();									
					 ImageUtil.reSize(image,width,height,getPathPrefix()+picture.getPictureUrl());
					 int maxWidth = ImageUtil.getMaxWidth(width,height);
					 int maxHeight = ImageUtil.getMaxHeight(width,height);
					 picture.setPictureWidth(maxWidth);
					 picture.setPictureHeight(maxHeight);
					 this.getPortalService(request).save(picture);
				 }catch(Exception e){
					 //ignore reading image meta data exception
					 e.printStackTrace();
				 }
			 }
			 request.setAttribute("success", "All Pictures have been successfully resized.");
		 }else if("reBlog".equals(action)){
			List<Blog> blogs = this.getBlogService(request).getBlogs(OrganizationThreadLocal.getOrganizationId());
			for(Blog blog : blogs){
				blog.setTitle(HTMLUtil.disableScripts(blog.getTitle()));
				blog.setSummary(HTMLUtil.disableScripts(blog.getSummary()));
				blog.setContent(HTMLUtil.disableScripts(blog.getContent()));
				this.getPortalService(request).save(blog);
				try{
					Thread.sleep(100);
				}catch(Exception e){}
			}
			 request.setAttribute("success", "ReBlog has been successfully finished.");
		 }
	 }
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 initSearch(request);
		 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/indexer.jsp").include(request,response);
	 }
	 
	 private String getPathPrefix(){
		return _FILE_PATH+OrganizationThreadLocal.getOrganizationId();
	}
 
	 private void initSearch(RenderRequest request){
			if(request.getPortletSession().getAttribute("searchTypes",PortletSession.APPLICATION_SCOPE) == null){
				List<LabelBean> types = new LinkedList<LabelBean>();
				types.add(new LabelBean("all","All"));
				String value=PropUtil.getString("portlet.search.list");
				String[] lists = value.split(";");
				int i = 0;
				for(String list : lists){
					String[] unit = list.split(",");							
					if(unit.length >= 4){
						if(i == 0)
							types.add(new LabelBean(unit[0],PortalContextFactory.getPortalContext().getMessageByKey(unit[3]),true));
						else
							types.add(new LabelBean(unit[0],PortalContextFactory.getPortalContext().getMessageByKey(unit[3])));
					}
					i++;
				}
				request.getPortletSession().setAttribute("searchTypes",types,PortletSession.APPLICATION_SCOPE);
			}
		}

}
