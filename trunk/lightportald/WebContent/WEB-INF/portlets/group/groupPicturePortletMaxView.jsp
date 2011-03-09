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
<light:authorizeGroupMember type='yes'>
<c:if test='${requestScope.group.memberImage == 1}'> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addPicture"/></a>
</td>
</tr>
</table>
</c:if>
</light:authorizeGroupMember>
<light:authorizeGroupMember type='leader'>
<c:if test='${requestScope.group.memberImage == 0}'> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<a href='javascript:void(0)' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addPicture"/></a>
</td>
</tr>
</table>
</c:if>
</light:authorizeGroupMember>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.thisGroup"/> <c:out value="${requestScope.pictureCount}"/> <fmt:message key="portlet.label.pictures"/>
</td>
</tr>
</table>
<light:authorizeGroupMember type='leader'>
<c:if test='${requestScope.pictureCount > 0}'>
<div onmouseover="javascript:document.getElementById('myPictureButton1').style.visibility = 'visible';getElementById('myPictureButton2').style.visibility = 'visible';getElementById('myPictureButton3').style.visibility = 'visible';getElementById('myPictureButton4').style.visibility = 'visible';getElementById('myPictureButton5').style.visibility = 'visible';"
	   onmouseout="javascript:getElementById('myPictureButton1').style.visibility = 'hidden';getElementById('myPictureButton2').style.visibility = 'hidden';getElementById('myPictureButton3').style.visibility = 'hidden';getElementById('myPictureButton4').style.visibility = 'hidden';getElementById('myPictureButton5').style.visibility = 'hidden';">
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center' colspan='5'>
<input id ='myPictureButton1' type='submit' onClick="document.pressed='edit';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.configCurrent"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton2' type='submit' onClick="document.pressed='profile';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.profilePhoto"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton3' type='submit' onClick="document.pressed='background';stopSlideShow('<c:out value="${requestScope.responseId}"/>');seBackgroundPicture('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.backgroundPicture"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton4' type='submit' onClick="document.pressed='header';stopSlideShow('<c:out value="${requestScope.responseId}"/>');seHeaderPicture('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.headerPicture"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton5' type='submit' onClick="document.pressed='delete';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' style="visibility: hidden"/>
</td>
</tr>
</table>
</c:if>
</div>
</light:authorizeGroupMember>
<c:if test='${requestScope.pictureCount > 0}'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center' colspan='3'>
<div onmouseover="javascript:document.getElementById('myPictureLink1').style.visibility = 'visible';getElementById('myPictureLink2').style.visibility = 'visible';getElementById('myPictureLink3').style.visibility = 'visible';getElementById('myPictureLink4').style.visibility = 'visible';"
	 onmouseout="javascript:getElementById('myPictureLink1').style.visibility = 'hidden';getElementById('myPictureLink2').style.visibility = 'hidden';getElementById('myPictureLink3').style.visibility = 'hidden';getElementById('myPictureLink4').style.visibility = 'hidden';">
<a id ='myPictureLink1' href='javascript:void(0)' onclick="javascript:previousSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.previous"/>' src="<%= request.getContextPath() %>/light/images/previous.gif" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink2' href='javascript:void(0)' onclick="javascript:stopSlideShow('<c:out value="${requestScope.responseId}"/>');"><image title='<fmt:message key="portlet.label.stopSlideShow"/>' src="<%= request.getContextPath() %>/light/images/stop.png" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink3' href='javascript:void(0)' onclick="javascript:startSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.startSlideShow"/>' src="<%= request.getContextPath() %>/light/images/play.png" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink4' href='javascript:void(0)' onclick="javascript:nextSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.next"/>' src="<%= request.getContextPath() %>/light/images/next.gif" style='border: 0px;' height='16' width='16' align='middle' /></a> 
</div>
</td>
</tr>
<tr>
<td class='portlet-table-td-center'>
<span id='picture_<c:out value="${requestScope.responseId}"/>'>
<img id='currentMyPicture_<c:out value="${requestScope.responseId}"/>' src='<%= request.getContextPath() %><c:out value="${currentPictureUrl}"/>' class='portlet2'  align="middle" width='<c:out value="${currentPictureWidth}"/>' height='<c:out value="${currentPictureHeight}"/>'/>
<br/><c:out value="${currentCaption}"/>
</span>
<input type='hidden' name='pictureId' value='<c:out value="${currentPictureId}"/>'/>
<input type='hidden' name='groupId' value='<c:out value="${group.id}"/>'/>
</td>
</tr>
</table>
</c:if>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="picture" items="${requestScope.groupPictures}" varStatus="status">
<c:if test='${status.index % 10 == 0}'>
<tr class='portlet-table-td-center'>
<td >
</c:if>
<span width= '70' >
<c:if test='${picture.pictureUrl == currentPictureUrl}'>
<a href='javascript:void(0);' onclick="javascript:viewGroupPicture('<c:out value="${requestScope.responseId}"/>','<c:out value="${group.id}"/>','<c:out value="${picture.pictureUrl}"/>','<c:out value="${picture.caption}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.largeWidth}"/>','<c:out value="${picture.largeHeight}"/>');">
<img id='pictures_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>' value='<c:out value="${picture.caption}"/>' src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.largeWidth / 8}"/>' height='<c:out value="${picture.largeHeight / 8}"/>'/>
</a>
</c:if>
<c:if test='${picture.pictureUrl != currentPictureUrl}'>
<a href='javascript:void(0);' onclick="javascript:viewGroupPicture('<c:out value="${requestScope.responseId}"/>','<c:out value="${group.id}"/>','<c:out value="${picture.pictureUrl}"/>','<c:out value="${picture.caption}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.largeWidth}"/>','<c:out value="${picture.largeHeight}"/>');">
<img id='pictures_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>' value='<c:out value="${picture.caption}"/>' src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${picture.largeWidth / 8}"/>' height='<c:out value="${picture.largeHeight / 8}"/>'/>
</a>
</c:if>
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
