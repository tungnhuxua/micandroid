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
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin:20px 0 0 0;'> 
<tr> 
<td class='portlet-table-td-left'> 
<span style="font-size: 150%;"><b><c:out value="${blog.title}"/></b></span>
</td> 
<td class='portlet-table-td-right' >
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','normal','');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr> 
<tr> 
<td class='portlet-table-td-left' colspan='2'> 
<span class="portlet-rss"> 					
<fmt:message key="portlet.label.postedBy"/>: 
<jsp:useBean id="blog" scope="request" class="org.light.portlets.blog.Blog"/>
<light:avatarUrl name="<%= blog.getDisplayName() %>" url="<%= blog.getUri() %>" /> 
<fmt:message key="portlet.label.at"/>: <c:out value="${blog.fullDate}"/>
<br/> 
<br/> 
</span> 
</td> 					
</tr> 
</table>
<table border='0' cellpadding='0' cellspacing='0' width='95%' > 
<!-- 
<c:if test='${blog.summary != null }'>
<tr> 
<td class='portlet-table-td-left'  style='padding:10px 0 20px 5px;'> 
<span>
<c:out value="${blog.summary}" escapeXml="false"/>
</span> 
</td> 
</tr> 
</c:if>
 -->
<tr> 
<td class='portlet-table-td-left'> 
<span>
<c:out value="${blog.content}" escapeXml="false"/>
</span> 
</td> 
</tr>
</table>
 
<c:if test='${blog.commentCount > 0 }'>
<table border='0' cellpadding='0' cellspacing='0' width='95%' > 
<tr> 
<td class='portlet-table-td-left' colspan='2' >
<br/><br/><b><fmt:message key="portlet.label.comments"/></b>(<c:out value="${blog.commentCount}"/>):<br/> 
</td>
</tr>
<c:forEach var="comment" items="${requestScope.comments}" >
<tr> 
<td class='portlet-table-td-center' colspan='2' >
<hr/>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-center' width='80px'> 
<jsp:useBean id="comment" scope="page" class="org.light.portal.model.UserComment"/>
<light:avatarUrl entityId="<%= comment.getUserId() %>" name="<%= comment.getDisplayName() %>" url="<%= comment.getUri() %>" /> 
<light:avatar entityId="<%= comment.getUserId() %>" name="<%= comment.getDisplayName() %>" url="<%= comment.getUri() %>" pictureUrl="<%= comment.getPhotoUrl() %>" width="50" height="50" /> 
</td>
<td class='portlet-table-td-left' >
<span class="portlet-note"><fmt:message key="portlet.label.date"/>: <c:out value="${comment.date}"/> </span> 
<br/>
<span>
<c:out value="${comment.comment}" escapeXml="false"/>
</span> 
</td> 
</tr>
</c:forEach>
<tr> 
<td class='portlet-table-td-center' colspan='2' >
<br/><br/><hr/>
</td> 
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width='95%' > 
<tr> 
<td class='portlet-table-td-right'> 
<input type='button' value='<fmt:message key="portlet.button.addComment"/>'
 onclick="javascript:addObjectComment(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${blog.id}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_BLOG %>);" class='portlet-form-button'/>
<!-- 
<input type='button' value='<fmt:message key="portlet.button.popIt"/>'
 onclick="javascript:popBlog('<c:out value="${blog.id}"/>','<c:out value="${requestScope.responseId}"/>');" class='portlet-form-button'/>
 -->
<input type='button' value='<fmt:message key="portlet.button.back"/>'
 onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" class='portlet-form-button'/>
</td>
</tr>		  
</table>
</fmt:bundle>
</body>
</html>
