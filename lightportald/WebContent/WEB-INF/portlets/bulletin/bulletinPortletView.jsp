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
<light:authenticateUser> 
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left' colspan='3'>
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.postBulletin"/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.from"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.date"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.subject"/></td>
</tr>
<c:forEach var="bulletin" items="${requestScope.bulletins}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<c:if test='${bulletin.status == 0}'>
<b>
</c:if>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${bulletin.uri}"/>' ><c:out value="${bulletin.displayName}"/></a>
<c:if test='${bulletin.status == 0}'>
</b>
</c:if>
</td>
<td class='portlet-item' >
<c:if test='${bulletin.status == 0}'>
<b>
</c:if>
<c:out value="${bulletin.createDate}"/>
<c:if test='${bulletin.status == 0}'>
</b>
</c:if>
</td>
<td class='portlet-item' >
<c:if test='${bulletin.status == 0}'>
<b>
</c:if>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','bulletinId=<c:out value="${bulletin.id}"/>');"><c:out value="${bulletin.subject}"/></a>
<c:if test='${bulletin.status == 0}'>
</b>
</c:if>
</td>
<td class='portlet-table-td-right'>
<input type="image" src="light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${bulletin.id}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</td>
</tr>
</c:forEach>
</table>
</form>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>