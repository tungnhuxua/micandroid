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
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin-bottom:5px;'>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 5px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showSendMessage(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${requestScope.user.id}"/>','<c:out value="${requestScope.user.name}"/>');" ><img src='<%= request.getContextPath() %>/light/images/inbox.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.sendMessage"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 5px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showForwardToFriends(event,'<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/friend.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.forwardToFriend"/></a>
</td>
</tr>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 5px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showAddToFriend(event,'<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/newFriend.gif' style='border: 0px;' height='16' width='16' align="top"/><fmt:message key="portlet.label.addToFriend"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 5px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showAddToFavorites(event,'<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/add_favorite.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addToFavorites"/></a>
</td>
</tr>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 0px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showBlockUser(event,'<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/block.png' style='border: 0px;' height='15' width='15' align="top"/><fmt:message key="portlet.label.blockUser"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 0px 0px 0px 5px;'>
<a href='javascript:;' onclick="javascript:Light.showInstantMessage(event,'<c:out value="${requestScope.responseId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/chat.gif' style='border: 0px;' height='12' width='12' align="top"/><fmt:message key="portlet.label.instantMessage2"/></a>
</td>
</tr>
</table>
</fmt:bundle>
</body>
</html>