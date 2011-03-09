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
<html>
<head>
</head>
<body>
<fmt:bundle basename="resourceBundle">
<form name="form_<c:out value="${requestScope.responseId}"/>">
<table border='0' cellpadding='0' cellspacing='0' width="95%">
<tr>
<td class='portlet-table-td-left'>
Feed:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prFeed'  value='<c:out value="${requestScope.feed}"/>' class='portlet-form-input-field' size='30' /> 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
Title:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prTitle'  value='<c:out value="${requestScope.portlet.title}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
Link:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prUrl'  value='<c:out value="${requestScope.portlet.url}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
Image Link:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='prIcon' value='<c:out value="${requestScope.portlet.icon}"/>' class='portlet-form-input-field' size='30'/>
</td>
</tr>

<tr>
<td class='portlet-table-td-right'>
<input name='Submit' type='button' value='Save' class='portlet-form-button'
	onclick="javascript:editViewerPortlet('<c:out value="${requestScope.responseId}"/>');" />
</td>
</tr>						
</table>		
</form>
</fmt:bundle>
</body>
</html>