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
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error'>
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.title"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input type='text' name='title'  value='<c:out value="${requestScope.title}"/>' class='portlet-form-input-field' style='width:98%;' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.content"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='content' class='portlet-form-textarea-field' rows='5' cols='50' ><c:if test='${requestScope.content != null}'><c:out value="${content}"/></c:if></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='save';document.resetLastAction='1';" value='<fmt:message key="portlet.button.publish"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>