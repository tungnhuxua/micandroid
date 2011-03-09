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
<%
/**
 * Customized/Extended POrtal Views
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">

<textarea id="peopleDirectoryPortlet.view" style="display:none;">
	<form action="javascript:Light.executeAction('${id}',this.form,'find',null,null,'VIEW','maximized');">
	<table border='0' cellpadding='0' cellspacing='0' width='98%'>
	<tr>
	<td class='portlet-table-td-left'>
	<br/>
	<label><fmt:message key="portlet.label.findMember"/></label>
	</td>
	</tr>
	<tr>
	<td class='portlet-table-td-left'>
	<br/>
	<input type='text' name='keyword' class='portlet-form-input-field-hint' size='32' value='<fmt:message key="portlet.label.findMemberBy"/>' onfocus="javascript:this.value='';"
		 onchange="javascript:this.form['input'].value='1';this.form.submit();" /> 
	<input type='hidden' name='input'  value ='0'/>
	<input name='action' type='submit' class='portlet-form-button' value='<fmt:message key="portlet.button.go"/>'/>
	</td>
	</tr>
	</table>
	</form>
</textarea>

</fmt:bundle>