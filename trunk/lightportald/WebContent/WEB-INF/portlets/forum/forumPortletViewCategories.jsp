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
<body> 
<fmt:bundle basename="resourceBundle">
<c:if test='${requestScope.success != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0' width='95%'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}" escapeXml="false"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}" escapeXml="false"/>
</td>
</tr>
</table>
</c:if>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left' >
<light:authenticateUser>  
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','','action=category');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForumCategory"/></a>
</light:authenticateUser>
</td>
<td class='portlet-link' >
<a href='javascript:;' onclick="javascript:Light.showChangeLanguage();" ><fmt:message key="portlet.label.changeLanguage"/></a>
</td>
</tr>
</table>
<c:if test='${requestScope.forumCategories != null}'>
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<tr>
<td class='portlet-table-td-left' width='60%' style='padding-left:10px;'><fmt:message key="portlet.label.forumCategory"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topics"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.lastPost"/></td>
</tr>
<c:forEach var="item" items="${requestScope.forumCategories}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='60%'>
<table>
<tr class='portlet-table-td-left' valign='top'>
<td>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${item.id}"/>;direction=down');">
<img src='<%= request.getContextPath() %>/light/images/folder.gif' style='border: 0px;'  align="top" />
</a>
<br/>
</td>
<td>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${item.id}"/>;direction=down');">
<c:out value="${item.displayName}"/></a>
<br/>
<span class='portlet-note'><c:out value="${item.displayDesc}"/></span>
</td>
</tr>
</table>
</td>
<td class='portlet-item' style='padding-left:10px;'>
<c:out value="${item.topics}"/>
</td>
<td class='portlet-item' style='padding-left:10px;'>
<c:out value="${item.posts}"/>
</td>
<td class='portlet-item' style='padding-left:10px;'>
<c:if test='${item.lastForumId  != null}'>
<span class='portlet-note'><c:out value="${item.lastForum.date}"/></span>
<br/>
<span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${item.lastForum.uri}"/>' ><c:out value="${item.lastForum.displayName}"/></a>
<span class='portlet-note'>>> </span>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${item.lastForum.categoryId}"/>;forumId=<c:out value="${item.lastForum.forumId}"/>;topicId=<c:out value="${item.lastForum.topicId}"/>');">
<fmt:message key="portlet.label.viewPost"/>
</a>
</c:if>
</td>
</tr>
</c:forEach>
</table>
</c:if>

</form>
</fmt:bundle>
</body>
</html>