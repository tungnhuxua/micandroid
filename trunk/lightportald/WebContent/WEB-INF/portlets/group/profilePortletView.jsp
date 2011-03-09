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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr VALIGN='TOP'>
<td class='portlet-table-td-left' width='100%'>
<table>
<tr VALIGN='TOP'>
<td class='portlet-table-td-center'>
<c:if test='${requestScope.group.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultGroupPortrait}"/>' style='border: 0px;' width='81' height='81'/>
</c:if>
<c:if test='${requestScope.group.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${requestScope.group.photoUrl}"/>' style='border: 0px;'  width='<c:out value="${requestScope.group.photoSmallWidth}"/>' height='<c:out value="${requestScope.group.photoSmallHeight}"/>'/>
</c:if>
<c:if test='${requestScope.group.caption != null}'>
<br/>
<c:out value="${requestScope.group.caption}"/>
</c:if>
<br/><br/>
<!-- 
<span class="portlet-rss" > 
<a href='javascript:void(0)' onclick="javascript:viewGroupPictures(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.viewGroupPictures"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:viewGroupMembers(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.viewGroupMembers"/></a>
</span>
 -->
</td>
<td class='portlet-table-td-left'>
<span class="portlet-rss" >   
<c:out value="${requestScope.group.displayName}"/>
<br/><br/> 
<fmt:message key="portlet.label.category"/>:
<b><a href='javascript:void(0)' onclick="javascript:viewGroupCategory(event,'<c:out value="${requestScope.group.categoryId}"/>','<c:out value="${requestScope.responseId}"/>');" >
<c:out value="${requestScope.group.categoryName}"/></a></b>
<br/><br/>
<fmt:message key="portlet.label.type"/>:
<c:if test='${requestScope.group.openJoin == 0}'>
<fmt:message key="portlet.label.privateMemberShip"/>
</c:if>
<c:if test='${requestScope.group.openJoin == 1}'>
<fmt:message key="portlet.label.publicMemberShip"/>
</c:if>
<br/>
<fmt:message key="portlet.label.founded"/>:<c:out value="${requestScope.group.date}"/>
<br/>
<c:if test='${requestScope.group.city != null || requestScope.group.province != null || requestScope.group.country != null}'>
<fmt:message key="portlet.label.location"/>:<c:out value="${requestScope.group.city}"/>
<c:if test='${requestScope.group.city != null && requestScope.group.province != null}'>
,
</c:if>
<c:out value="${requestScope.group.province}"/> <c:out value="${requestScope.group.country}"/>
<br/>
</c:if>
<fmt:message key="portlet.label.ProfileViews"/>: <c:out value="${requestScope.group.viewCount}"/>
<br/>
<fmt:message key="portlet.label.groupMembers"/>:<c:out value="${requestScope.group.members}"/>
<br/>
</span>
</td>
</tr>
</table>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-center' width='100%' >
<light:authorizeGroupMember type='no'>
<c:if test='${requestScope.group.openJoin == 1}'>
<input type="button"  value='<fmt:message key="portlet.button.join"/>' class='portlet-form-button' 
onclick="javascript:joinToGroup2(event,'<c:out value="${requestScope.responseId}"/>');"  />
<br/><br/>
</c:if>
</light:authorizeGroupMember>
<light:authorizeGroupMember type='yes'>
<c:if test='${requestScope.group.memberInvite == 1}'>
<input type="button"  value='<fmt:message key="portlet.button.inviteOthers"/>' class='portlet-form-button' 
onclick="javascript:inviteOthers(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
</c:if>
<c:if test='${requestScope.group.memberInvite == 0}'>
<light:authorizeGroupMember type='leader'>
<input type="button"  value='<fmt:message key="portlet.button.inviteOthers"/>' class='portlet-form-button' 
onclick="javascript:inviteOthers(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
</light:authorizeGroupMember>
</c:if>
<input type="button"  value='<fmt:message key="portlet.button.privacy"/>' class='portlet-form-button' 
onclick="javascript:groupPrivacy(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
<input type="button"  value='<fmt:message key="portlet.button.resign"/>' class='portlet-form-button' 
onclick="javascript:resign(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
<!-- 
<c:if test='${requestScope.group.memberImage == 1}'> 
<input type="button"  value='<fmt:message key="portlet.button.uploadPictures"/>' class='portlet-form-button' 
onclick="javascript:uploadGroupPictures2(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
<br/><br/>
</c:if>
<c:if test='${requestScope.group.memberImage == 0}'>
<light:authorizeGroupMember type='leader'>
<input type="button"  value='<fmt:message key="portlet.button.uploadPictures"/>' class='portlet-form-button' 
onclick="javascript:uploadGroupPictures2(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  />
<br/><br/>
</light:authorizeGroupMember>
</c:if>
-->
<br/><br/>
</light:authorizeGroupMember>
<light:authorizeGroupMember type='leader'>
<input type="button"  value='<fmt:message key="portlet.button.editProfile"/>' class='portlet-form-button' 
onclick="javascript:editGroupProfile(event,'<c:out value="${requestScope.group.id}"/>','<c:out value="${requestScope.responseId}"/>');"  /> 

<input type="button"  value='<fmt:message key="portlet.button.deleteGroup"/>' class='portlet-form-button' 
onclick="javascript:deleteGroupProfile(event,'<c:out value="${requestScope.group.id}"/>''<c:out value="${requestScope.group.displayName}"/>',,'<c:out value="${requestScope.responseId}"/>');"  /> 
<br/><br/>
</light:authorizeGroupMember>
</td>
</tr>
</table>
<!-- 
<td class='portlet-table-td-right' width='10%' >
<table>
<tr VALIGN='TOP'>
<td class='portlet-table-td-center'>
<span class='portlet-item'>
<c:if test='${requestScope.user.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'   width='75' height='75'/>
</c:if>
<c:if test='${requestScope.user.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${requestScope.user.photoUrl}"/>' style='border: 0px;'   width='<c:out value="${requestScope.user.photoSmallWidth}"/>' height='<c:out value="${requestScope.user.photoSmallHeight}"/>'/>
</c:if>
<br/>
<fmt:message key="portlet.label.groupLeader"/>
<br/>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${requestScope.user.uri}"/>'><c:out value="${requestScope.user.name}"/></a><br/><br/>
<c:if test='${requestScope.user.currentStatus == 1 }'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/><br/><br/>
</c:if>
</span>
</td>
</tr>
</table>
</td>
-->
</fmt:bundle>
</body>
</html>