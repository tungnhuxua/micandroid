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
<fmt:bundle basename="resourceBundle">
<form name='form_<c:out value="${requestScope.responseId}"/>' action="javascript:;">
<!-- 
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>  
<c:if test='${requestScope.userProfile.firstName != null }'>
<c:out value="${requestScope.userProfile.firstName}"/>
</c:if>
<c:if test='${requestScope.userProfile.firstName == null }'>
<c:out value="${requestScope.user.name}"/>
</c:if>
<br/>
<c:if test='${requestScope.userProfile.occupation != null }'>
<b><c:out value="${requestScope.userProfile.occupation}"/></b><br/><br/>
</c:if>
</td>
</tr>
</table>
 -->
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign="top">
<td class='portlet-table-td-center' width='50%' style="padding-top:10px">
<c:if test='${requestScope.user.photoUrl == null}'>
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' style='border: 0px;'  align="middle" width='75' height='75'/>
</c:if>
<c:if test='${requestScope.user.photoUrl != null}'>
<img src='<%= request.getContextPath() %><c:out value="${requestScope.user.photoUrl}"/>' style='border: 0px;'  align="middle" width='<c:out value="${requestScope.user.photoSmallWidth}"/>' height='<c:out value="${requestScope.user.photoSmallHeight}"/>'/>
</c:if>
<br/>
<light:authenticateUser> 
<span class="portlet-rss" style="text-align:center;">   
<a href='javascript:void(0)' onclick="javascript:editProfilePhoto(event,'<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.editPhoto"/></a>
</span>
</light:authenticateUser>
</td>
<td class='portlet-table-td-center' width='50%' style="padding-top:10px">
<span class="portlet-rss" > 
<c:if test='${requestScope.userProfile.firstName != null }'>
<c:out value="${requestScope.userProfile.firstName}"/>
</c:if>
<c:if test='${requestScope.userProfile.firstName == null }'>
<c:out value="${requestScope.user.name}"/>
</c:if>
<br/>
<c:if test='${requestScope.userProfile.occupation != null }'>
<b><c:out value="${requestScope.userProfile.occupation}"/></b><br/>
</c:if>  
<c:if test='${requestScope.chat.currentStatus == 1 && sessionScope.visitedUser != null }'>
<img src="<%= request.getContextPath() %>/light/images/online.gif" style='border: 0px;' height='16' width='16'  align="bottom" alt=''/><br/>
</c:if>
<!-- 
<c:out value="${requestScope.user.genderName}"/><br/>
<light:authenticateFriend>
<c:if test='${requestScope.user.age !="unknow" }'>
<c:out value="${requestScope.user.age}"/> <fmt:message key="portlet.label.age"/> <br/>
</c:if>
<c:if test='${requestScope.user.showBirthToFriend == 1}'>
<fmt:message key="portlet.label.birthday"/> :<c:out value="${requestScope.user.birthday}"/> 
</c:if>
</light:authenticateFriend>
 -->
<c:if test='${requestScope.user.city != null}'>
<c:out value="${requestScope.user.city}"/>, <c:out value="${requestScope.user.province}"/><br/>
</c:if>
<c:if test='${requestScope.user.country != null}'>
<c:out value="${requestScope.user.country}"/><br/>
</c:if>
<c:if test='${requestScope.edit == null}'>
<div style='width: 137px;'>
<img src="<%= request.getContextPath() %>/light/images/talk_top.png" style="border: medium none ;" width="137" height="11"/>
<div class='talk-body'>
<div>
<c:if test='${requestScope.user.caption != null}'>
<c:out value="${requestScope.user.caption}"/>
</c:if>
<c:if test='${requestScope.user.caption == null}'>
<fmt:message key="message.userStatus"/>
</c:if>
</div>
<light:authenticateUser> 
<div class='portlet-item' style='text-align:right;'><a href="javascript:;" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','edit','normal');" /><fmt:message key="portlet.button.update"/></a></div>
</light:authenticateUser>
</div>
<div><img src="<%= request.getContextPath() %>/light/images/talk_bottom.png" style="border: medium none ;"  width="137" height="11"></div>
</div>
</c:if>
<c:if test='${requestScope.edit != null}'>
<div style='width: 137px;'>
<img src="<%= request.getContextPath() %>/light/images/talk_top.png" style="border: medium none ;" width="137" height="11"/>
<div class='talk-body'>
<div><input type='text' name='caption' value='<c:out value="${requestScope.user.caption}"/>' class='portlet-form-input-field' size='15' onChange="javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>',this.form,'save',null,null,'VIEW','normal');"/> 
</div>
<div class='portlet-item' style='text-align:right;padding-top:10px;'>
<light:authenticateUser>
<a href="javascript:;" onclick="javascript:document.resetLastAction=1;Light.executeAction('<c:out value="${requestScope.responseId}"/>',this.form,'save',null,null,'view','normal');" /><fmt:message key="portlet.button.save"/></a>
</light:authenticateUser>
<a href="javascript:;" onclick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal');" /><fmt:message key="portlet.button.cancel"/></a>
</div>
</div>
<div><img src="<%= request.getContextPath() %>/light/images/talk_bottom.png" style="border: medium none ;" width="137" height="11"></div>
</div>
</c:if>
<c:if test='${requestScope.user.musicUrl != null}'>
<a href='javascript:void(0)' onclick="javascript:showMyMusic();" ><fmt:message key="portlet.label.myMusics"/></a>
<a href='javascript:void(0)' onClick="javascript:Light.playMusic('<c:out value="${user.musicUrl}"/>');" ><img src="<%= request.getContextPath() %>/light/images/play.png" title='<fmt:message key="portlet.button.playBg"/>' align='bottom' style='border: 0px;' height='16' width='16'/></a>
<a href='javascript:void(0)' onClick="javascript:Light.stopMusic();" ><img src="<%= request.getContextPath() %>/light/images/stop.png" title='<fmt:message key="portlet.button.stopBg"/>' align='bottom' style='border: 0px;' height='16' width='16'/></a>
</c:if>
<!-- 
<light:authenticateUser>
<a href='javascript:void(0)' onclick="javascript:Light.portal.editProfile();" ><fmt:message key="portlet.label.myProfile"/></a>
</light:authenticateUser>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyPicture();" ><fmt:message key="portlet.label.myPictures"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyMusic();" ><fmt:message key="portlet.label.myMusics"/></a>
<a href='javascript:void(0)' onClick="javascript:Light.playMusic('<c:out value="${user.musicUrl}"/>');" ><img src="<%= request.getContextPath() %>/light/images/play.png" title='<fmt:message key="portlet.button.playBg"/>' align='middle' style='border: 0px;' height='16' width='16'/></a>
<a href='javascript:void(0)' onClick="javascript:Light.stopMusic();" ><img src="<%= request.getContextPath() %>/light/images/stop.png" title='<fmt:message key="portlet.button.stopBg"/>' align='middle' style='border: 0px;' height='16' width='16'/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyFile();" ><fmt:message key="portlet.title.myFile"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyBlog();" ><fmt:message key="portlet.title.myBlog"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyFavorites();" ><fmt:message key="portlet.title.favourite"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyViewed();" ><fmt:message key="portlet.title.myViewedItem"/></a>
<br/>
<a href='javascript:void(0)' onclick="javascript:showMyRecommended();" ><fmt:message key="portlet.title.recommendedItem"/></a>
 -->
 </span>
