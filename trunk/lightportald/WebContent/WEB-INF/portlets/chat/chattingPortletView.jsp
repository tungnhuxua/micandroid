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
<div>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL />">
<table border='0' cellpadding='0' cellspacing='0' width ='100%'>
<c:forEach var="record" items="${requestScope.records}" >
<c:if test='${!record.systemAdd}'>
<tr>
<td class='portlet-table-td-left'>
<font color="#777777"><c:out value="${record.name}"/> <fmt:message key="portlet.label.says"/>:</font>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left'>
<span style="padding-left:10px;">
<c:if test='${!record.systemAdd}'>
<c:out value="${record.content}"/>
</c:if>
<c:if test='${record.systemAdd}'>
<font color="#ff0000"><c:out value="${record.content}"/></font>
</c:if>
</span>
</td>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</div>