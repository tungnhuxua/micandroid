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
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center' width='100%'>
<a href='javascript:void(0)' onclick="<portlet:renderURL><portlet:param name='previous' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px' /></a>						
<a href='javascript:void(0)' onclick="<portlet:renderURL><portlet:param name='next' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/next.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px' /></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="picture" items="${requestScope.showPictures}" varStatus="status">
<c:if test='${status.index % columnNumber == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-center'>
<c:if test='${picture.httpUrl}'>
<a href='javascript:void(0);' onclick="javascript:viewMaxPicture('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>');"><img src='<c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.smallWidth}"/>' height='<c:out value="${picture.smallHeight}"/>'/></a>
</c:if>
<c:if test='${!picture.httpUrl}'>
<a href='javascript:void(0);' onclick="javascript:viewMaxPicture('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>');"><img src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.smallWidth}"/>' height='<c:out value="${picture.smallHeight}"/>'/></a>
</c:if>
<br/>
<c:out value="${picture.caption}"/>
</td>
<c:if test='${status.index % columnNumber == columnNumber - 1}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.showNumber % columnNumber != 0}'>
</tr>
</c:if>
</table>
</form>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null}'>
<span class="portlet-rss" style="text-align:right;">
<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" >more...</a> 
</span>
</c:if>
</fmt:bundle>
</body>
</html>