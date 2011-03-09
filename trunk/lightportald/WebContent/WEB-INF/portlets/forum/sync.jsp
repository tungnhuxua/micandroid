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

<textarea id="forumCategories.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-link-left' >
<light:authenticateUser>  
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','','action=category');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForumCategory"/></a>
</light:authenticateUser>
</td>
</tr>
</table>
{if forum.categories }
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<tr>
<td class='portlet-table-td-left' width='60%' style='padding-left:10px;'><fmt:message key="portlet.label.forumCategory"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topics"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.lastPost"/></td>
</tr>
{for item in forum.categories }
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='60%'>
<table>
<tr class='portlet-table-td-left' valign='top'>
<td>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${item.id};direction=down');">
<img src='<%= request.getContextPath() %>/light/images/folder.gif'  align="top" />
</a>
<br/>
</td>
<td>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${item.id};direction=down');">
${item.name}
<br/>
<span class='portlet-note'>${item.desc}</span>
</td>
</tr>
</table>
</td>
<td class='portlet-table-td-left' style='padding-left:10px;'>
${item.topics}
</td>
<td class='portlet-table-td-left' style='padding-left:10px;'>
${item.posts}
</td>
<td class='portlet-item' style='padding-left:10px;'>
{if item.lastForumId }
<span class='portlet-note'>${item.lastForum.date}</span>
<br/>
<span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.lastForum.uri}' >${item.lastForum.displayName}</a>
<span class='portlet-note'>>> </span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${item.id};forumId=${item.lastForum.forumId};topicId=${item.lastForum.topicId}');">
<fmt:message key="portlet.label.viewPost"/>
</a>
{/if}
</td>
</tr>
{/for}
</table>
{/if}
</textarea>

<textarea id="forums.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' >
${forum.categoryName}
</td>
<light:authenticateUser> 
<td class='portlet-link-left' > 
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','edit','','action=forum;categoryId=${forum.categoryId}');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForum"/></a>
</td>
</light:authenticateUser>
{if forum.back }
<td class='portlet-link' >
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','');" ><img src='<%= request.getContextPath() %>/light/images/previous.gif' title='<fmt:message key="portlet.button.back"/>'/></a>
</td>
{/if}
</tr>
</table>
{if forum.forumLists }
<table border='0' cellpadding='0' cellspacing='0'  width='98%'>
<tr>
<td class='portlet-table-td-left' width='60%' style='padding-left:10px;'><fmt:message key="portlet.label.forum"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.topics"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.posts"/></td>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.lastPost"/></td>
</tr>
{for item in forum.forumLists}
<tr class='portlet-table-td-left'>
<td class='portlet-item' width='60%'>
<table>
<tr class='portlet-table-td-left'>
<td>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${item.id}');">
<img src='<%= request.getContextPath() %>/light/images/folder.gif'  align="top" />
</a>
<br/>
</td>
<td class='portlet-item'>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${item.id}');">
${item.name}</a>
<br/>
<span class='portlet-note'>${item.desc}</span>
</td>
</tr>
</table>
</td>
<td class='portlet-table-td-left' style='padding-left:10px;'>
${item.topics}
</td>
<td class='portlet-table-td-left' style='padding-left:10px;'>
${item.posts}
</td>
<td class='portlet-item' style='padding-left:10px;'>
{if item.lastForumId }
<span class='portlet-note'>${item.lastForum.date}</span>
<br/>
<span class='portlet-note'><fmt:message key="portlet.label.postedBy"/> </span> 
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.lastForum.uri}' >${item.lastForum.displayName}</a>
<span class='portlet-note'>>> </span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${item.lastForum.forumId};topicId=${item.lastForum.topicId}');">
<fmt:message key="portlet.label.viewPost"/>
</a>
{/if}
</td>
</tr>
{/for}
</table>
{/if}
</textarea>

