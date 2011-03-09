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
<textarea id="optionsLocalContent.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/optionsHeader.jsp"%>
<br/>
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:forEach var="region" items="${requestScope.regions}" varStatus="status">
<c:if test='${status.index % 3 == 0}'>
<tr>
</c:if>
<td class='portlet-table-td-left' width='33%'>
<input TYPE='radio' name='region' value='<c:out value="${region.id}"/>'
{if region == '<c:out value="${region.id}"/>' } checked="checked" {/if}
>
<c:out value="${region.desc}"/></input>
</td>
<c:if test='${status.index % 3 == 2}'>
</tr>
</c:if>
</c:forEach>
<c:if test='${requestScope.regionCount % 3 != 0}'>
</tr>
</c:if>
</table>

<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.saveRegion('${id}',true);Light.closePortlet('${id}');" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" />
<input name='Apply' type='button' value='<fmt:message key="portlet.button.apply"/>' class='portlet-form-button'
 onclick="javascript:Light.saveRegion('${id}',false);" />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>