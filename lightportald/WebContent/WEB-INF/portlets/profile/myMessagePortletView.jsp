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
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<c:if test='${requestScope.messageCount != null}'>
<tr class='portlet-table-td-left'>
<td class='portlet-item' colspan='2'>
<a href='javascript:;' onclick="javascript:Light.showInboxMessage();" >
<fmt:message key="portlet.label.newMessage"/>(<c:out value="${requestScope.messageCount}"/>)
</a>
</td>
</tr>
</c:if>
<c:if test='${requestScope.friendRequestCount != null}'>
<tr class='portlet-table-td-left'>
<td class='portlet-item' colspan='2'>
<a href='javascript:;' onclick="javascript:Light.showFriendRequest();" >
<fmt:message key="portlet.label.newFriendRequest"/>(<c:out value="${requestScope.friendRequestCount}"/>)
</a>
</td>
</tr>
</c:if>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='40%' style='padding: 5 5 0 5;'>
<a href='javascript:;' onclick="javascript:Light.showInboxMessage();" ><img src='<%= request.getContextPath() %>/light/images/inbox.gif' style='border: 0px;' height='16' width='16' align="top"/>
<fmt:message key="portlet.label.inbox"/></a>
</td>
<td class='portlet-item' width='60%' style='padding: 5 5 0 5;'>
<a href='javascript:;' onclick="javascript:Light.showFriendRequest();" ><img src='<%= request.getContextPath() %>/light/images/friend.gif' style='border: 0px;' height='16' width='16' align="top"/>
<fmt:message key="portlet.label.friendRequest"/></a>
</td>
</tr>
<tr  class='portlet-table-td-left'>
<td class='portlet-item' width='40%' style='padding: 5 5 5 5;'>
<a href='javascript:;' onclick="javascript:Light.showSentMessage();" ><img src='<%= request.getContextPath() %>/light/images/outbox.jpg' style='border: 0px;' height='16' width='16' align="top"/>
<fmt:message key="portlet.label.sent"/></a>
</td>
<td class='portlet-item' width='60%' style='padding: 5 5 5 5;'>
<a href='javascript:;' onclick="javascript:Light.showInviteFriend();" ><img src='<%= request.getContextPath() %>/light/images/newFriend.gif' style='border: 0px;' height='16' width='16' align="top"/>
<fmt:message key="portlet.label.inviteFriend"/></a>
</td>
</tr>
</table>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>