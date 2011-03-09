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
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
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
</c:if>
<light:authenticateUser> 
<c:if test='${requestScope.picture != null}'>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<input type='hidden' name='pictureId' value='<c:out value="${picture.id}"/>'/>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr valign='top'>
<td class='portlet-table-td-center' colspan='2'>
<span class='portlet-msg-success'>
<br/><br/>
<fmt:message key="message.picture.edit"/>
</span>
<br/><br/>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-center'>
<img id='picture_<c:out value="${picture.id}"/>' src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle"
 ondblclick="javascript:Light.showCreatePPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>');"
 onmouseover="javascript:Light.showPPT('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>',1);"
 onmousemove="javascript:Light.movePPT(event);"
 onmouseup="javascript:Light.moveEndPPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>');"
/>
<br/><br/>
</td>
<td class='portlet-table-td-center' width='30%'>
<table  cellpadding='0' cellspacing='0' width='100%' class='portlet2' style='margin:0 10px 0 10px;padding:10px;'>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.caption"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='caption' value='<c:out value="${picture.caption}"/>' class='portlet-form-input-field' size='36' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.tag"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='tag' value='<c:out value="${picture.tag}"/>' class='portlet-form-input-field' size='36' /> 
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'>
<fmt:message key="portlet.label.privacy"/>:
</td>
<td class='portlet-table-td-left'>
<input type='radio' name='status' value='0' class='portlet-form-radio'
<c:if test='${picture.status == 0}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.onlyMe"/></input><br/>
<input type='radio' name='status' value='1' class='portlet-form-radio'
<c:if test='${picture.status == 1}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.connections"/></input><br/>
<input type='radio' name='status' value='2' class='portlet-form-radio'
<c:if test='${picture.status == 2}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.member"/></input><br/>
<input type='radio' name='status' value='4' class='portlet-form-radio'
<c:if test='${picture.status == 4}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.public"/></input><br/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' onClick="Light.hidePPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');document.pressed='save';document.resetLastAction='1';" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.hidePPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','pictureId=<c:out value="${picture.id}"/>');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</td>
</tr>
</table>
</form>
</c:if>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>