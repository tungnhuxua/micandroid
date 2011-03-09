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

/**
 * 
 * @author Jianmin Liu
 **/
public final class Constants {
	
	public static final String _PORTL_SERVER_INFO 	    = "Light Portal Version 1.4.0";	
	public static final String _LIGHT_PORTAL			= "LightPortal";
	public static final String _LIGHT_PORTAL_USER_ID	= "light_portal_user_id";
	public static final String _PORTAL_TITLE_DEFAULT_KEY= "portal.title.default";
	public static final String _PORTAL_TITLE_LOGIN_KEY  = "portal.title.login";
	
	public static final int    _CLIENT_ALL			 	= 0;
	public static final int    _CLIENT_BROWSER		 	= 1;
	public static final int    _CLIENT_MOBILE		 	= 2;
	public static final String _MOBILE_MODE				= "mobileMode";
	public static final String _MOBILE					= "mobile";
	
	public static final String _PORTAL_INIT_LIST		= "portalInitList";
	public static final String _ORGANIZATION			= "org";	
	public static final String _USER					= "user";	
	public static final String _LOGGED_IN				= "loggedIn";
	public static final String _VISITED_USER			= "visitedUser";
	public static final String _VISITED_PAGE			= "visitedPage";
	public static final String _VISITED_GROUP			= "visitedGroup";
	public static final String _VISITED_STORE			= "visitedStore";
	public static final String _VISITED_PORTAL			= "visitedPortal";
	public static final String _PROFILE_PREFIX			= "profile_";	
	public static final String _PARENT_PREFIX			= "parent_";
	public static final String _PORTAL_ROLES			= "portal.roles";
	public static final String _TITLE_SUFFIX			= ".title";
	public static final String _PERMISSIONS_SUFFIX		= ".permissions";
	
	public static final String _PORTLET_RENDER_ID_PREFIX= PropUtil.getString("portlet.render.id.prefix");
	public static final String _PORTLET_TITLE_ID_PREFIX= PropUtil.getString("portlet.title.id.prefix");
	public static final String _PING_URL_PREFIX			= PropUtil.getString("portal.ping.url.prefix");
	public static final String _PAGE_PREFIX				= PropUtil.getString("portal.page.prefix");
	public static final String _GROUP_PREFIX			= PropUtil.getString("portal.group.prefix");		
	public static final String _GROUP_URL_PREFIX		= PropUtil.getString("portal.group.url.prefix");
	public static final String _PAGE_URL_PREFIX			= PropUtil.getString("portal.page.url.prefix");
	public static final String _REF_URL_PREFIX			= PropUtil.getString("portal.ref.url.prefix");
	public static final String _SPACE_URL_PREFIX		= PropUtil.getString("portal.space.url.prefix");
	public static final String _RSS_URL_PREFIX			= PropUtil.getString("portal.rss.url.prefix");
	public static final String _OPML_URL_PREFIX			= PropUtil.getString("portal.opml.url.prefix");
	public static final String _FORUM_URL_PREFIX		= PropUtil.getString("portal.forum.url.prefix");	
	public static final String _BLOG_URL_PREFIX			= PropUtil.getString("portal.blog.url.prefix");	
	public static final String _REQUEST_SUFFIX			= PropUtil.getString("portal.request.suffix");
	public static final String _PORTAL_INDEX			= PropUtil.getString("portal.jsp.index");
	public static final String _GUEST_INDEX				= "portal.jsp.guest.index";
	public static final String _MAIN_INDEX				= "portal.jsp.main.index";
	public static final String _MOBILE_GUEST_INDEX		= "portal.jsp.mobile.guest.index";
	public static final String _MOBILE_INDEX			= "portal.jsp.mobile.index";
	public static final String _IPAD_GUEST_INDEX		= "portal.jsp.ipad.guest.index";
	public static final String _IPAD_INDEX				= "portal.jsp.ipad.index";
	public static final String _GROUP_INDEX				= PropUtil.getString("portal.jsp.group.index");
	public static final String _GROUP_MOBILE_INDEX		= PropUtil.getString("portal.jsp.group.mobile.index");
	public static final String _PAGE_INDEX				= PropUtil.getString("portal.jsp.page.index");
	public static final String _SPACE_INDEX				= PropUtil.getString("portal.jsp.space.index");
	public static final String _SPACE_MOBILE_INDEX		= PropUtil.getString("portal.jsp.space.mobile.index");
	public static final String _MEMBER_INDEX_PATTERN	= PropUtil.getString("portal.jsp.member.index.pattern");
	public static final String _GROUP_INDEX_PATTERN		= PropUtil.getString("portal.jsp.group.index.pattern");
	public static final String _FORUM_INDEX				= PropUtil.getString("portal.jsp.forum.index");	
	public static final String _BLOG_INDEX				= PropUtil.getString("portal.jsp.blog.index");	
	public static final String _PAGE_ERROR_NOTFOUND		= PropUtil.getString("portal.jsp.page.error.notfound");
	public static final String _PAGE_ERROR_PERMISSION	= PropUtil.getString("portal.jsp.page.error.permission");
	public static final String _GROUP_SUBDOMAIN_INDEX	= PropUtil.getString("portal.jsp.group.subdomain.index");
	public static final String _MEMBER_SUBDOMAIN_INDEX	= PropUtil.getString("portal.jsp.member.subdomain.index");
	public static final String _NETWORK_ERROR_NOTFOUND	= PropUtil.getString("portal.jsp.network.error.notfound");
	public static final String _UNSUBSCRIBE_INDEX		= PropUtil.getString("portal.jsp.unsubscribe");	
	
