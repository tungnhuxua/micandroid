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
<var id="CONTEXT_PATH" title= '<%= request.getContextPath() %>'></var>
<var id='theme' title= '<%= (session.getAttribute("LightPortal") != null && ((org.light.portal.model.Portal)session.getAttribute("LightPortal")).getTheme() != null) ? ((org.light.portal.model.Portal)session.getAttribute("LightPortal")).getTheme() : "theme1"%>'/>
<var id="REQUEST_SUFFIX" title= '<%= org.light.portal.util.PropUtil.getString("portal.request.suffix") %>'></var>

<pre id="chattingInput.jst" style="display:none;">
<form name='form_${id}'>
<table border='0' cellpadding='0' cellspacing='0' width="100%">
<tr>
<td class='portlet-table-td-left' colspan='2'>
<textarea name='chat' class='portlet-form-textarea-field' rows='2' style="width:100%;" onkeypress="return keyDownChat(event,'${id}');"></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'  colspan='2'>
<input type='button' name='action' value='<fmt:message key="portlet.button.send"/>' class='portlet-form-button' onclick="javascript:sendChatMessage('${id}');"/>      
<input type='button' name='action' onClick="javascript:showInviteList('${id}');" value='<fmt:message key="portlet.button.invite"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</pre>

<textarea id="inviteList.jst" style="display:none;">
<form id='inviteList' name='inviteList'>
${content}
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-right'>
<input type='button' name='action' onClick="javascript:inviteBuddysToChat(this.form);hideTopPopupDiv('inviteList');" value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:hideTopPopupDiv('inviteList');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>

<jsp:include page='<%= org.light.portal.util.PropUtil.getString("default.language.page") %>' />
</fmt:bundle>