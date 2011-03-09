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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit="javascript:return validateForumReply(this);">
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<input type='hidden' name='categoryId'  value='<c:out value="${categoryId}"/>' /> 
<input type='hidden' name='forumId'  value='<c:out value="${forumId}"/>' /> 
<tr>
<td class='portlet-table-td-left' colspan = '2' >
<b>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>');" ><c:out value="${categoryName}"/></a>
->
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" ><c:out value="${forumName}"/></a>
->
<c:out value="${topicName}"/>
</b>
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/rss/forum<c:out value="${forumId}"/>.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' style='border: 0px' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan = '2' >
<c:if test='${requestScope.topicId == null}'>
<fmt:message key="portlet.label.topic"/>:
<input type='text' name='topic'  value='<c:out value='${requestScope.topic}'/>' class='portlet-form-input-field' size='50' /> 
<input type='hidden' name='newTopic'  value='1' /> 
</c:if>
<c:if test='${requestScope.topicId != null}'>
<input type='hidden' name='topicId'  value='<c:out value="${requestScope.topicId}"/>' /> 
<input type='hidden' name='newTopic'  value='0' />
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.content"/>:
<input type='radio' name='format' value='0' class='portlet-form-radio'
<c:if test='${format == null || format == 0}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.format.html"/></input> 
<input type='radio' name='format' value='1' class='portlet-form-radio'
<c:if test='${format == 1}'> checked="checked" </c:if>
>
<fmt:message key="portlet.label.format.text"/></input> 
<light:authorizeEditor>
<input type='checkbox' name='htmlEditor' value='1' onchange="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>','','','','','edit','maximized','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" 
<c:if test='${showHtmlEditor != null}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.htmlEditor"/></input>
</light:authorizeEditor>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<c:if test='${showHtmlEditor == null}'>
<textarea name='content' class='portlet-form-textarea-field' rows='10' cols='60' ><c:if test='${requestScope.content != null}'><c:out value='${requestScope.content}'/></c:if></textarea>
</c:if>
<c:if test='${showHtmlEditor != null}'>
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.editor.page") %>'>
	<jsp:param name='text' value='<%= (request.getAttribute("content") == null) ? "" : request.getAttribute("content") %>' />
</jsp:include>
</c:if>
</td>
</tr> 
<tr>
<td class='portlet-table-td-right' colspan='2' >
<c:if test='${requestScope.topicId == null}'>
<input type='submit' name='action' onClick="document.pressed='save';document.mode='view';document.resetLastAction='1';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</c:if>
<c:if test='${requestScope.topicId != null}'>
<input type='submit' name='action' onClick="document.pressed='reply';document.mode='view';document.resetLastAction='1';" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
</c:if>
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','categoryId=<c:out value="${categoryId}"/>;forumId=<c:out value="${forumId}"/>');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
