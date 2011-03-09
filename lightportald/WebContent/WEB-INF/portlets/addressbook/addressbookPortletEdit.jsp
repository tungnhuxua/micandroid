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
<table border='0' cellpadding='0' cellspacing='0' width="98%" style='padding-top:10px;'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.fullName"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='fullName'  value='<c:out value="${contact.fullName}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<span title='<fmt:message key="portlet.label.group.tip"/>'>
<fmt:message key="portlet.label.group"/>:
</span>
</td>
<td class='portlet-table-td-left' >
<input type='text' name='group'  value='<c:out value="${contact.addressGroup}" />' class='portlet-form-input-field' size='40' /> 
<c:if test='${requestScope.groups != null}'>
<fmt:message key="portlet.label.or"/>
<c:forEach var="grp" items="${requestScope.groups}">
<input TYPE='radio' name='groups' value='<c:out value="${grp}"/>' ><c:out value="${grp}"/></input>
</c:forEach>
</c:if>
<input type='hidden' name='id'  value='<c:out value="${contact.id}" />'  /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.homePhone"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='homePhone'  value='<c:out value="${contact.homePhone}" />' class='portlet-form-input-field' size='40' /> 
<input TYPE='radio' name='primaryPhone' value='1' checked="checked"><fmt:message key="portlet.label.primaryPhone"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.workPhone"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='workPhone'  value='<c:out value="${contact.workPhone}" />' class='portlet-form-input-field' size='40' /> 
<input TYPE='radio' name='primaryPhone' value='2' ><fmt:message key="portlet.label.primaryPhone"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.mobilePhone"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='mobilePhone'  value='<c:out value="${contact.mobilePhone}" />' class='portlet-form-input-field' size='40' /> 
<input TYPE='radio' name='primaryPhone' value='3' ><fmt:message key="portlet.label.primaryPhone"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.personalEmail"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='personalEmail'  value='<c:out value="${contact.personalEmail}" />' class='portlet-form-input-field' size='40' /> 
<input TYPE='radio' name='primaryEmail' value='1' checked="checked"><fmt:message key="portlet.label.primaryEmail"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.workEmail"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='workEmail'  value='<c:out value="${contact.workEmail}" />' class='portlet-form-input-field' size='40' /> 
<input TYPE='radio' name='primaryEmail' value='2' ><fmt:message key="portlet.label.primaryEmail"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.homePage"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='homePage'  value='<c:out value="${contact.homePage}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.address"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='address'  value='<c:out value="${contact.address}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.city"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='city'  value='<c:out value="${contact.city}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.province"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='province'  value='<c:out value="${contact.province}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.country"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='country'  value='<c:out value="${contact.country}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='20%'>
<fmt:message key="portlet.label.postalCode"/>:
</td>
<td class='portlet-table-td-left' >
<input type='text' name='postalCode'  value='<c:out value="${contact.postalCode}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input type='submit' name='action' onClick="document.pressed='save';document.resetLastAction='1'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
