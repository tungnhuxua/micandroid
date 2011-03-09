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
<light:authorize role="ROLE_ADMIN"> 
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<c:if test='${sessionScope.org.id == 1}'>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' colspan='3'>
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT' windowState='MAXIMIZED'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addOrg"/></a>
</td>
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr class='portlet-table-td-left' valign='top'>
<td class='portlet-item'>
<fmt:message key="portlet.label.webId"/>
</td>
<td class='portlet-item'>
<fmt:message key="portlet.label.virtualHost"/>
</td>
<td class='portlet-item'>
<fmt:message key="portlet.label.mx"/>
</td>
</tr>

<c:forEach var="org" items="${requestScope.orgs}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','orgId=<c:out value="${org.id}"/>');"><c:out value="${org.webId}"/></a>
</td>
<td class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','orgId=<c:out value="${org.id}"/>');"><c:out value="${org.virtualHost}"/></a>
</td>
<td class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','orgId=<c:out value="${org.id}"/>');"><c:out value="${org.mx}"/></a>
</td>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</light:authorize>
</body>
</html>