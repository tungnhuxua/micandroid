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
<light:authenticateOwner>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/bookmark_add.png' style='border: 0px;' height='16' width='16' align="bottom"/><fmt:message key="portlet.button.addBookmark"/></a>
</td>
</tr>
</table>
</light:authenticateOwner>

<form action="<portlet:actionURL />">
<c:if test='${bookmarkTags != null}'>
 <c:forEach var="tag" items="${bookmarkTags}" >
  <c:if test='${tag.expanded}'>
    <span class='portlet-rss'>
    <input type="image" src="<%= request.getContextPath() %>/light/images/hideMod.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag.tagId}'/>" onClick="document.pressed='close';document.parameter=this.name;"/>
    <a href="javascript:;" onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','close',null,'<c:out value="${tag.tagId}"/>','view',null,null);"><c:out value="${tag.tagName}"/></a>
    <light:authenticateOwner>    
    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${tag.tagId}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='deleteTag';document.parameter=this.name;"/>
    </light:authenticateOwner>
    </span>
    <c:forEach var="bookmark" items="${tag.bookmarks}" >
    <span class='portlet-rss' style="margin:0 0 0 20px;">
    <light:authenticateUser>
    <input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popBookmarkItem(event,'<c:out value="${bookmark.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardBookmarkToFriend(event,'<c:out value="${bookmark.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
	</light:authenticateUser>
	<a href="http://www.facebook.com/sharer.php?u=<c:out value="${bookmark.url}"/>&t=<c:out value="${bookmark.name}"/>" target='_blank' onClick="javascript:hidePopupDiv('${popupName}');"><img src="<%= request.getContextPath() %>/light/images/facebook.gif" title="facebook it!" alt="facebook it!" border="0" height='16' width='16'/></a>
	<a href='http://digg.com/submit?phase=2&url=<c:out value="${bookmark.url}"/>&title=<c:out value="${bookmark.name}"/>&bodytext=<c:out value="${bookmark.desc}"/>&topic=world_news' target='_blank'><img src="<%= request.getContextPath() %>/light/images/digg.gif" alt="digg it!" title="digg it!" border="0" height='16' width='16'/></a>	
	<light:authenticateOwner> 
	<input type="image" title='<fmt:message key="portlet.label.edit"/>' src="<%= request.getContextPath() %>/light/images/edit.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${bookmark.id}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
    <input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${bookmark.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;"/>
    </light:authenticateOwner>
    <a href='<c:out value="${bookmark.url}"/>' 
    	onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_BOOKMARK %>,'<c:out value="${bookmark.id}"/>');"
	    onmouseout="javascript:Light.hideSummary();" target='_blank'><c:out value="${bookmark.name}"/></a>
    
    </span>
    </c:forEach>
 </c:if>
 <c:if test='${!tag.expanded}'>
    <span class='portlet-rss'>
    <input type="image" src="<%= request.getContextPath() %>/light/images/showMod.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag.tagId}'/>" onClick="document.pressed='expand';document.parameter=this.name;"/>
    <a href="javascript:;" onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','expand',null,'<c:out value="${tag.tagId}"/>','view',null,null);"><c:out value="${tag.tagName}"/></a>    
    </span>
 </c:if>
 </c:forEach>
</c:if>
<c:if test='${defaultBookmarks != null}'>
<c:forEach var="bookmark" items="${defaultBookmarks}" >
<span class='portlet-rss' style="margin:0 0 0 10px;">
<a href='<c:out value="${bookmark.url}"/>' 
   onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_BOOKMARK %>,'<c:out value="${bookmark.id}"/>');"
   onmouseout="javascript:Light.hideSummary();" target='_blank'><c:out
value="${bookmark.name}"/></a>
<light:authenticateUser>
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" style='border: 0px;' height='16' width='16' onClick="javascript:popBookmarkItem(event,'<c:out value="${bookmark.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" style='border: 0px;' height='16' width='16' onClick="javascript:forwardBookmarkToFriend(event,'<c:out value="${bookmark.id}"/>','<c:out value="${requestScope.responseId}"/>');"/>
</light:authenticateUser>
<light:authenticateOwner> 
<input type="image" src="<%= request.getContextPath()%>/light/images/edit.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${bookmark.id}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="<%= request.getContextPath()%>/light/images/deleteLink.gif" name="<c:out value='${bookmark.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;"/>
</light:authenticateOwner>
</span>
</c:forEach>
</c:if>
</form>
</fmt:bundle>
</body>
</html>