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
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
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
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.youHave"/> <c:out value="${requestScope.blockCount}"/> <fmt:message key="portlet.label.blockUser"/>.
</td>
</tr>
</table>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width= '220'>
<c:forEach var="block" items="${requestScope.userBlocks}" >
<tr>
<td class='portlet-table-td-center'>
<c:if test='${block.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
<c:if test='${block.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${block.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
<br/>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${block.uri}"/>' ><c:out value="${block.displayName}"/></a>
<light:authenticateUser> 
<br/>
<input type='radio' name='blockId' value='<c:out value="${block.id}"/>' />
</light:authenticateUser>
</td>
</tr>
</c:forEach>
<light:authenticateUser> 
<c:if test='${requestScope.blockCount > 0}'>
<tr>
<td class='portlet-table-td-center'>
<input type='submit' onClick="document.pressed='unblock';" value='<fmt:message key="portlet.button.unblock"/>' class='portlet-form-button' />
</td>
</tr>
</c:if>
</light:authenticateUser>
</table>
</form>
</fmt:bundle>
</body>
</html>