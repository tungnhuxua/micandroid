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
<textarea id="optionsLanguage.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/optionsHeader.jsp"%>
<br/>
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='95%' style='margin:10px;'>
<c:forEach var="language" items="${applicationScope.languages}" varStatus="status">
<c:if test='${status.index % 3 == 0}'>
<tr>
</c:if>
<td class='portlet-table-td-left' width='33%'>
<c:if test='${language.supported}'>
<input type='radio' name='language' value='<c:out value="${language.id}"/>'
<c:if test='${sessionScope.currentLocale == language.id}'>
checked="checked"
</c:if>
>
<label><c:out value="${language.desc}"/></label></input>
</c:if>
<c:if test='${!language.supported}'>

<label style="padding:3px 0 0 25px;"><c:out value="${language.desc}"/></label>
</c:if>
</td>
<c:if test='${status.index % 3 == 2}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${applicationScope.languageCount % 3 != 0}'>
</tr>
</c:if>
<tr>
<td class='portlet-table-td-right' colspan='3' style="padding:20px;">
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.saveLanguage('${id}',true);Light.closePortlet('${id}');" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" />
<input name='Apply' type='button' value='<fmt:message key="portlet.button.apply"/>' class='portlet-form-button'
 onclick="javascript:Light.saveLanguage('${id}',false);" />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>