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

<textarea id="connection.view" style="display:none;">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<light:authenticateUser> 
<tr>
<td class='portlet-link-left' >
<span class="icons hand" title='<fmt:message key="portlet.button.addBuddy"/>'>
<span class="icons add" style="margin-top:3px;" onclick="javascript:Light.globalSearch('','org.light.portal.model.User');"></span>
</span>
<a href='javascript:;' onclick="javascript:Light.globalSearch('','org.light.portal.model.User');" ><fmt:message key="portlet.button.addBuddy"/></a>
</td>
</tr>
</light:authenticateUser>
{if conn.currentDesc }
<tr>
<td class='portlet-rss'>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=back','',true,false);" ><fmt:message key="portlet.label.location"/></a>
->
${conn.currentDesc}
</td>
</tr>
{/if}
{if conn.currentShowType == 5 }
<tr>
<td class='portlet-rss'>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=back','',true,false);" ><fmt:message key="portlet.label.type"/></a>
->
{if conn.currentFriendType == 0}<fmt:message key="portlet.label.connection.type.friend"/>{/if}
{if conn.currentFriendType == 1}<fmt:message key="portlet.label.connection.type.family"/>{/if}
{if conn.currentFriendType == 2}<fmt:message key="portlet.label.connection.type.closeFriend"/>{/if}
{if conn.currentFriendType == 3}<fmt:message key="portlet.label.connection.type.classmate"/>{/if}
{if conn.currentFriendType == 4}<fmt:message key="portlet.label.connection.type.colleague"/>{/if}
</td>
</tr>
{/if}
</table>

{if conn.buddyLocations }
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
{for buddyLocation in conn.buddyLocations }
<tr valign='top'>
<td class='portlet-table-td-left' width= '100%'>
<span class="portlet-rss">
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=location;location=${buddyLocation.id}','',true,false);">
${buddyLocation.id} (${buddyLocation.desc})
</a>
</span>
</td>
</tr>
{/for}
</table>
{/if}

{if conn.buddyTypes }
<table border='0' cellpadding='0' cellspacing='0' width= '100%' >
{for buddyType in conn.buddyTypes }
<tr valign='top'>
<td class='portlet-table-td-left' width= '100%'>
<span class="portlet-rss">
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=type;friendType=${buddyType.type}','',true,false);">
${buddyType.title} (${buddyType.count})
</a>
</span>
</td>
</tr>
{/for}
</table>
{/if}

{if conn.buddys }
<table border='0' cellpadding='0' cellspacing='0' width='100%' >
{for member in conn.buddys }
{if member_index % conn.columnNumber == 0}
<tr valign='top'>
{/if}
<td class='portlet-table-td-left' width='${100 / conn.columnNumber}%'>
<span class='portlet-item'>
{if member.buddyCurrentStatusId == 1}
<img src="<%= request.getContextPath() %>/light/images/online.gif" width='15' height='8' align="bottom" alt=''/>
{/if}
{if member.buddyCurrentStatusId == 0}
<span style='margin:0 0 0 15px;'></span>
{/if}
<span class="portlet-item" style="padding: 0pt;"><b><a style="font-size: 12px;" href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${member.uri}'>${member.displayName}</a></b></span> 
</span>
<light:authenticateOwner> 
<a href="javascript:;" onclick="javascript:showBuddyDetail(event,'${member.buddyUserId}','${id}');">
</light:authenticateOwner> 
<light:authenticateNotOwner> 
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${member.uri}'>
</light:authenticateNotOwner> 
{if member.photoUrl }
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %>${member.photoUrl}) no-repeat scroll 0 0; list-style-type: none; width:50px; height:50px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
{else}
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:50px; height:50px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
{/if}
</a>
</td>
{if member_index % conn.columnNumber == conn.columnNumber - 1}
</tr>
{/if}
{/for}
{if conn.showNumber % conn.columnNumber != 0}
</tr>
{/if}
</table>
{/if}
</textarea>

</fmt:bundle>