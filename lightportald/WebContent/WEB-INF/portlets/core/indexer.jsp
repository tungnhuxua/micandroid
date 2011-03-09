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
<light:authorize role="ROLE_ADMIN"> 
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='99%'>
<tr>
<td class='portlet-table-td-left'>
<select name='type' size='1' class='portlet-form-select' style="width:200px;">
	<c:forEach var="bean" items="${sessionScope.searchTypes}" >
	<option value='<c:out value="${bean.id}"/>'
	<c:if test='${bean.defaulted == true}'>
	selected="selected"
	</c:if>
	><c:out value="${bean.desc}"/></option>
	</c:forEach>
</select>
<input type='submit' name='action' onClick="document.pressed='reIndex'" value='<fmt:message key="portlet.button.reIndex"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
</fmt:bundle>
</light:authorize>
</body>
</html>