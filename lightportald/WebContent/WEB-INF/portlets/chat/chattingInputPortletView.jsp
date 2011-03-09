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
<form action="<portlet:actionURL />">

<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<input type='hidden' name='chattingId' value='<c:out value="${requestScope.chattingId}"/>'/>
<input type='hidden' name='displayName' value='<c:out value="${requestScope.displayName}"/>'/>
<input type='text' name='chat' class='portlet-form-input-field' size='80'
    onkeypress="document.currentForm=this.form; document.pressed='send'; return keyDownChat(event);" />
<input type='submit' name='action' onClick="document.currentForm=this.form;document.pressed='send'" value='<fmt:message key="portlet.button.send"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:showInviteList('<c:out value="${requestScope.chattingId}"/>');" value='<fmt:message key="portlet.button.invite"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>