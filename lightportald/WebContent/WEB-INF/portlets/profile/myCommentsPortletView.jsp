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
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left' colspan='3'>
<a href='javascript:;' onclick="javascript:addObjectComment(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${user.id}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_USER %>);" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addPost"/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<light:getHost/>
<c:forEach var="comment" items="${requestScope.userComments}" >
<tr valign='top'>
<td class='portlet-table-td-center' width='70px;'>
<jsp:useBean id="comment" scope="page" class="org.light.portal.model.UserComment"/>
<light:avatar entityId="<%= comment.getUserId() %>" name="<%= comment.getDisplayName() %>" url="<%= comment.getUri() %>" pictureUrl="<%= comment.getPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left'>
<light:avatarUrl entityId="<%= comment.getUserId() %>" name="<%= comment.getDisplayName() %>" url="<%= comment.getUri() %>" />: 
<c:out value="${comment.comment}"/>
<br/>
<span class='portlet-note'><c:out value="${comment.date}"/></span>
</td>
<light:authenticateOwner> 
<td class='portlet-table-td-left' width='5%'>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${comment.id}'/>" title='<fmt:message key="portlet.button.delete"/>' style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;decreasePortletTitle('<c:out value="${requestScope.responseId}"/>');"/>
</td>
</light:authenticateOwner>
</tr>
</c:forEach>
</table>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
<span class="portlet-rss" style="text-align:right;">
<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" ><fmt:message key="portlet.lable.more"/>...</a> 
</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>