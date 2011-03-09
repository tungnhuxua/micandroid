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

<textarea id="myProfile.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign="top">
<td class='portlet-table-td-center' width='50%' style="padding:10px 0 0 5px;">
{if user.photoUrl }
{if !user.httpPhotoUrl }
<img src='<%= request.getContextPath() %>${user.photoUrl}' align="middle" width='${user.photoSmallWidth}' height='${user.photoSmallHeight}'/>
{else}
<img src='${user.photoUrl}' align="middle" width='${user.photoSmallWidth}' height='${user.photoSmallHeight}'/>
{/if}
{else}
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>'  align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
{/if}
<br/>
<light:authenticateUser> 
<span class="portlet-rss" style="text-align:center;">   
<a href='javascript:;' onclick="javascript:editProfilePhoto(event,'${id}');" ><fmt:message key="portlet.label.editPhoto"/></a>
<%
if(!org.light.portal.util.StringUtil.isEmpty(org.light.portal.util.PropUtil.getString("facebook.apiKey"))){
%>
<a href='<%= request.getContextPath() %>/openId/facebookRequest.jsp' title="get profile photo from facebook">
<img src='<%= request.getContextPath() %>/light/images/facebook.gif' style='margin-left:5px;' align="top" alt='' width='16' height='16'/>
</a>	
<%
}
%>
<%
if(!org.light.portal.util.StringUtil.isEmpty(org.light.portal.util.PropUtil.getString("twitter.signin.api.key"))){
%>
<a href='<%= request.getContextPath() %>/openId/twitterRequest.jsp' title="get profile photo from twitter">
<img src='<%= request.getContextPath() %>/light/images/twitter16.png' style='margin-left:5px;' align="top" alt='' width='16' height='16'/>
</a>	
<%
}
%>
</span>
</light:authenticateUser>
</td>
<td class='portlet-table-td-center' width='50%' style="padding-top:10px">
<span class="portlet-rss" > 
${user.name}
<br/>
{if user.occupation }
${user.occupation}<br/>
{/if}
<c:if test='${sessionScope.visitedUser != null }'>
{if user.currentStatus == 1}
<img src="<%= request.getContextPath() %>/light/images/online.gif" height='16' width='16'  align="bottom" alt=''/><br/>
{/if}
</c:if>
${user.genderName}<br/>
<light:authenticateFriend>
{if user.age }
${user.age} <fmt:message key="portlet.label.age"/> <br/>
{/if}
{if user.birthday }
<fmt:message key="portlet.label.birthday"/> :${user.birthday}
{/if}
</light:authenticateFriend>
{if user.city }
${user.city}, ${user.province}<br/>
{/if}
{if user.country }
${user.country}<br/>
{/if}
</span>
</td>
</tr>
</table>
<br/>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left' >
<span class="icons hand" title='<fmt:message key="portlet.label.myPictures"/>'>
<span class="icons picture" style="margin-right:5px;" onclick="javascript:showMyPicture();"></span>
</span>
<span class="icons hand" title='<fmt:message key="portlet.label.myMusics"/>'>
<span class="icons music" style="margin-right:5px;" onclick="javascript:showMyMusic();"></span>
</span>
<span class="icons hand" title='<fmt:message key="portlet.title.myFile"/>'>
<span class="icons file" style="margin-right:5px;" onclick="javascript:showMyFile();"></span>
</span>
<span class="icons hand" title='<fmt:message key="portlet.title.myBlog"/>'>
<span class="icons blog" style="margin-right:5px;" onclick="javascript:showMyBlog();"></span>
</span>
<span class="icons hand" title='<fmt:message key="portlet.title.favourite"/>'>
<span class="icons bookmark" style="margin-right:5px;" onclick="javascript:showMyFavorites();"></span>
</span>
<light:authenticateUser> 
<span class="icons hand" title='<fmt:message key="portlet.title.myViewedItem"/>'>
<span class="icons viewed" style="margin-right:5px;" onclick="javascript:showMyViewed();"></span>
</span>
<span class="icons hand" title='<fmt:message key="portlet.title.recommendedItem"/>'>
<span class="icons recommended" style="margin-right:5px;" onclick="javascript:showMyRecommended();"></span>
</span>
</light:authenticateUser>
</td>
</tr>
<light:authenticateUser> 
<tr>
<td class='portlet-table-td-left' >
<fmt:message key="portlet.label.myUrl"/>
</td>
<td class='portlet-table-td-left' >
{if user.uriType == 0}
<span class="portlet-rss" > 
<a href='javascript:;' onclick="javascript:editMyUrl(event,'${id}');" ><fmt:message key="portlet.label.edit"/></a>
</span>
{/if}
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<span class="portlet-rss" > 
<a href='${user.url}' >${user.url}</a>
</span>
</td>
</tr>
</light:authenticateUser>
<tr>
<td class='portlet-table-td-left' >
<b><fmt:message key="portlet.label.myTags"/></b>
<light:authenticateUser>
<span class="icons hand" title='<fmt:message key="portlet.button.add"/>'>
<span class="icons add" style="margin-top:3px;" onclick="javascript:Light.showAddUserTag(event,'${id}',<%= org.light.portal.util.Constants._OBJECT_TYPE_USER %>,${user.id});"></span>
</span>
</light:authenticateUser>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' >
<span class="portlet-rss" style="padding:0 0 5px 0;"> 
{for tag in user.tags}
<a href="javascript:;" onclick="javascript:Light.globalSearch('{tag.tag}','org.light.portal.model.User');" title='${tag.score} <fmt:message key="portlet.label.users"/>'><label style='margin-left:5px;font-size:${tag.size}px;cursor:pointer;cursor:hand;'>${tag.tag}</label></a>
<light:authenticateUser>
<a href="javascript:;" onclick="javascript:document.resetLastAction=1;Light.executeAction('${id}',this.form,'delete',null,'${tag.id}','view','normal');" /><img src="<%= request.getContextPath() %>/light/images/delete.gif" style='border:0px;' title='<fmt:message key="portlet.button.delete"/>' width="7" height="7"></a>
</light:authenticateUser>
{/for}
</span>
</td>
</tr>
<light:authenticateUser>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.ProfileViews"/>: ${user.visitCount}
</td>
</tr>
</light:authenticateUser>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<fmt:message key="portlet.label.lastLoginDate"/>: ${user.lastDate}
</td>
</tr>
</table>
</textarea>

