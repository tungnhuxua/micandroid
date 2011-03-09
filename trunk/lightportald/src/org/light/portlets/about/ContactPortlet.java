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

package org.light.portlets.about;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portlets.feedback.Feedback;

/**
 * 
 * @author Jianmin Liu
 **/
public class ContactPortlet extends LightGenericPortlet {

    public void processAction (ActionRequest request, ActionResponse
response)
       throws PortletException, java.io.IOException {
     String action = request.getParameter("action");
     if("save".equals(action)){
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String email = request.getParameter("email");
        if(subject == null || subject.length() <= 0 ){
         request.setAttribute("error","Feedback's subject is required field.");
         return;
        }
        if(content == null || content.length() <= 0 ){
            request.setAttribute("error","Feedback's content is required field.");
            return;
        }
        if(this.getUser(request) != null)
            email = this.getUser(request).getEmail();
        Feedback feedback = new Feedback(this.getUser(request).getId(),subject,content,email,OrganizationThreadLocal.getOrganizationId());
        this.getPortalService(request).save(feedback);
        request.setAttribute("success","Your feedback has been saved successfully.");
     }
     else{
            String showHtmlEditor = request.getParameter("htmlEditor");
            if(showHtmlEditor != null)
                request.setAttribute("showHtmlEditor",true);
            String subject = request.getParameter("subject");
            if(subject != null){
                request.setAttribute("subject",subject);
            }
            String email = request.getParameter("email");
            if(email != null){
                request.setAttribute("email",email);
            }
            String content = request.getParameter("content");
            if(content != null){
                request.setAttribute("content",content);
            }
       }
      }

     protected void doView (RenderRequest request, RenderResponse response)
       throws PortletException, java.io.IOException
     {


    	 this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/about/contactPortletView.jsp").include(request,response);

     }

}
