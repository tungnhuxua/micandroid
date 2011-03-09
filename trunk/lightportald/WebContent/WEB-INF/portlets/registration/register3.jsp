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
<form name='form_<c:out value="${requestScope.responseId}"/>' action="<portlet:actionURL />">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left' width='30%'>
</td>
<td class='portlet-table-td-left' colspan='2'>
<br/>
<br/>
<fmt:message key="portlet.label.chooseChannel"/>:
</td>
</tr>
<c:forEach var="channel" items="${requestScope.channels}"  varStatus="status">
<c:if test='${status.index % 2 == 0}'>
<tr>
<td class='portlet-table-td-left' width='30%'>
</td>
</c:if>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='channels' value='<c:out value="${channel.name}"/>'
<c:if test='${channel.selected == 1}'>
checked="checked"
</c:if>
><c:out value="${channel.desc}"/></input>
</td>
<c:if test='${status.index % 2 == 1}'>
</tr>
</c:if>
</c:forEach>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-center'>
<input type='submit' name='action' onClick="document.pressed='step3'" value='<fmt:message key="portlet.button.next"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>