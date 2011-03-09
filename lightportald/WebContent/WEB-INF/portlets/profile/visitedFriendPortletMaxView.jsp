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
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.youHave"/> <c:out value="${requestScope.buddyCount}"/> <fmt:message key="portlet.label.friends"/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' >
<c:forEach var="buddy" items="${requestScope.buddys}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-center' width='80px'>
<jsp:useBean id="buddy" scope="page" class="org.light.portlets.connection.Connection"/>
<c:if test='${buddy.buddyCurrentStatusId == 1}'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px' width='15' height='12' align="bottom" alt=''/>
</c:if>
<br/>
<light:avatarUrl name="<%= buddy.getDisplayName() %>" url="<%= buddy.getUri() %>" />
<light:avatar name="<%= buddy.getDisplayName() %>" url="<%= buddy.getUri() %>" pictureUrl="<%= buddy.getPhotoUrl() %>" width="75" height="75" /> 
</td>
<c:if test='${status.index % 10 == 10 - 1}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.showNumber % 10 != 0}'>
</tr>
</c:if>
</table>
</fmt:bundle>
</body>
</html>