	public static final String _PORTAL_CONTROLLER_CHAIN	= "portal.controller.chain";
	public static final String _PORTAL_CONTROLLER_LIST	= "portal.controller.list";
	public static final String _DEFAULT_THEME           = "default.theme";
	public static final String _DEFAULT_SPACE_PREFIX    = PropUtil.getString("default.space.prefix");
	public static final String _DEFAULT_GROUP_PREFIX    = PropUtil.getString("default.group.prefix");
		
	public static final String _CURRENT_TAB				= "currentTab";
	public static final String _REMEMBER_LOCALE         = "userLocale";
	public static final String _PERSON_ID				= "personId";
	public static final String _REMEMBER_USER_ID        = "rememberedUserId";
	public static final String _REMEMBER_USER_PASSWORD  = "rememberedUserPassword";
	public static final String _IS_SIGN_OUT				= "isSignOut";
	public static final String _DEFAULT_LANGUAGE        = "default.language";//"en";
	public static final String _DEFAULT_LOCALE          = "default.locale";//"en";
	public static final String _CURRENT_LOCALE          = "currentLocale";
	public static final String _DEFAULT_RESOURCE_BUNDLE = "resourceBundle";
	public static final String _PORTAL_URL_FORMAT	    = "portal.url.format";
	public static final String _PORTAL_MOBILE_BROWSER_VERSION	    = "portal.setting.mobile.browser.version";
	public static final String _PORTAL_MOBILE_BROWSER_TABS_MAX		= "portal.setting.mobile.browser.tabs.max";
	public static final int _PORTAL_MOBILE_BROWSER_TABS_MAX_DEFAULT	= 3;
	public static final int _PORTAL_CLIENT_LISTEN_SERVER_INTERVAL = PropUtil.getInt("portal.client.listen.server.interval",10000);
	
