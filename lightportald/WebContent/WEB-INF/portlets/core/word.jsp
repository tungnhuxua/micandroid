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
<form name="form_<c:out value="${requestScope.responseId}"/>" action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width="95%">
<tr>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.notKeyword"/>(<c:out value="${notKeywordsTotal}"/>):
<br/><br/>
<select name='keywords' MULTIPLE='MULTIPLE' size='10' class='portlet-form-select' STYLE="width: 180px">
<c:forEach var="keyword" items="${requestScope.notKeywords}" >
<option value='<c:out value="${keyword.word}"/>'><c:out value="${keyword.word}"/></option>
</c:forEach>
</select>
<br/><br/>
<input type='button' name='action' onClick="javascript:Light.addNotKeywords(event,'<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.add"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='deleteNotKeywords';" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
</td>
<td class='portlet-table-td-left'>
<fmt:message key="portlet.label.notWord"/>(<c:out value="${notWordsTotal}"/>):
<br/><br/>
<select name='words' MULTIPLE='MULTIPLE' size='10' class='portlet-form-select' STYLE="width: 120px">
<c:forEach var="word" items="${requestScope.notWords}" >
<option value='<c:out value="${word.word}"/>'><c:out value="${word.word}"/></option>
</c:forEach>
</select>
<br/><br/>
<input type='button' name='action' onClick="javascript:Light.addNotWords(event,'<c:out value="${requestScope.responseId}"/>');" value='<fmt:message key="portlet.button.add"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='deleteNotWords';" value='<fmt:message key="portlet.button.delete"/>' class='portlet-form-button' />
</td>
</tr>				
</table>		
</form>
</fmt:bundle>
</body>
</html>