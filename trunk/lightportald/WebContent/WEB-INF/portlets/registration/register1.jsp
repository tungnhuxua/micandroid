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
<form name='form_<c:out value="${requestScope.responseId}"/>' action="<portlet:actionURL />" onsubmit="javascript:return validateSignUp(this);">
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-left' width='10%'>
</td>
<td class='portlet-table-td-left'>
<br/><br/>
<b><fmt:message key="portlet.registration.label.signup.message1"/></b>
<br/>
<fmt:message key="portlet.registration.label.signup.message2"/>
<br/><br/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.email"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='email' value='' class='portlet-form-input-field' size='30' onchange="validateUserId(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'/>
<img src='<%= request.getContextPath() %>/light/images/faq.gif' title='<fmt:message key="portlet.label.faq.email"/>' style='border: 0px;' width='16' height='16'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.userPassword"/>: </td>
<td class='portlet-table-td-left'>
<input type='password' name='password' value=''
class='portlet-form-input-field' size='30' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.confirmPassword"/>:</td>
<td class='portlet-table-td-left'>
<input type='password' name='confirmPassword' value=''
class='portlet-form-input-field' size='30' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.displayName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='displayName' value=''
class='portlet-form-input-field' size='30' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.myUrl"/>: </td>
<td class='portlet-table-td-left'>
<label>http://</label>
<%
int format = org.light.portal.util.PropUtil.getInt("portal.url.format");
if(format == 1){
%>
<input type='text' name='iUri' value='' class='portlet-form-input-field' size='18' onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'/><label>.<c:out value="${host}"/></label>
<%
}
%>
<%
if(format != 1){
%>
<label><c:out value="${sessionScope.org.userSpacePrefix}"/></label><input type='text' name='iUri' value='' class='portlet-form-input-field' size='18' onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'/>
<%
}
%>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'></td>
<td class='portlet-table-td-left'>
<input type="checkbox" name="accept" value="1" > 
<fmt:message key="portlet.message.accept1"/>
<span class='portlet-item'> 
<a href="javascript:void(0);" onclick="javascript:Light.portal.showTerms();"><fmt:message key="portlet.message.accept2"/></a>
</span>
<fmt:message key="portlet.message.accept3"/>
<span class='portlet-item'>  
<a href="javascript:void(0);" onclick="javascript:Light.portal.showPrivacy();"><fmt:message key="portlet.message.accept4"/></a>   
</span>
</input>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='step1'" value='<fmt:message key="portlet.button.next"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>