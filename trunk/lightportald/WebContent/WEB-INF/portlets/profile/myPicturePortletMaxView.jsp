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
<light:authenticateUser> 
<tr>
<td class='portlet-link-left'>
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT'><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addPicture"/></a>
</td>
</tr>
</light:authenticateUser>
<tr>
<td class='portlet-link-left' >
<fmt:message key="portlet.label.total"/> <c:out value="${requestScope.pictureCount}"/> <fmt:message key="portlet.label.pictures"/>
</td>
</tr>
</table>
<light:authenticateUser> 
<c:if test='${requestScope.pictureCount > 0}'>
<div onmouseover="javascript:document.getElementById('myPictureButton0').style.visibility = 'visible';document.getElementById('myPictureButton1').style.visibility = 'visible';getElementById('myPictureButton3').style.visibility = 'visible';getElementById('myPictureButton3').style.visibility = 'visible';getElementById('myPictureButton4').style.visibility = 'visible';getElementById('myPictureButton5').style.visibility = 'visible';getElementById('myPictureButton6').style.visibility = 'visible';"
	   onmouseout="javascript:getElementById('myPictureButton0').style.visibility = 'hidden';getElementById('myPictureButton1').style.visibility = 'hidden';getElementById('myPictureButton3').style.visibility = 'hidden';getElementById('myPictureButton3').style.visibility = 'hidden';getElementById('myPictureButton4').style.visibility = 'hidden';getElementById('myPictureButton5').style.visibility = 'hidden';getElementById('myPictureButton6').style.visibility = 'hidden';">
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center' colspan='5'>
<input id ='myPictureButton0' type='button' onClick="stopSlideShow('<c:out value="${requestScope.responseId}"/>');ConfigAllViewedPic('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);" value='<fmt:message key="portlet.button.configAllViewed"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton1' type='submit' onClick="document.pressed='edit';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.configCurrent"/>' class='portlet-form-button' style="visibility: hidden"/>
<!-- <input id ='myPictureButton2' type='submit' onClick="document.pressed='rank';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.rankPicture"/>' class='portlet-form-button' style="visibility: hidden"/>  -->
<input id ='myPictureButton3' type='submit' onClick="document.pressed='profile';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.profilePhoto"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton4' type='submit' onClick="document.pressed='background';stopSlideShow('<c:out value="${requestScope.responseId}"/>');seBackgroundPicture('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.backgroundPicture"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton5' type='submit' onClick="document.pressed='header';stopSlideShow('<c:out value="${requestScope.responseId}"/>');seHeaderPicture('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.headerPicture"/>' class='portlet-form-button' style="visibility: hidden"/>
<input id ='myPictureButton6' type='submit' onClick="document.pressed='delete';stopSlideShow('<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' style="visibility: hidden"/>
</td>
</tr>
</table>
</div>
</c:if>
</light:authenticateUser>
<c:if test='${requestScope.pictureCount > 0}'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center' colspan='3'>
<div onmouseover="javascript:document.getElementById('myPictureLink1').style.visibility = 'visible';getElementById('myPictureLink2').style.visibility = 'visible';getElementById('myPictureLink3').style.visibility = 'visible';getElementById('myPictureLink4').style.visibility = 'visible';"
	 onmouseout="javascript:getElementById('myPictureLink1').style.visibility = 'hidden';getElementById('myPictureLink2').style.visibility = 'hidden';getElementById('myPictureLink3').style.visibility = 'hidden';getElementById('myPictureLink4').style.visibility = 'hidden';">
<a id ='myPictureLink1' href='javascript:;' onclick="javascript:previousSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.previous"/>' src="<%= request.getContextPath() %>/light/images/previous.gif" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink2' href='javascript:;' onclick="javascript:stopSlideShow('<c:out value="${requestScope.responseId}"/>');"><image title='<fmt:message key="portlet.label.stopSlideShow"/>' src="<%= request.getContextPath() %>/light/images/stop.png" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink3' href='javascript:;' onclick="javascript:startSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.startSlideShow"/>' src="<%= request.getContextPath() %>/light/images/play.png" style='border: 0px;' height='16' width='16' align='middle' /></a> 
<a id ='myPictureLink4' href='javascript:;' onclick="javascript:nextSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);"><image title='<fmt:message key="portlet.label.next"/>' src="<%= request.getContextPath() %>/light/images/next.gif" style='border: 0px;' height='16' width='16' align='middle' /></a> 
</div>
</td>
</tr>
<tr valign='top'>
<td width= '12%'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<a href='javascript:;' onclick="javascript:startAllSlideShow('<c:out value="${requestScope.responseId}"/>',<c:out value="${requestScope.pictureCount}"/>);" ><fmt:message key="portlet.label.all"/>(<c:out value="${requestScope.pictureCount}"/>)</a>
</td>
</tr>
<c:forEach var="date" items="${requestScope.datePictures}" varStatus="status">
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<a href='javascript:;' onclick="javascript:startGroupSlideShow('<c:out value="${requestScope.responseId}"/>','date_<c:out value="${status.index}"/>',<c:out value="${date.count}"/>);" ><c:out value="${date.name}"/>(<c:out value="${date.count}"/>)</a>
</td>
</tr>
</c:forEach>
</table>
</td>

<td width= '70%'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<tr>
<td class='portlet-table-td-center'>
<span id='picture_<c:out value="${requestScope.responseId}"/>' style="filter:alpha(opacity=100); -moz-opacity:1.0;">
<img name='currentMyPicture_<c:out value="${requestScope.responseId}"/>' id='picture_<c:out value="${picture.id}"/>' src='<c:out value="${currentPictureUrl}"/>' class='portlet2'  align="middle" width='<c:out value="${picture.largeWidth}"/>' height='<c:out value="${picture.largeHeight}"/>'
 onmouseover="javascript:Light.showPPT('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');"
 onmouseout="javascript:Light.hidePPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');"
/>
<br/>
<c:if test='${currentCaption != null}'>
<c:out value="${currentCaption}"/>
</c:if>
<c:if test='${currentCaption == null}'>
<br/>
</c:if>
</span>
<input type='hidden' name='pictureId' value='<c:out value="${currentPictureId}"/>'/>
<c:if test='${picture.httpUrl}'>
<input type='hidden' id='userCurrentPicture' name='<c:out value="${currentPictureId}"/>;<c:out value="${currentPictureWidth}"/>;<c:out value="${currentPictureHeight}"/>;<c:out value="${currentPictureUrl}"/>' value='<c:out value="${currentCaption}"/>'/>
</c:if>
<c:if test='${!picture.httpUrl}'>
<input type='hidden' id='userCurrentPicture' name='<c:out value="${currentPictureId}"/>;<c:out value="${currentPictureWidth}"/>;<c:out value="${currentPictureHeight}"/>;<%= request.getContextPath() %><c:out value="${currentPictureUrl}"/>' value='<c:out value="${currentCaption}"/>'/>
</c:if>
</td>
</tr>
</table>
</td>

<td width= '18%'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="tag" items="${requestScope.tagPictures}" varStatus="status">
<tr class='portlet-table-td-left'>
<td class='portlet-item'>
<a href='javascript:;' onclick="javascript:startGroupSlideShow('<c:out value="${requestScope.responseId}"/>','tag_<c:out value="${status.index}"/>',<c:out value="${tag.count}"/>);" ><c:out value="${tag.name}"/>(<c:out value="${tag.count}"/>)</a>
</td>
</tr>
</c:forEach>
</table>
</td>
</tr>
</table>

<div id='userPictures'>
<c:forEach var="picture" items="${requestScope.userPictures}" varStatus="status">
<c:if test='${picture.httpUrl}'>
<input type='hidden' id='pictures_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
<c:if test='${!picture.httpUrl}'>
<input type='hidden' id='pictures_<c:out value="${status.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
</c:forEach>
</div>

<c:forEach var="date" items="${requestScope.datePictures}" varStatus="status1">
<div id='<c:out value="${date.name}"/>'>
<c:forEach var="picture" items="${date.pictues}" varStatus="status2">
<c:if test='${picture.httpUrl}'>
<input type='hidden' id='date_<c:out value="${status1.index}"/>_<c:out value="${status2.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
<c:if test='${!picture.httpUrl}'>
<input type='hidden' id='date_<c:out value="${status1.index}"/>_<c:out value="${status2.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
</c:forEach>
</div>
</c:forEach>

<c:forEach var="tag" items="${requestScope.tagPictures}" varStatus="status1">
<div id='<c:out value="${tag.name}"/>'>
<c:forEach var="picture" items="${tag.pictues}" varStatus="status2">
<c:if test='${picture.httpUrl}'>
<input type='hidden' id='tag_<c:out value="${status1.index}"/>_<c:out value="${status2.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
<c:if test='${!picture.httpUrl}'>
<input type='hidden' id='tag_<c:out value="${status1.index}"/>_<c:out value="${status2.index}"/>' name='<c:out value="${picture.id}"/>;<c:out value="${picture.largeWidth}"/>;<c:out value="${picture.largeHeight}"/>;<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>;<c:out value="${picture.status}"/>;<c:out value="${picture.tag}"/>;<c:out value="${picture.taggingsString}"/>' value='<c:out value="${picture.caption}"/>'/>
</c:if>
</c:forEach>
</div>
</c:forEach>

</c:if>
</form>
</fmt:bundle>
</body>
</html>