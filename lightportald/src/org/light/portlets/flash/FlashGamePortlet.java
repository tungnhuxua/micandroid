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

package org.light.portlets.flash;

import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.light.portal.model.FlashGame;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.OrganizationThreadLocal;

/**
 * 
 * @author Jianmin Liu
 **/
public class FlashGamePortlet extends LightGenericPortlet {
	
	public void processAction (ActionRequest request, ActionResponse response) 
    throws PortletException, java.io.IOException {
	 String action = request.getParameter("action");
	 if("add".equals(action)){	
  	   String link = request.getParameter("link");
  	   String title = request.getParameter("title");
  	   String desc = request.getParameter("desc");
  	   String instructions = request.getParameter("instructions");
  	   String tag = request.getParameter("tag");
  	   int width = Integer.parseInt(request.getParameter("width"));
  	   int height = Integer.parseInt(request.getParameter("height"));  	   
  	   if( link == null || link.length() <= 0 || title== null || title.length() <= 0){
		   request.setAttribute("error","Please input all required fields.");
		   response.setPortletMode(PortletMode.EDIT);
		   return;
	   }
  	   FlashGame game = new FlashGame(link,title,tag,desc,instructions,width,height,this.getUser(request).getId(),OrganizationThreadLocal.getOrganizationId());
  	   this.getPortalService(request).save(game);
	 }
	 if("modify".equals(action)){	
		   String id = request.getParameter("id");
	  	   String link = request.getParameter("link");
	  	   String title = request.getParameter("title");
	  	   String desc = request.getParameter("desc");
	  	   String instructions = request.getParameter("instructions");
	  	   String tag = request.getParameter("tag");
	  	   int width,height = 0;
	  	   try{
		  	   width = Integer.parseInt(request.getParameter("width"));
		  	   height = Integer.parseInt(request.getParameter("height"));  	   
	  	   }catch(Exception e){
	  		   request.setAttribute("error","Please input width and height as number.");
	  		   response.setPortletMode(PortletMode.EDIT);
		  	   if(id != null){
		 			FlashGame game = (FlashGame)this.getPortalService(request).getEntityById(FlashGame.class,Long.parseLong(id));
		 			request.setAttribute("entity",game);			
		 		}
			   return;
	  	   }
	  	   if( link == null || link.length() <= 0 || title== null || title.length() <= 0){
			   request.setAttribute("error","Please input all required fields.");
			   response.setPortletMode(PortletMode.EDIT);
			   if(id != null){
		 			FlashGame game = (FlashGame)this.getPortalService(request).getEntityById(FlashGame.class,Long.parseLong(id));
		 			request.setAttribute("entity",game);			
		 		}
			   return;
		   }
	  	  if(id != null){
				FlashGame game = (FlashGame)this.getPortalService(request).getEntityById(FlashGame.class,Long.parseLong(id));
				game.setLink(link);
				game.setTitle(title);
				game.setDesc(desc);
				game.setInstructions(instructions);
				game.setTag(tag);
				game.setWidth(width);
				game.setHeight(height);
				this.getPortalService(request).save(game);
	  	  }
	   }
	}
	
	protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		List<FlashGame> games = this.getPortletService(request).getFlashGames(OrganizationThreadLocal.getOrganizationId());
		request.setAttribute("games",games);
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/flash/flashGameView.jsp").include(request,response);
		
	 }
	
	protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		String id = request.getParameter("parameter");
		if(id != null){
			FlashGame game = (FlashGame)this.getPortalService(request).getEntityById(FlashGame.class,Long.parseLong(id));
			request.setAttribute("entity",game);			
		}
		this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/flash/flashGameEdit.jsp").include(request,response);
		
	 }
	
	protected void doConfig (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {			
		String id = request.getParameter("id");
		if(id != null){
			FlashGame game = (FlashGame)this.getPortalService(request).getEntityById(FlashGame.class,Integer.parseInt(id));
			if(game != null){
				request.setAttribute("game",game);
				this.getPortletContext().getRequestDispatcher(_PORTLET_JSP_PATH+"/flash/flashGameStart.jsp").include(request,response);
			}else
				doView(request,response);	
		}
		else
			doView(request,response);
	 }
}
