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
<pre id="microblogAddPortlet.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin:0 0 10px 0;'>
<tr valign='top'>
<td class='portlet-table-td-left' width='90%'>
<label class='portlet-title' style='font-size:150%'><b>
<light:authenticateOwner>
<fmt:message key="portlet.label.status.title"></fmt:message>
</light:authenticateOwner>
<light:authenticateNotOwner>
<fmt:message key="portlet.label.status.visit"><fmt:param value="${visitedUser.displayName}"/></fmt:message>
</light:authenticateNotOwner>
</b></label>
</td>
<td class='portlet-table-td-right' width='10%'>
<b><label id='${id}_counter' style='font-size:150%;color:#CCCCCC;'>140</label></b>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-left' colspan='2'>
<textarea id='${id}_content' class='portlet-form-textarea-field' rows='2' cols='25' style='width:100%;' onkeyup="javascript:Light.validateMicroblog('${id}');"></textarea>
</td>
</tr>
<tr valign='middle'>
<td class='portlet-table-td-left' width='90%'>
<span class="portlet-footer"><font color='#777777'><fmt:message key="portlet.label.status.tip"></fmt:message></font></span>
</td>
<td class='portlet-table-td-left' width='10%'>
<input type='button' name='action' onClick="javascript:if(Light.securityCheck())Light.addMicroblog('${id}');" value='<fmt:message key="portlet.button.post"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</pre>

<textarea id="status.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%' style='margin:10px 0 20px 0;'>
<light:authenticateOwner> 
<tr valign='top' style="height:40px;" 
	onmouseover="javascript:this.className='theme-bgcolor';this.getElementsByTagName('div')[0].style.opacity=100;"
	onmouseout="javascript:this.className='';this.getElementsByTagName('div')[0].style.opacity=0;">
<td class='portlet-table-td-center' colspan='4'>
<div style="opacity:0;padding:10px;">
<span class='portlet-item'>
{if type == 0 }
<b>
{/if}
<a href='javascript:;' onclick="javascript:Light.configMicroblog('${id}',0);">
<fmt:message key="portlet.label.status.setting.all"><fmt:param value="${user.displayName}"/></fmt:message>
</a>
{if type == 0 }
</b>
{/if}
</span>
<span class='portlet-item'>
{if type == 1 }
<b>
{/if}
<a href='javascript:;' onclick="javascript:Light.configMicroblog('${id}',1);">
<fmt:message key="portlet.label.status.setting.me"><fmt:param value="${user.displayName}"/></fmt:message>
</a>
{if type == 1 }
</b>
{/if}
</span>
<span class='portlet-item'>
{if type == 2 }
<b>
{/if}
<a href='javascript:;' onclick="javascript:Light.configMicroblog('${id}',2);">
<fmt:message key="portlet.label.status.setting.friends"></fmt:message>
</a>
{if type == 2 }
</b>
{/if}
</span>
</div>
</td>
</tr>
</light:authenticateOwner>

{for item in microblogs}
<tr valign='top' onmouseover="javascript:this.className='theme-bgcolor';this.getElementsByTagName('div')[1].style.opacity=100;" onmouseout="javascript:this.className='';this.getElementsByTagName('div')[1].style.opacity=0;">
<td class='portlet-table-td-center' width='70px'> 
{if item.postById == 0}
<span class="portlet-item" style="padding: 0pt;"><div style="position: relative;"><ul style="background: url('<%= request.getContextPath() %>/light/images/no_pic.gif') no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></span> 
{else}
<span class="portlet-item" style="padding: 0pt;"><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'><div style="position: relative;">
<ul style="background: url(<%= request.getContextPath() %>{if item.photoUrl }${item.photoUrl}{else}/light/images/no_pic.gif{/if}) no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></a></span> 
{/if}
</td>
<td class='portlet-table-td-left' colspan='2'>
{if item.postById == 0}
<span class="portlet-item" style="padding: 0pt;"><b><label style="font-size: 12px;">${item.displayName}</label></b></span> 
{else}
<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${item.uri}'>${item.displayName}</a></b></span> 
{/if}: 
${item.content}
<br/>
<span class="portlet-note"><fmt:message key="portlet.label.date"/>: ${item.date} 
<a href='javascript:;' title='<fmt:message key="portlet.label.comment.tip"/>' onclick="javascript:addObjectComment(event,'${id}','${item.id}',<%= org.light.portal.util.Constants._OBJECT_TYPE_MICROBLOG %>);"><fmt:message key="portlet.label.comment"/></a>
</span> 
</td>
<td class='portlet-table-td-left' width='5%'>
{if item.userId == <c:out value="${sessionScope.user.id}"/>}
<div class="icons hand" style="margin:5px;opacity:0;" >
<span class="icons deleteLink" onclick="javascript:Light.executeAction('${id}','','delete',null,'${item.id}','view',null,null);" title='<fmt:message key="portlet.button.delete"/>'></span>
</div>
{/if}
</td>
</tr>

