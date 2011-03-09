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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left' >
<fmt:message key="portlet.label.searchGroup"/>:
</td>
</tr>
</table>
<br/>
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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.groupName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='displayName' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.category"/>: </td>
<td class='portlet-table-td-left'>
<select name='categoryId' value='1' size='1' class='portlet-form-select'>
<c:forEach var="gc" items="${requestScope.groupCategories}" >
<option value='<c:out value="${gc.id}"/>'><c:out value="${gc.displayName}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='country' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.groupUrl"/>: </td>
<td class='portlet-table-td-left'>
http://www.<c:out value="${sessionScope.org.webId}"/><c:out value="${sessionScope.org.groupPrefix}"/>
<input type='text' name='uri' value='' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='search'" value='<fmt:message key="portlet.button.search"/>' class='portlet-form-button' />
</td>
</tr>
</table>
<c:if test='${requestScope.searchCount != null}'>
<c:out value="${requestScope.searchCount}"/> <fmt:message key="portlet.message.searchResult"/>
</c:if>
<br/>
<c:if test='${requestScope.searchGroups != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:forEach var="group" items="${requestScope.searchGroups}" >
<tr>
<td class='portlet-table-td-left'>
<a href='http://www.<c:out value="${sessionScope.org.webId}"/><c:out value="${sessionScope.org.groupPrefix}"/><c:out value="${group.uri}"/>' ><c:out value="${group.displayName}"/></a>
</td>
<td class='portlet-table-td-right'>
<input type="button"  value='<fmt:message key="portlet.button.join"/>' class='portlet-form-button' 
onclick="javascript:joinToGroup(event,'<c:out value="${group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
</td>
</tr>
</c:forEach>
</table>
</c:if>
</form>
</fmt:bundle>
</body>
</html>