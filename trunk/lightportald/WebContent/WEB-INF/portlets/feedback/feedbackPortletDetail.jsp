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
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin: 20px 0 0 0;'> 
<tr> 
<td class='portlet-table-td-left'> 
<b><c:out value="${feedback.subject}"/></b> 
</td> 
<td class='portlet-link' >
<a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','normal','');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' style='border: 0px' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
</tr> 
<tr> 
<td class='portlet-table-td-left' colspan='2'> 
<span class="portlet-note"> 					
<fmt:message key="portlet.label.postedBy"/>: 
<jsp:useBean id="feedback" scope="request" class="org.light.portlets.feedback.Feedback"/>
<light:avatarUrl name="<%= feedback.getDisplayName() %>" url="<%= feedback.getUri() %>" />
<fmt:message key="portlet.label.date"/>: <c:out value="${feedback.fullDate}"/>
<br/> 
<br/> 
</span> 
</td> 					
</tr> 
<tr> 
<td class='portlet-table-td-left'> 
<span>
<c:out value="${feedback.content}" escapeXml="false"/>
</span> 
</td> 
</tr> 
</table>
 
<c:if test='${feedback.commentCount > 0 }'>
<table border='0' cellpadding='0' cellspacing='0' width='95%' > 
<tr> 
<td class='portlet-table-td-left' colspan='2' >
<br/><br/><b><c:out value="${feedback.commentCount}"/> <fmt:message key="portlet.label.comments"/></b>:<br/> 
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
<light:authorize role="ROLE_ADMIN">
<a href='javascript:;' onclick="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','deleteComment',null,'<c:out value="${comment.id}"/>','view',null,'feedbackId=<c:out value="${feedback.id}"/>');" ><img src='<%= request.getContextPath() %>/light/images/deleteLink.gif' style='border: 0px;' height='11' width='11' title='<fmt:message key="portlet.button.delete"/>'/></a>
</light:authorize>
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
 onclick="javascript:addObjectComment(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${feedback.id}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_FEEDBACK %>);" class='portlet-form-button'/>
<input type='button' value='<fmt:message key="portlet.button.back"/>'
 onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" class='portlet-form-button'/>
</td>
</tr>		  
</table>
</fmt:bundle>
</body>
</html>