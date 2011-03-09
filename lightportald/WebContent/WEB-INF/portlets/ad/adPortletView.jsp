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
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<c:if test='${country != null && country != ""}'>
<fmt:message key="portlet.label.location"/>:
</c:if>
<c:out value="${requestScope.country}"/>
<c:if test='${province != null && province != ""}'>
-><c:out value="${requestScope.province}"/>
</c:if>
<c:if test='${city != null && city != ""}'>
-><c:out value="${requestScope.city}"/>
</c:if>
</td>
</tr>
<tr>
<light:authenticateUser>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addAd"/></a>
</td>
</light:authenticateUser>
<td class='portlet-link'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='CONFIG'/>" ><fmt:message key="portlet.label.changeLocation"/></a>
</td>
</tr>
</table>

<c:if test='${requestScope.adLists != null}'>
	<c:forEach var="item" items="${requestScope.adLists}" >	
	<span class="portlet-rss" >
	<light:authenticateUser>  
	<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popAd(event,'<c:out value="${item.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardAdToFriend(event,'<c:out value="${item.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	</light:authenticateUser>
	<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveAdToBookmark(event,'<c:out value="${item.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
    <a href='javascript:;' 
    onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_AD %>,'<c:out value="${item.id}"/>');"
    onmouseout="javascript:Light.hideSummary();"
    onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','adId=<c:out value="${item.id}"/>');">
	<c:out value="${item.title}"/>
	<c:if test='${item.commentCount != 0}'>
	(<c:out value="${item.commentCount}"/> <fmt:message key="portlet.label.comments"/>)
	</c:if>
	</a>	
	</span>
	</c:forEach>
	
	<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
	<span class="portlet-rss" style="text-align:right;">
	<a href='javascript:;' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" >more...</a> 
	</span>
	</c:if>
</c:if>
<c:if test='${requestScope.showMore == null }'>
<br/>	
</c:if>
</fmt:bundle>
</body>
</html>
