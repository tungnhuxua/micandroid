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
<head></head>
<body>
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>" >           
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
		<fmt:message key="portlet.label.startTime"/>:
		</td>
		<td class='portlet-table-td-left'>
		<select name='startTime' size='1' class='portlet-form-select' length='24'>
		<c:forEach var="time" items="${requestScope.lists}" >
		<option value='<c:out value="${time.time}"/>'
		<c:if test='${calendar.startTime ==time.time}'>
		selected='selected'
		</c:if>
		><c:out value="${time.desc}"/></option>
		</c:forEach>
		</select>				
		</td>
		</tr>
		<tr valign='top'>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.endTime"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<select name='endTime' size='1' class='portlet-form-select'>
		<c:forEach var="time" items="${requestScope.lists}" >
		<option value='<c:out value="${time.time}"/>'
		<c:if test='${calendar.endTime ==time.time}'>
		selected='selected'
		</c:if>
		><c:out value="${time.desc}"/></option>
		</c:forEach>
		</select>	
		</td>
		</tr>		
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.interval"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<select name='interval' size='1' class='portlet-form-select'>
		<c:forEach var="time" items="${requestScope.intervals}" >
		<option value='<c:out value="${time.time}"/>'
		<c:if test='${calendar.interval ==time.time}'>
		selected='selected'
		</c:if>
		><c:out value="${time.desc}"/></option>
		</c:forEach>
		</select>			
		</td>
		</tr>		
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.calendar.time"/>
		</td>
		<td class='portlet-table-td-left'>	
		<input TYPE='radio' name='timeType' value='0'
		<c:if test='${calendar.type == 0}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.calendar.hour.12"/></input>	
		<input TYPE='radio' name='timeType' value='1'
		<c:if test='${calendar.type == 1}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.calendar.hour.24"/></input>		
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>		
		<fmt:message key="portlet.label.event.defaultState"/>
		</td>
		<td class='portlet-table-td-left'>	
		<input TYPE='radio' name='eventState' value='0' 
		<c:if test='${calendar.state == 0}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.private"/></input>
		<input TYPE='radio' name='eventState' value='1'
		<c:if test='${calendar.state == 1}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.friend"/></input>
		<input TYPE='radio' name='eventState' value='2'
		<c:if test='${calendar.state == 2}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.public"/></input>		
		</td>
		</tr>
		
		<tr>
		<td class='portlet-table-td-right' colspan ='2'>
		<light:authenticateOwner> 
		<input type='submit' name='action' onClick="document.pressed='config'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
		</light:authenticateOwner>
		<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />		
		</td>
		</tr>
	</table>		
</form>
</fmt:bundle>
</body>
</html>