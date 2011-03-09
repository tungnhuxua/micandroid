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

import static org.light.portal.util.Constants._CATEGORY;
import static org.light.portal.util.Constants._DEFAULT;
import static org.light.portal.util.Constants._DEFAULT_ROLE;
import static org.light.portal.util.Constants._FEATURED;
import static org.light.portal.util.Constants._FEATURED_TITLE;
import static org.light.portal.util.Constants._LANGUAGE_ALL;
import static org.light.portal.util.Constants._LANGUAGE_EN;
import static org.light.portal.util.Constants._MY_FEED;
import static org.light.portal.util.Constants._MY_FEED_TITLE;
import static org.light.portal.util.Constants._PORTLET_JSP_PATH;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.light.portal.model.ContentCategory;
import org.light.portal.model.ContentSubCategory;
import org.light.portal.model.PortalTab;
import org.light.portal.model.PortletObjectRef;
import org.light.portal.model.User;
import org.light.portal.portlet.core.impl.LightGenericPortlet;
import org.light.portal.util.JSONUtil;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * 
 * @author Jianmin Liu
 **/
public class ContentPortlet extends LightGenericPortlet {
	 
	 public void processAction (ActionRequest request, ActionResponse response) 
	    throws PortletException, java.io.IOException {
		 String action = request.getParameter("action");
		 if("addFeed".equals(action)){
			 String feed = request.getParameter("feed");			 
			 if(feed != null && !"".equals(feed)){
				HttpServletRequest httpServletRequest = (HttpServletRequest)request.getAttribute("httpServletRequest");
				String proxySet = httpServletRequest.getSession().getServletContext().getInitParameter("proxySet");
				if("true".equals(proxySet)){
					String proxyHost = httpServletRequest.getSession().getServletContext().getInitParameter("proxyHost");
					String proxyPort = httpServletRequest.getSession().getServletContext().getInitParameter("proxyPort");
					System.getProperties().put( "proxySet", proxySet );
					System.getProperties().put( "proxyHost", proxyHost );
					System.getProperties().put( "proxyPort", proxyPort );
				}
				try{
					URL feedUrl = new URL(feed);
	                SyndFeedInput input = new SyndFeedInput();
	                SyndFeed rss = input.build(new XmlReader(feedUrl));
				    String imageUrl="";
				    if(rss.getImage() != null && rss.getImage().getUrl() != null)
				    	imageUrl = rss.getImage().getUrl().toString();
					String title = rss.getTitle();
					if(title == null && rss.getDescription() != null){
						String desc = rss.getDescription();
						if(desc.indexOf("-")>0){
							title = desc.substring(0,desc.indexOf("-"));
							if(title.length() > 30)
								title = title.substring(0,30);
						}else if(desc.length() > 15)
							title = desc.substring(0,15);
						else
							title = desc;
					}
					if(title == null) title = rss.getLink();										
					String feedName = String.valueOf(System.currentTimeMillis());
					String language = _LANGUAGE_EN;
					if(this.getUser(request) != null) language = this.getUser(request).getLanguage();
				    PortletObjectRef ref = new PortletObjectRef(
				    							feedName
				    						   ,"/rssPortlet.lp"
				    						   ,title
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,null
				    						   ,_MY_FEED_TITLE
				    						   ,language
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,this.getUser(request).getUserId());
				    this.getPortalService(request).save(ref);
					List<PortletObjectRef> myFeedLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("myFeedLists",PortletSession.APPLICATION_SCOPE);
					if(myFeedLists != null){
						myFeedLists.add(ref);
						request.getPortletSession().setAttribute("myFeedLists",myFeedLists,PortletSession.APPLICATION_SCOPE);
					}
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("error",e.getMessage());
				}
			}
		 }
		 else if("addFeaturedFeed".equals(action)){
			 String feed = request.getParameter("feed");			 
			 if(feed != null && !"".equals(feed)){				
				try{
					URL feedUrl = new URL(feed);
	                SyndFeedInput input = new SyndFeedInput();
	                SyndFeed rss = input.build(new XmlReader(feedUrl));
				    String imageUrl="";
				    if(rss.getImage() != null && rss.getImage().getUrl() != null)
				    	imageUrl = rss.getImage().getUrl().toString();
					String title = rss.getTitle();
					if(title == null && rss.getDescription() != null){
						String desc = rss.getDescription();
						if(desc.indexOf("-")>0){
							title = desc.substring(0,desc.indexOf("-"));
							if(title.length() > 30)
								title = title.substring(0,30);
						}else if(desc.length() > 15)
							title = desc.substring(0,15);
						else
							title = desc;
					}
					if(title == null) title = rss.getLink();					
					String feedName = String.valueOf(System.currentTimeMillis());					
				    PortletObjectRef ref = new PortletObjectRef(
				    							feedName
				    						   ,"/rssPortlet.lp"
				    						   ,title
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,null
				    						   ,_FEATURED_TITLE
				    						   ,_LANGUAGE_ALL
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,_DEFAULT_ROLE
				    						   ,this.getUser(request).getUserId());
				    this.getPortalService(request).save(ref);
					List<PortletObjectRef> featuredLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("featuredLists",PortletSession.APPLICATION_SCOPE);
					if(featuredLists != null){
						featuredLists.add(ref);
						request.getPortletSession().setAttribute("featuredLists",featuredLists,PortletSession.APPLICATION_SCOPE);
					}
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("error",e.getMessage());
				}
			}
		 }
		 else if("addCategoryFeed".equals(action)){
			 String feed = request.getParameter("feed");			 
			 if(feed != null && !"".equals(feed)){				
				try{
					URL feedUrl = new URL(feed);
	                SyndFeedInput input = new SyndFeedInput();
	                SyndFeed rss = input.build(new XmlReader(feedUrl));
				    String imageUrl="";
				    if(rss.getImage() != null && rss.getImage().getUrl() != null)
				    	imageUrl = rss.getImage().getUrl().toString();
					String title = rss.getTitle();
					if(title == null && rss.getDescription() != null){
						String desc = rss.getDescription();
						if(desc.indexOf("-")>0){
							title = desc.substring(0,desc.indexOf("-"));
							if(title.length() > 30)
								title = title.substring(0,30);
						}else if(desc.length() > 15)
							title = desc.substring(0,15);
						else
							title = desc;
					}
					if(title == null) title = rss.getLink();
					String feedName = String.valueOf(System.currentTimeMillis());
					User user = this.getUser(request);
					String tag = request.getParameter("tag");
				    PortletObjectRef ref = new PortletObjectRef(
				    							feedName
				    						   ,"/rssPortlet.lp"
				    						   ,title
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,null
				    						   ,tag
				    						   ,user.getRegion()
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,_DEFAULT_ROLE
				    						   ,this.getUser(request).getUserId());
				    this.getPortalService(request).save(ref);
				    List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
					for(ContentCategory dic : categories){
						 if(dic.isShowed()){
							 if(dic.getFeedLists() != null)
								 dic.getFeedLists().add(ref);						 
							 break;
						 }					 							 						 
					}	
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("error",e.getMessage());
				}
			}
		 }
		 else if("addSubCategoryFeed".equals(action)){
			 String feed = request.getParameter("feed");			 
			 if(feed != null && !"".equals(feed)){				
				try{
					URL feedUrl = new URL(feed);
	                SyndFeedInput input = new SyndFeedInput();
	                SyndFeed rss = input.build(new XmlReader(feedUrl));
				    String imageUrl="";
				    if(rss.getImage() != null && rss.getImage().getUrl() != null)
				    	imageUrl = rss.getImage().getUrl().toString();
					String title = rss.getTitle();
					if(title == null && rss.getDescription() != null){
						String desc = rss.getDescription();
						if(desc.indexOf("-")>0){
							title = desc.substring(0,desc.indexOf("-"));
							if(title.length() > 30)
								title = title.substring(0,30);
						}else if(desc.length() > 15)
							title = desc.substring(0,15);
						else
							title = desc;
					}
					if(title == null) title = rss.getLink();
					String feedName = String.valueOf(System.currentTimeMillis());
					User user = this.getUser(request);
					String tag = request.getParameter("tag");
					String subtag = request.getParameter("subtag");
				    PortletObjectRef ref = new PortletObjectRef(
			    								feedName
				    						   ,"/rssPortlet.lp"
				    						   ,rss.getTitle()
				    						   ,imageUrl
				    						   ,""
											   ,rss.getLink()
											   ,subtag
				    						   ,tag
				    						   ,user.getRegion()
				    						   ,1
				    						   ,1
				    						   ,0
				    						   ,1
				    						   ,1
				    						   ,1
				    						   ,null
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,6
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,0
				    						   ,"feed="+feed
				    						   ,_DEFAULT_ROLE
				    						   ,this.getUser(request).getUserId());
				    this.getPortalService(request).save(ref);
				    List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
					for(ContentCategory dic : categories){
						 if(dic.isShowed()){
							 for(ContentSubCategory sub : dic.getSubCategories()){
								 if(sub.isShowed()){
									if(sub.getFeedLists() != null)
										sub.getFeedLists().add(ref);
									break;
								 }								 
							 }						 
							 break;
						 }					 							 						 
					}	
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("error",e.getMessage());
				}
			}
		 }
		 else if("delete".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<PortletObjectRef> myFeedLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("myFeedLists",PortletSession.APPLICATION_SCOPE);
				 myFeedLists.remove(ref);	
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("deleteFeatured".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<PortletObjectRef> featuredLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("featuredLists",PortletSession.APPLICATION_SCOPE);
				 featuredLists.remove(ref);	
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("deleteCategoryFeed".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
				 for(ContentCategory dic : categories){
					 if(dic.isShowed()){
						 if(dic.getFeedLists() != null)
							 dic.getFeedLists().remove(ref);						 
						 break;
					 }					 							 						 
				 }	
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("deleteSubCategoryFeed".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
				 for(ContentCategory dic : categories){
					 if(dic.isShowed()){						 
						 for(ContentSubCategory sub : dic.getSubCategories()){
							 if(sub.isShowed()){
								if(sub.getFeedLists() != null)
									sub.getFeedLists().remove(ref);
								break;
							 }								 
						 }
						 break;
					 }					 							 						 
				 }	
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("deleteSearched".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<PortletObjectRef> list =(List<PortletObjectRef>)request.getPortletSession().getAttribute("searchLists",PortletSession.APPLICATION_SCOPE);
				 list.remove(ref);
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("deleteDefault".equals(action)){
			 String name = request.getParameter("parameter");
			 PortletObjectRef ref = this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){				 
				 List<PortletObjectRef> list =(List<PortletObjectRef>)request.getPortletSession().getAttribute("defaultLists",PortletSession.APPLICATION_SCOPE);
				 list.remove(ref);
				 this.getPortalService(request).delete(ref);
			 }
		 }
		 else if("showMyFeed".equals(action)){
			 request.getPortletSession().setAttribute("show",_MY_FEED,PortletSession.APPLICATION_SCOPE);			 
		 }
		 else if("hideMyFeed".equals(action)){
			 request.getPortletSession().setAttribute("show",_DEFAULT,PortletSession.APPLICATION_SCOPE);
		 }
		 else if("showFeatured".equals(action)){
			 request.getPortletSession().setAttribute("show",_FEATURED,PortletSession.APPLICATION_SCOPE);
		 }
		 else if("hideFeatured".equals(action)){
			 request.getPortletSession().setAttribute("show",_DEFAULT,PortletSession.APPLICATION_SCOPE);
		 }
		 else if("showCategory".equals(action)){
			 request.getPortletSession().setAttribute("show",_CATEGORY,PortletSession.APPLICATION_SCOPE);			 
		 }
		 else if("hideCategory".equals(action)){
			 request.getPortletSession().setAttribute("show",_DEFAULT,PortletSession.APPLICATION_SCOPE);
		 }
		 else if("showSubCategory".equals(action)){
			 request.getPortletSession().setAttribute("show",_CATEGORY,PortletSession.APPLICATION_SCOPE);
			 String name = request.getParameter("name");
			 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
			 if(categories != null && name != null){
				 for(ContentCategory dic : categories){
					 if(dic.isShowed()){
						 for(ContentSubCategory sub : dic.getSubCategories()){
							 if(sub.getName().equals(name))
								 sub.setShowed(true);						
							 else
								 sub.setShowed(false);
						 }
					 }					 							 						 
				 }
			 }
		 }
		 else if("hideSubCategory".equals(action)){
			 request.getPortletSession().setAttribute("show","category",PortletSession.APPLICATION_SCOPE);
			 String name = request.getParameter("name");
			 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
			 if(categories != null && name != null){
				 for(ContentCategory dic : categories){
					 if(dic.isShowed()){
						 for(ContentSubCategory sub : dic.getSubCategories()){
							 if(sub.getName().equals(name))								
								 sub.setShowed(false);
						 }
					 }					 							 						 
				 }
			 }
		 }
		 else if("showCategoryContent".equals(action)){
			 String name = request.getParameter("name");
			 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
			 if(categories != null && name != null){
				 for(ContentCategory dic : categories){
					 if(dic.getName().equals(name))
						 dic.setShowed(true);						
					 else
						 dic.setShowed(false);		
					 
						 
				 }
			 }
		 }
		 else if("hideCategoryContent".equals(action)){
			 String name = request.getParameter("name");
			 List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
			 if(categories != null && name != null){
				 for(ContentCategory dic : categories){
					 if(dic.getName().equals(name)){
						 dic.setShowed(false);
						 break;
					 }
						 
				 }
			 }
		 }
		 else if("save".equals(action)){
			 String name = request.getParameter("name");
			 PortletObjectRef ref =this.getPortalService(request).getPortletRefByName(name);
			 if(ref != null){
				 String keywords = request.getParameter("keywords");
				 String owner = request.getParameter("owner");
				 String orgId = request.getParameter("orgId");
				 String path = request.getParameter("path");
				 String label = request.getParameter("label");
				 String icon = request.getParameter("icon");
				 String iconCssSprite = request.getParameter("iconCssSprite");
				 String url = request.getParameter("url");
				 String tag = request.getParameter("tag");
				 String subtag = request.getParameter("subtag");
				 String language = request.getParameter("language");
				 String refreshMode = request.getParameter("refreshMode");
				 String editMode = request.getParameter("editMode");
				 String helpMode = request.getParameter("helpMode");
				 String configMode = request.getParameter("configMode");
				 String minimized = request.getParameter("minimized");
				 String maximized = request.getParameter("maximized");
				 String windowSkin = request.getParameter("windowSkin");
				 String allowJS = request.getParameter("allowJS");			 
				 String pageRefreshed = request.getParameter("pageRefreshed");
				 String autoRefreshed = request.getParameter("autoRefreshed");
				 String periodTime = request.getParameter("periodTime");
				 String showNumber = request.getParameter("showNumber");
				 String showType = request.getParameter("showType");
				 String windowStatus = request.getParameter("windowStatus");
				 String windowMode = request.getParameter("windowMode");
				 String type = request.getParameter("type");
				 String parameter = request.getParameter("parameter");			 
				 
				 ref.setKeywords(keywords);
				 ref.setUserId(owner);
				 ref.setOrgId(Long.parseLong(orgId));
				 ref.setPath(path);
				 ref.setLabel(label);
				 ref.setIcon(icon);
				 ref.setIconCssSprite(iconCssSprite);
				 ref.setUrl(url);
				 ref.setTag(tag);
				 ref.setSubTag(subtag);
				 ref.setLanguage(language);
				 ref.setRefreshMode(Integer.parseInt(refreshMode));
				 ref.setEditMode(Integer.parseInt(editMode));
				 ref.setHelpMode(Integer.parseInt(helpMode));
				 ref.setConfigMode(Integer.parseInt(configMode));
				 ref.setMinimized(Integer.parseInt(minimized));
				 ref.setMaximized(Integer.parseInt(maximized));
				 ref.setWindowSkin(windowSkin);
				 ref.setAllowJS(Integer.parseInt(allowJS));
				 ref.setPageRefreshed(Integer.parseInt(pageRefreshed));
				 ref.setAutoRefreshed(Integer.parseInt(autoRefreshed));
				 ref.setPeriodTime(Integer.parseInt(periodTime));
				 ref.setShowNumber(Integer.parseInt(showNumber));
				 ref.setShowType(Integer.parseInt(showType));
				 ref.setWindowStatus(Integer.parseInt(windowStatus));
				 ref.setMode(Integer.parseInt(windowMode));
				 ref.setType(Integer.parseInt(type));
				 ref.setParameter(parameter);
				 
				 this.getPortalService(request).save(ref);
			 }
			 
		 }
	  }
	 
	 protected void doView (RenderRequest request, RenderResponse response)
	   throws PortletException, java.io.IOException
	 {	
		PortalTab portalTab = getPortalTab(request);		
		String widths="300,300,300";
		int columns = 3;
				
		if(portalTab != null){			
			widths = portalTab.getWidths();	
			String[] widthArray = widths.split(",");
			columns = widthArray.length;
		}
		
		List<PortletObjectRef> defaultLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("defaultLists",PortletSession.APPLICATION_SCOPE);
		List<PortletObjectRef> myFeedLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("myFeedLists",PortletSession.APPLICATION_SCOPE);
		List<PortletObjectRef> featuredLists =(List<PortletObjectRef>)request.getPortletSession().getAttribute("featuredLists",PortletSession.APPLICATION_SCOPE);
		List<ContentCategory> categories =(List<ContentCategory>)request.getPortletSession().getAttribute("categories",PortletSession.APPLICATION_SCOPE);
		
		if(defaultLists == null){
			List<PortletObjectRef> list = this.getPortalService(request).getPortletRefsByUser(this.getUser(request).getUserId());
			defaultLists = new ArrayList<PortletObjectRef>();
			myFeedLists = new ArrayList<PortletObjectRef>();
			featuredLists = new ArrayList<PortletObjectRef>();
			categories = new ArrayList<ContentCategory>();
			for(PortletObjectRef ref : list){
				if(ref.getTag().equals(_DEFAULT)){
					defaultLists.add(ref);
				}else if(ref.getTag().equals(_MY_FEED_TITLE)){
					myFeedLists.add(ref);
				}else if(ref.getTag().equals(_FEATURED_TITLE)){
					featuredLists.add(ref);
				}else{
					if(ref.getSubTag() == null){
						ContentCategory fd = new ContentCategory(ref.getTag(),ref.getTagTitle());
						if(!categories.contains(fd)){
							fd.addFeed(ref);
							categories.add(fd);					
						}else{
							for(ContentCategory feedDictionary : categories){
								if(feedDictionary.equals(fd)){
									feedDictionary.addFeed(ref);
								    break;
							    }
							}
						}	
					}else{
						ContentCategory category = new ContentCategory(ref.getTag(),ref.getTagTitle());
						ContentSubCategory sub = new ContentSubCategory(ref.getSubTag(),ref.getSubTagTitle());
						if(!categories.contains(category)){							
							List<ContentSubCategory> subs = new ArrayList<ContentSubCategory>();
							sub.addFeed(ref);
							subs.add(sub);
							category.setSubCategories(subs);
							categories.add(category);					
						}else{
							for(ContentCategory categoryItem : categories){
								if(categoryItem.equals(category)){
									if(categoryItem.getSubCategories() == null){
										List<ContentSubCategory> subs = new ArrayList<ContentSubCategory>();
										sub.addFeed(ref);
										subs.add(sub);
										categoryItem.setSubCategories(subs);
									}else if(!categoryItem.getSubCategories().contains(sub)){
										sub.addFeed(ref);
										categoryItem.addSub(sub);
										break;
									}else{
										for(ContentSubCategory subCategory : categoryItem.getSubCategories()){
											if(subCategory.equals(sub)){
												subCategory.addFeed(ref);
											    break;
										    }
										}
									}
							    }
							}
						}	
					}
				}
			}
			request.getPortletSession().setAttribute("defaultLists",defaultLists,PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("myFeedLists",myFeedLists,PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("featuredLists",featuredLists,PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("categories",categories,PortletSession.APPLICATION_SCOPE);
		}
		
		String show = (String) request.getPortletSession().getAttribute("show",PortletSession.APPLICATION_SCOPE);
		if(show == null) show = _DEFAULT;
		request.setAttribute("show", show);
		request.setAttribute("columns", columns);		
		request.setAttribute("defaultLists", defaultLists);
		request.setAttribute("myFeedLists", myFeedLists);
		request.setAttribute("featuredLists", featuredLists);
		request.setAttribute("categories", categories);
		
		String keyword = request.getParameter("keyword");
		List<PortletObjectRef> searchLists = null;
		if(keyword != null){
			searchLists = this.getPortalService(request).getPortletRefsByUserAndKeyword(this.getUser(request).getUserId(),keyword);			
			request.getPortletSession().setAttribute("searchLists", searchLists,PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("keyword", keyword,PortletSession.APPLICATION_SCOPE);
		}else{
			request.getPortletSession().removeAttribute("searchLists", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().removeAttribute("keyword", PortletSession.APPLICATION_SCOPE);
		}
		try{
		 	JSONObject json = new JSONObject();  
		 	json.put("view", "addContent.view");
		    json.put("show", show);
		    json.put("columns", columns);
		    String error="";
		    if(request.getAttribute("error") != null) error = (String)request.getAttribute("error"); 
		    json.put("error", error);
		    
		    if(keyword != null){
		    	json.put("keyword", keyword);
		    	json.put("searchLists", JSONUtil.getPortletRefJArray(searchLists));
		    	json.put("defaultLists", "");
			    json.put("myFeedLists", "");
			    json.put("featuredLists", "");
			    json.put("categories", "");
		    }else{
		    	json.put("keyword", "");
		    	json.put("searchLists", "");
		    	if(show.equals(_DEFAULT))
		    		json.put("defaultLists", JSONUtil.getPortletRefJArray(defaultLists));
		    	else
		    		json.put("defaultLists", "");
		    	if(show.equals(_MY_FEED))
		    		json.put("myFeedLists", JSONUtil.getPortletRefJArray(myFeedLists));
		    	else
		    		json.put("myFeedLists", "");
		    	if(show.equals(_FEATURED))
		    		json.put("featuredLists", JSONUtil.getPortletRefJArray(featuredLists));
		    	else
		    		json.put("featuredLists", "");
		    	if(show.equals(_CATEGORY))
		    		json.put("categories", JSONUtil.getContentCategoryJArray(categories));
		    	else
		    		json.put("categories", "");
		    }
		 	response.getWriter().write(json.toString());
		 }catch(Exception e){}		  
	 }
	 
	 protected void doEdit (RenderRequest request, RenderResponse response)
     throws PortletException, java.io.IOException
   {
      String name = request.getParameter("name");      
      PortletObjectRef ref =this.getPortalService(request).getPortletRefByName(name);
      response.getWriter().write(JSONUtil.getPortletRefJSON(ref).toString());
   }
}