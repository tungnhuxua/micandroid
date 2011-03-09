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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit="javascript:return Light.securityCheck();">
<input type='hidden' name='categoryId'  value='<c:out value="${categoryId}"/>' /> 
<input type='hidden' name='forumId'  value='<c:out value="${forumId}"/>' /> 
<c:if test='${requestScope.postLists != null}'>
<table border='0' cellpadding='0' cellspacing='0' width='95%'>
<tr>
<td class='portlet-table-td-left' >
<b>
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>');" ><c:out value="${categoryName}"/></a>
->
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" ><c:out value="${forumName}"/></a>
->
<c:out value="${topicName}"/>
</b>
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/rss/topic<c:out value="${topicId}"/>p<c:out value="${pageId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popTopicItem(event,'<c:out value="${topicId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardTopicToFriend(event,'<c:out value="${topicId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" style='border: 0px;' height='16' width='16' onClick="javascript:saveTopicToBookmark(event,'<c:out value="${topicId}"/>','<c:out value="${pageId}"/>','<c:out value="${requestScope.responseId}"/>'); return false;"/>	
</light:authenticateUser>
</td>
<td class='portlet-link' >
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr>
<tr>
<td class='portlet-link' colspan='3'>
<c:if test='${requestScope.pages > 1}'>
<c:forEach var="i" begin="1" end="${requestScope.pages}" step="1">
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>;topicId=<c:out value="${forum.id}"/>;pageId=<c:out value="${i}"/>;pages=<c:out value="${pages}"/>');" ><c:out value="${i}"/></a>
</c:forEach>
</c:if>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0'  width='100%'>
<c:forEach var="forum" items="${requestScope.postLists}" varStatus="status" >
<c:if test='${status.index % 2 == 0}'>
<tr valign=top>
</c:if>
<c:if test='${status.index % 2 == 1}'>
<tr valign=top style='background-color: #EEF6FF'>
</c:if>
<td class='portlet-table-td-center' width='100px'>
<span class='portlet-item'>
<jsp:useBean id="forum" scope="page" class="org.light.portlets.forum.ForumPost"/>
<light:avatarUrl name="<%= forum.getDisplayName() %>" url="<%= forum.getUri() %>" /> 
<light:avatar name="<%= forum.getDisplayName() %>" url="<%= forum.getUri() %>" pictureUrl="<%= forum.getPhotoUrl() %>" width="75" height="75" /> 
<c:if test='${forum.currentStatusId == 1 }'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/>
</c:if>
<br/>
<c:if test='${forum.currentStatusId == 1 }'>
<a href='javascript:;' onclick="javascript:showInstantMessageMember(event,'<c:out value="${forum.postById}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.instantMessage"/></a>
<br/>
</c:if>
<a href='javascript:;' onclick="javascript:showSendMessageMember('<c:out value="${requestScope.responseId}"/>','<c:out value="${forum.postById}"/>','<c:out value="${forum.displayName}"/>',event);" ><fmt:message key="portlet.label.sendMessage"/></a>
<br/>
</span>
</td>
<td class='portlet-table-td-left'>
<span class="portlet-note"><fmt:message key="portlet.label.posted"/>: <c:out value="${forum.date}"/></span>
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
<td class='portlet-table-td-right' width='140px'>
<br/>
<input type='submit' name="<c:out value='${forum.id}'/>" onClick="document.pressed='quote';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.quote"/>' class='portlet-form-button' />
<input type='submit' name="<c:out value='${forum.id}'/>" onClick="document.pressed='replybegin';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
</td>
</tr>
</c:forEach>
<tr>
<td class='portlet-table-td-center' colspan='3' style='padding-top:10px;'>
<input type='submit' name="<c:out value='${requestScope.topicId}'/>" onClick="document.pressed='replybegin';document.parameter=this.name;document.mode='EDIT';" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>')" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
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