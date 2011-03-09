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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit="javascript:return validateMessage(this);">
<table border='0' cellpadding='0' cellspacing='0' width="98%">
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left' width= '60px;' >
<fmt:message key="portlet.label.to"/>:
</td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.toUser != null}'>
<input type='text' name='toName'  value='<c:out value="${requestScope.toUserName}"/>' class='portlet-form-input-field' size='50' readonly='true' /> 
<input type='hidden' name='to'  value='<c:out value="${requestScope.toUser}"/>' /> 
<input type='hidden' name='toUserName'  value='<c:out value="${requestScope.toUserName}"/>' /> 
</c:if>
<c:if test='${requestScope.toUser == null}'>
<select name='to' size='1' class='portlet-form-select'>
<c:forEach var="buddy" items="${requestScope.buddys}" >
<option value='<c:out value="${buddy.buddyUserId}"/>'><c:out value="${buddy.displayName}"/></option>
</c:forEach>
</select>
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.subject"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='subject'  value='<c:out value="${requestScope.subject}"/>' class='portlet-form-input-field' size='50' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.content"/>:
<input type='radio' name='format' value='0' class='portlet-form-radio'
<c:if test='${format == null || format == 0}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.format.html"/></input> 
<input type='radio' name='format' value='1' class='portlet-form-radio'
<c:if test='${format == 1}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.format.text"/></input> 
<light:authorizeEditor>
<input type='checkbox' name='htmlEditor' value='1' onchange="<portlet:actionURL windowState='maximized' portletMode='EDIT'/>" 
<c:if test='${showHtmlEditor != null}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.htmlEditor"/></input>
</light:authorizeEditor>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<c:if test='${showHtmlEditor == null}'>
<textarea name='content' class='portlet-form-textarea-field' rows='10' cols='50' ><c:if test='${requestScope.content != null}'><c:out value="${content}" /></c:if></textarea>
</c:if>
<c:if test='${showHtmlEditor != null}'>
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.editor.page") %>'>
	<jsp:param name='text' value='<%= (request.getAttribute("content") == null) ? "" : request.getAttribute("content") %>' />
</jsp:include>
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.send"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
