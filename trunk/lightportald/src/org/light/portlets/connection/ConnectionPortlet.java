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

package org.light.portlets.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.light.portal.Context;
import org.light.portal.cache.CacheService;
import org.light.portal.model.PortletObject;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.JSONUtil;
import org.light.portal.util.LabelBean;

/**
 * 
 * @author Jianmin Liu
 **/
public class ConnectionPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
	    String action = request.getParameter("action");
	    if("cancel".equals(action)){			
			response.setPortletMode(PortletMode.VIEW);
		}
	    else if("config".equals(action)){			
			String items = request.getParameter("items");
			PortletObject portlet =getPortlet(request);		
			if(portlet != null){				
				portlet.setShowNumber(Integer.parseInt(items));
				this.getPortalService(request).save(portlet);
			}
			String columns = request.getParameter("columns");
			String currentShowType = request.getParameter("showType");
			String currentFriendType = request.getParameter("friendType");
			PortletPreferences portletPreferences = request.getPreferences();
			portletPreferences.reset("columns");
			portletPreferences.setValue("columns",columns);	
			portletPreferences.setValue("currentShowType",currentShowType);
			if("3".equals(currentShowType)) portletPreferences.reset("currentLocation");
			portletPreferences.setValue("currentFriendType",currentFriendType);
			portletPreferences.store();
						
			response.setPortletMode(PortletMode.VIEW);
		}
	    else if("location".equals(action)){	
	    	String showLocation = request.getParameter("location");
	    	PortletPreferences portletPreferences = request.getPreferences();
	    	portletPreferences.setValue("currentLocation",showLocation);
			portletPreferences.store();
		}
	    else if("type".equals(action)){	
	    	String friendType = request.getParameter("friendType");
	    	PortletPreferences portletPreferences = request.getPreferences();
	    	portletPreferences.setValue("currentShowType","5");
			portletPreferences.setValue("currentFriendType",friendType);
			portletPreferences.store();
		}
	    else if("back".equals(action)){	
	    	PortletPreferences portletPreferences = request.getPreferences();
	    	String currentShowType = portletPreferences.getValue("currentShowType",null);
	    	if("3".equals(currentShowType)){
				portletPreferences.reset("currentLocation");
				portletPreferences.store();
	    	}else if("5".equals(currentShowType)){
	    		portletPreferences.reset("currentFriendType");
	    		portletPreferences.setValue("currentShowType","4");
				portletPreferences.store();
	    	}
		}
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		User user = this.getUser(request);
	    if(getVisitedUser(request) != null){
	    	 user = this.getVisitedUser(request);
	    	 request.setAttribute("visitMode",true);
	    }
	    if(user == null) return;

		PortletPreferences portletPreferences = request.getPreferences();
		String columns = portletPreferences.getValue("columns","1");
		String currentShowType = portletPreferences.getValue("currentShowType","0");
		String currentFriendType = portletPreferences.getValue("currentFriendType",null);
		String currentLocation = portletPreferences.getValue("currentLocation",null);
		
		String currentDesc = null;
		Collection<LabelBean> buddyLocations = null;
		Collection<ConnectionType> buddyTypes = null;
		List<Connection> buddys =null;
		if("0".equals(currentShowType))
			buddys = this.getConnectionService(request).getBuddysByUser(user.getId());
		else if("1".equals(currentShowType))
			buddys = this.getConnectionService(request).getOnlineBuddysByUser(user.getId());
		else if("2".equals(currentShowType))
			buddys = this.getConnectionService(request).getUpdatedBuddysByUser(user.getId());
		else if("3".equals(currentShowType)){			
			if(currentLocation == null){
				buddys = this.getConnectionService(request).getBuddysByUser(user.getId());
				Map<String, LabelBean> maps = new HashMap<String, LabelBean>();
				for(Connection buddy : buddys){
					String location = null;
					if(buddy.getCity() != null) location=buddy.getCity();
					if(buddy.getProvince() != null){
						if(location == null)
							location = buddy.getProvince();
						else
							location+=", "+buddy.getProvince();					
					}
					if(location == null) location ="Unknown";
					if(maps.containsKey(location)){
						LabelBean bean = maps.get(location);
						bean.setDesc(String.valueOf(Integer.parseInt(bean.getDesc())+1));
					}else{
						LabelBean bean = new LabelBean(location,"1");
						maps.put(location, bean);
					}				
				}
				if(buddys.size() > 0){
					buddyLocations = maps.values();
					buddys = null;
				}
			}else{
				String city=null;
				String province=null;
				if(!currentLocation.equals("Unknown")){
					city= currentLocation.substring(0, currentLocation.indexOf(","));
					province = currentLocation.substring(currentLocation.indexOf(",")+2, currentLocation.length());
				}			
				buddys = this.getConnectionService(request).getBuddysByUser(user.getId(),city,province);
				currentDesc = currentLocation+"("+buddys.size()+")";
			}
		}else if("4".equals(currentShowType)){
			buddys = this.getConnectionService(request).getBuddysByUser(user.getId());
			Map<Integer, ConnectionType> maps = new HashMap<Integer, ConnectionType>();
			for(Connection buddy : buddys){
				Integer type = buddy.getType();				
				if(maps.containsKey(type)){
					ConnectionType bean = maps.get(type);
					bean.add();
				}else{					
					ConnectionType bean = new ConnectionType(type,1);
					maps.put(type, bean);
				}				
			}
			if(buddys.size() > 0){
				buddyTypes = maps.values();
				buddys = null;
			}
		}else if("5".equals(currentShowType)){
			int type = 0;
			try{ 
				type = Integer.parseInt(currentFriendType);
			}catch(Exception e){}
			buddys = this.getConnectionService(request).getBuddysByUserAndType(user.getId(), type);
			request.setAttribute("currentDesc",ConnectionType.getTitle(type)+"("+buddys.size()+")");
		}
		
		try{
			JSONObject json = new JSONObject();
			json.put("view","connection.view");
			JSONObject conn = new JSONObject();
			conn.put("currentDesc",currentDesc);
			conn.put("buddyLocations",JSONUtil.getLabelBeanJSONArray(buddyLocations));
			conn.put("buddyTypes",JSONUtil.getConnectionTypeJSONArray(buddyTypes));
			int buddyCount =0;
			if(buddys != null) buddyCount=buddys.size();
			int showNumber = buddyCount;
			PortletObject portlet = getPortlet(request);	
			if(portlet != null && portlet.getShowNumber() > 0 && portlet.getShowNumber() < buddyCount && !request.getWindowState().equals(WindowState.MAXIMIZED)){
				showNumber = portlet.getShowNumber();
				List<Connection> showBuddys = new ArrayList<Connection>();
				for(int i=0;i<buddys.size();i++){
					if(i>=showNumber) break;
					Connection buddy = buddys.get(i);				 
					showBuddys.add(buddy);
				}
				conn.put("buddys", JSONUtil.getConnectionJSONArray(showBuddys));
				conn.put("showNumber", showNumber);
			}else{
				conn.put("buddys", JSONUtil.getConnectionJSONArray(buddys));
				conn.put("showNumber", buddyCount);
			}
			if(request.getWindowState().equals(WindowState.MAXIMIZED))
				conn.put("columnNumber",10);
			else
				conn.put("columnNumber",Integer.parseInt(columns));
			conn.put("currentShowType",currentShowType);
			conn.put("currentFriendType",currentFriendType);			
			conn.put("buddyCount", buddyCount);		
			
			json.put("conn",conn);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			throw new PortletException(e);			
		}
	 }	
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {							
		int showNumber = 0;
		PortletObject portlet =getPortlet(request);	
		if(portlet != null && portlet.getShowNumber() > 0)
			showNumber = portlet.getShowNumber();
		PortletPreferences portletPreferences = request.getPreferences();
		String columns = portletPreferences.getValue("columns","1");
		String currentShowType = portletPreferences.getValue("currentShowType","0");
		String currentFriendType = portletPreferences.getValue("currentFriendType","0");
		
		try{
			 JSONObject json = new JSONObject();
			 json.put("view","connection.edit");
			 JSONObject conn = new JSONObject();
			 conn.put("showNumber", showNumber);
			 conn.put("columnNumber",Integer.parseInt(columns));
			 conn.put("currentShowType",currentShowType);
			 conn.put("currentFriendType",currentFriendType);						
			 json.put("conn",conn);
			 response.getWriter().write(json.toString());
		}catch(Exception e){
			throw new PortletException(e);			
		}
	 }	
	 
	 protected void doHelp (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {  		 
		 try{
			 JSONObject json = new JSONObject();
			 json.put("view","connection.help");
			 response.getWriter().write(json.toString());
		 }catch(Exception e){
			throw new PortletException(e);			
		 }
	 }
	 
	 protected void doHeader (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {
		 User user = this.getUser(request);
		 if(getVisitedUser(request) != null)
			 user = this.getVisitedUser(request);
		 if(user != null){
			 List<Connection> connections = this.getConnectionService(request).getOnlineBuddysByUser(user.getId());
			 Context.getInstance().getCacheService(request).setList(Connection.class,user.getId()+CacheService.LAST_ONLINE,connections);
			 int count = getConnectionService(request).getBuddyCount(user.getId());
			 Context.getInstance().getCacheService(request).setObject(Connection.class,user.getId()+CacheService.LAST_COUNT,count);
			 if(count > 0){
				try{
					 JSONObject json = new JSONObject();
					 json.put("id",request.getParameter("responseId"));
					 json.put("suffix",count);
					 response.getWriter().write(json.toString());
				}catch(Exception e){
					throw new PortletException(e);			
				}
			}				              			 
		 }
	 }
	 
	 public Object doNotification(HttpServletRequest request)	
	 {
		 JSONObject json = new JSONObject();
		 try{
			 User user = Context.getInstance().getUser(request);
			 if(Context.getInstance().getVisitedUser(request) != null)
				 user = Context.getInstance().getVisitedUser(request);
			 if(user != null){
				 String lkey = user.getId()+CacheService.LAST_COUNT;
				 Integer lcount = (Integer)Context.getInstance().getCacheService(request).getObject(Connection.class,lkey);
				 int count = Context.getInstance().getConnectionService(request).getBuddyCount(user.getId());
				 if(lcount != null && lcount.intValue() != count){
					 Context.getInstance().getCacheService(request).setObject(Connection.class,user.getId()+CacheService.LAST_COUNT,count);
					 json.put("refresh",1);
				 }
				 if(json.length() == 0){
					 List<Connection> lconnections = (List<Connection>)Context.getInstance().getCacheService(request).getList(Connection.class,user.getId()+CacheService.LAST_ONLINE);
					 List<Connection> connections = Context.getInstance().getConnectionService(request).getOnlineBuddysByUser(user.getId());
					 if(lconnections != null && lconnections.size() != connections.size()){
						 Context.getInstance().getCacheService(request).setList(Connection.class,user.getId()+CacheService.LAST_ONLINE,connections);
						 json.put("refresh",1);
					 }	
				 }
			 }
		 }catch(Exception e){}
		 return json;
	 }
}