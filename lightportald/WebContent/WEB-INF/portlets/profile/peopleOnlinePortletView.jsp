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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>

<c:forEach var="profile" items="${requestScope.onlinePeople}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr valign='top' class='portlet-table-td-center'>
</c:if>
<td class='portlet-table-td-center'>
<span class='portlet-item'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${profile.uri}"/>' ><c:out value="${profile.displayName}"/></a>
</span>
<br/>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${profile.uri}"/>' >
<c:if test='${profile.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
<c:if test='${profile.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${profile.photoThumbUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
</a>
</td>
<c:if test='${status.index % 10 == 9}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.userCount % 10 != 0}'>
</tr>
</c:if>
</table>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
	<span class="portlet-rss" style="text-align:right;">
	<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" ><fmt:message key="portlet.label.more"/>...</a> 
	</span>
</c:if>
</fmt:bundle>
</body>
</html>