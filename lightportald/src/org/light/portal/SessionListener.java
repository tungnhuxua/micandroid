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

import static org.light.portal.util.Constants._USER;
import static org.light.portal.util.Constants._STATUS_OFFLINE;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.light.portal.model.User;
import org.light.portal.user.service.UserService;
import org.light.portlets.chat.service.ChatService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author Jianmin Liu
 **/
public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void sessionDestroyed(HttpSessionEvent event) {
		User user = this.getUser(event.getSession());
		if(user != null){
			user = getUserService(event.getSession()).getUserById(user.getId());
			if(user.getId() != getUserService(event.getSession()).getOrgById(user.getOrgId()).getUserId()){
				user.setCurrentStatus(_STATUS_OFFLINE);
				this.getUserService(event.getSession()).save(user);
				this.getChatService(event.getSession()).leaveChattingByUser(user.getId());
			}
		}
	}
	protected User getUser(HttpSession session){
			return (User)session.getAttribute(_USER);
	}
	
	protected UserService getUserService(HttpSession session) {			
		return (UserService)getService(session, "userService");
	}
	
	protected ChatService getChatService(HttpSession session) {			
		return (ChatService)getService(session, "chatService");
	}
	
	private Object getService(HttpSession session, String serviceName) {
		ApplicationContext ctx = (ApplicationContext) session.getServletContext()		
        							.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);		
		return ctx.getBean(serviceName);
	}	

}