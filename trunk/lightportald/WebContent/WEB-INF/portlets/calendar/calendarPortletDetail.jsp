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
	<table border='0' cellpadding='0' cellspacing='0'>
		<c:if test='${requestScope.error != null}'>
		<tr>
		<td class='portlet-msg-error' colspan = '2' >
		<c:out value="${requestScope.error}"/>
		</td>
		</tr>
		</c:if>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.what"/>:
		</td>
		<td class='portlet-table-td-left'>
		<c:out value="${requestScope.event.name}"/>
		<INPUT TYPE='hidden' NAME="id" value='<c:out value="${requestScope.event.id}"/>' />
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.when"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<c:out value="${requestScope.event.startDate}"/>:
		<c:out value="${event.time}"/>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.where"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<a href='http://maps.google.com/maps?q=<c:out value="${requestScope.event.location}"/>' target='_blank'><c:out value="${requestScope.event.location}"/></a>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>		
		</td>
		<td class='portlet-table-td-left'>	
		<input TYPE='radio' name='eventState' value='0' disabled='true'
		<c:if test='${event.state == 0}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.private"/></input>
		<input TYPE='radio' name='eventState' value='1' disabled='true'
		<c:if test='${event.state == 1}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.friend"/></input>
		<input TYPE='radio' name='eventState' value='2' disabled='true'
		<c:if test='${event.state == 2}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.public"/></input>		
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.description"/>:
		</td>
		<td class='portlet-table-td-left'>		
		<c:out value="${requestScope.event.desc}" />
		</td>
		</tr>						
		<tr>
		<td class='portlet-table-td-center' colspan ='2'>	
		<light:authenticateOwner> 
		<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal','modify=true;eventId=<c:out value="${event.id}"/>');" value='<fmt:message key="portlet.button.edit"/>' class='portlet-form-button' />
		</light:authenticateOwner>
		<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />		
		</td>
		</tr>
	</table>		
</form>
</fmt:bundle>
</body>
</html>