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
<textarea id="optionsTheme.view" style="display:none;">
<br/>
<%@ include file="/WEB-INF/view/default/core/optionsHeader.jsp"%>
<br/>
<form name="form_${id}">
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-left' colspan='5'><fmt:message key="portlet.label.theme"/>:</td>
</tr>
<tr>
<c:forEach var="theme" items="${requestScope.themes}" varStatus="status">
<td class='portlet-table-td-center'>
<a href="javascript:void(0);">
<img src='<%= request.getContextPath() %>/light/theme/<c:out value="${theme.id}"/>/images/theme.gif' height='75' width='75' alt=''
onmousedown="javascript:Light.showTheme(event,'/light/theme/<c:out value="${theme.id}"/>/images/theme.gif','<c:out value="${theme.desc}"/>','${id}');"
onmouseup="javascript:Light.hideTheme();" 
/></a>
<br/>
<c:out value="${theme.desc}"/>
<br/>
<input type='radio' name='ptTheme' value='<c:out value="${theme.id}"/>' class='portlet-form-radio' onclick="javascript:Light.selectTheme('<c:out value="${theme.id}"/>');" 
{if theme == '<c:out value="${theme.id}"/>' } checked='true' {/if}
/>
</td>
</c:forEach>
</tr>
</table>
<br/>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<fmt:message key="portlet.label.bgImage"/>:
<span height='20' width='100' style='' >Repeat</span>
<input type='radio' name='ptRepeat' value='0' 
{if bgRepeat == 0 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >No Repeat</span>
<input type='radio' name='ptRepeat' value='1' 
{if bgRepeat == 1 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >Repeat X</span>
<input type='radio' name='ptRepeat' value='2' 
{if bgRepeat == 2 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >Repeat Y</span>
<input type='radio' name='ptRepeat' value='3' 
{if bgRepeat == 3 } checked='true' {/if} 
/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >Default</span>
<input type='radio' name='ptBg' value='' 
{if bgImage == '' } checked='true' {/if} 
/>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >No Image</span>
<input type='radio' name='ptBg' value='no' 
{if bgImage == 'no' } checked='true' {/if} 
/>
</td>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >
<a href="javascript:void(0);" onclick="javascript:Light.showMoreBgImage(event,'${id}');">More Images</a>
<input type='radio' name='ptBg' value='more' 
{if bgImage != 'no' && bgImage != '' } checked='true' {/if} 
/>
</span>
</td>		
</tr>
</table>
<br/>
<br/>
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<fmt:message key="portlet.label.headerImage"/>:
<span height='20' width='100' style='' >Repeat</span>
<input type='radio' name='ptHeaderRepeat' value='0'
{if headerRepeat == 0 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >No Repeat</span>
<input type='radio' name='ptHeaderRepeat' value='1'
{if headerRepeat == 1 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >Repeat X</span>
<input type='radio' name='ptHeaderRepeat' value='2'
{if headerRepeat == 2 } checked='true' {/if} 
/>
<span height='20' width='100' style='' >Repeat Y</span>
<input type='radio' name='ptHeaderRepeat' value='3'
{if headerRepeat == 3 } checked='true' {/if} 
/>
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >Default</span>
<input type='radio' name='ptHeader' value=''
{if headerImage == '' } checked='true' {/if} 
/>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >No Image</span>
<input type='radio' name='ptHeader' value='no'
{if headerImage == 'no' } checked='true' {/if} 
/>
</td>	
<td class='portlet-table-td-left'>
<span height='20' width='100' style='' >
<a href="javascript:void(0);" onclick="javascript:Light.showMoreHeaderImage(event,'${id}');">More Images</a>
<input type='radio' name='ptHeader' value='more'
{if headerImage != 'no' && headerImage != ''} checked='true' {/if} 
/>
</span>
</td>
</tr>
<tr>
<td class='portlet-table-td-left' colspan='3'>
<input type='checkbox' name='transparent' value='${transparent}' class='portlet-form-checkbox'
{if transparent == 1 } checked='checked' {/if} 
>
<fmt:message key="portlet.label.transparent"/></input> 
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='90%'>
<tr>
<td class='portlet-table-td-right'>
<input name='Save' type='button' value='<fmt:message key="portlet.button.ok"/>' class='portlet-form-button'
 onclick="javascript:Light.saveTheme('${id}',true);Light.closePortlet('${id}');" />
<input name='Cancel' type='button' value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button'
 onclick="javascript:Light.closePortlet('${id}');" />
<input name='Save' type='button' value='<fmt:message key="portlet.button.apply"/>' class='portlet-form-button'
 onclick="javascript:Light.saveTheme('${id}',false);" />
</td>
</tr>
</table>
</form>
</textarea>
</fmt:bundle>