<textarea id="forumTopics.view" style="display:none;">
{if forum.topicLists }
	<table border='0' cellpadding='0' cellspacing='0' width='95%' style="margin: 20px 0 0 0;">
	<tr>
	<td class='portlet-link-left' >
		<b>
		{if forum.back }
			<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId}');" >${forum.categoryName}</a>
		{else}
			${forum.categoryName}
		{/if}
		-> ${forum.forumName}
		</b>
		<a href='<%= request.getContextPath() %>/rss/forum${forum.forumId}p${forum.pageId}.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
		<light:authenticateUser>  
			<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" height='16' width='16' onClick="javascript:popForumItem(event,'${forum.forumId}','${forum.pageId}','${id}'); return false;"/>
			<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" height='16' width='16' onClick="javascript:forwardForumToFriend(event,'${forum.forumId}','${forum.pageId}','${id}'); return false;"/>
			<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" height='16' width='16' onClick="javascript:saveForumToBookmark(event,'${forum.forumId}','${forum.pageId}','${id}'); return false;"/>
		</light:authenticateUser>	
	</td>
	<td class='portlet-link-left' >
		<a href='javascript:;' onclick="javascript:if(Light.securityCheck()) Light.executeRender('${id}','edit','','categoryId=${forum.categoryId};forumId=${forum.forumId}');" ><img src='<%= request.getContextPath() %>/light/images/add.gif' height='16' width='16' align="middle"/><fmt:message key="portlet.label.addForumTopic"/></a>
	</td>
	<td class='portlet-link' >
		<input type='text' id='${id}_forumSearch' class='portlet-form-input-field' size='24' value='' onchange="javascript:Light.globalSearch($('${id}_forumSearch').value,'org.light.portlets.forum.ForumPost');"/>
		<a href="javascript:;" onclick="javascript:Light.globalSearch($('${id}_forumSearch').value,'org.light.portlets.forum.ForumPost');"><img src='<%= request.getContextPath() %>/light/images/search.gif' height='16' width='16' align='top' title='<fmt:message key="portlet.button.search"/>'/></a>
	</td>
	</tr>
	<tr>
	<td class='portlet-link' colspan='3'>
	{if forum.pages > 1 }
		{var pagesArray = new Array(); for(var n=1;n <= forum.pages;n++) pagesArray.push(n);}
		{for i in pagesArray }
			{if i != forum.pageId }
			<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${forum.forumId};pageId=${i};pages=${forum.pages}');" >${i}</a>
			{else}
			<label class='currentpage'>${i}</label>
			{/if}
		{/for}
	{/if}
	</td>
	</tr>
	</table>

	<table border='0' cellpadding='0' cellspacing='0' width='98%'>
		<tr>
			<td class='portlet-table-td-left' width= '40%'><fmt:message key="portlet.label.forumTopic"/></td>
			<td class='portlet-table-td-left' width= '10%'><fmt:message key="portlet.label.posts"/></td>
			<td class='portlet-table-td-left' width= '25%' style='padding-left:10px;'><fmt:message key="portlet.label.lastPost"/></td>
			<td class='portlet-table-td-left' width= '25%' style='padding-left:10px;'><fmt:message key="portlet.label.topicStarter"/></td>
		</tr>
	{for item in forum.topicLists}
	<tr class='portlet-table-td-left'>
		<td class='portlet-item' >
			<a href='javascript:;' 
				onmouseover="javascript:Light.showSummary(event,'${id}',<%= org.light.portal.util.Constants._OBJECT_TYPE_FORUM %>,'${item.id}');"
				onmouseout="javascript:Light.hideSummary();"
				onclick="javascript:Light.hideSummary();Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${forum.forumId};topicId=${item.id}');">
			<img src='<%= request.getContextPath() %>/light/images/folder_smll.gif'  align="top" />
			${item.topic}</a>
		</td>
		<td class='portlet-item' >
			${item.posts}
		</td>
		<td class='portlet-item'>
			<table>
			<tr>
			<td>
			<span class="portlet-item" style="padding: 0pt;"><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.lastUri}'><div style="position: relative;">
			<ul style="background: url(<%= request.getContextPath() %>{if item.lastPhotoUrl }${item.lastPhotoUrl}{else}/light/images/no_pic.gif{/if}) no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></a></span> 
			</td>
			<td class='portlet-table-td-left'>
			<span class='portlet-note'>${item.lastDate}</span>
			<br/><span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span>
			<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.lastUri}'>${item.lastDisplayName}</a></b></span> 
			</td>
			</tr>
			</table>
		</td>
		<td class='portlet-item'>
			<table>
			<tr>
			<td>
			<span class="portlet-item" style="padding: 0pt;"><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'><div style="position: relative;">
			<ul style="background: url(<%= request.getContextPath() %>{if item.photoUrl }${item.photoUrl}{else}/light/images/no_pic.gif{/if}) no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></a></span> 
			</td>
			<td class='portlet-table-td-left'>
			<span class='portlet-note'>${item.date}</span>
			<br/><span class='portlet-note'><fmt:message key="portlet.label.postedBy"/>  </span> 
			<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'>${item.displayName}</a></b></span>  
			</td>
			</tr>
			</table>
		</td>
	</tr>
	{/for}
	</table>
{/if}
</textarea>

