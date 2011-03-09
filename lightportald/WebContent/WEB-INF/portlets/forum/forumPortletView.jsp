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
<input type='hidden' name='categoryId'  value='<c:out value="${categoryId}"/>' /> 
<input type='hidden' name='subCategoryId'  value='<c:out value="${subCategoryId}"/>' /> 
<c:if test='${requestScope.topLists != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' >
<b><c:out value="${categoryName}"/>-><c:out value="${subCategoryName}"/></b>
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/rss/forum/<c:out value="${subCategoryId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popForumItem(event,'<c:out value="${subCategoryId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardForumToFriend(event,'<c:out value="${subCategoryId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
</light:authenticateUser>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveForumToBookmark(event,'<c:out value="${subCategoryId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>	
</td>
<td class='portlet-link-left' >
<light:authenticateUser>  
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','','categoryId=<c:out value="${categoryId}"/>;subCategoryId=<c:out value="${subCategoryId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForumTopic"/></a>
</light:authenticateUser>
</td>
<td class='portlet-link' >
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.forumTopic"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.lastPost"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topicStarter"/></td>
</tr>
<c:forEach var="forum" items="${requestScope.topLists}" >
<tr class='portlet-table-td-left'>
<td class='portlet-item' >
<a href='javascript:void(0);' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;subCategoryId=<c:out value="${subCategoryId}"/>;topicId=<c:out value="${forum.id}"/>');">
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
<c:if test='${forum.lastPhotoUrl == null}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.lastUri}"/>' ><img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth * 2 / 3}"/>' height='<c:out value="${sessionScope.org.thumbHeight * 2 / 3}"/>'/></a>
</c:if>
<c:if test='${forum.lastPhotoUrl != null}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.lastUri}"/>' ><img src='<%= request.getContextPath() %><c:out value="${forum.lastPhotoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth * 2 / 3}"/>' height='<c:out value="${sessionScope.org.thumbHeight * 2 / 3}"/>'/></a>
</c:if>
</td>
<td class='portlet-table-td-left'>
<c:out value="${forum.lastDate}"/>
<br/>by 
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.lastUri}"/>' ><c:out value="${forum.lastDisplayName}"/></a>
</td>
</tr>
</table>
</td>
<td class='portlet-item'>
<table>
<tr>
<td>
<c:if test='${forum.photoUrl == null}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.uri}"/>' ><img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth * 2 / 3}"/>' height='<c:out value="${sessionScope.org.thumbHeight * 2 / 3}"/>'/></a>
</c:if>
<c:if test='${forum.photoUrl != null}'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.uri}"/>' ><img src='<%= request.getContextPath() %><c:out value="${forum.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth * 2 / 3}"/>' height='<c:out value="${sessionScope.org.thumbHeight * 2 / 3}"/>'/></a>
</c:if>
</td>
<td class='portlet-table-td-left'>
<c:out value="${forum.date}"/>
<br/>by 
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.uri}"/>' ><c:out value="${forum.displayName}"/></a>
</td>
</tr>
</table>
</td>
</tr>
</c:forEach>
</table>
</c:if>

<c:if test='${requestScope.forumLists != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' >
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popTopicItem(event,'<c:out value="${topicId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardTopicToFriend(event,'<c:out value="${topicId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
</light:authenticateUser>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveTopicToBookmark(event,'<c:out value="${topicId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>	
</td>
<td class='portlet-link' >
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;subCategoryId=<c:out value="${subCategoryId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0'  width='95%'>
<c:forEach var="forum" items="${requestScope.forumLists}" >
<tr valign=top>
<td class='portlet-table-td-center' width='20%'>
<span class='portlet-item'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.uri}"/>' ><c:out value="${forum.displayName}"/></a>
<br/>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${forum.uri}"/>' >
<c:if test='${forum.lastPhotoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='50' height='50'/>
</c:if>
<c:if test='${forum.lastPhotoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${forum.lastPhotoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${forum.lastPhotoWidth}"/>' height='<c:out value="${forum.lastPhotoHeight}"/>'/>
</c:if>
</a>
<br/>
<c:if test='${forum.currentStatusId == 1 }'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='15' width='12'  align="bottom" alt=''/>
</c:if>
<br/>
<c:out value="${forum.gender}"/>/<c:out value="${forum.age}"/>
<br/>
<c:if test='${forum.currentStatusId == 1 }'>
<a href='javascript:void(0)' onclick="javascript:showInstantMessageMember(event,'<c:out value="${forum.postById}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.instantMessage"/></a>
<br/>
</c:if>
<a href='javascript:void(0)' onclick="javascript:showSendMessageMember('<c:out value="${requestScope.responseId}"/>','<c:out value="${forum.postById}"/>','<c:out value="${forum.displayName}"/>',event);" ><fmt:message key="portlet.label.sendMessage"/></a>
<br/>
</span>
</td>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.posted"/>: <c:out value="${forum.date}"/>
<light:authorize role="ROLE_ADMIN"> 
<c:if test='${forum.topId == 0 }'>
<input type="image" title='<fmt:message key="portlet.label.deleteAllPosts"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${forum.id}'/>" onClick="document.pressed='deleteTopic';document.parameter=this.name;"/>
</c:if>
<c:if test='${forum.topId != 0 }'>
<input type="image" title='<fmt:message key="portlet.label.deletePost"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${forum.id}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</c:if>
</light:authorize>
<br/><br/>
<c:out value="${forum.content}" escapeXml="false"/>
</td>
<td class='portlet-table-td-right'>
<light:authenticateUser>  
<input type='submit' name="<c:out value='${forum.id}'/>" onClick="document.pressed='quote';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.quote"/>' class='portlet-form-button' />
<input type='submit' name="<c:out value='${forum.id}'/>" onClick="document.pressed='replybegin';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
</light:authenticateUser>
</td>
</tr>
</c:forEach>
<tr>
<td class='portlet-table-td-center' colspan='3'>
<light:authenticateUser>  
<input type='submit' name="<c:out value='${requestScope.topicId}'/>" onClick="document.pressed='replybegin';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
</light:authenticateUser>
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','categoryId=<c:out value="${categoryId}"/>;subCategoryId=<c:out value="${subCategoryId}"/>')" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
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