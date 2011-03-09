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
<textarea id="optionsGeneral.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/optionsHeader.jsp"%>
<br/>
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-right' width='200'><fmt:message key="portlet.label.textFont"/>:</td>
<td class='portlet-table-td-left'>
<select name='pcTextFont' size='1' class='portlet-form-select' style="width:100px;">
<c:forEach var="font" items="${requestScope.fonts}">
<option value='<c:out value="${font}"/>' style='font-family:<c:out value="${font}"/>;'
{if textFont == '<c:out value="${font}"/>' } selected='selected' {/if}
><c:out value="${font}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class='portlet-table-td-right'><fmt:message key="portlet.label.textColor"/>:</td>
<td class='portlet-table-td-left'>
<select size="1" name='pcTextColor' class="portlet-form-select" style="width:100px;" onchange="javascript:this.style.backgroundColor=this.value;" style='background-color:<c:out value="${requestScope.portal.textColor}"/>;'>
<c:forEach var="color" items="${requestScope.colors}">
<option value='<c:out value="${color.id}"/>' style='background-color:<c:out value="${color.id}"/>'
{if textColor == '<c:out value="${color.id}"/>' } selected='selected' {/if}
>
<c:out value="${color.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' ><fmt:message key="portlet.label.fontSize"/>:</td>
<td class='portlet-table-td-left'>
<select name='ptFontSize' size='1' class='portlet-form-select' style="width:100px;">
<c:forEach var="fontSize" items="${requestScope.fontSizes}">
<option value='<c:out value="${fontSize.id}"/>' 
{if fontSize == '<c:out value="${fontSize.id}"/>' } selected='selected' {/if}
><c:out value="${fontSize.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class='portlet-table-td-right' ><fmt:message key="portlet.label.maxShowTabs"/>:</td>
<td class='portlet-table-td-left'>
<select name='ptMaxShowTabs' size='1' class='portlet-form-select' style="width:100px;">
<c:forEach var="number" items="${requestScope.maxShowTabsNumber}">
<option value='<c:out value="${number}"/>' 
{if maxShowTabs == '<c:out value="${number}"/>' } selected='selected' {/if}
><c:out value="${number}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td/>
<td class='portlet-table-td-left'>
<input type='checkbox' name='showSearchBar' value='${showSearchBar}' class='portlet-form-checkbox'
{if showSearchBar == true} checked="checked" {/if}
>
<fmt:message key="portlet.label.showSearchBar"/></input> 
</td>
</tr>
<tr>
<td/>
<td class='portlet-table-td-left'>
<input type='checkbox' name='transparent' value='${transparent}' class='portlet-form-checkbox'
{if transparent == true} checked="checked" {/if}
>
<fmt:message key="portlet.label.transparent"/></input> 
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.saveGeneral('${id}');Light.closePortlet('${id}');" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" /> 
<input name='Save' type='button' value='<fmt:message key="portlet.button.apply"/>' class='portlet-form-button'
 onclick="javascript:Light.saveGeneral('${id}');" />
<input name='default' type='button' value='<fmt:message key="portlet.button.default"/>' class='portlet-form-button'
 onclick="javascript:Light.defaultGeneral('${id}');" />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>