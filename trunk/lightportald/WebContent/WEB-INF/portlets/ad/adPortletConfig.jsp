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
<table border='0' cellpadding='0' cellspacing='0' width="98%">
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right'  width='100'>
<fmt:message key="portlet.label.country"/>:
</td>
<td class='portlet-table-td-left'  width='100'>
<select name='country' size='1' class='portlet-form-select'>
<c:forEach var="cun" items="${requestScope.countries}" >
<option value='<c:out value="${cun.desc}"/>'
<c:if test='${country == cun.desc}'>
selected="selected"
</c:if>
 ><c:out value="${cun.desc}"/></option>
</c:forEach>
</select></td>
</tr>
<tr>
<td class='portlet-table-td-right'  width='100'>
<fmt:message key="portlet.label.province"/>:
</td>
<td class='portlet-table-td-left'  width='100'>
<input type='text' name='province' value='<c:out value="${province}"/>' class='portlet-form-input-field' size='18' />
<%--
<select name='province' size='1' class='portlet-form-select'>
<option value='' ></option>
<c:forEach var="province" items="${requestScope.provinces}" >
<option value='<c:out value="${province.desc}"/>' 
<c:if test='${requestScope.province == province.desc}'>
selected="selected"
</c:if>
><c:out value="${province.desc}"/></option>
</c:forEach>
</select>
--%>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'  width='100'>
<fmt:message key="portlet.label.city"/>:
</td>
<td class='portlet-table-td-left'  width='100'>
<input type='text' name='city'  value='<c:out value="${city}" />' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'  width='100'>
</td>
<td class='portlet-table-td-left'  width='100'>
<input type='radio' name='type'  value='1' class='portlet-form-radio' checked='checked'><fmt:message key="portlet.label.adLocation1"/></input> 
<br/>
<input type='radio' name='type'  value='1' class='portlet-form-radio' ><fmt:message key="portlet.label.adLocation2"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input type='submit' name='action' onClick="document.pressed='config';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>