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

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.init.page") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.header.page") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.properties.page") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.language.page") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.init.views.ext") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("portlet.profile.view.sync") %>' />
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("portlet.connection.view.sync") %>' />
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("portlet.microblog.view.sync") %>' />
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("portlet.forum.view.sync") %>' />

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.footer.page") %>' />
