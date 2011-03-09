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
<table border='0' cellpadding='0' cellspacing='0'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.adViewType"/>:
</td>
<td class='portlet-table-td-left'>
<select name='newType' size='1'  class='portlet-form-select'>
<c:if test='${requestScope.type == "1"}'>
<option selected='selected' value='1'><fmt:message key="portlet.message.newestAd"/></option>
</c:if>
<c:if test='${requestScope.type != "1"}'>
<option  value='1'><fmt:message key="portlet.message.newestAd"/></option>
</c:if>
<c:if test='${requestScope.type == "2"}'>
<option selected='selected' value='2'><fmt:message key="portlet.message.popularAd"/></option>
</c:if>
<c:if test='${requestScope.type != "2"}'>
<option value='2'><fmt:message key="portlet.message.popularAd"/></option>
</c:if>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
Show Items:
</td>
<td class='portlet-table-td-left'>
<select name='items' size='1'  class='portlet-form-select'>
<c:forEach var="i" begin="1" end="50" step="1">
<c:if test='${requestScope.showNumber == i}'>
<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
<c:if test='${requestScope.showNumber != i}'>
<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="document.pressed='config'" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
