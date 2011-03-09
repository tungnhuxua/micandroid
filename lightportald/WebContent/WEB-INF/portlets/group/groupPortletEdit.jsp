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
<form action="<portlet:actionURL/>">
<table border='0' cellpadding='0' cellspacing='0' width='80%' style="margin-left:50px;">
<tr>
<td class='portlet-table-td-left' width='20%'><fmt:message key="portlet.label.groupName"/>: </td>
<td class='portlet-table-td-left'>
<input type='hidden' name='groupId' value='<c:out value="${requestScope.group.id}"/>' />
<input type='text' name='displayName' value='<c:out value="${requestScope.group.displayName}"/>' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.groupUrl"/>: </td>
<td class='portlet-table-td-left'>
http://
<input type='text' name='iUri' value='<c:out value="${requestScope.group.uri}"/>' class='portlet-form-input-field' size='18'  onchange="validateInternalUri(this.value,'<c:out value="${requestScope.responseId}"/>');" AUTOCOMPLETE='OFF'/> 
.<c:out value="${sessionScope.org.webId}"/></td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.logo"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoUrl' value='<c:out value="${requestScope.org.logoUrl}"/>' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.logoIcon"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='logoIcon' value='<c:out value="${requestScope.org.logoIcon}"/>' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.category"/>: </td>
<td class='portlet-table-td-left'>
<select name='categoryId' value='<c:out value="${requestScope.group.categoryId}"/>' size='1' class='portlet-form-select'>
<c:forEach var="gc" items="${requestScope.groupCategories}" >
<option value='<c:out value="${gc.id}"/>'
<c:if test='${requestScope.group.categoryId == gc.id}'>
selected="selected"
</c:if>
><c:out value="${gc.displayName}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.openJoin"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.openJoin == 1}'>
<input type='radio' name='openJoin' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.openJoin != 1}'>
<input type='radio' name='openJoin' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.openJoin == 0}'>
<input type='radio' name='openJoin' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.openJoin != 0}'>
<input type='radio' name='openJoin' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.hiddenGroup"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.hiddenGroup == 1}'>
<input type='radio' name='hiddenGroup' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.hiddenGroup != 1}'>
<input type='radio' name='hiddenGroup' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.hiddenGroup == 0}'>
<input type='radio' name='hiddenGroup' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.hiddenGroup != 0}'>
<input type='radio' name='hiddenGroup' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.memberInvite"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.memberInvite == 1}'>
<input type='radio' name='memberInvite' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.memberInvite != 1}'>
<input type='radio' name='memberInvite' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.memberInvite == 0}'>
<input type='radio' name='memberInvite' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.memberInvite != 0}'>
<input type='radio' name='memberInvite' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.publicForum"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.publicForum == 1}'>
<input type='radio' name='publicForum' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.publicForum != 1}'>
<input type='radio' name='publicForum' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.publicForum == 0}'>
<input type='radio' name='publicForum' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.publicForum != 0}'>
<input type='radio' name='publicForum' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.memberBulletin"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.memberBulletin == 1}'>
<input type='radio' name='memberBulletin' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.memberBulletin != 1}'>
<input type='radio' name='memberBulletin' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.memberBulletin == 0}'>
<input type='radio' name='memberBulletin' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.memberBulletin != 0}'>
<input type='radio' name='memberBulletin' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.memberImage"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.memberImage == 1}'>
<input type='radio' name='memberImage' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.memberImage != 1}'>
<input type='radio' name='memberImage' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.memberImage == 0}'>
<input type='radio' name='memberImage' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.memberImage != 0}'>
<input type='radio' name='memberImage' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.privacy.noPicForward"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.noPicForward == 1}'>
<input type='radio' name='noPicForward' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.noPicForward != 1}'>
<input type='radio' name='noPicForward' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.noPicForward == 0}'>
<input type='radio' name='noPicForward' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.noPicForward != 0}'>
<input type='radio' name='noPicForward' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.matureContent"/>: </td>
<td class='portlet-table-td-left'>
<c:if test='${requestScope.group.matureContent == 1}'>
<input type='radio' name='matureContent' value='1' checked="checked"/> Yes
</c:if>
<c:if test='${requestScope.group.matureContent != 1}'>
<input type='radio' name='matureContent' value='1' /> Yes
</c:if>
<c:if test='${requestScope.group.matureContent == 0}'>
<input type='radio' name='matureContent' value='0' checked="checked"/> No
</c:if>
<c:if test='${requestScope.group.matureContent != 0}'>
<input type='radio' name='matureContent' value='0' /> No
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.country"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='country' value='<c:out value="${requestScope.group.country}"/>' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.province"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='province' value='<c:out value="${requestScope.group.province}"/>' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.city"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='city' value='<c:out value="${requestScope.group.city}"/>' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.postalCode"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='postalCode' value='<c:out value="${requestScope.group.postalCode}"/>' class='portlet-form-input-field' size='24' /> 
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.shortDesc"/>: </td>
<td class='portlet-table-td-left'>
<textarea name='shortDesc' class='portlet-form-textarea-field' rows='2' cols='24' ><c:out value="${requestScope.group.shortDesc}"/></textarea>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.desc"/>: </td>
<td class='portlet-table-td-left'>
<textarea name='desc' class='portlet-form-textarea-field' rows='5' cols='24' ><c:out value="${requestScope.group.desc}"/></textarea>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='modify';document.resetLastAction='1';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.closePortlet('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.close"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>