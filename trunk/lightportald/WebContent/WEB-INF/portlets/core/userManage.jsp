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
<light:authorize role="ROLE_ADMIN"> 
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW' windowState='MAXIMIZED'/>">
<table border='0' cellpadding='0' cellspacing='0' width='99%'>
<tr>
<td class='portlet-link-left' colspan='3'>
<a href='javascript:;' onclick="javascript:Light.showSignUp('${id}');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addUser"/></a>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr valign=top>
<td class='portlet-table-td-right'><LABEL FOR='email' ACCESSKEY='U'>ID <fmt:message key="portlet.label.or"/> <fmt:message key="portlet.label.userId"/>: </LABEL></td>
<td class='portlet-table-td-left'>
<input type='text' name='email' value='<c:out value="${requestScope.email}"/>' class='portlet-form-input-field' size='20' /> 
<input type='submit' name='action' onClick="document.pressed='search'" value='<fmt:message key="portlet.button.search"/>' class='portlet-form-button' />
</td>
</tr>
</table>
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.searchedUser != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.displayName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='displayName' value='<c:out value="${searchedUser.displayName}"/>' class='portlet-form-input-field' size='30' />
<input type='hidden' name='id' value = '<c:out value="${searchedUser.id}"/>' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'></td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='disabled' value='1' 
<c:if test='${searchedUser.disabled == 1}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.disabled"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'></td>
<td class='portlet-table-td-left'>
<input type='checkbox' name='locked' value='1' 
<c:if test='${searchedUser.locked == 1}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.locked"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.userPassword"/>: </td>
<td class='portlet-table-td-left'>
<input type='password' name='password' value='<c:out value="${searchedUser.password}"/>' class='portlet-form-input-field' size='30' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.confirmPassword"/>:</td>
<td class='portlet-table-td-left'>
<input type='password' name='confirmPassword' value='<c:out value="${searchedUser.password}"/>' class='portlet-form-input-field' size='30' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.birth"/>: </td>
<td class='portlet-table-td-left'>
<select name='birthM' size='1' class='portlet-form-select'>
<c:forEach var="month" items="${requestScope.months}" >
<option value='<c:out value="${month.id}"/>'
<c:if test='${searchedUser.birthM == month.id}'>
selected="selected"
</c:if>
><c:out value="${month.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthD' size='1' class='portlet-form-select'>
<c:forEach var="day" items="${requestScope.days}" >
<option value='<c:out value="${day.id}"/>'
<c:if test='${searchedUser.birthD == day.id}'>
selected="selected"
</c:if>
><c:out value="${day.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthY' size='1' class='portlet-form-select'>
<c:forEach var="year" items="${requestScope.years}" >
<option value='<c:out value="${year.id}"/>'
<c:if test='${searchedUser.birthY == year.id}'>
selected="selected"
</c:if>
><c:out value="${year.desc}"/></option>
</c:forEach>
</select>
<img src='<%= request.getContextPath() %>/light/images/faq.gif' title='<fmt:message key="portlet.label.faq.birth"/>' style='border: 0px;' width='16' height='16'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.gender"/>: </td>
<td class='portlet-table-td-left'>
<input TYPE='radio' name='gender' value='M' 
<c:if test='${searchedUser.gender == "M"}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.gender.male"/></input>
<input TYPE='radio' name='gender' value='F'
<c:if test='${searchedUser.gender == "F"}'>
checked="checked"
</c:if>
><fmt:message key="portlet.label.gender.female"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.language"/>: </td>
<td class='portlet-table-td-left'>
<select name='language' size='1' class='portlet-form-select'>
<c:forEach var="language" items="${requestScope.languages}" >
<c:if test='${language.supported}'>
<option value='<c:out value="${language.id}"/>'
<c:if test='${searchedUser.language == language.id}'>
selected="selected"
</c:if>
 ><c:out value="${language.desc}"/></option>
 </c:if>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.timeZone"/>: </td>
<td class='portlet-table-td-left'>
<select name='timeZone' size='1' class='portlet-form-select'>
<c:forEach var="timeZone" items="${requestScope.timeZones}" >
<option value='<c:out value="${timeZone.id}"/>'
<c:if test='${searchedUser.timeZone == timeZone.id}'>
selected="selected"
</c:if>
><c:out value="${timeZone.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.myUrl"/>: </td>
<td class='portlet-table-td-left'>
<span class='portlet-item'>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${searchedUser.uri}"/>' target='_blank'>http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${searchedUser.uri}"/></a>
</span>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<select name='country' size='1' class='portlet-form-select'>
<option value='' ></option>
<c:forEach var="country" items="${requestScope.countries}" >
<option value='<c:out value="${country.id}"/>'
<c:if test='${searchedUser.country == country.id}'>
selected="selected"
</c:if>
 ><c:out value="${country.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='<c:out value="${searchedUser.province}"/>' class='portlet-form-input-field' size='18' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='<c:out value="${searchedUser.city}"/>' class='portlet-form-input-field' size='18' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.postalCode"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='<c:out value="${searchedUser.postalCode}"/>' class='portlet-form-input-field' size='18' />
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='60%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='delete'" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</c:if>
</form>
</fmt:bundle>
</light:authorize>
</body>
</html>