<textarea id="forumTopicPosts.view" style="display:none;">
<form name='form_${id}' action="javascript:;" style='margin:20px 0 0 0;'>
<input type='hidden' name='categoryId'  value='${forum.categoryId}' /> 
<input type='hidden' name='forumId'  value='${forum.forumId}' /> 
{if forum.postLists }
<table border='0' cellpadding='0' cellspacing='0' width='95%'>
<tr>
<td class='portlet-link-left' >
<b>
{if forum.back }
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId}');" >${forum.categoryName}</a>
{else}
${forum.categoryName}
{/if}
-> 
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${forum.forumId}');" >${forum.forumName}</a>
-> 
${forum.topicName}
</b>
<a href='<%= request.getContextPath() %>/rss/topic${forum.topicId}p${forum.pageId}.xml'><img src='<%= request.getContextPath() %>/light/images/rss.gif' title='<fmt:message key="portlet.label.rssForum"/>'/></a>
<light:authenticateUser>  
<input type="image" title='<fmt:message key="portlet.label.popItem"/>' src="<%= request.getContextPath() %>/light/images/popular.gif" height='16' width='16' onClick="javascript:popTopicItem(event,'${forum.topicId}','${forum.pageId}','${id}'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.forwardItem"/>' src="<%= request.getContextPath() %>/light/images/forward.png" height='16' width='16' onClick="javascript:forwardTopicToFriend(event,'${forum.topicId}','${forum.pageId}','${id}'); return false;"/>
<input type="image" title='<fmt:message key="portlet.label.saveBookmark"/>' src="<%= request.getContextPath() %>/light/images/bookmark.gif" height='16' width='16' onClick="javascript:saveTopicToBookmark(event,'${forum.topicId}','${forum.pageId}','${id}'); return false;"/>	
</light:authenticateUser>
</td>
</tr>
<tr>
<td class='portlet-link' colspan='3'>
	{if forum.pages > 1 }
		{var pagesArray = new Array(); for(var n=1;n <= forum.pages;n++) pagesArray.push(n);}
		{for i in pagesArray }
			{if i != forum.pageId }
			<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','categoryId=${forum.categoryId};forumId=${forum.forumId};topicId=${forum.topicId};pageId=${i};pages=${forum.pages}');" >${i}</a>
			{else}
			<label class='currentpage'>${i}</label>
			{/if}
		{/for}
	{/if}
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0'  width='100%'>
{for item in forum.postLists }
{if item_index % 2 == 0}
<tr valign=top class="portlet-itemEven">
{else}
<tr valign=top class="portlet-item">
{/if}
<td class='portlet-table-td-center' width='100px'>
<span class="portlet-item" style="padding: 0pt;"><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'><div style="position: relative;">
<ul style="background: url(<%= request.getContextPath() %>{if item.photoUrl }${item.photoUrl}{else}/light/images/no_pic.gif{/if}) no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 75px; height: 75px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></a></span> 
<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'>${item.displayName}</a></b></span>  
{if item.currentStatusId == 1 }
<img src="<%= request.getContextPath() %>/light/images/online.gif" height='16' width='16'  align="bottom" alt=''/>
<br/>
<a href='javascript:;' onclick="javascript:showInstantMessageMember(event,'${item.postById}','${id}');" ><fmt:message key="portlet.label.instantMessage"/></a>
{/if}
<br/>
<a href='javascript:;' onclick="javascript:showSendMessageMember('${id}','${item.postById}','${item.displayName}',event);" ><fmt:message key="portlet.label.sendMessage"/></a>
<br/>
</span>
</td>
<td class='portlet-table-td-left'>
<span class="portlet-note"><fmt:message key="portlet.label.posted"/>: ${item.date}</span>
<light:authorize role="ROLE_ADMIN"> 
{if item.topId == 0 }
<input type="image" title='<fmt:message key="portlet.label.deleteAllPosts"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11' name="${item.id}" onClick="document.pressed='deleteTopic';document.parameter=this.name;"/>
{else}
<input type="image" title='<fmt:message key="portlet.label.deletePost"/>' src="<%= request.getContextPath() %>/light/images/deleteLink.gif" height='11' width='11' name="${item.id}" onClick="document.pressed='delete';document.parameter=this.name;"/>
{/if}
</light:authorize>
<br/><br/>
${item.content}
</td>
<td class='portlet-table-td-right' width='140px'>
<br/>
<input type='button' onClick="javascript:Light.executeAction('${id}','','quote',null,'${item.id}','edit',null,null);window.scrollTo(0,0);" value='<fmt:message key="portlet.button.quote"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeAction('${id}','','replybegin',null,'${item.id}','edit',null,null);window.scrollTo(0,0);" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
</td>
</tr>
{/for}
<tr>
<td class='portlet-table-td-left'></td>
<td class='portlet-table-td-left' colspan='2' style='padding:10px;'>
<input type='button' onClick="javascript:Light.executeAction('${id}','','replybegin',null,'${forum.topicId}','edit',null,null);window.scrollTo(0,0);" value='<fmt:message key="portlet.button.reply"/>' class='portlet-form-button' />
<input type='button' onClick="javascript:Light.executeRender('${id}','view','normal','categoryId=${forum.categoryId};forumId=${forum.forumId}')" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
{/if}
</form>
</textarea>
</fmt:bundle>
