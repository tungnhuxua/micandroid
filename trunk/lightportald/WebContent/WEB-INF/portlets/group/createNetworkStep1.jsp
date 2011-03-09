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
<form name='form_<c:out value="${requestScope.responseId}"/>' >
<div class="portal-header" style="padding-top:0px;">
<h1><fmt:message key="portlet.network.create.header"/></h1>
</div>
<table border='0' cellpadding='0' cellspacing='0' width='100%' >
<tr valign='middle'>
<td class='portlet-table-td-right' width= '30%'>
<label for="mainFocus" class='portlet-table-title'><b><fmt:message key="portlet.network.create.name"/></b></label>
</td>
<td class='portlet-table-td-left' width= '40%'>
<input type="text" name="name" id="mainFocus" value="" class='portlet-form-input-field' style='width:100%;font-size:22px;'>
</td>
<td class='portlet-table-td-left' width= '30%' colspan='2'>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right' width= '30%'></td>
<td class='portlet-table-td-left' width= '40%'>
<span class="portlet-footer"><font color='#777777'><i><fmt:message key="portlet.network.create.name.tip"/></i></font></span>
</td>
<td class='portlet-table-td-left' width= '30%' colspan='2'>
<br/><br/>
</td>
</tr>
<tr valign='middle'>
<td class='portlet-table-td-right' width= '30%'>
<label for="create-network-address" class='portlet-table-title'><b><fmt:message key="portlet.network.create.url"/></b></label>
</td>
<td class='portlet-table-td-left' width= '40%'>
<input type="text" name="iUri" id="create-network-address" value="" class='portlet-form-input-field' style='width:100%;font-size:22px;' onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'>
</td>
<td class='portlet-table-td-left' width= '10%'>
.<c:out value="${sessionScope.org.webId}"/>
</td>
<td class='portlet-table-td-left' width= '20%'>
<img src="<%= request.getContextPath() %>/light/images/create.gif" alt="Create" style='border: 0px; cursor: pointer; cursor: hand;' align="top" 
	onclick="javascript:Light.createNetwork(null,'<c:out value="${requestScope.responseId}"/>');"/>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right' width= '30%'></td>
<td class='portlet-table-td-left' width= '40%'>
<span class="portlet-footer"><font color='#777777'><i><fmt:message key="portlet.network.create.url.tip"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></i></font></span>
</td>
<td class='portlet-table-td-left' width= '30%' colspan='2'>
<br/><br/>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>