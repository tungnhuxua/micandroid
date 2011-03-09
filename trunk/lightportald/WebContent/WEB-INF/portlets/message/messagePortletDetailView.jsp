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
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width="98%" style='margin-top:40px;' >
<c:if test='${requestScope.error != null}'>
<tr>
<td class='portlet-msg-error' colspan = '2' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</c:if>
<tr valign='top'>
<jsp:useBean id="message" scope="request" class="org.light.portlets.message.Message"/>
<c:if test='${requestScope.type != "sent"}'>
<td class='portlet-table-td-center' width='80px;'>
<span class='portlet-item'>
<fmt:message key="portlet.label.from"/>:<br/>
<light:avatarUrl name="<%= message.getFromDisplayName() %>" url="<%= message.getFromUri() %>" /> 
<light:avatar name="<%= message.getFromDisplayName() %>" url="<%= message.getFromUri() %>" pictureUrl="<%= message.getFromPhotoUrl() %>" width="75" height="75" /> 
<c:if test='${requestScope.message.fromCurrentStatusId == 1 }'>
<br/>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/>
<br/>
<a href='javascript:;' onclick="javascript:showInstantMessageMember(event,'<c:out value="${requestScope.message.postById}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.instantMessage"/></a>
<br/>
</c:if>
</span>
</td>
</c:if>
<c:if test='${requestScope.type == "sent"}'>
<td class='portlet-table-td-center' width='80px;'>
<span class='portlet-item'>
<fmt:message key="portlet.label.to"/>:<br/>
<light:avatarUrl name="<%= message.getToDisplayName() %>" url="<%= message.getToUri() %>" /> 
<light:avatar name="<%= message.getToDisplayName() %>" url="<%= message.getToUri() %>" pictureUrl="<%= message.getToPhotoUrl() %>" width="75" height="75" /> 
<c:if test='${requestScope.message.toCurrentStatusId == 1 }'>
<br/>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/>
<br/>
<a href='javascript:;' onclick="javascript:showInstantMessageMember(event,'<c:out value="${requestScope.message.userId}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.instantMessage"/></a>
</span>
<br/>
</c:if>
</td>
</c:if>
<td class='portlet-table-td-left' width='50px'></td>
<td class='portlet-table-td-left'>
<table border='0' cellpadding='0' cellspacing='0' width="98%">
<tr>
<td class='portlet-table-td-left'>
<b><c:out value="${requestScope.message.subject}"/></b>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span class='portlet-note'>
<c:out value="${requestScope.message.fullDate}"/>
</span>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<c:out value="${requestScope.message.content}" escapeXml="false" />
</td>
</tr>
<c:if test='${requestScope.message.event != 0}'>
<tr>
<td class='portlet-table-td-left'>
<br/><br/>
<input type='submit' name='<c:out value="${requestScope.message.id}"/>' onClick="document.pressed='approve';document.parameter=this.name;" value='<fmt:message key="portlet.button.approve"/>' class='portlet-form-button' />
<input type='submit' name='<c:out value="${requestScope.message.id}"/>' onClick="document.pressed='deny';document.parameter=this.name;" value='<fmt:message key="portlet.button.deny"/>' class='portlet-form-button' />
</td>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-left'>
<br/><br/>
<input type='submit' name='<c:out value="${requestScope.message.id}"/>' onClick="document.pressed='reply';document.parameter=this.name;" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
<input type='submit' name='<c:out value="${requestScope.message.id}"/>' onClick="document.pressed='forward';document.parameter=this.name;" value='<fmt:message key="portlet.button.forward"/>' class='portlet-form-button' />
<input type='submit' name='<c:out value="${requestScope.message.id}"/>' onClick="document.pressed='delete';document.parameter=this.name;" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>