	public static final String _DEFAULT_ORG_WEBID       = PropUtil.getString("default.org.webId");
	public static final String _DEFAULT_ORG_TITLE	    = PropUtil.getString("default.org.title");
	public static final String _DEFAULT_ORG_VIRTUALHOST = PropUtil.getString("default.org.virtualHost");
	public static final String _DEFAULT_ORG_MX          = PropUtil.getString("default.org.mx");
	public static final String _DEFAULT_ORG_EMAIL       = PropUtil.getString("default.org.email");
	public static final String _DEFAULT_ORG_LOGO        = PropUtil.getString("default.org.logo");
	public static final String _DEFAULT_ORG_LOGOICON    = PropUtil.getString("default.org.logoIcon");	
	public static final String _DEFAULT_ORG_USER_PREFIX = PropUtil.getString("default.org.user.prefix");
	public static final String _DEFAULT_ORG_ADMIN_PREFIX= PropUtil.getString("default.org.admin.prefix");
	
	public static final String _DEFAULT_GROUP_PORTRAIT				= "default.group.portrait";
	public static final String _DEFAULT_USER_MALE_PORTRAIT    		= "default.user.male.portrait";
	public static final String _DEFAULT_USER_FEMALE_PORTRAIT    	= "default.user.female.portrait";
	public static final String _DEFAULT_USER_PORTRAIT_WIDTH    		= "default.user.portrait.width";
	public static final String _DEFAULT_USER_PORTRAIT_HEIGHT   		= "default.user.portrait.height";
	public static final String _DEFAULT_USER_PORTRAIT_SMALL_WIDTH   = "default.user.portrait.small.width";
	public static final String _DEFAULT_USER_PORTRAIT_SMALL_HEIGHT	= "default.user.portrait.small.height";
	public static final String _DEFAULT_USER_PICTURE_MAX_WIDTH 		= "default.user.picture.max.width";
	public static final String _DEFAULT_USER_PICTURE_MAX_HEIGHT		= "default.user.picture.max.height";
	public static final String _DEFAULT_USER_URL_PREFIX				= "portal.space.url.prefix";
	public static final String _DEFAULT_TAG_CLOUD_FONT_SIZE_MIN		= "default.tag.cloud.font.size.min";
	public static final String _DEFAULT_TAG_CLOUD_FONT_SIZE_MAX		= "default.tag.cloud.font.size.max";
	
	public static final String _DEFAULT_USER            = PropUtil.getString("default.user");//"default";
	public static final String _DEFAULT_ROLE            = PropUtil.getString("default.role");//"role_guest";
	public static final String _ROLE_PREFIX    		    = "role_";
	public static final String _ROLE_GUEST    		    = "role_guest";
	public static final String _ROLE_USER    		    = "role_user";
	public static final String _ROLE_MEMBER    		    = "role_member";
	public static final String _ROLE_GROUP    		    = "role_group";
	public static final String _ROLE_STORE    		    = "role_store";
	public static final String _ROLE_ADMIN              = "role_admin";
	public static final String _ROLE_PROFILE			= "role_profile";
	public static final String _ROLE_NO_PROFILE			= "role_noprofile";
	
	public static final String _MEMBER_SHOW_DEFAULT_PAGE= "member.show.default.page";
	
	public static final String _CHANNEL_LIST_NAME		= "channel.list.name";
	public static final String _CHANNEL_LIST_DESC		= "channel.list.desc";
	public static final String _CHANNEL_PREFIX          = "channel_";
	public static final String _CHANNEL_FORUM           = "channel_forum";
	public static final String _CHANNEL_BLOG			= "channel_blog";
	public static final String _CHANNEL_AD           	= "channel_ad";
	public static final String _CHANNEL_SEARCH          = "channel_search";
	public static final String _CHANNEL_NEWS           	= "channel_news";
	public static final String _CHANNEL_CULTURE        	= "channel_culture";
	public static final String _CHANNEL_RECIPE        	= "channel_recipe";
	public static final String _CHANNEL_GAME        	= "channel_game";
	public static final String _CHANNEL_ET        		= "channel_entertainment";
	
