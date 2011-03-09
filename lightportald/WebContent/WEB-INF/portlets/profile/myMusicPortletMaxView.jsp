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
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<light:authenticateOwner> 
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT'><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addMusic"/></a>
</light:authenticateOwner>
<c:if test='${requestScope.musicCount > 0}'>
<input type="image" title='<fmt:message key="portlet.button.play"/>' src="<%= request.getContextPath() %>/light/images/play.png" style='border: 0px;' height='16' width='16' align='top' onClick="javascript:openMyMusicPlayer();"/>
<input type="image" title='<fmt:message key="portlet.button.stop"/>' src="<%= request.getContextPath() %>/light/images/stop.png" style='border: 0px;' height='16' width='16' align='top' onClick="javascript:closeMyMusicPlayer();"/>
</c:if>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="music" items="${requestScope.userMusics}" >
<tr>
<td class='portlet-table-td-left' width='90%'>
<light:authenticateOwner> 
<input type='radio' name='musicId' value='<c:out value="${music.id}"/>' />
</light:authenticateOwner>
<span class="portlet-item">
<c:if test='${music.httpUrl}'>
<a href='<c:out value="${music.musicUrl}"/>' target="_blank">
<c:out value="${music.caption}"/>
</a>
</c:if>
<c:if test='${!music.httpUrl}'>
<a href='<%= request.getContextPath() %><c:out value="${music.musicUrl}"/>' target="_blank">
<c:out value="${music.caption}"/>
</a>
</c:if>
</span>
</td>
<td>
</td>
</tr>
</c:forEach>
<light:authenticateOwner> 
<c:if test='${requestScope.musicCount > 0}'>
<tr>
<td class='portlet-table-td-center' colspan='2'>
<br/><br/>
<input type='submit' onClick="document.pressed='select';" value='<fmt:message key="portlet.button.config"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='rank';" value='<fmt:message key="portlet.button.rankPicture"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='ring';" value='<fmt:message key="portlet.button.ringTone"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='background';" value='<fmt:message key="portlet.button.backgroundMusic"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='delete';" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
</td>
</tr>
</c:if>
</light:authenticateOwner>
</table>
</form>
</fmt:bundle>
</body>
</html>