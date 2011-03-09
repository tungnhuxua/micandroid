<%@ page import="java.util.*,org.light.portal.*,org.light.portal.util.*,org.json.*" %>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<span class="portal-header-menu-item">
<%
	List<LabelBean> list = (List<LabelBean>)request.getAttribute(Constants._PORTAL_INIT_LIST);
	if(list != null){
		for(LabelBean bean : list){	
			if(bean.getId() == "portalJSON"){
				JSONObject json = JSONUtil.parse(bean.getDesc());
				boolean logined = Context.getInstance().isAuthorized(request);
				if(logined){
%>
				<a href="javascript:;" onclick="javascript:Light.toMyAccount();"><%= json.getString("title") %></a>
<%
				}
				long permissions =  json.getLong("permissions");
				if(PermissionUtil.hasPermission(permissions,"PORTAL_OPTIONS")){
%>
					<a href="javascript:;" onclick="javascript:Light.showChangeOptions();">
						<img src="/light/images/options.png" title='<fmt:message key="_MENU_OPTIONS"/>' height="16" width="16">
					</a>
<%
				}
				if(PermissionUtil.hasPermission(permissions,"PORTAL_SIGN_UP")){
%>
					<a href="javascript:;" onclick="javascript:Light.showSignUp();" style="padding:0;"><fmt:message key="_MENU_SIGN_UP"/></a>
<%
				}
				if(PermissionUtil.hasPermission(permissions,"PORTAL_SIGN_IN")){
					if(logined){
%>
						<a href="javascript:;" onclick="javascript:Light.showMyAccount();">
							<img src="/light/images/myaccount.gif" title='<fmt:message key="_MENU_MY_ACCOUNT"/>' height="16" width="16">
						</a>
						<a href="javascript:;" onclick="javascript:Light.logout();">
							<img src="/light/images/signout.gif" title='<fmt:message key="_MENU_SIGN_OUT"/>' height="16" width="16">
						</a>
<%						
					}else{
%>						
						<a href="javascript:;" onclick="javascript:Light.showLogin(false);" style="padding:0;"><fmt:message key="_MENU_SIGN_IN"/></a>
<%
						String apiKey = org.light.portal.util.PropUtil.getString("facebook.apiKey");
						if(!org.light.portal.util.StringUtil.isEmpty(apiKey)){
%>
							<span>    
								<a href="<%= request.getContextPath() %>/openId/facebookLogin.jsp">
								<img align="top" alt="" src="<%= request.getContextPath() %>/light/images/fb-small.png">
								</a>
							</span>
<%
						}
%>	
<%
						String tapiKey = org.light.portal.util.PropUtil.getString("twitter.signin.api.key");
						if(!org.light.portal.util.StringUtil.isEmpty(tapiKey)){
%>
						<span>    
							<a href="<%= request.getContextPath() %>/openId/twitterLogin.jsp">
								<img align="top" alt="" src="<%= request.getContextPath() %>/light/images/twitter-small.png">
							</a>
						</span>
<%
						}
					}
%>	
<%
				}
				if(PermissionUtil.hasPermission(permissions,"PORTAL_CHANGE_LANGUAGE")){						
%>
					<span>
						<input type="button" value="" class="icons_world" onclick="javascript:Light.showChangeLanguage();" title="Select Language" />
					</span>
<%					
				}
				if(json.getBoolean("showSearchBar")){
%>
					<span class="portal-header-search">    
						<input class="icons_search" style="" title="Search" onclick="javascript:Light.globalSearch();" value="" type="button" />
					</span>
<%
				}
			}
		}
	}
%>
</span>
</fmt:bundle>
