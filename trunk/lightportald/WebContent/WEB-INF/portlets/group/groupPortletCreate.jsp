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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-center' >
<fmt:message key="portlet.label.createGroup"/>
</td>
</tr>
</table>
<br/>
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
<form action="<portlet:actionURL portletMode='VIEW'/>" onsubmit='return validateGroup(this);'>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-right' width='20%'>*<fmt:message key="portlet.label.groupName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='displayName' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.groupUrl"/>: </td>
<td class='portlet-table-td-left'>
<%
int format = org.light.portal.util.PropUtil.getInt("portal.url.format");
if(format != 1){
%>
http://www.<c:out value="${sessionScope.org.webId}"/><c:out value="${sessionScope.org.groupPrefix}"/>
<%
}
%>
<%
if(format == 1){
%>
http://
<%
}
%>

<input type='text' name='iUri' value='' class='portlet-form-input-field' size='18' onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'/> 
<%
if(format == 1){
%>
.<c:out value="${sessionScope.org.webId}"/>
<%
}
%>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.category"/>: </td>
<td class='portlet-table-td-left'>
<select name='categoryId' value='' size='1' class='portlet-form-select' style='width:170px'>
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
<tr valign='top'>
<td class='portlet-table-td-right'>*<fmt:message key="portlet.label.shortDesc"/>: </td>
<td class='portlet-table-td-left'>
<textarea name='shortDesc' class='portlet-form-textarea-field' rows='2' cols='12' ></textarea>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.desc"/>: </td>
<td class='portlet-table-td-left'>
<textarea name='desc' class='portlet-form-textarea-field' rows='4' cols='12' ></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.openJoin"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='openJoin' value='1' checked='yes'/> Yes
<input type='radio' name='openJoin' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.hiddenGroup"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='hiddenGroup' value='1' /> Yes
<input type='radio' name='hiddenGroup' value='0' checked='yes'/> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.memberInvite"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberInvite' value='1' checked='yes'/> Yes
<input type='radio' name='memberInvite' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.publicForum"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='publicForum' value='1' checked='yes'/> Yes
<input type='radio' name='publicForum' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.memberBulletin"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberBulletin' value='1' checked='yes'/> Yes
<input type='radio' name='memberBulletin' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.memberImage"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='memberImage' value='1' checked='yes'/> Yes
<input type='radio' name='memberImage' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.privacy.noPicForward"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='noPicForward' value='1' checked='yes'/> Yes
<input type='radio' name='noPicForward' value='0' /> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.matureContent"/>: </td>
<td class='portlet-table-td-left'>
<input type='radio' name='matureContent' value='1' /> Yes
<input type='radio' name='matureContent' value='0' checked='yes'/> No
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='country' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.postalCode"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='create';document.state='normal';document.resetLastAction='1';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' value='<fmt:message key="portlet.button.cancel"/>'
 onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" class='portlet-form-button'/>
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>