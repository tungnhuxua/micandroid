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
<light:authorizeGroupMember type='yes'>
<c:if test='${requestScope.group.memberImage == 1}'> 
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-link-left' >
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT'><portlet:param name='add' value='add'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addPicture"/></a>
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
<a href='javascript:;' onclick="<portlet:renderURL  portletMode='EDIT'/>" ><img src='<%= request.getContextPath() %>/light/images/add.gif' style='border: 0px;' height='16' width='16' align="middle"/><fmt:message key="portlet.button.addPicture"/></a>
</td>
</tr>
</table>
</c:if>
</light:authorizeGroupMember>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:if test='${requestScope.state == "normal" && requestScope.pictureNavigator != null}'>
<tr>
<td class='portlet-table-td-center' width='100%'>
<a href='javascript:;' onclick="<portlet:renderURL><portlet:param name='previous' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.label.previous"/>' style='border: 0px' /></a>						
<a href='javascript:;' onclick="<portlet:renderURL><portlet:param name='next' value='1'/></portlet:renderURL>" ><img src='<%= request.getContextPath() %>/light/images/next.gif' title='<fmt:message key="portlet.label.next"/>' style='border: 0px' /></a>
</td>
</tr>
</c:if>
</table>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
<c:forEach var="picture" items="${requestScope.showPictures}" varStatus="status">
<c:if test='${status.index % columnNumber == 0}'>
<tr valign='top'>
</c:if>
<td class='portlet-table-td-center'>
<table border='0' cellpadding='0' cellspacing='0' width= '100%' style="margin:20px 0 0 10px;" >
<tr valign='top'>
<td class='portlet-table-td-center' width= '70'>
<ul class='vote-container'>
 <li class='vote-score'>
 	<label class='theme-color'><font size='4'><c:out value="${picture.score}"/></font></label>
 </li>
 <li class='vote-button'>
 	<span class='portlet-item'><a href='javascript:;' onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','','','action=score;pictureId=<c:out value="${picture.id}"/>','',true,false);" ><fmt:message key="portlet.button.popIt"/></a></span>
 </li>
</ul>
</td>
<td class='portlet-table-td-left'>
<c:if test='${picture.caption != null && picture.caption != ""}'>
<b><span class="portal-header-title" style='padding: 0; '><FONT size='4'><c:out value="${picture.caption}"/></FONT></span></b>
<br/>
</c:if>
<span class="portlet-rss"> 					
<fmt:message key="portlet.label.postedBy"/>: <a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${picture.uri}"/>' target='_blank'><c:out value="${picture.displayName}"/></a>
</span>
<img id='picture_<c:out value="${picture.id}"/>' src='
<c:if test='${!picture.httpUrl}'>
<%= request.getContextPath() %>
</c:if>
<c:out value="${picture.pictureUrl}"/>' style='border: 0px;' align="top" 
 onmouseover="javascript:Light.showPPT('<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');"
 onmouseout="javascript:Light.hidePPT(event,'<c:out value="${requestScope.responseId}"/>','<c:out value="${picture.id}"/>','<c:out value="${picture.taggingsString}"/>');"
/>
<light:authenticateUser> 
<input type='button' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','','pictureId=<c:out value="${picture.id}"/>');" value='<fmt:message key="portlet.button.addTag"/>' class='portlet-form-button'/>
</light:authenticateUser>
</td>
</tr>
</table>
</td>
<c:if test='${status.index % columnNumber == columnNumber - 1}'>
</tr>
<tr>
<td colspan='<c:out value="${columnNumber}"/>' style='margin-top:20px;'>

</td>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.pictureCount % columnNumber != 0}'>
</tr>
<tr>
<td colspan='<c:out value="${columnNumber}"/>' style='margin-top:20px;'>
</td>
</tr>
</c:if>
<tr>
<td class='portlet-link-left' colspan='<c:out value="${columnNumber}"/>'>
<fmt:message key="portlet.label.total"/> <c:out value="${requestScope.pictureCount}"/> <fmt:message key="portlet.label.pictures"/>
</td>
</tr>
</table>
<c:if test='${requestScope.state == "normal" && requestScope.showMore != null}'>
<span class="portlet-rss" style="text-align:right;">
<a href='javascript:;' onclick="<portlet:renderURL  windowState='MAXIMIZED'/>" ><fmt:message key="portlet.label.more"/>...</a> 
</span>
</c:if>
</fmt:bundle>
</body>
</html>