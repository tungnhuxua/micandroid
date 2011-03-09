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
<td class="portlet-link-left" colspan='2'>
<span class="portlet-title"><c:out value="${requestScope.news.subject}"/></span>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<span class="portlet-note"><c:out value="${requestScope.news.date}"/></span>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<br/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<c:out value="${requestScope.news.content}" escapeXml="false"/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<light:authorize role="ROLE_ADMIN">
<input type='submit' name='<c:out value="${requestScope.bulletin.id}"/>' onClick="document.pressed='delete';document.parameter=this.name;" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
</light:authorize>
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
