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
<html>
<head>
</head>
<body>
<fmt:bundle basename="resourceBundle">
<c:if test='${requestScope.activities != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='100%' style="margin:10px 0 20px 0;">
<c:forEach var="activity" items="${requestScope.activities}">
<tr valign='top'>
<td class='portlet-table-td-center' width='70px;'>
<jsp:useBean id="activity" scope="page" class="org.light.portal.model.SocialActivity"/>
<light:avatarUrl name="<%= activity.getDisplayName() %>" url="<%= activity.getUri() %>" />
<light:avatar entityId="<%= activity.getUserId() %>" name="<%= activity.getDisplayName() %>" url="<%= activity.getUri() %>" pictureUrl="<%= activity.getPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left'>
<c:out value="${activity.content}"/>
<br/>
<span class='portlet-note' style='padding-left:0'><c:out value="${activity.date}"/></span>
</td>
</tr>
</c:forEach>
</table>
<%@ include file="/common/paginator.jsp"%>
</c:if>
<c:if test='${requestScope.activities == null}'>
<div class="portlet-item" style="margin:10px 0 40px 0;">
There is no recent activity. Why not start something?
</div>
</c:if>
</fmt:bundle>
</body>
</html>
