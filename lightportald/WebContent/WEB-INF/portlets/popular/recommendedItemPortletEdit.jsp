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
<form name="form_<c:out value="${requestScope.responseId}"/>" action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width="95%" style="margin:10px 0 0 0;">
<tr>
<td class='portlet-table-td-left' width='200'>
<fmt:message key="portlet.label.show.normal"/>:
</td>
<td class='portlet-table-td-left'>
<select name='items' size='1'  class='portlet-form-select'>
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
<tr>
<td class='portlet-table-td-left' width='200'>
<fmt:message key="portlet.label.show.maximized"/>:
</td>
<td class='portlet-table-td-left'>
<select name='maxItems' size='1'  class='portlet-form-select'>
<c:forEach var="i" begin="1" end="50" step="1">
<c:if test='${requestScope.maxNumber == i}'>
<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
<c:if test='${requestScope.maxNumber != i}'>
<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>	
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.keyword"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<select name='keywords' MULTIPLE='MULTIPLE' size='5' class='portlet-form-select' STYLE="width: 200px">
<c:forEach var="keyword" items="${requestScope.keywords}" >
<option value='<c:out value="${keyword.id}"/>'><c:out value="${keyword.keyword}"/></option>
</c:forEach>
</select>
<c:if test='${requestScope.growKeyword != null}'>
<tr>
<td class='portlet-table-td-left' colspan='2' >
<input type='checkbox' name='growKeyword' value='<c:out value="${sessionScope.user.growKeyword}"/>' class='portlet-form-checkbox'
<c:if test='${sessionScope.user.growKeyword == 1}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.keyword.grow"/></input> 
</td>
</tr>
<input type='button' name='action' onClick="javascript:Light.addKeywords(event,'<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.add"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='deleteKeywords';" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
</td>
</tr>	
</c:if>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="document.pressed='config';document.resetLastAction='1'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
<light:authenticateOwner>
<input type='submit' name='action' onClick="document.pressed='deleteAll';document.resetLastAction='1'" value='<fmt:message key="portlet.button.deleteAllNews"/>' class='portlet-form-button' />
</light:authenticateOwner>
</td>
</tr>					
</table>		
</form>
</fmt:bundle>
</body>
</html>