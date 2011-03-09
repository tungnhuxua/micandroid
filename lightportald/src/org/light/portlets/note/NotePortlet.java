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

package org.light.portlets.note;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class NotePortlet extends LightGenericPortlet {

   public void processAction (ActionRequest request, ActionResponse
response)
   throws PortletException, java.io.IOException {
       String action = request.getParameter("action");
       if("save".equals(action)){
           String content = request.getParameter("content");
           
           User user = this.getUser(request);
           long userId = 0;
           if(user != null) userId = user.getId();
           user = this.getVisitedUser(request);
           if(user != null) userId = user.getId();
           Note note =this.getUserService(request).getNoteByUser(userId);
           if(note != null){
               note.setContent(content);
               this.getPortalService(request).save(note);
           }
       }
       if("config".equals(action)){
           String title = request.getParameter("title");
           String barBgColor = request.getParameter("barBgColor");
           String barFontColor = request.getParameter("barFontColor");
           String contentBgColor = request.getParameter("contentBgColor");
           String textColor = request.getParameter("textColor");
           PortletObject portlet =getPortlet(request);
           if(portlet != null){
               portlet.setLabel(title);
               portlet.setBarBgColor(barBgColor);
               portlet.setBarFontColor(barFontColor);
               portlet.setContentBgColor(contentBgColor);
               this.getPortalService(request).save(portlet);
           }
           Note note =this.getUserService(request).getNoteByUser(this.getUser(request).getId());
           if(note != null){
               note.setColor(textColor);
               this.getPortalService(request).save(note);
           }
       }
       if("reset".equals(action)){
           PortletObject portlet =getPortlet(request);
           if(portlet != null){
               portlet.setBarBgColor(null);
               portlet.setBarFontColor(null);
               portlet.setContentBgColor(null);
               this.getPortalService(request).save(portlet);
           }
           Note note =this.getUserService(request).getNoteByUser(this.getUser(request).getId());
           if(note != null){
               note.setColor(null);
               this.getPortalService(request).save(note);
           }
       }
       if("edit".equals(action)){
           String title = request.getParameter("title");
           String color = request.getParameter("color");
           String bgColor = request.getParameter("bgColor");
           String widths = request.getParameter("width");
           String heights = request.getParameter("height");
           PortletObject notePortlet =getPortlet(request);
           
           User user = this.getUser(request);
           long userId = 0;
           if(user != null) userId = user.getId();
           user = this.getVisitedUser(request);
           if(user != null) userId = user.getId();
           Note note =this.getUserService(request).getNoteByUser(userId);
           if(notePortlet != null){
               notePortlet.setLabel(title);
               notePortlet.setContentBgColor(bgColor);
               this.getPortalService(request).save(notePortlet);
           }
           if(note != null){
        	   	int width = 45;
	   			int height = 7;
	   			try{
	   				width =Integer.parseInt(widths);
	   				height =Integer.parseInt(heights);
	   			}catch(Exception e){}
               note.setColor(color);
               note.setWidth(width);
               note.setHeight(height);
               this.getPortalService(request).save(note);
           }
       }
     }

    protected void doView (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {
    	User user = this.getUser(request);
    	if(this.getVisitedUser(request) != null) user = this.getVisitedUser(request);
    	if(user == null) return;
    	long userId = 0;   
        if(user != null) userId = user.getId();
        Note note = this.getUserService(request).getNoteByUser(userId);
        int len = note.getContent().length();
        int row = len / note.getWidth();
        for(int i=0;i<len;i++){
            if(note.getContent().charAt(i) == '\n') row++;
        }
        note.setHeight(row);
        PortletObject portlet =getPortlet(request);
        request.setAttribute("portlet", portlet);
        request.setAttribute("note",note);
        this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/note/notePortletView.jsp").include(request,response);

    }

    protected void doEdit (RenderRequest request, RenderResponse response)
      throws PortletException, java.io.IOException
    {
    	long userId = 0;   
    	User user = this.getUser(request);
        if(user != null) userId = user.getId();
        Note note = this.getUserService(request).getNoteByUser(userId);
        PortletObject portlet =getPortlet(request);
        request.setAttribute("portlet", portlet);
        request.setAttribute("note",note);

 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/note/notePortletEdit.jsp").include(request,response);
    }

}