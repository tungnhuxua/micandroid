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
<jsp:include page="calendarHeader.jsp" ></jsp:include>
<fmt:bundle basename="resourceBundle">
<form name="form_<c:out value="${requestScope.responseId}"/>" action="<portlet:actionURL portletMode='VIEW'/>">
<table cellpadding='0' cellspacing='0' width= '98%' align='center' border='0'>
<tr>
<td class='portlet-table-td-center'>
<span class='portlet-table-title'>
<a href="javascript:void(0);" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;previous=1');"><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px' align='top'/></a>						
<c:out value="${requestScope.title}"/>
<a href="javascript:void(0);" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;next=1');"><img src='<%= request.getContextPath() %>/light/images/next.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px' align='top'/></a>
</span>
</td>
</tr>
</table>
<table cellpadding='0' cellspacing='0' width= '95%' align='center' class='portlet-table'>
<c:forEach var="calendar" items="${requestScope.lists}" varStatus="status">
<tr valign='top'>
<td class='portlet-table-td-right' width='50%'>
<c:out value="${calendar.desc}"/>
</td>
<td class='portlet-table-td-left' width='50%'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=1;eventId=0;startTime=<c:out value="${calendar.time}"/>');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<c:forEach var="event" items="${calendar.events}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=1;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.name}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateVisitor> 
<fmt:message key="portlet.label.calendar.busy"/>
</light:authenticateVisitor>
<br/>
</c:if>
<c:if test='${event.state == 1}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=1;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.name}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=1;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.name}"/></a>
</span>
</light:authenticateFriend>
<light:authenticateNotFriend> 
<fmt:message key="portlet.label.calendar.busy"/>
</light:authenticateNotFriend>
<br/>
</c:if>
<c:if test='${event.state == 2}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','maximized','type=1;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.name}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.name}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</span>
</td>
</tr>
</c:forEach>
</table>
</form>
</fmt:bundle>
</body>
</html>