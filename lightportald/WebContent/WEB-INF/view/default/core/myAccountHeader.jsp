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
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr class='portlet-table-td-left'>
<td class='portlet-rss' >
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=name');" >
{if current == 'name' } <b> {/if}
<fmt:message key="portlet.label.name"/>
{if current == 'name' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'>
<img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=interests');" >
{if current == 'interests' } <b> {/if}
<fmt:message key="portlet.label.interests"/>
{if current == 'interests' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=basic');" >
{if current == 'basic' } <b> {/if}
<fmt:message key="portlet.label.basic"/>
{if current == 'basic' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=background');" >
{if current == 'background' } <b> {/if}
<fmt:message key="portlet.label.background"/>
{if current == 'background' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=password');" >
{if current == 'password' } <b> {/if}
<fmt:message key="portlet.label.changePassword"/>
{if current == 'password' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=privacy');" >
{if current == 'privacy' } <b> {/if}
<fmt:message key="portlet.label.privacy"/>
{if current == 'privacy' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=channels');" >
{if current == 'channels' } <b> {/if}
<fmt:message key="portlet.label.channels"/>
{if current == 'channels' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=block');" >
{if current == 'block' } <b> {/if}
<fmt:message key="portlet.label.blockUsers"/>
{if current == 'block' } </b> {/if}
</a>
<span class='portal-header-menu-item-separater'><img src='<%=request.getContextPath() %>/light/images/spacer.gif' style='border: 0px'/></span>
<a href='javascript:;' onclick="javascript:Light.executeRender('${id}','','','action=invite');" >
{if current == 'invite' } <b> {/if}
<fmt:message key="portlet.label.inviteHistory"/>
{if current == 'invite' } </b> {/if}
</a>
</td>
</tr>
</table>
<%@ include file="/WEB-INF/view/default/core/status.jsp"%>