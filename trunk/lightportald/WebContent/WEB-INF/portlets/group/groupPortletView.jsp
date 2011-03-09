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
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
<br/>
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
<br/>
</c:if>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<light:authenticateUser> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT' windowState='MAXIMIZED'><portlet:param name='type' value='create'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.createGroup"/></a>
</td>
<td class='portlet-link-left' >
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT' windowState='MAXIMIZED'><portlet:param name='type' value='join'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.joinGroup"/></a>
</td>
</tr>
</table>
</light:authenticateUser>
<c:if test='${requestScope.groupCount > 0}'>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.youJoin"/> <c:out value="${requestScope.groupCount}"/> <fmt:message key="portlet.label.groups"/>
</td>
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='95%' >
<light:getHost/>
<c:forEach var="group" items="${requestScope.userGroups}" >
<tr>
<td class='portlet-table-td-center' width= '80'>
<jsp:useBean id="group" scope="page" class="org.light.portlets.group.UserGroup"/>
<light:avatarUrl name="<%= group.getDisplayName() %>" url="<%= group.getUri() %>" type="<%= org.light.portal.util.Constants._OBJECT_TYPE_GROUP %>"/> 
<light:avatar name="<%= group.getDisplayName() %>" url="<%= group.getUri() %>" pictureUrl="<%= group.getPhotoUrl() %>" width="81" height="81" type="<%= org.light.portal.util.Constants._OBJECT_TYPE_GROUP %>"/> 
</td>
<light:authenticateUser> 
<td class='portlet-table-td-left'>
<c:if test='${group.openJoin == 1 || group.memberInvite == 1}'>
<input type="image" src="<%= request.getContextPath() %>/light/images/wins.gif" title='<fmt:message key="portlet.label.inviteGroup"/>' style='border: 0px;' align="top" height='16' width='16' name="" onClick="javascript:inviteOthers(event,'<c:out value="${group.groupId}"/>','<c:out value="${requestScope.responseId}"/>');"/>
</c:if>
<input type="image" src="<%= request.getContextPath() %>/light/images/group.gif" title='<fmt:message key="portlet.label.viewGroupMembers"/>' style='border: 0px;' align="top" height='16' width='16' name="" onClick="javascript:viewGroupMembers(event,'<c:out value="${group.groupId}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<input type="image" src="<%= request.getContextPath() %>/light/images/picture.gif" title='<fmt:message key="portlet.label.viewGroupPictures"/>' style='border: 0px;' align="top" height='16' width='16' name="" onClick="javascript:viewGroupPictures(event,'<c:out value="${group.groupId}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<input type="image" src="<%= request.getContextPath() %>/light/images/losses.gif" title='<fmt:message key="portlet.label.resignGroup"/>' style='border: 0px;' align="top" height='16' width='16' name="" onClick="javascript:resign(event,'<c:out value="${group.groupId}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<light:authorizeGroupMember type='leader'>
<input type="image" src="<%= request.getContextPath() %>/light/images/edit.gif" title='<fmt:message key="portlet.label.editGroup"/>' style='border: 0px;' align="top" height='11' width='11' name="" onClick="javascript:editGroupProfile(event,'<c:out value="${group.groupId}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" title='<fmt:message key="portlet.button.delete"/>' style='border: 0px;' align="top" height='11' width='11' onClick="javascript:deleteGroupProfile(event,'<c:out value="${group.groupId}"/>','<c:out value="${group.displayName}"/>','<c:out value="${requestScope.responseId}"/>');"/>
</light:authorizeGroupMember>
</td>
</light:authenticateUser>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</body>
</html>