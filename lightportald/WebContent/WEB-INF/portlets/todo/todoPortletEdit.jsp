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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit="javascript:return validateTodo(this);">           
	<table border='0' cellpadding='0' cellspacing='0' width='95%' style='padding: 10px 0 0 0;'>
		<c:if test='${requestScope.missingField != null}'>
			<tr>
			<td class='portlet-table-td-left'>
			Title is required fields.
			</td>
			</tr>
		</c:if>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.title"/>:
			
		<input type='text' name='name' class='portlet-form-input-field' style='width:60%;' value='<c:out value="${requestScope.todo.name}"/>' 
			<c:if test="${requestScope.todo.status !=0}">
			style="text-decoration: underline line-through;"
			</c:if>
		/>		
		<INPUT TYPE='hidden' NAME="id" value='<c:out value="${requestScope.todo.id}"/>' />
		<fmt:message key="portlet.label.priority"/>:
		<select name='priority' size='1'  class='portlet-form-select' style="width:40px">
		<c:forEach var="i" begin="1" end="10" step="1">
		<c:if test='${requestScope.todo.priority == i}'>
		<option selected='selected' value='<c:out value="${i}" />'><c:out value="${i}" /></option>
		</c:if>
		<c:if test='${requestScope.todo.priority != i}'>
		<option value='<c:out value="${i}" />'><c:out value="${i}" /></option>
		</c:if>
		</c:forEach>
		</select>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.description"/>:
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<textarea name='description' class='portlet-form-textarea-field' rows='5' cols='33'><c:if test='${todo.description != null}'><c:out value="${todo.description}" escapeXml="false" /></c:if></textarea>
		</td>
		</tr>						
		<tr>
		<td class='portlet-table-td-left'>
		<input type='submit' name='action' onClick="document.pressed='save';document.resetLastAction='1';increaseTodoPortletTitle('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.todo.id}"/>);" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
		<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />		
		</td>
		</tr>
	</table>		
</form>
</fmt:bundle>
</body>
</html>