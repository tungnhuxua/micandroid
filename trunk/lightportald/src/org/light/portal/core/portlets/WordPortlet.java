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

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.NotKeyword;
import org.light.portal.model.NotWord;
import org.light.portal.portlet.core.impl.LightGenericPortlet;

/**
 * 
 * @author Jianmin Liu
 **/
public class WordPortlet extends LightGenericPortlet {

    public void processAction (ActionRequest request, ActionResponse response)
       throws PortletException, java.io.IOException {
        String action = request.getParameter("action");
        if("saveNotKeywords".equals(action)){
        	String words = request.getParameter("words");
    		String[] keywords = words.split(",");
    		for(int i=0;i<keywords.length;i++){
    			if(keywords[i].trim().length() > 0){				
    				NotKeyword word = this.getUserService(request).getNotKeyword(keywords[i]);
    				if(word == null){
    					word = new NotKeyword(keywords[i]);
    					this.getPortalService(request).save(word);
    				}								
    			}
    		}
        }
        else if("saveNotWords".equals(action)){
        	String words = request.getParameter("words");
    		String[] keywords = words.split(" ");
    		for(int i=0;i<keywords.length;i++){
    			if(keywords[i].trim().length() > 0){				
    				NotWord word = this.getUserService(request).getNotWord(keywords[i]);
    				if(word == null){
    					word = new NotWord(keywords[i]);
    					this.getPortalService(request).save(word);
    				}								
    			}
    		}
        }
        else if("deleteNotKeywords".equals(action)){
        	String[] keywords = request.getParameterValues("keywords");
        	if(keywords != null){
	    		for(int i=0;i<keywords.length;i++){
	    			if(keywords[i].trim().length() > 0){
	    				NotKeyword word = this.getUserService(request).getNotKeyword(keywords[i]);
	    				this.getPortalService(request).delete(word);
	    			}
	    		}
        	}
        }
        else if("deleteNotWords".equals(action)){
        	String[] words = request.getParameterValues("words");
        	if(words != null){
	    		for(int i=0;i<words.length;i++){
	    			if(words[i].trim().length() > 0){
	    				NotWord word = this.getUserService(request).getNotWord(words[i]);
	    				this.getPortalService(request).delete(word);
	    			}
	    		}
        	}
        }
    }
    
    protected void doView (RenderRequest request, RenderResponse response)
    throws PortletException, java.io.IOException
  {
    	List<NotKeyword> notKeywords = this.getUserService(request).getNotKeywords();
    	List<NotWord> notWords = this.getUserService(request).getNotWords();
    	request.setAttribute("notKeywords",notKeywords);
    	request.setAttribute("notKeywordsTotal",notKeywords.size());
    	request.setAttribute("notWords",notWords);
    	request.setAttribute("notWordsTotal",notWords.size());
    	this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/core/word.jsp").include(request,response);
  }

}
