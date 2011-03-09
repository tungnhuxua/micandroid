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
<textarea id="myAccountPrivacy.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/myAccountHeader.jsp"%>
<br/>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='notification' 
{if user.notification == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.notification"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='newsLetter' 
{if user.newsLetter == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.newsLetter"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='fqNel' 
{if user.fqNel == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.fqNel"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='commentNeedApprove' 
{if user.commentNeedApprove == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.commentNeedApprove"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='showTitleToFriends' 
{if user.showTitleToFriends == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.showTitleToFriends"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='showBirthToFriend' 
{if user.showBirthToFriend == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.showBirthToFriend"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='blogCommentFriendOnly' 
{if user.blogCommentFriendOnly == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.blogCommentFriendOnly"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='profileFriendViewOnly' 
{if user.profileFriendViewOnly == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.profileFriendViewOnly"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='noPicForward' 
{if user.noPicForward == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.noPicForward"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='myMusicAutoPlay' 
{if user.myMusicAutoPlay == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.myMusicAutoPlay"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input TYPE='checkbox' name='otherMusucAutoPlay' 
{if user.otherMusucAutoPlay == 1}
checked="yes" 
{/if}
value='1'><fmt:message key="portlet.label.privacy.otherMusucAutoPlay"/></input>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<br/><fmt:message key="portlet.label.privacy.imprivacy"/>: <br/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<input type='radio' name='imprivacy' value='0'
{if user.imprivacy == 0}
checked="yes" 
{/if}
/> <fmt:message key="portlet.label.privacy.imprivacy.public"/><br/>
<input type='radio' name='imprivacy' value='1'
{if user.imprivacy == 1}
checked="yes" 
{/if}
/> <fmt:message key="portlet.label.privacy.imprivacy.friends"/><br/>
<input type='radio' name='imprivacy' value='2'
{if user.imprivacy == 2}
checked="yes" 
{/if}
/> <fmt:message key="portlet.label.privacy.imprivacy.no"/><br/>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'><br/>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='privacy'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>