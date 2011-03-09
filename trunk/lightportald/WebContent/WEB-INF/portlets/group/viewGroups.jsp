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
<div class="portlet2" >
<table border='0' cellpadding='0' cellspacing='0' width='100%' style="margin:10px;">
<tr valign='top' class='portlet-table-td-center'>
<td class='portlet-table-td-center'>
<label for="<c:out value="${requestScope.responseId}"/>_groupSearch" class='portlet-table-title'><b><fmt:message key="portlet.label.latestGroups"/></b></label>: 
<input type='text' id='<c:out value="${requestScope.responseId}"/>_groupSearch' class='portlet-form-input-field' size='48' value='' onchange="javascript:Light.globalSearch($('<c:out value="${requestScope.responseId}"/>_groupSearch').value,'org.light.portlets.group.Group');"/>
<a href="javascript:void(0);" onclick="javascript:Light.globalSearch($('<c:out value="${requestScope.responseId}"/>_groupSearch').value,'org.light.portlets.group.Group');"><img src='<%= request.getContextPath() %>/light/images/search.gif' style='border: 0px' height='16' width='16' align='top' title='<fmt:message key="portlet.button.search"/>'/></a>
</td>
</tr>
</table>
<%
String host = request.getHeader("Host");
if(host.startsWith("www") || host.startsWith("WWW")) host = host.substring(4);
host += request.getContextPath();
%>
<table border='0' cellpadding='0' cellspacing='0' width='100%' style="margin:10px;">
<tr valign='top' class='portlet-table-td-center'>
<td class='portlet-table-td-left' width='10%' style='padding: 30px 0 0 0;'>
<c:if test='${groupPage == 0}'>
<img src='<%= request.getContextPath() %>/light/images/left2.gif' title='<fmt:message key="portlet.label.first"/>' style='border: 0px;' />
</c:if>
<c:if test='${groupPage != 0}'>
<a href='javascript:void(0)' onclick="<portlet:renderURL><portlet:param name='previous' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/left2.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px;' /></a>
</c:if>
</td>
<c:forEach var="group" items="${requestScope.groups}" varStatus="status">
<td class='portlet-table-td-center' width='10%'>
<span class='portlet-item'>
<a href='http://<c:out value="${group.uri}"/>.<%= host %>' >
<c:if test='${group.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultGroupPortrait}"/>' style='border: 0px;' width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
<c:if test='${group.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${group.photoUrl}"/>' style='border: 0px;' width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
</c:if>
</a>
<br/> 
<a href='http://<c:out value="${group.uri}"/>.<%= host %>' ><c:out value="${group.displayName}"/></a>
</span>
</td>
</c:forEach>
<td class='portlet-table-td-right' width='10%' style='padding: 30px 20px 0 0;'>
<c:if test='${groupPageLast == null}'>
<a href='javascript:void(0)' onclick="<portlet:renderURL><portlet:param name='next' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/right2.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px;' /></a>
</c:if>
<c:if test='${groupPageLast != null}'>
<img src='<%= request.getContextPath() %>/light/images/right2.gif' title='<fmt:message key="portlet.label.last"/>' style='border: 0px;' />
</c:if>
</td>
</tr>
</table>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
	<span class="portlet-rss" style="text-align:right;">
	<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" ><fmt:message key="portlet.label.more"/>...</a> 
	</span>
</c:if>
</div>
</fmt:bundle>
</body>
</html>