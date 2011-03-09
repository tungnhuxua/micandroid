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
            <td colspan="2"><fmt:message key="portlet.label.edit"/></td>
        </tr>      
        <tr>
			<td class="portlet-table-td-left">Article URL:</td>
			<td class="portlet-table-td-left"><input type="text" name="url" class='portlet-form-input-field' value="<%= request.getAttribute("url") %>" size="32"/></td>
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