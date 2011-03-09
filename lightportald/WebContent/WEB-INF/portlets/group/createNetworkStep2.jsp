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
<c:if test='${requestScope.success != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
<br/>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
<br/>
</c:if>
<form name='form_<c:out value="${requestScope.responseId}"/>' action="<portlet:actionURL portletMode='VIEW'/>" onsubmit='return validateGroup(this);'>
<div class="portal-header" style="padding-top:0px;">
<h1><fmt:message key="portlet.network.create.header"/></h1>
</div>
<table border='0' cellpadding='0' cellspacing='0' width='100%' >
<tr valign='middle'>
<td class='portlet-table-td-right' width= '33%'>
<label for="mainFocus" class='portlet-table-title'><b><fmt:message key="portlet.network.create.name"/></b></label>
</td>
<td class='portlet-table-td-left' width= '33%'>
<input type="text" name="displayName" id="mainFocus" value="<c:out value="${requestScope.displayName}"/>" class='portlet-form-input-field' style='width:100%;font-size:22px;'>
</td>
<td class='portlet-table-td-left' width= '33%' colspan='2'>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right' width= '33%'></td>
<td class='portlet-table-td-left' width= '33%'>
<span class="portlet-footer"><font color='#777777'><fmt:message key="portlet.network.create.name.tip"/></font></span>
</td>
<td class='portlet-table-td-left' width= '33%' colspan='2'>
<br/><br/>
</td>
</tr>
<tr valign='middle'>
<td class='portlet-table-td-right' width= '33%'>
<label for="create-network-address" class='portlet-table-title'><b><fmt:message key="portlet.network.create.url"/></b></label>
</td>
<td class='portlet-table-td-left' width= '33%'>
<input type="text" name="iUri" id="create-network-address" value="<c:out value="${requestScope.iUri}"/>" class='portlet-form-input-field' style='width:100%;font-size:22px;' onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'>
</td>
<td class='portlet-table-td-left' width= '10%'>
.<c:out value="${sessionScope.org.webId}"/>
</td>
<td class='portlet-table-td-left' width= '23%'>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right' width= '33%'></td>
<td class='portlet-table-td-left' width= '33%'>
<span class="portlet-footer"><font color='#777777'><fmt:message key="portlet.network.create.url.tip"><fmt:param value="${sessionScope.org.webId}"/></fmt:message></font></span>
</td>
<td class='portlet-table-td-left' width= '33%' colspan='2'>
<br/><br/>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><label class='portlet-table-title'>*<fmt:message key="portlet.label.shortDesc"/></lebel></td>
<td class='portlet-table-td-left'>
<input type="text" name='shortDesc' value="" class='portlet-form-input-field' style='width:100%;'>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width= '33%'><label class='portlet-table-title'><fmt:message key="portlet.label.category"/></label></td>
<td class='portlet-table-td-left'>
<select name='categoryId' value='' size='1' class='portlet-form-select'>
<c:forEach var="gc" items="${requestScope.groupCategories}" >
<c:if test='${gc.id == 1}'>
<option value='<c:out value="${gc.id}"/>' selected='selected'><c:out value="${gc.displayName}"/></option>
</c:if>
<c:if test='${gc.id != 1}'>
<option value='<c:out value="${gc.id}"/>'><c:out value="${gc.displayName}"/></option>
</c:if>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.openJoin"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='openJoin' value='1' checked='yes'/> Yes
<input type='radio' name='openJoin' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.hiddenGroup"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='hiddenGroup' value='1' /> Yes
<input type='radio' name='hiddenGroup' value='0' checked='yes'/> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.memberInvite"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberInvite' value='1' checked='yes'/> Yes
<input type='radio' name='memberInvite' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.publicForum"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='publicForum' value='1' checked='yes'/> Yes
<input type='radio' name='publicForum' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.memberBulletin"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberBulletin' value='1' checked='yes'/> Yes
<input type='radio' name='memberBulletin' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.memberImage"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberImage' value='1' checked='yes'/> Yes
<input type='radio' name='memberImage' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.privacy.noPicForward"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='noPicForward' value='1' checked='yes'/> Yes
<input type='radio' name='noPicForward' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.matureContent"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='radio' name='matureContent' value='1' /> Yes
<input type='radio' name='matureContent' value='0' checked='yes'/> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.country"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='text' name='country' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.province"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.city"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.postalCode"/></lebel></td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><label class='portlet-table-title'><fmt:message key="portlet.label.desc"/></lebel></td>
<td class='portlet-table-td-left'>
<textarea name='desc' class='portlet-form-textarea-field' rows='4' cols='24' ></textarea>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='create';document.state='normal';document.resetLastAction='1';" value='<fmt:message key="portlet.button.next"/>' class='portlet-form-button' />
<input type='button' value='<fmt:message key="portlet.button.cancel"/>'
 onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" class='portlet-form-button'/>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>