	public static final String _PORTLET_MODE_CONFIG              = "config";
	public static final String _PORTLET_MODE_HEADER	             = "header";
	public static final String _PORTLET_MODE_CHANGE_POSITION     = "changePosition";
	public static final int	   _PORTLET_ASYN_LOAD 		= 0;
	public static final int	   _PORTLET_SYNC_LOAD 		= 1;
	public static final int	   _PORTLET_NORMAL	 		= 0;
	public static final int	   _PORTLET_MINIMIZED 		= 1;
	public static final int	   _PORTLET_MAXIMIZED 		= 2;
	public static final String _DISABLE_PAGE_REFRESH	= "disablePageRefresh";
	
	public static final String _DEFAULT            = "default";
	public static final String _LANGUAGE_EN   	   = "en";
	public static final String _LANGUAGE_ALL   	   = "all";
	public static final String _DEFAULT_COUNTRY	   = "US";
	public static final String _DEFAULT_TIME_ZONE  = "PST";
	public static final String _IMAGE_THUMB_SUFFIX = ".thumb.png";	
	public static final String _IMAGE_SMALL_SUFFIX = ".small.png";	
	public static final String _MY_FEED_TITLE      = "portlet.tag.title.myfeed";
	public static final String _FEATURED_TITLE     = "portlet.tag.title.featured";
	public static final String _MY_FEED 		   = "myFeed";
	public static final String _FEATURED	       = "featured";
	public static final String _CATEGORY	       = "category";
	
	public static final int _DEFAULT_NORMAL_SHOW_NUMBER  	= 6;
	public static final int _DEFAULT_MAX_SHOW_NUMBER   		= 20;
	
	public static final String _CHARSET_UTF		   = "UTF-8";
	public static final String _CHARSET_CHINESE	   = "gb2312";
	public static final String _CHARSET_8859	   = "8859_1";
	
	public static final String _PORTLET_WINDOW_CLASSIC		   		= "WindowSkin1";
	public static final String _PORTLET_WINDOW_TRADITIONAL		    = "WindowSkin2";
	
	public final static String SERVLET_REQUEST = "httpServletRequest";
	public final static String SERVLET_RESPONSE = "httpServletResponse";
	public final static String ACTION_RESPONSE = "javax.portlet.action.response";
	public final static String PORTLET_REQUEST = "javax.portlet.request";
    public final static String PORTLET_RESPONSE = "javax.portlet.response";
    public final static String PORTLET_CONFIG = "javax.portlet.config";
    
    public final static String _JAVASCRIPT_LIBRARYS	 = "javascript.library.list";
    public final static String _MOBILE_JAVASCRIPT_LIBRARYS	 = "javascript.library.mobile.list";
	public final static String _PORTLET_JSP_PATH	 = "/WEB-INF/portlets";
	public final static String _FILE_PATH		 	 = (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) ? PropUtil.getString("file.root") : PropUtil.getString("file.root.windows");
	public final static String _INDEX_PATH 			 = PropUtil.getString("file.index.path");
	public static final String _GROUP_PATH   	 	 = PropUtil.getString("file.group.path");
	public static final String _GROUP_IMAGE_PATH   	 = PropUtil.getString("file.group.images.path");
	public static final String _USER_PATH   	 	 = PropUtil.getString("file.user.path");
	public static final String _MY_IMAGE_PATH   	 = PropUtil.getString("file.my.images.path");
	public static final String _MY_MUSIC_PATH   	 = PropUtil.getString("file.my.musics.path");
	public static final String _MY_FILE_PATH   		 = PropUtil.getString("file.my.files.path");
	public static final String _SITE_MAP_PATH  		 = PropUtil.getString("portal.sitemap.path");
	public static final String _SITE_MAP_FILE  		 = PropUtil.getString("portal.sitemap.file");
		
	public final static boolean _TABLE_RECREATE = PropUtil.getBoolean("portal.setting.table.create");
	public final static String _COMMAND_LIST = "command.list";
    public final static String 	METHOD_ID = "org.light.portal.core.method";
    public final static Integer METHOD_RENDER = new Integer(1);
    public final static Integer METHOD_ACTION = new Integer(3);
    public final static Integer METHOD_NOOP = new Integer(5);
	public final static int NumberOfKnownMimetypes = 15;
	
