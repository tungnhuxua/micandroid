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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error'>
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left'>
<br/>
<fmt:message key="portlet.label.category"/>:
<c:if test='${requestScope.blog != null}'>
<c:forEach var="bean" items="${requestScope.categories}" >
<input type='radio' name='category' value='<c:out value="${bean.id}"/>' class='portlet-form-radio'
<c:if test='${bean.id == blog.categoryId}'>
checked="checked"
</c:if>
>
<c:out value="${bean.name}"/></input> 
</c:forEach>
</c:if>
<c:if test='${requestScope.blog == null}'>
<c:forEach var="bean" items="${requestScope.categories}" >
<input type='radio' name='category' value='<c:out value="${bean.id}"/>' class='portlet-form-radio'
<c:if test='${bean.id == requestScope.category}'>
checked="checked"
</c:if>
>
<c:out value="${bean.name}"/></input> 
</c:forEach>
</c:if>
<span class='portlet-link-left' > 
<input type='text' name='newCategory'  value='' class='portlet-form-input-field' size='12' /> 
<input type='submit' name='action' onClick="document.pressed='category';" value='<fmt:message key="portlet.label.addCategory"/>' class='portlet-form-button' />
</span>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.title"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.blog != null}'>
<input type='hidden' name='blogId' value='<c:out value="${blog.id}"/>' />
<input type='text' name='subject'  value='<c:out value="${blog.title}"/>' class='portlet-form-input-field' style='width:98%;'/> 
</c:if>
<c:if test='${requestScope.blog == null}'>
<input type='hidden' name='blogId' value='<c:out value="${blogId}"/>' />
<input type='text' name='subject'  value='<c:out value="${subject}"/>' class='portlet-form-input-field' style='width:98%;' /> 
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.summary"/>:
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='summary' class='portlet-form-textarea-field' rows='5' cols='50' ><c:if test='${requestScope.blog != null}'><c:out value="${blog.summary}"/></c:if><c:if test='${requestScope.blog == null}'><c:out value="${summary}" /></c:if></textarea>
</td>
</tr>

<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.content"/>:
<light:authorizeEditor>
<input type='checkbox' name='htmlEditor' value='1' onchange="<portlet:actionURL windowState='maximized' portletMode='EDIT'><portlet:param name='add' value='add'/></portlet:actionURL>" 
<c:if test='${showHtmlEditor != null}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.htmlEditor"/></input>
</light:authorizeEditor>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<c:if test='${showHtmlEditor == null}'>
<textarea name='content' class='portlet-form-textarea-field' rows='16' cols='40' ><c:if test='${requestScope.blog != null}'><c:out value="${blog.content}" escapeXml="false" /></c:if><c:if test='${requestScope.blog == null}'><c:out value="${content}" escapeXml="false" /></c:if></textarea>
</c:if>
<c:if test='${showHtmlEditor != null}'>
<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.editor.page") %>'>
	<jsp:param name='text' value='<%= (request.getAttribute("content") == null) ? "" : request.getAttribute("content") %>' />
</jsp:include>
</c:if>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='draft';document.resetLastAction='1';" value='<fmt:message key="portlet.button.draft"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='save';document.resetLastAction='1';" value='<fmt:message key="portlet.button.publish"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='preview'" value='<fmt:message key="portlet.button.preview"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
