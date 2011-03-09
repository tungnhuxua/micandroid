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
<textarea id="optionsPage.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/optionsHeader.jsp"%>
<br/>
{if authorized == 1 }
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
{if error != '' }
<tr valign='top'>
<td class="portlet-table-td-right"></td>
<td class='portlet-msg-error'>
${error}
</td>
</tr>
{/if}
{if parentId > 0 }
<tr valign='top'>
<td class="portlet-table-td-right"></td>
<td class="portlet-table-td-left">
<span class="portlet-rss" >
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=parentPage');" ><fmt:message key="portlet.label.editParentPage"/></a>
</span>
</td>
</tr>
{/if}
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.pageLink"/>:</td>
<td class="portlet-table-td-left">
<span class="portlet-rss" > 
{if hasUrl > 0 }
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabUrl}'>http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabUrl}</a>
{/if}
{if hasUrl == 0 }
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabId}'>http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabId}</a>
<input type='text' name='url'  value='' class='portlet-form-input-field' size='12' onChange="javascript:Light.editTabUrl('${id}');" /> 
<input name='edit' type='button' value='<fmt:message key="portlet.button.edit"/>' class='portlet-form-button' onclick="javascript:Light.editTabUrl('${id}');" />
{/if}
</span>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.title"/>:</td>
<td class="portlet-table-td-left">
<input type='hidden' name='tabId' value='${tabId}' />
<input name="ptTitle" value='${title}' class="portlet-form-input-field" size="16" type="text">
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.contentSkin"/>:</td>
<td class="portlet-table-td-left">
<select name="ptWindow" size="1" class="portlet-form-select">
<c:forEach var="windowSkin" items="${requestScope.windowSkins}" varStatus="status">
<option value='<c:out value="${windowSkin.id}"/>'
{if windowSkin == '<c:out value="${windowSkin.id}"/>' } selected="selected" {/if}
>
<c:out value="${windowSkin.desc}"/></option>
</c:forEach>
</select>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.allowClosed"/>:</td>
<td class="portlet-table-td-left">
<input name="ptClose" type="checkbox"
{if closeable == 1 } checked="checked" {/if}
/>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.defaultTab"/>:</td>
<td class="portlet-table-td-left">
<input name="ptDefault" type="checkbox"
{if defaulted } checked="checked" {/if}
/>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.privacy.page"/>:</td>
<td class="portlet-table-td-left">
<input type='radio' name='ptStatus' value='0' class='portlet-form-radio'
{if status == 0 } checked="checked" {/if}
>
<fmt:message key="portlet.label.privacy.onlyMe"/></input> 
<input type='radio' name='ptStatus' value='1' class='portlet-form-radio'
{if status == 1 } checked="checked" {/if}
>
<fmt:message key="portlet.label.privacy.connections"/></input> 
<input type='radio' name='ptStatus' value='2' class='portlet-form-radio'
{if status == 2 } checked="checked" {/if}
>
<fmt:message key="portlet.label.privacy.member"/></input> 
<input type='radio' name='ptStatus' value='3' class='portlet-form-radio'
{if status == 3 } checked="checked" {/if}
>
<fmt:message key="portlet.label.privacy.space"/></input> 
<input type='radio' name='ptStatus' value='4' class='portlet-form-radio'
{if status == 4 } checked="checked" {/if}
>
<fmt:message key="portlet.label.privacy.public"/></input> 
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.fitScreen"/>:</td>
<td class="portlet-table-td-left">
<input name="ptFitScreen" type="checkbox"
{if fitScreen == 1 } checked="checked" {/if}
/>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.allowColumns"/>:</td>
<td class="portlet-table-td-left">
<select name="ptColumns" size="1" class="portlet-form-select" onchange="javascript:Light.autoChangeTabWidths('${id}');">
<c:forEach var="i" begin="1" end="10" step="1">
<option value='<c:out value="${i}" />' 
{if columnTotal == <c:out value="${i}" /> } selected="selected" {/if}
><c:out value="${i}" /></option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.columnsWidth"/>:</td>
<td class="portlet-table-td-left">
<input name="ptWidths" value="${widths}" class="portlet-form-input-field" size="24" type="text">
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.columnBetween"/>:</td>
<td class="portlet-table-td-left">
<input name="ptBetween" value="${between}" class="portlet-form-input-field" size="10" type="text">
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.manageTab('${id}');Light.closePortlet('${id}');" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" /> 
<input name='Apply' type='button' value='<fmt:message key="portlet.button.apply"/>' class='portlet-form-button'
 onclick="javascript:Light.manageTab('${id}');" />
</td>
</tr>
</table>
</form>
{/if}
{if authorized == 0 }
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr valign='top'>
<td class="portlet-table-td-right"></td>
<td class='portlet-msg-error'>
<fmt:message key="error.page.permission.update"/>
</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.title"/>:</td>
<td class="portlet-table-td-left">${title}</td>
</tr>
<tr valign='top'>
<td class="portlet-table-td-right"><fmt:message key="portlet.label.pageLink"/>:</td>
<td class="portlet-table-td-left">
<span class="portlet-rss" style='padding:0;' > 
{if hasUrl > 0 }
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabUrl}'>http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabUrl}</a>
{/if}
{if hasUrl == 0 }
<a href='http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabId}'>http://www.<c:out value="${sessionScope.org.webId}"/>/page/${tabId}</a>
{/if}
</span>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" /> 
</td>
</tr>
</table>
{/if}
</textarea>
</fmt:bundle>