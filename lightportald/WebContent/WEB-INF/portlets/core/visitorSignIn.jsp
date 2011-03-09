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
<form name="form_<c:out value="${requestScope.responseId}"/>">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<h4>You Must Be Logged-In to do That!</h4>
<c:out value="${sessionScope.org.webId}"/> is FREE, But You Must Be a Member to Use That Feature.
</td>
</tr>
<tr>
<td class='portlet-link' colspan='3'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=signUp');" ><fmt:message key="portlet.label.signUp"/></a>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><LABEL FOR='plUserId' ACCESSKEY='U'><fmt:message key="portlet.label.userId"/>: </LABEL></td>
<td class='portlet-table-td-left'>
<input type='text' name='email' value='<c:out value="${requestScope.userId}"/>' class='portlet-form-input-field' size='20' /> 
</td>
<td>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.userPassword"/>: </td>
<td class='portlet-table-td-left'>
<input type='password' name='password' value='' class='portlet-form-input-field' size='20' onkeypress="return keyDownLogin(event,'<c:out value="${requestScope.responseId}"/>');"/> 
</td>
<td>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'></td>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='rememberMe'  value='1'><fmt:message key="portlet.message.rememberMe"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'></td>
<td class='portlet-table-td-left'>
<input name='login' type='button' value='<fmt:message key="portlet.button.login"/>' class='portlet-form-button'  onclick="javascript:loginPortal('<c:out value="${requestScope.responseId}"/>');" />
</td>
</tr>
<tr>
<td class='portlet-link' colspan='3'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=forgot');" ><fmt:message key="portlet.label.forgotPassword"/></a>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>