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
<fmt:bundle basename="resourceBundle">
<textarea id="registrationPortlet.view" style="display:none;">
	<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
	<form name='form_${id}' action="javascript:Light.executeAction('${id}',this.form,'step1',null,null,'VIEW','maximized');" onsubmit="javascript:return validateSignUp(this);">
	<table border='0' cellpadding='0' cellspacing='0' width='80%'>
	<tr>
	<td class='portlet-table-td-left' width='20%'>
	</td>
	<td class='portlet-table-td-left'>
	<p style='margin:10px 0;'><b><fmt:message key="portlet.registration.label.signup.message1"/></b></p>
	<p style='margin:10px 0;'><fmt:message key="portlet.registration.label.signup.message2"/></p>
	</td>
	</tr>
	</table>
	<table border='0' cellpadding='0' cellspacing='0' width='80%'>
	<tr>
	<td class='portlet-table-td-right' width='20%'>
	<img src='<%= request.getContextPath() %>/light/images/faq.gif' title='<fmt:message key="portlet.label.faq.email"/>' width='16' height='16'/>
	<label FOR='email' ACCESSKEY='U'>*<fmt:message key="portlet.label.email"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='text' name='email' value='' class='portlet-form-input-field' size='30' onchange="validateUserId(this.value,'${id}');" AUTOCOMPLETE='OFF'/>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-right'><label>*<fmt:message key="portlet.label.displayName"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='text' name='displayName' value=''
	class='portlet-form-input-field' size='30' />
	</td>
	</tr>	
	<tr>
	<td class='portlet-table-td-right'><label>*<fmt:message key="portlet.label.myUrl"/>: </label></td>
	<td class='portlet-table-td-left'>	
	<%
	int format = org.light.portal.util.PropUtil.getInt("portal.url.format");
	if(format == 1){
	%>
	<label>http://</label><input type='text' name='iUri' value='' class='portlet-form-input-field' size='30' onchange="validateInternalUri(this.value,'${id}');" AUTOCOMPLETE='OFF'/><label>.<c:out value="${host}"/></label>
	<%
	}
	%>
	<%
	if(format != 1){
	%>
	<label>http://<c:out value="${sessionScope.org.userSpacePrefix}"/></label><input type='text' name='iUri' value='' class='portlet-form-input-field' size='18' onchange="validateInternalUri(this.value,'${id}');" AUTOCOMPLETE='OFF'/>
	<%
	}
	%>
	</td>
	</tr>	
	<tr>
	<td class='portlet-table-td-right'><label>*<fmt:message key="portlet.label.userPassword"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='password' name='password' value=''
	class='portlet-form-input-field' size='30' />
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-right'><label>*<fmt:message key="portlet.label.confirmPassword"/>: </label></td>
	<td class='portlet-table-td-left'>
	<input type='password' name='confirmPassword' value=''
	class='portlet-form-input-field' size='30' />
	</td>
	</tr>	
	<tr>
	<td class='portlet-table-td-right'></td>
	<td class='portlet-table-td-left'>
	<label><fmt:message key="portlet.message.accept1"/></label>
	<span class='portlet-item'> 
	<a href="javascript:;" onclick="javascript:Light.showTerms();"><fmt:message key="portlet.message.accept2"/></a>
	</span>
	<label><fmt:message key="portlet.message.accept3"/></label>
	<span class='portlet-item'>  
	<a href="javascript:;" onclick="javascript:Light.showPrivacy();"><fmt:message key="portlet.message.accept4"/></a>   
	</span>
	</input>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-right'></td>
	<td class='portlet-table-td-left'>
	<input type='submit' name='action' value='<fmt:message key="portlet.button.register"/>' class='portlet-form-button' />
	<input type='button' name='action' onClick="javascript:Light.closePortlet('${id}');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
	</td>
	</tr>
	</table>
	</form>
</textarea>

