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
<table border='0' cellpadding='0' cellspacing='0' style='margin:20px 0 0 10px;'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='title'  value='<c:out value="${title}"/>' class='portlet-form-input-field' style='width:98%;' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' >
<fmt:message key="portlet.label.content"/>:
</td>
<td class='portlet-table-td-left'>
<select name='category' size='1'  class='portlet-form-select' style='width:200px;'>
<option value='0' 
<c:if test='${0 == requestScope.category}'>
selected="selected"
</c:if>
><fmt:message key="portlet.label.all"/></option>
<c:forEach var="bean" items="${requestScope.categories}" >
<option value='<c:out value="${bean.id}"/>'
<c:if test='${bean.id == requestScope.category}'>
selected="selected"
</c:if>
><c:out value="${bean.name}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' >
<fmt:message key="portlet.label.blogViewType"/>:
</td>
<td class='portlet-table-td-left'>
<select name='sortType' size='1'  class='portlet-form-select' style='width:200px;'>
<c:if test='${requestScope.sort == "1"}'>
<option selected='selected' value='1'><fmt:message key="portlet.message.newestBlog"/></option>
</c:if>
<c:if test='${requestScope.sort != "1"}'>
<option  value='1'><fmt:message key="portlet.message.newestBlog"/></option>
</c:if>
<c:if test='${requestScope.sort == "2"}'>
<option selected='selected' value='2'><fmt:message key="portlet.message.popularBlog"/></option>
</c:if>
<c:if test='${requestScope.sort != "2"}'>
<option value='2'><fmt:message key="portlet.message.popularBlog"/></option>
</c:if>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' >
<fmt:message key="portlet.label.blogShowLevel"/>:
</td>
<td class='portlet-table-td-left'>
<select name='showLevel' size='1'  class='portlet-form-select' style='width:200px;'>
<c:if test='${requestScope.showLevel == "0"}'>
<option selected='selected' value='0'><fmt:message key="portlet.message.blogShowSubject"/></option>
</c:if>
<c:if test='${requestScope.showLevel != "0"}'>
<option value='0'><fmt:message key="portlet.message.blogShowSubject"/></option>
</c:if>
<c:if test='${requestScope.showLevel == "1"}'>
<option selected='selected' value='1'><fmt:message key="portlet.message.blogShowSummary"/></option>
</c:if>
<c:if test='${requestScope.showLevel != "1"}'>
<option value='1'><fmt:message key="portlet.message.blogShowSummary"/></option>
</c:if>
<c:if test='${requestScope.showLevel == "2"}'>
<option selected='selected' value='2'><fmt:message key="portlet.message.blogShowContent"/></option>
</c:if>
<c:if test='${requestScope.showLevel != "2"}'>
<option value='2'><fmt:message key="portlet.message.blogShowContent"/></option>
</c:if>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' >
<fmt:message key="portlet.label.showItems"/>:
</td>
<td class='portlet-table-td-left'>
<select name='items' size='1'  class='portlet-form-select' style='width:200px;'>
<c:forEach var="i" begin="1" end="50" step="1">
<c:if test='${requestScope.showNumber == i}'>
<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
<c:if test='${requestScope.showNumber != i}'>
<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;' width="95%">
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='config';document.resetLastAction='1';" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' value='<fmt:message key="portlet.button.cancel"/>'
 onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" class='portlet-form-button'/>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
