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

<textarea id="connection.detail" style="display:none;">
<span title='${title}' width='100%' style='clear: both; display: block; text-align:right;'><a href='javascript:;' onclick='javascript:hideBuddyDetail();'><img src='<%= request.getContextPath() %>/light/images/close_on.gif'/></a></span>
<table border='0' cellpadding='0' cellspacing='0' >
<tr>
<td class='portlet-table-td-left'>
<input type='button' value='${buttonMessage}' 
	onclick="javascript:hideBuddyDetail();showSendMessageMember('${conn.id}','${conn.userId}','${conn.email}');" class='portlet-form-button'/>
</td>
<td class='portlet-table-td-left'>
{if conn.isFriend == 0 }
<input type='button' value='${buttonAdd}' class='portlet-form-button'
	onclick="javascript:hideBuddyDetail();saveAddBuddy('${conn.userId}','${conn.id}');" />
</td>
{else}
<input type='button' value='${buttonChat}'
{if conn.status == 0}
 disabled='true' class='portlet-form-button-disabled'
{else}
 class='portlet-form-button'
 onclick="javascript:hideBuddyDetail();chatWithBuddy('${conn.userId}');"/>
 {/if}
</td>
<td class='portlet-table-td-left'>
<input type='button' value='${buttonDelete}'
	onclick="javascript:hideBuddyDetail();deleteBuddy('${conn.userId}','${conn.id}');" class='portlet-form-button'/>
</td>
{/if}
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.name"/>:
</td>
<td class='portlet-table-td-left'>
${conn.name}
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.email"/>:
</td>
<td class='portlet-rss'>
<a href='mailto:${conn.email}'>${conn.email}</a>
</td>
</tr>
</table> 
<form name='buddyDetail_${conn.id}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr><td class='portlet-table-td-left'>
<fmt:message key="portlet.label.type"/>:
</td>
<td class='portlet-table-td-left'>
<select name="friendType" size="1" class="portlet-form-select">
<option value="0" {if conn.type == 0} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.friend"/></option>
<option value="1" {if conn.type == 1} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.family"/></option>
<option value="2" {if conn.type == 2} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.closeFriend"/></option>
<option value="3" {if conn.type == 3} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.classmate"/></option>
<option value="4" {if conn.type == 4} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.colleague"/></option>
</select>
</td>
<td class='portlet-table-td-left'>
<input type='button' value='${buttonSave}'
	onclick="javascript:saveBuddyType('${conn.userId}','${conn.id}');" class='portlet-form-button'/>	
</td></tr>	
</table>
</form> 						
</textarea>

<textarea id="connection.edit" style="display:none;">
<form name='form_${id}' action="javascript:;">
{if success }
<div class='portlet-msg-success' >
${success}
</div>
{/if}
{if error }
<div class='portlet-msg-error' >
${error}
</div>
{/if}
<table border='0' cellpadding='0' cellspacing='0' width='95%'>
<tr>
<td class='portlet-table-td-left' colspan='2'>
<input name="showType" value="0" class="portlet-form-radio" type="radio" {if conn.currentShowType == 0} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.all"/></input>
<br>
<input name="showType" value="1" class="portlet-form-radio" type="radio" {if conn.currentShowType == 1} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.online"/></input>
<br>
<input name="showType" value="2" class="portlet-form-radio" type="radio" {if conn.currentShowType == 2} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.updatedRecently"/></input>
<br>
<input name="showType" value="3" class="portlet-form-radio" type="radio" {if conn.currentShowType == 3} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.location"/></input>
<br>
<input name="showType" value="4" class="portlet-form-radio" type="radio" {if conn.currentShowType == 4} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.type"/></input>
<br>
<input name="showType" value="5" class="portlet-form-radio" type="radio" {if conn.currentShowType == 5} checked="checked" {/if}>
<fmt:message key="portlet.label.connection.show.type.is"/></input>
<select name="friendType" size="1" class="portlet-form-select">
<option value="0" {if conn.currentFriendType == 0} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.friend"/></option>
<option value="1" {if conn.currentFriendType == 1} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.family"/></option>
<option value="2" {if conn.currentFriendType == 2} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.closeFriend"/></option>
<option value="3" {if conn.currentFriendType == 3} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.classmate"/></option>
<option value="4" {if conn.currentFriendType == 4} selected="selected" {/if}><fmt:message key="portlet.label.connection.type.colleague"/></option>
</select>
<br>
</td>
</tr>

<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.showItems"/>:
</td>
<td class='portlet-table-td-left'>
<select name='items' size='1'  class='portlet-form-select' STYLE="width: 80px">
<option value='0' {if conn.showNumber == 0} selected="selected" {/if}><fmt:message key="portlet.label.all"/></option>
<c:forEach var="i" begin="1" end="50" step="1">
<option value='<c:out value="${i}" />' {if conn.showNumber == <c:out value="${i}" /> } selected="selected" {/if}><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.showColumns"/>:
</td>
<td class='portlet-table-td-left'>
<select name='columns' size='1'  class='portlet-form-select'  STYLE="width: 80px">
<c:forEach var="i" begin="1" end="50" step="1">
<option value='<c:out value="${i}" />' {if conn.columnNumber == <c:out value="${i}" /> } selected="selected" {/if}><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan='2' >
<input type='submit' name='action' onClick="javascript:document.resetLastAction='1';Light.executeAction('${id}',this.form,'config',null,null,'VIEW','normal');" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<textarea id="connection.help" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0">
<tr>
<td class="portlet-table-td-left">
<p>
My Connections is built for members to communicate each other easily.
For use this function, you have to add your connections first which has to be 
registered member. 
</p>
<p>
After you added your connections, System will detect whether your connection is online or not, 
if your connection is online, you can start to chat with your connection. If your connection is offline, 
you can send a message to your connection.
</p>
</td>
</tr>    
</table>
</textarea>

</fmt:bundle>