	public final static int _MESSAGE_EVENT_GROUP = 1;
	public final static int _MESSAGE_EVENT_CALENDAR = 2;
	public final static int _MESSAGE_EVENT_COMMENT = 3;
	public final static int _MESSAGE_EVENT_CONNECTION = 4;
	public final static int _MESSAGE_EVENT_FORUM_CATEGORY = 5;
	public final static int _MESSAGE_EVENT_FORUM_SUB_CATEGORY = 6;
		
	public final static int _MESSAGE_EVENT_TYPE_INVITE = 1;
	public final static int _MESSAGE_EVENT_TYPE_REQUEST = 2;
	
	public final static String _FORUM_CATEGORY_PROFIX = "forum.category.";
	public final static String _FORUM_CATEGORY_DESC_PROFIX = "forum.category.desc.";
	public final static String _FORUM_CATEGORY_SUB_PROFIX = "forum.category.sub.";
	public final static String _FORUM_CATEGORY_SUB_DESC_PROFIX = "forum.category.sub.desc.";
	
	public final static String _SEARCH_LIST="portlet.search.list";
	public static final int _DEFAULT_USER_SEARCHBAR_SHOW = PropUtil.getInt("default.user.searchBar.show"); 
	public static final String _DEFAULT_SEARCH_ENGINE = PropUtil.getString("default.search.engine");
	public static final int _DEFAULT_USER_MAX_TABS_SHOW = PropUtil.getInt("default.user.maxShowTabs");
	
	public static final String[] _EVENTS_STARTUP	 	= PropUtil.getStringArray("events.startup");
	public static final String[] _EVENTS_SHUTDOWN 		= PropUtil.getStringArray("events.shutdown");
	public static final String _EVENTS_LOGIN_BEFORE 	= "events.login.before";
	public static final String _EVENTS_LOGIN_AFTER 		= "events.login.after";
	public static final String _EVENTS_LOGOUT_BEFORE 	= "events.logout.before";
	public static final String _EVENTS_LOGOUT_AFTER 	= "events.logout.after";
	
	public final static String _THEME="portal.theme.list";	
	public final static String _BG_IMAGES="portal.bg.image.list";	
	public final static String _HEADER_IMAGES="portal.header.image.list";	
	public final static String _WINDOW_SKIN="portlet.window.skin";	
	public final static String _CLIENTS="portal.support.clients";	
	public final static String _FONTS="theme.fonts";
	public final static String _FONTSIZE_LABEL="theme.fontSize.label";
	public final static String _FONTSIZE_VALUE="theme.fontSize.value";
	public final static String _FONT_COLOR_LABEL="theme.font.color.label";
	public final static String _FONT_COLOR_VALUE="theme.font.color.value";
	public final static String _HEADER_HEIGHT_LABEL="theme.headerHeight.label";
	public final static String _HEADER_HEIGHT_VALUE="theme.headerHeight.value";
	public final static String _MAX_SHOW_TABS_MIN = "default.user.maxShowTabs.min";
	public final static String _MAX_SHOW_TABS_MAX = "default.user.maxShowTabs.max";
	public final static String _ALLOW_RANDOOM_THEME = "portal.setting.theme.random";
	public final static String _ITEM_IMAGE_DETAIL_MAX_WIDTH 	= "item.image.detail.max.width";
	public final static String _ITEM_IMAGE_VIEW_MAX_WIDTH 	= "item.image.view.max.width";
	
	public final static int _MAX_ROW_PER_FORUM_DEFAULT = 10;
	public final static int _MAX_ROW_PER_FORUM_PAGE = PropUtil.getInt("default.forum.show.max.posts",_MAX_ROW_PER_FORUM_DEFAULT);
	public final static int _MAX_ROW_PER_MICROBLOG_DEFAULT = 20;
	public final static int _MAX_ROW_PER_MICROBLOG_PAGE = PropUtil.getInt("default.microblog.show.max.posts",_MAX_ROW_PER_MICROBLOG_DEFAULT);
	public final static int _MAX_ROW_PER_CHAT_DEFAULT = 100;
	public final static int _MAX_ROW_PER_CHAT = PropUtil.getInt("default.chat.show.max.posts",_MAX_ROW_PER_CHAT_DEFAULT);
	public final static int _DEFAULT_ITEM_EXPIRED_DAYS = PropUtil.getInt("default.item.expire.day",30);
	