<textarea id="registrationStep2.view" style="display:none;">
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.birth"/>: </td>
<td class='portlet-table-td-left'>
<select name='birthM' size='1' class='portlet-form-select'>
<c:forEach var="month" items="${requestScope.months}" >
<option value='<c:out value="${month.id}"/>'><c:out value="${month.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthD' size='1' class='portlet-form-select'>
<c:forEach var="day" items="${requestScope.days}" >
<option value='<c:out value="${day.id}"/>'><c:out value="${day.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthY' size='1' class='portlet-form-select'>
<c:forEach var="year" items="${requestScope.years}" >
<option value='<c:out value="${year.id}"/>'><c:out value="${year.desc}"/></option>
</c:forEach>
</select>
<img src='<%= request.getContextPath() %>/light/images/faq.gif' title='<fmt:message key="portlet.label.faq.birth"/>' width='16' height='16'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.gender"/>: </td>
<td class='portlet-table-td-left'>
<input TYPE='radio' name='gender' value='M'><fmt:message key="portlet.label.gender.male"/></input>
<input TYPE='radio' name='gender' value='F'><fmt:message key="portlet.label.gender.female"/></input>
<input TYPE='radio' name='gender' value='' checked="checked"><fmt:message key="portlet.label.unknow"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.language"/>: </td>
<td class='portlet-table-td-left'>
<select name='language' size='1' class='portlet-form-select'>
<c:forEach var="language" items="${requestScope.languages}" >
<c:if test='${language.supported}'>
<option value='<c:out value="${language.id}"/>'
<c:if test='${sessionScope.currentLocale == language.id}'>
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
<option value='<c:out value="${timeZone.id}"/>'><c:out value="${timeZone.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<select name='country' size='1' class='portlet-form-select'>
<option value='' ></option>
<c:forEach var="country" items="${requestScope.countries}" >
<option value='<c:out value="${country.desc}"/>'><c:out value="${country.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='' class='portlet-form-input-field' size='18' />
<%-- 
<select name='plProvince' size='1' class='portlet-form-select'>
<option value='' ></option>
<c:forEach var="province" items="${requestScope.provinces}" >
<option value='<c:out value="${province.desc}"/>' >
<c:out value="${province.desc}"/></option>
</c:forEach>
</select>
--%>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='' class='portlet-form-input-field' size='18' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.postalCode"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='' class='portlet-form-input-field' size='18' />
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-center'>
<input type='submit' name='action' onClick="document.pressed='step2'" value='<fmt:message key="portlet.button.next"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='skip2'" value='<fmt:message key="portlet.button.skip"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.refreshPortal(true);" value='<fmt:message key="portlet.button.done"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<textarea id="registrationStep3.view" style="display:none;">
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-left' width='30%'>
</td>
<td class='portlet-table-td-left' colspan='2'>
<br/>
<br/>
<fmt:message key="portlet.label.chooseChannel"/>:
</td>
</tr>
<c:forEach var="channel" items="${channels}"  varStatus="status">
<c:if test='${status.index % 2 == 0}'>
<tr>
<td class='portlet-table-td-left' width='30%'>
</td>
</c:if>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='channels' value='<c:out value="${channel.name}"/>'><c:out value="${channel.desc}"/></input>
</td>
<c:if test='${status.index % 2 == 1}'>
</tr>
</c:if>
</c:forEach>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='step3'" value='<fmt:message key="portlet.button.next"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='skip3'" value='<fmt:message key="portlet.button.skip"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.refreshPortal(true);" value='<fmt:message key="portlet.button.done"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<textarea id="registrationLastStep.view" style="display:none;">
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-center' >
<b><fmt:message key="portlet.message.signup.success"/></b>
</td>
</tr>
<br/>
<br/>
<tr>
<td class='portlet-table-td-center'>
<input type='button' name='action' onClick="javascript:Light.refreshPortal(true);" value='<fmt:message key="portlet.button.done"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</textarea>
</fmt:bundle>