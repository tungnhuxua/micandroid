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
<light:authenticateUser> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<a href='javascript:;' onclick="javascript:Light.globalSearch('','org.light.portal.model.User');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addBuddy"/></a>
</td>
<c:if test='${currentDesc != null}'>
<td class='portlet-table-td-right' >
<span class='portlet-rss' >
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','action=back','',true,false);" ><c:out value="${currentDesc}"/><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' height='16' width='16' align="top" title='<fmt:message key="portlet.button.back"/>'/></a>
</span>
</td>
</c:if>
</tr>
</table>
</light:authenticateUser>
<c:if test='${buddyLocations != null}'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="buddyLocation" items="${requestScope.buddyLocations}" varStatus="status">
<tr valign='top'>
<td class='portlet-table-td-left' width= '100%'>
<span class="portlet-rss">
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','action=location;location=<c:out value="${buddyLocation.id}"/>','',true,false);">
<c:out value="${buddyLocation.id}"/> (<c:out value="${buddyLocation.desc}"/>)
</a>
</span>
</td>
</tr>
</c:forEach>
</table>
</c:if>
<c:if test='${buddyTypes != null}'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="buddyType" items="${requestScope.buddyTypes}" varStatus="status">
<tr valign='top'>
<td class='portlet-table-td-left' width= '100%'>
<span class="portlet-rss">
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','action=type;friendType=<c:out value="${buddyType.type}"/>','',true,false);">
<c:out value="${buddyType.title}"/> (<c:out value="${buddyType.count}"/>)
</a>
</span>
</td>
</tr>
</c:forEach>
</table>
</c:if>
<c:if test='${buddyTypes == null && buddyLocations == null}'>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign='top'>
<td class='portlet-table-td-left' >
<fmt:message key="portlet.label.youHave"/> <c:out value="${requestScope.buddyCount}"/> <fmt:message key="portlet.label.friends"/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' >
<c:forEach var="buddy" items="${requestScope.buddys}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-center' width='80'>
<span class='portlet-item'>
<c:if test='${buddy.buddyCurrentStatusId == 1}'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px' width='15' height='12' align="bottom" alt=''/>
</c:if>
<br/>
<jsp:useBean id="buddy" scope="page" class="org.light.portlets.connection.Connection"/>
<light:avatarUrl name="<%= buddy.getDisplayName() %>" url="<%= buddy.getUri() %>" />
</span>
<a href="javascript:;" onclick="javascript:showBuddyDetail(event,'<c:out value="${buddy.buddyUserId}"/>','<c:out value="${requestScope.responseId}"/>');">
<c:if test='${buddy.photoUrl == null}'>
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:75px; height:75px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
</c:if>
<c:if test='${buddy.photoUrl != null}'>
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${buddy.photoUrl}"/>) no-repeat scroll 0 0; list-style-type: none; width:75px; height:75px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
</c:if>
</a>
</td>
<c:if test='${status.index % 10 == 10 - 1}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.showNumber % 10 != 0}'>
</tr>
</c:if>
</table>
</c:if>
</fmt:bundle>
</body>
</html>