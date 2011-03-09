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
<input type='hidden' name='categoryId'  value='<c:out value="${categoryId}"/>' /> 
<input type='hidden' name='forumId'  value='<c:out value="${forumId}"/>' /> 
<c:if test='${requestScope.topicLists != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='95%' style="margin: 20px 0 0 0;">
<tr>
<td class='portlet-table-td-left' >
<b><a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>');" ><c:out value="${categoryName}"/></a>-><c:out value="${forumName}"/></b>
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/rss/forum<c:out value="${forumId}"/>p<c:out value="${pageId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popForumItem(event,'<c:out value="${forumId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardForumToFriend(event,'<c:out value="${forumId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveForumToBookmark(event,'<c:out value="${forumId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
</light:authenticateUser>	
</td>
<td class='portlet-link-left' >
<a href='javascript:;' onclick="javascript:if(Light.securityCheck()) Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForumTopic"/></a>
</td>
<td class='portlet-link' >
<input type='text' id='<c:out value="${requestScope.responseId}"/>_forumSearch' class='portlet-form-input-field' size='24' value='' onchange="javascript:Light.globalSearch($('<c:out value="${requestScope.responseId}"/>_forumSearch').value,'org.light.portlets.forum.ForumPost');"/>
<a href="javascript:;" onclick="javascript:Light.globalSearch($('<c:out value="${requestScope.responseId}"/>_forumSearch').value,'org.light.portlets.forum.ForumPost');"><img src='<%= request.getContextPath() %>/light/images/search.gif' style='border: 0px' height='16' width='16' align='top' title='<fmt:message key="portlet.button.search"/>'/></a>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr>
<tr>
<td class='portlet-link' colspan='3'>
<c:if test='${requestScope.pages > 1}'>
<c:forEach var="i" begin="1" end="${requestScope.pages}" step="1">
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>;pageId=<c:out value="${i}"/>;pages=<c:out value="${pages}"/>');" ><c:out value="${i}"/></a>
</c:forEach>
</c:if>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-left' width= '40%'><fmt:message key="portlet.label.forumTopic"/></td>
<td class='portlet-table-td-left' width= '10%'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left' width= '25%' style='padding-left:10px;'><fmt:message key="portlet.label.lastPost"/></td>
<td class='portlet-table-td-left' width= '25%' style='padding-left:10px;'><fmt:message key="portlet.label.topicStarter"/></td>
</tr>

<c:forEach var="forum" items="${requestScope.topicLists}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item' >
<a href='javascript:;' 
	onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_FORUM %>,'<c:out value="${forum.id}"/>');"
	onmouseout="javascript:Light.hideSummary();"
	onclick="javascript:Light.hideSummary();Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>;topicId=<c:out value="${forum.id}"/>');">
<img src='<%= request.getContextPath() %>/light/images/folder_smll.gif' style='border: 0px;'  align="top" />
<c:out value="${forum.topic}"/></a>
</td>
<td class='portlet-item' >
<c:out value="${forum.posts}"/>
</td>
<td class='portlet-item'>
<table>
<tr>
<td>
<jsp:useBean id="forum" scope="page" class="org.light.portlets.forum.ForumPost"/>
<light:avatar name="<%= forum.getLastDisplayName() %>" url="<%= forum.getLastUri() %>" pictureUrl="<%= forum.getLastPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left'>
<span class='portlet-note'><c:out value="${forum.lastDate}"/></span>
<br/><span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span> 
<light:avatarUrl name="<%= forum.getLastDisplayName() %>" url="<%= forum.getLastUri() %>" /> 
</td>
</tr>
</table>
</td>
<td class='portlet-item'>
<table>
<tr>
<td>
<light:avatar name="<%= forum.getDisplayName() %>" url="<%= forum.getUri() %>" pictureUrl="<%= forum.getPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left'>
<span class='portlet-note'><c:out value="${forum.date}"/></span>
<br/><span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span> 
<light:avatarUrl name="<%= forum.getDisplayName() %>" url="<%= forum.getUri() %>" /> 
</td>
</tr>
</table>
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
</fmt:bundle>
</body>
</html>