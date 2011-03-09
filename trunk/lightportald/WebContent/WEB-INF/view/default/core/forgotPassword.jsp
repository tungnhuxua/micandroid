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
<form name="form_${id}">
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>
<table border='0' cellpadding='0' cellspacing='0' width='98%' style='margin-top:20px;'>
<tr>
<td class='portlet-table-td-left'>*<LABEL FOR='email' ACCESSKEY='U'><b><fmt:message key="portlet.label.userId"/></b>: </LABEL>
<input type='text' name='email' value='' class='portlet-form-input-field' size='33' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left' >
<br/>
<input type='button' name='action' onClick="javascript:Light.executeAction('${id}','','request','','','edit','','type=forgot');" value='<fmt:message key="portlet.button.requestNewPassword"/>' class='portlet-form-button' />
<input type='button' name='action' onClick="javascript:Light.executeRender('${id}','view','normal','');" value='<fmt:message key="portlet.button.back"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
