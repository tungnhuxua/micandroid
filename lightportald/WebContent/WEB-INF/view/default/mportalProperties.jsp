<%
/**
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
%>
<%@ include file="/common/taglibs.jsp"%>
<var id="CONTEXT_PATH" title= '<%= request.getContextPath() %>'></var>
<var id="PORTAL_ON" title= '<%= org.light.portal.util.PropUtil.getInt("portal.on",1) %>'></var>
<var id="PORTAL_COMMON_THEME" title= '<%= org.light.portal.util.PropUtil.getString("default.common.theme") %>'></var>
<var id="PORTAL_THEME_BASE" title= '<%= org.light.portal.util.PropUtil.getString("default.theme.base") %>'></var>
<var id="PORTAL_THEME" title= '<%= org.light.portal.util.PropUtil.getString("default.theme") %>'></var>
<var id="PORTAL_WIDTH" title= '<%= org.light.portal.util.PropUtil.getInt("portal.width",1016) %>'></var>
<var id="PORTAL_BAR_WIDTH" title= '<%= org.light.portal.util.PropUtil.getInt("portal.bar.width",120) %>'></var>
<var id="PORTLET_RENDER_ID_PREFIX" title= '<%= org.light.portal.util.Constants._PORTLET_RENDER_ID_PREFIX %>'></var>
<var id="LISTEN_SERVER_INTERVAL" title= '<%= org.light.portal.util.Constants._PORTAL_CLIENT_LISTEN_SERVER_INTERVAL %>'></var>
<var id="SESSION_TIMEOUT" title= '<%= org.light.portal.util.PropUtil.getInt("portal.session.timeout",1800000) %>'></var>
<var id="SESSION_TIMEOUT_WARNING" title= '<%= org.light.portal.util.PropUtil.getInt("portal.session.timeout.warning",120000) %>'></var>
<var id="REQUEST_TIMEOUT" title= '<%= org.light.portal.util.PropUtil.getInt("portal.request.timeout",5000) %>'></var>
<var id="THEME_ROOT" title= '<%= org.light.portal.util.PropUtil.getString("default.theme.root") %>'></var>
<var id="REQUEST_SUFFIX" title= '<%= org.light.portal.util.PropUtil.getString("portal.request.suffix") %>'></var>
<var id="EMAIL_PATTERN" title= '<%= org.light.portal.util.PropUtil.getString("default.email.pattern.js") %>'></var>
<var id="URI_PATTERN" title= '<%= org.light.portal.util.PropUtil.getString("default.uri.pattern.js") %>'></var>
<var id="PASSWORD_PATTERN" title= '<%= org.light.portal.util.PropUtil.getString("default.password.pattern.js") %>'></var>
<var id="DOMAIN_LIST" title= '<%= org.light.portal.util.PropUtil.getString("top.level.domain.list") %>'></var>
<var id="JAVASCRIPT_LIBRARYS" title= '<%= org.light.portal.util.PropUtil.getString(org.light.portal.util.Constants._MOBILE_JAVASCRIPT_LIBRARYS,"","")%>'></var>

<%
request.setAttribute("jsHistoryDisableList",org.light.portal.util.PropUtil.getString("js.history.disable.list"));
%>	
<c:if test='${requestScope.jsHistoryDisableList != null}'>
<var id="JS_HISTORY_DISABLE_LIST" title= '<c:out value="${requestScope.jsHistoryDisableList}"/>'></var>
</c:if>