	public final static int _CALENDAR_START_TIME = 800; //8:00 AM
	public final static int _CALENDAR_END_TIME = 1700; // 5:00 PM
	public final static int _CALENDAR_INTERVAL = 60; //1 hour ( 30 is half hours)
	public final static int _CALENDAR_EVENT_STATE = 0; //private event
	
	public final static String _PERMISSION_PREFIX = "permission.";
	
	public final static String _PERMISSION_LIST_NAME = "permission.list.name";
	public final static String _PERMISSION_LIST_DESC = "permission.list.desc";

	//Permissions MODE
	public final static String _PERMISSION_VIEW = "VIEW";
	public final static String _PERMISSION_EDIT = "EDIT"; 
	public final static String _PERMISSION_HELP = "HELP";
	public final static String _PERMISSION_CONFIG = "CONFIG";
	
	//Permissions menu
	public final static String _PERMISSION_PORTAL_OPTIONS = "PORTAL_OPTIONS";
	public final static String _PERMISSION_PORTAL_ADD_TAB = "PORTAL_ADD_TAB";
	public final static String _PERMISSION_PORTAL_ADD_CONTENT = "PORTAL_ADD_CONTENT";
	public final static String _PERMISSION_PORTAL_SIGN_IN = "PORTAL_SIGN_IN";
	public final static String _PERMISSION_PORTAL_TURN_OFF = "PORTAL_TURN_OFF";
	public final static String _PERMISSION_PORTAL_CHANGE_LANGUAGE = "PORTAL_CHANGE_LANGUAGE";
	
	//Permissions ACTION
	public final static String _PERMISSION_ADD = "ADD";
	public final static String _PERMISSION_UPDATE = "UPDATE"; 
	public final static String _PERMISSION_DELETE = "DELETE";
	
	// Object Types (organization, group, store, picture, forum, etc)
	public final static int _OBJECT_TYPE_USER				= 1;	
	public final static int _OBJECT_TYPE_BLOG 				= 2;
	public final static int _OBJECT_TYPE_PIC_POS_TAG 		= 3;
	public final static int _OBJECT_TYPE_FEEDBACK 			= 4;
	public final static int _OBJECT_TYPE_AD 				= 5;
	public final static int _OBJECT_TYPE_POPULAR_ITEM 		= 6;
	public final static int _OBJECT_TYPE_NEWS		 		= 7;
	public final static int _OBJECT_TYPE_FORUM		 		= 8;
	public final static int _OBJECT_TYPE_PICTURE	 		= 9;
	public final static int _OBJECT_TYPE_ORG 				= 10;
	public final static int _OBJECT_TYPE_GROUP 				= 11;
	public final static int _OBJECT_TYPE_ROLE 				= 12;
	public final static int _OBJECT_TYPE_MICROBLOG			= 21;
	public final static int _OBJECT_TYPE_RECOMMENDED_ITEM 	= 22;
	public final static int _OBJECT_TYPE_VIEWED_ITEM 		= 23;
	public final static int _OBJECT_TYPE_TODO_ITEM 			= 24;
	public final static int _OBJECT_TYPE_DELI_ITEM 			= 25;
	public final static int _OBJECT_TYPE_RSS_ITEM 			= 26;
	public final static int _OBJECT_TYPE_INTERNAL_NEWS		= 27;
	public final static int _OBJECT_TYPE_BOOKMARK			= 28;
	
