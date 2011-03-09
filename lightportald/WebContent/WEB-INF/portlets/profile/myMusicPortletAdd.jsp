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
<c:if test='${requestScope.music == null}'>
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
<c:if test='${requestScope.music == null}'>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%' style="padding-top:10px;">
    <tr>
      <td class='portlet-table-td-left' width='100%'>
        <fmt:message key="portlet.label.music.url"/>:
      
        <input type='text' name='musicUrl' class='portlet-form-input-field' size='36'/>
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        <fmt:message key="portlet.label.music.caption"/>:
      
        <input type='text' name='musicCaption' class='portlet-form-input-field' size='36'/>
      
        <input type='submit' onClick="document.pressed='addUrl';document.resetLastAction='1';" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
      </td>
    </tr>
  </table>
</form>
<form name='myMusicForm' enctype='multipart/form-data' method='post' accept-charset='UTF-8' 
  action ='<%= request.getContextPath() %>/uploadMusic.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '<c:out value="${requestScope.responseId}"/>')">
  <table border='0' cellpadding='0' cellspacing='0' >
    <tr>
      <td class='portlet-table-td-left' >
        Or Upload Music File:
      </td>
    </tr>
    <tr>
      <td class='portlet-table-td-left' >
        <input type='file' name='file' size='24' />
      </td>
    </tr>
    <tr>
    	<td class='portlet-link-left' >
		<a href='javascript:void(0)' onclick="javascript:Light.showUploader('<c:out value="${requestScope.responseId}"/>','uploadMusics');" ><fmt:message key="portlet.button.uploadMultiMusics"/></a>
    	</td>      
    </tr>
    <tr>
      <td class='portlet-table-td-right' >
        <input type='submit' value='<fmt:message key="portlet.button.upload"/>' class='portlet-form-button' />
      	<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
      </td>
    </tr>
  </table>
</form>
</c:if>
</light:authenticateUser>
</fmt:bundle>
</body>
</html>