<textarea id="myContact.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin-bottom:5px;'>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand">
<span class="icons inbox" onclick="javascript:Light.showSendMessage(event,'${id}','${user.id}','${user.name}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showSendMessage(event,'${id}','${user.id}','${user.name}');" ><fmt:message key="portlet.label.sendMessage"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand" >
<span class="icons forward" onclick="javascript:Light.showForwardToFriends(event,'${id}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showForwardToFriends(event,'${id}');" ><fmt:message key="portlet.label.forwardToFriend"/></a>
</td>
</tr>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand" >
<span class="icons friend" onclick="javascript:Light.showAddToFriend(event,'${id}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showAddToFriend(event,'${id}');" ><fmt:message key="portlet.label.addToFriend"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand">
<span class="icons add_favorite" onclick="javascript:Light.showAddToFavorites(event,'${id}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showAddToFavorites(event,'${id}');" ><fmt:message key="portlet.label.addToFavorites"/></a>
</td>
</tr>
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='45%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand">
<span class="icons block" onclick="javascript:Light.showBlockUser(event,'${id}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showBlockUser(event,'${id}');" ><fmt:message key="portlet.label.blockUser"/></a>
</td>
<td class='portlet-item' width='55%' style='padding: 5px 0px 3px 5px;'>
<span class="icons hand">
<span class="icons chat" onclick="javascript:Light.showInstantMessage(event,'${id}');"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showInstantMessage(event,'${id}');" ><fmt:message key="portlet.label.instantMessage2"/></a>
</td>
</tr>
</table>
</textarea>

<textarea id="myBlurb.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
{if user.headline }
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
<h4>${user.headline}</h4>
</td>
</tr>
{/if}
{if user.aboutMe }
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
${user.aboutMe}<br/><br/>
</td>
</tr>
{/if}
{if user.interests }
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
<FONT color='#ff6600'><fmt:message key="portlet.label.interests"/>:</FONT>
</td>
</tr>
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
${user.interests}<br/><br/>
</td>
</tr>
{/if}
{if user.likeToMeet }
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
<FONT color='#ff6600'><fmt:message key="portlet.label.likeToMeet"/>:</FONT>
</td>
</tr>
<tr>
<td style='text-align:left; padding : 0 0 5 5; font-family: Cursive;'>
${user.likeToMeet}<br/><br/>
</td>
</tr>
{/if}
</table>
</textarea>


<textarea id="myMessage.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='98%'>
{if user.messageCount }
<tr class='portlet-table-td-left'>
<td class='portlet-item' colspan='2'>
<a href='javascript:;' onclick="javascript:Light.showInboxMessage();" >
<fmt:message key="portlet.label.newMessage"/>(${user.messageCount})
</a>
</td>
</tr>
{/if}
{if user.friendRequestCount }
<tr class='portlet-table-td-left'>
<td class='portlet-item' colspan='2'>
<a href='javascript:;' onclick="javascript:Light.showFriendRequest();" >
<fmt:message key="portlet.label.newFriendRequest"/>(${user.friendRequestCount})
</a>
</td>
</tr>
{/if}
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='40%' style='padding: 5 5 0 5;'>
<span class="icons hand">
<span class="icons inbox" onclick="javascript:Light.showInboxMessage();"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showInboxMessage();" ><fmt:message key="portlet.label.inbox"/></a>
</td>
<td class='portlet-item' width='60%' style='padding: 5 5 0 5;'>
<span class="icons hand">
<span class="icons friend" onclick="javascript:Light.showFriendRequest();"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showFriendRequest();" ><fmt:message key="portlet.label.friendRequest"/></a>
</td>
</tr>
<tr  class='portlet-table-td-left'>
<td class='portlet-item' width='40%' style='padding: 5 5 5 5;'>
<span class="icons hand">
<span class="icons outbox" onclick="javascript:Light.showSentMessage();"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showSentMessage();" ><fmt:message key="portlet.label.sent"/></a>
</td>
<td class='portlet-item' width='60%' style='padding: 5 5 5 5;'>
<span class="icons hand">
<span class="icons add_favorite" onclick="javascript:Light.showInviteFriend();"></span>
</span>
<a style='margin-left:5px;' href='javascript:;' onclick="javascript:Light.showInviteFriend();" ><fmt:message key="portlet.label.inviteFriend"/></a>
</td>
</tr>
</table>
</textarea>

</fmt:bundle>