	public final static int _PRIVACY_HIDDEN		= -1;
	public final static int _PRIVACY_ONLYME 	= 0;
	public final static int _PRIVACY_CONNECTION = 1;
	public final static int _PRIVACY_MEMBER 	= 2;
	public final static int _PRIVACY_PROFILE	= 3;
	public final static int _PRIVACY_PUBLIC		= 4;
	
	public final static int _STATUS_DELETE		= -10;
	public final static int _STATUS_NOT_APPROVED= -1;
	public final static int _STATUS_NOT_READ	= -1;
	public final static int _STATUS_STOP		= -1;
	public final static int _STATUS_NEW 		= 0;
	public final static int _STATUS_PENDING		= 0;
	public final static int _STATUS_DRAFT		= 0;
	public final static int _STATUS_NORMAL		= 0;
	public final static int _STATUS_OFFLINE 	= 0;
	public final static int _STATUS_ONLINE 		= 1;
	public final static int _STATUS_READ 		= 1;
	public final static int _STATUS_APPROVED 	= 1;
	public final static int _STATUS_PUBLISHED 	= 1;
	public final static int _STATUS_RECOMMENDED	= 1;
	public final static int _STATUS_COMPLETED	= 1;
	public final static int _STATUS_DELIVERED	= 2;
	public final static int _STATUS_CLOSED		= 3;
		
	public final static String _TAG_TYPE_ID = "tag.id";
	public final static String _TAG_TYPE_NAME = "tag.name";

	public static final String _COUNTRY_LIST = "country.list";
	public static final String _TIMEZONE_LIST = "timezone.list";
	
	public final static String _NOTIFICATION_ENABLED = "portal.notification.enabled";
	
	public final static int _INDEX_THREAD_POOL_MIN = PropUtil.getInt("index.thread.pool.executor.minPoolSize");
	public final static int _INDEX_THREAD_POOL_MAX = PropUtil.getInt("index.thread.pool.executor.maxPoolSize");
	public final static long _INDEX_THREAD_LIVE_TIME = PropUtil.getLong("index.thread.pool.executor.keepAliveTime");
	
	public final static boolean _CACHE_ENABLED = PropUtil.getBoolean("portal.cache.enabled");	
	public final static boolean _CACHE_REPLICATION_ON_CACHE_WRITE = PropUtil.getBoolean("portal.cache.replication.on.cache.write");	
	public final static String[] _CACHE_REPLICATION_ON_CACHE_WRITE_LIST = PropUtil.getStringArray("portal.cache.replication.on.cache.write.object.list");	
	public final static int _CACHE_CAPABILITY = PropUtil.getInt("portal.cache.capability");
	public final static long _CACHE_TIME_MAX_LIVE = PropUtil.getLong("portal.cache.timeToMaxLive");
	public final static long _CACHE_TIME_MIN_LIVE = PropUtil.getLong("portal.cache.timeToMinLive");
	public final static long _CACHE_CLEAN_MAX_INTERVAL = PropUtil.getLong("portal.cache.clean.max.interval");
	public final static long _CACHE_CLEAN_MIN_INTERVAL = PropUtil.getLong("portal.cache.clean.min.interval");
	public final static long _CACHE_CLEAN_CAPABILITY_THRESHOLD = PropUtil.getLong("portal.cache.clean.capability.threshold");
	public final static String _CACHE_SYNC_OBJECT_LIST = PropUtil.getString("portal.cache.sync.object.list");
	
	public final static int _DATABASE_INSTANCE= PropUtil.getInt("portal.distribute.database.instance");
	public final static int _FILE_INSTANCE= PropUtil.getInt("portal.distribute.file.instance");
	public final static int _INDEX_INSTANCE= PropUtil.getInt("portal.distribute.index.instance");
	public final static int _CACHE_INSTANCE= PropUtil.getInt("portal.distribute.cache.instance");
	
