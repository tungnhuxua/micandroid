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
<table border='0' cellpadding='0' cellspacing='0' width="95%" >
<tr>
<td class='portlet-table-td-left' width="50%"><fmt:message
key="portlet.label.title"/>:
</td>
<td class='portlet-table-td-left'>
<input type='text' name='pcTitle' value='<c:out
value="${requestScope.portlet.title}"/>'
class='portlet-form-input-field' size='16' />
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message
key="portlet.label.titleBgColor"/>:
</td>
<td class='portlet-table-td-left'>
<c:if test="${requestScope.portlet.barBgColor != null}">
<input type='text' name='pcBarBgColor' value='<c:out
value="${requestScope.portlet.barBgColor}"/>'
class='portlet-form-input-field-color' size='10' style='color:<c:out
value="${requestScope.portlet.barBgColor}"/>;background:<c:out
value="${requestScope.portlet.barBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',1);" />
</c:if>
<c:if test="${requestScope.portlet.barBgColor == null}">
<input type='text' name='pcBarBgColor' value='<c:out
value="${requestScope.portlet.barBgColor}"/>'
class='portlet-form-input-field-color' size='10'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',1);" />
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message
key="portlet.label.titleColor"/>:
</td>
<td class='portlet-table-td-left'>
<c:if test="${requestScope.portlet.barBgColor != null &&
requestScope.portlet.barFontColor != null}">
<input type='text' name='pcBarFontColor' value='<c:out
value="${requestScope.portlet.barFontColor}"/>'
class='portlet-form-input-field-color' size='10' style='color:<c:out
value="${requestScope.portlet.barFontColor}"/>;background:<c:out
value="${requestScope.portlet.barBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',2);" />
</c:if>
<c:if test="${requestScope.portlet.barBgColor != null &&
requestScope.portlet.barFontColor == null}">
<input type='text' name='pcBarFontColor' value='<c:out
value="${requestScope.portlet.barFontColor}"/>'
class='portlet-form-input-field-color' size='10'
style='background:<c:out value="${requestScope.portlet.barBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',2);" />
</c:if>
<c:if test="${requestScope.portlet.barBgColor == null &&
requestScope.portlet.barFontColor != null}">
<input type='text' name='pcBarFontColor' value='<c:out
value="${requestScope.portlet.barFontColor}"/>'
class='portlet-form-input-field-color' size='10' style='color:<c:out
value="${requestScope.portlet.barFontColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',2);" />
</c:if>
<c:if test="${requestScope.portlet.barBgColor == null &&
requestScope.portlet.barFontColor == null}">
<input type='text' name='pcBarFontColor' value='<c:out
value="${requestScope.portlet.barFontColor}"/>'
class='portlet-form-input-field-color' size='10'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',2);" />
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message
key="portlet.label.contentBgColor"/>:
</td>
<td class='portlet-table-td-left'>
<c:if test="${requestScope.portlet.contentBgColor != null}">
<input type='text' name='pcContentBgColor' value='<c:out
value="${requestScope.portlet.contentBgColor}"/>'
class='portlet-form-input-field' size='10' style='color:<c:out
value="${requestScope.portlet.contentBgColor}"/>;background:<c:out
value="${requestScope.portlet.contentBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',3);" />
</c:if>
<c:if test="${requestScope.portlet.contentBgColor == null}">
<input type='text' name='pcContentBgColor' value='<c:out
value="${requestScope.portlet.contentBgColor}"/>'
class='portlet-form-input-field' size='10'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',3);" />
</c:if>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'><fmt:message
key="portlet.label.textColor"/>:
</td>
<td class='portlet-table-td-left'>
<c:if test="${requestScope.note.color != null}">
<input type='text' name='pcTextColor' value='<c:out
value="${requestScope.note.color}"/>' class='portlet-form-input-field'
size='10' style='color:<c:out
value="${requestScope.note.color}"/>;background:<c:out
value="${requestScope.portlet.contentBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',4);" />
</c:if>
<c:if test="${requestScope.note.color == null}">
<input type='text' name='pcTextColor' value='#000000'
class='portlet-form-input-field' size='10'
style='color:#000000;background:<c:out
value="${requestScope.portlet.contentBgColor}"/>;'/>
<input name='pick' type='button' value='...' class='portlet-form-button'
 onclick="javascript:pickColor(event,'<c:out
value="${requestScope.responseId}"/>',4);" />
</c:if>
</td>
</tr>
<tr>
<td class='cright' colspan='2'>
<input name='Submit' type='button' value='<fmt:message
key="portlet.button.save"/>' class='portlet-form-button'
 onclick="javascript:configNote('<c:out
value="${requestScope.responseId}"/>');" />
<input name='Submit' type='button' value='<fmt:message
key="portlet.button.defaultColor"/>' class='portlet-form-button'
 onclick="javascript:resetNote('<c:out
value="${requestScope.responseId}"/>');" />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>