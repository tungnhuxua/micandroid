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
<table border='0' cellpadding='0' cellspacing='0' width='100%' style="margin-top:10px;">
<tr valign='top'>
<td class='portlet-table-td-center'>
<br/><br/>
<input type='hidden' name='pictureId' value='<c:out value="${picture.id}"/>'/>
<fmt:message key="portlet.label.caption"/>:
<input type='text' name='caption' value='<c:out value="${picture.caption}"/>' class='portlet-form-input-field' size='24' /> 
<br/>
<span id='picturePosition' style="text-align:center;"></span>
<br/>
<img  id='picture_<c:out value="${picture.id}"/>' src='<%= request.getContextPath() %><c:out value="${picture.pictureUrl}"/>' style='border: 0px;'  align="middle" 
/>
<br/><br/>
<input type='submit' onClick="document.pressed='save';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='submit' onClick="document.pressed='cancel';" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>

<%-- 
<td class='portlet-table-td-center'>
<br/>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'>
X:<input type='text' name='positionX1' 
<c:if test='${tag1 != null}'>
value = '<c:out value="${tag1.positionX}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Y:<input type='text' name='positionY1' 
<c:if test='${tag1 != null}'>
value = '<c:out value="${tag1.positionY}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.tag"/>:<input type='text' name='tag1' 
<c:if test='${tag1 != null}'>
value = '<c:out value="${tag1.tag}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
<c:if test='${tag1 != null}'>
<input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag1.id}'/>" onClick="document.pressed='deleteTag';document.parameter=this.name;"/>
<input type='hidden' name='tagId1' value='<c:out value="${tag1.id}"/>'/>
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
X:<input type='text' name='positionX2' 
<c:if test='${tag2 != null}'>
value = '<c:out value="${tag2.positionX}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Y:<input type='text' name='positionY2' 
<c:if test='${tag2 != null}'>
value = '<c:out value="${tag2.positionY}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Tag:<input type='text' name='tag2' 
<c:if test='${tag2 != null}'>
value = '<c:out value="${tag2.tag}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
<c:if test='${tag2 != null}'>
<input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag2.id}'/>" onClick="document.pressed='deleteTag';document.parameter=this.name;"/>
<input type='hidden' name='tagId2' value='<c:out value="${tag2.id}"/>'/>
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
X:<input type='text' name='positionX3' 
<c:if test='${tag3 != null}'>
value = '<c:out value="${tag3.positionX}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Y:<input type='text' name='positionY3' 
<c:if test='${tag3 != null}'>
value = '<c:out value="${tag3.positionY}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Tag:<input type='text' name='tag3' 
<c:if test='${tag3 != null}'>
value = '<c:out value="${tag3.tag}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
<c:if test='${tag3 != null}'>
<input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag3.id}'/>" onClick="document.pressed='deleteTag';document.parameter=this.name;"/>
<input type='hidden' name='tagId3' value='<c:out value="${tag3.id}"/>'/>
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
X:<input type='text' name='positionX4' 
<c:if test='${tag4 != null}'>
value = '<c:out value="${tag4.positionX}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Y:<input type='text' name='positionY4' 
<c:if test='${tag4 != null}'>
value = '<c:out value="${tag4.positionY}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
</td>
<td class='portlet-table-td-left'>
Tag:<input type='text' name='tag4' 
<c:if test='${tag4 != null}'>
value = '<c:out value="${tag4.tag}"/>'
</c:if>
class='portlet-form-input-field' size='18' /> 
<c:if test='${tag4 != null}'>
<input type="image" title='<fmt:message key="portlet.button.delete"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" style='border: 0px;' height='11' width='11' name="<c:out value='${tag4.id}'/>" onClick="document.pressed='deleteTag';document.parameter=this.name;"/>
<input type='hidden' name='tagId4' value='<c:out value="${tag4.id}"/>'/>
</c:if>
</td>
</tr>

<c:forEach var="tag" items="${requestScope.picture.taggings}" varStatus="status">
<tr>
<td class='portlet-table-td-left'>
X: <c:out value="${tag.positionX}"/>
</td>
<td class='portlet-table-td-left'>
Y: <c:out value="${tag.positionY}"/>
</td>
<td class='portlet-table-td-left'>
Tag: <c:out value="${tag.tag}"/>
</td>
</tr>
</c:forEach>
</table>
</td>
--%>
</tr>
</table>
</form>
</c:if>

<c:if test='${requestScope.picture == null}'>
<span>
<h4>Add a new Picture</h4>
</span>
<br/>
<span>
Share your photos to let friends and other members see who you are.<br/> 
Photos may not contain nudity, sexually explicit content, violent or offensive material, or copyrighted images. Do not load images of other people without their permission.
</span>
<form name='myPhotoForm' enctype='multipart/form-data' method='post'
  action ='<%= request.getContextPath() %>/uploadPicture.lp' onsubmit="javascript:return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback}, '<c:out value="${requestScope.responseId}"/>')">
  <table border='0' cellpadding='0' cellspacing='0' > 
    <tr>
      <td class='portlet-table-td-left' >
        <br/><input type='file' name='file' size='24' />
      </td>
    </tr>
    <tr>
    	<td class='portlet-link-left' >
		<a href='javascript:void(0)' onclick="javascript:Light.showUploader('<c:out value="${requestScope.responseId}"/>','uploadPictures');" ><fmt:message key="portlet.button.uploadMultiPictures"/></a>
    	</td>      
    </tr>
    <tr>
      <td class='portlet-table-td-right' >
        <input type='submit' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
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