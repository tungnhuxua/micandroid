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
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.userHas"/> <c:out value="${requestScope.pictureCount}"/> <fmt:message key="portlet.label.pictureRank"/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width= '220' >
<light:authenticateVisitor> 
<c:if test='${requestScope.pictureCount > 0}'>
<tr>
<td class='portlet-table-td-center'>
<input type='radio' name='score' value='1' />1
<input type='radio' name='score' value='2' />2
<input type='submit' onClick="document.pressed='rank';" value='<fmt:message key="portlet.button.rank"/>' class='portlet-form-button' />
<br/><br/>
</td>
</tr>
</c:if>
</light:authenticateVisitor>
<c:forEach var="picture" items="${requestScope.userPictures}" >
<tr>
<td class='portlet-table-td-center'>
<a href='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' target="_blank"><img src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.pictureWidth}"/>' height='<c:out value="${picture.pictureHeight}"/>'/></a>
<br/>
<c:out value="${picture.caption}"/>
<br/>
<input type='radio' name='pictureId' value='<c:out value="${picture.id}"/>' />
</td>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</body>
</html>