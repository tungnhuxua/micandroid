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
<textarea id="myAccountBlock.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/myAccountHeader.jsp"%>
<br/>
{if blockCount == 0}
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<fmt:message key="message.user.block.none"/>
</td>
</tr>
</table>
{/if}
{if blockCount > 0}
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width= '220'>
{for block in blocks}
<tr>
<td class='portlet-table-td-center'>
{if block.photoUrl == ''}
<img src='<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>' align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
{/if}
{if block.photoUrl != ''}
<img src='<%= request.getContextPath() %>${block.photoUrl}' align="middle" width='<c:out value="${sessionScope.org.thumbWidth}"/>' height='<c:out value="${sessionScope.org.thumbHeight}"/>'/>
{/if}
<br/>
<a href='http://<c:out value="${sessionScope.org.userSpacePrefix}"/>${block.uri}' >${block.displayName}</a>
<light:authenticateUser> 
<br/>
<input type='radio' name='blockId' value='${block.id}' />
</light:authenticateUser>
</td>
</tr>
{/for}
<tr>
<td class='portlet-table-td-center'>
<input type='submit' onClick="document.pressed='block';" value='<fmt:message key="portlet.button.unblock"/>' class='portlet-form-button' />
</td>
</tr>
{/if}
</table>
</form>
</textarea>
</fmt:bundle>