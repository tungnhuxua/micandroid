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
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.createMessage"/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<c:if test='${requestScope.type != "sent"}'>
<th class='portlet-table-td-left'><fmt:message key="portlet.label.from"/></th>
</c:if>
<c:if test='${requestScope.type == "sent"}'>
<th class='portlet-table-td-left'><fmt:message key="portlet.label.to"/></th>
</c:if>
<th class='portlet-table-td-center'><fmt:message key="portlet.label.date"/></th>
<th class='portlet-table-td-left'><fmt:message key="portlet.label.subject"/></th>
</tr>
<c:forEach var="message" items="${requestScope.messages}" >
<tr class='portlet-table-td-left'>

<td class='portlet-item'>
<c:if test='${requestScope.type != "sent"}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${message.fromUri}"/>' ><c:out value="${message.fromDisplayName}"/></a>
</c:if>
<c:if test='${requestScope.type == "sent"}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${message.toUri}"/>' ><c:out value="${message.toDisplayName}"/></a>
</c:if>
</td>
<td class='portlet-item' >
<c:out value="${message.date}"/>
</td>
<td class='portlet-item' >
<c:if test='${message.status == 0 && requestScope.type != "sent"}'><b>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','messageId=<c:out value="${message.id}"/>');"><c:out value="${message.subject}"/></a>
</b>
</c:if>
<c:if test='${message.status != 0 || requestScope.type == "sent"}'>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','messageId=<c:out value="${message.id}"/>');"><c:out value="${message.subject}"/></a>
</c:if>
</td>

<td class='portlet-table-td-right'>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${message.id}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</td>
</tr>
</c:forEach>
</table>
</form>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>