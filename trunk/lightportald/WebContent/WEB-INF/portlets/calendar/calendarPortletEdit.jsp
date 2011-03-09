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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit="javascript:return validateCalendarEvent(this);">           
	<table border='0' cellpadding='0' cellspacing='0' style='margin:10px 0 0 0;'>
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
		<input type='text' name='name' class='portlet-form-input-field' size='24' value='<c:out value="${requestScope.event.name}"/>'/>		
		<INPUT TYPE='hidden' NAME="id" value='<c:out value="${requestScope.event.id}"/>' />
		</td>
		</tr>
		<tr valign='top'>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.when"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<select name='startMonth' size='1' class='portlet-form-select'>
		<c:forEach var="month" items="${requestScope.months}" >
		<option value='<c:out value="${month.id}"/>'
		<c:if test='${requestScope.event.startMonth == month.id}'>
		selected='selected'
		</c:if>
		><c:out value="${month.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='startDay' size='1' class='portlet-form-select'>
		<c:forEach var="day" items="${requestScope.days}" >
		<option value='<c:out value="${day.id}"/>'
		<c:if test='${requestScope.event.startDay == day.id}'>
		selected='selected'
		</c:if>
		><c:out value="${day.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='startYear' size='1' class='portlet-form-select'>
		<c:forEach var="year" items="${requestScope.years}" >
		<option value='<c:out value="${year.id}"/>'
		<c:if test='${requestScope.event.startYear == year.id}'>
		selected='selected'
		</c:if>
		><c:out value="${year.desc}"/></option>
		</c:forEach>
		</select>
		
		<br/>
		<select name='startTime' size='1' class='portlet-form-select'>
		<c:forEach var="time" items="${requestScope.lists}" >
		<option value='<c:out value="${time.time}"/>'
		<c:if test='${requestScope.event.startTime ==time.time}'>
		selected='selected'
		</c:if>
		><c:out value="${time.desc}"/></option>
		</c:forEach>
		</select>
		-
		<%-- 
		<select name='endMonth' size='1' class='portlet-form-select'>
		<c:forEach var="month" items="${requestScope.months}" >
		<option value='<c:out value="${month.id}"/>'
		<c:if test='${requestScope.event.endMonth == month.id}'>
		selected='selected'
		</c:if>
		><c:out value="${month.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='endDay' size='1' class='portlet-form-select'>
		<c:forEach var="day" items="${requestScope.days}" >
		<option value='<c:out value="${day.id}"/>'
		<c:if test='${requestScope.event.endDay == day.id}'>
		selected='selected'
		</c:if>
		><c:out value="${day.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='endYear' size='1' class='portlet-form-select'>
		<c:forEach var="year" items="${requestScope.years}" >
		<option value='<c:out value="${year.id}"/>'
		<c:if test='${requestScope.event.endYear == year.id}'>
		selected='selected'
		</c:if>
		><c:out value="${year.desc}"/></option>
		</c:forEach>
		</select>
		--%>
		<select name='endTime' size='1' class='portlet-form-select'>
		<c:forEach var="time" items="${requestScope.lists}" >
		<option value='<c:out value="${time.time}"/>'
		<c:if test='${requestScope.event.endTime ==time.time}'>
		selected='selected'
		</c:if>
		><c:out value="${time.desc}"/></option>
		</c:forEach>
		</select>
		<c:if test='${requestScope.event.startTime != 10000}'>
		<input TYPE='checkbox' name='allDay' value='1'><fmt:message key="portlet.label.event.allDay"/></input>
		</c:if>
		<c:if test='${requestScope.event.startTime == 10000}'>
		<input TYPE='checkbox' name='allDay' value='1' checked="checked"><fmt:message key="portlet.label.event.allDay"/></input>
		</c:if>
		<br/>
		<input TYPE='radio' name='status' value='0' checked="checked"><fmt:message key="portlet.label.event.once"/></input>
		<input TYPE='radio' name='status' value='1'><fmt:message key="portlet.label.event.daily"/></input>
		<input TYPE='radio' name='status' value='2'><fmt:message key="portlet.label.event.weekly"/></input>
		<input TYPE='radio' name='status' value='3'><fmt:message key="portlet.label.event.monthly"/></input>

		<fmt:message key="portlet.label.event.till"/>
		<select name='endMonth' size='1' class='portlet-form-select'>
		<c:forEach var="month" items="${requestScope.months}" >
		<option value='<c:out value="${month.id}"/>'
		<c:if test='${requestScope.event.endMonth == month.id}'>
		selected='selected'
		</c:if>
		><c:out value="${month.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='endDay' size='1' class='portlet-form-select'>
		<c:forEach var="day" items="${requestScope.days}" >
		<option value='<c:out value="${day.id}"/>'
		<c:if test='${requestScope.event.endDay == day.id}'>
		selected='selected'
		</c:if>
		><c:out value="${day.desc}"/></option>
		</c:forEach>
		</select>
		/
		<select name='endYear' size='1' class='portlet-form-select'>
		<c:forEach var="year" items="${requestScope.years}" >
		<option value='<c:out value="${year.id}"/>'
		<c:if test='${requestScope.event.endYear == year.id}'>
		selected='selected'
		</c:if>
		><c:out value="${year.desc}"/></option>
		</c:forEach>
		</select>
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>
		<fmt:message key="portlet.label.where"/>:
		</td>
		<td class='portlet-table-td-left'>	
		<input type='text' name='location' class='portlet-form-input-field' size='24' value='<c:out value="${requestScope.event.location}"/>'/>		
		</td>
		</tr>
		<tr>
		<td class='portlet-table-td-left'>		
		</td>
		<td class='portlet-table-td-left'>	
		<input TYPE='radio' name='eventState' value='0' 
		<c:if test='${event.state == 0}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.private"/></input>
		<input TYPE='radio' name='eventState' value='1'
		<c:if test='${event.state == 1}'>
		checked="checked"
		</c:if>
		><fmt:message key="portlet.label.event.friend"/></input>
		<input TYPE='radio' name='eventState' value='2'
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
		<c:if test='${requestScope.event.desc != null}'>
		<textarea name='desc' class='portlet-form-textarea-field' rows='3' cols='36' ><c:out value="${requestScope.event.desc}" /></textarea>
		</c:if>	
		<c:if test='${requestScope.event.desc == null}'>
		<textarea name='desc' class='portlet-form-textarea-field' rows='3' cols='36' ></textarea>
		</c:if>	
		</td>
		</tr>						
		<tr>
		<td class='portlet-table-td-center' colspan ='2'>
		<light:authenticateOwner> 
		<input type='submit' name='action' onClick="document.pressed='save';document.resetLastAction='1';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
		</light:authenticateOwner>
		<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />		
		</td>
		</tr>
	</table>		
</form>
</fmt:bundle>
</body>
</html>