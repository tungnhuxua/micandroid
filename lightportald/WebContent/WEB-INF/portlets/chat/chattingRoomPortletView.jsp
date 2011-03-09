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
<form action="<portlet:actionURL />">
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addChatRoom"/></a>
</td>
</tr>
</table>
<c:if test="${requestScope.chattings != null && not empty requestScope.chattings}">
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr valign='top'>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topic"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.onlineUsers"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topicStarter"/></td>
</tr>

<c:forEach var="chatting" items="${requestScope.chattings}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item' >
<a href='javascript:;' title='<fmt:message key="portlet.label.enterChatRoom"/>' onclick="javascript:enterChattingRoom('<c:out value="${chatting.id}"/>','<c:out value="${chatting.name}"/>');">
<img src='<%= request.getContextPath() %>/light/images/chatroom.gif' style='border: 0px;'  align="top" /><br/>
<c:out value="${chatting.name}"/></a>
<light:authorize role="ROLE_ADMIN">
<input type="image" src="<%= request.getContextPath() %>/light/images/edit.gif" style='border: 0px;' height='13' width='11' name="<c:out value='${chatting.id}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${chatting.id}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</light:authorize>
</td>
<td class='portlet-item' >
<c:out value="${chatting.totalPosts}"/>
</td>
<td class='portlet-item'>
<c:out value="${chatting.totalUsers}"/>
</td>
<td class='portlet-item'>
<table>
<tr>
<td>
<jsp:useBean id="chatting" scope="page" class="org.light.portlets.chat.Chatting"/>
<light:avatar name="<%= chatting.getDisplayName() %>" url="<%= chatting.getUri() %>" pictureUrl="<%= chatting.getPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left'>
<span class='portlet-note'><c:out value="${chatting.date}"/>
<br/>by </span>
<light:avatarUrl name="<%= chatting.getDisplayName() %>" url="<%= chatting.getUri() %>" /> 
</td>
</tr>
</table>
</td>
</tr>
</c:forEach>
<tr>
<td class='portlet-table-td-right' colspan='4'></td>
</tr>
</table>
</c:if>

<c:if test='${requestScope.error != null}'>
<span class="portlet-item">
<c:out value="${requestScope.error}"/>
</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>