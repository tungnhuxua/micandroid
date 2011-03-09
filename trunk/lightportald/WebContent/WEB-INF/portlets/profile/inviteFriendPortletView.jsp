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
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}" escapeXml="false"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}" escapeXml="false"/>
</td>
</tr>
</table>
</c:if>
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width="98%">
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.to"/>: (<fmt:message key="portlet.label.email"/>)
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='to' class='portlet-form-textarea-field' rows='5' cols='40' ></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'></td>
<td class='portlet-table-td-left'>
(<fmt:message key="portlet.label.emailNote"/>)
</td>
</tr>
<br/><br/>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.inviteMessage"/>: (<fmt:message key="portlet.label.optional"/>)
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='content' class='portlet-form-textarea-field' rows='5' cols='60' ></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="document.pressed='send'" value='<fmt:message key="portlet.button.sendInvite"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
