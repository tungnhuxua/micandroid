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
<light:authenticateOwner>  
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT' windowState='MAXIMIZED'><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addToDo"/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<c:forEach var="todoList" items="${requestScope.todoLists}" >
<tr>
<c:if test="${todoList.status !=0}">
<td class='portlet-table-td-left' width='80%' style="text-decoration: line-through;">

<input TYPE='checkbox' name='<c:out value="${todoList.id}"/>' checked='yes' value='1' onClick="javascript:changeStatus('<c:out value="${requestScope.responseId}"/>',this.name);">
</input>

<span  
   onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_TODO_ITEM %>,'<c:out value="${todoList.id}"/>');"
   onmouseout="javascript:Light.hideSummary();">
<c:out value="${todoList.name}"/>
</span>
</td>
</c:if>
<c:if test="${todoList.status ==0}">
<td class='portlet-table-td-left' width='80%' style="color: #3169B5;">

<input TYPE='checkbox' name='<c:out value="${todoList.id}"/>' value='1' onClick="javascript:changeStatus('<c:out value="${requestScope.responseId}"/>',this.name,'<c:out value="${todoList.status}"/>');">
</input>

<span  
   onmouseover="javascript:Light.showSummary(event,'<c:out value="${requestScope.responseId}"/>',<%= org.light.portal.util.Constants._OBJECT_TYPE_TODO_ITEM %>,'<c:out value="${todoList.id}"/>');"
   onmouseout="javascript:Light.hideSummary();">
<c:out value="${todoList.name}"/> 
<c:if test='${requestScope.state == "maximized"}'>
(priority <c:out value="${todoList.priority}"/>)
</c:if>
</span>
</td>
</c:if>
<td class='portlet-table-td-right'>

<a href='javascript:;' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','','<c:out value="${todoList.id}"/>');"><img src="<%= request.getContextPath() %>/light/images/edit.gif" style='border: 0px;' height='11' width='11' /></a>
<input type="image" src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${todoList.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;decreasePortletTitle('<c:out value="${requestScope.responseId}"/>');"/>

</td>
</tr>
</c:forEach>
</table>
</form>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null }'>
<span class="portlet-rss" style="text-align:right;">
<a href='javascript:void(0)' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" >more......</a> 
</span>
</c:if>
</light:authenticateOwner>
</fmt:bundle>
</body>
</html>