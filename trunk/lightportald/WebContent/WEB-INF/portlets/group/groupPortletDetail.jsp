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
<form action="<portlet:actionURL/>">
<c:if test='${requestScope.groupCount != null}'>
<span class='portlet-msg-info'>
<c:out value="${requestScope.groupCount}"/> <fmt:message key="portlet.label.groups"/>
</span>
</c:if>
<br/>
<c:if test='${requestScope.groups != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<c:forEach var="group" items="${requestScope.groups}" >
<tr VALIGN='TOP'>
<td class='portlet-table-td-left' width='50%'>
<table>
<tr VALIGN='TOP'>
<td class='portlet-table-td-center'>
<c:if test='${group.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;' width='75' height='75'/>
</c:if>
<c:if test='${group.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${group.photoUrl}"/>' style='border: 0px;'  width='<c:out value="${group.photoSmallWidth}"/>' height='<c:out value="${group.photoSmallHeight}"/>'/>
</c:if>
<c:if test='${group.caption != null}'>
<br/>
<c:out value="${group.caption}"/>
</c:if>
<br/> 
<a href='http://www.<c:out value="${sessionScope.org.webId}"/><c:out value="${sessionScope.org.groupPrefix}"/><c:out value="${group.uri}"/>' ><c:out value="${group.displayName}"/></a>
</td>
<td class='portlet-table-td-left'>
<span class="portlet-rss" >   
<fmt:message key="portlet.label.category"/>:
<b><c:out value="${group.categoryName}"/></b>
<br/><br/>
<fmt:message key="portlet.label.type"/>:
<c:if test='${group.openJoin == 0}'>
<fmt:message key="portlet.label.privateMemberShip"/>
</c:if>
<c:if test='${group.openJoin == 1}'>
<fmt:message key="portlet.label.publicMemberShip"/>
</c:if>
<br/>
<fmt:message key="portlet.label.founded"/>:<c:out value="${group.date}"/>
<br/>
<fmt:message key="portlet.label.located"/>:<c:out value="${group.city}"/>,<c:out value="${group.province}"/> <c:out value="${group.country}"/>
<br/>
<fmt:message key="portlet.label.groupMembers"/>:<c:out value="${group.members}"/>
<br/>
</span>
</td>
</tr>
</table>
</td>
<td class='portlet-table-td-left' width='40%' >
<table>
<tr VALIGN='TOP'>
<td class='portlet-table-td-left'>
<input type="button"  value='<fmt:message key="portlet.button.join"/>' class='portlet-form-button' 
onclick="javascript:joinToGroup(event,'<c:out value="${group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
</td>
</tr>
</table>
</td>
</tr>
</c:forEach>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>