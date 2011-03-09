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
<form action="<portlet:actionURL portletMode='VIEW'/>">
<light:authenticateUser> 
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left'>
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addGame"/></a>
</td>
</tr>
</table>
</light:authenticateUser>
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<c:forEach var="game" items="${requestScope.games}" >
<tr>
<td >
<span class='portlet-item' onmouseover="javascript:showDesc(event,'<c:out value="${game.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='javascript:;' onclick="javascript:hideDesc();Light.executeRender('<c:out value="${requestScope.responseId}"/>','config','maximized','id=<c:out value="${game.id}"/>');"><c:out value="${game.title}"/></a>
</span>
</td>
<td class='portlet-table-td-right'>
<light:authorize role="ROLE_ADMIN"> 
<input type="image" src="light/images/edit.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${game.id}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="light/images/deleteLink.gif" name="<c:out value='${game.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;"/>
</light:authorize>
</td>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</body>
</html>