</td>
</tr>
</table>
<br/>

<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<light:authenticateUser> 
<light:getHost/>
<tr>
<td class='portlet-table-td-left' >
<b><fmt:message key="portlet.label.myUrl"/></b>
</td>
<td class='portlet-table-td-left' >
<c:if test='${requestScope.user.uriType == 0}'>
<span class="portlet-rss" > 
<a href='javascript:void(0)' onclick="javascript:editMyUrl(event,'<c:out value="${requestScope.responseId}"/>');" ><fmt:message key="portlet.label.edit"/></a>
</span>
</c:if>
</td>
</tr>
<c:if test='${applicationScope.urlFormat == 1}'>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<span class="portlet-rss" > 
<a href='http://<c:out value="${requestScope.user.uri}"/>.<c:out value="${host}"/>' >http://<c:out value="${requestScope.user.uri}"/>.<c:out value="${host}"/></a>
</span>
</td>
</tr>
</c:if>
<c:if test='${applicationScope.urlFormat != 1}'>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<span class="portlet-rss" > 
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${requestScope.user.uri}"/>'>http://<c:out value="${sessionScope.org.userSpacePrefix}"/><c:out value="${requestScope.user.uri}"/></a>
</span>
</tr>
</c:if>
</light:authenticateUser>
<tr>
<td class='portlet-table-td-left' >
<b><fmt:message key="portlet.label.myTags"/></b>
<light:authenticateUser>
<a href="javascript:void(0);" onClick="javascript:Light.showAddUserTag(event,'<c:out value="${requestScope.responseId}"/>');"><img src="<%= request.getContextPath() %>/light/images/addLink.gif" style='border:0px;margin-left:5px;' title='<fmt:message key="portlet.button.add"/>' width="10" height="10"></a>
</light:authenticateUser>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' >
<span class="portlet-rss" > 
<c:forEach var="tag" items="${requestScope.tags}" >
<a href="javascript:void(0);" onclick="javascript:Light.globalSearch('<c:out value="${tag.tag}"/>','org.light.portal.model.User');" title='<c:out value="${tag.score}"/> <fmt:message key="portlet.label.users"/>'><label style='color:<%= org.light.portal.util.ConfigurationUtil.getRandomColor() %>;font-size:<c:out value="${tag.size}"/>px;'><c:out value="${tag.tag}"/></label></a>
<light:authenticateUser>
<a href="javascript:;" onclick="javascript:document.resetLastAction=1;javascript:Light.executeAction('<c:out value="${requestScope.responseId}"/>',this.form,'delete',null,'<c:out value="${tag.id}"/>','view','normal');" /><img src="<%= request.getContextPath() %>/light/images/delete.gif" style='border:0px;' title='<fmt:message key="portlet.button.delete"/>' width="7" height="7"></a>
</light:authenticateUser>
</c:forEach>
</span>
</td>
</tr>
<light:authenticateUser>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.ProfileViews"/>: <c:out value="${requestScope.user.visitCount}"/>
</td>
</tr>
</light:authenticateUser>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.lastLoginDate"/>: <c:out value="${requestScope.user.lastDate}"/>
</td>
</tr>
</table>
</form>
</fmt:bundle>