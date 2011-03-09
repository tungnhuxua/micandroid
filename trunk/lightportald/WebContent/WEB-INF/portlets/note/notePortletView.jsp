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
<form name="form_<c:out value="${requestScope.responseId}"/>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<light:authenticateOwner>
<textarea name='content'
 style='border-width:0px;width:100%;overflow:visible;background-color: transparent; color:<c:out value="${requestScope.note.color}"/>;'
 rows='<c:out value="${requestScope.note.height}"/>' cols='<c:out value="${requestScope.note.width}"/>'
 onChange="javascript:saveNote('<c:out value="${requestScope.responseId}"/>')"
 onkeyup="javascript:changeNoteRow(event,'<c:out value="${requestScope.responseId}"/>')"><c:out value="${requestScope.note.displayContent}"/></textarea>
</light:authenticateOwner>
<light:authenticateVisitor>
<textarea name='content'
 style='border-width:0px;width:100%;overflow:visible;background-color: transparent; color:<c:out value="${requestScope.note.color}"/>;'
 rows='<c:out value="${requestScope.note.height}"/>' cols='<c:out value="${requestScope.note.width}"/>'
 readonly="true"><c:out value="${requestScope.note.displayContent}"/></textarea>
</light:authenticateVisitor>
<br/>
</td>
</table>
</form>
</body>
</html>