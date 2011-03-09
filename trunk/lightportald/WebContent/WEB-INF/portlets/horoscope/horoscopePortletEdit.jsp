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
<form action="<portlet:actionURL portletMode='VIEW' />">
<%
request.setAttribute("months", org.light.portal.util.CalendarUtil.getMonths());
request.setAttribute("days", org.light.portal.util.CalendarUtil.getDays());
request.setAttribute("years", org.light.portal.util.CalendarUtil.getYears());
%>
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin-top:10px;'>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.birth"/>: </td>
<td class='portlet-table-td-left'>
<select name='birthM' size='1' class='portlet-form-select'>
<c:forEach var="month" items="${requestScope.months}" >
<option value='<c:out value="${month.id}"/>'
<c:if test='${sessionScope.user.birthM == month.id}'>
selected="selected"
</c:if>
><c:out value="${month.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthD' size='1' class='portlet-form-select'>
<c:forEach var="day" items="${requestScope.days}" >
<option value='<c:out value="${day.id}"/>'
<c:if test='${sessionScope.user.birthD == day.id}'>
selected="selected"
</c:if>
><c:out value="${day.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthY' size='1' class='portlet-form-select'>
<c:forEach var="year" items="${requestScope.years}" >
<option value='<c:out value="${year.id}"/>'
<c:if test='${sessionScope.user.birthY == year.id}'>
selected="selected"
</c:if>
><c:out value="${year.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2'>
<input type='submit' name='action' onClick="document.pressed='edit'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>