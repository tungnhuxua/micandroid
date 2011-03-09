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
<c:if test='${requestScope.file == null}'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>         
</c:if>
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
<c:if test='${requestScope.file != null}'>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-right' width='15%'>
URL:
</td>
<td class='portlet-table-td-left'>
<a href='<%= request.getContextPath() %><c:out value="${file.fileUrl}"/>' target="_blank">
<c:out value="${file.fileUrl}"/>
<c:out value="${file.caption}"/>
</a>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='15%'>
<fmt:message key="portlet.label.caption"/>:
</td>
<td class='portlet-table-td-left'>
<input type='hidden' name='fileId' value='<c:out value="${file.id}"/>'/>
<input type='text' name='caption' value='<c:out value="${file.caption}"/>' class='portlet-form-input-field' size='18' /> 
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right' width='15%'>
<fmt:message key="portlet.label.privacy.pic"/>:
</td>
<td class='portlet-table-td-left'>
<input type='radio' name='status' value='0' class='portlet-form-radio'
<c:if test='${file.status == 0}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.pic.me"/></input><br/>
<input type='radio' name='status' value='1' class='portlet-form-radio'
<c:if test='${file.status == 1}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.pic.friends"/></input><br/>
<input type='radio' name='status' value='2' class='portlet-form-radio'
<c:if test='${file.status == 2}'>
checked="checked"
</c:if>
>
<fmt:message key="portlet.label.privacy.pic.public"/></input><br/> 
</td>
</tr>
<tr>
<td class='portlet-table-td-right' width='15%'>
</td>
<td class='portlet-table-td-left'>
<input type='submit' onClick="document.pressed='save';" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='cancel';" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</c:if>

<c:if test='${requestScope.file == null}'>
<form name='myFileForm' enctype='multipart/form-data' method='post' accept-charset='UTF-8' 
  action ='<%= request.getContextPath() %>/contentLibraryUploadFile.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '<c:out value="${requestScope.responseId}"/>')">
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Upload File :
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        <input type='file' name='file' size='30' />
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-right' >
        <input type='submit' value='<fmt:message key="portlet.button.upload"/>' class='portlet-form-button' />
      </td>
    </tr>
  </table>
</form>
</c:if>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>