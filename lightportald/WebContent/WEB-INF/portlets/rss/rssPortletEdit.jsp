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
<table border='0' cellpadding='0' cellspacing='0' width="95%" style="margin:20px 0 0 0;">
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.feed"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prFeed'  value='<c:out value="${requestScope.feed}"/>' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prTitle'  value='<c:out value="${requestScope.portlet.title}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.link"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prUrl'  value='<c:out value="${requestScope.portlet.url}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.imageLink"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prIcon' value='<c:out value="${requestScope.portlet.icon}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.autoRefresh"/>:
</td>
<td class='portlet-table-td-left'>
<input type='radio' name='prAuto' value='1' class='portlet-form-radio'
<c:if test='${requestScope.portlet.autoRefreshed == 1}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.true"/></input> 
<input type='radio' name='prAuto' value='0' class='portlet-form-radio'
<c:if test='${requestScope.portlet.autoRefreshed == 0}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.false"/></input> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.refreshMinute"/>:
</td>
<td class='portlet-table-td-left'>
<select name='prMinute' size='1'  class='portlet-form-select' style="width:200px;">
<option value='0' 
<c:if test='${requestScope.minute == 0}'> selected='selected' </c:if>
></option>
<c:forEach var="i" begin="1" end="30" step="1">
<option value='<c:out value="${i}" />'
<c:if test='${requestScope.minute == i}'> selected='selected'  </c:if>
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.showType"/>:
</td>
<td class='portlet-table-td-left'>
<select name='prShowType' size='1'  class='portlet-form-select' style="width:200px;">
<option value='0'
<c:if test='${requestScope.portlet.showType == 0}'>
selected='selected' 
</c:if>
><fmt:message key="portlet.label.showType.subject"/></option>
<option value='1'
<c:if test='${requestScope.portlet.showType == 1}'>
selected='selected' 
</c:if>
><fmt:message key="portlet.label.showType.desc"/></option>
<option value='2'
<c:if test='${requestScope.portlet.showType == 2}'>
selected='selected' 
</c:if>
><fmt:message key="portlet.label.showType.both"/></option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.show.normal"/>:
</td>
<td class='portlet-table-td-left'>
<select name='prItems' size='1'  class='portlet-form-select' style="width:200px;">
<c:forEach var="i" begin="1" end="50" step="1">
<c:if test='${requestScope.portlet.showNumber == i}'>
<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
<c:if test='${requestScope.portlet.showNumber != i}'>
<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
</td>
<td class='portlet-table-td-left'>
<input name='Submit' type='button' value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button'	onclick="javascript:editRssPortlet('<c:out value="${requestScope.responseId}"/>');" style="margin: 10px 0 0 0;"/>
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>						
</table>		
</form>
</fmt:bundle>
</body>
</html>