	public final static boolean _REPLICATION_ENABLED = PropUtil.getBoolean("portal.distribute.replication.enabled");
	public final static boolean _REPLICATION_ENABLED_INDEX = PropUtil.getBoolean("portal.distribute.replication.enabled.index");
	public final static boolean _REPLICATION_ENABLED_CACHE = PropUtil.getBoolean("portal.distribute.replication.enabled.cache");
	public final static boolean _REPLICATION_ENABLED_FILE = PropUtil.getBoolean("portal.distribute.replication.enabled.file");
	public final static boolean _REPLICATION_ENABLED_DATABASE = PropUtil.getBoolean("portal.distribute.replication.enabled.database");	
	
	public final static String _REPLICATION_HOSTS = PropUtil.getString("portal.distribute.replication.hosts");
	public final static String _REPLICATION_SERVER_NAME = PropUtil.getString("portal.distribute.replication.server.name");
	public final static int _REPLICATION_SERVER_PORT = PropUtil.getInt("portal.distribute.replication.server.port");
	public final static int _REPLICATION_SERVER_MAINTAIN_INTERVAL = PropUtil.getInt("portal.distribute.replication.server.maintain.interval");
	public final static int _REPLICATION_PUBLISHER_MAINTAIN_INTERVAL = PropUtil.getInt("portal.distribute.replication.publisher.maintain.interval");
		
	public final static long _GENEREATOR_START= PropUtil.getLong("portal.distribute.id.genereator.start");
	public final static int _GENEREATOR_INTERVAL= PropUtil.getInt("portal.distribute.id.genereator.interval");
	public final static int _GENEREATOR_LOAD_COUNT= PropUtil.getInt("portal.distribute.id.genereator.load.count");
	public final static int _GENEREATOR_LOAD_INTERVAL= PropUtil.getInt("portal.distribute.id.genereator.load.interval");
	public final static int _GENEREATOR_LOAD_THRESHOLD= PropUtil.getInt("portal.distribute.id.genereator.load.threshold");
	
	public final static boolean _FRONT_HOST = PropUtil.getBoolean("portal.front.host");	
	public final static String _PORTAL_FRONT_DAILY_TASK = "portal.front.daily.task";
	public final static boolean _BACKEND_HOST = PropUtil.getBoolean("portal.backend.host");	
	public final static String _PORTAL_BACKEND_DAILY_TASK = "portal.backend.daily.task";
	
	//lucene configuration
	public final static String _LUCENE_ANALYZER = "search.lucene.analyzer";
	public final static String _LUCENE_RESULT_TOP = "search.result.top";
	public final static String _LUCENE_HIGHLIGHTER_PREFIX = "search.lucene.highligher.prefix";
	public final static String _LUCENE_HIGHLIGHTER_SUFFIX = "search.lucene.highligher.suffix";
	
	public final static String _FACEBOOK_AUTO_POST = "facebook.autoPost";
	public final static String _FACEBOOK_API_KEY = "facebook.apiKey";
	public final static String _FACEBOOK_SECRET_KEY = "facebook.secretKey";
	public final static String _FACEBOOK_SESSION_KEY = "facebook.onetime.sessionkey";
	public final static String _FACEBOOK_TAGET_ID = "facebook.publish.targetId";
	public final static String _FACEBOOK_PUBLISH_TO_ACCOUNT = "facebook.publish.to.account";
	
	public final static String _TWITTER_AUTO_POST = "twitter.autoPost";
	public final static String _TWITTER_USERNAME = "twitter.username";
	public final static String _TWITTER_PASSWORD = "twitter.password";
	public final static String _TWITTER_CONSUMER_KEY = "twitter.consumer.key";
	public final static String _TWITTER_CONSUMER_SECRET = "twitter.consumer.secret";
	public final static String _TWITTER_ACCESS_TOKEN = "twitter.access.token";
	public final static String _TWITTER_ACCESS_TOKEN_SECRET = "twitter.access.token.secret";
	
	public final static String _BIT_LY_USERNAME = "bit.ly.username";
	public final static String _BIT_LY_API_KEY = "bit.ly.apiKey";

}