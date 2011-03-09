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
<pre id="myAccountInterests.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/myAccountHeader.jsp"%>
<br/>
<form name="form_${id}" action="javascript:Light.executeAction('${id}',document.currentForm,document.pressed,document.pressedName,document.parameter);">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.headline"/>: 
<input type='text' name='headline' value='${userProfile.headline}' class='portlet-form-input-field' size='64' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.aboutMe"/>: 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='aboutMe' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.aboutMe}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.likeToMeet"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='likeToMeet' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.likeToMeet}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.interests"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='interests' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.interests}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.music"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='music' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.music}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.movies"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='movies' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.movies}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.television"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='television' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.television}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.books"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='books' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.books}</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message key="portlet.label.heroes"/>: </td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<textarea name='heroes' class='portlet-form-textarea-field' rows='5' cols='80' >${userProfile.heroes}</textarea>
</td>
</tr>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='80%'>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='interests'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</pre>
</fmt:bundle>