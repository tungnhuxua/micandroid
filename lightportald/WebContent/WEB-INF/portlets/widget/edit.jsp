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
<form method="post" action="<portlet:actionURL/>">
<fmt:bundle basename="resourceBundle">
	<table>
        <tr class="portlet-msg-alert">
            <td colspan="2"><%= request.getAttribute("message") != null ?  request.getAttribute("message") : ""%></td>
        </tr>       
		<tr>
		<td class='portlet-table-td-left'>Title:</td>
		<td class='portlet-table-td-left'><input type='text' name='title' class='portlet-form-input-field' value="<%= request.getAttribute("iframeTitle") %>" size="32"/>
		</td>
		</tr>
		<tr valign='top'>
			<td class="portlet-table-td-left">Summary:</td>
			<td class="portlet-table-td-left"><textarea name='summary' class='portlet-form-textarea-field' rows='2' cols='30' ><c:out value="${requestScope.summary}" /></textarea></td>
		</tr>
		<tr>
			<td class="portlet-table-td-left">Width (px or %):</td>
			<td class="portlet-table-td-left"><input type="text" name="width" class='portlet-form-input-field' value="<%= request.getAttribute("iframeWidth") %>"/></td>
		</tr>
        <tr>
			<td class="portlet-table-td-left">Height (px):</td>
			<td class="portlet-table-td-left"><input type="text" name="height" class='portlet-form-input-field' value="<%= request.getAttribute("iframeHeight") %>"/></td>
		</tr>        
		<tr>
			<td class="portlet-table-td-left">Max Height (px or %):</td>
			<td class="portlet-table-td-left"><input type="text" name="maxHeight" class='portlet-form-input-field' value="<%= request.getAttribute("iframeMaxHeight") %>"/></td>
		</tr>
		<tr valign='top'>
			<td class="portlet-table-td-left">Widget Embed Code:</td>
			<td class="portlet-table-td-left"><textarea name='widgetCode' class='portlet-form-textarea-field' rows='3' cols='30' ><c:out value="${requestScope.widgetCode}" /></textarea></td>
		</tr>
        <tr>
			<td class='portlet-table-td-right' colspan ='2'>
			<input type='submit' name='action' onClick="document.pressed='save';document.mode='view';" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
			<input type='button' name='action' onClick="javascript:Light.executeRender('<c:out value="${requestScope.responseId}"/>','view','normal','');" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
			</td>
        </tr>
    </table>
</fmt:bundle>
</form>