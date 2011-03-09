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
<head></head>
<body> 
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<c:if test='${requestScope.type == "1"}'>
<span class="portlet-item">
<fmt:message key="portlet.message.top"/>
<c:out value="${requestScope.showNumber}"/>
<fmt:message key="portlet.message.newestAd"/>
</span>
</c:if>
<c:if test='${requestScope.type == "2"}'>
<span class="portlet-item">
<fmt:message key="portlet.message.top"/>
<c:out value="${requestScope.showNumber}"/>
<fmt:message key="portlet.message.popularAd"/>
</span>
</c:if>
<br/><br/>
<c:if test='${requestScope.adLists != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
<c:forEach var="item" items="${requestScope.adLists}" >
<tr>
<td class='portlet-table-td-left'>
<c:out value="${item.date}"/>
</td>
<td class='portlet-table-td-left'>
<span class="portlet-rss">
<a href='javascript:;' 
	onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_AD %>,'<c:out value="${item.id}"/>');"
    onmouseout="javascript:Light.hideSummary();"
    onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','adId=<c:out value="${item.id}"/>');">
<c:if test='${item.status != 0}'>
<c:out value="${item.title}"/>
</c:if>
<c:if test='${item.commentCount != 0}'>
(<c:out value="${item.commentCount}"/> <fmt:message key="portlet.label.comments"/>)
</c:if>
</a>
</span>
</td>
<td class='portlet-table-td-right'>
<span class="portlet-rss">
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.uri}"/>'><c:out value="${item.displayName}"/></a>
</span>
</td>
</tr>
</c:forEach>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<span class="portlet-item">
<c:out value="${requestScope.error}"/>
</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>