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
<td class='portlet-link-left'>
<fmt:message key="portlet.label.thisGroup"/> <c:out value="${requestScope.memberCount}"/> <fmt:message key="portlet.label.members"/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' style="margin-top:10px">
<c:forEach var="buddy" items="${requestScope.groupMembers}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-center' width=80px'>
<jsp:useBean id="buddy" scope="page" class="org.light.portlets.group.UserGroup"/>
<light:avatarUrl name="<%= buddy.getUserDisplayName() %>" url="<%= buddy.getUserUri() %>" /> 
<light:avatar name="<%= buddy.getUserDisplayName() %>" url="<%= buddy.getUserUri() %>" pictureUrl="<%= buddy.getUserPhotoUrl() %>" width="75" height="75" /> 
<br/>
<span class='portlet-item'>
<c:if test='${buddy.userCurrentStatusId == 1}'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px'  align="bottom" alt=''/>
</c:if>
<br/>
<c:if test='${buddy.userCurrentStatusId == 1 }'>
<a href='javascript:void(0)' onclick="javascript:showInstantMessageMember(event,'<c:out value="${buddy.userId}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.instantMessage"/></a>
<br/>
</c:if>
<c:if test='${buddy.userCurrentStatusId != 1}'>
<br/>
</c:if>
<a href='javascript:void(0)' onclick="javascript:showSendMessageMember('<c:out value="${requestScope.responseId}"/>','<c:out value="${buddy.userId}"/>','<c:out value="${buddy.userDisplayName}"/>',event);" ><fmt:message key="portlet.label.sendMessage"/></a>
</span>
</td>
<c:if test='${status.index % 10 == 9}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.memberCount % 10 != 0}'>
</tr>
</c:if>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-right'>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-center'>
<b><fmt:message key="portlet.label.groupLeader"/></b>
<jsp:useBean id="moderator" scope="request" class="org.light.portal.model.User"/>
<light:avatarUrl name="<%= moderator.getDisplayName() %>" url="<%= moderator.getUri() %>" />
</td>
</tr>
</table>
</fmt:bundle>
</body>
</html>