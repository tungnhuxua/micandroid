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
<textarea id="myAccountName.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/myAccountHeader.jsp"%>
<br/>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr valign="middle">
<td class='portlet-table-td-right'><fmt:message key="portlet.label.displayName"/>:</td>
<td class='portlet-table-td-left'>
<input type='text' name='displayName' class='portlet-form-input-field' size='60' value='${user.displayName}'/>
</td>
</tr>
<br/>
<tr>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.fullName"/>: </td>
<td class='portlet-table-td-left'>
<input type='text' name='firstName' value='${userProfile.firstName}' class='portlet-form-input-field' size='60' /> 
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='name'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>