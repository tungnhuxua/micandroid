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
<form name='form_<c:out value="${requestScope.responseId}"/>' action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
&nbsp;
<a href='javascript:void(0)' onclick="javascript:startSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);" ><fmt:message key="portlet.label.startSlideShow"/></a>
&nbsp;
<a href='javascript:void(0)' onclick="javascript:stopSlideShow('<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.stopSlideShow"/></a>
</td>
</tr>
</table>
<c:if test='${requestScope.pictureCount > 0}'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center'>
<span id='picture_<c:out value="${requestScope.responseId}"/>'>
<img src='<c:out value="${currentPictureUrl}"/>' class='portlet'  align="middle" width='<c:out value="${currentPictureWidth}"/>' height='<c:out value="${currentPictureHeight}"/>'/>
<br/>
<c:if test='${currentCaption != null}'>
<c:out value="${currentCaption}"/>
</c:if>
<c:if test='${currentCaption == null}'>
<br/>
</c:if>
</span>
<input type='hidden' name='pictureId' value='<c:out value="${currentPictureId}"/>'/>
</td>
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:if test='${requestScope.pictureCount > 0}'>
<tr>
<td class='portlet-table-td-center' colspan='5'>
<input id ='myPictureButton4' type='submit' onClick="document.pressed='background';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.backgroundPicture"/>' class='portlet-form-button' />
<input id ='myPictureButton5' type='submit' onClick="document.pressed='header';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.headerPicture"/>' class='portlet-form-button' />
</td>
</tr>
</c:if>
<c:forEach var="picture" items="${requestScope.userPictures}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr class='portlet-table-td-center'>
<td >
</c:if>

<span width= '70' >
<a href='javascript:void(0);' onclick="javascript:viewPicture('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.pictureUrl}"/>','<c:out value="${picture.caption}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.largeWidth}"/>','<c:out value="${picture.largeHeight}"/>');">
<c:if test='${picture.httpUrl}'>
<img id='myPicture_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>,<c:out value="${picture.largeWidth}"/>,<c:out value="${picture.largeHeight}"/>,<c:out value="${picture.caption}"/>' src='<c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.largeWidth / 8}"/>' height='<c:out value="${picture.largeHeight / 8}"/>'/>
</c:if>
<c:if test='${!picture.httpUrl}'>
<img id='myPicture_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>,<c:out value="${picture.largeWidth}"/>,<c:out value="${picture.largeHeight}"/>,<c:out value="${picture.caption}"/>' src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.largeWidth / 8}"/>' height='<c:out value="${picture.largeHeight / 8}"/>'/>
</c:if>
</a>
</span>

<c:if test='${status.index % 10 == 9}'>
</td>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.pictureCount % 10 != 0}'>
</td>
</tr>
</c:if>
</table>
</form>
</fmt:bundle>
</body>
</html>