{for comment in item.comments}
<tr valign='top' onmouseover="javascript:this.className='theme-bgcolor';this.getElementsByTagName('div')[1].style.opacity=100;" onmouseout="javascript:this.className='';this.getElementsByTagName('div')[1].style.opacity=0;">
<td class='portlet-table-td-center' width='70px;'></td>
<td class='portlet-table-td-center' width='70px;'>
{if comment.userId == 0}
<span class="portlet-item" style="padding: 0pt;"><div style="position: relative;"><ul style="background: url('<%= request.getContextPath() %>/light/images/no_pic.gif') no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></span> 
{else}
<span class="portlet-item" style="padding: 0pt;"><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${comment.uri}'><div style="position: relative;">
<ul style="background: url(<%= request.getContextPath() %>{if comment.photoUrl }${comment.photoUrl}{else}/light/images/no_pic.gif{/if}) no-repeat scroll 0pt 0pt transparent; list-style-type: none; width: 50px; height: 50px; margin: 0pt 0pt 0pt 10px; padding: 0pt; -moz-border-radius: 8px 8px 8px 8px;"><li style="font-size: 12px;"></li></ul></div></a></span> 
{/if}
</td>
<td class='portlet-table-td-left'>
{if comment.userId == 0}
<span class="portlet-item" style="padding: 0pt;"><b><label style="font-size: 12px;">${comment.displayName}</label></b></span> 
{else}
<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${comment.uri}'>${comment.displayName}</a></b></span> 
{/if}: 
${comment.comment}
<br/>
<span class="portlet-note"><fmt:message key="portlet.label.date"/>: ${comment.date} </span> 
</td>
<td class='portlet-table-td-left' width='5%'>
{if item.userId == <c:out value="${sessionScope.user.id}"/>}
<div class="icons hand" style="margin:5px;opacity:0;" >
<span class="icons deleteLink" onclick="javascript:Light.executeAction('${id}','','deleteComment',null,'${comment.id}','view',null,null);"></span>
</div>
{/if}
</td>
</tr>
{/for}
{/for}
</table>

{if pages > 1 }
 	<div class="portlet-rss" style="clear:both;text-align:right;padding:20px 20px 0 0;">
 	    <label>(<fmt:message key="portlet.label.Results"/> ${start + 1 } -  ${end} <fmt:message key="portlet.label.of"/> ${total})</label>
		{if page > 1 }
			<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page - 1}');"><img src='<%= request.getContextPath() %>/light/images/previous.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.previous"/>' alt='' /></a>						
		{/if}
		{for number in pageNumbers}
			{if number != page}
				<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','page=${number};pages=${pages}');" >${number}</a>
			{/if}
			{if number == page}
				<label class='currentpage'>${number}</label>
			{/if}
		{/for}
		{if page < pages}
			<a href="javascript:;" onclick="javascript:Light.executeRender('${id}','','','page=${page + 1}');"><img src='<%= request.getContextPath() %>/light/images/next.gif' width='16px' height='16px' title='<fmt:message key="portlet.label.next"/>' alt='' /></a>						
		{/if}
	</div>
{/if}
</form>
</textarea>

</fmt:bundle>