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
<a href="javascript:void(0);" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=0;previous=1');"><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px;'  height='16' width='16' align='top'/></a>						
<!--  <span><span class="icons"><input type="button" class="icons add" style="display: block;border-style: none;border-width:0;float:none;" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=0;previous=1');" value=""></input></span></span>
<a class="icons" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=0;previous=1');"><span class="icons add" style="display: block;border-style: none;border-width:0;float:none;" ></span></a>-->
<c:out value="${requestScope.title}"/>
<a href="javascript:void(0);" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=0;next=1');"><img src='<%= request.getContextPath() %>/light/images/next.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px;'  height='16' width='16' align='top'/></a>
</span>
</td>
</tr>
</table>
<table cellpadding='0' cellspacing='0' width= '95%' align='center' class='portlet-table'>
<tr>
<td class='portlet-table-td-left' colspan='7'>
<c:out value="${today}"/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='7'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${requestScope.events}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.name}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
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
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='7'>
<fmt:message key="portlet.label.overview.next"/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=1',false);" >
<c:out value="${today1}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=2',false);" >
<c:out value="${today2}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=3',false);" >
<c:out value="${today3}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=4',false);" >
<c:out value="${today4}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=5',false);" >
<c:out value="${today5}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=6',false);" >
<c:out value="${today6}"/>
</a>
</span>
</td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','type=1;nextDay=7',false);" >
<c:out value="${today7}"/>
</a>
</span>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=1');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events1}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateFriend>
<light:authenticateNotFriend> 
<fmt:message key="portlet.label.calendar.busy"/>
</light:authenticateNotFriend>
<br/>
</c:if>
<c:if test='${event.state == 2}'>
<light:authenticateOwner> 
<input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" name="<c:out value='${event.id}'/>" style='border: 0px;' height='11' width='11' onClick="document.pressed='delete';document.parameter=this.name;"/>    
</light:authenticateOwner>
<c:out value="${event.time}"/>
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=2');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events2}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=3');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events3}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=4');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<c:forEach var="event" items="${events4}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach></td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=5');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events5}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=6');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events6}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
<td class='portlet-table-td-left'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=0;nextDay=7');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/></a>
<br/>
</light:authenticateOwner>
<light:authenticateVisitor>
<br/><br/>
</light:authenticateVisitor>
<c:forEach var="event" items="${events7}" varStatus="status">
<c:if test='${event.state == 0}'>
<light:authenticateOwner> 
<a href='javascript:void(0)' onclick="javascript:deleteCalendarEvent('<c:out value="${requestScope.responseId}"/>',<c:out value="${event.id}"/>,<c:out value="${event.parentId}"/>);"><image title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11'  /></a> 
</light:authenticateOwner>
<c:out value="${event.time}"/>
<light:authenticateOwner> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
</light:authenticateOwner>
<light:authenticateFriend> 
<span class='portlet-item'>
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
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
<a href='javascript:void(0)' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','type=0;eventId=<c:out value="${event.id}"/>');" >
<c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
<c:if test='${event.state == 3}'>
<span class='portlet-item' 
   onmouseover="javascript:showDesc(event,'<c:out value="${event.desc}"/>','<c:out value="${requestScope.responseId}"/>');"
   onmouseout="javascript:hideDesc();">
<a href='<c:out value="${event.link}"/>' target="_blank"><c:out value="${event.shortName}"/></a>
</span>
<br/>
</c:if>
</c:forEach>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>