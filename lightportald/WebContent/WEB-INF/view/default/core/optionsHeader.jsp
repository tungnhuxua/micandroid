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
<table border='0' cellpadding='0' cellspacing='0' width='600'>
<tr class='portlet-table-td-left' width='600'>
<td class='portlet-rss' >
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=general');" >
{if current == 'general' } <b> {/if}
<fmt:message key="portlet.label.general"/>
{if current == 'general' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=theme');" >
{if current == 'theme' } <b> {/if}
<fmt:message key="portlet.label.theme"/>
{if current == 'theme' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=page');" >
{if current == 'page' } <b> {/if}
<fmt:message key="portlet.label.managePage"/>
{if current == 'page' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=language');" >
{if current == 'language' } <b> {/if}
<fmt:message key="portlet.label.language"/>
{if current == 'language' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=localContent');" >
{if current == 'localContent' } <b> {/if}
<fmt:message key="portlet.label.localContent"/>
{if current == 'localContent' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=timeZone');" >
{if current == 'timeZone' } <b> {/if}
<fmt:message key="portlet.label.timeZone"/>
{if current == 'timeZone' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'></span>
</td>
</tr>
<tr class='portlet-table-td-left' width='600'>
<td class='portlet-msg-success' >
<div id='optionsMessage'></div>
</td>
</tr>
</table>