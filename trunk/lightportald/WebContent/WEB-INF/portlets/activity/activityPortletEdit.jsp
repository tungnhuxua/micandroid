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
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin-top:10px;'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.showItems"/>:
</td>
<td class='portlet-table-td-left'>
<select name='items' size='1'  class='portlet-form-select' style='width:80px'>
<c:forEach var="i" begin="1" end="100" step="1">
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
<tr valign='top'>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.show"/>:
</td>
<td class='portlet-table-td-left'>
<light:authorize role="ROLE_ADMIN"> 
<input type='radio' name='type' value='0' class='portlet-form-radio'
<c:if test='${requestScope.type == 0}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.activity.community"/></input><br/>
</light:authorize>
<input type='radio' name='type' value='1' class='portlet-form-radio'
<c:if test='${requestScope.type == 1}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.activity.all"/></input><br/>
<input type='radio' name='type' value='2' class='portlet-form-radio'
<c:if test='${requestScope.type == 2}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.activity.group"/></input><br/>
<input type='radio' name='type' value='3' class='portlet-form-radio'
<c:if test='${requestScope.type == 3}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.activity.connections"/></input><br/>
<input type='radio' name='type' value='4' class='portlet-form-radio'
<c:if test='${requestScope.type == 4}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.activity.me"/></input><br/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="document.pressed='config';document.resetLastAction='1';" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
