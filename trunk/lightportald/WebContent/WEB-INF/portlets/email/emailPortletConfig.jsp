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
<light:authenticateOwner> 
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
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.fullName"/>
</td>
<td class='portlet-table-td-left'>
<input type='text' name='fullName'  value='<c:out value="${requestScope.fullName}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.email"/>
</td>
<td class='portlet-table-td-left'>
<input type='text' name='email'  value='<c:out value="${requestScope.email}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.password"/>:
</td>
<td class='portlet-table-td-left'>
<input type='password' name='password' value='<c:out value="${requestScope.password}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.incomingType"/>:
</td>
<td class='portlet-table-td-left'>
<select name='incomingType' size='1'  class='portlet-form-select' onchange="javascript:changeEmailConfig(this.form);">
<c:if test='${requestScope.incomingType == "pop3"}'>
<option selected='selected' value='pop3'>POP3</option>
</c:if>
<c:if test='${requestScope.incomingType != "pop3"}'>
<option value='pop3'>POP3</option>
</c:if>
<c:if test='${requestScope.incomingType == "imap"}'>
<option selected='selected' value='imap'>IMAP</option>
</c:if>
<c:if test='${requestScope.incomingType != "imap"}'>
<option value='imap'>IMAP</option>
</c:if>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.incoming"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='incoming'  value='<c:out value="${requestScope.incoming}"/>' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.port"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='incomingPort'  value='<c:out value="${requestScope.incomingPort}"/>' class='portlet-form-input-field' size='4' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.ssl"/>:
</td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='incomingSSL'  value='1' class='portlet-form-checkbox' onchange="javascript:changeEmailConfig(this.form);"
<c:if test='${requestScope.incomingSSL == "1"}'>
checked='checked'
</c:if>
/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.outgoing"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='outgoing'  value='<c:out value="${requestScope.outgoing}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.port"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='outgoingPort'  value='<c:out value="${requestScope.outgoingPort}"/>' class='portlet-form-input-field' size='4' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.ssl"/>:
</td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='outgoingSSL'  value='1' class='portlet-form-checkbox' onchange="javascript:changeEmailConfig(this.form);"
<c:if test='${requestScope.outgoingSSL == "1"}'>
checked='checked'
</c:if>
/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
Show Items:
</td>
<td class='portlet-table-td-left'>
<select name='number' size='1'  class='portlet-form-select'>
<c:forEach var="i" begin="1" end="20" step="1">
<c:if test='${number == i}'>
<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
<c:if test='${number != i}'>
<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input type='submit' name='action' onClick="document.pressed='config';document.resetLastAction='1'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>						
</table>		
</form>
</fmt:bundle>
</light:authenticateOwner>
</body>
</html>