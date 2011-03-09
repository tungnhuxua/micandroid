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
<form action="<portlet:actionURL />">
<light:authorize role="ROLE_ADMIN"> 
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addInternalNews"/></a>
</td>
</tr>
</table>
</light:authorize>
<c:if test='${requestScope.newsList != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left'>
<span class="portlet-title"><fmt:message key="portlet.label.latestNews"/></span>
</td>
</tr>
<c:forEach var="item" items="${requestScope.newsList}" >	
<tr>
<td class='portlet-table-td-left'>
<c:if test='${item.new }'>
<img src="light/images/new.png" style='border: 0px;' height='16' width='16' />
</c:if>
<light:authorize role="ROLE_ADMIN">
<input type="image" src="<%= request.getContextPath() %>/light/images/edit.gif" style='border: 0px;' height='13' width='11' name="<c:out value='${item.id}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${item.id}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</light:authorize>
<span class="portlet-item">
<a href='javascript:;' 
   		onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','maximized','newsId=<c:out value="${item.id}"/>');"
		onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_INTERNAL_NEWS %>,'<c:out value="${item.id}"/>');"
	    onmouseout="javascript:Light.hideSummary();">
	    <c:out value="${item.subject}" escapeXml="false"/>
</a>
</span>	
<br/>
<span class="portlet-note"><c:out value="${item.date}"/></span>
</td>
</tr>
</c:forEach>
</table>
</c:if>

<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
	<span class="portlet-rss" style="text-align:right;margin-right:10px;">
	<a href='javascript:;' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" ><fmt:message key="portlet.label.more"/>...</a> 
	</span>
</c:if>
<c:if test='${requestScope.state == "maximized" }'>
	<span class="portlet-rss" style="text-align:right;;margin-right:10px;">
	<a href='javascript:;' onclick="<portlet:renderURL  windowState='NORMAL'/>" ><fmt:message key="portlet.button.back"/></a> 
	</span>
</c:if>
</form>
</fmt:bundle>
</body>
</html>