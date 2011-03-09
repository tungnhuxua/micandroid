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
<c:if test='${requestScope.success != null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-success' >
<c:out value="${requestScope.success}"/>
</td>
</tr>
</table>
</c:if>
<c:if test='${requestScope.error != null}'>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-msg-error' >
<c:out value="${requestScope.error}"/>
</td>
</tr>
</table>
</c:if>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<c:if test='${entity == null}'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.url"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='link' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.name"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='title' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.desc"/>:
</td>
<td class='portlet-table-td-left'>
<textarea name='desc' class='portlet-form-textarea-field' rows='5' cols='35' onfocus="javascript:this.select();">...</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.instructions"/>:
</td>
<td class='portlet-table-td-left'>
<textarea name='instructions' class='portlet-form-textarea-field' rows='5' cols='35' onfocus="javascript:this.select();">...</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.tag"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='tag' class='portlet-form-input-field' size='40'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.width"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='width' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.height"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='height' class='portlet-form-input-field' size='40' />
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan ='2'>
<input type='submit' name='action' onClick="document.pressed='add'" value='<fmt:message key="portlet.button.add"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</c:if>

<c:if test='${entity != null}'>
<input type='hidden' name='id' value='<c:out value="${entity.id}"/>' />
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.url"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='link' class='portlet-form-input-field' size='40' value='<c:out value="${entity.link}"/>' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.name"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='title' class='portlet-form-input-field' size='40' value='<c:out value="${entity.title}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.desc"/>:
</td>
<td class='portlet-table-td-left'>
<textarea name='desc' class='portlet-form-textarea-field' rows='5' cols='35' onfocus="javascript:this.select();"><c:out value="${entity.desc}"/></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.instructions"/>:
</td>
<td class='portlet-table-td-left'>
<textarea name='instructions' class='portlet-form-textarea-field' rows='5' cols='35' onfocus="javascript:this.select();"><c:out value="${entity.instructions}"/></textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.tag"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='tag' class='portlet-form-input-field' size='40' value='<c:out value="${entity.tag}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.width"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='width' class='portlet-form-input-field' size='40' value='<c:out value="${entity.width}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.height"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='height' class='portlet-form-input-field' size='40' value='<c:out value="${entity.height}"/>'/>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' colspan ='2'>
<input type='submit' name='action' onClick="document.pressed='modify'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</c:if>
</form>
</fmt:bundle>
</body>
</html>