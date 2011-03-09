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
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin:20px 0 0 0;'>
<tr>
<td class='portlet-msg-info' colspan='3'>
Please send your feedbacks and comments, they are essential in order to determinate the direction we should follow. 
</td>
</tr>
<tr>
<td class='portlet-link-left' colspan='3'>
<a href='javascript:void(0)' onclick="<portlet:renderURL portletMode='EDIT'/>" >
<img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addFeedback"/></a>
</td>
</tr>
</table>

<c:if test='${requestScope.feebackLists != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<c:forEach var="item" items="${requestScope.feebackLists}" >
<tr valign='top'>
<td class='portlet-table-td-left'>
<span class="portlet-rss">
<light:authorize role="ROLE_ADMIN">
<a href='javascript:;' onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','delete',null,'<c:out value="${item.id}"/>','view',null,null);" ><img src='<%= request.getContextPath() %>/light/images/deleteLink.gif' style='border: 0px;' height='11' width='11' title='<fmt:message key="portlet.button.delete"/>'/></a>
</light:authorize>
<a href='javascript:;' 
  onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK %>,'<c:out value="${item.id}"/>');"
  onmouseout="javascript:Light.hideSummary();" 
  onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','feedbackId=<c:out value="${item.id}"/>');">
<c:out value="${item.subject}"/>
<c:if test='${item.commentCount != 0}'>
(<c:out value="${item.commentCount}"/> <fmt:message key="portlet.label.comments"/>)
</c:if>
</a>
</span>
</td>
</tr>
</c:forEach>
</table>
<c:if test='${requestScope.state == "normal" && requestScope.showMore !=
null }'>
<span class="portlet-rss" style="text-align:right;">
<a href='javascript:void(0)' onclick="<portlet:renderURL windowState='MAXIMIZED'/>" ><fmt:message key="portlet.label.more"/>...</a>
</span>
</c:if>
</c:if>
<c:if test='${requestScope.error != null}'>
<span class="portlet">
<c:out value="${requestScope.error}"/>
</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>