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
<textarea id="myAccountBasic.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/myAccountHeader.jsp"%>
<br/>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='60%'>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.email"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='email' value='${user.email}' class='portlet-form-input-field' size='24' /> 
<img src='<%= request.getContextPath() %>/light/images/faq.gif' title='<fmt:message key="portlet.label.alternateEmail"/>' width='16' height='16'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.birth"/>: </td>
<td class='portlet-table-td-left'>
<select name='birthM' size='1' class='portlet-form-select'>
<c:forEach var="month" items="${requestScope.months}" >
<option value='<c:out value="${month.id}"/>' 
{if user.birthM == '<c:out value="${month.id}"/>' } selected="selected" {/if}
><c:out value="${month.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthD' size='1' class='portlet-form-select'>
<c:forEach var="day" items="${requestScope.days}" >
<option value='<c:out value="${day.id}"/>'
{if user.birthD == '<c:out value="${day.id}"/>' } selected="selected" {/if}
><c:out value="${day.desc}"/></option>
</c:forEach>
</select>
/
<select name='birthY' size='1' class='portlet-form-select'>
<c:forEach var="year" items="${requestScope.years}" >
<option value='<c:out value="${year.id}"/>'
{if user.birthY == '<c:out value="${year.id}"/>' } selected="selected" {/if}
><c:out value="${year.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.gender"/>: </td>
<td class='portlet-table-td-left'>
<input TYPE='radio' name='gender' value='M' 
{if user.gender == 'M'}
checked="checked"
{/if}
><fmt:message key="portlet.label.gender.male"/></input>
<input TYPE='radio' name='gender' value='F'
{if user.gender == 'F'}
checked="checked"
{/if}
><fmt:message key="portlet.label.gender.female"/></input>
<input TYPE='radio' name='gender' value=''
{if user.gender == ''}
checked="checked"
{/if}
><fmt:message key="portlet.label.unknow"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.occupation"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='occupation' value='${userProfile.occupation}' class='portlet-form-input-field' size='40' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<select name='country' size='1' class='portlet-form-select' value='${user.country}'>
<option value='' ></option>
<c:forEach var="country" items="${requestScope.countries}" >
<option value='<c:out value="${country.desc}"/>'
{if user.country == '<c:out value="${country.desc}"/>'}
selected="selected"
{/if}
 ><c:out value="${country.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='${user.province}' class='portlet-form-input-field' size='18' />
<%--
<select name='province' size='1' class='portlet-form-select'>
<option value='' ></option>
<c:forEach var="province" items="${requestScope.provinces}" >
<option value='<c:out value="${province.desc}"/>' 
{if user.province == <c:out value="${province.desc}"/>}
selected="selected"
{/if}
><c:out value="${province.desc}"/></option>
</c:forEach>
</select>
--%>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='${user.city}' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.postalCode"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='${user.postalCode}' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.ethnicity"/>: </td>
<td class='portlet-table-td-left'>
<select name='ethnicity' size='1'  class='portlet-form-select' STYLE="width: 120px" >
	<option value="0"
	{if userProfile.ethnicity == 0}
	selected="selected"
	{/if}
	>No Answer</option>
	{if userProfile.ethnicity == 1}
	selected="selected"
	{/if}
	>Asian</option>
	<option value="2"
	{if userProfile.ethnicity == 2}
	selected="selected"
	{/if}
	>Black / African descent</option>
	<option value="3"
	{if userProfile.ethnicity == 3}
	selected="selected"
	{/if}
	>East Indian</option>
	<option value="4"
	{if userProfile.ethnicity == 4}
	selected="selected"
	{/if}
	>Latino / Hispanic</option>
	<option value="5"
	{if userProfile.ethnicity == 5}
	selected="selected"
	{/if}
	>Middle Eastern</option>
	<option value="6"
	{if userProfile.ethnicity == 6}
	selected="selected"
	{/if}
	>Native American</option>
	<option value="7"
	{if userProfile.ethnicity == 7}
	selected="selected"
	{/if}
	>Pacific Islander</option>
	<option value="8"
	{if userProfile.ethnicity == 8}
	selected="selected"
	{/if}
	>White / Caucasian</option>
	<option value="9"
	{if userProfile.ethnicity == 9}
	selected="selected"
	{/if}
	>Other</option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.bodyType"/>: </td>
<td class='portlet-table-td-left'>
<select name='bodyType' size='1'  class='portlet-form-select' STYLE="width: 120px">
	<option value="0"
	{if userProfile.bodyType == 0}
	selected="selected"
	{/if}
	>No Answer</option>
	<option value="1"
	{if userProfile.bodyType == 1}
	selected="selected"
	{/if}
	>Slim / Slender</option>
	<option value="2"
	{if userProfile.bodyType == 2}
	selected="selected"
	{/if}
	>Athletic</option>
	<option value="3"
	{if userProfile.bodyType == 3}
	selected="selected"
	{/if}
	>Average</option>
	<option value="4"
	{if userProfile.bodyType == 4}
	selected="selected"
	{/if}
	>Some extra baggage</option>
	<option value="5"
	{if userProfile.bodyType == 5}
	selected="selected"
	{/if}
	>More to love!</option>
	<option value="6"
	{if userProfile.bodyType == 6}
	selected="selected"
	{/if}
	>Body builder</option>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.height"/>: </td>
<td class='portlet-table-td-left'>
<select name='height' size='1'  class='portlet-form-select'>
<c:forEach var="i" begin="0" end="300" step="1">
<option  value='<c:out value="${i}" />'
{if userProfile.height == <c:out value="${i}" />}
selected="selected"
{/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
cm
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.registerPurpose"/>: </td>
<td class='portlet-table-td-left'>
<input TYPE='radio' name='registerPurpose' value='0' 
{if userProfile.registerPurpose == 0}
checked="checked"
{/if}
><fmt:message key="portlet.label.purpose.personal"/></input><br/>
<input TYPE='radio' name='registerPurpose' value='1' 
{if userProfile.registerPurpose == 1}
checked="checked"
{/if}
><fmt:message key="portlet.label.purpose.networking"/></input><br/>
<input TYPE='radio' name='registerPurpose' value='2' 
{if userProfile.registerPurpose == 2}
checked="checked"
{/if}
><fmt:message key="portlet.label.purpose.friends"/></input><br/>
<input TYPE='radio' name='registerPurpose' value='3' 
{if userProfile.registerPurpose == 3}
checked="checked"
{/if}
><fmt:message key="portlet.label.purpose.dating"/></input><br/>
<input TYPE='radio' name='registerPurpose' value='4' 
{if userProfile.registerPurpose == 4}
checked="checked"
{/if}
><fmt:message key="portlet.label.purpose.serious"/></input><br/>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='60%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='basic'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>